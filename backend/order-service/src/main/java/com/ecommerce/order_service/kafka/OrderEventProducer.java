package com.ecommerce.order_service.kafka;

import com.ecommerce.events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class OrderEventProducer {

    private static final Logger log = LoggerFactory.getLogger(OrderEventProducer.class);

    private static final String TOPIC = "order-created";

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public void publishOrderCreatedEvent(OrderCreatedEvent event) {
        kafkaTemplate.send(TOPIC, event);
        System.out.println("Logesh Log - Published Kafka Event: " + event);

        
        // log.info("Published Kafka Event: {}", event);
        // log.debug("Kafka Topic: {}", TOPIC);
        // log.trace("Full Event Payload: {}", event);
    }
}
