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
public class Struct_precalculo_seguridad_empresa_obra {
    JDialog dialog;
    String path;
    String formato;
    String fechaini;
    String fechafin;
    String empresa;
    String file;
    Integer FIC_HC;
    String grupo;
    public Struct_precalculo_seguridad_empresa_obra(JDialog dialog, String path, String formato, String fechaini, String fechafin, String empresa, String file, Integer hc, String g) {
        this.dialog = dialog;
        this.path = path;
        this.formato = formato;
        this.fechaini = fechaini;
        this.fechafin = fechafin;
        this.empresa = empresa;
        this.file = file;
        this.FIC_HC = hc;
        this.grupo = g;
        
    }

    
    
    
}
