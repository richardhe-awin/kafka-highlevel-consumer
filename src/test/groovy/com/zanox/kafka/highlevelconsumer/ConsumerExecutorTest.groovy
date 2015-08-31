package com.zanox.kafka.highlevelconsumer

import kafka.consumer.KafkaStream
import spock.lang.Specification

import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

class ConsumerExecutorTest extends Specification {
    def "It should execute all consumers in separate threads"() {
        setup:
        def numThreads = 2
        def consumerFactory = Mock(ConsumerFactory)
        def messageStreamFactory = Mock(MessageStreamFactory)
        def executorServiceFactory = Mock(ExecutorServiceFactory)
        def executorService = Mock(ExecutorService)
        def executor = new ConsumerExecutor(consumerFactory, messageStreamFactory, executorServiceFactory)
        def stream1 = Mock(KafkaStream)
        def stream2 = Mock(KafkaStream)
        def listOfStream = new ArrayList()
        listOfStream.add(stream1)
        listOfStream.add(stream2)
        def consumer1 = Mock(Consumer)
        def consumer2 = Mock(Consumer)

        when:
        def futures = executor.run(numThreads)

        then:
        futures instanceof Collection<Future>
        futures.size() == 2
        1 * messageStreamFactory.create(numThreads) >> listOfStream
        1 * consumerFactory.create(stream1, 0) >> consumer1
        1 * consumerFactory.create(stream2, 1) >> consumer2
        1 * executorServiceFactory.create(numThreads) >> executorService
        1 * executorService.submit(consumer1)
        1 * executorService.submit(consumer2)
    }
}
