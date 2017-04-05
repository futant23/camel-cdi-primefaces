/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.camel.cdi.wildfly;

import com.company.camel.cdi.wildfly.beans.SessionRegistryBean;
import java.io.Serializable;
import java.util.Set;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.websocket.Session;

/**
 *
 * @author 
 */
@ManagedBean
@ViewScoped

public class SessionManager implements Serializable{
    
    
    
private String name ="Brian";

    @EJB
    private SessionRegistryBean registryBean;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Session> getAll() {
        return registryBean.getAll();
    }
    
   
}
