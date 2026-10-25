package com.example.ecommerce.service;

import com.example.ecommerce.dto.request.OrderCreateRequestDto;
import com.example.ecommerce.dto.request.OrderItemRequestDto;
import com.example.ecommerce.dto.response.OrderResponseDto;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.enums.OrderStatus;
import com.example.ecommerce.events.OrderCreatedEvent;
import com.example.ecommerce.mapper.OrderMapper;
import com.example.ecommerce.repository.OrderRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final OrderMapper orderMapper;

    private final ApplicationEventPublisher publisher;
    public OrderService(OrderRepository orderRepository, ProductService productService, OrderMapper orderMapper, ApplicationEventPublisher publisher) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.orderMapper = orderMapper;
        this.publisher = publisher;
    }

    public OrderResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto){
        Order order = Order.builder()
                .customerId("GUEST_USER")
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.PENDING)
                .build();


        double totalAmount = 0;
        List<OrderItem> orderItems = new ArrayList<>();
        List<OrderItemRequestDto> orderItemRequestDtoList = orderCreateRequestDto.items();
        for(OrderItemRequestDto dto:orderItemRequestDtoList){
            Double price = productService.getPriceAndUpdateStock(dto.productId(),dto.quantity());
            totalAmount += (price*dto.quantity());
            OrderItem orderItem = OrderItem.builder()
                    .productId(dto.productId())
                    .quantity(dto.quantity())
                    .unitPrice(price)
                    .build();
            orderItems.add(orderItem);
        }

        order.setTotalAmount(totalAmount);
        order.setItems(orderItems);

        Order saved = orderRepository.save(order);
        publisher.publishEvent(new OrderCreatedEvent(saved.getId()));
        return orderMapper.toOrderDto(order);

    }
}
