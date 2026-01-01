package com.ecommerce.inventory_service.kafka;

import com.ecommerce.events.OrderCreatedEvent;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryEventConsumer {

    @KafkaListener(
            topics = "order-created",
            groupId = "inventory-service"
    )
    public void consumeOrderCreatedEvent(OrderCreatedEvent event) {

        System.out.println("Logesh Log - Inventory Service received event");
        System.out.println("Logesh Log - Order ID: " + event.getOrderId());
        System.out.println("Logesh Log - Product ID: " + event.getProductId());
        System.out.println("Logesh Log - Quantity: " + event.getQuantity());

        // Simulate stock deduction
        System.out.println("Logesh Log - Stock deducted successfully");
    }
}
