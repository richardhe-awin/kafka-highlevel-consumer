package com.zanox.kafka.highlevelconsumer

import spock.lang.Specification

import java.util.concurrent.ExecutorService


class ExecutorServiceFactoryTest extends Specification {
    def "It should create an executor service"() {
        when:
        def executorServiceFactory = new ExecutorServiceFactory()
        def executorService = executorServiceFactory.create(1)

        then:
        executorService instanceof ExecutorService

    }
}
