package com.bohdant.orderservice.web;

import com.bohdant.orderservice.kafka.OrderKafkaHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller that accepts order requests and publishes them to Kafka.
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrdersController.class);

    private final OrderKafkaHandler orderKafkaHandler;

    @Autowired
    public OrdersController(final OrderKafkaHandler orderKafkaHandler) {
        this.orderKafkaHandler = orderKafkaHandler;
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody final OrderDto orderDto) {
        LOGGER.info("Received create order request: {}", orderDto);
        orderKafkaHandler.sendOrderCreated(orderDto);
        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }
}
