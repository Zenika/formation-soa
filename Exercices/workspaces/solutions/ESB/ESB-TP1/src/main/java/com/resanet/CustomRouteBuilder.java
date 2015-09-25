/**
 * 
 */
package com.resanet;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.ProcessorDefinition;

/**
 * @author vickrame
 *
 */
public abstract class CustomRouteBuilder extends RouteBuilder {

	protected volatile boolean jobFinish = false;
	private int countError = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.camel.builder.RouteBuilder#configure()
	 */
	@Override
	public void configure() throws Exception {
		try {
			terminateRoute();
		} catch (Exception e) {
			e.printStackTrace();
			countError++;
			throw e;
		}

	}

	/**
	 * @return the jobFinish
	 */
	public boolean isJobFinish() {
		return jobFinish;
	}

	/**
	 * @param jobFinish
	 *            the jobFinish to set
	 */
	public void setJobFinish(boolean jobFinish) {
		this.jobFinish = jobFinish;
	}


	protected abstract ProcessorDefinition composeRoute() throws Exception;

	private void terminateRoute() throws Exception {
		ProcessorDefinition defineRoute = composeRoute();

		defineRoute.end();

		System.out.println("fin construction de la route");
	}




	/**
	 * @return the countError
	 */
	public int getCountError() {
		return countError;
	}

}
