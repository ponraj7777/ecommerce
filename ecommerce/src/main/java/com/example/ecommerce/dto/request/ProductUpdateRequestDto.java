package com.example.ecommerce.dto.request;

import com.example.ecommerce.enums.ProductCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductUpdateRequestDto(
        @NotBlank(message = "SKU cannot be Blank")
        String sku,

        @JsonProperty("product_name")
        @NotBlank(message = "Product name cannot be blank")
        String name,

        @JsonProperty("product_description")
        String description,

        @NotNull(message = "productCategory cannot be null")
        ProductCategory productCategory,

        @Min(value = 0,message = "Price cannot be negative")
        double price,

        @Min(value = 0,message = "Stock cannot be negative")
        int stockQuantity
) {
}
