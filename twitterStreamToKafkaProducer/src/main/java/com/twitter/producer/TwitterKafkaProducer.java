/*
Copyright 2021 C.Young
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
  limitations under the License.
*/
package com.twitter.producer;

import com.google.gson.Gson;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

// kafka asyn producer callback
class KafkaAsyncProducerCallback implements Callback {
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if( e!=null) { e.printStackTrace(); }
        else
        {
            System.out.println("record published to [partition:" + recordMetadata.partition() + ",offset:" + recordMetadata.offset() + "]");
        }
    }
}

public class TwitterKafkaProducer {

    // get an instance of kafka producer with string serializer
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

    // get an instance of kafka producer with avro serializer
    public static KafkaProducer<String, String> getAvroKafkaProducer(Properties externalProps){
        Properties props = new Properties();

        props.put("bootstrap.servers", externalProps.getProperty("bootstrap.servers"));
        props.put("schema.registry.url", externalProps.getProperty("schema.registry.url"));
        props.put("key.serializer", externalProps.getProperty("avro.key.serializer"));
        props.put("value.serializer", externalProps.getProperty("avro.value.serializer"));
        props.put("acks", externalProps.getProperty("acks"));
        props.put("retries", externalProps.getProperty("retries"));
        props.put("buffer.memory", externalProps.getProperty("buffer.memory"));
        props.put("max.block.ms", externalProps.getProperty("max.block.ms"));
        props.put("max.in.flight.req.conn", externalProps.getProperty("max.in.flight.req.conn"));
        props.put("batch.size", externalProps.getProperty("batch.size"));
        props.put("linger.ms", externalProps.getProperty("linger.ms"));

        return new KafkaProducer<>(props);
    }

    // send tweet messages as json string to the producer
    public static void sendTwitterJsonMessageToProducer(KafkaProducer<String, String> kafkaProducer, TwitterJsonData twitterJsonData, Properties props){
        Gson gson = new Gson();
        ProducerRecord<String, String> producerRecord = null;

        // filter the messages bofore sending to kafka
        String[] topicList = props.getProperty("tweet.track.list").split(",");
        for(String topic: topicList) {
            if(twitterJsonData.tweetText.contains(topic)){
                // generate kafka producer record
                String tweetJsonMessage = gson.toJson(twitterJsonData);
                producerRecord = new ProducerRecord<>(props.getProperty("stream.topic"), Long.toString(twitterJsonData.tweetID), tweetJsonMessage);
                break;
            }
        }

        // send messages to kafka brokers in async model
        if(producerRecord!=null){
            try{kafkaProducer.send(producerRecord, new KafkaAsyncProducerCallback());}
            catch(Exception e){e.printStackTrace();}
            finally{kafkaProducer.close();}
        }
        else System.out.println("....message is unrelated and disposed....");
    }

    // send tweet messages as avro data to the producer
    public static void sendTwitterAvroMessageToProducer(KafkaProducer<String, avro.TwitterAvroData> kafkaProducer, avro.TwitterAvroData twitterAvroData, Properties props){
        ProducerRecord<String, avro.TwitterAvroData> producerRecord = null;

        // filter the messages bofore sending to kafka
        String[] topicList = props.getProperty("tweet.track.list").split(",");
        for(String topic: topicList) {
            if(twitterAvroData.getTweetText().toString().contains(topic)){
                // generate kafka producer record
                twitterAvroData.setTweetRelatedTopic(topic);
                producerRecord = new ProducerRecord<>(props.getProperty("storage.topic"), twitterAvroData.getTweetRelatedTopic().toString(), twitterAvroData);
                break;
            }
        }

        // send messages to kafka brokers in async model
        if(producerRecord!=null){
            try{kafkaProducer.send(producerRecord, new KafkaAsyncProducerCallback());}
            catch(Exception e){e.printStackTrace();}
            finally{kafkaProducer.close();}
        }
        else System.out.println("....message is unrelated and disposed....");
    }
}
