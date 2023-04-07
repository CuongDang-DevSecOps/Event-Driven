package com.example.payments.dtos;

import com.example.payments.enums.PaymentStatus;

public record PaymentResultDTO(String paymentId, String orderId, PaymentStatus status) {
}
