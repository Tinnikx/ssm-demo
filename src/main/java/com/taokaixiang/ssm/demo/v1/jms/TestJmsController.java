package com.taokaixiang.ssm.demo.v1.jms;

import com.taokaixiang.ssm.demo.v1.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestJmsController {

    @Autowired
    private JmsTemplate jmsQueueTemplate;

    @Autowired
    private JmsTemplate jmsTopicTemplate;

    @Autowired
    private JmsTemplate jmsExternalQueueTemplate;

    @Autowired
    private JmsTemplate jmsExternalTopicTemplate;

    @PostMapping("/send-queue-message")
    public void sendQueueMessage() {
        Car car = new Car();
        car.setColor("yellow");
        car.setName("teslla");
        jmsQueueTemplate.convertAndSend(car);
    }

    @PostMapping("/send-topic-message")
    public void sendTopicMessage() {
        Car car = new Car();
        car.setColor("red");
        car.setName("QQ");
        jmsTopicTemplate.convertAndSend(car);
    }

    @PostMapping("/send-external-queue-message")
    public void sendExternalQueueMessage() {
        jmsExternalQueueTemplate.send(session -> session.createTextMessage("nihaoahaoaa"));
    }

    @PostMapping("/send-external-topic-message")
    public void sendExternalTopicMessage() {
        jmsExternalTopicTemplate.send(session -> session.createTextMessage("test external topic message"));
    }
}
