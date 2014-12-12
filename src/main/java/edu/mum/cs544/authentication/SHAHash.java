/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs544.authentication;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fikru
 */
public class SHAHash {

    public String getEncryptedPassword(String textPassword) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SHAHash.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            md.update(textPassword.getBytes("UTF-8")); // Change this to "UTF-16" if needed
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SHAHash.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] digest = md.digest();

        return byteToString(digest);
    }

    public String byteToString(byte[] input) {
        String hash = "";
        int i = 0;
        for (byte b : input) {
            i = b & 0xFF;
            hash += Integer.toHexString(i);
        }
        return hash;
    }

}
