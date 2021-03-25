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

    // get twitter config
    public static Configuration getTweetConfig(Properties props){
        ConfigurationBuilder tweetConfigBuilder = new ConfigurationBuilder();

        tweetConfigBuilder.setDebugEnabled(true);
        tweetConfigBuilder.setOAuthConsumerKey(props.getProperty("api.key"));
        tweetConfigBuilder.setOAuthConsumerSecret(props.getProperty("api.secrete.key"));
        tweetConfigBuilder.setOAuthAccessToken(props.getProperty("access.token"));
        tweetConfigBuilder.setOAuthAccessTokenSecret(props.getProperty("access.token.secrete"));

        return tweetConfigBuilder.build();
    }

    // get tweet stream instance with the config
    public static TwitterStream getTweetStream(Configuration config){
        return new TwitterStreamFactory(config).getInstance();
    }

}
