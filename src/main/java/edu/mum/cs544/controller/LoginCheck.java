/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.controller;

import edu.mum.cs544.boundary.DoctorFacade;
import edu.mum.cs544.boundary.PatientFacade;
import edu.mum.cs544.model.Doctor;
import edu.mum.cs544.model.Patient;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

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
    private Doctor doctor;
    private Patient patient=new Patient();

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String checkLogin()
//    {
//        Patient p = this.patientFacade.find("102");
//        this.patientFacade.remove(p);
//        return "LoginFailure";
//    }
    public String checkLogin() {

        if ("".equals(this.username) && "".equals(this.password)) {
            return "AdminPortal";
        } else {
            List<Doctor> docList = this.doctorFacade.findAll();
            for (Doctor doc : docList) {
                if (this.username.equals( doc.getEmail()) && this.password.equals(doc.getPassword())) {
                    this.doctor = doc;
                    return "DoctorPortal";
                }
            }

            List<Patient> patList = this.patientFacade.findAll();
            for (Patient pat : patList) {
                if (this.username.equals(pat.getEmail()) && this.password.equals(pat.getPassword())) {
                    this.patient=pat;
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("patientKey", patient);                    
                    return "PatientPortal";
                }
            }
        }
        return "LoginFailure";
    }
}
