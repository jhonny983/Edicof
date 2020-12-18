/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.Clases;

import com.jm.Edicof.GUI.Main;
import com.jm.Edicof.GUI.Wait_rep;
import com.jm.ScriptletsJasper.ScriptletsPrecalculo;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author ADMIN
 */
public class GenerarReportes extends Thread{
    Object rep;
    Struct_nov_pila_retiro s_n_p_r;
    Struct_nov_pila_retiro_emp s_n_p_r_e;
    Struct_nov_pila_ingreso s_n_p;
    Struct_nov_pila_ingreso_emp s_n_p_e;
    Struct_precalculo_seguridad s_p_s;
    Struct_precalculo_seguridad_empresa_ciudad s_p_s_e_c;
    Struct_precalculo_seguridad_empresa_obra s_p_s_e_o;
    Struct_personal_activo s_p_a;
    Struct_personal_activo_semanal s_p_a_s;
    Struct_personal_activo_externo s_p_a_e;
    Struct_personal_activo_semanal_externo s_p_a_s_e;
    Struct_asistencias s_a;
    Struct_asistencias_empresas s_a_e;
    Struct_asistencias_obras s_a_o;
    Struct_biometrico s_b;
    Struct_balance_novedades s_b_n;
    Struct_empresas s_e_a;
    Struct_no_asistencias s_n_a;
    Struct_no_asistencias_empresas s_n_a_e;
    Struct_Cruce_Precalculo s_c_p;
    Struct_Cruce_Tesoreria s_c_t;
    public GenerarReportes(Object o) {
        this.rep=o;
    }
    @Override
    public void run() {
       if (rep instanceof Struct_nov_pila_ingreso) {
            s_n_p = (Struct_nov_pila_ingreso)rep;
            Novedades_Pila_Ingreso(s_n_p.path, s_n_p.f_inicial, s_n_p.f_final);
        }else{
            if (rep instanceof Struct_precalculo_seguridad) {
                s_p_s = (Struct_precalculo_seguridad)rep;
                if (s_p_s.grupo.equals("")) {
                    precalculo_seguridad_ciudad(s_p_s.path, s_p_s.formato, s_p_s.fechaini, s_p_s.fechafin, s_p_s.FIC_HC, s_p_s.def, s_p_s.id_prec);
                }else{
                    precalculo_seguridad_obra(s_p_s.path, s_p_s.formato, s_p_s.fechaini, s_p_s.fechafin, s_p_s.FIC_HC, s_p_s.grupo);
                }
            }else{
                if (rep instanceof Struct_precalculo_seguridad_empresa_ciudad) {
                    s_p_s_e_c = (Struct_precalculo_seguridad_empresa_ciudad)rep;
                    precalculo_seguridad_empresa_ciudad(s_p_s_e_c.path, s_p_s_e_c.formato, s_p_s_e_c.fechaini, s_p_s_e_c.fechafin, s_p_s_e_c.empresa, s_p_s_e_c.FIC_HC);
                }else{
                    if (rep instanceof Struct_precalculo_seguridad_empresa_obra) {
                        s_p_s_e_o = (Struct_precalculo_seguridad_empresa_obra)rep;
                        precalculo_seguridad_empresa_obra(s_p_s_e_o.path, s_p_s_e_o.formato, s_p_s_e_o.fechaini, s_p_s_e_o.fechafin, s_p_s_e_o.empresa, s_p_s_e_o.FIC_HC, s_p_s_e_o.grupo);
                    }
                    else{
                        if (rep instanceof Struct_personal_activo) {
                            s_p_a = (Struct_personal_activo)rep;
                            personal_activo(s_p_a.path, s_p_a.formato, s_p_a.fecha);
                        }else{
                            if (rep instanceof Struct_personal_activo_semanal) {
                                s_p_a_s = (Struct_personal_activo_semanal)rep;
                                personal_activo_semanal(s_p_a_s.path, s_p_a_s.formato, s_p_a_s.fecha);
                            }else{
                                if (rep instanceof Struct_asistencias) {
                                    s_a = (Struct_asistencias)rep;
                                    if (s_a.tipo.equals("EMPRESA")) {
                                        asistencias_empresas(s_a.path, s_a.formato, s_a.fechaini, s_a.fechafin);
                                    }else{
                                        if (s_a.tipo.equals("OBRAS")) {
                                            asistencias_obras(s_a.path, s_a.formato, s_a.fechaini, s_a.fechafin);
                                        }
                                    }
                                }else{
                                    if (rep instanceof Struct_asistencias_empresas) {
                                        s_a_e = (Struct_asistencias_empresas)rep;
                                        asistencias_empresas_empresa(s_a_e.path, s_a_e.formato, s_a_e.fechaini, s_a_e.fechafin, s_a_e.empleador);
                                    }else{
                                        if (rep instanceof Struct_asistencias_obras) {
                                            s_a_o = (Struct_asistencias_obras)rep;
                                            asistencias_empresas_obras(s_a_o.path, s_a_o.formato, s_a_o.fechaini, s_a_o.fechafin, s_a_o.obra);
                                        }else{
                                            if (rep instanceof Struct_biometrico) {
                                                s_b = (Struct_biometrico)rep;
                                                biometrico(s_b.path, s_b.formato, s_b.fechaini, s_b.fechafin,s_b.empleador, s_b.tipo);
                                            }else{
                                                if (rep instanceof Struct_nov_pila_ingreso_emp) {
                                                    s_n_p_e = (Struct_nov_pila_ingreso_emp)rep;
                                                    Novedades_Pila_Ingreso_empresa(s_n_p_e.path, s_n_p_e.f_inicial, s_n_p_e.f_final, s_n_p_e.id_empresa);
                                                }else{
                                                    if (rep instanceof Struct_balance_novedades) {
                                                        s_b_n = (Struct_balance_novedades)rep;
                                                        //System.out.println(s_b_n.path+"_"+s_b_n.format+"_"+s_b_n.f_inicial+"_"+s_b_n.f_final+"_"+s_b_n.f_desp+"_"+s_b_n.host+"_"+s_b_n.db+"_"+s_b_n.usu+"_"+s_b_n.cont);
                                                        Balance_novedades(s_b_n.path, s_b_n.format, s_b_n.f_ant, s_b_n.f_inicial, s_b_n.f_final, s_b_n.f_desp, s_b_n.host, s_b_n.db, s_b_n.usu, s_b_n.cont);
                                                    }else{
                                                        if (rep instanceof Struct_nov_pila_retiro) {
                                                            s_n_p_r = (Struct_nov_pila_retiro)rep;
                                                            //System.out.println(s_b_n.path+"_"+s_b_n.format+"_"+s_b_n.f_inicial+"_"+s_b_n.f_final+"_"+s_b_n.f_desp+"_"+s_b_n.host+"_"+s_b_n.db+"_"+s_b_n.usu+"_"+s_b_n.cont);
                                                            Novedades_Pila_Retiro(s_n_p_r.path, s_n_p_r.f_inicial, s_n_p_r.f_final);
                                                        }else{
                                                            if (rep instanceof Struct_nov_pila_retiro_emp) {
                                                                s_n_p_r_e = (Struct_nov_pila_retiro_emp)rep;
                                                                Novedades_Pila_Retiro_empresa(s_n_p_r_e.path, s_n_p_r_e.f_inicial, s_n_p_r_e.f_final, s_n_p_r_e.id_empresa);
                                                            }else{
                                                                if (rep instanceof Struct_empresas) {
                                                                    s_e_a = (Struct_empresas)rep;
                                                                    empresas_activas(s_e_a.path, s_e_a.formato, s_e_a.fecha, s_e_a.tipo);
                                                                }else{
                                                                    if (rep instanceof Struct_no_asistencias) {
                                                                       s_n_a = (Struct_no_asistencias)rep;
                                                                       no_asistencias(s_n_a.path, s_n_a.formato, s_n_a.dias, s_n_a.fecha);
                                                                    }else{
                                                                        if (rep instanceof Struct_no_asistencias_empresas) {
                                                                            s_n_a_e = (Struct_no_asistencias_empresas)rep;
                                                                            no_asistencias_empresas(s_n_a_e.path, s_n_a_e.formato, s_n_a_e.dias, s_n_a_e.fecha, s_n_a_e.empresa);
                                                                        }else{
                                                                            if (rep instanceof Struct_Cruce_Precalculo) {
                                                                                s_c_p = (Struct_Cruce_Precalculo)rep;
                                                                                cruce_precalculo(s_c_p.path, s_c_p.formato, s_c_p.id_prec);
                                                                            }else{
                                                                                if (rep instanceof Struct_Cruce_Tesoreria) {
                                                                                    s_c_t = (Struct_Cruce_Tesoreria)rep;
                                                                                    cruce_tesoreria(s_c_t.path, s_c_t.formato, s_c_t.id_prec);
                                                                                }else{
                                                                                    if (rep instanceof Struct_personal_activo_externo) {
                                                                                        System.out.println("Personal Activo Externo Consolidado");
                                                                                        s_p_a_e = (Struct_personal_activo_externo)rep;
                                                                                        personal_activo_externo(s_p_a_e.path, s_p_a_e.formato, s_p_a_e.fecha);
                                                                                    }else{
                                                                                        if (rep instanceof Struct_personal_activo_semanal_externo) {
                                                                                            System.out.println("Personal Activo Externo Semanal");
                                                                                            s_p_a_s_e = (Struct_personal_activo_semanal_externo)rep;
                                                                                            personal_activo_semanal_externo(s_p_a_s_e.path, s_p_a_s_e.formato, s_p_a_s_e.fecha);
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void personal_activo (String path, String formato, Date fecha){
        try {
            Wait_rep.btn_aceptar.setEnabled(false);
            s_p_a.dialog.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            Wait_rep.mensaje.setText("Generando reporte de personal activo");
            Wait_rep.bar.setMaximum(1);
            Conexion con = new Conexion();
            con.conexion();
            ClassLoader cl= this.getClass().getClassLoader();
            InputStream fis = (cl.getResourceAsStream("Reportes/listado_diario_obra.jasper"));
            JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
            Map par = new HashMap();
            par.put("Fecha",fecha);
            JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
            con.cerrar();
            Wait_rep.progreso_1.setText(s_p_a.file);
            Wait_rep.bar.setValue(1);
            if (formato.equals(".pdf")) {
                JasperExportManager.exportReportToPdfFile(jprint,path);
            }
            if (formato.equals(".xls")) {
                System.out.println("Clases.GenerarReportes.personal_activo()");
                JRXlsxExporter exporterXLS = new JRXlsxExporter();
                Map<String, String> dateFormats = new HashMap<String, String>();
                dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                exporterXLS.setExporterInput(new SimpleExporterInput(jprint));
                exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path));       
                repConfig.setWrapText(Boolean.TRUE);//
                repConfig.setOnePagePerSheet(Boolean.FALSE);
                repConfig.setDetectCellType(Boolean.TRUE);
                repConfig.setWhitePageBackground(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                repConfig.setFormatPatternsMap(dateFormats);
                repConfig.setCollapseRowSpan(Boolean.TRUE);//
                exporterXLS.setConfiguration(repConfig);
                exporterXLS.exportReport();
                
            }
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"El reporte se ha generado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
            s_p_a.dialog.dispose();
            
           JasperViewer jv = new JasperViewer(jprint,false);
//            jv.setTitle("Reporte Personal Activo");
//            jv.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        } 
    
    }
    public void personal_activo_externo (String path, String formato, Date fecha){
        try {
            Wait_rep.btn_aceptar.setEnabled(false);
            s_p_a_e.dialog.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            Wait_rep.mensaje.setText("Generando reporte de personal activo externo");
            Wait_rep.bar.setMaximum(1);
            Conexion con = new Conexion();
            con.conexion();
            ClassLoader cl= this.getClass().getClassLoader();
            InputStream fis = (cl.getResourceAsStream("Reportes/listado_diario_obra_externo.jasper"));
            JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
            Map par = new HashMap();
            par.put("Fecha",fecha);
            JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
            con.cerrar();
            Wait_rep.progreso_1.setText(s_p_a_e.file);
            Wait_rep.bar.setValue(1);
            if (formato.equals(".pdf")) {
                JasperExportManager.exportReportToPdfFile(jprint,path);
            }
            if (formato.equals(".xls")) {
                System.out.println("Clases.GenerarReportes.personal_activo()");
                JRXlsxExporter exporterXLS = new JRXlsxExporter();
                Map<String, String> dateFormats = new HashMap<String, String>();
                dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                exporterXLS.setExporterInput(new SimpleExporterInput(jprint));
                exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path));       
                repConfig.setWrapText(Boolean.TRUE);//
                repConfig.setOnePagePerSheet(Boolean.FALSE);
                repConfig.setDetectCellType(Boolean.TRUE);
                repConfig.setWhitePageBackground(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                repConfig.setFormatPatternsMap(dateFormats);
                repConfig.setCollapseRowSpan(Boolean.TRUE);//
                exporterXLS.setConfiguration(repConfig);
                exporterXLS.exportReport();
                
            }
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"El reporte se ha generado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
            s_p_a_e.dialog.dispose();
            
           JasperViewer jv = new JasperViewer(jprint,false);
//            jv.setTitle("Reporte Personal Activo");
//            jv.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        } 
    
    }
    public void personal_activo_semanal (String path, String formato, Date fecha){
        Wait_rep.btn_aceptar.setEnabled(false);
        s_p_a_s.dialog.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Wait_rep.mensaje.setText("Generando reportes de personal activo semanal");
        path=path+"\\PERSONAL_ACTIVO_SEMANAL_"+new SimpleDateFormat("yyyy-MM-dd").format(fecha);
        File folder = new File(path);
        folder.mkdirs();
        List<InputStream> list = new ArrayList<>();
        XSSFWorkbook wb=null;
        if (formato.equals(".xls")) {
            wb = new XSSFWorkbook(); // Carga todo el archivo
        }
        Conexion con = new Conexion();
        con.conexion();
        try {
            
            ResultSet r;
            r = con.s.executeQuery ("SELECT t_novedades.ID_EMPRESA,t_empresas.NOMBRE_EMPRESA,COUNT(t_novedades.ID_EMPRESA) AS NUMERO_EMPLEADOS\n" +
                                    "FROM\n" +
                                    "t_novedades\n" +
                                    "INNER JOIN t_obra \n" +
                                    "	ON (t_novedades.ID_OBRA = t_obra.ID_OBRA)\n" +
                                    "INNER JOIN t_municipios \n" +
                                    "	ON (t_obra.ID_MUN_OBRA = t_municipios.ID_MUN)\n" +
                                    "INNER JOIN t_departamentos \n" +
                                    "	ON (t_municipios.ID_DEP = t_departamentos.ID_DEP)\n" +
                                    "INNER JOIN t_afp \n" +
                                    "	ON (t_novedades.ID_AFP = t_afp.ID_AFP)\n" +
                                    "INNER JOIN t_eps \n" +
                                    "	ON (t_novedades.ID_EPS = t_eps.ID_EPS)\n" +
                                    "INNER JOIN t_empresas \n" +
                                    "        ON (t_novedades.ID_EMPRESA = t_empresas.ID_EMPRESA)\n" +
                                    "INNER JOIN t_empleados \n" +
                                    "        ON (t_novedades.ID_EMPLEADO = t_empleados.ID_EMP)\n" +
                                    "INNER JOIN t_tipo_novedad \n" +
                                    "        ON (t_novedades.ID_TIPO = t_tipo_novedad.ID_TIPO)\n" +
                                    "WHERE\n" +
                                    "t_novedades.ID_TIPO IN (1,2,4,6)\n" +
                                    "AND ((t_novedades.`FECHA_INGRESO` <= '"+new SimpleDateFormat("yyyy-MM-dd").format(fecha)+"' AND t_novedades.`FECHA_RETIRO` = '1900-01-01')\n" +
                                    "	OR ( t_novedades.`FECHA_INGRESO` <= '"+new SimpleDateFormat("yyyy-MM-dd").format(fecha)+"' AND t_novedades.`FECHA_RETIRO` >= '"+new SimpleDateFormat("yyyy-MM-dd").format(fecha)+"'))\n" +
                                    "GROUP BY t_novedades.ID_EMPRESA");
            Wait_rep.bar.setMaximum(getRowCount(r));
            while(r.next()){
                ClassLoader cl= this.getClass().getClassLoader();
                InputStream fis = (cl.getResourceAsStream("Reportes/Personal_activo_empresa.jasper"));
                JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
                Map par = new HashMap();
                par.put("ID_EMPRESA", r.getString("ID_EMPRESA"));
                par.put("Fecha",fecha);
                JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
                if (formato.equals(".pdf")) {
                    Wait_rep.progreso_1.setText(r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.pdf");
                    JasperExportManager.exportReportToPdfFile(jprint,path+"\\"+r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.pdf");
                }
                if (formato.equals(".xls")) {
                    Wait_rep.progreso_1.setText(r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.xlsx");
                    JRXlsxExporter exporterXLS = new JRXlsxExporter();
                    Map<String, String> dateFormats = new HashMap<String, String>();
                    dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                    SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                    exporterXLS.setExporterInput(new SimpleExporterInput(jprint));
                    exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path+"\\"+r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.xlsx"));       
                    repConfig.setWrapText(Boolean.TRUE);//
                    repConfig.setOnePagePerSheet(Boolean.FALSE);
                    repConfig.setDetectCellType(Boolean.TRUE);
                    repConfig.setWhitePageBackground(Boolean.TRUE);
                    repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                    repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                    repConfig.setFormatPatternsMap(dateFormats);
                    repConfig.setCollapseRowSpan(Boolean.TRUE);//
                    exporterXLS.setConfiguration(repConfig);
                    exporterXLS.exportReport();
                    
                }
                Wait_rep.bar.setValue(r.getRow());
            }
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"El reporte se ha generado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
            con.cerrar();
            s_p_a_s.dialog.dispose();
            
        } catch (SQLException | JRException | HeadlessException e) {
            con.cerrar();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void personal_activo_semanal_externo (String path, String formato, Date fecha){
        Wait_rep.btn_aceptar.setEnabled(false);
        s_p_a_s_e.dialog.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Wait_rep.mensaje.setText("Generando reportes de personal activo semanal externo");
        path=path+"\\PERSONAL_ACTIVO_SEMANAL_EXTERNO_"+new SimpleDateFormat("yyyy-MM-dd").format(fecha);
        File folder = new File(path);
        folder.mkdirs();
        List<InputStream> list = new ArrayList<>();
        XSSFWorkbook wb=null;
        if (formato.equals(".xls")) {
            wb = new XSSFWorkbook(); // Carga todo el archivo
        }
        Conexion con = new Conexion();
        con.conexion();
        try {
            
            ResultSet r;
            r = con.s.executeQuery ("SELECT t_novedades.ID_EMPRESA,t_empresas.NOMBRE_EMPRESA,COUNT(t_novedades.ID_EMPRESA) AS NUMERO_EMPLEADOS\n" +
                                    "FROM\n" +
                                    "t_novedades\n" +
                                    "INNER JOIN t_obra \n" +
                                    "	ON (t_novedades.ID_OBRA = t_obra.ID_OBRA)\n" +
                                    "INNER JOIN t_municipios \n" +
                                    "	ON (t_obra.ID_MUN_OBRA = t_municipios.ID_MUN)\n" +
                                    "INNER JOIN t_departamentos \n" +
                                    "	ON (t_municipios.ID_DEP = t_departamentos.ID_DEP)\n" +
                                    "INNER JOIN t_afp \n" +
                                    "	ON (t_novedades.ID_AFP = t_afp.ID_AFP)\n" +
                                    "INNER JOIN t_eps \n" +
                                    "	ON (t_novedades.ID_EPS = t_eps.ID_EPS)\n" +
                                    "INNER JOIN t_empresas \n" +
                                    "        ON (t_novedades.ID_EMPRESA = t_empresas.ID_EMPRESA)\n" +
                                    "INNER JOIN t_empleados \n" +
                                    "        ON (t_novedades.ID_EMPLEADO = t_empleados.ID_EMP)\n" +
                                    "INNER JOIN t_tipo_novedad \n" +
                                    "        ON (t_novedades.ID_TIPO = t_tipo_novedad.ID_TIPO)\n" +
                                    "WHERE\n" +
                                    "t_novedades.ID_TIPO IN (5,7)\n" +
                                    "AND ((t_novedades.`FECHA_INGRESO` <= '"+new SimpleDateFormat("yyyy-MM-dd").format(fecha)+"' AND t_novedades.`FECHA_RETIRO` = '1900-01-01')\n" +
                                    "	OR ( t_novedades.`FECHA_INGRESO` <= '"+new SimpleDateFormat("yyyy-MM-dd").format(fecha)+"' AND t_novedades.`FECHA_RETIRO` >= '"+new SimpleDateFormat("yyyy-MM-dd").format(fecha)+"'))\n" +
                                    "GROUP BY t_novedades.ID_EMPRESA");
            Wait_rep.bar.setMaximum(getRowCount(r));
            while(r.next()){
                ClassLoader cl= this.getClass().getClassLoader();
                InputStream fis = (cl.getResourceAsStream("Reportes/Personal_activo_empresa_externo.jasper"));
                JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
                Map par = new HashMap();
                par.put("ID_EMPRESA", r.getString("ID_EMPRESA"));
                par.put("Fecha",fecha);
                JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
                if (formato.equals(".pdf")) {
                    Wait_rep.progreso_1.setText(r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.pdf");
                    JasperExportManager.exportReportToPdfFile(jprint,path+"\\"+r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.pdf");
                }
                if (formato.equals(".xls")) {
                    Wait_rep.progreso_1.setText(r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.xlsx");
                    JRXlsxExporter exporterXLS = new JRXlsxExporter();
                    Map<String, String> dateFormats = new HashMap<String, String>();
                    dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                    SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                    exporterXLS.setExporterInput(new SimpleExporterInput(jprint));
                    exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path+"\\"+r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.xlsx"));       
                    repConfig.setWrapText(Boolean.TRUE);//
                    repConfig.setOnePagePerSheet(Boolean.FALSE);
                    repConfig.setDetectCellType(Boolean.TRUE);
                    repConfig.setWhitePageBackground(Boolean.TRUE);
                    repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                    repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                    repConfig.setFormatPatternsMap(dateFormats);
                    repConfig.setCollapseRowSpan(Boolean.TRUE);//
                    exporterXLS.setConfiguration(repConfig);
                    exporterXLS.exportReport();
                    
                }
                Wait_rep.bar.setValue(r.getRow());
            }
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"El reporte se ha generado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
            con.cerrar();
            s_p_a_s_e.dialog.dispose();
            
        } catch (SQLException | JRException | HeadlessException e) {
            con.cerrar();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void precalculo_seguridad_ciudad (String path, String formato, String fechaini, String fechafin, int FIC_HC, boolean def, long id){
        //System.out.println("Grupo: "+g);
        Wait_rep.btn_aceptar.setEnabled(false);
        s_p_s.dialog.setCursor(new Cursor(Cursor.WAIT_CURSOR)); 
        Wait_rep.mensaje.setText("Generando reportes de precalculo de seguridad");
        path=path+"\\PRECALCULO_SEGURIDAD_CIUDAD_"+fechaini+"_A_"+fechafin;
        File folder = new File(path);
        folder.mkdirs();
        float salario_min=0;
        Conexion con = new Conexion();
        Conexion con1 = new Conexion();
        Conexion con2 = new Conexion();
        con.conexion();
        con1.conexion();
        con2.conexion();
        try {
            ResultSet r, r1, r2;
            r = con.s.executeQuery("SELECT*\n" +
                                    "FROM\n" +
                                    "    t_parametros\n" +
                                    "WHERE	\n" +
                                    "    NOMBRE_PAR='SALARIO_MINIMO'");
            if (r.next()) {
                salario_min=new Float(r.getString("VALOR_PAR"));
            }
            r = con.s.executeQuery ("SELECT t_novedades.ID_EMPRESA, NOMBRE_EMPRESA\n" +
                                    "FROM\n" +
                                    "t_novedades\n" +
                                    "INNER JOIN t_obra \n" +
                                    "	ON (t_novedades.ID_OBRA = t_obra.ID_OBRA)\n" +
                                    "INNER JOIN t_municipios \n" +
                                    "	ON (t_obra.ID_MUN_OBRA = t_municipios.ID_MUN)\n" +
                                    "INNER JOIN t_grupo_empresa \n" +
                                    "	ON (t_obra.ID_GRUPO = t_grupo_empresa.ID_GRUPO)\n" +
                                    "INNER JOIN t_departamentos \n" +
                                    "	ON (t_municipios.ID_DEP = t_departamentos.ID_DEP)\n" +
                                    "INNER JOIN t_afp \n" +
                                    "	ON (t_novedades.ID_AFP = t_afp.ID_AFP)\n" +
                                    "INNER JOIN t_eps \n" +
                                    "	ON (t_novedades.ID_EPS = t_eps.ID_EPS)\n" +
                                    "INNER JOIN t_empresas \n" +
                                    "	ON (t_novedades.ID_EMPRESA = t_empresas.ID_EMPRESA)\n" +
                                    "INNER JOIN t_empleados \n" +
                                    "	ON (t_novedades.ID_EMPLEADO = t_empleados.ID_EMP)\n" +
                                    "INNER JOIN t_tipo_novedad \n" +
                                    "	ON (t_novedades.ID_TIPO = t_tipo_novedad.ID_TIPO)\n" +
                                    "WHERE\n" +
                                    "t_novedades.ID_TIPO IN (1,2,4,6)\n" +
                                    "AND ((t_novedades.`FECHA_INGRESO` <= '"+fechafin+"' AND t_novedades.`FECHA_RETIRO` = '1900-01-01')\n" +
                                    "	OR ( t_novedades.`FECHA_INGRESO` <= '"+fechafin+"' AND t_novedades.`FECHA_RETIRO` >= '"+fechaini+"'))\n" +
                                    "GROUP BY t_novedades.ID_EMPRESA");
            Wait_rep.bar.setMaximum(getRowCount(r)+1);
            while (r.next()) {
                ScriptletsPrecalculo script_p = new ScriptletsPrecalculo();
                String id_empresa = r.getString("ID_EMPRESA");
                Date f_ini = new SimpleDateFormat("yyyy-MM-dd").parse(fechaini);
                Date f_fin = new SimpleDateFormat("yyyy-MM-dd").parse(fechafin);
                //////--------------------------ALMACENA PRECALCULO DEFINITIVO-------------------------///////
                if (def) {
                    r1 = con1.s.executeQuery("SELECT * FROM `t_det_precalculos` WHERE ID_EMPRESA = '"+id_empresa+"' AND ID_PREC = "+id);
                    if (r1.next()) {
                        con2.s.executeUpdate("UPDATE `t_det_precalculos` SET `FIC`="+script_p.FIC(f_ini, f_fin, id_empresa, salario_min, Main.host, Main.bd, Main.usu, Main.cont, FIC_HC)+",`TOTAL`="+script_p.calc_TOTAL_SEG(f_ini, f_fin, id_empresa, "", Main.host, Main.bd, Main.usu, Main.cont)+",F_REGISTRO='"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"',ID_USUARIO = '"+Main.id_usuario+"' WHERE ID_EMPRESA = '"+id_empresa+"' AND ID_PREC = = "+id);
                    }else{
                        con2.s.executeUpdate("INSERT INTO `t_det_precalculos`(ID_EMPRESA , `FIC`, `TOTAL`, ID_PREC) VALUES ('"+id_empresa+"', "+script_p.FIC(f_ini, f_fin, id_empresa, salario_min, Main.host, Main.bd, Main.usu, Main.cont, FIC_HC)+", "+script_p.calc_TOTAL_SEG(f_ini, f_fin, id_empresa, "", Main.host, Main.bd, Main.usu, Main.cont)+","+id+")");
                    }
                }
                //////--------------------------REPORTE PRECALCULO-------------------------------------///////
                ClassLoader cl= this.getClass().getClassLoader();
                InputStream fis = (cl.getResourceAsStream("Reportes/Precalculo_seguridad_ciudad.jasper"));
                JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
                Map par = new HashMap();
                //System.out.println("nit: "+);
                par.put("ID_EMPRESA",id_empresa);
                par.put("F_INICIAL",f_ini);
                par.put("F_FINAL",f_fin);
                par.put("SALARIO_MIN",salario_min);//HALF_COMPLETE
                par.put("HALF_COMPLETE",FIC_HC);
                JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
                if (formato.equals(".pdf")) {
                    Wait_rep.progreso_1.setText(id_empresa+"-"+r.getString("NOMBRE_EMPRESA")+"_.pdf");
                    JasperExportManager.exportReportToPdfFile(jprint,path+"\\"+id_empresa+"-"+r.getString("NOMBRE_EMPRESA")+"_.pdf");
                }
                if (formato.equals(".xls")) {
                    Wait_rep.progreso_1.setText(id_empresa+"-"+r.getString("NOMBRE_EMPRESA")+"_.xlsx");
                    JRXlsxExporter exporterXLS = new JRXlsxExporter();
                    Map<String, String> dateFormats = new HashMap<String, String>();
                    dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                    SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                    exporterXLS.setExporterInput(new SimpleExporterInput(jprint));
                    exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path+"\\"+id_empresa+"-"+r.getString("NOMBRE_EMPRESA")+"_.xlsx"));       
                    repConfig.setWrapText(Boolean.TRUE);//
                    repConfig.setOnePagePerSheet(Boolean.FALSE);
                    repConfig.setDetectCellType(Boolean.TRUE);
                    repConfig.setWhitePageBackground(Boolean.TRUE);
                    repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                    repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                    repConfig.setFormatPatternsMap(dateFormats);
                    repConfig.setCollapseRowSpan(Boolean.TRUE);//
                    exporterXLS.setConfiguration(repConfig);
                    exporterXLS.exportReport();
                }
                Wait_rep.bar.setValue(r.getRow());
            }
            //con.cerrar();
            //////--------------------------REPORTE PRECALCULO CONSOLIDADO POR CIUDAD--------------------------///////
            ClassLoader cl1= this.getClass().getClassLoader();
            InputStream fis1 = (cl1.getResourceAsStream("Reportes/Descuentos.jasper"));
            JasperReport rep1 = (JasperReport) JRLoader.loadObject(fis1);
            Map par1 = new HashMap();
            par1.put("F_INICIAL",new SimpleDateFormat("yyyy-MM-dd").parse(fechaini));
            par1.put("F_FINAL",new SimpleDateFormat("yyyy-MM-dd").parse(fechafin));
            par1.put("SALARIO_MIN",salario_min);
            par1.put("host",Main.host);
            par1.put("db",Main.bd);
            par1.put("usu",Main.usu);
            par1.put("cont",Main.cont);
            par1.put("HALF_COMPLETE",FIC_HC);
            JasperPrint jprint1 = JasperFillManager.fillReport(rep1,par1,con.c);
            if (formato.equals(".pdf")) {
                Wait_rep.progreso_1.setText("CONSOLIDADO_SEGURIDAD_SOCIAL_CIUDAD.pdf");
                JasperExportManager.exportReportToPdfFile(jprint1,path+"\\CONSOLIDADO_SEGURIDAD_SOCIAL_CIUDAD.pdf");
            }
            if (formato.equals(".xls")) {
                Wait_rep.progreso_1.setText("CONSOLIDADO_SEGURIDAD_SOCIAL_CIUDAD.xls");
                JRXlsxExporter exporterXLS = new JRXlsxExporter();
                Map<String, String> dateFormats = new HashMap<String, String>();
                dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                exporterXLS.setExporterInput(new SimpleExporterInput(jprint1));
                exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path+"\\CONSOLIDADO_SEGURIDAD_SOCIAL_CIUDAD.xlsx"));       
                repConfig.setWrapText(Boolean.TRUE);//
                repConfig.setOnePagePerSheet(Boolean.FALSE);
                repConfig.setDetectCellType(Boolean.TRUE);
                repConfig.setWhitePageBackground(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                repConfig.setFormatPatternsMap(dateFormats);
                repConfig.setCollapseRowSpan(Boolean.TRUE);//
                exporterXLS.setConfiguration(repConfig);
                exporterXLS.exportReport();
            }
            con.cerrar();
            con1.cerrar();
            con2.cerrar();
            Wait_rep.bar.setValue(Wait_rep.bar.getMaximum());
            Toolkit.getDefaultToolkit().beep();
            if (def) {
                JOptionPane.showMessageDialog(null,"Los reportes se han generado correctamente y se han almacenado como definitivos.","Información",JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null,"Los reportes se han generado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
            }
            
            s_p_s.dialog.dispose();
        } catch (JRException | ParseException | SQLException e) {
            con.cerrar();
            con1.cerrar();
            con2.cerrar();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    
    }
    public void precalculo_seguridad_obra (String path, String formato, String fechaini, String fechafin, Integer FIC_HC, String g){
        System.out.println("Grupo: "+g);
        Wait_rep.btn_aceptar.setEnabled(false);
        s_p_s.dialog.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Wait_rep.mensaje.setText("Generando reportes de precalculo de seguridad");
        path=path+"\\PRECALCULO_SEGURIDAD_OBRA_"+fechaini+"_A_"+fechafin;
        File folder = new File(path);
        folder.mkdirs();
        float salario_min=0;
        Conexion con = new Conexion();
        con.conexion();
        try {
            ResultSet r, r1;
            r1 = con.s.executeQuery("SELECT*\n" +
                                    "FROM\n" +
                                    "    t_parametros\n" +
                                    "WHERE	\n" +
                                    "    NOMBRE_PAR='SALARIO_MINIMO'");
            if (r1.next()) {
                salario_min=new Float(r1.getString("VALOR_PAR"));
            }
            r = con.s.executeQuery ("SELECT t_novedades.ID_EMPRESA, NOMBRE_EMPRESA\n" +
                                    "FROM\n" +
                                    "t_novedades\n" +
                                    "INNER JOIN t_obra \n" +
                                    "	ON (t_novedades.ID_OBRA = t_obra.ID_OBRA)\n" +
                                    "INNER JOIN t_municipios \n" +
                                    "	ON (t_obra.ID_MUN_OBRA = t_municipios.ID_MUN)\n" +
                                    "INNER JOIN t_grupo_empresa \n" +
                                    "	ON (t_obra.ID_GRUPO = t_grupo_empresa.ID_GRUPO)\n" +
                                    "INNER JOIN t_departamentos \n" +
                                    "	ON (t_municipios.ID_DEP = t_departamentos.ID_DEP)\n" +
                                    "INNER JOIN t_afp \n" +
                                    "	ON (t_novedades.ID_AFP = t_afp.ID_AFP)\n" +
                                    "INNER JOIN t_eps \n" +
                                    "	ON (t_novedades.ID_EPS = t_eps.ID_EPS)\n" +
                                    "INNER JOIN t_empresas \n" +
                                    "	ON (t_novedades.ID_EMPRESA = t_empresas.ID_EMPRESA)\n" +
                                    "INNER JOIN t_empleados \n" +
                                    "	ON (t_novedades.ID_EMPLEADO = t_empleados.ID_EMP)\n" +
                                    "INNER JOIN t_tipo_novedad \n" +
                                    "	ON (t_novedades.ID_TIPO = t_tipo_novedad.ID_TIPO)\n" +
                                    "WHERE\n" +
                                    "t_novedades.ID_TIPO IN (1,2,4,6)\n" +
                                    "AND t_grupo_empresa.NOMBRE_GRUPO = '"+g+"'\n" +
                                    "AND ((t_novedades.`FECHA_INGRESO` <= '"+fechafin+"' AND t_novedades.`FECHA_RETIRO` = '1900-01-01')\n" +
                                    "	OR ( t_novedades.`FECHA_INGRESO` <= '"+fechafin+"' AND t_novedades.`FECHA_RETIRO` >= '"+fechaini+"'))\n" +
                                    "GROUP BY t_novedades.ID_EMPRESA");
            Wait_rep.bar.setMaximum(getRowCount(r)+2);
            while (r.next()) {
                //////--------------------------REPORTE PRECALCULO-------------------------------------///////
                ClassLoader cl= this.getClass().getClassLoader();
                InputStream fis = (cl.getResourceAsStream("Reportes/Precalculo_seguridad_obra.jasper"));
                JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
                Map par = new HashMap();
                par.put("ID_EMPRESA",r.getString("ID_EMPRESA"));
                par.put("F_INICIAL",new SimpleDateFormat("yyyy-MM-dd").parse(fechaini));
                par.put("F_FINAL",new SimpleDateFormat("yyyy-MM-dd").parse(fechafin));
                par.put("SALARIO_MIN",salario_min);//HALF_COMPLETE
                par.put("HALF_COMPLETE",FIC_HC);
                par.put("grupo",g);
                JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
                if (formato.equals(".pdf")) {
                    Wait_rep.progreso_1.setText(r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.pdf");
                    JasperExportManager.exportReportToPdfFile(jprint,path+"\\"+r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.pdf");
                }
                if (formato.equals(".xls")) {
                    Wait_rep.progreso_1.setText(r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.xlsx");
                    JRXlsxExporter exporterXLS = new JRXlsxExporter();
                    Map<String, String> dateFormats = new HashMap<String, String>();
                    dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                    SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                    exporterXLS.setExporterInput(new SimpleExporterInput(jprint));
                    exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path+"\\"+r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.xlsx"));       
                    repConfig.setWrapText(Boolean.TRUE);//
                    repConfig.setOnePagePerSheet(Boolean.FALSE);
                    repConfig.setDetectCellType(Boolean.TRUE);
                    repConfig.setWhitePageBackground(Boolean.TRUE);
                    repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                    repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                    repConfig.setFormatPatternsMap(dateFormats);
                    repConfig.setCollapseRowSpan(Boolean.TRUE);//
                    exporterXLS.setConfiguration(repConfig);
                    exporterXLS.exportReport();
                }
                Wait_rep.bar.setValue(r.getRow());
            }
            //////--------------------------REPORTE PRECALCULO CONSOLIDADO POR OBRA----------------------------///////
            ClassLoader cl2= this.getClass().getClassLoader();
            InputStream fis2 = (cl2.getResourceAsStream("Reportes/Descuentos_por_obra.jasper"));
            JasperReport rep2 = (JasperReport) JRLoader.loadObject(fis2);
            Map par2 = new HashMap();
            par2.put("F_INICIAL",new SimpleDateFormat("yyyy-MM-dd").parse(fechaini));
            par2.put("F_FINAL",new SimpleDateFormat("yyyy-MM-dd").parse(fechafin));
            par2.put("SALARIO_MIN",salario_min);
            par2.put("host",Main.host);
            par2.put("db",Main.bd);
            par2.put("usu",Main.usu);
            par2.put("cont",Main.cont);
            par2.put("HALF_COMPLETE",FIC_HC);
            par2.put("grupo",g);
            JasperPrint jprint2 = JasperFillManager.fillReport(rep2,par2,con.c);
            if (formato.equals(".pdf")) {
                Wait_rep.progreso_1.setText("CONSOLIDADO_SEGURIDAD_SOCIAL_OBRA.pdf");
                JasperExportManager.exportReportToPdfFile(jprint2,path+"\\CONSOLIDADO_SEGURIDAD_SOCIAL_OBRA.pdf");
            }
            if (formato.equals(".xls")) {
                Wait_rep.progreso_1.setText("CONSOLIDADO_SEGURIDAD_SOCIAL_OBRA.xls");
                JRXlsxExporter exporterXLS = new JRXlsxExporter();
                Map<String, String> dateFormats = new HashMap<String, String>();
                dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                exporterXLS.setExporterInput(new SimpleExporterInput(jprint2));
                exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path+"\\CONSOLIDADO_SEGURIDAD_SOCIAL_OBRA.xlsx"));       
                repConfig.setWrapText(Boolean.TRUE);//
                repConfig.setOnePagePerSheet(Boolean.FALSE);
                repConfig.setDetectCellType(Boolean.TRUE);
                repConfig.setWhitePageBackground(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                repConfig.setFormatPatternsMap(dateFormats);
                repConfig.setCollapseRowSpan(Boolean.TRUE);//
                exporterXLS.setConfiguration(repConfig);
                exporterXLS.exportReport();
            }
            con.cerrar();
            Wait_rep.bar.setValue(Wait_rep.bar.getMaximum());
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"Los reportes se han generado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
            s_p_s.dialog.dispose();
        } catch (JRException | ParseException | SQLException e) {
            con.cerrar();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    
    }
    public void precalculo_seguridad_empresa_ciudad(String path, String formato, String fechaini, String fechafin, String empresa, Integer FIC_HC){
        Wait_rep.btn_aceptar.setEnabled(false);
        s_p_s_e_c.dialog.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Wait_rep.mensaje.setText("Generando reportes de precalculo de seguridad empresa");
        Wait_rep.bar.setMaximum(1);
        float salario_min=0;
        String nit="";
        Conexion con = new Conexion();
        con.conexion();
        try {
            ResultSet r, r1;
            r1 = con.s.executeQuery("SELECT*\n" +
                                    "FROM\n" +
                                    "    t_parametros\n" +
                                    "WHERE	\n" +
                                    "    NOMBRE_PAR='SALARIO_MINIMO'");
            if (r1.next()) {
                salario_min=new Float(r1.getString("VALOR_PAR"));
            }
            r1 = con.s.executeQuery("SELECT * FROM t_empresas WHERE NOMBRE_EMPRESA='"+empresa+"'");
            if (r1.next()) {
                nit=r1.getString("ID_EMPRESA");
            }
            ClassLoader cl= this.getClass().getClassLoader();
            InputStream fis = (cl.getResourceAsStream("Reportes/Precalculo_seguridad_empresa_ciudad.jasper"));
            JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
            Map par = new HashMap();
            par.put("ID_EMPRESA",nit);
            par.put("F_INICIAL",new SimpleDateFormat("yyyy-MM-dd").parse(fechaini));
            par.put("F_FINAL",new SimpleDateFormat("yyyy-MM-dd").parse(fechafin));
            par.put("SALARIO_MIN",salario_min);
            par.put("HALF_COMPLETE",FIC_HC);
            JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
            Wait_rep.progreso_1.setText(s_p_s_e_c.empresa);
            Wait_rep.bar.setValue(1);
            if (formato.equals(".pdf")) {
                JasperExportManager.exportReportToPdfFile(jprint,path);
            }
            if (formato.equals(".xls")) {
                JRXlsxExporter exporterXLS = new JRXlsxExporter();
                Map<String, String> dateFormats = new HashMap<String, String>();
                dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                exporterXLS.setExporterInput(new SimpleExporterInput(jprint));
                exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path));       
                repConfig.setWrapText(Boolean.TRUE);//
                repConfig.setOnePagePerSheet(Boolean.FALSE);
                repConfig.setDetectCellType(Boolean.TRUE);
                repConfig.setWhitePageBackground(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                repConfig.setFormatPatternsMap(dateFormats);
                repConfig.setCollapseRowSpan(Boolean.TRUE);//
                exporterXLS.setConfiguration(repConfig);
                exporterXLS.exportReport();
            
            }
            con.cerrar();
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"El reporte se ha generado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
            s_p_s_e_c.dialog.dispose();
        } catch (SQLException | NumberFormatException | JRException | ParseException e) {
            con.cerrar();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void precalculo_seguridad_empresa_obra(String path, String formato, String fechaini, String fechafin, String empresa, Integer FIC_HC, String g){
        Wait_rep.btn_aceptar.setEnabled(false);
        s_p_s_e_o.dialog.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Wait_rep.mensaje.setText("Generando reportes de precalculo de seguridad empresa");
        Wait_rep.bar.setMaximum(1);
        float salario_min=0;
        String nit="";
        Conexion con = new Conexion();
        con.conexion();
        try {
            ResultSet r, r1;
            r1 = con.s.executeQuery("SELECT*\n" +
                                    "FROM\n" +
                                    "    t_parametros\n" +
                                    "WHERE	\n" +
                                    "    NOMBRE_PAR='SALARIO_MINIMO'");
            if (r1.next()) {
                salario_min=new Float(r1.getString("VALOR_PAR"));
            }
            r1 = con.s.executeQuery("SELECT * FROM t_empresas WHERE NOMBRE_EMPRESA='"+empresa+"'");
            if (r1.next()) {
                nit=r1.getString("ID_EMPRESA");
            }
            ClassLoader cl= this.getClass().getClassLoader();
            InputStream fis = (cl.getResourceAsStream("Reportes/Precalculo_seguridad_empresa_obra.jasper"));
            JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
            Map par = new HashMap();
            par.put("ID_EMPRESA",nit);
            par.put("F_INICIAL",new SimpleDateFormat("yyyy-MM-dd").parse(fechaini));
            par.put("F_FINAL",new SimpleDateFormat("yyyy-MM-dd").parse(fechafin));
            par.put("SALARIO_MIN",salario_min);
            par.put("HALF_COMPLETE",FIC_HC);
            par.put("grupo",g);
            JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
            Wait_rep.progreso_1.setText(s_p_s_e_o.empresa);
            Wait_rep.bar.setValue(1);
            if (formato.equals(".pdf")) {
                JasperExportManager.exportReportToPdfFile(jprint,path);
            }
            if (formato.equals(".xls")) {
                JRXlsxExporter exporterXLS = new JRXlsxExporter();
                Map<String, String> dateFormats = new HashMap<String, String>();
                dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                exporterXLS.setExporterInput(new SimpleExporterInput(jprint));
                exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path));       
                repConfig.setWrapText(Boolean.TRUE);//
                repConfig.setOnePagePerSheet(Boolean.FALSE);
                repConfig.setDetectCellType(Boolean.TRUE);
                repConfig.setWhitePageBackground(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                repConfig.setFormatPatternsMap(dateFormats);
                repConfig.setCollapseRowSpan(Boolean.TRUE);//
                exporterXLS.setConfiguration(repConfig);
                exporterXLS.exportReport();
            
            }
            con.cerrar();
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"El reporte se ha generado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
            s_p_s_e_o.dialog.dispose();
        } catch (SQLException | NumberFormatException | JRException | ParseException e) {
            con.cerrar();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void biometrico(String path, String formato, Date fechaini, Date fechafin, String empleador, String tipo){
            Wait_rep.btn_aceptar.setEnabled(false);
            s_b.dialog.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            Wait_rep.mensaje.setText("Generando plantillas de biometrico");
            Wait_rep.bar.setMaximum(1);
            System.out.println(path);
            System.out.println(fechaini);
            System.out.println(fechafin);
            System.out.println(empleador);
            System.out.println(tipo);
            Conexion con = new Conexion();
            con.conexion();
        try {
            ClassLoader cl= this.getClass().getClassLoader();
            InputStream fis = null;
            if (empleador.equals("")) {
                if (tipo.equals("INGRESO")) {
                    fis = (cl.getResourceAsStream("Reportes/Biometrico_ingreso_all.jasper"));
                }else{
                    if (tipo.equals("RETIRO")) {
                        fis = (cl.getResourceAsStream("Reportes/Biometrico_retiro_all.jasper"));
                    }
                }
            }else{
                if (tipo.equals("INGRESO")) {
                    fis = (cl.getResourceAsStream("Reportes/Biometrico_ingreso.jasper"));
                }else{
                    if (tipo.equals("RETIRO")) {
                        fis = (cl.getResourceAsStream("Reportes/Biometrico_retiro.jasper"));
                    }
                }
            }
            JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
            Map par = new HashMap();
            par.put("F_INICIAL",fechaini);
            par.put("F_FINAL",fechafin);
            par.put("TIPO",tipo);
            par.put("EMPRESA",empleador);
            JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
            con.cerrar();
            Wait_rep.progreso_1.setText(s_b.file);
            if (formato.equals(".pdf")) {
                JasperExportManager.exportReportToPdfFile(jprint,path);
            }
            if (formato.equals(".xls")) {
                JRXlsxExporter exporterXLS = new JRXlsxExporter();
                Map<String, String> dateFormats = new HashMap<String, String>();
                dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                exporterXLS.setExporterInput(new SimpleExporterInput(jprint));
                exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path));       
                repConfig.setWrapText(Boolean.TRUE);//
                repConfig.setOnePagePerSheet(Boolean.FALSE);
                repConfig.setDetectCellType(Boolean.TRUE);
                repConfig.setWhitePageBackground(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                repConfig.setFormatPatternsMap(dateFormats);
                repConfig.setCollapseRowSpan(Boolean.TRUE);//
                exporterXLS.setConfiguration(repConfig);
                exporterXLS.exportReport();
            }
            Wait_rep.bar.setValue(1);
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"La plantilla se ha generado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
            s_b.dialog.dispose();
        } catch (JRException | HeadlessException e) {
            con.cerrar();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        } 
    }
    public void asistencias_empresas(String path, String formato, String fechaini, String fechafin){
        Wait_rep.btn_aceptar.setEnabled(false);
        s_a.dialog.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Wait_rep.mensaje.setText("Generando reportes de asistencia por empresas");
        path=path+"\\ASISTENCIAS_EMPRESAS_"+fechaini+"_A_"+fechafin;
        File folder = new File(path);
        folder.mkdirs();
        Conexion con = new Conexion();
        con.conexion();
        try {
            ResultSet r;
            r = con.s.executeQuery("SELECT *\n" +
                                    "FROM\n" +
                                    "    t_asistencias\n" +
                                    "    INNER JOIN t_novedades \n" +
                                    "        ON (t_asistencias.ID_EMPLEADO = t_novedades.ID_EMPLEADO) AND (t_asistencias.ID_EMPRESA = t_novedades.ID_EMPRESA) AND (t_asistencias.F_INGRESO = t_novedades.FECHA_INGRESO) AND (t_asistencias.ID_TIPO = t_novedades.ID_TIPO) AND (t_asistencias.F_RETIRO = t_novedades.FECHA_RETIRO)\n" +
                                    "    INNER JOIN t_obra \n" +
                                    "        ON (t_novedades.ID_OBRA = t_obra.ID_OBRA)\n" +
                                    "    INNER JOIN t_empresas \n" +
                                    "        ON (t_novedades.ID_EMPRESA = t_empresas.ID_EMPRESA)\n" +
                                    "    WHERE ((t_asistencias.F_INGRESO <= '"+fechafin+"' AND t_asistencias.F_RETIRO = '1900-01-01')\n" +
                                    "    OR (t_asistencias.F_INGRESO <= '"+fechafin+"' AND t_asistencias.F_RETIRO >= '"+fechaini+"'))\n" +
                                    "    GROUP BY t_empresas.ID_EMPRESA");
            Wait_rep.bar.setMaximum(getRowCount(r)+1);
            while(r.next()){
                //////--------------------------REPORTE ASISTENCIAS POR EMPRESA-------------------------------------///////
                ClassLoader cl= this.getClass().getClassLoader();
                InputStream fis = (cl.getResourceAsStream("Reportes/Asistencias_Empleador.jasper"));
                JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
                Map par = new HashMap();
                par.put("ID_EMPRESA",r.getString("ID_EMPRESA"));
                par.put("F_INICIO",new SimpleDateFormat("yyyy-MM-dd").parse(fechaini));
                par.put("F_FINAL",new SimpleDateFormat("yyyy-MM-dd").parse(fechafin));
                par.put("Host",Main.host);
                par.put("DB",Main.bd);
                par.put("Usu",Main.usu);
                par.put("Cont",Main.cont);
                JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
                if (formato.equals(".pdf")) {
                    Wait_rep.progreso_1.setText(r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.pdf");
                    JasperExportManager.exportReportToPdfFile(jprint,path+"\\"+r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.pdf");
                }
                if (formato.equals(".xls")) {
                    Wait_rep.progreso_1.setText(r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.xlsx");
                    JRXlsxExporter exporterXLS = new JRXlsxExporter();
                    Map<String, String> dateFormats = new HashMap<String, String>();
                    dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                    SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                    exporterXLS.setExporterInput(new SimpleExporterInput(jprint));
                    exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path+"\\"+r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.xlsx"));       
                    repConfig.setWrapText(Boolean.TRUE);//
                    repConfig.setOnePagePerSheet(Boolean.FALSE);
                    repConfig.setDetectCellType(Boolean.TRUE);
                    repConfig.setWhitePageBackground(Boolean.TRUE);
                    repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                    repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                    repConfig.setFormatPatternsMap(dateFormats);
                    repConfig.setCollapseRowSpan(Boolean.TRUE);//
                    exporterXLS.setConfiguration(repConfig);
                    exporterXLS.exportReport();
                }
                Wait_rep.bar.setValue(r.getRow());
            }
            Wait_rep.bar.setValue(Wait_rep.bar.getMaximum());
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"Los reportes se han generado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
            s_a.dialog.dispose();
            con.cerrar();
        }catch (JRException | ParseException | SQLException e) {
            con.cerrar();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        } 
    }
    public void asistencias_empresas_empresa(String path, String formato, String fechaini, String fechafin, String emp){
        Wait_rep.btn_aceptar.setEnabled(false);
        s_a_e.dialog.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Wait_rep.mensaje.setText("Generando reporte de asistencia por empresa");
        Wait_rep.bar.setMaximum(1);
        Conexion con = new Conexion();
        con.conexion();
        try {   
            String id_emp="";
            ResultSet r;
            r = con.s.executeQuery("SELECT * FROM T_EMPRESAS WHERE NOMBRE_EMPRESA = '"+emp+"'");
            if (r.next()) {
                id_emp=r.getString("ID_EMPRESA");
            }
            //System.out.println("ID EMPRESA: "+id_emp);
            //////--------------------------REPORTE ASISTENCIAS POR EMPRESA-------------------------------------///////
            ClassLoader cl= this.getClass().getClassLoader();
            InputStream fis = (cl.getResourceAsStream("Reportes/Asistencias_Empleador.jasper"));
            JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
            Map par = new HashMap();
            par.put("ID_EMPRESA",id_emp);
            par.put("F_INICIO",new SimpleDateFormat("yyyy-MM-dd").parse(fechaini));
            par.put("F_FINAL",new SimpleDateFormat("yyyy-MM-dd").parse(fechafin));
            par.put("Host",Main.host);
            par.put("DB",Main.bd);
            par.put("Usu",Main.usu);
            par.put("Cont",Main.cont);
            System.out.println("id: "+id_emp);
            System.out.println("empresa: "+emp);
            System.out.println("inicial: "+fechaini);
            System.out.println("final: "+fechafin);
            JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
            if (formato.equals(".pdf")) {
                Wait_rep.progreso_1.setText(emp);
                JasperExportManager.exportReportToPdfFile(jprint,path);
            }
            if (formato.equals(".xls")) {
                JRXlsxExporter exporterXLS = new JRXlsxExporter();
                Map<String, String> dateFormats = new HashMap<String, String>();
                dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                exporterXLS.setExporterInput(new SimpleExporterInput(jprint));
                exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path));       
                repConfig.setWrapText(Boolean.TRUE);//
                repConfig.setOnePagePerSheet(Boolean.FALSE);
                repConfig.setDetectCellType(Boolean.TRUE);
                repConfig.setWhitePageBackground(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                repConfig.setFormatPatternsMap(dateFormats);
                repConfig.setCollapseRowSpan(Boolean.TRUE);//
                exporterXLS.setConfiguration(repConfig);
                exporterXLS.exportReport();
            }
            con.cerrar();
            Wait_rep.bar.setValue(1);
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"El reporte se ha generado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
            s_a_e.dialog.dispose();
        } catch (JRException | ParseException | SQLException e) {
            con.cerrar();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void asistencias_obras(String path, String formato, String fechaini, String fechafin){
        Wait_rep.btn_aceptar.setEnabled(false);
        s_a.dialog.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Wait_rep.mensaje.setText("Generando plantillas de asistencia por obras");
        path=path+"\\ASISTENCIAS_OBRAS_"+fechaini+"_A_"+fechafin;
        File folder = new File(path);
        folder.mkdirs();
        Conexion con = new Conexion();
        con.conexion();
        try {
            ResultSet r;
            r = con.s.executeQuery("SELECT *\n" +
                                    "FROM\n" +
                                    "    t_asistencias\n" +
                                    "    INNER JOIN t_novedades \n" +
                                    "        ON (t_asistencias.ID_EMPLEADO = t_novedades.ID_EMPLEADO) AND (t_asistencias.ID_EMPRESA = t_novedades.ID_EMPRESA) AND (t_asistencias.F_INGRESO = t_novedades.FECHA_INGRESO) AND (t_asistencias.ID_TIPO = t_novedades.ID_TIPO) AND (t_asistencias.F_RETIRO = t_novedades.FECHA_RETIRO)\n" +
                                    "    INNER JOIN t_obra \n" +
                                    "        ON (t_novedades.ID_OBRA = t_obra.ID_OBRA)\n" +
                                    "    INNER JOIN t_empresas \n" +
                                    "        ON (t_novedades.ID_EMPRESA = t_empresas.ID_EMPRESA)\n" +
                                    "    WHERE ((t_asistencias.F_INGRESO <= '"+fechafin+"' AND t_asistencias.F_RETIRO = '1900-01-01')\n" +
                                    "    OR (t_asistencias.F_INGRESO <= '"+fechafin+"' AND t_asistencias.F_RETIRO >= '"+fechaini+"'))\n" +
                                    "    GROUP BY t_obra.NOMBRE_OBRA");
            Wait_rep.bar.setMaximum(getRowCount(r)+1);
            while(r.next()){
                //////--------------------------REPORTE ASISTENCIAS POR OBRA-------------------------------------///////
                ClassLoader cl= this.getClass().getClassLoader();
                InputStream fis = (cl.getResourceAsStream("Reportes/Asistencias_Obra.jasper"));
                JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
                Map par = new HashMap();
                par.put("NOMBRE_OBRA",r.getString("NOMBRE_OBRA"));
                par.put("F_INICIO",new SimpleDateFormat("yyyy-MM-dd").parse(fechaini));
                par.put("F_FINAL",new SimpleDateFormat("yyyy-MM-dd").parse(fechafin));
                par.put("Host",Main.host);
                par.put("DB",Main.bd);
                par.put("Usu",Main.usu);
                par.put("Cont",Main.cont);
                JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
                if (formato.equals(".pdf")) {
                    Wait_rep.progreso_1.setText(r.getString("ID_OBRA")+"-"+r.getString("NOMBRE_OBRA")+"_.pdf");
                    JasperExportManager.exportReportToPdfFile(jprint,path+"\\"+r.getString("ID_OBRA")+"-"+r.getString("NOMBRE_OBRA")+"_.pdf");
                }
                if (formato.equals(".xls")) {
                    Wait_rep.progreso_1.setText(r.getString("ID_OBRA")+"-"+r.getString("NOMBRE_OBRA")+"_.xlsx");
                    JRXlsxExporter exporterXLS = new JRXlsxExporter();
                    Map<String, String> dateFormats = new HashMap<String, String>();
                    dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                    SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                    exporterXLS.setExporterInput(new SimpleExporterInput(jprint));
                    exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path+"\\"+r.getString("ID_OBRA")+"-"+r.getString("NOMBRE_OBRA")+"_.xlsx"));       
                    repConfig.setWrapText(Boolean.TRUE);//
                    repConfig.setOnePagePerSheet(Boolean.FALSE);
                    repConfig.setDetectCellType(Boolean.TRUE);
                    repConfig.setWhitePageBackground(Boolean.TRUE);
                    repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                    repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                    repConfig.setFormatPatternsMap(dateFormats);
                    repConfig.setCollapseRowSpan(Boolean.TRUE);//
                    exporterXLS.setConfiguration(repConfig);
                    exporterXLS.exportReport();
                }
                Wait_rep.bar.setValue(r.getRow());
                
            }
            con.cerrar();
                Wait_rep.bar.setValue(Wait_rep.bar.getMaximum());
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null,"Los reportes se han generado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
                s_a.dialog.dispose();
        }catch (JRException | ParseException | SQLException e) {
            con.cerrar();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        } 
    }
    public void asistencias_empresas_obras(String path, String formato, String fechaini, String fechafin, String o){
        Wait_rep.btn_aceptar.setEnabled(false);
        s_a_o.dialog.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Wait_rep.mensaje.setText("Generando reporte de asistencia por obra");
        Wait_rep.bar.setMaximum(1);
        Conexion con = new Conexion();
        con.conexion();
        try {   
            //////--------------------------REPORTE ASISTENCIAS POR EMPRESA-------------------------------------///////
            ClassLoader cl= this.getClass().getClassLoader();
            InputStream fis = (cl.getResourceAsStream("Reportes/Asistencias_Obra.jasper"));
            JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
            Map par = new HashMap();
            par.put("NOMBRE_OBRA",o);
            par.put("F_INICIO",new SimpleDateFormat("yyyy-MM-dd").parse(fechaini));
            par.put("F_FINAL",new SimpleDateFormat("yyyy-MM-dd").parse(fechafin));
            par.put("Host",Main.host);
            par.put("DB",Main.bd);
            par.put("Usu",Main.usu);
            par.put("Cont",Main.cont);
            JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
            if (formato.equals(".pdf")) {
                Wait_rep.progreso_1.setText(o);
                JasperExportManager.exportReportToPdfFile(jprint,path);
            }
            if (formato.equals(".xls")) {
                JRXlsxExporter exporterXLS = new JRXlsxExporter();
                Map<String, String> dateFormats = new HashMap<String, String>();
                dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                exporterXLS.setExporterInput(new SimpleExporterInput(jprint));
                exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path));       
                repConfig.setWrapText(Boolean.TRUE);//
                repConfig.setOnePagePerSheet(Boolean.FALSE);
                repConfig.setDetectCellType(Boolean.TRUE);
                repConfig.setWhitePageBackground(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                repConfig.setFormatPatternsMap(dateFormats);
                repConfig.setCollapseRowSpan(Boolean.TRUE);//
                exporterXLS.setConfiguration(repConfig);
                exporterXLS.exportReport();
            }
            con.cerrar();
            Wait_rep.bar.setValue(1);
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"El reporte se ha generado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
            s_a_o.dialog.dispose();
        } catch (JRException | ParseException e) {
            con.cerrar();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void no_asistencias(String path, String formato, String dias, String fecha){
        Wait_rep.btn_aceptar.setEnabled(false);
        s_n_a.dialog.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Wait_rep.mensaje.setText("Generando reportes de NO Asistencia");
        path=path+"\\NO_ASISTENCIAS_EMPRESAS_"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"_"+dias+"_dias";
        File folder = new File(path);
        folder.mkdirs();
        Conexion con = new Conexion();
        con.conexion();
        try {
            ResultSet r;
            r = con.s.executeQuery("SELECT	\n" +
                                    "	*		\n" +
                                    "FROM\n" +
                                    "	t_novedades\n" +
                                    "	INNER JOIN t_obra \n" +
                                    "		ON (t_novedades.ID_OBRA = t_obra.ID_OBRA)\n" +
                                    "	INNER JOIN t_municipios \n" +
                                    "		ON (t_obra.ID_MUN_OBRA = t_municipios.ID_MUN)\n" +
                                    "	INNER JOIN t_departamentos \n" +
                                    "		ON (t_municipios.ID_DEP = t_departamentos.ID_DEP)\n" +
                                    "	INNER JOIN t_afp \n" +
                                    "		ON (t_novedades.ID_AFP = t_afp.ID_AFP)\n" +
                                    "	INNER JOIN t_eps \n" +
                                    "		ON (t_novedades.ID_EPS = t_eps.ID_EPS)\n" +
                                    "	INNER JOIN t_empresas \n" +
                                    "		ON (t_novedades.ID_EMPRESA = t_empresas.ID_EMPRESA)\n" +
                                    "	INNER JOIN t_empleados \n" +
                                    "		ON (t_novedades.ID_EMPLEADO = t_empleados.ID_EMP)\n" +
                                    "	INNER JOIN t_tipo_novedad \n" +
                                    "		ON (t_novedades.ID_TIPO = t_tipo_novedad.ID_TIPO)\n" +
                                    "	WHERE t_novedades.ID_TIPO IN (1,2,4,6)\n" +
                                    "	AND ((t_novedades.`FECHA_INGRESO` <= '"+fecha+"' AND t_novedades.`FECHA_RETIRO` = '1900-01-01')\n" +
                                    "		OR ( t_novedades.`FECHA_INGRESO` <= '"+fecha+"' AND t_novedades.`FECHA_RETIRO` >= '"+fecha+"'))\n" +
                                    "	GROUP BY t_novedades.ID_EMPRESA");
            Wait_rep.bar.setMaximum(getRowCount(r)+1);
            while (r.next()) {                
                //////-----------------------------REPORTE NO ASISTENCIAS-------------------------------------///////
                ClassLoader cl= this.getClass().getClassLoader();
                InputStream fis = (cl.getResourceAsStream("Reportes/No_Asistencias.jasper"));
                JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
                Map par = new HashMap();
                par.put("NOMBRE_EMPRESA",r.getString("NOMBRE_EMPRESA"));
                par.put("FECHA",new SimpleDateFormat("yyyy-MM-dd").parse(fecha));
                par.put("DIAS",Integer.parseInt(dias));
                JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
                if (formato.equals(".pdf")) {
                    Wait_rep.progreso_1.setText(r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.pdf");
                    JasperExportManager.exportReportToPdfFile(jprint,path+"\\"+r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.pdf");
                }
                if (formato.equals(".xls")) {
                    Wait_rep.progreso_1.setText(r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.xlsx");
                    JRXlsxExporter exporterXLS = new JRXlsxExporter();
                    Map<String, String> dateFormats = new HashMap<String, String>();
                    dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                    SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                    exporterXLS.setExporterInput(new SimpleExporterInput(jprint));
                    exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path+"\\"+r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.xlsx"));       
                    repConfig.setWrapText(Boolean.TRUE);//
                    repConfig.setOnePagePerSheet(Boolean.FALSE);
                    repConfig.setDetectCellType(Boolean.TRUE);
                    repConfig.setWhitePageBackground(Boolean.TRUE);
                    repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                    repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                    repConfig.setFormatPatternsMap(dateFormats);
                    repConfig.setCollapseRowSpan(Boolean.TRUE);//
                    exporterXLS.setConfiguration(repConfig);
                    exporterXLS.exportReport();
                }
                Wait_rep.bar.setValue(r.getRow());
            }
            Wait_rep.bar.setValue(Wait_rep.bar.getMaximum());
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"Los reportes se han generado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
            s_n_a.dialog.dispose();
            con.cerrar();
        } catch (JRException | ParseException | SQLException e) {
            con.cerrar();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        } 
    }
    public void no_asistencias_empresas(String path, String formato, String dias, String fecha, String empresa){
        Wait_rep.btn_aceptar.setEnabled(false);
        s_n_a_e.dialog.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Wait_rep.mensaje.setText("Generando reporte de asistencia por empresa");
        Wait_rep.bar.setMaximum(1);
        Conexion con = new Conexion();
        con.conexion();
        try {   
            //////--------------------------REPORTE ASISTENCIAS POR EMPRESA-------------------------------------///////
            ClassLoader cl= this.getClass().getClassLoader();
            InputStream fis = (cl.getResourceAsStream("Reportes/No_Asistencias.jasper"));
            JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
            Map par = new HashMap();
            par.put("NOMBRE_EMPRESA",empresa);
            par.put("FECHA",new SimpleDateFormat("yyyy-MM-dd").parse(fecha));
            par.put("DIAS",Integer.parseInt(dias));
            JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
            if (formato.equals(".pdf")) {
                Wait_rep.progreso_1.setText(empresa);
                JasperExportManager.exportReportToPdfFile(jprint,path);
            }
            if (formato.equals(".xls")) {
                JRXlsxExporter exporterXLS = new JRXlsxExporter();
                Map<String, String> dateFormats = new HashMap<String, String>();
                dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                exporterXLS.setExporterInput(new SimpleExporterInput(jprint));
                exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path));       
                repConfig.setWrapText(Boolean.TRUE);//
                repConfig.setOnePagePerSheet(Boolean.FALSE);
                repConfig.setDetectCellType(Boolean.TRUE);
                repConfig.setWhitePageBackground(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                repConfig.setFormatPatternsMap(dateFormats);
                repConfig.setCollapseRowSpan(Boolean.TRUE);//
                exporterXLS.setConfiguration(repConfig);
                exporterXLS.exportReport();
            }
            con.cerrar();
            Wait_rep.bar.setValue(1);
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"El reporte se ha generado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
            s_n_a_e.dialog.dispose();
        } catch (JRException | ParseException  e) {
            con.cerrar();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void Novedades_Pila_Ingreso (String path, String fechaini, String fechafin){
        Wait_rep.btn_aceptar.setEnabled(false);
        s_n_p.jd_nov_pila.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Wait_rep.mensaje.setText("Generando reportes de novedades de pila ingreso consolidado");
        path=path+"\\NOVEDADES_PILA_INGRESO_"+fechaini+"_A_"+fechafin;
        System.out.println(path);
        File folder = new File(path);
        folder.mkdirs();
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r, r1;
        try {
            
            r = con.s.executeQuery ("SELECT t_novedades.*,t_empleados.*,t_empresas.*,t_eps.*,t_afp.*, t_obra.*,t_municipios.*\n" +
                                    "FROM\n" +
                                    "    t_novedades\n" +
                                    "    INNER JOIN t_empleados \n" +
                                    "        ON (t_novedades.ID_EMPLEADO = t_empleados.ID_EMP)\n" +
                                    "    INNER JOIN t_empresas \n" +
                                    "        ON (t_novedades.ID_EMPRESA = t_empresas.ID_EMPRESA)\n" +
                                    "    INNER JOIN t_eps \n" +
                                    "        ON (t_novedades.ID_EPS = t_eps.ID_EPS)\n" +
                                    "    INNER JOIN t_afp \n" +
                                    "        ON (t_novedades.ID_AFP = t_afp.ID_AFP)\n" +
                                    "    INNER JOIN t_obra \n" +
                                    "        ON (t_novedades.ID_OBRA = t_obra.ID_OBRA)\n" +
                                    "    INNER JOIN t_municipios \n" +
                                    "        ON (t_obra.ID_MUN_OBRA = t_municipios.ID_MUN)\n" +
                                    "    WHERE	ID_TIPO IN (1,2,4,6) \n" +
                                    "	AND (FECHA_INGRESO BETWEEN '"+fechaini+"' AND '"+fechafin+"')\n" +
                                    "	GROUP BY t_novedades.ID_EMPRESA\n" +
                                    "UNION\n" +
                                    "SELECT t_novedades.*,t_empleados.*,t_empresas.*,t_eps.*,t_afp.*, t_obra.*,t_municipios.*\n" +
                                    "FROM\n" +
                                    "    t_novedades\n" +
                                    "    INNER JOIN t_empleados \n" +
                                    "        ON (t_novedades.ID_EMPLEADO = t_empleados.ID_EMP)\n" +
                                    "    INNER JOIN t_empresas \n" +
                                    "        ON (t_novedades.ID_EMPRESA = t_empresas.ID_EMPRESA)\n" +
                                    "    INNER JOIN t_eps \n" +
                                    "        ON (t_novedades.ID_EPS = t_eps.ID_EPS)\n" +
                                    "    INNER JOIN t_afp \n" +
                                    "        ON (t_novedades.ID_AFP = t_afp.ID_AFP)\n" +
                                    "    INNER JOIN t_obra \n" +
                                    "        ON (t_novedades.ID_OBRA = t_obra.ID_OBRA)\n" +
                                    "    INNER JOIN t_municipios \n" +
                                    "        ON (t_obra.ID_MUN_OBRA = t_municipios.ID_MUN)\n" +
                                    "    INNER JOIN t_registro \n" +
                                    "        ON (t_registro.ID_EMPLEADO = t_novedades.ID_EMPLEADO) AND (t_registro.ID_EMPRESA = t_novedades.ID_EMPRESA) AND (t_registro.F_INGRESO = t_novedades.FECHA_INGRESO) AND (t_registro.ID_TIPO = t_novedades.ID_TIPO) AND (t_registro.F_RETIRO = t_novedades.FECHA_RETIRO)\n" +
                                    "    WHERE t_registro.F_INGRESO < '"+fechaini+"'\n" +
                                    "    AND t_registro.REGISTRO = 'PREINGRESO_TRASLADO'\n" +
                                    "    AND t_registro.F_REGISTRO BETWEEN '"+fechaini+"' AND '"+fechafin+"'\n" +
                                    "    GROUP BY t_novedades.ID_EMPRESA");
            Wait_rep.bar.setMaximum(getRowCount(r));
            while (r.next()) {
                //////--------------------------REPORTE NOVEDADES PILA-------------------------------------///////
                Wait_rep.progreso_1.setText(r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.xls");
                ClassLoader cl= this.getClass().getClassLoader();
                InputStream fis = (cl.getResourceAsStream("Reportes/Novedades_pila_ingreso.jasper"));
                JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
                Map par = new HashMap();
                par.put("ID_EMPRESA",r.getString("ID_EMPRESA"));
                par.put("F_INICIAL",new SimpleDateFormat("yyyy-MM-dd").parse(fechaini));
                par.put("F_FINAL",new SimpleDateFormat("yyyy-MM-dd").parse(fechafin));
                JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
                JRXlsExporter exporterXLS = new JRXlsExporter();
                Map<String, String> dateFormats = new HashMap<String, String>();
                dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                exporterXLS.setExporterInput(new SimpleExporterInput(jprint));
                exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path+"\\"+r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.xls"));       
                repConfig.setWrapText(Boolean.TRUE);//
                repConfig.setOnePagePerSheet(Boolean.FALSE);
                repConfig.setDetectCellType(Boolean.TRUE);
                repConfig.setWhitePageBackground(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                repConfig.setFormatPatternsMap(dateFormats);
                repConfig.setCollapseRowSpan(Boolean.TRUE);//
                exporterXLS.setConfiguration(repConfig);
                exporterXLS.exportReport();
                Add_list_novedades_pila add = new Add_list_novedades_pila(path+"\\"+r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.xls");
                Wait_rep.bar.setValue(r.getRow());
            }
            con.cerrar();
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"Los reportes se han generado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
            s_n_p.jd_nov_pila.dispose();
        } catch (JRException | ParseException | SQLException e) {
            con.cerrar();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    
    }
    public void Novedades_Pila_Ingreso_empresa (String path, String fechaini, String fechafin, String id_empresa){
        Wait_rep.btn_aceptar.setEnabled(false);
        s_n_p_e.jd_nov_pila.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Wait_rep.mensaje.setText("Generando reporte de novedad de pila ingreso individual");
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
        try {
            r = con.s.executeQuery ("SELECT t_novedades.*,t_empleados.*,t_empresas.*,t_eps.*,t_afp.*, t_obra.*,t_municipios.*\n" +
                                    "FROM\n" +
                                    "    t_novedades\n" +
                                    "    INNER JOIN t_empleados \n" +
                                    "        ON (t_novedades.ID_EMPLEADO = t_empleados.ID_EMP)\n" +
                                    "    INNER JOIN t_empresas \n" +
                                    "        ON (t_novedades.ID_EMPRESA = t_empresas.ID_EMPRESA)\n" +
                                    "    INNER JOIN t_eps \n" +
                                    "        ON (t_novedades.ID_EPS = t_eps.ID_EPS)\n" +
                                    "    INNER JOIN t_afp \n" +
                                    "        ON (t_novedades.ID_AFP = t_afp.ID_AFP)\n" +
                                    "    INNER JOIN t_obra \n" +
                                    "        ON (t_novedades.ID_OBRA = t_obra.ID_OBRA)\n" +
                                    "    INNER JOIN t_municipios \n" +
                                    "        ON (t_obra.ID_MUN_OBRA = t_municipios.ID_MUN)\n" +
                                    "    WHERE	ID_TIPO IN (1,2,4,6) \n" +
                                    "   AND t_novedades.ID_EMPRESA = '"+id_empresa+"'\n" +
                                    "	AND (FECHA_INGRESO BETWEEN '"+fechaini+"' AND '"+fechafin+"')\n" +
                                    "	GROUP BY t_novedades.ID_EMPRESA\n" +
                                    "UNION\n" +
                                    "SELECT t_novedades.*,t_empleados.*,t_empresas.*,t_eps.*,t_afp.*, t_obra.*,t_municipios.*\n" +
                                    "FROM\n" +
                                    "    t_novedades\n" +
                                    "    INNER JOIN t_empleados \n" +
                                    "        ON (t_novedades.ID_EMPLEADO = t_empleados.ID_EMP)\n" +
                                    "    INNER JOIN t_empresas \n" +
                                    "        ON (t_novedades.ID_EMPRESA = t_empresas.ID_EMPRESA)\n" +
                                    "    INNER JOIN t_eps \n" +
                                    "        ON (t_novedades.ID_EPS = t_eps.ID_EPS)\n" +
                                    "    INNER JOIN t_afp \n" +
                                    "        ON (t_novedades.ID_AFP = t_afp.ID_AFP)\n" +
                                    "    INNER JOIN t_obra \n" +
                                    "        ON (t_novedades.ID_OBRA = t_obra.ID_OBRA)\n" +
                                    "    INNER JOIN t_municipios \n" +
                                    "        ON (t_obra.ID_MUN_OBRA = t_municipios.ID_MUN)\n" +
                                    "    INNER JOIN t_registro \n" +
                                    "        ON (t_registro.ID_EMPLEADO = t_novedades.ID_EMPLEADO) AND (t_registro.ID_EMPRESA = t_novedades.ID_EMPRESA) AND (t_registro.F_INGRESO = t_novedades.FECHA_INGRESO) AND (t_registro.ID_TIPO = t_novedades.ID_TIPO) AND (t_registro.F_RETIRO = t_novedades.FECHA_RETIRO)\n" +
                                    "    WHERE t_registro.F_INGRESO < '"+fechaini+"'\n" +
                                    "    AND t_novedades.ID_EMPRESA = '"+id_empresa+"'\n" +
                                    "    AND t_registro.REGISTRO = 'PREINGRESO_TRASLADO'\n" +
                                    "    AND t_registro.F_REGISTRO BETWEEN '"+fechaini+"' AND '"+fechafin+"'\n" +
                                    "    GROUP BY t_novedades.ID_EMPRESA");
            if (r.next()) {
                Wait_rep.bar.setValue(0);
                Wait_rep.bar.setMaximum(1);
                Wait_rep.progreso_1.setText(s_n_p_e.file);
                ClassLoader cl= this.getClass().getClassLoader();
                InputStream fis = (cl.getResourceAsStream("Reportes/Novedades_pila_ingreso.jasper"));
                JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
                Map par = new HashMap();
                par.put("ID_EMPRESA",id_empresa);
                par.put("F_INICIAL",new SimpleDateFormat("yyyy-MM-dd").parse(fechaini));
                par.put("F_FINAL",new SimpleDateFormat("yyyy-MM-dd").parse(fechafin));
                JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
                //jprint.
                JRXlsExporter exporterXLS = new JRXlsExporter();
                Map<String, String> dateFormats = new HashMap<String, String>();
                dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                exporterXLS.setExporterInput(new SimpleExporterInput(jprint));
                exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path));       
                repConfig.setWrapText(Boolean.TRUE);//
                repConfig.setOnePagePerSheet(Boolean.FALSE);
                repConfig.setDetectCellType(Boolean.TRUE);
                repConfig.setWhitePageBackground(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                repConfig.setFormatPatternsMap(dateFormats);
                repConfig.setCollapseRowSpan(Boolean.TRUE);//
                exporterXLS.setConfiguration(repConfig);
                exporterXLS.exportReport();
                Add_list_novedades_pila add = new Add_list_novedades_pila(path);
                Wait_rep.bar.setValue(1);
                con.cerrar();
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null,"El reporte se ha generado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
                s_n_p_e.jd_nov_pila.dispose();
            }else{
                JOptionPane.showMessageDialog(null,"La empresa seleccionada no posee novedades en este intervalo de tiempo","Información",JOptionPane.INFORMATION_MESSAGE);
                s_n_p_e.jd_nov_pila.dispose();
            }
            
        } catch (SQLException | JRException | ParseException e) {
            con.cerrar();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        } 
    
    }
    public void Novedades_Pila_Retiro (String path, String fechaini, String fechafin){
        Wait_rep.btn_aceptar.setEnabled(false);
        s_n_p_r.jd_nov_pila.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Wait_rep.mensaje.setText("Generando reportes de novedades de pila retiro consolidado");
        path=path+"\\NOVEDADES_PILA_RETIRO_"+fechaini+"_A_"+fechafin;
        System.out.println(path);
        File folder = new File(path);
        folder.mkdirs();
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r, r1;
        try {
            
            r = con.s.executeQuery ("SELECT t_novedades.*,t_empleados.*,t_empresas.*,t_eps.*,t_afp.*, t_obra.*,t_municipios.*\n" +
                                    "FROM\n" +
                                    "    t_novedades\n" +
                                    "    INNER JOIN t_empleados \n" +
                                    "        ON (t_novedades.ID_EMPLEADO = t_empleados.ID_EMP)\n" +
                                    "    INNER JOIN t_empresas \n" +
                                    "        ON (t_novedades.ID_EMPRESA = t_empresas.ID_EMPRESA)\n" +
                                    "    INNER JOIN t_eps \n" +
                                    "        ON (t_novedades.ID_EPS = t_eps.ID_EPS)\n" +
                                    "    INNER JOIN t_afp \n" +
                                    "        ON (t_novedades.ID_AFP = t_afp.ID_AFP)\n" +
                                    "    INNER JOIN t_obra \n" +
                                    "        ON (t_novedades.ID_OBRA = t_obra.ID_OBRA)\n" +
                                    "    INNER JOIN t_municipios \n" +
                                    "        ON (t_obra.ID_MUN_OBRA = t_municipios.ID_MUN)\n" +
                                    "    WHERE	ID_TIPO IN (1,2,4,6) \n" +
                                    "	AND (FECHA_RETIRO BETWEEN '"+fechaini+"' AND '"+fechafin+"')\n" +
                                    "	AND (FECHA_INGRESO < '"+fechaini+"')\n" +
                                    "	GROUP BY t_novedades.ID_EMPRESA");
            Wait_rep.bar.setMaximum(getRowCount(r));
            while (r.next()) {
                //////--------------------------REPORTE NOVEDADES PILA-------------------------------------///////
                Wait_rep.progreso_1.setText(r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.xls");
                ClassLoader cl= this.getClass().getClassLoader();
                InputStream fis = (cl.getResourceAsStream("Reportes/Novedades_pila_retiro.jasper"));
                JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
                Map par = new HashMap();
                par.put("ID_EMPRESA",r.getString("ID_EMPRESA"));
                par.put("F_INICIAL",new SimpleDateFormat("yyyy-MM-dd").parse(fechaini));
                par.put("F_FINAL",new SimpleDateFormat("yyyy-MM-dd").parse(fechafin));
                JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
                JRXlsExporter exporterXLS = new JRXlsExporter();
                Map<String, String> dateFormats = new HashMap<String, String>();
                dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                exporterXLS.setExporterInput(new SimpleExporterInput(jprint));
                exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path+"\\"+r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.xls"));       
                repConfig.setWrapText(Boolean.TRUE);//
                repConfig.setOnePagePerSheet(Boolean.FALSE);
                repConfig.setDetectCellType(Boolean.TRUE);
                repConfig.setWhitePageBackground(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                repConfig.setFormatPatternsMap(dateFormats);
                repConfig.setCollapseRowSpan(Boolean.TRUE);//
                exporterXLS.setConfiguration(repConfig);
                exporterXLS.exportReport();
                //Add_list_novedades_pila add = new Add_list_novedades_pila(path+"\\"+r.getString("ID_EMPRESA")+"-"+r.getString("NOMBRE_EMPRESA")+"_.xls");
                Wait_rep.bar.setValue(r.getRow());
            }
            con.cerrar();
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"Los reportes se han generado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
            s_n_p_r.jd_nov_pila.dispose();
        } catch (JRException | ParseException | SQLException e) {
            con.cerrar();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    
    }
    public void Novedades_Pila_Retiro_empresa (String path, String fechaini, String fechafin, String id_empresa){
        Wait_rep.btn_aceptar.setEnabled(false);
        s_n_p_r_e.jd_nov_pila.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Wait_rep.mensaje.setText("Generando reporte de novedad de pila retiro individual");
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r, r1;
        try {
            r = con.s.executeQuery ("SELECT t_novedades.*,t_empleados.*,t_empresas.*,t_eps.*,t_afp.*, t_obra.*,t_municipios.*\n" +
                                    "FROM\n" +
                                    "    t_novedades\n" +
                                    "    INNER JOIN t_empleados \n" +
                                    "        ON (t_novedades.ID_EMPLEADO = t_empleados.ID_EMP)\n" +
                                    "    INNER JOIN t_empresas \n" +
                                    "        ON (t_novedades.ID_EMPRESA = t_empresas.ID_EMPRESA)\n" +
                                    "    INNER JOIN t_eps \n" +
                                    "        ON (t_novedades.ID_EPS = t_eps.ID_EPS)\n" +
                                    "    INNER JOIN t_afp \n" +
                                    "        ON (t_novedades.ID_AFP = t_afp.ID_AFP)\n" +
                                    "    INNER JOIN t_obra \n" +
                                    "        ON (t_novedades.ID_OBRA = t_obra.ID_OBRA)\n" +
                                    "    INNER JOIN t_municipios \n" +
                                    "        ON (t_obra.ID_MUN_OBRA = t_municipios.ID_MUN)\n" +
                                    "    WHERE	ID_TIPO IN (1,2,4,6) \n" +
                                    "	AND (FECHA_RETIRO BETWEEN '"+fechaini+"' AND '"+fechafin+"')\n" +
                                    "	AND (FECHA_INGRESO < '"+fechaini+"')\n" +
                                    "   AND t_novedades.ID_EMPRESA = '"+id_empresa+"'\n" +
                                    "	GROUP BY t_novedades.ID_EMPRESA");
            if (r.next()) {
                Wait_rep.bar.setValue(0);
                Wait_rep.bar.setMaximum(1);
                Wait_rep.progreso_1.setText(s_n_p_r_e.file);
                ClassLoader cl= this.getClass().getClassLoader();
                InputStream fis = (cl.getResourceAsStream("Reportes/Novedades_pila_retiro.jasper"));
                JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
                Map par = new HashMap();
                par.put("ID_EMPRESA",id_empresa);
                par.put("F_INICIAL",new SimpleDateFormat("yyyy-MM-dd").parse(fechaini));
                par.put("F_FINAL",new SimpleDateFormat("yyyy-MM-dd").parse(fechafin));
                JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
                JRXlsExporter exporterXLS = new JRXlsExporter();
                Map<String, String> dateFormats = new HashMap<String, String>();
                dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                exporterXLS.setExporterInput(new SimpleExporterInput(jprint));
                exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path));       
                repConfig.setWrapText(Boolean.TRUE);//
                repConfig.setOnePagePerSheet(Boolean.FALSE);
                repConfig.setDetectCellType(Boolean.TRUE);
                repConfig.setWhitePageBackground(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                repConfig.setFormatPatternsMap(dateFormats);
                repConfig.setCollapseRowSpan(Boolean.TRUE);//
                exporterXLS.setConfiguration(repConfig);
                exporterXLS.exportReport();
                Add_list_novedades_pila add = new Add_list_novedades_pila(path);
                Wait_rep.bar.setValue(1);
                con.cerrar();
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null,"Los reportes se han generado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
                s_n_p_r_e.jd_nov_pila.dispose();
            }else{
                JOptionPane.showMessageDialog(null,"La empresa seleccionada no posee novedades en este intervalo de tiempo","Información",JOptionPane.INFORMATION_MESSAGE);
                s_n_p_r_e.jd_nov_pila.dispose();
            }
            
            
            
            
        } catch (JRException | ParseException e) {
            con.cerrar();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(GenerarReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public void Balance_novedades(String path, String formato, String fechaant, String fechaini, String fechafin, String fechadesp, String h, String d, String u, String c){
        Wait_rep.btn_aceptar.setEnabled(false);
        s_b_n.dialog.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Wait_rep.mensaje.setText("Generando reporte de balance de novedades");
        Wait_rep.bar.setMaximum(1);
        Conexion con = new Conexion();
        con.conexion();
        try {    
            ClassLoader cl= this.getClass().getClassLoader();
            InputStream fis = (cl.getResourceAsStream("Reportes/Balance_novedades.jasper"));
            JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
            Map par = new HashMap();
            par.put("Fecha_ant",new SimpleDateFormat("yyyy-MM-dd").parse(fechaant));
            par.put("Fecha_inicial",new SimpleDateFormat("yyyy-MM-dd").parse(fechaini));
            par.put("Fecha_final",new SimpleDateFormat("yyyy-MM-dd").parse(fechafin));
            par.put("Fecha_desp",new SimpleDateFormat("yyyy-MM-dd").parse(fechadesp));
            par.put("host",h);
            par.put("db",d);
            par.put("usu",u);
            par.put("cont",c);
            Wait_rep.progreso_1.setText(s_b_n.file);
            JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
            con.cerrar();
            if (formato.equals(".pdf")) {
                JasperExportManager.exportReportToPdfFile(jprint,path);
            }
            if (formato.equals(".xls")) {
                JRXlsxExporter exporterXLS = new JRXlsxExporter();
                Map<String, String> dateFormats = new HashMap<String, String>();
                dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                exporterXLS.setExporterInput(new SimpleExporterInput(jprint));
                exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path));       
                repConfig.setWrapText(Boolean.TRUE);//
                repConfig.setOnePagePerSheet(Boolean.FALSE);
                repConfig.setDetectCellType(Boolean.TRUE);
                repConfig.setWhitePageBackground(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                repConfig.setFormatPatternsMap(dateFormats);
                repConfig.setCollapseRowSpan(Boolean.TRUE);//
                exporterXLS.setConfiguration(repConfig);
                exporterXLS.exportReport();
            }
            Wait_rep.bar.setValue(1);
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"El informe se ha generado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
            s_b_n.dialog.dispose();
        } catch (JRException | HeadlessException e) {
            con.cerrar();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            Logger.getLogger(GenerarReportes.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public void empresas_activas (String path, String formato, Date fecha, String tipo){
        try {
            InputStream fis = null;
            Wait_rep.btn_aceptar.setEnabled(false);
            s_e_a.dialog.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            ClassLoader cl= this.getClass().getClassLoader();
            if (tipo.equals("ACTIVAS")) {
                Wait_rep.mensaje.setText("Generando reporte de empresas activas");
                fis = (cl.getResourceAsStream("Reportes/Empleadores.jasper"));
            }else{
                if (tipo.equals("TODAS")) {
                    Wait_rep.mensaje.setText("Generando reporte de todas las empresas");
                    fis = (cl.getResourceAsStream("Reportes/Empleadores_all.jasper"));
                }
                
            }
            Wait_rep.bar.setMaximum(1);
            Conexion con = new Conexion();
            con.conexion();
            JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
            Map par = new HashMap();
            par.put("Fecha",fecha);
            if (tipo.equals("TODAS")) {
                par.put("host",Main.host);
                par.put("db",Main.bd);
                par.put("usu",Main.usu);
                par.put("cont",Main.cont);
            }
            JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
            con.cerrar();
            Wait_rep.progreso_1.setText(s_e_a.file);
            Wait_rep.bar.setValue(1);
            if (formato.equals(".pdf")) {
                JasperExportManager.exportReportToPdfFile(jprint,path);
            }
            if (formato.equals(".xls")) {
                JRXlsxExporter exporterXLS = new JRXlsxExporter();
                Map<String, String> dateFormats = new HashMap<String, String>();
                dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                exporterXLS.setExporterInput(new SimpleExporterInput(jprint));
                exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path));       
                repConfig.setWrapText(Boolean.TRUE);//
                repConfig.setOnePagePerSheet(Boolean.FALSE);
                repConfig.setDetectCellType(Boolean.TRUE);
                repConfig.setWhitePageBackground(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                repConfig.setFormatPatternsMap(dateFormats);
                repConfig.setCollapseRowSpan(Boolean.TRUE);//
                exporterXLS.setConfiguration(repConfig);
                exporterXLS.exportReport();
            }
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"El reporte se ha generado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
            s_e_a.dialog.dispose();
            
//            JasperViewer jv = new JasperViewer(jprint,false);
//            jv.setTitle("Reporte Personal Activo");
//            jv.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        } 
    
    }
    public void cruce_precalculo(String path, String formato, int id){
        Conexion con = new Conexion();
        con.conexion();
        Wait_rep.btn_aceptar.setEnabled(false);
        s_c_p.dialog.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Wait_rep.mensaje.setText("Generando reporte de cruce de precalculo");
        Wait_rep.bar.setMaximum(1);
        Wait_rep.bar.setValue(0);
        try {
            ClassLoader cl= this.getClass().getClassLoader();
            InputStream fis = (cl.getResourceAsStream("Reportes/Cruce_Precalculo.jasper"));
            JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
            Map par = new HashMap();
            par.put("ID_PREC",id);
            JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
            con.cerrar();
            Wait_rep.progreso_1.setText(s_c_p.file);
            Wait_rep.bar.setValue(1);
            /////////////////////////************************************************************
            if (formato.equals("xlsx")) {
                JRXlsxExporter exporterXLS = new JRXlsxExporter();
                Map<String, String> dateFormats = new HashMap<String, String>();
                dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                exporterXLS.setExporterInput(new SimpleExporterInput(jprint));
                exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path+"."+formato));       
                repConfig.setWrapText(Boolean.TRUE);//
                repConfig.setOnePagePerSheet(Boolean.FALSE);
                repConfig.setDetectCellType(Boolean.TRUE);
                repConfig.setWhitePageBackground(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                repConfig.setFormatPatternsMap(dateFormats);
                repConfig.setCollapseRowSpan(Boolean.TRUE);//
                exporterXLS.setConfiguration(repConfig);
                exporterXLS.exportReport();
            }
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"El reporte se ha generado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
            s_c_p.dialog.dispose();
        }
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
            con.cerrar();
        }
    }
    public void cruce_tesoreria(String path, String formato, int id){
        Conexion con = new Conexion();
        con.conexion();
        Wait_rep.btn_aceptar.setEnabled(false);
        s_c_t.dialog.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Wait_rep.mensaje.setText("Generando reporte de cruce de tesoreria");
        Wait_rep.bar.setMaximum(1);
        Wait_rep.bar.setValue(0);
        try {
            ClassLoader cl= this.getClass().getClassLoader();
            InputStream fis = (cl.getResourceAsStream("Reportes/Cruce_Tesoreria.jasper"));
            JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
            Map par = new HashMap();
            par.put("ID_PREC",id);
            JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
            con.cerrar();
            Wait_rep.progreso_1.setText(s_c_t.file+"."+formato);
            Wait_rep.bar.setValue(1);
            /////////////////////////************************************************************
            if (formato.equals("xlsx")) {
                JRXlsxExporter exporterXLS = new JRXlsxExporter();
                Map<String, String> dateFormats = new HashMap<String, String>();
                dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
                SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
                exporterXLS.setExporterInput(new SimpleExporterInput(jprint));
                exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(path+"."+formato));       
                repConfig.setWrapText(Boolean.TRUE);//
                repConfig.setOnePagePerSheet(Boolean.FALSE);
                repConfig.setDetectCellType(Boolean.TRUE);
                repConfig.setWhitePageBackground(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                repConfig.setFormatPatternsMap(dateFormats);
                repConfig.setCollapseRowSpan(Boolean.TRUE);//
                exporterXLS.setConfiguration(repConfig);
                exporterXLS.exportReport();
            }
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"El reporte se ha generado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
            s_c_t.dialog.dispose();
        }
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
            con.cerrar();
        }
    }
    
    public InputStream exportXls(JasperPrint jprint) throws JRException, IOException {
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //OutputStream outputStream =null;
	JRXlsxExporter exporterXLS = new JRXlsxExporter();
	Map<String, String> dateFormats = new HashMap<String, String>();
        dateFormats.put("EEEEE, MMM d, yyyy", "ddd, mmm d, yyyy");
        SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
        exporterXLS.setExporterInput(new SimpleExporterInput(jprint));
        exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));       
        repConfig.setWrapText(Boolean.TRUE);//
        repConfig.setOnePagePerSheet(Boolean.FALSE);
        repConfig.setDetectCellType(Boolean.TRUE);
        repConfig.setWhitePageBackground(Boolean.TRUE);
        repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
        repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
        repConfig.setFormatPatternsMap(dateFormats);
        repConfig.setCollapseRowSpan(Boolean.TRUE);//
        exporterXLS.setConfiguration(repConfig);
        exporterXLS.exportReport();
        inputStream = new ByteArrayInputStream(outputStream.toByteArray());
	return inputStream;
}
    private int getRowCount(ResultSet resultSet) {
    if (resultSet == null) {
        return 0;
    }
    try {
        resultSet.last();
        return resultSet.getRow();
    } catch (SQLException exp) {
        exp.printStackTrace();
    } finally {
        try {
            resultSet.beforeFirst();
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }
    return 0;
}
    
}   
