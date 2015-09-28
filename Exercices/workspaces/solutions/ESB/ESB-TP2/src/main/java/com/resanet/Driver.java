package com.resanet;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

import com.mongodb.MongoClient;
import com.resanet.routes.MongoToFileRoute;

public class Driver {

	public static void main(String[] args) throws Exception {
		
//		startRouteActiveMQ();
		startRouteMongo();
	}
	
	private static void startRouteActiveMQ() throws Exception{
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
	
	
	private static void startRouteMongo() throws Exception{
		DefaultCamelContext contextCamel = new DefaultCamelContext();
		
		
		SimpleRegistry registry = new SimpleRegistry();
		MongoClient client = new MongoClient("localhost", 27017);
		registry.put("mongoDb", client);
		contextCamel.setRegistry(registry);
		
		
		contextCamel.addRoutes(new MongoToFileRoute());
		contextCamel.start();
		
		Thread.sleep(5000);
		contextCamel.stop();

	}
}
