This Project uses an In memory Active Message Queue
The sample Request payload for /message request mapping is following

<?xml version="1.0" encoding="UTF-8"?>
<message id="2">
<information>Any Information</information>
<postedBy>Chaitanya Ankam</postedBy>
</message>

This will be listned by JMS listner and then pushed to kafka producer.
Kafka producer has value serializer called PayloadSeralizer which is a customSerializer.
This Serializer will Unmarshall, Object Serialize(Java Serialization) and then place serailzed message in Kafak topic which will be ready to consume. 

Following are steps to start the kafka server and create/maintain/consume topic
Please create a topic with name topicA01, the same is mentioned in the application.properties.

1. start zookeeper and kafka server
zookeeper-server-start.bat config/zookeeper.properties
kafka-server-start.bat config/server.properties
2. creating a kafka topic
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1  --partitions 1 --topic topicA01
3. verfiy if the kafka topic is created
kafka-topics.bat --list --zookeeper localhost:2181
4. Optional Delete step if needed
kafka-topics.bat --zookeeper localhost:2181 --delete --topic topicA01
5. Consuming messages in kafka only for testing
kafka-console-consumer.bat --zookeeper localhost:2181 —topic topicA01 --from-beginning
