/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.authentication;

import edu.mum.cs544.boundary.AddressFacade;
import edu.mum.cs544.boundary.CategoryFacade;
import edu.mum.cs544.boundary.DoctorFacade;
import edu.mum.cs544.boundary.PatientFacade;
import edu.mum.cs544.boundary.SymptomFacade;
import edu.mum.cs544.boundary.usersFacade;
import edu.mum.cs544.model.Address;
import edu.mum.cs544.model.Category;
import edu.mum.cs544.model.Doctor;
import edu.mum.cs544.model.Patient;
import edu.mum.cs544.model.Symptom;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author Fikru
 */
@Singleton
@Startup
public class startup {

    @Inject
    private usersFacade usersfacade;

    @Inject
    private PatientFacade patientFacade;
     @Inject
    private SymptomFacade symptomFacade;

    @Inject
    private DoctorFacade doctorFacade;

    @Inject
    private CategoryFacade categoryFacade;

    @Inject
    private AddressFacade addressFacade;

    @PostConstruct
    public void creatGroups() {

        SHAHash sha = new SHAHash();
        Users _users = new Users();
        _users.setUsername("admin");

        _users.setPassword(sha.getEncryptedPassword("admin"));

        _users.setGroupname("ADMIN");

        this.usersfacade.create(_users);

         Address address1 = new Address();
        address1.setState("IA");
        address1.setStreet("100N 4th");
        address1.setZip("52557");

        Patient patient1 = new Patient();
        patient1.setFirstName("Yared");
        patient1.setLastName("Asefaw");
        patient1.setDob(null);
        patient1.setGender("M");
        patient1.setEmail("yared@gmail.com");
        patient1.setPassword(sha.getEncryptedPassword(""));
        patient1.setAddress(address1);
        patient1.setDob( new Date(11/11/1983));
        
        this.patientFacade.create(patient1);
        
        Category category1 = new Category();
        category1.setTitle("Phsyiotherapy");
        this.categoryFacade.create(category1);
        Category category2 = new Category();
        category2.setTitle("General Practioner"); 
        this.categoryFacade.create(category2);
        
        Symptom symptom1 = new Symptom();
        symptom1.setSystolic(100);
        symptom1.setDizziness(true);
        symptom1.setTemperature(100);
        symptom1.setNausea(true);
        symptom1.setCategory(category1);
        symptom1.setPatient(patient1);
//        symptom1.setPatient(patient1);
        this.symptomFacade.create(symptom1);
        
        Doctor doctor1 = new Doctor();
        doctor1.setFirstName("Zeriet");
        doctor1.setLastName("Tekie");
        doctor1.setEmail("zeriet@gmail.com");
        doctor1.setWorkExp(3);
        doctor1.setCategory(category1);
        doctor1.setAddress(address1);
        doctor1.setPassword("");
        this.doctorFacade.create(doctor1);  
        System.out.println("+++++++++++Test++++++++" + patient1.getAddress().getState());
        
        Users patientUser = new Users();
        patientUser.setGroupname("PATIENT");
        patientUser.setUsername(patient1.getEmail());
        patientUser.setPassword(patient1.getPassword());
       this.usersfacade.create(patientUser);
    }
}
