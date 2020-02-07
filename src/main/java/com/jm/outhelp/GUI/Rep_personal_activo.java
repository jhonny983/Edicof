/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.outhelp.GUI;

import com.jm.outhelp.GUI.*;
import com.jm.outhelp.Clases.Animar_barra;
import com.jm.outhelp.Clases.GenerarReportes;
import com.jm.outhelp.Clases.Struct_personal_activo;
import com.jm.outhelp.Clases.Struct_personal_activo_semanal;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ADMIN
 */
public class Rep_personal_activo extends javax.swing.JDialog {

    /**
     * Creates new form Rep_personal_activo
     */
    public Rep_personal_activo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        fecha.setDate(new Date());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rep_consolidado = new javax.swing.JRadioButton();
        rep_semanal = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        cb_formato = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        fecha = new com.toedter.calendar.JDateChooser();
        f_anterior = new javax.swing.JRadioButton();
        hoy = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte personal activo");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccionar reporte"));

        buttonGroup1.add(rep_consolidado);
        rep_consolidado.setSelected(true);
        rep_consolidado.setText("Reporte diario consolidado");

        buttonGroup1.add(rep_semanal);
        rep_semanal.setText("Reporte semanal");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rep_semanal)
                    .addComponent(rep_consolidado))
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(rep_consolidado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rep_semanal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Generar reporte"));

        cb_formato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..", ".pdf", ".xls" }));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/report.png"))); // NOI18N
        jButton1.setText("Generar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Formato");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(cb_formato, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_formato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setBackground(Color.WHITE);
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancel_1.png"))); // NOI18N
        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione fecha"));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, f_anterior, org.jdesktop.beansbinding.ELProperty.create("${selected}"), fecha, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        buttonGroup2.add(f_anterior);
        f_anterior.setText("Otra fecha");

        buttonGroup2.add(hoy);
        hoy.setSelected(true);
        hoy.setText("Hoy");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hoy)
                    .addComponent(f_anterior))
                .addGap(46, 46, 46)
                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(f_anterior)
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hoy)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        FileFilter filter = null;
        Date fecha_rep = null;
        if (hoy.isSelected()) {
            fecha_rep = new Date();
        } else {
            if (f_anterior.isSelected()) {
                if (fecha.getDate()!=null) {
                    fecha_rep = fecha.getDate();
                }else{
                    fecha.setDate(new Date());
                    fecha_rep = fecha.getDate();
                }
            }
        }
//        if (fecha.getDate().compareTo(new Date())<=0) {
            if (!cb_formato.getSelectedItem().equals("Seleccione..")) {
                if (rep_consolidado.isSelected()) {
                    try {
                        JFileChooser fc_rep_diario = new JFileChooser();
                        if (cb_formato.getSelectedItem().equals(".pdf")) {
                            filter = new FileNameExtensionFilter("Archivos PDF (*.pdf)","pdf");
                            fc_rep_diario.setFileFilter(filter);
                            fc_rep_diario.setSelectedFile(new File("Reporte_diario_consolidado_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha_rep)+".pdf"));
                        }
                        if (cb_formato.getSelectedItem().equals(".xls")) {
                            filter = new FileNameExtensionFilter("Archivos Excel (*.xlsx)","xlsx");
                            fc_rep_diario.setFileFilter(filter);
                            fc_rep_diario.setSelectedFile(new File("Reporte_diario_consolidado_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha_rep)+".xlsx"));
                        }
                        if(fc_rep_diario.showSaveDialog(this)==JFileChooser.APPROVE_OPTION){
                            if (fc_rep_diario.getSelectedFile().exists()) {
                                int conf = JOptionPane.showConfirmDialog(this,"El archivo ya existe\ndesea sobreescribirlo?","Confirmación",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                                if (conf == JOptionPane.YES_OPTION) {
                                    Struct_personal_activo s = new Struct_personal_activo(this,fc_rep_diario.getSelectedFile().getAbsolutePath(),cb_formato.getSelectedItem().toString(), fecha_rep,fc_rep_diario.getSelectedFile().toString());
                                    Wait_rep t = new Wait_rep(this,true,s);
                                    t.setLocationRelativeTo(null);
                                    t.setVisible(true);
                                    this.dispose();
                
                                }
                            }else{
                                Struct_personal_activo s = new Struct_personal_activo(this,fc_rep_diario.getSelectedFile().getAbsolutePath(),cb_formato.getSelectedItem().toString(), fecha_rep,fc_rep_diario.getSelectedFile().toString());
                                Wait_rep t = new Wait_rep(this,true,s);
                                t.setLocationRelativeTo(null);
                                t.setVisible(true);
                                this.dispose();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this,e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (rep_semanal.isSelected()) {
                    System.out.println("REPORTE SEMANAL");
                    try {
                        JFileChooser fc_rep_remanal = new JFileChooser();
                        fc_rep_remanal.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        if (cb_formato.getSelectedItem().equals(".pdf")) {
                            filter = new FileNameExtensionFilter("Archivos PDF (*.pdf)","pdf");
                            fc_rep_remanal.setFileFilter(filter);
                            //fc_rep_remanal.setSelectedFile(new File("Reporte_diario_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha_rep)+".pdf"));
                        }else{
                            if (cb_formato.getSelectedItem().equals(".xls")) {
                                filter = new FileNameExtensionFilter("Archivos Excel (*.xlsx)","xlsx");
                                fc_rep_remanal.setFileFilter(filter);
                                //fc_rep_remanal.setSelectedFile(new File("Reporte_diario_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha_rep)+".xlsx"));
                            }
                        }
                        if(fc_rep_remanal.showSaveDialog(this)==JFileChooser.APPROVE_OPTION){
                            Struct_personal_activo_semanal s = new Struct_personal_activo_semanal(this,fc_rep_remanal.getSelectedFile().getAbsolutePath(),cb_formato.getSelectedItem().toString(),fecha_rep);
                            Wait_rep t = new Wait_rep(this,true,s);
                            t.setLocationRelativeTo(null);
                            t.setVisible(true);
                            this.dispose();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this,e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this,"Seleccione el formato del reporte.", "Error",JOptionPane.ERROR_MESSAGE);
            }
//        }else{
//            JOptionPane.showMessageDialog(this,"La fecha seleccionada no puede ser mayor a la fecha actual.", "Error",JOptionPane.ERROR_MESSAGE);
//        }
            
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
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
            java.util.logging.Logger.getLogger(Rep_personal_activo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Rep_personal_activo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Rep_personal_activo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Rep_personal_activo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Rep_personal_activo dialog = new Rep_personal_activo(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cb_formato;
    private javax.swing.JRadioButton f_anterior;
    private com.toedter.calendar.JDateChooser fecha;
    private javax.swing.JRadioButton hoy;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton rep_consolidado;
    private javax.swing.JRadioButton rep_semanal;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
