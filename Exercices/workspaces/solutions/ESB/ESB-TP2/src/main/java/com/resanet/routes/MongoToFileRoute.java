package com.resanet.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.dataformat.BindyType;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MongoToFileRoute  extends RouteBuilder {

	private String repEntree = null;

	private String mongoHost = null;
	private String mongoPort = null;
	private String mongoDataBase = null;
	private String mongoCollection = null;
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		repEntree = "in/file";

//		mongo.host=localhost
//				mongo.port=27017
//				mongo.database=testCamel
//				mongo.collection=personnes
		
		mongoHost = "localhost";
		mongoPort = "27017";
		mongoDataBase = "testCamel2";
		mongoCollection = "personnes";

		RouteDefinition routeCompose = null;

		ObjectMapper objectMapper = new ObjectMapper();
		// objectMapper
		// .disable(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS);
		JacksonDataFormat jackson = new JacksonDataFormat(objectMapper, null);

		routeCompose = from("file://" + repEntree)
				.convertBodyTo(String.class)
				.process(new Processor() {

					@Override
					public void process(Exchange exchange) throws Exception {
						System.err.println(exchange.getIn().getBody());

					}
				})
				.unmarshal()
				.bindy(BindyType.Csv, UserLigth.class)
				.to("mongodb:mongoDb" + "?database=" + mongoDataBase
						+ "&collection=" + mongoCollection
						+ "&operation=insert");
		
	}


}
