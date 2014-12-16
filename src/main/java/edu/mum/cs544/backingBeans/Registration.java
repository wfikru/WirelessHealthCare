/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.backingBeans;

import edu.mum.cs544.EJBs.RegistrationEjb;
import edu.mum.cs544.authentication.SHAHash;
import edu.mum.cs544.authentication.Users;
import edu.mum.cs544.model.Address;
import edu.mum.cs544.model.Category;
import edu.mum.cs544.model.Doctor;
import edu.mum.cs544.model.Patient;
import java.io.Serializable;
import java.util.ArrayList;
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
    private RegistrationEjb registrationEjb;
    
    private SHAHash sha = new SHAHash();
    
    public Registration() {
    }
    
    private Users users = new Users();
    private Patient patient = new Patient();
    private Address address = new Address();
    private Doctor doctor = new Doctor();
    private Category category = new Category();
    
    private List<Category> categories = new ArrayList<Category>();
    
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
        
        this.registrationEjb.registerUser(patient, address, users, sha);
        return "RegistrationConfirmation";
    }   
  
    
    public String registerDoctor() {
        this.registrationEjb.registerDoctor(doctor, address, category, users, sha);
        return "AdminPortal";
    }
    
    public String addCatagories() {
        this.registrationEjb.addCatagories(category);
        return "AdminPortal";
    }
    
    public String loadCatagories() {
        categories = this.registrationEjb.loadCatagories();
        return "DoctorRegistration";
    }
}
