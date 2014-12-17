/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.backingBeans;

import edu.mum.cs544.EJBs.VirtualPatientEJB;
import edu.mum.cs544.boundary.MedicalHistoryFacade;
import edu.mum.cs544.model.Category;
import edu.mum.cs544.model.MedicalHistory;
import edu.mum.cs544.model.Medicine;
import edu.mum.cs544.model.Patient;
import edu.mum.cs544.model.Prescription;
import edu.mum.cs544.model.Symptom;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author FWorku
 */
@Named("virtualPatient")
@SessionScoped
public class VirtualPatient implements Serializable {

    @EJB
    private VirtualPatientEJB virtualPatientEJB;
    @EJB
    private MedicalHistoryFacade historyFacade;

    private String categorySelected;

    public VirtualPatient() {
    }

    private Symptom symptom = new Symptom();
    private Patient patient = new Patient();
    private List<MedicalHistory> history = new ArrayList<MedicalHistory>();
    private Prescription prescription = new Prescription();
    private List<Medicine> medicines = new ArrayList<Medicine>();

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

    public VirtualPatientEJB getVirtualPatientEJB() {
        return virtualPatientEJB;
    }

    public void setVirtualPatientEJB(VirtualPatientEJB virtualPatientEJB) {
        this.virtualPatientEJB = virtualPatientEJB;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<MedicalHistory> getHistory() {
        return history;
    }

    public void setHistory(List<MedicalHistory> history) {
        this.history = history;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public List<Medicine> getMedicine() {
        return medicines;
    }

    public void setMedicine(List<Medicine> medicine) {
        this.medicines = medicine;
    }

    public MedicalHistoryFacade getHistoryFacade() {
        return historyFacade;
    }

    public void setHistoryFacade(MedicalHistoryFacade historyFacade) {
        this.historyFacade = historyFacade;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }
    

    public String submitSysmptom() {
        virtualPatientEJB.submitSymptom(symptom, categorySelected);
        return "PatientPortal";

    }

    public String viewAllHistory() {
        patient = (Patient) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("patientKey");
        history = patient.getHistory();

        return "PatientHistory";
    }

    public String viewPrescriptions(MedicalHistory h) {
//        prescription =  historyFacade.find(h.getId()).getPrescription();
        prescription = h.getPrescription();
        medicines = prescription.getMedicines();
        return "prescriptionHistory";

    }

}
