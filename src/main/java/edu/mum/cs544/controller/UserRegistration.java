/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.controller;

import edu.mum.cs544.boundary.PatientFacade;
import edu.mum.cs544.model.Patient;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author FWorku
 */
@Named
@SessionScoped
public class UserRegistration implements Serializable {

    /**
     * Creates a new instance of UserRegistration
     */
    @EJB
    private PatientFacade patientFacade;
    public UserRegistration() {
    }
    
    private Patient patient = new Patient();

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    
    
    public void registerUser()
    {
        this.patientFacade.create(patient);        
    }
}
