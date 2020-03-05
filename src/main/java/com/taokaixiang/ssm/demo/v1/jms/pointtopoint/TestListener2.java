package com.taokaixiang.ssm.demo.v1.jms.pointtopoint;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.io.IOException;
import java.util.Map;

@Component
public class TestListener2 implements MessageListener {

    public void onMessage(Message message) {
        try{
            if (message instanceof BytesMessage) {
                BytesMessage message1 = (BytesMessage) message;
                byte[] bytes = new byte[(int) message1.getBodyLength()];
                message1.readBytes(bytes);
                Map<String, String> map = new ObjectMapper().readValue(bytes, new TypeReference<Map<String, String>>() {
                });
                System.out.println(TestListener2.class.getName() + " 收到的消息： " + map);
            } else if (message instanceof TextMessage)
                System.out.println(TestListener2.class.getName() + " 收到的消息： "+((TextMessage)message).getText());
            else
                System.out.println(TestListener2.class.getName() + " 收到一个未识别的消息: " + message.toString());
        } catch (JMSException | IOException e) {
            e.printStackTrace();
        }
    }
}
