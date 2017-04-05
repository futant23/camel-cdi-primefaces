/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.camel.cdi.wildfly.beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author 
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "topic/test"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class MessageListenerBean implements MessageListener {
    
    private final static Logger log =Logger.getLogger(MessageListenerBean.class.getName());
    
    @EJB
    private MessageDataBean bean;
    
    public MessageListenerBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            String msg =message.getBody(String.class);
            bean.setMessage(msg);
        } catch (JMSException ex) {
            Logger.getLogger(MessageListenerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
