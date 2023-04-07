package com.example.shipments.facades;

import com.example.shipments.dtos.PaymentResultDTO;
import org.springframework.stereotype.Component;

@Component
public record ShipmentOrderFacade() {

    public void makeShipment(PaymentResultDTO paymentResult) {
        //
    }
}
