/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.GUI;

import com.jm.Edicof.Clases.AutoCompletion;
import com.jm.Edicof.Clases.CellRender_Retiros;
import com.jm.Edicof.Clases.Conexion;
import com.jm.Edicof.Clases.PegarExcel_retiros;
import com.jm.Edicof.Clases.Validations;
import com.jm.Edicof.Clases.GetInfo;
import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.HeadlessException;
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
import java.util.List;
import java.util.StringTokenizer;
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
import javax.swing.table.TableModel;

/**
 *
 * @author ADMIN
 */
public class Add_Retiros extends javax.swing.JDialog {
    static Dimension screenSize = null;
    TextAutoCompleter tac_empleador = null;
    //TextAutoCompleter tac_barrio = null;
    TextAutoCompleter tac_parentesco = null;
    TextAutoCompleter tac_municipio = null;
    TextAutoCompleter tac_obra = null;
    DefaultTableModel modelo = null;
    DefaultTableModel modelo_asist = null;
    Object [] fila = new Object[21];
    Object [] fila_asist = new Object[21];
    boolean perm_cedula = true;
    boolean perm_empleador = true;
    boolean perm_tabla = false;
    //////////////////////----------------------------
    TextAutoCompleter tac_cedula_table = null;
    JTextField tb_cedula_table = null;
    TextAutoCompleter tac_empleador_table = null;
    JTextField tb_empleador_table = null;
    TextAutoCompleter tac_barrio_table = null;
    JTextField tb_barrio_table = null;
    TextAutoCompleter tac_parentesco_table = null;
    JTextField tb_parentesco_table = null;
    TextAutoCompleter tac_municipio_table = null;
    JTextField tb_municipio_table = null;
    TextAutoCompleter tac_obra_table = null;
    JTextField tb_obra_table = null;
    ////////////////////-------------------------------
    String before_edit_cell = null;
    JTable asist = new javax.swing.JTable();
    //////////////////////----------------------------
    int row_cedula=0;
    int row_empleado=1;
    int row_empleador = 2;
    int row_f_ingreso = 3;
    int row_f_retiro = 4;
    int row_salario=5;
    int row_eps=6;
    int row_afp=7;
    int row_arl=8;
    int row_ccf=9;
    int row_obra=10;
    int row_obs=11;
    int row_tipo=12;
    //static DefaultTableModel modelo = new DefaultTableModel();
    /**
     * Creates new form Add_Preingresos
     */
    public Add_Retiros(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //////////////////----------------------------------------
        jTable1.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    TableModel model = (TableModel)e.getSource();
                    String columnName = model.getColumnName(column);
                    Object data = model.getValueAt(row, column);
                    if (columnName.equals("Cedula*")) {
                        System.out.println("Registro nuevo: "+data);
                        check_empleado(data,model,row);
                        
                    }
                    
                }
                //System.out.println(e);
            }
        });
        //////////////////----------------------------------------
        jTable1.setDefaultRenderer (Object.class, new CellRender_Retiros());
        //////////////////----------------------------------------
        screenSize = Main.getsize();
        this.setSize(screenSize.width, screenSize.height-10);
        this.setLocationRelativeTo(null);
        /////////////////-----------------------------------------
        tb_cedula_table = new JTextField();
        tb_empleador_table = new JTextField();
        tb_barrio_table = new JTextField();
        tb_parentesco_table = new JTextField();
        tb_municipio_table = new JTextField();
        tb_obra_table = new JTextField();
        /////////////////-----------------------------------------
        init();
        cb_cedula();
        cb_empleador();
        tac_empleador();
        //tac_barrio();
        tac_parentesco();
        tac_municipio();
        tac_obra();
        tac_empleador.setMode(0);
        //tac_barrio.setMode(0);
        tac_parentesco.setMode(0);
        tac_municipio.setMode(0);
        tac_obra.setMode(0);
        //////////////---------------------------------------
        tac_cedula_table.setMode(0);
        tac_empleador_table.setMode(0);
        tac_barrio_table.setMode(0);
        tac_parentesco_table.setMode(0);
        tac_municipio_table.setMode(0);
        tac_obra_table.setMode(0);
        //////////////---------------------------------------
        InputMap map1 = bsc_cedula.getInputMap(bsc_cedula.WHEN_FOCUSED);
        map1.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK), "null");
        InputMap map2 = jTable1.getInputMap(jTable1.WHEN_FOCUSED);
        map2.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK), "null");
        /////////////////-----------------------------------------
        //jTable1.getColumnModel().getColumn(0).setCellEditor(new MyTableCellEditorDate(tb_cedula_table,true));
        jTable1.getColumnModel().getColumn(3).setCellEditor(new MyTableCellEditorDate(true));
//        
//        TableColumn column0 = jTable1.getColumnModel().getColumn(0);
//        TableCellEditor tce0 = new DefaultCellEditor(tb_cedula_table);
//        column0.setCellEditor(tce0);
//        TableColumn column1 = jTable1.getColumnModel().getColumn(1);
//        TableCellEditor tce1 = new DefaultCellEditor(tb_empleador_table);
//        column1.setCellEditor(tce1);
//        jTable1.getColumnModel().getColumn(3).setCellEditor(new MyTableCellEditorDate());
//        TableColumn column8 = jTable1.getColumnModel().getColumn(8);
//        TableCellEditor tce8 = new DefaultCellEditor(tb_barrio_table);
//        column8.setCellEditor(tce8);
//        TableColumn column13 = jTable1.getColumnModel().getColumn(13);
//        TableCellEditor tce13 = new DefaultCellEditor(tb_parentesco_table);
//        column13.setCellEditor(tce13);
//        TableColumn column9 = jTable1.getColumnModel().getColumn(9);
//        TableCellEditor tce9 = new DefaultCellEditor(tb_municipio_table);
//        column9.setCellEditor(tce9);
//        TableColumn column6 = jTable1.getColumnModel().getColumn(6);
//        TableCellEditor tce6 = new DefaultCellEditor(tb_obra_table);
//        column6.setCellEditor(tce6); 
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
        //((JTextField) component).setEditable(false);
        ((JTextField) component).setText((String) value);
        ((JTextField) component).addKeyListener(new KeyAdapter(){
        @Override
        public void keyReleased(KeyEvent e) {
            try {
                char a=e.getKeyChar();
                int b=e.getKeyCode();
                System.out.println("Key Released Cell: '"+a+"'("+b+")");
                if (par_date) {
                    autocomplete_date_released(b);
                }
            }catch(Exception ev){
                ev.printStackTrace();
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            char a=e.getKeyChar();
            int b=e.getKeyCode();
            //System.out.println("Key Pressed Editor: '"+a+"'("+b+")");
            if (par_date) {
                autocomplete_date_pressed(b);
            }
            if((b>=96 & b<=105)|(b>=65 & b<=90)) {
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
                        e.consume();
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
                                e.setKeyCode(10);
                            }else{
                                e.consume();
                            }
                            e.consume();
                        }else{
                            if (jTable1.isEditing()){
                                jTable1.getCellEditor().cancelCellEditing();
                            }
                            e.consume();
                        }
                    }else{
                        if (a==KeyEvent.VK_TAB) {
                            if (par_list) {
                                e.setKeyCode(10);
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
    public void init(){
        tac_empleador = new TextAutoCompleter(t_empleador, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            load_data_empleador(selectedItem.toString());
        }
        });
//        tac_barrio = new TextAutoCompleter(t_barrio, new AutoCompleterCallback() {
//        @Override
//        public void callback(Object selectedItem) {
//            //load_data_barrio(selectedItem.toString());
//        }
//        });
        tac_parentesco = new TextAutoCompleter(t_parentesco, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            //load_data_barrio(selectedItem.toString());
        }
        });
        tac_municipio = new TextAutoCompleter(t_municipio, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            //load_data_barrio(selectedItem.toString());
        }
        });
        tac_obra = new TextAutoCompleter(t_obra, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            //load_data_barrio(selectedItem.toString());
        }
        });
        tac_cedula_table = new TextAutoCompleter(tb_cedula_table, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            //load_data_barrio(selectedItem.toString());
        }
        });
        tac_empleador_table = new TextAutoCompleter(tb_empleador_table, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            //load_data_barrio(selectedItem.toString());
        }
        });
        tac_barrio_table = new TextAutoCompleter(tb_barrio_table, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            //load_data_barrio(selectedItem.toString());
        }
        });
        tac_parentesco_table = new TextAutoCompleter(tb_parentesco_table, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            //load_data_barrio(selectedItem.toString());
        }
        });
        tac_municipio_table = new TextAutoCompleter(tb_municipio_table, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            //load_data_barrio(selectedItem.toString());
        }
        });
        tac_obra_table = new TextAutoCompleter(tb_obra_table, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            //load_data_barrio(selectedItem.toString());
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        add_row = new javax.swing.JMenuItem();
        del_row = new javax.swing.JMenuItem();
        del_table = new javax.swing.JMenuItem();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        t_empleado = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        add_emp = new javax.swing.JButton();
        edit_emp = new javax.swing.JButton();
        bsc_cedula = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        bsc_empleador = new javax.swing.JComboBox<>();
        add_empresa = new javax.swing.JButton();
        edit_empresa = new javax.swing.JButton();
        t_empleador = new javax.swing.JTextField();
        t_municipio = new javax.swing.JTextField();
        add_barrio2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        t_parentesco = new javax.swing.JTextField();
        add_par = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        t_obra = new javax.swing.JTextField();
        add_barrio3 = new javax.swing.JButton();
        add_barrio4 = new javax.swing.JButton();
        add_barrio5 = new javax.swing.JButton();
        add_par1 = new javax.swing.JButton();
        edit_emp1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        cr_ret_asist = new javax.swing.JCheckBox();
        an_rest_f_retiro = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
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
        setTitle("Agregar Retiros");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar"));
        jPanel2.setToolTipText("Ingresar Parentesco");

        t_empleado.setEditable(false);
        t_empleado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_empleado.setToolTipText("");

        jLabel1.setText("Empleado");

        add_emp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_add.png"))); // NOI18N
        add_emp.setToolTipText("Ingresar Empleado");
        add_emp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_empActionPerformed(evt);
            }
        });

        edit_emp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_edit.png"))); // NOI18N
        edit_emp.setToolTipText("Editar Empleado");
        edit_emp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_empActionPerformed(evt);
            }
        });

        AutoCompletion.enable(bsc_cedula);
        bsc_cedula.setEditable(true);
        bsc_cedula.setToolTipText("Ingrese la cedula del empleado");
        bsc_cedula.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                bsc_cedulaItemStateChanged(evt);
            }
        });

        jLabel2.setText("Empleador");

        AutoCompletion.enable(bsc_empleador);
        bsc_empleador.setEditable(true);
        bsc_empleador.setToolTipText("Ingrese la cedula del empleador");
        bsc_empleador.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                bsc_empleadorItemStateChanged(evt);
            }
        });

        add_empresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/building_add.png"))); // NOI18N
        add_empresa.setToolTipText("Ingresar Empleador");
        add_empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_empresaActionPerformed(evt);
            }
        });

        edit_empresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/building_edit.png"))); // NOI18N
        edit_empresa.setToolTipText("Editar Empleador");
        edit_empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_empresaActionPerformed(evt);
            }
        });

        t_empleador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_empleador.setToolTipText("Ingrese el nombre de la empresa");

        t_municipio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_municipio.setToolTipText("Ingrese el nombre de la empresa");

        add_barrio2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map_add.png"))); // NOI18N
        add_barrio2.setToolTipText("Ingresar Municipio");
        add_barrio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_barrio2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Buscar Municipio");

        jLabel5.setText("Parentesco");

        t_parentesco.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_parentesco.setToolTipText("Ingrese el nombre de la empresa");

        add_par.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_add.png"))); // NOI18N
        add_par.setToolTipText("Ingresar Parentesco");
        add_par.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_parActionPerformed(evt);
            }
        });

        jLabel6.setText("Buscar Obra");

        t_obra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_obra.setToolTipText("Ingrese el nombre de la empresa");

        add_barrio3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/brick_add.png"))); // NOI18N
        add_barrio3.setToolTipText("Ingresar Obra");
        add_barrio3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_barrio3ActionPerformed(evt);
            }
        });

        add_barrio4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map_edit.png"))); // NOI18N
        add_barrio4.setToolTipText("Editar Municipio");
        add_barrio4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_barrio4ActionPerformed(evt);
            }
        });

        add_barrio5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/brick_edit.png"))); // NOI18N
        add_barrio5.setToolTipText("Editar Obra");
        add_barrio5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_barrio5ActionPerformed(evt);
            }
        });

        add_par1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_edit.png"))); // NOI18N
        add_par1.setToolTipText("Editar Parentesco");
        add_par1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_par1ActionPerformed(evt);
            }
        });

        edit_emp1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_tab_search.png"))); // NOI18N
        edit_emp1.setToolTipText("Buscar Empleado");
        edit_emp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_emp1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bsc_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(bsc_empleador, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t_empleador)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(t_empleado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(add_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(14, 14, 14)
                                .addComponent(t_parentesco, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(add_par, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(add_par1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(t_obra, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(t_municipio)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(add_barrio2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(add_barrio3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(add_barrio4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(add_barrio5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(add_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edit_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(edit_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edit_emp1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(bsc_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_emp)
                    .addComponent(edit_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edit_emp1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(bsc_empleador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_empresa)
                    .addComponent(edit_empresa)
                    .addComponent(t_empleador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(t_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_barrio2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_barrio4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(t_parentesco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_par, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(t_obra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_barrio3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_barrio5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_par1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Herramientas"));
        jPanel3.setPreferredSize(new java.awt.Dimension(600, 112));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_row_delete.png"))); // NOI18N
        jButton3.setText("Eliminar Fila");
        jButton3.setToolTipText("Eliminar Fila seleccionada");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_refresh.png"))); // NOI18N
        jButton4.setText("Limpiar tabla");
        jButton4.setToolTipText("Eliminar todas las Filas");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        cr_ret_asist.setText("Cruzar retiros con asistencias");

        an_rest_f_retiro.setText("Anular restriccion fecha retiro");
        an_rest_f_retiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                an_rest_f_retiroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6))
                    .addComponent(an_rest_f_retiro)
                    .addComponent(cr_ret_asist))
                .addContainerGap(7, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addGap(19, 19, 19)
                .addComponent(cr_ret_asist)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(an_rest_f_retiro)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Información de pre-ingresos"));

        new PegarExcel_retiros(jTable1);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Cedula*", "Empleado", "Empleador (Nombre)", "F Ingreso (DD-MM-AAAA)", "F Retiro (DD-MM-AAAA)", "Salario", "EPS (Nombre)", "AFP (Nombre)", "ARL (Nombre)", "CCF (Nombre)", "Obra", "Observaciones*", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, true, false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setToolTipText("qwerty");
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(80);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(80);
            jTable1.getColumnModel().getColumn(1).setMinWidth(250);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(250);
            jTable1.getColumnModel().getColumn(2).setMinWidth(250);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(250);
            jTable1.getColumnModel().getColumn(3).setMinWidth(150);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(150);
            jTable1.getColumnModel().getColumn(4).setMinWidth(150);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(150);
            jTable1.getColumnModel().getColumn(5).setMinWidth(100);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(5).setMaxWidth(100);
            jTable1.getColumnModel().getColumn(6).setMinWidth(120);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(6).setMaxWidth(120);
            jTable1.getColumnModel().getColumn(7).setMinWidth(120);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(7).setMaxWidth(120);
            jTable1.getColumnModel().getColumn(8).setMinWidth(120);
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(8).setMaxWidth(120);
            jTable1.getColumnModel().getColumn(9).setMinWidth(120);
            jTable1.getColumnModel().getColumn(9).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(9).setMaxWidth(120);
            jTable1.getColumnModel().getColumn(10).setMinWidth(200);
            jTable1.getColumnModel().getColumn(10).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(10).setMaxWidth(200);
            jTable1.getColumnModel().getColumn(11).setMinWidth(250);
            jTable1.getColumnModel().getColumn(11).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(11).setMaxWidth(250);
            jTable1.getColumnModel().getColumn(12).setMinWidth(150);
            jTable1.getColumnModel().getColumn(12).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(12).setMaxWidth(150);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancel_1.png"))); // NOI18N
        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void edit_empActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_empActionPerformed
        // TODO add your handling code here:
        Edd_Empleado edd_empleado = new Edd_Empleado(this, true);
        edd_empleado.setLocationRelativeTo(this);
        edd_empleado.setVisible(true);
        cb_cedula();

    }//GEN-LAST:event_edit_empActionPerformed

    private void add_empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_empresaActionPerformed
        // TODO add your handling code here:
        Add_Empresa add_empresa = new Add_Empresa(this, true);
        add_empresa.setLocationRelativeTo(this);
        add_empresa.setVisible(true);
        cb_empleador();
    }//GEN-LAST:event_add_empresaActionPerformed

    private void edit_empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_empresaActionPerformed
        // TODO add your handling code here:
        Edd_Empresa edd_empresa = new Edd_Empresa(this, true, "");
        edd_empresa.setLocationRelativeTo(this);
        edd_empresa.setVisible(true);
        cb_empleador();
    }//GEN-LAST:event_edit_empresaActionPerformed

    private void bsc_empleadorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_bsc_empleadorItemStateChanged
        // TODO add your handling code here:
        if (perm_empleador) {
            load_empleador(bsc_empleador.getSelectedItem().toString());
        }

    }//GEN-LAST:event_bsc_empleadorItemStateChanged

    private void bsc_cedulaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_bsc_cedulaItemStateChanged
        // TODO add your handling code here:
        if (perm_cedula) {
            load_empleado(bsc_cedula.getSelectedItem().toString());
        }

    }//GEN-LAST:event_bsc_cedulaItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(verify_data()){
            JOptionPane.showMessageDialog(this,"La informacion de la tabla esta completa","Información",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void add_barrio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_barrio2ActionPerformed
        // TODO add your handling code here:
        Add_Municipio add_mun=new Add_Municipio(this,true);
        add_mun.setLocationRelativeTo(this);
        add_mun.setVisible(true);
    }//GEN-LAST:event_add_barrio2ActionPerformed

    private void add_parActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_parActionPerformed
        // TODO add your handling code here:
        Add_Parentesco add_par = new Add_Parentesco(this, true);
        add_par.setLocationRelativeTo(this);
        add_par.setVisible(true);
    }//GEN-LAST:event_add_parActionPerformed

    private void add_barrio3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_barrio3ActionPerformed
        // TODO add your handling code here:
        Add_Obra add_obra = new Add_Obra(this, true);
        add_obra.setLocationRelativeTo(this);
        add_obra.setVisible(true);
    }//GEN-LAST:event_add_barrio3ActionPerformed

    private void add_empActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_empActionPerformed
        // TODO add your handling code here:
        Add_Empleado add_empleado = new Add_Empleado(this, true);
        add_empleado.setLocationRelativeTo(this);
        add_empleado.setVisible(true);
        //System.out.println("qwerty");
        cb_cedula();
    }//GEN-LAST:event_add_empActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
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
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        modelo = (DefaultTableModel) jTable1.getModel();
        modelo.insertRow(jTable1.getSelectedRow()+1, fila);
        jTable1.setModel(modelo);
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        modelo = (DefaultTableModel) jTable1.getModel();
        if (jTable1.getRowCount()>0) {
            //System.out.println("Filas: "+jTable1.getRowCount());
            int j = jTable1.getRowCount();
            for (int i = 0; i < j; i++) {
                //System.out.println("q");
                modelo.removeRow(jTable1.getRowCount()-1);
                jTable1.setModel(modelo);
            }
        }else{
            JOptionPane.showMessageDialog(this,"La tabla no contiene registros","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void add_barrio4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_barrio4ActionPerformed
        // TODO add your handling code here:
        Edd_Municipio edd_mun=new Edd_Municipio(this,true);
        edd_mun.setLocationRelativeTo(this);
        edd_mun.setVisible(true);
    }//GEN-LAST:event_add_barrio4ActionPerformed

    private void add_barrio5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_barrio5ActionPerformed
        // TODO add your handling code here:
        Edd_Obra edd_obra = new Edd_Obra(this, true);
        edd_obra.setLocationRelativeTo(this);
        edd_obra.setVisible(true);
    }//GEN-LAST:event_add_barrio5ActionPerformed

    private void add_par1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_par1ActionPerformed
        // TODO add your handling code here:
        Edd_Parentesco edd_parentesco = new Edd_Parentesco(this, true);
        edd_parentesco.setLocationRelativeTo(this);
        edd_parentesco.setVisible(true);
    }//GEN-LAST:event_add_par1ActionPerformed

    private void edit_emp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_emp1ActionPerformed
        // TODO add your handling code here:
        Buscar_Empleado busc_nov=new Buscar_Empleado(this,true);
        busc_nov.setLocationRelativeTo(this);
        busc_nov.setVisible(true);
        cb_cedula();

    }//GEN-LAST:event_edit_emp1ActionPerformed

    private void add_rowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_rowActionPerformed
        // TODO add your handling code here:
        modelo = (DefaultTableModel) jTable1.getModel();
        modelo.insertRow(jTable1.getSelectedRow()+1, fila);
        jTable1.setModel(modelo);
    }//GEN-LAST:event_add_rowActionPerformed

    private void del_rowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_del_rowActionPerformed
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
    }//GEN-LAST:event_del_rowActionPerformed

    private void del_tableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_del_tableActionPerformed
        // TODO add your handling code here:
        modelo = (DefaultTableModel) jTable1.getModel();
        if (jTable1.getRowCount()>0) {
            //System.out.println("Filas: "+jTable1.getRowCount());
            int j = jTable1.getRowCount();
            for (int i = 0; i < j; i++) {
                //System.out.println("q");
                modelo.removeRow(jTable1.getRowCount()-1);
                jTable1.setModel(modelo);
            }
        }else{
            JOptionPane.showMessageDialog(this,"La tabla no contiene registros","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_del_tableActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
    
    char a=evt.getKeyChar();
    int b=evt.getKeyCode();

    if (jTable1.getEditingColumn()==3) {
        autocomplete_date_pressed(b);
    }
    if((b>=96 & b<=105)|(b>=65 & b<=90)) {
//        System.out.println("alpha");
        if (jTable1.getModel().isCellEditable(jTable1.getSelectedRow(),jTable1.getSelectedColumn())) {
            modelo = (DefaultTableModel) jTable1.getModel();
            String aux = (String)modelo.getValueAt(jTable1.getSelectedRow(), jTable1.getSelectedColumn());
            if (aux!=null) {
    //            System.out.println("Longitud: "+aux.length());
                if (aux.length()>0) {
                    before_edit_cell= aux;
    //                System.out.println("Nuevo valor de la celda: "+before_edit_cell);
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
//            System.out.println("DEL");
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
//                System.out.println("ESC");
                modelo = (DefaultTableModel) jTable1.getModel();
                String aux = (String)modelo.getValueAt(jTable1.getSelectedRow(), jTable1.getSelectedColumn());
//                System.out.println("Antes: "+aux);
                if (aux!=null) {
//                    System.out.println("aux no null");
                    if (jTable1.isEditing()){
//                        System.out.println("recuperando dato");
                        modelo.setValueAt(before_edit_cell,jTable1.getSelectedRow(),jTable1.getSelectedColumn());
                        jTable1.setModel(modelo);
                        jTable1.getCellEditor().cancelCellEditing();
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
            }
        }
    }
    }//GEN-LAST:event_jTable1KeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        perm_tabla=false;
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        boolean confirm = true;
        List asist = new ArrayList();
        if(verify_data()){
            int conf = JOptionPane.showConfirmDialog(this,"La informacion de la tabla esta completa\nEsta seguro que desea continuar?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (conf == JOptionPane.YES_OPTION) {
                modelo = (DefaultTableModel)jTable1.getModel(); 
                jTable1.setModel(modelo);
                cr_ret_asist.setEnabled(false);
                asist.clear();
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r=null;
                try {
                    for (int i = 0; i < jTable1.getRowCount(); i++) {
                        if (!cr_ret_asist.isSelected() & new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_retiro).toString()).compareTo(get_last_asist(modelo.getValueAt(i, row_cedula).toString())) >= 0) {
                            if (modelo.getValueAt(i, row_tipo).toString().equals("INGRESO")) {
                                con.s.executeUpdate("UPDATE `t_novedades` "
                                        + "SET `F_REGISTRO`='"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"', "
                                        + "`FECHA_RETIRO`='"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_retiro).toString()))+"', "
                                        + "`OBS_NOV`='RETIRO>USR:"+Main.login.getText()+">FR:"+new SimpleDateFormat("dd-MM-yyyy").format(new Date())+">FRET:"+modelo.getValueAt(i, row_f_retiro).toString()+","+modelo.getValueAt(i, row_obs).toString().toUpperCase()+"', "
                                        + "`ID_TIPO`=2 "
                                        + "WHERE ID_EMPLEADO="+modelo.getValueAt(i, row_cedula)+" "
                                        + "AND ID_EMPRESA='"+GetInfo.get_id_empleador(modelo.getValueAt(i, row_empleador))+"' "
                                        + "AND FECHA_INGRESO='"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_ingreso).toString()))+"' "
                                        + "AND FECHA_RETIRO='1900-01-01' "
                                        + "AND ID_TIPO=1");
                                con.s.executeUpdate("INSERT INTO `t_registro` "
                                        + "(ID_EMPLEADO,"
                                        + "ID_EMPRESA,"
                                        + "F_INGRESO,"
                                        + "F_RETIRO,"
                                        + "ID_TIPO, "
                                        + "REGISTRO, "
                                        + "F_REGISTRO, "
                                        + "FECHA, "
                                        + "ID_USUARIO) "
                                        + "VALUES ("+modelo.getValueAt(i, row_cedula)+","
                                        + "'"+GetInfo.get_id_empleador(modelo.getValueAt(i, row_empleador))+"',"
                                        + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_ingreso).toString()))+"',"
                                        + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_retiro).toString()))+"',"
                                        + "2,"
                                        + "'RETIRO',"
                                        + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"',"
                                        + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_retiro).toString()))+"',"
                                        + "'"+Main.id_usuario+"')");
                            }else if (modelo.getValueAt(i, row_tipo).toString().equals("BLOQUEADO")) {
                                con.s.executeUpdate("UPDATE `t_novedades` "
                                        + "SET `F_REGISTRO`='"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"', "
                                        + "`FECHA_RETIRO`='"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_retiro).toString()))+"', "
                                        + "`OBS_NOV`='RETIRO>USR:"+Main.login.getText()+">FR:"+new SimpleDateFormat("dd-MM-yyyy").format(new Date())+">FRET:"+modelo.getValueAt(i, row_f_retiro).toString()+","+modelo.getValueAt(i, row_obs).toString().toUpperCase()+"', "
                                        + "`ID_TIPO`=6 "
                                        + "WHERE ID_EMPLEADO="+modelo.getValueAt(i, row_cedula)+" "
                                        + "AND ID_EMPRESA='"+GetInfo.get_id_empleador(modelo.getValueAt(i, row_empleador))+"' "
                                        + "AND FECHA_INGRESO='"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_ingreso).toString()))+"' "
                                        + "AND FECHA_RETIRO='1900-01-01' "
                                        + "AND ID_TIPO=4");
                                con.s.executeUpdate("INSERT INTO `t_registro` "
                                        + "(ID_EMPLEADO,"
                                        + "ID_EMPRESA,"
                                        + "F_INGRESO,"
                                        + "F_RETIRO,"
                                        + "ID_TIPO, "
                                        + "REGISTRO, "
                                        + "F_REGISTRO, "
                                        + "FECHA, "
                                        + "ID_USUARIO) "
                                        + "VALUES ("+modelo.getValueAt(i, row_cedula)+","
                                        + "'"+GetInfo.get_id_empleador(modelo.getValueAt(i, row_empleador))+"',"
                                        + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_ingreso).toString()))+"',"
                                        + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_retiro).toString()))+"',"
                                        + "6,"
                                        + "'RETIRO BLOQUEADO',"
                                        + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"',"
                                        + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_retiro).toString()))+"',"
                                        + "'"+Main.id_usuario+"')");
                            }else if (modelo.getValueAt(i, row_tipo).toString().equals("EXTERNO")) {
                                con.s.executeUpdate("UPDATE `t_novedades` "
                                        + "SET `F_REGISTRO`='"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"', "
                                        + "`FECHA_RETIRO`='"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_retiro).toString()))+"', "
                                        + "`OBS_NOV`='RETIRO>USR:"+Main.login.getText()+">FR:"+new SimpleDateFormat("dd-MM-yyyy").format(new Date())+">FRET:"+modelo.getValueAt(i, row_f_retiro).toString()+","+modelo.getValueAt(i, row_obs).toString().toUpperCase()+"',"
                                        + "`ID_TIPO`=7 "
                                        + "WHERE ID_EMPLEADO="+modelo.getValueAt(i, row_cedula)+" "
                                        + "AND ID_EMPRESA='"+GetInfo.get_id_empleador(modelo.getValueAt(i, row_empleador))+"' "
                                        + "AND FECHA_INGRESO='"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_ingreso).toString()))+"' "
                                        + "AND FECHA_RETIRO='1900-01-01' "
                                        + "AND ID_TIPO=5");
                                con.s.executeUpdate("INSERT INTO `t_registro` "
                                        + "(ID_EMPLEADO,"
                                        + "ID_EMPRESA,"
                                        + "F_INGRESO,"
                                        + "F_RETIRO,"
                                        + "ID_TIPO, "
                                        + "REGISTRO, "
                                        + "F_REGISTRO, "
                                        + "FECHA, "
                                        + "ID_USUARIO) "
                                        + "VALUES ("+modelo.getValueAt(i, row_cedula)+","
                                        + "'"+GetInfo.get_id_empleador(modelo.getValueAt(i, row_empleador))+"',"
                                        + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_ingreso).toString()))+"',"
                                        + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_retiro).toString()))+"',"
                                        + "7,"
                                        + "'RETIRO EXTERNO',"
                                        + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"',"
                                        + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_retiro).toString()))+"',"
                                        + "'"+Main.id_usuario+"')");
                            }
                            modelo.removeRow(i);
                            i=i-1;
                            confirm = confirm & true;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
                    //break;
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
                if (confirm) {
                    JOptionPane.showMessageDialog(this,"La información ha sido ingresada correctamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
                    cr_ret_asist.setEnabled(true);
                }
                if(cr_ret_asist.isSelected()){
                    if (jTable1.getRowCount()>0) {
                        JOptionPane.showMessageDialog(null,"Estas novedades de retiro no se pueden registrar por restriccion de fecha de asistencia\nLa fecha de retiro es menor a la ultima asistencia registrada del empleado","Error",JOptionPane.ERROR_MESSAGE);
                        cr_ret_asist.setEnabled(true);
                    }
                }else{
                    confirm = true;
                    if (jTable1.getRowCount()>0) {
                        int q = JOptionPane.showConfirmDialog(this,"Estas novedades tienen restriccion por fecha de asistencia\n Desea registrarlas?","Verificacion",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                        if (q==JOptionPane.YES_OPTION) {
                            con.conexion();
                            r=null;
                            try {
                                for (int i = 0; i < jTable1.getRowCount(); i++) {
                                    if (modelo.getValueAt(i, row_tipo).toString().equals("INGRESO")) {
                                        con.s.executeUpdate("UPDATE `t_novedades` "
                                                + "SET `F_REGISTRO`='"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"', "
                                                + "`FECHA_RETIRO`='"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_retiro).toString()))+"', "
                                                + "`OBS_NOV`='RETIRO>USR:"+Main.login.getText()+">FR:"+new SimpleDateFormat("dd-MM-yyyy").format(new Date())+">FRET:"+modelo.getValueAt(i, row_f_retiro).toString()+","+modelo.getValueAt(i, row_obs).toString().toUpperCase()+"', "
                                                + "`ID_TIPO`=2 "
                                                + "WHERE ID_EMPLEADO="+modelo.getValueAt(i, row_cedula)+" "
                                                + "AND ID_EMPRESA='"+GetInfo.get_id_empleador(modelo.getValueAt(i, row_empleador))+"' "
                                                + "AND FECHA_INGRESO='"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_ingreso).toString()))+"' "
                                                + "AND FECHA_RETIRO='1900-01-01' "
                                                + "AND ID_TIPO=1");
                                        con.s.executeUpdate("INSERT INTO `t_registro` "
                                                + "(ID_EMPLEADO,"
                                                + "ID_EMPRESA,"
                                                + "F_INGRESO,"
                                                + "F_RETIRO,"
                                                + "ID_TIPO, "
                                                + "REGISTRO, "
                                                + "F_REGISTRO, "
                                                + "FECHA, "
                                                + "ID_USUARIO) "
                                                + "VALUES ("+modelo.getValueAt(i, row_cedula)+","
                                                + "'"+GetInfo.get_id_empleador(modelo.getValueAt(i, row_empleador))+"',"
                                                + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_ingreso).toString()))+"',"
                                                + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_retiro).toString()))+"',"
                                                + "2,"
                                                + "'RETIRO',"
                                                + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"',"
                                                + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_retiro).toString()))+"',"
                                                + "'"+Main.id_usuario+"')");
                                    }else if (modelo.getValueAt(i, row_tipo).toString().equals("BLOQUEADO")) {
                                        con.s.executeUpdate("UPDATE `t_novedades` "
                                                + "SET `F_REGISTRO`='"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"', "
                                                + "`FECHA_RETIRO`='"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_retiro).toString()))+"', "
                                                + "`OBS_NOV`='RETIRO>USR:"+Main.login.getText()+">FR:"+new SimpleDateFormat("dd-MM-yyyy").format(new Date())+">FRET:"+modelo.getValueAt(i, row_f_retiro).toString()+","+modelo.getValueAt(i, row_obs).toString().toUpperCase()+"', "
                                                + "`ID_TIPO`=6 "
                                                + "WHERE ID_EMPLEADO="+modelo.getValueAt(i, row_cedula)+" "
                                                + "AND ID_EMPRESA='"+GetInfo.get_id_empleador(modelo.getValueAt(i, row_empleador))+"' "
                                                + "AND FECHA_INGRESO='"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_ingreso).toString()))+"' "
                                                + "AND FECHA_RETIRO='1900-01-01' "
                                                + "AND ID_TIPO=4");
                                        con.s.executeUpdate("INSERT INTO `t_registro` "
                                                + "(ID_EMPLEADO,"
                                                + "ID_EMPRESA,"
                                                + "F_INGRESO,"
                                                + "F_RETIRO,"
                                                + "ID_TIPO, "
                                                + "REGISTRO, "
                                                + "F_REGISTRO, "
                                                + "FECHA, "
                                                + "ID_USUARIO) "
                                                + "VALUES ("+modelo.getValueAt(i, row_cedula)+","
                                                + "'"+GetInfo.get_id_empleador(modelo.getValueAt(i, row_empleador))+"',"
                                                + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_ingreso).toString()))+"',"
                                                + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_retiro).toString()))+"',"
                                                + "6,"
                                                + "'RETIRO BLOQUEADO',"
                                                + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"',"
                                                + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_retiro).toString()))+"',"
                                                + "'"+Main.id_usuario+"')");
                                    }else if (modelo.getValueAt(i, row_tipo).toString().equals("EXTERNO")) {
                                        con.s.executeUpdate("UPDATE `t_novedades` "
                                                + "SET `F_REGISTRO`='"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"', "
                                                + "`FECHA_RETIRO`='"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_retiro).toString()))+"', "
                                                + "`OBS_NOV`='RETIRO>USR:"+Main.login.getText()+">FR:"+new SimpleDateFormat("dd-MM-yyyy").format(new Date())+">FRET:"+modelo.getValueAt(i, row_f_retiro).toString()+","+modelo.getValueAt(i, row_obs).toString().toUpperCase()+"',"
                                                + "`ID_TIPO`=7 "
                                                + "WHERE ID_EMPLEADO="+modelo.getValueAt(i, row_cedula)+" "
                                                + "AND ID_EMPRESA='"+GetInfo.get_id_empleador(modelo.getValueAt(i, row_empleador))+"' "
                                                + "AND FECHA_INGRESO='"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_ingreso).toString()))+"' "
                                                + "AND FECHA_RETIRO='1900-01-01' "
                                                + "AND ID_TIPO=5");
                                        con.s.executeUpdate("INSERT INTO `t_registro` "
                                                + "(ID_EMPLEADO,"
                                                + "ID_EMPRESA,"
                                                + "F_INGRESO,"
                                                + "F_RETIRO,"
                                                + "ID_TIPO, "
                                                + "REGISTRO, "
                                                + "F_REGISTRO, "
                                                + "FECHA, "
                                                + "ID_USUARIO) "
                                                + "VALUES ("+modelo.getValueAt(i, row_cedula)+","
                                                + "'"+GetInfo.get_id_empleador(modelo.getValueAt(i, row_empleador))+"',"
                                                + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_ingreso).toString()))+"',"
                                                + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_retiro).toString()))+"',"
                                                + "7,"
                                                + "'RETIRO EXTERNO',"
                                                + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"',"
                                                + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_retiro).toString()))+"',"
                                                + "'"+Main.id_usuario+"')");
                                    }
                                    modelo.removeRow(i);
                                    i=i-1;
                                    confirm = confirm & true;
                                }
                            } catch (Exception e) {
                                con.cerrar();
                                e.printStackTrace();
                                JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
                                //break;
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
                            cr_ret_asist.setEnabled(true);
                            if (confirm) {
                                JOptionPane.showMessageDialog(this,"La información ha sido ingresada correctamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        cr_ret_asist.setEnabled(true);
                    }
                }
                con.cerrar();
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
        try {
            char a=evt.getKeyChar();
            int b=evt.getKeyCode();
            System.out.println("Key Released Tabla: '"+a+"'("+b+")");
            if (jTable1.getEditingColumn()==3) {
                autocomplete_date_released(b);
            }
        } catch (UnsupportedOperationException uop) {
            System.out.println(uop.getMessage());
        }catch(Exception ev){
            ev.printStackTrace();
        }
    }//GEN-LAST:event_jTable1KeyReleased

    private void an_rest_f_retiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_an_rest_f_retiroActionPerformed
        // TODO add your handling code here:
        if(!(Main.rol.getText().equalsIgnoreCase("ADMINISTRADOR") | Main.rol.getText().equalsIgnoreCase("MASTER"))){
            an_rest_f_retiro.setSelected(false);
        }
    }//GEN-LAST:event_an_rest_f_retiroActionPerformed

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
            java.util.logging.Logger.getLogger(Add_Retiros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add_Retiros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add_Retiros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add_Retiros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Add_Retiros dialog = new Add_Retiros(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton add_barrio2;
    private javax.swing.JButton add_barrio3;
    private javax.swing.JButton add_barrio4;
    private javax.swing.JButton add_barrio5;
    private javax.swing.JButton add_emp;
    private javax.swing.JButton add_empresa;
    private javax.swing.JButton add_par;
    private javax.swing.JButton add_par1;
    private javax.swing.JMenuItem add_row;
    private javax.swing.JCheckBox an_rest_f_retiro;
    private javax.swing.JComboBox<String> bsc_cedula;
    private javax.swing.JComboBox<String> bsc_empleador;
    private javax.swing.JCheckBox cr_ret_asist;
    private javax.swing.JMenuItem del_row;
    private javax.swing.JMenuItem del_table;
    private javax.swing.JButton edit_emp;
    private javax.swing.JButton edit_emp1;
    private javax.swing.JButton edit_empresa;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    public javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField t_empleado;
    private javax.swing.JTextField t_empleador;
    private javax.swing.JTextField t_municipio;
    private javax.swing.JTextField t_obra;
    private javax.swing.JTextField t_parentesco;
    // End of variables declaration//GEN-END:variables
public void check_empleado(Object emp, TableModel model_1, int row){
    ArrayList<String> ret_nov = new ArrayList<String>();
    if (true) { //& !jTable1.isEditing()
        //TableModel model = jTable1.getModel();
        if (Validations.check_cedula(model_1.getValueAt(row,0))) {
            Conexion con = new Conexion();
            con.conexion();
            ResultSet r=null;
            ResultSet r1=null;
            ResultSet r2=null;
            ResultSet r3=null;
            try {
                r = con.s.executeQuery ("SELECT COUNT(ID_EMPLEADO),ID_EMPLEADO,ID_EMPRESA,FECHA_INGRESO,FECHA_RETIRO,ID_TIPO FROM t_novedades WHERE ID_EMPLEADO = "+emp.toString()+" AND ID_TIPO IN (1,4,5)");//modelo.getValueAt(jTable1.getSelectedRow(),jTable1.getSelectedColumn()).toString().trim()
                if (r.next()) {
                    int count = r.getInt("COUNT(ID_EMPLEADO)");
                    String id_empleado = r.getString("ID_EMPLEADO");
                    String id_empresa = r.getString("ID_EMPRESA");
                    String f_ingreso = r.getString("FECHA_INGRESO");
                    String f_retiro = r.getString("FECHA_RETIRO");
                    int id_tipo = r.getInt("ID_TIPO");
                    if (count>1) {
                        ret_nov=call_sel_emp(emp.toString(),"1,4,5");
                        //System.out.println("Tamaño: "+ret_nov.size());
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
                                                    + "  INNER JOIN t_obra\n"
                                                    + "    ON (t_novedades.ID_OBRA = t_obra.ID_OBRA)\n"
                                                    + "  INNER JOIN t_tipo_novedad\n"
                                                    + "    ON (t_novedades.ID_TIPO = t_tipo_novedad.ID_TIPO)\n"
                                                    + "WHERE (t_novedades.ID_EMPLEADO = "+ret_nov.get(0)+" AND t_novedades.ID_EMPRESA = '"+ret_nov.get(1)+"' AND t_novedades.FECHA_INGRESO = '"+ret_nov.get(2)+"' AND t_novedades.FECHA_RETIRO = '"+ret_nov.get(3)+"' AND t_novedades.ID_TIPO = "+ret_nov.get(4)+")\n"
                                                    + "ORDER BY FECHA_INGRESO DESC;");
                            if(r.next()){
                                model_1.setValueAt(r.getString("NOMBRE_EMPRESA"),row,row_empleador);
                                model_1.setValueAt(r.getString("APELLIDO_1_EMP")+" "+r.getString("APELLIDO_2_EMP")+" "+r.getString("NOMBRE_1_EMP")+" "+r.getString("NOMBRE_2_EMP"),row,row_empleado);
                                model_1.setValueAt(new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(r.getString("FECHA_INGRESO"))),row,row_f_ingreso);
                                model_1.setValueAt(new SimpleDateFormat("dd-MM-yyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(r.getString("FECHA_RETIRO"))).replace("01-01-1900",""),jTable1.getRowCount()-1,row_f_retiro);
                                model_1.setValueAt(r.getString("SALARIO_NOVEDAD"),row,row_salario);
                                model_1.setValueAt(r.getString("NOMBRE_EPS"),row,row_eps);
                                model_1.setValueAt(r.getString("NOMBRE_AFP"),row,row_afp);
                                model_1.setValueAt(r.getString("ARL_NOV"),row,row_arl);
                                model_1.setValueAt(r.getString("CCF_NOV"),row,row_ccf);
                                int id_obra = r.getInt("ID_OBRA");
                                model_1.setValueAt(r.getString("OBS_NOV"),row,row_obs);
                                model_1.setValueAt(r.getString("NOMBRE_TIPO"),row,row_tipo);
                                r1 = con.s.executeQuery ("SELECT *\n" +
                                                        "FROM\n" +
                                                        "    t_obra\n" +
                                                        "    INNER JOIN t_municipios \n" +
                                                        "        ON (t_obra.ID_MUN_OBRA = t_municipios.ID_MUN)\n" +
                                                        "    INNER JOIN t_departamentos \n" +
                                                        "        ON (t_municipios.ID_DEP = t_departamentos.ID_DEP) WHERE ID_OBRA ="+id_obra);
                                if (r1.next()) {
                                    model_1.setValueAt(r1.getString("NOMBRE_OBRA")+"-"+r1.getString("NOMBRE_MUN")+"-"+r1.getString("NOMBRE_DEP"),row,row_obra);
                                }
                            }
                        }
                    }else if (count==0){
                        JOptionPane.showMessageDialog(null,"El empleado no se encuentra activo","Error",JOptionPane.ERROR_MESSAGE);
                        //model = (DefaultTableModel) jTable1.getModel();
                        for (int i = 0; i < jTable1.getColumnCount(); i++) {
                            model_1.setValueAt("",row,i);
                        }
                        //jTable1.setModel(modelo);
                    }else if (count==1) {
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
                                                    + "  INNER JOIN t_obra\n"
                                                    + "    ON (t_novedades.ID_OBRA = t_obra.ID_OBRA)\n"
                                                    + "  INNER JOIN t_tipo_novedad\n"
                                                    + "    ON (t_novedades.ID_TIPO = t_tipo_novedad.ID_TIPO)\n"
                                                    + "WHERE (t_novedades.ID_EMPLEADO = "+id_empleado+" AND t_novedades.ID_EMPRESA = '"+id_empresa+"' AND t_novedades.FECHA_INGRESO = '"+f_ingreso+"' AND t_novedades.FECHA_RETIRO = '"+f_retiro+"' AND t_novedades.ID_TIPO = "+id_tipo+")\n"
                                                    + "ORDER BY FECHA_INGRESO DESC;");
                        if(r.next()){
                            model_1.setValueAt(r.getString("NOMBRE_EMPRESA"),row,row_empleador);
                            model_1.setValueAt(r.getString("APELLIDO_1_EMP")+" "+r.getString("APELLIDO_2_EMP")+" "+r.getString("NOMBRE_1_EMP")+" "+r.getString("NOMBRE_2_EMP"),row,row_empleado);
                            model_1.setValueAt(new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(r.getString("FECHA_INGRESO"))),row,row_f_ingreso);
                            model_1.setValueAt(new SimpleDateFormat("dd-MM-yyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(r.getString("FECHA_RETIRO"))).replace("01-01-1900",""),jTable1.getRowCount()-1,row_f_retiro);
                            model_1.setValueAt(r.getString("SALARIO_NOVEDAD"),row,row_salario);
                            model_1.setValueAt(r.getString("NOMBRE_EPS"),row,row_eps);
                            model_1.setValueAt(r.getString("NOMBRE_AFP"),row,row_afp);
                            model_1.setValueAt(r.getString("ARL_NOV"),row,row_arl);
                            model_1.setValueAt(r.getString("CCF_NOV"),row,row_ccf);
                            int id_obra = r.getInt("ID_OBRA");
                            model_1.setValueAt(r.getString("OBS_NOV"),row,row_obs);
                            model_1.setValueAt(r.getString("NOMBRE_TIPO"),row,row_tipo);
                            r1 = con.s.executeQuery ("SELECT *\n" +
                                                    "FROM\n" +
                                                    "    t_obra\n" +
                                                    "    INNER JOIN t_municipios \n" +
                                                    "        ON (t_obra.ID_MUN_OBRA = t_municipios.ID_MUN)\n" +
                                                    "    INNER JOIN t_departamentos \n" +
                                                    "        ON (t_municipios.ID_DEP = t_departamentos.ID_DEP) WHERE ID_OBRA ="+id_obra);
                            if (r1.next()) {
                                model_1.setValueAt(r1.getString("NOMBRE_OBRA")+"-"+r1.getString("NOMBRE_MUN")+"-"+r1.getString("NOMBRE_DEP"),row,row_obra);
                            }
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"El empleado no se encuentra activo","Error",JOptionPane.ERROR_MESSAGE);
                    //model = (DefaultTableModel) jTable1.getModel();
                    for (int i = 0; i < jTable1.getColumnCount(); i++) {
                        model_1.setValueAt("",row,i);
                    }
                    //jTable1.setModel(modelo);
                }
                con.cerrar();
            } catch (SQLException | HeadlessException | ParseException e) {
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
                if (r1!=null) {
                    try {
                        r1.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    r1=null;
                }
                if (r2!=null) {
                    try {
                        r2.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    r2=null;
                }
                if (r3!=null) {
                    try {
                        r3.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    r3=null;
                }
                con.cerrar();
            }
        }
    }
}

public  ArrayList call_sel_emp(String c, String t){
    Sel_Empleador sel=new Sel_Empleador(this,true,c,t);
    sel.setLocationRelativeTo(this);
    sel.setVisible(true);
    return sel.getRet();
}
public void cb_cedula(){
    String before="";
    perm_cedula=false;
    if (bsc_cedula.getItemCount()>0) {
        before=bsc_cedula.getSelectedItem().toString();
    }else{
        before="Seleccione..";
    }
    tac_cedula_table.removeAllItems();
    bsc_cedula.removeAllItems();
    bsc_cedula.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r=null;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_EMPLEADOS ORDER BY ID_EMP ASC;");
        while(r.next()){
            String str=r.getString("ID_EMP");
            bsc_cedula.addItem(str);
            tac_cedula_table.addItem(str);
        }
        con.cerrar();
        perm_cedula=true;
        bsc_cedula.setSelectedItem(before);
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
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
public void cb_empleador(){
    String before = "";
    perm_empleador=false;
    if (bsc_empleador.getItemCount()>0) {
        before=bsc_empleador.getSelectedItem().toString();
    }else{
        before="Seleccione..";
    }
    
    bsc_empleador.removeAllItems();
    bsc_empleador.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r=null;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_EMPRESAS ORDER BY ID_EMPRESA ASC;");
        while(r.next()){
            String str=r.getString("ID_EMPRESA");
            bsc_empleador.addItem(str);
            
        }
        perm_empleador=true;
        bsc_empleador.setSelectedItem(before);
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
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

public void tac_empleador(){
    tac_empleador_table.removeAllItems();
    tac_empleador.removeAllItems();
    tac_empleador.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r=null;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_EMPRESAS ORDER BY NOMBRE_EMPRESA ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_EMPRESA");
            tac_empleador.addItem(str);
            tac_empleador_table.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
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

public void tac_parentesco(){
    tac_parentesco_table.removeAllItems();
    tac_parentesco.removeAllItems();
    tac_parentesco.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r=null;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_PARENTESCO ORDER BY NOMBRE_PAR ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_PAR");
            tac_parentesco.addItem(str);
            tac_parentesco_table.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
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
public void tac_municipio(){
    tac_municipio_table.removeAllItems();
    tac_municipio.removeAllItems();
    tac_municipio.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r=null;
    try{
        r = con.s.executeQuery ("SELECT *\n" +
                                "FROM\n" +
                                "    t_municipios\n" +
                                "    INNER JOIN t_departamentos \n" +
                                "        ON (t_municipios.ID_DEP = t_departamentos.ID_DEP) ORDER BY NOMBRE_MUN ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_MUN");
            String str1=r.getString("NOMBRE_DEP");
            tac_municipio.addItem(str+"-"+str1);
            tac_municipio_table.addItem(str+"-"+str1);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
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
public void tac_obra(){
    tac_obra_table.removeAllItems();
    tac_obra.removeAllItems();
    tac_obra.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r=null;
    try{
        r = con.s.executeQuery ("SELECT *\n" +
                                "FROM\n" +
                                "    t_obra\n" +
                                "    INNER JOIN t_municipios \n" +
                                "        ON (t_obra.ID_MUN_OBRA = t_municipios.ID_MUN)\n" +
                                "    INNER JOIN t_departamentos \n" +
                                "        ON (t_municipios.ID_DEP = t_departamentos.ID_DEP) ORDER BY NOMBRE_OBRA ASC");
        while(r.next()){
                String str=r.getString("NOMBRE_OBRA");
                String str1=r.getString("NOMBRE_MUN");
                String str2=r.getString("NOMBRE_DEP");
                tac_obra.addItem(str+"-"+str1+"-"+str2);
                tac_obra_table.addItem(str+"-"+str1+"-"+str2);
            }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
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

public void load_empleador(String nit){
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r=null;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_EMPRESAS WHERE ID_EMPRESA = '"+nit+"';");
        if(r.next()){
            String str=r.getString("NOMBRE_EMPRESA");
            t_empleador.setText(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
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
public void load_empleado(String ced){
    if (!ced.equals("Seleccione..")) {
       Conexion con = new Conexion();
        con.conexion();
        ResultSet r=null;
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

public void load_data_empleador(String afp){
    if (!afp.equals("Seleccione..")) {
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r=null;
        try {
            r = con.s.executeQuery ("SELECT * FROM T_EMPRESAS WHERE NOMBRE_EMPRESA = '"+afp+"'");
            if (r.next()) {
                bsc_empleador.setSelectedItem(r.getString("ID_EMPRESA"));
                t_empleador.setText(r.getString("NOMBRE_EMPRESA"));
            }
            con.cerrar();
        }catch(SQLException j){
            con.cerrar();
            j.printStackTrace();
            JOptionPane.showMessageDialog(this,j,"Error",JOptionPane.ERROR_MESSAGE);
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
    else{
        t_empleador.setText("");
        
    }
}
//public void load_data_barrio(String afp){
//    if (!afp.equals("Seleccione..")) {
//        Conexion con = new Conexion();
//        con.conexion();
//        ResultSet r;
//        try {
//            r = con.s.executeQuery ("SELECT * FROM T_BARRIO WHERE NOMBRE_BARRIO = '"+afp+"'");
//            if (r.next()) {
//                t_barrio.setText(r.getString("NOMBRE_BARRIO"));
//            }
//            con.cerrar();
//        }catch(SQLException j){
//            j.printStackTrace();
//            JOptionPane.showMessageDialog(this,j,"Error",JOptionPane.ERROR_MESSAGE);
//        }
//    }
//    else{
//        t_empleador.setText("");
//        
//    }
//}
/*
public boolean check_cedula(Object ced){
    boolean ret=false;
    if (ced!=null) {
        if (chech_char(ced.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!ced.toString().equals("")) {
                if(comprobarLong(ced.toString().trim())){
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
public boolean check_fecha(Object fecha){
    boolean ret=false;
    if (fecha!=null) {
        if (chech_char(fecha.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!fecha.toString().trim().equals("") & count_char(fecha.toString().trim(),'-')==2 & fecha.toString().length()==10) {
                int str_año=0;
                int str_mes=0;
                int str_dia=0;
                try {
                    StringTokenizer tokens=new StringTokenizer(fecha.toString().trim(),"-");
                    while(tokens.hasMoreTokens()){
                        str_dia=Integer.parseInt(tokens.nextToken().trim());
                        str_mes=Integer.parseInt(tokens.nextToken().trim());
                        str_año=Integer.parseInt(tokens.nextToken().trim());
                        System.out.println("PRUEBA: "+str_dia+"-"+str_mes+"-"+str_año);
                    }
                    if (str_dia>0 & str_dia<=31) {
                        if (str_mes>0 & str_mes<=12) {
                            if (str_año>=1900 & str_año<=2050) {
                                Calendar ahoraCal = Calendar.getInstance();
                                ahoraCal.setLenient(false);
                                ahoraCal.set(str_año,str_mes-1,1);
                                ahoraCal.set(Calendar.DATE, ahoraCal.getActualMaximum(Calendar.DATE));
                                int f = ahoraCal.get(Calendar.DAY_OF_MONTH);
                                System.out.println("Test Fecha: "+new SimpleDateFormat("dd-MM-yyyy").format(ahoraCal.getTime()));
                                if (str_dia<=f) {
                                    return true;
                                }
                            }else{
                                return false;
                            }
                        }else{
                            return false;
                        }
                    }else{
                        return false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }else{
        return false;
    }
    return false;
}
public boolean check_eps(Object eps){
    boolean ret=false;
    if (eps!=null) {
        if (chech_char(eps.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,")) {
            if (!eps.toString().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT * FROM T_EPS WHERE NOMBRE_EPS='"+eps+"'");
                    if(r.next()){
                        ret = !r.getString("NOMBRE_EPS").equals("NO ASIGNADA");
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
public boolean check_afp(Object afp){
    boolean ret=false;
    if (afp!=null) {
        if (chech_char(afp.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,")) {
            if (!afp.toString().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT * FROM T_AFP WHERE NOMBRE_AFP='"+afp+"'");
                    if(r.next()){
                        ret = !r.getString("NOMBRE_AFP").equals("NO ASIGNADA");
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
public boolean check_arl(Object arl){
    boolean ret=false;
    if (arl!=null) {
        if (chech_char(arl.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,")) {
            if (!arl.toString().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT * FROM T_ARL WHERE NOMBRE_ARL='"+arl+"'");
                    if(r.next()){
                        ret = !r.getString("NOMBRE_ARL").equals("NO ASIGNADA");
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
public boolean check_ccf(Object ccf){
    boolean ret=false;
    if (ccf!=null) {
        if (chech_char(ccf.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,")) {
            if (!ccf.toString().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT * FROM T_CCF WHERE NOMBRE_CCF='"+ccf+"'");
                    if(r.next()){
                        ret = !r.getString("NOMBRE_CCF").equals("NO ASIGNADA");
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
public boolean check_salario(Object salario){
    boolean ret=false;
    if (salario!=null) {
        if (chech_char(salario.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!salario.toString().equals("")) {
                if ( comprobarFloat(salario.toString())) {
                    ret = true;
                }
            }
        }
    }
return ret;
}
public boolean check_obra(Object obra){
    boolean ret=false;
    if (obra!=null) {
        if (chech_char(obra.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!obra.toString().trim().equals("") & count_char(obra.toString().trim(),'-')==2) {
                String str_obra="";
                String str_mun="";
                String str_dep="";
                StringTokenizer tokens=new StringTokenizer(obra.toString().trim(), "-");
                while(tokens.hasMoreTokens()){
                    str_obra=tokens.nextToken().trim();
                    str_mun=tokens.nextToken().trim();
                    str_dep=tokens.nextToken().trim();
                }
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT *\n" +
                                            "FROM\n" +
                                            "    t_obra\n" +
                                            "    INNER JOIN t_municipios \n" +
                                            "        ON (t_obra.ID_MUN_OBRA = t_municipios.ID_MUN)\n" +
                                            "    INNER JOIN t_departamentos \n" +
                                            "        ON (t_municipios.ID_DEP = t_departamentos.ID_DEP) WHERE NOMBRE_OBRA = '"+str_obra+"' AND NOMBRE_MUN ='"+str_mun+"' AND NOMBRE_DEP='"+str_dep+"'");
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
public boolean check_barrio(Object barrio){
    boolean ret=false;
    if (barrio!=null) {
        if (chech_char(barrio.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!barrio.toString().trim().equals("")){
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT * FROM T_BARRIO WHERE NOMBRE_BARRIO='"+barrio.toString().trim()+"'");
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
public boolean check_municipio(Object municipio){
    boolean ret=false;
    if (municipio!=null) {
        if (chech_char(municipio.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
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
public boolean check_parentesco(Object par){
    boolean ret=false;
    if (par!=null) {
        if (chech_char(par.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!par.toString().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT * FROM T_PARENTESCO WHERE NOMBRE_PAR='"+par+"'");
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
public boolean check_field (Object field){
boolean ret=false;
    if (field!=null) {
        if (chech_char(field.toString().trim(),"'$%&()=?¡¿/*+[]{};:<>,")) {
            if (!field.toString().equals("")) {
               ret=true;
            }
        }
    }

return ret;
}
public boolean check_field_dir (Object field){
boolean ret=false;
    if (field!=null) {
        if (chech_char(field.toString().trim(),"'$%&()=?¡¿/*+[]{};:<>,")) {
            if (!field.toString().equals("")) {
               ret=true;
            }
        }
    }

return ret;
}
public boolean check_field_mail (Object field){
boolean ret=false;
    if (field!=null) {
        if (chech_char(field.toString().trim(),"'$%&()=?¡¿/*+[]{};:<>,") & !chech_char(field.toString().trim(),"@") & !chech_char(field.toString().trim(),".") ) {
            if (!field.toString().equals("")) {
               ret=true;
            }
        }
    }

return ret;
}
public boolean comprobarFloat (String cadena){
try{
    float num = Float.parseFloat(cadena);
}catch (Exception e){
    e.printStackTrace();
    return false;
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
*/
public boolean verify_data(){
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    boolean ret=true;
    modelo = (DefaultTableModel)jTable1.getModel(); 
    if (jTable1.getRowCount()>0) {
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            if (modelo.getValueAt(i, row_obs)==null) {
                modelo.setValueAt("Sin observaciones", i, row_obs);
            }else{
                if (modelo.getValueAt(i, row_obs).toString().equals("")){
                    modelo.setValueAt("Sin observaciones", i, row_obs);
                }
            }
            if (Validations.check_cedula(modelo.getValueAt(i, row_cedula))) {
                if (Validations.check_empleador(modelo.getValueAt(i, row_empleador))){
                    if (Validations.check_fecha(modelo.getValueAt(i, row_f_ingreso))){
                        try {
                            if (Validations.check_fecha(modelo.getValueAt(i, row_f_retiro))) {
                                r = con.s.executeQuery ("SELECT * FROM `t_novedades` WHERE (ID_EMPLEADO ="+modelo.getValueAt(i, row_cedula)+" AND ID_EMPRESA='"+GetInfo.get_id_empleador(modelo.getValueAt(i, row_empleador))+"' AND FECHA_INGRESO='"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_ingreso).toString()))+"' AND FECHA_RETIRO='1900-01-01' AND ID_TIPO=2);");
                                if(!r.next()){
                                    Calendar date_today = Calendar.getInstance();
                                    date_today.setTime(new Date());
                                    date_today.set(Calendar.HOUR_OF_DAY,0);
                                    date_today.set(Calendar.MINUTE,0);
                                    date_today.set(Calendar.SECOND,0);
                                    date_today.set(Calendar.MILLISECOND,0);
                                    Calendar date_out = Calendar.getInstance();
                                    date_out.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_retiro).toString()));
                                    date_out.set(Calendar.HOUR_OF_DAY,0);
                                    date_out.set(Calendar.MINUTE,0);
                                    date_out.set(Calendar.SECOND,0);
                                    date_out.set(Calendar.MILLISECOND,0);
                                    Calendar date_in = Calendar.getInstance();
                                    date_in.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_ingreso).toString()));
                                    date_in.set(Calendar.HOUR_OF_DAY,0);
                                    date_in.set(Calendar.MINUTE,0);
                                    date_in.set(Calendar.SECOND,0);
                                    date_in.set(Calendar.MILLISECOND,0);
                                    System.out.println("Hoy: "+date_today.getTime());
                                    System.out.println("Retiro: "+date_out.getTime());
                                    System.out.println("Ingreso: "+date_in.getTime());
                                    if(date_out.getTime().compareTo(date_in.getTime())>=0){//VERIFICA SI LA FECHA DE RETIRO ES MAYOR O IGUAL A LA FECHA DE INGRESO
                                        if (date_out.get(Calendar.MONTH)<date_today.get(Calendar.MONTH)) {//VERIFICA SI LA FECHA DE RETIRO SE REGISTRA EL MES PASADO AL MES CORRIENTE
                                            Calendar date_out_low = Calendar.getInstance();
                                            int month_low=0;
                                            int year_low=0;
                                            if (date_today.get(Calendar.MONTH)==0) {
                                                month_low = 12;
                                                year_low = date_today.get(Calendar.YEAR)-1;
                                            }else{
                                                month_low = date_today.get(Calendar.MONTH);
                                                year_low = date_today.get(Calendar.YEAR);
                                            }
                                            date_out_low.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(getlastDate(month_low, year_low)));
                                            date_out_low.set(Calendar.HOUR_OF_DAY,0);
                                            date_out_low.set(Calendar.MINUTE,0);
                                            date_out_low.set(Calendar.SECOND,0);
                                            date_out_low.set(Calendar.MILLISECOND,0);
                                            Calendar date_4 = Calendar.getInstance();
                                            date_4.setTime(new SimpleDateFormat("dd-MM-yyyy").parse("04-"+(date_today.get(Calendar.MONTH)+1)+"-"+date_today.get(Calendar.YEAR)));
                                            date_4.set(Calendar.HOUR_OF_DAY,0);
                                            date_4.set(Calendar.MINUTE,0);
                                            date_4.set(Calendar.SECOND,0);
                                            date_4.set(Calendar.MILLISECOND,0);
                                            if ((date_out.getTime().compareTo(date_out_low.getTime())>=0& date_today.getTime().compareTo(date_4.getTime())<=0) || an_rest_f_retiro.isSelected()) {
                                                ret=true&ret;
                                            }else{
                                                jTable1.changeSelection(i,row_f_retiro, false, false);
                                                jTable1.requestFocus();
                                                JOptionPane.showMessageDialog(this,"Verifique que la fecha de Retiro sea mayor o igual al utimo dia del mes pasado y solo puede ser registrada los 4 primeros dias del mes corriente","Error",JOptionPane.ERROR_MESSAGE);
                                                ret=false&ret;
                                                break;
                                            }
                                        }else {
                                            if (date_out.get(Calendar.MONTH)==date_today.get(Calendar.MONTH)) {//VERIFICA SI LA FECHA DE RETIRO SE REGISTRA EL MISMO MES CORRIENTE
                                                ret=true&ret;
                                            } else {
                                                if (date_out.get(Calendar.MONTH)>date_today.get(Calendar.MONTH)) {//VERIFICA SI LA FECHA DE RETIRO SE REGISTRA EL SIGUIENTE MES AL MES CORRIENTE
                                                    Calendar date_out_high = Calendar.getInstance();
                                                    int month_high=0;
                                                    int year_high=0;
                                                    if (date_today.get(Calendar.MONTH)+1==12) {
                                                        month_high = 1;
                                                        year_high = date_today.get(Calendar.YEAR)+1;
                                                    }else{
                                                        month_high = date_today.get(Calendar.MONTH)+2;
                                                        year_high = date_today.get(Calendar.YEAR);
                                                    }
                                                    date_out_high.setTime(new SimpleDateFormat("dd-MM-yyyy").parse("01-"+month_high+"-"+year_high));
                                                    if (date_out.getTime().compareTo(date_out_high.getTime())<=0) {
                                                        ret=true&ret;
                                                        //break;
                                                    }else{
                                                        jTable1.changeSelection(i,row_f_retiro, false, false);
                                                        jTable1.requestFocus();
                                                        JOptionPane.showMessageDialog(this,"Verifique que la fecha de Retiro sea menor o igual al primer dia del mes siguiente","Error",JOptionPane.ERROR_MESSAGE);
                                                        ret=false&ret;
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }else{
                                        jTable1.changeSelection(i,row_f_retiro, false, false);
                                        jTable1.requestFocus();
                                        JOptionPane.showMessageDialog(this,"Verifique que la fecha de Retiro sea mayor o igual a la fecha de Ingreso","Error",JOptionPane.ERROR_MESSAGE);
                                        ret=false&ret;
                                        break;
                                    }
                                }
                                else{
                                    jTable1.changeSelection(i,row_cedula, false, false);
                                    jTable1.requestFocus();
                                    JOptionPane.showMessageDialog(this,"Esta novedad de Retiro que intenta ingresar ya existe.","Error",JOptionPane.ERROR_MESSAGE);
                                    ret=false&ret;
                                    break;
                                }
                            }else{
                                jTable1.changeSelection(i,row_f_retiro, false, false);
                                jTable1.requestFocus();
                                JOptionPane.showMessageDialog(this,"Verifique la fecha de Retiro del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                ret=false&ret;
                                break;
                            }
                        } catch (SQLException | ParseException ex) {
                            jTable1.changeSelection(i,2, false, false);
                            jTable1.requestFocus();
                            JOptionPane.showMessageDialog(this,"Verifique la Fecha de Ingreso del empleado","Error",JOptionPane.ERROR_MESSAGE);
                            ret=false&ret;
                            break;
                        } 
                    } else {
                        jTable1.changeSelection(i,row_f_ingreso, false, false);
                        jTable1.requestFocus();
                        JOptionPane.showMessageDialog(this,"Verifique la Fecha de Ingreso del empleado..","Error",JOptionPane.ERROR_MESSAGE);
                        ret=false&ret;
                        break;
                    }
                } else {
                    jTable1.changeSelection(i,row_empleador, false, false);
                    jTable1.requestFocus();
                    JOptionPane.showMessageDialog(this,"Verifique el nombre de la Empresa del empleado","Error",JOptionPane.ERROR_MESSAGE);
                    ret=false&ret;
                    break;
                }
            } else {
                jTable1.changeSelection(i,row_cedula, false, false);
                jTable1.requestFocus();
                JOptionPane.showMessageDialog(this,"Verifique la Cedula del empleado","Error",JOptionPane.ERROR_MESSAGE);
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
/*
public String get_id_empleador(Object emp){
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    String i = "";
    try{
        r = con.s.executeQuery ("SELECT * FROM T_EMPRESAS WHERE NOMBRE_EMPRESA='"+emp.toString().trim()+"'");
        if(r.next()){
            i = r.getString("ID_EMPRESA");
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
public String get_id_obra(Object obra){
    String i="";
    String str_obra="";
    String str_mun="";
    String str_dep="";
    StringTokenizer tokens=new StringTokenizer(obra.toString().trim(), "-");
    while(tokens.hasMoreTokens()){
        str_obra=tokens.nextToken().trim();
        str_mun=tokens.nextToken().trim();
        str_dep=tokens.nextToken().trim();
    }
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT *\n" +
                                "FROM\n" +
                                "    t_obra\n" +
                                "    INNER JOIN t_municipios \n" +
                                "        ON (t_obra.ID_MUN_OBRA = t_municipios.ID_MUN)\n" +
                                "    INNER JOIN t_departamentos \n" +
                                "        ON (t_municipios.ID_DEP = t_departamentos.ID_DEP) WHERE NOMBRE_OBRA = '"+str_obra+"' AND NOMBRE_MUN ='"+str_mun+"' AND NOMBRE_DEP='"+str_dep+"'");
        if(r.next()){
            i = r.getString("ID_OBRA");
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
public String get_id_barrio(Object barrio){
    String i = "";
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_BARRIO WHERE NOMBRE_BARRIO='"+barrio.toString().trim()+"'");
        if(r.next()){
            i = r.getString("ID_BARRIO");
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
public String get_id_parentesco(Object par){
    String i = "";
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_PARENTESCO WHERE NOMBRE_PAR='"+par+"'");
        if(r.next()){
            i = r.getString("ID_PAR");
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
*/
public void autocomplete_date_pressed(int b){
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
private static String getlastDate(int month, int year) {
    Calendar calendar = Calendar.getInstance();
    // passing month-1 because 0-->jan, 1-->feb... 11-->dec
    calendar.set(year, month - 1, 1);
    calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
    Date date = calendar.getTime();
    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    return DATE_FORMAT.format(date);
}
public boolean check_date_out(String d_out){
boolean date_ok=false;

return date_ok;
}
public Date get_last_asist(String ced){
    Date ret = new Date();
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try {
        r = con.s.executeQuery ("SELECT * FROM t_asistencias WHERE ID_EMPLEADO ="+ced+" ORDER BY F_ASISTENCIA DESC");
        if(r.next()){
            ret.setTime(r.getDate("F_ASISTENCIA").getTime());
        }else{
            ret.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("1900-01-01").getTime());
        }
    } catch(ParseException | SQLException j){
        con.cerrar();
        j.printStackTrace();
    } 
return ret;

}
}
