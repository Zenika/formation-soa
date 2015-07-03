package com.resanet.routes;

import org.apache.camel.builder.RouteBuilder;

public class FileToFileRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("file:///Users/raphael/camel/in2?delay=10000").to("file:///Users/raphael/camel/out2");
	}
}
