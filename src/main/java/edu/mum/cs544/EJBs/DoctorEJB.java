/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.EJBs;

import edu.mum.cs544.backingBeans.Registration;
import edu.mum.cs544.boundary.CategoryFacade;
import edu.mum.cs544.boundary.DoctorFacade;
import edu.mum.cs544.boundary.MedicalHistoryFacade;
import edu.mum.cs544.boundary.MedicineFacade;
import edu.mum.cs544.boundary.PatientFacade;
import edu.mum.cs544.boundary.PrescriptionFacade;
import edu.mum.cs544.boundary.SymptomFacade;
import edu.mum.cs544.model.Doctor;
import edu.mum.cs544.model.MedicalHistory;
import edu.mum.cs544.model.Medicine;
import edu.mum.cs544.model.Patient;
import edu.mum.cs544.model.Prescription;
import edu.mum.cs544.model.Symptom;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.interceptor.AroundInvoke;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import webServices.BundleMessages;
import webServices.MailService;

/**
 *
 * @author hiwot
 */
@Stateless
public class DoctorEJB {

    private Doctor doctor = new Doctor();
    private List<Doctor> doctors;
    private List<Patient> patients;
    private List<Symptom> symptoms;
    private Symptom symptom = new Symptom();
    private Patient patient = new Patient();
    private List<Medicine> medicines = new ArrayList<Medicine>();
    private Medicine medicine = new Medicine();
    private Prescription prescription = new Prescription();
    private MedicalHistory history = new MedicalHistory();
    private List<MedicalHistory> historyList = new ArrayList<MedicalHistory>();
    private List<Prescription> prescriptions = new ArrayList<Prescription>();
    List<String> medicineNames = new ArrayList<String>();
    private String diagnosis;
    private String recipient;
    private String subject = "Prescription";
    private BundleMessages bundle1 = new BundleMessages();
    private String message = bundle1.getPrescriptionNotification();

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
    @EJB
    private MedicalHistoryFacade historyFacade;


//    public Doctor getDoctor() {
//        return doctor;
//    }
//
//    public void setDoctor(Doctor doctor) {
//        this.doctor = doctor;
//    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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

    public MedicalHistory getHistory() {
        return history;
    }

    public List<MedicalHistory> getHistoryList() {
        return historyList;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public void setHistoryList(List<MedicalHistory> historyList) {
        this.historyList = historyList;
    }

    public void setHistory(MedicalHistory history) {
        this.history = history;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public BundleMessages getBundle1() {
        return bundle1;
    }

    public void setBundle1(BundleMessages bundle1) {
        this.bundle1 = bundle1;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public List<Symptom> findSymptoms(String query, int bindParam, String bindValue) {
        return symptomFacade.findListByQuery(query, bindParam, bindValue);
    }

    public String symptomDetail(Symptom s) {
        symptom = s;
        return "viewSymptomDetail";
    }

    public String writePrescription() {
        symptom.setPrescribed(true);
        symptoms.remove(symptom);
        history.setCheckUpDate(new Date());
        history.setDiagnosticResult(diagnosis);
        symptom.getPatient().getHistory().add(history);
        symptom.getPatient().getPrescriptions().add(prescription);
        history.setPrescription(prescription);
        doctor.getPatients().add(symptom.getPatient());
        symptom.getPatient().getDoctors().add(doctor);
        prescriptionFacade.create(prescription);
        patientFacade.edit(symptom.getPatient());
        doctorFacade.edit(doctor);
        symptomFacade.edit(symptom);

        recipient = symptom.getPatient().getEmail();
        sendEmail(recipient);
        return "prescriptionConfirmation";
    }
//    @AroundInvoke

    @Asynchronous
    public void sendEmail(String recipient) {
        try {
            MailService.sendMessage(recipient, subject, message);
        } catch (MessagingException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    
//     public List<Patient> viewAllHistory(Doctor doc) {
//        return doctorFacade.find(doc.getId()).getPatients();
//    }
//     
     public MedicalHistory findHistory(Long id) {       
        return historyFacade.find(id);
    }

    public String addMedicine() {
        prescription.getMedicines().add(medicine);
        for (Medicine m : medicines) {
            if (m.equals(medicine)) {
                medicine = new Medicine();
                return "prescriptionForm";
            }
        }
        medicines.add(medicine);
        medicine = new Medicine();
        return "prescriptionForm";
    }

    public String lastMedicine() {
        prescription.getMedicines().add(medicine);
        for (Medicine m : medicines) {
            if (m.equals(medicine)) {
                return "confirmPrescription";
            }
        }
        medicines.add(medicine);
//        System.out.print(medicine.isEditable());
        return "confirmPrescription";
    }

    public String deleteRow(Medicine m) {
        prescription.getMedicines().remove(m);
        medicines.remove(medicine);
        return "confirmPrescription";
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
//
//    public String viewAllHistory(Doctor doc) {
//        patients = doctorFacade.find(doc.getId()).getPatients();
//        return "patientHistoryFromDoctor";
//    }

    public String historyDetail(Patient p) {
        historyList = p.getHistory();
        return "patientHistoryDetail";
    }

    public String viewPrescription(MedicalHistory histroy) {
        prescription = history.getPrescription();
        medicines = history.getPrescription().getMedicines();
        return "historyPrescriptionsDetail";
    }
}
