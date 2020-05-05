package com.latihan.rabbitmq.consumer;

import com.latihan.rabbitmq.model.Student;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class FinanceConsumer
{
    static final String financeQueue= "financeQueue";

    @RabbitListener(queues = financeQueue)
    public Student takeFinanceRabbit(Student student)
    {
        Student stdn = new Student();
        stdn.setNama(student.getNama());
        stdn.setUsia(student.getUsia());
        return stdn;
    }
}
