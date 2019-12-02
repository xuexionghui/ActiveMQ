package com.junlaninfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * 默认情况下，springboot与activemq整合是开启事务的，
 * 即使设置了手动签收也不生效
 */
@SpringBootApplication
public class application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication springApplication = new SpringApplication(application.class);
		springApplication.run(args);

	}

}
