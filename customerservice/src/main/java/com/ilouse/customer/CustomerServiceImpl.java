package com.ilouse.customer;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepo customerRepo;
    private final RestTemplate restTemplate;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${notification.topic}")
    private String topic;


    @Override
    public Customer registerCustomer(Customer customer) throws IllegalAccessException {
        customerRepo.saveAndFlush(customer);

        Boolean isFraudster = restTemplate.getForObject("http://localhost:8082/fraudcheck/" + customer.getId(), Boolean.class);

        if (Boolean.TRUE.equals(isFraudster)) {
            throw new IllegalAccessException("This customer is a fraudster");
        }

        sendNotificationEvent("New customer registered: " + customer.getId());

        return customer;
    }

    public void sendNotificationEvent(String message) {
        kafkaTemplate.send(topic, message);
    }
}
