package com.example.orders.dtos;

import com.example.orders.enums.OrderStatus;

public record OrderResultDTO(String orderId, OrderStatus status) {
}
