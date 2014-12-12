/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.controller;

import edu.mum.cs544.boundary.CategoryFacade;
import edu.mum.cs544.boundary.PatientFacade;
import edu.mum.cs544.boundary.SymptomFacade;
import edu.mum.cs544.model.Category;
import edu.mum.cs544.model.MedicalHistory;
import edu.mum.cs544.model.Patient;
import edu.mum.cs544.model.Symptom;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author FWorku
 */
@Named(value = "virtualPatient")
@SessionScoped
public class VirtualPatient implements Serializable {

    @EJB
    private CategoryFacade categoryFacade;

    @EJB
    private PatientFacade patientFacade;
    @EJB
    private SymptomFacade symptomFacade;

    private String categorySelected;
    private Category category = new Category();

    public VirtualPatient() {
    }
    private Symptom symptom = new Symptom();
    private Patient patient = new Patient();

    public Symptom getSymptom() {
        return symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }

    public List<MedicalHistory> getPatientHistory() {
        return patient.getHistory();
    }

    public List<Category> getCategory() {
        return this.categoryFacade.findAll();
    }

    public String getCategorySelected() {
        return categorySelected;
    }

    public void setCategorySelected(String categorySelected) {
        this.categorySelected = categorySelected;
    }

    public Category findCatagory(String title) {
        String query = "SELECT c FROM Category c WHERE c.title = :" + categorySelected;
        Category cat = this.categoryFacade.findSingleByQuery(category, query, categorySelected, title);
        return cat;
    }

    public String submitSysmptom() {

        Category cat = findCatagory(categorySelected);        
        System.out.println("++++++++++++++++++++++++++++++++++++++++++" +cat.getTitle());
        
//        patient = new LoginCheck().getPatient();
        patient = (Patient) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("patientKey");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++" + patient.getEmail());

        symptom.setCategory(cat);         
        symptom.setDate(new Date());
        symptom.setPatient(patient);
        this.symptomFacade.create(symptom);
        return "symptomSubmitSuccess";

    }

}
