/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.controller;

import edu.mum.cs544.boundary.SymptomFacade;
import edu.mum.cs544.model.Patient;
import edu.mum.cs544.model.Symptom;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author FWorku
 */
@Named(value = "virtualPatient")
@SessionScoped
public class VirtualPatient implements Serializable {

    @EJB
    private SymptomFacade symptomFacade;

    public VirtualPatient() {
    }

    @PersistenceContext(unitName = "com.mycompany_VirtualHEalthCareSystem_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    private Symptom symptom = new Symptom();
    private Patient patient;  // get the registered patient object
    private String userName = "zeriet"; // this value should be retrieved when the patient is loggedin 

    public Symptom getSymptom() {
        return symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }

    public Patient findPatient(EntityManager em, String userName) {
        Query query = em.createQuery("SELECT p FROM Patient p WHERE p.userName = :userName");
        query.setParameter("userName", userName);
        return (Patient) query.getSingleResult();
    }

    public String submitSysmptom() {
        Patient patientTest = findPatient(em, userName);
        patientTest.setSymptoms(symptom);  //symptoms should be changed to symptom
        em.persist(patientTest);
        return "symptomSubmitSuccess";

    }

}
