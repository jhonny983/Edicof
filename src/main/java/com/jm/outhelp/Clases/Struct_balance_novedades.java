/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.outhelp.Clases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JDialog;

/**
 *
 * @author ADMIN
 */
public class Struct_balance_novedades {
    JDialog dialog =null;
    String format;
    String path = "";
    String f_ant="";
    String f_inicial = "";
    String f_final = "";
    String f_desp="";
    String host;
    String db;
    String usu;
    String cont;
    String file="";
    
    public Struct_balance_novedades(JDialog jd, String p, String form, String f1, String f2, String h, String d, String u, String c, String f) {
        try {
            dialog = jd;
            path=p;
            format=form;
            f_inicial=f1;
            f_final=f2;
            Calendar cal = Calendar.getInstance();
            cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(f2));
            cal.add(Calendar.MONTH, 1);
            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            f_desp=new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
            
            cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(f1));
            cal.add(Calendar.MONTH, -1);
            cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
            f_ant=new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
            
            this.host=h;
            this.db=d;
            this.usu=u;
            this.cont=c;
            file=f;
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    
    
}
