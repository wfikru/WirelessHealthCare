/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.EJBs;

import edu.mum.cs544.boundary.CategoryFacade;
import edu.mum.cs544.boundary.PatientFacade;
import edu.mum.cs544.boundary.SymptomFacade;
import edu.mum.cs544.model.Category;
import edu.mum.cs544.model.Patient;
import edu.mum.cs544.model.Symptom;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;

/**
 *
 * @author zeriet
 */
@Stateless
public class VirtualPatientEJB {

    @EJB
    private CategoryFacade categoryFacade;

    @EJB
    private PatientFacade patientFacade;
    @EJB
    private SymptomFacade symptomFacade;

    private Category category = new Category();

    public List<Category> getAllCategory() {
        return this.categoryFacade.findAll();
    }

    public Category findCatagory(String categorySelected) {
        String query = "SELECT c FROM Category c WHERE c.title = :" + categorySelected;
        Category cat = this.categoryFacade.findSingleByQuery(category, query, categorySelected, categorySelected);
        return cat;
    }

    public void submitSymptom(Symptom symptom, String categorySelected) {

        Category cat = findCatagory(categorySelected);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++" + cat.getTitle());

        Patient patient = (Patient) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("patientKey");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++" + patient.getEmail());
        symptom.setCategory(cat);
        symptom.setDate(new Date());
        symptom.setPatient(patient);
        this.symptomFacade.create(symptom);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
