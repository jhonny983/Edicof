/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.GUI;

import com.jm.Edicof.Clases.AutoCompletion;
import com.jm.Edicof.Clases.Conexion;
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
public class Add_Barrio extends javax.swing.JDialog {
    TextAutoCompleter tac_barrio = null;
    boolean perm=false;
    /**
     * Creates new form Add_Empleado
     */
    public Add_Barrio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        init();
        ac_barrio();
        ac_dep();
        tac_barrio.setMode(0);
    }
    public Add_Barrio(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        init();
        ac_barrio();
        ac_dep();
        tac_barrio.setMode(0);
    }
    public void init(){
        tac_barrio = new TextAutoCompleter(nombre_barrio, new AutoCompleterCallback() {
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
        nombre_barrio = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cb_dep = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cb_mun = new javax.swing.JComboBox<>();
        comuna = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Barrio");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion del Barrio"));

        jLabel1.setText("Nombre");

        nombre_barrio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombre_barrio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombre_barrioKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(nombre_barrio, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombre_barrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Municipio Barrio"));

        jLabel2.setText("Departamento");

        AutoCompletion.enable(cb_dep);
        cb_dep.setEditable(true);
        cb_dep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cb_dep.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_depItemStateChanged(evt);
            }
        });

        jLabel3.setText("Municipio");

        AutoCompletion.enable(cb_mun);
        cb_mun.setEditable(true);
        cb_mun.setEnabled(false);

        comuna.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        comuna.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                comunaKeyTyped(evt);
            }
        });

        jLabel4.setText("Comuna");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comuna, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cb_dep, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb_mun, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comuna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cb_dep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cb_mun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jButton1)
                .addGap(38, 38, 38)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nombre_barrioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombre_barrioKeyTyped
        // TODO add your handling code here:
//        char a=evt.getKeyChar();
//        a = Character.toUpperCase(a);
//        if (a!=KeyEvent.VK_PERIOD & a!=KeyEvent.VK_MINUS & a!=KeyEvent.VK_SPACE) {
//            if(!(a>=KeyEvent.VK_A && a<=KeyEvent.VK_Z)) {
//                evt.consume();
//            }
//        }
//        if(nombre_barrio.getText().length()>50){
//            evt.consume();
//        }
        
    }//GEN-LAST:event_nombre_barrioKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (!nombre_barrio.getText().equals("")) {
            if (cb_mun.getItemCount()!=0) {
                if (!cb_mun.getSelectedItem().equals("")) {
                    
                } else {
                }
                
            } else {
            }
            
            
            
            
            
            Conexion con = new Conexion();
            con.conexion();
            ResultSet r;
            try {
                r = con.s.executeQuery ("SELECT *\n" +
                                        "FROM\n" +
                                        "    `t_barrio`\n" +
                                        "    INNER JOIN `t_municipios` \n" +
                                        "        ON (`t_barrio`.`ID_MUN_BARRIO` = `t_municipios`.`ID_MUN`) WHERE NOMBRE_BARRIO = '"+nombre_barrio.getText().toUpperCase()+"' AND NOMBRE_MUN = '"+cb_mun.getSelectedItem()+"'");
                if(r.next()){
                    JOptionPane.showMessageDialog(this,"El BARRIO que intenta ingresar ya existe","Error",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    int conf = JOptionPane.showConfirmDialog(this,"Esta seguro que desea continuar?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                    if (conf == JOptionPane.YES_OPTION) {
                        String id_dep="";
                        String id_mun="";
                        r = con.s.executeQuery ("SELECT * FROM T_DEPARTAMENTOS WHERE NOMBRE_DEP='"+cb_dep.getSelectedItem().toString().toUpperCase()+"';");
                        if (r.next()) {
                            id_dep = r.getString("ID_DEP");
                        }
                        r = con.s.executeQuery ("SELECT * FROM T_MUNICIPIOS WHERE NOMBRE_MUN='"+cb_mun.getSelectedItem().toString().toUpperCase()+"' AND ID_DEP ="+id_dep);
                        if (r.next()) {
                            id_mun = r.getString("ID_MUN");
                        }
                        con.s.executeUpdate("INSERT INTO `t_barrio`\n" +
                                            "            (`NOMBRE_BARRIO`,\n" +
                                            "             `COMUNA_BARRIO`,\n" +
                                            "             `ID_MUN_BARRIO`)\n" +
                                            "VALUES ('"+nombre_barrio.getText().toUpperCase().trim()+"',\n" +
                                            "        "+comuna.getText()+",\n" +
                                            "        "+id_mun+");");
                        JOptionPane.showMessageDialog(this,"El BARRIO fue ingresado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
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
            JOptionPane.showMessageDialog(this,"Digite el nombre de la BARRIO","Error",JOptionPane.ERROR_MESSAGE);
        }
           
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cb_depItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_depItemStateChanged
        // TODO add your handling code here:
        if (perm) {
            if (!cb_dep.getSelectedItem().toString().equals("Seleccione..")) {
                ac_mun(cb_dep.getSelectedItem().toString());
            }

        }

    }//GEN-LAST:event_cb_depItemStateChanged

    private void comunaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comunaKeyTyped
        // TODO add your handling code here:
        char a=evt.getKeyChar();
        a = Character.toUpperCase(a);
        if(!(a>=KeyEvent.VK_0 && a<=KeyEvent.VK_9)) {
                evt.consume();
        }
        
    }//GEN-LAST:event_comunaKeyTyped

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
            java.util.logging.Logger.getLogger(Add_Barrio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add_Barrio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add_Barrio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add_Barrio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                Add_Barrio dialog = new Add_Barrio(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cb_dep;
    private javax.swing.JComboBox<String> cb_mun;
    private javax.swing.JTextField comuna;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private static javax.swing.JTextField nombre_barrio;
    // End of variables declaration//GEN-END:variables

public void ac_barrio(){
    tac_barrio.removeAllItems();
    tac_barrio.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_BARRIO ORDER BY NOMBRE_BARRIO ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_BARRIO");
            tac_barrio.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}    
public void load_data(String barrio){
    if (!barrio.equals("Seleccione..")) {
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
        try {
            r = con.s.executeQuery ("SELECT * FROM `t_barrio` WHERE NOMBRE_BARRIO = '"+barrio+"'");
            if (r.next()) {
                nombre_barrio.setText(r.getString("NOMBRE_BARRIO"));
            }
            con.cerrar();
        }catch(SQLException j){
            con.cerrar();
            j.printStackTrace();
            JOptionPane.showMessageDialog(this,j,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    else{
        nombre_barrio.setText("");
        
    }
}
public void ac_dep(){
    cb_dep.removeAllItems();
    cb_dep.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_DEPARTAMENTOS ORDER BY NOMBRE_DEP ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_DEP");
            cb_dep.addItem(str);
        }
        perm=true;
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public void ac_mun(String dep){
    int id_dep=0;
    cb_mun.setEnabled(false);
    cb_mun.removeAllItems();
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_DEPARTAMENTOS WHERE NOMBRE_DEP='"+dep+"' ORDER BY NOMBRE_DEP ASC;");
        if (r.next()) {
            id_dep=r.getInt("ID_DEP");
        }
        r = con.s.executeQuery ("SELECT * FROM T_MUNICIPIOS WHERE ID_DEP="+id_dep+" ORDER BY NOMBRE_MUN ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_MUN");
            cb_mun.addItem(str);
        }
        cb_mun.setEnabled(true);
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
}
