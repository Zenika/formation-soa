package com.resanet.ws;

import javax.jws.WebService;

@WebService(endpointInterface = "com.resanet.ws.AccueilService")
public class AccueilServiceImpl implements AccueilService {

	@Override
	public String afficherMessage(String str) {
		return "RESANET : Bienvenue " + str;
	}
}
