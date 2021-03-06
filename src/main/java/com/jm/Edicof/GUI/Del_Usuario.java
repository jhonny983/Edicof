/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.GUI;

import com.jm.Edicof.Clases.AutoCompletion;
import com.jm.Edicof.Clases.Conexion;
import java.awt.Event;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.InputMap;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author ADMIN
 */
public class Del_Usuario extends javax.swing.JDialog {

    /**
     * Creates new form Add_Empleado
     */
    public Del_Usuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        ac_cedula();
        
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
        nombre_1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ape_1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        nombre_2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ape_2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        login = new javax.swing.JTextField();
        cb_cedula = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        tipo = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Eliminar Usuario");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion del empleado"));

        jLabel1.setText("Primer Nombre");

        nombre_1.setEditable(false);
        nombre_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombre_1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombre_1KeyTyped(evt);
            }
        });

        jLabel3.setText("Primer Apellido");

        ape_1.setEditable(false);
        ape_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ape_1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ape_1KeyTyped(evt);
            }
        });

        jLabel4.setText("Segundo Nombre");

        nombre_2.setEditable(false);
        nombre_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombre_2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombre_2KeyTyped(evt);
            }
        });

        jLabel5.setText("Segundo Apellido");

        ape_2.setEditable(false);
        ape_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ape_2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ape_2KeyTyped(evt);
            }
        });

        jLabel6.setText("Tipo de usuario");

        jLabel9.setText("Login");

        login.setEditable(false);
        login.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        login.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                loginKeyTyped(evt);
            }
        });

        AutoCompletion.enable(cb_cedula);
        cb_cedula.setEditable(true);
        cb_cedula.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_cedulaItemStateChanged(evt);
            }
        });
        cb_cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cb_cedulaKeyReleased(evt);
            }
        });

        jLabel7.setText("Cedula");

        tipo.setEditable(false);
        tipo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nombre_1)
                                    .addComponent(cb_cedula, 0, 144, Short.MAX_VALUE))
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(nombre_2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(ape_1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel5))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel6)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ape_2, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                    .addComponent(tipo))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cb_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombre_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(nombre_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ape_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(ape_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(156, 156, 156)
                .addComponent(jButton1)
                .addGap(38, 38, 38)
                .addComponent(jButton3)
                .addContainerGap(158, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void nombre_1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombre_1KeyTyped
        // TODO add your handling code here:
        char a=evt.getKeyChar();
        a = Character.toUpperCase(a);
        System.out.println(evt.getKeyCode());
        if(!(a>=KeyEvent.VK_A && a<=KeyEvent.VK_Z)& a!=209) {
            evt.consume();
        }
        if(nombre_1.getText().length()>50){
            evt.consume();
        }
        
    }//GEN-LAST:event_nombre_1KeyTyped

    private void ape_1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ape_1KeyTyped
        // TODO add your handling code here:
        char a=evt.getKeyChar();
        a = Character.toUpperCase(a);
        if(!(a>=KeyEvent.VK_A && a<=KeyEvent.VK_Z)& a!=209) {
            evt.consume();
        }
        if(ape_1.getText().length()>50){
            evt.consume();
        }
    }//GEN-LAST:event_ape_1KeyTyped

    private void nombre_2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombre_2KeyTyped
        // TODO add your handling code here:
        char a=evt.getKeyChar();
        a = Character.toUpperCase(a);
        if(!(a>=KeyEvent.VK_A && a<=KeyEvent.VK_Z)& a!=209) {
            evt.consume();
        }
        if(nombre_2.getText().length()>50){
            evt.consume();
        }
    }//GEN-LAST:event_nombre_2KeyTyped

    private void ape_2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ape_2KeyTyped
        // TODO add your handling code here:
        char a=evt.getKeyChar();
        a = Character.toUpperCase(a);
        if(!(a>=KeyEvent.VK_A && a<=KeyEvent.VK_Z)& a!=209) {
            evt.consume();
        }
        if(ape_2.getText().length()>50){
            evt.consume();
        }
    }//GEN-LAST:event_ape_2KeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
        try {
            int conf = JOptionPane.showConfirmDialog(this,"Esta seguro que desea continuar?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (conf == JOptionPane.YES_OPTION) {
                con.s.executeUpdate("DELETE FROM T_USUARIOS WHERE ID_USUARIO="+cb_cedula.getSelectedItem());
                JOptionPane.showMessageDialog(this,"El usuario fue eliminado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
                con.cerrar();
                this.dispose();
            }
            con.cerrar();
        } catch (HeadlessException | SQLException e) {
            con.cerrar();
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void loginKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loginKeyTyped
        // TODO add your handling code here:
        char a=evt.getKeyChar();
        if(a==KeyEvent.VK_COMMA ||a==KeyEvent.VK_PERIOD) {
            evt.consume();
        }
    }//GEN-LAST:event_loginKeyTyped

    private void cb_cedulaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_cedulaItemStateChanged
        // TODO add your handling code here:
        load_data();
    }//GEN-LAST:event_cb_cedulaItemStateChanged

    private void cb_cedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cb_cedulaKeyReleased
        // TODO add your handling code here:
        int b=evt.getKeyCode();
        if(b==KeyEvent.VK_ENTER){
            load_data();
        }
    }//GEN-LAST:event_cb_cedulaKeyReleased

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
            java.util.logging.Logger.getLogger(Del_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Del_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Del_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Del_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                Del_Usuario dialog = new Del_Usuario(new javax.swing.JFrame(), true);
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
    private static javax.swing.JTextField ape_1;
    private javax.swing.JTextField ape_2;
    private javax.swing.JComboBox<String> cb_cedula;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private static javax.swing.JTextField login;
    private static javax.swing.JTextField nombre_1;
    private javax.swing.JTextField nombre_2;
    private javax.swing.JTextField tipo;
    // End of variables declaration//GEN-END:variables

public void ac_cedula(){
    cb_cedula.removeAllItems();
    cb_cedula.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_USUARIOS ORDER BY ID_USUARIO ASC;");
        while(r.next()){
            String str=r.getString("ID_USUARIO");
            cb_cedula.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public void load_data(){
    if (!cb_cedula.getSelectedItem().equals("Seleccione..")) {
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
        try {
            r = con.s.executeQuery ("SELECT * FROM t_usuarios INNER JOIN t_roles ON (t_usuarios.ID_ROL = t_roles.ID_ROL) WHERE ID_USUARIO = "+cb_cedula.getSelectedItem());
            if (r.next()) {
                nombre_1.setText(r.getString("NOMBRE_USUARIO"));
                nombre_2.setText(r.getString("NOMBRE_2_USUARIO"));
                ape_1.setText(r.getString("APELLIDO_USUARIO"));
                ape_2.setText(r.getString("APELLIDO_2_USUARIO"));
                login.setText(r.getString("USUARIO_USUARIO"));
                tipo.setText(r.getString("NOMBRE_ROL"));
            }
            con.cerrar();
        }catch(SQLException j){
            con.cerrar();
            j.printStackTrace();
            JOptionPane.showMessageDialog(this,j,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    else{
        nombre_1.setText("");
        nombre_2.setText("");
        ape_1.setText("");
        ape_2.setText("");
        login.setText("");
        tipo.setText("");
    }
    


}
}
