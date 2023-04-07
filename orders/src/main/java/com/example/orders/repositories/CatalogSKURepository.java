package com.example.orders.repositories;

import com.example.orders.dtos.CatalogInfoDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CatalogSKURepository {

    private static final Map<String, CatalogInfoDTO> SKUS = new HashMap<>();

    public void storeSKU(CatalogInfoDTO catalog) {
        SKUS.put(catalog.catalogId(), catalog);
    }

    public boolean isValidSKU(String catalogId, int quantity) {
        var catalog = SKUS.get(catalogId);
        return null != catalog && quantity <= catalog.amount();
    }
}
