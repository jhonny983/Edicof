/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.outhelp.GUI;

import com.jm.outhelp.GUI.*;
import com.jm.outhelp.Clases.AutoCompletion;
import com.jm.outhelp.Clases.Conexion;
import java.awt.Event;
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
public class Edd_Empleado extends javax.swing.JDialog {

    /**
     * Creates new form Add_Empleado
     */
    public Edd_Empleado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        ac_cedula();
        InputMap map1 = cedula.getInputMap(cedula.WHEN_FOCUSED);
        map1.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
    }
    public Edd_Empleado(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        ac_cedula();
        InputMap map1 = cedula.getInputMap(cedula.WHEN_FOCUSED);
        map1.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
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
        jLabel2 = new javax.swing.JLabel();
        cedula = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ape_1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        nombre_2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ape_2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cb_cedula = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Empleado");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion del empleado"));

        jLabel1.setText("Primer Nombre");

        nombre_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombre_1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombre_1KeyTyped(evt);
            }
        });

        jLabel2.setText("Cedula");

        cedula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cedulaKeyTyped(evt);
            }
        });

        jLabel3.setText("Primer Apellido");

        ape_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ape_1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ape_1KeyTyped(evt);
            }
        });

        jLabel4.setText("Segundo Nombre");

        nombre_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombre_2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombre_2KeyTyped(evt);
            }
        });

        jLabel5.setText("Segundo Apellido");

        ape_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ape_2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ape_2KeyTyped(evt);
            }
        });

        jLabel6.setText("Nueva Cedula");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nombre_1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                            .addComponent(cb_cedula, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(nombre_2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ape_1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(ape_2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jButton1)
                        .addGap(38, 38, 38)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void cedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedulaKeyTyped
        // TODO add your handling code here:
        char a=evt.getKeyChar();
        if(!(a>=KeyEvent.VK_0 && a<=KeyEvent.VK_9)) {
            evt.consume();
        }
        if(cedula.getText().length()>15){
            evt.consume();
        }
    }//GEN-LAST:event_cedulaKeyTyped

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
        if (!cedula.getText().equals("")) {
            if (!nombre_1.getText().equals("")) {
                if (!ape_1.getText().equals("")) {
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r;
                    try {
                        r = con.s.executeQuery ("SELECT * FROM `t_empleados` WHERE ID_EMP = "+cedula.getText()+" AND ID_EMP <>"+cb_cedula.getSelectedItem());
                        if(r.next()){
                            JOptionPane.showMessageDialog(this,"El empleado que desea ingresar ya existe","Error",JOptionPane.ERROR_MESSAGE);
                        }else{
                            int conf = JOptionPane.showConfirmDialog(this,"Esta seguro que desea continuar?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                            if (conf == JOptionPane.YES_OPTION) {
                                con.s.executeUpdate("UPDATE `t_empleados` SET `ID_EMP`="+cedula.getText()+",`NOMBRE_1_EMP`='"+nombre_1.getText().toUpperCase()+"',`NOMBRE_2_EMP`='"+nombre_2.getText().toUpperCase()+"',`APELLIDO_1_EMP`='"+ape_1.getText().toUpperCase()+"',`APELLIDO_2_EMP`='"+ape_2.getText().toUpperCase()+"' WHERE ID_EMP="+cb_cedula.getSelectedItem());
                                JOptionPane.showMessageDialog(this,"El empleado fue actualizado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
                                con.cerrar();
                                this.dispose();
                            }
                        }
                        con.cerrar();
                    } catch (SQLException e) {
                        con.cerrar();
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this,"Digite el primer apellido del empleado","Error",JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,"Digite el primer nombre del empleado","Error",JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,"Digite la cedula del empleado","Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cb_cedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cb_cedulaKeyReleased
        // TODO add your handling code here:
        int b=evt.getKeyCode();
        if(b==KeyEvent.VK_ENTER){
            load_data();
        }
    }//GEN-LAST:event_cb_cedulaKeyReleased

    private void cb_cedulaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_cedulaItemStateChanged
        // TODO add your handling code here:
        load_data();
    }//GEN-LAST:event_cb_cedulaItemStateChanged

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
            java.util.logging.Logger.getLogger(Edd_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Edd_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Edd_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Edd_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Edd_Empleado dialog = new Edd_Empleado(new javax.swing.JFrame(), true);
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
    private static javax.swing.JTextField cedula;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private static javax.swing.JTextField nombre_1;
    private javax.swing.JTextField nombre_2;
    // End of variables declaration//GEN-END:variables
public void ac_cedula(){
    cb_cedula.removeAllItems();
    cb_cedula.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_EMPLEADOS ORDER BY ID_EMP ASC;");
        while(r.next()){
            String str=r.getString("ID_EMP");
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
            r = con.s.executeQuery ("SELECT * FROM T_EMPLEADOS WHERE ID_EMP = "+cb_cedula.getSelectedItem());
            if (r.next()) {
                cedula.setText(r.getString("ID_EMP"));
                nombre_1.setText(r.getString("NOMBRE_1_EMP"));
                nombre_2.setText(r.getString("NOMBRE_2_EMP"));
                ape_1.setText(r.getString("APELLIDO_1_EMP"));
                ape_2.setText(r.getString("APELLIDO_2_EMP"));
            }
            con.cerrar();
        }catch(SQLException j){
            con.cerrar();
            j.printStackTrace();
            JOptionPane.showMessageDialog(this,j,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    else{
        cedula.setText("");
        nombre_1.setText("");
        nombre_2.setText("");
        ape_1.setText("");
        ape_2.setText("");
    }
    


}

}
