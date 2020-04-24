/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.Clases;
import com.jm.Edicof.GUI.Email;
import com.jm.Edicof.GUI.Wait_mail;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class EmailSenderService extends Thread{
    File[] list;
    JDialog jwm;
    public EmailSenderService(File[] l, JDialog j) {
        this.list=l;
        this.jwm = j;
    }
    public EmailSenderService() {
        
    }

    @Override
    public void run() {
        Email.informe.setText("");
        //Email.listModel_errores.clear();
        Wait_mail.btn_aceptar.setEnabled(false);
        if (list.length>0) {
            Email.informe.setText("Informe de envio de correo automático\n");
            Email.informe.setText(Email.informe.getText()+"Numero de empresas en la lista: "+get_num_empresas()+"\n");
            Email.informe.setText(Email.informe.getText()+"Numero de correos configurados a enviar: "+get_num_mail()+"\n");
            //System.out.println("Numero de correos a enviar: "+get_num_mail());
            if (get_num_mail()>0) {
                Wait_mail.mensaje.setText("Enviando correos");
                Wait_mail.bar.setMinimum(0);
                Wait_mail.bar.setMaximum(get_num_mail());
                Wait_mail.bar.setValue(0);
                for (File archivo : list) {
                    String nit = get_nit_from_file(archivo.getName());
                    if (!nit.equals("")) {
                        Conexion con = new Conexion();
                        con.conexion();
                        ResultSet r;
                        try{
                            r = con.s.executeQuery ("SELECT * FROM T_EMPRESAS WHERE ID_EMPRESA='"+nit+"'");
                            if(r.next()){
                                if (r.getInt("ENABLE_INTERNO")==1) {
                                    Wait_mail.mensaje.setText("Enviando correo a "+r.getString("NOMBRE_EMPRESA"));
                                    if (enviarcorreo(Email.remitente.getText(),new String (Email.contraseña.getPassword()),r.getString("CORREO_INTERNO"),Email.mensaje.getText(),Email.asunto.getText()+"_"+r.getString("ID_EMPRESA")+"_"+r.getString("NOMBRE_EMPRESA"), Email.path.getText(), archivo.getName())) {
                                        Wait_mail.bar.setValue(Wait_mail.bar.getValue());
                                        Email.informe.setText(Email.informe.getText()+"Correo enviado: "+r.getString("ID_EMPRESA")+"_"+r.getString("NOMBRE_EMPRESA")+": "+r.getString("CORREO_INTERNO")+"\n");
                                    }else{
                                        Wait_mail.bar.setValue(Wait_mail.bar.getValue());
                                        Email.informe.setText(Email.informe.getText()+"Error al enviar correo: "+r.getString("ID_EMPRESA")+"_"+r.getString("NOMBRE_EMPRESA")+": "+r.getString("CORREO_INTERNO")+"\n");
                                        //Email.listModel_errores.addElement(r.getString("ID_EMPRESA")+"_"+r.getString("NOMBRE_EMPRESA")+": "+r.getString("CORREO_INTERNO"));
                                    }
                                }
                                if (r.getInt("ENABLE_1")==1) {
                                    Wait_mail.mensaje.setText("Enviando correo a "+r.getString("NOMBRE_EMPRESA"));
                                    if (enviarcorreo(Email.remitente.getText(),new String (Email.contraseña.getPassword()),r.getString("CORREO_CONTRATISTA_1"),Email.mensaje.getText(),Email.asunto.getText()+"_"+r.getString("ID_EMPRESA")+"_"+r.getString("NOMBRE_EMPRESA"), Email.path.getText(), archivo.getName())) {
                                        Wait_mail.bar.setValue(Wait_mail.bar.getValue());
                                        Email.informe.setText(Email.informe.getText()+"Correo enviado: "+r.getString("ID_EMPRESA")+"_"+r.getString("NOMBRE_EMPRESA")+": "+r.getString("CORREO_CONTRATISTA_1")+"\n");
                                    }else{
                                        Wait_mail.bar.setValue(Wait_mail.bar.getValue());
                                        Email.informe.setText(Email.informe.getText()+"Error al enviar correo: "+r.getString("ID_EMPRESA")+"_"+r.getString("NOMBRE_EMPRESA")+": "+r.getString("CORREO_CONTRATISTA_1")+"\n");
                                        //Email.listModel_errores.addElement(r.getString("ID_EMPRESA")+"_"+r.getString("NOMBRE_EMPRESA")+": "+r.getString("CORREO_CONTRATISTA_1"));
                                    }
                                }
                                if (r.getInt("ENABLE_2")==1) {
                                    Wait_mail.mensaje.setText("Enviando correo a "+r.getString("NOMBRE_EMPRESA"));
                                    if (enviarcorreo(Email.remitente.getText(),new String (Email.contraseña.getPassword()),r.getString("CORREO_CONTRATISTA_2"),Email.mensaje.getText(),Email.asunto.getText()+"_"+r.getString("ID_EMPRESA")+"_"+r.getString("NOMBRE_EMPRESA"), Email.path.getText(), archivo.getName())) {
                                        Wait_mail.bar.setValue(Wait_mail.bar.getValue());
                                        Email.informe.setText(Email.informe.getText()+"Correo enviado: "+r.getString("ID_EMPRESA")+"_"+r.getString("NOMBRE_EMPRESA")+": "+r.getString("CORREO_CONTRATISTA_2")+"\n");
                                    }else{
                                        Wait_mail.bar.setValue(Wait_mail.bar.getValue());
                                        Email.informe.setText(Email.informe.getText()+"Error al enviar correo: "+r.getString("ID_EMPRESA")+"_"+r.getString("NOMBRE_EMPRESA")+": "+r.getString("CORREO_CONTRATISTA_2")+"\n");
                                        //Email.listModel_errores.addElement(r.getString("ID_EMPRESA")+"_"+r.getString("NOMBRE_EMPRESA")+": "+r.getString("CORREO_CONTRATISTA_2"));
                                    }
                                }
                                if (r.getInt("ENABLE_3")==1) {
                                    Wait_mail.mensaje.setText("Enviando correo a "+r.getString("NOMBRE_EMPRESA"));
                                    if (enviarcorreo(Email.remitente.getText(),new String (Email.contraseña.getPassword()),r.getString("CORREO_CONTRATISTA_3"),Email.mensaje.getText(),Email.asunto.getText()+"_"+r.getString("ID_EMPRESA")+"_"+r.getString("NOMBRE_EMPRESA"), Email.path.getText(), archivo.getName())) {
                                        Wait_mail.bar.setValue(Wait_mail.bar.getValue());
                                        Email.informe.setText(Email.informe.getText()+"Correo enviado: "+r.getString("ID_EMPRESA")+"_"+r.getString("NOMBRE_EMPRESA")+": "+r.getString("CORREO_CONTRATISTA_3")+"\n");
                                    }else{
                                        Wait_mail.bar.setValue(Wait_mail.bar.getValue());
                                        Email.informe.setText(Email.informe.getText()+"Error al enviar correo: "+r.getString("ID_EMPRESA")+"_"+r.getString("NOMBRE_EMPRESA")+": "+r.getString("CORREO_CONTRATISTA_3")+"\n");
                                        //Email.listModel_errores.addElement(r.getString("ID_EMPRESA")+"_"+r.getString("NOMBRE_EMPRESA")+": "+r.getString("CORREO_CONTRATISTA_3"));
                                    }
                                }
                            }
                            con.cerrar();
                        }catch(SQLException j){
                            con.cerrar();
                            j.printStackTrace();
                        }
                    }
                }
                JOptionPane.showMessageDialog(null,"Correos electronicos enviados correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
                this.jwm.dispose();
            }else{
                JOptionPane.showMessageDialog(null,"Ninguna empresa tiene habilitado el correo electronico automático","Error",JOptionPane.ERROR_MESSAGE);
                this.jwm.dispose();
            }
            
        }
        
    }
    
    public boolean enviarcorreo(String de, String clave, String para, String mensaje, String asunto, String path, String file){
        boolean enviado = false;
        BodyPart adjunto = null;
        String aux = "";
        String host="";
        Properties prop=null;
        aux = de.substring(de.indexOf("@")+1,de.length());
        System.out.println(aux);
        try{
            switch (aux) {
                case "gmail.com":   host="smtp.gmail.com"; 
                                    prop=System.getProperties();
                                    prop.put("mail.smtp.starttls.enable","true");
                                    prop.put("mail.smtp.host",host);
                                    prop.put("mail.smtp.user",de);
                                    prop.put("mail.smtp.password",clave);
                                    prop.put("mail.smtp.port",587);
                                    prop.put("mail.smtp.auth","true");
                                    break;
                case "hotmail.com": host="smtp.live.com"; 
                                    prop=System.getProperties();
                                    prop.put("mail.smtp.starttls.enable","true");
                                    prop.put("mail.smtp.host",host);
                                    prop.put("mail.smtp.user",de);
                                    prop.put("mail.smtp.password",clave);
                                    prop.put("mail.smtp.port",25);
                                    prop.put("mail.smtp.auth","true");
                                    break;
                case "outlook.com": /*host="smtp-mail.outlook.com"; 
                                    prop=System.getProperties();
                                    prop.put("mail.smtp.starttls.enable","true");
                                    prop.put("mail.smtp.host",host);
                                    prop.put("mail.smtp.user",de);
                                    prop.put("mail.smtp.password",clave);
                                    prop.put("mail.smtp.port",25);
                                    prop.put("mail.smtp.auth","true");*/
                                    host="SMTP.Office365.com"; 
                                    prop=System.getProperties();
                                    prop.put("mail.smtp.starttls.enable","true");
                                    prop.put("mail.smtp.host",host);
                                    prop.put("mail.smtp.user",de);
                                    prop.put("mail.smtp.password",clave);
                                    prop.put("mail.smtp.port",587);
                                    prop.put("mail.smtp.auth","true");
                                    break;
                case "yahoo.com":   host="smtp.correo.yahoo.es"; 
                                    prop=System.getProperties();
                                    prop.put("mail.smtp.starttls.enable","true");
                                    prop.put("mail.smtp.host",host);
                                    prop.put("mail.smtp.user",de);
                                    prop.put("mail.smtp.password",clave);
                                    prop.put("mail.smtp.port",587);
                                    prop.put("mail.smtp.auth","true");
                                    break;
                case "cbolivar.com":    /*host="zimbra.cbolivar.com"; 
                                        prop=System.getProperties();
                                        prop.put("mail.smtp.host",host);
                                        prop.put("mail.smtp.user",de);
                                        prop.put("mail.smtp.password",clave);
                                        prop.put("mail.smtp.port",587);
                                        prop.put("mail.smtp.auth","true");*/
                                        host="smtp.gmail.com"; 
                                        prop=System.getProperties();
                                        prop.put("mail.smtp.starttls.enable","true");
                                        prop.put("mail.smtp.host",host);
                                        prop.put("mail.smtp.user",de);
                                        prop.put("mail.smtp.password",clave);
                                        prop.put("mail.smtp.port",587);
                                        prop.put("mail.smtp.auth","true");
                                        break;
                case "edoccidente.onmicrosoft.com":     host="smtp.office365.com"; 
                                                        prop=System.getProperties();
                                                        prop.put("mail.smtp.starttls.enable","true");
                                                        prop.put("mail.smtp.host",host);
                                                        prop.put("mail.smtp.user",de);
                                                        prop.put("mail.smtp.password",clave);
                                                        prop.put("mail.smtp.port",587);
                                                        prop.put("mail.smtp.auth","true");
                                                        break;
            }
            Session session=Session.getDefaultInstance(prop,null);
            session.setDebug(true);
            /////////////*******************************AGREGANDO TEXTO DEL MENSAJE*******************************/////////////
            BodyPart texto = new MimeBodyPart();
            texto.setText(mensaje);
            /////////////*******************************AGREGANDO ADJUNTO DEL MENSAJE*******************************/////////////
            if (!path.equals("") & !file.equals("")) {
                adjunto = new MimeBodyPart();
                adjunto.setDataHandler(new DataHandler(new FileDataSource(path+"/"+file)));
                adjunto.setFileName(file);
            }
            ///////////////****************************JUNTANDO TEXTO Y ADJUNTO*******************************/////////////
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            if (!path.equals("") & !file.equals("")) {
                multiParte.addBodyPart(adjunto);
            }
            
            /////////////*******************************************************
            MimeMessage message= new MimeMessage(session);
            message.setFrom(new InternetAddress(de));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject(asunto);
            message.setContent(multiParte);
            Transport transport = session.getTransport("smtp");
            //transport.connect(host,de,clave);
            transport.connect(de,clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            enviado=true;
            
    }catch (Exception e){
        enviado=false;
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
    }  
    return enviado;
    }
    public String get_nit_from_file (String n){
    String nit="";
    for (int i = 0; i < n.length(); i++) {
        if (is_numeric(n.charAt(i))) {
            nit+=n.charAt(i);
        }else{
            return nit;
        }
    }
return "";
}
    public boolean is_numeric(char c){
    try {
        Integer.parseInt(String.valueOf(c));
        return true;
    } catch (NumberFormatException  e) {
        return false;
    }
}
    public int get_num_mail(){
        int count = 0;
        if (list.length>0) {
            Conexion con = new Conexion();
            con.conexion();
            ResultSet r;
            for (File archivo : list) {
                String nit = get_nit_from_file(archivo.getName());
                if (!nit.equals("")) {
                    try{
                        r = con.s.executeQuery ("SELECT * FROM T_EMPRESAS WHERE ID_EMPRESA='"+nit+"'");
                        if(r.next()){
                            if (r.getInt("ENABLE_INTERNO")==1) {
                                count++;
                            }
                            if (r.getInt("ENABLE_1")==1) {
                                count++;
                            }
                            if (r.getInt("ENABLE_2")==1) {
                                count++;
                            }
                            if (r.getInt("ENABLE_3")==1) {
                                count++;
                            }
                        }
                    }catch(SQLException j){
                        //con.cerrar();
                        j.printStackTrace();
                    }
                }
            }
            con.cerrar();
        }
    return count;
    }
    public int get_num_empresas(){
        int count =0;
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
        for (File archivo : list) {
            String nit = get_nit_from_file(archivo.getName());
            if (!nit.equals("")) {
                try{
                    r = con.s.executeQuery ("SELECT * FROM T_EMPRESAS WHERE ID_EMPRESA='"+nit+"'");
                    if(r.next()){
                        count ++;
                    }
                }catch(SQLException j){
                    //con.cerrar();
                    j.printStackTrace();
                }
            }
        }
        con.cerrar();
        return count;
    }
}