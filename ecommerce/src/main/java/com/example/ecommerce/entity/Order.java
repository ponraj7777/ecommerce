package com.example.ecommerce.entity;

import com.example.ecommerce.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    private String id;
    private String customerId; // We will hardcode this until you add Spring Security (Users)
    private List<OrderItem> items;
    private double totalAmount;
    private OrderStatus status;
    private LocalDateTime orderDate;
}
