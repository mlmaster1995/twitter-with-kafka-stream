## Tweet Topic Tracking Pipeline with Lambda Architecture
![Hex.pm](https://img.shields.io/hexpm/l/plug?logo=Apache&logoColor=%23ff0000&style=flat-square)

### Table of Contents
* [About the Project](#about-the-project)
* [Built With](#built-with)
* [Pipelines](#pipelines)  
  * [Pipeline Structure](#pipeline-structure)
  * [Pipeline Layers](#pipeline-laysers)
  * [Pipeline Use](#use-pipeline)
* [Project Content](#project-content)
* [Structure Data Samples](#structure-data-samples)
* [Contact](#contact)

### About The Project
I. This is a small project to leverage the benefits of Apache Kafka realizing the stateful processing and the direct storage via ```Kafka Stream```, ```Kafka Connect``` 
and ```Kafka Producer, Consumer API```. 
  

II. Different from [ETL(Extract Transform Load) Data Pipeline Collection OOP](https://github.com/mlmaster1995/Flume_Kafka_StructureStream_ELT_Updated) project, this project 
  is not a combination of multiple point-to-point data pipelines, BUT a tweet stream pipeline based on ```Lambda Architecture``` which includes the batch layer, and the real-time 
  stateful processing layer for further down-stream process in the serving layer. 
  

III. The batch layer uses ```Kafka HDFS Connect``` and ```Confluent Schema Registry``` ingesting tweet stream with ```Avro Schema``` into ```Hadoop system``` directly. The same stream source
  is processed based on the user-defined topics with ```Json Schema``` via ```Kafka Stream``` saving the data into ```Cassandra database```. 
  

IV. The 2nd upgrade comparing to the [previous project](https://github.com/mlmaster1995/Flume_Kafka_StructureStream_ELT_Updated), this project is developed as an OOP in Java and 
the compiled jar files of different component of the pipeline are generated. All properties related to the pipeline could be configured via a property file and it's easy to reuse the pipeline
in any configured big data environment. 

V. This project is developed and tested in the self-configured VM with related technologies as [Built With](#built-with). 

### Built With
* [JDK 8](https://www.oracle.com/ca-en/java/technologies/javase/javase-jdk8-downloads.html)
* [Apache Kafka 2.7.0](https://kafka.apache.org/0102/documentation.html)
* [Apache Hadoop 2.7.7](https://hadoop.apache.org/)
* [Confluent Schema Registry (Community Platform 6.1.0)](https://github.com/confluentinc/schema-registry)
* [Twitter4j 4.0.7](http://twitter4j.org/en/index.html)
* [Cassandra 3.11](https://cassandra.apache.org/)

### Pipelines
    
#### Pipeline Structure:

![tweet-stream-kafka](https://user-images.githubusercontent.com/55723894/112486281-1ee39c80-8d52-11eb-80ce-c51c2b9ad798.jpeg)

#### Pipeline Layers:
* Data source Layer: the tweet data is extracted via the twitter stream API with the specific configs for the language and key words tracking, which could be set up in the 
  ```tweet-message-trending-pipeline.properties```. The default langage is ```english``` and default keywords are the 2021 trending technologies including ```5G,IoB,DevSecOps,
  Intelligent Process Automation,Tactile Virtual Reality,Big Data Analytics,CyberSecurity, Artificial Intelligence```. The tweet stream is ingested into two producers and messages 
  are published to the kafka cluster with both Avro and Json schemas.
  

* Batch Layer: the kafka cluster redirects messages based on the topics into Kakfa Connenct for HDFS storage and Kafka Stream for the stateful processing. The HDFS stores the original data with avro format which
  could be fetched via Spark SQL or Spark Structured API for batch process in the serving layer (Not included in the repo). 
  

* Speed Layer: the stateful process is using a hopping window with custom-defined window size, default at 2min to aggregate the messages with related keywords into a ```related topic count``` which could reflect
the popularity of the trending technology in the tweet. The processed data is saved into Cassandra database which could be extracted and processed in the servering layer (Not included in the repo).
  

* Serving Layer: Not included in this project repo.

#### Pipeline Use:
**NOTE**: to compile and generate jars of pipeline component, go to the app folder run ```mvn clean install``` for the compile and jar generation. 

I. Set up the pipeline properties in the file ```tweet-message-trending-pipeline.properties``` and this file includes all props needed by the pipeline such as kafka topics, tweeter developer API
credentials...

   
II. Run the bash script ```start-tweet-to-kafka-producer.sh``` to start tweet streaming into the kafka producer, and the producer console will show the message published status asynchronously.
   This process writes data into two producers. If the kafka connect is configured properly, the avro files will be automatically saved in HDFS within the path as ```/topics/streamToHdfs/...```.
   The kafka connect is tested in the VM with the standalone config, and the config sample files & drivers are explained in ```simpleKafkaConnectConfig``` folder. 
 
  
III. Run the bash script ```kafka-stream-processing.sh``` to start the kafka streaming process.


IV. Run the bash script ```kafka-stream-to-cassandra.sh``` to write the processed stream into Cassandra database. The consumer console will show the processed messages but it's a minor different from 
the data saved in Cassandra.

### Project Content

    ├── Covid19ToKafkaProducer                    # Airflow DAG for COVID-19 batch data
    ├── KafkaConsumer                             # KakfaConsumer application for tweet stream and COVID-19 data sources
    ├── KafkaSparkUnit                            # Spark extract, transform and load application 
    ├── TwitterStreamToKafkaProducer              # Twitter Stream to Kafka application
    ├── create-database-table.sql                 # SQL script for create the database and tables in mySQL
    ├── start-kafkaConsumer.sh                    # Bash script to run scala application KafkaConsumer
    ├── start-spark-kafka-unit.sh                 # Bash script to submit spark application KafkaSparkUnit
    ├── start-tweetStream-to-kafkaProducer.sh     # Bash script to run scala application KafkaConsumer
    ├── start-vmstats-with-flume.sh               # Bash script to run flume for vmstat data stream
    ├── vmstat_flume_kafka.conf                   # Flume agent configuration file
    ├── jars                                      # Jar folder for KafkaSparkUnit app including all dependencies
    ├── kafka-spark-unit.properties               # Properties for data pipeline and the pipeline selection

### Structure Data Samples
**NOTE**: Sensitive Data Is Hidden Or Modified In The Following Samples. 

* Pipeline: vmstat -> flume -> kafka -> spark structured streaming -> mySQL

 
    | row_id | topic | time                | r    | b    | swpd | free   | buff | cache   | si   | so   | bi   | bo   | in_val | cs   | us   | sy   | id   | wa   | st   |
    |--------|-------|---------------------|------|------|------|--------|------|---------|------|------|------|------|--------|------|------|------|------|------|------|
    |      1 | exec  | 2021-02-02 10:43:02 | 1    | 2    | 3    | 4      | 5    | 6       | 7    | 8    | 9    | 10   | 11     | 12   | 13   | 14   | 15   | 16   | 17   |
    |      2 | exec  | 2021-02-02 10:56:47 | 0    | 0    | 8    | 301620 | 1144 | 8950572 | 0    | 0    | 0    | 35   | 1706   | 1672 | 6    | 2    | 92   | 0    | 0    |
    |      3 | exec  | 2021-02-02 10:56:47 | 0    | 0    | 8    | 301176 | 1144 | 8950576 | 0    | 0    | 0    | 0    | 1469   | 1540 | 4    | 2    | 95   | 0    | 0    |
    |      4 | exec  | 2021-02-02 10:56:47 | 1    | 0    | 8    | 247564 | 1144 | 8950612 | 0    | 0    | 0    | 0    | 3564   | 3661 | 15   | 4    | 81   | 0    | 0    |
    |      5 | exec  | 2021-02-02 10:56:50 | 2    | 0    | 8    | 170608 | 1144 | 8919396 | 0    | 0    | 0    | 0    | 5363   | 4051 | 35   | 5    | 60   | 0    | 0    |
   

* Pipeline: tweet stream -> kafka -> spark structured streaming -> mySQL


    | row_id | tweet_time                   | user_id  | full_name           | tweet_id  | tweet_source        | is_truncated | is_rt | tweet_text                         |
    |--------|------------------------------|----------|---------------------|-----------|---------------------|--------------|-------|------------------------------------|
    |      1 | Fri Feb 12 20:04:55 EST 2021 |   ...    |      ...            |   ...     | Twitter for iPhone  | false        | false | just ordered ... 🥰 ...       ...  |
    |      2 | Fri Feb 12 20:04:55 EST 2021 |   ...    | chrisy 🌼@pptyaacy  |   ...     | Twitter for Android | false        | false | @bluexjjkyu okeyyy,           ...  |
    |      3 | Fri Feb 12 20:04:55 EST 2021 |   ...    |      ...            |   ...     | Twitter for iPhone  | false        | false | RT @uhprome: I really         ...  |
    |      4 | Fri Feb 12 20:04:55 EST 2021 |   ...    |      ...            |  ...      | Twitter for iPhone  | false        | false | RT @thesecret: Every          ...  |
    |      5 | Fri Feb 12 20:04:55 EST 2021 |   ...    |      ...            |   ...     | Twitter for iPhone  | false        | false | RT @ferbIatin: the            ...  |

* Pipeline: tweet stream -> kafka -> spark structred streaming -> mongoDB
  

      {
        "_id" : ObjectId("60271b6f6a142c2014fdc296"),
        "tweet_time" : "Fri Feb 12 19:20:53 EST 2021",
        "user_id" : "...",
        "full_name" : "...",
        "tweet_id" : "...",
        "tweet_source" : "Twitter for iPhone",
        "is_truncated" : "false",
        "is_rt" : "false",
        "tweet_text" : "First Time She Put Dat Pussy On Me I Put Her In A Benz 🤞🏽"
      }

* Pipeline: tweet stream -> kafka + Schema Registry -> Confluent Kafka Avro Consumer 
    
     
     {"tweetdate":"Sat Feb 20 19:23:25 EST 2021","userID":{"long":...},"fullName":{"string":"Aphrodi\uD83D\uD..."},"tweetID":{"long":...},"tweetSource":{"string":"Twitter for iPhone"},"isTruncated":{"boolean":false},"isRT":{"boolean":false},"tweet":{"string":"RT @deeptrusts: I want someo ..."}}
     
     {"tweetdate":"Sat Feb 20 19:23:25 EST 2021","userID":{"long":...},"fullName":{"string":"Ro ♒\uD83D\uDC96..."},"tweetID":{"long":...},"tweetSource":{"string":"Twitter for iPhone"},"isTruncated":{"boolean":false},"isRT":{"boolean":false},"tweet":{"string":"RT @feelxpain: i fucking fac ..."}}
     
     {"tweetdate":"Sat Feb 20 19:23:25 EST 2021","userID":{"long":...},"fullName":{"string":"nico._.macedo@ni..."},"tweetID":{"long":...},"tweetSource":{"string":"Twitter for Android"},"isTruncated":{"boolean":false},"isRT":{"boolean":false},"tweet":{"string":"@mukti_alin NFR lbinoBateon ..."}}


### Contact
* C. Young: kyang3@lakeheadu.ca
