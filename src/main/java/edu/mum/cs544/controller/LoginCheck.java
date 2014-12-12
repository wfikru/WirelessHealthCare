/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.controller;

import edu.mum.cs544.boundary.DoctorFacade;
import edu.mum.cs544.boundary.PatientFacade;
import edu.mum.cs544.model.Category;
import edu.mum.cs544.model.Doctor;
import edu.mum.cs544.model.Patient;
import java.io.Serializable;
import java.security.Principal;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author FWorku
 */
@Named(value = "loginCheck")
@SessionScoped
public class LoginCheck implements Serializable {

    /**
     * Creates a new instance of LoginCheck
     */
    public LoginCheck() {
    }

    @EJB
    private DoctorFacade doctorFacade;
    @EJB
    private PatientFacade patientFacade;

    private String username;
    private String password;

    private Doctor doctor = new Doctor();
    private Patient patient = new Patient();

    public String logout() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException e) {

            context.addMessage(null, new FacesMessage("Logout failed."));
        }
        return "home";
    }

    @PersistenceContext(unitName = "com.mycompany_VirtualHEalthCareSystem_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public Doctor getDoctor() {

        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

//        if (principal != null) {
//            Query q = em.createQuery("SELECT d FROM Doctor d WHERE d.email = " + principal.getName());
//            doctor = (Doctor) q.getSingleResult();
//        }
        return doctor;
    }

    public Patient getPatient() {

        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

//        if (principal != null) {
//            Query q = em.createQuery("SELECT p FROM Patient p WHERE p.email = " + principal.getName());
//            patient = (Patient) q.getSingleResult();
//            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("patientKey", patient);                    
//        }
        return patient;
    }

}
