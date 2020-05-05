package com.latihan.rabbitmq.producer;

import com.latihan.rabbitmq.model.Student;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketingProducer
{
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String exchange = "direct-exchange";
    private static final String routingKey = "marketingQueue";

    public void sendMaketingQueue(Student student)
    {
        rabbitTemplate.convertAndSend(exchange,routingKey,student);
    }
}
