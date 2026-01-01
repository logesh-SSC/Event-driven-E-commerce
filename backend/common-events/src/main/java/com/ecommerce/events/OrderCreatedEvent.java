package com.ecommerce.events;

public class OrderCreatedEvent {
    private Long orderId;
    private Long productId;
    private Integer quantity;

    public OrderCreatedEvent() {} 

    public OrderCreatedEvent(Long orderId, Long productId, Integer quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }


    public Long getOrderId() { return orderId; }
    public Long getProductId() { return productId; }
    public Integer getQuantity() { return quantity; }
    
    @Override
    public String toString() {
        return "Logesh Log - OrderCreatedEvent{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}