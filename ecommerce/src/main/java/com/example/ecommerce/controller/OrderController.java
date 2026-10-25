package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.OrderCreateRequestDto;
import com.example.ecommerce.dto.response.OrderResponseDto;
import com.example.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(
            @Valid @RequestBody OrderCreateRequestDto orderCreateRequestDto
    ){
        OrderResponseDto orderResponseDto = orderService.createOrder(orderCreateRequestDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(orderResponseDto.id())
                .toUri();

        return ResponseEntity.created(location)
                .body(orderResponseDto);
    }
}
