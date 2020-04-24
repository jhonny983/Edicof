/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.Clases;

import com.jm.Edicof.GUI.Main;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

//@autor Henry Joe Wong Urquiza

//La clase que tiene la tarea debe de implementar de la clase Job de Quartz
//ya que el programador de la tarea va a buscar una clase que implemente de ella
//y buscara el metodo execute para ejecutar la tarea
public class TareaProgramada implements Job {

  //Metodo que se ejecutara cada cierto tiempo que lo programemos despues
  @Override
  public void execute(JobExecutionContext jec) throws JobExecutionException {
    String executeCmd = "";
    for (int i = 0; i < Main.conection_list.size(); i++) {
        String [] p= (String[])Main.conection_list.get(i);
        //executeCmd = Main.ruta_sql_bin+"/mysqldump -u "+Main.usu+" -p"+Main.cont+" --add-drop-database -B "+Main.bd+"";
        executeCmd = Main.ruta_sql_bin+"/mysqldump -u "+p[2]+" -p"+p[3]+" --add-drop-database -B "+p[1]+"";
        System.out.println(executeCmd);
        try {   
                Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                InputStream is = runtimeProcess.getInputStream();
                FileOutputStream fos = new FileOutputStream(Main.ruta_backup+"/Backup_"+p[1]+"_"+new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date())+".sql");
                byte[] buffer = new byte[50000000];
                int leido = is.read(buffer);
                while (leido > 0) {
                   fos.write(buffer, 0, leido);
                   leido = is.read(buffer);
                }
                fos.close();  
                int processComplete = runtimeProcess.waitFor();
                if(processComplete == 0){
                    System.err.println("Backup taken successfully");
                } else {
                    System.err.println("Could not take mysql backup");
                }
        } catch (IOException | InterruptedException ioe) {
            ioe.printStackTrace();
            JOptionPane.showMessageDialog(null,ioe.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    SimpleDateFormat formato = new SimpleDateFormat("hh:mm:ss");
    System.out.println( "Tarea invocada a la hora: " + formato.format(new Date()));
  }
}