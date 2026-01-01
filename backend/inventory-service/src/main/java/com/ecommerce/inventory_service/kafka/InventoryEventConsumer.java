package com.ecommerce.inventory_service.kafka;

import com.ecommerce.events.OrderCreatedEvent;
import com.ecommerce.events.PaymentCompletedEvent;

import lombok.RequiredArgsConstructor;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryEventConsumer {

    private final InventoryPaymentProducer paymentProducer;

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

        PaymentCompletedEvent paymentEvent =
                new PaymentCompletedEvent(
                        event.getOrderId(),
                        event.getProductId(),
                        event.getQuantity(),
                        "SUCCESS"
                );

        paymentProducer.publishPaymentCompleted(paymentEvent);
    }
}
