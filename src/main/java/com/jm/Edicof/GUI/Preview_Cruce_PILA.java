/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.GUI;

import com.jm.Edicof.Clases.Conexion;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author Johnnatan
 */
public final class Preview_Cruce_PILA extends javax.swing.JDialog {
static Dimension screenSize = null;
int mes,año=0;
    /**
     * Creates new form Preview_Cruce
     */
    public Preview_Cruce_PILA(java.awt.Frame parent, boolean modal, JTable t_pila, int mes, int año) {
        super(parent, modal);
        initComponents(); 
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        //////////////////----------------------------------------
        screenSize = Main.getsize();
        this.setSize(screenSize.width-20, screenSize.height-10);
        this.setLocationRelativeTo(null);
        this.mes=mes;
        this.año=año;
        //***********************************************
        emp_eps_diff.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                n_eps_dif.setText(String.valueOf(emp_eps_diff.getRowCount()));
            }
        });
        //***********************************************
        emp_arl_diff.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                n_arl_diff.setText(String.valueOf(emp_arl_diff.getRowCount()));
            }
        });
        //***********************************************
        emp_afp_diff.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                n_afp_diff.setText(String.valueOf(emp_afp_diff.getRowCount()));
            }
        });
        //***********************************************
        emp_ccf_diff.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                n_ccf_diff.setText(String.valueOf(emp_ccf_diff.getRowCount()));
            }
        });
        sys_no(t_pila,mes,año);

    }
    public Preview_Cruce_PILA(javax.swing.JDialog parent, boolean modal, JTable t_pila, int m, int y) {
        super(parent, modal);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        //////////////////----------------------------------------
        screenSize = Main.getsize();
        this.setSize(screenSize.width-20, screenSize.height-10);
        this.setLocationRelativeTo(null);
        this.mes=m;
        this.año=y;
        //***********************************************
        emp_eps_diff.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                n_eps_dif.setText(String.valueOf(emp_eps_diff.getRowCount()));
            }
        });
        //***********************************************
        emp_arl_diff.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                n_arl_diff.setText(String.valueOf(emp_arl_diff.getRowCount()));
            }
        });
        //***********************************************
        emp_afp_diff.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                n_afp_diff.setText(String.valueOf(emp_afp_diff.getRowCount()));
            }
        });
        //***********************************************
        emp_ccf_diff.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                n_ccf_diff.setText(String.valueOf(emp_ccf_diff.getRowCount()));
            }
        });
        //***********************************************
        sys_no(t_pila,m,y);

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
        jScrollPane1 = new javax.swing.JScrollPane();
        emp_eps_diff = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        emp_arl_diff = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        emp_afp_diff = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        n_eps_dif = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        n_arl_diff = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        n_afp_diff = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        emp_ccf_diff = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        n_ccf_diff = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vista preliminar cruce PILA");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleados del sistema con EPS diferente"));

        emp_eps_diff.setAutoCreateRowSorter(true);
        emp_eps_diff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cedula", "Nombre", "EPS Pila", "EPS sistema"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        emp_eps_diff.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(emp_eps_diff);
        if (emp_eps_diff.getColumnModel().getColumnCount() > 0) {
            emp_eps_diff.getColumnModel().getColumn(0).setMinWidth(150);
            emp_eps_diff.getColumnModel().getColumn(0).setPreferredWidth(150);
            emp_eps_diff.getColumnModel().getColumn(0).setMaxWidth(150);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleados del sistema con ARL diferente"));

        emp_arl_diff.setAutoCreateRowSorter(true);
        emp_arl_diff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cedula", "Nombre", "ARL Pila", "ARL sistema"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        emp_arl_diff.setCellSelectionEnabled(true);
        jScrollPane2.setViewportView(emp_arl_diff);
        if (emp_arl_diff.getColumnModel().getColumnCount() > 0) {
            emp_arl_diff.getColumnModel().getColumn(0).setMinWidth(150);
            emp_arl_diff.getColumnModel().getColumn(0).setPreferredWidth(150);
            emp_arl_diff.getColumnModel().getColumn(0).setMaxWidth(150);
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleados del sistema con AFP diferente"));
        jPanel4.setPreferredSize(new java.awt.Dimension(484, 218));

        emp_afp_diff.setAutoCreateRowSorter(true);
        emp_afp_diff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cedula", "Nombre", "AFP Pila", "AFP sistema"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        emp_afp_diff.setCellSelectionEnabled(true);
        jScrollPane3.setViewportView(emp_afp_diff);
        if (emp_afp_diff.getColumnModel().getColumnCount() > 0) {
            emp_afp_diff.getColumnModel().getColumn(0).setMinWidth(150);
            emp_afp_diff.getColumnModel().getColumn(0).setPreferredWidth(150);
            emp_afp_diff.getColumnModel().getColumn(0).setMaxWidth(150);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen"));

        jLabel6.setText("Numero de Novedades");

        n_eps_dif.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        n_eps_dif.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n_eps_dif.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(n_eps_dif, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(n_eps_dif)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen"));

        jLabel7.setText("Numero de Novedades");

        n_arl_diff.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        n_arl_diff.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n_arl_diff.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(n_arl_diff, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(n_arl_diff)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen"));

        jLabel8.setText("Numero de Novedades");

        n_afp_diff.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        n_afp_diff.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n_afp_diff.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(n_afp_diff, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(n_afp_diff)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/accept_1.png"))); // NOI18N
        jButton1.setText("Almacenar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancel_2.png"))); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleados del sistema con CCF diferente"));
        jPanel7.setPreferredSize(new java.awt.Dimension(484, 218));

        emp_ccf_diff.setAutoCreateRowSorter(true);
        emp_ccf_diff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cedula", "Nombre", "CCF Pila", "CCF sistema"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        emp_ccf_diff.setCellSelectionEnabled(true);
        jScrollPane4.setViewportView(emp_ccf_diff);
        if (emp_ccf_diff.getColumnModel().getColumnCount() > 0) {
            emp_ccf_diff.getColumnModel().getColumn(0).setMinWidth(150);
            emp_ccf_diff.getColumnModel().getColumn(0).setPreferredWidth(150);
            emp_ccf_diff.getColumnModel().getColumn(0).setMaxWidth(150);
            emp_ccf_diff.getColumnModel().getColumn(2).setHeaderValue("CCF Pila");
            emp_ccf_diff.getColumnModel().getColumn(3).setHeaderValue("CCF sistema");
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen"));

        jLabel9.setText("Numero de Novedades");

        n_ccf_diff.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        n_ccf_diff.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n_ccf_diff.setText("0");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(n_ccf_diff, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(n_ccf_diff)
                .addContainerGap(34, Short.MAX_VALUE))
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
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        boolean term =false;
        int conf = JOptionPane.showConfirmDialog(this,"Esta seguro que desea almacenar el CRUCE en la base de datos?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (conf == JOptionPane.YES_OPTION) {
            Conexion con = new Conexion();
            con.conexion();
            PreparedStatement ps = null;
            try {
//                FileInputStream fis;
//                fis = new FileInputStream(path);
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("Cruce_PILA_"+new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
                CellRangeAddress region = CellRangeAddress.valueOf("B1:F1");
                sheet.addMergedRegion(region);
                region = CellRangeAddress.valueOf("B3:F3");
                sheet.addMergedRegion(region);
                region = CellRangeAddress.valueOf("B5:F5");
                sheet.addMergedRegion(region);
                                
                HSSFRow row_tittle = sheet.createRow(0);
                HSSFCell cellB1 = row_tittle.createCell(1);
                cellB1.setCellValue("CRUCE PILA");
                HSSFCellStyle centerstyle = workbook.createCellStyle();
                centerstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cellB1.setCellStyle(centerstyle);
                                
                HSSFRow row_date = sheet.createRow(2);
                HSSFCell cellB4 = row_date.createCell(1);
                cellB4.setCellValue(get_fecha_fin(mes, año, 1));
                HSSFCellStyle cellStyle = workbook.createCellStyle();
                CreationHelper createHelper = workbook.getCreationHelper();
                cellStyle.setDataFormat(
                    createHelper.createDataFormat().getFormat("dddd, d \"de\" mmmm \"de\" yyyy"));
                cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cellB4.setCellStyle(cellStyle);
                
                
                HSSFRow row_eps = sheet.createRow(4);
                HSSFCell cellB7 = row_eps.createCell(1);
                cellB7.setCellValue("Empleados del sistema con EPS diferente");
                cellB7.setCellStyle(centerstyle);
                
                HSSFRow row_eps_det = sheet.createRow(5);
                HSSFCell cellB8 = row_eps_det.createCell(1);
                cellB8.setCellValue("Cedula - ID");
                HSSFCell cellC8 = row_eps_det.createCell(2);
                cellC8.setCellValue("Nombre");
                HSSFCell cellD8 = row_eps_det.createCell(3);
                cellD8.setCellValue("EPS Pila");
                HSSFCell cellE8 = row_eps_det.createCell(4);
                cellE8.setCellValue("EPS Sistema");
                
                int cell_start = 6;
                int cell_eps_end = cell_start + emp_eps_diff.getRowCount();
                for (int i = 0; i < emp_eps_diff.getRowCount(); i++) {
                    HSSFRow eps_det = sheet.createRow(cell_start+i);
                    for (int j = 0; j < emp_eps_diff.getColumnCount(); j++) {
                        HSSFCell cell_eps_det = eps_det.createCell(1+j);
                        if (j==0) {
//                            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                            cell_eps_det.setCellValue(Double.parseDouble(emp_eps_diff.getValueAt(i, j).toString()));
                        }
                        if (j==1) {
//                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell_eps_det.setCellValue(emp_eps_diff.getValueAt(i, j).toString());
                        }
                        if (j==2) {
                            cell_eps_det.setCellValue(emp_eps_diff.getValueAt(i, j).toString());
                        }
                        if (j==3) {
                            cell_eps_det.setCellValue(emp_eps_diff.getValueAt(i, j).toString());
                        }
                    }
                }
                region = CellRangeAddress.valueOf("B"+(cell_eps_end+2)+":F"+(cell_eps_end+2));
                sheet.addMergedRegion(region);
                
                HSSFRow row_arl = sheet.createRow(1+cell_eps_end);
                HSSFCell cellBx = row_arl.createCell(1);
                cellBx.setCellValue("Empleados del sistema con ARL diferente");
                cellBx.setCellStyle(centerstyle);
                
                HSSFRow row_arl_det = sheet.createRow(2+cell_eps_end);
                HSSFCell cellBy = row_arl_det.createCell(1);
                cellBy.setCellValue("Cedula - ID");
                HSSFCell cellCy = row_arl_det.createCell(2);
                cellCy.setCellValue("Nombre");
                HSSFCell cellDy = row_arl_det.createCell(3);
                cellDy.setCellValue("ARL Pila");
                HSSFCell cellEy = row_arl_det.createCell(4);
                cellEy.setCellValue("ARL Sistema");
                
                int cell_arl_end = 3 + cell_eps_end + emp_arl_diff.getRowCount();
                for (int i = 0; i < emp_arl_diff.getRowCount(); i++) {
                    HSSFRow arl_det = sheet.createRow(3+cell_eps_end+i);
                    for (int j = 0; j < emp_arl_diff.getColumnCount(); j++) {
                        HSSFCell cell_arl_det = arl_det.createCell(1+j);
                        if (j==0) {
//                            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                            cell_arl_det.setCellValue(Double.parseDouble(emp_arl_diff.getValueAt(i, j).toString()));
                        }
                        if (j==1) {
//                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell_arl_det.setCellValue(emp_arl_diff.getValueAt(i, j).toString());
                        }
                        if (j==2) {
                          cell_arl_det.setCellValue(emp_arl_diff.getValueAt(i, j).toString());
                        }
                        if (j==3) {
                          cell_arl_det.setCellValue(emp_arl_diff.getValueAt(i, j).toString());
                        }
                    }
                }
                region = CellRangeAddress.valueOf("B"+(cell_arl_end+2)+":F"+(cell_arl_end+2));
                sheet.addMergedRegion(region);
                
                
                HSSFRow row_afp = sheet.createRow(cell_arl_end+1);
                HSSFCell cellBw = row_afp.createCell(1);
                cellBw.setCellValue("Empleados del sistema con AFP diferente");
                cellBw.setCellStyle(centerstyle);
                
                HSSFRow row_afp_det = sheet.createRow(cell_arl_end+2);
                HSSFCell cellBz = row_afp_det.createCell(1);
                cellBz.setCellValue("Cedula - ID");
                HSSFCell cellCz = row_afp_det.createCell(2);
                cellCz.setCellValue("Nombre");
                HSSFCell cellDz = row_afp_det.createCell(3);
                cellDz.setCellValue("AFP Pila");
                HSSFCell cellEz = row_afp_det.createCell(4);
                cellEz.setCellValue("AFP Sistema");
                
                int cell_afp_end = cell_arl_end + 3 + emp_afp_diff.getRowCount();
                for (int i = 0; i < emp_afp_diff.getRowCount(); i++) {
                    HSSFRow afp_det = sheet.createRow(cell_arl_end+3+i);
                    for (int j = 0; j < emp_afp_diff.getColumnCount(); j++) {
                        HSSFCell cell_afp_det = afp_det.createCell(1+j);
                        if (j==0) {
//                            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                            cell_afp_det.setCellValue(Double.parseDouble(emp_afp_diff.getValueAt(i, j).toString()));
                        }
                        if (j==1) {
//                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell_afp_det.setCellValue(emp_afp_diff.getValueAt(i, j).toString());
                        }
                        if (j==2) {
                            cell_afp_det.setCellValue(emp_afp_diff.getValueAt(i, j).toString());
                        }
                        if (j==3) {
                            cell_afp_det.setCellValue(emp_afp_diff.getValueAt(i, j).toString());
                        }
                        
                    }
                }
                region = CellRangeAddress.valueOf("B"+(cell_afp_end+2)+":F"+(cell_afp_end+2));
                sheet.addMergedRegion(region);
                
                HSSFRow row_ccf = sheet.createRow(cell_afp_end+1);
                HSSFCell cellBv = row_ccf.createCell(1);
                cellBv.setCellValue("Empleados del sistema con CCF diferente");
                cellBv.setCellStyle(centerstyle);
                
                HSSFRow row_ccf_det = sheet.createRow(cell_afp_end+2);
                HSSFCell cellBv1 = row_ccf_det.createCell(1);
                cellBv1.setCellValue("Cedula - ID");
                HSSFCell cellCv1 = row_ccf_det.createCell(2);
                cellCv1.setCellValue("Nombre");
                HSSFCell cellDv1 = row_ccf_det.createCell(3);
                cellDv1.setCellValue("CCF Pila");
                HSSFCell cellEv1 = row_ccf_det.createCell(4);
                cellEv1.setCellValue("CCF Sistema");
                
                for (int i = 0; i < emp_ccf_diff.getRowCount(); i++) {
                    HSSFRow ccf_det = sheet.createRow(cell_afp_end+3+i);
                    for (int j = 0; j < emp_ccf_diff.getColumnCount(); j++) {
                        HSSFCell cell_ccf_det = ccf_det.createCell(1+j);
                        if (j==0) {
//                            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                            cell_ccf_det.setCellValue(Double.parseDouble(emp_ccf_diff.getValueAt(i, j).toString()));
                        }
                        if (j==1) {
//                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell_ccf_det.setCellValue(emp_ccf_diff.getValueAt(i, j).toString());
                        }
                        if (j==2) {
                            cell_ccf_det.setCellValue(emp_ccf_diff.getValueAt(i, j).toString());
                        }
                        if (j==3) {
                            cell_ccf_det.setCellValue(emp_ccf_diff.getValueAt(i, j).toString());
                        }
                    }
                }
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);
                byte[] xls = baos.toByteArray();
                
                ResultSet r;
                Blob blob=new SerialBlob(xls);
                Calendar ahoraCal = Calendar.getInstance();
                
                ps = con.c.prepareStatement("INSERT INTO `t_cruce_pila`(`CRUCE`, `VIGENCIA_CRUCE`, `FECHA_CRUCE`) VALUES (?,?,?)");
                ps.setBlob(1, blob);
                ps.setString(2, get_fecha_fin(this.mes, this.año));
                ps.setObject(3, new java.sql.Timestamp(ahoraCal.getTimeInMillis()));
                ps.execute();
                con.cerrar();
                term = true;
                
//                
//                FileOutputStream fileOut;
//                fileOut = new FileOutputStream("C:\\Users\\Johnnatan\\Documents\\CrucePILA.xls");
//                workbook.write(fileOut);
//                fileOut.close();
            } catch (NumberFormatException | IOException | SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);
                con.cerrar();
            }finally{
                    if (ps!=null) {
                        try {
                            ps.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        ps=null;
                    }
                    con.cerrar();
                }
            if (term) {
                JOptionPane.showMessageDialog(this,"El Cruce fue almacenado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this,"El Cruce no pudo ser generado","Advertencia",JOptionPane.WARNING_MESSAGE);
                this.dispose();
            }
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Preview_Cruce_PILA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Preview_Cruce_PILA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Preview_Cruce_PILA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Preview_Cruce_PILA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                Preview_Cruce_PILA dialog = new Preview_Cruce_PILA(new javax.swing.JFrame(), true, new JTable(), new Integer(""), new Integer(""));
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
    private javax.swing.JTable emp_afp_diff;
    private javax.swing.JTable emp_arl_diff;
    private javax.swing.JTable emp_ccf_diff;
    private javax.swing.JTable emp_eps_diff;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel n_afp_diff;
    private javax.swing.JLabel n_arl_diff;
    private javax.swing.JLabel n_ccf_diff;
    private javax.swing.JLabel n_eps_dif;
    // End of variables declaration//GEN-END:variables

@SuppressWarnings("CallToPrintStackTrace")
public void sys_no(JTable pila,int m, int y){
    DefaultTableModel modelo_eps = (DefaultTableModel)emp_eps_diff.getModel();
    DefaultTableModel modelo_arl = (DefaultTableModel)emp_arl_diff.getModel();
    DefaultTableModel modelo_afp = (DefaultTableModel)emp_afp_diff.getModel();
    DefaultTableModel modelo_ccf = (DefaultTableModel)emp_ccf_diff.getModel();
    Object [] fila = new Object[4];
    if (emp_eps_diff.getRowCount()>0) {
        int j = emp_eps_diff.getRowCount();
        for (int i = 0; i < j; i++) {
            modelo_eps.removeRow(emp_eps_diff.getRowCount()-1);
            emp_eps_diff.setModel(modelo_eps);
        }
    }
    if (emp_arl_diff.getRowCount()>0) {
        int j = emp_arl_diff.getRowCount();
        for (int i = 0; i < j; i++) {
            modelo_arl.removeRow(emp_arl_diff.getRowCount()-1);
            emp_arl_diff.setModel(modelo_arl);
        }
    }
    if (emp_afp_diff.getRowCount()>0) {
        int j = emp_afp_diff.getRowCount();
        for (int i = 0; i < j; i++) {
            modelo_afp.removeRow(emp_afp_diff.getRowCount()-1);
            emp_afp_diff.setModel(modelo_afp);
        }
    }
    if (emp_ccf_diff.getRowCount()>0) {
        int j = emp_ccf_diff.getRowCount();
        for (int i = 0; i < j; i++) {
            modelo_ccf.removeRow(emp_ccf_diff.getRowCount()-1);
            emp_ccf_diff.setModel(modelo_ccf);
        }
    }
    for (int i = 0; i < pila.getRowCount(); i++) {//SE RECORRE TODA LA INFORMACION DE LA PILA INGRESADA EN LA TABLA        
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r=null;
        try{
            r = con.s.executeQuery ("SELECT *\n" +
                                    "FROM\n" +
                                    "    t_novedades\n" +
                                    "    INNER JOIN t_empresas \n" +
                                    "        ON (t_novedades.ID_EMPRESA = t_empresas.ID_EMPRESA)\n" +
                                    "    INNER JOIN t_empleados \n" +
                                    "        ON (t_novedades.ID_EMPLEADO = t_empleados.ID_EMP)\n" +
//                                    "    INNER JOIN t_arl \n" +
//                                    "        ON (t_empresas.ID_ARL = t_arl.ID_ARL)\n" +
//                                    "    INNER JOIN t_ccf \n" +
//                                    "        ON (t_empresas.ID_CCF = t_ccf.ID_CCF)\n" +
                                    "    INNER JOIN t_eps \n" +
                                    "        ON (t_novedades.ID_EPS = t_eps.ID_EPS)\n" +
                                    "    INNER JOIN t_afp \n" +
                                    "        ON (t_novedades.ID_AFP = t_afp.ID_AFP)\n" +
                                    "    WHERE t_novedades.ID_EMPRESA = '"+pila.getValueAt(i,1).toString()+"'\n" +
                                    "    AND t_novedades.ID_EMPLEADO = "+pila.getValueAt(i,0).toString()+"\n" +
                                    "    AND  t_novedades.ID_TIPO IN (1,4,5)\n"+
                                    "    AND ((t_novedades.FECHA_INGRESO <= '"+get_fecha_fin(m, y)+"' AND t_novedades.FECHA_RETIRO = '1900-01-01')\n" +
                                    "	     OR ( t_novedades.FECHA_INGRESO <= '"+get_fecha_fin(m, y)+"' AND t_novedades.FECHA_RETIRO >= '"+get_fecha_ini(m, y)+"'))\n"+
                                    "    ORDER BY t_novedades.FECHA_INGRESO DESC");
            
            
            if(r.next()){
                if (!r.getString("NOMBRE_EPS").equals(pila.getValueAt(i,2).toString())) {
                    modelo_eps.addRow(fila);
                    modelo_eps.setValueAt(Long.parseLong(r.getString("ID_EMPLEADO")),emp_eps_diff.getRowCount()-1,0);
                    modelo_eps.setValueAt(r.getString("APELLIDO_1_EMP")+" "+r.getString("APELLIDO_2_EMP")+" "+r.getString("NOMBRE_1_EMP")+" "+r.getString("NOMBRE_2_EMP"),emp_eps_diff.getRowCount()-1,1);
                    modelo_eps.setValueAt(pila.getValueAt(i,2).toString(),emp_eps_diff.getRowCount()-1,2);
                    modelo_eps.setValueAt(r.getString("NOMBRE_EPS"),emp_eps_diff.getRowCount()-1,3);
                }
                if (!r.getString("ARL_NOV").equals(pila.getValueAt(i,3).toString())) {
                    modelo_arl.addRow(fila);
                    modelo_arl.setValueAt(Long.parseLong(r.getString("ID_EMPLEADO")),emp_arl_diff.getRowCount()-1,0);
                    modelo_arl.setValueAt(r.getString("APELLIDO_1_EMP")+" "+r.getString("APELLIDO_2_EMP")+" "+r.getString("NOMBRE_1_EMP")+" "+r.getString("NOMBRE_2_EMP"),emp_arl_diff.getRowCount()-1,1);
                    modelo_arl.setValueAt(pila.getValueAt(i,3).toString(),emp_arl_diff.getRowCount()-1,2);
                    modelo_arl.setValueAt(r.getString("ARL_NOV"),emp_arl_diff.getRowCount()-1,3);
                }
                if (!r.getString("NOMBRE_AFP").equals(pila.getValueAt(i,4).toString())) {
                    modelo_afp.addRow(fila);
                    modelo_afp.setValueAt(Long.parseLong(r.getString("ID_EMPLEADO")),emp_afp_diff.getRowCount()-1,0);
                    modelo_afp.setValueAt(r.getString("APELLIDO_1_EMP")+" "+r.getString("APELLIDO_2_EMP")+" "+r.getString("NOMBRE_1_EMP")+" "+r.getString("NOMBRE_2_EMP"),emp_afp_diff.getRowCount()-1,1);
                    modelo_afp.setValueAt(pila.getValueAt(i,4).toString(),emp_afp_diff.getRowCount()-1,2);
                    modelo_afp.setValueAt(r.getString("NOMBRE_AFP"),emp_afp_diff.getRowCount()-1,3);
                }
                if (!r.getString("CCF_NOV").equals(pila.getValueAt(i,5).toString())) {
                    modelo_ccf.addRow(fila);
                    modelo_ccf.setValueAt(Long.parseLong(r.getString("ID_EMPLEADO")),emp_ccf_diff.getRowCount()-1,0);
                    modelo_ccf.setValueAt(r.getString("APELLIDO_1_EMP")+" "+r.getString("APELLIDO_2_EMP")+" "+r.getString("NOMBRE_1_EMP")+" "+r.getString("NOMBRE_2_EMP"),emp_ccf_diff.getRowCount()-1,1);
                    modelo_ccf.setValueAt(pila.getValueAt(i,5).toString(),emp_ccf_diff.getRowCount()-1,2);
                    modelo_ccf.setValueAt(r.getString("CCF_NOV"),emp_ccf_diff.getRowCount()-1,3);
                }
            }
            con.cerrar();
        }catch(SQLException e){
            con.cerrar();
            e.printStackTrace();
        }finally{
            if (r!=null) {
                try {
                    r.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                r=null;
            }
            con.cerrar();
        }
    }
}
public boolean chech_char(String s, String c){
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
public int count_char(String str, char c){
    String pr=str.trim();
    char _toCompare=c;
    int veces=0;
    char []caracteres=pr.toCharArray();
    for(int i=0;i<=caracteres.length-1;i++){
        if(_toCompare ==caracteres[i]){
                veces++;
        }
}
    return veces;
}
public Date get_fecha(Object fecha){
    Date ret=null;
    if (fecha!=null) {
        if (chech_char(fecha.toString().trim(),"'#$%&()=?¡¿*+[]{};:<>,.")) {
            if (!fecha.toString().trim().equals("") & count_char(fecha.toString().trim(),'/')==2) {
                int str_año=0;
                int str_mes=0;
                int str_dia=0;
                try {
                    StringTokenizer tokens=new StringTokenizer(fecha.toString().trim(),"/");
                    while(tokens.hasMoreTokens()){
                        str_dia=Integer.parseInt(tokens.nextToken().trim());
                        str_mes=Integer.parseInt(tokens.nextToken().trim());
                        str_año=Integer.parseInt(tokens.nextToken().trim());
                    }
                    if (str_dia>0 & str_dia<=31) {
                        if (str_mes>0 & str_mes<=12) {
                            if (str_año>=1900 & str_año<=2050) {
                                Calendar ahoraCal = Calendar.getInstance();
                                
                                ahoraCal.set(str_año,str_mes-1,1,0,0,0);
                                ahoraCal.set(Calendar.DATE, ahoraCal.getActualMaximum(Calendar.DATE));
                                int f = ahoraCal.get(Calendar.DAY_OF_MONTH);
                                if (str_dia<=f) {
                                    Calendar r = Calendar.getInstance();
                                    r.set(str_año, str_mes-1,str_dia,0,0,0);
                                    r.set(Calendar.MILLISECOND, 0);
                                    ret=r.getTime();
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
    return ret;
}
public String get_fecha_ini(int m, int y){
    Calendar ahoraCal = Calendar.getInstance();
    ahoraCal.set(Calendar.MONTH,m);
    ahoraCal.set(Calendar.YEAR,y);
    ahoraCal.set(Calendar.DATE, ahoraCal.getActualMinimum(Calendar.DATE));
    ahoraCal.set(Calendar.HOUR_OF_DAY,0);
    ahoraCal.set(Calendar.MINUTE,0);
    ahoraCal.set(Calendar.SECOND,0);
    return new SimpleDateFormat("yyyy-MM-dd").format(ahoraCal.getTime());
}
public String get_fecha_fin(int m, int y){
    Calendar ahoraCal = Calendar.getInstance();
    ahoraCal.set(Calendar.MONTH,m);
    ahoraCal.set(Calendar.YEAR,y);
    ahoraCal.set(Calendar.DATE, ahoraCal.getActualMaximum(Calendar.DATE));
    ahoraCal.set(Calendar.HOUR_OF_DAY,0);
    ahoraCal.set(Calendar.MINUTE,0);
    ahoraCal.set(Calendar.SECOND,0);
    return  new SimpleDateFormat("yyyy-MM-dd").format(ahoraCal.getTime());
}
public Date get_fecha_fin(int m, int y , int z){
    Calendar ahoraCal = Calendar.getInstance();
    ahoraCal.set(Calendar.MONTH,m);
    ahoraCal.set(Calendar.YEAR,y);
    ahoraCal.set(Calendar.DATE, ahoraCal.getActualMaximum(Calendar.DATE));
    ahoraCal.set(Calendar.HOUR_OF_DAY,0);
    ahoraCal.set(Calendar.MINUTE,0);
    ahoraCal.set(Calendar.SECOND,0);
    return  ahoraCal.getTime();
}
public Float get_porc(Object porc){
    Float ret=null;
    if (porc!=null) {
        if (chech_char(porc.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>")) {
            try {
                Float p = Float.parseFloat(porc.toString());
                ret = p;
            } catch (Exception e) {
                ret = null;
            }
        }
    }
    return ret;
}
public static ArrayList<ArrayList<String>> load_table_to_list (JTable t){
    ArrayList<ArrayList<String>> p = new ArrayList<>();
    p.clear();
    if (t.getRowCount()>0) {
        for (int i = 0; i < t.getRowCount(); i++) {
            ArrayList<String>col=new ArrayList<>();
            col.add(t.getValueAt(i, 0).toString());
            col.add(t.getValueAt(i, 1).toString());
            if (t.getValueAt(i, 2)!=null) {
                col.add(t.getValueAt(i, 2).toString());
            }else{
                col.add("");
            }
            if (t.getValueAt(i, 3)!=null) {
                col.add(t.getValueAt(i, 3).toString());
            }else{
                col.add("");
            }
            if (t.getValueAt(i, 4)!=null) {
                col.add(t.getValueAt(i, 4).toString());
            }else{
                col.add("");
            }
            if (t.getValueAt(i, 5)!=null) {
                col.add(t.getValueAt(i, 5).toString());
            }else{
                col.add("");
            }
            p.add(col);
        }
    }
    return p;
}
}
