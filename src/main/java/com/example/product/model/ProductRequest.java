package com.example.product.model;

import com.example.product.entity.ProductStatus;
import com.example.product.entity.ProductType;

public record ProductRequest(
        String name,
        double price,
        int quantity,
        ProductStatus status,
        ProductType type
) {

}
