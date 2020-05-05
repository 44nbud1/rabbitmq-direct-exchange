package com.latihan.rabbitmq.producer;

import com.latihan.rabbitmq.consumer.FinanceConsumer;
import com.latihan.rabbitmq.model.Student;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinanceProducer
{
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String exchange = "direct-exchange";
    private static final String routingKey = "financeQueue";

    public void sendFinanceToRabbit(Student student)
    {
        rabbitTemplate.convertAndSend(exchange,routingKey,student);
    }
}
