package com.example.ecommerce.service;

import com.example.ecommerce.events.OrderCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @EventListener
    public void sendOrderConfirmation(OrderCreatedEvent event){
        System.out.println("========================================");
        System.out.println("EMAIL SERVICE: Sending receipt for Order ID: " + event.orderid());
        System.out.println("========================================");
    }
}
