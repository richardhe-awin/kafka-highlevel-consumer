package com.zanox.kafka.highlevelconsumer

import kafka.consumer.KafkaStream
import kafka.javaapi.consumer.ConsumerConnector
import spock.lang.Specification


class MessageStreamFactoryTest extends Specification {
    def "It should create a list of message streams"() {
        setup:
        def topic = "topic"
        def numThreads = 2
        def consumerConnector = Mock(ConsumerConnector)
        def messageStreamFactory = new MessageStreamFactory(topic, consumerConnector)
        def topicCountMap = new HashMap()
        topicCountMap.put(topic, numThreads)
        def consumerMap = new HashMap()
        def consumerMapList = new ArrayList()
        def stream1 = Mock(KafkaStream)
        def stream2 = Mock(KafkaStream)
        consumerMapList.add(stream1)
        consumerMapList.add(stream2)
        consumerMap.put(topic, consumerMapList)


        when:
        def streamList = messageStreamFactory.create(2)

        then:
        consumerConnector.createMessageStreams(topicCountMap) >> consumerMap
        streamList instanceof List
        streamList.size() == 2
        streamList.get(0) instanceof KafkaStream
        streamList.get(1) instanceof KafkaStream

    }
}
