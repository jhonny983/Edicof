/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.Clases;

import com.jm.Edicof.GUI.Main;
import java.sql.* ;
import javax.swing.*;
public class Conexion {
    //public String dbUrl = "jdbc:mysql://"+Main.host+":3306/"+Main.bd+"?useTimezone=true&serverTimezone=UTC";
    private String dbUrl = "jdbc:mysql://"+Main.host+":3306/"+Main.bd;
    private String usuario = Main.usu;//"root";
    private String contrasena = Main.cont;//"root";
    public Connection c;
    public Statement s;
    boolean p=false;
    public Conexion() {
        
    }
    public Conexion(String ip, String db, String usu, String cont) {
        //this.dbUrl="jdbc:mysql://"+ip+":3306/"+db+"?useTimezone=true&serverTimezone=UTC";
        this.dbUrl="jdbc:mysql://"+ip+":3306/"+db;
        this.usuario = usu;
        this.contrasena = cont;
    }
//    public boolean first_conexion(){
//        try {
//            //Class.forName ("com.mysql.cj.jdbc.Driver");
//            Class.forName ("com.mysql.jdbc.Driver");
//            c = DriverManager.getConnection(dbUrl, usuario, contrasena);
//            s = c.createStatement() ;
//            p=true;
//        }catch(Exception e) {
//            p=false;
//            JOptionPane.showMessageDialog(null,"Error de conexion!\n " + e, "Error",JOptionPane.ERROR_MESSAGE);
//        }
//        return p;
//    }
    public boolean test(String h, String d, String u, String c){
        boolean t = false;
        String url = "jdbc:mysql://"+h+":3306/"+d; 
        Statement s = null;
        //String url = "jdbc:mysql://"+h+":3306/"+d+"?useTimezone=true&serverTimezone=UTC"; 
        try {
            //Class.forName ("com.mysql.cj.jdbc.Driver");
            Class.forName ("com.mysql.jdbc.Driver");
            this.c = DriverManager.getConnection(url, u, c);
            s = this.c.createStatement() ;
            t=true;
        }catch(Exception e) {
            t=false;
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error de conexion!\n " + e, "Error",JOptionPane.ERROR_MESSAGE);
        }finally{
            if (s != null) {
                try {
                    s.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (this.c != null) {
                try {
                    this.c.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            
        }
        
        
    
    return t;
    }
    public Connection conexion(){
        if (c == null) {
            try {
                Class.forName ("com.mysql.jdbc.Driver");
                c = DriverManager.getConnection(dbUrl, usuario, contrasena);
                s = c.createStatement() ;
            }catch(ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"Error de conexion!\n " + e, "Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        return c;
        
    }
    public void cerrar(){
    	if (s!=null) {
            try {
                s.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,"No se puede Cerrar!" + ex, "Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        if (c!= null) {
            try{
                c.close();
                c=null;
            } catch(Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"No se puede Cerrar!" + e, "Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    	
    }
    
    
}