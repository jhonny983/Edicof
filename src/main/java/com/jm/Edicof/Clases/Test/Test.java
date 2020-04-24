/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.Clases.Test;

import com.jm.Edicof.Clases.Conexion;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ADMIN
 */
public class Test {
    public Test (){
        
            Conexion con = new Conexion("127.0.0.1","e_occidente","root","jhonny3029735");
            con.conexion();
        try {    
            ClassLoader cl= this.getClass().getClassLoader();
            InputStream fis = (cl.getResourceAsStream("Reportes/listado_diario_obra.jasper"));
            //InputStream fis = (cl.getResourceAsStream("listado_diario_obra.jasper"));
            JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
            //JasperReport rep = (JasperReport) JRLoader.loadObject("listado_diario_obra.jasper");
            Map par = new HashMap();
            par.put("Fecha",new Date());
            JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
            con.cerrar();
            JasperViewer jv = new JasperViewer(jprint,false);
            jv.setTitle("Reporte Personal Activo");
            jv.setVisible(true);
        } catch (Exception ex) {
            con.cerrar();
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new Test();
    }

}
