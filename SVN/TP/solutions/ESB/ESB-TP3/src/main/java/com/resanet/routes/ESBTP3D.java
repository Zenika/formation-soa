package com.resanet.routes;

import org.apache.camel.builder.RouteBuilder;

public class ESBTP3D extends RouteBuilder {

	private static final String LOG = "log:" + ESBTP3D.class + "?showAll=true&multiline=true";

	@Override
	public void configure() throws Exception {

		// transform message translator
		from("direct:messageTranslator").convertBodyTo(String.class).transform(body().append("World !")).to(LOG);

	}
}
