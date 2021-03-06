/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jm.Edicof.GUI;

import com.jm.Edicof.Clases.Hilo_Bloqueo_Inicio;
import com.jm.Edicof.Clases.Conexion;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import com.jm.Edicof.Encriptar.Encriptar;
import java.awt.Toolkit;

/**
 *
 * @author autom
 */
public class Login extends javax.swing.JDialog {
    private int conteo;

    /**
     * Creates new form Login1
     */
    public Login(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setIconImage(parent.getIconImage());
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        usuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cont = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Inicio de sesion");
        setBackground(new java.awt.Color(-1,true));
        setResizable(false);

        jButton1.setBackground(new java.awt.Color(-1,true));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/accept_1.png"))); // NOI18N
        jButton1.setText("Ingresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(-1,true));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancel_1.png"))); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Inicio de sesion"));

        jLabel1.setText("Usuario");

        usuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setText("Contraseña");

        cont.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cont.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                contKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cont)
                    .addComponent(usuario, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_go.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(63, 63, 63))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(149, 149, 149))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    inicio_sesion();
    verifica_rol();
}//GEN-LAST:event_jButton1ActionPerformed

private void contKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contKeyTyped
// TODO add your handling code here:
    char a=evt.getKeyChar();
    if(a==KeyEvent.VK_ENTER) {
        inicio_sesion();
        verifica_rol();
    }
}//GEN-LAST:event_contKeyTyped

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login dialog = new Login(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        int resp = JOptionPane.showConfirmDialog(null,"Esta seguro que desea salir del sistema?");
                        if (JOptionPane.OK_OPTION == resp){
                            System.exit(0);
                        }
                        else{
                            
                        }
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField cont;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables

void inicio_sesion(){
    Conexion con=new Conexion();
    ResultSet r;
    con.conexion();
    //String c = new String (cont.getPassword());
        try {
            String c = new String (cont.getPassword());
            r=con.s.executeQuery("SELECT t_usuarios.*, t_roles.* FROM t_usuarios INNER JOIN t_roles ON (t_usuarios.ID_ROL = t_roles.ID_ROL) WHERE (t_usuarios.USUARIO_USUARIO = '"+usuario.getText().toUpperCase()+"')");
            if(r.next()){
                //if(Encriptar.Desencriptar(r.getString("CONT_USUARIO")).equals(c)){
                if(Encriptar.getMD5(c).equals(r.getString("CONT_USUARIO"))){
                    JOptionPane.showMessageDialog(this,"Bienvenido: "+r.getString("NOMBRE_USUARIO")+" "+r.getString("APELLIDO_USUARIO"),"Confirmación",JOptionPane.INFORMATION_MESSAGE);
                    Main.login.setText(r.getString("USUARIO_USUARIO"));
                    Main.rol.setText(r.getString("NOMBRE_ROL"));
                    Main.id_usuario=r.getString("ID_USUARIO");
                    Main.mainperm=true;
                    con.cerrar();
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null,"Error en la validacion\nPor favor verifique que su contraseña sea correcta", "Error",JOptionPane.ERROR_MESSAGE);
                    usuario.setText("");
                    cont.setText("");
                    conteo++;
                    if(conteo>=3){
                        conteo=0;
                        Main.time_login=false;
                        Hilo_Bloqueo_Inicio hbi=new Hilo_Bloqueo_Inicio(); 
                        hbi.start();
                        dispose();
                    }
                    con.cerrar();
                }
                con.cerrar();
            }else{
                con.cerrar();
                JOptionPane.showMessageDialog(null,"Error en la validacion\nPor favor verifique que su usuario sea correctos", "Error",JOptionPane.ERROR_MESSAGE);
                usuario.setText("");
                cont.setText("");
                conteo++;
                if(conteo>=3){
                    conteo=0;
                    Main.time_login=false;
                    Hilo_Bloqueo_Inicio hbi=new Hilo_Bloqueo_Inicio(); 
                    hbi.start();
                    dispose();
                }
            }
            con.cerrar();
        } catch (SQLException ex ) {
            con.cerrar();
            ex.printStackTrace();
        } catch (Exception ex) {
            con.cerrar();
            ex.printStackTrace();
        }
     


}

void verifica_rol(){
    if(Main.rol.getText().equalsIgnoreCase("ADMINISTRADOR")){
        Main.jm_iniciar_sesion.setEnabled(false);
        Main.jm_cerrar_sesion.setEnabled(true);
        Main.jm_cambio_contraseña.setEnabled(true);
        Main.jm_novedades.setEnabled(true);
        Main.jm_informacion.setEnabled(true);
        Main.jm_reportes.setEnabled(true);
        Main.jm_pqr.setEnabled(true);
        Main.jm_importar.setEnabled(true);
        Main.jm_usuarios.setEnabled(true);
        Main.jm_config.setEnabled(true);
        Main.jm_parametros.setEnabled(true);
    }
    if(Main.rol.getText().equalsIgnoreCase("OPERADOR")){
        Main.jm_iniciar_sesion.setEnabled(false);
        Main.jm_cerrar_sesion.setEnabled(true);
        Main.jm_cambio_contraseña.setEnabled(true);
        Main.jm_novedades.setEnabled(true);
        Main.jm_informacion.setEnabled(true);
        Main.jm_reportes.setEnabled(true);
        Main.jm_pqr.setEnabled(true);
        Main.jm_importar.setEnabled(false);
        Main.jm_usuarios.setEnabled(false);
        Main.jm_config.setEnabled(true);
        Main.jm_parametros.setEnabled(false);
    }
    if(Main.rol.getText().equalsIgnoreCase("MASTER")){
        Main.jm_iniciar_sesion.setEnabled(false);
        Main.jm_cerrar_sesion.setEnabled(true);
        Main.jm_cambio_contraseña.setEnabled(true);
        Main.jm_novedades.setEnabled(true);
        Main.jm_informacion.setEnabled(true);
        Main.jm_reportes.setEnabled(true);
        Main.jm_pqr.setEnabled(true);
        Main.jm_importar.setEnabled(true);
        Main.jm_usuarios.setEnabled(true);
        Main.jm_config.setEnabled(true);
        Main.jm_parametros.setEnabled(true);
    }

}

}
