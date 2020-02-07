/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.outhelp.Clases;

import java.io.IOException;

/**
 *
 * @author ADMIN
 */
public class HiloUpdate extends Thread{
    String url_update="";
    String version="";
    public HiloUpdate(String url, String ver){
        this.url_update = url;
        this.version = ver;
    }
    @Override
    public void run (){
        try {
            Runtime.getRuntime().exec("java -jar Edicof_Update_Downloader.jar \""+url_update+"\" \""+version+"\"");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
