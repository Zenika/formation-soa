package com.resanet.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

	private final static QName QNAME = new QName("", "libelle");

	@XmlElementDecl(namespace = "", name = "libelle")
	public JAXBElement<String> createLibelle(String value) {
		return new JAXBElement<String>(QNAME, String.class, value);
	}

	public Voyage createVoyage() {
		return new Voyage();
	}

}
