package com.example.orders.services;

import com.example.orders.dtos.OrderPlacingDTO;
import com.example.orders.dtos.OrderResultDTO;
import com.example.orders.enums.OrderStatus;
import com.example.orders.repositories.CatalogSKURepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.example.orders.configs.KafkaConfig.ORDER_TOPIC;

@Service
public record OrderService(KafkaTemplate<String, OrderResultDTO> kafkaTemplate,
                           CatalogSKURepository catalogSKURepository) {

    public Mono<OrderResultDTO> placeOrder(final OrderPlacingDTO request) {
        validateOrder(request);
        var orderId = UUID.randomUUID().toString();
        var orderStatus = OrderStatus.PLACED_ORDER;
        var orderResult = new OrderResultDTO(orderId, orderStatus);
        kafkaTemplate.send(ORDER_TOPIC, orderId, orderResult);
        return Mono.just(orderResult);
    }

    private void validateOrder(OrderPlacingDTO request) {
        var has = request.items().stream()
                .filter(o -> !catalogSKURepository.isValidSKU(o.itemId(), o.quantity()))
                .count();

        if (has > 0) {
            throw new RuntimeException("Bad Request!");
        }
    }
}
