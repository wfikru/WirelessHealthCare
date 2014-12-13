/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.controller;

import edu.mum.cs544.authentication.Users;
import edu.mum.cs544.boundary.DoctorFacade;
import edu.mum.cs544.boundary.PatientFacade;
import edu.mum.cs544.boundary.usersFacade;
import edu.mum.cs544.model.Doctor;
import edu.mum.cs544.model.Patient;
import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    @EJB
    private usersFacade userfacade;

    private String username;
    private String password;

    private Doctor doctor = new Doctor();
    private Patient patient = new Patient();
    private Users users = new Users();

    public void logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Virtual-HealthCareSystem");
        } catch (IOException ex) {
            Logger.getLogger(LoginCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PersistenceContext(unitName = "com.mycompany_VirtualHEalthCareSystem_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public Doctor getDoctor() {

        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        doctor = this.doctorFacade.find(1);
//        if (principal != null) {
//            Query q = em.createQuery("SELECT d FROM Doctor d WHERE d.email = " + principal.getName());
//            doctor = (Doctor) q.getSingleResult();
//        }
        return doctor;
    }

    public Patient getPatient() {

        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        patient = this.patientFacade.find(principal.getName());
//        if (principal != null) {
//            Query q = em.createQuery("SELECT p FROM Patient p WHERE p.email = " + principal.getName());
//            patient = (Patient) q.getSingleResult();
//            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("patientKey", patient);                    
//        }
        return patient;
    }

    public boolean isLogedIn() {
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        if (principal == null) {
            return false;
        } else {
            return true;
        }
    }
}
