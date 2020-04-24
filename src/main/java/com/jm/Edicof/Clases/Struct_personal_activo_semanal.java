/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.Clases;

import java.util.Date;
import javax.swing.JDialog;

/**
 *
 * @author ADMIN
 */
public class Struct_personal_activo_semanal {
    JDialog dialog;
    String path;
    String formato;
    Date fecha;
    

    public Struct_personal_activo_semanal(JDialog dialog, String path, String formato, Date fecha) {
        this.dialog = dialog;
        this.path = path;
        this.formato = formato;
        this.fecha = fecha;
        
    }
    
    
}
