/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Opciones.java
 *
 * Created on 10-feb-2015, 16:25:57
 */
package com.jm.Edicof.GUI;

import com.jm.Edicof.Clases.Conexion;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author Johnnatan
 */
public class Opciones_Add_conection extends javax.swing.JDialog {
FileWriter fw = null;
PrintWriter pw = null;
static FileReader fr = null;
static BufferedReader br = null;
String aux="";
String aux1="";
String aux2="";
String temp="";
String [] param = {"","","","","","","","","","",""};
String [] host;
int i=0;
//private StringTokenizer st;
//private StringTokenizer st1;
    /** Creates new form Opciones */
    public Opciones_Add_conection(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        this.setLocationRelativeTo(null);
        //check_tipo();
        
}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jPanel1 = new javax.swing.JPanel();
        paneles = new javax.swing.JTabbedPane();
        jp_base_datos = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        host1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        bd = new javax.swing.JTextField();
        host2 = new javax.swing.JTextField();
        host3 = new javax.swing.JTextField();
        host4 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        usu = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cont = new javax.swing.JPasswordField();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Conexion");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ajustes de conexion"));

        jLabel6.setText("IP Host");

        host1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        host1.setText("0");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jp_base_datos, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), host1, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jLabel7.setText("Base de datos");

        bd.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jp_base_datos, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), bd, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        host2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        host2.setText("0");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jp_base_datos, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), host2, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        host3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        host3.setText("0");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jp_base_datos, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), host3, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        host4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        host4.setText("0");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jp_base_datos, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), host4, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jLabel2.setText("Usuario");

        usu.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jp_base_datos, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), usu, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jLabel3.setText("Contraseña");

        cont.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jp_base_datos, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), cont, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/database_connect.png"))); // NOI18N
        jButton4.setText("Test");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_base_datosLayout = new javax.swing.GroupLayout(jp_base_datos);
        jp_base_datos.setLayout(jp_base_datosLayout);
        jp_base_datosLayout.setHorizontalGroup(
            jp_base_datosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_base_datosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jp_base_datosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(38, 38, 38)
                .addGroup(jp_base_datosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addGroup(jp_base_datosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(usu, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bd, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jp_base_datosLayout.createSequentialGroup()
                            .addComponent(host1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(host2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(host3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(host4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(cont, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jp_base_datosLayout.setVerticalGroup(
            jp_base_datosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_base_datosLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jp_base_datosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(host1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(host2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(host3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(host4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jp_base_datosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(bd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jp_base_datosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(usu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jp_base_datosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        paneles.addTab("Base de Datos", jp_base_datos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneles)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneles)
                .addContainerGap())
        );

        jButton1.setBackground(Color.WHITE);
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/accept_1.png"))); // NOI18N
        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(Color.WHITE);
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancel_1.png"))); // NOI18N
        jButton2.setText("Cancelar");
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
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(99, 99, 99))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(!(host1.getText().equals("") || host2.getText().equals("") || host3.getText().equals("") || host4.getText().equals("") || bd.getText().equals("") || usu.getText().equals(""))){
            if(JOptionPane.showConfirmDialog(null,"Esta seguro que desea continuar?","Confirmar",JOptionPane.OK_CANCEL_OPTION)==0){
                //System.out.println(host1.getText()+"."+host2.getText()+"."+host3.getText()+"."+host4.getText());
                param[0]=host1.getText()+"."+host2.getText()+"."+host3.getText()+"."+host4.getText();
                param[1]=bd.getText();
                param[2]= usu.getText();
                param[3]= new String(cont.getPassword());
                dispose();
            }
        }else{
        JOptionPane.showMessageDialog(null,"Por favor complete todos los campos", "Error",JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_jButton1ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        param=null;
        this.dispose();
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Conexion con=new Conexion();
        if(con.test(host1.getText()+"."+host2.getText()+"."+host3.getText()+"."+host4.getText(), bd.getText(), usu.getText(), new String(cont.getPassword()))){
            JOptionPane.showMessageDialog(this,"Test exitoso.", "Información",JOptionPane.INFORMATION_MESSAGE);
            con.cerrar();
        }else{
            JOptionPane.showMessageDialog(this,"Test fallido.", "Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formWindowClosing

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Opciones_Add_conection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Opciones_Add_conection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Opciones_Add_conection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Opciones_Add_conection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                Opciones_Add_conection dialog = new Opciones_Add_conection(new javax.swing.JDialog(), true);
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
    public javax.swing.JTextField bd;
    private javax.swing.JPasswordField cont;
    public javax.swing.JTextField host1;
    public javax.swing.JTextField host2;
    public javax.swing.JTextField host3;
    public javax.swing.JTextField host4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jp_base_datos;
    private javax.swing.JTabbedPane paneles;
    private javax.swing.JTextField usu;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
//public void enable_panel(){
//    if(Main.rol.getText().equalsIgnoreCase("MASTER")){
//        if (tipo_estacion.getSelectedItem().toString().equals("CLIENTE")) {
//            jp_general.setEnabled(false);
//            jp_base_datos.setEnabled(true);
//            jp_backup.setEnabled(false);
//            jp_restore.setEnabled(false);
//            jp_rutas.setEnabled(false);
//            jp_advanced.setEnabled(true);
//        }else{
//            if (tipo_estacion.getSelectedItem().toString().equals("SERVIDOR")) {
//                jp_general.setEnabled(true);
//                jp_base_datos.setEnabled(true);
//                jp_backup.setEnabled(true);
//                jp_restore.setEnabled(true);
//                jp_rutas.setEnabled(true);
//                jp_advanced.setEnabled(true);
//            }
//        }
//    }
//    if(Main.rol.getText().equalsIgnoreCase("ADMINISTRADOR")){
//        if (tipo_estacion.getSelectedItem().toString().equals("CLIENTE")) {
//            jp_general.setEnabled(false);
//            jp_base_datos.setEnabled(true);
//            jp_backup.setEnabled(false);
//            jp_restore.setEnabled(false);
//            jp_rutas.setEnabled(false);
//            jp_advanced.setEnabled(false);
//        }else{
//            if (tipo_estacion.getSelectedItem().toString().equals("SERVIDOR")) {
//                jp_general.setEnabled(true);
//                jp_base_datos.setEnabled(true);
//                jp_backup.setEnabled(true);
//                jp_restore.setEnabled(true);
//                jp_rutas.setEnabled(true);
//                jp_advanced.setEnabled(false);
//            }
//        }
//    }
//    if(Main.rol.getText().equalsIgnoreCase("OPERADOR")){
//        jp_general.setEnabled(false);
//        jp_base_datos.setEnabled(false);
//        jp_backup.setEnabled(false);
//        jp_restore.setEnabled(false);
//        jp_rutas.setEnabled(false);
//        jp_advanced.setEnabled(false);
//    }
//    
//}
public String[] getParam() {
    return param;
}
//public void check_tipo(){
//    if (tipo_estacion.getSelectedItem().equals("CLIENTE")) {
//        jp_backup.setEnabled(false);
//        jp_restore.setEnabled(false);
//        jp_rutas.setEnabled(false);
//        jp_advanced.setEnabled(true);
//        //install_bd.setEnabled(false);
//    }else{
//        jp_backup.setEnabled(true);
//        jp_restore.setEnabled(true);
//        jp_rutas.setEnabled(true);
//        jp_advanced.setEnabled(true);
//        //install_bd.setEnabled(true);
//    }
//
//}

}
