package com.example.payments.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Configuration
public class KafkaConfig {

    public static final String ORDER_TOPIC = "orders";
    public static final String PAYMENT_TOPIC = "payments";

    @Bean
    public RecordMessageConverter recordMessageConverter() {
        return new StringJsonMessageConverter();
    }
}
