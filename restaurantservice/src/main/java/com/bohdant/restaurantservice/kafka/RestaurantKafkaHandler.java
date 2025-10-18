package com.bohdant.restaurantservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Kafka producer and consumer for Restaurant Service events.
 */
@Component
public class RestaurantKafkaHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantKafkaHandler.class);
    private static final String ORDER_CREATED_TOPIC = "order-created";
    private static final String RESTAURANT_EVENT_TOPIC = "restaurant-event";
    private static final String ORDER_STATUS_TOPIC = "order-status";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * Constructor for dependency injection.
     *
     * @param kafkaTemplate the Kafka template
     */
    @Autowired
    public RestaurantKafkaHandler(final KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Sends a restaurant event message to Kafka.
     *
     * @param message the message to send (String or Avro object)
     */
    public void sendRestaurantEvent(final Object message) {
        LOGGER.info("Sending restaurant event: {}", message);
        kafkaTemplate.send(RESTAURANT_EVENT_TOPIC, message);
    }

    /**
     * Sends an order-status event to Kafka (e.g. order ready).
     *
     * @param message the status message to send
     */
    public void sendOrderStatus(final Object message) {
        LOGGER.info("Sending order status event: {}", message);
        kafkaTemplate.send(ORDER_STATUS_TOPIC, message);
    }

    /**
     * Listens for order created events from Kafka.
     *
     * @param message the received message
     */
    @KafkaListener(topics = ORDER_CREATED_TOPIC, groupId = "restaurant-service-group")
    public void listenOrderCreated(final Object message) {
        LOGGER.info("Received order created event: {}", message);
        // Handle the event (e.g., update restaurant availability)

        // For this demo wiring: immediately publish an order-status update indicating the order is ready.
        // In a real system this would be triggered when the kitchen finishes preparation.
        try {
            final String statusMessage = String.format("%s | status=READY", message == null ? "" : message.toString());
            sendOrderStatus(statusMessage);
        } catch (final Exception ex) {
            LOGGER.warn("Failed to send order-status for message {}: {}", message, ex.getMessage());
        }
    }
}
