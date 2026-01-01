package com.ecommerce.notification_service.kafka;

import com.ecommerce.events.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationEventConsumer {

    @KafkaListener(
            topics = "order-created",
            groupId = "notification-service"
    )
    public void consumeOrderCreatedEvent(OrderCreatedEvent event) {

        System.out.println("Logesh Says - Notification Service received event");
        System.out.println("Logesh Says - Notification Order ID: " + event.getOrderId());

        // Simulate Email
        sendEmail(event);

        // Simulate SMS
        sendSms(event);
    }

    private void sendEmail(OrderCreatedEvent event) {
        System.out.println("Logesh Says - Notification - Email sent for Order ID: " + event.getOrderId());
    }

    private void sendSms(OrderCreatedEvent event) {
        System.out.println("Logesh Says - Notification - SMS sent for Order ID: " + event.getOrderId());
    }
}
