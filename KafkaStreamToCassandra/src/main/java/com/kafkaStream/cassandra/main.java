package com.kafkaStream.cassandra;

import com.datastax.driver.core.Session;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

public class main {
    public static void main(String[] args) {
        // load the external props
        Properties props = new Properties();
        if(args==null) throw new RuntimeException("\nargs cannot be empty for properties file...\n");
        else {
            try (InputStream input = new FileInputStream(args[0])) { props.load(input);}
            catch (IOException io) { io.printStackTrace(); }
        }

        // config kafka consumer
        props.put("bootstrap.servers", props.getProperty("consumer.bootstrap.servers"));
        props.put("key.deserializer", props.getProperty("consumer.key.deserializer"));
        props.put("value.deserializer", props.getProperty("consumer.value.deserializer"));
        props.put("group.id", props.getProperty("consumer.group.id"));
        props.put("enable.auto.commit", props.getProperty("consumer.enable.auto.commit"));
        props.put("auto.commit.interval.ms", props.getProperty("consumer.auto.commit.interval.ms"));
        props.put("session.timeout.ms", props.getProperty("consumer.session.timeout.ms"));
        props.put("max.poll.records", props.getProperty("consumer.max.poll.records"));

        // create the kafka consumer instance with the topic and props
        String consumerTopic = props.getProperty("sink.topic");
        KafkaConsumer<String, Long> consumer = new KafkaConsumer<String, Long>(props);
        consumer.subscribe(Arrays.asList(consumerTopic));

        // create connection and session of the cassandra database
        CassandraConnect connect = new CassandraConnect();
        connect.connectToCluster(props.getProperty("cassandra.node.url"));
        Session session = connect.getSession();

        // starts to consume message
        try{
            // execute keyspace and table creation for further data sinking
            String keySpaceQuery = new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ").append(props.getProperty("cassandra.key.space")).append(" WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1 };").toString();
            session.execute(keySpaceQuery);

            String tableName = props.getProperty("cassandra.key.space") + "." + props.getProperty("cassandra.table");
            String createTableQuery = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(tableName).append("(local_timestamp timestamp, kafka_topic TEXT, tweet_topic TEXT, tweet_topic_count BIGINT, PRIMARY KEY (tweet_topic))").toString();
            session.execute(createTableQuery);

            String insertTableQuery = new StringBuilder("INSERT INTO ").append(tableName).append(" (local_timestamp, kafka_topic, tweet_topic, tweet_topic_count) values (?,?,?,?)").toString();

            // write data to cassandra and kafka consumer console
            while(true){
                ConsumerRecords<String, Long> records = consumer.poll(Duration.ofSeconds(10));
                for(ConsumerRecord<String, Long> record: records){
                    // print to console
                    System.out.println("TIMESTAMP:" + new Date(record.timestamp())+","+"TOPIC:"+record.topic()+","+"KEY:"+record.key()+","+"VALUE:"+ record.value().toString());

                    // write to cassandra
                    session.execute(insertTableQuery, new Date(record.timestamp()), record.topic(), record.key(), record.value());
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            consumer.close();
            connect.closeCassandraCollect();
        }
    }
}
