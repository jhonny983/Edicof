/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.outhelp.Clases;

import java.util.Date;
import javax.swing.JDialog;

/**
 *
 * @author ADMIN
 */
public class Struct_biometrico {
    JDialog dialog;
    String path;
    String formato;
    Date fechaini;
    Date fechafin;
    String empleador;
    String tipo;
    String file;

    public Struct_biometrico(JDialog dialog, String path, String formato, Date fechaini, Date fechafin, String empleador, String tipo, String file) {
        this.dialog = dialog;
        this.path = path;
        this.formato = formato;
        this.fechaini = fechaini;
        this.fechafin = fechafin;
        this.empleador = empleador;
        this.tipo = tipo;
        this.file = file;
    }
    
    
}
