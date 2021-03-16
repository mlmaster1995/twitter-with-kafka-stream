package com.twitter.producer;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Properties;

public class TwitterKafkaProducer {
    // methods
    public static KafkaProducer<String, String> getStringKafkaProducer(Properties externalProps){
        Properties props = new Properties();

        props.put("bootstrap.servers", externalProps.getProperty("bootstrap.servers"));
        props.put("key.serializer", externalProps.getProperty("string.key.serializer"));
        props.put("value.serializer", externalProps.getProperty("string.value.serializer"));
        props.put("acks", externalProps.getProperty("acks"));
        props.put("retries", externalProps.getProperty("retries"));
        props.put("buffer.memory", externalProps.getProperty("buffer.memory"));
        props.put("max.block.ms", externalProps.getProperty("max.block.ms"));
        props.put("max.in.flight.req.conn", externalProps.getProperty("max.in.flight.req.conn"));
        props.put("batch.size", externalProps.getProperty("batch.size"));
        props.put("linger.ms", externalProps.getProperty("linger.ms"));

        return new KafkaProducer<>(props);
    }

    public static KafkaProducer<String, String> getAvroKafkaProducer(){
        return null;
    }

}
