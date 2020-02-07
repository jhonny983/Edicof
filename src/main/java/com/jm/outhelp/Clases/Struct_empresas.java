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
public class Struct_empresas {
    JDialog dialog;
    String path;
    String formato;
    Date fecha;
    String file;
    String tipo;

    public Struct_empresas(JDialog dialog, String path, String formato, Date fecha, String file, String tipo) {
        this.dialog = dialog;
        this.path = path;
        this.formato = formato;
        this.fecha = fecha;
        this.file = file;
        this.tipo = tipo;
    }
    
    
}
