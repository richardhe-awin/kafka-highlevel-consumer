# kafka-highlevel-consumer
An implementation of the high level kafka consumer

### Run with:  
```
mvn package && java -cp target/highlevelconsumer.jar com.zanox.kafka.highlevelconsumer.App {zookeeper} {group-id} {topic} {num-of-threads}
```

**{zookeeper}** - zookeeper host (without the port number), e.g. localhost   
**{group-id}** - consumer group id   
**{topic}** - name of the topic  
**{num-of-threads}** - number of threads to use for the consumer. If you specify more threads than the number of Kafka partitions, some of the threads won't be doing anything because Kafka never allow a single partition to be consumed from more than one thread.  

### Test it:
A good way to verify all threads work as expected, try to use the following kafka cmd tool 
 
```
watch {path-to-kafka}/bin/kafka-run-class.sh kafka.tools.ConsumerOffsetChecker --topic {topic} --zookeeper {zookeeper} --group {group-id}
```
