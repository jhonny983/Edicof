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
public class Struct_precalculo_seguridad {
    String path;
    String formato;
    String fechaini;
    String fechafin;
    JDialog dialog;
    Integer FIC_HC;
    String grupo;
    Boolean def;
    long id_prec;
    public Struct_precalculo_seguridad(JDialog jd, String path, String formato, String fechaini, String fechafin, Integer hc, String g, boolean d, long id) {
        this.dialog=jd;
        this.path = path;
        this.formato = formato;
        this.fechaini = fechaini;
        this.fechafin = fechafin;
        this.FIC_HC = hc;
        this.grupo = g;
        this.def = d;
        this.id_prec = id;
    }
    
}
