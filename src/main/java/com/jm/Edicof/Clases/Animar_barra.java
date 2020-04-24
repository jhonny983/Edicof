/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.Clases;

import com.jm.Edicof.GUI.Main;

/**
 *
 * @author ADMIN
 */
public class Animar_barra extends Thread{
    boolean cont;
    String s;
    public Animar_barra(){
    }

    public void setS(String s) {
        this.s = s;
    }

    @Override
    public void run() {
        cont = true;
        Main.Bar.setString(s);
        Main.Bar.setMaximum(1000);
        Main.Bar.setValue(0);
        while (cont) {
            for(int i=0;i<=1000;i++){
                try {
                    Thread.sleep(100);
                    Main.Bar.setValue(i);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } 
            }   
        }
        Main.Bar.setValue(0);
        Main.statusAnimationLabel.setText("");
        Main.Bar.setString("");
   }

    public void setCont(boolean cont) {
        this.cont = cont;
    }


}