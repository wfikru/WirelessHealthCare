/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.controller;

import edu.mum.cs544.boundary.CategoryFacade;
import edu.mum.cs544.boundary.DoctorFacade;
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
    @EJB
    private CategoryFacade categoryFacade;
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
        this.doc = this.doctorFacade.findAll();

        return "manageDoctorsList";
    }

    public String editCategoryList() {
        this.cat = this.categoryFacade.findAll();
        return "manageCategoryList";
    }

    public void saveDocChanges() {

        for (Doctor doctor : doc) {
            doctor.setEditable(false);
            this.doctorFacade.edit(doctor);
        }
    }

    public void saveCatChanges() {

        for (Category category : cat) {
            category.setEditable(false);
            this.categoryFacade.edit(category);
        }
    }

    //how to handle the patients associated with it when deleted
    public void removeDoctor(Doctor doctor) {
        this.doctorFacade.remove(doctor);
    }

    public void editDocRecord() {
        doctor.setEditable(true);
    }

    public void editCatRecord() {
        category.setEditable(true);
    }
}
