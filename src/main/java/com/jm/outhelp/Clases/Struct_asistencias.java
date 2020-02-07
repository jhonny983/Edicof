/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.outhelp.Clases;

import javax.swing.JDialog;

/**
 *
 * @author ADMIN
 */
public class Struct_asistencias {
    String path;
    String formato;
    String fechaini;
    String fechafin;
    JDialog dialog;
    String tipo;
    //String empleador;
    //String file;

    public Struct_asistencias(JDialog dialog, String path, String formato, String fechaini, String fechafin, String t) {
        this.dialog = dialog;
        this.path = path;
        this.formato = formato;
        this.fechaini = fechaini;
        this.fechafin = fechafin;
        this.tipo = t;
//        this.empleador = empleador;
//        this.file = file;
    }
    
}
