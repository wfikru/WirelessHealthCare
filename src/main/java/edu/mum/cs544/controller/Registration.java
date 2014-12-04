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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.mail.MessagingException;
import webServices.BundleMessages;
import webServices.MailService;

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
    
    // email service
    private String recipient;
    private String subject="Welcome to VHC";
   
    private String statusMessage = "";
 
    //

    private Patient patient = new Patient();
    private Address address = new Address();
    private Doctor doctor = new Doctor();
    private Category category = new Category();
    private BundleMessages bundle1=new BundleMessages();
     private String message=bundle1.getPatientWelcome();

    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public Address getAddress() {
        return address;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        this.doctor.setCategory(category);
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
        
        recipient=this.patient.getEmail();
        try {
            MailService.sendMessage(recipient, subject, message);
        } catch (MessagingException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "home";
    }

    public Category findCatagory(String title) {
        String query = "SELECT c FROM Category c WHERE c.title = :" + category.getTitle();
        Category cat = this.categoryFacade.findSingleByQuery(category, query, category.getTitle(), title);

        return cat;
    }


    public String registerDoctor() {
        this.doctor.setAddress(address);
        this.addressFacade.create(address);
        this.category = findCatagory(this.doctor.getCategory().getTitle());
        this.doctor.setCategory(category);

        this.doctorFacade.create(doctor);
        return "home";
    }

    public String addCatagories() {
        this.categoryFacade.create(category);
        return "AdminPortal";
    }

    public String loadCatagories() {
        this.categories = this.categoryFacade.findAll();
        return "DoctorRegistration";
    }
}
