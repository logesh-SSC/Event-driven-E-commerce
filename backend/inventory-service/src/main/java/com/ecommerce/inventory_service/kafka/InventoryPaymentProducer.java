package com.ecommerce.inventory_service.kafka;

import com.ecommerce.events.PaymentCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryPaymentProducer {

    private static final String TOPIC = "payment-completed";

    private final KafkaTemplate<String, PaymentCompletedEvent> kafkaTemplate;

    public void publishPaymentCompleted(PaymentCompletedEvent event) {
        kafkaTemplate.send(TOPIC, event);
        System.out.println("Logesh Say - Inventory published payment event: " + event);
    }
}
