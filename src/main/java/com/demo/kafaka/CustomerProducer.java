package com.demo.kafaka;

import com.demo.entity.Customers;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerProducer {

    private static final String TOPIC = "customer-topic";

    private final KafkaTemplate<String, Customers> kafkaTemplate;

    public CustomerProducer(KafkaTemplate<String, Customers> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Customers customer) {
        System.out.println("Sending to Kafka: " + customer);
        kafkaTemplate.send(TOPIC, customer);
    }
}