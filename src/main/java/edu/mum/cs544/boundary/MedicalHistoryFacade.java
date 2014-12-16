/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.boundary;

import edu.mum.cs544.model.MedicalHistory;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author FWorku
 */
@Stateless
public class MedicalHistoryFacade extends AbstractFacade<MedicalHistory> {
    @PersistenceContext(unitName = "com.mycompany_VirtualHEalthCareSystem_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicalHistoryFacade() {
        super(MedicalHistory.class);
    }
    
}
