package com.example.catalogues.configs;

import com.example.catalogues.handlers.CatalogHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> catalogRoutes(final CatalogHandler catalogHandler) {
        return route()
                .POST("/api/v1/skus", RequestPredicates.contentType(MediaType.APPLICATION_JSON), catalogHandler::publishSKU)
                .build();
    }
}
