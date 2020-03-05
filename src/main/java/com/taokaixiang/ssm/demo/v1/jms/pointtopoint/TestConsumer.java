package com.taokaixiang.ssm.demo.v1.jms.pointtopoint;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TestConsumer {


    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;//默认用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;//默认密码
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;//默认连接地址


    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory;//连接工厂，用来生产Connection
        Connection connection;
        Session session;//会话，接收或者发送消息的线程
        Destination destination;//消息的目的地
        MessageConsumer messageConsumer;//消息消费者
        //实例化连接工厂
        connectionFactory=new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
        connection=connectionFactory.createConnection();//通过连接工厂获取链接
        connection.start();//启动连接
        session=connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);//获取session 不需要加事务了
        destination=session.createTopic("FirstQueue1");//创建消息队列,名为FirstQueue1
        messageConsumer=session.createConsumer(destination);//创建消息消费者
        //注册消息监听
        messageConsumer.setMessageListener(new TestListener());

        System.in.read();
        messageConsumer.close();
        session.close();
        connection.close();
    }

}
