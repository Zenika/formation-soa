
package com.resanet.ws.commentaire;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.1
 * 2013-11-06T00:52:54.518+01:00
 * Generated source version: 2.6.1
 */

@WebFault(name = "commentaireFault", targetNamespace = "http://www.resanet.partenaires.com/commentaire")
public class ListerFault extends Exception {
    
    private com.resanet.ws.commentaire.CommentaireFault commentaireFault;

    public ListerFault() {
        super();
    }
    
    public ListerFault(String message) {
        super(message);
    }
    
    public ListerFault(String message, Throwable cause) {
        super(message, cause);
    }

    public ListerFault(String message, com.resanet.ws.commentaire.CommentaireFault commentaireFault) {
        super(message);
        this.commentaireFault = commentaireFault;
    }

    public ListerFault(String message, com.resanet.ws.commentaire.CommentaireFault commentaireFault, Throwable cause) {
        super(message, cause);
        this.commentaireFault = commentaireFault;
    }

    public com.resanet.ws.commentaire.CommentaireFault getFaultInfo() {
        return this.commentaireFault;
    }
}