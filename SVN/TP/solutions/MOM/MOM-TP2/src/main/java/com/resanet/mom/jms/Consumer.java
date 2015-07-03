package com.resanet.mom.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

public class Consumer {

	public static void main(String[] args) throws Exception {

		ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61617");
		Destination dest = new ActiveMQQueue("ma.queue");

		Connection con = cf.createConnection();
		con.start();

		Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageConsumer consumer = session.createConsumer(dest);

		Message msg = consumer.receive(100);
		if (msg != null) {
			String s = ((TextMessage) msg).getText();
			System.out.println("Message reçu : " + s);
			System.out.println("JMSCorrelationID : " + msg.getJMSCorrelationID());
		}

		consumer.close();
		session.close();
		con.close();
	}
}
