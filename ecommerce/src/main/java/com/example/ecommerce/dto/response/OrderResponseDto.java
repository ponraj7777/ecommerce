package com.example.ecommerce.dto.response;

import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record OrderResponseDto(

        @JsonProperty("order_id")
        String id,
        String customerId,// We will hardcode this until you add Spring Security (Users)
        List<OrderItem>items,
        Double totalAmount,
        OrderStatus status,
        @JsonFormat(pattern = "dd-MM-yyyy  HH:mm:ss")
        LocalDateTime orderDate
) {
}
