package com.example.orders.handlers;

import com.example.orders.dtos.CatalogInfoDTO;
import com.example.orders.repositories.CatalogSKURepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.example.orders.configs.KafkaConfig.CATALOG_TOPIC;

@Component
public record CatalogHandler(CatalogSKURepository catalogSKURepository) {

    private static final Logger log = LoggerFactory.getLogger(CatalogHandler.class);

    @KafkaListener(id = "catalog-order", topics = CATALOG_TOPIC)
    public void receiveOrder(CatalogInfoDTO request) {
        log.info("Received Catalog {}", request);
        catalogSKURepository.storeSKU(request);
    }
}
