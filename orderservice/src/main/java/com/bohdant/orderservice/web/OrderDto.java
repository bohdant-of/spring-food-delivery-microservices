package com.bohdant.orderservice.web;

import java.util.Objects;

/**
 * Immutable DTO representing an order.
 */
public final class OrderDto {

    private final String orderId;
    private final String customerId;
    private final String restaurantId;
    private final String details;

    public OrderDto(final String orderId, final String customerId, final String restaurantId, final String details) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.details = details;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", details='" + details + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OrderDto orderDto = (OrderDto) o;
        return Objects.equals(orderId, orderDto.orderId) &&
                Objects.equals(customerId, orderDto.customerId) &&
                Objects.equals(restaurantId, orderDto.restaurantId) &&
                Objects.equals(details, orderDto.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, customerId, restaurantId, details);
    }
}

