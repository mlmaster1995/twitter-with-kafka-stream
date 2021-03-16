package com.twitter.producer;

import com.google.gson.Gson;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import java.util.Date;
import java.util.Properties;


public class TwitterStreamListener extends StreamListener implements StatusListener{
    // constructor
    public TwitterStreamListener(Properties externalProps){this.props = externalProps;}

    // extract tweet from the stream
    private TwitterData extractTweetDataFromStream(Status tweetStatus){
        Date tweetCreatedDate = tweetStatus.getCreatedAt();
        Long tweetID = tweetStatus.getId();
        String tweetText = tweetStatus.getText();
        Long tweetUserID = tweetStatus.getUser().getId();
        String tweetFullName = tweetStatus.getUser().getName() + "@" + tweetStatus.getUser().getScreenName();

        return new TwitterData(tweetCreatedDate,tweetID,tweetText,tweetUserID,tweetFullName);
    }

    // send tweet messages to the producer
    private void sendTwitterJsonMessageToProducer(KafkaProducer<String, String> kafkaProducer, TwitterData twitterData){
        Gson gson = new Gson();
        String tweetJsonMessage = gson.toJson(twitterData);
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(this.props.getProperty("stream.topic"), Long.toString(twitterData.tweetID), tweetJsonMessage);

        // send messages to kafka brokers in async model
        try{kafkaProducer.send(producerRecord, new KafkaAsyncProducerCallback());}
        catch(Exception e){e.printStackTrace();}
        finally{kafkaProducer.close();}
    }

    @Override
    public void onStatus(Status status) {
        TwitterData twitterData = this.extractTweetDataFromStream(status);
        KafkaProducer jsonKafkaProducer = TwitterKafkaProducer.getStringKafkaProducer(this.props);
        this.sendTwitterJsonMessageToProducer(jsonKafkaProducer, twitterData);
    }
    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
    @Override
    public void onTrackLimitationNotice(int numberOfLimitedStatuses) { System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);}
    @Override
    public void onScrubGeo(long userId, long upToStatusId) {}
    @Override
    public void onStallWarning(StallWarning warning) {}
    @Override
    public void onException(Exception ex) { ex.printStackTrace(); }

}

class KafkaAsyncProducerCallback implements Callback{
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if( e!=null) { e.printStackTrace(); }
        else
        {
            System.out.println("record published to [partition:" + recordMetadata.partition() + ",offset:" + recordMetadata.offset() + "]");
        }
    }
}
