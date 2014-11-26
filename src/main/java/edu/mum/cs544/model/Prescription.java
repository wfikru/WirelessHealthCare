/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author zwoldeab
 */
@Entity
public class Prescription implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Doctor doctor;
    private Date date;
    private Patient patient;
    
    //one to many unidirectional
    @OneToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "presc_med",
    joinColumns= @JoinColumn(name = "presc_fk"),
    inverseJoinColumns= @JoinColumn(name = "med_fk") )
    private List<Medicine> medicines;
    
    private String prescDescription;    

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public <any> getMedicines() {
        return medicines;
    }

    public void setMedicines(<any> medicines) {
        this.medicines = medicines;
    }

    public String getPrescDescription() {
        return prescDescription;
    }

    public void setPrescDescription(String prescDescription) {
        this.prescDescription = prescDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prescription)) {
            return false;
        }
        Prescription other = (Prescription) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.mum.cs544.model.Prescription[ id=" + id + " ]";
    }
    
}
