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
public class CellRender_Info_Sociodemografica extends DefaultTableCellRenderer{
    public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected,boolean hasFocus,int row,int column){
        JLabel cell = (JLabel) super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
        
        if (table.getColumnName(column).equals("Id*")) {
            if (Validations.check_cedula(value)) {
                cell.setOpaque(true);
                cell.setBackground(new Color(150,240,160));
                cell.setForeground(Color.black);
            }else{
                cell.setOpaque(true);
                cell.setBackground(Color.red);
                cell.setForeground(Color.white);
            }
        }else{
            if (table.getColumnName(column).equals("Direccion*")) {
                if (Validations.check_field_dir(value)) {
                    cell.setOpaque(true);
                    cell.setBackground(new Color(150,240,160));
                    cell.setForeground(Color.black);
                }else{
                    cell.setOpaque(true);
                    cell.setBackground(Color.red);
                    cell.setForeground(Color.white);
                }
            }else{
                if (table.getColumnName(column).equals("Tel/Cel*")) {
                    if (Validations.check_tel_field(value)) {
                        cell.setOpaque(true);
                        cell.setBackground(new Color(150,240,160));
                        cell.setForeground(Color.black);
                    }else{
                        cell.setOpaque(true);
                        cell.setBackground(Color.red);
                        cell.setForeground(Color.white);
                    }
                }else{
                    if (table.getColumnName(column).equals("Municipio Residencia*")) {
                        if ( Validations.check_municipio(value)) {
                            cell.setOpaque(true);
                            cell.setBackground(new Color(150,240,160));
                            cell.setForeground(Color.black);
                        }else{
                            cell.setOpaque(true);
                            cell.setBackground(Color.red);
                            cell.setForeground(Color.white);
                        }
                    }else{
                        if (table.getColumnName(column).equals("Barrio*")) {
                            if (Validations.check_barrio_info(value)) {
                                cell.setOpaque(true);
                                cell.setBackground(new Color(150,240,160));
                                cell.setForeground(Color.black);
                            }else{
                                cell.setOpaque(true);
                                cell.setBackground(Color.red);
                                cell.setForeground(Color.white);
                            }
                        }else{
                            if (table.getColumnName(column).equals("Estrato*")) {
                                if (Validations.check_estrato(value)) {
                                    cell.setOpaque(true);
                                    cell.setBackground(new Color(150,240,160));
                                    cell.setForeground(Color.black);
                                }else{
                                    cell.setOpaque(true);
                                    cell.setBackground(Color.red);
                                    cell.setForeground(Color.white);
                                }
                            }else{
                                if (table.getColumnName(column).equals("Tipo Vivienda*")) {
                                    if (Validations.check_tip_vivienda(value)) {
                                        cell.setOpaque(true);
                                        cell.setBackground(new Color(150,240,160));
                                        cell.setForeground(Color.black);
                                    }else{
                                        cell.setOpaque(true);
                                        cell.setBackground(Color.red);
                                        cell.setForeground(Color.white);
                                    }
                                }else{
                                    if (table.getColumnName(column).equals("Area Trabajo*")) {
                                        if (Validations.check_tip_area(value)) {
                                            cell.setOpaque(true);
                                            cell.setBackground(new Color(150,240,160));
                                            cell.setForeground(Color.black);
                                        }else{
                                            cell.setOpaque(true);
                                            cell.setBackground(Color.red);
                                            cell.setForeground(Color.white);
                                        }
                                    }else{
                                        if (table.getColumnName(column).equals("Cargo*")) {
                                            if (Validations.check_cargo(value)) {
                                                cell.setOpaque(true);
                                                cell.setBackground(new Color(150,240,160));
                                                cell.setForeground(Color.black);
                                            }else{
                                                cell.setOpaque(true);
                                                cell.setBackground(Color.red);
                                                cell.setForeground(Color.white);
                                            }
                                        }else{
                                            if (table.getColumnName(column).equals("Estado Civil*")) {
                                                if (Validations.check_estado_civil(value)) {
                                                    cell.setOpaque(true);
                                                    cell.setBackground(new Color(150,240,160));
                                                    cell.setForeground(Color.black);
                                                }else{
                                                    cell.setOpaque(true);
                                                    cell.setBackground(Color.red);
                                                    cell.setForeground(Color.white);
                                                }
                                            }else{
                                                if (table.getColumnName(column).equals("No Hijos*")) {
                                                    if (Validations.check_integer(value)) {
                                                        cell.setOpaque(true);
                                                        cell.setBackground(new Color(150,240,160));
                                                        cell.setForeground(Color.black);
                                                    }else{
                                                        cell.setOpaque(true);
                                                        cell.setBackground(Color.red);
                                                        cell.setForeground(Color.white);
                                                    }
                                                }else{
                                                    if (table.getColumnName(column).equals("Nivel Academico*")) {
                                                        if (Validations.check_niv_acad(value)) {
                                                            cell.setOpaque(true);
                                                            cell.setBackground(new Color(150,240,160));
                                                            cell.setForeground(Color.black);
                                                        }else{
                                                            cell.setOpaque(true);
                                                            cell.setBackground(Color.red);
                                                            cell.setForeground(Color.white);
                                                        }
                                                    }else{
                                                        if (table.getColumnName(column).equals("Nacionalidad*")) {
                                                            if (Validations.check_nacion(value)) {
                                                                cell.setOpaque(true);
                                                                cell.setBackground(new Color(150,240,160));
                                                                cell.setForeground(Color.black);
                                                            }else{
                                                                cell.setOpaque(true);
                                                                cell.setBackground(Color.red);
                                                                cell.setForeground(Color.white);
                                                            }
                                                        }else{
                                                            if (table.getColumnName(column).equals("Personas a Cargo*")) {
                                                                if (Validations.check_integer(value)) {
                                                                    cell.setOpaque(true);
                                                                    cell.setBackground(new Color(150,240,160));
                                                                    cell.setForeground(Color.black);
                                                                }else{
                                                                    cell.setOpaque(true);
                                                                    cell.setBackground(Color.red);
                                                                    cell.setForeground(Color.white);
                                                                }
                                                            }else{
                                                                if (table.getColumnName(column).equals("Personas en el Hogar*")) {
                                                                    if (Validations.check_integer(value)) {
                                                                        cell.setOpaque(true);
                                                                        cell.setBackground(new Color(150,240,160));
                                                                        cell.setForeground(Color.black);
                                                                    }else{
                                                                        cell.setOpaque(true);
                                                                        cell.setBackground(Color.red);
                                                                        cell.setForeground(Color.white);
                                                                    }
                                                                }else{
                                                                    if (table.getColumnName(column).equals("Aportantes Hogar*")) {
                                                                        if (Validations.check_integer(value)) {
                                                                            cell.setOpaque(true);
                                                                            cell.setBackground(new Color(150,240,160));
                                                                            cell.setForeground(Color.black);
                                                                        }else{
                                                                            cell.setOpaque(true);
                                                                            cell.setBackground(Color.red);
                                                                            cell.setForeground(Color.white);
                                                                        }
                                                                    }else{
                                                                        if (table.getColumnName(column).equals("Nombre Acudiente*")) {
                                                                            if (Validations.check_field(value)) {
                                                                                cell.setOpaque(true);
                                                                                cell.setBackground(new Color(150,240,160));
                                                                                cell.setForeground(Color.black);
                                                                            }else{
                                                                                cell.setOpaque(true);
                                                                                cell.setBackground(Color.red);
                                                                                cell.setForeground(Color.white);
                                                                            }
                                                                        }else{
                                                                            if (table.getColumnName(column).equals("Tel/Cel Acudiente*")) {
                                                                                if (Validations.check_tel_field(value)) {
                                                                                    cell.setOpaque(true);
                                                                                    cell.setBackground(new Color(150,240,160));
                                                                                    cell.setForeground(Color.black);
                                                                                }else{
                                                                                    cell.setOpaque(true);
                                                                                    cell.setBackground(Color.red);
                                                                                    cell.setForeground(Color.white);
                                                                                }
                                                                            }else{
                                                                                if (table.getColumnName(column).equals("Parentesco Acudiente*")) {
                                                                                    if (Validations.check_parentesco(value)) {
                                                                                        cell.setOpaque(true);
                                                                                        cell.setBackground(new Color(150,240,160));
                                                                                        cell.setForeground(Color.black);
                                                                                    }else{
                                                                                        cell.setOpaque(true);
                                                                                        cell.setBackground(Color.red);
                                                                                        cell.setForeground(Color.white);
                                                                                    }
                                                                                }else{
                                                                                    if (table.getColumnName(column).equals("Productos Financieros*")) {
                                                                                        if (Validations.check_prod_fin(value)) {
                                                                                            cell.setOpaque(true);
                                                                                            cell.setBackground(new Color(150,240,160));
                                                                                            cell.setForeground(Color.black);
                                                                                        }else{
                                                                                            cell.setOpaque(true);
                                                                                            cell.setBackground(Color.red);
                                                                                            cell.setForeground(Color.white);
                                                                                        }
                                                                                    }else{
                                                                                        if (table.getColumnName(column).equals("Tiempo Libre*")) {
                                                                                            if (Validations.check_tiempo_libre(value)) {
                                                                                                cell.setOpaque(true);
                                                                                                cell.setBackground(new Color(150,240,160));
                                                                                                cell.setForeground(Color.black);
                                                                                            }else{
                                                                                                cell.setOpaque(true);
                                                                                                cell.setBackground(Color.red);
                                                                                                cell.setForeground(Color.white);
                                                                                            }
                                                                                        }else{
                                                                                            if (table.getColumnName(column).equals("Servicios Publicos*")) {
                                                                                                if (Validations.check_serv_public(value)) {
                                                                                                    cell.setOpaque(true);
                                                                                                    cell.setBackground(new Color(150,240,160));
                                                                                                    cell.setForeground(Color.black);
                                                                                                }else{
                                                                                                    cell.setOpaque(true);
                                                                                                    cell.setBackground(Color.red);
                                                                                                    cell.setForeground(Color.white);
                                                                                                }
                                                                                            }else{
                                                                                                if (table.getColumnName(column).equals("Ahorro*")) {
                                                                                                    if (Validations.check_field_ahorro(value)) {
                                                                                                        cell.setOpaque(true);
                                                                                                        cell.setBackground(new Color(150,240,160));
                                                                                                        cell.setForeground(Color.black);
                                                                                                    }else{
                                                                                                        cell.setOpaque(true);
                                                                                                        cell.setBackground(Color.red);
                                                                                                        cell.setForeground(Color.white);
                                                                                                    }
                                                                                                }else{
                                                                                                    if (table.getColumnName(column).equals("Fuente Ahorro*")) {
                                                                                                        if (Validations.check_fte_ahorro(value)) {
                                                                                                            cell.setOpaque(true);
                                                                                                            cell.setBackground(new Color(150,240,160));
                                                                                                            cell.setForeground(Color.black);
                                                                                                        }else{
                                                                                                            cell.setOpaque(true);
                                                                                                            cell.setBackground(Color.red);
                                                                                                            cell.setForeground(Color.white);
                                                                                                        }
                                                                                                    }else{

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
            }
        }
        if (hasFocus & isSelected) {
            cell.setOpaque(true);
            cell.setBackground(new Color(0,128,255));
            cell.setForeground(Color.black);
        }
    return this;
   }
/*    
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
public boolean check_empleador(Object emp){
    boolean ret=false;
    if (emp!=null) {
        if (chech_char(emp.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,")) {
            if (!emp.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT * FROM T_EMPRESAS WHERE NOMBRE_EMPRESA='"+emp.toString().trim()+"'");
                    if(r.next()){
                        ret=true;
                    }
                    con.cerrar();
                }catch(SQLException j){
                    con.cerrar();
                    j.printStackTrace();
                    return false;
                    //JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
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
public boolean check_fecha(Object fecha){
    boolean ret=false;
    if (fecha!=null) {
        if (chech_char(fecha.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!fecha.toString().trim().equals("") & count_char(fecha.toString().trim(),'-')==2 & fecha.toString().length()==10) {
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
            }else{
                ret=false;
            }
        }
    }
    return ret;
}
public boolean check_salario(Object salario){
    boolean ret=false;
    if (salario!=null) {
        if (chech_char(salario.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!salario.toString().equals("")) {
                if ( comprobarFloat(salario.toString())) {
                    ret = true;
                }
            }
        }
    }
return ret;
}
public boolean check_obra(Object obra){
    boolean ret=false;
    if (obra!=null) {
        if (chech_char(obra.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!obra.toString().trim().equals("") & count_char(obra.toString().trim(),'-')==2) {
                String str_obra="";
                String str_mun="";
                String str_dep="";
                StringTokenizer tokens=new StringTokenizer(obra.toString().trim(), "-");
                while(tokens.hasMoreTokens()){
                    str_obra=tokens.nextToken().trim();
                    str_mun=tokens.nextToken().trim();
                    str_dep=tokens.nextToken().trim();
                }
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT *\n" +
                                            "FROM\n" +
                                            "    t_obra\n" +
                                            "    INNER JOIN t_municipios \n" +
                                            "        ON (t_obra.ID_MUN_OBRA = t_municipios.ID_MUN)\n" +
                                            "    INNER JOIN t_departamentos \n" +
                                            "        ON (t_municipios.ID_DEP = t_departamentos.ID_DEP) WHERE NOMBRE_OBRA = '"+str_obra+"' AND NOMBRE_MUN ='"+str_mun+"' AND NOMBRE_DEP='"+str_dep+"'");
                    if(r.next()){
                        ret=true;
                    }
                    con.cerrar();
                }catch(SQLException j){
                    con.cerrar();
                    j.printStackTrace();
                    return false;
                    //JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    return ret;
}
public boolean check_barrio(Object barrio){
    boolean ret=false;
    if (barrio!=null) {
        if (chech_char(barrio.toString().trim(),"'$%&()=?¡¿/*+[]{};:<>,")) {
            if (!barrio.toString().equals("") & count_char(barrio.toString().trim(),'-')==1) {
                String str_barrio="";
                String str_mun="";
                StringTokenizer tokens=new StringTokenizer(barrio.toString().trim(), "-");
                while(tokens.hasMoreTokens()){
                    str_barrio=tokens.nextToken().trim();
                    str_mun=tokens.nextToken().trim();
                }
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT *\n" +
                                            "FROM\n" +
                                            "    `t_barrio`\n" +
                                            "    INNER JOIN `t_municipios` \n" +
                                            "        ON (`t_barrio`.`ID_MUN_BARRIO` = `t_municipios`.`ID_MUN`) WHERE NOMBRE_BARRIO='"+str_barrio+"' AND NOMBRE_MUN='"+str_mun+"'");
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
public boolean check_municipio(Object municipio){
    boolean ret=false;
    if (municipio!=null) {
        if (chech_char(municipio.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
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
public boolean check_parentesco(Object par){
    boolean ret=false;
    if (par!=null) {
        if (chech_char(par.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!par.toString().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT * FROM T_PARENTESCO WHERE NOMBRE_PAR='"+par+"'");
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
public boolean check_field (Object field){
boolean ret=false;
    if (field!=null) {
        if (chech_char(field.toString().trim(),"'$#%&()=?¡¿/*+[]{};:<>,")) {
            if (!field.toString().equals("")) {
               ret=true;
            }
        }
    }

return ret;
}
public boolean check_field_dir (Object field){
boolean ret=false;
    if (field!=null) {
        if (chech_char(field.toString().trim(),"'$%&()=?¡¿/*+[]{};:<>,")) {
            if (!field.toString().equals("")) {
               ret=true;
            }
        }
    }

return ret;
}
public boolean check_tel_field (Object field){
boolean ret=false;
    if (field!=null) {
        if (chech_char(field.toString().trim(),"'$%&()=?¡¿/*+[]{};:<>,")) {
            if (!field.toString().equals("")) {
                int count=0;
                for (int i = 0; i < field.toString().length(); i++) {
                    if ((int)field.toString().charAt(i)>=48 & (int)field.toString().charAt(i)<=57) {
                        count++;
                    }
                }
                ret = count>=7;
            }
        }
    }
return ret;
}
public boolean check_field_mail (Object field){
boolean ret=false;
    if (field!=null) {
        if (chech_char(field.toString().trim(),"'$%&()=?¡¿/*+[]{};:<>,") & !chech_char(field.toString().trim(),"@") & !chech_char(field.toString().trim(),".") ) {
            if (!field.toString().equals("")) {
               ret=true;
            }
        }
    }

return ret;
}
public boolean check_tip_vivienda(Object tip){
    boolean ret=false;
    if (tip!=null) {
        if (chech_char(tip.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!tip.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT *\n" +
                                            "FROM\n" +
                                            "    t_tipo_vivienda\n" +
                                            "WHERE NOMBRE_TIPO_VIVIENDA = '"+tip.toString().trim()+"'");
                    if(r.next()){
                        ret=true;
                    }
                    con.cerrar();
                }catch(SQLException j){
                    con.cerrar();
                    j.printStackTrace();
                    return false;
                    //JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    return ret;
}
public boolean check_tip_area(Object area){
    boolean ret=false;
    if (area!=null) {
        if (chech_char(area.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!area.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT *\n" +
                                            "FROM\n" +
                                            "    t_actividades\n" +
                                            "WHERE NOMBRE_ACTIVIDAD = '"+area.toString().trim()+"'");
                    if(r.next()){
                        ret=true;
                    }
                    con.cerrar();
                }catch(SQLException j){
                    con.cerrar();
                    j.printStackTrace();
                    return false;
                    //JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    return ret;
}
public boolean check_cargo(Object cargo){
    boolean ret=false;
    if (cargo!=null) {
        if (chech_char(cargo.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!cargo.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT *\n" +
                                            "FROM\n" +
                                            "    t_cargo\n" +
                                            "WHERE NOMBRE_CARGO = '"+cargo.toString().trim()+"'");
                    if(r.next()){
                        ret=true;
                    }
                    con.cerrar();
                }catch(SQLException j){
                    con.cerrar();
                    j.printStackTrace();
                    return false;
                    //JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    return ret;
}
public boolean check_estado_civil(Object est){
    boolean ret=false;
    if (est!=null) {
        if (chech_char(est.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!est.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT *\n" +
                                            "FROM\n" +
                                            "    t_estado_civil\n" +
                                            "WHERE NOMBRE_ESTADO = '"+est.toString().trim()+"'");
                    if(r.next()){
                        ret=true;
                    }
                    con.cerrar();
                }catch(SQLException j){
                    con.cerrar();
                    j.printStackTrace();
                    return false;
                    //JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    return ret;
}
public boolean check_niv_acad(Object niv){
    boolean ret=false;
    if (niv!=null) {
        if (chech_char(niv.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!niv.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT *\n" +
                                            "FROM\n" +
                                            "    t_nivel_academico\n" +
                                            "WHERE NOMBRE_NIVEL = '"+niv.toString().trim()+"'");
                    if(r.next()){
                        ret=true;
                    }
                    con.cerrar();
                }catch(SQLException j){
                    con.cerrar();
                    j.printStackTrace();
                    return false;
                    //JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    return ret;
}
public boolean check_nacion(Object nac){
    boolean ret=false;
    if (nac!=null) {
        if (chech_char(nac.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!nac.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT *\n" +
                                            "FROM\n" +
                                            "    t_nacionalidad\n" +
                                            "WHERE NOMBRE_NACION = '"+nac.toString().trim()+"'");
                    if(r.next()){
                        ret=true;
                    }
                    con.cerrar();
                }catch(SQLException j){
                    con.cerrar();
                    j.printStackTrace();
                    return false;
                    //JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    return ret;
}
public boolean check_prod_fin(Object prod_fin){
    boolean ret=true;
    if (prod_fin!=null) {
        if (chech_char(prod_fin.toString().trim(),"-_'#$%&()=?¡¿/*+[]{};:<>.")) {
            if (!prod_fin.toString().trim().equals("")){
                if (count_char(prod_fin.toString().trim(),',')>0) {
                    StringTokenizer tokens=new StringTokenizer(prod_fin.toString().trim(), ",");
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r;
                    try{
                        while (tokens.hasMoreTokens()) {
                            r = con.s.executeQuery ("SELECT *\n" +
                                                "FROM\n" +
                                                "    t_productos_financieros\n" +
                                                "WHERE NOMBRE_PRODUCTO='"+tokens.nextToken().trim()+"'");
                            if(r.next()){
                                ret=ret&true;
                            }else{
                                ret=ret&false;
                                break;
                            }
                        }
                        con.cerrar();
                    }catch(SQLException j){
                        con.cerrar();
                        j.printStackTrace();
                        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r;
                    try{
                        r = con.s.executeQuery ("SELECT *\n" +
                                                "FROM\n" +
                                                "    t_productos_financieros\n" +
                                                "WHERE NOMBRE_PRODUCTO='"+prod_fin.toString().trim()+"'");
                        if(r.next()){
                            ret=ret&true;
                        }else{
                            ret=ret&false;
                        }
                        con.cerrar();
                    }catch(SQLException j){
                        con.cerrar();
                        j.printStackTrace();
                        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else{
                ret=false;
            }
        }else{
            ret=false;
        }
    }else{
        ret=false;
    }
    return ret;
}
public boolean check_tiempo_libre(Object tiempo_lib){
    boolean ret=true;
    if (tiempo_lib!=null) {
        if (chech_char(tiempo_lib.toString().trim(),"-_'#$%&()=?¡¿/*+[]{};:<>.")) {
            if (!tiempo_lib.toString().trim().equals("")){
                if (count_char(tiempo_lib.toString().trim(),',')>0) {
                    StringTokenizer tokens=new StringTokenizer(tiempo_lib.toString().trim(), ",");
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r;
                    try{
                        while (tokens.hasMoreTokens()) {
                            r = con.s.executeQuery ("SELECT *\n" +
                                                "FROM\n" +
                                                "    t_tiempo_libre\n" +
                                                "WHERE NOMBRE_TIEMPO='"+tokens.nextToken().trim()+"'");
                            if(r.next()){
                                ret=ret&true;
                            }else{
                                ret=ret&false;
                                break;
                            }
                        }
                        con.cerrar();
                    }catch(SQLException j){
                        con.cerrar();
                        j.printStackTrace();
                        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r;
                    try{
                        r = con.s.executeQuery ("SELECT *\n" +
                                                "FROM\n" +
                                                "    t_tiempo_libre\n" +
                                                "WHERE NOMBRE_TIEMPO='"+tiempo_lib.toString().trim()+"'");
                        if(r.next()){
                            ret=ret&true;
                        }else{
                            ret=ret&false;
                        }
                        con.cerrar();
                    }catch(SQLException j){
                        con.cerrar();
                        j.printStackTrace();
                        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else{
                ret=false;
            }
        }else{
            ret=false;
        }
    }else{
        ret=false;
    }
    return ret;
}
public boolean check_serv_public(Object serv_pub){
    boolean ret=true;
    if (serv_pub!=null) {
        if (chech_char(serv_pub.toString().trim(),"-_'#$%&()=?¡¿/*+[]{};:<>.")) {
            if (!serv_pub.toString().trim().equals("")){
                if (count_char(serv_pub.toString().trim(),',')>0) {
                    StringTokenizer tokens=new StringTokenizer(serv_pub.toString().trim(), ",");
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r;
                    try{
                        while (tokens.hasMoreTokens()) {
                            r = con.s.executeQuery ("SELECT *\n" +
                                                "FROM\n" +
                                                "    t_servicios_publicos\n" +
                                                "WHERE NOMBRE_SERVICIO='"+tokens.nextToken().trim()+"'");
                            if(r.next()){
                                ret=ret&true;
                            }else{
                                ret=ret&false;
                                break;
                            }
                        }
                        con.cerrar();
                    }catch(SQLException j){
                        con.cerrar();
                        j.printStackTrace();
                        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r;
                    try{
                        r = con.s.executeQuery ("SELECT *\n" +
                                                "FROM\n" +
                                                "    t_servicios_publicos\n" +
                                                "WHERE NOMBRE_SERVICIO='"+serv_pub.toString().trim()+"'");
                        if(r.next()){
                            ret=true;
                        }else{
                            ret=false;
                        }
                        con.cerrar();
                    }catch(SQLException j){
                        con.cerrar();
                        j.printStackTrace();
                        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else{
                ret=false;
            }
        }else{
            ret=false;
        }
    }else{
        ret=false;
    }
    return ret;
}
public boolean check_field_ahorro (Object ahorro){
boolean ret=false;
    if (ahorro!=null) {
        if (chech_char(ahorro.toString().trim(),"'$%&()=?¡¿/*+[]{};:<>,")) {
            if (!ahorro.toString().equals("")) {
                if (ahorro.toString().toUpperCase().equals("SI") | ahorro.toString().toUpperCase().equals("NO")) {
                    ret=true;
                }
            }
        }
    }

return ret;
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
public boolean check_fte_ahorro(Object fte_ahorro){
    boolean ret=true;
    if (fte_ahorro!=null) {
        if (chech_char(fte_ahorro.toString().trim(),"-_'#$%&()=?¡¿/*+[]{};:<>.")) {
            if (!fte_ahorro.toString().trim().equals("")){
                if (count_char(fte_ahorro.toString().trim(),',')>0) {
                    StringTokenizer tokens=new StringTokenizer(fte_ahorro.toString().trim(), ",");
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r;
                    try{
                        while (tokens.hasMoreTokens()) {
                            r = con.s.executeQuery ("SELECT *\n" +
                                                "FROM\n" +
                                                "    t_fte_ahorro\n" +
                                                "WHERE NOMBRE_FTE_AHORRO='"+tokens.nextToken().trim()+"'");
                            if(r.next()){
                                ret=ret&true;
                            }else{
                                ret=ret&false;
                                break;
                            }
                        }
                        con.cerrar();
                    }catch(SQLException j){
                        con.cerrar();
                        j.printStackTrace();
                        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r;
                    try{
                        r = con.s.executeQuery ("SELECT *\n" +
                                                "FROM\n" +
                                                "    t_fte_ahorro\n" +
                                                "WHERE NOMBRE_FTE_AHORRO='"+fte_ahorro.toString().trim()+"'");
                        if(r.next()){
                            ret=ret&true;
                        }else{
                            ret=ret&false;
                        }
                        con.cerrar();
                    }catch(SQLException j){
                        con.cerrar();
                        j.printStackTrace();
                        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else{
                ret=false;
            }
        }else{
            ret=false;
        }
    }else{
        ret=false;
    }
    return ret;
}
public boolean check_integer (Object n){
    boolean ret=false;
    if (n!=null) {
        if (chech_char(n.toString().trim(),"'$#%&()=?¡¿/*+[]{};:<>,")) {
            if (!n.toString().equals("")) {
               try{
                    int num = Integer.parseInt(n.toString().trim());
                    return true;
                }catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
            }
        }
        
    }
    return ret;

}
public boolean check_estrato (Object n){
    boolean ret=false;
    if (n!=null) {
        if (chech_char(n.toString().trim(),"'$#%&()=?¡¿/*+[]{};:<>,")) {
            if (!n.toString().equals("")) {
               try{
                    int num = Integer.parseInt(n.toString().trim());
                    ret = num>0 & num<7;
                }catch (Exception e){
                    //e.printStackTrace();
                    ret=false;
                }
            }
        }
    }
    return ret;
}
public static boolean comprobarLong (String cadena){
try{
    long num = Long.parseLong(cadena);
}catch (Exception e){
    //e.printStackTrace();
    return false;
}
return true;

}*/
}
