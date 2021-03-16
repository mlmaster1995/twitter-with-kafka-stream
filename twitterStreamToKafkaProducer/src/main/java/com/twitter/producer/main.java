package com.twitter.producer;

import twitter4j.*;
import twitter4j.conf.Configuration;

import java.util.Properties;

import static com.twitter.producer.TwitterKafkaProducerUtils.*;

public class main {
    public static void main(String[] args) {
        // load properties from the external file
        Properties externalAppProps;
        if(args==null) throw new RuntimeException("\nargs cannot be empty for properties file...\n");
        else externalAppProps = getExternalProps(args[0]);

        // get tweet config
        Configuration tweetConfig = getTweetConfig(externalAppProps);

        // get tweet stream instance
        TwitterStream tweetStream = getTweetStream(tweetConfig);

        // add the tweet stream listener to the tweet stream
        TwitterStreamListener tweetStreamListener = new TwitterStreamListener(externalAppProps);
        tweetStream.addListener(tweetStreamListener);

        // samples the tweet based on the language and the track key words
        FilterQuery filterQuery = new FilterQuery();
        filterQuery.language(externalAppProps.getProperty("tweet.language"));
        filterQuery.track(externalAppProps.getProperty("tweet.track.list"));
        tweetStream.filter(filterQuery);

    }
}
