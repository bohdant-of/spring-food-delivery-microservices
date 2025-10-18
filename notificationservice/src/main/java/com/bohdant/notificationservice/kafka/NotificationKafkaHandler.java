package com.bohdant.notificationservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Kafka producer and consumer for Notification Service events.
 */
@Component
public class NotificationKafkaHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationKafkaHandler.class);
    private static final String ORDER_STATUS_TOPIC = "order-status";
    private static final String NOTIFICATION_TOPIC = "notifications";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * Constructor for dependency injection.
     *
     * @param kafkaTemplate the Kafka template
     */
    @Autowired
    public NotificationKafkaHandler(final KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Sends a notification event to Kafka.
     *
     * @param message the message to send
     */
    public void sendNotification(final Object message) {
        LOGGER.info("Sending notification event: {}", message);
        kafkaTemplate.send(NOTIFICATION_TOPIC, message);
    }

    /**
     * Listens for order status updates from Kafka.
     *
     * @param message the received message
     */
    @KafkaListener(topics = ORDER_STATUS_TOPIC, groupId = "notification-service-group")
    public void listenOrderStatus(final Object message) {
        LOGGER.info("Received order status update: {}", message);
        // Handle the event (e.g., send notification to user)
    }
}
