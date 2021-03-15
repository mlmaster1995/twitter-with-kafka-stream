package com.twitter.producer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppUtils {

    // load the external properties file
    public static Properties getExternalProps(String argPath){
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(argPath)) { prop.load(input);}
        catch (IOException io) { io.printStackTrace(); }
        return prop;
    }



}
