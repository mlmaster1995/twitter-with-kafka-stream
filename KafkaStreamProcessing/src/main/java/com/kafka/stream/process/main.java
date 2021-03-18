package com.kafka.stream.process;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.processor.TopicNameExtractor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class main {
    public static void main(String[] args) {
        // load the external props
        Properties props = new Properties();
        if(args==null) throw new RuntimeException("\nargs cannot be empty for properties file...\n");
        else {
            try (InputStream input = new FileInputStream(args[0])) { props.load(input);}
            catch (IOException io) { io.printStackTrace(); }
        }

        // config kafka stream props
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, props.getProperty("application.id"));
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, props.getProperty("bootstrap.servers"));
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass().getName());
        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG,props.getProperty("commit.interval.ms.config"));

        // declare the topology params
        String[] topicList = props.getProperty("tweet.track.list").split(",");
        Duration windowSize = Duration.ofMinutes(2);
        Serdes.StringSerde stringSerde = new Serdes.StringSerde();
        Serdes.LongSerde longSerde = new Serdes.LongSerde();

        // build the stream topology
        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> streamSource = builder.stream(props.getProperty("stream.topic"));
        KTable<Windowed<String>, Long> aggTableWithTopicKey =
                streamSource
                        .selectKey((key,value)->{
                            String newTopic = "";
                            for(String topic: topicList){ if(value.contains(topic)) newTopic = topic;}
                            return newTopic;
                            })
                        .groupByKey()
                        .windowedBy(TimeWindows.of(windowSize).advanceBy(windowSize))
                        .count();
        KStream<String, Long> streamProcessed = aggTableWithTopicKey.toStream((stringWindowed, aLong) -> {return stringWindowed.toString();});

        streamProcessed.to(props.getProperty("sink.topic"), Produced.with(stringSerde, longSerde));

        // load the topology into a kafka stream instance
        KafkaStreams stream = new KafkaStreams(builder.build(), props);

        // set up a clean shutdown
        final CountDownLatch latch = new CountDownLatch(1);
        Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
            @Override
            public void run() {
                stream.close();
                latch.countDown();
            }
        });

        // start streaming
        try {
            stream.cleanUp();
            stream.start();
            latch.await();
        }
        catch (Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
        finally{
            System.exit(0);
        }
    }
}
