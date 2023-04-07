package com.example.catalogues.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    public static final String CATALOG_TOPIC = "catalog";

    @Bean
    public NewTopic catalogTopic() {
        return TopicBuilder.name(CATALOG_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
