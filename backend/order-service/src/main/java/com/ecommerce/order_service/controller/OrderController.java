package com.ecommerce.order_service.controller;

import com.ecommerce.events.OrderCreatedEvent;

import com.ecommerce.order_service.dto.OrderRequest;
import com.ecommerce.order_service.kafka.OrderEventProducer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderEventProducer orderEventProducer;
    private static final AtomicLong ORDER_ID_SEQ = new AtomicLong(1);

    public OrderController(OrderEventProducer orderEventProducer) {
        this.orderEventProducer = orderEventProducer;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createOrder(
            @RequestBody OrderRequest orderRequest) {

        Long orderId = ORDER_ID_SEQ.getAndIncrement();

        OrderCreatedEvent event = new OrderCreatedEvent(orderId,orderRequest.getProductId(),orderRequest.getQuantity());

        if (orderRequest.getQuantity() <= 0) {
             return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Quantity must be greater than zero"));
        }

        if (orderRequest.getProductId() <= 0) {
             return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Product ID Info is invalid"));
        }

        else {
            orderEventProducer.publishOrderCreatedEvent(event);
            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("status", "ORDER_CREATED"));
        }

    }
}
