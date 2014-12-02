/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.controller;

import edu.mum.cs544.boundary.PatientFacade;
import edu.mum.cs544.boundary.SymptomFacade;
import edu.mum.cs544.model.Patient;
import edu.mum.cs544.model.Symptom;
import java.io.Serializable;
import java.util.Date;
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
    private PatientFacade patientFacade;

    @EJB
    private SymptomFacade symptomFacade;

    public VirtualPatient() {
    }
    private Symptom symptom = new Symptom();
    private Patient patient;  // get the registered patient object
    private String userName = "zeriet"; // this value should be retrieved when the patient is loggedin 

    @PersistenceContext(unitName = "com.mycompany_VirtualHEalthCareSystem_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public Symptom getSymptom() {
        return symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }


    public String submitSysmptom() {
        
        Query query = em.createQuery("SELECT p FROM Patient p WHERE p.firstName = :userName");// change firstName to username later
        query.setParameter("userName", userName);
        Patient patient = (Patient) query.getSingleResult(); 
        symptom.setDate(new Date());       
        patient.setSymptoms(symptom);  //symptoms should be changed to symptom
        this.patientFacade.edit(patient);
        return "symptomSubmitSuccess";

    }
    
}
