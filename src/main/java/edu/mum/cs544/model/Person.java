/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.model;

import java.io.Serializable;
import java.util.Calendar;
import static java.util.Calendar.DAY_OF_YEAR;
import static java.util.Calendar.YEAR;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author zeriet
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firsttName;
    private String lastName;
    private String gender;
    private String email;
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Transient
    private int age;
    private String userName;
    private String password; //later password can be sent through email
    private Long phone;

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public int getAge() {
        Calendar birth = new GregorianCalendar();
        birth.setTime(dob);
        Calendar now = new GregorianCalendar();
        now.setTime(new Date());
        int adjust = 0;
        if (now.get(DAY_OF_YEAR) - birth.get(DAY_OF_YEAR) < 0) {
            adjust = -1;
        }
        age = now.get(YEAR) - birth.get(YEAR) + adjust;
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirsttName() {
        return firsttName;
    }

    public void setFirsttName(String firsttName) {
        this.firsttName = firsttName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.mum.cs544.model.Person[ id=" + id + " ]";
    }

}
