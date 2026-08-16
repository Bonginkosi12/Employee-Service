package com.bonginkosi.employeeservice.producer;

import com.bonginkosi.employeeservice.config.RabbitMQConfig;
import com.bonginkosi.employeeservice.event.EmployeeCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public EmployeeEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishEmployeeCreated(EmployeeCreatedEvent event) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                event
        );
    }
}
