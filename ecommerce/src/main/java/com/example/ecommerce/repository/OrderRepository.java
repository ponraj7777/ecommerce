package com.example.ecommerce.repository;


import com.example.ecommerce.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class OrderRepository implements CrudRepository<Order,String>{

    private ConcurrentHashMap<String,Order> orders = new ConcurrentHashMap<>();

    @Override
    public Order save(Order order) {
        if(order.getId() == null){
            String id = UUID.randomUUID().toString();
            order.setId(id);
        }
        orders.put(order.getId(), order);
        return order;
    }

    @Override
    public Optional<Order> findById(String id) {
        if(id == null) return Optional.empty();
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public boolean deleteById(String id) {
        return id!=null && orders.remove(id)!=null;
    }
}
