package com.zanox.kafka.highlevelconsumer

import kafka.consumer.ConsumerConfig
import spock.lang.Specification


class ConsumerConfigFactoryTest extends Specification {
    def "It should create a ConsumerConfig object"() {
        when:
        def consumerConfig = ConsumerConfigFactory.create("dummyzookeeper", "dummygroup");

        then:
        consumerConfig instanceof ConsumerConfig
    }
}
