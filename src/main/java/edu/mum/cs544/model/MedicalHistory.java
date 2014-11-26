/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author YAsfaw
 */
@Entity
public class MedicalHistory implements Serializable {
    
    private Patient  PatientId;
    private String DiagnosticResult;
    private Date date;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "hist_pat",
    joinColumns = @JoinColumn(name = "history_fk"),
    inverseJoinColumns = @JoinColumn(name = "patient_fk"))
    
    private List<Patient> patients;
    
    public MedicalHistory(String PatientId, Long id) {
        this.PatientId = PatientId;
        this.id = id;
    }

    public String getDiagnosticResult() {
        return DiagnosticResult;
    }

    public void setDiagnosticResult(String DiagnosticResult) {
        this.DiagnosticResult = DiagnosticResult;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String Condition) {
        this.Condition = Condition;
    }
    private String  Condition;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
  

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
        if (!(object instanceof MedicalHistory)) {
            return false;
        }
        MedicalHistory other = (MedicalHistory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.mum.cs544.model.MedicalHistory[ id=" + id + " ]";
    }
    
}
