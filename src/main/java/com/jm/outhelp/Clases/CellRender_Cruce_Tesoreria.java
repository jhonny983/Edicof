/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.outhelp.Clases;

import java.awt.Color;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ADMIN
 */
public class CellRender_Cruce_Tesoreria extends DefaultTableCellRenderer{
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected,boolean hasFocus,int row,int column){
        JLabel cell = (JLabel) super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
        cell.setOpaque(true);
        cell.setBackground(Color.red);
        cell.setForeground(Color.white);
        if (table.getColumnName(column).equals("FIC Aplicado")) {
            cell.setOpaque(true);
            cell.setBackground(Color.red);
            cell.setForeground(Color.white);
            if (value!=null) {
                if(!value.toString().equals("")){
                    if (chech_char(value.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>")) {
                        if (comprobarFloat(value.toString().trim())) {
                            cell.setOpaque(true);
                            cell.setBackground(new Color(150,240,160));
                            cell.setForeground(Color.black);
                        }else{
                            cell.setOpaque(true);
                            cell.setBackground(Color.red);
                            cell.setForeground(Color.white);
                        }
                    }else{
                        cell.setOpaque(true);
                        cell.setBackground(Color.red);
                        cell.setForeground(Color.white);
                    }
                }
            }else{
                cell.setOpaque(true);
                cell.setBackground(Color.red);
                cell.setForeground(Color.white);
            }
        }
        if (hasFocus & isSelected) {
//            cell.setOpaque(true);
//            cell.setBackground(new Color(0,128,255));
//            cell.setForeground(Color.black);
            cell.setOpaque(true);
            cell.setBackground(Color.red);
            cell.setForeground(Color.white);
        }
    return this;
   }
public boolean check_cedula(Object ced){
    boolean ret=false;
    if (ced!=null) {
        if (chech_char(ced.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (comprobarLong(ced.toString().trim())) {
                if(!ced.toString().equals("")){
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r;
                    try{
                        r = con.s.executeQuery ("SELECT * FROM T_EMPLEADOS WHERE ID_EMP="+ced.toString().trim());
                        if(r.next()){
                            ret=true;
                        }
                    con.cerrar();
                    }catch(SQLException j){
                        con.cerrar();
                        j.printStackTrace();
                        return false;
                    }
                }
            }
        }
    }
    return ret;  
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
public boolean chech_char(String s, String c){
    //boolean ret=false;
    char []char_s=s.toCharArray();
    char []char_c=c.toCharArray();  
    for (int i = 0; i < char_s.length; i++) {
        for (int j = 0; j < char_c.length; j++) {
            if (char_s[i]==char_c[j]) {
                return false;
            }
        }
    }
    return true;
}

public boolean comprobarFloat (String cadena){
try{
    float num = Float.parseFloat(cadena);
}catch (Exception e){
    e.printStackTrace();
    return false;
}
return true;

}
public static boolean comprobarLong (String cadena){
try{
    long num = Long.parseLong(cadena);
}catch (Exception e){
    e.printStackTrace();
    return false;
}
return true;

}
}
