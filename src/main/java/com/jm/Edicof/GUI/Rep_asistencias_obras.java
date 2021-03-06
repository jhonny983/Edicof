/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.GUI;

import com.jm.Edicof.Clases.Conexion;
import com.jm.Edicof.Clases.Struct_asistencias;
import com.jm.Edicof.Clases.Struct_asistencias_obras;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ADMIN
 */
public class Rep_asistencias_obras extends javax.swing.JDialog {
TextAutoCompleter tac_obras = null;
    /**
     * Creates new form Rep_biometrico
     */
    public Rep_asistencias_obras(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        init();
        ac_obra();
        tac_obras.setMode(0);
    }
public void init(){
        tac_obras = new TextAutoCompleter(t_obra);
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        f_inicial = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        f_final = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        t_obra = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cb_formato_1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cb_formato = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte Asistencia por Obras");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione rango de fechas"));

        jLabel1.setText("Fecha de inicio");

        jLabel2.setText("Fecha final");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(f_inicial, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                    .addComponent(f_final, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(f_inicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(f_final, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione la obra"));

        jLabel5.setText("Obra");

        t_obra.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setText("Formato");

        cb_formato_1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..", ".pdf", ".xls" }));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/report.png"))); // NOI18N
        jButton2.setText("Generar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(38, 38, 38)
                .addComponent(t_obra)
                .addGap(34, 34, 34))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jLabel4)
                .addGap(60, 60, 60)
                .addComponent(cb_formato_1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(t_obra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jLabel4)
                    .addComponent(cb_formato_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Generar reporte"));

        jLabel3.setText("Formato");

        cb_formato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..", ".pdf", ".xls" }));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/report.png"))); // NOI18N
        jButton1.setText("Generar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_formato, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_formato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jButton3.setBackground(Color.WHITE);
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancel_1.png"))); // NOI18N
        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        FileFilter filter = null;
        Date f_f_rep;
        f_inicial.setDate(clear_date(f_inicial.getDate()));
        f_final.setDate(clear_date(f_final.getDate()));
        if (f_inicial.getDate()!=null & f_final.getDate()!=null) {
//            if (check_empleador(t_empleador.getText())) {
                if (f_inicial.getDate().compareTo(f_final.getDate())<=0) {
                    Calendar in = Calendar.getInstance();
                    in.setTime(f_inicial.getDate());
                    Calendar fin = Calendar.getInstance();
                    fin.setTime(in.getTime());
                    fin.add(Calendar.DAY_OF_YEAR, 29);
                    if (f_final.getDate().compareTo(fin.getTime())<=0) {
                        f_f_rep=f_final.getDate();
                    }else{
                        f_f_rep=fin.getTime();
                        JOptionPane.showMessageDialog(this,"Recuerde que el reporte de asistencias se genera en un rango maximo de hasta 30 dias.", "Información",JOptionPane.INFORMATION_MESSAGE);
                    }
                    if (!cb_formato.getSelectedItem().equals("Seleccione..")) {
                        JFileChooser fc_asistencias = new JFileChooser();
                        fc_asistencias.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        if(fc_asistencias.showSaveDialog(this)==JFileChooser.APPROVE_OPTION){
                            Struct_asistencias s = new Struct_asistencias(this,fc_asistencias.getSelectedFile().getAbsolutePath(),cb_formato.getSelectedItem().toString(), new SimpleDateFormat("yyyy-MM-dd").format(f_inicial.getDate()), new SimpleDateFormat("yyyy-MM-dd").format(f_f_rep),"OBRAS");
                            Wait_rep t = new Wait_rep(this,true,s);
                            t.setLocationRelativeTo(null);
                            t.setVisible(true);
                            this.dispose();
                            
                        }
                    }else {
                        JOptionPane.showMessageDialog(this,"Seleccione el formato del reporte.", "Error",JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(this,"La fecha final debe ser mayor a la fecha inicial", "Error",JOptionPane.ERROR_MESSAGE);
                }
//            }else{
//                JOptionPane.showMessageDialog(this,"Seleccione un empleador válido.", "Error",JOptionPane.ERROR_MESSAGE);
//            }
        }else{
            JOptionPane.showMessageDialog(this,"Seleccione un rango de fechas válido.", "Error",JOptionPane.ERROR_MESSAGE);
        }
    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        boolean cont =false;
        FileFilter filter = null;
        Date f_f_rep;
        f_inicial.setDate(clear_date(f_inicial.getDate()));
        f_final.setDate(clear_date(f_final.getDate()));
        if (!cb_formato_1.getSelectedItem().equals("Seleccione..")) {
            try {
                if (f_inicial.getDate()!=null) {
                    if (f_final.getDate()!=null) {
                        if (check_obra(t_obra.getText())) {
                            if (f_inicial.getDate().compareTo(f_final.getDate())<0) {
                                Calendar in = Calendar.getInstance();
                                in.setTime(f_inicial.getDate());
                                Calendar fin = Calendar.getInstance();
                                fin.setTime(in.getTime());
                                fin.add(Calendar.DAY_OF_YEAR, 29);
                                if (f_final.getDate().compareTo(fin.getTime())<=0) {
                                    f_f_rep=f_final.getDate();
                                }else{
                                    f_f_rep=fin.getTime();
                                    JOptionPane.showMessageDialog(this,"Recuerde que el reporte de asistencias se genera en un rango maximo de hasta 30 dias.", "Información",JOptionPane.INFORMATION_MESSAGE);
                                }
                                Conexion con = new Conexion();
                                con.conexion();
                                ResultSet r;
                                try{
                                    r = con.s.executeQuery("SELECT \n" +
                                                            "COUNT(t_obra.NOMBRE_OBRA)\n" +
                                                            "FROM\n" +
                                                            "    t_asistencias\n" +
                                                            "    INNER JOIN t_novedades \n" +
                                                            "        ON (t_asistencias.ID_EMPLEADO = t_novedades.ID_EMPLEADO) AND (t_asistencias.ID_EMPRESA = t_novedades.ID_EMPRESA) AND (t_asistencias.F_INGRESO = t_novedades.FECHA_INGRESO) AND (t_asistencias.ID_TIPO = t_novedades.ID_TIPO) AND (t_asistencias.F_RETIRO = t_novedades.FECHA_RETIRO)\n" +
                                                            "    INNER JOIN t_obra \n" +
                                                            "        ON (t_novedades.ID_OBRA = t_obra.ID_OBRA)\n" +
                                                            "    INNER JOIN t_empresas \n" +
                                                            "        ON (t_novedades.ID_EMPRESA = t_empresas.ID_EMPRESA)\n" +
                                                            "    WHERE ((t_asistencias.F_INGRESO <= '"+new SimpleDateFormat("yyyy-MM-dd").format(f_f_rep)+"' AND t_asistencias.F_RETIRO = '1900-01-01')\n" +
                                                            "    OR (t_asistencias.F_INGRESO <= '"+new SimpleDateFormat("yyyy-MM-dd").format(f_f_rep)+"' AND t_asistencias.F_RETIRO >= '"+new SimpleDateFormat("yyyy-MM-dd").format(f_inicial.getDate())+"'))\n" +
                                                            "    AND t_obra.NOMBRE_OBRA = '"+t_obra.getText()+"'");
                                    
                                    if(r.next()){
                                        System.out.println(r.getInt("COUNT(t_obra.NOMBRE_OBRA)"));
                                        if (r.getInt("COUNT(t_obra.NOMBRE_OBRA)")>1) {
                                            cont = true;
                                        }
                                    }
                                    con.cerrar();
                                }catch(SQLException j){
                                    con.cerrar();
                                    JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                                }
                                if (cont) {
                                    JFileChooser fc_asistencias = new JFileChooser();
                                    if (cb_formato_1.getSelectedItem().equals(".pdf")) {
                                        filter = new FileNameExtensionFilter("Archivos PDF (*.pdf)","pdf");
                                        fc_asistencias.setFileFilter(filter);
                                        fc_asistencias.setSelectedFile(new File("Reporte_asistencia_parcial_"+t_obra.getText()+"_"+new SimpleDateFormat("dd-MM-yyyy").format(f_inicial.getDate())+"_A_"+new SimpleDateFormat("dd-MM-yyyy").format(f_f_rep)+"_.pdf"));
                                    }
                                    if (cb_formato_1.getSelectedItem().equals(".xls")) {
                                        filter = new FileNameExtensionFilter("Archivos Excel (*.xlsx)","xlsx");
                                        fc_asistencias.setFileFilter(filter);
                                        fc_asistencias.setSelectedFile(new File("Reporte_asistencia_parcial_"+t_obra.getText()+"_"+new SimpleDateFormat("dd-MM-yyyy").format(f_inicial.getDate())+"_A_"+new SimpleDateFormat("dd-MM-yyyy").format(f_f_rep)+"_.xlsx"));
                                    }
                                    if(fc_asistencias.showSaveDialog(this)==JFileChooser.APPROVE_OPTION){//fc_asistencias.getSelectedFile().getAbsolutePath()
                                        Struct_asistencias_obras s = new Struct_asistencias_obras(this,fc_asistencias.getSelectedFile().getAbsolutePath(),cb_formato_1.getSelectedItem().toString(), new SimpleDateFormat("yyyy-MM-dd").format(f_inicial.getDate()), new SimpleDateFormat("yyyy-MM-dd").format(f_f_rep),"",fc_asistencias.getSelectedFile().toString(),t_obra.getText());
                                        Wait_rep t = new Wait_rep(this,true,s);
                                        t.setLocationRelativeTo(null);
                                        t.setVisible(true);
                                        this.dispose();
                                    }
                                }else{
                                    JOptionPane.showMessageDialog(null,"El empleador seleccionado no tine asistencias con los criterios seleccionados","Información",JOptionPane.INFORMATION_MESSAGE);
                                }

                            } else {
                                JOptionPane.showMessageDialog(this,"La fecha final debe ser mayor a la fecha inicial", "Error",JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this,"Seleccione una obra válida.", "Error",JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this,"Seleccione la fecha final", "Error",JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(this,"Seleccione la fecha inicial", "Error",JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,e, "Error",JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,"Seleccione el formato del reporte.", "Error",JOptionPane.ERROR_MESSAGE);
        }
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
            java.util.logging.Logger.getLogger(Rep_asistencias_obras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Rep_asistencias_obras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Rep_asistencias_obras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Rep_asistencias_obras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Rep_asistencias_obras dialog = new Rep_asistencias_obras(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cb_formato;
    private javax.swing.JComboBox<String> cb_formato_1;
    private com.toedter.calendar.JDateChooser f_final;
    private com.toedter.calendar.JDateChooser f_inicial;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField t_obra;
    // End of variables declaration//GEN-END:variables
public void ac_obra(){
    tac_obras.removeAllItems();
    tac_obras.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM t_obra ORDER BY NOMBRE_OBRA ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_OBRA");
            tac_obras.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public boolean check_empleador(String emp){
    boolean ret=false;
    if (emp.trim().equals("")) {
        System.out.println("Vacio ok");
        ret=false;
    }else{
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
        try{
            r = con.s.executeQuery ("SELECT * FROM T_EMPRESAS WHERE NOMBRE_EMPRESA='"+emp.trim()+"'");
            if(r.next()){
                ret=true;
                //System.out.println("Existe ok");
            }else{
                ret=false;
            }
            con.cerrar();
        }catch(SQLException j){
            con.cerrar();
            j.printStackTrace();
            ret=false;
            System.out.println("No Existe ok");
            //JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    return ret;
}
public boolean check_obra(String obra){
    boolean ret=false;
    if (obra.trim().equals("")) {
        System.out.println("Vacio ok");
        ret=false;
    }else{
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
        try{
            r = con.s.executeQuery ("SELECT * FROM T_OBRA WHERE NOMBRE_OBRA='"+obra.trim()+"'");
            if(r.next()){
                ret=true;
                //System.out.println("Existe ok");
            }else{
                ret=false;
            }
            con.cerrar();
        }catch(SQLException j){
            con.cerrar();
            j.printStackTrace();
            ret=false;
            System.out.println("No Existe ok");
            //JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    return ret;
}
public String get_id_empleador(Object emp){
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
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
    }
    return i;
}
public Date clear_date(Date d){
    Date ret;
    Calendar date = Calendar.getInstance();
    date.setTime(d);
    date.set(Calendar.HOUR_OF_DAY, 0);
    date.set(Calendar.MINUTE, 0);
    date.set(Calendar.SECOND, 0);
    date.set(Calendar.MILLISECOND, 0);
    return date.getTime();
}
}
