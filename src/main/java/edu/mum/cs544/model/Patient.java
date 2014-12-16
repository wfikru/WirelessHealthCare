/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author hiwot
 */
@Entity
public class Patient extends Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "address_fk")
    private Address address;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    private List<Symptom> symptoms;// = new ArrayList<Symptom>();

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinTable(name = "patient_history",
            joinColumns = @JoinColumn(name = "patient_fk"),
            inverseJoinColumns = @JoinColumn(name = "history_fk"))
//    @OneToMany(fetch = FetchType.LAZY)
    private List<MedicalHistory> history;

    @ManyToMany(mappedBy = "patients")
    private List<Doctor> doctors;

    //also have a different  as history
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "prescription_fk")
    
    private List<Prescription> prescriptions;

//    @ManyToMany(mappedBy = "patients",cascade=CascadeType.ALL)
//    private List<Category> categories;
    
    public List<MedicalHistory> getHistory() {
        return history;
    }

    public void setHistory(MedicalHistory history) {
        this.history.add(history);
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Prescription prescriptions) {
        this.prescriptions.add(prescriptions);
    }

//    public List<Category> getCategories() {
//        return categories;
//    }
//
//    public void setCategories(Category categorie) {
//        this.categories.add(categorie);
//    }

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.mum.cs544.model.Patient[ id=" + id + " ]";
    }

}
