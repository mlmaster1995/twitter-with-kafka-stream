* The project uses Apache Kafka as the base, but both schema registry and kafka connect are from confluent community 
  platform at [here](https://www.confluent.io/get-started).


* This project uses ```kafka connect hdfs driver``` to ingest data into HDFS and the driver is 
  from confluent at [here](https://www.confluent.io/hub/confluentinc/kafka-connect-hdfs). The 
  kafka connect mode is standalone. The property files, in the repo, show the config for both kafka connect and schema
  registry to link kafka brokers and HDFS. 

  
* ```quickstart-hdfs.properties``` is the config for the kafka hdfs connect used in this project 
  and it comes with the confluent kafka hdfs connect package.  

  
* ```connect-avro-standalone.properties``` is the config for schema registry used in this project and it comes with the 
  confluent schema registry folder in the confluent platform

  
* To start the kafka hdfs connect, run ```./bin/connect-standalone ./etc/schema-registry/connect-avro-standalone.properties 
  ./plugins/confluentinc-kafka-connect-hdfs-10.0.2/etc/quickstart-hdfs.properties```
  

