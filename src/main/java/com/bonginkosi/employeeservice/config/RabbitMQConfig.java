package com.bonginkosi.employeeservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "employee.exchange";

    public static final String SALARY_QUEUE = "salary.queue";

    public static final String LEAVE_QUEUE = "leave.queue";

    public static final String ROUTING_KEY = "employee.created";


    @Bean
    public TopicExchange employeeExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }


    @Bean
    public Queue salaryQueue() {
        return new Queue(SALARY_QUEUE);
    }


    @Bean
    public Queue leaveQueue() {
        return new Queue(LEAVE_QUEUE);
    }


    @Bean
    public Binding salaryBinding(
            Queue salaryQueue,
            TopicExchange employeeExchange) {

        return BindingBuilder
                .bind(salaryQueue)
                .to(employeeExchange)
                .with(ROUTING_KEY);
    }


    @Bean
    public Binding leaveBinding(
            Queue leaveQueue,
            TopicExchange employeeExchange) {

        return BindingBuilder
                .bind(leaveQueue)
                .to(employeeExchange)
                .with(ROUTING_KEY);
    }

    // Converts EmployeeCreatedEvent into JSON
    @Bean
    public JacksonJsonMessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    // Configures RabbitTemplate to use the JSON converter
    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            JacksonJsonMessageConverter messageConverter) {

        RabbitTemplate rabbitTemplate =
                new RabbitTemplate(connectionFactory);

        rabbitTemplate.setMessageConverter(messageConverter);

        return rabbitTemplate;
    }
}