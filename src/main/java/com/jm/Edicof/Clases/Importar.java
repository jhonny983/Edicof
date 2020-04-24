/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.Clases;

import com.jm.Edicof.GUI.Main;
import java.awt.Cursor;
import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ADMIN
 */
public class Importar extends Thread{
Frame parent;
private File archivo = null;
private int total=0;
List datos_empresas = new ArrayList();
List datos_eps = new ArrayList();
List datos_afp = new ArrayList();
List datos_ccf = new ArrayList();
List datos_arl = new ArrayList();
List datos_barrios = new ArrayList();
List datos_obras = new ArrayList();
List datos_municipios = new ArrayList();
List datos_parentescos = new ArrayList();
List datos_empleados = new ArrayList();
List datos_novedades = new ArrayList();
public Importar(java.awt.Frame p,File a){
    this.archivo = a;
    this.parent=p;
    this.parent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
}
@Override
public void run(){
    total = 0;
    Boolean verif_info=false;
    Main.Bar.setValue(0);
    //parent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    try {
        Main.Bar.setString("Cargando lista de Empresas");
        if (Verifcar_hoja(archivo,"GENERAL_EMPRESAS")) {
            datos_empresas = GetListXls(archivo,"GENERAL_EMPRESAS");
            //PrintList(datos_empresas);
            total = total + datos_empresas.size();
            Main.Bar.setString("Cargando lista de EPS's");
            if (Verifcar_hoja(archivo,"GENERAL_EPS")) {
                datos_eps = GetListXls(archivo,"GENERAL_EPS");
                total = total + datos_eps.size();
                Main.Bar.setString("Cargando lista de AFP's");
                if (Verifcar_hoja(archivo,"GENERAL_AFP")) {
                    datos_afp = GetListXls(archivo,"GENERAL_AFP");
                    total = total + datos_afp.size();
                    Main.Bar.setString("Cargando lista de CCF's");
                    if (Verifcar_hoja(archivo,"GENERAL_CCF")) {
                        datos_ccf = GetListXls(archivo,"GENERAL_CCF");
                        total = total + datos_ccf.size();
                        Main.Bar.setString("Cargando lista de ARL's");
                        if (Verifcar_hoja(archivo,"GENERAL_ARL")) {
                            datos_arl = GetListXls(archivo,"GENERAL_ARL");
                            total = total + datos_arl.size();
                            Main.Bar.setString("Cargando lista de Barrios");
                            if (Verifcar_hoja(archivo,"GENERAL_BARRIOS")) {
                                datos_barrios = GetListXls(archivo,"GENERAL_BARRIOS");
                                PrintList(datos_barrios);
                                System.out.println("Barrios: "+datos_barrios.size());
                                total = total + datos_barrios.size();
                                Main.Bar.setString("Cargando lista de Obras");
                                if (Verifcar_hoja(archivo,"GENERAL_OBRAS")) {
                                    datos_obras = GetListXls(archivo,"GENERAL_OBRAS");
                                    total = total + datos_obras.size();
                                    Main.Bar.setString("Cargando lista de Municipios");
                                    if (Verifcar_hoja(archivo,"GENERAL_MUNICIPIOS")) {
                                        datos_municipios = GetListXls(archivo,"GENERAL_MUNICIPIOS");
                                        total = total + datos_municipios.size();
                                        Main.Bar.setString("Cargando lista de Parentescos");
                                        if (Verifcar_hoja(archivo,"GENERAL_PARENTESCOS")) {
                                            datos_parentescos = GetListXls(archivo,"GENERAL_PARENTESCOS");
                                            total = total + datos_parentescos.size();
                                            Main.Bar.setString("Cargando lista de Empleados");
                                            if (Verifcar_hoja(archivo,"PLANTILLA_EMPLEADOS")) {
                                                datos_empleados = GetListXls(archivo,"PLANTILLA_EMPLEADOS");
                                                total = total + datos_empleados.size();
                                                Main.Bar.setString("Cargando lista de Novedades");
                                                if (Verifcar_hoja(archivo,"CONSOLIDADO")) {
                                                    datos_novedades = GetListXls(archivo,"CONSOLIDADO");
                                                    total = total + datos_novedades.size();
                                                    verif_info=true;
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(parent,"Informacion de Novedades no disponible.", "Error",JOptionPane.ERROR_MESSAGE);
                                                }
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(parent,"Informacion de Empleados no disponible.", "Error",JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(parent,"Informacion de Parentescos no disponible.", "Error",JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(parent,"Informacion de Municipios no disponible.", "Error",JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                                else{
                                    JOptionPane.showMessageDialog(parent,"Informacion de Obras no disponible.", "Error",JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(parent,"Informacion de Barrios no disponible.", "Error",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(parent,"Informacion de ARL's no disponible.", "Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(parent,"Informacion de CCF's no disponible.", "Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(parent,"Informacion de AFP's no disponible.", "Error",JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(parent,"Informacion de EPS's no disponible.", "Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(parent,"Informacion de empresas no disponible.", "Error",JOptionPane.ERROR_MESSAGE);
        }
        if (true) {
            Main.Bar.setMaximum(total);
            if (CheckListEmpresas(datos_empresas)) {
                if (CheckListEPS(datos_eps)) {
                    if (CheckListAFP(datos_afp)) {
                        if (CheckListCCF(datos_ccf)) {
                            if (CheckListARL(datos_arl)) {
                                if (CheckListBarrios(datos_barrios)) {
                                    if (CheckListObras(datos_obras)) {
                                        if (CheckListMunicipios(datos_municipios)) {
                                            if (CheckListParentescos(datos_parentescos)) {
                                                if (CheckListEmpleados(datos_empleados)) {
                                                    if (CheckListNovedades(datos_novedades)) {
                                                        Main.Bar.setString("Informacion completa");
                                                        int r = JOptionPane.showConfirmDialog(parent,"Los datos estan correctos y listos para ser importados\nDesea continuar?","Verificacion",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                                                        if (r==JOptionPane.YES_OPTION) {
                                                            boolean t=false;
                                                            Main.Bar.setString("Informacion completa");
                                                            Main.Bar.setValue(0);
                                                                if (WriteListEPS(datos_eps)) {
                                                                    if (WriteListAFP(datos_afp)) {
                                                                        if (WriteListCCF(datos_ccf)) {
                                                                            if (WriteListARL(datos_arl)) {
//                                                                                if (WriteListBARRIOS(datos_barrios)) {
                                                                                    if (WriteListOBRAS(datos_obras)) {
                                                                                        if (WriteListPARENTESCOS(datos_parentescos)) {
                                                                                            if (WriteListEMPLEADOS(datos_empleados)) {
                                                                                                if (WriteListEmpresas(datos_empresas)) {
                                                                                                    if (WriteListNOVEDADES(datos_novedades)) {
                                                                                                        t = true;
                                                                                                    }else{
                                                                                                        JOptionPane.showMessageDialog(parent,"Error al importar la lista de NOVEDADES\nVerifique el archivo.", "Error",JOptionPane.ERROR_MESSAGE);
                                                                                                    }
                                                                                                }else{
                                                                                                    JOptionPane.showMessageDialog(parent,"Error al importar la lista de EMPRESAS\nVerifique el archivo.", "Error",JOptionPane.ERROR_MESSAGE);
                                                                                                }
                                                                                                
                                                                                            } else {
                                                                                                JOptionPane.showMessageDialog(parent,"Error al importar la lista de EMPLEADOS\nVerifique el archivo.", "Error",JOptionPane.ERROR_MESSAGE);
                                                                                            }
                                                                                        } else {
                                                                                            JOptionPane.showMessageDialog(parent,"Error al importar la lista de PARENTESCOS\nVerifique el archivo.", "Error",JOptionPane.ERROR_MESSAGE);
                                                                                        }
                                                                                    } else {
                                                                                        JOptionPane.showMessageDialog(parent,"Error al importar la lista de OBRAS\nVerifique el archivo.", "Error",JOptionPane.ERROR_MESSAGE);
                                                                                    }
//                                                                                } else {
//                                                                                    JOptionPane.showMessageDialog(parent,"Error al importar la lista de BARRIOS\nVerifique el archivo.", "Error",JOptionPane.ERROR_MESSAGE);
//                                                                                }
                                                                            } else {
                                                                                JOptionPane.showMessageDialog(parent,"Error al importar la lista de ARL\nVerifique el archivo.", "Error",JOptionPane.ERROR_MESSAGE);
                                                                            }
                                                                        }else{
                                                                            JOptionPane.showMessageDialog(parent,"Error al importar la lista de CCF\nVerifique el archivo.", "Error",JOptionPane.ERROR_MESSAGE);
                                                                        }
                                                                    }else{
                                                                        JOptionPane.showMessageDialog(parent,"Error al importar la lista de AFP\nVerifique el archivo.", "Error",JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }else{
                                                                    JOptionPane.showMessageDialog(parent,"Error al importar la lista de EPS\nVerifique el archivo.", "Error",JOptionPane.ERROR_MESSAGE);
                                                                }
                                                            
                                                            if(t){
                                                                Main.Bar.setString("Informacion ingresada");
                                                                JOptionPane.showMessageDialog(parent,"Archivo de seguimiento importado satisfactoriamente.", "Información",JOptionPane.INFORMATION_MESSAGE);
                                                                Main.Bar.setString("");
                                                                Main.Bar.setValue(0);
                                                            }else{
                                                                Main.Bar.setString("Error al ingresar la informacion");
                                                                Main.Bar.setValue(0);
                                                            }
                                                        }
                                                        else{
                                                            Main.Bar.setString("");
                                                            Main.Bar.setValue(0);
                                                        }
                                                    }
                                                    else {
                                                        Main.Bar.setString("");
                                                        Main.Bar.setValue(0);
                                                        JOptionPane.showMessageDialog(parent,"No es posible importar los datos\nVerifique el archivo..", "Error",JOptionPane.ERROR_MESSAGE);
                                                    }
                                                }
                                                else {
                                                    Main.Bar.setString("");
                                                    Main.Bar.setValue(0);
                                                    JOptionPane.showMessageDialog(parent,"No es posible importar los datos\nVerifique el archivo..", "Error",JOptionPane.ERROR_MESSAGE);
                                                }
                                            }
                                            else {
                                                Main.Bar.setString("");
                                                Main.Bar.setValue(0);
                                                JOptionPane.showMessageDialog(parent,"No es posible importar los datos\nVerifique el archivo..", "Error",JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                        else {
                                            Main.Bar.setString("");
                                            Main.Bar.setValue(0);
                                            JOptionPane.showMessageDialog(parent,"No es posible importar los datos\nVerifique el archivo..", "Error",JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                    else {
                                        Main.Bar.setString("");
                                        Main.Bar.setValue(0);
                                        JOptionPane.showMessageDialog(parent,"No es posible importar los datos\nVerifique el archivo..", "Error",JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                                else {
                                    Main.Bar.setString("");
                                    Main.Bar.setValue(0);
                                    JOptionPane.showMessageDialog(parent,"No es posible importar los datos\nVerifique el archivo..", "Error",JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            else {
                                Main.Bar.setString("");
                                Main.Bar.setValue(0);
                                JOptionPane.showMessageDialog(parent,"No es posible importar los datos\nVerifique el archivo..", "Error",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else {
                            Main.Bar.setString("");
                            Main.Bar.setValue(0);
                            JOptionPane.showMessageDialog(parent,"No es posible importar los datos\nVerifique el archivo..", "Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else {
                        Main.Bar.setString("");
                        Main.Bar.setValue(0);
                        JOptionPane.showMessageDialog(parent,"No es posible importar los datos\nVerifique el archivo..", "Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    Main.Bar.setString("");
                    Main.Bar.setValue(0);
                    JOptionPane.showMessageDialog(parent,"No es posible importar los datos de las EPS\nVerifique la pestaña GENERAL_EPS", "Error",JOptionPane.ERROR_MESSAGE);
                }
            }else {
                Main.Bar.setString("");
                Main.Bar.setValue(0);
                JOptionPane.showMessageDialog(parent,"No es posible importar los datos de las empresas\nVerifique la pestaña GENERAL_EMPRESAS", "Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,e.getMessage(), "Error Try General",JOptionPane.ERROR_MESSAGE);
    }finally{
        this.parent.setCursor(Cursor.getDefaultCursor());
    }
}

public static void PrintList (List cdl){
    for (int i = 0; i < cdl.size(); i++) {
        List ctl = (List) cdl.get(i);
        for (int j = 0; j < ctl.size(); j++) {
            System.out.print(ctl.get(j));
            System.out.print(" ");
        }
        System.out.println("");
    }

}
public Boolean Verifcar_hoja(File arc, String s){
    Boolean r = true;
    try {
        FileInputStream fis = new FileInputStream(arc);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        if(wb.getSheetIndex(s)== -1){
            r = false;
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(parent,e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
    }
return r;
}
public List GetListXls(File arc, String s){
    List resp = new ArrayList();
    try {
        FileInputStream fis = new FileInputStream(arc);
        XSSFWorkbook wb = new XSSFWorkbook(fis); // Carga todo el archivo
        XSSFSheet sfsheet = wb.getSheetAt(wb.getSheetIndex(s)); // Carga la hoja 1
        Iterator rowiterator = sfsheet.rowIterator(); //Este elemento es el que maneja las filas de la hoja seleccionada
        while(rowiterator.hasNext()){
            XSSFRow sfrow = (XSSFRow) rowiterator.next();
            Iterator iterator = sfrow.cellIterator();
            List celltemp = new ArrayList();
            while (iterator.hasNext()) { 
                XSSFCell sfcell = (XSSFCell) iterator.next();
                switch(sfcell.getCellType()) {
                    case XSSFCell.CELL_TYPE_NUMERIC:
                        if( DateUtil.isCellDateFormatted(sfcell) ){
                            celltemp.add(new SimpleDateFormat("yyyy-MM-dd").format(sfcell.getDateCellValue()));
                        }else{	
                            celltemp.add(new DecimalFormat("###").format(sfcell.getNumericCellValue()));
                        }
                        break;
                    case XSSFCell.CELL_TYPE_STRING:
                        celltemp.add(sfcell.getStringCellValue());
                        break;
                    case XSSFCell.CELL_TYPE_BOOLEAN:
                        celltemp.add(String.valueOf(sfcell.getBooleanCellValue()));
                        break;
                    case XSSFCell.CELL_TYPE_FORMULA:
                        celltemp.add(sfcell.getRawValue());
                        break;
                }
            }
            resp.add(celltemp);
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
    }
return resp;
}
public Boolean CheckListEmpresas (List cdl){
    Boolean resp = true;
    Main.Bar.setString("Verificando datos de empresas");
    for (int i = 1; i < cdl.size(); i++) {
        Main.Bar.setValue(i);
        List ctl = (List) cdl.get(i);
        if(ctl.get(2).equals("#N/A") | ctl.get(2).equals(" ") | ctl.get(2).equals("") | ctl.get(3).equals("#N/A")| ctl.get(3).equals("") | ctl.get(3).equals(" ") | ctl.get(4).equals("") | ctl.get(4).equals(" ") | ctl.get(5).equals("") | ctl.get(5).equals(" ")){
            resp = false;
            JOptionPane.showMessageDialog(parent,"Verifique los datos de la empresa\nPestaña: "+ctl.get(0)+"\nNIT: "+ctl.get(2)+"\nNombre: "+ctl.get(3), "Error",JOptionPane.ERROR_MESSAGE);
            Main.Bar.setString("");
            Main.Bar.setValue(0);
            break;
        }
    }
    return resp;
}
public Boolean CheckListEPS (List cdl){
    Boolean resp = true;
    Main.Bar.setString("Verificando datos de EPS's");
    for (int i = 0; i < cdl.size(); i++) {
        Main.Bar.setValue(datos_empresas.size()+i);
        List ctl = (List) cdl.get(i);
        if(ctl.get(0).equals("#N/A")|ctl.get(0).equals(" ")){
            resp = false;
            JOptionPane.showMessageDialog(parent,"Lista de EPS no valido.", "Error",JOptionPane.ERROR_MESSAGE);
            Main.Bar.setString("");
            Main.Bar.setValue(0);
            break;
        }
    }
    return resp;
}
public Boolean CheckListAFP (List cdl){
    Boolean resp = true;
    Main.Bar.setString("Verificando datos de AFP's");
    for (int i = 0; i < cdl.size(); i++) {
        Main.Bar.setValue(datos_empresas.size()+datos_eps.size()+i);
        List ctl = (List) cdl.get(i);
        if(ctl.get(0).equals("#N/A")|ctl.get(0).equals(" ")){
            resp = false;
            JOptionPane.showMessageDialog(parent,"Lista de AFP no valido.", "Error",JOptionPane.ERROR_MESSAGE);
            Main.Bar.setString("");
            Main.Bar.setValue(0);
            break;
        }
    }
    return resp;
}
public Boolean CheckListCCF (List cdl){
    Boolean resp = true;
    Main.Bar.setString("Verificando datos de CCF's");
    for (int i = 0; i < cdl.size(); i++) {
        Main.Bar.setValue(datos_empresas.size()+datos_eps.size()+datos_afp.size()+i);
        List ctl = (List) cdl.get(i);
        if(ctl.get(0).equals("#N/A")|ctl.get(0).equals(" ")){
            resp = false;
            JOptionPane.showMessageDialog(parent,"Lista de CCF no valido.", "Error",JOptionPane.ERROR_MESSAGE);
            Main.Bar.setString("");
            Main.Bar.setValue(0);
            break;
        }
    }
    return resp;
}
public Boolean CheckListARL (List cdl){
    Boolean resp = true;
    Main.Bar.setString("Verificando datos de ARL's");
    for (int i = 0; i < cdl.size(); i++) {
        Main.Bar.setValue(datos_empresas.size()+datos_eps.size()+datos_afp.size()+datos_ccf.size()+i);
        List ctl = (List) cdl.get(i);
        if(ctl.get(0).equals("#N/A")|ctl.get(0).equals(" ")){
            resp = false;
            JOptionPane.showMessageDialog(parent,"Lista de ARL no valido.", "Error",JOptionPane.ERROR_MESSAGE);
            Main.Bar.setString("");
            Main.Bar.setValue(0);
            break;
        }
    }
    return resp;
}
public Boolean CheckListBarrios (List cdl){
    Boolean resp = true;
    Main.Bar.setString("Verificando datos de Barrios");
    System.out.println("CDL: "+cdl.size());
    for (int i = 0; i < cdl.size(); i++) {
        Main.Bar.setValue(datos_empresas.size()+datos_eps.size()+datos_afp.size()+datos_ccf.size()+datos_arl.size()+i);
        List ctl = (List) cdl.get(i);
        System.out.println("CtL: "+ctl.size()+", "+i+","+ctl.get(0));
        if(ctl.get(0).equals("#N/A")|ctl.get(0).equals(" ")){
            resp = false;
            JOptionPane.showMessageDialog(parent,"Lista de Barrios no valido.", "Error",JOptionPane.ERROR_MESSAGE);
            Main.Bar.setString("");
            Main.Bar.setValue(0);
            break;
        }
    }
    return resp;
}
public Boolean CheckListObras (List cdl){
    Boolean resp = true;
    Main.Bar.setString("Verificando datos de Obras");
    for (int i = 0; i < cdl.size(); i++) {
        Main.Bar.setValue(datos_empresas.size()+datos_eps.size()+datos_afp.size()+datos_ccf.size()+datos_arl.size()+datos_barrios.size()+i);
        List ctl = (List) cdl.get(i);
        if(ctl.get(0).equals("#N/A")|ctl.get(0).equals(" ")){
            resp = false;
            JOptionPane.showMessageDialog(parent,"Lista de Obras no valido.", "Error",JOptionPane.ERROR_MESSAGE);
            Main.Bar.setString("");
            Main.Bar.setValue(0);
            break;
        }
    }
    return resp;
}
public Boolean CheckListMunicipios (List cdl){
    Boolean resp = true;
    Main.Bar.setString("Verificando datos de Municipios");
    for (int i = 0; i < cdl.size(); i++) {
        Main.Bar.setValue(datos_empresas.size()+datos_eps.size()+datos_afp.size()+datos_ccf.size()+datos_arl.size()+datos_barrios.size()+datos_obras.size()+i);
        List ctl = (List) cdl.get(i);
        if(ctl.get(0).equals("#N/A")|ctl.get(0).equals(" ")){
            resp = false;
            JOptionPane.showMessageDialog(parent,"Lista de Muicipios no valido.", "Error",JOptionPane.ERROR_MESSAGE);
            Main.Bar.setString("");
            Main.Bar.setValue(0);
            break;
        }
    }
    return resp;
}
public Boolean CheckListParentescos (List cdl){
    Boolean resp = true;
    Main.Bar.setString("Verificando datos de Parentescos");
    for (int i = 0; i < cdl.size(); i++) {
        Main.Bar.setValue(datos_empresas.size()+datos_eps.size()+datos_afp.size()+datos_ccf.size()+datos_arl.size()+datos_barrios.size()+datos_obras.size()+datos_municipios.size()+i);
        List ctl = (List) cdl.get(i);
        if(ctl.get(0).equals("#N/A")|ctl.get(0).equals(" ")){
            resp = false;
            JOptionPane.showMessageDialog(parent,"Lista de parentescos no valido.", "Error",JOptionPane.ERROR_MESSAGE);
            Main.Bar.setString("");
            Main.Bar.setValue(0);
            break;
        }
    }
    return resp;
}
public Boolean CheckListEmpleados (List cdl){
    Boolean resp = true;
    Main.Bar.setString("Verificando datos de Empleados");
    for (int i = 1; i < cdl.size(); i++) {
        Main.Bar.setValue(datos_empresas.size()+datos_eps.size()+datos_afp.size()+datos_ccf.size()+datos_arl.size()+datos_barrios.size()+datos_obras.size()+datos_municipios.size()+datos_parentescos.size()+i);
        List ctl = (List) cdl.get(i);
        if(ctl.get(0).equals("Null")|ctl.get(0).equals(" ")|ctl.get(2).equals("Null")|ctl.get(4).equals("Null")){
            resp = false;
            JOptionPane.showMessageDialog(parent,"Verifique los datos del empleado\nCedula: "+ctl.get(0)+"\nNombre: "+ctl.get(4)+"\nApellido: "+ctl.get(2), "Error",JOptionPane.ERROR_MESSAGE);
            Main.Bar.setString("");
            Main.Bar.setValue(0);
        }
    }
    return resp;
}
public Boolean CheckListNovedades (List cdl){
    //System.out.println("Numero de novedades: "+cdl.size());
    Boolean resp = true;
    Main.Bar.setString("Verificando datos de Novedades");
    for (int i = 1; i < cdl.size(); i++) {
        //System.out.println("Verificando Novedad: "+i);
        Main.Bar.setValue(datos_empresas.size()+datos_eps.size()+datos_afp.size()+datos_ccf.size()+datos_arl.size()+datos_barrios.size()+datos_obras.size()+datos_municipios.size()+datos_parentescos.size()+datos_empleados.size()+i);
        List ctl = (List) cdl.get(i);
        try {
            if(ctl.get(0).equals("Null")|ctl.get(0).equals("#N/A")|ctl.get(2).equals("Null")|ctl.get(2).equals("#N/A")|ctl.get(3).equals("Null")|ctl.get(3).equals("#N/A")|ctl.get(5).equals("Null")|ctl.get(5).equals("#N/A")|ctl.get(5).equals("0")|ctl.get(6).equals("Null")|ctl.get(6).equals("#N/A")|ctl.get(7).equals("Null")|ctl.get(7).equals("#N/A")|ctl.get(8).equals("Null")|ctl.get(8).equals("#N/A")|ctl.get(9).equals("Null")|ctl.get(9).equals("#N/A")|ctl.get(10).equals("Null")|ctl.get(10).equals("#N/A")|ctl.get(11).equals("Null")|ctl.get(11).equals("#N/A")|ctl.get(12).equals("Null")|ctl.get(12).equals("#N/A")|ctl.get(13).equals("Null")|ctl.get(13).equals("#N/A")|ctl.get(14).equals("Null")|ctl.get(14).equals("#N/A")|ctl.get(15).equals("Null")|ctl.get(15).equals("#N/A")|ctl.get(16).equals("Null")|ctl.get(16).equals("#N/A")|ctl.get(17).equals("Null")|ctl.get(17).equals("#N/A")|ctl.get(18).equals("Null")|ctl.get(18).equals("#N/A")|ctl.get(19).equals("Null")|ctl.get(19).equals("#N/A")|ctl.get(20).equals("Null")|ctl.get(20).equals("#N/A")|ctl.get(21).equals("Null")|ctl.get(21).equals("#N/A")){
                resp = false;
                JOptionPane.showMessageDialog(parent,"Verifique los datos de la Novedad\nCedula: "+ctl.get(0)+"\nNIT: "+ctl.get(2)+"\nFecha ingreso: "+ctl.get(3)+"\nSalario: "+ctl.get(5)+"\nEps: "+ctl.get(6)+"\nAFP: "+ctl.get(7)+"\nARL: "+ctl.get(8)+"\nCCF: "+ctl.get(9)+"\nF Nac: "+ctl.get(10)+"\nF Exp: "+ctl.get(11)+"\nObra: "+ctl.get(12)+"\nDirección: "+ctl.get(13)+"\nBarrio: "+ctl.get(14)+"\nMunicipio: "+ctl.get(15)+"\nTelófono: "+ctl.get(16)+"\nCorreo: "+ctl.get(17)+"\nAcudiente: "+ctl.get(18)+"\nParentesco: "+ctl.get(19)+"\nTel par: "+ctl.get(20)+"\nObservaciones: "+ctl.get(21), "Error",JOptionPane.ERROR_MESSAGE);
                Main.Bar.setString("");
                Main.Bar.setValue(0);
                break;
            }else{
                try {
                    Date test_f_ingreso=new SimpleDateFormat("yyyy-MM-dd").parse(ctl.get(3).toString());
                    try {
                        Date test_f_retiro=new SimpleDateFormat("yyyy-MM-dd").parse(ctl.get(4).toString());
                        try {
                            Date test_f_nac=new SimpleDateFormat("yyyy-MM-dd").parse(ctl.get(10).toString());
                            try {
                                Date test_f_exp=new SimpleDateFormat("yyyy-MM-dd").parse(ctl.get(11).toString());
                                resp = true;
                            } catch (ParseException ex) {
                                resp = false;
                                JOptionPane.showMessageDialog(parent,"Fecha de expedicion no valida :"+ctl.get(11).toString()+"\nLinea: "+(i+1)+"\nPestaña Consolidado.", "Error",JOptionPane.ERROR_MESSAGE);
                                break;
                            }
                        } catch (ParseException ex) {
                            resp = false;
                            JOptionPane.showMessageDialog(parent,"Fecha de nacimiento no valida :"+ctl.get(10).toString()+"\nLinea: "+(i+1)+"\nPestaña Consolidado.", "Error",JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                    } catch (ParseException ex) {
                        resp = false;
                        JOptionPane.showMessageDialog(parent,"Fecha de retiro no valida :"+ctl.get(4).toString()+"\nLinea: "+(i+1)+"\nPestaña Consolidado.", "Error",JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                } catch (ParseException ex) {
                    resp = false;
                    JOptionPane.showMessageDialog(parent,"Fecha de ingreso no valida :"+ctl.get(3).toString()+"\nLinea: "+(i+1)+"\nPestaña Consolidado.", "Error",JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parent,"Verifique los datos de la Novedad "+i+" Pestaña Consolidado", "Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    return resp;
}
public int count_char(String str, char c){
    String pr=str.trim();
    char _toCompare=c;
    int veces=0;
    char []caracteres=pr.toCharArray();
    for(int i=0;i<=caracteres.length-1;i++){
        if(_toCompare ==caracteres[i]){
                veces++;
        }
}
    return veces;
}
public Boolean WriteListEmpresas(List cdl){
    boolean ret=false;
    int id_arl=0;
    int id_ccf=0;
    Conexion con = new Conexion();
    ResultSet r;
    con.conexion();
    try {
        Main.Bar.setString("Ingresando datos de empresas");
        System.out.println(cdl.size()+" EMPRESAS REGISTRADAS");
        for (int i = 1; i < cdl.size(); i++) {
            Main.Bar.setValue(i);
            List ctl = (List) cdl.get(i);
            r = con.s.executeQuery ("SELECT * FROM `t_arl` WHERE (NOMBRE_ARL ='"+ctl.get(4).toString().replace("'","''").toUpperCase().trim()+"');");
            if(r.next()){
                id_arl=r.getInt("ID_ARL");    
                r = con.s.executeQuery ("SELECT * FROM `t_ccf` WHERE (NOMBRE_CCF ='"+ctl.get(5).toString().replace("'","''").toUpperCase().trim()+"');");
                if(r.next()){
                    id_ccf=r.getInt("ID_CCF");
                }
            }
            r = con.s.executeQuery ("SELECT * FROM `t_empresas` WHERE (ID_EMPRESA ='"+ctl.get(2)+"');");
            if(r.next()){
                System.out.println("EMPRESA YA EXISTE: "+ctl.get(2));
            }else{
                System.out.println(i+" INSERT INTO `t_empresas` VALUES ('"+ctl.get(2)+"','"+ctl.get(3).toString().replace("'","''").toUpperCase().trim()+"',"+id_arl+","+id_ccf+")");
                con.s.executeUpdate("INSERT INTO `t_empresas` VALUES ('"+ctl.get(2)+"','"+ctl.get(3).toString().replace("'","''").toUpperCase().trim()+"',"+id_arl+","+id_ccf+")");
                
            }
        }
        ret = true;
    } catch (Exception ex) {
        con.cerrar();
        ex.printStackTrace();
        JOptionPane.showMessageDialog(parent,"Error al insertar el listado de empresas \n"+ex, "Error",JOptionPane.ERROR_MESSAGE);
    }
    con.cerrar();
    return ret;
}
public Boolean WriteListEPS(List cdl){
    boolean ret=false;
    Conexion con = new Conexion();
    ResultSet r;
    con.conexion();
    try {
        Main.Bar.setString("Ingresando datos de EPS's");
        for (int i = 0; i < cdl.size(); i++) {
            Main.Bar.setValue(datos_empresas.size()+i);
            List ctl = (List) cdl.get(i);
            r = con.s.executeQuery ("SELECT * FROM `t_eps` WHERE (NOMBRE_EPS ='"+ctl.get(0).toString().replace("'","''").toUpperCase()+"');");
            //System.out.println("SELECT * FROM `t_eps` WHERE (NOMBRE_EPS ='"+ctl.get(0).toString().replace("'","''").toUpperCase()+"');");
            if(r.next()){
                System.out.println("EPS ya existe: "+ctl.get(0));
            }else{
                //System.out.println("INSERT INTO `t_eps` VALUES ('"+ctl.get(0).toString().replace("'","''").toUpperCase()+"')");
                con.s.executeUpdate("INSERT INTO `t_eps` VALUES (NULL,'"+ctl.get(0).toString().replace("'","''").toUpperCase().trim()+"')");
            }
        }
        ret = true;
    } catch (SQLException ex) {
        con.cerrar();
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parent,"Error al insertar el listado de EPS's \n"+ex, "Error",JOptionPane.ERROR_MESSAGE);
    }
    con.cerrar();
    return ret;
}
public Boolean WriteListAFP(List cdl){
    boolean ret=false;
    Conexion con = new Conexion();
    ResultSet r;
    con.conexion();
    try {
        Main.Bar.setString("Ingresando datos de AFP's");
        for (int i = 0; i < cdl.size(); i++) {
            Main.Bar.setValue(datos_empresas.size()+datos_eps.size()+i);
            List ctl = (List) cdl.get(i);
            r = con.s.executeQuery ("SELECT * FROM `t_afp` WHERE (NOMBRE_AFP ='"+ctl.get(0).toString().replace("'","''").toUpperCase()+"');");
            if(r.next()){
                System.out.println("AFP ya existe: "+ctl.get(0));
            }else{
                con.s.executeUpdate("INSERT INTO `t_afp` VALUES (NULL,'"+ctl.get(0).toString().replace("'","''").toUpperCase().trim()+"')");
            }
        }
        ret = true;
    } catch (SQLException ex) {
        con.cerrar();
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parent,"Error al insertar el listado de AFP's \n"+ex, "Error",JOptionPane.ERROR_MESSAGE);
        }
    con.cerrar();
    return ret;
}
public Boolean WriteListCCF(List cdl){
    boolean ret=false;
    Conexion con = new Conexion();
    ResultSet r;
    con.conexion();
    try {
        //con.s.executeUpdate("INSERT INTO `t_ccf` VALUES ('Ninguno')");
        Main.Bar.setString("Ingresando datos de CCF's");
        for (int i = 0; i < cdl.size(); i++) {
            Main.Bar.setValue(datos_empresas.size()+datos_eps.size()+datos_afp.size()+i);
            List ctl = (List) cdl.get(i);
            r = con.s.executeQuery ("SELECT * FROM `t_ccf` WHERE (NOMBRE_CCF ='"+ctl.get(0).toString().replace("'","''").toUpperCase()+"');");
            if(r.next()){
                System.out.println("CCF ya existe: "+ctl.get(0));
            }else{
                //System.out.println("INSERT INTO `t_ccf` VALUES ('"+ctl.get(0).toString().replace("'","''").toUpperCase()+"')");
                con.s.executeUpdate("INSERT INTO `t_ccf` VALUES (NULL,'"+ctl.get(0).toString().replace("'","''").toUpperCase().trim()+"')");
            }
        }
        ret = true;
    } catch (SQLException ex) {
        con.cerrar();
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parent,"Error al insertar el listado de CCF's \n"+ex, "Error",JOptionPane.ERROR_MESSAGE);
    }
    con.cerrar();
    return ret;
}
public Boolean WriteListARL(List cdl){
    boolean ret=false;
    Conexion con = new Conexion();
    ResultSet r;
    con.conexion();
    try {
        //con.s.executeUpdate("INSERT INTO `t_arl` VALUES ('Ninguno')");
        Main.Bar.setString("Ingresando datos de ARL's");
        for (int i = 0; i < cdl.size(); i++) {
            Main.Bar.setValue(datos_empresas.size()+datos_eps.size()+datos_afp.size()+datos_ccf.size()+i);
            List ctl = (List) cdl.get(i);
            r = con.s.executeQuery ("SELECT * FROM `t_arl` WHERE (NOMBRE_ARL ='"+ctl.get(0).toString().replace("'","''").toUpperCase()+"');");
            if(r.next()){
                System.out.println("ARL ya existe: "+ctl.get(0));
            }else{
                //System.out.println("INSERT INTO `t_arl` VALUES ('"+ctl.get(0).toString().replace("'","''").toUpperCase()+"')");
                con.s.executeUpdate("INSERT INTO `t_arl` VALUES (NULL,'"+ctl.get(0).toString().replace("'","''").toUpperCase().trim()+"')");
            }
        }
        ret=true;
    } catch (SQLException ex) {
        con.cerrar();
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parent,"Error al insertar el listado de ARL's \n"+ex, "Error",JOptionPane.ERROR_MESSAGE);
    }
    //JOptionPane.showMessageDialog(parent,"Listado de empresas importado satisfactoriamente.", "Información",JOptionPane.INFORMATION_MESSAGE);
    con.cerrar();
    return ret;
}
public Boolean WriteListBARRIOS(List cdl){
    boolean ret=false;
    Conexion con = new Conexion();
    ResultSet r;
    con.conexion();
    try {
        Main.Bar.setString("Ingresando datos de Barrios");
        for (int i = 0; i < cdl.size(); i++) {
            Main.Bar.setValue(datos_empresas.size()+datos_eps.size()+datos_afp.size()+datos_ccf.size()+datos_arl.size()+i);
            List ctl = (List) cdl.get(i);
            r = con.s.executeQuery ("SELECT * FROM `t_barrio` WHERE (NOMBRE_BARRIO ='"+ctl.get(0).toString().replace("'","''").toUpperCase()+"');");
            if(r.next()){
                System.out.println("Barrio ya existe: "+ctl.get(0));
            }else{
                con.s.executeUpdate("INSERT INTO `t_barrio` VALUES (NULL,'"+ctl.get(0).toString().replace("'","''").toUpperCase().trim()+"')");
            }
        }
        ret=true;
    } catch (SQLException ex) {
        con.cerrar();
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parent,"Error al insertar el listado de barrios. \n"+ex, "Error",JOptionPane.ERROR_MESSAGE);
    }
    con.cerrar();
    return ret;
}
public Boolean WriteListOBRAS(List cdl){
    boolean ret=true;
    String id_mun;
    String obra="";
    String mun="";
    String dep="";
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try {
        Main.Bar.setString("Ingresando datos de Obras");
        for (int i = 0; i < cdl.size(); i++) {
            Main.Bar.setValue(datos_empresas.size()+datos_eps.size()+datos_afp.size()+datos_ccf.size()+datos_arl.size()+datos_barrios.size()+i);
            List ctl = (List) cdl.get(i);
            obra = ctl.get(0).toString().replace("'","''").toUpperCase();
            obra = obra.trim();
            if (obra.contains("(") & obra.contains(")")) {
                int index0 = obra.indexOf("(");
                int index1 = obra.indexOf(")");
                if (count_char(obra.substring(index0+1,index1),'-')==1) {
                    StringTokenizer tokens=new StringTokenizer(obra.substring(index0+1,index1),"-");
                    obra = obra.substring(0,index0);
                    while(tokens.hasMoreTokens()){
                        mun=tokens.nextToken().trim();
                        dep=tokens.nextToken().trim();
                    }
                }else{
                    JOptionPane.showMessageDialog(parent,"Error al insertar el listado de Obras. \nEl nombre del municipio y departamento de la obra deben estar separados por el caracter '-'\nMunicipio-Departamento: "+obra.substring(index0+1,index1)+"\nLinea: "+(i+1)+" Pestaña 'GENERAL_OBRAS'", "Error",JOptionPane.ERROR_MESSAGE);
                    ret = false;
                    break;
                }
                
            }else{
                mun = "CALI";
                dep = "VALLE DEL CAUCA";
            }
            r = con.s.executeQuery ("SELECT *\n" +
                                    "FROM\n" +
                                    "    t_obra\n" +
                                    "    INNER JOIN t_municipios \n" +
                                    "        ON (t_obra.ID_MUN_OBRA = t_municipios.ID_MUN)\n" +
                                    "    INNER JOIN t_departamentos \n" +
                                    "        ON (t_municipios.ID_DEP = t_departamentos.ID_DEP) WHERE NOMBRE_OBRA ='"+obra+"' AND NOMBRE_MUN = '"+mun+"' AND NOMBRE_DEP = '"+dep+"'");
            if(r.next()){
                System.out.println("Obra ya existe: "+obra+"-"+mun+"-"+dep);
            }else{
                r = con.s.executeQuery ("SELECT *\n" +
                                        "FROM\n" +
                                        "    t_municipios\n" +
                                        "    INNER JOIN t_departamentos \n" +
                                        "        ON (t_municipios.ID_DEP = t_departamentos.ID_DEP) WHERE NOMBRE_MUN ='"+mun+"' AND NOMBRE_DEP = '"+dep+"'");
                if (r.next()) {
                    id_mun = r.getString("ID_MUN");
                    con.s.executeUpdate("INSERT INTO `t_obra` VALUES (NULL,'"+obra+"',"+id_mun+")");
                    ret = true;
                }else{
                    JOptionPane.showMessageDialog(parent,"Error al insertar el listado de Obras. \nEl municipio no existe en el listado\nMunicipio: "+mun+"\nLinea: "+(i+1)+" Pestaña 'GENERAL_OBRAS'", "Error",JOptionPane.ERROR_MESSAGE);
                    ret = false;
                    break;
                }
            }
        }
    } catch (SQLException ex) {
        con.cerrar();
        ex.printStackTrace();
        JOptionPane.showMessageDialog(parent,"Error al insertar el listado de Obras. \n"+ex, "Error",JOptionPane.ERROR_MESSAGE);
    }
    con.cerrar();
    return ret;
}
public Boolean WriteListMUNICIPIOS(List cdl){
    boolean ret=false;
    Conexion con = new Conexion();
    ResultSet r;
    con.conexion();
    try {
        Main.Bar.setString("Ingresando datos de Municipios");
        for (int i = 0; i < cdl.size(); i++) {
            Main.Bar.setValue(datos_empresas.size()+datos_eps.size()+datos_afp.size()+datos_ccf.size()+datos_arl.size()+datos_barrios.size()+datos_obras.size()+i);
            List ctl = (List) cdl.get(i);
            r = con.s.executeQuery ("SELECT * FROM `t_municipio` WHERE (NOMBRE_MUNICIPIO ='"+ctl.get(0).toString().replace("'","''").toUpperCase()+"');");
            if(r.next()){
                System.out.println("Municipio ya existe: "+ctl.get(0));
            }else{
                //System.out.println("INSERT INTO `t_municipio` VALUES ('"+ctl.get(0).toString().replace("'","''").toUpperCase()+"')");
                con.s.executeUpdate("INSERT INTO `t_municipio` VALUES (NULL,'"+ctl.get(0).toString().replace("'","''").toUpperCase()+"')");
            }

        }
        ret=true;
    } catch (SQLException ex) {
        con.cerrar();
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parent,"Error al insertar el listado de Municipios. \n"+ex, "Error",JOptionPane.ERROR_MESSAGE);
    }
    con.cerrar();
    return ret;
}
public Boolean WriteListPARENTESCOS(List cdl){
    boolean ret=false;
    Conexion con = new Conexion();
    ResultSet r;
    con.conexion();
    try {
        //con.s.executeUpdate("INSERT INTO `t_parentesco` VALUES ('Ninguno')");
        Main.Bar.setString("Ingresando datos de Parentescos");
        for (int i = 0; i < cdl.size(); i++) {
            Main.Bar.setValue(datos_empresas.size()+datos_eps.size()+datos_afp.size()+datos_ccf.size()+datos_arl.size()+datos_barrios.size()+datos_obras.size()+datos_municipios.size()+i);
            List ctl = (List) cdl.get(i);
            r = con.s.executeQuery ("SELECT * FROM `t_parentesco` WHERE (NOMBRE_PAR ='"+ctl.get(0).toString().replace("'","''").toUpperCase()+"');");
            if(r.next()){
                System.out.println("Parentesco ya existe: "+ctl.get(0));
            }else{
                //System.out.println("INSERT INTO `t_parentesco` VALUES ('"+ctl.get(0).toString().replace("'","''").toUpperCase()+"')");
                con.s.executeUpdate("INSERT INTO `t_parentesco` VALUES (NULL,'"+ctl.get(0).toString().replace("'","''").toUpperCase().trim()+"')");
            }
        }
        ret=true;
    } catch (SQLException ex) {
        con.cerrar();
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parent,"Error al insertar el listado de Parentescos. \n"+ex, "Error",JOptionPane.ERROR_MESSAGE);
    }
    con.cerrar();
    return ret;
}
public Boolean WriteListEMPLEADOS(List cdl){
    boolean ret=false;
    Conexion con = new Conexion();
    ResultSet r;
    con.conexion();
    Main.Bar.setString("Ingresando datos de Empleados");
    try {
        for (int i = 1; i < cdl.size(); i++) {
        Main.Bar.setValue(datos_empresas.size()+datos_eps.size()+datos_afp.size()+datos_ccf.size()+datos_arl.size()+datos_barrios.size()+datos_obras.size()+datos_municipios.size()+datos_parentescos.size()+i);
        List ctl = (List) cdl.get(i);
        r = con.s.executeQuery ("SELECT * FROM `t_empleados` WHERE (ID_EMP ="+ctl.get(0).toString().replace("'","''").toUpperCase()+");");
            if(r.next()){
                System.out.println("Empleado ya existe: "+ctl.get(0));
            }else{
                //System.out.println("INSERT INTO `t_empleados` (`ID_EMP`, `NOMBRE_1_EMP`, `NOMBRE_2_EMP`, `APELLIDO_1_EMP`, `APELLIDO_2_EMP`) VALUES ("+ctl.get(0).toString().replace("'","''").replace("Null","").toUpperCase()+", '"+ctl.get(4).toString().replace("'","''").replace("Null","").toUpperCase()+"', '"+ctl.get(5).toString().replace("'","''").replace("Null","").toUpperCase()+"', '"+ctl.get(2).toString().replace("'","''").replace("Null","").toUpperCase()+"', '"+ctl.get(3).toString().replace("'","''").replace("Null","").toUpperCase()+"')");
                con.s.executeUpdate("INSERT INTO `t_empleados` (`ID_EMP`, `NOMBRE_1_EMP`, `NOMBRE_2_EMP`, `APELLIDO_1_EMP`, `APELLIDO_2_EMP`) VALUES ("+ctl.get(0).toString().replace("'","''").replace("Null","").toUpperCase().trim()+", '"+ctl.get(4).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"', '"+ctl.get(5).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"', '"+ctl.get(2).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"', '"+ctl.get(3).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"')");
            }
        }
        ret=true;
    } catch (Exception ex) {
        con.cerrar();
        ex.printStackTrace();
        JOptionPane.showMessageDialog(parent,"Error al insertar el listado de Empleados. \n"+ex, "Error",JOptionPane.ERROR_MESSAGE);
    }
    con.cerrar();
    return ret;
}
public Boolean WriteListNOVEDADES(List cdl){
    boolean ret=true;
    int id_eps=0;
    int id_afp=0;
    String id_arl="";
    String id_ccf="";
    int id_obra=0;
    //int id_barrio=0;
    int id_municipio=0;
    int id_par_acud=0;
    String obra="";
    String mun="";
    String dep="";
    //String f_ret="";
    Conexion con = new Conexion();
    ResultSet r;
    con.conexion();
    Main.Bar.setString("Ingresando datos de Novedades");
    try {
        for (int i = 1; i < cdl.size(); i++) {
            Main.Bar.setValue(datos_empresas.size()+datos_eps.size()+datos_afp.size()+datos_ccf.size()+datos_arl.size()+datos_barrios.size()+datos_obras.size()+datos_municipios.size()+datos_parentescos.size()+datos_empleados.size()+i);
            List ctl = (List) cdl.get(i);
            r = con.s.executeQuery ("SELECT * FROM `t_eps` WHERE (NOMBRE_EPS ='"+ctl.get(6).toString().replace("'","''").toUpperCase().trim()+"');");
            if(r.next()){
                id_eps=r.getInt("ID_EPS"); 
                r = con.s.executeQuery ("SELECT * FROM `t_afp` WHERE (NOMBRE_AFP ='"+ctl.get(7).toString().replace("'","''").toUpperCase().trim()+"');");
                if(r.next()){
                    id_afp=r.getInt("ID_AFP"); 
                    r = con.s.executeQuery ("SELECT * FROM `t_arl` WHERE (NOMBRE_ARL ='"+ctl.get(8).toString().replace("'","''").toUpperCase().trim()+"');");
                    if(r.next()){
                        id_arl=r.getString("NOMBRE_ARL");    
                        r = con.s.executeQuery ("SELECT * FROM `t_ccf` WHERE (NOMBRE_CCF ='"+ctl.get(9).toString().replace("'","''").toUpperCase().trim()+"');");
                        if(r.next()){
                            id_ccf=r.getString("NOMBRE_CCF");   
                            obra = ctl.get(12).toString().replace("'","''").toUpperCase();
                            obra = obra.trim();
                            if (obra.contains("(") & obra.contains(")")) {
                                int index0 = obra.indexOf("(");
                                int index1 = obra.indexOf(")");
                                if (count_char(obra.substring(index0+1,index1),'-')==1) {
                                    StringTokenizer tokens=new StringTokenizer(obra.substring(index0+1,index1),"-");
                                    obra = obra.substring(0,index0);
                                    while(tokens.hasMoreTokens()){
                                        mun=tokens.nextToken().trim();
                                        dep=tokens.nextToken().trim();
                                    }
                                }else{
                                    JOptionPane.showMessageDialog(parent,"Error al insertar el listado de Novedades. \nEl nombre del municipio y departamento de la obra deben estar separados por el caracter '-'\nMunicipio-Departamento: "+obra.substring(index0+1,index1)+"\nLinea: "+(i+1)+" Pestaña 'GENERAL_OBRAS'", "Error",JOptionPane.ERROR_MESSAGE);
                                    ret = false;
                                    break;
                                }
                            }else{
                                mun = "CALI";
                                dep = "VALLE DEL CAUCA";
                            }
                            r = con.s.executeQuery ("SELECT *\n" +
                                                "FROM\n" +
                                                "    t_obra\n" +
                                                "    INNER JOIN t_municipios \n" +
                                                "        ON (t_obra.ID_MUN_OBRA = t_municipios.ID_MUN)\n" +
                                                "    INNER JOIN t_departamentos \n" +
                                                "        ON (t_municipios.ID_DEP = t_departamentos.ID_DEP) WHERE NOMBRE_OBRA ='"+obra+"' AND NOMBRE_MUN ='"+mun+"' AND NOMBRE_DEP ='"+dep+"';");
                            if(r.next()){
                                id_obra=r.getInt("ID_OBRA");    
//                                r = con.s.executeQuery ("SELECT * FROM `t_barrio` WHERE (NOMBRE_BARRIO ='"+ctl.get(14).toString().replace("'","''").toUpperCase().trim()+"');");
//                                if(r.next()){
//                                    id_barrio=r.getInt("ID_BARRIO"); 
                                    r = con.s.executeQuery ("SELECT * FROM `t_municipios` WHERE (NOMBRE_MUN ='"+ctl.get(15).toString().replace("'","''").toUpperCase().trim()+"');");
                                    if(r.next()){
                                        id_municipio=r.getInt("ID_MUN");   
                                        r = con.s.executeQuery ("SELECT * FROM `t_parentesco` WHERE (NOMBRE_PAR ='"+ctl.get(19).toString().replace("'","''").toUpperCase().trim()+"');");
                                        if(r.next()){
                                            id_par_acud=r.getInt("ID_PAR");  
                                            if(ctl.get(4).equals("1900-01-01")){
                                                r = con.s.executeQuery ("SELECT * FROM `t_novedades` WHERE (ID_EMPLEADO ="+ctl.get(0).toString().replace("'","''").toUpperCase().trim()+" AND ID_EMPRESA='"+ctl.get(2).toString().replace("'","''").toUpperCase().trim()+"' AND FECHA_INGRESO='"+ctl.get(3).toString().replace("'","''").toUpperCase().trim()+"' AND FECHA_RETIRO='"+ctl.get(4).toString().replace("'","''").toUpperCase().trim()+"' AND ID_TIPO=1);");
                                                if(r.next()){
                                                    System.out.println(i +" Novedad de ingreso ya existe: "+ctl.get(0));
                                                }
                                                else{
                                                    //System.out.println("INSERT INTO `t_novedades` (ID_EMPLEADO,ID_EMPRESA,FECHA_INGRESO,FECHA_RETIRO,SALARIO_NOVEDAD,ID_EPS,ID_AFP,ID_ARL,ID_CCF,F_NAC_NOV,F_EXP_NOV,DIR_EMP_NOV,ID_BARRIO_NOV,ID_MUN_NOV,TEL_NOV,MAIL_NOV,ACUD_NOV,ID_PAR_ACU_NOV,TEL_ACUD_NOV,OBS_NOV,ID_OBRA,ID_TIPO,F_REGISTRO) VALUES ("+ctl.get(0).toString().replace("'","''").replace("Null","").toUpperCase().trim()+",'"+ctl.get(2).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(3).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(4).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"',"+ctl.get(5).toString().replace("'","''").replace("Null","").toUpperCase().trim()+","+id_eps+","+id_afp+",'"+id_arl+"','"+id_ccf+"','"+ctl.get(10).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(11).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(13).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(14).toString()+"',"+id_municipio+",'"+ctl.get(16).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(17).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(18).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"',"+id_par_acud+",'"+ctl.get(20).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(21).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"',"+id_obra+",1,'"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"')");
                                                    con.s.executeUpdate("INSERT INTO `t_novedades` (ID_EMPLEADO,ID_EMPRESA,FECHA_INGRESO,FECHA_RETIRO,SALARIO_NOVEDAD,ID_EPS,ID_AFP,ARL_NOV,CCF_NOV,F_NAC_NOV,F_EXP_NOV,DIR_EMP_NOV,BARRIO_NOV,ID_MUN_NOV,TEL_NOV,MAIL_NOV,ACUD_NOV,ID_PAR_ACU_NOV,TEL_ACUD_NOV,OBS_NOV,ID_OBRA,ID_TIPO,F_REGISTRO) VALUES ("+ctl.get(0).toString().replace("'","''").replace("Null","").toUpperCase().trim()+",'"+ctl.get(2).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(3).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(4).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"',"+ctl.get(5).toString().replace("'","''").replace("Null","").toUpperCase().trim()+","+id_eps+","+id_afp+",'"+id_arl+"','"+id_ccf+"','"+ctl.get(10).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(11).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(13).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(14).toString()+"',"+id_municipio+",'"+ctl.get(16).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(17).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(18).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"',"+id_par_acud+",'"+ctl.get(20).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(21).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"',"+id_obra+",1,'"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"')");
                                                }
                                            }
                                            else{
                                                r = con.s.executeQuery ("SELECT * FROM `t_novedades` WHERE (ID_EMPLEADO ="+ctl.get(0).toString().replace("'","''").toUpperCase()+" AND ID_EMPRESA='"+ctl.get(2).toString().replace("'","''").toUpperCase()+"' AND FECHA_INGRESO='"+ctl.get(3).toString().replace("'","''").toUpperCase()+"' AND FECHA_RETIRO='"+ctl.get(4).toString().replace("'","''").toUpperCase()+"' AND ID_TIPO=2);");
                                                if(r.next()){
                                                    System.out.println(i +" Novedad de retiro ya existe: "+ctl.get(0));
                                                }
                                                else{
                                                    //System.out.println("INSERT INTO `t_novedades` (ID_EMPLEADO,ID_EMPRESA,FECHA_INGRESO,FECHA_RETIRO,SALARIO_NOVEDAD,ID_EPS,ID_AFP,ID_ARL,ID_CCF,F_NAC_NOV,F_EXP_NOV,DIR_EMP_NOV,ID_BARRIO_NOV,ID_MUN_NOV,TEL_NOV,MAIL_NOV,ACUD_NOV,ID_PAR_ACU_NOV,TEL_ACUD_NOV,OBS_NOV,ID_OBRA,ID_TIPO,F_REGISTRO) VALUES ("+ctl.get(0).toString().replace("'","''").replace("Null","").toUpperCase().trim()+",'"+ctl.get(2).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(3).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(4).toString().replace("'","''").toUpperCase().trim()+"',"+ctl.get(5).toString().replace("'","''").replace("Null","").toUpperCase().trim()+","+id_eps+","+id_afp+",'"+id_arl+"','"+id_ccf+"','"+ctl.get(10).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(11).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(13).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(14).toString()+"',"+id_municipio+",'"+ctl.get(16).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(17).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(18).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"',"+id_par_acud+",'"+ctl.get(20).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(21).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"',"+id_obra+",2,'"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"')");
                                                    con.s.executeUpdate("INSERT INTO `t_novedades` (ID_EMPLEADO,ID_EMPRESA,FECHA_INGRESO,FECHA_RETIRO,SALARIO_NOVEDAD,ID_EPS,ID_AFP,ARL_NOV,CCF_NOV,F_NAC_NOV,F_EXP_NOV,DIR_EMP_NOV,BARRIO_NOV,ID_MUN_NOV,TEL_NOV,MAIL_NOV,ACUD_NOV,ID_PAR_ACU_NOV,TEL_ACUD_NOV,OBS_NOV,ID_OBRA,ID_TIPO,F_REGISTRO) VALUES ("+ctl.get(0).toString().replace("'","''").replace("Null","").toUpperCase().trim()+",'"+ctl.get(2).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(3).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(4).toString().replace("'","''").toUpperCase().trim()+"',"+ctl.get(5).toString().replace("'","''").replace("Null","").toUpperCase().trim()+","+id_eps+","+id_afp+",'"+id_arl+"','"+id_ccf+"','"+ctl.get(10).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(11).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(13).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(14).toString()+"',"+id_municipio+",'"+ctl.get(16).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(17).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(18).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"',"+id_par_acud+",'"+ctl.get(20).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"','"+ctl.get(21).toString().replace("'","''").replace("Null","").toUpperCase().trim()+"',"+id_obra+",2,'"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"')");
                                                }
                                            }
                                        }else{
                                            JOptionPane.showMessageDialog(parent,"Error al insertar el listado de Novedades\nEl PARENTESCO no existe\nPARENTESCO: "+ctl.get(19).toString().replace("'","''").toUpperCase()+"Linea :"+(i+1)+"\nPestaña Consolidado", "Error",JOptionPane.ERROR_MESSAGE);
                                            ret=false;
                                            break;
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(parent,"Error al insertar el listado de Novedades\nEl MUNICIPIO no existe\nMUNICIPIO: "+ctl.get(15).toString().replace("'","''").toUpperCase()+"Linea :"+(i+1)+"\nPestaña Consolidado", "Error",JOptionPane.ERROR_MESSAGE);
                                        ret=false;
                                        break;
                                    }
//                                }else{
//                                    JOptionPane.showMessageDialog(parent,"Error al insertar el listado de Novedades\nEl BARRIO no existe\nBARRIO: "+ctl.get(14).toString().replace("'","''").toUpperCase()+"Linea :"+(i+1)+"\nPestaña Consolidado", "Error",JOptionPane.ERROR_MESSAGE);
//                                    ret=false;
//                                    break;
//                                }
                            }else{
                                JOptionPane.showMessageDialog(parent,"Error al insertar el listado de Novedades\nLa OBRA no existe\nOBRA: "+obra+"Linea :"+(i+1)+"\nPestaña Consolidado", "Error",JOptionPane.ERROR_MESSAGE);
                                ret=false;
                                break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(parent,"Error al insertar el listado de Novedades\nLa CCF no existe\nCCF: "+ctl.get(9).toString().replace("'","''").toUpperCase()+"Linea :"+(i+1)+"\nPestaña Consolidado", "Error",JOptionPane.ERROR_MESSAGE);
                            ret=false;
                            break;
                        }
                    }else{
                        JOptionPane.showMessageDialog(parent,"Error al insertar el listado de Novedades\nLa ARL no existe\nARL: "+ctl.get(8).toString().replace("'","''").toUpperCase()+"Linea :"+(i+1)+"\nPestaña Consolidado", "Error",JOptionPane.ERROR_MESSAGE);
                        ret=false;
                        break;
                    }
                }else{
                    JOptionPane.showMessageDialog(parent,"Error al insertar el listado de Novedades\nLa AFP no existe\nAFP: "+ctl.get(7).toString().replace("'","''").toUpperCase()+"Linea :"+(i+1)+"\nPestaña Consolidado", "Error",JOptionPane.ERROR_MESSAGE);
                    ret=false;
                    break;
                }
            }else{
                JOptionPane.showMessageDialog(parent,"Error al insertar el listado de Novedades\nLa EPS no existe\nEPS: "+ctl.get(6).toString().replace("'","''").toUpperCase()+"Linea :"+(i+1)+"\nPestaña Consolidado", "Error",JOptionPane.ERROR_MESSAGE);
                ret=false;
                break;
            }
        }
    } catch (Exception ex) {
        con.cerrar();
        ex.printStackTrace();
        JOptionPane.showMessageDialog(parent,"Error al insertar el listado de Novedades. \n"+ex, "Error",JOptionPane.ERROR_MESSAGE);
    }
    con.cerrar();
    return ret;
}
}
