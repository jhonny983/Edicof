/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.outhelp.Clases;

import com.jm.outhelp.GUI.Main;
import java.sql.* ;
import javax.swing.*;
public class Conexion {
    public String dbUrl = "jdbc:mysql://"+Main.host+":3306/"+Main.bd;
    public String usuario = Main.usu;//"root";
    public String contrasena = Main.cont;//"root";
    static public Connection c;
    public Statement s;
    boolean p=false;
    public Conexion() {
        
    }
    public Conexion(String ip, String db, String usu, String cont) {
        this.dbUrl="jdbc:mysql://"+ip+":3306/"+db;
        this.usuario = usu;
        this.contrasena = cont;
    }
    public boolean first_conexion(){
        try {
            Class.forName ("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection(dbUrl, usuario, contrasena);
            s = c.createStatement() ;
            p=true;
        }catch(Exception e) {
            p=false;
            JOptionPane.showMessageDialog(null,"Error de conexion!\n " + e, "Error",JOptionPane.ERROR_MESSAGE);
        }
        return p;
    }
    public boolean test(String h, String d, String u, String c){
        boolean t = false;
        String url = "jdbc:mysql://"+h+":3306/"+d; 
        try {
            Class.forName ("com.mysql.jdbc.Driver");
            this.c = DriverManager.getConnection(url, u, c);
            this.s = this.c.createStatement() ;
            t=true;
        }catch(Exception e) {
            t=false;
            //JOptionPane.showMessageDialog(null,"Error de conexion!\n " + e, "Error",JOptionPane.ERROR_MESSAGE);
        }
    
    return t;
    }
    public boolean conexion(){
        try {
            Class.forName ("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection(dbUrl, usuario, contrasena);
            s = c.createStatement() ;
            p=true;
        }catch(ClassNotFoundException | SQLException e) {
            p=false;
            JOptionPane.showMessageDialog(null,"Error de conexion!\n " + e, "Error",JOptionPane.ERROR_MESSAGE);
        }
        return p;
    }
    public void cerrar()
    {
    	try{
            c.close();
        } catch(Exception e) {
         JOptionPane.showMessageDialog(null,"No se puede Cerrar!" + e, "Error",JOptionPane.ERROR_MESSAGE);
       }
    	
    }
    
    
}