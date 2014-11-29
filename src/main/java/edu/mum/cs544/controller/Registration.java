/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.controller;

import edu.mum.cs544.boundary.AddressFacade;
import edu.mum.cs544.boundary.PatientFacade;
import edu.mum.cs544.model.Address;
import edu.mum.cs544.model.Patient;
import java.io.Serializable;
import javax.annotation.ManagedBean;
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
public class Registration implements Serializable {

    /**
     * Creates a new instance of UserRegistration
     */
    @EJB
    private PatientFacade patientFacade;
    
    @EJB
    private AddressFacade addressFacade;
    
    public Registration() {
    }
    
    private Patient patient = new Patient();
    private Address address = new Address();

    public Address getAddress() {
        return address;
    }
    
    
    public Patient getPatient() {
        return patient;
    }    
    
    public String registerUser()
    {
        this.patient.setAddress(address);
        this.addressFacade.create(address);
        this.patientFacade.create(patient);
        
        return "index";
    }
    
//    public String registerDoctor()
//    {
//    }
}
