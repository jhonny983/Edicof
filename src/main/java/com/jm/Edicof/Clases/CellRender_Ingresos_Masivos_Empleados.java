/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.Clases;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ADMIN
 */
public class CellRender_Ingresos_Masivos_Empleados extends DefaultTableCellRenderer{
    public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected,boolean hasFocus,int row,int column){
        JLabel cell = (JLabel) super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
        if (table.getColumnName(column).equals("Cedula (*)")) {
            if (check_cedula(value)) {
                cell.setOpaque(true);
                cell.setBackground(new Color(150,240,160));
                cell.setForeground(Color.black);
            }else{
                cell.setOpaque(true);
                cell.setBackground(Color.red);
                cell.setForeground(Color.white);
            }
        }else{
            if (table.getColumnName(column).equals("Nombre 1 (*)")) {
                if (check_field(value)) {
                    cell.setOpaque(true);
                    cell.setBackground(new Color(150,240,160));
                    cell.setForeground(Color.black);
                }else{
                    cell.setOpaque(true);
                    cell.setBackground(Color.red);
                    cell.setForeground(Color.white);
                }
            }else{
                if (table.getColumnName(column).equals("Nombre 2")) {
                    cell.setOpaque(true);
                    cell.setBackground(new Color(150,240,160));
                    cell.setForeground(Color.black);
                }else{
                    if (table.getColumnName(column).equals("Apellido 1 (*)")) {
                        if (check_field(value)) {
                            cell.setOpaque(true);
                            cell.setBackground(new Color(150,240,160));
                            cell.setForeground(Color.black);
                        }else{
                            cell.setOpaque(true);
                            cell.setBackground(Color.red);
                            cell.setForeground(Color.white);
                        }
                    }else{
                        if (table.getColumnName(column).equals("Apellido 2")) {
                            cell.setOpaque(true);
                            cell.setBackground(new Color(150,240,160));
                            cell.setForeground(Color.black);
                        }
                    }
                }
            }
        }
        if (hasFocus | isSelected) {
            cell.setOpaque(true);
            cell.setBackground(new Color(0,128,255));
            cell.setForeground(Color.black);
        }
    return this;
   }
public boolean check_cedula(Object ced){
    boolean ret=false;
    if (ced!=null) {
        if (chech_char(ced.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            ret=comprobarLong(ced.toString().trim());
        }
    }
    return ret;  
}
public boolean check_field (Object field){
boolean ret=false;
    if (field!=null) {
        if (chech_char(field.toString().trim(),"'[]{};:<>,")) {
            if (!field.toString().equals("")) {
               ret=true;
            }
        }
    }

return ret;
}
public static boolean comprobarLong (String cadena){
try{
    long num = Long.parseLong(cadena);
}catch (Exception e){
    return false;
}
return true;

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
}
