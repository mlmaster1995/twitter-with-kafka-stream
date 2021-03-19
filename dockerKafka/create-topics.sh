# /usr/bin/bash -e

./bin/kafka-topics.sh --zookeeper ${ZOOKEEPER_CONNECT} --create --topic kafkaTest --partitions 1 --replication-factor 1
