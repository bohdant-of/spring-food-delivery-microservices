package com.bohdant.notificationservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka consumer for Notification Service. Listens for order-created events
 * and performs a placeholder notification action (logging for now).
 */
@Component
public class NotificationKafkaHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationKafkaHandler.class);
    private static final String ORDER_CREATED_TOPIC = "order-created";

    /**
     * Listens for order-created events and logs a notification.
     *
     * @param message the incoming message payload (string)
     */
    @KafkaListener(topics = ORDER_CREATED_TOPIC, groupId = "notification-service-group")
    public void handleOrderCreated(final String message) {
        LOGGER.info("NotificationService received order-created event: {}", message);
        // TODO: integrate with real notification channel (email/SMS/push)
        sendNotification(message);
    }

    private void sendNotification(final String message) {
        // Placeholder implementation: log the notification delivery
        LOGGER.info("Sending notification for order: {}", message);
    }
}

