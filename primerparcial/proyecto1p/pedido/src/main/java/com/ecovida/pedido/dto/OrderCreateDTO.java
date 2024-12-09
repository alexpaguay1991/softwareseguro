package com.ecovida.pedido.dto;

import com.ecovida.pedido.entidades.OrderStatus;
import com.ecovida.pedido.entidades.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderCreateDTO {

    private Long userId;
    private BigDecimal total;
    private OrderStatus status; // Usar el tipo enum correspondiente
    private String shippingAddress;
    private PaymentMethod paymentMethod; // Usar el tipo enum correspondiente
    private LocalDateTime orderDate; // Se agrega la fecha del pedido

    // Getters y Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
