package com.junlaninfo.Producter;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Producter {
	public static void main(String[] args) throws JMSException {
		// ConnectionFactory ：连接工厂，JMS 用它创建连接
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://192.168.196.131:61616");
		// JMS 客户端到JMS Provider 的连接
		Connection connection = connectionFactory.createConnection();
		connection.start();
		// Session： 一个发送或接收消息的线程
		// 这里设置session不携带事务，所以不用commit
		
		/*Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);*/
		//Session.CLIENT_ACKNOWLEDGE  客戶端调用acknowledge方法手动签收
		Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
		// Destination ：消息的目的地;消息发送给谁.
		// 获取session注意参数值my-queue是Query的名字
		Destination destination = session.createQueue("my-queue");
		// MessageProducer：消息生产者
		MessageProducer producer = session.createProducer(destination);
		// 设置不持久化
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		// 发送一条消息
		for (int i = 1; i <= 5; i++) {
			sendMsg(session, producer, i);
		}
		//session.commit();
		connection.close();
	}

	/**
	 * 在指定的会话上，通过指定的消息生产者发出一条消息
	 * 
	 * @param session  消息会话
	 * @param producer 消息生产者
	 */
	public static void sendMsg(Session session, MessageProducer producer, int i) throws JMSException {
		// 创建一条文本消息
		TextMessage message = session.createTextMessage("Hello ActiveMQ！" + i);
		// 通过消息生产者发出消息
		producer.send(message);
	}
}