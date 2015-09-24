package com.resanet.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class RouteTX extends RouteBuilder {
	@Override
	public void configure() throws Exception {

		from("jmsTX:inQueueTX")
			.transacted()
			.process(new Processor() {
				
				@Override
				public void process(Exchange exchange) throws Exception {
	
					System.out.println("Je vais dormir...");
					Thread.sleep(10000);
					System.out.println("Je me réveille !");
					
				}
			})
			
		.to("jmsTX:outQueueTX");

	}
}
