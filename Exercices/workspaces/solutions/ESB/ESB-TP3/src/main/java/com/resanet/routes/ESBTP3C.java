package com.resanet.routes;

import org.apache.camel.builder.RouteBuilder;

public class ESBTP3C extends RouteBuilder {

	private static final String LOG = ESBTP3C.class + "?showAll=true&multiline=true";

	@Override
	public void configure() throws Exception {

		// routage statique sequentiel
		from("direct:mutlicast")
		.convertBodyTo(String.class)
		.multicast().parallelProcessing()
		.to("log:A- " + LOG, "log:B- " + LOG,	"log:C- " + LOG)
		.to("direct:consumer");

	}
}
