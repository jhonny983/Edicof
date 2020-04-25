/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.GUI;

import com.jm.Edicof.Clases.Conexion;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Johnnatan
 */
public class View_PQR extends javax.swing.JDialog {
    DefaultTableModel modelo;
    DefaultTableModel modelo1;
    Object [] fila = new Object[2];
    
    /**
     * Creates new form Asig_User_PQR
     */
    public View_PQR(java.awt.Frame parent, boolean modal, long r) {
        super(parent, modal);
        initComponents();
        modelo = (DefaultTableModel)evo.getModel();
        modelo1 = (DefaultTableModel)empleados.getModel();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        clear_evo();
        clear_emp();
        load_data(r);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        evo.getColumnModel().getColumn(0).setCellRenderer(tcr);
        evo.getColumnModel().getColumn(1).setCellRenderer(tcr);
        empleados.getColumnModel().getColumn(0).setCellRenderer(tcr);
        empleados.getColumnModel().getColumn(1).setCellRenderer(tcr);
    }
    public View_PQR(javax.swing.JDialog parent, boolean modal, long r) {
        super(parent, modal);
        initComponents();
        modelo = (DefaultTableModel)evo.getModel();
        modelo1 = (DefaultTableModel)empleados.getModel();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        clear_evo();
        clear_emp();
        load_data(r);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        evo.getColumnModel().getColumn(0).setCellRenderer(tcr);
        evo.getColumnModel().getColumn(1).setCellRenderer(tcr);
        empleados.getColumnModel().getColumn(0).setCellRenderer(tcr);
        empleados.getColumnModel().getColumn(1).setCellRenderer(tcr);
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
        t_radicado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        t_tipo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        t_nit = new javax.swing.JTextField();
        t_empresa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        t_usuario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        t_estado = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        usu = new javax.swing.JTextField();
        btn_add_evo = new javax.swing.JButton();
        btn_reasig = new javax.swing.JButton();
        btn_cerrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_desc = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        evo = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        empleados = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Caso PQR");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion del caso"));

        jLabel1.setText("Radicado");

        t_radicado.setEditable(false);
        t_radicado.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setText("Tipo");

        t_tipo.setEditable(false);
        t_tipo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setText("Empresa");

        t_nit.setEditable(false);
        t_nit.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        t_empresa.setEditable(false);
        t_empresa.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setText("Creado por");

        t_usuario.setEditable(false);
        t_usuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setText("Estado");

        t_estado.setEditable(false);
        t_estado.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setText("Usuario");

        usu.setEditable(false);
        usu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuActionPerformed(evt);
            }
        });

        btn_add_evo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add.png"))); // NOI18N
        btn_add_evo.setText("Agregar Evolución");
        btn_add_evo.setToolTipText("Agrega una evolucion al caso");
        btn_add_evo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_evoActionPerformed(evt);
            }
        });

        btn_reasig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_go_1.png"))); // NOI18N
        btn_reasig.setText("Reasignar Usuario");
        btn_reasig.setToolTipText("Reasignar caso a otro usuario");
        btn_reasig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reasigActionPerformed(evt);
            }
        });

        btn_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete.png"))); // NOI18N
        btn_cerrar.setText("Cerrar Caso");
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(30, 30, 30)
                        .addComponent(t_nit)
                        .addGap(18, 18, 18)
                        .addComponent(t_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(t_radicado, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                                    .addComponent(usu)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(29, 29, 29)
                                .addComponent(t_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(t_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(t_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btn_reasig)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_add_evo)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_cerrar)
                .addGap(111, 111, 111))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(t_radicado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(t_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(t_nit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(t_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(t_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(usu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(t_estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_add_evo)
                    .addComponent(btn_reasig))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cerrar)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Descripcion del caso"));

        t_desc.setEditable(false);
        t_desc.setColumns(20);
        t_desc.setLineWrap(true);
        t_desc.setRows(5);
        jScrollPane1.setViewportView(t_desc);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancel_2.png"))); // NOI18N
        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/accept_1.png"))); // NOI18N
        jButton2.setText("Aceptar");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Evolución"));

        evo.setAutoCreateRowSorter(true);
        evo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, ""}
            },
            new String [] {
                "Fecha", "Usuario", "Detalles"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        evo.setToolTipText("Dar doble click para observar el detalle de la evolución");
        evo.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        evo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                evoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(evo);
        if (evo.getColumnModel().getColumnCount() > 0) {
            evo.getColumnModel().getColumn(0).setMinWidth(100);
            evo.getColumnModel().getColumn(0).setPreferredWidth(100);
            evo.getColumnModel().getColumn(0).setMaxWidth(100);
            evo.getColumnModel().getColumn(1).setMinWidth(100);
            evo.getColumnModel().getColumn(1).setPreferredWidth(100);
            evo.getColumnModel().getColumn(1).setMaxWidth(100);
            evo.getColumnModel().getColumn(2).setPreferredWidth(2000);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleados relacionados"));

        empleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cedula", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(empleados);
        if (empleados.getColumnModel().getColumnCount() > 0) {
            empleados.getColumnModel().getColumn(0).setMinWidth(150);
            empleados.getColumnModel().getColumn(0).setPreferredWidth(150);
            empleados.getColumnModel().getColumn(0).setMaxWidth(150);
            empleados.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void evoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_evoMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            if (evo.getSelectedRow()!=-1) {
                load_t_evo();
            }else{
                JOptionPane.showMessageDialog(null,"No se ha seleccionado ningun registro de la tabla.","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_evoMouseClicked

    private void btn_reasigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reasigActionPerformed
        // TODO add your handling code here:
        String r ="";
        if (Main.rol.getText().equals("MASTER") | Main.rol.getText().equals("ADMINISTRADOR") | usu.getText().equals(Main.login.getText())) {
            r = t_radicado.getText();
            Asig_User_PQR asig =new Asig_User_PQR(this,true,new Long(r));
            asig.setLocationRelativeTo(this);
            asig.setVisible(true);
            clear_evo();
            clear_emp();
            load_data(new Long(r));
        }else{
            JOptionPane.showMessageDialog(null,"No tiene permisos para esta opcion.","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_reasigActionPerformed

    private void btn_add_evoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_evoActionPerformed
        // TODO add your handling code here:
        String r ="";
        if (Main.rol.getText().equals("MASTER") | Main.rol.getText().equals("ADMINISTRADOR") | usu.getText().equals(Main.login.getText())) {
            r = t_radicado.getText();
            Add_Evo_PQR add_ev =new Add_Evo_PQR(this,true,r);
            add_ev.setLocationRelativeTo(this);
            add_ev.setVisible(true);
            clear_evo();
            clear_emp();
            load_data(new Long(r));
        }else{
            JOptionPane.showMessageDialog(null,"No tiene permisos para esta opcion.","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_add_evoActionPerformed

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        // TODO add your handling code here:
        String r ="";
        if (Main.rol.getText().equals("MASTER") | Main.rol.getText().equals("ADMINISTRADOR") | usu.getText().equals(Main.login.getText())) {
            r = t_radicado.getText();
            Close_Evo_PQR cl_ev =new Close_Evo_PQR(this,true,r);
            cl_ev.setLocationRelativeTo(this);
            cl_ev.setVisible(true);
            clear_evo();
            clear_emp();
            load_data(new Long(r));
        }else{
            JOptionPane.showMessageDialog(null,"No tiene permisos para esta opcion.","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void usuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuActionPerformed

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
            java.util.logging.Logger.getLogger(View_PQR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View_PQR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View_PQR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View_PQR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                View_PQR dialog = new View_PQR(new javax.swing.JFrame(), true, new  Long(""));
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
    private javax.swing.JButton btn_add_evo;
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_reasig;
    private javax.swing.JTable empleados;
    private javax.swing.JTable evo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea t_desc;
    private javax.swing.JTextField t_empresa;
    private javax.swing.JTextField t_estado;
    private javax.swing.JTextField t_nit;
    private javax.swing.JTextField t_radicado;
    private javax.swing.JTextField t_tipo;
    private javax.swing.JTextField t_usuario;
    private javax.swing.JTextField usu;
    // End of variables declaration//GEN-END:variables
public void load_data(long rd){
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT *\n" +
                        "FROM\n" +
                        "    t_caso_pqr\n" +
                        "    INNER JOIN t_tipo_caso_pqr \n" +
                        "        ON (t_caso_pqr.ID_TIPO_CASO = t_tipo_caso_pqr.ID_TIPO_CASO)\n" +
                        "    INNER JOIN t_empresas \n" +
                        "        ON (t_caso_pqr.ID_EMPRESA = t_empresas.ID_EMPRESA)\n" +
                        "    INNER JOIN t_usuarios AS C1\n" +
                        "        ON (t_caso_pqr.ID_USUARIO = C1.ID_USUARIO)\n" +
                        "    INNER JOIN t_usuarios AS C2\n" +
                        "        ON (t_caso_pqr.ID_USU_ASIG = C2.ID_USUARIO)\n" +
                        "    INNER JOIN t_estado_caso_pqr \n" +
                        "        ON (t_caso_pqr.ID_ESTADO_CASO = t_estado_caso_pqr.ID_ESTADO_CASO)\n" +
                        "    LEFT JOIN t_casos_empleados \n" +
                        "        ON (t_casos_empleados.ID_CASO = t_caso_pqr.ID_CASO) WHERE t_caso_pqr.ID_CASO="+rd);
        if(r.next()){
            t_radicado.setText(r.getString("t_caso_pqr.ID_CASO"));
            t_nit.setText(r.getString("t_empresas.ID_EMPRESA"));
            t_empresa.setText(r.getString("t_empresas.NOMBRE_EMPRESA"));
            t_tipo.setText(r.getString("t_tipo_caso_pqr.NOMBRE_TIPO"));
            t_estado.setText(r.getString("t_estado_caso_pqr.NOMBRE_ESTADO"));
            if (t_estado.getText().equals("CERRADO")) {
                btn_add_evo.setEnabled(false);
                btn_cerrar.setEnabled(false);
                btn_reasig.setEnabled(false);
            }else{
                btn_add_evo.setEnabled(true);
                btn_cerrar.setEnabled(true);
                btn_reasig.setEnabled(true);
            }
            String u = r.getString("C2.USUARIO_USUARIO");
            if(u.equals("TEST")){
                usu.setText("NO ASIGNADO");
            }else{
                usu.setText(u);
            }
            t_usuario.setText(r.getString("C1.USUARIO_USUARIO"));
            t_desc.setText(r.getString("t_caso_pqr.DESCRIPCION_CASO"));
        }
        String str_query = " SELECT *\n" +
                        "    FROM\n" +
                        "    t_evolucion_pqr\n" +
                        "    INNER JOIN t_caso_pqr \n" +
                        "        ON (t_caso_pqr.ID_CASO = t_evolucion_pqr.ID_CASO)\n" +
                        "    INNER JOIN t_usuarios\n" +
                        "        ON (t_evolucion_pqr.ID_USUARIO = t_usuarios.ID_USUARIO) WHERE t_evolucion_pqr.ID_CASO="+t_radicado.getText()+" ORDER BY FECHA_EVO DESC";

        r = con.s.executeQuery (str_query);
        while(r.next()){
            modelo.addRow(fila);
            modelo.setValueAt(r.getString("t_evolucion_pqr.FECHA_EVO"),evo.getRowCount()-1,0);
            modelo.setValueAt(r.getString("t_usuarios.USUARIO_USUARIO"),evo.getRowCount()-1,1);
            modelo.setValueAt(r.getString("t_evolucion_pqr.TRATAMIENTO"),evo.getRowCount()-1,2);
        }
        str_query = " SELECT *\n" +
                        "    FROM\n" +
                        "    t_casos_empleados\n" +
                        "    INNER JOIN t_empleados \n" +
                        "        ON (t_casos_empleados.ID_EMPLEADO = t_empleados.ID_EMP) WHERE t_casos_empleados.ID_CASO="+t_radicado.getText()+" ORDER BY t_casos_empleados.ID_EMPLEADO ASC";

        r = con.s.executeQuery (str_query);
        while(r.next()){
            modelo1.addRow(fila);
            modelo1.setValueAt(r.getString("t_casos_empleados.ID_EMPLEADO"),empleados.getRowCount()-1,0);
            modelo1.setValueAt(r.getString("APELLIDO_1_EMP")+" "+r.getString("APELLIDO_2_EMP")+" "+r.getString("NOMBRE_1_EMP")+" "+r.getString("NOMBRE_2_EMP"),empleados.getRowCount()-1,1);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }

}
public void clear_evo(){
    while(evo.getRowCount()!=0){
        modelo.removeRow(evo.getRowCount()-1);
    }
}
public void clear_emp(){
    while(empleados.getRowCount()!=0){
        modelo1.removeRow(empleados.getRowCount()-1);
    }
}
public void load_t_evo(){
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        String str_query = " SELECT *\n" +
                        "    FROM\n" +
                        "    t_evolucion_pqr\n" +
                        "    INNER JOIN t_caso_pqr \n" +
                        "        ON (t_caso_pqr.ID_CASO = t_evolucion_pqr.ID_CASO)\n" +
                        "    INNER JOIN t_usuarios\n" +
                        "        ON (t_evolucion_pqr.ID_USUARIO = t_usuarios.ID_USUARIO) WHERE t_evolucion_pqr.ID_CASO="+t_radicado.getText()+" AND FECHA_EVO='"+evo.getValueAt(evo.getSelectedRow(), 0)+"' AND t_usuarios.USUARIO_USUARIO='"+evo.getValueAt(evo.getSelectedRow(), 1)+"'";

        r = con.s.executeQuery (str_query);
        if(r.next()){
            View_Evolucion v =new View_Evolucion(this,true,r.getString("FECHA_EVO"),r.getString("USUARIO_USUARIO"),r.getString("TRATAMIENTO"));
            v.setLocationRelativeTo(this);
            v.setVisible(true);
            //JOptionPane.showMessageDialog(null,r.getString("TRATAMIENTO"),"Evolucion",JOptionPane.PLAIN_MESSAGE);
            //t_evo.setText(r.getString("TRATAMIENTO"));
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }

}

}