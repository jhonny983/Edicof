/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.GUI;

import com.jm.Edicof.Clases.AutoCompletion;
import com.jm.Edicof.Clases.CellRender_Ingresos;
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
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author ADMIN
 */
public class Add_Ingresos extends javax.swing.JDialog {
    //private final static Logger LOGGER = Logger.getLogger(Add_Ingresos.class.getName());
    static Dimension screenSize = null;
    TextAutoCompleter tac_empleador = null;
    TextAutoCompleter tac_parentesco = null;
    TextAutoCompleter tac_municipio = null;
    TextAutoCompleter tac_obra = null;
    //////////////////////----------------------------
    TextAutoCompleter tac_eps = null;
    TextAutoCompleter tac_afp = null;
    TextAutoCompleter tac_arl = null;
    TextAutoCompleter tac_ccf = null;
    //////////////////////----------------------------
    DefaultTableModel modelo = null;
    Object [] fila = new Object[11];
    boolean perm_cedula = true;
    boolean perm_empleador = true;
    boolean perm_tabla = false;
    //////////////////////----------------------------
    TextAutoCompleter tac_eps_table = null;
    TextAutoCompleter tac_afp_table = null;
    TextAutoCompleter tac_arl_table = null;
    TextAutoCompleter tac_ccf_table = null;
    //////////////////////----------------------------
    JTextField tb_eps_table = null;
    JTextField tb_afp_table = null;
    JTextField tb_arl_table = null;
    JTextField tb_ccf_table = null;
    //////////////////////----------------------------
    String before_edit_cell = null;
    //////////////////////----------------------------
    int row_cedula=0;
    int row_empleado = 1;
    int row_empleador = 2;
    int row_f_ingreso = 3;
    int row_salario=4;
    int row_eps=5;
    int row_afp=6;
    int row_arl=7;
    int row_ccf=8;
    int row_obra=9;
    int row_obs=10;
    /**
     * Creates new form Add_Preingresos
     */
    public Add_Ingresos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //////////////////----------------------------------------
        jTable1.setDefaultRenderer (Object.class, new CellRender_Ingresos());
        //////////////////----------------------------------------
        screenSize = Main.getsize();
        this.setSize(screenSize.width, screenSize.height-10);
        this.setLocationRelativeTo(null);
        /////////////////-----------------------------------------
        tb_eps_table = new JTextField();
        tb_arl_table = new JTextField();
        tb_afp_table = new JTextField();
        tb_ccf_table = new JTextField();
        /////////////////-----------------------------------------
        load_all_preingresos();
        init();
        cb_cedula();
        cb_empleador();
        tac_empleador();
        //tac_barrio();
        tac_parentesco();
        tac_municipio();
        tac_obra();
        tac_eps();
        tac_arl();
        tac_afp();
        tac_ccf();
        /////////////////-----------------------------------------
        tac_empleador.setMode(0);
        tac_parentesco.setMode(0);
        tac_municipio.setMode(0);
        tac_obra.setMode(0);
        //////////////---------------------------------------
        tac_eps_table.setMode(0);
        tac_arl_table.setMode(0);
        tac_afp_table.setMode(0);
        tac_ccf_table.setMode(0);

        //////////////---------------------------------------
        InputMap map1 = bsc_cedula.getInputMap(bsc_cedula.WHEN_FOCUSED);
        map1.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK), "null");
        InputMap map2 = jTable1.getInputMap(jTable1.WHEN_FOCUSED);
        map2.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK), "null");
        /////////////////-----------------------------------------
        jTable1.getColumnModel().getColumn(5).setCellEditor(new MyTableCellEditorDate(tb_eps_table,true));
        jTable1.getColumnModel().getColumn(6).setCellEditor(new MyTableCellEditorDate(tb_afp_table,true));
        jTable1.getColumnModel().getColumn(7).setCellEditor(new MyTableCellEditorDate(tb_arl_table,true));
        jTable1.getColumnModel().getColumn(8).setCellEditor(new MyTableCellEditorDate(tb_ccf_table,true));
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
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            char a=e.getKeyChar();
            int b=e.getKeyCode();
            //System.out.println("Key Pressed Editor: '"+a+"'("+b+")");
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
        tac_eps_table = new TextAutoCompleter(tb_eps_table, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            //load_data_barrio(selectedItem.toString());
        }
        });
        tac_arl_table = new TextAutoCompleter(tb_arl_table, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            //load_data_barrio(selectedItem.toString());
        }
        });
        tac_afp_table = new TextAutoCompleter(tb_afp_table, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            //load_data_barrio(selectedItem.toString());
        }
        });
        tac_ccf_table = new TextAutoCompleter(tb_ccf_table, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            //load_data_barrio(selectedItem.toString());
        }
        });
        tac_eps = new TextAutoCompleter(t_eps, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            //load_data_barrio(selectedItem.toString());
        }
        });
        tac_arl = new TextAutoCompleter(t_arl, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            //load_data_barrio(selectedItem.toString());
        }
        });
        tac_afp = new TextAutoCompleter(t_afp, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            //load_data_barrio(selectedItem.toString());
        }
        });
        tac_ccf = new TextAutoCompleter(t_ccf, new AutoCompleterCallback() {
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
        t_eps = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        t_afp = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        t_arl = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        t_ccf = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        add_eps = new javax.swing.JButton();
        add_afp = new javax.swing.JButton();
        add_arl = new javax.swing.JButton();
        add_ccf = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        view_user_local = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        sync_ARHI = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Agregar Ingresos");
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

        t_eps.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_eps.setToolTipText("Ingrese el nombre de la empresa");

        jLabel7.setText("EPS");

        t_afp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_afp.setToolTipText("Ingrese el nombre de la empresa");

        jLabel8.setText("AFP");

        t_arl.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_arl.setToolTipText("Ingrese el nombre de la empresa");

        jLabel9.setText("ARL");

        t_ccf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_ccf.setToolTipText("Ingrese el nombre de la empresa");

        jLabel10.setText("CCF");

        add_eps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/health.png"))); // NOI18N
        add_eps.setToolTipText("Ingresar EPS");
        add_eps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_epsActionPerformed(evt);
            }
        });

        add_afp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/medal_gold_add.png"))); // NOI18N
        add_afp.setToolTipText("Ingresar AFP");
        add_afp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_afpActionPerformed(evt);
            }
        });

        add_arl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/link_add.png"))); // NOI18N
        add_arl.setToolTipText("Ingresar ARL");
        add_arl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_arlActionPerformed(evt);
            }
        });

        add_ccf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/heart_add.png"))); // NOI18N
        add_ccf.setToolTipText("Ingresar CCF");
        add_ccf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_ccfActionPerformed(evt);
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
                            .addComponent(t_empleador, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(t_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(add_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(t_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(add_barrio2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(add_barrio3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(add_barrio4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addGap(14, 14, 14)
                                .addComponent(t_arl, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(add_arl, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(add_barrio5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10)
                                .addGap(14, 14, 14)
                                .addComponent(t_ccf, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(add_ccf, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(add_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edit_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addGap(14, 14, 14)
                        .addComponent(t_afp, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(add_afp, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(edit_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edit_emp1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addGap(14, 14, 14)
                        .addComponent(t_eps, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(add_eps, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(edit_emp1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(t_eps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_eps, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(bsc_empleador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_empresa)
                    .addComponent(edit_empresa)
                    .addComponent(t_empleador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(t_afp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_afp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(t_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_barrio2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_barrio4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(t_arl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_arl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(t_parentesco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_par, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(t_obra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_barrio3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_barrio5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_par1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(t_ccf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_ccf))
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

        view_user_local.setSelected(true);
        view_user_local.setText("Ver preingresos usuario actual");
        view_user_local.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_user_localActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_refresh.png"))); // NOI18N
        jButton3.setText("Actualizar Datos");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        sync_ARHI.setSelected(true);
        sync_ARHI.setText("Sincronizar con ARHI");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(view_user_local)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sync_ARHI))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(view_user_local)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addGap(3, 3, 3)
                .addComponent(sync_ARHI))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Información de pre-ingresos"));

        new PegarExcel(jTable1);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cedula", "Empleado (Nombre)", "Empleador (Nombre)", "F Ingreso (DD-MM-AAAA)", "Salario", "EPS (Nombre)*", "AFP (Nombre)*", "ARL (Nombre)*", "CCF (Nombre)*", "Obra", "Observaciones"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setToolTipText("");
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
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
            jTable1.getColumnModel().getColumn(4).setMinWidth(100);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(100);
            jTable1.getColumnModel().getColumn(5).setMinWidth(120);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(5).setMaxWidth(120);
            jTable1.getColumnModel().getColumn(6).setMinWidth(120);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(6).setMaxWidth(120);
            jTable1.getColumnModel().getColumn(7).setMinWidth(120);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(7).setMaxWidth(120);
            jTable1.getColumnModel().getColumn(8).setMinWidth(120);
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(8).setMaxWidth(120);
            jTable1.getColumnModel().getColumn(9).setMinWidth(200);
            jTable1.getColumnModel().getColumn(9).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(9).setMaxWidth(200);
            jTable1.getColumnModel().getColumn(10).setMinWidth(250);
            jTable1.getColumnModel().getColumn(10).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(10).setMaxWidth(250);
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(14, 14, 14))
        );

        jScrollPane2.setViewportView(jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addGap(0, 0, 0))
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
        Edd_Empresa edd_empresa = new Edd_Empresa(this, true,"");
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

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        char a=evt.getKeyChar();
        int b=evt.getKeyCode();
        //System.out.println("Key Pressed Table: '"+a+"'("+b+")");
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
            }else{
                evt.consume();
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
                }else{
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
        String usu="";
        boolean confirm = false;
        if(verify_data()){
            int conf = JOptionPane.showConfirmDialog(this,"La informacion de la tabla esta completa\nEsta seguro que desea continuar?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (conf == JOptionPane.YES_OPTION) {
                modelo = (DefaultTableModel)jTable1.getModel(); 
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    try {
                        if (!get_id_empleador(modelo.getValueAt(i, row_empleador)).equals("")) {
                            r = con.s.executeQuery ("SELECT * FROM `t_novedades` WHERE (ID_EMPLEADO ="+modelo.getValueAt(i, row_cedula)+" AND ID_EMPRESA='"+get_id_empleador(modelo.getValueAt(i, row_empleador))+"' AND FECHA_INGRESO='"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_ingreso).toString()))+"' AND FECHA_RETIRO='1900-01-01' AND ID_TIPO IN(1));");
                            if(r.next()){
                                JOptionPane.showMessageDialog(this,"Esta novedad de ingreso ya existe.","Error",JOptionPane.ERROR_MESSAGE);
                                confirm = false;
                                jTable1.changeSelection(i,row_empleado, false, false);
                                jTable1.requestFocus();
                                break;
                            }else{
                                con.s.executeUpdate("UPDATE `t_novedades`"
                                        + "SET `ID_EPS`='"+get_id_eps(modelo.getValueAt(i, row_eps))+"',"
                                        + "`ID_AFP`='"+get_id_afp(modelo.getValueAt(i, row_afp))+"',"
                                        + "`ARL_NOV`='"+modelo.getValueAt(i, row_arl)+"',"
                                        + "`CCF_NOV`='"+modelo.getValueAt(i, row_ccf)+"',"
                                        + "`OBS_NOV`='INGRESO>USR:"+Main.login.getText()+">FR:"+new SimpleDateFormat("dd-MM-yyyy").format(new Date())+">FI:"+modelo.getValueAt(i, 3).toString()+","+modelo.getValueAt(i, 10).toString().toUpperCase()+"',"
                                        + "`ID_TIPO`=1"
                                        + " WHERE ID_EMPLEADO="+modelo.getValueAt(i, row_cedula)+""
                                        + " AND ID_EMPRESA='"+get_id_empleador(modelo.getValueAt(i, row_empleador))+"'"
                                        + " AND FECHA_INGRESO='"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_ingreso).toString()))+"'"
                                        + " AND FECHA_RETIRO='1900-01-01'"
                                        + " AND ID_TIPO=3");
                                con.s.executeUpdate("INSERT INTO `t_registro`"
                                        + " (ID_EMPLEADO,"
                                        + "ID_EMPRESA,"
                                        + "F_INGRESO,"
                                        + "F_RETIRO,"
                                        + "ID_TIPO,"
                                        + "REGISTRO,"
                                        + "F_REGISTRO,"
                                        + "FECHA,"
                                        + "ID_USUARIO)"
                                        + "VALUES ("+modelo.getValueAt(i, row_cedula)+","
                                        + "'"+get_id_empleador(modelo.getValueAt(i, row_empleador))+"',"
                                        + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_ingreso).toString()))+"',"
                                        + "'1900-01-01',"
                                        + "1,"
                                        + "'INGRESO',"
                                        + "'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"',"
                                        + "'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(modelo.getValueAt(i, row_f_ingreso).toString()))+"',"
                                        + "'"+Main.id_usuario+"')");
                                modelo.removeRow(i);
                                i=i-1;
                                confirm = true;
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

    private void add_epsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_epsActionPerformed
        // TODO add your handling code here:
        Add_EPS add_eps = new Add_EPS(this, true);
        add_eps.setLocationRelativeTo(this);
        add_eps.setVisible(true);
    }//GEN-LAST:event_add_epsActionPerformed

    private void add_afpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_afpActionPerformed
        // TODO add your handling code here:
        Add_AFP add_afp = new Add_AFP(this, true);
        add_afp.setLocationRelativeTo(this);
        add_afp.setVisible(true);
    }//GEN-LAST:event_add_afpActionPerformed

    private void add_arlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_arlActionPerformed
        // TODO add your handling code here:
        Add_ARL add_arl = new Add_ARL(this, true);
        add_arl.setLocationRelativeTo(this);
        add_arl.setVisible(true);
    }//GEN-LAST:event_add_arlActionPerformed

    private void add_ccfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_ccfActionPerformed
        // TODO add your handling code here:
        Add_CCF add_ccf = new Add_CCF(this, true);
        add_ccf.setLocationRelativeTo(this);
        add_ccf.setVisible(true);
    }//GEN-LAST:event_add_ccfActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        modelo = (DefaultTableModel) jTable1.getModel();
        if (jTable1.getRowCount()>0) {
            int j = jTable1.getRowCount();
            for (int i = 0; i < j; i++) {
                modelo.removeRow(jTable1.getRowCount()-1);
                jTable1.setModel(modelo);
            }
        }
        if (view_user_local.isSelected()) {
            load_user_preingresos(Main.login.getText());
        }else{
            load_all_preingresos();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void view_user_localActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_user_localActionPerformed
        // TODO add your handling code here:
        modelo = (DefaultTableModel) jTable1.getModel();
        if (jTable1.getRowCount()>0) {
            int j = jTable1.getRowCount();
            for (int i = 0; i < j; i++) {
                modelo.removeRow(jTable1.getRowCount()-1);
                jTable1.setModel(modelo);
            }
        }
        if (view_user_local.isSelected()) {
            load_user_preingresos(Main.login.getText());
        }else{
            load_all_preingresos();
        }
    }//GEN-LAST:event_view_user_localActionPerformed

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
            java.util.logging.Logger.getLogger(Add_Ingresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add_Ingresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add_Ingresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add_Ingresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Add_Ingresos dialog = new Add_Ingresos(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton add_afp;
    private javax.swing.JButton add_arl;
    private javax.swing.JButton add_barrio2;
    private javax.swing.JButton add_barrio3;
    private javax.swing.JButton add_barrio4;
    private javax.swing.JButton add_barrio5;
    private javax.swing.JButton add_ccf;
    private javax.swing.JButton add_emp;
    private javax.swing.JButton add_empresa;
    private javax.swing.JButton add_eps;
    private javax.swing.JButton add_par;
    private javax.swing.JButton add_par1;
    private javax.swing.JComboBox<String> bsc_cedula;
    private javax.swing.JComboBox<String> bsc_empleador;
    private javax.swing.JButton edit_emp;
    private javax.swing.JButton edit_emp1;
    private javax.swing.JButton edit_empresa;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JCheckBox sync_ARHI;
    private javax.swing.JTextField t_afp;
    private javax.swing.JTextField t_arl;
    private javax.swing.JTextField t_ccf;
    private javax.swing.JTextField t_empleado;
    private javax.swing.JTextField t_empleador;
    private javax.swing.JTextField t_eps;
    private javax.swing.JTextField t_municipio;
    private javax.swing.JTextField t_obra;
    private javax.swing.JTextField t_parentesco;
    private javax.swing.JCheckBox view_user_local;
    // End of variables declaration//GEN-END:variables
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
    bsc_cedula.removeAllItems();
    bsc_cedula.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_EMPLEADOS ORDER BY ID_EMP ASC;");
        while(r.next()){
            String str=r.getString("ID_EMP");
            bsc_cedula.addItem(str);
        }
        con.cerrar();
        perm_cedula=true;
        bsc_cedula.setSelectedItem(before);
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
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
    ResultSet r;
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
    }
}

public void tac_empleador(){
    tac_empleador.removeAllItems();
    tac_empleador.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_EMPRESAS ORDER BY NOMBRE_EMPRESA ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_EMPRESA");
            tac_empleador.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public void tac_parentesco(){
    tac_parentesco.removeAllItems();
    tac_parentesco.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_PARENTESCO ORDER BY NOMBRE_PAR ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_PAR");
            tac_parentesco.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public void tac_municipio(){
    tac_municipio.removeAllItems();
    tac_municipio.addItem("Seleccione..");
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
            tac_municipio.addItem(str+"-"+str1);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public void tac_obra(){
    tac_obra.removeAllItems();
    tac_obra.addItem("Seleccione..");
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
                                "        ON (t_municipios.ID_DEP = t_departamentos.ID_DEP) ORDER BY NOMBRE_OBRA ASC");
        while(r.next()){
            String str=r.getString("NOMBRE_OBRA");
            String str1=r.getString("NOMBRE_MUN");
            String str2=r.getString("NOMBRE_DEP");
            tac_obra.addItem(str+"-"+str1+"-"+str2);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}

public void tac_eps(){
    tac_eps_table.removeAllItems();
    tac_eps.removeAllItems();
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM t_eps ORDER BY NOMBRE_EPS ASC");
        while(r.next()){
            String str=r.getString("NOMBRE_EPS");
            tac_eps_table.addItem(str);
            tac_eps.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE,getStackTrace(j));
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public void tac_arl(){
    tac_arl_table.removeAllItems();
    tac_arl.removeAllItems();
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM t_arl ORDER BY NOMBRE_ARL ASC");
        while(r.next()){
            String str=r.getString("NOMBRE_ARL");
            tac_arl_table.addItem(str);
            tac_arl.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE,getStackTrace(j));
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public void tac_afp(){
    tac_afp_table.removeAllItems();
    tac_afp.removeAllItems();
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM t_afp ORDER BY NOMBRE_AFP ASC");
        while(r.next()){
            String str=r.getString("NOMBRE_AFP");
            tac_afp_table.addItem(str);
            tac_afp.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public void tac_ccf(){
    tac_ccf_table.removeAllItems();
    tac_ccf.removeAllItems();
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM t_ccf ORDER BY NOMBRE_CCF ASC");
        while(r.next()){
            String str=r.getString("NOMBRE_CCF");
            tac_ccf_table.addItem(str);
            tac_ccf.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}

public void load_all_preingresos(){
    modelo = (DefaultTableModel)jTable1.getModel(); 
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
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
                                + "WHERE (t_novedades.ID_TIPO = 3)\n"
                                + "ORDER BY ID_EMPLEADO ASC;");
        while(r.next()){
            modelo.addRow(fila);
            jTable1.setModel(modelo);
            modelo.setValueAt(r.getString("ID_EMPLEADO"),jTable1.getRowCount()-1,row_cedula);
            modelo.setValueAt(r.getString("NOMBRE_1_EMP")+" "+r.getString("NOMBRE_2_EMP")+" "+r.getString("APELLIDO_1_EMP")+" "+r.getString("APELLIDO_2_EMP"),jTable1.getRowCount()-1,row_empleado);
            modelo.setValueAt(r.getString("NOMBRE_EMPRESA"),jTable1.getRowCount()-1,row_empleador);
            modelo.setValueAt(new SimpleDateFormat("dd-MM-yyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(r.getString("FECHA_INGRESO"))),jTable1.getRowCount()-1,row_f_ingreso);
            modelo.setValueAt(r.getString("SALARIO_NOVEDAD"),jTable1.getRowCount()-1,row_salario);
            modelo.setValueAt(r.getString("NOMBRE_EPS"),jTable1.getRowCount()-1,row_eps);
            modelo.setValueAt(r.getString("NOMBRE_AFP"),jTable1.getRowCount()-1,row_afp);
            modelo.setValueAt(r.getString("ARL_NOV"),jTable1.getRowCount()-1,row_arl);
            modelo.setValueAt(r.getString("CCF_NOV"),jTable1.getRowCount()-1,row_ccf);
            modelo.setValueAt(r.getString("OBS_NOV"),jTable1.getRowCount()-1,row_obs);
            String id_obra=r.getString("ID_OBRA");
            modelo.setValueAt(load_obra(id_obra),jTable1.getRowCount()-1,row_obra);
        }
        con.cerrar();
    }catch(SQLException | ParseException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }

}
public void load_user_preingresos(String user){
    modelo = (DefaultTableModel)jTable1.getModel(); 
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
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
                                + "WHERE (t_novedades.ID_TIPO = 3)\n"
                                + "AND t_novedades.OBS_NOV LIKE '%PREINGRESO>USR:"+user+"%'"
                                + "ORDER BY FECHA_INGRESO DESC;");
        while(r.next()){
            modelo.addRow(fila);
            jTable1.setModel(modelo);
            modelo.setValueAt(r.getString("ID_EMPLEADO"),jTable1.getRowCount()-1,row_cedula);
            modelo.setValueAt(r.getString("NOMBRE_1_EMP")+" "+r.getString("NOMBRE_2_EMP")+" "+r.getString("APELLIDO_1_EMP")+" "+r.getString("APELLIDO_2_EMP"),jTable1.getRowCount()-1,row_empleado);
            modelo.setValueAt(r.getString("NOMBRE_EMPRESA"),jTable1.getRowCount()-1,row_empleador);
            modelo.setValueAt(new SimpleDateFormat("dd-MM-yyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(r.getString("FECHA_INGRESO"))),jTable1.getRowCount()-1,row_f_ingreso);
            modelo.setValueAt(r.getString("SALARIO_NOVEDAD"),jTable1.getRowCount()-1,row_salario);
            modelo.setValueAt(r.getString("NOMBRE_EPS"),jTable1.getRowCount()-1,row_eps);
            modelo.setValueAt(r.getString("NOMBRE_AFP"),jTable1.getRowCount()-1,row_afp);
            modelo.setValueAt(r.getString("ARL_NOV"),jTable1.getRowCount()-1,row_arl);
            modelo.setValueAt(r.getString("CCF_NOV"),jTable1.getRowCount()-1,row_ccf);
            modelo.setValueAt(r.getString("OBS_NOV"),jTable1.getRowCount()-1,row_obs);
            String id_obra=r.getString("ID_OBRA");
            modelo.setValueAt(load_obra(id_obra),jTable1.getRowCount()-1,row_obra);
        }
        con.cerrar();
    }catch(SQLException | ParseException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }

}
public String load_obra(String id_obra){
    
    String ret="";
    Conexion con = new Conexion();
    con.conexion();
    try {
        ResultSet r;
        r = con.s.executeQuery ("SELECT *\n" +
                "FROM\n" +
                "    t_obra\n" +
                "    INNER JOIN t_municipios \n" +
                "        ON (t_obra.ID_MUN_OBRA = t_municipios.ID_MUN)\n" +
                "    INNER JOIN t_departamentos \n" +
                "        ON (t_municipios.ID_DEP = t_departamentos.ID_DEP) WHERE ID_OBRA = "+id_obra+" ORDER BY NOMBRE_OBRA ASC");
        if(r.next()){
            String str=r.getString("NOMBRE_OBRA");
            String str1=r.getString("NOMBRE_MUN");
            String str2=r.getString("NOMBRE_DEP");
            ret = (str+"-"+str1+"-"+str2);
        }   
        con.cerrar();
    } catch (SQLException ex) {
        con.cerrar();
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null,ex,"Error",JOptionPane.ERROR_MESSAGE);
    }
    return ret;
}
public void load_empleador(String nit){
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
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
    }

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

public void load_data_empleador(String afp){
    if (!afp.equals("Seleccione..")) {
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
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
        }
    }
    else{
        t_empleador.setText("");
        
    }
}
public boolean check_cedula(Object ced){
    boolean ret=false;
    if (ced!=null) {
        if (chech_char(ced.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (comprobarLong(ced.toString().trim())) {
                if(!ced.toString().equals("")){
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
                if (str_dia>0 & str_dia<31) {
                    if (str_mes>0 & str_mes<12) {
                        if (str_año>=1900 & str_año<=2050) {
                            Calendar ahoraCal = Calendar.getInstance();
                            ahoraCal.set(str_año,str_mes-1,str_dia);
                            //System.out.println(ahoraCal.getTime());
                            ret=true;
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
public boolean check_eps(Object eps){
    boolean ret=false;
    if (eps!=null) {
        if (chech_char(eps.toString().trim(),"'#$%&=?¡¿/*+[]{};:<>,")) {
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
        if (chech_char(afp.toString().trim(),"'#$%&=?¡¿/*+[]{};:<>,")) {
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
        if (chech_char(arl.toString().trim(),"'#$%&=?¡¿/*+[]{};:<>,")) {
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
        if (chech_char(ccf.toString().trim(),"'#$%&=?¡¿/*+[]{};:<>,")) {
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
public boolean check_field (Object field){
boolean ret=false;
    if (field!=null) {
        if (chech_char(field.toString().trim(),"'$%&()=*+[]{}/")) {
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
public boolean verify_data(){
    boolean ret=true;
    modelo = (DefaultTableModel)jTable1.getModel(); 
    if (jTable1.getRowCount()>0) {
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            if (modelo.getValueAt(i, row_eps)==null) {
                modelo.setValueAt("NO ASIGNADA", i, row_eps);
            }else{
                if (modelo.getValueAt(i, row_eps).toString().equals("")){
                    modelo.setValueAt("NO ASIGNADA", i, row_eps);
                }
            }
            if (modelo.getValueAt(i, row_afp)==null) {
                modelo.setValueAt("NO ASIGNADA", i, row_afp);
            }else{
                if (modelo.getValueAt(i, row_afp).toString().equals("")){
                    modelo.setValueAt("NO ASIGNADA", i, row_afp);
                }
            }
            if (modelo.getValueAt(i, row_arl)==null) {
                modelo.setValueAt("NO ASIGNADA", i, row_arl);
            }else{
                if (modelo.getValueAt(i, row_arl).toString().equals("")){
                    modelo.setValueAt("NO ASIGNADA", i, row_arl);
                }
            }
            if (modelo.getValueAt(i, row_ccf)==null) {
                modelo.setValueAt("NO ASIGNADA", i, row_ccf);
            }else{
                if (modelo.getValueAt(i, row_ccf).toString().equals("")){
                    modelo.setValueAt("NO ASIGNADA", i, row_ccf);
                }
            }
            if (modelo.getValueAt(i, row_obs)==null) {
                modelo.setValueAt("Sin observaciones", i, row_obs);
            }else{
                if (modelo.getValueAt(i, row_obs).toString().equals("")){
                    modelo.setValueAt("Sin observaciones", i, row_obs);
                }
            }
        }
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            if (check_eps(modelo.getValueAt(i, row_eps))) {
                if (check_afp(modelo.getValueAt(i, row_afp))) {
                    if (check_arl(modelo.getValueAt(i, row_arl))) {
                        if (check_ccf(modelo.getValueAt(i, row_ccf))) {
                            if (check_field(modelo.getValueAt(i, row_obs))) {//observaciones
//                                if (!sync_ARHI.isSelected()|search_info(modelo.getValueAt(i, 0).toString())) {//verifica que el empleado tenga info sociodemografica
//                                    
//                                } else {
//                                    jTable1.changeSelection(i,0, false, false);
//                                    jTable1.requestFocus();
//                                    JOptionPane.showMessageDialog(this,"Para ingresar los datos a ARHI es necesario que\neste empleado cuente con información sociodemográfica ingresada","Error",JOptionPane.ERROR_MESSAGE);
//                                    ret=false&ret;
//                                    break;
//                                }
                                ret=true&ret;
                            } else {
                                jTable1.changeSelection(i,row_obs, false, false);
                                jTable1.requestFocus();
                                JOptionPane.showMessageDialog(this,"Verifique las observaciones del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                ret=false&ret;
                                break;
                            }
                        } else {
                            jTable1.changeSelection(i,row_ccf, false, false);
                            jTable1.requestFocus();
                            JOptionPane.showMessageDialog(this,"Verifique la CCF del empleado","Error",JOptionPane.ERROR_MESSAGE);
                            ret=false&ret;
                            break;
                        }
                    } else {
                        jTable1.changeSelection(i,row_arl, false, false);
                        jTable1.requestFocus();
                        JOptionPane.showMessageDialog(this,"Verifique la ARL del empleado","Error",JOptionPane.ERROR_MESSAGE);
                        ret=false&ret;
                        break;
                    }
                } else {
                    jTable1.changeSelection(i,row_afp, false, false);
                    jTable1.requestFocus();
                    JOptionPane.showMessageDialog(this,"Verifique la AFP del empleado","Error",JOptionPane.ERROR_MESSAGE);
                    ret=false&ret;
                    break;
                }
            } else {
                jTable1.changeSelection(i,row_eps, false, false);
                jTable1.requestFocus();
                JOptionPane.showMessageDialog(this,"Verifique la EPS del empleado","Error",JOptionPane.ERROR_MESSAGE);
                ret=false&ret;
                break;
            }
        }
    }else{
        JOptionPane.showMessageDialog(this,"La tabla no tiene registros","Error",JOptionPane.ERROR_MESSAGE);
    }
    
return ret;
}
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
public String get_id_eps(Object eps){
    String i = "";
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_EPS WHERE NOMBRE_EPS='"+eps+"'");
        if(r.next()){
            i = r.getString("ID_EPS");
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
public String get_id_arl(Object arl){
    String i = "";
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_ARL WHERE NOMBRE_ARL='"+arl+"'");
        if(r.next()){
            i = r.getString("ID_ARL");
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
public String get_id_afp(Object afp){
    String i = "";
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_AFP WHERE NOMBRE_AFP='"+afp+"'");
        if(r.next()){
            i = r.getString("ID_AFP");
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
public String get_id_ccf(Object ccf){
    String i = "";
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_CCF WHERE NOMBRE_CCF='"+ccf+"'");
        if(r.next()){
            i = r.getString("ID_CCF");
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

public static String getStackTrace(Exception e) {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        e.printStackTrace(pWriter);
        return sWriter.toString();
    }

}
