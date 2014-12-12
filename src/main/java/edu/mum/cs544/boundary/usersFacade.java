/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.cs544.boundary;

import edu.mum.cs544.boundary.AbstractFacade;
import edu.mum.cs544.authentication.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Fikru
 */
@Stateless
public class usersFacade extends AbstractFacade<Users> {
    @PersistenceContext(unitName = "com.mycompany_VirtualHEalthCareSystem_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public usersFacade() {
        super(Users.class);
    }
    
}
