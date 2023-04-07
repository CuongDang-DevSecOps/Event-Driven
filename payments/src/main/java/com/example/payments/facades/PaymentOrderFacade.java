package com.example.payments.facades;

import com.example.payments.dtos.OrderResultDTO;
import com.example.payments.dtos.PaymentResultDTO;
import com.example.payments.enums.PaymentStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.example.payments.configs.KafkaConfig.PAYMENT_TOPIC;

@Component
public record PaymentOrderFacade(KafkaTemplate<String, PaymentResultDTO> kafkaTemplate) {

    public void makePayment(OrderResultDTO orderResult) {
        var paymentId = UUID.randomUUID().toString();
        var paymentStatus = PaymentStatus.PAID;
        var paymentResult = new PaymentResultDTO(paymentId, orderResult.orderId(), paymentStatus);
        kafkaTemplate.send(PAYMENT_TOPIC, orderResult.orderId(), paymentResult);
    }
}
