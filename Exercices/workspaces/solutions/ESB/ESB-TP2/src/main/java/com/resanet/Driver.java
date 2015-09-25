package com.resanet;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class Driver {

	public static void main(String[] args) throws Exception {
		
//		ActiveMQComponent component = new ActiveMQComponent();
//
//		component.setBrokerURL("tcp://localhost:61616");
//		component.setUserName("admin");
//		component.setPassword("admin");

		CamelContext contextCamel = new DefaultCamelContext();
//		contextCamel.addComponent("activemq", component);
		
		
		ConnectionFactory connectionFactory = new
				ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");
		
		
		contextCamel.addComponent("jms",
				JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		
		contextCamel.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				from("jms:ma.queue").to("file://out");
				
			}
		});
		
		contextCamel.start();
		
		Thread.sleep(5000);
		contextCamel.stop();
	}
}
