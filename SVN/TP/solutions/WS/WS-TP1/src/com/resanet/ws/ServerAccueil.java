package com.resanet.ws;

import javax.xml.ws.Endpoint;

public class ServerAccueil {

	public static void main(String[] args) {
		System.out.println("Serveur démarré...");
		AccueilService service = new AccueilServiceImpl();
		String address = "http://localhost:9000/accueil";
		Endpoint.publish(address, service);
	}

}
