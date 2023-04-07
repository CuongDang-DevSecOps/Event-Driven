package com.example.catalogues.handlers;

import com.example.catalogues.dtos.CatalogInfoDTO;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static com.example.catalogues.configs.KafkaConfig.CATALOG_TOPIC;

@Component
public record CatalogHandler(KafkaTemplate<String, CatalogInfoDTO> kafkaTemplate) {

    public Mono<ServerResponse> publishSKU(final ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CatalogInfoDTO.class)
                .flatMap(request -> Mono.just(kafkaTemplate.send(CATALOG_TOPIC, request.catalogId(), request)))
                .flatMap(result -> ServerResponse
                        .status(201)
                        .build());
    }
}
