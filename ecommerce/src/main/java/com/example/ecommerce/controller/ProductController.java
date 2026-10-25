package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.ProductCreateRequestDto;
import com.example.ecommerce.dto.request.ProductPatchRequestDto;
import com.example.ecommerce.dto.request.ProductUpdateRequestDto;
import com.example.ecommerce.dto.response.ProductResponseDto;
import com.example.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(
            @PathVariable String id
    ){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> postProduct(
            @Valid @RequestBody ProductCreateRequestDto productCreateRequestDto
    ){
        ProductResponseDto productResponseDto = productService.createProduct(productCreateRequestDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productResponseDto.id())
                .toUri();

        return ResponseEntity.created(location)
                .body(productResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> putProduct(
            @PathVariable String id,
            @Valid @RequestBody ProductUpdateRequestDto productUpdateRequestDto
    ){
        ProductResponseDto productResponseDto = productService.update(id,productUpdateRequestDto);

        return ResponseEntity.ok(productResponseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDto> patchProduct(
            @PathVariable String id,
            @Valid @RequestBody ProductPatchRequestDto productPatchRequestDto
    ){
        ProductResponseDto productResponseDto = productService.patch(id,productPatchRequestDto);

        return ResponseEntity.ok(productResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable String id
    ){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
