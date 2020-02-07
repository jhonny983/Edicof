/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.outhelp.Clases;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jm.outhelp.GUI.Main;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Autom
 */
public class Hilo_Bloqueo_Inicio extends Thread{
    public Hilo_Bloqueo_Inicio(){
    }
    
    @Override
    public void run (){
        Main.statusAnimationLabel.setText("Tiempo de bloqueo...");
        float v;
        for(float i=0;i<=60;i++){
            try {
                Thread.sleep(1000);
                v = (i/60)*100;
                Main.Bar.setValue((int)v);
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilo_Bloqueo_Inicio.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        Main.Bar.setValue(0);
        Main.statusAnimationLabel.setText("");
        Main.time_login=true;
    }
}
