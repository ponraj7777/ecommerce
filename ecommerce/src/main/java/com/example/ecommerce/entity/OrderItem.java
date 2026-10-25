package com.example.ecommerce.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItem {
    private String productId;
    private int quantity;
    private double unitPrice;
}
