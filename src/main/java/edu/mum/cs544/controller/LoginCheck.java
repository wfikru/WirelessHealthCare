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
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author FWorku
 */
@Named(value = "loginCheck")
@Dependent
public class LoginCheck {

    /**
     * Creates a new instance of LoginCheck
     */
    public LoginCheck() {
    }

    private String username;
    private String password;

    @EJB
    private DoctorFacade doctorFacade;
    @EJB
    private PatientFacade patientFacade;

    private Doctor doctor;
    private Patient patient;

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

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public String checkLogin() {

        if (getUsername() == "" && getPassword() == "") {
            return "AdminPortal";
        } else {
            List<Doctor> docList = this.doctorFacade.findAll();
            for (Doctor doc : docList) {
                if (getUsername() == doc.getUsername() && getPassword() == doc.getPassword()) {
                    return "DoctorPortal";
                }
            }
            List<Patient> patList = this.patientFacade.findAll();
            for (Patient pat : patList) {
                if (getUsername() == pat.getUsername() && getPassword() == pat.getPassword()) {
                    return "PatientPortal";
                }
            }
        }

        return "LoginFailure";
    }
}
