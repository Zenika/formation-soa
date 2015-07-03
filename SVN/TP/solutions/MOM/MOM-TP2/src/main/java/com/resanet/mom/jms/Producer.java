package com.resanet.mom.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

public class Producer {

	public static void main(String[] args) throws Exception {		
		
		ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61617");
		Destination dest = new ActiveMQQueue("ma.queue");

		Connection con = cf.createConnection();
		Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageProducer producer = session.createProducer(dest);
		Message msg = session.createTextMessage("mon message");
		msg.setJMSCorrelationID("myCorrelationID");

		producer.send(msg);

		producer.close();
		session.close();
		con.close();
	}
}
