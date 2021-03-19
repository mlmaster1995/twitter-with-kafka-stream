ARG tag=20.04
FROM ubuntu:${tag}

# install dependencies
RUN apt-get update -y
RUN apt-get install wget -y
RUN apt-get install openjdk-8-jdk -y  

# install schema registry
ARG confluent_folder=confluent-community-6.1.0
ARG confluent_tar_folder=${confluent_folder}.tar.gz
ARG confluent_version=6.1
ARG confluent_community_url=http://packages.confluent.io/archive/${confluent_version}/${confluent_tar_folder}

WORKDIR /opt/

RUN wget ${confluent_community_url}
RUN tar -xvzf ./${confluent_tar_folder}
RUN rm ./${confluent_tar_folder}

# config schema registry and connect props
ARG connect_props_file=connect-avro-standalone.properties
ENV CONNECT_PROPS_FILE=${connect_props_file}

ARG schema_props_file=schema-registry.properties
ENV SCHEMA_PROPS_FILE=${schema_props_file}

ARG kafka_broker_connect
ENV KAFKA_BROKER_CONNECT=${kafka_broker_connect}

WORKDIR /opt/${confluent_folder}/etc/schema-registry/

COPY ./${schema_props_file} ${connect_props_file} ./ 

RUN echo "kafkastore.bootstrap.servers=PLAINTEXT://${kafka_broker_connect}" >> ./${schema_props_file}

RUN echo "bootstrap.servers=${kafka_broker_connect}" >> ./${connect_props_file}

# start schema registry
WORKDIR /opt/${confluent_folder}

COPY ./start-schema-registry.sh ./

RUN chmod a+x ./*sh

CMD ./start-schema-registry.sh
