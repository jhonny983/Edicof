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
 * @author Johnnatan
 */
public class Struct_Cruce_Precalculo {
    JDialog dialog;
    String path;
    String formato;
    int id_prec;
    String file;
    public Struct_Cruce_Precalculo(JDialog dialog, String path, String formato, int id, String file) {
        this.dialog = dialog;
        this.path = path;
        this.formato = formato;
        this.id_prec = id;
        this.file = file;
    }
}
