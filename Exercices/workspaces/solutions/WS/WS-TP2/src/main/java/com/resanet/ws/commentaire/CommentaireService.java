package com.resanet.ws.commentaire;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.2
 * 2015-09-24T14:58:07.473+02:00
 * Generated source version: 3.1.2
 * 
 */
@WebServiceClient(name = "commentaireService", 
                  wsdlLocation = "file:wsdl/commentaire.wsdl",
                  targetNamespace = "http://www.resanet.partenaires.com/commentaire") 
public class CommentaireService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.resanet.partenaires.com/commentaire", "commentaireService");
    public final static QName CommentaireServicePort = new QName("http://www.resanet.partenaires.com/commentaire", "commentaireServicePort");
    static {
        URL url = null;
        try {
            url = new URL("file:wsdl/commentaire.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(CommentaireService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:wsdl/commentaire.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public CommentaireService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public CommentaireService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CommentaireService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public CommentaireService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public CommentaireService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public CommentaireService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns CommentairePortType
     */
    @WebEndpoint(name = "commentaireServicePort")
    public CommentairePortType getCommentaireServicePort() {
        return super.getPort(CommentaireServicePort, CommentairePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CommentairePortType
     */
    @WebEndpoint(name = "commentaireServicePort")
    public CommentairePortType getCommentaireServicePort(WebServiceFeature... features) {
        return super.getPort(CommentaireServicePort, CommentairePortType.class, features);
    }

}