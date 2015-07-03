package com.resanet.routes;

import org.apache.camel.builder.RouteBuilder;

public class FileToFileRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("jms:ma.queue").to("file:///Users/raphael/camel/out2");
	}
}
