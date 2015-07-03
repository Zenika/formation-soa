package com.resanet.ws;

import javax.jws.WebService;

@WebService
public interface AccueilService {

	String afficherMessage(String str);

}
