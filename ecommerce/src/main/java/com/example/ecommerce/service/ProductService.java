package com.example.ecommerce.service;

import com.example.ecommerce.dto.request.ProductCreateRequestDto;
import com.example.ecommerce.dto.request.ProductPatchRequestDto;
import com.example.ecommerce.dto.request.ProductUpdateRequestDto;
import com.example.ecommerce.dto.response.ProductResponseDto;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.enums.ProductCategory;
import com.example.ecommerce.exception.InsufficientStockException;
import com.example.ecommerce.exception.ProductNotFoundException;
import com.example.ecommerce.mapper.ProductMapper;
import com.example.ecommerce.repository.ProductInMemoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductInMemoryRepository productInMemoryRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductInMemoryRepository productInMemoryRepository, ProductMapper productMapper) {
        this.productInMemoryRepository = productInMemoryRepository;
        this.productMapper = productMapper;
    }

    public ProductResponseDto getProductById(String id){
        Product product = productInMemoryRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product not found with id: "+id));
        return productMapper.toDto(product);
    }

    public List<ProductResponseDto> getAllProducts(){
        List<Product> products = productInMemoryRepository.findAll();
        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }

    public ProductResponseDto createProduct(ProductCreateRequestDto productCreateRequestDto){
        Product newproduct = productMapper.toEntity(productCreateRequestDto);
        Product savedproduct = productInMemoryRepository.save(newproduct);
        return productMapper.toDto(savedproduct);
    }

    public void deleteProduct(String id){
        boolean isDeleted = productInMemoryRepository.deleteById(id);
        if(!isDeleted){
            throw new ProductNotFoundException("Product not found with id: "+id);
        }

    }

    public ProductResponseDto update(String id, ProductUpdateRequestDto productUpdateRequestDto) {
        Product existingProduct = productInMemoryRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product not found with id: "+id));

        existingProduct.setSku(productUpdateRequestDto.sku());
        existingProduct.setName(productUpdateRequestDto.name());
        existingProduct.setDescription(productUpdateRequestDto.description());
        existingProduct.setPrice(productUpdateRequestDto.price());
        existingProduct.setProductCategory(productUpdateRequestDto.productCategory());
        existingProduct.setStockQuantity(productUpdateRequestDto.stockQuantity());

        Product updateProduct = productInMemoryRepository.save(existingProduct);
        return productMapper.toDto(updateProduct);

    }

    public ProductResponseDto patch(String id, ProductPatchRequestDto productPatchRequestDto){
        Product existingProduct = productInMemoryRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException("Product not found with id: "+id));

        String sku = productPatchRequestDto.sku()!=null ? productPatchRequestDto.sku() : existingProduct.getSku();
        String name = productPatchRequestDto.name()!=null ? productPatchRequestDto.name() : existingProduct.getName();
        Double price = productPatchRequestDto.price()!=null ? productPatchRequestDto.price() : existingProduct.getPrice();
        ProductCategory productCategory = productPatchRequestDto.productCategory()!=null ? productPatchRequestDto.productCategory() : existingProduct.getProductCategory();
        Integer stock = productPatchRequestDto.stockQuantity()!=null ? productPatchRequestDto.stockQuantity() : existingProduct.getStockQuantity();
        String description = productPatchRequestDto.description()!=null ? productPatchRequestDto.description() : existingProduct.getDescription();

        existingProduct.setSku(sku);
        existingProduct.setName(name);
        existingProduct.setPrice(price);
        existingProduct.setProductCategory(productCategory);
        existingProduct.setStockQuantity(stock);
        existingProduct.setDescription(description);

        Product updatedProduct = productInMemoryRepository.save(existingProduct);
        return productMapper.toDto(updatedProduct);

    }

    public Double getPriceAndUpdateStock(String id,int quantity){
        Product product = productInMemoryRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException("Product not found with id: "+id));

        if(quantity > product.getStockQuantity()){
            throw new InsufficientStockException("Not enough stock for product: " + product.getName()
                    + ". Available: " + product.getStockQuantity());
        }

        product.setStockQuantity(product.getStockQuantity() - quantity);
        Product savedProduct = productInMemoryRepository.save(product);
        return savedProduct.getPrice();
    }




}
