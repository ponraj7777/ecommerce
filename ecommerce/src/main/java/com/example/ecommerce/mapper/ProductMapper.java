package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.request.ProductCreateRequestDto;
import com.example.ecommerce.dto.response.ProductResponseDto;
import com.example.ecommerce.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    // productCreateRequestDto to Product
    public Product toEntity(ProductCreateRequestDto productdto){
        return Product
                .builder()
                .sku(productdto.sku())
                .description(productdto.description())
                .name(productdto.name())
                .price(productdto.price())
                .productCategory(productdto.productCategory())
                .stockQuantity(productdto.stockQuantity())
                .build();
    }

    // Product to ProductResponseDto
    public ProductResponseDto toDto(Product product){
        return ProductResponseDto.builder()
                .sku(product.getSku())
                .id(product.getId())
                .name(product.getName())
                .productCategory(product.getProductCategory())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .build();

    }
}
