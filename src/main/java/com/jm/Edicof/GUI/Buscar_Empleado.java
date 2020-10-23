/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.GUI;

import com.jm.Edicof.Clases.AutoCompletion;
import com.jm.Edicof.Clases.Conexion;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class Buscar_Empleado extends javax.swing.JDialog {
static DefaultTableModel modelo = new DefaultTableModel();
Object [] fila = new Object[5];
    /**
     * Creates new form Busc_Empleado
     */
    public Buscar_Empleado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        //setUndecorated(true); 
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        ac_all();
        modelo = (DefaultTableModel)empleados.getModel(); 
        while(empleados.getRowCount()!=0){
            modelo.removeRow(empleados.getRowCount()-1);
        }
        empleados.setModel(modelo);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        empleados.getColumnModel().getColumn(0).setCellRenderer(tcr);
        empleados.getColumnModel().getColumn(1).setCellRenderer(tcr);
        empleados.getColumnModel().getColumn(2).setCellRenderer(tcr);
        empleados.getColumnModel().getColumn(3).setCellRenderer(tcr);
        empleados.getColumnModel().getColumn(4).setCellRenderer(tcr);
        empleados.getColumnModel().getColumn(5).setCellRenderer(tcr);
        empleados.getColumnModel().getColumn(6).setCellRenderer(tcr);
        empleados.getColumnModel().getColumn(7).setCellRenderer(tcr);
        empleados.getColumnModel().getColumn(8).setCellRenderer(tcr);
    }
    public Buscar_Empleado(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        //setUndecorated(true); 
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        ac_all();
        modelo = (DefaultTableModel)empleados.getModel(); 
        while(empleados.getRowCount()!=0){
            modelo.removeRow(empleados.getRowCount()-1);
        }
        empleados.setModel(modelo);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cb_cedula = new javax.swing.JComboBox<>();
        cb_nombre1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cb_apellido1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cb_apellido2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cb_nombre2 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        empleados = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/information.png"))); // NOI18N
        jMenu2.setText("Información Personal");
        jMenu2.setToolTipText("");

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_edit.png"))); // NOI18N
        jMenuItem3.setText("Editar Empleado");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete.png"))); // NOI18N
        jMenuItem1.setText("Eliminar Empleado");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jPopupMenu1.add(jMenu2);
        jPopupMenu1.add(jSeparator4);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map.png"))); // NOI18N
        jMenu1.setText("Info. Sociodemográfica");

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eye.png"))); // NOI18N
        jMenuItem7.setText("Visualizar");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add.png"))); // NOI18N
        jMenuItem4.setText("Agregar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pencil.png"))); // NOI18N
        jMenuItem5.setText("Editar");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jPopupMenu1.add(jMenu1);
        jPopupMenu1.add(jSeparator3);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/book_add.png"))); // NOI18N
        jMenuItem6.setText("Administrar Certificados y Cursos");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem6);
        jPopupMenu1.add(jSeparator2);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/accept_1.png"))); // NOI18N
        jMenuItem2.setText("Eliminar de lista Vetados");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);
        jPopupMenu1.add(jSeparator1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar Empleado");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Criterio de busqueda"));

        jLabel1.setText("Cedula");

        AutoCompletion.enable(cb_cedula);
        cb_cedula.setEditable(true);
        cb_cedula.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_cedulaItemStateChanged(evt);
            }
        });

        AutoCompletion.enable(cb_nombre1);
        cb_nombre1.setEditable(true);

        jLabel2.setText("Primer nombre");

        AutoCompletion.enable(cb_apellido1);
        cb_apellido1.setEditable(true);

        jLabel3.setText("Primer apellido");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_tab_search.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Segundo apellido");

        AutoCompletion.enable(cb_apellido2);
        cb_apellido2.setEditable(true);

        jLabel5.setText("Segundo nombre");

        AutoCompletion.enable(cb_nombre2);
        cb_nombre2.setEditable(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(173, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_nombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)))
                    .addComponent(cb_nombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(cb_apellido1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_apellido2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(174, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cb_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_apellido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_nombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cb_apellido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cb_nombre2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultados de la busqueda"));

        empleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cedula", "Nombre 1", "Nombre 2", "Apellido 1", "Apellido 2", "Info. Socio-demografica", "Fecha Curso - Alturas", "Fecha Curso - Protocolo", "Fecha Encuesta - Riesgo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        empleados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        empleados.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(empleados);
        if (empleados.getColumnModel().getColumnCount() > 0) {
            empleados.getColumnModel().getColumn(0).setPreferredWidth(120);
            empleados.getColumnModel().getColumn(1).setPreferredWidth(150);
            empleados.getColumnModel().getColumn(2).setPreferredWidth(150);
            empleados.getColumnModel().getColumn(3).setPreferredWidth(150);
            empleados.getColumnModel().getColumn(4).setPreferredWidth(150);
            empleados.getColumnModel().getColumn(5).setPreferredWidth(150);
            empleados.getColumnModel().getColumn(6).setPreferredWidth(150);
            empleados.getColumnModel().getColumn(7).setPreferredWidth(150);
            empleados.getColumnModel().getColumn(8).setPreferredWidth(150);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancel_1.png"))); // NOI18N
        jButton4.setText("Cerrar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        search();
    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        //evt.get
        if (Main.rol.getText().equals("ADMINISTRADOR") | Main.rol.getText().equals("MASTER")) {
            Conexion con = new Conexion();
            con.conexion();
            ResultSet r;
            try {
                int conf = JOptionPane.showConfirmDialog(this,"Esta seguro que desea eliminar al empleado?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (conf == JOptionPane.YES_OPTION) {
                    modelo = (DefaultTableModel)empleados.getModel(); 
                    r = con.s.executeQuery ("SELECT COUNT(ID_EMPLEADO) FROM t_novedades WHERE ID_EMPLEADO ="+modelo.getValueAt(empleados.getSelectedRow(),0));
                    if (r.next()) {
                        if (r.getInt("COUNT(ID_EMPLEADO)")>0) {
                            JOptionPane.showMessageDialog(this,"El empleado no puede ser eliminado porque ya tiene novedades registradas","Error",JOptionPane.ERROR_MESSAGE);
                        }else{
                            con.s.executeUpdate("DELETE FROM T_EMPLEADOS WHERE ID_EMP="+modelo.getValueAt(empleados.getSelectedRow(), 0));
                            JOptionPane.showMessageDialog(this,"El empleado fue eliminado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
                            con.cerrar();
                            this.dispose();
                        }
                    }
                    
                }
            } catch (HeadlessException | SQLException e) {
                con.cerrar();
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null,"No tiene permisos para esta opcion.","Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
        try {
            int conf = JOptionPane.showConfirmDialog(this,"Esta seguro que desea eliminar al empleado de la lista de Vetados?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (conf == JOptionPane.YES_OPTION) {
                modelo = (DefaultTableModel)empleados.getModel(); 
                r = con.s.executeQuery ("SELECT * FROM t_vetados WHERE ID_EMPLEADO = "+modelo.getValueAt(empleados.getSelectedRow(),0));
                if (r.next()) {
                    con.s.executeUpdate("DELETE FROM t_vetados WHERE ID_EMPLEADO = "+modelo.getValueAt(empleados.getSelectedRow(),0));
                    JOptionPane.showMessageDialog(this,"El empleado fue eliminado de la lista correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
                    con.cerrar();
                    this.dispose();
                }else{
                    JOptionPane.showMessageDialog(this,"El empleado no puede ser eliminado porque no existe en la lista de vetados.","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (HeadlessException | SQLException e) {
            con.cerrar();
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        modelo = (DefaultTableModel)empleados.getModel(); 
        if (!search_info(modelo.getValueAt(empleados.getSelectedRow(),0).toString())) {
            Add_Info_Socio_Demografico add_ent = new Add_Info_Socio_Demografico(this, false, modelo.getValueAt(empleados.getSelectedRow(),0).toString());
            add_ent.setLocationRelativeTo(this);
            add_ent.setVisible(true);
            search();
        }else{
            JOptionPane.showMessageDialog(this,"El empleado seleccionado ya tiene asociada informacion sociodemografica, si desea consultarla o editarla por favor acceda al menu correspondiente.","Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        if (search_info(modelo.getValueAt(empleados.getSelectedRow(),0).toString())) {
            Edd_Info_Socio_Demografico edd_ent = new Edd_Info_Socio_Demografico(this, false, modelo.getValueAt(empleados.getSelectedRow(),0).toString());
            edd_ent.setLocationRelativeTo(this);
            edd_ent.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this,"El empleado seleccionado no tiene asociada informacion sociodemografica.","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        if (empleados.getSelectedRow()!=-1) {
            Edd_Empleado edd_empleado = new Edd_Empleado(this, false,modelo.getValueAt(empleados.getSelectedRow(),0).toString());
            edd_empleado.setLocationRelativeTo(this);
            edd_empleado.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this,"Debe seleccionar al menos un registro para editarlo.","Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void cb_cedulaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_cedulaItemStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cb_cedulaItemStateChanged

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        if (empleados.getSelectedRow()!=-1) {
            Admin_Certificados ad_cert = new Admin_Certificados(this, true, modelo.getValueAt(empleados.getSelectedRow(),0).toString());
            ad_cert.setLocationRelativeTo(this);
            ad_cert.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this,"Debe seleccionar al menos un registro para administrar los certificados.","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        if (empleados.getSelectedRow()!=-1) {
            if (search_info(modelo.getValueAt(empleados.getSelectedRow(),0).toString())) {
                View_Info_Socio_Demografico v_info = new View_Info_Socio_Demografico(this, true, modelo.getValueAt(empleados.getSelectedRow(),0).toString());
                v_info.setLocationRelativeTo(this);
                v_info.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(this,"El empleado seleccionado no tiene asociada informacion sociodemografica.","Error",JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,"Debe seleccionar al menos un registro para visualizar la información.","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

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
            java.util.logging.Logger.getLogger(Buscar_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Buscar_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Buscar_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Buscar_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Buscar_Empleado dialog = new Buscar_Empleado(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cb_apellido1;
    private javax.swing.JComboBox<String> cb_apellido2;
    private javax.swing.JComboBox<String> cb_cedula;
    private javax.swing.JComboBox<String> cb_nombre1;
    private javax.swing.JComboBox<String> cb_nombre2;
    private javax.swing.JTable empleados;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    // End of variables declaration//GEN-END:variables
public void ac_all(){
    cb_cedula.removeAllItems();
    cb_cedula.addItem("Seleccione..");
    cb_nombre1.removeAllItems();
    cb_nombre1.addItem("Seleccione..");
    cb_nombre2.removeAllItems();
    cb_nombre2.addItem("Seleccione..");
    cb_apellido1.removeAllItems();
    cb_apellido1.addItem("Seleccione..");
    cb_apellido2.removeAllItems();
    cb_apellido2.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_EMPLEADOS ORDER BY ID_EMP ASC;");
        while(r.next()){
            String str1=r.getString("ID_EMP");
            cb_cedula.addItem(str1);
        }
        r = con.s.executeQuery ("SELECT * FROM T_EMPLEADOS GROUP BY NOMBRE_1_EMP ORDER BY NOMBRE_1_EMP ASC;");
        while(r.next()){
            String str2=r.getString("NOMBRE_1_EMP");
            if (!str2.equals("")) {
                cb_nombre1.addItem(str2);
            }
        }
        r = con.s.executeQuery ("SELECT * FROM T_EMPLEADOS GROUP BY NOMBRE_2_EMP ORDER BY NOMBRE_2_EMP ASC;");
        while(r.next()){
            String str3=r.getString("NOMBRE_2_EMP");
            if (!str3.equals("")) {
                cb_nombre2.addItem(str3);
            }
        }
        r = con.s.executeQuery ("SELECT * FROM T_EMPLEADOS GROUP BY APELLIDO_1_EMP ORDER BY APELLIDO_1_EMP ASC;");
        while(r.next()){
            String str4=r.getString("APELLIDO_1_EMP");
            if (!str4.equals("")) {
                cb_apellido1.addItem(str4);
            }
        }
        r = con.s.executeQuery ("SELECT * FROM T_EMPLEADOS GROUP BY APELLIDO_2_EMP ORDER BY APELLIDO_2_EMP ASC;");
        while(r.next()){
            String str5=r.getString("APELLIDO_2_EMP");
            if (!str5.equals("")) {
                cb_apellido2.addItem(str5);
            }
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public void clear_empleados(){
    modelo = (DefaultTableModel)empleados.getModel(); 
    while(empleados.getRowCount()!=0){
        modelo.removeRow(empleados.getRowCount()-1);
    }
    empleados.setModel(modelo);
}
public boolean search_info(String ced){
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    boolean ret=false;
    try{    r = con.s.executeQuery ("SELECT * FROM t_info_sociodemografica WHERE ID_EMP = "+ced);
            ret = r.next();
            con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
    return ret;
}
public final void search(){
    String strsql="", ced="";
        clear_empleados();
        if (1==1) {
            Conexion con = new Conexion();
            Conexion con1 = new Conexion();
            con.conexion();
            con1.conexion();
            ResultSet r,r1;
            try {
                //r = con.s.executeQuery(strsql);  
                r = con.s.executeQuery(query_filter(cb_cedula, cb_nombre1, cb_nombre2, cb_apellido1, cb_apellido2));  
                while (r.next()) {
                    ced = r.getString("ID_EMP");
                    modelo.addRow(fila);
                    empleados.setModel(modelo);
                    modelo.setValueAt(ced,empleados.getRowCount()-1,0);
                    modelo.setValueAt(r.getString("NOMBRE_1_EMP"),empleados.getRowCount()-1,1);
                    modelo.setValueAt(r.getString("NOMBRE_2_EMP"),empleados.getRowCount()-1,2);
                    modelo.setValueAt(r.getString("APELLIDO_1_EMP"),empleados.getRowCount()-1,3);
                    modelo.setValueAt(r.getString("APELLIDO_2_EMP"),empleados.getRowCount()-1,4);
                    //////////////////***************************************///////////////////////////
                    r1=con1.s.executeQuery("SELECT * FROM t_info_sociodemografica WHERE ID_EMP = "+ ced);
                    if (r1.next()) {
                        modelo.setValueAt("SI",empleados.getRowCount()-1,5);
                    } else {
                        modelo.setValueAt("NO",empleados.getRowCount()-1,5);
                    }
                    ///////////////////////////*****************************************
                    r1=con1.s.executeQuery("SELECT * FROM t_cert_alturas WHERE ID_EMP = "+ ced +" ORDER BY FECHA_CERT DESC");
                    if (r1.next()) {
                        String fecha_curso = new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(r1.getString("FECHA_CERT")));
                        modelo.setValueAt(fecha_curso,empleados.getRowCount()-1,6);
                    }
                    ///////////////////////*********************************************
                    r1=con1.s.executeQuery("SELECT * FROM t_cap_prot WHERE ID_EMP = "+ ced +" ORDER BY FECHA_CAP_PROT DESC");
                    if (r1.next()) {
                        String fecha_protocolo = new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(r1.getString("FECHA_CAP_PROT")));
                        modelo.setValueAt(fecha_protocolo,empleados.getRowCount()-1,7);
                    }
                    ///////////////////////***********************************************
                    r1=con1.s.executeQuery("SELECT * FROM t_riesgo WHERE ID_EMP = "+ ced +" ORDER BY FECHA_ENC DESC");
                    if (r1.next()) {
                        String fecha_riesgo = new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(r1.getString("FECHA_ENC")));
                        modelo.setValueAt(fecha_riesgo,empleados.getRowCount()-1,8);
                    }
                }
                con.cerrar();
                con1.cerrar();
            } catch (ParseException | SQLException e) {
                e.printStackTrace();
                con.cerrar();
                con1.cerrar();
                JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,"Seleccione al menos un criterio de busqueda","Error",JOptionPane.ERROR_MESSAGE);
        }
}
public String query_filter (javax.swing.JComboBox ced, javax.swing.JComboBox n1,javax.swing.JComboBox n2,javax.swing.JComboBox a1,javax.swing.JComboBox a2){
    String str_ced = "";
    String str_n1 = "";
    String str_n2 = "";
    String str_a1 = "";
    String str_a2 = "";
    String str_query = "SELECT\n" +
                        "  *\n" +
                        "  FROM t_empleados";
    if (!ced.getSelectedItem().equals("Seleccione..") ) {
        str_ced = "ID_EMP ="+ced.getSelectedItem();
    }else{
        str_ced = " ";
    }
    if (!n1.getSelectedItem().equals("Seleccione..")) {
        str_n1 = "NOMBRE_1_EMP='"+n1.getSelectedItem()+"'";
    }else{
        str_n1 =" ";
    }
    if (!n2.getSelectedItem().equals("Seleccione..")) {
        str_n2 = "NOMBRE_2_EMP='"+n2.getSelectedItem()+"'";
    }else{
        str_n2 =" ";
    }
    if (!a1.getSelectedItem().equals("Seleccione..")) {
        str_a1 = "APELLIDO_1_EMP='"+a1.getSelectedItem()+"'";
    }else{
        str_a1 =" ";
    }
    if (!a2.getSelectedItem().equals("Seleccione..")) {
        str_a2 = "APELLIDO_2_EMP='"+a2.getSelectedItem()+"'";
    }else{
        str_a2 =" ";
    }
    
//    if (!str_cedula.equals(" ") | !str_empleador.equals(" ") | !str_municipio.equals(" ") | !str_obra.equals(" ") | !str_eps.equals(" ") | !str_afp.equals(" ") | !str_arl.equals(" ") | !str_ccf.equals(" ") | !str_tipo.equals(" ") | !str_date.equals(" ")) {
    if(!str_ced.equals(" ") & !str_n1.equals(" ")){
        str_n1 = " AND "+str_n1;
    }
    if ((!str_ced.equals(" ") & !str_n1.equals(" "))& !str_n2.equals(" ")) {
        str_n2 = " AND "+str_n2;
    }
    if ((!str_ced.equals(" ") | !str_n1.equals(" ") | !str_n2.equals(" "))& !str_a1.equals(" ")) {
        str_a1 = " AND "+str_a1;
    }
    if ((!str_ced.equals(" ") | !str_n1.equals(" ") | !str_n2.equals(" ") | !str_a1.equals(" "))& !str_a2.equals(" ")) {
        str_a2 = " AND "+str_a2;
    }
    if (!str_ced.equals(" ") | !str_n1.equals(" ") | !str_n2.equals(" ") | !str_a1.equals(" ") | !str_a2.equals(" ")) {
        str_query = str_query + " WHERE " + str_ced + str_n1 + str_n2 + str_a1 + str_a2;
    }else{
        str_query = str_query + " ORDER BY ID_EMP ASC";
    }
    return str_query;
}
}
