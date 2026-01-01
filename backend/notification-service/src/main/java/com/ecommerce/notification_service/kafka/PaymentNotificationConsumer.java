package com.ecommerce.notification_service.kafka;

import com.ecommerce.events.PaymentCompletedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentNotificationConsumer {

    @KafkaListener(
            topics = "payment-completed",
            groupId = "notification-service"
    )
    public void consumePaymentCompleted(PaymentCompletedEvent event) {

        System.out.println("Logesh Says -  Payment Notification received");
        System.out.println("Logesh Says - Order ID: " + event.getOrderId());
        System.out.println("Logesh Says - Payment Status: " + event.getPaymentStatus());

        sendPaymentConfirmation(event);
    }

    private void sendPaymentConfirmation(PaymentCompletedEvent event) {
        System.out.println("Logesh Says -  Payment confirmation sent for Order ID: "
                + event.getOrderId());
    }
}