/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.backingBeans;


import edu.mum.cs544.EJBs.DoctorEJB;
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
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.mail.MessagingException;
import webServices.BundleMessages;
import webServices.MailService;

/**
 *
 * @author hiwot
 */
@Named("doctor")
@SessionScoped
public class DoctorBean implements Serializable {

    private Doctor doctor = new LoginCheck().getDoctor();
    private List<Doctor> doctors;
    private List<Patient> patients;
    private List<Symptom> symptoms;
    private Symptom symptom = new Symptom();
    private Patient patient = new Patient();
    private List<Medicine> medicines = new ArrayList<Medicine>();
    private List<Medicine> medicines2 = new ArrayList<Medicine>();
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
    private DoctorEJB doctorEjb;
    

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
    
    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public List<Medicine> getMedicines2() {
        return medicines2;
    }

    public void setMedicines2(List<Medicine> medicines2) {
        this.medicines2 = medicines2;
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

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public String viewMyAssignments() {
        doctor = (Doctor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("doctorKey");
        String doctorCategory = doctor.getCategory().getTitle();
        String query = "SELECT symptom FROM Symptom symptom WHERE symptom.category.title= ?1"
                + " AND symptom.prescribed=false ";
        symptoms = doctorEjb.findSymptoms(query, 1, doctorCategory);
        return "viewAssignments";
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
        return doctorEjb.writePrescription(prescription, doctor, symptom, history);
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
        return "confirmPrescription2";
    }

    public String viewAllHistory() {
        doctor = (Doctor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("doctorKey");
        patients = doctorFacade.find(doctor.getId()).getPatients();
        return "patientHistoryFromDoctor";
    }

    public String historyDetail(Patient p) {
        historyList = p.getHistory();
        return "patientHistoryDetail";
    }

    public String viewPrescription(Long id) {
        history = doctorEjb.findHistory(id);
        prescription = history.getPrescription();
        medicines.clear();
        setMedicines2(history.getPrescription().getMedicines());
        return "historyPrescriptionsDetail";
    }

}
