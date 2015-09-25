package com.resanet;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import com.resanet.routes.FileToFileRoute;

public class Driver {

	public static void main(String[] args) throws Exception {

		CamelContext contextCamel = new DefaultCamelContext();
		contextCamel.addRoutes(new FileToFileRoute());
		
		contextCamel.start();
		
		Thread.sleep(2000);
		contextCamel.stop();
	}
}
