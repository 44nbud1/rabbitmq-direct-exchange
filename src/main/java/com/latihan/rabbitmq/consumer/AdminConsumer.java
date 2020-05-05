package com.latihan.rabbitmq.consumer;

import com.latihan.rabbitmq.model.Student;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminConsumer
{
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String queue = "adminQueue";

    @RabbitListener(queues = queue)
    public Student takeAdmin(Student student)
    {
        Student stdn = new Student();
        stdn.setNama(student.getNama());
        stdn.setUsia(student.getUsia());
        return stdn;
    }
}
