
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.resanet.ws.commentaire;

import java.util.logging.Logger;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.6.1
 * 2013-11-06T00:52:54.546+01:00
 * Generated source version: 2.6.1
 * 
 */

@javax.jws.WebService(
                      serviceName = "commentaireService",
                      portName = "commentaireServicePort",
                      targetNamespace = "http://www.resanet.partenaires.com/commentaire",
                      wsdlLocation = "wsdl/commentaire.wsdl",
                      endpointInterface = "com.resanet.ws.commentaire.CommentairePortType")
                      
public class CommentairePortTypeImpl implements CommentairePortType {

    private static final Logger LOG = Logger.getLogger(CommentairePortTypeImpl.class.getName());

    /* (non-Javadoc)
     * @see com.resanet.ws.commentaire.CommentairePortType#lister(java.lang.String  hotel )*
     */
    public java.util.List<java.lang.String> lister(java.lang.String hotel) throws ListerFault    { 
        LOG.info("Executing operation lister");
        System.out.println(hotel);
        try {
            java.util.List<java.lang.String> _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new ListerFault("listerFault...");
    }

    /* (non-Javadoc)
     * @see com.resanet.ws.commentaire.CommentairePortType#ajouter(java.lang.String  hotel ,)java.lang.String  commentaire )*
     */
    public void ajouter(java.lang.String hotel,java.lang.String commentaire) { 
        LOG.info("Executing operation ajouter");
        System.out.println(hotel);
        System.out.println(commentaire);
        try {
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}