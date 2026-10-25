package com.example.ecommerce.dto.response;

import com.example.ecommerce.enums.ProductCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record ProductResponseDto(
        String id,
        String sku,
        @JsonProperty("product_name")
        String name,

        ProductCategory productCategory,
        double price,
        int stockQuantity

) {
}
