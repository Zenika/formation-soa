package com.resanet.mom.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AsyncConsumer implements MessageListener {

	@Override
	public void onMessage(Message msg) {
		try {
			System.out.println("Message reçu : " + ((TextMessage) msg).getText());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("classpath:/spring/spring-context.xml");
	}

}
