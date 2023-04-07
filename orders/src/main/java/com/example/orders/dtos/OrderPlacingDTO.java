package com.example.orders.dtos;

import java.util.Set;

public record OrderPlacingDTO(String customerId, Set<OrderLineItemDTO> items) {
}
