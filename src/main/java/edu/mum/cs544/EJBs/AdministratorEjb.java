/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.EJBs;

import edu.mum.cs544.boundary.CategoryFacade;
import edu.mum.cs544.boundary.DoctorFacade;
import edu.mum.cs544.model.Category;
import edu.mum.cs544.model.Doctor;
import javax.ejb.EJB;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author FWorku
 */
@Stateless
public class AdministratorEjb  {

    /**
     * Creates a new instance of Administrator
     */
    public AdministratorEjb() {
    }
    @EJB
    private DoctorFacade doctorFacade;
    @EJB
    private CategoryFacade categoryFacade;
    
    public List<Doctor> editDoctorList() {
        List<Doctor> doc = null;
        doc = this.doctorFacade.findAll();

        return doc;
    }

    public List<Category> editCategoryList() {
        List<Category> cat= null;
        cat = this.categoryFacade.findAll();
        return cat;
    }

    public void saveDocChanges(List<Doctor> doc) {

        for (Doctor doctor : doc) {
            doctor.setEditable(false);
            this.doctorFacade.edit(doctor);
        }
    }

    public void saveCatChanges(List<Category> cat) {

        for (Category category : cat) {
            category.setEditable(false);
            this.categoryFacade.edit(category);
        }
    }

    //how to handle the patients associated with it when deleted
    public void removeDoctor(Doctor doctor) {
        this.doctorFacade.remove(doctor);
    }
}
