package com.example.ecommerce.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record OrderItemRequestDto(

        @NotBlank(message = "Product ID cannot be blank")
        @JsonProperty("product_id")
        String productId,

        @Min(value = 1,message = "Quantity must be atleast 1")
        Integer quantity
) {
}
