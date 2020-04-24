/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.Clases;

import javax.swing.JDialog;

/**
 *
 * @author ADMIN
 */
public class Struct_nov_pila_retiro_emp {
    String path = "";
    String f_inicial = "";
    String f_final = "";
    JDialog jd_nov_pila =null;
    String file="";
    String id_empresa="";

    public Struct_nov_pila_retiro_emp(JDialog jd, String p, String f1, String f2, String f, String id) {
        this.jd_nov_pila = jd;
        this.path = p;
        this.f_inicial = f1;
        this.f_final = f2;
        this.file=f;
        this.id_empresa = id;
    }
    
    
}
