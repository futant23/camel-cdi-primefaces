/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.camel.cdi.wildfly;

import com.company.camel.cdi.wildfly.beans.MessageDataBean;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.cdi.ContextName;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author 
 */
//@Model
@ManagedBean
//@ApplicationScoped
@ViewScoped
public class Manager implements Serializable {

    private final static Logger log = Logger.getLogger(Manager.class.getName());

    @Inject
    @ContextName("cdi-context")
    private CamelContext context;
 
    @EJB
    private MessageDataBean dataBean;
    
    private String message;
    private String output;

    private String notify;
    
    private String data;
    
    private EventBus eventBus = EventBusFactory.getDefault().eventBus();

 
//    @PostConstruct
//    private void wire() {
//        try {
//            context.addRoutes(new RouteBuilder() {
//                @Override
//                public void configure() throws Exception {
//                    from("seda:spy").bean(Manager.this, "recv");
////                      from("jms:topic:test").bean(Manager.this, "recv");
//                }
//            }); 
//        } catch (Exception ex) {
//            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

//    public void recv(String msg) {
//        log.info("recv() - " + msg);
//        notify = msg;
//        log.info("publishing over eventbus ...");
//        
//        eventBus.publish("/notify", notify);
//    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public String getData() {
return dataBean.getMessage();
    }

    public void setData(String data) {
        this.data = data;
    }

    public void send() {
        log.info("send()");
        ProducerTemplate producer = context.createProducerTemplate();
        output = producer.requestBody("direct:start", message, String.class);
    }
}
