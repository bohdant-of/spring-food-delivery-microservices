package com.bohdant.orderservice.kafka;

import com.bohdant.orderservice.web.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Kafka producer for Orders Service events.
 */
@Component
public class OrderKafkaHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderKafkaHandler.class);
    private static final String ORDER_CREATED_TOPIC = "order-created";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * Constructor for dependency injection.
     *
     * @param kafkaTemplate the Kafka template
     */
    @Autowired
    public OrderKafkaHandler(final KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Sends an order-created event to Kafka.
     *
     * @param orderDto the order data transfer object
     */
    public void sendOrderCreated(final OrderDto orderDto) {
        LOGGER.info("Sending order created event: {}", orderDto);
        // The KafkaTemplate in this project is configured to use String serializer for values,
        // so convert to a stable String representation. Using toString() is sufficient for now.
        kafkaTemplate.send(ORDER_CREATED_TOPIC, orderDto.toString());
    }
}

