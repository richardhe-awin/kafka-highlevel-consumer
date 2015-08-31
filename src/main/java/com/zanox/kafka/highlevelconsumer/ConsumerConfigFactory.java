package com.zanox.kafka.highlevelconsumer;

import kafka.consumer.ConsumerConfig;

import java.util.Properties;

public class ConsumerConfigFactory {
    public static ConsumerConfig create(String zookeeper, String groupId) {
        Properties props = new Properties();
        props.put("zookeeper.connect", zookeeper);
        props.put("group.id", groupId);
        props.put("zookeeper.session.timeout.ms", "400");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "smallest"); //means consume from the beginning
        return new ConsumerConfig(props);
    }
}
