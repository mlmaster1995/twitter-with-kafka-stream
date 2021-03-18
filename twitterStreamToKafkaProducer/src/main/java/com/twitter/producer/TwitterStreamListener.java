package com.twitter.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import java.util.Date;
import java.util.Properties;

public class TwitterStreamListener extends StreamListener implements StatusListener{
    // fields
    private Properties props;

    // constructor
    public TwitterStreamListener(Properties externalProps){ this.props= externalProps;}

    // extract tweet from the stream to json data
    private TwitterJsonData extractTweetDataFromStreamToJson(Status tweetStatus){
        Date tweetCreatedDate = tweetStatus.getCreatedAt();
        Long tweetID = tweetStatus.getId();
        String tweetText = tweetStatus.getText();
        Long tweetUserID = tweetStatus.getUser().getId();
        String tweetFullName = tweetStatus.getUser().getName() + "@" + tweetStatus.getUser().getScreenName();

        return new TwitterJsonData(tweetCreatedDate,tweetID,tweetText,tweetUserID,tweetFullName);
    }

    // extract tweet from the stream to avro data
    private avro.TwitterAvroData extractTweetDataFromStreamToAvro(Status tweetStatus){
        Date tweetCreatedDate = tweetStatus.getCreatedAt();
        Long tweetID = tweetStatus.getId();
        String tweetText = tweetStatus.getText();
        Long tweetUserID = tweetStatus.getUser().getId();
        String tweetFullName = tweetStatus.getUser().getName() + "@" + tweetStatus.getUser().getScreenName();

        return new avro.TwitterAvroData(tweetCreatedDate.toString(),tweetID,tweetText,tweetUserID,tweetFullName);
    }

    // override statusListener interface
    @Override
    public void onStatus(Status status) {
        // extract same tweet data in both Json and Avro format
        TwitterJsonData twitterJsonData = extractTweetDataFromStreamToJson(status);
        avro.TwitterAvroData twitterAvroData = extractTweetDataFromStreamToAvro(status);

        // get kafka producer instances for both Json and Avro format
        KafkaProducer jsonKafkaProducer = TwitterKafkaProducer.getStringKafkaProducer(this.props);
        KafkaProducer avroKafkaProducer = TwitterKafkaProducer.getAvroKafkaProducer(this.props);

        // write data to stream.topic and storage.topic for both stream process and hdfs storage
        TwitterKafkaProducer.sendTwitterJsonMessageToProducer(jsonKafkaProducer, twitterJsonData, this.props);
        TwitterKafkaProducer.sendTwitterAvroMessageToProducer(avroKafkaProducer, twitterAvroData, this.props);
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


