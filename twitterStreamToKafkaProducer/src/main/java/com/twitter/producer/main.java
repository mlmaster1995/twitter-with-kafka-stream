package com.twitter.producer;

import java.util.Properties;
import static com.twitter.producer.AppUtils.getExternalProps;

public class main {
    public static void main(String[] args) {
        // load properties from the external file
        Properties externalAppProps = getExternalProps(args[0]);

        System.out.println(externalAppProps.getProperty("API_key"));
        System.out.println(externalAppProps.getProperty("API_secrete_key"));
        System.out.println(externalAppProps.getProperty("Bear_token"));
        System.out.println(externalAppProps.getProperty("Access_token"));
        System.out.println(externalAppProps.getProperty("Access_token_secret"));


    }
}
