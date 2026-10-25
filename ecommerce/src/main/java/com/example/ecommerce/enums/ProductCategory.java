package com.example.ecommerce.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductCategory {
    ELECTRONICS,
    GROCERY,
    CLOTHING,
    BOOKS,
    FURNITURE,
    TOYS;

    @JsonValue
    public String toJson(){
        return name().toLowerCase();
    }

    @JsonCreator
    public static ProductCategory fromJson(String value){
        return ProductCategory.valueOf(value.toUpperCase());
    }
}

