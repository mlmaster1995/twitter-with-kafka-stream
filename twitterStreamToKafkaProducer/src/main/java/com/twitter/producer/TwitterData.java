package com.twitter.producer;

import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Date;

public class TwitterData {
    // field
    public Date tweetCreatedDate;
    public Long tweetID;
    public Long tweetUserID;
    public String tweetText;
    public String tweetFullName;

    // constructor
    public TwitterData(Date tweetCreatedDate, Long tweetID, String tweetText, Long tweetUserID, String tweetFullName){
        this.tweetCreatedDate=tweetCreatedDate;
        this.tweetID=tweetID;
        this.tweetText=tweetText;
        this.tweetUserID=tweetUserID;
        this.tweetFullName=tweetFullName;
    }
}
