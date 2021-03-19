ARG tag=20.04
FROM ubuntu:${tag}


# install dependencies
RUN apt-get update -y
RUN apt-get install vim -y
RUN apt-get install wget -y
RUN apt-get install openjdk-8-jdk -y  

# install schema registry
ARG conf_folder=confluent-community-components
ARG schema_path=${conf_folder}/etc/schema-registry

WORKDIR /opt/
COPY ./${conf_folder} ./

# config schema registry and connect props
ARG connect_props_file=connect-avro-standalone.properties
ENV CONNECT_PROPS_FILE=${connect_props_file}

ARG schema_props_file=schema-registry.properties
ENV SCHEMA_PROPS_FILE=${schema_props_file}

ARG kafka_broker_connect
ENV KAFKA_BROKER_CONNECT=${kafka_broker_connect}

RUN echo "kafkastore.bootstrap.servers=PLAINTEXT://${kafka_broker_connect}" >> ./etc/schema-registry/${schema_props_file}

RUN echo "bootstrap.servers=${kafka_broker_connect}" >> ./etc/schema-registry/${connect_props_file}

# start schema registry
COPY ./start-schema-registry.sh ./

RUN chmod a+x ./*sh

CMD ./start-schema-registry.sh
