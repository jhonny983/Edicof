/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.Clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author JHONN
 */

public class Validations {
    
    
    
    
///////*****************VALIDATIONS TEXT CELLRENDER_ASISTENCIAS*******************//////////    
public static boolean check_char(String s, String c){
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
public static boolean comprobarLong (String cadena){
try{
    long num = Long.parseLong(cadena);
}catch (Exception e){
    e.printStackTrace();
    return false;
}
return true;

}
public static boolean comprobarFloat (String cadena){
try{
    float num = Float.parseFloat(cadena);
}catch (Exception e){
    e.printStackTrace();
    return false;
}
return true;

}
public static int count_char(String str, char c){
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
public static boolean check_fecha(Object fecha){
    boolean ret=false;
    if (fecha!=null) {
        if (check_char(fecha.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
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
public static boolean check_salario(Object salario){
    boolean ret=false;
    if (salario!=null) {
        if (check_char(salario.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!salario.toString().equals("")) {
                if ( comprobarFloat(salario.toString())) {
                    ret = true;
                }
            }
        }
    }
return ret;
}
public static boolean check_barrio(Object field){
    boolean ret=false;
    if (field!=null) {
        if (check_char(field.toString().trim(),"'$%&()=?¡¿/*+[]{};:<>,")) {
            if (!field.toString().equals("")) {
               ret=true;
            }
        }
    }

return ret;
}
public static boolean check_field (Object field){
boolean ret=false;
    if (field!=null) {
        if (check_char(field.toString().trim(),"'$#%&()=?¡¿/*+[]{};:<>,")) {
            if (!field.toString().equals("")) {
               ret=true;
            }
        }
    }

return ret;
}
public static boolean check_field_obs (Object field){
boolean ret=false;
    if (field!=null) {
        if (check_char(field.toString().trim(),"'$#%&()=?¡¿/*+[]{};,")) {
            if (!field.toString().equals("")) {
               ret=true;
            }
        }
    }

return ret;
}
public static boolean check_field_dir (Object field){
boolean ret=false;
    if (field!=null) {
        if (check_char(field.toString().trim(),"'$%&()=?¡¿/*+[]{};:<>,")) {
            if (!field.toString().equals("")) {
               ret=true;
            }
        }
    }

return ret;
}
public static boolean check_field_mail (Object field){
boolean ret=false;
    if (field!=null) {
        if (check_char(field.toString().trim(),"'$%&()=?¡¿/*+[]{};:<>,") & !check_char(field.toString().trim(),"@") & !check_char(field.toString().trim(),".") ) {
            if (!field.toString().equals("")) {
               ret=true;
            }
        }
    }

return ret;
}
//////////////////////////CELLRENDER_INFO_SOCIODEMOGRAFICA/////////
public static boolean check_estrato (Object n){
    boolean ret=false;
    if (n!=null) {
        if (check_char(n.toString().trim(),"'$#%&()=?¡¿/*+[]{};:<>,")) {
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
public static boolean check_integer (Object n){
    boolean ret=false;
    if (n!=null) {
        if (check_char(n.toString().trim(),"'$#%&()=?¡¿/*+[]{};:<>,")) {
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
public static boolean check_field_ahorro (Object ahorro){
boolean ret=false;
    if (ahorro!=null) {
        if (check_char(ahorro.toString().trim(),"'$%&()=?¡¿/*+[]{};:<>,")) {
            if (!ahorro.toString().equals("")) {
                if (ahorro.toString().toUpperCase().equals("SI") | ahorro.toString().toUpperCase().equals("NO")) {
                    ret=true;
                }
            }
        }
    }

return ret;
}
public static boolean check_tel_field (Object field){
    boolean ret=false;
        if (field!=null) {
            if (check_char(field.toString().trim(),"'$%&()=?¡¿/*+[]{};:<>,")) {
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
//////////////////////////CELLRENDER_INGRESOS_MASIVOS
public static boolean check_estat(Object ced){
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
////////////////////////ADD PREINGRESOS TRASLADO
public static boolean check_exon(Object ex){
    boolean ret=false;
    if (ex!=null) {
        if (check_char(ex.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,")) {
            if (!ex.toString().trim().toUpperCase().equals("")) {
                switch (ex.toString().trim().toUpperCase()){
                        case "SI":  ret=true;
                                    break;
                        case "NO":  ret=true;
                                    break;
                        default:    ret=false;
                }
            }
        }
    }
    return ret;
}
///////*****************VALIDATIONS WITH CONNECTION DATABASE CELLRENDER_ASISTENCIAS*******************//////////
public static boolean check_cedula(Object ced){
    boolean ret=false;
    if (ced!=null) {
        if (check_char(ced.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (comprobarLong(ced.toString().trim())) {
                if(!ced.toString().equals("")){
                    Conexion con = new Conexion();//1.REEMPLAZAR Conexion con = new Conexion(); POR Conexion condb = new Conexion();
                    con.conexion();//2.REEMPLAZAR con.conexion(); POR Connection con = condb.conexion();
                    ResultSet r=null;
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
                    }finally{
                        if (r!=null) {
                            try {
                                r.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            r=null;
                        }
                        con.cerrar();
                    }
                }
            }
        }
    }
    return ret;  
}
public static boolean check_empleador(Object emp){
    boolean ret=false;
    if (emp!=null) {
        if (check_char(emp.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,")) {
            if (!emp.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r = null;
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
                }finally{
                    if (r!=null) {
                        try {
                            r.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        r=null;
                    }
                    con.cerrar();
                }
            }
        }
    }
    
    return ret;
}
public static boolean check_nit(Object emp){
    boolean ret=false;
    if (emp!=null) {
        if (check_char(emp.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,")) {
            if (!emp.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r=null;
                try{
                    r = con.s.executeQuery ("SELECT * FROM T_EMPRESAS WHERE ID_EMPRESA='"+emp.toString().trim()+"'");
                    if(r.next()){
                        ret=true;
                    }
                    con.cerrar();
                }catch(SQLException j){
                    con.cerrar();
                    j.printStackTrace();
                    return false;
                    //JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                }finally{
                    if (r!=null) {
                        try {
                            r.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        r=null;
                    }
                    con.cerrar();
                }
            }
        }
    }
    
    return ret;
}
public static boolean check_obra(Object obra){
    boolean ret=false;
    if (obra!=null) {
        if (check_char(obra.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
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
                ResultSet r=null;
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
                }finally{
                    if (r!=null) {
                        try {
                            r.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        r=null;
                    }
                    con.cerrar();
                }
            }
        }
    }
    
    return ret;
}
public static boolean check_municipio(Object municipio){
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
                ResultSet r=null;
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
                }finally{
                    if (r!=null) {
                        try {
                            r.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        r=null;
                    }
                    con.cerrar();
                }
            }
        }
    }
    return ret;
}
public static boolean check_parentesco(Object par){
    boolean ret=false;
    if (par!=null) {
        if (check_char(par.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!par.toString().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r=null;
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
                }finally{
                    if (r!=null) {
                        try {
                            r.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        r=null;
                    }
                    con.cerrar();
                }
            }
        }
    }
    return ret;
}
/////////////////CELLRENDER_CRUCE_PILA
public static boolean check_ccf(Object ccf){
        boolean ret=false;
        if (ccf!=null) {
            if (check_char(ccf.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,")) {
                if (!ccf.toString().trim().equals("")) {
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r=null;
                    try{
                        r = con.s.executeQuery ("SELECT * FROM T_CCF WHERE NOMBRE_CCF='"+ccf.toString().trim()+"'");
                        if(r.next()){
                            ret=true;
                        }
                        con.cerrar();
                    }catch(SQLException j){
                        con.cerrar();
                        j.printStackTrace();
                        return false;
                        //JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                    }finally{
                    if (r!=null) {
                        try {
                            r.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        r=null;
                    }
                    con.cerrar();
                }
                }
            }
        }else{
            ret=true;
        }

        return ret;
    }
public static boolean check_afp(Object afp){
        boolean ret=false;
        if (afp!=null) {
            if (check_char(afp.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,")) {
                if (!afp.toString().trim().equals("")) {
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r=null;
                    try{
                        r = con.s.executeQuery ("SELECT * FROM T_AFP WHERE NOMBRE_AFP='"+afp.toString().trim()+"'");
                        if(r.next()){
                            ret=true;
                        }
                        con.cerrar();
                    }catch(SQLException j){
                        con.cerrar();
                        j.printStackTrace();
                        return false;
                        //JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                    }finally{
                        if (r!=null) {
                            try {
                                r.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            r=null;
                        }
                        con.cerrar();
                    }
                }
            }
        }else{
            ret=true;
        }
        return ret;
    }
public static boolean check_arl(Object arl){
        boolean ret=false;
        if (arl!=null) {
            if (check_char(arl.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,")) {
                if (!arl.toString().trim().equals("")) {
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r = null;
                    try{
                        r = con.s.executeQuery ("SELECT * FROM T_ARL WHERE NOMBRE_ARL='"+arl.toString().trim()+"'");
                        if(r.next()){
                            ret=true;
                        }
                        con.cerrar();
                    }catch(SQLException j){
                        con.cerrar();
                        j.printStackTrace();
                        return false;
                        //JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                    }finally{
                        if (r!=null) {
                            try {
                                r.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            r=null;
                        }
                        con.cerrar();
                    }
                }
            }
        }else{
            ret=true;
        }
        return ret;
    }
public static boolean check_eps(Object eps){
        boolean ret=false;
        if (eps!=null) {
            if (check_char(eps.toString().trim(),"'#$%&=?¡¿/*+[]{};:<>,")) {
                if (!eps.toString().trim().equals("")) {
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r=null;
                    try{
                        r = con.s.executeQuery ("SELECT * FROM T_EPS WHERE NOMBRE_EPS='"+eps.toString().trim()+"'");
                        if(r.next()){
                            ret=true;
                        }
                        con.cerrar();
                    }catch(SQLException j){
                        con.cerrar();
                        j.printStackTrace();
                        return false;
                        //JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                    }finally{
                        if (r!=null) {
                            try {
                                r.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            r=null;
                        }
                        con.cerrar();
                    }
                }
            }else{
                ret=false;
            }
        }else{
            ret=true;
        }

        return ret;
    }
/////////////////CELLRENDER_INFO_SOCIODEMOGRAFICA
public static boolean check_fte_ahorro(Object fte_ahorro){
    boolean ret=true;
    if (fte_ahorro!=null) {
        if (check_char(fte_ahorro.toString().trim(),"-_'#$%&()=?¡¿/*+[]{};:<>.")) {
            if (!fte_ahorro.toString().trim().equals("")){
                if (count_char(fte_ahorro.toString().trim(),',')>0) {
                    StringTokenizer tokens=new StringTokenizer(fte_ahorro.toString().trim(), ",");
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r=null;
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
                    }finally{
                        if (r!=null) {
                            try {
                                r.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            r=null;
                        }
                        con.cerrar();
                    }
                }else{
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r=null;
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
                    }finally{
                        if (r!=null) {
                            try {
                                r.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            r=null;
                        }
                        con.cerrar();
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
public static boolean check_serv_public(Object serv_pub){
    boolean ret=true;
    if (serv_pub!=null) {
        if (check_char(serv_pub.toString().trim(),"-_'#$%&()=?¡¿/*+[]{};:<>.")) {
            if (!serv_pub.toString().trim().equals("")){
                if (count_char(serv_pub.toString().trim(),',')>0) {
                    StringTokenizer tokens=new StringTokenizer(serv_pub.toString().trim(), ",");
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r=null;
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
                    }finally{
                        if (r!=null) {
                            try {
                                r.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            r=null;
                        }
                        con.cerrar();
                    }
                }else{
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r=null;
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
                    }finally{
                        if (r!=null) {
                            try {
                                r.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            r=null;
                        }
                        con.cerrar();
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
public static boolean check_tiempo_libre(Object tiempo_lib){
    boolean ret=true;
    if (tiempo_lib!=null) {
        if (check_char(tiempo_lib.toString().trim(),"-_'#$%&()=?¡¿/*+[]{};:<>.")) {
            if (!tiempo_lib.toString().trim().equals("")){
                if (count_char(tiempo_lib.toString().trim(),',')>0) {
                    StringTokenizer tokens=new StringTokenizer(tiempo_lib.toString().trim(), ",");
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r=null;
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
                    }finally{
                        if (r!=null) {
                            try {
                                r.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            r=null;
                        }
                        con.cerrar();
                    }
                }else{
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r=null;
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
                    }finally{
                        if (r!=null) {
                            try {
                                r.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            r=null;
                        }
                        con.cerrar();
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
public static boolean check_prod_fin(Object prod_fin){
    boolean ret=true;
    if (prod_fin!=null) {
        if (check_char(prod_fin.toString().trim(),"-_'#$%&()=?¡¿/*+[]{};:<>.")) {
            if (!prod_fin.toString().trim().equals("")){
                if (count_char(prod_fin.toString().trim(),',')>0) {
                    StringTokenizer tokens=new StringTokenizer(prod_fin.toString().trim(), ",");
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r=null;
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
                    }finally{
                        if (r!=null) {
                            try {
                                r.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            r=null;
                        }
                        con.cerrar();
                    }
                }else{
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r=null;
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
                    }finally{
                        if (r!=null) {
                            try {
                                r.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            r=null;
                        }
                        con.cerrar();
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
public static boolean check_nacion(Object nac){
    boolean ret=false;
    if (nac!=null) {
        if (check_char(nac.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!nac.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r=null;
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
                }finally{
                        if (r!=null) {
                            try {
                                r.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            r=null;
                        }
                        con.cerrar();
                    }
            }
        }
    }
    
    return ret;
}
public static boolean check_niv_acad(Object niv){
    boolean ret=false;
    if (niv!=null) {
        if (check_char(niv.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!niv.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r=null;
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
                }finally{
                    if (r!=null) {
                        try {
                            r.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        r=null;
                    }
                    con.cerrar();
                }
            }
        }
    }
    
    return ret;
}
public static boolean check_estado_civil(Object est){
    boolean ret=false;
    if (est!=null) {
        if (check_char(est.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!est.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r=null;
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
                }finally{
                    if (r!=null) {
                        try {
                            r.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        r=null;
                    }
                    con.cerrar();
                }
            }
        }
    }
    
    return ret;
}
public static boolean check_cargo(Object cargo){
    boolean ret=false;
    if (cargo!=null) {
        if (check_char(cargo.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!cargo.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r=null;
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
                }finally{
                    if (r!=null) {
                        try {
                            r.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        r=null;
                    }
                    con.cerrar();
                }
            }
        }
    }
    
    return ret;
}
public static boolean check_tip_area(Object area){
    boolean ret=false;
    if (area!=null) {
        if (check_char(area.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!area.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r=null;
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
                }finally{
                    if (r!=null) {
                        try {
                            r.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        r=null;
                    }
                    con.cerrar();
                }
            }
        }
    }
    
    return ret;
}
public static boolean check_tip_vivienda(Object tip){
    boolean ret=false;
    if (tip!=null) {
        if (check_char(tip.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!tip.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r=null;
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
                }finally{
                    if (r!=null) {
                        try {
                            r.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        r=null;
                    }
                    con.cerrar();
                }
            }
        }
    }
    
    return ret;
}
public static boolean check_barrio_table(Object barrio){
    boolean ret=false;
    if (barrio!=null) {
        if (check_char(barrio.toString().trim(),"'$%&()=?¡¿/*+[]{};:<>,")) {
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
                ResultSet r=null;
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
                }finally{
                    if (r!=null) {
                        try {
                            r.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        r=null;
                    }
                    con.cerrar();
                }
            }
        }
    }

return ret;
}
///////////////////CELLRENDER_INGRESOS_MASICO
public static boolean check_tip_ident(Object tipo){
    boolean ret=false;
    if (tipo!=null) {
        if (check_char(tipo.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            Conexion con = new Conexion();
            con.conexion();
            ResultSet r=null;
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
            }finally{
                    if (r!=null) {
                        try {
                            r.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        r=null;
                    }
                    con.cerrar();
                }
        }
    }
    return ret;
    }
public static boolean check_tip_sangre(Object tipo){
    boolean ret=false;
    if (tipo!=null) {
        if (check_char(tipo.toString().trim(),"'#$%&()=?¡¿/*[]{};:<>,.")) {
            Conexion con = new Conexion();
            con.conexion();
            ResultSet r=null;
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
            }finally{
                if (r!=null) {
                    try {
                        r.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    r=null;
                }
                con.cerrar();
            }
        }
    }
    return ret;
    }
public static boolean check_genero(Object genero){
    boolean ret=false;
    if (genero!=null) {
        if (check_char(genero.toString().trim(),"'#$%&()=?¡¿/*+-[]{};:<>,.")) {
            Conexion con = new Conexion();
            con.conexion();
            ResultSet r=null;
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
            }finally{
                if (r!=null) {
                    try {
                        r.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    r=null;
                }
                con.cerrar();
            }
        }
    }
    return ret;
    }
//////////////////PREINGRESO_TRASLADO
public static boolean check_sal_min(Object salario){
    boolean ret=false;
    float sal=0;
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r=null;
    try {
        r = con.s.executeQuery ("SELECT * FROM `t_parametros` WHERE NOMBRE_PAR = 'SALARIO_MINIMO';");
        if(r.next()){
            sal=r.getFloat("VALOR_PAR"); 
        }
        con.cerrar();
        if (Float.parseFloat(salario.toString())>=sal) {
            ret = true;
        }
    } catch (Exception e) {
        con.cerrar();
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
    }finally{
        if (r!=null) {
            try {
                r.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            r=null;
        }
        con.cerrar();
    }
    return ret;
}
public static boolean check_barrio_mun(Object barrio, Object mun){
    boolean ret=false;
    if (barrio!=null & mun!=null) {
        if (check_char(barrio.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.") & check_char(mun.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!barrio.toString().trim().equals("") & !mun.toString().trim().equals("")){
               Conexion con = new Conexion();
                con.conexion();
                ResultSet r=null;
                try{
                    String str_barrio="";
                    String str_barrio_mun="";
                    String str_mun="";
                    String str_dep="";
                    StringTokenizer tk_barrio=new StringTokenizer(barrio.toString().trim(), "-");
                    while(tk_barrio.hasMoreTokens()){
                        str_barrio=tk_barrio.nextToken().trim();
                        str_barrio_mun=tk_barrio.nextToken().trim();
                    }
                    StringTokenizer tk_mun=new StringTokenizer(mun.toString().trim(), "-");
                    while(tk_mun.hasMoreTokens()){
                        str_mun=tk_mun.nextToken().trim();
                        str_dep=tk_mun.nextToken().trim();
                    }
                    if (str_barrio_mun.equals(str_mun)) {
                        r = con.s.executeQuery ("SELECT * FROM t_barrio WHERE NOMBRE_BARRIO='"+str_barrio+"' AND ID_MUN_BARRIO="+GetInfo.get_id_municipio(mun.toString().trim()));
                        if(r.next()){
                            ret=true;
                        }
                    }
                    con.cerrar();
                }catch(SQLException j){
                    con.cerrar();
                    j.printStackTrace();
                    return false;
                }finally{
                    if (r!=null) {
                        try {
                            r.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        r=null;
                    }
                    con.cerrar();
                }
            }
        }
    }
    return ret;
}
public static boolean check_active(String id_empleado, String empleador){
    boolean ret = false;
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r=null;
    try{
        r = con.s.executeQuery ("SELECT *\n" +
                                "FROM\n" +
                                "    t_novedades\n" +
                                "    INNER JOIN t_empresas \n" +
                                "        ON (t_novedades.ID_EMPRESA = t_empresas.ID_EMPRESA)" +
                                "    WHERE t_novedades.ID_EMPLEADO ="+id_empleado+"  AND t_empresas.NOMBRE_EMPRESA='"+empleador+"' AND t_novedades.ID_TIPO IN(1,3,4,5);");
        ret = !r.next();
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        ret = false;
        j.printStackTrace();
    }finally{
        if (r!=null) {
            try {
                r.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            r=null;
        }
        con.cerrar();
    }
    return ret;
}
public static boolean check_vetado(String id_empleado){
    boolean ret;
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r=null;
    try{
        r = con.s.executeQuery ("SELECT * FROM t_vetados WHERE ID_EMPLEADO = "+id_empleado);
        ret = !r.next();
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        ret = false;
        j.printStackTrace();
    }finally{
        if (r!=null) {
            try {
                r.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            r=null;
        }
        con.cerrar();
    }
    return ret;
}
public static boolean check_protocolo(String id_empleado){
    boolean ret;
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r=null;
    try{
        r = con.s.executeQuery ("SELECT *\n" +
                                "from `t_cap_prot`WHERE ID_EMP = "+id_empleado);
        ret = r.next();
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        ret = false;
        j.printStackTrace();
    }finally{
        if (r!=null) {
            try {
                r.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            r=null;
        }
        con.cerrar();
    }
    return ret;
}

public static boolean check_info(Object ced){
    boolean ret=false;
    if (ced!=null) {
        if (check_char(ced.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!ced.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r=null;
                try{
                    r = con.s.executeQuery ("SELECT *\n" +
                                            "FROM\n" +
                                            "    `t_info_sociodemografica` WHERE ID_EMP = "+ced.toString().trim());
                    ret=r.next();
                    con.cerrar();
                }catch(SQLException j){
                    con.cerrar();
                    j.printStackTrace();
                    return false;
                    //JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                }finally{
                    if (r!=null) {
                        try {
                            r.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        r=null;
                    }
                    con.cerrar();
                }
            }
        }
    }
    return ret;
}
public static boolean check_block_emp (Object field){
    boolean ret=false;
    if (field!=null) {
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r=null;
        try{
            r = con.s.executeQuery ("SELECT * FROM t_empresas WHERE ID_EMPRESA='"+field+"'");
            if(r.next()){
                ret = r.getInt("ESTADO_EMPRESA")==1;
            }
            con.cerrar();
        }catch(SQLException j){
            con.cerrar();
            j.printStackTrace();
        }finally{
            if (r!=null) {
                try {
                    r.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                r=null;
            }
            con.cerrar();
        }
    }
    return ret;

    
    }
}
