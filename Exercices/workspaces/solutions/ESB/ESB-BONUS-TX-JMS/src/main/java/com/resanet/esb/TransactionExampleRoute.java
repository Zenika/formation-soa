package com.resanet.esb;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;

public class TransactionExampleRoute extends SpringRouteBuilder {

	@Override
	public void configure() {

        from("jmstx:in")
		  .transacted() //.policy("required")
		 
		  .to("log:a?showAll=true&multiline=true")
		  
          .process(new Processor() {

            public void process(Exchange exchange) throws Exception {
                
                // emulate long processing
                Thread.sleep(10000);
                
            }

          })
        .to("jmstx:out");

	}
}
