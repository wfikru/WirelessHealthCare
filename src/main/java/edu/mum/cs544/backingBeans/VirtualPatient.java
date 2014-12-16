/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.backingBeans;

import edu.mum.cs544.EJBs.VirtualPatientEJB;
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
    private VirtualPatientEJB virtualPatientEJB;
    
    
      
     private String categorySelected;
    
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
        return virtualPatientEJB.getAllCategory();
    }
  

    public String getCategorySelected() {
        return categorySelected;
    }

    public void setCategorySelected(String categorySelected) {
        this.categorySelected = categorySelected;
    }
    
    public String submitSysmptom() {
        virtualPatientEJB.submitSymptom(symptom, categorySelected);        
        return "symptomSubmitSuccess";
        
    }
    
}
