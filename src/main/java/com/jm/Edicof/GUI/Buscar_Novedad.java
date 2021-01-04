/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.GUI;

import com.jm.Edicof.Clases.Conexion;
import com.jm.Edicof.Clases.AutoCompletion;
import com.jm.Edicof.Clases.ClipBoardKeyAdapter;
import com.jm.Edicof.Clases.Copiar2Excel;
import com.jm.Edicof.Clases.TableModelBuscNov;
import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class Buscar_Novedad extends javax.swing.JDialog {
    TextAutoCompleter tac_empleador = null;
    TextAutoCompleter tac_eps = null;
    TextAutoCompleter tac_afp = null;
    TextAutoCompleter tac_arl = null;
    TextAutoCompleter tac_ccf = null;
    TextAutoCompleter tac_municipio = null;
    TextAutoCompleter tac_obra = null;
    Object [] fila = new Object[21];
    static Dimension screenSize = null;
    Copiar2Excel c2e = null;
    DefaultTableModel modelo = new TableModelBuscNov(new Object [][] {}, new String [] { "Cedula", 
                                                                                "Nombre empleado", 
                                                                                "NIT empleador", 
                                                                                "Nombre empresa", 
                                                                                "F Ingreso", 
                                                                                "F Retiro", 
                                                                                "Salario", 
                                                                                "Obra", 
                                                                                "EPS", 
                                                                                "AFP", 
                                                                                "ARL", 
                                                                                "CCF", 
                                                                                "Tipo", 
                                                                                "F Registro", 
//                                                                                "F nacimiento", 
//                                                                                "F expedición", 
//                                                                                "Direccion", 
//                                                                                "Barrio", 
//                                                                                "Telefono",
                                                                                "Municipio", 
                                                                                "Observaciones"});
    //////////////////////----------------------------
    int row_cedula=0;
    int row_empleado = 1;
    int row_nit = 2;
    int row_empleador = 3;
    int row_f_ingreso = 4;
    int row_f_retiro = 5;
    int row_salario=6;
    int row_obra=7;
    int row_eps=8;
    int row_afp=9;
    int row_arl=10;
    int row_ccf=11;
    int row_tipo=12;
    int row_f_registro=13;
    int row_mun=14; 
    int row_obs=15;
    /**
     * Creates new form Editar_Novedad
     * 
     */
    public Buscar_Novedad(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        Toolkit t = Toolkit.getDefaultToolkit();
        screenSize = Main.getsize();
        System.out.println(screenSize.height+"x"+screenSize.width);
        this.setSize(screenSize.width, screenSize.height-10);
        this.setLocationRelativeTo(null);
        novedades.addKeyListener(new ClipBoardKeyAdapter(novedades));
        c2e = new Copiar2Excel (novedades);
        c2e.setJTable(novedades);
        init();
        ac_cedula();
        ac_tipo_nov();
        ac_empleador();
        ac_municipio();
        ac_obra();
        ac_eps();
        ac_afp();
        ac_arl();
        ac_ccf();
        tac_empleador.setMode(0);
        tac_municipio.setMode(0);
        tac_obra.setMode(0);
        tac_eps.setMode(0);
        tac_afp.setMode(0);
        tac_arl.setMode(0);
        tac_ccf.setMode(0);
//        InputMap map1 = novedades.getInputMap(novedades.WHEN_FOCUSED);
//        map1.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK), "null");
//        InputMap map2 = novedades.getInputMap(novedades.WHEN_FOCUSED);
//        map2.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
        //ClipBoardKeyAdapter clip = new ClipBoardKeyAdapter(novedades);
        
        DefaultTableCellRenderer tcr = new FormatRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        novedades.getColumnModel().getColumn(0).setCellRenderer(tcr);
        novedades.getColumnModel().getColumn(1).setCellRenderer(tcr);
        novedades.getColumnModel().getColumn(2).setCellRenderer(tcr);
        novedades.getColumnModel().getColumn(3).setCellRenderer(tcr);
        novedades.getColumnModel().getColumn(4).setCellRenderer(tcr);
        novedades.getColumnModel().getColumn(5).setCellRenderer(tcr);
        novedades.getColumnModel().getColumn(6).setCellRenderer(tcr);
        novedades.getColumnModel().getColumn(7).setCellRenderer(tcr);
        novedades.getColumnModel().getColumn(8).setCellRenderer(tcr);
        novedades.getColumnModel().getColumn(9).setCellRenderer(tcr);
        novedades.getColumnModel().getColumn(10).setCellRenderer(tcr);
        novedades.getColumnModel().getColumn(11).setCellRenderer(tcr);
        novedades.getColumnModel().getColumn(12).setCellRenderer(tcr);
        novedades.getColumnModel().getColumn(13).setCellRenderer(tcr);
        novedades.getColumnModel().getColumn(14).setCellRenderer(tcr);
        novedades.getColumnModel().getColumn(15).setCellRenderer(tcr);
//        novedades.getColumnModel().getColumn(16).setCellRenderer(tcr);
//        novedades.getColumnModel().getColumn(17).setCellRenderer(tcr);
//        novedades.getColumnModel().getColumn(18).setCellRenderer(tcr);
//        novedades.getColumnModel().getColumn(19).setCellRenderer(tcr);
        while(novedades.getRowCount()!=0){
            modelo.removeRow(novedades.getRowCount()-1);
        }
        
    }
    public class FormatRenderer extends DefaultTableCellRenderer{

        public FormatRenderer(){
        
        }
        @Override
        public void setValue(Object value)
	{
            try{
                if (value != null ){
                    if (value instanceof java.util.Date) {
                        value = new SimpleDateFormat("dd-MM-yyyy").format(value);
                        if (value.equals("01-01-1900")) {
                            value="";
                        }
                    }
                }
            }catch(IllegalArgumentException e) {
                e.printStackTrace();
            }
            super.setValue(value);
	}
    }
    public void init(){
        tac_empleador = new TextAutoCompleter(t_empleador, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            //(selectedItem.toString());
        }
        });
        tac_municipio = new TextAutoCompleter(tf_municipio, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            //(selectedItem.toString());
        }
        });
        tac_obra = new TextAutoCompleter(tf_obra, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            //(selectedItem.toString());
        }
        });
        tac_eps = new TextAutoCompleter(tf_eps, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            //(selectedItem.toString());
        }
        });
        tac_afp = new TextAutoCompleter(tf_afp, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            //(selectedItem.toString());
        }
        });
        tac_arl = new TextAutoCompleter(tf_arl, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            //(selectedItem.toString());
        }
        });
        tac_ccf = new TextAutoCompleter(tf_ccf, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            //(selectedItem.toString());
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

        pm_novedades = new javax.swing.JPopupMenu();
        edit_novedad = new javax.swing.JMenuItem();
        delete_novedad = new javax.swing.JMenuItem();
        registro_novedad = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        cb_empleados = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        f_inicio = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        f_final = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        tipo_nov = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        t_empleador = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tf_municipio = new javax.swing.JTextField();
        tf_obra = new javax.swing.JTextField();
        tf_eps = new javax.swing.JTextField();
        tf_afp = new javax.swing.JTextField();
        tf_arl = new javax.swing.JTextField();
        tf_ccf = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        novedades = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        n_novedades = new javax.swing.JLabel();
        btn_copy = new javax.swing.JButton();

        edit_novedad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/date_edit.png"))); // NOI18N
        edit_novedad.setText("Editar Novedad");
        edit_novedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_novedadActionPerformed(evt);
            }
        });
        pm_novedades.add(edit_novedad);

        delete_novedad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/date_delete.png"))); // NOI18N
        delete_novedad.setText("Eliminar");
        delete_novedad.setToolTipText("");
        delete_novedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_novedadActionPerformed(evt);
            }
        });
        pm_novedades.add(delete_novedad);

        registro_novedad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/book_open.png"))); // NOI18N
        registro_novedad.setText("Registro");
        registro_novedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registro_novedadActionPerformed(evt);
            }
        });
        pm_novedades.add(registro_novedad);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar Novedades");
        setSize(new java.awt.Dimension(0, 0));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Criterio de busqueda"));

        cb_empleados.setEditable(true);
        AutoCompletion.enable(cb_empleados);

        jLabel1.setText("Cedula");

        jLabel2.setText("Empleador");

        jLabel3.setText("Fecha Inicio");

        jLabel4.setText("Fecha Final");

        jLabel5.setText("Tipo");

        tipo_nov.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipo_novItemStateChanged(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_tab_search.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/filter_delete.png"))); // NOI18N
        jButton2.setText("Limpiar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        t_empleador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_empleador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_empleadorActionPerformed(evt);
            }
        });

        jLabel7.setText("EPS");

        jLabel8.setText("AFP");

        jLabel9.setText("Municipio");

        jLabel10.setText("Obra");

        jLabel11.setText("ARL");

        jLabel12.setText("CCF");

        tf_municipio.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tf_obra.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tf_eps.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tf_afp.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tf_arl.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tf_ccf.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cb_empleados, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(t_empleador, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel7))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tf_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel8)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tf_eps, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_afp, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(428, 428, 428)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tf_arl, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_obra, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(f_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addGap(16, 16, 16)
                                .addComponent(f_final, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(tipo_nov, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(tf_ccf, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cb_empleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1)
                    .addComponent(t_empleador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(tf_eps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel8)
                        .addComponent(tf_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_afp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(tf_obra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_arl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(tf_ccf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel3)
                        .addComponent(f_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(f_final, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tipo_nov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultados de la busqueda"));

        novedades.setAutoCreateRowSorter(true);
        novedades.setModel(modelo);
        novedades.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        novedades.setComponentPopupMenu(pm_novedades);
        novedades.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        novedades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                novedadesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(novedades);
        if (novedades.getColumnModel().getColumnCount() > 0) {
            novedades.getColumnModel().getColumn(0).setPreferredWidth(80);
            novedades.getColumnModel().getColumn(1).setPreferredWidth(200);
            novedades.getColumnModel().getColumn(2).setPreferredWidth(80);
            novedades.getColumnModel().getColumn(3).setPreferredWidth(200);
            novedades.getColumnModel().getColumn(4).setPreferredWidth(80);
            novedades.getColumnModel().getColumn(5).setPreferredWidth(80);
            novedades.getColumnModel().getColumn(6).setPreferredWidth(100);
            novedades.getColumnModel().getColumn(7).setPreferredWidth(160);
            novedades.getColumnModel().getColumn(8).setPreferredWidth(160);
            novedades.getColumnModel().getColumn(9).setPreferredWidth(160);
            novedades.getColumnModel().getColumn(10).setPreferredWidth(160);
            novedades.getColumnModel().getColumn(11).setPreferredWidth(160);
            novedades.getColumnModel().getColumn(12).setPreferredWidth(100);
            novedades.getColumnModel().getColumn(13).setPreferredWidth(80);
            novedades.getColumnModel().getColumn(14).setPreferredWidth(80);
            novedades.getColumnModel().getColumn(15).setPreferredWidth(1000);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancel_1.png"))); // NOI18N
        jButton4.setText("Cerrar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen"));

        jLabel6.setText("Numero de Novedades");

        n_novedades.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        n_novedades.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n_novedades.setText("0");

        btn_copy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/page_copy.png"))); // NOI18N
        btn_copy.setText("Copiar todo");
        btn_copy.setEnabled(false);
        btn_copy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_copyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_copy)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(n_novedades, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(n_novedades)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(btn_copy)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(23, 23, 23)
                .addComponent(jButton4)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        cb_empleados.setSelectedItem("Seleccione..");
        t_empleador.setText("");
        tf_municipio.setText("");
        tf_obra.setText("");
        tf_eps.setText("");
        tf_afp.setText("");
        tf_arl.setText("");
        tf_ccf.setText("");
        tipo_nov.setSelectedItem("Seleccione..");
        f_inicio.setDate(null);
        f_final.setDate(null);
        clear_novedades();
        n_novedades.setText(String.valueOf(novedades.getRowCount()));
        btn_copy.setEnabled(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        clear_novedades();
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
        try {
            r = con.s.executeQuery (query(cb_empleados, t_empleador, tf_municipio, tf_obra, tf_eps, tf_afp, tf_arl, tf_ccf, f_inicio, f_final, tipo_nov));
            while (r.next()) {     
                modelo.addRow(fila);
                modelo.setValueAt(Long.parseLong(r.getString("ID_EMPLEADO")),novedades.getRowCount()-1,row_cedula);
                modelo.setValueAt(r.getString("APELLIDO_1_EMP")+" "+r.getString("APELLIDO_2_EMP")+" "+r.getString("NOMBRE_1_EMP")+" "+r.getString("NOMBRE_2_EMP"),novedades.getRowCount()-1,row_empleado);
                modelo.setValueAt(r.getString("ID_EMPRESA"),novedades.getRowCount()-1,row_nit);
                modelo.setValueAt(r.getString("NOMBRE_EMPRESA"),novedades.getRowCount()-1,row_empleador);
                modelo.setValueAt(new SimpleDateFormat("yyyy-MM-dd").parse(r.getString("FECHA_INGRESO")),novedades.getRowCount()-1,row_f_ingreso);
                modelo.setValueAt(new SimpleDateFormat("yyyy-MM-dd").parse(r.getString("FECHA_RETIRO")),novedades.getRowCount()-1,row_f_retiro);
                modelo.setValueAt(String.format ("%.0f", Float.parseFloat(r.getString("SALARIO_NOVEDAD"))),novedades.getRowCount()-1,row_salario);
                modelo.setValueAt(r.getString("NOMBRE_OBRA"),novedades.getRowCount()-1,row_obra);
                modelo.setValueAt(r.getString("NOMBRE_EPS"),novedades.getRowCount()-1,row_eps);
                modelo.setValueAt(r.getString("NOMBRE_AFP"),novedades.getRowCount()-1,row_afp);
                modelo.setValueAt(r.getString("ARL_NOV"),novedades.getRowCount()-1,row_arl);
                modelo.setValueAt(r.getString("CCF_NOV"),novedades.getRowCount()-1,row_ccf);
                modelo.setValueAt(r.getString("NOMBRE_TIPO"),novedades.getRowCount()-1,row_tipo);
                modelo.setValueAt(new SimpleDateFormat("yyyy-MM-dd").parse(r.getString("F_REGISTRO")),novedades.getRowCount()-1,row_f_registro);//F_EXP_NOV
//                modelo.setValueAt(new SimpleDateFormat("yyyy-MM-dd").parse(r.getString("F_NAC_NOV")),novedades.getRowCount()-1,14);
//                modelo.setValueAt(new SimpleDateFormat("yyyy-MM-dd").parse(r.getString("F_EXP_NOV")),novedades.getRowCount()-1,15);
//                modelo.setValueAt(r.getString("DIR_EMP_NOV"),novedades.getRowCount()-1,16);
//                modelo.setValueAt(r.getString("BARRIO_NOV"),novedades.getRowCount()-1,17);
//                modelo.setValueAt(r.getString("TEL_NOV"),novedades.getRowCount()-1,18);
                modelo.setValueAt(r.getString("NOMBRE_MUN")+"-"+r.getString("NOMBRE_DEP"),novedades.getRowCount()-1,row_mun);
                modelo.setValueAt(r.getString("OBS_NOV"),novedades.getRowCount()-1,row_obs);
            }
            n_novedades.setText(String.valueOf(novedades.getRowCount()));
            if (novedades.getRowCount()>0) {
                btn_copy.setEnabled(true);
            }else{
                btn_copy.setEnabled(false);
            }
            con.cerrar();
        }catch (SQLException | ParseException e) {
            con.cerrar();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        } 
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void edit_novedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_novedadActionPerformed
        // TODO add your handling code here:
        if (novedades.getSelectedRow()!=-1) {
            if (Main.rol.getText().equals("ADMINISTRADOR") | Main.rol.getText().equals("MASTER")) {
                String aux = novedades.getValueAt(novedades.getSelectedRow(),row_f_retiro).toString();
                if (aux.equals("")) {
                    aux = "01-01-1900";
                }
                Edd_Novedad ed_nov = new Edd_Novedad(this, true,
                        novedades.getValueAt(novedades.getSelectedRow(),row_cedula).toString(),
                        novedades.getValueAt(novedades.getSelectedRow(),row_nit).toString(),
                        novedades.getValueAt(novedades.getSelectedRow(),row_f_ingreso),
                        novedades.getValueAt(novedades.getSelectedRow(),row_f_retiro),
                        novedades.getValueAt(novedades.getSelectedRow(),row_tipo).toString(),
                        true);
                ed_nov.setVisible(true);
                cb_empleados.setSelectedItem("Seleccione..");
                tipo_nov.setSelectedItem("Seleccione..");
                f_inicio.setDate(null);
                f_final.setDate(null);
                n_novedades.setText("0");
                clear_novedades();
            }else{
                JOptionPane.showMessageDialog(null,"Solo tiene permisos para modificar las observaciones de la novedad seleccionada.","Información",JOptionPane.INFORMATION_MESSAGE);
                String aux = novedades.getValueAt(novedades.getSelectedRow(),row_f_retiro).toString();
                if (aux.equals("")) {
                    aux = "01-01-1900";
                }
                Edd_Novedad ed_nov = new Edd_Novedad(this,true,
                        novedades.getValueAt(novedades.getSelectedRow(),row_cedula).toString(),
                        novedades.getValueAt(novedades.getSelectedRow(),row_nit).toString(),
                        novedades.getValueAt(novedades.getSelectedRow(),row_f_ingreso),
                        novedades.getValueAt(novedades.getSelectedRow(),row_f_retiro),
                        novedades.getValueAt(novedades.getSelectedRow(),row_tipo).toString(),
                        false);
                ed_nov.setVisible(true);
                cb_empleados.setSelectedItem("Seleccione..");
                tipo_nov.setSelectedItem("Seleccione..");
                f_inicio.setDate(null);
                f_final.setDate(null);
                n_novedades.setText("0");
                clear_novedades();
            }
        }else{
            JOptionPane.showMessageDialog(null,"Debe seleccionar un registro.","Error",JOptionPane.ERROR_MESSAGE);
        }
        
        
        
        
    }//GEN-LAST:event_edit_novedadActionPerformed

    private void tipo_novItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipo_novItemStateChanged
        // TODO add your handling code here:
        //modelo = (DefaultTableModel)novedades.getModel(); 
        while(novedades.getRowCount()!=0){
            modelo.removeRow(novedades.getRowCount()-1);
        }
        n_novedades.setText(String.valueOf(novedades.getRowCount()));
        //novedades.setModel(modelo);

    }//GEN-LAST:event_tipo_novItemStateChanged

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2){
            JOptionPane.showMessageDialog(this,"La AFP que intenta ingresar ya existe","Error",JOptionPane.ERROR_MESSAGE);
        }
            
    }//GEN-LAST:event_formMouseClicked

    private void t_empleadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_empleadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_empleadorActionPerformed

    private void delete_novedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_novedadActionPerformed
        // TODO add your handling code here:
        String aux ="";
        
        if (novedades.getSelectedRow()!=-1) {
            if (Main.rol.getText().equals("ADMINISTRADOR") | Main.rol.getText().equals("MASTER")) {
                int conf = JOptionPane.showConfirmDialog(this,"Esta seguro que desea eliminar la Novedad?\n Cedula: "+novedades.getValueAt(novedades.getSelectedRow(),row_cedula).toString()+"\nEmpresa: "+novedades.getValueAt(novedades.getSelectedRow(),row_empleador).toString()+"\nFecha de Ingreso: "+new SimpleDateFormat("dd-MM-yyyy").format(novedades.getValueAt(novedades.getSelectedRow(),row_f_ingreso))+"\nFecha de Retiro: "+new SimpleDateFormat("dd-MM-yyyy").format(novedades.getValueAt(novedades.getSelectedRow(),row_f_retiro)).replace("01-01-1900","")+"\nTipo de Novedad: "+novedades.getValueAt(novedades.getSelectedRow(),row_tipo).toString(),"Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (conf == JOptionPane.YES_OPTION) {
                    Conexion con = new Conexion();
                    con.conexion();
                    ResultSet r;
                    try {
                        r = con.s.executeQuery ("SELECT * FROM t_tipo_novedad WHERE NOMBRE_TIPO ='"+novedades.getValueAt(novedades.getSelectedRow(),row_tipo).toString()+"'");
                        if (r.next()) {
                           aux=r.getString("ID_TIPO");//con.s.executeUpdate
                           con.s.executeUpdate("DELETE FROM T_NOVEDADES "
                                   + "WHERE ID_EMPLEADO="+modelo.getValueAt(novedades.getSelectedRow(),row_cedula).toString()+" "
                                   + "AND ID_EMPRESA='"+modelo.getValueAt(novedades.getSelectedRow(),row_nit).toString()+"' "
                                   + "AND FECHA_INGRESO='"+new SimpleDateFormat("yyyy-MM-dd").format(novedades.getValueAt(novedades.getSelectedRow(),row_f_ingreso))+"' "
                                   + "AND FECHA_RETIRO='"+new SimpleDateFormat("yyyy-MM-dd").format(novedades.getValueAt(novedades.getSelectedRow(),row_f_retiro))+"' "
                                   + "AND ID_TIPO="+aux);
                           JOptionPane.showMessageDialog(this,"La Novedad ha sido eliminada","Confirmación",JOptionPane.INFORMATION_MESSAGE);
                           clear_novedades();
                        }
                        con.cerrar();
                    } catch (SQLException ex) {
                        con.cerrar();
                        JOptionPane.showMessageDialog(this, ex, "Error",JOptionPane.ERROR_MESSAGE );
                        ex.printStackTrace();
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null,"No tiene permisos para esta opcion.","Error",JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Debe seleccionar un registro.","Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_delete_novedadActionPerformed

    private void novedadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_novedadesMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2 ){
            JOptionPane.showMessageDialog(null, novedades.getValueAt(novedades.getSelectedRow(),0).toString());
        }
    }//GEN-LAST:event_novedadesMouseClicked

    private void registro_novedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registro_novedadActionPerformed
        // TODO add your handling code here:
        if (novedades.getSelectedRow()!=-1) {
            if (Main.rol.getText().equals("ADMINISTRADOR") | Main.rol.getText().equals("MASTER")) {
                String aux = novedades.getValueAt(novedades.getSelectedRow(),row_f_retiro).toString();
                if (aux.equals("")) {
                    aux = "01-01-1900";
                }
                //System.out.println(novedades.getValueAt(novedades.getSelectedRow(),0).toString()+"\n"+novedades.getValueAt(novedades.getSelectedRow(),2).toString()+"\n"+novedades.getValueAt(novedades.getSelectedRow(),4)+"\n"+novedades.getValueAt(novedades.getSelectedRow(),5)+"\n"+novedades.getValueAt(novedades.getSelectedRow(),12).toString());
                View_Registro reg_nov = new View_Registro(this,true,
                        novedades.getValueAt(novedades.getSelectedRow(),row_cedula).toString(),
                        novedades.getValueAt(novedades.getSelectedRow(),row_nit).toString(),
                        novedades.getValueAt(novedades.getSelectedRow(),row_f_ingreso),
                        novedades.getValueAt(novedades.getSelectedRow(),row_f_retiro),
                        novedades.getValueAt(novedades.getSelectedRow(),row_tipo).toString());
                reg_nov.setLocationRelativeTo(this);
                reg_nov.setVisible(true);
            }
        }
    }//GEN-LAST:event_registro_novedadActionPerformed

    private void btn_copyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_copyActionPerformed
        // TODO add your handling code here:
        if (novedades.getRowCount()>0) {
            novedades.setRowSelectionInterval(0, novedades.getRowCount() - 1);
            novedades.setColumnSelectionInterval(0, novedades.getColumnCount() - 1);
            
            c2e.setBtn(true);
            c2e.copyaction();
        }

    }//GEN-LAST:event_btn_copyActionPerformed

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
            java.util.logging.Logger.getLogger(Buscar_Novedad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Buscar_Novedad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Buscar_Novedad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Buscar_Novedad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                Buscar_Novedad dialog = new Buscar_Novedad(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn_copy;
    private javax.swing.JComboBox<String> cb_empleados;
    private javax.swing.JMenuItem delete_novedad;
    private javax.swing.JMenuItem edit_novedad;
    private com.toedter.calendar.JDateChooser f_final;
    private com.toedter.calendar.JDateChooser f_inicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel n_novedades;
    private javax.swing.JTable novedades;
    private javax.swing.JPopupMenu pm_novedades;
    private javax.swing.JMenuItem registro_novedad;
    private javax.swing.JTextField t_empleador;
    private javax.swing.JTextField tf_afp;
    private javax.swing.JTextField tf_arl;
    private javax.swing.JTextField tf_ccf;
    private javax.swing.JTextField tf_eps;
    private javax.swing.JTextField tf_municipio;
    private javax.swing.JTextField tf_obra;
    private javax.swing.JComboBox<String> tipo_nov;
    // End of variables declaration//GEN-END:variables
public void ac_cedula(){
    cb_empleados.removeAllItems();
    cb_empleados.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_EMPLEADOS ORDER BY ID_EMP ASC;");
        while(r.next()){
            String str=r.getString("ID_EMP");
            cb_empleados.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public void ac_empleador(){
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
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public void ac_eps(){
    tac_eps.removeAllItems();
    tac_eps.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_EPS ORDER BY NOMBRE_EPS ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_EPS");
            tac_eps.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public void ac_afp(){
    tac_afp.removeAllItems();
    tac_afp.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_AFP ORDER BY NOMBRE_AFP ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_AFP");
            tac_afp.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public void ac_arl(){
    tac_arl.removeAllItems();
    tac_arl.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_ARL ORDER BY NOMBRE_ARL ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_ARL");
            tac_arl.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public void ac_ccf(){
    tac_ccf.removeAllItems();
    tac_ccf.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_CCF ORDER BY NOMBRE_CCF ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_CCF");
            tac_ccf.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public void ac_municipio(){
    tac_municipio.removeAllItems();
    tac_municipio.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM t_municipios\n" +
                                "    INNER JOIN t_departamentos \n" +
                                "        ON (t_municipios.ID_DEP = t_departamentos.ID_DEP) ORDER BY NOMBRE_MUN ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_MUN")+"-"+r.getString("NOMBRE_DEP");
            tac_municipio.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public void ac_obra(){
    tac_obra.removeAllItems();
    tac_obra.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_OBRA ORDER BY NOMBRE_OBRA ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_OBRA");
            tac_obra.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public void ac_tipo_nov(){
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
        try{
            r = con.s.executeQuery ("SELECT * FROM `t_tipo_novedad` ORDER BY ID_TIPO ASC");
            tipo_nov.removeAllItems();
            tipo_nov.addItem("Seleccione..");
            while(r.next()){
                tipo_nov.addItem(r.getString("NOMBRE_TIPO"));
            }
            con.cerrar();
            
        }catch(SQLException j){
            con.cerrar();
            JOptionPane.showMessageDialog(null,j,"Error!",JOptionPane.ERROR_MESSAGE);
        }
}
public void clear_novedades(){
    //modelo = (DefaultTableModel)novedades.getModel(); 
    while(novedades.getRowCount()!=0){
        modelo.removeRow(novedades.getRowCount()-1);
    }
    //novedades.setModel(modelo);
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
public void load_data(String afp){
    if (!afp.equals("Seleccione..")) {
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
        try {
            r = con.s.executeQuery ("SELECT * FROM T_EMPRESAS WHERE NOMBRE_EMPRESA = '"+afp+"'");
            if (r.next()) {
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
public String query (javax.swing.JComboBox cedula, javax.swing.JTextField empleador, javax.swing.JTextField municipio, javax.swing.JTextField obra, javax.swing.JTextField eps, javax.swing.JTextField afp, javax.swing.JTextField arl, javax.swing.JTextField ccf, com.toedter.calendar.JDateChooser f_ini, com.toedter.calendar.JDateChooser f_fin, javax.swing.JComboBox tipo ){
    String ret = "";
    String str_cedula = "";
    String str_empleador = "";
    String str_municipio= "";
    String str_obra= "";
    String str_eps= "";
    String str_afp= "";
    String str_arl= "";
    String str_ccf= "";
    String str_tipo="";
    String str_date= "";
    String str_query = "SELECT\n" +
                        "  *\n" +
                        "  FROM t_novedades\n" +
                        "  INNER JOIN t_empleados\n" +
                        "    ON (t_novedades.ID_EMPLEADO = t_empleados.ID_EMP)\n" +
                        "  INNER JOIN t_empresas\n" +
                        "    ON (t_novedades.ID_EMPRESA = t_empresas.ID_EMPRESA)\n" +
                        "  INNER JOIN t_eps\n" +
                        "    ON (t_novedades.ID_EPS = t_eps.ID_EPS)\n" +
                        "  INNER JOIN t_afp\n" +
                        "    ON (t_novedades.ID_AFP = t_afp.ID_AFP)\n" +
                        "  INNER JOIN t_obra\n" +
                        "    ON (t_novedades.ID_OBRA = t_obra.ID_OBRA)\n" +
                        "  INNER JOIN t_tipo_novedad\n" +
                        "    ON (t_novedades.ID_TIPO = t_tipo_novedad.ID_TIPO)\n" +
                        "  INNER JOIN t_municipios\n" +
                        "    ON (t_obra.ID_MUN_OBRA = t_municipios.ID_MUN)\n" +
                        "  INNER JOIN t_departamentos\n" +
                        "    ON (t_municipios.ID_DEP = t_departamentos.ID_DEP)\n";
    if (!cedula.getSelectedItem().equals("Seleccione..") ) {
        str_cedula = "t_novedades.ID_EMPLEADO ="+cedula.getSelectedItem();
    }else{
        str_cedula = " ";
    }
    if (!empleador.getText().equals("") & !empleador.getText().equals(" ")) {
        str_empleador = "t_empresas.NOMBRE_EMPRESA='"+empleador.getText()+"'";
    }else{
        str_empleador =" ";
    }
    if (!municipio.getText().equals("") & !municipio.getText().equals(" ")) {
        String aux = get_id_municipio(municipio.getText());
        str_municipio = "t_municipios.ID_MUN="+aux;
    }else{
        str_municipio =" ";
    }
    if (!obra.getText().equals("") & !obra.getText().equals(" ")) {
        str_obra = "t_obra.NOMBRE_OBRA='"+obra.getText()+"'";
    }else{
        str_obra =" ";
    }
    if (!eps.getText().equals("") & !eps.getText().equals(" ")) {
        str_eps = "t_eps.NOMBRE_EPS='"+eps.getText()+"'";
    }else{
        str_eps =" ";
    }
    if (!afp.getText().equals("") & !afp.getText().equals(" ")) {
        str_afp = "t_afp.NOMBRE_AFP='"+afp.getText()+"'";
    }else{
        str_afp =" ";
    }
    if (!arl.getText().equals("") & !arl.getText().equals(" ")) {
        str_arl = "t_novedades.ARL_NOV='"+arl.getText()+"'";
    }else{
        str_arl =" ";
    }
    if (!ccf.getText().equals("") & !ccf.getText().equals(" ")) {
        str_ccf = "t_novedades.CCF_NOV='"+ccf.getText()+"'";
    }else{
        str_ccf =" ";
    }
    if (!tipo.getSelectedItem().equals("Seleccione..")) {
        str_tipo = "t_tipo_novedad.NOMBRE_TIPO='"+tipo.getSelectedItem()+"'";
    }else{
        str_tipo =" ";
    }
    if (f_inicio.getDate()==null & f_final.getDate()==null) {
        str_date = " ";
    }else{
        if (f_inicio.getDate()!=null & f_final.getDate()==null) {
            JOptionPane.showMessageDialog(this,"Ingrese la fecha final.","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            if (f_inicio.getDate()==null & f_final.getDate()!=null) {
                JOptionPane.showMessageDialog(this,"Ingrese la fecha inicial.","Error",JOptionPane.ERROR_MESSAGE);
            }else{
                if (f_inicio.getDate()!=null & f_final.getDate()!=null) {
                    if (tipo.getSelectedItem().equals("INGRESO")) {
                        str_date = " t_novedades.FECHA_INGRESO BETWEEN '"+new SimpleDateFormat("yyyy-MM-dd").format(f_ini.getDate())+"' AND '"+new SimpleDateFormat("yyyy-MM-dd").format(f_fin.getDate())+"' ORDER BY FECHA_INGRESO DESC";
                    }else{
                        if (tipo.getSelectedItem().equals("RETIRO")) {
                            str_date = " t_novedades.FECHA_RETIRO BETWEEN '"+new SimpleDateFormat("yyyy-MM-dd").format(f_ini.getDate())+"' AND '"+new SimpleDateFormat("yyyy-MM-dd").format(f_fin.getDate())+"' ORDER BY FECHA_RETIRO DESC";
                        }else{
                            if (tipo.getSelectedItem().equals("PRE INGRESO")) {
                                str_date = " t_novedades.FECHA_INGRESO BETWEEN '"+new SimpleDateFormat("yyyy-MM-dd").format(f_ini.getDate())+"' AND '"+new SimpleDateFormat("yyyy-MM-dd").format(f_fin.getDate())+"' ORDER BY FECHA_INGRESO DESC";
                            }else{
                                if (tipo.getSelectedItem().equals("Seleccione..")) {
                                    str_date = " t_novedades.F_REGISTRO BETWEEN '"+new SimpleDateFormat("yyyy-MM-dd").format(f_ini.getDate())+"' AND '"+new SimpleDateFormat("yyyy-MM-dd").format(f_fin.getDate())+"' ORDER BY F_REGISTRO DESC";
                                }
                            }
                        }
                    }
                }
            }
        }
    } 
//    if (!str_cedula.equals(" ") | !str_empleador.equals(" ") | !str_municipio.equals(" ") | !str_obra.equals(" ") | !str_eps.equals(" ") | !str_afp.equals(" ") | !str_arl.equals(" ") | !str_ccf.equals(" ") | !str_tipo.equals(" ") | !str_date.equals(" ")) {
    if(!str_cedula.equals(" ") & !str_empleador.equals(" ")){
        str_empleador = " AND "+str_empleador;
    }
    if ((!str_cedula.equals(" ") | !str_empleador.equals(" "))& !str_municipio.equals(" ")) {
        str_municipio = " AND "+str_municipio;
    }
    if ((!str_cedula.equals(" ") | !str_empleador.equals(" ") | !str_municipio.equals(" "))& !str_obra.equals(" ")) {
        str_obra = " AND "+str_obra;
    }
    if ((!str_cedula.equals(" ") | !str_empleador.equals(" ") | !str_municipio.equals(" ") | !str_obra.equals(" "))& !str_eps.equals(" ")) {
        str_eps = " AND "+str_eps;
    }
    if ((!str_cedula.equals(" ") | !str_empleador.equals(" ") | !str_municipio.equals(" ") | !str_obra.equals(" ") | !str_eps.equals(" "))& !str_afp.equals(" ")) {
        str_afp = " AND "+str_afp;
    }
    if ((!str_cedula.equals(" ") | !str_empleador.equals(" ") | !str_municipio.equals(" ") | !str_obra.equals(" ") | !str_eps.equals(" ") | !str_afp.equals(" "))& !str_arl.equals(" ")) {
        str_arl = " AND "+str_arl;
    }
    if ((!str_cedula.equals(" ") | !str_empleador.equals(" ") | !str_municipio.equals(" ") | !str_obra.equals(" ") | !str_eps.equals(" ") | !str_afp.equals(" ") | !str_arl.equals(" "))& !str_ccf.equals(" ")) {
        str_ccf = " AND "+str_ccf;
    }
    if ((!str_cedula.equals(" ") | !str_empleador.equals(" ") | !str_municipio.equals(" ") | !str_obra.equals(" ") | !str_eps.equals(" ") | !str_afp.equals(" ") | !str_arl.equals(" ") | !str_ccf.equals(" "))& !str_tipo.equals(" ")) {
        str_tipo = " AND "+str_tipo;
    }
    if ((!str_cedula.equals(" ") | !str_empleador.equals(" ") | !str_municipio.equals(" ") | !str_obra.equals(" ") | !str_eps.equals(" ") | !str_afp.equals(" ") | !str_arl.equals(" ") | !str_ccf.equals(" ") | !str_tipo.equals(" "))& !str_date.equals(" ")) {
        str_date = " AND "+str_date;
    }
    if (!str_cedula.equals(" ") | !str_empleador.equals(" ") | !str_municipio.equals(" ") | !str_obra.equals(" ") | !str_eps.equals(" ") | !str_afp.equals(" ") | !str_arl.equals(" ") | !str_ccf.equals(" ") | !str_tipo.equals(" ") | !str_date.equals(" ")) {
        str_query = str_query + " WHERE " + str_cedula + str_empleador + str_municipio + str_obra + str_eps + str_afp + str_arl + str_ccf + str_tipo + str_date;
    }else{
        str_query = str_query + " ORDER BY F_REGISTRO DESC";
    }
        
        
    
return str_query;
}
}
