package com.example.orders.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Configuration
public class KafkaConfig {

    public static final String CATALOG_TOPIC = "catalog";
    public static final String ORDER_TOPIC = "orders";

    @Bean
    public NewTopic orderTopic() {
        return TopicBuilder.name(ORDER_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public RecordMessageConverter recordMessageConverter() {
        return new StringJsonMessageConverter();
    }
}
