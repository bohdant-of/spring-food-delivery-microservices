package com.bohdant.notificationservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka consumer for Notification Service. Listens for order-created and order-status events
 * and performs placeholder notification actions (logging for now).
 */
@Component
public class NotificationKafkaHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationKafkaHandler.class);
    private static final String ORDER_CREATED_TOPIC = "order-created";
    private static final String ORDER_STATUS_TOPIC = "order-status";

    /**
     * Listens for order-created events and logs a notification.
     *
     * @param message the incoming message payload (string)
     */
    @KafkaListener(topics = ORDER_CREATED_TOPIC, groupId = "notification-service-group")
    public void handleOrderCreated(final String message) {
        LOGGER.info("NotificationService received order-created event: {}", message);
        // TODO: integrate with real notification channel (email/SMS/push)
        sendNotification("Order created: " + message);
    }

    /**
     * Listens for order-status events (e.g., order ready) and notifies the customer.
     *
     * @param message the incoming message payload (string)
     */
    @KafkaListener(topics = ORDER_STATUS_TOPIC, groupId = "notification-service-group")
    public void handleOrderStatus(final String message) {
        LOGGER.info("NotificationService received order-status event: {}", message);
        // In a real system we'd parse status and target recipient; for now log and send placeholder
        sendNotification("Order status update: " + message);
    }

    private void sendNotification(final String message) {
        // Placeholder implementation: log the notification delivery
        LOGGER.info("Sending notification: {}", message);
    }
}
