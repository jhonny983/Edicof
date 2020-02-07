/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.outhelp.GUI;

import com.jm.outhelp.GUI.*;
import com.jm.outhelp.Clases.Conexion;
import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class Edd_Entidad extends javax.swing.JDialog {
    TextAutoCompleter tac_ent = null;
    /**
     * Creates new form Add_Empleado
     */
    public Edd_Entidad(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        init();
        ac_ent();
        tac_ent.setMode(0);
    }
    public Edd_Entidad(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        init();
        ac_ent();
        tac_ent.setMode(0);
    }
    public void init(){
        tac_ent = new TextAutoCompleter(busc_ent, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            load_data(selectedItem.toString());
        }
        });
    
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nombre_ent = new javax.swing.JTextField();
        busc_ent = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Entidad");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion de la Entidad"));

        jLabel1.setText("Nuevo nombre");

        nombre_ent.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombre_ent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombre_entKeyTyped(evt);
            }
        });

        busc_ent.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setText("Nombre");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(busc_ent)
                    .addComponent(nombre_ent, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(busc_ent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombre_ent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(26, 26, 26))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/accept_1.png"))); // NOI18N
        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancel_1.png"))); // NOI18N
        jButton3.setText("Cancelar");
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(174, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(172, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nombre_entKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombre_entKeyTyped
        // TODO add your handling code here:
        char a=evt.getKeyChar();
        a = Character.toUpperCase(a);
        if (a!=KeyEvent.VK_PERIOD & a!=KeyEvent.VK_MINUS & a!=KeyEvent.VK_SPACE) {
            if(!(a>=KeyEvent.VK_A && a<=KeyEvent.VK_Z)& a!=209) {
                evt.consume();
            }
        }
        if(nombre_ent.getText().length()>50){
            evt.consume();
        }
        
    }//GEN-LAST:event_nombre_entKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (!busc_ent.getText().equals("")) {
            if (!nombre_ent.getText().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try {
                    r = con.s.executeQuery ("SELECT * FROM `t_entidades` WHERE NOMBRE_ENTIDAD = '"+nombre_ent.getText().toUpperCase()+"'");
                    if(r.next()){
                        JOptionPane.showMessageDialog(this,"La Entidad que intenta ingresar ya existe","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        int conf = JOptionPane.showConfirmDialog(this,"Esta seguro que desea continuar?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                        if (conf == JOptionPane.YES_OPTION) {
                            con.s.executeUpdate("UPDATE `t_entidades` SET `NOMBRE_ENTIDAD`='"+nombre_ent.getText().toUpperCase()+"' WHERE `NOMBRE_ENTIDAD`='"+busc_ent.getText().toUpperCase()+"'");
                            JOptionPane.showMessageDialog(this,"La Entidad fue modificada correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
                            con.cerrar();
                            this.dispose();
                        }

                    }
                    con.cerrar();
                } catch (SQLException | HeadlessException e) {
                    con.cerrar();
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,"Digite el nuevo nombre de la AFP que desea modificar","Error",JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,"Digite el nombre de la AFP que desea modificar","Error",JOptionPane.ERROR_MESSAGE);
        }
           
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Edd_Entidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Edd_Entidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Edd_Entidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Edd_Entidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            @Override
            public void run() {
                Edd_Entidad dialog = new Edd_Entidad(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField busc_ent;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private static javax.swing.JTextField nombre_ent;
    // End of variables declaration//GEN-END:variables
public void ac_ent(){
    tac_ent.removeAllItems();
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_ENTIDADES ORDER BY NOMBRE_ENTIDAD ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_ENTIDAD");
            tac_ent.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public void load_data(String ent){
    if (!ent.equals("Seleccione..") & !ent.equals("")) {
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
        try {
            r = con.s.executeQuery ("SELECT * FROM `t_entidades` WHERE NOMBRE_ENTIDAD = '"+ent+"'");
            if (r.next()) {
                nombre_ent.setText(r.getString("NOMBRE_ENTIDAD"));
            }
            con.cerrar();
        }catch(SQLException j){
            con.cerrar();
            j.printStackTrace();
            JOptionPane.showMessageDialog(this,j,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    else{
        nombre_ent.setText("");
        
    }
}
}
