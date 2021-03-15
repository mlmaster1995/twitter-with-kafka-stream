package com.twitter.producer;

import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TwitterKafkaProducerUtils {

    // load the external properties file
    public static Properties getExternalProps(String argPath){
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(argPath)) { prop.load(input);}
        catch (IOException io) { io.printStackTrace(); }
        return prop;
    }

    // get twitter utils
    public static Configuration getTweetConfig(Properties props){
        ConfigurationBuilder tweetConfigBuilder = new ConfigurationBuilder();

        tweetConfigBuilder.setDebugEnabled(true);
        tweetConfigBuilder.setOAuthConsumerKey(props.getProperty("api_key"));
        tweetConfigBuilder.setOAuthConsumerSecret(props.getProperty("api_secrete_key"));
        tweetConfigBuilder.setOAuthAccessToken(props.getProperty("access_token"));
        tweetConfigBuilder.setOAuthAccessTokenSecret(props.getProperty("access_token_secrete"));

        return tweetConfigBuilder.build();
    }

    // get tweet stream
    public static TwitterStream getTweetStream(Configuration config){
        return new TwitterStreamFactory(config).getInstance();
    }



}
