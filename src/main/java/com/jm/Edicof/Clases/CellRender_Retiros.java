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
public class CellRender_Retiros extends DefaultTableCellRenderer{
    public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected,boolean hasFocus,int row,int column){
        JLabel cell = (JLabel) super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
        
        if (table.getColumnName(column).equals("Cedula*")) {
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
            if (table.getColumnName(column).equals("Empleador (Nombre)")) {
                if (check_empleador(value)) {
                    cell.setOpaque(true);
                    cell.setBackground(new Color(150,240,160));
                    cell.setForeground(Color.black);
                }else{
                    cell.setOpaque(true);
                    cell.setBackground(Color.red);
                    cell.setForeground(Color.white);
                }
            }else{
                if (table.getColumnName(column).equals("F Ingreso (DD-MM-AAAA)")) {
                    if (check_fecha(value)) {
                        cell.setOpaque(true);
                        cell.setBackground(new Color(150,240,160));
                        cell.setForeground(Color.black);
                    }else{
                        cell.setOpaque(true);
                        cell.setBackground(Color.red);
                        cell.setForeground(Color.white);
                    }
                }else{
                    if (table.getColumnName(column).equals("F Retiro (DD-MM-AAAA)")) {
                        if (check_fecha(value)) {
                            cell.setOpaque(true);
                            cell.setBackground(new Color(150,240,160));
                            cell.setForeground(Color.black);
                        }else{
                            cell.setOpaque(true);
                            cell.setBackground(Color.red);
                            cell.setForeground(Color.white);
                        }
                    }else{
                        if (table.getColumnName(column).equals("EPS (Nombre)")) {
                            if (check_eps(value)) {
                                cell.setOpaque(true);
                                cell.setBackground(new Color(150,240,160));
                                cell.setForeground(Color.black);
                            }else{
                                cell.setOpaque(true);
                                cell.setBackground(Color.red);
                                cell.setForeground(Color.white);
                            }
                        }else{
                            if (table.getColumnName(column).equals("AFP (Nombre)")) {
                                if (check_afp(value)) {
                                    cell.setOpaque(true);
                                    cell.setBackground(new Color(150,240,160));
                                    cell.setForeground(Color.black);
                                }else{
                                    cell.setOpaque(true);
                                    cell.setBackground(Color.red);
                                    cell.setForeground(Color.white);
                                }
                            }else{
                                if (table.getColumnName(column).equals("ARL (Nombre)")) {
                                    if (check_arl(value)) {
                                        cell.setOpaque(true);
                                        cell.setBackground(new Color(150,240,160));
                                        cell.setForeground(Color.black);
                                    }else{
                                        cell.setOpaque(true);
                                        cell.setBackground(Color.red);
                                        cell.setForeground(Color.white);
                                    }
                                }else{
                                    if (table.getColumnName(column).equals("CCF (Nombre)")) {
                                        if (check_ccf(value)) {
                                            cell.setOpaque(true);
                                            cell.setBackground(new Color(150,240,160));
                                            cell.setForeground(Color.black);
                                        }else{
                                            cell.setOpaque(true);
                                            cell.setBackground(Color.red);
                                            cell.setForeground(Color.white);
                                        }
                                    }else{
                                        if (table.getColumnName(column).equals("Salario")) {
                                            if ( check_salario(value)) {
                                                cell.setOpaque(true);
                                                cell.setBackground(new Color(150,240,160));
                                                cell.setForeground(Color.black);
                                            }else{
                                                cell.setOpaque(true);
                                                cell.setBackground(Color.red);
                                                cell.setForeground(Color.white);
                                            }
                                        }else{
                                            if (table.getColumnName(column).equals("F Nacimiento (DD-MM-AAAA)")) {
                                                if (check_fecha(value)) {
                                                    cell.setOpaque(true);
                                                    cell.setBackground(new Color(150,240,160));
                                                    cell.setForeground(Color.black);
                                                }else{
                                                    cell.setOpaque(true);
                                                    cell.setBackground(Color.red);
                                                    cell.setForeground(Color.white);
                                                }
                                            }else{
                                                if (table.getColumnName(column).equals("F Expedicion (DD-MM-AAAA)")) {
                                                    if (check_fecha(value)) {
                                                        cell.setOpaque(true);
                                                        cell.setBackground(new Color(150,240,160));
                                                        cell.setForeground(Color.black);
                                                    }else{
                                                        cell.setOpaque(true);
                                                        cell.setBackground(Color.red);
                                                        cell.setForeground(Color.white);
                                                    }
                                                }else{
                                                    if (table.getColumnName(column).equals("Obra")) {
                                                        if (check_obra(value)) {
                                                            cell.setOpaque(true);
                                                            cell.setBackground(new Color(150,240,160));
                                                            cell.setForeground(Color.black);
                                                        }else{
                                                            cell.setOpaque(true);
                                                            cell.setBackground(Color.red);
                                                            cell.setForeground(Color.white);
                                                        }
                                                    }else{
                                                        if (table.getColumnName(column).equals("Barrio")) {
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
                                                            if (table.getColumnName(column).equals("Municipio")) {
                                                                if (check_municipio(value)) {
                                                                    cell.setOpaque(true);
                                                                    cell.setBackground(new Color(150,240,160));
                                                                    cell.setForeground(Color.black);
                                                                }else{
                                                                    cell.setOpaque(true);
                                                                    cell.setBackground(Color.red);
                                                                    cell.setForeground(Color.white);
                                                                }
                                                            }else{
                                                                if (table.getColumnName(column).equals("Parentesco")) {
                                                                    if (check_parentesco(value)) {
                                                                        cell.setOpaque(true);
                                                                        cell.setBackground(new Color(150,240,160));
                                                                        cell.setForeground(Color.black);
                                                                    }else{
                                                                        cell.setOpaque(true);
                                                                        cell.setBackground(Color.red);
                                                                        cell.setForeground(Color.white);
                                                                    }
                                                                }else{
                                                                    if (table.getColumnName(column).equals("Direccion")) {
                                                                        if (check_field_dir(value)) {
                                                                            cell.setOpaque(true);
                                                                            cell.setBackground(new Color(150,240,160));
                                                                            cell.setForeground(Color.black);
                                                                        }else{
                                                                            cell.setOpaque(true);
                                                                            cell.setBackground(Color.red);
                                                                            cell.setForeground(Color.white);
                                                                        }
                                                                    }else{
                                                                        if (table.getColumnName(column).equals("Telefono")) {
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
                                                                            if (table.getColumnName(column).equals("Correo")) {
                                                                                if (check_field_mail(value)) {
                                                                                    cell.setOpaque(true);
                                                                                    cell.setBackground(new Color(150,240,160));
                                                                                    cell.setForeground(Color.black);
                                                                                }else{
                                                                                    cell.setOpaque(true);
                                                                                    cell.setBackground(Color.red);
                                                                                    cell.setForeground(Color.white);
                                                                                }
                                                                            }else{
                                                                                if (table.getColumnName(column).equals("Acudiente")) {
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
                                                                                    if (table.getColumnName(column).equals("Tipo")) {
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
                                                                                        if (table.getColumnName(column).equals("Observaciones*")) {
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
            if (!ced.toString().equals("")) {
                if(comprobarLong(ced.toString().trim())){
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
public boolean check_eps(Object eps){
    boolean ret=false;
    if (eps!=null) {
        if (chech_char(eps.toString().trim(),"'#$%&=?¡¿/*+[]{};:<>,")) {
            if (!eps.toString().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT * FROM T_EPS WHERE NOMBRE_EPS='"+eps+"'");
                    if(r.next()){
                        ret = !r.getString("NOMBRE_EPS").equals("NO ASIGNADA");
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
public boolean check_afp(Object afp){
    boolean ret=false;
    if (afp!=null) {
        if (chech_char(afp.toString().trim(),"'#$%&=?¡¿/*+[]{};:<>,")) {
            if (!afp.toString().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT * FROM T_AFP WHERE NOMBRE_AFP='"+afp+"'");
                    if(r.next()){
                        ret = !r.getString("NOMBRE_AFP").equals("NO ASIGNADA");
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
public boolean check_arl(Object arl){
    boolean ret=false;
    if (arl!=null) {
        if (chech_char(arl.toString().trim(),"'#$%&=?¡¿/*+[]{};:<>,")) {
            if (!arl.toString().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT * FROM T_ARL WHERE NOMBRE_ARL='"+arl+"'");
                    if(r.next()){
                        ret = !r.getString("NOMBRE_ARL").equals("NO ASIGNADA");
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
public boolean check_ccf(Object ccf){
    boolean ret=false;
    if (ccf!=null) {
        if (chech_char(ccf.toString().trim(),"'#$%&=?¡¿/*+[]{};:<>,")) {
            if (!ccf.toString().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT * FROM T_CCF WHERE NOMBRE_CCF='"+ccf+"'");
                    if(r.next()){
                        ret = !r.getString("NOMBRE_CCF").equals("NO ASIGNADA");
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
                    if (str_dia>0 & str_dia<=31) {
                        if (str_mes>0 & str_mes<=12) {
                            if (str_año>=1900 & str_año<=2050) {
                                Calendar ahoraCal = Calendar.getInstance();
                                ahoraCal.setLenient(false);
                                ahoraCal.set(str_año,str_mes-1,1);
                                ahoraCal.set(Calendar.DATE, ahoraCal.getActualMaximum(Calendar.DATE));
                                int f = ahoraCal.get(Calendar.DAY_OF_MONTH);
                                if (str_dia<=f) {
                                    return true;
                                }
                            }else{
                                return false;
                            }
                        }else{
                            return false;
                        }
                    }else{
                        return false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }else{
                ret=false;
            }
        }else{
            return false;
        }
    }else{
        return false;
    }
    return false;
}
public boolean check_salario(Object salario){
    boolean ret=false;
    if (salario!=null) {
        if (chech_char(salario.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>")) {
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
        if (chech_char(barrio.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!barrio.toString().trim().equals("")){
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT * FROM T_BARRIO WHERE NOMBRE_BARRIO='"+barrio.toString().trim()+"'");
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
        if (chech_char(field.toString().trim(),"'$#%&()=?¡¿/*+[]{};<")) {
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
