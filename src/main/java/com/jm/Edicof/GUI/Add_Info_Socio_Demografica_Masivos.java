/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.GUI;

import com.jm.Edicof.Clases.CellRender_Info_Sociodemografica;
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
public class Add_Info_Socio_Demografica_Masivos extends javax.swing.JDialog {
    static Dimension screenSize = null;
    DefaultTableModel modelo = null;
    Object [] fila = new Object[16];
    boolean perm_cedula = true;
    boolean perm_empleador = true;
    boolean perm_tabla = false;
    //////////////////////----------------------------
    JTextField tb_cedula_table = new JTextField();
    JTextField tb_barrio_table = new JTextField();
    JTextField tb_mun_table = new JTextField();
    JTextField tb_tip_viv_table = new JTextField();
    JTextField tb_est_civil_table = new JTextField();
    JTextField tb_niv_academico_table = new JTextField();
    JTextField tb_nacionalidad_table = new JTextField();
    JTextField tb_cargo_table = new JTextField();
    JTextField tb_area_trabajo_table = new JTextField();
    JTextField tb_parentesco_table = new JTextField();
    JTextField tb_ahorro_table = new JTextField();
    TextAutoCompleter tac_cedula_table = new TextAutoCompleter(tb_cedula_table);
    TextAutoCompleter tac_barrio_table = new TextAutoCompleter(tb_barrio_table);
    TextAutoCompleter tac_mun_table = new TextAutoCompleter(tb_mun_table);
    TextAutoCompleter tac_tip_viv_table = new TextAutoCompleter(tb_tip_viv_table);
    TextAutoCompleter tac_est_civil_table = new TextAutoCompleter(tb_est_civil_table);
    TextAutoCompleter tac_niv_academico_table = new TextAutoCompleter(tb_niv_academico_table);
    TextAutoCompleter tac_nacionalidad_table = new TextAutoCompleter(tb_nacionalidad_table);
    TextAutoCompleter tac_cargo_table = new TextAutoCompleter(tb_cargo_table);
    TextAutoCompleter tac_area_trabajo_table = new TextAutoCompleter(tb_area_trabajo_table);
    TextAutoCompleter tac_parentesco_table = new TextAutoCompleter(tb_parentesco_table);
    TextAutoCompleter tac_ahorro_table = new TextAutoCompleter(tb_ahorro_table);
/////////////////////////----------------------------------
    String before_edit_cell = null;
    //static DefaultTableModel modelo = new DefaultTableModel();
    /**
     * Creates new form Add_Preingresos
     */
    public Add_Info_Socio_Demografica_Masivos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        //////////////////----------------------------------------
        info_soci_demo.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {//e.getType() == TableModelEvent.UPDATE
                    int fila = e.getFirstRow();
                    int columna = e.getColumn();
                    if (columna == 0) {
                        //check_empleado();
                    }
                    
                }
                //System.out.println(e);
            }
        });
        //////////////////----------------------------------------
        info_soci_demo.setDefaultRenderer (Object.class, new CellRender_Info_Sociodemografica());
        //////////////////----------------------------------------
        screenSize = Main.getsize();
        this.setSize(screenSize.width, screenSize.height-10);
        this.setLocationRelativeTo(null);
        /////////////////-----------------------------------------
        tac_cedula();
        tac_barrio();
        tac_municipio();
        tac_tipo_viv();
        tac_est_civil();
        tac_niv_academico();
        tac_nacionalidad();
        tac_area_trabajo();
        tac_cargo();
        tac_parentesco();
        tac_ahorro();
        //////////////---------------------------------------
        tac_cedula_table.setMode(0);
        tac_barrio_table.setMode(0);
        tac_mun_table.setMode(0);
        tac_tip_viv_table.setMode(0);
        tac_est_civil_table.setMode(0);
        tac_niv_academico_table.setMode(0);
        tac_nacionalidad_table.setMode(0);
        tac_area_trabajo_table.setMode(0);
        tac_cargo_table.setMode(0);
        tac_parentesco_table.setMode(0);
        tac_ahorro_table.setMode(0);
        /////////////////-----------------------------------------
        info_soci_demo.getColumnModel().getColumn(0).setCellEditor(new MyTableCellEditorDate(tb_cedula_table,true));
        info_soci_demo.getColumnModel().getColumn(3).setCellEditor(new MyTableCellEditorDate(tb_mun_table,true));
        info_soci_demo.getColumnModel().getColumn(4).setCellEditor(new MyTableCellEditorDate(tb_barrio_table,true));
        info_soci_demo.getColumnModel().getColumn(6).setCellEditor(new MyTableCellEditorDate(tb_tip_viv_table,true));
//        info_soci_demo.getColumnModel().getColumn(7).setCellEditor(new MyTableCellEditorDate(tb_area_trabajo_table,true));
//        info_soci_demo.getColumnModel().getColumn(8).setCellEditor(new MyTableCellEditorDate(tb_cargo_table,true));
        info_soci_demo.getColumnModel().getColumn(7).setCellEditor(new MyTableCellEditorDate(tb_est_civil_table,true));
        info_soci_demo.getColumnModel().getColumn(9).setCellEditor(new MyTableCellEditorDate(tb_niv_academico_table,true));
        //info_soci_demo.getColumnModel().getColumn(12).setCellEditor(new MyTableCellEditorDate(tb_nacionalidad_table,true));
        info_soci_demo.getColumnModel().getColumn(15).setCellEditor(new MyTableCellEditorDate(tb_parentesco_table,true));
        info_soci_demo.getColumnModel().getColumn(20).setCellEditor(new MyTableCellEditorDate(tb_ahorro_table,true));
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
                if (info_soci_demo.getModel().isCellEditable(info_soci_demo.getSelectedRow(),info_soci_demo.getSelectedColumn())) {
                    modelo = (DefaultTableModel) info_soci_demo.getModel();
                    String aux = (String)modelo.getValueAt(info_soci_demo.getSelectedRow(), info_soci_demo.getSelectedColumn());
                    if (aux!=null) {
                        if (aux.length()>0) {
                            before_edit_cell= aux;
                            modelo.setValueAt("",info_soci_demo.getSelectedRow(),info_soci_demo.getSelectedColumn());
                            info_soci_demo.setModel(modelo);
                            info_soci_demo.requestFocus();
                        }
                    }else{
                        before_edit_cell = "";
                    }
                }
            }else{
                if(a==KeyEvent.VK_DELETE) {
                    if (info_soci_demo.getModel().isCellEditable(info_soci_demo.getSelectedRow(),info_soci_demo.getSelectedColumn())) {
                        modelo = (DefaultTableModel) info_soci_demo.getModel();
                        modelo.setValueAt("",info_soci_demo.getSelectedRow(),info_soci_demo.getSelectedColumn());
                        info_soci_demo.setModel(modelo);
                        before_edit_cell = "";
                        if (info_soci_demo.isEditing()){
                            info_soci_demo.getCellEditor().stopCellEditing();
                        }
                        evt.consume();
                    }

                }else{
                    if(a==KeyEvent.VK_ESCAPE) {
                        modelo = (DefaultTableModel) info_soci_demo.getModel();
                        String aux = (String)modelo.getValueAt(info_soci_demo.getSelectedRow(), info_soci_demo.getSelectedColumn());
                        if (aux!=null) {
                            if (info_soci_demo.isEditing()){
                                modelo.setValueAt(before_edit_cell,info_soci_demo.getSelectedRow(),info_soci_demo.getSelectedColumn());
                                info_soci_demo.setModel(modelo);
                                info_soci_demo.getCellEditor().cancelCellEditing();
                                evt.setKeyCode(10);
                            }else{
                                evt.consume();
                            }
                            evt.consume();
                        }else{
                            if (info_soci_demo.isEditing()){
                                info_soci_demo.getCellEditor().cancelCellEditing();
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
        info_soci_demo = new javax.swing.JTable();
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
        setTitle("Agregar Info Sociodemografica");
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
                .addContainerGap(113, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap(115, Short.MAX_VALUE))
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

        new PegarExcel(info_soci_demo);
        info_soci_demo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id*", "Direccion*", "Tel/Cel*", "Municipio Residencia*", "Barrio*", "Estrato*", "Tipo Vivienda*", "Estado Civil*", "No Hijos*", "Nivel Academico*", "Personas a Cargo*", "Personas en el Hogar*", "Aportantes Hogar*", "Nombre Acudiente*", "Tel/Cel Acudiente*", "Parentesco Acudiente*", "Productos Financieros*", "Tiempo Libre*", "Servicios Publicos*", "Ahorro*", "Fuente Ahorro*"
            }
        ));
        info_soci_demo.setToolTipText("");
        info_soci_demo.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        info_soci_demo.setComponentPopupMenu(jPopupMenu1);
        info_soci_demo.getTableHeader().setReorderingAllowed(false);
        info_soci_demo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                info_soci_demoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                info_soci_demoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(info_soci_demo);
        if (info_soci_demo.getColumnModel().getColumnCount() > 0) {
            info_soci_demo.getColumnModel().getColumn(0).setMinWidth(90);
            info_soci_demo.getColumnModel().getColumn(0).setPreferredWidth(90);
            info_soci_demo.getColumnModel().getColumn(0).setMaxWidth(90);
            info_soci_demo.getColumnModel().getColumn(1).setMinWidth(250);
            info_soci_demo.getColumnModel().getColumn(1).setPreferredWidth(250);
            info_soci_demo.getColumnModel().getColumn(1).setMaxWidth(250);
            info_soci_demo.getColumnModel().getColumn(2).setMinWidth(150);
            info_soci_demo.getColumnModel().getColumn(2).setPreferredWidth(150);
            info_soci_demo.getColumnModel().getColumn(2).setMaxWidth(150);
            info_soci_demo.getColumnModel().getColumn(3).setMinWidth(150);
            info_soci_demo.getColumnModel().getColumn(3).setPreferredWidth(150);
            info_soci_demo.getColumnModel().getColumn(3).setMaxWidth(150);
            info_soci_demo.getColumnModel().getColumn(4).setMinWidth(150);
            info_soci_demo.getColumnModel().getColumn(4).setPreferredWidth(150);
            info_soci_demo.getColumnModel().getColumn(4).setMaxWidth(150);
            info_soci_demo.getColumnModel().getColumn(5).setMinWidth(80);
            info_soci_demo.getColumnModel().getColumn(5).setPreferredWidth(80);
            info_soci_demo.getColumnModel().getColumn(5).setMaxWidth(80);
            info_soci_demo.getColumnModel().getColumn(6).setMinWidth(150);
            info_soci_demo.getColumnModel().getColumn(6).setPreferredWidth(150);
            info_soci_demo.getColumnModel().getColumn(6).setMaxWidth(150);
            info_soci_demo.getColumnModel().getColumn(7).setMinWidth(100);
            info_soci_demo.getColumnModel().getColumn(7).setPreferredWidth(100);
            info_soci_demo.getColumnModel().getColumn(7).setMaxWidth(100);
            info_soci_demo.getColumnModel().getColumn(8).setMinWidth(100);
            info_soci_demo.getColumnModel().getColumn(8).setPreferredWidth(100);
            info_soci_demo.getColumnModel().getColumn(8).setMaxWidth(100);
            info_soci_demo.getColumnModel().getColumn(9).setMinWidth(150);
            info_soci_demo.getColumnModel().getColumn(9).setPreferredWidth(150);
            info_soci_demo.getColumnModel().getColumn(9).setMaxWidth(150);
            info_soci_demo.getColumnModel().getColumn(10).setMinWidth(150);
            info_soci_demo.getColumnModel().getColumn(10).setPreferredWidth(150);
            info_soci_demo.getColumnModel().getColumn(10).setMaxWidth(150);
            info_soci_demo.getColumnModel().getColumn(11).setMinWidth(150);
            info_soci_demo.getColumnModel().getColumn(11).setPreferredWidth(150);
            info_soci_demo.getColumnModel().getColumn(11).setMaxWidth(150);
            info_soci_demo.getColumnModel().getColumn(12).setMinWidth(150);
            info_soci_demo.getColumnModel().getColumn(12).setPreferredWidth(150);
            info_soci_demo.getColumnModel().getColumn(12).setMaxWidth(150);
            info_soci_demo.getColumnModel().getColumn(13).setMinWidth(250);
            info_soci_demo.getColumnModel().getColumn(13).setPreferredWidth(250);
            info_soci_demo.getColumnModel().getColumn(13).setMaxWidth(250);
            info_soci_demo.getColumnModel().getColumn(14).setMinWidth(150);
            info_soci_demo.getColumnModel().getColumn(14).setPreferredWidth(150);
            info_soci_demo.getColumnModel().getColumn(14).setMaxWidth(150);
            info_soci_demo.getColumnModel().getColumn(15).setMinWidth(150);
            info_soci_demo.getColumnModel().getColumn(15).setPreferredWidth(150);
            info_soci_demo.getColumnModel().getColumn(15).setMaxWidth(150);
            info_soci_demo.getColumnModel().getColumn(16).setMinWidth(250);
            info_soci_demo.getColumnModel().getColumn(16).setPreferredWidth(250);
            info_soci_demo.getColumnModel().getColumn(16).setMaxWidth(250);
            info_soci_demo.getColumnModel().getColumn(17).setMinWidth(200);
            info_soci_demo.getColumnModel().getColumn(17).setPreferredWidth(200);
            info_soci_demo.getColumnModel().getColumn(17).setMaxWidth(200);
            info_soci_demo.getColumnModel().getColumn(18).setMinWidth(250);
            info_soci_demo.getColumnModel().getColumn(18).setPreferredWidth(250);
            info_soci_demo.getColumnModel().getColumn(18).setMaxWidth(250);
            info_soci_demo.getColumnModel().getColumn(19).setMinWidth(60);
            info_soci_demo.getColumnModel().getColumn(19).setPreferredWidth(60);
            info_soci_demo.getColumnModel().getColumn(19).setMaxWidth(60);
            info_soci_demo.getColumnModel().getColumn(20).setMinWidth(300);
            info_soci_demo.getColumnModel().getColumn(20).setPreferredWidth(300);
            info_soci_demo.getColumnModel().getColumn(20).setMaxWidth(300);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 912, Short.MAX_VALUE)
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
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)))
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
        modelo = (DefaultTableModel) info_soci_demo.getModel();
        modelo.insertRow(info_soci_demo.getSelectedRow()+1, fila);
        info_soci_demo.setModel(modelo);
    }//GEN-LAST:event_add_rowActionPerformed

    private void del_rowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_del_rowActionPerformed
        // TODO add your handling code here:
        if (info_soci_demo.getRowCount()>0) {
            if (info_soci_demo.getSelectedRow()>-1) {
                modelo = (DefaultTableModel) info_soci_demo.getModel();
                modelo.removeRow(info_soci_demo.getSelectedRow());
                info_soci_demo.setModel(modelo);
            }else{
                JOptionPane.showMessageDialog(this,"No se ha seleccionado ningun registro","Error",JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this,"La tabla no contiene registros","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_del_rowActionPerformed

    private void del_tableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_del_tableActionPerformed
        // TODO add your handling code here:
        modelo = (DefaultTableModel) info_soci_demo.getModel();
        if (info_soci_demo.getRowCount()>0) {
            //System.out.println("Filas: "+jTable1.getRowCount());
            int j = info_soci_demo.getRowCount();
            for (int i = 0; i < j; i++) {
                //System.out.println("q");
                modelo.removeRow(info_soci_demo.getRowCount()-1);
                info_soci_demo.setModel(modelo);
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

    private void info_soci_demoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_info_soci_demoKeyReleased

        try {
            char a=evt.getKeyChar();
            int b=evt.getKeyCode();
            System.out.println("Key Released Tabla: '"+a+"'("+b+")");
            //System.out.println("Longitud: "+((JTextField) jTable1.getEditorComponent()).getText().length());
            if (info_soci_demo.getEditingColumn()==2 | info_soci_demo.getEditingColumn()==4 |info_soci_demo.getEditingColumn()==5) {
                //autocomplete_date_released(b);
            }
        } catch (UnsupportedOperationException uop) {
            System.out.println(uop.getMessage());
        }catch(Exception ev){
            ev.printStackTrace();
        }
    }//GEN-LAST:event_info_soci_demoKeyReleased

    private void info_soci_demoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_info_soci_demoKeyPressed
        // TODO add your handling code here:
        char a=evt.getKeyChar();
        int b=evt.getKeyCode();
        System.out.println("Key Pressed Tabla: '"+a+"'("+b+")");
        if (info_soci_demo.getEditingColumn()==2|info_soci_demo.getEditingColumn()==4|info_soci_demo.getEditingColumn()==5) {
            //autocomplete_date_pressed(b);
        }
        if((b>=96 & b<=105)|(b>=65 & b<=90)) {
            //        System.out.println("alpha");
            if (info_soci_demo.getModel().isCellEditable(info_soci_demo.getSelectedRow(),info_soci_demo.getSelectedColumn())) {
                modelo = (DefaultTableModel) info_soci_demo.getModel();
                String aux = (String)modelo.getValueAt(info_soci_demo.getSelectedRow(), info_soci_demo.getSelectedColumn());
                if (aux!=null) {
                    //            System.out.println("Longitud: "+aux.length());
                    if (aux.length()>0) {
                        before_edit_cell= aux;
                        //                System.out.println("Nuevo valor de la celda: "+before_edit_cell);
                        modelo.setValueAt("",info_soci_demo.getSelectedRow(),info_soci_demo.getSelectedColumn());
                        info_soci_demo.setModel(modelo);
                        info_soci_demo.requestFocus();
                    }
                }else{
                    before_edit_cell = "";
                }
            }
        }else{
            if(a==KeyEvent.VK_DELETE) {
                //            System.out.println("DEL");
                if (info_soci_demo.getModel().isCellEditable(info_soci_demo.getSelectedRow(),info_soci_demo.getSelectedColumn())) {
                    modelo = (DefaultTableModel) info_soci_demo.getModel();
                    modelo.setValueAt("",info_soci_demo.getSelectedRow(),info_soci_demo.getSelectedColumn());
                    info_soci_demo.setModel(modelo);
                    before_edit_cell = "";
                    if (info_soci_demo.isEditing()){
                        info_soci_demo.getCellEditor().stopCellEditing();
                    }
                    evt.consume();
                }

            }else{
                if(a==KeyEvent.VK_ESCAPE) {
                    //                System.out.println("ESC");
                    modelo = (DefaultTableModel) info_soci_demo.getModel();
                    String aux = (String)modelo.getValueAt(info_soci_demo.getSelectedRow(), info_soci_demo.getSelectedColumn());
                    //                System.out.println("Antes: "+aux);
                    if (aux!=null) {
                        //                    System.out.println("aux no null");
                        if (info_soci_demo.isEditing()){
                            //                        System.out.println("recuperando dato");
                            modelo.setValueAt(before_edit_cell,info_soci_demo.getSelectedRow(),info_soci_demo.getSelectedColumn());
                            info_soci_demo.setModel(modelo);
                            info_soci_demo.getCellEditor().cancelCellEditing();
                        }else{
                            evt.consume();
                        }
                        evt.consume();
                    }else{
                        if (info_soci_demo.isEditing()){
                            info_soci_demo.getCellEditor().cancelCellEditing();
                        }
                        evt.consume();
                    }
                }
            }
        }
    }//GEN-LAST:event_info_soci_demoKeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        String arl = "";
        String ccf="";
        String usu="";
        boolean confirm = false;
        if(verify_data()){
            if (JOptionPane.showConfirmDialog(this, "La informacion esta completa\nEsta seguro que desea almacenar la información?", "Confirmación", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                for (int i = 0; i < info_soci_demo.getRowCount(); i++) {
                    int id_mun_res=0;
                    int id_barrio=0;
                    int id_tip_vivienda=0;
                    int id_area=0;
                    int id_cargo=0;
                    int id_est_civil=0;
                    int id_niv_acad=0;
                    int id_nacion=0;
                    int id_par=0;
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r;
                    try{
                        //**************GET ID MUNICIPIO RESIDENCIA
                        id_mun_res=Integer.parseInt(get_id_municipio(info_soci_demo.getValueAt(i, 3)));
                        
                        //**************GET ID BARRIO
                        id_barrio=Integer.parseInt(get_id_barrio(info_soci_demo.getValueAt(i, 4)));
                        //**************GET ID TIPO VIVIENDA
                        r = con.s.executeQuery ("SELECT *\n" +
                                                "FROM\n" +
                                                "    t_tipo_vivienda WHERE NOMBRE_TIPO_VIVIENDA = '"+info_soci_demo.getValueAt(i, 6).toString()+"';");
                        if(r.next()){
                            id_tip_vivienda=r.getInt("ID_TIPO_VIVIENDA");
                        }
//                        //**************GET ID AREA
//                        r = con.s.executeQuery ("SELECT *\n" +
//                                                "FROM\n" +
//                                                "    t_actividades WHERE NOMBRE_ACTIVIDAD = '"+info_soci_demo.getValueAt(i, 7).toString()+"';");
//                        if(r.next()){
//                            id_area=r.getInt("ID_ACTIVIDAD");
//                        }
//                        //**************GET ID CARGO
//                        r = con.s.executeQuery ("SELECT *\n" +
//                                                "FROM\n" +
//                                                "    t_cargo WHERE NOMBRE_CARGO = '"+info_soci_demo.getValueAt(i, 8).toString()+"';");
//                        if(r.next()){
//                            id_cargo=r.getInt("ID_CARGO");
//                        }
                        //**************GET ID ESTADO CIVIL
                        r = con.s.executeQuery ("SELECT *\n" +
                                                "FROM\n" +
                                                "    t_estado_civil WHERE NOMBRE_ESTADO = '"+info_soci_demo.getValueAt(i, 7).toString()+"';");
                        if(r.next()){
                            id_est_civil=r.getInt("ID_ESTADO");
                        }
                        //**************GET ID NIVEL ACADEMICO
                        r = con.s.executeQuery ("SELECT *\n" +
                                                "FROM\n" +
                                                "    t_nivel_academico WHERE NOMBRE_NIVEL = '"+info_soci_demo.getValueAt(i, 9).toString()+"';");
                        if(r.next()){
                            id_niv_acad=r.getInt("ID_NIVEL");
                        }
//                        //**************GET ID NACIONALIDAD
//                        r = con.s.executeQuery ("SELECT *\n" +
//                                                "FROM\n" +
//                                                "    t_nacionalidad WHERE NOMBRE_NACION = '"+info_soci_demo.getValueAt(i, 12).toString()+"';");
//                        if(r.next()){
//                            id_nacion=r.getInt("ID_NACION");
//                        }
                        //**************GET ID PARENTESCO
                        r = con.s.executeQuery ("SELECT *\n" +
                                                "FROM\n" +
                                                "    t_parentesco WHERE NOMBRE_PAR = '"+info_soci_demo.getValueAt(i, 15).toString()+"';");
                        if(r.next()){
                            id_par=r.getInt("ID_PAR");
                        }
                        //**************QUERY PRODUCTOS FINANCIEROS
                        set_prod_financieros(info_soci_demo.getValueAt(i, 0), info_soci_demo.getValueAt(i, 16));
                        //**************QUERY TIEMPO LIBRE
                        set_tiemp_lib(info_soci_demo.getValueAt(i, 0), info_soci_demo.getValueAt(i, 17));
                        //**************QUERY SERVICIOS PUBLICOS
                        set_serv_pub(info_soci_demo.getValueAt(i, 0), info_soci_demo.getValueAt(i, 18));
                        //**************QUERY FUENTES AHORROS
                        set_fte_ahorro(info_soci_demo.getValueAt(i, 0), info_soci_demo.getValueAt(i, 20));
                        con.s.executeUpdate("INSERT INTO `t_info_sociodemografica`("
                                + "`ID_EMP`, "
                                + "`DIRECCION_EMP`, "
                                + "`TEL_CEL_EMP`, "
                                + "`ID_ESTADO_CIVIL_EMP`, "
                                + "`NUM_HIJOS_EMP`, "
                                + "`NOMBRE_ACUDIENTE_EMP`, "
                                + "`TEL_CEL_ACU_EMP`, "
                                + "`ID_PAR_ACU_EMP`, "
                                + "`ID_BARRIO`, "
                                + "`ID_MUN_RES_EMP`, "
                                + "`ESTRATO_EMP`, "
                                + "`PER_CARGO_EMP`, "
                                + "`ID_NIV_ACAD_EMP`, "
                                + "`ID_TIP_VIV_EMP`, "
                                + "`PERS_HOGAR_EMP`, "
                                + "`PERS_AP_HOGAR_EMP`, "
                                + "`AHORRO_EMP`) "
                                + "VALUES ("+info_soci_demo.getValueAt(i, 0).toString()+","
                                + "'"+info_soci_demo.getValueAt(i, 1).toString().toUpperCase()+"',"
                                + "'"+info_soci_demo.getValueAt(i, 2).toString().toUpperCase()+"',"
                                + ""+id_est_civil+","
                                + ""+info_soci_demo.getValueAt(i, 8).toString()+","
                                + "'"+info_soci_demo.getValueAt(i, 13).toString().toUpperCase()+"',"
                                + "'"+info_soci_demo.getValueAt(i, 14).toString()+"',"
                                + ""+id_par+","
                                + ""+id_barrio+","
                                + ""+id_mun_res+","
                                + ""+info_soci_demo.getValueAt(i, 5).toString()+","
                                + ""+info_soci_demo.getValueAt(i, 10).toString()+","
                                + ""+id_niv_acad+","
                                + ""+id_tip_vivienda+","
                                + ""+info_soci_demo.getValueAt(i, 11).toString()+","
                                + ""+info_soci_demo.getValueAt(i, 12).toString()+","
                                + "'"+info_soci_demo.getValueAt(i, 19).toString()+"')");
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
        modelo = (DefaultTableModel) info_soci_demo.getModel();
        if (info_soci_demo.getRowCount()>0) {
            //System.out.println("Filas: "+jTable1.getRowCount());
            int j = info_soci_demo.getRowCount();
            for (int i = 0; i < j; i++) {
                //System.out.println("q");
                modelo.removeRow(info_soci_demo.getRowCount()-1);
                info_soci_demo.setModel(modelo);
            }
        }else{
            JOptionPane.showMessageDialog(this,"La tabla no contiene registros","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        modelo = (DefaultTableModel) info_soci_demo.getModel();
        modelo.insertRow(info_soci_demo.getSelectedRow()+1, fila);
        info_soci_demo.setModel(modelo);

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (info_soci_demo.getRowCount()>0) {
            if (info_soci_demo.getSelectedRow()>-1) {
                modelo = (DefaultTableModel) info_soci_demo.getModel();
                modelo.removeRow(info_soci_demo.getSelectedRow());
                info_soci_demo.setModel(modelo);
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
            java.util.logging.Logger.getLogger(Add_Info_Socio_Demografica_Masivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Add_Info_Socio_Demografica_Masivos dialog = new Add_Info_Socio_Demografica_Masivos(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable info_soci_demo;
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
    // End of variables declaration//GEN-END:variables
public  ArrayList call_sel_emp(String c, String t){
    Sel_Empleador sel=new Sel_Empleador(this,true,c,t);
    sel.setLocationRelativeTo(this);
    sel.setVisible(true);
return sel.getRet();
}
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
public final void tac_barrio(){
    tac_barrio_table.removeAllItems();
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
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
    }
}
public final void tac_municipio(){
    tac_mun_table.removeAllItems();
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
            tac_mun_table.addItem(str+"-"+str1);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public final void tac_tipo_viv(){
    tac_tip_viv_table.removeAllItems();
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT *\n" +
                                "FROM\n" +
                                "    `t_tipo_vivienda` ORDER BY NOMBRE_TIPO_VIVIENDA;");
        while(r.next()){
            String str=r.getString("NOMBRE_TIPO_VIVIENDA");
            tac_tip_viv_table.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public final void tac_est_civil(){
    tac_est_civil_table.removeAllItems();
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT *\n" +
                                "FROM\n" +
                                "    `t_estado_civil` ORDER BY NOMBRE_ESTADO;");
        while(r.next()){
            String str=r.getString("NOMBRE_ESTADO");
            tac_est_civil_table.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public final void tac_niv_academico(){
    tac_niv_academico_table.removeAllItems();
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT *\n" +
                                "FROM\n" +
                                "    `t_nivel_academico` ORDER BY NOMBRE_NIVEL;");
        while(r.next()){
            String str=r.getString("NOMBRE_NIVEL");
            tac_niv_academico_table.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public final void tac_nacionalidad(){
    tac_nacionalidad_table.removeAllItems();
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT *\n" +
                                "FROM\n" +
                                "    `t_nacionalidad` ORDER BY NOMBRE_NACION ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_NACION");
            tac_nacionalidad_table.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public final void tac_area_trabajo(){
    tac_area_trabajo_table.removeAllItems();
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
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
    }
}
public final void tac_cargo(){
    tac_cargo_table.removeAllItems();
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
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
    }
}
public final void tac_parentesco(){
    tac_parentesco_table.removeAllItems();
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM t_parentesco ORDER BY NOMBRE_PAR ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_PAR");
            tac_parentesco_table.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public final void tac_ahorro(){
    tac_ahorro_table.removeAllItems();
    tac_ahorro_table.addItem("SI");
    tac_ahorro_table.addItem("NO");
    
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
        if (chech_char(salario.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!salario.toString().equals("")) {
                if (comprobarFloat(salario.toString())) {
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
public boolean check_barrio(Object barrio, Object mun){
    boolean ret=false;
    if (barrio!=null & mun!=null) {
        if (chech_char(barrio.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.") & chech_char(mun.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
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
public boolean check_info(Object ced){
    boolean ret=false;
    if (ced!=null) {
        if (chech_char(ced.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!ced.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT *\n" +
                                            "FROM\n" +
                                            "    `t_info_sociodemografica` WHERE ID_EMP = "+ced.toString().trim());
                    if(!r.next()){
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
public boolean check_tel_field (Object field){
boolean ret=false;
    if (field!=null) {
        if (!field.toString().trim().equals("")) {
            if (chech_char(field.toString().trim(),"'$%&()=?¡¿/*+[]{};:<>,")) {
                if (!field.toString().equals("")) {
                    int count=0;
                    for (int i = 0; i < field.toString().length(); i++) {
                        if ((int)field.toString().charAt(i)>=48 & (int)field.toString().charAt(i)<=57) {
                            count++;
                        }
                    }
                    if (count>=7) {
                        ret=true;
                    } else {
                        ret=false;
                    }
                }
            } 
        }
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
public void autocomplete_date_pressed(int b){
    Calendar c = new GregorianCalendar();
    String annio = Integer.toString(c.get(Calendar.YEAR));
    switch (((JTextField) info_soci_demo.getEditorComponent()).getText().length()) {
        case 5:
            if (b!=KeyEvent.VK_BACK_SPACE) {
                    ((JTextField) info_soci_demo.getEditorComponent()).setText(((JTextField) info_soci_demo.getEditorComponent()).getText() + "-");
                }else{
                    if (b==KeyEvent.VK_ENTER) {
                    ((JTextField) info_soci_demo.getEditorComponent()).setText(((JTextField) info_soci_demo.getEditorComponent()).getText() + "-"+annio);
                    }
                }
            break;
        case 6:
            if (KeyEvent.VK_ENTER == b) {
                ((JTextField) info_soci_demo.getEditorComponent()).setText(((JTextField) info_soci_demo.getEditorComponent()).getText() + annio);
                int str_año=0;
                int str_mes=0;
                int str_dia=0;
                try {
                    StringTokenizer tokens=new StringTokenizer(((JTextField) info_soci_demo.getEditorComponent()).getText().trim(),"-");
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
                                    ((JTextField) info_soci_demo.getEditorComponent()).setText("");
                                }
                                //System.out.println(ahoraCal.getTime());
                            }else{
                                ((JTextField) info_soci_demo.getEditorComponent()).setText("");
                            }
                        }else{
                            ((JTextField) info_soci_demo.getEditorComponent()).setText("");
                        }
                    }else{
                        ((JTextField) info_soci_demo.getEditorComponent()).setText("");
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                    ((JTextField) info_soci_demo.getEditorComponent()).setText("");
                }
            }   break;
        case 10:
            System.out.println("test");
            if (KeyEvent.VK_ENTER == b) {
                int str_año=0;
                int str_mes=0;
                int str_dia=0;
                try {
                    StringTokenizer tokens=new StringTokenizer(((JTextField) info_soci_demo.getEditorComponent()).getText().trim(),"-");
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
                                    ((JTextField) info_soci_demo.getEditorComponent()).setText("");
                                }
                                //System.out.println(ahoraCal.getTime());
                            }else{
                                ((JTextField) info_soci_demo.getEditorComponent()).setText("");
                            }
                        }else{
                            ((JTextField) info_soci_demo.getEditorComponent()).setText("");
                        }
                    }else{
                        ((JTextField) info_soci_demo.getEditorComponent()).setText("");
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                    ((JTextField) info_soci_demo.getEditorComponent()).setText("");
                }
                
            }   break;
        default:
            break;
    }
}
public void autocomplete_date_released(int b) {
        Calendar c = new GregorianCalendar();
        String annio = Integer.toString(c.get(Calendar.YEAR));
        switch (((JTextField) info_soci_demo.getEditorComponent()).getText().length()) {
            case 2:
                if (b!=KeyEvent.VK_BACK_SPACE) {
                    ((JTextField) info_soci_demo.getEditorComponent()).setText(((JTextField) info_soci_demo.getEditorComponent()).getText() + "-");
                }
                break;
            case 5:
                if (b!=KeyEvent.VK_BACK_SPACE) {
                    ((JTextField) info_soci_demo.getEditorComponent()).setText(((JTextField) info_soci_demo.getEditorComponent()).getText() + "-");
                }else{
                    if (b==KeyEvent.VK_ENTER) {
                        ((JTextField) info_soci_demo.getEditorComponent()).setText(((JTextField) info_soci_demo.getEditorComponent()).getText() + "-"+annio);
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
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM t_vetados WHERE ID_EMPLEADO = "+id_empleado);
        ret = !r.next();
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        ret = false;
        j.printStackTrace();
    }
    return ret;
}
public boolean check_tip_vivienda(Object tip){
    boolean ret=false;
    if (tip!=null) {
        if (chech_char(tip.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!tip.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT *\n" +
                                            "FROM\n" +
                                            "    t_tipo_vivienda\n" +
                                            "WHERE NOMBRE_TIPO_VIVIENDA = '"+tip.toString().trim()+"'");
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
        if (chech_char(area.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
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
        if (chech_char(cargo.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
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
public boolean check_estado_civil(Object est){
    boolean ret=false;
    if (est!=null) {
        if (chech_char(est.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!est.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT *\n" +
                                            "FROM\n" +
                                            "    t_estado_civil\n" +
                                            "WHERE NOMBRE_ESTADO = '"+est.toString().trim()+"'");
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
public boolean check_niv_acad(Object niv){
    boolean ret=false;
    if (niv!=null) {
        if (chech_char(niv.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!niv.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT *\n" +
                                            "FROM\n" +
                                            "    t_nivel_academico\n" +
                                            "WHERE NOMBRE_NIVEL = '"+niv.toString().trim()+"'");
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
public boolean check_nacion(Object nac){
    boolean ret=false;
    if (nac!=null) {
        if (chech_char(nac.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if (!nac.toString().trim().equals("")) {
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try{
                    r = con.s.executeQuery ("SELECT *\n" +
                                            "FROM\n" +
                                            "    t_nacionalidad\n" +
                                            "WHERE NOMBRE_NACION = '"+nac.toString().trim()+"'");
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
public boolean check_prod_fin(Object prod_fin){
    boolean ret=true;
    if (prod_fin!=null) {
        if (chech_char(prod_fin.toString().trim(),"-_'#$%&()=?¡¿/*+[]{};:<>.")) {
            if (!prod_fin.toString().trim().equals("")){
                if (count_char(prod_fin.toString().trim(),',')>0) {
                    StringTokenizer tokens=new StringTokenizer(prod_fin.toString().trim(), ",");
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r;
                    try{
                        while (tokens.hasMoreTokens()) {
                            r = con.s.executeQuery ("SELECT *\n" +
                                                "FROM\n" +
                                                "    t_productos_financieros\n" +
                                                "WHERE NOMBRE_PRODUCTO='"+tokens.nextToken().trim()+"'");
                            if(r.next()){
                                ret=ret&true;
                            }else{
                                ret=ret&false;
                                break;
                            }
                        }
                        con.cerrar();
                    }catch(SQLException j){
                        con.cerrar();
                        j.printStackTrace();
                        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r;
                    try{
                        r = con.s.executeQuery ("SELECT *\n" +
                                                "FROM\n" +
                                                "    t_productos_financieros\n" +
                                                "WHERE NOMBRE_PRODUCTO='"+prod_fin.toString().trim()+"'");
                        if(r.next()){
                            ret=ret&true;
                        }else{
                            ret=ret&false;
                        }
                        con.cerrar();
                    }catch(SQLException j){
                        con.cerrar();
                        j.printStackTrace();
                        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else{
            ret=false;
        }
        }else{
            ret=false;
        }
    }else{
        ret=false;
    }
    return ret;
}
public boolean check_tiempo_libre(Object tiempo_lib){
    boolean ret=true;
    if (tiempo_lib!=null) {
        if (chech_char(tiempo_lib.toString().trim(),"-_'#$%&()=?¡¿/*+[]{};:<>.")) {
            if (!tiempo_lib.toString().trim().equals("")){
                if (count_char(tiempo_lib.toString().trim(),',')>0) {
                    StringTokenizer tokens=new StringTokenizer(tiempo_lib.toString().trim(), ",");
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r;
                    try{
                        while (tokens.hasMoreTokens()) {
                            r = con.s.executeQuery ("SELECT *\n" +
                                                "FROM\n" +
                                                "    t_tiempo_libre\n" +
                                                "WHERE NOMBRE_TIEMPO='"+tokens.nextToken().trim()+"'");
                            if(r.next()){
                                ret=ret&true;
                            }else{
                                ret=ret&false;
                                break;
                            }
                        }
                        con.cerrar();
                    }catch(SQLException j){
                        con.cerrar();
                        j.printStackTrace();
                        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r;
                    try{
                        r = con.s.executeQuery ("SELECT *\n" +
                                                "FROM\n" +
                                                "    t_tiempo_libre\n" +
                                                "WHERE NOMBRE_TIEMPO='"+tiempo_lib.toString().trim()+"'");
                        if(r.next()){
                            ret=ret&true;
                        }else{
                            ret=ret&false;
                        }
                        con.cerrar();
                    }catch(SQLException j){
                        con.cerrar();
                        j.printStackTrace();
                        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else{
                ret=false;
            }
        }else{
            ret=false;
        }
    }else{
        ret=false;
    }
    return ret;
}
public boolean check_serv_public(Object serv_pub){
    boolean ret=true;
    if (serv_pub!=null) {
        if (chech_char(serv_pub.toString().trim(),"-_'#$%&()=?¡¿/*+[]{};:<>.")) {
            if (!serv_pub.toString().trim().equals("")){
                if (count_char(serv_pub.toString().trim(),',')>0) {
                    StringTokenizer tokens=new StringTokenizer(serv_pub.toString().trim(), ",");
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r;
                    try{
                        while (tokens.hasMoreTokens()) {
                            String serv = tokens.nextToken().trim();
                           r = con.s.executeQuery ("SELECT *\n" +
                                                "FROM\n" +
                                                "    t_servicios_publicos\n" +
                                                "WHERE NOMBRE_SERVICIO='"+serv+"'");
                            if(r.next()){
                                ret=ret&true;
                            }else{
                                ret=ret&false;
                                break;
                            }
                        }
                        con.cerrar();
                    }catch(SQLException j){
                        con.cerrar();
                        j.printStackTrace();
                        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r;
                    try{
                        r = con.s.executeQuery ("SELECT *\n" +
                                                "FROM\n" +
                                                "    t_servicios_publicos\n" +
                                                "WHERE NOMBRE_SERVICIO='"+serv_pub.toString().trim()+"'");
                        if(r.next()){
                            ret=ret&true;
                        }else{
                            ret=ret&false;
                        }
                        con.cerrar();
                    }catch(SQLException j){
                        con.cerrar();
                        j.printStackTrace();
                        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else{
                ret=false;
            }
        }else{
            ret=false;
        }
    }else{
        ret=false;
    }
    return ret;
}
public boolean check_integer (Object n){
    boolean ret=false;
    if (n!=null) {
        if (chech_char(n.toString().trim(),"'$#%&()=?¡¿/*+[]{};:<>,")) {
            if (!n.toString().equals("")) {
               try{
                    int num = Integer.parseInt(n.toString().trim());
                    ret=true;
                }catch (Exception e){
                    //e.printStackTrace();
                    ret=false;
                }
            }
        }
    }
    return ret;
}
public boolean check_estrato (Object n){
    boolean ret=false;
    if (n!=null) {
        if (chech_char(n.toString().trim(),"'$#%&()=?¡¿/*+[]{};:<>,")) {
            if (!n.toString().equals("")) {
               try{
                    int num = Integer.parseInt(n.toString().trim());
                    ret = num>0 & num<7;
                }catch (Exception e){
                    //e.printStackTrace();
                    ret=false;
                }
            }
        }
    }
    return ret;
}
public boolean check_field_ahorro (Object ahorro){
boolean ret=false;
    if (ahorro!=null) {
        if (chech_char(ahorro.toString().trim(),"'$%&()=?¡¿/*+[]{};:<>,")) {
            if (!ahorro.toString().equals("")) {
                if (ahorro.toString().toUpperCase().equals("SI") | ahorro.toString().toUpperCase().equals("NO")) {
                    ret=true;
                }
            }
        }
    }

return ret;
}
public boolean check_fte_ahorro(Object fte_ahorro){
    boolean ret=true;
    if (fte_ahorro!=null) {
        if (chech_char(fte_ahorro.toString().trim(),"-_'#$%&()=?¡¿/*+[]{};:<>.")) {
            if (!fte_ahorro.toString().trim().equals("")){
                if (count_char(fte_ahorro.toString().trim(),',')>0) {
                    StringTokenizer tokens=new StringTokenizer(fte_ahorro.toString().trim(), ",");
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r;
                    try{
                        while (tokens.hasMoreTokens()) {
                            r = con.s.executeQuery ("SELECT *\n" +
                                                "FROM\n" +
                                                "    t_fte_ahorro\n" +
                                                "WHERE NOMBRE_FTE_AHORRO='"+tokens.nextToken().trim()+"'");
                            if(r.next()){
                                ret=ret&true;
                            }else{
                                ret=ret&false;
                                break;
                            }
                        }
                        con.cerrar();
                    }catch(SQLException j){
                        con.cerrar();
                        j.printStackTrace();
                        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r;
                    try{
                        r = con.s.executeQuery ("SELECT *\n" +
                                                "FROM\n" +
                                                "    t_fte_ahorro\n" +
                                                "WHERE NOMBRE_FTE_AHORRO='"+fte_ahorro.toString().trim()+"'");
                        if(r.next()){
                            ret=ret&true;
                        }else{
                            ret=ret&false;
                        }
                        con.cerrar();
                    }catch(SQLException j){
                        con.cerrar();
                        j.printStackTrace();
                        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else{
                ret=false;
            }
        }else{
            ret=false;
        }
    }else{
        ret=false;
    }
    return ret;
}
private void set_prod_financieros(Object ced, Object prod_fin){
    StringTokenizer tokens=new StringTokenizer(prod_fin.toString().trim(), ",");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        while (tokens.hasMoreTokens()) {
            String prod = tokens.nextToken().trim();
            r = con.s.executeQuery ("SELECT *\n" +
                                "FROM\n" +
                                "    `t_rel_t_prod_fin`\n" +
                                "    INNER JOIN `t_productos_financieros` \n" +
                                "        ON (`t_rel_t_prod_fin`.`ID_PROD_FIN` = `t_productos_financieros`.`ID_PRODUCTO`) WHERE ID_EMP = "+ced.toString()+" AND NOMBRE_PRODUCTO = '"+prod+"';");
            if (!r.next()) {
                con.s.executeUpdate("INSERT INTO `t_rel_t_prod_fin`(`ID_EMP`, `ID_PROD_FIN`) VALUES ("+ced.toString()+",(SELECT ID_PRODUCTO FROM t_productos_financieros WHERE NOMBRE_PRODUCTO = '"+prod+"'))");
            }
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
private void set_tiemp_lib(Object ced, Object tiemp_lib){
    StringTokenizer tokens=new StringTokenizer(tiemp_lib.toString().trim(), ",");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        while (tokens.hasMoreTokens()) {
            String tiemp = tokens.nextToken().trim();
            r = con.s.executeQuery ("SELECT *\n" +
                                    "FROM\n" +
                                    "    `t_rel_t_tiempo_libre`\n" +
                                    "    INNER JOIN `t_tiempo_libre` \n" +
                                    "        ON (`t_rel_t_tiempo_libre`.`ID_TIEMP_LIBRE` = `t_tiempo_libre`.`ID_TIEMPO`) WHERE ID_EMP = "+ced.toString()+" AND NOMBRE_TIEMPO = '"+tiemp+"';");
            if (!r.next()) {
                con.s.executeUpdate("INSERT INTO `t_rel_t_tiempo_libre`(`ID_EMP`, `ID_TIEMP_LIBRE`) VALUES ("+ced.toString()+",(SELECT ID_TIEMPO FROM t_tiempo_libre WHERE NOMBRE_TIEMPO = '"+tiemp+"'))");
            }
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
private void set_serv_pub(Object ced, Object serv){
    StringTokenizer tokens=new StringTokenizer(serv.toString().trim(), ",");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        while (tokens.hasMoreTokens()) {
            String serv_p = tokens.nextToken().trim();
            r = con.s.executeQuery ("SELECT *\n" +
                                    "FROM\n" +
                                    "    `t_rel_serv_public`\n" +
                                    "    INNER JOIN `t_servicios_publicos` \n" +
                                    "        ON (`t_rel_serv_public`.`ID_SERV_PUB` = `t_servicios_publicos`.`ID_SERVICIO`) WHERE ID_EMP = "+ced.toString()+" AND NOMBRE_SERVICIO = '"+serv_p+"';");
            if (!r.next()) {
                con.s.executeUpdate("INSERT INTO `t_rel_serv_public`(`ID_EMP`, `ID_SERV_PUB`) VALUES ("+ced.toString()+",(SELECT ID_SERVICIO FROM t_servicios_publicos WHERE NOMBRE_SERVICIO = '"+serv_p+"'))");
            }
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
private void set_fte_ahorro(Object ced, Object fte_ahorro){
    StringTokenizer tokens=new StringTokenizer(fte_ahorro.toString().trim(), ",");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        while (tokens.hasMoreTokens()) {
            String fte = tokens.nextToken().trim();
            r = con.s.executeQuery ("SELECT *\n" +
                                    "FROM\n" +
                                    "    `t_rel_fte_ahorro`\n" +
                                    "    INNER JOIN `t_fte_ahorro` \n" +
                                    "        ON (`t_rel_fte_ahorro`.`ID_FTE_AHORRO` = `t_fte_ahorro`.`ID_FTE_AHORRO`) WHERE ID_EMP = "+ced.toString()+" AND NOMBRE_FTE_AHORRO = '"+fte+"';");
            if (!r.next()) {
                con.s.executeUpdate("INSERT INTO `t_rel_fte_ahorro`(`ID_EMP`, `ID_FTE_AHORRO`) VALUES ("+ced.toString()+",(SELECT ID_FTE_AHORRO FROM t_fte_ahorro WHERE NOMBRE_FTE_AHORRO = '"+fte+"'))");
            }
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public boolean verify_data(){
    boolean ret=true;
    modelo = (DefaultTableModel)info_soci_demo.getModel(); 
    for (int i = 0; i < info_soci_demo.getRowCount(); i++) {
        if (check_info(info_soci_demo.getValueAt(i, 0))) {
            if (check_field_dir(info_soci_demo.getValueAt(i, 1))) {//DIR EMPLEADO
                if (check_tel_field(info_soci_demo.getValueAt(i, 2))) {//TEL_CEL EMPLEADO
                        if (check_municipio(info_soci_demo.getValueAt(i, 3))) {
                            if (check_barrio(info_soci_demo.getValueAt(i, 4), info_soci_demo.getValueAt(i, 3))) {
                                if (check_estrato(info_soci_demo.getValueAt(i, 5))) {//ESTRATO
                                    if (check_tip_vivienda(info_soci_demo.getValueAt(i, 6))) {
//                                        if (check_tip_area(info_soci_demo.getValueAt(i, 7))) {
//                                            if (check_cargo(info_soci_demo.getValueAt(i, 8))) {
                                                if (check_estado_civil(info_soci_demo.getValueAt(i, 7))) {
                                                    if (check_integer(info_soci_demo.getValueAt(i, 8))) {//NUMERO HIJOS
                                                        if (check_niv_acad(info_soci_demo.getValueAt(i, 9))) {
//                                                            if (check_nacion(info_soci_demo.getValueAt(i, 12))) {
                                                                if (check_integer(info_soci_demo.getValueAt(i, 10))) {//PERSONAS A CARGO
                                                                    if (check_integer(info_soci_demo.getValueAt(i, 11))) {//PERSONAS EN EL HOGAR
                                                                        if (check_integer(info_soci_demo.getValueAt(i, 12))) {//PERSONAS APORTAN
                                                                            if (check_field(info_soci_demo.getValueAt(i, 13))) {//NOMBRE ACUDIENTE
                                                                                if (check_tel_field(info_soci_demo.getValueAt(i, 14))) {//TEL_CEL ACUDIENTE
                                                                                    if (check_parentesco(info_soci_demo.getValueAt(i, 15))) {
                                                                                        if (check_prod_fin(info_soci_demo.getValueAt(i, 16))) {
                                                                                            if (check_tiempo_libre(info_soci_demo.getValueAt(i, 17))) {
                                                                                                if (check_serv_public(info_soci_demo.getValueAt(i, 18))) {
                                                                                                    if (check_field_ahorro(info_soci_demo.getValueAt(i, 19))) {
                                                                                                        if (check_fte_ahorro(info_soci_demo.getValueAt(i, 20))) {
                                                                                                            ret=true;
                                                                                                        } else {
                                                                                                            info_soci_demo.changeSelection(i,23, false, false);
                                                                                                            info_soci_demo.requestFocus();
                                                                                                            JOptionPane.showMessageDialog(this,"Verifique que las opciones de fuente de ahorro sean correctas","Error",JOptionPane.ERROR_MESSAGE);
                                                                                                            ret=false&ret;
                                                                                                            break;
                                                                                                        }
                                                                                                    } else {
                                                                                                        info_soci_demo.changeSelection(i,22, false, false);
                                                                                                        info_soci_demo.requestFocus();
                                                                                                        JOptionPane.showMessageDialog(this,"Verifique el campo ahorro","Error",JOptionPane.ERROR_MESSAGE);
                                                                                                        ret=false&ret;
                                                                                                        break;
                                                                                                    }
                                                                                                } else {
                                                                                                    info_soci_demo.changeSelection(i,21, false, false);
                                                                                                    info_soci_demo.requestFocus();
                                                                                                    JOptionPane.showMessageDialog(this,"Verifique que las opciones de servicios publicos sean correctos","Error",JOptionPane.ERROR_MESSAGE);
                                                                                                    ret=false&ret;
                                                                                                    break;
                                                                                                }
                                                                                            } else {
                                                                                                info_soci_demo.changeSelection(i,20, false, false);
                                                                                                info_soci_demo.requestFocus();
                                                                                                JOptionPane.showMessageDialog(this,"Verifique que las opciones de tiempo libre sean correctos","Error",JOptionPane.ERROR_MESSAGE);
                                                                                                ret=false&ret;
                                                                                                break;
                                                                                            }
                                                                                        } else {
                                                                                            info_soci_demo.changeSelection(i,19, false, false);
                                                                                            info_soci_demo.requestFocus();
                                                                                            JOptionPane.showMessageDialog(this,"Verifique que las opciones de productos financieros sean correctos","Error",JOptionPane.ERROR_MESSAGE);
                                                                                            ret=false&ret;
                                                                                            break;
                                                                                        }

                                                                                    }else {
                                                                                        info_soci_demo.changeSelection(i,18, false, false);
                                                                                        info_soci_demo.requestFocus();
                                                                                        JOptionPane.showMessageDialog(this,"Verifique que el parentesco sea correcto","Error",JOptionPane.ERROR_MESSAGE);
                                                                                        ret=false&ret;
                                                                                        break;
                                                                                    }
                                                                                }else {
                                                                                    info_soci_demo.changeSelection(i,17, false, false);
                                                                                    info_soci_demo.requestFocus();
                                                                                    JOptionPane.showMessageDialog(this,"Verifique que el telefono o celular del acudiente del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                                                    ret=false&ret;
                                                                                    break;
                                                                                }
                                                                            }else {
                                                                                info_soci_demo.changeSelection(i,16, false, false);
                                                                                info_soci_demo.requestFocus();
                                                                                JOptionPane.showMessageDialog(this,"Verifique que el nombre del acudiente del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                                                ret=false&ret;
                                                                                break;
                                                                            }

                                                                        } else {
                                                                            info_soci_demo.changeSelection(i,15, false, false);
                                                                            info_soci_demo.requestFocus();
                                                                            JOptionPane.showMessageDialog(this,"Verifique que el numero de personas que aportan en el hogar del empleado sea correcto","Error",JOptionPane.ERROR_MESSAGE);
                                                                            ret=false&ret;
                                                                            break;
                                                                        }

                                                                    } else {
                                                                        info_soci_demo.changeSelection(i,14, false, false);
                                                                        info_soci_demo.requestFocus();
                                                                        JOptionPane.showMessageDialog(this,"Verifique que el numero de personas en el hogar del empleado sea correcto","Error",JOptionPane.ERROR_MESSAGE);
                                                                        ret=false&ret;
                                                                        break;
                                                                    }

                                                                } else {
                                                                    info_soci_demo.changeSelection(i,13, false, false);
                                                                    info_soci_demo.requestFocus();
                                                                    JOptionPane.showMessageDialog(this,"Verifique que el numero de personas a cargo del empleado sea correcto","Error",JOptionPane.ERROR_MESSAGE);
                                                                    ret=false&ret;
                                                                    break;
                                                                }

//                                                            } else {
//                                                                info_soci_demo.changeSelection(i,12, false, false);
//                                                                info_soci_demo.requestFocus();
//                                                                JOptionPane.showMessageDialog(this,"Verifique que la nacionalidad del empleado sea correcto","Error",JOptionPane.ERROR_MESSAGE);
//                                                                ret=false&ret;
//                                                                break;
//                                                            }

                                                        } else {
                                                            info_soci_demo.changeSelection(i,11, false, false);
                                                            info_soci_demo.requestFocus();
                                                            JOptionPane.showMessageDialog(this,"Verifique que el nivel academico del empleado sea correcto","Error",JOptionPane.ERROR_MESSAGE);
                                                            ret=false&ret;
                                                            break;
                                                        }

                                                    } else {
                                                        info_soci_demo.changeSelection(i,10, false, false);
                                                        info_soci_demo.requestFocus();
                                                        JOptionPane.showMessageDialog(this,"Verifique que el numero de hijos del empleado sea correcto","Error",JOptionPane.ERROR_MESSAGE);
                                                        ret=false&ret;
                                                        break;
                                                    }

                                                } else {
                                                    info_soci_demo.changeSelection(i,9, false, false);
                                                    info_soci_demo.requestFocus();
                                                    JOptionPane.showMessageDialog(this,"Verifique que el estado civil del empleado sea correcto","Error",JOptionPane.ERROR_MESSAGE);
                                                    ret=false&ret;
                                                    break;
                                                }

//                                            } else {
//                                                info_soci_demo.changeSelection(i,8, false, false);
//                                                info_soci_demo.requestFocus();
//                                                JOptionPane.showMessageDialog(this,"Verifique que el cargo del empleado sea correcto","Error",JOptionPane.ERROR_MESSAGE);
//                                                ret=false&ret;
//                                                break;
//                                            }
//
//                                        } else {
//                                            info_soci_demo.changeSelection(i,7, false, false);
//                                            info_soci_demo.requestFocus();
//                                            JOptionPane.showMessageDialog(this,"Verifique que el area de trabajo del empleado sea correcto","Error",JOptionPane.ERROR_MESSAGE);
//                                            ret=false&ret;
//                                            break;
//                                        }

                                    } else {
                                        info_soci_demo.changeSelection(i,6, false, false);
                                        info_soci_demo.requestFocus();
                                        JOptionPane.showMessageDialog(this,"Verifique que el tipo de vivienda del empleado sea correcto","Error",JOptionPane.ERROR_MESSAGE);
                                        ret=false&ret;
                                        break;
                                    }
                                } else {
                                    info_soci_demo.changeSelection(i,5, false, false);
                                    info_soci_demo.requestFocus();
                                    JOptionPane.showMessageDialog(this,"Verifique que el estrato del empleado sea correcto","Error",JOptionPane.ERROR_MESSAGE);
                                    ret=false&ret;
                                    break;
                                }
                            } else {
                                info_soci_demo.changeSelection(i,4, false, false);
                                info_soci_demo.requestFocus();
                                JOptionPane.showMessageDialog(this,"Verifique que el barrio del empleado sea correcto","Error",JOptionPane.ERROR_MESSAGE);
                                ret=false&ret;
                                break;
                            }

                        } else {
                            info_soci_demo.changeSelection(i,3, false, false);
                            info_soci_demo.requestFocus();
                            JOptionPane.showMessageDialog(this,"Verifique que el municipio del empleado sea correcto","Error",JOptionPane.ERROR_MESSAGE);
                            ret=false&ret;
                            break;
                        }

                } else {
                    info_soci_demo.changeSelection(i,2, false, false);
                    info_soci_demo.requestFocus();
                    JOptionPane.showMessageDialog(this,"Verifique que el telefono del empleado sea correcto","Error",JOptionPane.ERROR_MESSAGE);
                    ret=false&ret;
                    break;
                }
            } else {
                info_soci_demo.changeSelection(i,1, false, false);
                info_soci_demo.requestFocus();
                JOptionPane.showMessageDialog(this,"Verifique que la dirección de residencia del empleado sea correcto","Error",JOptionPane.ERROR_MESSAGE);
                ret=false&ret;
                break;
            }
        } else {
            info_soci_demo.changeSelection(i,0, false, false);
            info_soci_demo.requestFocus();
            JOptionPane.showMessageDialog(this,"El empleado asociado a esta identificacion, ya tiene registrada información sociodemografica","Error",JOptionPane.ERROR_MESSAGE);
            ret=false&ret;
            break;
        }
            
    }
    return ret;
}


}
