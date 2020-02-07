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
public class Struct_no_asistencias {
    String path;
    String formato;
    String dias;
    String fecha;
    JDialog dialog;
    

    public Struct_no_asistencias(JDialog dialog, String path, String formato, String dias_, String fecha_) {
        this.dialog = dialog;
        this.path = path;
        this.formato = formato;
        this.dias = dias_;
        this.fecha = fecha_;

    }
    
}
