package com.latihan.rabbitmq.controller;

import com.latihan.rabbitmq.consumer.AdminConsumer;
import com.latihan.rabbitmq.consumer.FinanceConsumer;
import com.latihan.rabbitmq.consumer.MarketingConsumer;
import com.latihan.rabbitmq.model.Student;
import com.latihan.rabbitmq.producer.AdminProducer;
import com.latihan.rabbitmq.producer.FinanceProducer;
import com.latihan.rabbitmq.producer.MarketingProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private MarketingProducer marketingProducer;

    @Autowired
    private FinanceProducer financeProducer;

    @Autowired
    private MarketingConsumer marketingConsumer;

    @Autowired
    private FinanceConsumer financeConsumer;

    @Autowired
    private AdminProducer adminProducer;

    @Autowired
    private AdminConsumer adminConsumer;

    @PostMapping("/marketing")
    public ResponseEntity<?> marketing(@RequestBody Student student)
    {
        //send to rabbit
        marketingProducer.sendMaketingQueue(student);
        //take from rabbit
        Student stdn = marketingConsumer.takeMarketing(student);

        return new ResponseEntity<>(stdn, HttpStatus.ACCEPTED);
    }

    @PostMapping("/finance")
    public ResponseEntity<?> finance(@RequestBody Student student)
    {
        //send to rabbit
        financeProducer.sendFinanceToRabbit(student);
        //take from rabbit
        financeConsumer.takeFinanceRabbit(student);
        Student stdn = financeConsumer.takeFinanceRabbit(student);
        return new ResponseEntity<>(stdn, HttpStatus.ACCEPTED);
    }

    @PostMapping("/admin")
    public ResponseEntity<?> admin(@RequestBody Student student)
    {
        //send to rabbit
        adminProducer.sendToRabbit(student);
        //take from rabbit
        adminConsumer.takeAdmin(student);
        Student stdn = financeConsumer.takeFinanceRabbit(student);
        return new ResponseEntity<>(stdn, HttpStatus.ACCEPTED);
    }

}
