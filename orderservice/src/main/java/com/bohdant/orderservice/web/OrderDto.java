package com.bohdant.orderservice.web;

/**
 * Simple DTO representing an order payload for demonstration.
 */
public class OrderDto {

    private String orderId;
    private String restaurantId;
    private String items;
    private long totalCents;

    public OrderDto() {
        // no-args for Jackson
    }

    public OrderDto(final String orderId, final String restaurantId, final String items, final long totalCents) {
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.items = items;
        this.totalCents = totalCents;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(final String orderId) {
        this.orderId = orderId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(final String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getItems() {
        return items;
    }

    public void setItems(final String items) {
        this.items = items;
    }

    public long getTotalCents() {
        return totalCents;
    }

    public void setTotalCents(final long totalCents) {
        this.totalCents = totalCents;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderId='" + orderId + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", items='" + items + '\'' +
                ", totalCents=" + totalCents +
                '}';
    }
}

