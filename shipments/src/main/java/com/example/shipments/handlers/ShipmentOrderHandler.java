package com.example.shipments.handlers;

import com.example.shipments.dtos.PaymentResultDTO;
import com.example.shipments.facades.ShipmentOrderFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.example.shipments.configs.KafkaConfig.PAYMENT_TOPIC;

@Component
public record ShipmentOrderHandler(ShipmentOrderFacade shipmentOrderFacade) {

    private static final Logger log = LoggerFactory.getLogger(ShipmentOrderHandler.class);

    @KafkaListener(id = "shipment-payment", topics = PAYMENT_TOPIC)
    public void receivePayment(PaymentResultDTO request) {
        log.info("Received Payment {}", request);
        shipmentOrderFacade.makeShipment(request);
    }
}
