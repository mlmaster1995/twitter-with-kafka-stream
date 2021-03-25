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
and ```Kafka Producer & Consumer API```. 
  

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

I. Set up the pipeline properties in the file ```tweet-message-trending-pipeline.properties``` and this file includes all props needed by the pipeline such as kafka topics, twitter developer API
credentials...

   
II. Run the bash script ```start-tweet-to-kafka-producer.sh``` to start tweet streaming into the kafka producer, and the producer console will show the message published status asynchronously.
   This process writes data into two producers. If the kafka connect is configured properly, the avro files will be automatically saved in HDFS within the path as ```/topics/streamToHdfs/...```.
   The kafka connect is tested in the VM with the standalone config, and the config sample files & drivers are explained in ```simpleKafkaConnectConfig``` folder. 
 
  
III. Run the bash script ```kafka-stream-processing.sh``` to start the kafka streaming process.


IV. Run the bash script ```kafka-stream-to-cassandra.sh``` to write the processed stream into Cassandra database. The consumer console will show the processed messages but it's a minor different from 
the data saved in Cassandra.

### Project Content
    
    ├── Jars                                            # an folder for app jars
    ├── KafkaStreamProcessing                           # app folder for kafka stream processing
    ├── kafka-stream-processing.sh                      # bash to start the kafka stream processing
    ├── KafkaStreamToCassandra                          # app folder for kafka stream to cassandra
    ├── kafka-stream-to-cassandra.sh                    # bash to start writing stream to cassandra
    ├── start-tweet-to-kafka-producer.sh                # bash to start twitter streaming to kafka producers
    ├── tweet-message-trending-pipeline.properties      # pipeline config file
    └── twitterStreamToKafkaProducer                    # app folder for twitter stream to kafka producer

### Data Samples in Storage and Console
**NOTE**: Sensitive Data Is Hidden by ```...``` Or Modified with ```**``` In The Following Samples. 

* pipeline: tweet stream -> kafka producer with avro schema -> app console
* kafka producer runs in async mode so every ack from the broker will generate a callback for the message transfer status and printed as follows. ```....message is unrelated and disposed....``` means
the tweet messages from API has not any related keywords defined in ```tweet-message-trending-pipeline.properties```, but the related messages are pulished to the cluster.  


    |....message is unrelated and disposed....  |
    |....message is unrelated and disposed....  |
    |record published to [partition:2,offset:33]|
    |record published to [partition:1,offset:60]|



* Pipeline: tweet stream -> kafka producer with avro schema -> [kafka connect, schema registry] -> hdfs
* To read avro file in hdfs by spark: ```spark.read.format("avro").load("hdfs://localhost:9000/topics/streamToHdfs/partition=1/*.avro").show(3)```

    

    |    tweetCreatedDate|            tweetID|           tweetText|        tweetUserID|       tweetFullName|tweetRelatedTopic|
    |--------------------|-------------------|--------------------|-------------------|--------------------|-----------------|
    |Thu Mar 18 10:55:...|        ...        |RT @TechnicalGuru...|                ...|   Am*d@amid********|               5G|
    |Thu Mar 18 11:00:...|        ...        |In both public an...|                ...|                 ...|    CyberSecurity|
    |Thu Mar 18 11:00:...|13*2563688117*684*4|$KEYS 📰 Keysight.. |                ...|                 ...|               5G|
 


* Pipeline: tweet stream -> kafka producer with json schema -> kafka stream -> kafka consumer -> kafka console


    |TIMESTAMP:Thu Mar 25 11:29:44 EDT 2021,TOPIC:processedStream,KEY:[5G@2021-03-25T15:28:00Z-2021-03-25T15:30:00Z],VALUE:1                      |
    |TIMESTAMP:Thu Mar 25 11:29:47 EDT 2021,TOPIC:processedStream,KEY:[5G@2021-03-25T15:28:00Z-2021-03-25T15:30:00Z],VALUE:2                      |
    |TIMESTAMP:Thu Mar 25 11:29:47 EDT 2021,TOPIC:processedStream,KEY:[Artificial Intelligence@2021-03-25T15:28:00Z-2021-03-25T15:30:00Z],VALUE:1 |
    |TIMESTAMP:Thu Mar 25 11:29:51 EDT 2021,TOPIC:processedStream,KEY:[5G@2021-03-25T15:28:00Z-2021-03-25T15:30:00Z],VALUE:3                      |
    |TIMESTAMP:Thu Mar 25 11:29:57 EDT 2021,TOPIC:processedStream,KEY:[5G@2021-03-25T15:28:00Z-2021-03-25T15:30:00Z],VALUE:4                      |
    |TIMESTAMP:Thu Mar 25 11:29:57 EDT 2021,TOPIC:processedStream,KEY:[CyberSecurity@2021-03-25T15:28:00Z-2021-03-25T15:30:00Z],VALUE:1           |



* Pipeline: tweet stream -> kafka producer with json schema -> kafka stream -> kafka consumer -> cassandra
* the CQL table is created with ```tweet_topic``` as primary key/partition key, so the count is automatically updated under the fixed window size until next hopping window starts. 


    |tweet_topic                                                         | kafka_topic     | local_timestamp                 | tweet_topic_count |
    |--------------------------------------------------------------------+-----------------+---------------------------------+-------------------|
    |[CyberSecurity@2021-03-24T15:34:00Z-2021-03-24T15:36:00Z]           | processedStream | 2021-03-24 15:34:15.997000+0000 |                 2 |
    |[5G@2021-03-24T15:26:00Z-2021-03-24T15:28:00Z]                      | processedStream | 2021-03-24 15:27:41.766000+0000 |                 1 |
    |[CyberSecurity@2021-03-25T15:28:00Z-2021-03-25T15:30:00Z]           | processedStream | 2021-03-25 15:29:57.712000+0000 |                 1 |
    |[Artificial Intelligence@2021-03-24T15:34:00Z-2021-03-24T15:36:00Z] | processedStream | 2021-03-24 15:34:12.470000+0000 |                 1 |
    |[5G@2021-03-25T15:28:00Z-2021-03-25T15:30:00Z]                      | processedStream | 2021-03-25 15:29:57.242000+0000 |                 4 |
    |[5G@2021-03-24T15:34:00Z-2021-03-24T15:36:00Z]                      | processedStream | 2021-03-24 15:34:29.088000+0000 |                 6 |
    |[Artificial Intelligence@2021-03-25T15:28:00Z-2021-03-25T15:30:00Z] | processedStream | 2021-03-25 15:29:47.046000+0000 |                 1 |


### Contact
* C. Young: kyang3@lakeheadu.ca
