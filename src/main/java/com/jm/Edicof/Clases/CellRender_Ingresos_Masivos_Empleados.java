/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.Clases;

import java.awt.Color;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.StringTokenizer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ADMIN
 */
public class CellRender_Ingresos_Masivos_Empleados extends DefaultTableCellRenderer{
    public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected,boolean hasFocus,int row,int column){
        JLabel cell = (JLabel) super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
        if (table.getColumnName(column).equals("Tipo Ident*")) {
            if (check_tip_ident(value)) {
                cell.setOpaque(true);
                cell.setBackground(new Color(150,240,160));
                cell.setForeground(Color.black);
            }else{
                cell.setOpaque(true);
                cell.setBackground(Color.red);
                cell.setForeground(Color.white);
            }
        } else {
            if (table.getColumnName(column).equals("Id*")) {
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
                if (table.getColumnName(column).equals("Ciudad Expedicion Id*")) {
                    if (check_municipio(value)) {
                        cell.setOpaque(true);
                        cell.setBackground(new Color(150,240,160));
                        cell.setForeground(Color.black);
                    } else {
                        cell.setOpaque(true);
                        cell.setBackground(Color.red);
                        cell.setForeground(Color.white);
                    }
                } else {
                    if (table.getColumnName(column).equals("Fecha expedicion (DD-MM-AAAA)*")) {//Fecha expedicion (DD-MM-AAAA)*
                        if (check_fecha(value)) {
                            cell.setOpaque(true);
                            cell.setBackground(new Color(150,240,160));
                            cell.setForeground(Color.black);
                        } else {
                            cell.setOpaque(true);
                            cell.setBackground(Color.red);
                            cell.setForeground(Color.white);
                        }
                    } else {
                         if (table.getColumnName(column).equals("Nombre 1*")) {
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
                                if (table.getColumnName(column).equals("Apellido 1*")) {
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
                                    }else{
                                        if (table.getColumnName(column).equals("Tipo de sangre*")) {
                                            if (check_tip_sangre(value)) {
                                                cell.setOpaque(true);
                                                cell.setBackground(new Color(150,240,160));
                                                cell.setForeground(Color.black);
                                            } else {
                                                cell.setOpaque(true);
                                                cell.setBackground(Color.red);
                                                cell.setForeground(Color.white);
                                            }

                                        } else {
                                            if (table.getColumnName(column).equals("Género (M-F)*")) {
                                                if (check_genero(value)) {
                                                    cell.setOpaque(true);
                                                    cell.setBackground(new Color(150,240,160));
                                                    cell.setForeground(Color.black);
                                                } else {
                                                    cell.setOpaque(true);
                                                    cell.setBackground(Color.red);
                                                    cell.setForeground(Color.white);
                                                }
                                            } else {//Lugar nacimiento*
                                                if (table.getColumnName(column).equals("Lugar nacimiento*")) {
                                                    if (check_municipio(value)) {
                                                        cell.setOpaque(true);
                                                        cell.setBackground(new Color(150,240,160));
                                                        cell.setForeground(Color.black);
                                                    } else {
                                                        cell.setOpaque(true);
                                                        cell.setBackground(Color.red);
                                                        cell.setForeground(Color.white);
                                                    }
                                                } else {
                                                    if (table.getColumnName(column).equals("Fecha nacimiento (DD-MM-AAAA)*")) {//Fecha nacimiento (DD-MM-AAAA)*
                                                        if (check_fecha(value)) {
                                                            cell.setOpaque(true);
                                                            cell.setBackground(new Color(150,240,160));
                                                            cell.setForeground(Color.black);
                                                        } else {
                                                            cell.setOpaque(true);
                                                            cell.setBackground(Color.red);
                                                            cell.setForeground(Color.white);
                                                        }
                                                    } else {
                                                        if (table.getColumnName(column).equals("Estatura (cm)*")){//Estatura (cm)*
                                                            if (check_estat(value)) {
                                                                cell.setOpaque(true);
                                                                cell.setBackground(new Color(150,240,160));
                                                                cell.setForeground(Color.black);
                                                            } else {
                                                                cell.setOpaque(true);
                                                                cell.setBackground(Color.red);
                                                                cell.setForeground(Color.white);
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
        if (check_char(ced.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            ret=comprobarLong(ced.toString().trim());
        }
    }
    return ret;  
}
public boolean check_estat(Object ced){
        boolean ret=false;
        long num = 0;
        if (ced!=null) {
            if (check_char(ced.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
                if (!ced.toString().equals("")) {
                    try{
                        num = Long.parseLong(ced.toString());
                        return num > 0 && num <= 220;
                        
                    }catch (Exception e){
                        e.printStackTrace();
                        return false;
                    }
                    
                }
            }
        }
        return ret;  
    }
public boolean check_field (Object field){
boolean ret=false;
    if (field!=null) {
        if (check_char(field.toString().trim(),"'[]{};:<>,")) {
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
public boolean check_char(String s, String c){
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
public boolean check_tip_ident(Object tipo){
    boolean ret=false;
    if (tipo!=null) {
        if (check_char(tipo.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            Conexion con = new Conexion();
            con.conexion();
            ResultSet r;
            try{
                r = con.s.executeQuery ("SELECT *\n" +
                                        "FROM\n" +
                                        "    t_tipo_ident\n" +
                                        "where t_tipo_ident.NOMBRE_TIPO_IDENT ='"+tipo+"'");
                if(r.next()){
                    ret=true;
                }
                con.cerrar();
            }catch(SQLException j){
                con.cerrar();
                j.printStackTrace();
                JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    return ret;
    }
public boolean check_fecha(Object fecha){
    boolean ret=false;
    if (fecha!=null) {
        if (check_char(fecha.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!fecha.toString().trim().equals("") & count_char(fecha.toString().trim(),'-')==2) {
                int str_año=0;
                int str_mes=0;
                int str_dia=0;
                try {
                    StringTokenizer tokens=new StringTokenizer(fecha.toString().trim(),"-");
                    while(tokens.hasMoreTokens()){
                        str_dia=Integer.parseInt(tokens.nextToken().trim());
                        str_mes=Integer.parseInt(tokens.nextToken().trim());
                        str_año=Integer.parseInt(tokens.nextToken().trim());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                if (str_dia>0 & str_dia<=31) {
                    if (str_mes>0 & str_mes<=12) {
                        if (str_año>=1900 & str_año<=2050) {
                            Calendar ahoraCal = Calendar.getInstance();
                            ahoraCal.set(str_año,str_mes-1,1);
                            ahoraCal.set(Calendar.DATE, ahoraCal.getActualMaximum(Calendar.DATE));
                            int f = ahoraCal.get(Calendar.DAY_OF_MONTH);
                            if (str_dia<=f) {
                                ret=true;
                            }
                        }
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
public boolean check_tip_sangre(Object tipo){
    boolean ret=false;
    if (tipo!=null) {
        if (check_char(tipo.toString().trim(),"'#$%&()=?¡¿/*[]{};:<>,.")) {
            Conexion con = new Conexion();
            con.conexion();
            ResultSet r;
            try{
                r = con.s.executeQuery ("SELECT *\n" +
                                        "FROM\n" +
                                        "    t_tipo_sangre\n" +
                                        "where t_tipo_sangre.NOMBRE_TIPO ='"+tipo+"'");
                if(r.next()){
                    ret=true;
                }
                con.cerrar();
            }catch(SQLException j){
                con.cerrar();
                j.printStackTrace();
                JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    return ret;
    }
public boolean check_genero(Object genero){
    boolean ret=false;
    if (genero!=null) {
        if (check_char(genero.toString().trim(),"'#$%&()=?¡¿/*+-[]{};:<>,.")) {
            Conexion con = new Conexion();
            con.conexion();
            ResultSet r;
            try{
                r = con.s.executeQuery ("SELECT *\n" +
                                        "FROM\n" +
                                        "    t_genero\n" +
                                        "where t_genero.NOMBRE_GENERO ='"+genero+"'");
                if(r.next()){
                    ret=true;
                }
                con.cerrar();
            }catch(SQLException j){
                con.cerrar();
                j.printStackTrace();
                JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    return ret;
    }
public boolean check_municipio(Object municipio){
    boolean ret=false;
    if (municipio!=null) {
        if (check_char(municipio.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!municipio.toString().trim().equals("")& count_char(municipio.toString().trim(),'-')==1){
                String str_mun="";
                String str_dep="";
                StringTokenizer tokens=new StringTokenizer(municipio.toString().trim(), "-");
                while(tokens.hasMoreTokens()){
                    str_mun=tokens.nextToken().trim();
                    str_dep=tokens.nextToken().trim();
                }
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT *\n" +
                                            "FROM\n" +
                                            "    t_municipios\n" +
                                            "    INNER JOIN t_departamentos \n" +
                                            "        ON (t_municipios.ID_DEP = t_departamentos.ID_DEP) WHERE NOMBRE_MUN='"+str_mun+"' AND NOMBRE_DEP='"+str_dep+"'");
                    if(r.next()){
                        ret=true;
                    }
                    con.cerrar();
                }catch(SQLException j){
                    con.cerrar();
                    j.printStackTrace();
                    JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    return ret;
    }
}
