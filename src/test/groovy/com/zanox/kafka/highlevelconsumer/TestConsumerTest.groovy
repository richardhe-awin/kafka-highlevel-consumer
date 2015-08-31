package com.zanox.kafka.highlevelconsumer

import kafka.consumer.ConsumerIterator
import kafka.consumer.KafkaStream
import kafka.message.MessageAndMetadata
import spock.lang.Specification


class TestConsumerTest extends Specification {
    def "It should iterate the stream and try to consume messages one by one"() {
        setup:
        def messageAndMetadata = Mock(MessageAndMetadata)
        def kafkaStream = Mock(KafkaStream)
        def consumerIterator = Mock(ConsumerIterator)
        def message = "some message".bytes

        when:
        def consumer = new TestConsumer(kafkaStream, 1)

        and:
        consumer.run()

        then:
        kafkaStream.iterator() >> consumerIterator
        1 * consumerIterator.hasNext() >> true
        1 * consumerIterator.hasNext() >> false
        1 * consumerIterator.next() >> messageAndMetadata
        1 * messageAndMetadata.message() >> message

    }
}
