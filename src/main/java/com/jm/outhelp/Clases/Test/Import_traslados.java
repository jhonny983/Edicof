/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.outhelp.Clases.Test;

import com.jm.outhelp.Clases.Conexion;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ADMIN
 */
public class Import_traslados {

    public Import_traslados() {
        
    }
    public static void main(String[] args) {
        FileInputStream fis = null;
        Conexion con = new Conexion("127.0.0.1","e_occidente","root","jhonny3029735");
        con.conexion();
                
        try {
            String path = "E:\\Dropbox\\MIS DISEÑOS\\EDICOF\\CONTROL DE VERSIONES\\2.0.0.3/TRASLADOS PARA APLICATIVO.xlsx";
            fis = new FileInputStream(path);
            Workbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = (XSSFSheet)workbook.getSheetAt(workbook.getSheetIndex("Hoja1"));//new SimpleDateFormat("yyyy-MM-dd").format(sfcell.getDateCellValue())
            for (int i = 2; i < 3; i++) {
                XSSFRow row = sheet.getRow(i);
                ResultSet r;
                //System.out.println("SELECT * FROM t_novedades WHERE ID_EMPLEADO = "+row.getCell(0).getRawValue()+" AND FECHA_INGRESO = '"+new SimpleDateFormat("yyyy-MM-dd").format(row.getCell(3).getDateCellValue())+"'");
                r = con.s.executeQuery("SELECT * FROM t_novedades WHERE ID_EMPLEADO = "+row.getCell(0).getRawValue()+" AND FECHA_INGRESO = '"+new SimpleDateFormat("yyyy-MM-dd").format(row.getCell(3).getDateCellValue())+"'");
                if (r.next()) {
                    String id_empleado = r.getString("ID_EMPLEADO");
                    String id_empresa = r.getString("ID_EMPRESA");
                    String f_ingreso = r.getString("FECHA_INGRESO");
                    String f_retiro = r.getString("FECHA_RETIRO");
                    String id_tipo = r.getString("ID_TIPO");
                    con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, ID_USUARIO) VALUES ("+id_empleado+",'"+id_empresa+"','"+f_ingreso+"','"+f_retiro+"',"+id_tipo+",'PREINGRESO_TRASLADO','"+new SimpleDateFormat("yyyy-MM-dd").format(row.getCell(10).getDateCellValue())+"',14703274)");
                    con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, ID_USUARIO) VALUES ("+id_empleado+",'"+id_empresa+"','"+f_ingreso+"','"+f_retiro+"',"+id_tipo+",'INGRESO','"+new SimpleDateFormat("yyyy-MM-dd").format(row.getCell(10).getDateCellValue())+"',14703274)");
                    if (id_tipo.equals("2")) {
                        con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, ID_USUARIO) VALUES ("+id_empleado+",'"+id_empresa+"','"+f_ingreso+"','"+f_retiro+"',"+id_tipo+",'RETIRO','"+f_retiro+"',14703274)");
                    }
                    //System.out.println("ID_EMPLEADO: "+r.getString("ID_EMPLEADO"));
                }
//                XSSFCell cell = row.getCell(0);
//                String a = cell.getRawValue();
//                System.out.println("Fila "+i+"Dato:" +a);
                con.cerrar();
            }
            
        } catch (IOException | SQLException ex) {
            con.cerrar();
            ex.printStackTrace();
        } finally {
            try {
                con.cerrar();
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Import_traslados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
