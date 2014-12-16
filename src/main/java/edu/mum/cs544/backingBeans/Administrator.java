/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.backingBeans;

import edu.mum.cs544.EJBs.AdministratorEjb;
import edu.mum.cs544.model.Category;
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
@Named
@SessionScoped
public class Administrator implements Serializable {

    /**
     * Creates a new instance of Administrator
     */
    public Administrator() {
    }
    
    
   @EJB
    private AdministratorEjb administratorEjb;
   
    private Doctor doctor = new Doctor();
    private Category category = new Category();

    private List<Doctor> doc;
    private List<Category> cat;

    public Doctor getDoctor() {
        return doctor;
    }

    public Category getCategory() {
        return category;
    }

    public List<Doctor> getDoc() {
        return doc;
    }

    public List<Category> getCat() {
        return cat;
    }

    public String editDoctorList() {
        this.doc = this.administratorEjb.editDoctorList();

        return "manageDoctorsList";
    }

    public String editCategoryList() {
        this.cat = this.administratorEjb.editCategoryList();
        return "manageCategoryList";
    }

    public void saveDocChanges() {

        this.administratorEjb.saveDocChanges(doc);
    }

    public void saveCatChanges() {

        this.administratorEjb.saveCatChanges(cat);
    }

    //how to handle the patients associated with it when deleted
    public void removeDoctor(Doctor doctor) {
        this.administratorEjb.removeDoctor(doctor);
    }

    public void editDocRecord() {
        doctor.setEditable(true);
    }

    public void editCatRecord() {
        category.setEditable(true);
    }
}
