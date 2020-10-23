/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.GUI;

import com.jm.Edicof.Clases.CellRender_Info_Sociodemografica;
import com.jm.Edicof.Clases.ClipBoardActionListener;
import com.jm.Edicof.Clases.Conexion;
import com.jm.Edicof.Clases.PegarExcel;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
public class Add_Info_Transporte extends javax.swing.JDialog {
    static Dimension screenSize = null;
    DefaultTableModel modelo = null;
    Object [] fila = new Object[16];
    boolean perm_cedula = true;
    boolean perm_empleador = true;
    boolean perm_tabla = false;
    //////////////////////----------------------------
    JTextField tb_cedula_table = new JTextField();
    JTextField tb_tipo_vehiculo = new JTextField();

    TextAutoCompleter tac_cedula_table = new TextAutoCompleter(tb_cedula_table);
    TextAutoCompleter tac_tip_vehic_table = new TextAutoCompleter(tb_tipo_vehiculo);

/////////////////////////----------------------------------
    String before_edit_cell = null;
    //static DefaultTableModel modelo = new DefaultTableModel();
    /**
     * Creates new form Add_Preingresos
     */
    public Add_Info_Transporte(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        //////////////////----------------------------------------
        vehiculos.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {//e.getType() == TableModelEvent.UPDATE
                    int fila = e.getFirstRow();
                    int columna = e.getColumn();
                    if (columna == 1) {
                        
                        //check_empleado();
                    }
                    
                }
                //System.out.println(e);
            }
        });
        //////////////////----------------------------------------
        vehiculos.setDefaultRenderer (Object.class, new CellRender_Info_Sociodemografica());
        //////////////////----------------------------------------
        screenSize = Main.getsize();
        //this.setSize(screenSize.width, screenSize.height-10);
        this.setLocationRelativeTo(null);
        /////////////////-----------------------------------------
        tac_cedula();
        tac_tipo_vehic();
        //////////////---------------------------------------
        tac_cedula_table.setMode(0);
        tac_tip_vehic_table.setMode(0);
        /////////////////-----------------------------------------
        vehiculos.getColumnModel().getColumn(0).setCellEditor(new MyTableCellEditorDate(tb_cedula_table,true));
        vehiculos.getColumnModel().getColumn(1).setCellEditor(new MyTableCellEditorDate(tb_tipo_vehiculo,true));
        perm_tabla=true;
        
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
                //System.out.println("Key Released Cell: '"+a+"'("+b+")");
                if (par_date) {
                    //autocomplete_date_released(b);
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
                //autocomplete_date_pressed(b);
            }
            if((b>=96 & b<=105)|(b>=65 & b<=90)) {
                if (vehiculos.getModel().isCellEditable(vehiculos.getSelectedRow(),vehiculos.getSelectedColumn())) {
                    modelo = (DefaultTableModel) vehiculos.getModel();
                    String aux = (String)modelo.getValueAt(vehiculos.getSelectedRow(), vehiculos.getSelectedColumn());
                    if (aux!=null) {
                        if (aux.length()>0) {
                            before_edit_cell= aux;
                            modelo.setValueAt("",vehiculos.getSelectedRow(),vehiculos.getSelectedColumn());
                            vehiculos.setModel(modelo);
                            vehiculos.requestFocus();
                        }
                    }else{
                        before_edit_cell = "";
                    }
                }
            }else{
                if(a==KeyEvent.VK_DELETE) {
                    if (vehiculos.getModel().isCellEditable(vehiculos.getSelectedRow(),vehiculos.getSelectedColumn())) {
                        modelo = (DefaultTableModel) vehiculos.getModel();
                        modelo.setValueAt("",vehiculos.getSelectedRow(),vehiculos.getSelectedColumn());
                        vehiculos.setModel(modelo);
                        before_edit_cell = "";
                        if (vehiculos.isEditing()){
                            vehiculos.getCellEditor().stopCellEditing();
                        }
                        evt.consume();
                    }

                }else{
                    if(a==KeyEvent.VK_ESCAPE) {
                        modelo = (DefaultTableModel) vehiculos.getModel();
                        String aux = (String)modelo.getValueAt(vehiculos.getSelectedRow(), vehiculos.getSelectedColumn());
                        if (aux!=null) {
                            if (vehiculos.isEditing()){
                                modelo.setValueAt(before_edit_cell,vehiculos.getSelectedRow(),vehiculos.getSelectedColumn());
                                vehiculos.setModel(modelo);
                                vehiculos.getCellEditor().cancelCellEditing();
                                evt.setKeyCode(10);
                            }else{
                                evt.consume();
                            }
                            evt.consume();
                        }else{
                            if (vehiculos.isEditing()){
                                vehiculos.getCellEditor().cancelCellEditing();
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        add_row = new javax.swing.JMenuItem();
        del_row = new javax.swing.JMenuItem();
        del_table = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        vehiculos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        add_row.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_row_insert.png"))); // NOI18N
        add_row.setText("Insertar Fila");
        add_row.setToolTipText("Insertar Fila");
        add_row.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_rowActionPerformed(evt);
            }
        });
        jPopupMenu1.add(add_row);

        del_row.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_row_delete.png"))); // NOI18N
        del_row.setText("Eliminar Fila");
        del_row.setToolTipText("Eliminar Fila seleccionada");
        del_row.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                del_rowActionPerformed(evt);
            }
        });
        jPopupMenu1.add(del_row);

        del_table.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_refresh.png"))); // NOI18N
        del_table.setText("Limpiar Tabla");
        del_table.setToolTipText("Eliminar todas las Filas");
        del_table.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                del_tableActionPerformed(evt);
            }
        });
        jPopupMenu1.add(del_table);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Agregar Informacion Medio de Transporte");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar"));
        jPanel2.setToolTipText("");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_row_delete.png"))); // NOI18N
        jButton3.setText("Eliminar Fila");
        jButton3.setToolTipText("Eliminar Fila seleccionada");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_row_insert.png"))); // NOI18N
        jButton5.setText("Insertar Fila");
        jButton5.setToolTipText("Insertar Fila");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_delete.png"))); // NOI18N
        jButton4.setText("Limpiar tabla");
        jButton4.setToolTipText("Eliminar todas las Filas");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));
        jPanel4.setPreferredSize(new java.awt.Dimension(600, 112));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_error.png"))); // NOI18N
        jButton2.setText("Verificar Datos");
        jButton2.setToolTipText("Verificar Datos de la tabla");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_save.png"))); // NOI18N
        jButton6.setText("Guardar Datos");
        jButton6.setToolTipText("Ingresar Información");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Información de pre-ingresos"));

        new ClipBoardActionListener(vehiculos);
        vehiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Id*", "Tipo de Vehiculo*", "Placa*"
            }
        ));
        vehiculos.setToolTipText("");
        vehiculos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        vehiculos.setColumnSelectionAllowed(false);
        vehiculos.setComponentPopupMenu(jPopupMenu1);
        vehiculos.getTableHeader().setReorderingAllowed(false);
        vehiculos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                vehiculosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                vehiculosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(vehiculos);
        if (vehiculos.getColumnModel().getColumnCount() > 0) {
            vehiculos.getColumnModel().getColumn(0).setMinWidth(90);
            vehiculos.getColumnModel().getColumn(0).setPreferredWidth(90);
            vehiculos.getColumnModel().getColumn(0).setMaxWidth(90);
            vehiculos.getColumnModel().getColumn(1).setMinWidth(250);
            vehiculos.getColumnModel().getColumn(1).setPreferredWidth(250);
            vehiculos.getColumnModel().getColumn(1).setMaxWidth(250);
            vehiculos.getColumnModel().getColumn(2).setMinWidth(150);
            vehiculos.getColumnModel().getColumn(2).setPreferredWidth(150);
            vehiculos.getColumnModel().getColumn(2).setMaxWidth(150);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancel_1.png"))); // NOI18N
        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void add_rowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_rowActionPerformed
        // TODO add your handling code here:
        modelo = (DefaultTableModel) vehiculos.getModel();
        modelo.insertRow(vehiculos.getSelectedRow()+1, fila);
        vehiculos.setModel(modelo);
    }//GEN-LAST:event_add_rowActionPerformed

    private void del_rowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_del_rowActionPerformed
        // TODO add your handling code here:
        if (vehiculos.getRowCount()>0) {
            if (vehiculos.getSelectedRow()>-1) {
                modelo = (DefaultTableModel) vehiculos.getModel();
                modelo.removeRow(vehiculos.getSelectedRow());
                vehiculos.setModel(modelo);
            }else{
                JOptionPane.showMessageDialog(this,"No se ha seleccionado ningun registro","Error",JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this,"La tabla no contiene registros","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_del_rowActionPerformed

    private void del_tableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_del_tableActionPerformed
        // TODO add your handling code here:
        modelo = (DefaultTableModel) vehiculos.getModel();
        if (vehiculos.getRowCount()>0) {
            //System.out.println("Filas: "+jTable1.getRowCount());
            int j = vehiculos.getRowCount();
            for (int i = 0; i < j; i++) {
                //System.out.println("q");
                modelo.removeRow(vehiculos.getRowCount()-1);
                vehiculos.setModel(modelo);
            }
        }else{
            JOptionPane.showMessageDialog(this,"La tabla no contiene registros","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_del_tableActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        perm_tabla=false;
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void vehiculosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vehiculosKeyReleased

        try {
            char a=evt.getKeyChar();
            int b=evt.getKeyCode();
            System.out.println("Key Released Tabla: '"+a+"'("+b+")");
            //System.out.println("Longitud: "+((JTextField) jTable1.getEditorComponent()).getText().length());
            if (vehiculos.getEditingColumn()==2 | vehiculos.getEditingColumn()==4 |vehiculos.getEditingColumn()==5) {
                //autocomplete_date_released(b);
            }
        } catch (UnsupportedOperationException uop) {
            System.out.println(uop.getMessage());
        }catch(Exception ev){
            ev.printStackTrace();
        }
    }//GEN-LAST:event_vehiculosKeyReleased

    private void vehiculosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vehiculosKeyPressed
        // TODO add your handling code here:
        char a=evt.getKeyChar();
        int b=evt.getKeyCode();
        System.out.println("Key Pressed Tabla: '"+a+"'("+b+")");
        if (vehiculos.getEditingColumn()==2|vehiculos.getEditingColumn()==4|vehiculos.getEditingColumn()==5) {
            //autocomplete_date_pressed(b);
        }
        if((b>=96 & b<=105)|(b>=65 & b<=90)) {
            //        System.out.println("alpha");
            if (vehiculos.getModel().isCellEditable(vehiculos.getSelectedRow(),vehiculos.getSelectedColumn())) {
                modelo = (DefaultTableModel) vehiculos.getModel();
                String aux = (String)modelo.getValueAt(vehiculos.getSelectedRow(), vehiculos.getSelectedColumn());
                if (aux!=null) {
                    //            System.out.println("Longitud: "+aux.length());
                    if (aux.length()>0) {
                        before_edit_cell= aux;
                        //                System.out.println("Nuevo valor de la celda: "+before_edit_cell);
                        modelo.setValueAt("",vehiculos.getSelectedRow(),vehiculos.getSelectedColumn());
                        vehiculos.setModel(modelo);
                        vehiculos.requestFocus();
                    }
                }else{
                    before_edit_cell = "";
                }
            }
        }else{
            if(a==KeyEvent.VK_DELETE) {
                //            System.out.println("DEL");
                if (vehiculos.getModel().isCellEditable(vehiculos.getSelectedRow(),vehiculos.getSelectedColumn())) {
                    modelo = (DefaultTableModel) vehiculos.getModel();
                    modelo.setValueAt("",vehiculos.getSelectedRow(),vehiculos.getSelectedColumn());
                    vehiculos.setModel(modelo);
                    before_edit_cell = "";
                    if (vehiculos.isEditing()){
                        vehiculos.getCellEditor().stopCellEditing();
                    }
                    evt.consume();
                }

            }else{
                if(a==KeyEvent.VK_ESCAPE) {
                    //                System.out.println("ESC");
                    modelo = (DefaultTableModel) vehiculos.getModel();
                    String aux = (String)modelo.getValueAt(vehiculos.getSelectedRow(), vehiculos.getSelectedColumn());
                    //                System.out.println("Antes: "+aux);
                    if (aux!=null) {
                        //                    System.out.println("aux no null");
                        if (vehiculos.isEditing()){
                            //                        System.out.println("recuperando dato");
                            modelo.setValueAt(before_edit_cell,vehiculos.getSelectedRow(),vehiculos.getSelectedColumn());
                            vehiculos.setModel(modelo);
                            vehiculos.getCellEditor().cancelCellEditing();
                        }else{
                            evt.consume();
                        }
                        evt.consume();
                    }else{
                        if (vehiculos.isEditing()){
                            vehiculos.getCellEditor().cancelCellEditing();
                        }
                        evt.consume();
                    }
                }
            }
        }
    }//GEN-LAST:event_vehiculosKeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        boolean confirm = false;
        if(verify_data()){
            if (JOptionPane.showConfirmDialog(this, "La informacion esta completa\nEsta seguro que desea almacenar la información?", "Confirmación", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                for (int i = 0; i < vehiculos.getRowCount(); i++) {
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r;
                    try{
                        r = con.s.executeQuery ("SELECT *\n" +
                                                "FROM\n" +
                                                "    `t_vehiculo`\n" +
                                                "    INNER JOIN `t_tip_vehiculo` \n" +
                                                "        ON (`t_vehiculo`.`ID_TIPO_VEHIC` = `t_tip_vehiculo`.`ID_TIPO_VEHIC`) WHERE ID_VEHIC= '"+vehiculos.getValueAt(i, 2).toString().trim()+"' AND NOMBRE_TIPO_VEHIC= '"+vehiculos.getValueAt(i, 1).toString().trim()+"';");
                        if(!r.next()){
                            con.s.executeUpdate("INSERT INTO `t_vehiculo` (`ID_VEHIC`,`ID_TIPO_VEHIC`) VALUES ('"+vehiculos.getValueAt(i, 2).toString().trim()+"',(SELECT ID_TIPO_VEHIC FROM t_tip_vehiculo WHERE NOMBRE_TIPO_VEHIC= '"+vehiculos.getValueAt(i, 1).toString().trim()+"'));");
                        }
                        con.s.executeUpdate("INSERT INTO `t_rel_vehiculo`(`ID_EMP`, `ID_VEHIC`, `F_REGISTRO`) VALUES ("+vehiculos.getValueAt(i, 0).toString().trim()+",'"+vehiculos.getValueAt(i, 2).toString().trim()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"');");
                        JOptionPane.showMessageDialog(null,"La información ha sido almacenada correctamente","Confirmación",JOptionPane.INFORMATION_MESSAGE);
                        con.cerrar();
                        this.dispose();
                    }catch(SQLException j){
                        con.cerrar();
                        j.printStackTrace();
                        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                
                
                
                
                    
            }

        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(verify_data()){
            JOptionPane.showMessageDialog(this,"La informacion de la tabla esta completa","Información",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        modelo = (DefaultTableModel) vehiculos.getModel();
        if (vehiculos.getRowCount()>0) {
            //System.out.println("Filas: "+jTable1.getRowCount());
            int j = vehiculos.getRowCount();
            for (int i = 0; i < j; i++) {
                //System.out.println("q");
                modelo.removeRow(vehiculos.getRowCount()-1);
                vehiculos.setModel(modelo);
            }
        }else{
            JOptionPane.showMessageDialog(this,"La tabla no contiene registros","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        modelo = (DefaultTableModel) vehiculos.getModel();
        modelo.insertRow(vehiculos.getSelectedRow()+1, fila);
        vehiculos.setModel(modelo);

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (vehiculos.getRowCount()>0) {
            if (vehiculos.getSelectedRow()>-1) {
                modelo = (DefaultTableModel) vehiculos.getModel();
                modelo.removeRow(vehiculos.getSelectedRow());
                vehiculos.setModel(modelo);
            }else{
                JOptionPane.showMessageDialog(this,"No se ha seleccionado ningun registro","Error",JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this,"La tabla no contiene registros","Error",JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton3ActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add_Info_Transporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Add_Info_Transporte dialog = new Add_Info_Transporte(new javax.swing.JFrame(), true);
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
    private javax.swing.JMenuItem add_row;
    private javax.swing.JMenuItem del_row;
    private javax.swing.JMenuItem del_table;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable vehiculos;
    // End of variables declaration//GEN-END:variables

public final void tac_cedula(){
    tac_cedula_table.removeAllItems();
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_EMPLEADOS ORDER BY ID_EMP ASC;");
        while(r.next()){
            String str=r.getString("ID_EMP");
            tac_cedula_table.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public final void tac_tipo_vehic(){
    tac_tip_vehic_table.removeAllItems();
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT *\n" +
                                "FROM\n" +
                                "`t_tip_vehiculo`;");
        while(r.next()){
            String str=r.getString("NOMBRE_TIPO_VEHIC");
            tac_tip_vehic_table.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public boolean check_cedula(Object ced){
    boolean ret=false;
    if (ced!=null) {
        if (chech_char(ced.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if(!ced.toString().trim().equals("")){
                if (comprobarLong(ced.toString().trim())) {
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r;
                    try{
                        r = con.s.executeQuery ("SELECT * FROM T_EMPLEADOS WHERE ID_EMP="+ced.toString().trim());
                        if(r.next()){
                            ret=true;
                        }
                        con.cerrar();
                    }catch(SQLException j){
                        con.cerrar();
                        j.printStackTrace();
                        return false;
                    }
                }
            }
        }
    }
    return ret;  
}
public boolean check_tipo_vehiculo(Object tip_v){
    boolean ret=false;
    if (tip_v!=null) {
        if (chech_char(tip_v.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,. ")) {
            if(!tip_v.toString().trim().equals("")){
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT * FROM t_tip_vehiculo WHERE NOMBRE_TIPO_VEHIC='"+tip_v.toString().trim()+"'");
                    if(r.next()){
                        ret=true;
                    }
                    con.cerrar();
                }catch(SQLException j){
                    con.cerrar();
                    j.printStackTrace();
                    return false;
                }
            }
        }
    }
    return ret;  
}
public boolean check_field (Object field){
boolean ret=false;
    if (field!=null) {
        if (chech_char(field.toString().trim(),"'$%&()=?¡¿/*+-[]{};:<>, ")) {
            if (!field.toString().equals("")) {
               ret=true;
            }
        }
    }

return ret;
}
public boolean check_vehiculo(Object placa_vehic,Object ced){
    boolean ret=false;
    if (placa_vehic!=null) {
        if (chech_char(placa_vehic.toString().trim(),"-'#$%&()=?¡¿/*+[]{};:<>,. ")) {
            if(!placa_vehic.toString().trim().equals("")){
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT * FROM t_rel_vehiculo WHERE ID_EMP = "+ced.toString().trim()+" AND ID_VEHIC='"+placa_vehic.toString().trim()+"'");
                    if(!r.next()){
                        ret=true;
                    }
                    con.cerrar();
                }catch(SQLException j){
                    con.cerrar();
                    j.printStackTrace();
                    return false;
                }
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
public static boolean comprobarLong (String cadena){
try{
    
    long num = Long.parseLong(cadena);
}catch (Exception e){
    e.printStackTrace();
    return false;
}
return true;

}
public void autofill_placa(int row){
    if (vehiculos.getValueAt(row, 1).toString().equals("TRANSPORTE PUBLICO")) {
        vehiculos.setValueAt("TTE_PB", row, 2);
    }
    if (vehiculos.getValueAt(row, 1).toString().equals("BICICLETA")) {
        vehiculos.setValueAt("BICIC", row, 2);
    }
}
public boolean verify_data(){
    boolean ret=true;
    modelo = (DefaultTableModel)vehiculos.getModel(); 
    for (int i = 0; i < vehiculos.getRowCount(); i++) {
        if (check_cedula(vehiculos.getValueAt(i, 0))) {
            if (check_tipo_vehiculo(vehiculos.getValueAt(i, 1))) {//TIPO DE VEHICULO
                autofill_placa(i);
                if (check_field(vehiculos.getValueAt(i, 2))) {//PLACA DEL VEHICULO
                    if (check_vehiculo(vehiculos.getValueAt(i, 2), vehiculos.getValueAt(i, 1))) {
                        ret=true;
                    } else {
                        vehiculos.changeSelection(i,0, false, false);
                        vehiculos.requestFocus();
                        JOptionPane.showMessageDialog(this,"Este vehiculo ya se encuentra registrado con el empleado","Error",JOptionPane.ERROR_MESSAGE);
                        ret=false&ret;
                        break;
                    }
                } else {
                    vehiculos.changeSelection(i,2, false, false);
                    vehiculos.requestFocus();
                    JOptionPane.showMessageDialog(this,"Verifique que el campo placa no contenga caracteres especiales ni espacios","Error",JOptionPane.ERROR_MESSAGE);
                    ret=false&ret;
                    break;
                }
            } else {
                vehiculos.changeSelection(i,1, false, false);
                vehiculos.requestFocus();
                JOptionPane.showMessageDialog(this,"Verifique que el tipo de vehiculo sea el correcto","Error",JOptionPane.ERROR_MESSAGE);
                ret=false&ret;
                break;
            }
        } else {
            vehiculos.changeSelection(i,0, false, false);
            vehiculos.requestFocus();
            JOptionPane.showMessageDialog(this,"Verifique que la cedula del empleado sea correcta","Error",JOptionPane.ERROR_MESSAGE);
            ret=false&ret;
            break;
        }
    }
    return ret;
}


}
