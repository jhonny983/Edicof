/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.GUI;

import com.jm.Edicof.Clases.Conexion;
import com.jm.Edicof.Clases.AutoCompletion;
import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import javax.swing.InputMap;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author ADMIN
 */
public class Edd_Novedad extends javax.swing.JDialog {
static Dimension screenSize = null;
TextAutoCompleter ac_empleados = null;
TextAutoCompleter ac_empleador = null;
int id_tip_edit =0;
String tip_edit="";
Date f_ing_edit=null;
Date f_ret_edit=null;
String nit_edit="";
String ced_edit="";
float salario_edit=0;
String afp_edit="";
String arl_edit="";
String eps_edit="";
String ccf_edit="";
String obra_edit="";
String obs_edit="";

Boolean block=true;
    /**
     * Creates new form Agregar_Novedad
     */
    public Edd_Novedad(javax.swing.JDialog parent, boolean modal, String empl, String emp, Object f_i, Object f_r, String t, boolean en) {
        super(parent, modal);
        initComponents();
        //////////////////////////////------------------------------------------------------------
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
//        screenSize = Main.getsize();
//        this.setSize(screenSize.width, screenSize.height-10);
        this.setLocationRelativeTo(null);
        //////////////////////////////------------------------------------------------------------
        this.ced_edit=empl;
        this.nit_edit=emp;
        this.f_ing_edit=(Date) f_i;
        this.f_ret_edit=(Date)f_r;
        this.tip_edit=t;
        if (tip_edit.equals("INGRESO")) {
            f_ing_nov.setEnabled(true);
            f_ret_nov.setEnabled(false);
        } else if(tip_edit.equals("RETIRO")) {
            f_ing_nov.setEnabled(false);
            f_ret_nov.setEnabled(true);
        } else if (tip_edit.equals("PRE INGRESO")) {
            f_ing_nov.setEnabled(true);
            f_ret_nov.setEnabled(false);
        } else if (tip_edit.equals("BLOQUEADO")) {
            f_ing_nov.setEnabled(false);
            f_ret_nov.setEnabled(false);
        }
        InputMap map1 = Busc_nit.getInputMap(Busc_nit.WHEN_FOCUSED);
        map1.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
        InputMap map2 = Busc_cedula.getInputMap(Busc_cedula.WHEN_FOCUSED);
        map2.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
        update_cb_tipo_nov();
        ac_eps();
        ac_arl();
        ac_afp();
        ac_ccf();
//        ac_mun();
//        ac_par();
        ac_obra();
        ac_area();
        ac_cargo();
        sel_campos(ced_edit,nit_edit,f_ing_edit,f_ret_edit,tip_edit);
        if (!en) {
            tipo_nov.setEnabled(false);
            f_ing_nov.setEnabled(false);
            f_ret_nov.setEnabled(false);
            
            Busc_nit.setEnabled(false);
            Busc_cedula.setEnabled(false);
            
            salario.setEnabled(false);
            cb_afp.setEnabled(false);
            cb_arl.setEnabled(false);
            cb_eps.setEnabled(false);
            cb_ccf.setEnabled(false);
            cb_obra.setEnabled(false);
//            f_nac.setEnabled(false);
//            f_exp.setEnabled(false);
//            dir_emp.setEnabled(false);
//            tel_emp.setEnabled(false);
//            barrio.setEnabled(false);
//            cb_mun.setEnabled(false);
            mail_emp.setEnabled(false);
//            acud.setEnabled(false);
//            cb_par.setEnabled(false);
//            tel_acud.setEnabled(false);
            obs.setEnabled(true);
        }
        init();
        ac_nit();
        ac_cedula();
        ac_empleados.setMode(0);
        ac_empleador.setMode(0);
        
    }

    public void init(){
        ac_empleados = new TextAutoCompleter(Busc_cedula, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            callback_empleado(selectedItem.toString());
        }
        });
        ac_empleador = new TextAutoCompleter(Busc_nit, new AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
            Conexion con = new Conexion();
            con.conexion();
            ResultSet r;
            try{
                r = con.s.executeQuery ("SELECT * FROM T_EMPRESAS WHERE ID_EMPRESA='"+selectedItem+"';");
                if(r.next()){
                    nom_empl.setText(r.getString("NOMBRE_EMPRESA"));
                }
                con.cerrar();
            }catch(SQLException j){
                con.cerrar();
                JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        f_ing_nov = new com.toedter.calendar.JDateChooser();
        jLabel23 = new javax.swing.JLabel();
        tipo_nov = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        f_ret_nov = new com.toedter.calendar.JDateChooser();
        jPanel6 = new javax.swing.JPanel();
        edit_empresa = new javax.swing.JButton();
        nom_empl = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Busc_nit = new javax.swing.JTextField();
        add_empresa = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nomb_emp = new javax.swing.JTextField();
        ap_emp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        edit_emp = new javax.swing.JButton();
        add_emp = new javax.swing.JButton();
        Busc_cedula = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        salario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        mail_emp = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        add_eps = new javax.swing.JButton();
        add_ccf = new javax.swing.JButton();
        add_afp = new javax.swing.JButton();
        add_arl = new javax.swing.JButton();
        cb_arl = new javax.swing.JComboBox<>();
        cb_eps = new javax.swing.JComboBox<>();
        cb_afp = new javax.swing.JComboBox<>();
        cb_ccf = new javax.swing.JComboBox<>();
        cb_obra = new javax.swing.JComboBox<>();
        add_barrio1 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        cb_area = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        cb_cargo = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        obs = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        f_ex_ing = new com.toedter.calendar.JDateChooser();
        jLabel28 = new javax.swing.JLabel();
        f_cons = new com.toedter.calendar.JDateChooser();
        jLabel29 = new javax.swing.JLabel();
        cb_exon = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Novedad");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Novedad"));

        jLabel22.setText("Fecha de ingreso");

        jLabel23.setText("Tipo");

        tipo_nov.setEditable(true);
        AutoCompletion.enable(tipo_nov);
        tipo_nov.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipo_novItemStateChanged(evt);
            }
        });

        jLabel15.setText("Fecha de retiro");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addGap(38, 38, 38)
                .addComponent(tipo_nov, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addComponent(f_ing_nov, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jLabel15)
                .addGap(46, 46, 46)
                .addComponent(f_ret_nov, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel23)
                    .addComponent(tipo_nov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(f_ing_nov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(f_ret_nov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Empleador"));

        edit_empresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/building_edit.png"))); // NOI18N
        edit_empresa.setToolTipText("Editar Empleador");
        edit_empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_empresaActionPerformed(evt);
            }
        });

        nom_empl.setEditable(false);
        nom_empl.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setText("Nombre");

        jLabel5.setText("NIT");

        Busc_nit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Busc_nitKeyTyped(evt);
            }
        });

        add_empresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/building_add.png"))); // NOI18N
        add_empresa.setToolTipText("Ingresar Empleador");
        add_empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_empresaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Busc_nit, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(add_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(nom_empl, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edit_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(nom_empl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edit_empresa)
                    .addComponent(jLabel5)
                    .addComponent(Busc_nit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_empresa)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Empleado"));

        jLabel2.setText("Nombres");

        jLabel3.setText("Apellidos");

        nomb_emp.setEditable(false);
        nomb_emp.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        ap_emp.setEditable(false);
        ap_emp.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setText("Cedula");

        edit_emp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_edit.png"))); // NOI18N
        edit_emp.setToolTipText("Editar Empleado");
        edit_emp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_empActionPerformed(evt);
            }
        });

        add_emp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_add.png"))); // NOI18N
        add_emp.setToolTipText("Ingresar Empleado");
        add_emp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_empActionPerformed(evt);
            }
        });

        Busc_cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Busc_cedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Busc_cedulaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Busc_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(add_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomb_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edit_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ap_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(Busc_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_emp)
                    .addComponent(jLabel2)
                    .addComponent(nomb_emp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edit_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(ap_emp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos adicionales"));

        jLabel1.setText("Salario");

        salario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        salario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                salarioFocusLost(evt);
            }
        });
        salario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salarioMouseClicked(evt);
            }
        });
        salario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                salarioKeyTyped(evt);
            }
        });

        jLabel6.setText("EPS");

        jLabel8.setText("AFP");

        jLabel9.setText("ARL");

        jLabel17.setText("Correo");

        mail_emp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mail_emp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                mail_empFocusLost(evt);
            }
        });
        mail_emp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mail_empMouseClicked(evt);
            }
        });

        jLabel21.setText("CCF");

        add_eps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/health.png"))); // NOI18N
        add_eps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_epsActionPerformed(evt);
            }
        });

        add_ccf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/heart_add.png"))); // NOI18N
        add_ccf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_ccfActionPerformed(evt);
            }
        });

        add_afp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/medal_gold_add.png"))); // NOI18N
        add_afp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_afpActionPerformed(evt);
            }
        });

        add_arl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/link_add.png"))); // NOI18N
        add_arl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_arlActionPerformed(evt);
            }
        });

        AutoCompletion.enable(cb_arl);
        cb_arl.setEditable(true);

        AutoCompletion.enable(cb_eps);
        cb_eps.setEditable(true);

        AutoCompletion.enable(cb_afp);
        cb_afp.setEditable(true);

        AutoCompletion.enable(cb_ccf);
        cb_ccf.setEditable(true);

        AutoCompletion.enable(cb_obra);
        cb_obra.setEditable(true);

        add_barrio1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/brick_add.png"))); // NOI18N
        add_barrio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_barrio1ActionPerformed(evt);
            }
        });

        jLabel24.setText("Obra");

        jLabel25.setText("Area");

        jLabel26.setText("Cargo");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel17)
                    .addComponent(jLabel1))
                .addGap(24, 24, 24)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mail_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(salario)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(cb_eps, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(add_eps, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel21))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cb_afp, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_ccf, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(add_afp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(add_ccf, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cb_area, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cb_obra, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_arl, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(add_arl, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(add_barrio1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cb_cargo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(salario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cb_afp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_afp)
                    .addComponent(jLabel9)
                    .addComponent(cb_arl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_arl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(cb_eps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_eps, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(cb_ccf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_ccf)
                    .addComponent(jLabel24)
                    .addComponent(cb_obra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_barrio1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel17)
                    .addComponent(mail_emp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_area, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_cargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Observaciones"));

        obs.setColumns(20);
        obs.setRows(5);
        obs.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                obsFocusLost(evt);
            }
        });
        obs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                obsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(obs);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancel_1.png"))); // NOI18N
        jButton4.setText("Cancelar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/accept_1.png"))); // NOI18N
        jButton14.setText("Aceptar");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel27.setText("Fecha examen de ingreso");

        jLabel28.setText("Fecha  de consentimiento");

        jLabel29.setText("Exonerado FIC");

        cb_exon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..", "SI", "NO" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addGap(18, 18, 18)
                .addComponent(f_ex_ing, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addComponent(f_cons, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_exon, 0, 152, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cb_exon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(f_cons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f_ex_ing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addGap(307, 307, 307))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton14)
                    .addComponent(jButton4))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        int id_tip=0;
        String id_eps="";
        String id_afp="";
        int id_arl=0;
        int id_ccf=0;
        int id_obra=0;
        int id_barrio=0;
        int id_area=0;
        int id_cargo=0;
        String id_municipio="";
        int id_par_acud=0;
        String arl = "";
        String ccf = "";
        if (tipo_nov.getSelectedItem().toString().equals("INGRESO") | tipo_nov.getSelectedItem().toString().equals("BLOQUEADO") | tipo_nov.getSelectedItem().toString().equals("EXTERNO")) {
            if (f_ing_nov.getDate()!=null) {
                Calendar date_in = Calendar.getInstance();
                date_in.setTime(f_ing_nov.getDate());
                Calendar date_today = Calendar.getInstance();
                try {
                    date_today.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(new SimpleDateFormat("dd-MM-yyyy").format(new Date())));
                }catch(ParseException e){
                    e.printStackTrace();
                }
//                if(date_in.getTime().compareTo(date_today.getTime())>=0){
                if (!Busc_nit.getText().equals("")) {
                    if (!nom_empl.getText().equals("")) {
                        if (!Busc_cedula.getText().equals("")) {
                            if (!nomb_emp.getText().equals("")) {
                                if (!ap_emp.getText().equals("")) {
                                    if (!salario.getText().equals("")) {
                                        if (!cb_afp.getSelectedItem().equals("Seleccione..")) {
                                            if (!cb_arl.getSelectedItem().equals("Seleccione..")) {
                                                if (!cb_eps.getSelectedItem().equals("Seleccione..")) {
                                                    if (!cb_ccf.getSelectedItem().equals("Seleccione..")) {
                                                        if (check_field_mail(mail_emp.getText())) {
                                                            if (!cb_area.getSelectedItem().equals("Seleccione..")) {
                                                                if (!cb_cargo.getSelectedItem().equals("Seleccione..")) {
                                                                    if (f_ex_ing.getDate()!=null) {
                                                                        Calendar date_examen = Calendar.getInstance();
                                                                        date_examen.setTime(f_ex_ing.getDate());
                                                                        date_in.add(Calendar.MONTH, -12);
                                                                        if (date_examen.getTime().compareTo(date_in.getTime())>=0) {
                                                                            if (!cb_exon.getSelectedItem().equals("Seleccione..")) {
                                                                                if (f_cons.getDate()!=null) {
                                                                                    Conexion con = new Conexion();
                                                                                    con.conexion();
                                                                                    ResultSet r;
                                                                                    try {
                                                                                        r = con.s.executeQuery ("SELECT * FROM `t_eps` WHERE (NOMBRE_EPS ='"+cb_eps.getSelectedItem().toString()+"');");
                                                                                        if(r.next()){
                                                                                            id_eps=r.getString("ID_EPS");    
                                                                                        }
                                                                                        r = con.s.executeQuery ("SELECT * FROM `t_afp` WHERE (NOMBRE_AFP ='"+cb_afp.getSelectedItem().toString()+"');");
                                                                                        if(r.next()){
                                                                                            id_afp=r.getString("ID_AFP");    
                                                                                        }
//                                                                                        r = con.s.executeQuery ("SELECT * FROM `t_arl` WHERE (NOMBRE_ARL ='"+cb_arl.getSelectedItem().toString()+"');");
//                                                                                        if(r.next()){
//                                                                                            id_arl=r.getInt("ID_ARL");    
//                                                                                        }
//                                                                                        r = con.s.executeQuery ("SELECT * FROM `t_ccf` WHERE (NOMBRE_CCF ='"+cb_ccf.getSelectedItem().toString()+"');");
//                                                                                        if(r.next()){
//                                                                                            id_ccf=r.getInt("ID_CCF");    
//                                                                                        }
                                                                                        r = con.s.executeQuery ("SELECT * FROM `t_obra` WHERE (NOMBRE_OBRA ='"+cb_obra.getSelectedItem().toString()+"');");
                                                                                        if(r.next()){
                                                                                            id_obra=r.getInt("ID_OBRA");    
                                                                                        }
                                                                                        r = con.s.executeQuery ("SELECT * FROM t_tipo_novedad WHERE NOMBRE_TIPO ='"+tipo_nov.getSelectedItem().toString()+"'");
                                                                                        if (r.next()) {
                                                                                            id_tip=r.getInt("ID_TIPO");
                                                                                        }
                                                                                        //**************GET ID AREA
                                                                                        r = con.s.executeQuery ("SELECT *\n" +
                                                                                                                "FROM\n" +
                                                                                                                "    t_actividades WHERE NOMBRE_ACTIVIDAD = '"+cb_area.getSelectedItem()+"';");
                                                                                        if(r.next()){
                                                                                            id_area=r.getInt("ID_ACTIVIDAD");
                                                                                        }
                                                                                        //**************GET ID CARGO
                                                                                        r = con.s.executeQuery ("SELECT *\n" +
                                                                                                                "FROM\n" +
                                                                                                                "    t_cargo WHERE NOMBRE_CARGO = '"+cb_cargo.getSelectedItem()+"';");
                                                                                        if(r.next()){
                                                                                            id_cargo=r.getInt("ID_CARGO");
                                                                                        }
                                                                                        int conf = JOptionPane.showConfirmDialog(this,"Esta seguro que desea continuar?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                                                                                        if (conf == JOptionPane.YES_OPTION) {
                                                                                            con.s.executeUpdate("UPDATE `e_occidente`.`t_novedades`\n" +
                                                                                                                "SET `ID_EMPLEADO` = "+Busc_cedula.getText()+",\n" +
                                                                                                                "  `ID_EMPRESA` = '"+Busc_nit.getText()+"',\n" +
                                                                                                                "  `FECHA_INGRESO` = '"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"',\n" +
                                                                                                                "  `FECHA_RETIRO` = '1900-01-01',\n" +
                                                                                                                "  `SALARIO_NOVEDAD` = "+salario.getText()+",\n" +
                                                                                                                "  `ID_EPS` = '"+id_eps+"',\n" +
                                                                                                                "  `ID_AFP` = '"+id_afp+"',\n" +
                                                                                                                "  `ARL_NOV` = '"+cb_arl.getSelectedItem()+"',\n" +
                                                                                                                "  `CCF_NOV` = '"+cb_ccf.getSelectedItem()+"',\n" +
                                                                                                                "  `OBS_NOV` = '"+obs.getText().toUpperCase()+"',\n" +
                                                                                                                "  `ID_OBRA` = "+id_obra+",\n" +
                                                                                                                "  `ID_TIPO` = "+id_tip+",\n" +
                                                                                                                "  `F_REGISTRO` = '"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"',\n" +
                                                                                                                "  `ID_CARGO` = "+id_cargo+",\n" +
                                                                                                                "  `ID_AREA_TRABAJO` = "+id_area+",\n" +
                                                                                                                "  `F_EXAMEN_INGRESO` = '"+new SimpleDateFormat("yyyy-MM-dd").format(f_ex_ing.getDate())+"',\n" +
                                                                                                                "  `F_CONSENTIMIENTO` = '"+new SimpleDateFormat("yyyy-MM-dd").format(f_cons.getDate())+"',\n" +
                                                                                                                "  `EXON_FIC` = '"+cb_exon.getSelectedItem()+"',\n" +
                                                                                                                "  `MAIL_NOV` = '"+mail_emp.getText()+"'\n" +
                                                                                                                "WHERE `ID_EMPLEADO` = "+ced_edit+"\n" +
                                                                                                                "    AND `ID_EMPRESA` = '"+nit_edit+"'\n" +
                                                                                                                "    AND `FECHA_INGRESO` = '"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_edit)+"'\n" +
                                                                                                                "    AND `ID_TIPO` = "+id_tip_edit+"\n" +
                                                                                                                "    AND `FECHA_RETIRO` = '"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_edit)+"';");
                                                                                            if (id_tip_edit!= id_tip & id_tip==1) {
                                                                                                con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',"+id_tip+",'MODIFICADO A INGRESO','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+Main.id_usuario+"')");
                                                                                            }else if (id_tip_edit!= id_tip & id_tip==4){
                                                                                                con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',"+id_tip+",'MODIFICADO A BLOQUEADO','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+Main.id_usuario+"')");
                                                                                            }else if (id_tip_edit!= id_tip & id_tip==5){
                                                                                                con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',"+id_tip+",'MODIFICADO A EXTERNO','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+Main.id_usuario+"')");
                                                                                            }
                                                                                            if (f_ing_edit.compareTo(f_ing_nov.getDate())!=0) {
                                                                                                con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',"+id_tip+",'MODIFICA FECHA INGRESO','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+Main.id_usuario+"')");
                                                                                            }
                                                                                            if (!nit_edit.equals(Busc_nit.getText())) {
                                                                                                con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',"+id_tip+",'MODIFICA NIT EMPRESA DE "+nit_edit+" A "+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                            }
                                                                                            if (!ced_edit.equals(Busc_cedula.getText())) {
                                                                                                con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',"+id_tip+",'MODIFICA EMPLEADO DE "+ced_edit+" A "+Busc_cedula.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                            }
                                                                                            if (salario_edit!=Float.parseFloat(salario.getText())) {
                                                                                                con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',"+id_tip+",'MODIFICA SALARIO DE "+salario_edit+" A "+salario.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                            }
                                                                                            if (!afp_edit.equals(cb_afp.getSelectedItem().toString())) {
                                                                                                con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',"+id_tip+",'MODIFICA AFP DE "+afp_edit+" A "+cb_afp.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                            }
                                                                                            if (!arl_edit.equals(cb_arl.getSelectedItem().toString())) {
                                                                                                con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',"+id_tip+",'MODIFICA ARL DE "+arl_edit+" A "+cb_arl.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                            }
                                                                                            if (!eps_edit.equals(cb_eps.getSelectedItem().toString())) {
                                                                                                con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',"+id_tip+",'MODIFICA EPS DE "+eps_edit+" A "+cb_eps.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                            }
                                                                                            if (!ccf_edit.equals(cb_ccf.getSelectedItem().toString())) {
                                                                                                con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',"+id_tip+",'MODIFICA CCF DE "+ccf_edit+" A "+cb_ccf.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                            }
                                                                                            if (!obra_edit.equals(cb_obra.getSelectedItem().toString())) {
                                                                                                con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',"+id_tip+",'MODIFICA OBRA DE "+obra_edit+" A "+cb_obra.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                            }
                                                                                            if (!obs_edit.equals(obs.getText())) {
                                                                                                con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',"+id_tip+",'MODIFICA OBSERVACION A "+obs.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                            }
                                                                                            JOptionPane.showMessageDialog(this,"Novedad editada correctamente.","Información",JOptionPane.INFORMATION_MESSAGE);
                                                                                            clear_all();
                                                                                            Busc_cedula.requestFocus();
                                                                                            con.cerrar();
                                                                                            this.dispose();
                                                                                        } else {
                                                                                            Busc_cedula.requestFocus();
                                                                                        }
                                                                                        con.cerrar();
                                                                                    } catch (SQLException e) {
                                                                                        con.cerrar();
                                                                                        e.printStackTrace();
                                                                                        JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);
                                                                                    }
                                                                                }else{
                                                                                    JOptionPane.showMessageDialog(this,"Seleccione la fecha de consentimiento del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                                                }
                                                                            } else {
                                                                                JOptionPane.showMessageDialog(this,"Seleccione la exoneracion del FIC del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                                            }
                                                                        }else {
                                                                            f_ex_ing.requestFocus();
                                                                            JOptionPane.showMessageDialog(this,"Verifique que la Fecha de Examen medico tenga vigencia de un año con respecto a la fecha de ingreso","Error",JOptionPane.ERROR_MESSAGE);
                                                                        }
                                                                    }else {
                                                                        JOptionPane.showMessageDialog(this,"Ingrese la fecha de examen de ingreso.","Error",JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }else {
                                                                    JOptionPane.showMessageDialog(this,"Seleccione el cargo del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                                }
                                                            }else{
                                                                JOptionPane.showMessageDialog(this,"Seleccione el area de trabajo del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                            }
                                                        } else {
                                                            JOptionPane.showMessageDialog(this,"Ingrese un email valido para el empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                        }

                                                    } else {
                                                        JOptionPane.showMessageDialog(this,"Ingrese ls CCF del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(this,"Ingrese la EPS del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(this,"Ingrese la ARL del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(this,"Ingrese la AFP del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(this,"Ingrese el salario del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(this,"Ingrese la cedula del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                }

                            } else {
                                JOptionPane.showMessageDialog(this,"Ingrese la cedula del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this,"Ingrese la cedula del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(this,"Ingrese el nombre del empleador.","Error",JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this,"Ingrese el NIT del empleador.","Error",JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,"Ingrese la fecha de ingreso.","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        if (tipo_nov.getSelectedItem().toString().contains("RETIRO")) {
            if (f_ing_nov.getDate()!=null) {   
                Calendar date_in = Calendar.getInstance();
                date_in.setTime(f_ing_nov.getDate());
                Calendar date_today = Calendar.getInstance();
                try {
                    date_today.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(new SimpleDateFormat("dd-MM-yyyy").format(new Date())));
                }catch(ParseException e){
                    e.printStackTrace();
                }
                if (f_ret_nov.getDate()!=null) {
                    if (!Busc_nit.getText().equals("")) {
                        if (!nom_empl.getText().equals("")) {
                            if (!Busc_cedula.getText().equals("")) {
                                if (!nomb_emp.getText().equals("")) {
                                    if (!ap_emp.getText().equals("")) {
                                        if (!salario.getText().equals("")) {
                                            if (!cb_afp.getSelectedItem().equals("Seleccione..")) {
                                                if (!cb_arl.getSelectedItem().equals("Seleccione..")) {
                                                    if (!cb_eps.getSelectedItem().equals("Seleccione..")) {
                                                        if (!cb_ccf.getSelectedItem().equals("Seleccione..")) {
                                                            if (check_field_mail(mail_emp.getText())) {
                                                                if (!cb_area.getSelectedItem().equals("Seleccione..")) {
                                                                    if (!cb_cargo.getSelectedItem().equals("Seleccione..")) {
                                                                        if (f_ret_nov.getDate().compareTo(f_ing_nov.getDate())>=0 & f_ret_nov.getDate().compareTo(new Date())<0) {
                                                                            if (f_ex_ing.getDate()!=null) {
                                                                                Calendar date_examen = Calendar.getInstance();
                                                                                date_examen.setTime(f_ex_ing.getDate());
                                                                                date_in.add(Calendar.MONTH, -6);
                                                                                if (date_examen.getTime().compareTo(date_in.getTime())>=0) {
                                                                                    if (!cb_exon.getSelectedItem().equals("Seleccione..")) {
                                                                                        if (f_cons.getDate()!=null) {
                                                                                            Conexion con = new Conexion();
                                                                                            con.conexion();
                                                                                            ResultSet r;
                                                                                            try {
                                                                                                r = con.s.executeQuery ("SELECT * FROM `t_eps` WHERE (NOMBRE_EPS ='"+cb_eps.getSelectedItem().toString()+"');");
                                                                                                if(r.next()){
                                                                                                    id_eps=r.getString("ID_EPS");    
                                                                                                }
                                                                                                r = con.s.executeQuery ("SELECT * FROM `t_afp` WHERE (NOMBRE_AFP ='"+cb_afp.getSelectedItem().toString()+"');");
                                                                                                if(r.next()){
                                                                                                    id_afp=r.getString("ID_AFP");    
                                                                                                }
//                                                                                                r = con.s.executeQuery ("SELECT * FROM `t_arl` WHERE (NOMBRE_ARL ='"+cb_arl.getSelectedItem().toString()+"');");
//                                                                                                if(r.next()){
//                                                                                                    id_arl=r.getInt("ID_ARL");    
//                                                                                                }
//                                                                                                r = con.s.executeQuery ("SELECT * FROM `t_ccf` WHERE (NOMBRE_CCF ='"+cb_ccf.getSelectedItem().toString()+"');");
//                                                                                                if(r.next()){
//                                                                                                    id_ccf=r.getInt("ID_CCF");    
//                                                                                                }
                                                                                                r = con.s.executeQuery ("SELECT * FROM `t_obra` WHERE (NOMBRE_OBRA ='"+cb_obra.getSelectedItem().toString()+"');");
                                                                                                if(r.next()){
                                                                                                    id_obra=r.getInt("ID_OBRA");    
                                                                                                }
                                                                                                r = con.s.executeQuery ("SELECT * FROM t_tipo_novedad WHERE NOMBRE_TIPO ='"+tipo_nov.getSelectedItem().toString()+"'");
                                                                                                if (r.next()) {
                                                                                                    id_tip=r.getInt("ID_TIPO");
                                                                                                }
                                                                                                //**************GET ID AREA
                                                                                                r = con.s.executeQuery ("SELECT *\n" +
                                                                                                                        "FROM\n" +
                                                                                                                        "    t_actividades WHERE NOMBRE_ACTIVIDAD = '"+cb_area.getSelectedItem()+"';");
                                                                                                if(r.next()){
                                                                                                    id_area=r.getInt("ID_ACTIVIDAD");
                                                                                                }
                                                                                                //**************GET ID CARGO
                                                                                                r = con.s.executeQuery ("SELECT *\n" +
                                                                                                                        "FROM\n" +
                                                                                                                        "    t_cargo WHERE NOMBRE_CARGO = '"+cb_cargo.getSelectedItem()+"';");
                                                                                                if(r.next()){
                                                                                                    id_cargo=r.getInt("ID_CARGO");
                                                                                                }
                                                                                                int conf = JOptionPane.showConfirmDialog(this,"Esta seguro que desea continuar?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                                                                                                if (conf == JOptionPane.YES_OPTION) {
                                                                                                    con.s.executeUpdate("UPDATE `e_occidente`.`t_novedades`\n" +
                                                                                                                        "SET `ID_EMPLEADO` = "+Busc_cedula.getText()+",\n" +
                                                                                                                        "  `ID_EMPRESA` = '"+Busc_nit.getText()+"',\n" +
                                                                                                                        "  `FECHA_INGRESO` = '"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"',\n" +
                                                                                                                        "  `FECHA_RETIRO` = '"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',\n" +
                                                                                                                        "  `SALARIO_NOVEDAD` = "+salario.getText()+",\n" +
                                                                                                                        "  `ID_EPS` = '"+id_eps+"',\n" +
                                                                                                                        "  `ID_AFP` = '"+id_afp+"',\n" +
                                                                                                                        "  `ARL_NOV` = '"+cb_arl.getSelectedItem()+"',\n" +
                                                                                                                        "  `CCF_NOV` = '"+cb_ccf.getSelectedItem()+"',\n" +
                                                                                                                        "  `OBS_NOV` = '"+obs.getText().toUpperCase()+"',\n" +
                                                                                                                        "  `ID_OBRA` = "+id_obra+",\n" +
                                                                                                                        "  `ID_TIPO` = "+id_tip+",\n" +
                                                                                                                        "  `F_REGISTRO` = '"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"',\n" +
                                                                                                                        "  `ID_CARGO` = "+id_cargo+",\n" +
                                                                                                                        "  `ID_AREA_TRABAJO` = "+id_area+",\n" +
                                                                                                                        "  `F_EXAMEN_INGRESO` = '"+new SimpleDateFormat("yyyy-MM-dd").format(f_ex_ing.getDate())+"',\n" +
                                                                                                                        "  `F_CONSENTIMIENTO` = '"+new SimpleDateFormat("yyyy-MM-dd").format(f_cons.getDate())+"',\n" +
                                                                                                                        "  `EXON_FIC` = '"+cb_exon.getSelectedItem()+"',\n" +
                                                                                                                        "  `MAIL_NOV` = '"+mail_emp.getText()+"'\n" +
                                                                                                                        "WHERE `ID_EMPLEADO` = "+ced_edit+"\n" +
                                                                                                                        "    AND `ID_EMPRESA` = '"+nit_edit+"'\n" +
                                                                                                                        "    AND `FECHA_INGRESO` = '"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_edit)+"'\n" +
                                                                                                                        "    AND `ID_TIPO` = "+id_tip_edit+"\n" +
                                                                                                                        "    AND `FECHA_RETIRO` = '"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_edit)+"';");
                                                                                                    if (id_tip_edit!=id_tip & id_tip==2) {
                                                                                                        con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',"+id_tip+",'MODIFICADO A RETIRO','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"','"+Main.id_usuario+"')");
                                                                                                    }else if (id_tip_edit!=id_tip & id_tip==6) {
                                                                                                        con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',"+id_tip+",'MODIFICADO A RETIRO BLOQUEADO','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"','"+Main.id_usuario+"')");
                                                                                                    }else if (id_tip_edit!=id_tip & id_tip==7) {
                                                                                                        con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',"+id_tip+",'MODIFICADO A RETIRO EXTERNO','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"','"+Main.id_usuario+"')");
                                                                                                    }
                                                                                                    if (f_ing_edit.compareTo(f_ing_nov.getDate())!=0) {
                                                                                                        con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',"+id_tip+",'MODIFICA FECHA INGRESO','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+Main.id_usuario+"')");
                                                                                                    }
                                                                                                    if (f_ret_edit.compareTo(f_ret_nov.getDate())!=0) {
                                                                                                        con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',"+id_tip+",'MODIFICA FECHA RETIRO','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"','"+Main.id_usuario+"')");
                                                                                                    }
                                                                                                    if (!nit_edit.equals(Busc_nit.getText())) {
                                                                                                        con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',"+id_tip+",'MODIFICA NIT EMPRESA DE "+nit_edit+" A "+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                    }
                                                                                                    if (!ced_edit.equals(Busc_cedula.getText())) {
                                                                                                        con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',"+id_tip+",'MODIFICA EMPLEADO DE "+ced_edit+" A "+Busc_cedula.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                    }
                                                                                                    if (salario_edit!=Float.parseFloat(salario.getText())) {
                                                                                                        con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',"+id_tip+",'MODIFICA SALARIO DE "+salario_edit+" A "+salario.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                    }
                                                                                                    if (!afp_edit.equals(cb_afp.getSelectedItem().toString())) {
                                                                                                        con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',"+id_tip+",'MODIFICA AFP DE "+afp_edit+" A "+cb_afp.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                    }
                                                                                                    if (!arl_edit.equals(cb_arl.getSelectedItem().toString())) {
                                                                                                        con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',"+id_tip+",'MODIFICA ARL DE "+arl_edit+" A "+cb_arl.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                    }
                                                                                                    if (!eps_edit.equals(cb_eps.getSelectedItem().toString())) {
                                                                                                        con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',"+id_tip+",'MODIFICA EPS DE "+eps_edit+" A "+cb_eps.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                    }
                                                                                                    if (!ccf_edit.equals(cb_ccf.getSelectedItem().toString())) {
                                                                                                        con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',"+id_tip+",'MODIFICA CCF DE "+ccf_edit+" A "+cb_ccf.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                    }
                                                                                                    if (!obra_edit.equals(cb_obra.getSelectedItem().toString())) {
                                                                                                        con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',"+id_tip+",'MODIFICA OBRA DE "+obra_edit+" A "+cb_obra.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                    }
                                                                                                    if (!obs_edit.equals(obs.getText())) {
                                                                                                        con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',"+id_tip+",'MODIFICA OBSERVACION A "+obs.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                    }
                                                                                                    JOptionPane.showMessageDialog(this,"Novedad de retiro insertada correctamente.","Información",JOptionPane.INFORMATION_MESSAGE);
                                                                                                    clear_all();
                                                                                                    Busc_cedula.requestFocus();
                                                                                                    con.cerrar();
                                                                                                    this.dispose();
                                                                                                } else {
                                                                                                    Busc_cedula.requestFocus();
                                                                                                }
                                                                                                con.cerrar();
                                                                                            } catch (SQLException e) {
                                                                                                e.printStackTrace();
                                                                                                JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);
                                                                                            }
                                                                                        }else{
                                                                                            JOptionPane.showMessageDialog(this,"Seleccione la fecha de consentimiento del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                                                        }
                                                                                    } else {
                                                                                        JOptionPane.showMessageDialog(this,"Seleccione la exoneracion del FIC del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                                                    }
                                                                                }else {
                                                                                    f_ex_ing.requestFocus();
                                                                                    JOptionPane.showMessageDialog(this,"Verifique que la Fecha de Examen medico tenga vigencia de un mes con respecto a la fecha de ingreso","Error",JOptionPane.ERROR_MESSAGE);
                                                                                }
                                                                            }else {
                                                                                f_ex_ing.requestFocus();
                                                                                JOptionPane.showMessageDialog(this,"Ingrese una fecha de Retiro de examen de ingreso valida.","Error",JOptionPane.ERROR_MESSAGE);
                                                                            }
                                                                        } else {
                                                                            JOptionPane.showMessageDialog(this,"Ingrese una fecha de Retiro valida.","Error",JOptionPane.ERROR_MESSAGE);
                                                                        }
                                                                    }else {
                                                                        JOptionPane.showMessageDialog(this,"Seleccione el cargo del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }else{
                                                                    JOptionPane.showMessageDialog(this,"Seleccione el area de trabajo del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                                }
                                                            } else {
                                                                JOptionPane.showMessageDialog(this,"Ingrese un email valido para el empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                            }
                                                        } else {
                                                            JOptionPane.showMessageDialog(this,"Ingrese ls CCF del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                        }
                                                    } else {
                                                        JOptionPane.showMessageDialog(this,"Ingrese la EPS del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(this,"Ingrese la ARL del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(this,"Ingrese la AFP del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(this,"Ingrese el salario del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(this,"Ingrese la cedula del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(this,"Ingrese la cedula del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(this,"Ingrese la cedula del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this,"Ingrese el nombre del empleador.","Error",JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this,"Ingrese el NIT del empleador.","Error",JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this,"Ingrese la fecha de retiro.","Error",JOptionPane.ERROR_MESSAGE);
                }
            } else {
                    JOptionPane.showMessageDialog(this,"Ingrese la fecha de ingreso.","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        if (tipo_nov.getSelectedItem().toString().equals("PRE INGRESO")) {
            if (f_ing_nov.getDate()!=null) {
                Calendar date_in = Calendar.getInstance();
                date_in.setTime(f_ing_nov.getDate());
                Calendar date_today = Calendar.getInstance();
                try {
                    date_today.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(new SimpleDateFormat("dd-MM-yyyy").format(new Date())));
                }catch(ParseException e){
                    e.printStackTrace();
                }
                if (!Busc_nit.getText().equals("")) {
                    if (!nom_empl.getText().equals("")) {
                        if (!Busc_cedula.getText().equals("")) {
                            if (!nomb_emp.getText().equals("")) {
                                if (!ap_emp.getText().equals("")) {
                                    if (!salario.getText().equals("")) {
                                        if (!cb_afp.getSelectedItem().equals("Seleccione..")) {
                                            if (!cb_arl.getSelectedItem().equals("Seleccione..")) {
                                                if (!cb_eps.getSelectedItem().equals("Seleccione..")) {
                                                    if (!cb_ccf.getSelectedItem().equals("Seleccione..")) {
                                                        if (check_field_mail(mail_emp.getText())) {
                                                            if (!cb_area.getSelectedItem().equals("Seleccione..")) {
                                                                if (!cb_cargo.getSelectedItem().equals("Seleccione..")) {
                                                                    if (f_ex_ing.getDate()!=null) {
                                                                        Calendar date_examen = Calendar.getInstance();
                                                                        date_examen.setTime(f_ex_ing.getDate());
                                                                        date_in.add(Calendar.MONTH, -6);
                                                                        if (date_examen.getTime().compareTo(date_in.getTime())>=0) {
                                                                            if (!cb_exon.getSelectedItem().equals("Seleccione..")) {
                                                                                if (f_cons.getDate()!=null) {
                                                                                    Conexion con = new Conexion();
                                                                                    con.conexion();
                                                                                    ResultSet r;
                                                                                    try {
                                                                                        r = con.s.executeQuery ("SELECT *\n" +
                                                                                                                "FROM\n" +
                                                                                                                "    t_empresas\n" +
                                                                                                                "    INNER JOIN t_arl \n" +
                                                                                                                "        ON (t_empresas.ID_ARL = t_arl.ID_ARL)\n" +
                                                                                                                "    INNER JOIN t_ccf \n" +
                                                                                                                "        ON (t_empresas.ID_CCF = t_ccf.ID_CCF)"
                                                                                                + "WHERE NOMBRE_EMPRESA = '"+nom_empl.getText()+"'");
                                                                                        if (r.next()) {
                                                                                            arl = r.getString("NOMBRE_ARL");
                                                                                            ccf = r.getString("NOMBRE_CCF");
                                                                                        }
                                                                                        r = con.s.executeQuery ("SELECT * FROM `t_obra` WHERE (NOMBRE_OBRA ='"+cb_obra.getSelectedItem().toString()+"');");
                                                                                        if(r.next()){
                                                                                            id_obra=r.getInt("ID_OBRA");    
                                                                                        }
                                                                                        //**************GET ID AREA
                                                                                        r = con.s.executeQuery ("SELECT *\n" +
                                                                                                                "FROM\n" +
                                                                                                                "    t_actividades WHERE NOMBRE_ACTIVIDAD = '"+cb_area.getSelectedItem()+"';");
                                                                                        if(r.next()){
                                                                                            id_area=r.getInt("ID_ACTIVIDAD");
                                                                                        }
                                                                                        //**************GET ID CARGO
                                                                                        r = con.s.executeQuery ("SELECT *\n" +
                                                                                                                "FROM\n" +
                                                                                                                "    t_cargo WHERE NOMBRE_CARGO = '"+cb_cargo.getSelectedItem()+"';");
                                                                                        if(r.next()){
                                                                                            id_cargo=r.getInt("ID_CARGO");
                                                                                        }
                                                                                        int conf = JOptionPane.showConfirmDialog(this,"Esta seguro que desea continuar?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                                                                                        if (conf == JOptionPane.YES_OPTION) {
                                                                                            con.s.executeUpdate("UPDATE `e_occidente`.`t_novedades`\n" +
                                                                                                                "SET `ID_EMPLEADO` = "+Busc_cedula.getText()+",\n" +
                                                                                                                "  `ID_EMPRESA` = '"+Busc_nit.getText()+"',\n" +
                                                                                                                "  `FECHA_INGRESO` = '"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"',\n" +
                                                                                                                "  `FECHA_RETIRO` = '1900-01-01',\n" +
                                                                                                                "  `SALARIO_NOVEDAD` = "+salario.getText()+",\n" +
                                                                                                                "  `ID_EPS` = 1,\n" +
                                                                                                                "  `ID_AFP` = 1,\n" +
                                                                                                                "  `ARL_NOV` = '"+arl+"',\n" +
                                                                                                                "  `CCF_NOV` = '"+ccf+"',\n" +
                                                                                                                "  `OBS_NOV` = '"+obs.getText().toUpperCase()+"',\n" +
                                                                                                                "  `ID_OBRA` = "+id_obra+",\n" +
                                                                                                                "  `ID_TIPO` = 3,\n" +
                                                                                                                "  `F_REGISTRO` = '"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"',\n" +
                                                                                                                "  `ID_CARGO` = "+id_cargo+",\n" +
                                                                                                                "  `ID_AREA_TRABAJO` = "+id_area+",\n" +
                                                                                                                "  `F_EXAMEN_INGRESO` = '"+new SimpleDateFormat("yyyy-MM-dd").format(f_ex_ing.getDate())+"',\n" +
                                                                                                                "  `F_CONSENTIMIENTO` = '"+new SimpleDateFormat("yyyy-MM-dd").format(f_cons.getDate())+"',\n" +
                                                                                                                "  `EXON_FIC` = '"+cb_exon.getSelectedItem()+"',\n" +
                                                                                                                "  `MAIL_NOV` = '"+mail_emp.getText()+"'\n" +
                                                                                                                "WHERE `ID_EMPLEADO` = "+ced_edit+"\n" +
                                                                                                                "    AND `ID_EMPRESA` = '"+nit_edit+"'\n" +
                                                                                                                "    AND `FECHA_INGRESO` = '"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_edit)+"'\n" +
                                                                                                                "    AND `ID_TIPO` = "+id_tip_edit+"\n" +
                                                                                                                "    AND `FECHA_RETIRO` = '"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_edit)+"';");
//                                                                                            con.s.executeUpdate("UPDATE `t_novedades` "
//                                                                                                    + "SET `ID_EMPLEADO`="+Busc_cedula.getText()+","
//                                                                                                    + "`ID_EMPRESA`='"+Busc_nit.getText()+"',"
//                                                                                                    + "`FECHA_INGRESO`='"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"',"
//                                                                                                    + "`FECHA_RETIRO`='1900-01-01',"
//                                                                                                    + "`SALARIO_NOVEDAD`="+salario.getText()+","
//                                                                                                    + "`ID_EPS`=1,"
//                                                                                                    + "`ID_AFP`=1,"
//                                                                                                    + "`ARL_NOV`='"+arl+"',"
//                                                                                                    + "`CCF_NOV`="+cb_ccf.getSelectedItem()+","
//                                                                                                    + "`F_NAC_NOV`='"+aux_f_nac+"',"
//                                                                                                    + "`F_EXP_NOV`='"+aux_f_exp+"',"
//                                                                                                    + "`DIR_EMP_NOV`='"+dir_emp.getText()+"',"
//                                                                                                    + "`BARRIO_NOV`='"+barrio.getText()+"',"
//                                                                                                    + "`ID_MUN_NOV`="+id_municipio+","
//                                                                                                    + "`TEL_NOV`='"+tel_emp.getText()+"',"
//                                                                                                    + "`MAIL_NOV`='"+mail_emp.getText()+"',"
//                                                                                                    + "`ACUD_NOV`='"+acud.getText()+"',"
//                                                                                                    + "`ID_PAR_ACU_NOV`="+id_par_acud+","
//                                                                                                    + "`TEL_ACUD_NOV`='"+tel_acud.getText()+"',"
//                                                                                                    + "`OBS_NOV`='"+obs.getText().toUpperCase()+"',"
//                                                                                                    + "`ID_OBRA`="+id_obra+","
//                                                                                                    + "`ID_TIPO`=3, "
//                                                                                                    + "`F_REGISTRO`='"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"', "
//                                                                                                    + "ID_CARGO = "+id_cargo+", "
//                                                                                                    + "ID_AREA_TRABAJO = "+id_area+" "
//                                                                                                    + "WHERE ID_EMPLEADO="+ced_edit+" "
//                                                                                                    + "AND ID_EMPRESA='"+nit_edit+"' "
//                                                                                                    + "AND FECHA_INGRESO='"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_edit)+"' "
//                                                                                                    + "AND FECHA_RETIRO='"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_edit)+"' "
//                                                                                                    + "AND ID_TIPO="+id_tip_edit);
                                                                                            if (id_tip_edit!=2) {
                                                                                                con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',3,'MODIFICADO A PRE-INGRESO','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+Main.id_usuario+"')");
                                                                                            }
                                                                                            if (f_ing_edit.compareTo(f_ing_nov.getDate())!=0) {
                                                                                                con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',3,'MODIFICA FECHA INGRESO','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+Main.id_usuario+"')");
                                                                                            }
                                                                                            if (!nit_edit.equals(Busc_nit.getText())) {
                                                                                                con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',3,'MODIFICA NIT EMPRESA DE "+nit_edit+" A "+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                            }
                                                                                            if (!ced_edit.equals(Busc_cedula.getText())) {
                                                                                                con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',3,'MODIFICA EMPLEADO DE "+ced_edit+" A "+Busc_cedula.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                            }
                                                                                            if (salario_edit!=Float.parseFloat(salario.getText())) {
                                                                                                con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',3,'MODIFICA SALARIO DE "+salario_edit+" A "+salario.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                            }
                                                                                            if (!afp_edit.equals(cb_afp.getSelectedItem().toString())) {
                                                                                                con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',3,'MODIFICA AFP DE "+afp_edit+" A "+cb_afp.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                            }
                                                                                            if (!arl_edit.equals(cb_arl.getSelectedItem().toString())) {
                                                                                                con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',3,'MODIFICA ARL DE "+arl_edit+" A "+cb_arl.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                            }
                                                                                            if (!eps_edit.equals(cb_eps.getSelectedItem().toString())) {
                                                                                                con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',3,'MODIFICA EPS DE "+eps_edit+" A "+cb_eps.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                            }
                                                                                            if (!ccf_edit.equals(cb_ccf.getSelectedItem().toString())) {
                                                                                                con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',3,'MODIFICA CCF DE "+ccf_edit+" A "+cb_ccf.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                            }
                                                                                            if (!obra_edit.equals(cb_obra.getSelectedItem().toString())) {
                                                                                                con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',3,'MODIFICA OBRA DE "+obra_edit+" A "+cb_obra.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                            }
                                                                                            if (!obs_edit.equals(obs.getText())) {
                                                                                                con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',3,'MODIFICA OBSERVACION A "+obs.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                            }
                                                                                            JOptionPane.showMessageDialog(this,"Novedad de pre-ingreso editada correctamente.","Información",JOptionPane.INFORMATION_MESSAGE);
                                                                                            clear_all();
                                                                                            Busc_cedula.requestFocus();
                                                                                            con.cerrar();
                                                                                            this.dispose();
                                                                                        } else {
                                                                                            Busc_cedula.requestFocus();
                                                                                        }
                                                                                        con.cerrar();
                                                                                    } catch (SQLException e) {
                                                                                        con.cerrar();
                                                                                        e.printStackTrace();
                                                                                        JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);
                                                                                    }
                                                                                }else{
                                                                                    JOptionPane.showMessageDialog(this,"Seleccione la fecha de consentimiento del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                                                }
                                                                            } else {
                                                                                JOptionPane.showMessageDialog(this,"Seleccione la exoneracion del FIC del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                                            }
                                                                        }else {
                                                                            f_ex_ing.requestFocus();
                                                                            JOptionPane.showMessageDialog(this,"Verifique que la Fecha de Examen medico tenga vigencia de un mes con respecto a la fecha de ingreso","Error",JOptionPane.ERROR_MESSAGE);
                                                                        }
                                                                    }
                                                                }else {
                                                                    JOptionPane.showMessageDialog(this,"Seleccione el cargo del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                                }
                                                            }else{
                                                                JOptionPane.showMessageDialog(this,"Seleccione el area de trabajo del empleado","Error",JOptionPane.ERROR_MESSAGE);
                                                            }
                                                        } else {
                                                            JOptionPane.showMessageDialog(this,"Ingrese un email valido para el empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                        }
                                                    } else {
                                                        JOptionPane.showMessageDialog(this,"Ingrese ls CCF del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(this,"Ingrese la EPS del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(this,"Ingrese la ARL del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(this,"Ingrese la AFP del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(this,"Ingrese el salario del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(this,"Ingrese la cedula del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(this,"Ingrese la cedula del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this,"Ingrese la cedula del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this,"Ingrese el nombre del empleador.","Error",JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this,"Ingrese el NIT del empleador.","Error",JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,"Ingrese la fecha de ingreso.","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void obsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_obsMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2 & block) {
            obs.setEditable(true);
        }
    }//GEN-LAST:event_obsMouseClicked

    private void obsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_obsFocusLost
        // TODO add your handling code here:
        if (!obs.getText().equals("")) {
            obs.setEditable(false);
        }

    }//GEN-LAST:event_obsFocusLost

    private void add_barrio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_barrio1ActionPerformed
        // TODO add your handling code here:
        Add_Obra add_obra = new Add_Obra(this, true);
        add_obra.setLocationRelativeTo(this);
        add_obra.setVisible(true);
    }//GEN-LAST:event_add_barrio1ActionPerformed

    private void add_epsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_epsActionPerformed
        // TODO add your handling code here:
        Add_EPS add_eps = new Add_EPS(this, true);
        add_eps.setLocationRelativeTo(this);
        add_eps.setVisible(true);
    }//GEN-LAST:event_add_epsActionPerformed

    private void mail_empMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mail_empMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2 & block) {
            mail_emp.setEditable(true);
        }
    }//GEN-LAST:event_mail_empMouseClicked

    private void mail_empFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mail_empFocusLost
        // TODO add your handling code here:
        if (!mail_emp.getText().equals("")) {
            mail_emp.setEditable(false);
        }
    }//GEN-LAST:event_mail_empFocusLost

    private void salarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_salarioKeyTyped
        // TODO add your handling code here:
        char a=evt.getKeyChar();
        if(!(a>=KeyEvent.VK_0 && a<=KeyEvent.VK_9)) {
            evt.consume();
        }
    }//GEN-LAST:event_salarioKeyTyped

    private void salarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salarioMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2 & block) {
            salario.setEditable(true);
        }
    }//GEN-LAST:event_salarioMouseClicked

    private void salarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_salarioFocusLost
        // TODO add your handling code here:
        if (!salario.getText().equals("")) {
            salario.setEditable(false);
        }

    }//GEN-LAST:event_salarioFocusLost

    private void Busc_cedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Busc_cedulaKeyTyped
        // TODO add your handling code here:
        char a=evt.getKeyChar();
        if(!(a>=KeyEvent.VK_0 && a<=KeyEvent.VK_9)) {
            evt.consume();
        }
    }//GEN-LAST:event_Busc_cedulaKeyTyped

    private void Busc_cedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Busc_cedulaKeyReleased
        // TODO add your handling code here:
        int b=evt.getKeyCode();
        if(b==KeyEvent.VK_ENTER){
            callback_empleado(Busc_cedula.getText());
        }
    }//GEN-LAST:event_Busc_cedulaKeyReleased

    private void Busc_nitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Busc_nitKeyTyped
        // TODO add your handling code here:
        char a=evt.getKeyChar();
        if (a!=KeyEvent.VK_MINUS) {
            if(!(a>=KeyEvent.VK_0 && a<=KeyEvent.VK_9)) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_Busc_nitKeyTyped

    private void tipo_novItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipo_novItemStateChanged
        // TODO add your handling code here:
        if (tipo_nov.getSelectedItem().equals("INGRESO") | tipo_nov.getSelectedItem().equals("EXTERNO") | tipo_nov.getSelectedItem().equals("BLOQUEADO")) {
            f_ret_nov.setDate(null);
            f_ret_nov.setEnabled(false);
            f_ing_nov.setEnabled(true);
            cb_eps.setEnabled(true);
            cb_afp.setEnabled(true);
        }else if (tipo_nov.getSelectedItem().toString().contains("RETIRO")) {
            f_ret_nov.setEnabled(true);
            f_ing_nov.setEnabled(true);
            cb_eps.setEnabled(true);
            cb_afp.setEnabled(true);
        }else if (tipo_nov.getSelectedItem().equals("PRE INGRESO")) {
            f_ret_nov.setDate(null);
            f_ret_nov.setEnabled(false);
            f_ing_nov.setEnabled(true);
            cb_eps.setSelectedItem("Seleccione..");
            cb_afp.setSelectedItem("Seleccione..");
            cb_eps.setEnabled(false);
            cb_afp.setEnabled(false);
        }
    }//GEN-LAST:event_tipo_novItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void add_empActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_empActionPerformed
        // TODO add your handling code here:
        Add_Empleado add_empleado = new Add_Empleado(this, true);
        add_empleado.setLocationRelativeTo(this);
        add_empleado.setVisible(true);
    }//GEN-LAST:event_add_empActionPerformed

    private void add_empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_empresaActionPerformed
        // TODO add your handling code here:
        Add_Empresa add_empresa = new Add_Empresa(this, true);
        add_empresa.setLocationRelativeTo(this);
        add_empresa.setVisible(true);
    }//GEN-LAST:event_add_empresaActionPerformed

    private void edit_empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_empresaActionPerformed
        // TODO add your handling code here:
        Edd_Empresa edd_empresa = new Edd_Empresa(this, true, "");
        edd_empresa.setLocationRelativeTo(this);
        edd_empresa.setVisible(true);
    }//GEN-LAST:event_edit_empresaActionPerformed

    private void edit_empActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_empActionPerformed
        // TODO add your handling code here:
        Edd_Empleado edd_empleado = new Edd_Empleado(this, true);
        edd_empleado.setLocationRelativeTo(this);
        edd_empleado.setVisible(true);
    }//GEN-LAST:event_edit_empActionPerformed

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
            java.util.logging.Logger.getLogger(Edd_Novedad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Edd_Novedad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Edd_Novedad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Edd_Novedad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                Edd_Novedad dialog = new Edd_Novedad(new javax.swing.JDialog(), true, new String(),new String(),new Object(),new Object(), new String(), true);
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
    private javax.swing.JTextField Busc_cedula;
    private javax.swing.JTextField Busc_nit;
    private javax.swing.JButton add_afp;
    private javax.swing.JButton add_arl;
    private javax.swing.JButton add_barrio1;
    private javax.swing.JButton add_ccf;
    private javax.swing.JButton add_emp;
    private javax.swing.JButton add_empresa;
    private javax.swing.JButton add_eps;
    private javax.swing.JTextField ap_emp;
    private javax.swing.JComboBox<String> cb_afp;
    private javax.swing.JComboBox<String> cb_area;
    private javax.swing.JComboBox<String> cb_arl;
    private javax.swing.JComboBox<String> cb_cargo;
    private javax.swing.JComboBox<String> cb_ccf;
    private javax.swing.JComboBox<String> cb_eps;
    private javax.swing.JComboBox<String> cb_exon;
    private javax.swing.JComboBox<String> cb_obra;
    private javax.swing.JButton edit_emp;
    private javax.swing.JButton edit_empresa;
    private com.toedter.calendar.JDateChooser f_cons;
    private com.toedter.calendar.JDateChooser f_ex_ing;
    private com.toedter.calendar.JDateChooser f_ing_nov;
    private com.toedter.calendar.JDateChooser f_ret_nov;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField mail_emp;
    private javax.swing.JTextField nom_empl;
    private javax.swing.JTextField nomb_emp;
    private javax.swing.JTextArea obs;
    private javax.swing.JTextField salario;
    private static javax.swing.JComboBox<String> tipo_nov;
    // End of variables declaration//GEN-END:variables
public void update_cb_tipo_nov(){
        Conexion con = new Conexion();
        con.conexion();
        ResultSet r;
        try{
            r = con.s.executeQuery ("SELECT * FROM `t_tipo_novedad` ORDER BY ID_TIPO ASC");
            tipo_nov.removeAllItems();
            tipo_nov.addItem("Seleccione.");
            while(r.next()){
                tipo_nov.addItem(r.getString("NOMBRE_TIPO"));
            }
            con.cerrar();
            
        }catch(SQLException j){
            con.cerrar();
            JOptionPane.showMessageDialog(null,j,"Error!",JOptionPane.ERROR_MESSAGE);
        }
}
public void ac_nit(){
    ac_empleador.removeAllItems();
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        //r = con.s.executeQuery ("SELECT * FROM T_EMPRESAS WHERE ID_EMPRESA LIKE '"+Busc_nit.getText()+"%' ORDER BY ID_EMPRESA ASC;");
        r = con.s.executeQuery ("SELECT * FROM T_EMPRESAS ORDER BY ID_EMPRESA ASC;");
        while(r.next()){
            String str=r.getString("ID_EMPRESA");
            ac_empleador.addItem(str);
            //list_empleador.addElement(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public void ac_cedula(){
    ac_empleados.removeAllItems();
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_EMPLEADOS ORDER BY ID_EMP ASC;");
        while(r.next()){
            String str=r.getString("ID_EMP");
            ac_empleados.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public void ac_eps(){
    cb_eps.removeAllItems();
    cb_eps.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_EPS ORDER BY NOMBRE_EPS ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_EPS");
            cb_eps.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public void ac_afp(){
    cb_afp.removeAllItems();
    cb_afp.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_AFP ORDER BY NOMBRE_AFP ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_AFP");
            cb_afp.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }

}
public void ac_ccf(){
    cb_ccf.removeAllItems();
    cb_ccf.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_CCF ORDER BY NOMBRE_CCF ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_CCF");
            cb_ccf.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }

}
public void ac_arl(){
    cb_arl.removeAllItems();
    cb_arl.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_ARL ORDER BY NOMBRE_ARL ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_ARL");
            cb_arl.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }

}

//public void ac_mun(){
//    cb_mun.removeAllItems();
//    cb_mun.addItem("Seleccione..");
//    Conexion con = new Conexion();
//    con.conexion();
//    ResultSet r;
//    try{
//        r = con.s.executeQuery ("SELECT *\n" +
//                                "FROM\n" +
//                                "    t_municipios\n" +
//                                "    INNER JOIN t_departamentos \n" +
//                                "        ON (t_municipios.ID_DEP = t_departamentos.ID_DEP) ORDER BY NOMBRE_MUN ASC;");
//        while(r.next()){
//            String str=r.getString("NOMBRE_MUN")+"-"+r.getString("NOMBRE_DEP");
//            cb_mun.addItem(str);
//        }
//        con.cerrar();
//    }catch(SQLException j){
//        con.cerrar();
//        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
//    }
//
//}
//public void ac_par(){
//    cb_par.removeAllItems();
//    cb_par.addItem("Seleccione..");
//    Conexion con = new Conexion();
//    con.conexion();
//    ResultSet r;
//    try{
//        r = con.s.executeQuery ("SELECT * FROM T_PARENTESCO ORDER BY NOMBRE_PAR ASC;");
//        while(r.next()){
//            String str=r.getString("NOMBRE_PAR");
//            cb_par.addItem(str);
//        }
//        con.cerrar();
//    }catch(SQLException j){
//        con.cerrar();
//        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
//    }
//
//}
public void ac_obra(){
    cb_obra.removeAllItems();
    cb_obra.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_OBRA ORDER BY NOMBRE_OBRA ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_OBRA");
            cb_obra.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }

}
public final void ac_area(){
    cb_area.removeAllItems();
    cb_area.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT *\n" +
                                "FROM\n" +
                                "    t_actividades\n" +
                                "    ORDER BY NOMBRE_ACTIVIDAD ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_ACTIVIDAD");
            cb_area.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public final void ac_cargo(){
    cb_cargo.removeAllItems();
    cb_cargo.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT *\n" +
                                "FROM\n" +
                                "    t_cargo\n" +
                                "    ORDER BY NOMBRE_CARGO ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_CARGO");
            cb_cargo.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
public Boolean call_info_emp(String c){
    Info_Empleador info=new Info_Empleador(this,true,c);
    info.setLocationRelativeTo(this);
    info.setVisible(true);
    return info.getRet();
}
public void clear_all (){
    f_ing_nov.setDate(null);
    f_ret_nov.setDate(null);
    Busc_nit.setText("");
    Busc_cedula.setText("");
    nom_empl.setText("");
    nomb_emp.setText("");
    ap_emp.setText("");
    salario.setText("");
    cb_afp.setSelectedItem("Seleccione..");
    cb_arl.setSelectedItem("Seleccione..");
    cb_eps.setSelectedItem("Seleccione..");
    cb_ccf.setSelectedItem("Seleccione..");
    cb_obra.setSelectedItem("Seleccione..");
    f_ex_ing.setDate(null);
    f_cons.setDate(null);
    cb_exon.setSelectedItem("Seleccione..");
    mail_emp.setText("");
    obs.setText("");


}
public void callback_empleado(String s){
    Busc_cedula.setText(s);
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_EMPLEADOS WHERE ID_EMP="+s+";");
        if(r.next()){
            nomb_emp.setText(r.getString("NOMBRE_1_EMP")+" "+r.getString("NOMBRE_2_EMP"));
            ap_emp.setText(r.getString("APELLIDO_1_EMP")+" "+r.getString("APELLIDO_2_EMP"));
            ap_emp.requestFocus();
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        j.printStackTrace();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    } 

}
public void enable_ingreso(){
    f_ing_nov.setEnabled(true);
    f_ret_nov.setEnabled(false);
    Busc_nit.setEditable(true);
    Busc_cedula.setEditable(true);
    salario.setEditable(true);
    cb_afp.setEnabled(true);
    cb_arl.setEnabled(true);
    cb_eps.setEnabled(true);
    cb_ccf.setEnabled(true);
    cb_obra.setEnabled(true);
    add_afp.setEnabled(true);
    add_arl.setEnabled(true);
    add_ccf.setEnabled(true);
    add_emp.setEnabled(true);
    add_empresa.setEnabled(true);
    add_eps.setEnabled(true);
    edit_emp.setEnabled(true);
    edit_empresa.setEnabled(true);
//    f_ex_ing.setEnabled(true);
//    f_cons.setEnabled(true);
//    cb_exon.setEnabled(true);
    mail_emp.setEditable(true);
    obs.setEditable(true);
    Busc_cedula.requestFocus();

}
public void disable_ingreso(){
    f_ing_nov.setEnabled(true);
    f_ret_nov.setEnabled(false);
    Busc_nit.setEditable(false);
    Busc_cedula.setEditable(true);
    salario.setEditable(false);
    cb_afp.setEnabled(false);
    cb_arl.setEnabled(false);
    cb_eps.setEnabled(false);
    cb_ccf.setEnabled(false);
    cb_obra.setEnabled(false);
    add_afp.setEnabled(false);
    add_arl.setEnabled(false);
    add_ccf.setEnabled(false);
    add_emp.setEnabled(false);
    add_empresa.setEnabled(false);
    add_eps.setEnabled(false);
    edit_emp.setEnabled(false);
    edit_empresa.setEnabled(false);
    mail_emp.setEditable(false);
    obs.setEditable(false);
    Busc_cedula.requestFocus();

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
private void sel_campos(String ced, String nit, Date f_ingreso, Date f_retiro, String tip) {
    int id_tipo=0;
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try {
        //System.out.println("SELECT * FROM t_empleados WHERE ID_EMP ="+Long.parseLong(ced));
        r = con.s.executeQuery ("SELECT * FROM t_empleados WHERE ID_EMP ="+Long.parseLong(ced));
        if (r.next()) {
            //System.out.println("Test 4");
            Busc_cedula.setText(r.getString("ID_EMP"));
            nomb_emp.setText(r.getString("NOMBRE_1_EMP")+" "+r.getString("NOMBRE_2_EMP"));
            ap_emp.setText(r.getString("APELLIDO_1_EMP")+" "+r.getString("APELLIDO_2_EMP"));
            //System.out.println("ok empleado");
        }
        r = con.s.executeQuery ("SELECT * FROM t_tipo_novedad WHERE NOMBRE_TIPO ='"+tip+"'");
        if (r.next()) {
            id_tipo=r.getInt("ID_TIPO");
            tipo_nov.setSelectedItem(r.getString("NOMBRE_TIPO"));
            this.id_tip_edit=id_tipo;
            //System.out.println("ok tipo novedad");
        }
        r = con.s.executeQuery (
            "SELECT\n" +
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
            "  INNER JOIN `t_actividades` \n" +
            "    ON (`t_novedades`.`ID_AREA_TRABAJO` = `t_actividades`.`ID_ACTIVIDAD`)\n" +
            "  INNER JOIN `t_cargo` \n" +
            "    ON (`t_novedades`.`ID_CARGO` = `t_cargo`.`ID_CARGO`)"
            + "WHERE (ID_EMP = "+Long.parseLong(ced)+"\n"
            + "       AND t_novedades.ID_EMPRESA = '"+nit+"' AND t_novedades.FECHA_INGRESO = '"+new SimpleDateFormat("yyyy-MM-dd").format(f_ingreso)+"' AND t_novedades.FECHA_RETIRO = '"+new SimpleDateFormat("yyyy-MM-dd").format(f_retiro)+"' AND t_novedades.ID_TIPO = "+id_tipo+")\n"
            + "ORDER BY FECHA_INGRESO DESC;");
        if(r.next()){
            tipo_nov.setSelectedItem(tip);
            f_ing_nov.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(r.getString("FECHA_INGRESO")));
            if (r.getString("FECHA_RETIRO").equals("1900-01-01")) {
                f_ret_nov.setDate(null);
            } else {
                f_ret_nov.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(r.getString("FECHA_RETIRO")));
            }
            Busc_nit.setText(r.getString("ID_EMPRESA"));
            nom_empl.setText(r.getString("NOMBRE_EMPRESA"));
            salario.setText(r.getString("SALARIO_NOVEDAD"));
            this.salario_edit=Float.parseFloat(salario.getText());
            cb_afp.setSelectedItem(r.getString("NOMBRE_AFP"));
            this.afp_edit=cb_afp.getSelectedItem().toString();
            cb_arl.setSelectedItem(r.getString("ARL_NOV"));
            this.arl_edit=cb_arl.getSelectedItem().toString();
            cb_eps.setSelectedItem(r.getString("NOMBRE_EPS"));
            this.eps_edit=cb_eps.getSelectedItem().toString();
            cb_ccf.setSelectedItem(r.getString("CCF_NOV"));
            this.ccf_edit=cb_ccf.getSelectedItem().toString();
            cb_obra.setSelectedItem(r.getString("NOMBRE_OBRA"));
            this.obra_edit=cb_obra.getSelectedItem().toString();
//            f_nac.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(r.getString("F_NAC_NOV")));
//            f_exp.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(r.getString("F_EXP_NOV")));
//            dir_emp.setText(r.getString("DIR_EMP_NOV"));
//            tel_emp.setText(r.getString("TEL_NOV"));
//            barrio.setText(r.getString("BARRIO_NOV"));
//            cb_mun.setSelectedItem(r.getString("NOMBRE_MUN")+"-"+r.getString("NOMBRE_DEP"));
            mail_emp.setText(r.getString("MAIL_NOV"));
//            acud.setText(r.getString("ACUD_NOV"));
//            cb_par.setSelectedItem(r.getString("NOMBRE_PAR"));
//            tel_acud.setText(r.getString("TEL_ACUD_NOV"));
            obs.setText(r.getString("OBS_NOV"));
            this.obs_edit=obs.getText();
            cb_area.setSelectedItem(r.getString("NOMBRE_ACTIVIDAD"));
            cb_cargo.setSelectedItem(r.getString("NOMBRE_CARGO"));
            f_ex_ing.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(r.getString("F_EXAMEN_INGRESO")));
            f_cons.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(r.getString("F_CONSENTIMIENTO")));
            cb_exon.setSelectedItem(r.getString("EXON_FIC"));
            Busc_cedula.requestFocus();
        }
        con.cerrar();
    } catch (SQLException | ParseException e) {
        con.cerrar();
        e.printStackTrace();
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
public void load(){
    sel_campos(ced_edit,nit_edit,f_ing_edit,f_ret_edit,tip_edit);
}
}
