package com.example.ecommerce.dto.request;

import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.enums.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record OrderCreateRequestDto(

        @NotNull(message = "Order must contain atleast one item")
        @Valid
        List<OrderItemRequestDto> items

) {
}
