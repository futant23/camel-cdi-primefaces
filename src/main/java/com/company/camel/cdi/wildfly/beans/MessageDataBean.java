/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.camel.cdi.wildfly.beans;

import java.util.logging.Logger;
import javax.ejb.Singleton;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author 
 */
@Singleton
public class MessageDataBean {
private static final Logger log =Logger.getLogger(MessageDataBean.class.getName());
    

    private EventBus eventBus = EventBusFactory.getDefault().eventBus();


    private String message ="initial";
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
     public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        log.info("setting new data: "+message);
        this.message = message;
        
        eventBus.publish("/message", message);
    }
    
    
}
