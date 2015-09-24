package com.resanet.ws.commentaire;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.2
 * 2015-09-24T14:58:07.461+02:00
 * Generated source version: 3.1.2
 * 
 */
//@WebService(targetNamespace = "http://www.resanet.partenaires.com/commentaire", name = "commentairePortType")
@WebService
public interface CommentairePortType {

//    @WebResult(name = "commentaire", targetNamespace = "")
//    @RequestWrapper(localName = "lister", targetNamespace = "http://www.resanet.partenaires.com/commentaire", className = "com.resanet.ws.commentaire.Lister")
    @WebMethod
//    @ResponseWrapper(localName = "listerResponse", targetNamespace = "http://www.resanet.partenaires.com/commentaire", className = "com.resanet.ws.commentaire.ListerResponse")
    public java.util.List<java.lang.String> lister(
        @WebParam(name = "hotel", targetNamespace = "http://www.resanet.partenaires.com/commentaire")
        java.lang.String hotel
    ) throws ListerFault;

//    @Oneway
//    @RequestWrapper(localName = "ajouter", targetNamespace = "http://www.resanet.partenaires.com/commentaire", className = "com.resanet.ws.commentaire.Ajouter")
    @WebMethod
    public void ajouter(
        @WebParam(name = "hotel", targetNamespace = "http://www.resanet.partenaires.com/commentaire")
        java.lang.String hotel,
        @WebParam(name = "commentaire", targetNamespace = "http://www.resanet.partenaires.com/commentaire")
        java.lang.String commentaire
    );
}
