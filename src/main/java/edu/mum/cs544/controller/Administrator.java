/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.controller;

import edu.mum.cs544.boundary.DoctorFacade;
import edu.mum.cs544.model.Doctor;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import java.util.List;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author FWorku
 */
@Named(value = "administrator")
@SessionScoped
public class Administrator implements Serializable {

    /**
     * Creates a new instance of Administrator
     */
    public Administrator() {
    }
    @EJB
    private DoctorFacade doctorFacade;

    private Doctor doctor;

    private List<Doctor> doc;

    public Doctor getDoctor() {
        return doctor;
    }

    public List<Doctor> getDoc() {
        return doc;
    }

    public String editDoctorList() {
        this.doc = this.doctorFacade.findAll();

        return "manageDoctorsList";
    }

    public void saveChanges() {        
        
        for (Doctor doctor : doc) {
            doctor.setEditable(false);
            this.doctorFacade.edit(doctor);            
        }
    }
    //how to handle the patients associated with it when deleted
    public void removeDoctor(Doctor doctor)
    {
        this.doctorFacade.remove(doctor);
    }

    public void editRecord(Doctor doctor) {

        doctor.setEditable(true);
       
    }

}
