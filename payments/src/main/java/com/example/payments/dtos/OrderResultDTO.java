package com.example.payments.dtos;

import com.example.payments.enums.OrderStatus;

public record OrderResultDTO(String orderId, OrderStatus status) {
}
