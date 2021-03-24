#/usr/bin/bash
clear

FOLDER=data-dir

rm -rf $FOLDER

mkdir -p ${FOLDER}/kafka-logs-101
mkdir -p ${FOLDER}/zookeeper-data-1
mkdir -p ${FOLDER}/zookeeper-datalog-1
mkdir -p ${FOLDER}/cassandra_data
mkdir -p ${FOLDER}/name_node
mkdir -p ${FOLDER}/data_node

