/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.controller;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author FWorku
 */
@Named(value = "virtualDoctor")
@Dependent
public class VirtualDoctor {

    /**
     * Creates a new instance of VirtualDoctor
     */
    public VirtualDoctor() {
    }
    
}
