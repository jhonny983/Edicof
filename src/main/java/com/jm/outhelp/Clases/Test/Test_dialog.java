/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.outhelp.Clases.Test;

import com.jm.outhelp.Clases.EmailSenderService;
import com.jm.outhelp.Clases.Conexion;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ADMIN
 */
public class Test_dialog extends javax.swing.JDialog {

    /**
     * Creates new form Test_dialog
     */
    public Test_dialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        BigDecimal bd = new BigDecimal("1250");
        bd = bd.setScale(-2, RoundingMode.HALF_UP);
        System.out.println(bd.doubleValue());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Enviar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(170, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jButton1)
                .addGap(38, 38, 38)
                .addComponent(jButton2)
                .addContainerGap(125, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
            Conexion con = new Conexion("127.0.0.1","e_occidente","root","jhonny3029735");
            con.conexion();
        try {    
            ClassLoader cl= this.getClass().getClassLoader();
            InputStream fis = (cl.getResourceAsStream("Reportes/listado_diario_obra.jasper"));
            //InputStream fis = (cl.getResourceAsStream("listado_diario_obra.jasper"));
            JasperReport rep = (JasperReport) JRLoader.loadObject(fis);
            //JasperReport rep = (JasperReport) JRLoader.loadObject("listado_diario_obra.jasper");
            Map par = new HashMap();
            par.put("Fecha",new Date());
            JasperPrint jprint = JasperFillManager.fillReport(rep,par,con.c);
            con.cerrar();
            JasperViewer jv = new JasperViewer(jprint,false);
            jv.setTitle("Reporte Personal Activo");
            jv.setVisible(true);
        } catch (Exception ex) {
            con.cerrar();
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        EmailSenderService send = new EmailSenderService();
        //if (send.enviarcorreo("afiliaciones@cbolivar.com", "afiliaciones08", "jhonny983@gmail.com", "Mensaje de prueba JMailJava", "Prueva JavaMail","","")) {
        if (send.enviarcorreo("aplicativo.masivos@outlook.com", "870510LUIS", "jhonny983@hotmail.com", "Mensaje de prueba JMailJava", "Prueva JavaMail","","")) {
        //if (send.enviarcorreo("jhonny983@hotmail.com", "jhonny29681237", "ventass.jmingenieria@outlook.com", "Mensaje de prueba JMailJava", "Prueva JavaMail","","")) {
            JOptionPane.showMessageDialog(this,"Mensaje enviado","Información",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this,"Mensaje rechazado","Información",JOptionPane.INFORMATION_MESSAGE);
        }
//        if (send.enviarcorreo("jhonny983@gmail.com", "jhonny14703274", "ventas.jmingenieria@outlook.com", "Mensaje de prueba JMailJava", "Prueva JavaMail","","")) {
//        //if (send.enviarcorreo("jhonny983@hotmail.com", "jhonny29681237", "ventas.jmingenieria@outlook.com", "Mensaje de prueba JMailJava", "Prueva JavaMail","","")) {
//            JOptionPane.showMessageDialog(this,"Mensaje enviado","Información",JOptionPane.INFORMATION_MESSAGE);
//        }else{
//            JOptionPane.showMessageDialog(this,"Mensaje rechazado","Información",JOptionPane.INFORMATION_MESSAGE);
//        }
        //USUARIO YWZpbGlhY2lvbmVzQGNib2xpdmFyLmNvbQ==
        //CONTRASEÑA YWZpbGlhY2lvbmVzMDg=
        //mail from: afiliaciones@cbolivar.com
        //rcpt to: jhonny983@gmail.com
        //Subject: test message
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Test_dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Test_dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Test_dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Test_dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Test_dialog dialog = new Test_dialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    // End of variables declaration//GEN-END:variables
}
