package com.resanet.routes;

import org.apache.camel.builder.RouteBuilder;

public class ESBTP3B extends RouteBuilder {

	private static final String LOG = ESBTP3B.class + "?showAll=true&multiline=true";
	
	@Override
	public void configure() throws Exception {

		from("jms://queue_cbr")
		.convertBodyTo(String.class)
		.to("log:" + LOG)
		.choice()
			.when(header("JMSType").isEqualTo("gadget"))
				.to("log:" + ESBTP3B.class + ".GADGET?showAll=true&multiline=true")
				.to("jms://queue_gadget")

			.when(header("JMSType").isEqualTo("widget"))
				.to("log:" + ESBTP3B.class + ".WIDGET?showAll=true&multiline=true")
				.to("jms://queue_widget")

			.otherwise()
				.to("log:" + ESBTP3B.class + ".AUTRE?showAll=true&multiline=true")
		.end();
		
	}
}
