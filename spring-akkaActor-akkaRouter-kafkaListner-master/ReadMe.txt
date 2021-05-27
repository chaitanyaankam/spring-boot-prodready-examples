Pre-requisite >> spring-JMS-Kafka-broker project GitHub public

This project is a standalone (non-web) application using Apache Akka Actors for converting byte[] to Java Object (POJO) form kafka topic.
Akka actors are created in pool and payload is distributed using RoundRobbinPool (all the Objects are declared as spring beans in respective configuration files).
RoundRobbin pool uses RoundRobbin Algorithm and distributes load across the actors pool.
The actors-pool.size in application.properties describes the pool size to use.

--------------------------------------------------------------------------------
Following are the steps to start, create/maintain kafka topic with name topicA01.
1. start zookeeper and kafka server
zookeeper-server-start.bat config/zookeeper.properties
kafka-server-start.bat config/server.properties
2. creating a kafka topic
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1  --partitions 1 --topic topicA01
3. verfiy if the kafka topic is created
kafka-topics.bat --list --zookeeper localhost:2181
4. Optional Delete step if needed
kafka-topics.bat --zookeeper localhost:2181 --delete --topic topicA01
