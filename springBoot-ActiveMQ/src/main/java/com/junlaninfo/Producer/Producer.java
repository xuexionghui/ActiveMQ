/**
 * 
 */
package com.junlaninfo.Producer;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

/**
 * @author 辉
 *
 */
@Component
@EnableScheduling
public class Producer {
	@Autowired
	private JmsMessagingTemplate  jmsMessagingTemplate;
	
	@Autowired
	private  Queue  queue;
	
	@Scheduled(fixedDelay = 5000)
	public void  sendMessage() {
		jmsMessagingTemplate.convertAndSend(queue,  
				"测试消息队列" + System.currentTimeMillis());
	}

}
