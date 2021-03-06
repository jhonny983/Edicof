/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.GUI;

import com.jm.Edicof.Clases.AutoCompletion;
import com.jm.Edicof.Clases.CellRender_Preingresos_Traslados;
import com.jm.Edicof.Clases.Conexion;
import com.jm.Edicof.Clases.PegarExcel;
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

/**
 *
 * @author ADMIN
 */
public class Add_Preingreso_Traslado extends javax.swing.JDialog {
    static Dimension screenSize = null;
    TextAutoCompleter tac_empleador = null;
    TextAutoCompleter tac_barrio = null;
    TextAutoCompleter tac_parentesco = null;
    TextAutoCompleter tac_municipio = null;
    TextAutoCompleter tac_obra = null;
    DefaultTableModel modelo = null;
    Object [] fila = new Object[16];
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
    TextAutoCompleter tac_cargo_table = null;
    JTextField tb_cargo_table = null;
    TextAutoCompleter tac_area_trabajo_table = null;
    JTextField tb_area_trabajo_table = null;
    /////////////////////////----------------------------------
    String before_edit_cell = null;
    /////////////////////////----------------------------------
    int row_cedula=0;
    int row_empleador = 1;
    int row_f_ingreso = 2;
    int row_salario=3;
    int row_obra=4;
    int row_area=5;
    int row_cargo=6;
    int row_direccion=7;
    int row_barrio=8;
    int row_municipio=9;
    int row_telefono=10;
    int row_correo=11;
    int row_acud=12;
    int row_par=13;
    int row_tel_acu=14;
    int row_f_examen_ing=15;
    int row_f_consent=16;
    int row_f_prot=17;
    int row_exon=18;
    int row_obs=19;
    //static DefaultTableModel modelo = new DefaultTableModel();
    /**
     * Creates new form Add_Preingresos
     */
    public Add_Preingreso_Traslado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //////////////////----------------------------------------
        jTable1.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {//e.getType() == TableModelEvent.UPDATE
                    int fila = e.getFirstRow();
                    int columna = e.getColumn();
                    if (columna == 0) {
                        check_empleado();
                    }else{
                        return;
                    }
                    
                }
                //System.out.println(e);
            }
        });
        //////////////////----------------------------------------
        jTable1.setDefaultRenderer (Object.class, new CellRender_Preingresos_Traslados());
        
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
        tb_cargo_table = new JTextField();
        tb_area_trabajo_table = new JTextField();
        /////////////////-----------------------------------------
        
        /////////////////-----------------------------------------
        init();
        cb_cedula();
        cb_empleador();
        tac_empleador();
        tac_barrio();
        tac_parentesco();
        tac_municipio();
        tac_obra();
        tac_area_trabajo();
        tac_cargo();
        tac_empleador.setMode(0);
//        tac_barrio.setMode(0);
        tac_parentesco.setMode(0);
        tac_municipio.setMode(0);
        tac_obra.setMode(0);
        tac_area_trabajo_table.setMode(0);
        tac_cargo_table.setMode(0);
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
        jTable1.getColumnModel().getColumn(row_cedula).setCellEditor(new MyTableCellEditorDate(tb_cedula_table,true));
        jTable1.getColumnModel().getColumn(row_empleador).setCellEditor(new MyTableCellEditorDate(tb_empleador_table,true));
        jTable1.getColumnModel().getColumn(row_f_ingreso).setCellEditor(new MyTableCellEditorDate(true));
        jTable1.getColumnModel().getColumn(row_f_consent).setCellEditor(new MyTableCellEditorDate(true));
        jTable1.getColumnModel().getColumn(row_f_examen_ing).setCellEditor(new MyTableCellEditorDate(true));
        jTable1.getColumnModel().getColumn(row_f_prot).setCellEditor(new MyTableCellEditorDate(true));
        jTable1.getColumnModel().getColumn(row_obra).setCellEditor(new MyTableCellEditorDate(tb_obra_table,true));
        jTable1.getColumnModel().getColumn(row_area).setCellEditor(new MyTableCellEditorDate(tb_area_trabajo_table,true));
        jTable1.getColumnModel().getColumn(row_cargo).setCellEditor(new MyTableCellEditorDate(tb_cargo_table,true));
        jTable1.getColumnModel().getColumn(row_barrio).setCellEditor(new MyTableCellEditorDate(tb_barrio_table,true));
        jTable1.getColumnModel().getColumn(row_municipio).setCellEditor(new MyTableCellEditorDate(tb_municipio_table,true));
        jTable1.getColumnModel().getColumn(row_par).setCellEditor(new MyTableCellEditorDate(tb_parentesco_table,true));
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
        public void keyPressed(KeyEvent evt) {
            char a=evt.getKeyChar();
            int b=evt.getKeyCode();
            //System.out.println("Key Pressed Cell: '"+a+"'("+b+")");
            //if (jTable1.getEditingColumn()==2|jTable1.getEditingColumn()==4|jTable1.getEditingColumn()==5) {
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
        tac_area_trabajo_table = new TextAutoCompleter(tb_area_trabajo_table, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            //load_data_barrio(selectedItem.toString());
        }
        });
        tac_cargo_table = new TextAutoCompleter(tb_cargo_table, new AutoCompleterCallback() {
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
        setTitle("Agregar Pre-Ingresos Traslado");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar"));
        jPanel2.setToolTipText("");

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Información de pre-ingresos"));

        new PegarExcel(jTable1);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Cedula*", "Empleador (Nombre)*", "F Ingreso (DD-MM-AAAA)*", "Salario*", "Obra*", "Area Trabajo*", "Cargo*", "Direccion", "Barrio*", "Municipio*", "Telefono", "Correo", "Acudiente", "Parentesco*", "Telefono Acudiente", "F Examen Ingreso (DD-MM-YYYY)*", "F Consentimiento (DD-MM-YYYY)*", "F Protocolo (DD-MM-YYYY)*", "Exonerado FIC", "Observaciones"
            }
        ));
        jTable1.setToolTipText("");
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
            jTable1.getColumnModel().getColumn(2).setMinWidth(150);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(150);
            jTable1.getColumnModel().getColumn(3).setMinWidth(100);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(100);
            jTable1.getColumnModel().getColumn(4).setMinWidth(200);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(200);
            jTable1.getColumnModel().getColumn(7).setMinWidth(150);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(7).setMaxWidth(150);
            jTable1.getColumnModel().getColumn(8).setMinWidth(150);
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(8).setMaxWidth(150);
            jTable1.getColumnModel().getColumn(9).setMinWidth(150);
            jTable1.getColumnModel().getColumn(9).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(9).setMaxWidth(150);
            jTable1.getColumnModel().getColumn(10).setMinWidth(80);
            jTable1.getColumnModel().getColumn(10).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(10).setMaxWidth(80);
            jTable1.getColumnModel().getColumn(11).setMinWidth(150);
            jTable1.getColumnModel().getColumn(11).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(11).setMaxWidth(150);
            jTable1.getColumnModel().getColumn(12).setMinWidth(150);
            jTable1.getColumnModel().getColumn(12).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(12).setMaxWidth(150);
            jTable1.getColumnModel().getColumn(13).setMinWidth(80);
            jTable1.getColumnModel().getColumn(13).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(13).setMaxWidth(80);
            jTable1.getColumnModel().getColumn(14).setMinWidth(150);
            jTable1.getColumnModel().getColumn(14).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(14).setMaxWidth(150);
            jTable1.getColumnModel().getColumn(16).setMinWidth(180);
            jTable1.getColumnModel().getColumn(16).setPreferredWidth(180);
            jTable1.getColumnModel().getColumn(16).setMaxWidth(180);
            jTable1.getColumnModel().getColumn(17).setMinWidth(180);
            jTable1.getColumnModel().getColumn(17).setPreferredWidth(180);
            jTable1.getColumnModel().getColumn(17).setMaxWidth(180);
            jTable1.getColumnModel().getColumn(19).setMinWidth(150);
            jTable1.getColumnModel().getColumn(19).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(19).setMaxWidth(150);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1143, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
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
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
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
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
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
// TODO add your handling code here:

    char a=evt.getKeyChar();
    int b=evt.getKeyCode();
    System.out.println("Key Pressed Tabla: '"+a+"'("+b+")");
    if (jTable1.getEditingColumn()==2|jTable1.getEditingColumn()==4|jTable1.getEditingColumn()==5) {
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
        
//        if(!(a>=KeyEvent.VK_0 && a<=KeyEvent.VK_9) & a!=KeyEvent.VK_ENTER & a!=KeyEvent.VK_ESCAPE & a!=KeyEvent.VK_CONTROL & jTable1.getSelectedColumn()==0) {
//            getToolkit().beep(); 
//            evt.consume();
//        }
//        if(!(a>=KeyEvent.VK_0 && a<=KeyEvent.VK_9) & a!=KeyEvent.VK_ENTER & a!=KeyEvent.VK_ESCAPE & a!=KeyEvent.VK_BACK_SPACE & a!=KeyEvent.VK_MINUS & jTable1.getSelectedColumn()==2) {
//            getToolkit().beep(); 
//            evt.consume();
//        }
//        if(!(a>=KeyEvent.VK_0 && a<=KeyEvent.VK_9) & a!=KeyEvent.VK_ENTER & a!=KeyEvent.VK_PERIOD & jTable1.getSelectedColumn()==3) {
//            getToolkit().beep(); 
//            evt.consume();
//        }
//        if(!(a>=KeyEvent.VK_0 && a<=KeyEvent.VK_9) & a!=KeyEvent.VK_ENTER & a!=KeyEvent.VK_ESCAPE & a!=KeyEvent.VK_BACK_SPACE & a!=KeyEvent.VK_MINUS & jTable1.getSelectedColumn()==4) {
            getToolkit().beep(); 
//            evt.consume();
//        }
//        if(!(a>=KeyEvent.VK_0 && a<=KeyEvent.VK_9) & a!=KeyEvent.VK_ENTER & a!=KeyEvent.VK_ESCAPE & a!=KeyEvent.VK_BACK_SPACE & a!=KeyEvent.VK_MINUS & jTable1.getSelectedColumn()==5) {
//            getToolkit().beep(); 
//            evt.consume();
//        }
        
        
    }//GEN-LAST:event_jTable1KeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        perm_tabla=false;
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        String arl = "";
        String ccf="";
        String usu="";
        boolean confirm = false;
        if(verify_data()){
            int conf = JOptionPane.showConfirmDialog(this,"La informacion de la tabla esta completa\nEsta seguro que desea continuar?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (conf == JOptionPane.YES_OPTION) {
                modelo = (DefaultTableModel)jTable1.getModel(); 
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r,r1;
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    try {
                        if (!GetInfo.get_id_empleador(modelo.getValueAt(i, row_empleador)).equals("")) {
                            if (!GetInfo.get_id_obra(modelo.getValueAt(i, row_obra)).equals("")) {
                                if (!GetInfo.get_id_barrio(modelo.getValueAt(i, row_barrio)).equals("")) {
                                    if (!GetInfo.get_id_municipio(modelo.getValueAt(i, row_municipio)).equals("")) {
                                        if (!GetInfo.get_id_parentesco(modelo.getValueAt(i, row_par)).equals("")) {
                                            r = con.s.executeQuery ("SELECT * FROM `t_novedades` WHERE (ID_EMPLEADO ="+modelo.getValueAt(i, row_cedula)+" AND ID_EMPRESA='"+GetInfo.get_id_empleador(modelo.getValueAt(i, row_empleador))+"' AND FECHA_INGRESO='"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_ingreso).toString()))+"' AND FECHA_RETIRO='1900-01-01' AND ID_TIPO IN(3));");
                                            if(r.next()){
                                                JOptionPane.showMessageDialog(this,"Esta novedad de pre-ingreso ya existe.","Error",JOptionPane.ERROR_MESSAGE);
                                                confirm = false;
                                                jTable1.changeSelection(i,row_cedula, false, false);
                                                jTable1.requestFocus();
                                                break;
                                            }else{
                                                int id_area=0;
                                                int id_cargo=0;
                                                r1 = con.s.executeQuery ("SELECT *\n" +
                                                                        "FROM\n" +
                                                                        "    t_empresas\n" +
                                                                        "    INNER JOIN t_arl \n" +
                                                                        "        ON (t_empresas.ID_ARL = t_arl.ID_ARL)\n" +
                                                                        "    INNER JOIN t_ccf \n" +
                                                                        "        ON (t_empresas.ID_CCF = t_ccf.ID_CCF)"
                                                        + "WHERE NOMBRE_EMPRESA = '"+modelo.getValueAt(i,row_empleador).toString().trim()+"'");
                                                if (r1.next()) {
                                                    arl = r1.getString("NOMBRE_ARL");
                                                    ccf = r1.getString("NOMBRE_CCF");
                                                }
                                                //**************GET ID AREA
                                                r = con.s.executeQuery ("SELECT *\n" +
                                                                        "FROM\n" +
                                                                        "    t_actividades WHERE NOMBRE_ACTIVIDAD = '"+jTable1.getValueAt(i, row_area).toString()+"';");
                                                if(r.next()){
                                                    id_area=r.getInt("ID_ACTIVIDAD");
                                                }
                                                //**************GET ID CARGO
                                                r = con.s.executeQuery ("SELECT *\n" +
                                                                        "FROM\n" +
                                                                        "    t_cargo WHERE NOMBRE_CARGO = '"+jTable1.getValueAt(i, row_cargo).toString()+"';");
                                                if(r.next()){
                                                    id_cargo=r.getInt("ID_CARGO");
                                                }
                                                if (!Validations.check_protocolo(jTable1.getValueAt(i, row_cedula).toString())) {
                                                    con.s.executeUpdate("UPDATE `t_cap_prot`\n" +
                                                                        "SET `FECHA_CAP_PROT` = '"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_prot).toString()))+"'\n" +
                                                                        "WHERE `ID_EMP` = "+jTable1.getValueAt(i, row_cedula).toString()+";");
                                                }
                                                con.s.executeUpdate("UPDATE `t_info_sociodemografica`\n" +
                                                                    "SET `DIRECCION_EMP` = '"+modelo.getValueAt(i, row_direccion)+"',\n" +
                                                                    "  `ID_BARRIO` = "+GetInfo.get_id_barrio(modelo.getValueAt(i, row_barrio))+",\n" +
                                                                    "  `ID_MUN_RES_EMP` = "+GetInfo.get_id_municipio(modelo.getValueAt(i, row_municipio))+",\n" +
                                                                    "  `TEL_CEL_EMP` = '"+modelo.getValueAt(i, row_telefono)+"',\n" +
                                                                    "  `NOMBRE_ACUDIENTE_EMP` = '"+modelo.getValueAt(i, row_acud)+"',\n" +
                                                                    "  `ID_PAR_ACU_EMP` = "+GetInfo.get_id_parentesco(modelo.getValueAt(i, row_par))+",\n" +
                                                                    "  `TEL_CEL_ACU_EMP` = '"+modelo.getValueAt(i, row_tel_acu)+"'\n" +
                                                                    "   WHERE `ID_EMP` = "+modelo.getValueAt(i, row_cedula)+";");
                                                con.s.executeUpdate("insert into `t_novedades`\n" +
                                                                    "            (`ID_EMPLEADO`,\n" +
                                                                    "             `ID_EMPRESA`,\n" +
                                                                    "             `FECHA_INGRESO`,\n" +
                                                                    "             `FECHA_RETIRO`,\n" +
                                                                    "             `SALARIO_NOVEDAD`,\n" +
                                                                    "             `ID_EPS`,\n" +
                                                                    "             `ID_AFP`,\n" +
                                                                    "             `ARL_NOV`,\n" +
                                                                    "             `CCF_NOV`,\n" +
                                                                    "             `OBS_NOV`,\n" +
                                                                    "             `ID_OBRA`,\n" +
                                                                    "             `ID_TIPO`,\n" +
                                                                    "             `F_REGISTRO`,\n" +
                                                                    "             `ID_CARGO`,\n" +
                                                                    "             `ID_AREA_TRABAJO`,\n" +
                                                                    "             `F_EXAMEN_INGRESO`,\n" +
                                                                    "             `F_CONSENTIMIENTO`,\n" +
                                                                    "             `EXON_FIC`,\n" +
                                                                    "             `MAIL_NOV`)\n" +
                                                                    "values ('"+modelo.getValueAt(i, row_cedula)+"',\n" +
                                                                    "        '"+GetInfo.get_id_empleador(modelo.getValueAt(i, row_empleador))+"',\n" +
                                                                    "        '"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_ingreso).toString()))+"',\n" +
                                                                    "        '1900-01-01',\n" +
                                                                    "        '"+modelo.getValueAt(i, row_salario)+"',\n" +
                                                                    "        '1',\n" +
                                                                    "        '1',\n" +
                                                                    "        '"+arl+"',\n" +
                                                                    "        '"+ccf+"',\n" +
                                                                    "        'PREINGRESO_TRASLADO>USR:"+Main.login.getText()+">FR:"+new SimpleDateFormat("dd-MM-yyyy").format(new Date())+">FI:"+modelo.getValueAt(i, row_f_ingreso).toString()+","+modelo.getValueAt(i, row_obs).toString().toUpperCase()+"',\n" +
                                                                    "        '"+GetInfo.get_id_obra(modelo.getValueAt(i, row_obra))+"',\n" +
                                                                    "        '3',\n" +
                                                                    "        '"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"',\n" +
                                                                    "        '"+id_cargo+"',\n" +
                                                                    "        '"+id_area+"',\n" +
                                                                    "        '"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_examen_ing).toString()))+"',\n" +
                                                                    "        '"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_consent).toString()))+"',\n" +
                                                                    "        '"+modelo.getValueAt(i, row_exon)+"',\n" +
                                                                    "        '"+modelo.getValueAt(i, row_correo)+"');");
//                                                con.s.executeUpdate("INSERT INTO `t_novedades` (ID_EMPLEADO,ID_EMPRESA,FECHA_INGRESO,FECHA_RETIRO,SALARIO_NOVEDAD,ID_EPS,ID_AFP,ARL_NOV,CCF_NOV,F_NAC_NOV,F_EXP_NOV,DIR_EMP_NOV,BARRIO_NOV,ID_MUN_NOV,TEL_NOV,MAIL_NOV,ACUD_NOV,ID_PAR_ACU_NOV,TEL_ACUD_NOV,OBS_NOV,ID_OBRA,ID_TIPO,F_REGISTRO,ID_CARGO,ID_AREA_TRABAJO) VALUES ("+modelo.getValueAt(i, 0)+",'"+get_id_empleador(modelo.getValueAt(i, 1))+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, 2).toString()))+"','1900-01-01',"+modelo.getValueAt(i, 3)+",1,1,'"+arl+"','"+ccf+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, 4).toString()))+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, 5).toString()))+"','"+modelo.getValueAt(i, 9).toString().toUpperCase()+"','"+modelo.getValueAt(i, 10)+"',"+get_id_municipio(modelo.getValueAt(i, 11))+",'"+modelo.getValueAt(i, 12)+"','"+modelo.getValueAt(i, 13)+"','"+modelo.getValueAt(i, 14).toString().toUpperCase()+"',"+get_id_parentesco(modelo.getValueAt(i, 15))+",'"+modelo.getValueAt(i, 16)+"','"+modelo.getValueAt(i, 17).toString().toUpperCase()+"',"+get_id_obra(modelo.getValueAt(i, 6))+",3,'"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"',"+id_cargo+","+id_area+")");
                                                con.s.executeUpdate("INSERT INTO `t_registro`"
                                                        + "(ID_EMPLEADO,"
                                                        + "ID_EMPRESA,"
                                                        + "F_INGRESO,"
                                                        + "F_RETIRO,"
                                                        + "ID_TIPO,"
                                                        + "REGISTRO,"
                                                        + "F_REGISTRO,"
                                                        + "FECHA,"
                                                        + "ID_USUARIO)"
                                                        + "VALUES ("+modelo.getValueAt(i, row_cedula)+","
                                                        + "'"+GetInfo.get_id_empleador(modelo.getValueAt(i, row_empleador))+"',"
                                                        + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_ingreso).toString()))+"',"
                                                        + "'1900-01-01',"
                                                        + "3,"
                                                        + "'PREINGRESO_TRASLADO',"
                                                        + "'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"',"
                                                        + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_ingreso).toString()))+"',"
                                                        + ""+Main.id_usuario+")");
                                                modelo.removeRow(i);
                                                i=i-1;
                                                confirm = true;
                                            }
                                        }else {
                                            jTable1.changeSelection(i,row_par, false, false);
                                            jTable1.requestFocus();
                                            JOptionPane.showMessageDialog(this,"Verifique la informacion del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                            break;
                                        }
                                    }else {
                                        jTable1.changeSelection(i,row_municipio, false, false);
                                        jTable1.requestFocus();
                                        JOptionPane.showMessageDialog(this,"Verifique la informacion del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                        break;
                                    }
                                }else {
                                    jTable1.changeSelection(i,row_barrio, false, false);
                                    jTable1.requestFocus();
                                    JOptionPane.showMessageDialog(this,"Verifique la informacion del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                    break;
                                }
                            }else {
                                jTable1.changeSelection(i,6, false, false);
                                jTable1.requestFocus();
                                JOptionPane.showMessageDialog(this,"Verifique la informacion del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                break;
                            }
                        }else {
                            jTable1.changeSelection(i,1, false, false);
                            jTable1.requestFocus();
                            JOptionPane.showMessageDialog(this,"Verifique la informacion del empleado","Error",JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        
                    } catch (ParseException | SQLException e) {
                        con.cerrar();
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }
                con.cerrar();
                if (confirm) {
                    JOptionPane.showMessageDialog(this,"La información ha sido ingresada correctamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(this,"Se ha presentado un error al ingresar la información","Informacion",JOptionPane.ERROR_MESSAGE);
                }
                
            }
            
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        try {
            char a=evt.getKeyChar();
            int b=evt.getKeyCode();
            System.out.println("Key Released Tabla: '"+a+"'("+b+")");
            //System.out.println("Longitud: "+((JTextField) jTable1.getEditorComponent()).getText().length());
            if (jTable1.getEditingColumn()==2 | jTable1.getEditingColumn()==4 |jTable1.getEditingColumn()==5) {
                autocomplete_date_released(b);
            }
        } catch (UnsupportedOperationException uop) {
            System.out.println(uop.getMessage());
        }catch(Exception ev){
            ev.printStackTrace();
        }
    }//GEN-LAST:event_jTable1KeyReleased

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
            java.util.logging.Logger.getLogger(Add_Preingreso_Traslado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Add_Preingreso_Traslado dialog = new Add_Preingreso_Traslado(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> bsc_cedula;
    private javax.swing.JComboBox<String> bsc_empleador;
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
    private javax.swing.JButton jButton6;
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
public void check_empleado(){
    ArrayList<String> ret_nov = new ArrayList<String>();
    if (true) { //& !jTable1.isEditing()
        modelo = (DefaultTableModel)jTable1.getModel();
        if (Validations.check_cedula(modelo.getValueAt(jTable1.getSelectedRow(),jTable1.getSelectedColumn()))) {
            Conexion con = new Conexion();
            con.conexion();
            ResultSet r=null;
            ResultSet r1=null;
            ResultSet r2=null;
            try {
                r = con.s.executeQuery ("SELECT COUNT(ID_EMPLEADO) FROM t_novedades WHERE ID_EMPLEADO = "+modelo.getValueAt(jTable1.getSelectedRow(),jTable1.getSelectedColumn()).toString().trim()+" AND ID_TIPO IN (1,3,4,5)");
                if (r.next()) {
                    if (r.getInt("COUNT(ID_EMPLEADO)")>0) {
                        JOptionPane.showMessageDialog(this,"El empleado actualmente esta activo con varios empleadores.","Información",JOptionPane.INFORMATION_MESSAGE);
                        ret_nov=call_sel_emp(modelo.getValueAt(jTable1.getSelectedRow(),jTable1.getSelectedColumn()).toString().trim(),"1,3,4,5");
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
                                                    + "    ON (t_novedades.ID_OBRA = t_obra.ID_OBRA)\n"
                                                    + "  INNER JOIN t_tipo_novedad\n"
                                                    + "    ON (t_novedades.ID_TIPO = t_tipo_novedad.ID_TIPO)\n"
                                                    + "WHERE (t_novedades.ID_EMPLEADO = "+ret_nov.get(0)+" AND t_novedades.ID_EMPRESA = '"+ret_nov.get(1)+"' AND t_novedades.FECHA_INGRESO = '"+ret_nov.get(2)+"' AND t_novedades.FECHA_RETIRO = '"+ret_nov.get(3)+"' AND t_novedades.ID_TIPO = "+ret_nov.get(4)+")\n"
                                                    + "ORDER BY FECHA_INGRESO DESC;");
                            if(r.next()){
                                modelo.setValueAt(r.getString("NOMBRE_EMPRESA"),jTable1.getSelectedRow(),row_empleador);
                                modelo.setValueAt(r.getString("SALARIO_NOVEDAD"),jTable1.getSelectedRow(),row_salario);
                                modelo.setValueAt(r.getString("NOMBRE_OBRA"),jTable1.getSelectedRow(),row_obra);
                                modelo.setValueAt(r.getString("NOMBRE_ACTIVIDAD"),jTable1.getSelectedRow(),row_area);
                                modelo.setValueAt(r.getString("NOMBRE_CARGO"),jTable1.getSelectedRow(),row_cargo);
                                modelo.setValueAt(r.getString("MAIL_NOV"),jTable1.getSelectedRow(),row_correo);
                                modelo.setValueAt(new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(r.getString("F_EXAMEN_INGRESO"))),jTable1.getSelectedRow(),row_f_examen_ing);
                                modelo.setValueAt(new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(r.getString("F_CONSENTIMIENTO"))),jTable1.getSelectedRow(),row_f_consent);
                                modelo.setValueAt(r.getString("EXON_FIC"),jTable1.getSelectedRow(),row_exon);
                                modelo.setValueAt(r.getString("OBS_NOV"),jTable1.getSelectedRow(),row_obs);
                                r1 = con.s.executeQuery ("SELECT *\n" +
                                                        "FROM\n" +
                                                        "    `t_info_sociodemografica`\n" +
                                                        "    INNER JOIN `t_barrio` \n" +
                                                        "        ON (`t_info_sociodemografica`.`ID_BARRIO` = `t_barrio`.`ID_BARRIO`)\n" +
                                                        "    INNER JOIN `t_municipios` \n" +
                                                        "        ON (`t_info_sociodemografica`.`ID_MUN_RES_EMP` = `t_municipios`.`ID_MUN`)\n" +
                                                        "    INNER JOIN `t_departamentos` \n" +
                                                        "        ON (`t_municipios`.`ID_DEP` = `t_departamentos`.`ID_DEP`)\n" +
                                                        "    INNER JOIN `t_parentesco` \n" +
                                                        "        ON (`t_info_sociodemografica`.`ID_PAR_ACU_EMP` = `t_parentesco`.`ID_PAR`)\n" +
                                                        "    WHERE ID_EMP ="+ret_nov.get(0));
                                if (r1.next()) {
                                    modelo.setValueAt(r.getString("DIRECCION_EMP"),jTable1.getSelectedRow(),row_direccion);
                                    modelo.setValueAt(r.getString("NOMBRE_BARRIO"),jTable1.getSelectedRow(),row_barrio);
                                    modelo.setValueAt(r.getString("NOMBRE_MUN")+"-"+r.getString("NOMBRE_DEP"),jTable1.getSelectedRow(),row_municipio);
                                    modelo.setValueAt(r1.getString("TEL_CEL_EMP"),jTable1.getSelectedRow(),row_telefono);
                                    modelo.setValueAt(r1.getString("NOMBRE_ACUDIENTE_EMP"),jTable1.getSelectedRow(),row_acud);
                                    modelo.setValueAt(r1.getString("NOMBRE_PAR"),jTable1.getSelectedRow(),row_par);
                                    modelo.setValueAt(r1.getString("TEL_CEL_ACU_EMP"),jTable1.getSelectedRow(),row_tel_acu);
                                }
                            }
                        }
                    }
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
                con.cerrar();
            }

        }
        //JOptionPane.showMessageDialog(null,"asd","Error",JOptionPane.ERROR_MESSAGE);
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
public final void tac_barrio(){
    tac_barrio_table.removeAllItems();
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r=null;
    try{
        r = con.s.executeQuery ("SELECT *\n" +
                                "FROM\n" +
                                "`t_barrio`\n" +
                                "    INNER JOIN `t_municipios` \n" +
                                "        ON (`t_barrio`.`ID_MUN_BARRIO` = `t_municipios`.`ID_MUN`);");
        while(r.next()){
            String str=r.getString("NOMBRE_BARRIO")+"-"+r.getString("NOMBRE_MUN");
            tac_barrio_table.addItem(str);
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
public final void tac_area_trabajo(){
    tac_area_trabajo_table.removeAllItems();
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r=null;
    try{
        r = con.s.executeQuery ("SELECT *\n" +
                                "FROM\n" +
                                "    `t_actividades` ORDER BY NOMBRE_ACTIVIDAD ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_ACTIVIDAD");
            tac_area_trabajo_table.addItem(str);
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
public final void tac_cargo(){
    tac_cargo_table.removeAllItems();
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r=null;
    try{
        r = con.s.executeQuery ("SELECT *\n" +
                                "FROM\n" +
                                "    `t_cargo` ORDER BY NOMBRE_CARGO ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_CARGO");
            tac_cargo_table.addItem(str);
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
        if (check_char(ced.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
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
public boolean check_empleador(Object emp){
    boolean ret=false;
    if (emp!=null) {
        if (check_char(emp.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,")) {
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
public boolean check_char(String s, String c){
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
public boolean check_salario(Object salario){
    boolean ret=false;
    if (salario!=null) {
        if (check_char(salario.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!salario.toString().equals("")) {
                if ( comprobarFloat(salario.toString())) {
                    ret = true;
                }
            }
        }
    }
return ret;
}
public boolean check_sal_min(Object salario){
    boolean ret=false;
    float sal=0;
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try {
        r = con.s.executeQuery ("SELECT * FROM `t_parametros` WHERE NOMBRE_PAR = 'SALARIO_MINIMO';");
        if(r.next()){
            sal=r.getFloat("VALOR_PAR"); 
        }
        con.cerrar();
        if (Float.parseFloat(salario.toString())>=sal) {
            ret = true;
        }
    } catch (SQLException | NumberFormatException e) {
        con.cerrar();
        e.printStackTrace();
        JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);
    }
    return ret;
}
public boolean check_obra(Object obra){
    boolean ret=false;
    if (obra!=null) {
        if (check_char(obra.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
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
public boolean check_tip_area(Object area){
    boolean ret=false;
    if (area!=null) {
        if (check_char(area.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!area.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT *\n" +
                                            "FROM\n" +
                                            "    t_actividades\n" +
                                            "WHERE NOMBRE_ACTIVIDAD = '"+area.toString().trim()+"'");
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
public boolean check_cargo(Object cargo){
    boolean ret=false;
    if (cargo!=null) {
        if (check_char(cargo.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!cargo.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT *\n" +
                                            "FROM\n" +
                                            "    t_cargo\n" +
                                            "WHERE NOMBRE_CARGO = '"+cargo.toString().trim()+"'");
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
public boolean check_barrio(Object barrio, Object mun){
    boolean ret=false;
    if (barrio!=null & mun!=null) {
        if (check_char(barrio.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.") & check_char(mun.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!barrio.toString().trim().equals("") & !mun.toString().trim().equals("")){
               Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    String str_barrio="";
                    String str_barrio_mun="";
                    String str_mun="";
                    String str_dep="";
                    StringTokenizer tk_barrio=new StringTokenizer(barrio.toString().trim(), "-");
                    while(tk_barrio.hasMoreTokens()){
                        str_barrio=tk_barrio.nextToken().trim();
                        str_barrio_mun=tk_barrio.nextToken().trim();
                    }
                    StringTokenizer tk_mun=new StringTokenizer(mun.toString().trim(), "-");
                    while(tk_mun.hasMoreTokens()){
                        str_mun=tk_mun.nextToken().trim();
                        str_dep=tk_mun.nextToken().trim();
                    }
                    if (str_barrio_mun.equals(str_mun)) {
                        r = con.s.executeQuery ("SELECT * FROM t_barrio WHERE NOMBRE_BARRIO='"+str_barrio+"' AND ID_MUN_BARRIO="+get_id_municipio(mun.toString().trim()));
                        if(r.next()){
                            ret=true;
                        }
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
public boolean check_parentesco(Object par){
    boolean ret=false;
    if (par!=null) {
        if (check_char(par.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
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
        if (check_char(field.toString().trim(),"'$%&()=?¡¿/*+[]{};:<>,")) {
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
        if (check_char(field.toString().trim(),"'$%&()=?¡¿/*+[]{};:<>,")) {
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
        if (check_char(field.toString().trim(),"'$%&()=?¡¿/*+[]{};:<>,") & !check_char(field.toString().trim(),"@") & !check_char(field.toString().trim(),".") ) {
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
    boolean ret=true;
    modelo = (DefaultTableModel)jTable1.getModel(); 
    if (jTable1.getRowCount()>0) {
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            if (modelo.getValueAt(i, row_direccion)==null) {
                modelo.setValueAt("Direccion", i, row_direccion);
            }else{
                if (modelo.getValueAt(i, row_direccion).toString().equals("")){
                    modelo.setValueAt("Direccion", i, row_direccion);
                }
            }
            if (modelo.getValueAt(i, row_telefono)==null) {
                modelo.setValueAt("Telefono", i, row_telefono);
            }else{
                if (modelo.getValueAt(i, row_telefono).toString().equals("")){
                    modelo.setValueAt("Telefono", i, row_telefono);
                }
            }
            if (modelo.getValueAt(i, row_correo)==null) {
                modelo.setValueAt("Correo@correo.com", i, row_correo);
            }else{
                if (modelo.getValueAt(i, row_correo).toString().equals("")){
                    modelo.setValueAt("Correo@correo.com", i, row_correo);
                }
            }
            if (modelo.getValueAt(i, row_acud)==null) {
                modelo.setValueAt("Acudiente", i, row_acud);
            }else{
                if (modelo.getValueAt(i, row_acud).toString().equals("")){
                    modelo.setValueAt("Acudiente", i, row_acud);
                }
            }
            if (modelo.getValueAt(i, row_tel_acu)==null) {
                modelo.setValueAt("Telefono Acudiente", i, row_tel_acu);
            }else{
                if (modelo.getValueAt(i, row_tel_acu).toString().equals("")){
                    modelo.setValueAt("Telefono Acudiente", i, row_tel_acu);
                }
            }
            if (modelo.getValueAt(i, row_obs)==null) {
                modelo.setValueAt("Observaciones", i, row_obs);
            }else{
                if (modelo.getValueAt(i, row_obs).toString().equals("")){
                    modelo.setValueAt("Observaciones", i, row_obs);
                }
            }
        }
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            if (Validations.check_cedula(modelo.getValueAt(i, row_cedula))) {
                if (Validations.check_empleador(modelo.getValueAt(i, row_empleador))) {
                    if (Validations.check_fecha(modelo.getValueAt(i, row_f_ingreso))) {
                        try {
                            Calendar date_in = Calendar.getInstance();
                            date_in.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_ingreso).toString()));
                            Calendar date_today = Calendar.getInstance();
                            date_today.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(new SimpleDateFormat("dd-MM-yyyy").format(new Date())));
                            //System.out.println(getlastDate(date_in.get(Calendar.MONTH)+1, date_in.get(Calendar.YEAR)));
                            //System.out.println("Compara con hoy "+date_in.getTime().compareTo(date_today.getTime()));
                            //System.out.println("Compara con ultimo dia: "+date_in.getTime().compareTo(new SimpleDateFormat("dd-MM-yyyy").parse(getlastDate(date_in.get(Calendar.MONTH)+1, date_in.get(Calendar.YEAR)))));
                            if(date_in.getTime().compareTo(date_today.getTime())<=0){
                                if (Validations.check_salario(modelo.getValueAt(i, row_salario))) {
                                    if (Validations.check_sal_min(modelo.getValueAt(i, row_salario))) {
//                                        if (check_fecha(modelo.getValueAt(i, 4))) {
//                                            if (check_fecha(modelo.getValueAt(i, 5))) {
                                                if (Validations.check_obra(modelo.getValueAt(i, row_obra))) {
                                                    if (Validations.check_tip_area(modelo.getValueAt(i, row_area))) {
                                                        if (Validations.check_cargo(modelo.getValueAt(i, row_cargo))) {
                                                            if (Validations.check_field_dir(modelo.getValueAt(i, row_direccion))) {
                                                                if (Validations.check_barrio_mun(modelo.getValueAt(i, row_barrio),modelo.getValueAt(i, row_municipio))) {
                                                                    if (Validations.check_municipio(modelo.getValueAt(i, row_municipio))) {
                                                                        if (Validations.check_field(modelo.getValueAt(i, row_telefono))) {
                                                                            if (Validations.check_field_mail(modelo.getValueAt(i, row_correo))) {
                                                                                if (Validations.check_field(modelo.getValueAt(i, row_acud))) {
                                                                                    if (Validations.check_parentesco(modelo.getValueAt(i, row_par))) {
                                                                                        if (Validations.check_field(modelo.getValueAt(i, row_tel_acu))) {
//                                                                                            if (check_protocolo(modelo.getValueAt(i, row_cedula).toString())) {
                                                                                                if (Validations.check_fecha(modelo.getValueAt(i, row_f_examen_ing))) {
                                                                                                    Calendar date_examen = Calendar.getInstance();
                                                                                                    date_examen.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_examen_ing).toString()));
                                                                                                    date_in.add(Calendar.MONTH, -12);
                                                                                                    System.out.println("F_examen: "+date_examen.getTime());
                                                                                                    System.out.println("F_vigencia: "+date_in.getTime());
                                                                                                    if (date_examen.getTime().compareTo(date_in.getTime())>=0) {
                                                                                                        if (Validations.check_fecha(modelo.getValueAt(i, row_f_consent))) {
                                                                                                            if (Validations.check_active(modelo.getValueAt(i, row_cedula).toString(), modelo.getValueAt(i, row_empleador).toString())) {
                                                                                                                if (Validations.check_vetado(modelo.getValueAt(i, row_cedula).toString())) {
                                                                                                                    if (Validations.check_protocolo(modelo.getValueAt(i, row_cedula).toString()) | Validations.check_fecha(modelo.getValueAt(i, row_f_prot))) {
                                                                                                                        if (Validations.check_exon(modelo.getValueAt(i, row_exon).toString())) {
                                                                                                                            if (Validations.check_info(modelo.getValueAt(i, row_cedula).toString())) {
                                                                                                                                if (Validations.check_block_emp(GetInfo.get_id_empleador(modelo.getValueAt(i, row_empleador)))) {
                                                                                                                                    ret=true&ret;
                                                                                                                                } else {
                                                                                                                                    jTable1.changeSelection(i,row_empleador, false, false);
                                                                                                                                    jTable1.requestFocus();
                                                                                                                                    JOptionPane.showMessageDialog(this,"El empleador se encuentra bloqueado por seguridad social","Error",JOptionPane.ERROR_MESSAGE);
                                                                                                                                    ret=false&ret;
                                                                                                                                    break;
                                                                                                                                }
                                                                                                                                //ret=true&ret;
                                                                                                                            } else {
                                                                                                                                jTable1.changeSelection(i,row_cedula, false, false);
                                                                                                                                jTable1.requestFocus();
                                                                                                                                JOptionPane.showMessageDialog(this,"El empleado que intenta ingresar no tiene registrada información sociodemografica.","Error",JOptionPane.ERROR_MESSAGE);
                                                                                                                                ret=false&ret;
                                                                                                                                break;
                                                                                                                            }
                                                                                                                            
                                                                                                                        } else {
                                                                                                                            jTable1.changeSelection(i,row_exon, false, false);
                                                                                                                            jTable1.requestFocus();
                                                                                                                            JOptionPane.showMessageDialog(this,"Verifique la opcion de exoneracion de FIC del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                                                                            ret=false&ret;
                                                                                                                            break;
                                                                                                                        }
                                                                                                                    } else {
                                                                                                                        jTable1.changeSelection(i,row_cedula, false, false);
                                                                                                                        jTable1.requestFocus();
                                                                                                                        JOptionPane.showMessageDialog(this,"El empleado no tiene curso de protocolo.","Error",JOptionPane.ERROR_MESSAGE);
                                                                                                                        ret=false&ret;
                                                                                                                        break;
                                                                                                                    }
                                                                                                                }else{
                                                                                                                    jTable1.changeSelection(i,row_cedula, false, false);
                                                                                                                    jTable1.requestFocus();
                                                                                                                    JOptionPane.showMessageDialog(this,"El empleado actualmente se encuentra en la lista de vetados.","Error",JOptionPane.ERROR_MESSAGE);
                                                                                                                    ret=false&ret;
                                                                                                                    break;
                                                                                                                }
                                                                                                            }else{
                                                                                                                jTable1.changeSelection(i,row_cedula, false, false);
                                                                                                                jTable1.requestFocus();
                                                                                                                JOptionPane.showMessageDialog(this,"El empleado ya se encuentra activo con el empleador","Error",JOptionPane.ERROR_MESSAGE);
                                                                                                                ret=false&ret;
                                                                                                                break;
                                                                                                            }
                                                                                                        }else {
                                                                                                            jTable1.changeSelection(i,row_f_consent, false, false);
                                                                                                            jTable1.requestFocus();
                                                                                                            JOptionPane.showMessageDialog(this,"Verifique la Fecha de Consentimiento del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                                                                            ret=false&ret;
                                                                                                            break;
                                                                                                        }
                                                                                                    }else {
                                                                                                        jTable1.changeSelection(i,row_f_examen_ing, false, false);
                                                                                                        jTable1.requestFocus();
                                                                                                        JOptionPane.showMessageDialog(this,"Verifique que la Fecha de Examen medico tenga vigencia de un año con respecto a la fecha de ingreso","Error",JOptionPane.ERROR_MESSAGE);
                                                                                                        ret=false&ret;
                                                                                                        break;
                                                                                                    }
                                                                                                }else {
                                                                                                    jTable1.changeSelection(i,row_f_examen_ing, false, false);
                                                                                                    jTable1.requestFocus();
                                                                                                    JOptionPane.showMessageDialog(this,"Verifique que la Fecha de Examen medico del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                                                                    ret=false&ret;
                                                                                                    break;
                                                                                                }
                                                                                                
//                                                                                            }else{
//                                                                                                jTable1.changeSelection(i,row_tel_acu, false, false);
//                                                                                                jTable1.requestFocus();
//                                                                                                JOptionPane.showMessageDialog(this,"Verifique el Telefono del Acudiente del empleado","Error",JOptionPane.ERROR_MESSAGE);
//                                                                                                ret=false&ret;
//                                                                                                break;
//                                                                                            }
                                                                                            
                                                                                        } else {
                                                                                            jTable1.changeSelection(i,row_tel_acu, false, false);
                                                                                            jTable1.requestFocus();
                                                                                            JOptionPane.showMessageDialog(this,"Verifique el Telefono del Acudiente del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                                                            ret=false&ret;
                                                                                            break;
                                                                                        }
                                                                                    } else {
                                                                                        jTable1.changeSelection(i,row_par, false, false);
                                                                                        jTable1.requestFocus();
                                                                                        JOptionPane.showMessageDialog(this,"Verifique el Parentesco del Acudiente del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                                                        ret=false&ret;
                                                                                        break;
                                                                                    }
                                                                                } else {
                                                                                    jTable1.changeSelection(i,row_acud, false, false);
                                                                                    jTable1.requestFocus();
                                                                                    JOptionPane.showMessageDialog(this,"Verifique el Acudiente del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                                                    ret=false&ret;
                                                                                    break;
                                                                                }
                                                                            } else {
                                                                                jTable1.changeSelection(i,row_correo, false, false);
                                                                                jTable1.requestFocus();
                                                                                JOptionPane.showMessageDialog(this,"Verifique el Correo del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                                                ret=false&ret;
                                                                                break;
                                                                            }
                                                                        } else {
                                                                            jTable1.changeSelection(i,row_telefono, false, false);
                                                                            jTable1.requestFocus();
                                                                            JOptionPane.showMessageDialog(this,"Verifique el Telefono del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                                            ret=false&ret;
                                                                            break;
                                                                        }
                                                                    } else {
                                                                        jTable1.changeSelection(i,row_municipio, false, false);
                                                                        jTable1.requestFocus();
                                                                        JOptionPane.showMessageDialog(this,"Verifique el Municipio del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                                        ret=false&ret;
                                                                        break;
                                                                    }
                                                                } else {
                                                                    jTable1.changeSelection(i,row_barrio, false, false);
                                                                    jTable1.requestFocus();
                                                                    JOptionPane.showMessageDialog(this,"Verifique el Barrio del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                                    ret=false&ret;
                                                                    break;
                                                                }
                                                            } else {
                                                                jTable1.changeSelection(i,row_direccion, false, false);
                                                                jTable1.requestFocus();
                                                                JOptionPane.showMessageDialog(this,"Verifique la Direccion del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                                ret=false&ret;
                                                                break;
                                                            }
                                                        }else {
                                                            jTable1.changeSelection(i,row_cargo, false, false);
                                                            jTable1.requestFocus();
                                                            JOptionPane.showMessageDialog(this,"Verifique el cargo del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                            ret=false&ret;
                                                            break;
                                                        }
                                                    }else {
                                                        jTable1.changeSelection(i,row_area, false, false);
                                                        jTable1.requestFocus();
                                                        JOptionPane.showMessageDialog(this,"Verifique el area de trabajo seleccionada del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                        ret=false&ret;
                                                        break;
                                                    }
                                                            
                                                } else {
                                                    jTable1.changeSelection(i,row_obra, false, false);
                                                    jTable1.requestFocus();
                                                    JOptionPane.showMessageDialog(this,"Verifique la Obra a la que pertenece el empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                    ret=false&ret;
                                                    break;
                                                }
//                                            } else {
//                                                jTable1.changeSelection(i,5, false, false);
//                                                jTable1.requestFocus();
//                                                JOptionPane.showMessageDialog(this,"Verifique la Fecha de Expedicion del empleado","Error",JOptionPane.ERROR_MESSAGE);
//                                                ret=false&ret;
//                                                break;
//                                            }
//                                        } else {
//                                            jTable1.changeSelection(i,4, false, false);
//                                            jTable1.requestFocus();
//                                            JOptionPane.showMessageDialog(this,"Verifique la Fecha de Nacimiento del empleado","Error",JOptionPane.ERROR_MESSAGE);
//                                            ret=false&ret;
//                                            break;
//                                        }
                                    }else{
                                        jTable1.changeSelection(i,row_salario, false, false);
                                        jTable1.requestFocus();
                                        JOptionPane.showMessageDialog(this,"Verifique que el Salario del empleado no sea menor al Salario Minimo Legal Vigente","Error",JOptionPane.ERROR_MESSAGE);
                                        ret=false&ret;
                                        break;
                                    }
                                } else {
                                    jTable1.changeSelection(i,row_salario, false, false);
                                    jTable1.requestFocus();
                                    JOptionPane.showMessageDialog(this,"Verifique el Salario del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                    ret=false&ret;
                                    break;
                                }
                            }else{
                                jTable1.changeSelection(i,row_f_ingreso, false, false);
                                jTable1.requestFocus();
                                JOptionPane.showMessageDialog(this,"Verifique que la fecha de ingreso sea menor o igual al dia de hoy","Error",JOptionPane.ERROR_MESSAGE);
                                ret=false&ret;
                                break;
                            }
                        } catch (ParseException ex) {
                            jTable1.changeSelection(i,row_f_ingreso, false, false);
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
    String str_barrio="";
    String str_barrio_mun="";
    StringTokenizer tk_barrio=new StringTokenizer(barrio.toString().trim(), "-");
    while(tk_barrio.hasMoreTokens()){
        str_barrio=tk_barrio.nextToken().trim();
        str_barrio_mun=tk_barrio.nextToken().trim();
    }
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT *\n" +
                                "FROM\n" +
                                "    `t_barrio`\n" +
                                "    INNER JOIN `t_municipios` \n" +
                                "        ON (`t_barrio`.`ID_MUN_BARRIO` = `t_municipios`.`ID_MUN`) WHERE NOMBRE_BARRIO='"+str_barrio+"' AND NOMBRE_MUN = '"+str_barrio_mun+"'");
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
public boolean check_protocolo(String id_empleado){
    boolean ret;
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT *\n" +
                                "from `t_cap_prot`WHERE ID_EMP = "+id_empleado);
        ret = r.next();
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        ret = false;
        j.printStackTrace();
    }
    return ret;
}
public boolean check_active(String id_empleado, String empleador){
    boolean ret = false;
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT *\n" +
                                "FROM\n" +
                                "    t_novedades\n" +
                                "    INNER JOIN t_empresas \n" +
                                "        ON (t_novedades.ID_EMPRESA = t_empresas.ID_EMPRESA)" +
                                "    WHERE t_novedades.ID_EMPLEADO ="+id_empleado+"  AND t_empresas.NOMBRE_EMPRESA='"+empleador+"' AND t_novedades.ID_TIPO IN(1,3,4,5);");
        ret = !r.next();
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        ret = false;
        j.printStackTrace();
    }
    return ret;
}
public boolean check_vetado(String id_empleado){
    boolean ret;
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r=null;
    try{
        r = con.s.executeQuery ("SELECT * FROM t_vetados WHERE ID_EMPLEADO = "+id_empleado);
        ret = !r.next();
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        ret = false;
        j.printStackTrace();
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
    return ret;
}
public boolean check_exon(Object ex){
    boolean ret=false;
    if (ex!=null) {
        if (check_char(ex.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,")) {
            if (!ex.toString().trim().toUpperCase().equals("")) {
                switch (ex.toString().trim().toUpperCase()){
                        case "SI":  ret=true;
                                    break;
                        case "NO":  ret=true;
                                    break;
                        default:    ret=false;
                }
            }
        }
    }
    return ret;
}
public boolean check_info(Object ced){
    boolean ret=false;
    if (ced!=null) {
        if (check_char(ced.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!ced.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT *\n" +
                                            "FROM\n" +
                                            "    `t_info_sociodemografica` WHERE ID_EMP = "+ced.toString().trim());
                    ret=r.next();
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
public boolean check_block_emp (Object field){
    boolean ret=false;
    if (field!=null) {
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
        try{
            r = con.s.executeQuery ("SELECT * FROM t_empresas WHERE ID_EMPRESA='"+field+"'");
            if(r.next()){
                ret = r.getInt("ESTADO_EMPRESA")==1;
            }
            con.cerrar();
        }catch(SQLException j){
            con.cerrar();
            j.printStackTrace();
        }
    }
    return ret;

    
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
}
