/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.EJBs;

import edu.mum.cs544.boundary.DoctorFacade;
import edu.mum.cs544.boundary.PatientFacade;
import edu.mum.cs544.boundary.usersFacade;
import edu.mum.cs544.model.Doctor;
import edu.mum.cs544.model.Patient;
import java.io.Serializable;
import java.security.Principal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author FWorku
 */
@Stateless
public class LoginCheckEjb implements Serializable {

    /**
     * Creates a new instance of LoginCheck
     */
    public LoginCheckEjb() {
    }

    @EJB
    private DoctorFacade doctorFacade;
    @EJB
    private PatientFacade patientFacade;
    @EJB
    private usersFacade userfacade;

    @PersistenceContext(unitName = "com.mycompany_VirtualHEalthCareSystem_war_1.0-SNAPSHOTPU")
    private EntityManager em;

//    public Users checkLogin() {
//
//        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
//        String username = principal.getName();
//        Users users = new Users();
//
//        String query = "SELECT u FROM Users u WHERE u.username = :" + users.getUsername();
//        users = this.userfacade.findSingleByQuery(users, query, users.getUsername(), username);
//        
//        return users;
//    }
    public Doctor getDoctor() {

        Doctor doctor = new Doctor();
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        String email = principal.getName();

//        if (principal != null) {
            String query = "SELECT d FROM Doctor d WHERE d.email = \"" + email + "\"";
            doctor = this.doctorFacade.findByName(query);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("doctorKey", doctor);
//        }
        return doctor;
    }

    public Patient getPatient() {
        Patient patient = new Patient();
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        String email = principal.getName();

//        if (principal != null) {
            String query = "SELECT p FROM Patient p WHERE p.email = \"" + email + "\"";
            patient = this.patientFacade.findByName(query);
            
//        }
        return patient;
    }
}
