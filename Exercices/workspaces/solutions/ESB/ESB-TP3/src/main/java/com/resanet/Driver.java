package com.resanet;

import org.apache.camel.spring.Main;

public class Driver {

	public static void main(String[] args) throws Exception {
		Main m = new Main();
		m.enableHangupSupport();
		m.run();
	}

}
