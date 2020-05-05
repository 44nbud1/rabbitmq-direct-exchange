package com.latihan.rabbitmq.producer;

import com.latihan.rabbitmq.model.Student;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminProducer
{
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static final String exchange = "direct-exchange";
    public static final String routingKey = "adminQueue";

    public void sendToRabbit(Student student)
    {
        rabbitTemplate.convertAndSend(exchange,routingKey,student);
    }

}
