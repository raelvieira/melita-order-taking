package com.melita.ordertakingapi.modules.order.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderAMQPConfiguration {
    @Bean
    public Queue createQueue() {
        return QueueBuilder.durable("new.order").build();
    }

    @Bean
    public RabbitAdmin createRabbitAdmin(ConnectionFactory con) {
        return new RabbitAdmin(con);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initializeAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        return jackson2JsonMessageConverter;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory con, Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(con);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}
