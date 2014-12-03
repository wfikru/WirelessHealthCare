/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.controller;

import edu.mum.cs544.boundary.AddressFacade;
import edu.mum.cs544.boundary.CategoryFacade;
import edu.mum.cs544.boundary.DoctorFacade;
import edu.mum.cs544.boundary.PatientFacade;
import edu.mum.cs544.model.Address;
import edu.mum.cs544.model.Category;
import edu.mum.cs544.model.Doctor;
import edu.mum.cs544.model.Patient;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
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
    @EJB
    private DoctorFacade doctorFacade;
    @EJB
    private CategoryFacade categoryFacade;

    public Registration() {
    }

//    public String generateID()
//    {        
//    }
    private Patient patient = new Patient();
    private Address address = new Address();
    private Doctor doctor = new Doctor();
    private Category category = new Category();

    public Address getAddress() {
        return address;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String registerUser() {
        this.patient.setAddress(address);
        this.addressFacade.create(address);
        this.patientFacade.create(patient);
        return "home";
    }

    public String registerDoctor() {
        this.doctor.setAddress(address);
        this.doctor.setCategory(category);

        this.addressFacade.create(address);
        this.doctorFacade.create(doctor);
        return "home";
    }

    public String addCatagories() {
        this.categoryFacade.create(category);
        return "AdminPortal";
    }

    public List<Category> loadCatagories() {
        List<Category> category = this.categoryFacade.findAll();
        return category;
    }
}
