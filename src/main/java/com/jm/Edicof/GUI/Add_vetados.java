/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.GUI;

import com.jm.Edicof.Clases.AutoCompletion;
import com.jm.Edicof.Clases.CellRender_vetados;
import com.jm.Edicof.Clases.Conexion;
import com.jm.Edicof.Clases.PegarExcel;
import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.HeadlessException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EventObject;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractCellEditor;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author ADMIN
 */
public class Add_vetados extends javax.swing.JDialog {
    static Dimension screenSize = null;
    DefaultTableModel modelo = null;
    Object [] fila = new Object[4];
    TextAutoCompleter tac_cedula_table = null;
    JTextField tb_cedula_table = null;
    String before_edit_cell = null;
    /**
     * Creates new form Add_Asistencias
     */
    public Add_vetados(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //////////////////----------------------------------------
        jTable1.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {//e.getType() == TableModelEvent.UPDATE
                    int fila = e.getFirstRow();
                    int fila_f = e.getLastRow();
                    int columna = e.getColumn();
                    //System.out.println("Celda modificada: "+fila+","+fila_f+","+columna+",Valor: "+jTable1.getValueAt(fila, columna).toString().trim());
                    if (columna == 0) {
                        if (check_cedula(jTable1.getValueAt(fila, columna))) {
                            jTable1.setValueAt(get_name_empleado(jTable1.getValueAt(fila, columna)), fila, 1);
                        }
//                        if (check_empleador(t_empleador.getText())) {
//                            check_empleado(fila,columna,jTable1.getValueAt(fila, columna).toString().trim(),t_empleador.getText());
//                        }
                    }else{
                        return;
                    }
                    
                }
            }
        });
        //////////////////----------------------------------------
        jTable1.setDefaultRenderer (Object.class, new CellRender_vetados());
        jTable1.addKeyListener(new KeyAdapter() {
        
        @Override
        public void keyReleased(KeyEvent evt) {
//            try {
//                char a=evt.getKeyChar();
//                int b=evt.getKeyCode();
//                System.out.println("Key Released Table: '"+a+"'("+b+")");
//            }catch(Exception ev){
//                ev.printStackTrace();
//            }
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
                        if (jTable1.getModel().isCellEditable(jTable1.getSelectedRow(),jTable1.getSelectedColumn())) {
                            modelo = (DefaultTableModel) jTable1.getModel();
                            String aux = (String)modelo.getValueAt(jTable1.getSelectedRow(), jTable1.getSelectedColumn());
                            if (aux!=null) {
                                if (aux.length()>0) {
                                    before_edit_cell= aux;
                                    modelo.setValueAt("",jTable1.getSelectedRow(),jTable1.getSelectedColumn());
                                    jTable1.setModel(modelo);
                                    jTable1.requestFocus();
                                }
                            }else{
                                before_edit_cell = "";
                            }
                        }
                    }else{
                        if(a==KeyEvent.VK_DELETE) {
                            if (jTable1.getModel().isCellEditable(jTable1.getSelectedRow(),jTable1.getSelectedColumn())) {
                                modelo = (DefaultTableModel) jTable1.getModel();
                                modelo.setValueAt("",jTable1.getSelectedRow(),jTable1.getSelectedColumn());
                                jTable1.setModel(modelo);
                                before_edit_cell = "";
                                if (jTable1.isEditing()){
                                    jTable1.getCellEditor().stopCellEditing();
                                }
                                evt.consume();
                            }

                        }else{
                            if(a==KeyEvent.VK_ESCAPE) {
                                modelo = (DefaultTableModel) jTable1.getModel();
                                String aux = (String)modelo.getValueAt(jTable1.getSelectedRow(), jTable1.getSelectedColumn());
                                if (aux!=null) {
                                    if (jTable1.isEditing()){
                                        modelo.setValueAt(before_edit_cell,jTable1.getSelectedRow(),jTable1.getSelectedColumn());
                                        jTable1.setModel(modelo);
                                        jTable1.getCellEditor().cancelCellEditing();
                                        evt.setKeyCode(10);
                                    }else{
                                        evt.consume();
                                    }
                                    evt.consume();
                                }else{
                                    if (jTable1.isEditing()){
                                        jTable1.getCellEditor().cancelCellEditing();
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
        screenSize = Main.getsize();
        this.setSize(screenSize.width, screenSize.height-10);
        this.setLocationRelativeTo(null);
        tb_cedula_table = new JTextField();
        init();
        cb_cedula();
        tac_cedula_table.setMode(0);
        InputMap map1 = jTable1.getInputMap(jTable1.WHEN_FOCUSED);
        map1.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK), "null");

        jTable1.getColumnModel().getColumn(0).setCellEditor(new MyTableCellEditorDate(tb_cedula_table));
        jTable1.getColumnModel().getColumn(2).setCellEditor(new MyTableCellEditorDate(true));
    }
    public void init(){
        
        tac_cedula_table = new TextAutoCompleter(tb_cedula_table, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            jTable1.setValueAt(get_name_empleado(selectedItem), jTable1.getSelectedRow(), 1);
        }
        });

        
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
                char a=evt.getKeyChar();
                int b=evt.getKeyCode();
                System.out.println("Key Released Cell: '"+a+"'("+b+")");
                if (par_date) {
                    autocomplete_date_released(b);
                }
        }

        @Override
        public void keyTyped(KeyEvent evt) {
            
        }

        @Override
        public void keyPressed(KeyEvent evt) {
            char a=evt.getKeyChar();
            int b=evt.getKeyCode();
            System.out.println("Key Pressed Cell: '"+a+"'("+b+")");
            if (evt.isControlDown()&& evt.getKeyChar() != 'v' && evt.getKeyCode() == 86) {
                                
            } else {
                if (evt.isControlDown()&& evt.getKeyChar() != 'c' && evt.getKeyCode() == 67) {
                                
                }else {
                    if (par_date) {
                        //autocomplete_date_pressed(b);
                    }else{
                        if((b>=96 & b<=105)) { //(b>=65 & b<=90)
                            if (jTable1.getModel().isCellEditable(jTable1.getSelectedRow(),jTable1.getSelectedColumn())) {
                                modelo = (DefaultTableModel) jTable1.getModel();
                                String aux = (String)modelo.getValueAt(jTable1.getSelectedRow(), jTable1.getSelectedColumn());
                                if (aux!=null) {
                                    if (aux.length()>0) {
                                        before_edit_cell= aux;
                                        modelo.setValueAt("",jTable1.getSelectedRow(),jTable1.getSelectedColumn());
                                        jTable1.setModel(modelo);
                                        jTable1.requestFocus();
                                    }
                                }else{
                                    before_edit_cell = "";
                                }
                            }
                        }else{
                            System.out.println("Key Presed Cell 1: '"+a+"'("+b+")");
                            if(a==KeyEvent.VK_DELETE) {
                                if (jTable1.getModel().isCellEditable(jTable1.getSelectedRow(),jTable1.getSelectedColumn())) {
                                    if (!jTable1.isEditing()){
                                        modelo = (DefaultTableModel) jTable1.getModel();
                                        modelo.setValueAt("",jTable1.getSelectedRow(),jTable1.getSelectedColumn());
                                        jTable1.setModel(modelo);
                                        before_edit_cell = "";
                                        jTable1.getCellEditor().stopCellEditing();
                                    }
                                }
                            }else{
                                System.out.println("Key Presed Cell 2: '"+a+"'("+b+")");
                                if(a==KeyEvent.VK_ESCAPE) {
                                    modelo = (DefaultTableModel) jTable1.getModel();
                                    String aux = (String)modelo.getValueAt(jTable1.getSelectedRow(), jTable1.getSelectedColumn());
                                    if (aux!=null) {
                                        if (jTable1.isEditing()){
                                            modelo.setValueAt(before_edit_cell,jTable1.getSelectedRow(),jTable1.getSelectedColumn());
                                            jTable1.setModel(modelo);
                                            jTable1.getCellEditor().cancelCellEditing();
                                            evt.setKeyCode(10);
                                        }else{
                                            evt.consume();
                                        }
                                        evt.consume();
                                    }else{
                                        if (jTable1.isEditing()){
                                            jTable1.getCellEditor().cancelCellEditing();
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
                                                if (a==KeyEvent.VK_BACK_SPACE) {
                                                    
                                                }else{
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
            }
                        

            
        }
        });
        
        return component;
    }
//    @Override
//    public boolean isCellEditable(EventObject evt) {
//      boolean ret =true;
////        if (evt instanceof MouseEvent) {
////            int clickCount;
////            clickCount = 2;
////            ret = (((MouseEvent)evt).getClickCount() >= clickCount)&();
////        }else{
////            ret = false;
////        }
//      return ret;
//  }
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
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        bsc_cedula = new javax.swing.JComboBox<>();
        t_empleado = new javax.swing.JTextField();
        add_emp1 = new javax.swing.JButton();
        edit_emp2 = new javax.swing.JButton();
        edit_emp3 = new javax.swing.JButton();
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
        setTitle("Agregar Vetados");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos asistencia"));

        new PegarExcel(jTable1);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "ID Empleado", "Nombre empleado", "Fecha", "Motivo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(200);
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar"));
        jPanel1.setPreferredSize(new java.awt.Dimension(671, 137));

        jLabel3.setText("Empleado");

        AutoCompletion.enable(bsc_cedula);
        bsc_cedula.setEditable(true);
        bsc_cedula.setToolTipText("Ingrese la cedula del empleado");
        bsc_cedula.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                bsc_cedulaItemStateChanged(evt);
            }
        });

        t_empleado.setEditable(false);
        t_empleado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_empleado.setToolTipText("");

        add_emp1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_add.png"))); // NOI18N
        add_emp1.setToolTipText("Ingresar Empleado");
        add_emp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_emp1ActionPerformed(evt);
            }
        });

        edit_emp2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_edit.png"))); // NOI18N
        edit_emp2.setToolTipText("Editar Empleado");
        edit_emp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_emp2ActionPerformed(evt);
            }
        });

        edit_emp3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_tab_search.png"))); // NOI18N
        edit_emp3.setToolTipText("Buscar Empleado");
        edit_emp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_emp3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bsc_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(t_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(add_emp1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edit_emp2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edit_emp3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(bsc_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_emp1)
                    .addComponent(edit_emp2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edit_emp3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton10)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton11)
                .addContainerGap(15, Short.MAX_VALUE))
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
                .addContainerGap(71, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(71, Short.MAX_VALUE))
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane3)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        if (jTable1.getRowCount()>0) {
            if (jTable1.getSelectedRow()>-1) {
                modelo = (DefaultTableModel) jTable1.getModel();
                modelo.removeRow(jTable1.getSelectedRow());
                jTable1.setModel(modelo);
            }else{
                JOptionPane.showMessageDialog(this,"No se ha seleccionado ningun registro","Error",JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this,"La tabla no contiene registros","Error",JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        modelo = (DefaultTableModel) jTable1.getModel();
        if (jTable1.getRowCount()>0) {
            while(jTable1.getRowCount()!=0){
                modelo.removeRow(jTable1.getRowCount()-1);
            }
        }else{
            JOptionPane.showMessageDialog(this,"La tabla no contiene registros","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        modelo = (DefaultTableModel) jTable1.getModel();
        modelo.insertRow(jTable1.getSelectedRow()+1, fila);
        jTable1.setModel(modelo);

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(verify_data()){
            JOptionPane.showMessageDialog(this,"La informacion de la tabla esta completa","Información",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        boolean confirm = true;
        if(verify_data()){
            int conf = JOptionPane.showConfirmDialog(this,"La informacion de la tabla esta completa\nEsta seguro que desea continuar?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (conf == JOptionPane.YES_OPTION) {
                modelo = (DefaultTableModel)jTable1.getModel(); 
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    try {
                        r = con.s.executeQuery ("SELECT * FROM t_vetados WHERE ID_EMPLEADO ="+modelo.getValueAt(i, 0)+"");
                        if(r.next()){
                            JOptionPane.showMessageDialog(this,"Este empleado ya se encuantra en la lista de vetados.","Error",JOptionPane.ERROR_MESSAGE);
                            confirm = confirm & false;
                            jTable1.changeSelection(i,0, false, false);
                            jTable1.requestFocus();
                            break;
                        }else{
                            con.s.executeUpdate("INSERT INTO `t_vetados` (ID_EMPLEADO,FECHA_VETADO,MOTIVO_VETADO) VALUES ("+modelo.getValueAt(i, 0)+",'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, 2).toString()))+"','"+modelo.getValueAt(i, 3)+"')");
                            modelo.removeRow(i);
                            i=i-1;
                            confirm = true;
                        }
                        
                    } catch (SQLException | HeadlessException e) {
                        con.cerrar();
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);
                        break;
                    } catch (ParseException ex) {
                        Logger.getLogger(Add_vetados.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                con.cerrar();
            }   if (confirm) {
                    JOptionPane.showMessageDialog(this,"La información ha sido ingresada correctamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
                }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void edit_emp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_emp3ActionPerformed
        // TODO add your handling code here:
        Buscar_Empleado busc_nov=new Buscar_Empleado(this,true);
        busc_nov.setLocationRelativeTo(this);
        busc_nov.setVisible(true);
        cb_cedula();
    }//GEN-LAST:event_edit_emp3ActionPerformed

    private void edit_emp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_emp2ActionPerformed
        // TODO add your handling code here:
        Edd_Empleado edd_empleado = new Edd_Empleado(this, true);
        edd_empleado.setLocationRelativeTo(this);
        edd_empleado.setVisible(true);
        cb_cedula();
    }//GEN-LAST:event_edit_emp2ActionPerformed

    private void add_emp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_emp1ActionPerformed
        // TODO add your handling code here:
        Add_Empleado add_empleado = new Add_Empleado(this, true);
        add_empleado.setLocationRelativeTo(this);
        add_empleado.setVisible(true);
        //System.out.println("qwerty");
        cb_cedula();
    }//GEN-LAST:event_add_emp1ActionPerformed

    private void bsc_cedulaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_bsc_cedulaItemStateChanged
        // TODO add your handling code here:
        load_empleado(bsc_cedula.getSelectedItem().toString());

    }//GEN-LAST:event_bsc_cedulaItemStateChanged

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
            java.util.logging.Logger.getLogger(Add_vetados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add_vetados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add_vetados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add_vetados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Add_vetados dialog = new Add_vetados(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton add_emp1;
    private javax.swing.JComboBox<String> bsc_cedula;
    private javax.swing.JButton edit_emp2;
    private javax.swing.JButton edit_emp3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField t_empleado;
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
public String get_name_empleado(Object o){
    String ret = "";
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_EMPLEADOS WHERE ID_EMP="+o.toString().trim());
        if(r.next()){
            ret=r.getString("APELLIDO_1_EMP")+" "+r.getString("APELLIDO_2_EMP")+" "+r.getString("NOMBRE_1_EMP")+" "+r.getString("NOMBRE_2_EMP");
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        ret="";
    }
    return ret;
}
public void check_empleado(int fila, int col, String ced, String empleador){
    ArrayList<String> ret_nov = new ArrayList<String>();
    if (true) { //& !jTable1.isEditing()
        modelo = (DefaultTableModel)jTable1.getModel();
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
public boolean verify_data(){
    boolean ret=true;
    modelo = (DefaultTableModel)jTable1.getModel(); 
    if (jTable1.getRowCount()>0) {
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            if (check_cedula(modelo.getValueAt(i, 0))) {
                if (check_fecha(modelo.getValueAt(i, 2))) {
                    if (check_field(modelo.getValueAt(i, 3))) {
                        ret=true;
                    } else {
                        jTable1.changeSelection(i,1, false, false);
                        jTable1.requestFocus();
                        JOptionPane.showMessageDialog(this,"Ingrese el motivo","Error",JOptionPane.ERROR_MESSAGE);
                        ret=false;
                        break;
                    }
                }else{
                    jTable1.changeSelection(i,2, false, false);
                        jTable1.requestFocus();
                        JOptionPane.showMessageDialog(this,"Verifique la Fecha de Vetado del empleado..","Error",JOptionPane.ERROR_MESSAGE);
                        ret=false;
                        break;
                }
            }else{
                jTable1.changeSelection(i,0, false, false);
                jTable1.requestFocus();
                JOptionPane.showMessageDialog(this,"Verifique que el empleado se encuentre activo en la empresa seleccionada","Error",JOptionPane.ERROR_MESSAGE);
                ret=false;
                break;
            }
        }
        
    }else{
        JOptionPane.showMessageDialog(this,"La tabla no tiene registros","Error",JOptionPane.ERROR_MESSAGE);
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
public void load_empleado(String ced){
    if (!ced.equals("Seleccione..")) {
       Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
        try{
            r = con.s.executeQuery ("SELECT * FROM T_EMPLEADOS WHERE ID_EMP = "+ced+";");
            if(r.next()){
                String str=r.getString("NOMBRE_1_EMP")+" "+r.getString("NOMBRE_2_EMP")+" "+r.getString("APELLIDO_1_EMP")+" "+r.getString("APELLIDO_2_EMP");
                t_empleado.setText(str);
            }
            con.cerrar();
        }catch(SQLException j){
            con.cerrar();
            j.printStackTrace();
            JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
        } 
    }
    

}
public boolean check_fecha(Object fecha){
    boolean ret=false;
    if (fecha!=null) {
        if (chech_char(fecha.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
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
public void autocomplete_date_released(int b) {
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
//                    if (b==KeyEvent.VK_ENTER) {
//                        ((JTextField) jTable1.getEditorComponent()).setText(((JTextField) jTable1.getEditorComponent()).getText() + "-"+annio);
//                    }else{
                        ((JTextField) jTable1.getEditorComponent()).setText(((JTextField) jTable1.getEditorComponent()).getText() + "-"+annio);
//                    }
                    
                }
                break;
            default:
                break;
        }
    }
public void autocomplete_date_pressed(int b){
    Calendar c = new GregorianCalendar();
    String annio = Integer.toString(c.get(Calendar.YEAR));
    switch (((JTextField) jTable1.getEditorComponent()).getText().length()) {
        case 5:
//            if (b!=KeyEvent.VK_BACK_SPACE) {
//                ((JTextField) jTable1.getEditorComponent()).setText(((JTextField) jTable1.getEditorComponent()).getText() + "-");
//            }else{
//                if (b==KeyEvent.VK_ENTER) {
//                ((JTextField) jTable1.getEditorComponent()).setText(((JTextField) jTable1.getEditorComponent()).getText() + "-"+annio);
//                }
//            }
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
