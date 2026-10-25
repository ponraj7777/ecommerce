package com.example.ecommerce.entity;

import com.example.ecommerce.enums.ProductCategory;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private String id;
    private String sku;
    private String name;
    private String description;
    private ProductCategory productCategory;
    private Double price;
    private Integer stockQuantity;

}
