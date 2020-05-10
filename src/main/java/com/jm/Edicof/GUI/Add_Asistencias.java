/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.GUI;

import com.jm.Edicof.Clases.CellRender_Asistencias;
import com.jm.Edicof.Clases.Conexion;
import com.jm.Edicof.Clases.PegarExcel;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author ADMIN
 */
public class Add_Asistencias extends javax.swing.JDialog {
    static Dimension screenSize = null;
    DefaultTableModel modelo = null;
    Object [] fila = new Object[3];
    TextAutoCompleter tac_cedula_table = null;
    TextAutoCompleter tac_empleador_table = null;
    JTextField tb_cedula_table = null;
    JTextField tb_nit_table = null;
    String before_edit_cell = null;
    /**
     * Creates new form Add_Asistencias
     */
    public Add_Asistencias(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        //////////////////----------------------------------------
//        jTable1.getModel().addTableModelListener(new TableModelListener() {
//            @Override
//            public void tableChanged(TableModelEvent e) {
//                if (e.getType() == TableModelEvent.UPDATE) {//e.getType() == TableModelEvent.UPDATE
//                    int fila = e.getFirstRow();
//                    int fila_f = e.getLastRow();
//                    int columna = e.getColumn();
//                    //System.out.println("Celda modificada: "+fila+","+fila_f+","+columna+",Valor: "+jTable1.getValueAt(fila, columna).toString().trim());
//                    if (columna == 0) {
//                        if (check_empleador_nit(jTable1.getValueAt(fila,1))) {
//                            check_empleado(fila,columna,jTable1.getValueAt(fila, columna).toString().trim(),t_empleador.getText());
//                        }
//                    }else{
//                        return;
//                    }
//                    
//                }
//            }
//        });
        //////////////////----------------------------------------
        asistencias.setDefaultRenderer (Object.class, new CellRender_Asistencias());
        asistencias.addKeyListener(new KeyAdapter() {
        
        @Override
        public void keyReleased(KeyEvent evt) {
            try {
                char a=evt.getKeyChar();
                int b=evt.getKeyCode();
                System.out.println("Key Released Table: '"+a+"'("+b+")");
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
            if (evt.isControlDown()&& evt.getKeyChar() != 'v' && evt.getKeyCode() == 86) {
                                
            } else {
                if (evt.isControlDown()&& evt.getKeyChar() != 'c' && evt.getKeyCode() == 67) {
                                
                }else {
                    if((b>=96 & b<=105)) { //(b>=65 & b<=90)
        //                if ((b>=96 & b<=105) | a==KeyEvent.VK_V) {
                            if (asistencias.getModel().isCellEditable(asistencias.getSelectedRow(),asistencias.getSelectedColumn())) {
                                modelo = (DefaultTableModel) asistencias.getModel();
                                String aux = (String)modelo.getValueAt(asistencias.getSelectedRow(), asistencias.getSelectedColumn());
                                if (aux!=null) {
                                    if (aux.length()>0) {
                                        before_edit_cell= aux;
                                        modelo.setValueAt("",asistencias.getSelectedRow(),asistencias.getSelectedColumn());
                                        asistencias.setModel(modelo);
                                        asistencias.requestFocus();
                                    }
                                }else{
                                    before_edit_cell = "";
                                }
                            }
                    }else{
                        if(a==KeyEvent.VK_DELETE) {
                            if (asistencias.getModel().isCellEditable(asistencias.getSelectedRow(),asistencias.getSelectedColumn())) {
                                modelo = (DefaultTableModel) asistencias.getModel();
                                modelo.setValueAt("",asistencias.getSelectedRow(),asistencias.getSelectedColumn());
                                asistencias.setModel(modelo);
                                before_edit_cell = "";
                                if (asistencias.isEditing()){
                                    asistencias.getCellEditor().stopCellEditing();
                                }
                                evt.consume();
                            }

                        }else{
                            if(a==KeyEvent.VK_ESCAPE) {
                                modelo = (DefaultTableModel) asistencias.getModel();
                                String aux = (String)modelo.getValueAt(asistencias.getSelectedRow(), asistencias.getSelectedColumn());
                                if (aux!=null) {
                                    if (asistencias.isEditing()){
                                        modelo.setValueAt(before_edit_cell,asistencias.getSelectedRow(),asistencias.getSelectedColumn());
                                        asistencias.setModel(modelo);
                                        asistencias.getCellEditor().cancelCellEditing();
                                        evt.setKeyCode(10);
                                    }else{
                                        evt.consume();
                                    }
                                    evt.consume();
                                }else{
                                    if (asistencias.isEditing()){
                                        asistencias.getCellEditor().cancelCellEditing();
                                    }
                                    evt.consume();
                                }
                            }else{
                                if (a==KeyEvent.VK_TAB) {
                                    evt.setKeyCode(10);
                                }else{
                                    if (a==KeyEvent.VK_ENTER) {
                                        
                                    }else{
                                        if (b==KeyEvent.VK_DOWN | b==KeyEvent.VK_UP | b==KeyEvent.VK_LEFT | b==KeyEvent.VK_RIGHT) {
                                            
                                        } else {
                                            evt.consume();
                                        }
                                        
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
        }
        });
        //////////////////----------------------------------------
//        screenSize = Main.getsize();
//        this.setSize(screenSize.width, screenSize.height-10);
        this.setLocationRelativeTo(null);
        tb_cedula_table = new JTextField();
        tb_nit_table = new JTextField();
        init();
        cb_cedula();
        tac_empleador();
        tac_cedula_table.setMode(0);
        tac_empleador_table.setMode(0);
        //tac_empleador.setMode(0);
        InputMap map1 = asistencias.getInputMap(asistencias.WHEN_FOCUSED);
        map1.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK), "null");

        asistencias.getColumnModel().getColumn(0).setCellEditor(new MyTableCellEditorDate(tb_cedula_table));
        asistencias.getColumnModel().getColumn(1).setCellEditor(new MyTableCellEditorDate(tb_nit_table));
    }
    public void init(){
        
        tac_cedula_table = new TextAutoCompleter(tb_cedula_table);
        tac_empleador_table = new TextAutoCompleter(tb_nit_table);
    }
    class MyTableCellEditorDate extends AbstractCellEditor implements TableCellEditor {
    JComponent component=null;
    boolean par_date=false;
    boolean par_list=false;
    private MyTableCellEditorDate() {
        component = new JTextField();
        
    }
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
                    //autocomplete_date_released(b);
                }
            }catch(Exception ev){
                ev.printStackTrace();
            }
        }

        @Override
        public void keyTyped(KeyEvent evt) {
            
        }

        @Override
        public void keyPressed(KeyEvent evt) {
            char a=evt.getKeyChar();
            int b=evt.getKeyCode();
            System.out.println("Key Presed Cell: '"+a+"'("+b+")");
            if (evt.isControlDown()&& evt.getKeyChar() != 'v' && evt.getKeyCode() == 86) {
                                
            } else {
                if (evt.isControlDown()&& evt.getKeyChar() != 'c' && evt.getKeyCode() == 67) {
                                
                }else {
                    if((b>=96 & b<=105)) { //(b>=65 & b<=90)
        //                if ((b>=96 & b<=105) | a==KeyEvent.VK_V) {
                            if (asistencias.getModel().isCellEditable(asistencias.getSelectedRow(),asistencias.getSelectedColumn())) {
                                modelo = (DefaultTableModel) asistencias.getModel();
                                String aux = (String)modelo.getValueAt(asistencias.getSelectedRow(), asistencias.getSelectedColumn());
                                if (aux!=null) {
                                    if (aux.length()>0) {
                                        before_edit_cell= aux;
                                        modelo.setValueAt("",asistencias.getSelectedRow(),asistencias.getSelectedColumn());
                                        asistencias.setModel(modelo);
                                        asistencias.requestFocus();
                                    }
                                }else{
                                    before_edit_cell = "";
                                }
                            }
                    }else{
                        System.out.println("Key Presed Cell 1: '"+a+"'("+b+")");
                        if(a==KeyEvent.VK_DELETE) {
                            if (asistencias.getModel().isCellEditable(asistencias.getSelectedRow(),asistencias.getSelectedColumn())) {
                                if (!asistencias.isEditing()){
                                    modelo = (DefaultTableModel) asistencias.getModel();
                                    modelo.setValueAt("",asistencias.getSelectedRow(),asistencias.getSelectedColumn());
                                    asistencias.setModel(modelo);
                                    before_edit_cell = "";
                                    asistencias.getCellEditor().stopCellEditing();
                                }
                            }
                        }else{
                            System.out.println("Key Presed Cell 2: '"+a+"'("+b+")");
                            if(a==KeyEvent.VK_ESCAPE) {
                                modelo = (DefaultTableModel) asistencias.getModel();
                                String aux = (String)modelo.getValueAt(asistencias.getSelectedRow(), asistencias.getSelectedColumn());
                                if (aux!=null) {
                                    if (asistencias.isEditing()){
                                        modelo.setValueAt(before_edit_cell,asistencias.getSelectedRow(),asistencias.getSelectedColumn());
                                        asistencias.setModel(modelo);
                                        asistencias.getCellEditor().cancelCellEditing();
                                        evt.setKeyCode(10);
                                    }else{
                                        evt.consume();
                                    }
                                    evt.consume();
                                }else{
                                    if (asistencias.isEditing()){
                                        asistencias.getCellEditor().cancelCellEditing();
                                    }
                                    evt.consume();
                                }
                            }else{
                                System.out.println("Key Presed Cell 3: '"+a+"'("+b+")");
                                if (a==KeyEvent.VK_TAB) {
                                    evt.setKeyCode(10);
                                }else{
                                    System.out.println("Key Presed Cell 4: '"+a+"'("+b+")");
                                    if (a==KeyEvent.VK_ENTER) {
                                        
                                    }else{
                                        System.out.println("Key Presed Cell 5: '"+a+"'("+b+")");
                                        if (b==KeyEvent.VK_DOWN | b==KeyEvent.VK_UP | b==KeyEvent.VK_LEFT | b==KeyEvent.VK_RIGHT) {
                                            
                                        } else {
                                            System.out.println("Key Presed Cell 6: '"+a+"'("+b+")");
                                            evt.consume();
                                        }
                                    }
                                }
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
    public boolean isCellEditable(EventObject evt) {
      if (evt instanceof MouseEvent) {
          int clickCount;
          clickCount = 2;
          return ((MouseEvent)evt).getClickCount() >= clickCount;
      }
      return true;
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
        jMenuItem1 = new javax.swing.JMenuItem();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        asistencias = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        f_asistencia = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();

        jMenuItem1.setText("jMenuItem1");
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Asistencias");
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos asistencia"));

        new PegarExcel(asistencias);
        asistencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "ID Empleado", "Nit"
            }
        ));
        asistencias.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(asistencias);
        if (asistencias.getColumnModel().getColumnCount() > 0) {
            asistencias.getColumnModel().getColumn(0).setResizable(false);
            asistencias.getColumnModel().getColumn(0).setPreferredWidth(80);
            asistencias.getColumnModel().getColumn(1).setResizable(false);
            asistencias.getColumnModel().getColumn(1).setPreferredWidth(80);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha de asistencia"));

        jLabel1.setText("Fecha de asistencia");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(f_asistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(f_asistencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Herramientas"));
        jPanel5.setPreferredSize(new java.awt.Dimension(600, 112));

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_row_delete.png"))); // NOI18N
        jButton9.setText("Eliminar Fila");
        jButton9.setToolTipText("Eliminar Fila seleccionada");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_delete.png"))); // NOI18N
        jButton10.setText("Limpiar tabla");
        jButton10.setToolTipText("Eliminar todas las Filas");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_row_insert.png"))); // NOI18N
        jButton11.setText("Insertar Fila");
        jButton11.setToolTipText("Insertar Fila");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton10)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton11)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_error.png"))); // NOI18N
        jButton1.setText("Verificar datos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_save.png"))); // NOI18N
        jButton2.setText("Guardar datos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(10, 10, 10)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancel_1.png"))); // NOI18N
        jButton12.setText("Cerrar");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton12)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton12)
                .addContainerGap())
        );

        jPanel1.getAccessibleContext().setAccessibleParent(jScrollPane3);
        jPanel6.getAccessibleContext().setAccessibleParent(jScrollPane3);

        jScrollPane3.setViewportView(jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        if (asistencias.getRowCount()>0) {
            if (asistencias.getSelectedRow()>-1) {
                modelo = (DefaultTableModel) asistencias.getModel();
                modelo.removeRow(asistencias.getSelectedRow());
                asistencias.setModel(modelo);
            }else{
                JOptionPane.showMessageDialog(this,"No se ha seleccionado ningun registro","Error",JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this,"La tabla no contiene registros","Error",JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        modelo = (DefaultTableModel) asistencias.getModel();
        if (asistencias.getRowCount()>0) {
            while(asistencias.getRowCount()!=0){
                modelo.removeRow(asistencias.getRowCount()-1);
            }
        }else{
            JOptionPane.showMessageDialog(this,"La tabla no contiene registros","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        modelo = (DefaultTableModel) asistencias.getModel();
        modelo.insertRow(asistencias.getSelectedRow()+1, fila);
        asistencias.setModel(modelo);

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(verify_data()){
            JOptionPane.showMessageDialog(this,"La informacion de la tabla esta completa","Información",JOptionPane.INFORMATION_MESSAGE);
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        boolean confirm = true;
        if(verify_data()){
            int conf = JOptionPane.showConfirmDialog(this,"La informacion de la tabla esta completa\nEsta seguro que desea continuar?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (conf == JOptionPane.YES_OPTION) {
                modelo = (DefaultTableModel)asistencias.getModel(); 
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                for (int i = 0; i < asistencias.getRowCount(); i++) {
                    try {
                        r = con.s.executeQuery ("SELECT\n"
                                                + "*\n"
                                                + "FROM t_novedades\n"
                                                + "WHERE t_novedades.ID_TIPO IN (1,2)\n" 
                                                + "AND ((t_novedades.`FECHA_INGRESO` <= '"+new SimpleDateFormat("yyyy-MM-dd").format(f_asistencia.getDate())+"' AND t_novedades.`FECHA_RETIRO` = '1900-01-01')\n"
                                                + "OR ( t_novedades.`FECHA_INGRESO` <= '"+new SimpleDateFormat("yyyy-MM-dd").format(f_asistencia.getDate())+"' AND t_novedades.`FECHA_RETIRO` >= '"+new SimpleDateFormat("yyyy-MM-dd").format(f_asistencia.getDate())+"'))"
                                                + "AND t_novedades.ID_EMPLEADO = "+asistencias.getValueAt(i, 0).toString()+" AND t_novedades.ID_EMPRESA='"+asistencias.getValueAt(i, 1)+"'");
                        if(r.next()){
                            con.s.executeUpdate("INSERT INTO `t_asistencias` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO,F_ASISTENCIA) VALUES ("+modelo.getValueAt(i, 0)+",'"+modelo.getValueAt(i, 1)+"','"+new SimpleDateFormat("yyyy-MM-dd").format(r.getDate("FECHA_INGRESO"))+"','"+new SimpleDateFormat("yyyy-MM-dd").format(r.getDate("FECHA_RETIRO"))+"',"+r.getInt("ID_TIPO")+",'"+new SimpleDateFormat("yyyy-MM-dd").format(f_asistencia.getDate())+"')");
                            modelo.removeRow(i);
                            i=i-1;
                            confirm = confirm & true;
                        }else{
                            confirm = confirm & false;
                        }
                    } catch (SQLException | HeadlessException e) {
                        confirm = confirm & false;
                        con.cerrar();
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this,e,"Información",JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }
                if (confirm) {
                    JOptionPane.showMessageDialog(this,"La información ha sido ingresada correctamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
                    Toolkit.getDefaultToolkit().beep();
                }
                con.cerrar();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Add_Asistencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add_Asistencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add_Asistencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add_Asistencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Add_Asistencias dialog = new Add_Asistencias(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable asistencias;
    private com.toedter.calendar.JDateChooser f_asistencia;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

public void cb_cedula(){
    String before="";
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
public void check_empleado(int fila, int col, String ced, String empleador){
    ArrayList<String> ret_nov = new ArrayList<String>();
    if (true) { //& !jTable1.isEditing()
        modelo = (DefaultTableModel)asistencias.getModel();
        if (check_cedula(ced)) {
            Conexion con = new Conexion();
            con.conexion();
            ResultSet r;
            ResultSet r1;
            try {
                r = con.s.executeQuery ("SELECT\n"
                                        + "*, COUNT(ID_EMPLEADO)\n"
                                        + "FROM t_novedades\n"
                                        + "  INNER JOIN t_empleados\n"
                                        + "    ON (t_novedades.ID_EMPLEADO = t_empleados.ID_EMP)\n"
                                        + "  INNER JOIN t_empresas\n"
                                        + "    ON (t_novedades.ID_EMPRESA = t_empresas.ID_EMPRESA)\n"
                                        + "  INNER JOIN t_eps\n"
                                        + "    ON (t_novedades.ID_EPS = t_eps.ID_EPS)\n"
                                        + "  INNER JOIN t_afp\n"
                                        + "    ON (t_novedades.ID_AFP = t_afp.ID_AFP)\n"
                                        + "  INNER JOIN t_municipios\n"
                                        + "    ON (t_novedades.ID_MUN_NOV = t_municipios.ID_MUN)\n"
                                        + "  INNER JOIN t_parentesco\n"
                                        + "    ON (t_novedades.ID_PAR_ACU_NOV = t_parentesco.ID_PAR)\n"
                                        + "  INNER JOIN t_obra\n"
                                        + "    ON (t_novedades.ID_OBRA = t_obra.ID_OBRA)\n"
                                        + "  INNER JOIN t_tipo_novedad\n"
                                        + "    ON (t_novedades.ID_TIPO = t_tipo_novedad.ID_TIPO)\n"
                                        + "WHERE t_novedades.ID_EMPLEADO = "+ced+" AND t_novedades.ID_TIPO = 1 AND t_empresas.NOMBRE_EMPRESA='"+empleador+"'");
                if (r.next()) {
                    if (r.getInt("COUNT(ID_EMPLEADO)")>1) {
                        JOptionPane.showMessageDialog(this,"El empleado actualmente esta activo con varios empleadores.","Información",JOptionPane.INFORMATION_MESSAGE);
                        Toolkit.getDefaultToolkit().beep();
                        ret_nov=call_sel_emp(ced,"1");
                        if (!ret_nov.isEmpty()) {
                            r = con.s.executeQuery ("SELECT\n"
                                                    + "*\n"
                                                    + "FROM t_novedades\n"
                                                    + "  INNER JOIN t_empleados\n"
                                                    + "    ON (t_novedades.ID_EMPLEADO = t_empleados.ID_EMP)\n"
                                                    + "  INNER JOIN t_empresas\n"
                                                    + "    ON (t_novedades.ID_EMPRESA = t_empresas.ID_EMPRESA)\n"
                                                    + "  INNER JOIN t_eps\n"
                                                    + "    ON (t_novedades.ID_EPS = t_eps.ID_EPS)\n"
                                                    + "  INNER JOIN t_afp\n"
                                                    + "    ON (t_novedades.ID_AFP = t_afp.ID_AFP)\n"
                                                    + "  INNER JOIN t_municipios\n"
                                                    + "    ON (t_novedades.ID_MUN_NOV = t_municipios.ID_MUN)\n"
                                                    + "  INNER JOIN t_parentesco\n"
                                                    + "    ON (t_novedades.ID_PAR_ACU_NOV = t_parentesco.ID_PAR)\n"
                                                    + "  INNER JOIN t_obra\n"
                                                    + "    ON (t_novedades.ID_OBRA = t_obra.ID_OBRA)\n"
                                                    + "  INNER JOIN t_tipo_novedad\n"
                                                    + "    ON (t_novedades.ID_TIPO = t_tipo_novedad.ID_TIPO)\n"
                                                    + "WHERE (t_novedades.ID_EMPLEADO = "+ret_nov.get(0)+" AND t_novedades.ID_EMPRESA = '"+ret_nov.get(1)+"' AND t_novedades.FECHA_INGRESO = '"+ret_nov.get(2)+"' AND t_novedades.FECHA_RETIRO = '"+ret_nov.get(3)+"' AND t_novedades.ID_TIPO = "+ret_nov.get(4)+")\n"
                                                    + "ORDER BY FECHA_INGRESO DESC;");
                            if(r.next()){
                                modelo.setValueAt(r.getString("NOMBRE_1_EMP")+" "+r.getString("NOMBRE_2_EMP")+" "+r.getString("APELLIDO_1_EMP")+" "+r.getString("APELLIDO_2_EMP"),fila,1);
                                modelo.setValueAt(r.getString("ID_EMPRESA"),fila,2);
                                modelo.setValueAt(r.getString("NOMBRE_EMPRESA"),fila,3);
                            }
                        }
                    }else{
                        if (r.getInt("COUNT(ID_EMPLEADO)")==1) {
                            modelo.setValueAt(r.getString("NOMBRE_1_EMP")+" "+r.getString("NOMBRE_2_EMP")+" "+r.getString("APELLIDO_1_EMP")+" "+r.getString("APELLIDO_2_EMP"),fila,1);
                            modelo.setValueAt(r.getString("ID_EMPRESA"),fila,2);
                            modelo.setValueAt(r.getString("NOMBRE_EMPRESA"),fila,3);
                        }
                    }
                }
            con.cerrar();
            } catch (SQLException | HeadlessException e) {
                con.cerrar();
                e.printStackTrace();
            }

        }
        //JOptionPane.showMessageDialog(null,"asd","Error",JOptionPane.ERROR_MESSAGE);
    }
}
public static boolean comprobarLong (String cadena){
try{
    
    long num = Long.parseLong(cadena);
}catch (Exception e){
    return false;
}
return true;

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
public  ArrayList call_sel_emp(String c, String t){
    Sel_Empleador sel=new Sel_Empleador(this,true,c,t);
    sel.setLocationRelativeTo(this);
    sel.setVisible(true);
return sel.getRet();
}
public void tac_empleador(){
    tac_empleador_table.removeAllItems();
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_EMPRESAS ORDER BY NOMBRE_EMPRESA ASC;");
        while(r.next()){
            String str=r.getString("ID_EMPRESA");
            tac_empleador_table.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public boolean check_empleador(Object emp){
    boolean ret=false;
    if (emp!=null) {
        if (chech_char(emp.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,")) {
            if (!emp.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT * FROM T_EMPRESAS WHERE NOMBRE_EMPRESA='"+emp.toString().trim()+"'");
                    if(r.next()){
                        ret=true;
                    }
                    con.cerrar();
                }catch(SQLException j){
                    con.cerrar();
                    j.printStackTrace();
                    return false;
                    //JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    return ret;
}
public boolean check_empleador_nit(Object emp){
    boolean ret=false;
    if (emp!=null) {
        if (chech_char(emp.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,")) {
            if (!emp.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT * FROM T_EMPRESAS WHERE ID_EMPRESA='"+emp.toString().trim()+"'");
                    if(r.next()){
                        ret=true;
                    }
                    con.cerrar();
                }catch(SQLException j){
                    con.cerrar();
                    j.printStackTrace();
                    ret=true;
                    //JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    return ret;
}
public boolean verify_data(){
    boolean ret=true;
    if (asistencias.getRowCount()>0) { //VERIFICA QUE LA TABLA TENGA REGISTROS
        if (f_asistencia.getDate()!=null) {
            for (int i = 0; i < asistencias.getRowCount(); i++) {
                if (check_empleador_nit(asistencias.getValueAt(i, 1))) {
                    if (check_cedula(asistencias.getValueAt(i, 0))) {
                        Conexion con = new Conexion();
                        con.conexion();
                        ResultSet r, r1;
                        try {
                            r = con.s.executeQuery ("SELECT\n"
                                                + "*, COUNT(ID_EMPLEADO)\n"
                                                + "FROM t_novedades\n"
                                                + "WHERE t_novedades.ID_TIPO IN (1,2)\n" 
                                                + "AND ((t_novedades.`FECHA_INGRESO` <= '"+new SimpleDateFormat("yyyy-MM-dd").format(f_asistencia.getDate())+"' AND t_novedades.`FECHA_RETIRO` = '1900-01-01')\n"
                                                + "OR ( t_novedades.`FECHA_INGRESO` <= '"+new SimpleDateFormat("yyyy-MM-dd").format(f_asistencia.getDate())+"' AND t_novedades.`FECHA_RETIRO` >= '"+new SimpleDateFormat("yyyy-MM-dd").format(f_asistencia.getDate())+"'))"
                                                + "AND t_novedades.ID_EMPLEADO = "+asistencias.getValueAt(i, 0).toString()+" AND t_novedades.ID_EMPRESA='"+asistencias.getValueAt(i, 1)+"'");
                            if(r.next()){
                                if (r.getInt("COUNT(ID_EMPLEADO)")==1) {
                                    r1 = con.s.executeQuery ("SELECT * FROM t_asistencias WHERE ID_EMPLEADO ="+asistencias.getValueAt(i, 0)+" AND FECHA_ASIST='"+new SimpleDateFormat("yyyy-MM-dd").format(f_asistencia.getDate())+"' AND ID_EMPRESA='"+asistencias.getValueAt(i, 1)+"' AND F_INGRESO='"+new SimpleDateFormat("yyyy-MM-dd").format(r.getDate("FECHA_INGRESO"))+"' AND F_RETIRO ='"+new SimpleDateFormat("yyyy-MM-dd").format(r.getDate("FECHA_RETIRO"))+"' AND ID_TIPO ="+r.getInt("ID_TIPO"));
                                    if(r1.next()){
                                        asistencias.changeSelection(i,0, false, false);
                                        asistencias.requestFocus();
                                        JOptionPane.showMessageDialog(this,"Esta asistencia ya existe.","Error",JOptionPane.ERROR_MESSAGE);
                                        ret=false;
                                        break;
                                    }else{
                                        ret=ret&true;
                                    }
                                }else{
                                    asistencias.changeSelection(i,0, false, false);
                                    asistencias.requestFocus();
                                    JOptionPane.showMessageDialog(this,"EL empleado no se encuentra activo con este empleador para la fecha selaccionada","Error",JOptionPane.ERROR_MESSAGE);
                                    ret=false;
                                    break;
                                }
                            }else{
                                asistencias.changeSelection(i,0, false, false);
                                asistencias.requestFocus();
                                JOptionPane.showMessageDialog(this,"EL empleado no se encuentra activo con este empleador para la fecha selaccionada","Error",JOptionPane.ERROR_MESSAGE);
                                ret=false;
                                break;
                            }
                            con.cerrar();
                        }catch (SQLException ex) {
                            con.cerrar();
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(this,ex,"Error",JOptionPane.ERROR_MESSAGE); 
                            ret=false;
                                break;
                        }
                    } else {
                        asistencias.changeSelection(i,0, false, false);
                        asistencias.requestFocus();
                        JOptionPane.showMessageDialog(this,"Verifique la Cedula del empleado","Error",JOptionPane.ERROR_MESSAGE);
                        ret=false;
                        break;
                    }
                }else {
                    asistencias.changeSelection(i,1, false, false);
                    JOptionPane.showMessageDialog(this,"Verifique el nit de la Empresa","Error",JOptionPane.ERROR_MESSAGE);
                    ret=false;
                    break;
                }
            }
        }else {
            f_asistencia.requestFocus();
            JOptionPane.showMessageDialog(this,"Verifique la Fecha de las asistencias que desea ingresar","Error",JOptionPane.ERROR_MESSAGE);
            ret=false;
        }
    }else{
        JOptionPane.showMessageDialog(this,"La tabla no tiene registros","Error",JOptionPane.ERROR_MESSAGE);
        ret = false;
    }
return ret;
}
public boolean check_field (Object field){
boolean ret=false;
    if (field!=null) {
        if (chech_char(field.toString().trim(),"'$#%&()=?¡¿/*+[]{};:<>,")) {
            if (!field.toString().equals("")) {
               ret=true;
            }
        }
    }

return ret;
}
public boolean check_nit(Object emp){
    boolean ret=false;
    if (emp!=null) {
        if (chech_char(emp.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,")) {
            if (!emp.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT * FROM T_EMPRESAS WHERE ID_EMPRESA='"+emp.toString().trim()+"'");
                    if(r.next()){
                        ret=true;
                    }
                    con.cerrar();
                }catch(SQLException j){
                    con.cerrar();
                    j.printStackTrace();
                    return false;
                    //JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    return ret;
}
}
