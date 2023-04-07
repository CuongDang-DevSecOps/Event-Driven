package com.example.shipments.dtos;

import com.example.shipments.enums.PaymentStatus;

public record PaymentResultDTO(String paymentId, String orderId, PaymentStatus status) {
}
