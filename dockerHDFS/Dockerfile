ARG tag=20.04
FROM ubuntu:${tag}

# install dependencies
RUN apt-get update -y
RUN apt-get install vim -y
RUN apt-get install wget -y
RUN apt-get install openjdk-8-jdk -y

# install hadoop 2.7
ARG hadoop_version=2.7.7
ARG hadoop_folder=hadoop-${hadoop_version}
ARG hadoop_tar_folder=${hadoop_folder}.tar.gz
ARG hadoop_url=https://archive.apache.org/dist/hadoop/common/${hadoop_folder}/${hadoop_tar_folder}

WORKDIR /opt/

RUN wget ${hadoop_url}
RUN tar -xvzf ./${hadoop_tar_folder}
RUN rm ./${hadoop_tar_folder}

# set up hadoop env path
WORKDIR /opt/${hadoop_folder}/

RUN export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
RUN export HADOOP_INSTALL=./
RUN export PATH=$PATH:$HADOOP_INSTALL/bin
RUN export PATH=$PATH:$HADOOP_INSTALL/sbin
RUN export HADOOP_MAPRED_HOME=$HADOOP_INSTALL
RUN export HADOOP_COMMON_HOME=$HADOOP_INSTALL
RUN export HADOOP_HDFS_HOME=$HADOOP_INSTALL
RUN export YARN_HOME=$HADOOP_INSTALL
RUN export HADOOP_COMMON_LIB_NATIVE_DIR=$HADOOP_INSTALL/lib/native
RUN export HADOOP_OPTS="-Djava.library.path=$HADOOP_INSTALL/lib"

# config hadoop




CMD ["echo","done"]





