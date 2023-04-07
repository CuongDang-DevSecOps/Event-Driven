package com.example.payments.handlers;

import com.example.payments.dtos.OrderResultDTO;
import com.example.payments.facades.PaymentOrderFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.example.payments.configs.KafkaConfig.ORDER_TOPIC;

@Component
public record PaymentOrderHandler(PaymentOrderFacade paymentOrderFacade) {

    private static final Logger log = LoggerFactory.getLogger(PaymentOrderHandler.class);

    @KafkaListener(id = "payment-order", topics = ORDER_TOPIC)
    public void receiveOrder(OrderResultDTO request) {
        log.info("Received Order {}", request);
        paymentOrderFacade.makePayment(request);
    }
}
