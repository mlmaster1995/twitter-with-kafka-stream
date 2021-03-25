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
