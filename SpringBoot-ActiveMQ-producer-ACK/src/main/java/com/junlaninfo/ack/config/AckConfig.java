package com.junlaninfo.ack.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

/*
 * 手动签收
 */
@Configuration
public class AckConfig {
	@Bean
	public JmsTemplate jmsTemplate(ActiveMQConnectionFactory factory) {
	    JmsTemplate jmsTemplate = new JmsTemplate();
	    //关闭事物
	    jmsTemplate.setSessionTransacted(false);
	    //设置为单条消息确认
	    jmsTemplate.setSessionAcknowledgeMode(4);
	    jmsTemplate.setConnectionFactory(factory);
	    return jmsTemplate;
	}

}
