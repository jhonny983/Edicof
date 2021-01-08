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
public class Struct_FIC {
    String path;
    String formato;
    String fechaini;
    String fechafin;
    String empresa;
    JDialog dialog;
    Integer FIC_HC;
    boolean all;
    //PARA GENERAR TODOS LOS REPORTES EL CAMPO EMPRESA DEBE IR VACIO Y EL CAMPO ALL DEBE SER VERDADERO
    //PARA GENERAR UN REPORTE ESPECIFICO EL CAMPO EMPRESA DEBE IR CON EL NIT DE LA EMPRESA Y EL CAMPO ALL DEBE SER FALSO
    public Struct_FIC(JDialog jd, String path, String formato, String fechaini, String fechafin, String empresa, Integer hc, boolean all) {
        this.dialog=jd;
        this.path = path;
        this.formato = formato;
        this.fechaini = fechaini;
        this.fechafin = fechafin;
        this.empresa = empresa;
        this.FIC_HC = hc;
        this.all = all;
    }
    
}
