/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.backingBeans;

import edu.mum.cs544.EJBs.LoginCheckEjb;
import edu.mum.cs544.authentication.Users;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author FWorku
 */
@Named
@SessionScoped
public class LoginCheck implements Serializable {

    /**
     * Creates a new instance of LoginCheck
     */
    public LoginCheck() {
    }

    private Doctor doctor = new Doctor();
    private Patient Patient = new Patient();
    @EJB
    private LoginCheckEjb loginCheckEjb;
    private Users users = new Users();

//    public String checkLogin() {
//        users = this.loginCheckEjb.checkLogin();
//
//        if (users.getGroupname().equals("ADMIN")) {
//            return "AdminPortal";
//        } else if (users.getGroupname().equals("DOCTOR")) {
//            return "DoctorPortal";
//        } else {
//            return "PatientPortal";
//        }
//    }

    public void logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Virtual-HealthCareSystem");
        } catch (IOException ex) {
            Logger.getLogger(LoginCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Doctor getDoctor() {

        doctor = this.loginCheckEjb.getDoctor();
        return doctor;
    }

    public Patient getPatient() {

//        Patient= this.loginCheckEjb.getPatient();
        return Patient;
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
