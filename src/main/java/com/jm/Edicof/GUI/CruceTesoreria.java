/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.GUI;


import com.jm.Edicof.Clases.Conexion;
import com.jm.Edicof.Clases.Copiar2Excel_CruceTesoreria;
import com.jm.Edicof.Clases.Struct_Cruce_Precalculo;
import com.jm.Edicof.Clases.Struct_Cruce_Tesoreria;
import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Johnnatan
 */
public class CruceTesoreria extends javax.swing.JDialog {
    DefaultTableModel modelo_prec_def = null;
    DefaultTableModel modelo_cruce_prec = null;
    /**
     * Creates new form CrucePrecalculo
     */
    public CruceTesoreria(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        modelo_prec_def=(DefaultTableModel) cruce_precs.getModel();
        modelo_cruce_prec=(DefaultTableModel) cruce_tesor.getModel();
        load_cruce_prec();
        DefaultTableCellRenderer tcr = new FormatRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        cruce_tesor.getColumnModel().getColumn(0).setCellRenderer(tcr);
        cruce_tesor.getColumnModel().getColumn(1).setCellRenderer(tcr);
        cruce_tesor.getColumnModel().getColumn(2).setCellRenderer(tcr);
        cruce_tesor.getColumnModel().getColumn(3).setCellRenderer(tcr);
        cruce_tesor.getColumnModel().getColumn(4).setCellRenderer(tcr);
        cruce_tesor.getColumnModel().getColumn(5).setCellRenderer(tcr);
        cruce_tesor.getColumnModel().getColumn(6).setCellRenderer(tcr);
        cruce_tesor.getColumnModel().getColumn(7).setCellRenderer(tcr);
        cruce_tesor.getColumnModel().getColumn(8).setCellRenderer(tcr);
        cruce_tesor.getColumnModel().getColumn(9).setCellRenderer(tcr);
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr1.setHorizontalAlignment(SwingConstants.CENTER);
        cruce_precs.getColumnModel().getColumn(0).setCellRenderer(tcr1);
        cruce_precs.getColumnModel().getColumn(1).setCellRenderer(tcr1);
        cruce_precs.getColumnModel().getColumn(2).setCellRenderer(tcr1);
        cruce_precs.getColumnModel().getColumn(3).setCellRenderer(tcr1);
        cruce_precs.getColumnModel().getColumn(4).setCellRenderer(tcr1);
        //cruce_tesor.setDefaultRenderer (Object.class, new CellRender_Cruce_Tesoreria());
        cruce_precs.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent event) {
                clear_cruc();
            }
        });
        cruce_tesor.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType()==TableModelEvent.INSERT||e.getType()==TableModelEvent.DELETE) {
                    if (cruce_tesor.getRowCount()!=0) {
                        btn_almacenar.setEnabled(true);
                        btn_dif.setEnabled(true);
                    }else{
                        btn_almacenar.setEnabled(false);
                        btn_dif.setEnabled(false);
                    }
                }
                
                //cruce_tesor.getCellEditor().stopCellEditing();
            }
    });
    }
    public class FormatRenderer extends DefaultTableCellRenderer{
        Locale locale = new Locale("es","CO"); 
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
        public FormatRenderer(){
        
        }
        @Override
        public void setValue(Object value)
	{
            try{
                if (value != null ){
                    if (value instanceof Float) {
                        value = nf.format(value);
                    }
                }
            }catch(IllegalArgumentException e) {
                e.printStackTrace();
            }
            super.setValue(value);
	}
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected,boolean hasFocus,int row,int column){
            JLabel cell = (JLabel) super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
            if (table.getModel().isCellEditable(row,column)) {
                cell.setOpaque(true);
                cell.setBackground(new Color(150,240,160));
                cell.setForeground(Color.black);
            }else{
                cell.setOpaque(true);
                cell.setBackground(new Color(192,192,192));
                cell.setForeground(Color.black);
            }
            if (table.getColumnName(column).equals("FIC Aplicado") | table.getColumnName(column).equals("Total Aplicado")) {
                if (value!=null) {
                    if(!value.toString().equals("")){
                        if (chech_char(value.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>")) {
                            if (comprobarFloat(value.toString().trim())) {
                                cell.setOpaque(true);
                                cell.setBackground(new Color(150,240,160));
                                cell.setForeground(Color.black);
                            }else{
                                cell.setOpaque(true);
                                cell.setBackground(Color.red);
                                cell.setForeground(Color.white);
                            }
                        }else{
                            cell.setOpaque(true);
                            cell.setBackground(Color.red);
                            cell.setForeground(Color.white);
                        }
                    }
                }else{
                    cell.setOpaque(true);
                    cell.setBackground(Color.red);
                    cell.setForeground(Color.white);
                }
            }
            if (isSelected) {
                cell.setOpaque(true);
                cell.setBackground(new Color(0,128,255));
                cell.setForeground(Color.black);
            }
        return this;
       }
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
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        año = new com.toedter.calendar.JYearChooser();
        mes = new com.toedter.calendar.JMonthChooser();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cruce_precs = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        cruce_tesor = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        btn_almacenar = new javax.swing.JButton();
        btn_dif = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        registros = new javax.swing.JLabel();

        jMenu1.setText("Cruces de Precalculo");

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete.png"))); // NOI18N
        jMenuItem4.setText("Eliminar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jPopupMenu1.add(jMenu1);

        jMenu2.setText("Cruces de Tesoreria");
        jMenu2.setToolTipText("");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cross_promotion_selling.png"))); // NOI18N
        jMenuItem1.setText("Generar Cruce");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/document_inspector.png"))); // NOI18N
        jMenuItem2.setText("Consultar Cruce");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete.png"))); // NOI18N
        jMenuItem3.setText("Eliminar Cruce");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jPopupMenu1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cruce de Tesoreria");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione mes y año"));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_tab_search.png"))); // NOI18N
        jButton4.setText("Buscar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(111, Short.MAX_VALUE)
                .addComponent(mes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(año, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(mes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(año, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cruces de Precalculo"));

        cruce_precs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cruce_precs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha Inicial", "Fecha Final", "Fecha Creación", "Cruce Tesoreria"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        cruce_precs.setComponentPopupMenu(jPopupMenu1);
        cruce_precs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cruce_precsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(cruce_precs);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(9, 9, 9))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion del Cruce"));

        cruce_tesor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NIT", "Nombre Empresa", "FIC Reportado", "FIC Aplicado", "Diferencia FIC", "Total Reportado", "Total Aplicado", "Diferencia Total", "Comp. Egreso", "Observaciones"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, true, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        cruce_tesor.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        cruce_tesor.setCellSelectionEnabled(true);
        cruce_tesor.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        cruce_tesor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cruce_tesorKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(cruce_tesor);
        new Copiar2Excel_CruceTesoreria(cruce_tesor);
        if (cruce_tesor.getColumnModel().getColumnCount() > 0) {
            cruce_tesor.getColumnModel().getColumn(0).setResizable(false);
            cruce_tesor.getColumnModel().getColumn(0).setPreferredWidth(90);
            cruce_tesor.getColumnModel().getColumn(1).setResizable(false);
            cruce_tesor.getColumnModel().getColumn(1).setPreferredWidth(200);
            cruce_tesor.getColumnModel().getColumn(2).setResizable(false);
            cruce_tesor.getColumnModel().getColumn(2).setPreferredWidth(90);
            cruce_tesor.getColumnModel().getColumn(3).setResizable(false);
            cruce_tesor.getColumnModel().getColumn(3).setPreferredWidth(90);
            cruce_tesor.getColumnModel().getColumn(4).setResizable(false);
            cruce_tesor.getColumnModel().getColumn(4).setPreferredWidth(90);
            cruce_tesor.getColumnModel().getColumn(5).setResizable(false);
            cruce_tesor.getColumnModel().getColumn(5).setPreferredWidth(90);
            cruce_tesor.getColumnModel().getColumn(6).setResizable(false);
            cruce_tesor.getColumnModel().getColumn(6).setPreferredWidth(90);
            cruce_tesor.getColumnModel().getColumn(7).setResizable(false);
            cruce_tesor.getColumnModel().getColumn(7).setPreferredWidth(90);
            cruce_tesor.getColumnModel().getColumn(8).setResizable(false);
            cruce_tesor.getColumnModel().getColumn(8).setPreferredWidth(90);
            cruce_tesor.getColumnModel().getColumn(9).setResizable(false);
            cruce_tesor.getColumnModel().getColumn(9).setPreferredWidth(500);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 987, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancel_2.png"))); // NOI18N
        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_money.png"))); // NOI18N
        jButton3.setText("Generar Cruce");
        jButton3.setToolTipText("Seleccione una fila en la tabla de precalculos definitivos para generar el cruce");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btn_almacenar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_save.png"))); // NOI18N
        btn_almacenar.setText("Almacenar Cruce");
        btn_almacenar.setEnabled(false);
        btn_almacenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_almacenarActionPerformed(evt);
            }
        });

        btn_dif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calculator.png"))); // NOI18N
        btn_dif.setText("Calcular Diferencias");
        btn_dif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_difActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_almacenar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(btn_dif)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(btn_almacenar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_dif)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen"));

        jLabel1.setText("Registros");

        registros.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        registros.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registros.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(registros, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(registros, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        clear_prec();
        clear_cruc();
        load_cruce_prec();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        calcular_cruce();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cruce_precsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cruce_precsMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            //calcular_cruce();
        }
    }//GEN-LAST:event_cruce_precsMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        calcular_cruce();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btn_almacenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_almacenarActionPerformed
        // TODO add your handling code here:
        int s = JOptionPane.showConfirmDialog(this,"Usted esta a punto de almacenar el Cruce de Tesoreria para\nel siguiente precalculo definitivo:\nID: "+cruce_precs.getValueAt(cruce_precs.getSelectedRow(), 0)+"\nFecha inicial: "+cruce_precs.getValueAt(cruce_precs.getSelectedRow(), 1)+"\nFecha final: "+cruce_precs.getValueAt(cruce_precs.getSelectedRow(), 2)+"\nDesea Continuar?", "Advertencia",JOptionPane.WARNING_MESSAGE);
        if(s==JOptionPane.YES_OPTION){
            if (cruce_precs.getValueAt(cruce_precs.getSelectedRow(), 4).toString().equals("NO")) {
                Conexion con = new Conexion();
                con.conexion();
                int id_cruce=0;
                boolean conf=true;
                try {
                    ResultSet r;
                    int i = con.s.executeUpdate("INSERT INTO `t_cruce_tesoreria`(`F_REGISTRO`, `ID_USUARIO`, `ID_CRUCE_PREC`) VALUES ('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+Main.id_usuario+"',"+cruce_precs.getValueAt(cruce_precs.getSelectedRow(), 0)+")", Statement.RETURN_GENERATED_KEYS);
                    if (i==1) {
                        r = con.s.getGeneratedKeys();
                        if (r.next()) {
                            id_cruce=r.getInt(1);
                        }
                    }
                    for (int j = 0; j < cruce_tesor.getRowCount(); j++) {
                        con.s.executeUpdate("INSERT INTO `t_det_cruce_tesoreria`(`ID_CRUCE_TESO`, `ID_EMPRESA`, `FIC_REPORTADO`, `FIC_APLICADO`, `DIF_FIC`, `TOT_REPORTADO`, `TOT_APLICADO`, `DIF_TOTAL`, `EGRESO`, `OBS`) VALUES ("+id_cruce+",'"+cruce_tesor.getValueAt(j, 0)+"',"+cruce_tesor.getValueAt(j, 2)+","+cruce_tesor.getValueAt(j, 3)+","+cruce_tesor.getValueAt(j, 4)+","+cruce_tesor.getValueAt(j, 5)+","+cruce_tesor.getValueAt(j, 6)+","+cruce_tesor.getValueAt(j, 7)+",'"+cruce_tesor.getValueAt(j, 8).toString().replace("\n","-")+"','"+cruce_tesor.getValueAt(j, 9).toString().replace("\n","-")+"')");
                        modelo_cruce_prec.removeRow(j);
                        j=j-1;
                        conf=conf&true;
                    }
                    con.cerrar();
                }catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);
                    con.cerrar();
                    conf=conf&false;
                }
                if (conf) {
                    JOptionPane.showMessageDialog(this,"El cruce de tesoreria ha sido almacenado correctamente","Confirmación",JOptionPane.INFORMATION_MESSAGE);
                    clear_prec();
                    clear_cruc();
                    load_cruce_prec();
                }
            } else {
                JOptionPane.showMessageDialog(this,"Debe seleccionar un precalculo definitivo que no tenga ningun cruce almacenado","Error",JOptionPane.ERROR_MESSAGE);
            }
        }    
    }//GEN-LAST:event_btn_almacenarActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        if (cruce_precs.getRowCount()>0) {
            if (cruce_precs.getSelectedRow()!=-1) {
                if (cruce_precs.getValueAt(cruce_precs.getSelectedRow(), 4).toString().equals("SI")) {
                    JFileChooser fc_rep_cruce = new JFileChooser();
                    fc_rep_cruce.setFileFilter(new FileNameExtensionFilter("Archivos Excel (*.xlsx)","xlsx"));
                    fc_rep_cruce.setSelectedFile(new File("Reporte_Cruce_Tesoreria_del_"+cruce_precs.getValueAt(cruce_precs.getSelectedRow(), 1)+"_al_"+cruce_precs.getValueAt(cruce_precs.getSelectedRow(), 2)));
                    //System.out.println("Extencion: "+((FileNameExtensionFilter)fc_rep_cruce.getFileFilter()).getExtensions()[0]);
                    if(fc_rep_cruce.showSaveDialog(this)==JFileChooser.APPROVE_OPTION){
                        Struct_Cruce_Tesoreria s = new Struct_Cruce_Tesoreria(this,fc_rep_cruce.getSelectedFile().getAbsolutePath(),((FileNameExtensionFilter)fc_rep_cruce.getFileFilter()).getExtensions()[0], Integer.parseInt(cruce_precs.getValueAt(cruce_precs.getSelectedRow(), 0).toString()),fc_rep_cruce.getSelectedFile().toString());
                        Wait_rep t = new Wait_rep(this,true,s);
                        t.setLocationRelativeTo(null);
                        t.setVisible(true);
                        this.dispose();
                    }
                }else{
                    JOptionPane.showMessageDialog(this,"El registro seleccionado no tiene ningun Cruce generado anteriormente","Error",JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(this,"Debe seleccionar un registro en la tabla de precalculos definitivos","Error",JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this,"La tabla de precalculos definitivos debe tener al menos 1 registro","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        if (cruce_precs.getSelectedRow()!=-1) {
            if (cruce_precs.getValueAt(cruce_precs.getSelectedRow(), 4).toString().equals("SI")) {
                int s = JOptionPane.showConfirmDialog(this,"Usted esta a punto de eliminar el cruce de tesoreria para\nel siguiente precalculo definitivo:\nID: "+cruce_precs.getValueAt(cruce_precs.getSelectedRow(), 0)+"\nFecha inicial: "+cruce_precs.getValueAt(cruce_precs.getSelectedRow(), 1)+"\nFecha final: "+cruce_precs.getValueAt(cruce_precs.getSelectedRow(), 2)+"\nDesea Continuar?", "Advertencia",JOptionPane.WARNING_MESSAGE);
                if(s==JOptionPane.YES_OPTION){
                    Conexion con = new Conexion();
                    con.conexion();
                    try {
                        con.s.executeUpdate("DELETE FROM `t_cruce_tesoreria` WHERE ID_CRUCE_PREC= "+cruce_precs.getValueAt(cruce_precs.getSelectedRow(),0));
                        con.cerrar();
                    }catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);
                        con.cerrar();
                    }
                    JOptionPane.showMessageDialog(this,"El Cruce de Tesoreria fue eliminado satisfactoriamente","Confirmación",JOptionPane.INFORMATION_MESSAGE);
                    clear_prec();
                    clear_cruc();
                    load_cruce_prec();
                }
                
            }else{
                JOptionPane.showMessageDialog(this,"El registro seleccionado no tiene asociado ningún Cruce de Tesoreria","Error",JOptionPane.ERROR_MESSAGE);
            }   
        }else{
            JOptionPane.showMessageDialog(this,"Debe seleccionar un registro en la tabla de cruces de precalculo","Error",JOptionPane.ERROR_MESSAGE);
        }    
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        if (cruce_precs.getSelectedRow()!=-1) {
            int s = JOptionPane.showConfirmDialog(this,"Usted esta a punto de eliminar el Cruce de Precalculo:\nID: "+cruce_precs.getValueAt(cruce_precs.getSelectedRow(), 0)+"\nFecha inicial precalculo definitivo: "+cruce_precs.getValueAt(cruce_precs.getSelectedRow(), 1)+"\nFecha final precalculo definitivo: "+cruce_precs.getValueAt(cruce_precs.getSelectedRow(), 2)+"\nDesea Continuar?", "Advertencia",JOptionPane.WARNING_MESSAGE);
            if(s==JOptionPane.YES_OPTION){
                Conexion con = new Conexion();
                con.conexion();
                try {
                    con.s.executeUpdate("DELETE FROM `t_cruce_precalculo` WHERE ID_CRUCE_PREC= "+cruce_precs.getValueAt(cruce_precs.getSelectedRow(),0));
                    con.cerrar();
                }catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);
                    con.cerrar();
                }
                JOptionPane.showMessageDialog(this,"El Precalculo Definitivo fue eliminado satisfactoriamente","Confirmación",JOptionPane.INFORMATION_MESSAGE);
                clear_prec();
                clear_cruc();
                load_cruce_prec();
            }

        }else{
            JOptionPane.showMessageDialog(this,"Debe seleccionar un registro en la tabla de cruces de precalculo","Error",JOptionPane.ERROR_MESSAGE);
        } 
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void cruce_tesorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cruce_tesorKeyPressed
        // TODO add your handling code here:
        char a=evt.getKeyChar();
        int b=evt.getKeyCode();
        System.out.println("Key Pressed Tabla: '"+a+"'("+b+")");
        if (cruce_tesor.getEditingColumn()!=-1 && b==10) {
            evt.consume();
        }
    }//GEN-LAST:event_cruce_tesorKeyPressed

    private void btn_difActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_difActionPerformed
        // TODO add your handling code here:
        calcular_diferencias();
    }//GEN-LAST:event_btn_difActionPerformed

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
            java.util.logging.Logger.getLogger(CruceTesoreria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CruceTesoreria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CruceTesoreria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CruceTesoreria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CruceTesoreria dialog = new CruceTesoreria(new javax.swing.JFrame(), true);
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
    private com.toedter.calendar.JYearChooser año;
    private javax.swing.JButton btn_almacenar;
    private javax.swing.JButton btn_dif;
    private javax.swing.JTable cruce_precs;
    private javax.swing.JTable cruce_tesor;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JMonthChooser mes;
    private javax.swing.JLabel registros;
    // End of variables declaration//GEN-END:variables

    private void clear_prec() {
        while(cruce_precs.getRowCount()!=0){
            modelo_prec_def.removeRow(cruce_precs.getRowCount()-1);
        }
    }
    private void clear_cruc(){
        while(cruce_tesor.getRowCount()!=0){
            modelo_cruce_prec.removeRow(cruce_tesor.getRowCount()-1);
        }
        registros.setText(String.valueOf(cruce_tesor.getRowCount()));
    }
    private void load_cruce_prec() {
        Calendar fecha_1 = Calendar.getInstance();
        Calendar fecha_2 = Calendar.getInstance();
        fecha_1.set(año.getYear(), mes.getMonth(),1);
        fecha_2.set(año.getYear(), mes.getMonth(),1);
        fecha_1.set(Calendar.DATE, fecha_1.getActualMinimum(Calendar.DATE));
        fecha_2.set(Calendar.DATE, fecha_1.getActualMaximum(Calendar.DATE));
        Conexion con = new Conexion();
        Conexion con1 = new Conexion();
        con.conexion();
        con1.conexion();
        try {
            ResultSet r,r1;
            r = con.s.executeQuery ("SELECT *\n" +
                                    "FROM\n" +
                                    "    t_cruce_precalculo\n" +
                                    "    INNER JOIN t_precalculo_definitivo \n" +
                                    "        ON (t_cruce_precalculo.ID_PREC = t_precalculo_definitivo.ID_PREC)\n" +
                                    "    WHERE (F_INICIAL BETWEEN '"+new SimpleDateFormat("yyyy-MM-dd").format(fecha_1.getTime())+"' AND '"+new SimpleDateFormat("yyyy-MM-dd").format(fecha_2.getTime())+"') AND (F_FINAL BETWEEN '"+new SimpleDateFormat("yyyy-MM-dd").format(fecha_1.getTime())+"' AND '"+new SimpleDateFormat("yyyy-MM-dd").format(fecha_2.getTime())+"') ORDER BY F_INICIAL,F_FINAL ASC");
            while (r.next()) {    
                modelo_prec_def.addRow(new Object[4]);
                modelo_prec_def.setValueAt(r.getString("ID_CRUCE_PREC"),cruce_precs.getRowCount()-1,0);
                modelo_prec_def.setValueAt(r.getString("F_INICIAL"),cruce_precs.getRowCount()-1,1);
                modelo_prec_def.setValueAt(r.getString("F_FINAL"),cruce_precs.getRowCount()-1,2);
                modelo_prec_def.setValueAt(r.getString("t_cruce_precalculo.F_REGISTRO"),cruce_precs.getRowCount()-1,3);
                r1 = con1.s.executeQuery ("SELECT * FROM `t_cruce_tesoreria` WHERE ID_CRUCE_PREC ="+cruce_precs.getValueAt(cruce_precs.getRowCount()-1,0));
                if (r1.next()) {
                    modelo_prec_def.setValueAt("SI",cruce_precs.getRowCount()-1,4);
                }else{
                    modelo_prec_def.setValueAt("NO",cruce_precs.getRowCount()-1,4);
                }
            }
            con.cerrar();
            con1.cerrar();
        }catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);
            con.cerrar();
            con1.cerrar();
        }
    }
    private void calcular_cruce(){
        float fic_1=0,fic_2=0, total_1=0, total_2=0;
        if (cruce_precs.getSelectedRow()!=-1) {
            if (cruce_precs.getValueAt(cruce_precs.getSelectedRow(), 4).toString().equals("NO")) {
                while(cruce_tesor.getRowCount()!=0){
                    modelo_cruce_prec.removeRow(cruce_tesor.getRowCount()-1);
                }
                Conexion con = new Conexion();
                con.conexion();
                try {
                    ResultSet r;
                    r = con.s.executeQuery ("SELECT * "
                            + "FROM t_det_cruce_precalculo INNER JOIN t_empresas ON (t_det_cruce_precalculo.ID_EMPRESA = t_empresas.ID_EMPRESA) "
                            + "WHERE ID_CRUCE_PREC = "+cruce_precs.getValueAt(cruce_precs.getSelectedRow(), 0)+" "
                            + "ORDER BY t_det_cruce_precalculo.ID_EMPRESA ASC");
                    while (r.next()) {
                        modelo_cruce_prec.addRow(new Object[10]);
                        modelo_cruce_prec.setValueAt(r.getString("ID_EMPRESA"),cruce_tesor.getRowCount()-1,0);
                        modelo_cruce_prec.setValueAt(r.getString("NOMBRE_EMPRESA"),cruce_tesor.getRowCount()-1,1);
                        modelo_cruce_prec.setValueAt(r.getFloat("DIF_FIC"),cruce_tesor.getRowCount()-1,2);
//                        modelo_cruce_prec.setValueAt(new Float("0"),cruce_tesor.getRowCount()-1,3);
//                        modelo_cruce_prec.setValueAt(new Float("0"),cruce_tesor.getRowCount()-1,4);
                        modelo_cruce_prec.setValueAt(r.getFloat("DIF_TOT"),cruce_tesor.getRowCount()-1,5);
//                        modelo_cruce_prec.setValueAt(new Float("0"),cruce_tesor.getRowCount()-1,6);
//                        modelo_cruce_prec.setValueAt(new Float("0"),cruce_tesor.getRowCount()-1,7);
                    }
                    con.cerrar();
                }catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);
                    con.cerrar();
                }
                registros.setText(String.valueOf(cruce_tesor.getRowCount()));
            }else{
                JOptionPane.showMessageDialog(this,"El registro seleccionado no debe tener ningun Cruce generado anteriormente","Error",JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this,"Debe seleccionar un registro en la tabla de Precalculos Definitivos","Error",JOptionPane.ERROR_MESSAGE);
        }
    
    }
    private void calcular_diferencias(){
        boolean test=true;
        for (int i = 0; i < cruce_tesor.getRowCount(); i++) {
            if(comprobarFloat(cruce_tesor.getValueAt(i, 2))){
                test=test&true;
                if(comprobarFloat(cruce_tesor.getValueAt(i, 3))){
                    test=test&true;
                    cruce_tesor.setValueAt(new Float(cruce_tesor.getValueAt(i, 3).toString()) - new Float(cruce_tesor.getValueAt(i, 2).toString()), i, 4);
                   if(comprobarFloat(cruce_tesor.getValueAt(i, 5))){
                        test=test&true;
                        if(comprobarFloat(cruce_tesor.getValueAt(i, 6))){
                            test=test&true;
                            cruce_tesor.setValueAt(new Float(cruce_tesor.getValueAt(i, 6).toString()) - new Float(cruce_tesor.getValueAt(i, 5).toString()), i, 7);
                        }else{
                            test=test&false;
                            cruce_tesor.changeSelection(i,6, false, false);
                            cruce_tesor.requestFocus();
                            JOptionPane.showMessageDialog(this,"Verifique el dato ingresado no coincide con el formato de numero.","Error",JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        if (cruce_tesor.getValueAt(i, 8)==null) {
                                cruce_tesor.setValueAt("",i,8);
                        }
                        if (cruce_tesor.getValueAt(i, 9)==null) {
                            cruce_tesor.setValueAt("",i,9);
                        }
                    }else{
                        test=test&false;
                        cruce_tesor.changeSelection(i,5, false, false);
                        cruce_tesor.requestFocus();
                        JOptionPane.showMessageDialog(this,"Verifique el dato ingresado no coincide con el formato de numero.","Error",JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }else{
                    test=test&false;
                    cruce_tesor.changeSelection(i,3, false, false);
                    cruce_tesor.requestFocus();
                    JOptionPane.showMessageDialog(this,"Verifique el dato ingresado no coincide con el formato de numero.","Error",JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }else{
                test=test&false;
                cruce_tesor.changeSelection(i,2, false, false);
                cruce_tesor.requestFocus();
                JOptionPane.showMessageDialog(this,"Verifique el dato ingresado no coincide con el formato de numero.","Error",JOptionPane.ERROR_MESSAGE);
                break;
            }
        }
        if (test) {
            JOptionPane.showMessageDialog(this,"Se ha realizado el calculo de la diferencia de todos los registros de la tabla.","Error",JOptionPane.ERROR_MESSAGE);
            btn_almacenar.setEnabled(true);
        }else{
            btn_almacenar.setEnabled(false);
        }
    }
    public boolean comprobarFloat (Object cadena){
        try{
            float num = Float.parseFloat(cadena.toString());
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;

    }
    public float getFloat (Object cadena){
        float num = 0;
        try{
            num = Float.parseFloat(cadena.toString());
        }catch (Exception e){
            e.printStackTrace();
            return num;
        }
        return num;

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
