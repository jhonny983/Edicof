package com.jm.outhelp.Clases.Test;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;

public class XLCellDropDown {
static String RGSUCURSALES = "OBRAS_CBOLIVAR_AB";
static String CENTROTRABAJO = "9999-ADMINISTRATIVO 0.522%\n"
            + "1-CENTRO TRABAJO 1\n"
            + "9901-TODAS LAS OBRAS CALI - CONSTRUCCIÓN 6.96%\n"
            + "0104-ZAGUAN DE LAS FLORES - CONSTRUCCION 6.96%";
static String RGTIPOSCOT = "1. DEPENDIENTE\n" +
            "12. APRENDICES EN ETAPA LECTIVA\n" +
            "15. DESEMPLEADO CON SUBSIDIO DE CAJA DE COMPENSACIÓN FAMILIAR\n" +
            "16. INDEPENDIENTE AGREMIADO O ASOCIADO\n" +
            "18. FUNCIONARIOS PÚBLICOS SIN TOPE MÁXIMO EN EL IBC\n" +
            "19. APRENDICES EN ETAPA PRODUCTIVA\n" +
            "20. ESTUDIANTES\n" +
            "21. ESTUDIANTES DE POSGRADO EN SALUD\n" +
            "22. PROFESOR DE ESTABLECIMIENTO PARTICULAR\n" +
            "23. ESTUDIANTES DECRETO 055\n" +
            "3. INDEPENDIENTE\n" +
            "30. DEPENDIENTE ENTIDADES O UNIVERSIDADES PÚBLICAS CON RÉGIMEN ESPECIAL EN SALUD\n" +
            "31. COOPERADO\n" +
            "32. DIPLOMÁTICO, CONSULAR O FUNCIONARIO NO SOMETIDO A LEGISLACIÓN COLOMBIANA\n" +
            "34. CONCEJAL O EDIL DE BOGOTÁ D.C.\n" +
            "35. CONCEJAL NO AMPARADO CON PÓLIZA DE SALUD\n" +
            "36. CONCEJAL O EDIL SIN PÓLIZA DE SALUD BENEFICIARIO DEL FSP\n" +
            "4. MADRE SUSTITUTA\n" +
            "40. BENEFICIARIO DE UPC ADICIONAL\n" +
            "43. COTIZANTE VOLUNTARIO A PENSIONES CON PAGO POR TERCERO\n" +
            "44. EMPLEO DE EMERGENCIA MAYOR A UN MES\n" +
            "45. EMPLEO DE EMERGENCIA MENOR A UN MES\n" +
            "47. DEPENDIENTE BENEFICIARIO DEL SISTEMA GENERAL DE PARTICIPACIONES\n" +
            "51. TRABAJADOR DE TIEMPO PARCIAL\n" +
            "53. AFILIADO PARTICIPE\n" +
            "54. PREPENSIONADO DE ENTIDAD EN LIQUIDACIÓN\n" +
            "55. AFILIADO PARTICIPE – DEPENDIENTE\n" +
            "57. INDEPENDIENTE VOLUNTARIO AL SISTEMA DE RIESGOS LABORALES\n" +
            "59. INDEPENDIENTE CON CONTRATO DE PRESTACIÓN DE SERVICIOS SUPERIOR A 1 MES\n" +
            "60. EDIL NO BENEFICIARIO DEL FSP";
static String RGGRUPOPOB = "NINGUNO\n" +
            "CONCEJALES I\n" +
            "CONCEJALES II\n" +
            "DESEMPLEADOS\n" +
            "DESOCUPADOS\n" +
            "DISCAPACITADOS\n" +
            "INDEPENDIENTES RURALES I\n" +
            "INDEPENDIENTES RURALES II\n" +
            "INDEPENDIENTES RURALES III\n" +
            "INDEPENDIENTES URBANOS I\n" +
            "INDEPENDIENTES URBANOS II\n" +
            "INDEPENDIENTES URBANOS III\n" +
            "MADRE COMUNITARIA";
static String RGSUBTIPOSCOT = "NINGUNO\n" +
            "1. DEPENDIENTE PENSIONADO ACTIVO\n" +
            "2. INDEPENDIENTE PENSIONADO ACTIVO\n" +
            "3. COTIZANTE NO OBLIGADO A COTIZACIÓN A PENSIONES POR EDAD\n" +
            "4. COTIZANTE CON REQUISITOS CUMPLIDOS PARA PENSIÓN\n" +
            "5. COTIZANTE CON INDEMNIZACIÓN SUSTITUTIVA O DEVOLUCIÓN DE SALDOS\n" +
            "6. COTIZANTE PERTENECIENTE A UN RÉGIMEN EXCEPTUADO DE PENSIONES\n" +
            "9. PENSIONADO CON MESADA IGUAL A 25 SMLMV\n" +
            "10. COLOMBIANO RESIDENTE EN EL EXTERIOR\n" +
            "11. TAXISTA\n" +
            "12. TAXISTA NO OBLIGADOS A COTIZAR PENSIÓN";
static String SI_NO = "SI\n"
            + "NO";
static String RGTARIFAARL = "NINGUNA\n" +
            "0,52%\n" +
            "1,04%\n" +
            "2,44%\n" +
            "4,35%\n" +
            "6,96%";
static String RGTARIFACCF = "NINGUNA\n" +
            "0,60%\n" +
            "2,00%\n" +
            "4,00%";
static String RGALTORIESGO = "SIN RIESGO\n" +
            "ALTO RIESGO\n" +
            "CTI\n" +
            "ALTO RIESGO (6%)\n" +
            "ALTO RIESGO (8.5%)\n" +
            "AVIADORES\n" +
            "SENADORES";
public static void main(String args[]) throws FileNotFoundException {
    try {
        String path = "E:\\Documents\\NOVEDADES_PILA_2017-10-01_A_2017-10-31/900419544-CONSTRUMET DEL CARIBE S.A.S_.xls";
        FileInputStream fis = new FileInputStream(path);
        Workbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet = (HSSFSheet)workbook.getSheetAt(workbook.getSheetIndex("Novedades_Pila"));
        HSSFSheet hidden = (HSSFSheet)workbook.createSheet("Data_Validation");
        for (int i = 0, length= RGSUCURSALES.split("\n").length; i < length; i++) {
            String name = RGSUCURSALES.split("\n")[i];
            HSSFRow row;
            if (hidden.getRow(i)==null) {
                row = hidden.createRow(i);
            }else{
                row = hidden.getRow(i);
            }
            HSSFCell cell = row.createCell(0);
            cell.setCellValue(name);
        }
        Name namedCell_RGSUCURSALES = workbook.createName();
        namedCell_RGSUCURSALES.setNameName("RGSUCURSALES");
        namedCell_RGSUCURSALES.setRefersToFormula("Data_Validation!$A$1:$A$" + RGSUCURSALES.split("\n").length);
        DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint("RGSUCURSALES");
        CellRangeAddressList addressList = new CellRangeAddressList(2, 2002, 8, 8);
        DataValidation dataValidation = new HSSFDataValidation(addressList,dvConstraint);
        dataValidation.setSuppressDropDownArrow(false);
        sheet.addValidationData(dataValidation);
        for (int i = 0, length= CENTROTRABAJO.split("\n").length; i < length; i++) {
            String name = CENTROTRABAJO.split("\n")[i];
            HSSFRow row;
            if (hidden.getRow(i)==null) {
                row = hidden.createRow(i);
            }else{
                row = hidden.getRow(i);
            }
            HSSFCell cell = row.createCell(1);
            cell.setCellValue(name);
        }
        Name namedCell_CENTROTRABAJO = workbook.createName();
        namedCell_CENTROTRABAJO.setNameName("CENTROTRABAJO");
        namedCell_CENTROTRABAJO.setRefersToFormula("Data_Validation!$B$1:$B$" + CENTROTRABAJO.split("\n").length);
        dvConstraint = DVConstraint.createFormulaListConstraint("CENTROTRABAJO");
        addressList = new CellRangeAddressList(2, 2002, 9, 9);
        dataValidation = new HSSFDataValidation(addressList,dvConstraint);
        dataValidation.setSuppressDropDownArrow(false);
        sheet.addValidationData(dataValidation);
        for (int i = 0, length= RGTIPOSCOT.split("\n").length; i < length; i++) {
            String name = RGTIPOSCOT.split("\n")[i];
            HSSFRow row;
            if (hidden.getRow(i)==null) {
                row = hidden.createRow(i);
            }else{
                row = hidden.getRow(i);
            }
            HSSFCell cell = row.createCell(2);
            cell.setCellValue(name);
        }
        Name namedCell_RGTIPOSCOT = workbook.createName();
        namedCell_RGTIPOSCOT.setNameName("RGTIPOSCOT");
        namedCell_RGTIPOSCOT.setRefersToFormula("Data_Validation!$C$1:$C$" + RGTIPOSCOT.split("\n").length);
        dvConstraint = DVConstraint.createFormulaListConstraint("RGTIPOSCOT");
        addressList = new CellRangeAddressList(2, 2002, 10, 10);
        dataValidation = new HSSFDataValidation(addressList,dvConstraint);
        dataValidation.setSuppressDropDownArrow(false);
        sheet.addValidationData(dataValidation);
        for (int i = 0, length= RGGRUPOPOB.split("\n").length; i < length; i++) {
            String name = RGGRUPOPOB.split("\n")[i];
            HSSFRow row;
            if (hidden.getRow(i)==null) {
                row = hidden.createRow(i);
            }else{
                row = hidden.getRow(i);
            }
            HSSFCell cell = row.createCell(3);
            cell.setCellValue(name);
        }
        Name namedCell_RGGRUPOPOB = workbook.createName();
        namedCell_RGGRUPOPOB.setNameName("RGGRUPOPOB");
        namedCell_RGGRUPOPOB.setRefersToFormula("Data_Validation!$D$1:$D$" + RGGRUPOPOB.split("\n").length);
        dvConstraint = DVConstraint.createFormulaListConstraint("RGGRUPOPOB");
        addressList = new CellRangeAddressList(2, 2002, 11, 11);
        dataValidation = new HSSFDataValidation(addressList,dvConstraint);
        dataValidation.setSuppressDropDownArrow(false);
        sheet.addValidationData(dataValidation);
        for (int i = 0, length= RGSUBTIPOSCOT.split("\n").length; i < length; i++) {
            String name = RGSUBTIPOSCOT.split("\n")[i];
            HSSFRow row;
            if (hidden.getRow(i)==null) {
                row = hidden.createRow(i);
            }else{
                row = hidden.getRow(i);
            }
            HSSFCell cell = row.createCell(4);
            cell.setCellValue(name);
        }
        Name namedCell_RGSUBTIPOSCOT = workbook.createName();
        namedCell_RGSUBTIPOSCOT.setNameName("RGSUBTIPOSCOT");
        namedCell_RGSUBTIPOSCOT.setRefersToFormula("Data_Validation!$E$1:$E$" + RGSUBTIPOSCOT.split("\n").length);
        dvConstraint = DVConstraint.createFormulaListConstraint("RGSUBTIPOSCOT");
        addressList = new CellRangeAddressList(2, 2002, 12, 12);
        dataValidation = new HSSFDataValidation(addressList,dvConstraint);
        dataValidation.setSuppressDropDownArrow(false);
        sheet.addValidationData(dataValidation);
        for (int i = 0, length= SI_NO.split("\n").length; i < length; i++) {
            String name = SI_NO.split("\n")[i];
            HSSFRow row;
            if (hidden.getRow(i)==null) {
                row = hidden.createRow(i);
            }else{
                row = hidden.getRow(i);
            }
            HSSFCell cell = row.createCell(5);
            cell.setCellValue(name);
        }
        Name namedCell_RGSINO = workbook.createName();
        namedCell_RGSINO.setNameName("RGSINO");
        namedCell_RGSINO.setRefersToFormula("Data_Validation!$F$1:$F$" + SI_NO.split("\n").length);
        dvConstraint = DVConstraint.createFormulaListConstraint("RGSINO");
        addressList = new CellRangeAddressList(2, 2002, 13, 13);
        CellRangeAddressList ddressList_1 = new CellRangeAddressList(2, 2002, 14, 14);
        CellRangeAddressList ddressList_2 = new CellRangeAddressList(2, 2002, 15, 15);
        CellRangeAddressList ddressList_3 = new CellRangeAddressList(2, 2002, 19, 19);
        CellRangeAddressList ddressList_4 = new CellRangeAddressList(2, 2002, 22, 22);
        CellRangeAddressList ddressList_5 = new CellRangeAddressList(2, 2002, 29, 29);
        dataValidation = new HSSFDataValidation(addressList,dvConstraint);
        DataValidation dataValidation_1 = new HSSFDataValidation(ddressList_1,dvConstraint);
        DataValidation dataValidation_2 = new HSSFDataValidation(ddressList_2,dvConstraint);
        DataValidation dataValidation_3 = new HSSFDataValidation(ddressList_3,dvConstraint);
        DataValidation dataValidation_4 = new HSSFDataValidation(ddressList_4,dvConstraint);
        DataValidation dataValidation_5 = new HSSFDataValidation(ddressList_5,dvConstraint);
        dataValidation.setSuppressDropDownArrow(false);
        dataValidation_1.setSuppressDropDownArrow(false);
        dataValidation_2.setSuppressDropDownArrow(false);
        dataValidation_3.setSuppressDropDownArrow(false);
        dataValidation_4.setSuppressDropDownArrow(false);
        dataValidation_5.setSuppressDropDownArrow(false);
        sheet.addValidationData(dataValidation);
        sheet.addValidationData(dataValidation_1);
        sheet.addValidationData(dataValidation_2);
        sheet.addValidationData(dataValidation_3);
        sheet.addValidationData(dataValidation_4);
        sheet.addValidationData(dataValidation_5);
        for (int i = 0, length= RGTARIFAARL.split("\n").length; i < length; i++) {
            String name = RGTARIFAARL.split("\n")[i];
            HSSFRow row;
            if (hidden.getRow(i)==null) {
                row = hidden.createRow(i);
            }else{
                row = hidden.getRow(i);
            }
            HSSFCell cell = row.createCell(6);
            cell.setCellValue(name);
        }
        Name namedCell_RGTARIFAARL = workbook.createName();
        namedCell_RGTARIFAARL.setNameName("RGTARIFAARL");
        namedCell_RGTARIFAARL.setRefersToFormula("Data_Validation!$G$1:$G$" + RGTARIFAARL.split("\n").length);
        dvConstraint = DVConstraint.createFormulaListConstraint("RGTARIFAARL");
        addressList = new CellRangeAddressList(2, 2002, 21, 21);
        dataValidation = new HSSFDataValidation(addressList,dvConstraint);
        dataValidation.setSuppressDropDownArrow(false);
        sheet.addValidationData(dataValidation);
        for (int i = 0, length= RGTARIFACCF.split("\n").length; i < length; i++) {
            String name = RGTARIFACCF.split("\n")[i];
            HSSFRow row;
            if (hidden.getRow(i)==null) {
                row = hidden.createRow(i);
            }else{
                row = hidden.getRow(i);
            }
            HSSFCell cell = row.createCell(7);
            cell.setCellValue(name);
        }
        Name namedCell_RGTARIFACCF = workbook.createName();
        namedCell_RGTARIFACCF.setNameName("RGTARIFACCF");
        namedCell_RGTARIFACCF.setRefersToFormula("Data_Validation!$H$1:$H$" + RGTARIFACCF.split("\n").length);
        dvConstraint = DVConstraint.createFormulaListConstraint("RGTARIFACCF");
        addressList = new CellRangeAddressList(2, 2002, 23, 23);
        dataValidation = new HSSFDataValidation(addressList,dvConstraint);
        dataValidation.setSuppressDropDownArrow(false);
        sheet.addValidationData(dataValidation);
        for (int i = 0, length= RGALTORIESGO.split("\n").length; i < length; i++) {
            String name = RGALTORIESGO.split("\n")[i];
            HSSFRow row;
            if (hidden.getRow(i)==null) {
                row = hidden.createRow(i);
            }else{
                row = hidden.getRow(i);
            }
            HSSFCell cell = row.createCell(8);
            cell.setCellValue(name);
        }
        Name namedCell_RGALTORIESGO = workbook.createName();
        namedCell_RGALTORIESGO.setNameName("RGALTORIESGO");
        namedCell_RGALTORIESGO.setRefersToFormula("Data_Validation!$I$1:$I$" + RGALTORIESGO.split("\n").length);
        dvConstraint = DVConstraint.createFormulaListConstraint("RGALTORIESGO");
        addressList = new CellRangeAddressList(2, 2002, 24, 24);
        dataValidation = new HSSFDataValidation(addressList,dvConstraint);
        dataValidation.setSuppressDropDownArrow(false);
        sheet.addValidationData(dataValidation);
//        Name namedCell = workbook.createName();
//        namedCell.setNameName("hidden");
//        namedCell.setRefersToFormula("Data_Validation!$A$1:$A$" + RGSUCURSALES.split("\n").length);
//        DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint("hidden");
//        CellRangeAddressList addressList = new CellRangeAddressList(2, 2002, 8, 9);
//        DataValidation dataValidation = new HSSFDataValidation(addressList,dvConstraint);
        //workbook.setSheetHidden(1, true);
        dataValidation.setSuppressDropDownArrow(false);
        sheet.addValidationData(dataValidation);
        FileOutputStream fileOut = new FileOutputStream("E:/XLCellDropDown.xls");
        //FileOutputStream fileOut = new FileOutputStream(path);
        workbook.write(fileOut);
        fileOut.close();

    } catch (IOException ex) {
        ex.printStackTrace();
            //Logger.getLogger(XLCellDropDown.class.getName()).log(Level.SEVERE, null, ex);
    }
}
}