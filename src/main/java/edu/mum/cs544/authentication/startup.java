/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.authentication;

import edu.mum.cs544.boundary.usersFacade;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Fikru
 */
@Singleton
@Startup
public class startup {

    @Inject
    private usersFacade usersfacade;

    @PostConstruct
    public void creatGroups() {    
       SHAHash sha = new SHAHash();
        Users _users = new Users();
        _users.setUsername("admin");

        _users.setPassword(sha.getEncryptedPassword("admin"));
        
        _users.setGroupname("ADMIN");
        
        this.usersfacade.create(_users);
    }
}
