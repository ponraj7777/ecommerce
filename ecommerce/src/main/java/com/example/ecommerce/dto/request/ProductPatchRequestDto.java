package com.example.ecommerce.dto.request;

import com.example.ecommerce.enums.ProductCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductPatchRequestDto(

        String sku,

        @JsonProperty("product_name")

        String name,

        @JsonProperty("product_description")
        String description,

        ProductCategory productCategory,

        @Min(value = 0,message = "Price cannot be negative")
        Double price,

        @Min(value = 0,message = "Stock cannot be negative")
        Integer stockQuantity
) {
}
