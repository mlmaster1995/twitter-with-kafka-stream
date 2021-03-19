ARG tag=20.04
FROM ubuntu:${tag}

ARG server_props_file=server-101.properties

# install dependencies
RUN apt-get update -y
RUN apt-get install vim -y
RUN apt-get install wget -y
RUN apt-get install openjdk-8-jdk -y 


# install apache kafka
ARG scala_version=2.12
ARG kafka_version=2.7.0
ARG kafka_tgz_file=kafka_${scala_version}-${kafka_version}.tgz
ARG kafka_untar_folder=kafka_${scala_version}-${kafka_version}
ARG kafka_folder=kafka-${scala_version}-${kafka_version}
ARG kafka_url=https://muug.ca/mirror/apache-dist/kafka/${kafka_version}/${kafka_tgz_file}

WORKDIR /opt/

RUN wget ${kafka_url}
RUN tar -xvzf ./${kafka_tgz_file}
RUN cp -r ./${kafka_untar_folder} ./${kafka_folder}
RUN rm -rf ./${kafka_untar_folder}
RUN export PATH=${PATH}:/opt/${kafka_folder}/bin

COPY ./${server_props_file} ./${kafka_folder}/config/


# config kafka zookeeper connection during build time
ARG zookeeper_connect
ENV ZOOKEEPER_CONNECT=${zookeeper_connect}
RUN echo "zookeeper.connect=${zookeeper_connect}" >> ./${kafka_folder}/config/${server_props_file}


# start kafka broker
ENV SERVER_PROPS_FILE=${server_props_file}

WORKDIR /opt/${kafka_folder}

COPY ./start-broker1.sh ./create-topics.sh ./

RUN chmod a+x ./*.sh

CMD ./start-broker1.sh





