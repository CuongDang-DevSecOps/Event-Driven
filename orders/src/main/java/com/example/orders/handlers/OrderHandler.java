package com.example.orders.handlers;

import com.example.orders.dtos.OrderPlacingDTO;
import com.example.orders.services.OrderService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public record OrderHandler(OrderService orderService) {

    public Mono<ServerResponse> placeOrder(final ServerRequest serverRequest) {
        return serverRequest.bodyToMono(OrderPlacingDTO.class)
                .flatMap(this.orderService::placeOrder)
                .flatMap(orderResult -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(orderResult)));
    }
}
