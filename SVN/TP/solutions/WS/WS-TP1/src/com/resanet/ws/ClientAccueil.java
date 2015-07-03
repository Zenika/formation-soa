package com.resanet.ws;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class ClientAccueil {

	public static void main(String[] args) throws Exception {
		ClientAccueil client = new ClientAccueil();
		client.callService();
	}

	public void callService() throws Exception {
		URL wsdlURL = new URL("http://localhost:9000/accueil?wsdl");
		QName serviceName = new QName("http://ws.resanet.com/", "AccueilServiceImplService");

		Service service = Service.create(wsdlURL, serviceName);
		AccueilService client = service.getPort(AccueilService.class);

		System.out.println(client.afficherMessage("Air France"));
	}

}
