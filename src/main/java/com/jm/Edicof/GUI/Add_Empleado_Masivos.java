/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.GUI;

import com.jm.Edicof.Clases.CellRender_Ingresos_Masivos_Empleados;
import com.jm.Edicof.Clases.Conexion;
import com.jm.Edicof.Clases.PegarExcel_Empleados_Masivo;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author ADMIN
 */
public class Add_Empleado_Masivos extends javax.swing.JDialog {
    DefaultTableModel modelo = null;
    Object [] fila = new Object[5];
    //******************************/////////////
    TextAutoCompleter tac_municipio_table = null;
    TextAutoCompleter tac_tip_ident_table = null;
    TextAutoCompleter tac_tip_sangre_table = null;
    TextAutoCompleter tac_genero_table = null;
    JTextField tb_municipio_table = null;
    JTextField tb_tip_ident_table = null;
    JTextField tb_tip_sangre_table = null;
    JTextField tb_genero_table = null;
    //******************************/////////////
    String before_edit_cell = null;
    /**
     * Creates new form Add_Empleado
     */
    public Add_Empleado_Masivos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        empleados.setDefaultRenderer (Object.class, new CellRender_Ingresos_Masivos_Empleados());
        empleados.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {//e.getType() == TableModelEvent.UPDATE
                    n_registros.setText(String.valueOf(empleados.getRowCount()));
                }
            }
        });
        tb_municipio_table = new JTextField();
        tb_tip_ident_table = new JTextField();
        tb_tip_sangre_table = new JTextField();
        tb_genero_table = new JTextField();
        
        tac_municipio_table = new TextAutoCompleter(tb_municipio_table);
        tac_tip_ident_table = new TextAutoCompleter(tb_tip_ident_table);
        tac_tip_sangre_table = new TextAutoCompleter(tb_tip_sangre_table);
        tac_genero_table = new TextAutoCompleter(tb_genero_table);
        
        tac_municipio();
        tac_tip_ident();
        tac_tip_sangre();
        tac_genero();
        
        tac_municipio_table.setMode(0);
        tac_tip_ident_table.setMode(0);
        tac_tip_sangre_table.setMode(0);
        tac_genero_table.setMode(0);
        
        empleados.getColumnModel().getColumn(0).setCellEditor(new MyTableCellEditorDate(tb_tip_ident_table,true));
        empleados.getColumnModel().getColumn(2).setCellEditor(new MyTableCellEditorDate(tb_municipio_table,true));
        empleados.getColumnModel().getColumn(7).setCellEditor(new MyTableCellEditorDate(tb_tip_sangre_table,true));
        empleados.getColumnModel().getColumn(8).setCellEditor(new MyTableCellEditorDate(tb_genero_table,true));
        empleados.getColumnModel().getColumn(9).setCellEditor(new MyTableCellEditorDate(tb_municipio_table,true));
        empleados.getColumnModel().getColumn(10).setCellEditor(new MyTableCellEditorDate(true));
    }
    class MyTableCellEditorDate extends AbstractCellEditor implements TableCellEditor {
        JComponent component=null;
        boolean par_date=false;
        boolean par_list=false;

        private MyTableCellEditorDate(JTextField tf) {
            component = tf;
        }
        private MyTableCellEditorDate(JTextField tf, boolean list) {
            component = tf;
            par_list = list;
        }
        private MyTableCellEditorDate(boolean date) {
            component = new JTextField();
            par_date = date;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
            int rowIndex, int vColIndex) {
            ((JTextField) component).setText((String) value);
            ((JTextField) component).addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent evt) {
                try {
                    char a=evt.getKeyChar();
                    int b=evt.getKeyCode();
                    System.out.println("Key Released Cell: '"+a+"'("+b+")");
                    if (par_date) {
                        autocomplete_date_released(b,empleados);
                    }
                }catch(Exception ev){
                    ev.printStackTrace();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent evt) {
                char a=evt.getKeyChar();
                int b=evt.getKeyCode();
                //System.out.println("Key Pressed Cell: '"+a+"'("+b+")");
                if (par_date) {
                    autocomplete_date_pressed(b,empleados);
                }
                if((b>=96 & b<=105)|(b>=65 & b<=90)) {
                    if (empleados.getModel().isCellEditable(empleados.getSelectedRow(),empleados.getSelectedColumn())) {
                        modelo = (DefaultTableModel) empleados.getModel();
                        String aux = (String)modelo.getValueAt(empleados.getSelectedRow(), empleados.getSelectedColumn());
                        if (aux!=null) {
                            if (aux.length()>0) {
                                before_edit_cell= aux;
                                modelo.setValueAt("",empleados.getSelectedRow(),empleados.getSelectedColumn());
                                empleados.setModel(modelo);
                                empleados.requestFocus();
                            }
                        }else{
                            before_edit_cell = "";
                        }
                    }
                }else{
                    if(a==KeyEvent.VK_DELETE) {
                        if (empleados.getModel().isCellEditable(empleados.getSelectedRow(),empleados.getSelectedColumn())) {
                            modelo = (DefaultTableModel) empleados.getModel();
                            modelo.setValueAt("",empleados.getSelectedRow(),empleados.getSelectedColumn());
                            empleados.setModel(modelo);
                            before_edit_cell = "";
                            if (empleados.isEditing()){
                                empleados.getCellEditor().stopCellEditing();
                            }
                            evt.consume();
                        }

                    }else{
                        if(a==KeyEvent.VK_ESCAPE) {
                            modelo = (DefaultTableModel) empleados.getModel();
                            String aux = (String)modelo.getValueAt(empleados.getSelectedRow(), empleados.getSelectedColumn());
                            if (aux!=null) {
                                if (empleados.isEditing()){
                                    modelo.setValueAt(before_edit_cell,empleados.getSelectedRow(),empleados.getSelectedColumn());
                                    empleados.setModel(modelo);
                                    empleados.getCellEditor().cancelCellEditing();
                                    evt.setKeyCode(10);
                                }else{
                                    evt.consume();
                                }
                                evt.consume();
                            }else{
                                if (empleados.isEditing()){
                                    empleados.getCellEditor().cancelCellEditing();
                                }
                                evt.consume();
                            }
                        }else{
                            if (a==KeyEvent.VK_TAB) {
                                if (par_list) {
                                    evt.setKeyCode(10);
                                }
                            }
                        }
                    }
                }
            }
            });
            return component;
        }
    
        @Override
        public Object getCellEditorValue() {
            return ((JTextField) component).getText();
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

        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        empleados = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        n_registros = new javax.swing.JLabel();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Empleados");
        setResizable(false);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancel_1.png"))); // NOI18N
        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Información Empleados"));

        new PegarExcel_Empleados_Masivo(empleados);
        empleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tipo Ident*", "Id*", "Ciudad Expedicion Id*", "Nombre 1*", "Nombre 2", "Apellido 1*", "Apellido 2", "Tipo de sangre*", "Género (M-F)*", "Ciudad nacimiento*", "Fecha nacimiento (DD-MM-AAAA)*"
            }
        ));
        empleados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(empleados);
        if (empleados.getColumnModel().getColumnCount() > 0) {
            empleados.getColumnModel().getColumn(0).setMinWidth(80);
            empleados.getColumnModel().getColumn(0).setPreferredWidth(80);
            empleados.getColumnModel().getColumn(0).setMaxWidth(80);
            empleados.getColumnModel().getColumn(1).setMinWidth(100);
            empleados.getColumnModel().getColumn(1).setPreferredWidth(100);
            empleados.getColumnModel().getColumn(1).setMaxWidth(100);
            empleados.getColumnModel().getColumn(2).setMinWidth(180);
            empleados.getColumnModel().getColumn(2).setPreferredWidth(180);
            empleados.getColumnModel().getColumn(2).setMaxWidth(180);
            empleados.getColumnModel().getColumn(3).setMinWidth(180);
            empleados.getColumnModel().getColumn(3).setPreferredWidth(180);
            empleados.getColumnModel().getColumn(3).setMaxWidth(180);
            empleados.getColumnModel().getColumn(4).setMinWidth(180);
            empleados.getColumnModel().getColumn(4).setPreferredWidth(180);
            empleados.getColumnModel().getColumn(4).setMaxWidth(180);
            empleados.getColumnModel().getColumn(5).setMinWidth(180);
            empleados.getColumnModel().getColumn(5).setPreferredWidth(180);
            empleados.getColumnModel().getColumn(5).setMaxWidth(180);
            empleados.getColumnModel().getColumn(6).setMinWidth(180);
            empleados.getColumnModel().getColumn(6).setPreferredWidth(180);
            empleados.getColumnModel().getColumn(6).setMaxWidth(180);
            empleados.getColumnModel().getColumn(7).setMinWidth(100);
            empleados.getColumnModel().getColumn(7).setPreferredWidth(100);
            empleados.getColumnModel().getColumn(7).setMaxWidth(100);
            empleados.getColumnModel().getColumn(8).setMinWidth(100);
            empleados.getColumnModel().getColumn(8).setPreferredWidth(100);
            empleados.getColumnModel().getColumn(8).setMaxWidth(100);
            empleados.getColumnModel().getColumn(9).setMinWidth(150);
            empleados.getColumnModel().getColumn(9).setPreferredWidth(150);
            empleados.getColumnModel().getColumn(9).setMaxWidth(150);
            empleados.getColumnModel().getColumn(10).setMinWidth(100);
            empleados.getColumnModel().getColumn(10).setPreferredWidth(100);
            empleados.getColumnModel().getColumn(10).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Herramientas"));
        jPanel3.setPreferredSize(new java.awt.Dimension(600, 112));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_row_delete.png"))); // NOI18N
        jButton4.setText("Eliminar Fila");
        jButton4.setToolTipText("Eliminar Fila seleccionada");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_refresh.png"))); // NOI18N
        jButton5.setText("Limpiar tabla");
        jButton5.setToolTipText("Eliminar todas las Filas");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_row_insert.png"))); // NOI18N
        jButton6.setText("Insertar Fila");
        jButton6.setToolTipText("Insertar Fila");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));
        jPanel4.setPreferredSize(new java.awt.Dimension(600, 112));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_error.png"))); // NOI18N
        jButton7.setText("Verificar Datos");
        jButton7.setToolTipText("Verificar Datos de la tabla");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_save.png"))); // NOI18N
        jButton8.setText("Guardar Datos");
        jButton8.setToolTipText("Ingresar Información");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton8)
                    .addComponent(jButton7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Registros"));

        jLabel6.setText("Numero de Registros");

        n_registros.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        n_registros.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n_registros.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addGap(58, 58, 58))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(n_registros, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(n_registros)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (empleados.getRowCount()>0) {
            if (empleados.getSelectedRow()>-1) {
                modelo = (DefaultTableModel) empleados.getModel();
                modelo.removeRow(empleados.getSelectedRow());
                empleados.setModel(modelo);
            }else{
                JOptionPane.showMessageDialog(this,"No se ha seleccionado ningun registro","Error",JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this,"La tabla no contiene registros","Error",JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        modelo = (DefaultTableModel) empleados.getModel();
        if (empleados.getRowCount()>0) {
            //System.out.println("Filas: "+jTable1.getRowCount());
            int j = empleados.getRowCount();
            for (int i = 0; i < j; i++) {
                //System.out.println("q");
                modelo.removeRow(empleados.getRowCount()-1);
                empleados.setModel(modelo);
            }
        }else{
            JOptionPane.showMessageDialog(this,"La tabla no contiene registros","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        modelo = (DefaultTableModel) empleados.getModel();
        modelo.insertRow(empleados.getSelectedRow()+1, fila);
        empleados.setModel(modelo);

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        if(verify_data()){
            JOptionPane.showMessageDialog(this,"La informacion de la tabla esta completa","Información",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        boolean confirm = true;
        if(verify_data()){
            int conf = JOptionPane.showConfirmDialog(this,"La informacion de la tabla esta completa\nEsta seguro que desea continuar?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (conf == JOptionPane.YES_OPTION) {
                modelo = (DefaultTableModel)empleados.getModel();
                empleados.setModel(modelo);
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                for (int i = 0; i < empleados.getRowCount(); i++) {
                    try {
                        if (!get_id_tip_ident(modelo.getValueAt(i, 0)).equals("")) {
                            if (!get_id_municipio(modelo.getValueAt(i, 2)).equals("")) {
                                if (!get_id_tip_sangre(modelo.getValueAt(i, 7)).equals("")) {
                                    if (!get_id_genero(modelo.getValueAt(i, 8)).equals("")) {
                                        if (!get_id_municipio(modelo.getValueAt(i, 9)).equals("")) {
                                            r = con.s.executeQuery ("SELECT * FROM `t_empleados` WHERE ID_EMP = "+modelo.getValueAt(i, 1).toString());
                                            if(r.next()){
                                                JOptionPane.showMessageDialog(this,"El empleado que intenta ingresar ya existe","Error",JOptionPane.ERROR_MESSAGE);
                                                empleados.changeSelection(i,0, false, false);
                                                empleados.requestFocus();
                                                confirm=false;
                                                break;
                                            } else{
                                                String nomb2,ape2="";
                                                if (modelo.getValueAt(i, 4)==null) {
                                                    nomb2="";
                                                }else{
                                                    nomb2=modelo.getValueAt(i, 4).toString().trim().toUpperCase();
                                                }
                                                if (modelo.getValueAt(i, 6)==null) {
                                                    ape2="";
                                                }else{
                                                    ape2=modelo.getValueAt(i, 6).toString().trim().toUpperCase();
                                                }
                                                con.s.executeUpdate("INSERT INTO `t_empleados`(`ID_EMP`, `NOMBRE_1_EMP`, `NOMBRE_2_EMP`, `APELLIDO_1_EMP`, `APELLIDO_2_EMP`, `ID_TIPO_IDENT`, `ID_MUN_EXPEDICION`, `ID_TIPO_SANGRE`, `ID_TIPO_GENERO`, `ID_MUN_NACIMIENTO`, `FECHA_NAC`) VALUES ("+modelo.getValueAt(i, 1).toString().trim()+",'"+modelo.getValueAt(i, 3).toString().toUpperCase().trim()+"','"+nomb2+"','"+modelo.getValueAt(i, 5).toString().toUpperCase().trim()+"','"+ape2+"',"+get_id_tip_ident(modelo.getValueAt(i, 0))+","+get_id_municipio(modelo.getValueAt(i, 2))+","+get_id_tip_sangre(modelo.getValueAt(i, 7))+","+get_id_genero(modelo.getValueAt(i, 8))+","+get_id_municipio(modelo.getValueAt(i, 9))+",'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, 10).toString()))+"')");
                                                modelo.removeRow(i);
                                                i=i-1;
                                                confirm = confirm & true;
                                            }
                                        } else {
                                            empleados.changeSelection(i,9, false, false);
                                            empleados.requestFocus();
                                            JOptionPane.showMessageDialog(this,"Verifique el lugar de nacimiento del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                            break;
                                        }
                                    } else {
                                        empleados.changeSelection(i,8, false, false);
                                        empleados.requestFocus();
                                        JOptionPane.showMessageDialog(this,"Verifique el genero del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                        break;
                                    }
                                } else {
                                    empleados.changeSelection(i,7, false, false);
                                    empleados.requestFocus();
                                    JOptionPane.showMessageDialog(this,"Verifique el tipo de sangre del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                    break;
                                }
                            } else {
                                empleados.changeSelection(i,2, false, false);
                                empleados.requestFocus();
                                JOptionPane.showMessageDialog(this,"Verifique la ciudad de expedición del Id del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                break;
                            }
                            
                        } else {
                            empleados.changeSelection(i,0, false, false);
                            empleados.requestFocus();
                            JOptionPane.showMessageDialog(this,"Verifique el tipo de Id del empleado","Error",JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        
                        
                        
                        
                        
                    } catch (Exception ex) {
                        con.cerrar();
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null,ex,"Error",JOptionPane.ERROR_MESSAGE);
                        confirm=false;
                        break;
                    }
                }
                con.cerrar();
                if (confirm) {
                    JOptionPane.showMessageDialog(this,"La información ha sido ingresada correctamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(Add_Empleado_Masivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add_Empleado_Masivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add_Empleado_Masivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add_Empleado_Masivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Add_Empleado_Masivos dialog = new Add_Empleado_Masivos(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable empleados;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    public javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel n_registros;
    // End of variables declaration//GEN-END:variables
    public boolean verify_data(){
//        Conexion con = new Conexion();
//        con.conexion();
//        ResultSet r;
        boolean ret=true;
        modelo = (DefaultTableModel)empleados.getModel(); 
        if (empleados.getRowCount()>0) {
            for (int i = 0; i < empleados.getRowCount(); i++) {
                if (check_tip_ident(modelo.getValueAt(i, 0))) {
                    if (check_cedula(modelo.getValueAt(i, 1))) {
                        if (check_municipio(modelo.getValueAt(i, 2))) {
                            if (check_field(modelo.getValueAt(i, 3))){
                                if (check_char(modelo.getValueAt(i, 4),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
                                    if (check_field(modelo.getValueAt(i, 5))){
                                        if (check_char(modelo.getValueAt(i, 6),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
                                            if (check_tip_sangre(modelo.getValueAt(i, 7))) {
                                                if (check_genero(modelo.getValueAt(i, 8))) {
                                                    if (check_municipio(modelo.getValueAt(i, 9))) {
                                                        if (check_fecha(modelo.getValueAt(i, 10))) {
                                                            ret=true&ret;
                                                        } else {
                                                            empleados.changeSelection(i,10, false, false);
                                                            empleados.requestFocus();
                                                            JOptionPane.showMessageDialog(this,"Verifique la fecha de nacimiento del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                            ret=false&ret;
                                                            break;
                                                        }
                                                        
                                                    } else {
                                                        empleados.changeSelection(i,9, false, false);
                                                        empleados.requestFocus();
                                                        JOptionPane.showMessageDialog(this,"Verifique la ciudad de nacimiento del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                        ret=false&ret;
                                                        break;
                                                    }
                                                } else {
                                                    empleados.changeSelection(i,8, false, false);
                                                    empleados.requestFocus();
                                                    JOptionPane.showMessageDialog(this,"Verifique el genero del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                    ret=false&ret;
                                                    break;
                                                }
                                            } else {
                                                empleados.changeSelection(i,7, false, false);
                                                empleados.requestFocus();
                                                JOptionPane.showMessageDialog(this,"Verifique el tipo de sangre del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                ret=false&ret;
                                                break;
                                            }
                                        }else {
                                            empleados.changeSelection(i,5, false, false);
                                            empleados.requestFocus();
                                            JOptionPane.showMessageDialog(this,"Verifique que el segundo apellido del empleado no tenga caracteres especiales","Error",JOptionPane.ERROR_MESSAGE);
                                            ret=false&ret;
                                            break;
                                        }
                                    } else {
                                        empleados.changeSelection(i,4, false, false);
                                        empleados.requestFocus();
                                        JOptionPane.showMessageDialog(this,"Verifique el primer apellido del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                        ret=false&ret;
                                        break;
                                    }
                                } else {
                                    empleados.changeSelection(i,3, false, false);
                                    empleados.requestFocus();
                                    JOptionPane.showMessageDialog(this,"Verifique que el segundo nombre del empleado no tenga caracteres especiales","Error",JOptionPane.ERROR_MESSAGE);
                                    ret=false&ret;
                                    break;
                                }
                            } else {
                                empleados.changeSelection(i,2, false, false);
                                empleados.requestFocus();
                                JOptionPane.showMessageDialog(this,"Verifique el primer nombre del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                ret=false&ret;
                                break;
                            }
                        } else {
                            empleados.changeSelection(i,2, false, false);
                            empleados.requestFocus();
                            JOptionPane.showMessageDialog(this,"Verifique la ciudad de expedicion del Id del empleado","Error",JOptionPane.ERROR_MESSAGE);
                            ret=false&ret;
                            break;
                        }
                        
                    } else {
                        empleados.changeSelection(i,1, false, false);
                        empleados.requestFocus();
                        JOptionPane.showMessageDialog(this,"Verifique la Cedula del empleado","Error",JOptionPane.ERROR_MESSAGE);
                        ret=false&ret;
                        break;
                    }
                }else{
                    empleados.changeSelection(i,0, false, false);
                    empleados.requestFocus();
                    JOptionPane.showMessageDialog(this,"Verifique el tipo de identificacion del empleado","Error",JOptionPane.ERROR_MESSAGE);
                    ret=false&ret;
                    break;
                }
                
            }
        }else{
            JOptionPane.showMessageDialog(this,"La tabla no tiene registros","Error",JOptionPane.ERROR_MESSAGE);
            ret=false&ret;
        }
    return ret;
    }
    public boolean check_cedula(Object ced){
        boolean ret=false;
        if (ced!=null) {
            if (check_char(ced.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
                if (!ced.toString().equals("")) {
                    ret=checkLong(ced.toString().trim());
                }
            }
        }
        return ret;  
    }
    public boolean check_char(Object s, String c){
    //boolean ret=false;
        if (s!=null) {
            char []char_s=s.toString().toCharArray();
            char []char_c=c.toCharArray();  
            for (int i = 0; i < char_s.length; i++) {
                for (int j = 0; j < char_c.length; j++) {
                    if (char_s[i]==char_c[j]) {
                        return false;
                    }
                }
            }
            return true;
        }else{
            return true;
        }
    }
    public static boolean checkLong (String cadena){
        try{
            long num = Long.parseLong(cadena);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;

        }
    public boolean check_field (Object field){
        boolean ret=false;
            if (field!=null) {
                if (check_char(field.toString().trim(),"'$%&()=?¡¿/*+[]{};:<>,")) {
                    if (!field.toString().equals("")) {
                       ret=true;
                    }
                }
            }

        return ret;
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
    public boolean check_municipio(Object municipio){
    boolean ret=false;
    if (municipio!=null) {
        if (check_char(municipio.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!municipio.toString().trim().equals("")& count_char(municipio.toString().trim(),'-')==1){
                String str_mun="";
                String str_dep="";
                StringTokenizer tokens=new StringTokenizer(municipio.toString().trim(), "-");
                while(tokens.hasMoreTokens()){
                    str_mun=tokens.nextToken().trim();
                    str_dep=tokens.nextToken().trim();
                }
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT *\n" +
                                            "FROM\n" +
                                            "    t_municipios\n" +
                                            "    INNER JOIN t_departamentos \n" +
                                            "        ON (t_municipios.ID_DEP = t_departamentos.ID_DEP) WHERE NOMBRE_MUN='"+str_mun+"' AND NOMBRE_DEP='"+str_dep+"'");
                    if(r.next()){
                        ret=true;
                    }
                    con.cerrar();
                }catch(SQLException j){
                    con.cerrar();
                    j.printStackTrace();
                    JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    return ret;
    }
    public boolean check_tip_ident(Object tipo){
    boolean ret=false;
    if (tipo!=null) {
        if (check_char(tipo.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            Conexion con = new Conexion();
            con.conexion();
            ResultSet r;
            try{
                r = con.s.executeQuery ("SELECT *\n" +
                                        "FROM\n" +
                                        "    t_tipo_ident\n" +
                                        "where t_tipo_ident.NOMBRE_TIPO_IDENT ='"+tipo+"'");
                if(r.next()){
                    ret=true;
                }
                con.cerrar();
            }catch(SQLException j){
                con.cerrar();
                j.printStackTrace();
                JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    return ret;
    }
    public boolean check_tip_sangre(Object tipo){
    boolean ret=false;
    if (tipo!=null) {
        if (check_char(tipo.toString().trim(),"'#$%&()=?¡¿/*[]{};:<>,.")) {
            Conexion con = new Conexion();
            con.conexion();
            ResultSet r;
            try{
                r = con.s.executeQuery ("SELECT *\n" +
                                        "FROM\n" +
                                        "    t_tipo_sangre\n" +
                                        "where t_tipo_sangre.NOMBRE_TIPO ='"+tipo+"'");
                if(r.next()){
                    ret=true;
                }
                con.cerrar();
            }catch(SQLException j){
                con.cerrar();
                j.printStackTrace();
                JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    return ret;
    }
    public boolean check_genero(Object genero){
    boolean ret=false;
    if (genero!=null) {
        if (check_char(genero.toString().trim(),"'#$%&()=?¡¿/*+-[]{};:<>,.")) {
            Conexion con = new Conexion();
            con.conexion();
            ResultSet r;
            try{
                r = con.s.executeQuery ("SELECT *\n" +
                                        "FROM\n" +
                                        "    t_genero\n" +
                                        "where t_genero.NOMBRE_GENERO ='"+genero+"'");
                if(r.next()){
                    ret=true;
                }
                con.cerrar();
            }catch(SQLException j){
                con.cerrar();
                j.printStackTrace();
                JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    return ret;
    }
    public boolean check_fecha(Object fecha){
    boolean ret=false;
    if (fecha!=null) {
        if (check_char(fecha.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!fecha.toString().trim().equals("") & count_char(fecha.toString().trim(),'-')==2) {
                int str_año=0;
                int str_mes=0;
                int str_dia=0;
                try {
                    StringTokenizer tokens=new StringTokenizer(fecha.toString().trim(),"-");
                    while(tokens.hasMoreTokens()){
                        str_dia=Integer.parseInt(tokens.nextToken().trim());
                        str_mes=Integer.parseInt(tokens.nextToken().trim());
                        str_año=Integer.parseInt(tokens.nextToken().trim());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                if (str_dia>0 & str_dia<=31) {
                    if (str_mes>0 & str_mes<=12) {
                        if (str_año>=1900 & str_año<=2050) {
                            Calendar ahoraCal = Calendar.getInstance();
                            ahoraCal.set(str_año,str_mes-1,1);
                            ahoraCal.set(Calendar.DATE, ahoraCal.getActualMaximum(Calendar.DATE));
                            int f = ahoraCal.get(Calendar.DAY_OF_MONTH);
                            if (str_dia<=f) {
                                ret=true;
                            }
                        }
                    }
                }
            }
        }
    }
    return ret;
}
    private void tac_municipio(){
        tac_municipio_table.removeAllItems();
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
        try{
            r = con.s.executeQuery ("SELECT *\n" +
                                    "FROM\n" +
                                    "    t_municipios\n" +
                                    "    INNER JOIN t_departamentos \n" +
                                    "        ON (t_municipios.ID_DEP = t_departamentos.ID_DEP) ORDER BY NOMBRE_MUN ASC;");
            while(r.next()){
                String str=r.getString("NOMBRE_MUN");
                String str1=r.getString("NOMBRE_DEP");
                tac_municipio_table.addItem(str+"-"+str1);
            }
            con.cerrar();
        }catch(SQLException j){
            con.cerrar();
            j.printStackTrace();
            JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    private void tac_tip_ident(){
        tac_tip_ident_table.removeAllItems();
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
        try{
            r = con.s.executeQuery ("SELECT *\n" +
                                    "FROM\n" +
                                    "    t_tipo_ident\n" +
                                    "ORDER BY t_tipo_ident.NOMBRE_TIPO_IDENT  ASC ");
            while(r.next()){
                String str=r.getString("NOMBRE_TIPO_IDENT");
                tac_tip_ident_table.addItem(str);
            }
            con.cerrar();
        }catch(SQLException j){
            con.cerrar();
            j.printStackTrace();
            JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    private void tac_tip_sangre(){
        tac_tip_sangre_table.removeAllItems();
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
        try{
            r = con.s.executeQuery ("SELECT *\n" +
                                    "FROM\n" +
                                    "    t_tipo_sangre\n" +
                                    "order by t_tipo_sangre.NOMBRE_TIPO asc ");
            while(r.next()){
                String str=r.getString("NOMBRE_TIPO");
                tac_tip_sangre_table.addItem(str);
            }
            con.cerrar();
        }catch(SQLException j){
            con.cerrar();
            j.printStackTrace();
            JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    private void tac_genero(){
        tac_genero_table.removeAllItems();
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
        try{
            r = con.s.executeQuery ("SELECT *\n" +
                                    "FROM\n" +
                                    "    t_genero\n" +
                                    "order by t_genero.NOMBRE_GENERO asc");
            while(r.next()){
                String str=r.getString("NOMBRE_GENERO");
                tac_genero_table.addItem(str);
            }
            System.out.println(tac_genero_table);
            con.cerrar();
        }catch(SQLException j){
            con.cerrar();
            j.printStackTrace();
            JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public String get_id_municipio(Object municipio){
        String i = "";
        String str_mun="";
        String str_dep="";
        StringTokenizer tokens=new StringTokenizer(municipio.toString().trim(), "-");
        while(tokens.hasMoreTokens()){
            str_mun=tokens.nextToken().trim();
            str_dep=tokens.nextToken().trim();
        }
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
        try{
            r = con.s.executeQuery ("SELECT *\n" +
                                    "FROM\n" +
                                    "    t_municipios\n" +
                                    "    INNER JOIN t_departamentos \n" +
                                    "        ON (t_municipios.ID_DEP = t_departamentos.ID_DEP) WHERE NOMBRE_MUN='"+str_mun+"' AND NOMBRE_DEP='"+str_dep+"'");
            if(r.next()){
                i = r.getString("ID_MUN");
            }else{
                i="";
            }
            con.cerrar();
        }catch(SQLException j){
            i="";
            con.cerrar();
            j.printStackTrace();
        }
        return i;
    }
    public String get_id_tip_ident(Object tip_ident){
        String i = "";
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
        try{
            r = con.s.executeQuery ("SELECT *\n" +
                                    "FROM\n" +
                                    "    t_tipo_ident\n" +
                                    "WHERE t_tipo_ident.NOMBRE_TIPO_IDENT = '"+tip_ident.toString()+"'");
            if(r.next()){
                i = r.getString("ID_TIPO_IDENT");
            }else{
                i="";
            }
            con.cerrar();
        }catch(SQLException j){
            i="";
            con.cerrar();
            j.printStackTrace();
        }
        return i;
    }
    public String get_id_tip_sangre(Object tip_sangre){
        String i = "";
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
        try{
            r = con.s.executeQuery ("SELECT *\n" +
                                    "FROM\n" +
                                    "    t_tipo_sangre\n" +
                                    "WHERE t_tipo_sangre.NOMBRE_TIPO = '"+tip_sangre.toString()+"'");
            if(r.next()){
                i = r.getString("ID_TIPO");
            }else{
                i="";
            }
            con.cerrar();
        }catch(SQLException j){
            i="";
            con.cerrar();
            j.printStackTrace();
        }
        return i;
    }
    public String get_id_genero(Object genero){
        String i = "";
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
        try{
            r = con.s.executeQuery ("SELECT *\n" +
                                    "FROM\n" +
                                    "    t_genero\n" +
                                    "WHERE t_genero.NOMBRE_GENERO = '"+genero.toString()+"'");
            if(r.next()){
                i = r.getString("ID_GENERO");
            }else{
                i="";
            }
            con.cerrar();
        }catch(SQLException j){
            i="";
            con.cerrar();
            j.printStackTrace();
        }
        return i;
    }
    public void autocomplete_date_released(int b, JTable jTable1) {
        Calendar c = new GregorianCalendar();
        String annio = Integer.toString(c.get(Calendar.YEAR));
        switch (((JTextField) jTable1.getEditorComponent()).getText().length()) {
            case 2:
                if (b!=KeyEvent.VK_BACK_SPACE) {
                    ((JTextField) jTable1.getEditorComponent()).setText(((JTextField) jTable1.getEditorComponent()).getText() + "-");
                }
                break;
            case 5:
                if (b!=KeyEvent.VK_BACK_SPACE) {
                    ((JTextField) jTable1.getEditorComponent()).setText(((JTextField) jTable1.getEditorComponent()).getText() + "-");
                }else{
                    if (b==KeyEvent.VK_ENTER) {
                        ((JTextField) jTable1.getEditorComponent()).setText(((JTextField) jTable1.getEditorComponent()).getText() + "-"+annio);
                    }
                }
                break;
            default:
                break;
        }
    }
    public void autocomplete_date_pressed(int b, JTable jTable1){
    Calendar c = new GregorianCalendar();
    String annio = Integer.toString(c.get(Calendar.YEAR));
    switch (((JTextField) jTable1.getEditorComponent()).getText().length()) {
        case 5:
            if (b!=KeyEvent.VK_BACK_SPACE) {
                    ((JTextField) jTable1.getEditorComponent()).setText(((JTextField) jTable1.getEditorComponent()).getText() + "-");
                }else{
                    if (b==KeyEvent.VK_ENTER) {
                    ((JTextField) jTable1.getEditorComponent()).setText(((JTextField) jTable1.getEditorComponent()).getText() + "-"+annio);
                    }
                }
            break;
        case 6:
            if (KeyEvent.VK_ENTER == b) {
                ((JTextField) jTable1.getEditorComponent()).setText(((JTextField) jTable1.getEditorComponent()).getText() + annio);
                int str_año=0;
                int str_mes=0;
                int str_dia=0;
                try {
                    StringTokenizer tokens=new StringTokenizer(((JTextField) jTable1.getEditorComponent()).getText().trim(),"-");
                    while(tokens.hasMoreTokens()){
                        str_dia=Integer.parseInt(tokens.nextToken().trim());
                        str_mes=Integer.parseInt(tokens.nextToken().trim());
                        str_año=Integer.parseInt(tokens.nextToken().trim());
                    }
                    if (str_dia>0 & str_dia<=31) {
                        if (str_mes>0 & str_mes<=12) {
                            if (str_año>=1900 & str_año<=2050) {
                                Calendar ahoraCal = Calendar.getInstance();
                                ahoraCal.set(str_año,str_mes-1,1);
                                ahoraCal.set(Calendar.DATE, ahoraCal.getActualMaximum(Calendar.DATE));
                                int f = ahoraCal.get(Calendar.DAY_OF_MONTH);
                                if (str_dia>f) {
                                    ((JTextField) jTable1.getEditorComponent()).setText("");
                                }
                                //System.out.println(ahoraCal.getTime());
                            }else{
                                ((JTextField) jTable1.getEditorComponent()).setText("");
                            }
                        }else{
                            ((JTextField) jTable1.getEditorComponent()).setText("");
                        }
                    }else{
                        ((JTextField) jTable1.getEditorComponent()).setText("");
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                    ((JTextField) jTable1.getEditorComponent()).setText("");
                }
            }   break;
        case 10:
            System.out.println("test");
            if (KeyEvent.VK_ENTER == b) {
                int str_año=0;
                int str_mes=0;
                int str_dia=0;
                try {
                    StringTokenizer tokens=new StringTokenizer(((JTextField) jTable1.getEditorComponent()).getText().trim(),"-");
                    while(tokens.hasMoreTokens()){
                        str_dia=Integer.parseInt(tokens.nextToken().trim());
                        str_mes=Integer.parseInt(tokens.nextToken().trim());
                        str_año=Integer.parseInt(tokens.nextToken().trim());
                    }
                    if (str_dia>0 & str_dia<=31) {
                        if (str_mes>0 & str_mes<=12) {
                            if (str_año>=1900 & str_año<=2050) {
                                Calendar ahoraCal = Calendar.getInstance();
                                ahoraCal.set(str_año,str_mes-1,1);
                                ahoraCal.set(Calendar.DATE, ahoraCal.getActualMaximum(Calendar.DATE));
                                int f = ahoraCal.get(Calendar.DAY_OF_MONTH);
                                if (str_dia>f) {
                                    ((JTextField) jTable1.getEditorComponent()).setText("");
                                }
                                //System.out.println(ahoraCal.getTime());
                            }else{
                                ((JTextField) jTable1.getEditorComponent()).setText("");
                            }
                        }else{
                            ((JTextField) jTable1.getEditorComponent()).setText("");
                        }
                    }else{
                        ((JTextField) jTable1.getEditorComponent()).setText("");
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                    ((JTextField) jTable1.getEditorComponent()).setText("");
                }
                
            }   break;
        default:
            break;
    }
}
}
