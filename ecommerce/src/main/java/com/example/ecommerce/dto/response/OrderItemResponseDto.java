package com.example.ecommerce.dto.response;

import lombok.Builder;

@Builder
public record OrderItemResponseDto(
        String productId,
        int quantity,
        double unitPrice
) {
}
