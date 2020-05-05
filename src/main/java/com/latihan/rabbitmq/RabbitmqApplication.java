package com.latihan.rabbitmq;

import com.rabbitmq.client.impl.AMQImpl;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RabbitmqApplication {

/*
Configuration rabbit mq
 */
    // Queue
    @Bean
    Queue marketingQueue()
    {
        return new Queue("marketingQueue",true);
    }

    @Bean
    Queue financeQueue()
    {
        return new Queue("financeQueue",true);
    }

    @Bean
    Queue adminQueue()
    {
        return new Queue("adminQueue",true);
    }

    // Exchange

    @Bean
    DirectExchange directExchange()
    {
        return new DirectExchange("direct-exchange");
    }

    // Binding
    @Bean
    Binding marketingBinding(Queue marketingQueue, DirectExchange exchange)
    {
        return BindingBuilder.bind(marketingQueue).to(exchange).with("marketing");
    }

    @Bean
    Binding financeBinding(Queue financeQueue, DirectExchange directExchange)
    {
        return BindingBuilder.bind(financeQueue).to(directExchange).with("finance");
    }

    @Bean
    Binding adminBinding(Queue adminQueue, DirectExchange directExchange)
    {
        return BindingBuilder.bind(adminQueue).to(directExchange).with("admin");
    }

    //produce and consume json file

    @Bean
    MessageConverter jsonMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    AmqpTemplate amqpTemplate (ConnectionFactory connectionFactory)
    {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApplication.class, args);
    }

}
