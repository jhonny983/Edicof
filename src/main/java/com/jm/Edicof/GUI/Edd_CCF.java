/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.GUI;

import com.jm.Edicof.Clases.Conexion;
import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;
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
public class Edd_CCF extends javax.swing.JDialog {
    TextAutoCompleter tac_ccf = null;
    /**
     * Creates new form Add_Empleado
     */
    public Edd_CCF(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        init();
        ac_ccf();
        tac_ccf.setMode(0);
    }
    public Edd_CCF(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        init();
        ac_ccf();
        tac_ccf.setMode(0);
    }
    public void init(){
        tac_ccf = new TextAutoCompleter(busc_ccf, new AutoCompleterCallback() {
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
        nombre_ccf = new javax.swing.JTextField();
        busc_ccf = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        codigo_ccf = new javax.swing.JTextField();
        nuevo_codigo_ccf = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar CCF");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion de la CCF"));

        jLabel1.setText("Nuevo nombre");

        nombre_ccf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombre_ccf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombre_ccfKeyTyped(evt);
            }
        });

        busc_ccf.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setText("Nombre");

        jLabel3.setText("Codigo");

        jLabel4.setText("Nuevo codigo");

        codigo_ccf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        codigo_ccf.setEnabled(false);
        codigo_ccf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                codigo_ccfKeyTyped(evt);
            }
        });

        nuevo_codigo_ccf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nuevo_codigo_ccf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevo_codigo_ccfActionPerformed(evt);
            }
        });
        nuevo_codigo_ccf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nuevo_codigo_ccfKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(23, 23, 23)
                            .addComponent(nuevo_codigo_ccf))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(55, 55, 55)
                            .addComponent(codigo_ccf, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(busc_ccf)
                            .addComponent(nombre_ccf, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(busc_ccf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombre_ccf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigo_ccf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nuevo_codigo_ccf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(7, 7, 7))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(172, 172, 172))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void nombre_ccfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombre_ccfKeyTyped
        // TODO add your handling code here:
        char a=evt.getKeyChar();
        a = Character.toUpperCase(a);
//        if (a!=KeyEvent.VK_PERIOD & a!=KeyEvent.VK_MINUS & a!=KeyEvent.VK_SPACE) {
//            if(!(a>=KeyEvent.VK_A && a<=KeyEvent.VK_Z)& a!=209) {
//                evt.consume();
//            }
//        }
        if(nombre_ccf.getText().length()>50){
            evt.consume();
        }
        
    }//GEN-LAST:event_nombre_ccfKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (!busc_ccf.getText().equals("")) {
            if (check_field (nombre_ccf.getText())) {
                if (check_field (nuevo_codigo_ccf.getText())) {
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r;
                    try {
                        r = con.s.executeQuery ("SELECT * FROM `t_ccf` WHERE NOMBRE_CCF = '"+nombre_ccf.getText().toUpperCase()+"'");
                        if(r.next() & !busc_ccf.getText().equals(nombre_ccf.getText())){
                            JOptionPane.showMessageDialog(this,"La CCF que intenta ingresar ya existe","Error",JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                            r = con.s.executeQuery ("SELECT * FROM `t_ccf` WHERE ID_CCF = '"+nuevo_codigo_ccf.getText().toUpperCase().trim()+"'");
                        if(r.next() & !codigo_ccf.getText().equals(nuevo_codigo_ccf.getText())){
                            JOptionPane.showMessageDialog(this,"La CCF que intenta ingresar ya existe","Error",JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                            int conf = JOptionPane.showConfirmDialog(this,"Esta seguro que desea continuar?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                            if (conf == JOptionPane.YES_OPTION) {
                                con.s.executeUpdate("UPDATE `t_ccf` SET `NOMBRE_CCF`='"+nombre_ccf.getText().toUpperCase()+"', `ID_CCF`='"+nuevo_codigo_ccf.getText().toUpperCase().trim()+"' WHERE `NOMBRE_CCF`='"+busc_ccf.getText().toUpperCase()+"'");
                                JOptionPane.showMessageDialog(this,"La CCF fue ingresada correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
                                con.cerrar();
                                this.dispose();
                            }
                        }
                            

                        }
                        con.cerrar();
                    } catch (SQLException | HeadlessException e) {
                        con.cerrar();
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(this,"Verifique que el nuevo codigo de la CCF no este vacio ni contenga caracteres especiales.","Error",JOptionPane.ERROR_MESSAGE);
                }
                    
            } else {
                JOptionPane.showMessageDialog(this,"Verifique que el nuevo nombre de la CCF no este vacio ni contenga caracteres especiales.","Error",JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,"Digite el nombre de la CCF que desea modificar","Error",JOptionPane.ERROR_MESSAGE);
        }
           
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void codigo_ccfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigo_ccfKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_codigo_ccfKeyTyped

    private void nuevo_codigo_ccfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevo_codigo_ccfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nuevo_codigo_ccfActionPerformed

    private void nuevo_codigo_ccfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nuevo_codigo_ccfKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_nuevo_codigo_ccfKeyTyped

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
            java.util.logging.Logger.getLogger(Edd_CCF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Edd_CCF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Edd_CCF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Edd_CCF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            @Override
            public void run() {
                Edd_CCF dialog = new Edd_CCF(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField busc_ccf;
    private static javax.swing.JTextField codigo_ccf;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private static javax.swing.JTextField nombre_ccf;
    private static javax.swing.JTextField nuevo_codigo_ccf;
    // End of variables declaration//GEN-END:variables
public void ac_ccf(){
    tac_ccf.removeAllItems();
    tac_ccf.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_CCF ORDER BY NOMBRE_CCF ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_CCF");
            tac_ccf.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public void load_data(String ccf){
    if (!ccf.equals("Seleccione..")) {
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
        try {
            r = con.s.executeQuery ("SELECT * FROM `t_ccf` WHERE NOMBRE_CCF = '"+ccf+"'");
            if (r.next()) {
                nombre_ccf.setText(r.getString("NOMBRE_CCF"));
                codigo_ccf.setText(r.getString("ID_CCF"));
                nuevo_codigo_ccf.setText(codigo_ccf.getText());
            }
            con.cerrar();
        }catch(SQLException j){
            con.cerrar();
            j.printStackTrace();
            JOptionPane.showMessageDialog(this,j,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    else{
        nombre_ccf.setText("");
        
    }
}
public boolean check_field (Object field){
boolean ret=false;
    if (field!=null) {
        if (!field.toString().trim().equals("")) {
            if (chech_char(field.toString().trim(),"'$%&()=?¡¿/*+[]{};:<>,-")) {
                if (!field.toString().equals("")) {
                   ret=true;
                }
            } 
        }
    }
return ret;
}
public boolean chech_char(String s, String c){
    //boolean ret=false;
    char []char_s=s.toCharArray();
    char []char_c=c.toCharArray();  
    for (int i = 0; i < char_s.length; i++) {
        for (int j = 0; j < char_c.length; j++) {
            if (char_s[i]==char_c[j]) {
                return false;
            }
        }
    }
    return true;
}
}
