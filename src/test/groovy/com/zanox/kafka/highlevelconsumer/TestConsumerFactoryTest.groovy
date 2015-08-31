package com.zanox.kafka.highlevelconsumer

import kafka.consumer.KafkaStream
import spock.lang.Specification


class TestConsumerFactoryTest extends Specification {
    def "It should create a test consumer"() {
        setup:
        def consumerFactory = new TestConsumerFactory()

        when:
        def consumer = consumerFactory.create(Mock(KafkaStream), 1)

        then:
        consumer instanceof Consumer
    }
}
