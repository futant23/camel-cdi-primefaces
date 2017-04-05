/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.camel.cdi.wildfly;

import java.util.logging.Logger;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;

/**
 *
 * @author 
 */
@Startup
@ApplicationScoped
@ContextName("cdi-context")
public class BasicRoute extends RouteBuilder {

    private final static Logger log =Logger.getLogger(BasicRoute.class.getName());
    
    public BasicRoute() {
    }

    
    @Override
    public void configure() throws Exception {
        
        log.info("configure()");
        
        // wiretap the request and send it to direct:spy
        from("direct:start").wireTap("seda:spy").transform(body().prepend("HI: ")).log(body().toString());
 
    }
}
