/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.EJBs;

import edu.mum.cs544.backingBeans.*;
import edu.mum.cs544.authentication.SHAHash;
import edu.mum.cs544.authentication.Users;
import edu.mum.cs544.boundary.AddressFacade;
import edu.mum.cs544.boundary.CategoryFacade;
import edu.mum.cs544.boundary.DoctorFacade;
import edu.mum.cs544.boundary.PatientFacade;
import edu.mum.cs544.boundary.usersFacade;
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
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.mail.MessagingException;
import webServices.BundleMessages;
import webServices.MailService;

/**
 *
 * @author FWorku
 */
@Stateless
public class RegistrationEjb implements Serializable {

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
    @EJB
    private usersFacade usersfacade;

    //email service
    private String recipient;
    private String subject = "Welcome to VHC";
    private String statusMessage = "";
    private BundleMessages bundle1 = new BundleMessages();
    private String message = bundle1.getPatientWelcome();

    public RegistrationEjb() {
    }

    public void registerUser(Patient patient, Address address, Users users, SHAHash sha) {
        patient.setAddress(address);
        this.addressFacade.create(address);
        this.patientFacade.create(patient);

        users.setGroupname("PATIENT");
        users.setUsername(patient.getEmail());
        users.setPassword(sha.getEncryptedPassword(patient.getPassword()));
        this.usersfacade.create(users);

        recipient = patient.getEmail();
        try {
            MailService.sendMessage(recipient, subject, message);
        } catch (MessagingException ex) {
            Logger.getLogger(RegistrationEjb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Category findCatagory(String title, Category category) {
        String query = "SELECT c FROM Category c WHERE c.title = :" + category.getTitle();
        Category cat = this.categoryFacade.findSingleByQuery(category, query, category.getTitle(), title);

        return cat;
    }

    public void registerDoctor(Doctor doctor, Address address, Category category, Users users, SHAHash sha) {
        doctor.setAddress(address);
        this.addressFacade.create(address);
        
        doctor.setCategory(findCatagory(doctor.getCategory().getTitle(),category));
        this.doctorFacade.create(doctor);

        users.setGroupname("DOCTOR");
        users.setUsername(doctor.getEmail());
        users.setPassword(sha.getEncryptedPassword(doctor.getPassword()));
        this.usersfacade.create(users);

    }

    public Category addCatagories(Category category) {
        this.categoryFacade.create(category);
        return category;
    }

    public List<Category> loadCatagories() {
        List<Category> categories = this.categoryFacade.findAll();
        return categories;
    }
}
