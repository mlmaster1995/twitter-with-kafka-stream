package com.kafkaStream.cassandra;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
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

        // starts to consume message
        try{
            while(true){
                ConsumerRecords<String, Long> records = consumer.poll(Duration.ofSeconds(10));
                for(ConsumerRecord<String, Long> record: records){
                    System.out.println("TIMESTAMP:" + new Date(record.timestamp())+","+"TOPIC:"+record.topic()+","+"KEY:"+record.key()+","+"VALUE:"+ record.value().toString());
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            consumer.close();
        }
    }
}
