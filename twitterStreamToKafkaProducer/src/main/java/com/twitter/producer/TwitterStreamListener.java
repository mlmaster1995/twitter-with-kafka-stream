package com.twitter.producer;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public class TwitterStreamListener implements StatusListener{
    @Override
    public void onStatus(Status status) {
        System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
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
