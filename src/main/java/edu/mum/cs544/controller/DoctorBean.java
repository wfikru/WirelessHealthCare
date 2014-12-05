/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.controller;

import edu.mum.cs544.boundary.CategoryFacade;
import edu.mum.cs544.boundary.DoctorFacade;
import edu.mum.cs544.boundary.MedicineFacade;
import edu.mum.cs544.boundary.PatientFacade;
import edu.mum.cs544.boundary.PrescriptionFacade;
import edu.mum.cs544.boundary.SymptomFacade;
import edu.mum.cs544.model.Doctor;
import edu.mum.cs544.model.Medicine;
import edu.mum.cs544.model.Prescription;
import edu.mum.cs544.model.Symptom;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author hiwot
 */
@Named("doctor")
@SessionScoped
public class DoctorBean implements Serializable {

    private Doctor doctor = new LoginCheck().getDoctor();
    private List<Doctor> doctors;
    private List<Symptom> symptoms;
    private Symptom symptom = new Symptom();
    private List<Medicine> medicines;
    private Medicine medicine = new Medicine();
    private Prescription prescription = new Prescription();
    List<String> medicineNames = new ArrayList<String>();

    @EJB
    private CategoryFacade categoryFacade;
    @EJB
    private SymptomFacade symptomFacade;
    @EJB
    private DoctorFacade doctorFacade;
    @EJB
    private MedicineFacade medicineFacade;
    @EJB
    private PatientFacade patientFacade;
    @EJB
    private PrescriptionFacade prescriptionFacade;

    private EntityManager em;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public Symptom getSymptom() {
        return symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }

    public List<Medicine> getMedicines() {
        medicines = medicineFacade.findAll();
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public CategoryFacade getCategoryFacade() {
        return categoryFacade;
    }

    public void setCategoryFacade(CategoryFacade categoryFacade) {
        this.categoryFacade = categoryFacade;
    }

    public SymptomFacade getSymptomFacade() {
        return symptomFacade;
    }

    public void setSymptomFacade(SymptomFacade symptomFacade) {
        this.symptomFacade = symptomFacade;
    }

    public DoctorFacade getDoctorFacade() {
        return doctorFacade;
    }

    public void setDoctorFacade(DoctorFacade doctorFacade) {
        this.doctorFacade = doctorFacade;
    }

    public MedicineFacade getMedicineFacade() {
        return medicineFacade;
    }

    public void setMedicineFacade(MedicineFacade medicineFacade) {
        this.medicineFacade = medicineFacade;
    }

    public PatientFacade getPatientFacade() {
        return patientFacade;
    }

    public void setPatientFacade(PatientFacade patientFacade) {
        this.patientFacade = patientFacade;
    }

    public PrescriptionFacade getPrescriptionFacade() {
        return prescriptionFacade;
    }

    public void setPrescriptionFacade(PrescriptionFacade prescriptionFacade) {
        this.prescriptionFacade = prescriptionFacade;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public String viewMyAssignments(Doctor doc) {
        doctor = doc;
        String doctorCategory = doc.getCategory().getTitle();
        String query = "SELECT symptom FROM Symptom symptom WHERE symptom.category.title =:catTitle";
//        query.setParameter("catTitle",doctorCategory);
//        symptoms = query.getResultList();
        symptoms = symptomFacade.findListByQuery(symptoms, query, "catTitle", doctorCategory);
        symptoms = symptomFacade.findAll();
        return "viewAssignments";
    }

    public String symptomDetail(Symptom s) {
        symptom = s;
        return "viewSymptomDetail";
    }

    public String writePrescription() {
        doctor.getPatients().add(symptom.getPatient());
        doctorFacade.edit(doctor);
        symptom.getPatient().getDoctors().add(doctor);
        patientFacade.edit(symptom.getPatient());
        prescription.getMedicines().add(medicine);
        symptom.getPatient().getPrescriptions().add(prescription);
        prescriptionFacade.create(prescription);
        patientFacade.edit(symptom.getPatient());
        return "prescriptionConfirmation";
    }

    public List<String> getMedicineNames() {
        for (Medicine m : medicineFacade.findAll()) {
            medicineNames.add(m.getNameOfMedicine());
        }
        return medicineNames;
    }

    public void setMedicineNames(List<String> medicineNames) {
        this.medicineNames = medicineNames;
    }
    
}
