package com.resanet.routes;

import org.apache.camel.builder.RouteBuilder;

public class ESBTP3A extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("jms://queue.esbtp3a").to("log:ESBTP3A?showAll=true&multiline=true").to("file:///Users/raphael/camel/esbtp3a");
	}
}
