/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.camel.cdi.wildfly.beans;

import java.math.BigDecimal;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author 
 */
@ServerEndpoint("/example")
public class WebSocketEndpoint {

    private final static Logger log = Logger.getLogger(WebSocketEndpoint.class.getName());

    @Inject
    SessionRegistryBean sessionRegBean;

    @OnMessage
    public void recv(String message, Session session) {
        log.info("Received : " + message + ", session:" + session.getId());
        
        // capitilize what is given and return it.
        for(Session sess : sessionRegBean.getAll()) {
            sess.getAsyncRemote().sendText("Web Server recv'd your message: "+System.currentTimeMillis());
        }
        
        
//        for(Session sess : sessionRegBean.getAll()) {
//            JsonObject jsonObj =Json.createObjectBuilder()
//                    .add("name", "shizzle")
//                    .add("addy", "some address")
//                    .build();
//            
//            sess.getAsyncRemote().sendText(jsonObj.toString());
//        }
    }
    
      
    public void send() {
        for(Session sess : sessionRegBean.getAll()) {
            JsonObject jsonObj =Json.createObjectBuilder()
                    .add("name", "shizzle")
                    .add("address", "some address")
                    .add("time", System.currentTimeMillis())
                    .build();
            
            sess.getAsyncRemote().sendText(jsonObj.toString());
        }
    }
 
    @OnOpen
    public void open(Session session) {
        log.info("Open session:" + session.getId());
        
        sessionRegBean.add(session);
        
        send();
    }

    @OnClose
    public void close(Session session, CloseReason c) {
        log.info("Closing:" + session.getId());
        log.info("close reason: " + c.getReasonPhrase());
        
        sessionRegBean.remove(session);
        send();
    }

}
