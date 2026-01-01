package com.ecommerce.events;

public class PaymentCompletedEvent {

    private Long orderId;
    private Long productId;
    private Integer quantity;
    private String paymentStatus;

    public PaymentCompletedEvent() {}

    public PaymentCompletedEvent(Long orderId, Long productId,
                                 Integer quantity, String paymentStatus) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.paymentStatus = paymentStatus;
    }

    public Long getOrderId() { return orderId; }
    public Long getProductId() { return productId; }
    public Integer getQuantity() { return quantity; }
    public String getPaymentStatus() { return paymentStatus; }

    @Override
    public String toString() {
        return "PaymentCompletedEvent{" +
                "orderId=" + orderId +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}
