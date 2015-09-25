package com.resanet.routes;

import org.apache.camel.model.ProcessorDefinition;

import com.resanet.CustomRouteBuilder;

public class FileToFileRoute extends CustomRouteBuilder {

	@Override
	protected ProcessorDefinition composeRoute() throws Exception {
		ProcessorDefinition composeRoute  = null;
		composeRoute = from("file://in?delay=10000").to("file://out");
		return composeRoute;
	}
}
