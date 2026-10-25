package com.example.ecommerce.repository;

import com.example.ecommerce.dto.request.ProductCreateRequestDto;
import com.example.ecommerce.dto.request.ProductUpdateRequestDto;
import com.example.ecommerce.dto.response.ProductResponseDto;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.enums.ProductCategory;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ProductInMemoryRepository implements CrudRepository<Product,String>{
    private ConcurrentHashMap<String,Product> products = new ConcurrentHashMap<>();

    @PostConstruct
    public void init(){
        // Electronics
        seed("ELE-001", "Wireless Mouse", "2.4GHz ergonomic wireless mouse with silent clicks", ProductCategory.ELECTRONICS, 799.00, 120);
        seed("ELE-002", "Mechanical Keyboard", "Backlit mechanical keyboard with blue switches", ProductCategory.ELECTRONICS, 2999.00, 60);
        seed("ELE-003", "Bluetooth Earbuds", "True wireless earbuds with 24-hour battery life", ProductCategory.ELECTRONICS, 1999.00, 150);
        seed("ELE-004", "Power Bank 20000mAh", "Fast-charging power bank with dual USB output", ProductCategory.ELECTRONICS, 1799.00, 90);
        seed("ELE-005", "USB-C Hub", "7-in-1 USB-C hub with HDMI and card reader", ProductCategory.ELECTRONICS, 1499.00, 75);
        seed("ELE-006", "Smart Watch", "Fitness smart watch with heart rate and SpO2 monitor", ProductCategory.ELECTRONICS, 3499.00, 45);

        // Grocery
        seed("GRO-001", "Basmati Rice 5kg", "Premium aged long-grain basmati rice", ProductCategory.GROCERY, 649.00, 250);
        seed("GRO-002", "Cold Pressed Groundnut Oil 1L", "Chekku-style cold pressed groundnut oil", ProductCategory.GROCERY, 299.00, 300);
        seed("GRO-003", "Filter Coffee Powder 500g", "South Indian filter coffee blend with chicory", ProductCategory.GROCERY, 349.00, 220);
        seed("GRO-004", "Toor Dal 1kg", "Unpolished protein-rich toor dal", ProductCategory.GROCERY, 165.00, 280);
        seed("GRO-005", "Idli Rice 5kg", "Parboiled rice ideal for idli and dosa batter", ProductCategory.GROCERY, 380.00, 200);

        // Clothing
        seed("CLO-001", "Cotton T-Shirt", "Round neck 100% cotton t-shirt", ProductCategory.CLOTHING, 499.00, 200);
        seed("CLO-002", "Slim Fit Jeans", "Stretchable slim fit denim jeans", ProductCategory.CLOTHING, 1499.00, 110);
        seed("CLO-003", "Hooded Sweatshirt", "Fleece-lined pullover hoodie", ProductCategory.CLOTHING, 1299.00, 80);
        seed("CLO-004", "Running Shoes", "Lightweight breathable running shoes", ProductCategory.CLOTHING, 2499.00, 70);
        seed("CLO-005", "Formal Shirt", "Wrinkle-free full sleeve formal shirt", ProductCategory.CLOTHING, 999.00, 95);

        // Books
        seed("BOK-001", "Clean Code", "A handbook of agile software craftsmanship", ProductCategory.BOOKS, 899.00, 40);
        seed("BOK-002", "Effective Java", "Best practices for the Java platform", ProductCategory.BOOKS, 1049.00, 35);
        seed("BOK-003", "Designing Data-Intensive Applications", "Principles behind reliable, scalable systems", ProductCategory.BOOKS, 1599.00, 30);
        seed("BOK-004", "Introduction to Algorithms", "Comprehensive textbook on algorithms", ProductCategory.BOOKS, 1899.00, 25);
        seed("BOK-005", "Spring in Action", "Hands-on guide to building Spring applications", ProductCategory.BOOKS, 1199.00, 30);

        // Furniture
        seed("FUR-001", "Ergonomic Office Chair", "Mesh back chair with lumbar support and adjustable height", ProductCategory.FURNITURE, 6499.00, 25);
        seed("FUR-002", "Study Table", "Engineered wood study table with a storage shelf", ProductCategory.FURNITURE, 4299.00, 30);
        seed("FUR-003", "Bookshelf 5-Tier", "Sturdy five-tier open bookshelf", ProductCategory.FURNITURE, 3799.00, 20);
        seed("FUR-004", "Single Bed with Storage", "Solid wood single bed with under-bed drawers", ProductCategory.FURNITURE, 12999.00, 10);
        seed("FUR-005", "Shoe Rack", "Four-shelf compact shoe rack", ProductCategory.FURNITURE, 1499.00, 55);

        // Toys
        seed("TOY-001", "Building Blocks 500 pcs", "Colourful interlocking building blocks set", ProductCategory.TOYS, 999.00, 75);
        seed("TOY-002", "Remote Control Car", "Rechargeable 2.4GHz RC racing car", ProductCategory.TOYS, 1399.00, 50);
        seed("TOY-003", "Jigsaw Puzzle 1000 pcs", "Scenic landscape jigsaw puzzle", ProductCategory.TOYS, 549.00, 90);
        seed("TOY-004", "Chess Board Set", "Foldable magnetic chess board with pieces", ProductCategory.TOYS, 649.00, 60);
    }


    private void seed(String sku, String name, String description,
                      ProductCategory category, double price, int stockQuantity){
        save(Product.builder()
                .sku(sku)
                .name(name)
                .description(description)
                .productCategory(category)
                .price(price)
                .stockQuantity(stockQuantity)
                .build()
        );
    }


    @Override
    public Product save(Product product) {
        if(product.getId() == null){
            product.setId(UUID.randomUUID().toString());
        }
        products.put(product.getId(),product);
        return product;
    }

    @Override
    public Optional<Product> findById(String id) {
        if(id == null){
            return Optional.empty();
        }
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public boolean deleteById(String id) {
        return id!=null && products.remove(id)!=null;
    }


}
