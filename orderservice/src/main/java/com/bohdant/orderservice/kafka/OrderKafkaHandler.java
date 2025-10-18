package com.bohdant.orderservice.kafka;

import com.bohdant.orderservice.web.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Kafka producer and consumer for Order Service events.
 */
@Component
public class OrderKafkaHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderKafkaHandler.class);
    private static final String ORDER_CREATED_TOPIC = "order-created";
    private static final String ORDER_STATUS_TOPIC = "order-status";

    private final KafkaTemplate<String, OrderDto> kafkaTemplate;

    /**
     * Constructor for dependency injection.
     *
     * @param kafkaTemplate the Kafka template
     */
    @Autowired
    public OrderKafkaHandler(final KafkaTemplate<String, OrderDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Sends an order created event to Kafka.
     *
     * @param order the order to send
     */
    public void sendOrderCreated(final OrderDto order) {
        final String key = order != null ? order.getOrderId() : null;
        LOGGER.info("Sending order created event for orderId={}: {}", key, order);
        kafkaTemplate.send(ORDER_CREATED_TOPIC, key, order);
    }

    /**
     * Listens for order status updates from Kafka.
     *
     * @param message the received message
     */
    @KafkaListener(topics = ORDER_STATUS_TOPIC, groupId = "order-service-group")
    public void listenOrderStatus(final Object message) {
        LOGGER.info("Received order status update: {}", message);
        // Handle the event (e.g., update order status)
    }
}
