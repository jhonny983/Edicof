/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.Clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

/**
 *
 * @author JHONN
 */
public class GetInfo {
    ///////GET ID MUNICIOIO CON OBJECT = MUNICIPIO-CIUDAD
    public static String get_id_municipio(Object municipio){
    String i = "";
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
            i = r.getString("ID_MUN");
        }else{
            i="";
        }
        con.cerrar();
    }catch(SQLException j){
        i="";
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
    return i;
}
    public static String get_id_empleador(Object emp){
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r=null;
    String i = "";
    try{
        r = con.s.executeQuery ("SELECT * FROM T_EMPRESAS WHERE NOMBRE_EMPRESA='"+emp.toString().trim()+"'");
        if(r.next()){
            i = r.getString("ID_EMPRESA");
        }else{
            i="";
        }
        con.cerrar();
    }catch(SQLException j){
        i="";
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
    return i;
}
    public static String get_id_obra(Object obra){
    String i="";
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
            i = r.getString("ID_OBRA");
        }else{
            i="";
        }
        con.cerrar();
    }catch(SQLException j){
        i="";
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
    return i;
}
    public static String get_id_barrio(Object barrio){
    String i = "";
    String str_barrio="";
    String str_barrio_mun="";
    StringTokenizer tk_barrio=new StringTokenizer(barrio.toString().trim(), "-");
    while(tk_barrio.hasMoreTokens()){
        str_barrio=tk_barrio.nextToken().trim();
        str_barrio_mun=tk_barrio.nextToken().trim();
    }
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r=null;
    try{
        r = con.s.executeQuery ("SELECT *\n" +
                                "FROM\n" +
                                "    `t_barrio`\n" +
                                "    INNER JOIN `t_municipios` \n" +
                                "        ON (`t_barrio`.`ID_MUN_BARRIO` = `t_municipios`.`ID_MUN`) WHERE NOMBRE_BARRIO='"+str_barrio+"' AND NOMBRE_MUN = '"+str_barrio_mun+"'");
        if(r.next()){
            i = r.getString("ID_BARRIO");
        }else{
            i="";
        }
        con.cerrar();
    }catch(SQLException j){
        i="";
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
    return i;
}
    public static String get_id_parentesco(Object par){
    String i = "";
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r=null;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_PARENTESCO WHERE NOMBRE_PAR='"+par+"'");
        if(r.next()){
            i = r.getString("ID_PAR");
        }else{
            i="";
        }
        con.cerrar();
    }catch(SQLException j){
        i="";
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
    return i;
}
    public static String get_id_eps(Object eps){
    String i = "";
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r=null;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_EPS WHERE NOMBRE_EPS='"+eps+"'");
        if(r.next()){
            i = r.getString("ID_EPS");
        }else{
            i="";
        }
        con.cerrar();
    }catch(SQLException j){
        i="";
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
    return i;
}
    public static String get_id_afp(Object afp){
    String i = "";
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r=null;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_AFP WHERE NOMBRE_AFP='"+afp+"'");
        if(r.next()){
            i = r.getString("ID_AFP");
        }else{
            i="";
        }
        con.cerrar();
    }catch(SQLException j){
        i="";
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
    return i;
}
}
