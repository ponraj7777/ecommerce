package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.request.OrderCreateRequestDto;
import com.example.ecommerce.dto.response.OrderItemResponseDto;
import com.example.ecommerce.dto.response.OrderResponseDto;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.enums.OrderStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class OrderMapper {


    public OrderResponseDto toOrderDto(Order order){
        List<OrderItemResponseDto> orderResponseDtoList = order.getItems()
                .stream()
                .map(this::toOrderItemDto)
                .toList();

        return OrderResponseDto.builder()
                .customerId(order.getCustomerId())
                .id(order.getId())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .items(order.getItems())
                .build();

    }

    public OrderItemResponseDto toOrderItemDto(OrderItem orderItem){
        return OrderItemResponseDto.builder()
                .productId(orderItem.getProductId())
                .quantity(orderItem.getQuantity())
                .unitPrice(orderItem.getUnitPrice())
                .build();
    }

}
