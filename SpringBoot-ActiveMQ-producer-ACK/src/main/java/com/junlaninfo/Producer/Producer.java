package com.junlaninfo.Producer;
import javax.jms.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
 
@Component
@EnableScheduling
public class Producer {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	/**
	 * 发送消息，estination是发送到的队列，message是待发送的消息
	 * @param destination
	 * @param message
	 */
	/*
	 * public void sendMessage(Destination destination, final String message) {
	 * System.out.println(jmsTemplate.getDeliveryMode());
	 * jmsTemplate.convertAndSend(destination, message); }
	 */
	/**
	 * 发送消息，message是待发送的消息
	 * @param message
	 */
	@Scheduled(fixedDelay = 5000)
	public void sendMessage() {
		System.out.println(jmsTemplate.getDeliveryMode());
			jmsTemplate.convertAndSend("queue1","测试消息队列" + System.currentTimeMillis());
	}
 
}
