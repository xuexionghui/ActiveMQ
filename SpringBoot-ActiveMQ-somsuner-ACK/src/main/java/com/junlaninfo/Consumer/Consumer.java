package com.junlaninfo.Consumer;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	@Value("${queue}")
	private String queue;

	@Bean
	public DefaultMessageListenerContainer defaultMessageListenerContainer(ConnectionFactory connectionFactory) {
		DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
		defaultMessageListenerContainer.setSessionAcknowledgeMode(4);// 设置单条消息确认
		defaultMessageListenerContainer.setDestinationName(queue);// 需要指定队列
		defaultMessageListenerContainer.setSessionTransacted(false);// 关闭事物，否则不生效

		MessageListener messageListener = new MessageListener() {

			public void onMessage(Message message) {

				try {
					System.out.println("消费消息：" + message); // 手动确认消息，如若不确认，消息将一直存在于消息队列中
					message.acknowledge();
				} catch (JMSException e) {
					e.printStackTrace();
				}

			}
		}; // 消费消息
		defaultMessageListenerContainer.setMessageListener(messageListener);

		defaultMessageListenerContainer.setConnectionFactory(connectionFactory);
		return defaultMessageListenerContainer;
	}

	// @JmsListener(destination = "${queue}")
	public void receive(String msg) {
		System.out.println("监听器收到msg:" + msg);
	}
}
