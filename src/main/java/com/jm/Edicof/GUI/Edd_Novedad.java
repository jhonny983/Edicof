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
        System.out.println("Test 1");
        //////////////////////////////------------------------------------------------------------
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        screenSize = Main.getsize();
        this.setSize(screenSize.width, screenSize.height-10);
        this.setLocationRelativeTo(null);
        System.out.println("Test 2");
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
        ac_mun();
        ac_par();
        ac_obra();
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
            f_nac.setEnabled(false);
            f_exp.setEnabled(false);
            dir_emp.setEnabled(false);
            tel_emp.setEnabled(false);
            barrio.setEnabled(false);
            cb_mun.setEnabled(false);
            mail_emp.setEnabled(false);
            acud.setEnabled(false);
            cb_par.setEnabled(false);
            tel_acud.setEnabled(false);
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

        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
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
        jLabel11 = new javax.swing.JLabel();
        f_nac = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        f_exp = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        dir_emp = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        tel_emp = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        mail_emp = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        acud = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        tel_acud = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        add_eps = new javax.swing.JButton();
        add_ccf = new javax.swing.JButton();
        add_afp = new javax.swing.JButton();
        add_arl = new javax.swing.JButton();
        add_mun = new javax.swing.JButton();
        add_par = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cb_arl = new javax.swing.JComboBox<>();
        cb_eps = new javax.swing.JComboBox<>();
        cb_afp = new javax.swing.JComboBox<>();
        cb_ccf = new javax.swing.JComboBox<>();
        cb_mun = new javax.swing.JComboBox<>();
        cb_par = new javax.swing.JComboBox<>();
        cb_obra = new javax.swing.JComboBox<>();
        add_barrio1 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        barrio = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        obs = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Novedad");

        jPanel4.setAutoscrolls(true);

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
                .addContainerGap(59, Short.MAX_VALUE)
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
                .addContainerGap(59, Short.MAX_VALUE))
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
                .addContainerGap(94, Short.MAX_VALUE)
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
                .addContainerGap(95, Short.MAX_VALUE))
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
                .addContainerGap(47, Short.MAX_VALUE)
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
                .addContainerGap(47, Short.MAX_VALUE))
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

        jLabel11.setText("Fecha Nacimiento");

        jLabel12.setText("Fecha Expedición");

        jLabel13.setText("Dirección");

        dir_emp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dir_emp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dir_empFocusLost(evt);
            }
        });
        dir_emp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dir_empMouseClicked(evt);
            }
        });

        jLabel14.setText("Barrio");

        jLabel16.setText("Telefono empleado");

        tel_emp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tel_emp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tel_empFocusLost(evt);
            }
        });
        tel_emp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tel_empMouseClicked(evt);
            }
        });

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

        jLabel18.setText("Acudiente");

        acud.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        acud.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                acudFocusLost(evt);
            }
        });
        acud.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acudMouseClicked(evt);
            }
        });

        jLabel19.setText("Parentesco");

        jLabel20.setText("Telefono acudiente");

        tel_acud.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tel_acud.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tel_acudFocusLost(evt);
            }
        });
        tel_acud.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tel_acudMouseClicked(evt);
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

        add_mun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cards_bind_address.png"))); // NOI18N
        add_mun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_munActionPerformed(evt);
            }
        });

        add_par.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_add.png"))); // NOI18N
        add_par.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_parActionPerformed(evt);
            }
        });

        jLabel10.setText("Municipio");

        AutoCompletion.enable(cb_arl);
        cb_arl.setEditable(true);

        AutoCompletion.enable(cb_eps);
        cb_eps.setEditable(true);

        AutoCompletion.enable(cb_afp);
        cb_afp.setEditable(true);

        AutoCompletion.enable(cb_ccf);
        cb_ccf.setEditable(true);

        AutoCompletion.enable(cb_mun);
        cb_mun.setEditable(true);

        AutoCompletion.enable(cb_par);
        cb_par.setEditable(true);

        AutoCompletion.enable(cb_obra);
        cb_obra.setEditable(true);

        add_barrio1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/brick_add.png"))); // NOI18N
        add_barrio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_barrio1ActionPerformed(evt);
            }
        });

        jLabel24.setText("Obra");

        barrio.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addGap(18, 18, 18))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addGap(25, 25, 25)))
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(f_nac, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel12))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(tel_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel14)
                                    .addGap(18, 18, 18)
                                    .addComponent(barrio, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addGap(21, 21, 21)
                                    .addComponent(dir_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel10)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel6))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(salario)
                                        .addComponent(cb_eps, 0, 162, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(add_eps, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel21))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cb_afp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cb_ccf, 0, 168, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                            .addComponent(add_afp, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel9)
                                            .addGap(18, 18, 18))
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addComponent(add_ccf, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel24)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cb_obra, 0, 167, Short.MAX_VALUE)
                                        .addComponent(cb_arl, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(f_exp, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(228, 228, 228)))
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(add_arl, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(add_barrio1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(22, 22, 22)
                                .addComponent(tel_acud))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(mail_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addGap(26, 26, 26)
                        .addComponent(acud, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cb_par, 0, 136, Short.MAX_VALUE)
                            .addComponent(cb_mun, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(add_mun, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(add_par, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(salario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(add_afp)
                    .addComponent(jLabel9)
                    .addComponent(add_arl)
                    .addComponent(cb_afp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_arl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(add_eps, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(add_ccf)
                    .addComponent(cb_eps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_ccf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_barrio1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_obra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel11)
                    .addComponent(f_nac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(f_exp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(dir_emp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel16)
                    .addComponent(tel_emp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel10)
                    .addComponent(add_mun, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_mun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(barrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel17)
                    .addComponent(mail_emp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(acud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(add_par, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_par, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(jLabel20))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(tel_acud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton14)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        jScrollPane3.setViewportView(jPanel4);

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

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        int id_tip=0;
        int id_eps=0;
        int id_afp=0;
        int id_arl=0;
        int id_ccf=0;
        int id_obra=0;
        int id_barrio=0;
        String id_municipio="";
        int id_par_acud=0;
        String arl = "";
        String ccf = "";
        if (tipo_nov.getSelectedItem().toString().equals("INGRESO")) {
            if (f_ing_nov.getDate()!=null) {
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
//                                                        if (f_nac.getDate()!=null) {
//                                                            if (f_exp.getDate()!=null) {
                                                                if (!dir_emp.getText().equals("")) {
                                                                    if (!tel_emp.getText().equals("")) {
                                                                        if (!barrio.getText().equals("")) {
                                                                            if (!cb_mun.getSelectedItem().equals("Seleccione..")) {
                                                                                if (mail_emp.getText().contains("@") && (mail_emp.getText().contains(".com")| mail_emp.getText().contains(".COM"))) {
                                                                                    if (!acud.getText().equals("")) {
                                                                                        if (!cb_par.getSelectedItem().equals("Seleccione..")) {
                                                                                            if (!tel_acud.getText().equals("")) {
                                                                                                Conexion con = new Conexion();
                                                                                                con.conexion();
                                                                                                ResultSet r;
                                                                                                try {
                                                                                                    r = con.s.executeQuery ("SELECT * FROM `t_eps` WHERE (NOMBRE_EPS ='"+cb_eps.getSelectedItem().toString()+"');");
                                                                                                    if(r.next()){
                                                                                                        id_eps=r.getInt("ID_EPS");    
                                                                                                    }
                                                                                                    r = con.s.executeQuery ("SELECT * FROM `t_afp` WHERE (NOMBRE_AFP ='"+cb_afp.getSelectedItem().toString()+"');");
                                                                                                    if(r.next()){
                                                                                                        id_afp=r.getInt("ID_AFP");    
                                                                                                    }
                                                                                                    r = con.s.executeQuery ("SELECT * FROM `t_arl` WHERE (NOMBRE_ARL ='"+cb_arl.getSelectedItem().toString()+"');");
                                                                                                    if(r.next()){
                                                                                                        id_arl=r.getInt("ID_ARL");    
                                                                                                    }
                                                                                                    r = con.s.executeQuery ("SELECT * FROM `t_ccf` WHERE (NOMBRE_CCF ='"+cb_ccf.getSelectedItem().toString()+"');");
                                                                                                    if(r.next()){
                                                                                                        id_ccf=r.getInt("ID_CCF");    
                                                                                                    }
                                                                                                    r = con.s.executeQuery ("SELECT * FROM `t_obra` WHERE (NOMBRE_OBRA ='"+cb_obra.getSelectedItem().toString()+"');");
                                                                                                    if(r.next()){
                                                                                                        id_obra=r.getInt("ID_OBRA");    
                                                                                                    }
                                                                                                    id_municipio = get_id_municipio(cb_mun.getSelectedItem());
                                                                                                    r = con.s.executeQuery ("SELECT * FROM `t_parentesco` WHERE (NOMBRE_PAR ='"+cb_par.getSelectedItem().toString()+"');");
                                                                                                    if(r.next()){
                                                                                                        id_par_acud=r.getInt("ID_PAR");    
                                                                                                    }
                                                                                                    String aux_f_nac = "";
                                                                                                    String aux_f_exp = "";
                                                                                                    if (f_nac.getDate()==null) {
                                                                                                        aux_f_nac = "1900-01-01";
                                                                                                    }else{
                                                                                                        aux_f_nac = new SimpleDateFormat("yyyy-MM-dd").format(f_nac.getDate());
                                                                                                    }
                                                                                                    if (f_exp.getDate()==null) {
                                                                                                        aux_f_exp = "1900-01-01";
                                                                                                    }else{
                                                                                                        aux_f_exp = new SimpleDateFormat("yyyy-MM-dd").format(f_exp.getDate());
                                                                                                    }
                                                                                                    int conf = JOptionPane.showConfirmDialog(this,"Esta seguro que desea continuar?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                                                                                                    if (conf == JOptionPane.YES_OPTION) {
                                                                                                        con.s.executeUpdate("UPDATE `t_novedades` SET `ID_EMPLEADO`="+Busc_cedula.getText()+",`ID_EMPRESA`='"+Busc_nit.getText()+"',`FECHA_INGRESO`='"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"',`FECHA_RETIRO`='1900-01-01',`SALARIO_NOVEDAD`="+salario.getText()+",`ID_EPS`="+id_eps+",`ID_AFP`="+id_afp+",`ARL_NOV`='"+cb_arl.getSelectedItem()+"',`CCF_NOV`='"+cb_ccf.getSelectedItem()+"',`F_NAC_NOV`='"+aux_f_nac+"',`F_EXP_NOV`='"+aux_f_exp+"',`DIR_EMP_NOV`='"+dir_emp.getText()+"',`BARRIO_NOV`='"+barrio.getText()+"',`ID_MUN_NOV`="+id_municipio+",`TEL_NOV`='"+tel_emp.getText()+"',`MAIL_NOV`='"+mail_emp.getText()+"',`ACUD_NOV`='"+acud.getText()+"',`ID_PAR_ACU_NOV`="+id_par_acud+",`TEL_ACUD_NOV`='"+tel_acud.getText()+"',`OBS_NOV`='"+obs.getText().toUpperCase()+"',`ID_OBRA`="+id_obra+",`ID_TIPO`=1, `F_REGISTRO`='"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"' WHERE ID_EMPLEADO="+ced_edit+" AND ID_EMPRESA='"+nit_edit+"' AND FECHA_INGRESO='"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_edit)+"' AND FECHA_RETIRO='"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_edit)+"' AND ID_TIPO="+id_tip_edit);
                                                                                                        if (id_tip_edit!=1) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',1,'MODIFICADO A INGRESO','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (f_ing_edit.compareTo(f_ing_nov.getDate())!=0) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',1,'MODIFICA FECHA INGRESO','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (!nit_edit.equals(Busc_nit.getText())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',1,'MODIFICA NIT EMPRESA DE "+nit_edit+" A "+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (!ced_edit.equals(Busc_cedula.getText())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',1,'MODIFICA EMPLEADO DE "+ced_edit+" A "+Busc_cedula.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (salario_edit!=Float.parseFloat(salario.getText())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',1,'MODIFICA SALARIO DE "+salario_edit+" A "+salario.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (!afp_edit.equals(cb_afp.getSelectedItem().toString())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',1,'MODIFICA AFP DE "+afp_edit+" A "+cb_afp.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (!arl_edit.equals(cb_arl.getSelectedItem().toString())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',1,'MODIFICA ARL DE "+arl_edit+" A "+cb_arl.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (!eps_edit.equals(cb_eps.getSelectedItem().toString())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',1,'MODIFICA EPS DE "+eps_edit+" A "+cb_eps.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (!ccf_edit.equals(cb_ccf.getSelectedItem().toString())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',1,'MODIFICA CCF DE "+ccf_edit+" A "+cb_ccf.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (!obra_edit.equals(cb_obra.getSelectedItem().toString())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',1,'MODIFICA OBRA DE "+obra_edit+" A "+cb_obra.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (!obs_edit.equals(obs.getText())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',1,'MODIFICA OBSERVACION A "+obs.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        JOptionPane.showMessageDialog(this,"Novedad de ingreso editada correctamente.","Información",JOptionPane.INFORMATION_MESSAGE);
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
                                                                                            } else {
                                                                                                JOptionPane.showMessageDialog(this,"Ingrese el telefono del acudiente del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                                            }
                                                                                        } else {
                                                                                            JOptionPane.showMessageDialog(this,"Ingrese el parentesco del acudiente del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                                        }
                                                                                    } else {
                                                                                        JOptionPane.showMessageDialog(this,"Ingrese el acudiente del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                                    }
                                                                                } else {
                                                                                    JOptionPane.showMessageDialog(this,"Ingrese un email valido para el empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                                }
                                                                            } else {
                                                                                JOptionPane.showMessageDialog(this,"Ingrese el municipio del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                            }
                                                                        } else {
                                                                            JOptionPane.showMessageDialog(this,"Ingrese el barrio del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                        }
                                                                    } else {
                                                                        JOptionPane.showMessageDialog(this,"Ingrese el telefono del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                } else {
                                                                    JOptionPane.showMessageDialog(this,"Ingrese la dirección del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                }
//                                                            } else {
//                                                                JOptionPane.showMessageDialog(this,"Ingrese la fecha de expedicion de la cedula del empleado.","Error",JOptionPane.ERROR_MESSAGE);
//                                                            }
//                                                        } else {
//                                                            JOptionPane.showMessageDialog(this,"Ingrese la fecha de nacimiento del empleado.","Error",JOptionPane.ERROR_MESSAGE);
//                                                        }
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
        if (tipo_nov.getSelectedItem().toString().equals("RETIRO")) {
            if (f_ing_nov.getDate()!=null) {    
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
//                                                            if (f_nac.getDate()!=null) {
//                                                                if (f_exp.getDate()!=null) {
                                                                    if (!dir_emp.getText().equals("")) {
                                                                        if (!tel_emp.getText().equals("")) {
                                                                            if (!barrio.getText().equals("")) {
                                                                                if (!cb_mun.getSelectedItem().equals("Seleccione..")) {
                                                                                    if (mail_emp.getText().contains("@") && (mail_emp.getText().contains(".com")|mail_emp.getText().contains(".COM"))) {
                                                                                        if (!acud.getText().equals("")) {
                                                                                            if (!cb_par.getSelectedItem().equals("Seleccione..")) {
                                                                                                if (!tel_acud.getText().equals("")) {
                                                                                                    if (f_ret_nov.getDate().compareTo(f_ing_nov.getDate())>=0 & f_ret_nov.getDate().compareTo(new Date())<0) {
                                                                                                        Conexion con = new Conexion();
                                                                                                        con.conexion();
                                                                                                        ResultSet r;
                                                                                                        try {
                                                                                                            r = con.s.executeQuery ("SELECT * FROM `t_eps` WHERE (NOMBRE_EPS ='"+cb_eps.getSelectedItem().toString()+"');");
                                                                                                            if(r.next()){
                                                                                                                id_eps=r.getInt("ID_EPS");    
                                                                                                            }
                                                                                                            r = con.s.executeQuery ("SELECT * FROM `t_afp` WHERE (NOMBRE_AFP ='"+cb_afp.getSelectedItem().toString()+"');");
                                                                                                            if(r.next()){
                                                                                                                id_afp=r.getInt("ID_AFP");    
                                                                                                            }
                                                                                                            r = con.s.executeQuery ("SELECT * FROM `t_arl` WHERE (NOMBRE_ARL ='"+cb_arl.getSelectedItem().toString()+"');");
                                                                                                            if(r.next()){
                                                                                                                id_arl=r.getInt("ID_ARL");    
                                                                                                            }
                                                                                                            r = con.s.executeQuery ("SELECT * FROM `t_ccf` WHERE (NOMBRE_CCF ='"+cb_ccf.getSelectedItem().toString()+"');");
                                                                                                            if(r.next()){
                                                                                                                id_ccf=r.getInt("ID_CCF");    
                                                                                                            }
                                                                                                            r = con.s.executeQuery ("SELECT * FROM `t_obra` WHERE (NOMBRE_OBRA ='"+cb_obra.getSelectedItem().toString()+"');");
                                                                                                            if(r.next()){
                                                                                                                id_obra=r.getInt("ID_OBRA");    
                                                                                                            }
                                                                                                            id_municipio = get_id_municipio(cb_mun.getSelectedItem());
                                                                                                            r = con.s.executeQuery ("SELECT * FROM `t_parentesco` WHERE (NOMBRE_PAR ='"+cb_par.getSelectedItem().toString()+"');");
                                                                                                            if(r.next()){
                                                                                                                id_par_acud=r.getInt("ID_PAR");    
                                                                                                            }
                                                                                                            String aux_f_nac = "";
                                                                                                            String aux_f_exp = "";
                                                                                                            if (f_nac.getDate()==null) {
                                                                                                                aux_f_nac = "1900-01-01";
                                                                                                            }else{
                                                                                                                aux_f_nac = new SimpleDateFormat("yyyy-MM-dd").format(f_nac.getDate());
                                                                                                            }
                                                                                                            if (f_exp.getDate()==null) {
                                                                                                                aux_f_exp = "1900-01-01";
                                                                                                            }else{
                                                                                                                aux_f_exp = new SimpleDateFormat("yyyy-MM-dd").format(f_exp.getDate());
                                                                                                            }
                                                                                                            int conf = JOptionPane.showConfirmDialog(this,"Esta seguro que desea continuar?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                                                                                                            if (conf == JOptionPane.YES_OPTION) {
                                                                                                                //System.out.println("UPDATE `t_novedades` SET `ID_EMPLEADO`="+Busc_cedula.getText()+",`ID_EMPRESA`='"+Busc_nit.getText()+"',`FECHA_INGRESO`='"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"',`FECHA_RETIRO`='"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',`SALARIO_NOVEDAD`="+salario.getText()+",`ID_EPS`="+id_eps+",`ID_AFP`="+id_afp+",`ID_ARL`="+id_arl+",`ID_CCF`="+id_ccf+",`F_NAC_NOV`='"+new SimpleDateFormat("yyyy-MM-dd").format(f_nac.getDate())+"',`F_EXP_NOV`='"+new SimpleDateFormat("yyyy-MM-dd").format(f_exp.getDate())+"',`DIR_EMP_NOV`='"+dir_emp.getText()+"',`ID_BARRIO_NOV`="+id_barrio+",`ID_MUN_NOV`="+id_municipio+",`TEL_NOV`='"+tel_emp.getText()+"',`MAIL_NOV`='"+mail_emp.getText()+"',`ACUD_NOV`='"+acud.getText()+"',`ID_PAR_ACU_NOV`="+id_par_acud+",`TEL_ACUD_NOV`='"+tel_acud.getText()+"',`OBS_NOV`='"+obs.getText().toUpperCase()+"',`ID_OBRA`="+id_obra+",`ID_TIPO`=2 WHERE ID_EMPLEADO="+ced_edit+" AND ID_EMPRESA='"+nit_edit+"' AND FECHA_INGRESO='"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(f_ing_edit))+"'AND FECHA_RETIRO='"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(f_ret_edit))+"' AND ID_TIPO="+id_tip_edit);
                                                                                                                con.s.executeUpdate("UPDATE `t_novedades` SET `ID_EMPLEADO`="+Busc_cedula.getText()+",`ID_EMPRESA`='"+Busc_nit.getText()+"',`FECHA_INGRESO`='"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"',`FECHA_RETIRO`='"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',`SALARIO_NOVEDAD`="+salario.getText()+",`ID_EPS`="+id_eps+",`ID_AFP`="+id_afp+",`ARL_NOV`='"+cb_arl.getSelectedItem()+"',`CCF_NOV`='"+cb_ccf.getSelectedItem()+"',`F_NAC_NOV`='"+aux_f_nac+"',`F_EXP_NOV`='"+aux_f_exp+"',`DIR_EMP_NOV`='"+dir_emp.getText()+"',`BARRIO_NOV`='"+barrio.getText()+"',`ID_MUN_NOV`="+id_municipio+",`TEL_NOV`='"+tel_emp.getText()+"',`MAIL_NOV`='"+mail_emp.getText()+"',`ACUD_NOV`='"+acud.getText()+"',`ID_PAR_ACU_NOV`="+id_par_acud+",`TEL_ACUD_NOV`='"+tel_acud.getText()+"',`OBS_NOV`='"+obs.getText().toUpperCase()+"',`ID_OBRA`="+id_obra+",`ID_TIPO`=2, `F_REGISTRO`='"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"' WHERE ID_EMPLEADO="+ced_edit+" AND ID_EMPRESA='"+nit_edit+"' AND FECHA_INGRESO='"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_edit)+"'AND FECHA_RETIRO='"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_edit)+"' AND ID_TIPO="+id_tip_edit);
                                                                                                                if (id_tip_edit!=2) {
                                                                                                                    con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',2,'MODIFICADO A RETIRO','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"','"+Main.id_usuario+"')");
                                                                                                                }
                                                                                                                if (f_ing_edit.compareTo(f_ing_nov.getDate())!=0) {
                                                                                                                    con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',1,'MODIFICA FECHA INGRESO','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+Main.id_usuario+"')");
                                                                                                                }
                                                                                                                if (f_ret_edit.compareTo(f_ret_nov.getDate())!=0) {
                                                                                                                    con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',2,'MODIFICA FECHA RETIRO','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"','"+Main.id_usuario+"')");
                                                                                                                }
                                                                                                                /****
                                                                                                                 * 
                                                                                                                 */
                                                                                                                if (!nit_edit.equals(Busc_nit.getText())) {
                                                                                                                    con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',2,'MODIFICA NIT EMPRESA DE "+nit_edit+" A "+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                                }
                                                                                                                if (!ced_edit.equals(Busc_cedula.getText())) {
                                                                                                                    con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',2,'MODIFICA EMPLEADO DE "+ced_edit+" A "+Busc_cedula.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                                }
                                                                                                                if (salario_edit!=Float.parseFloat(salario.getText())) {
                                                                                                                    con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',2,'MODIFICA SALARIO DE "+salario_edit+" A "+salario.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                                }
                                                                                                                if (!afp_edit.equals(cb_afp.getSelectedItem().toString())) {
                                                                                                                    con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',2,'MODIFICA AFP DE "+afp_edit+" A "+cb_afp.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                                }
                                                                                                                if (!arl_edit.equals(cb_arl.getSelectedItem().toString())) {
                                                                                                                    con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',2,'MODIFICA ARL DE "+arl_edit+" A "+cb_arl.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                                }
                                                                                                                if (!eps_edit.equals(cb_eps.getSelectedItem().toString())) {
                                                                                                                    con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',2,'MODIFICA EPS DE "+eps_edit+" A "+cb_eps.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                                }
                                                                                                                if (!ccf_edit.equals(cb_ccf.getSelectedItem().toString())) {
                                                                                                                    con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',2,'MODIFICA CCF DE "+ccf_edit+" A "+cb_ccf.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                                }
                                                                                                                if (!obra_edit.equals(cb_obra.getSelectedItem().toString())) {
                                                                                                                    con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',2,'MODIFICA OBRA DE "+obra_edit+" A "+cb_obra.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                                }
                                                                                                                if (!obs_edit.equals(obs.getText())) {
                                                                                                                    con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_nov.getDate())+"',2,'MODIFICA OBSERVACION A "+obs.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
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
                                                                                                    } else {
                                                                                                        JOptionPane.showMessageDialog(this,"Ingrese una fecha de Retiro valida.","Error",JOptionPane.ERROR_MESSAGE);
                                                                                                    }
                                                                                                } else {
                                                                                                    JOptionPane.showMessageDialog(this,"Ingrese el telefono del acudiente del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                                                }
                                                                                            } else {
                                                                                                JOptionPane.showMessageDialog(this,"Ingrese el parentesco del acudiente del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                                            }
                                                                                        } else {
                                                                                            JOptionPane.showMessageDialog(this,"Ingrese el acudiente del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                                        }
                                                                                    } else {
                                                                                        JOptionPane.showMessageDialog(this,"Ingrese un email valido para el empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                                    }
                                                                                } else {
                                                                                    JOptionPane.showMessageDialog(this,"Ingrese el municipio del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                                }
                                                                            } else {
                                                                                JOptionPane.showMessageDialog(this,"Ingrese el barrio del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                            }
                                                                        } else {
                                                                            JOptionPane.showMessageDialog(this,"Ingrese el telefono del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                        }
                                                                    } else {
                                                                        JOptionPane.showMessageDialog(this,"Ingrese la dirección del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                    }
//                                                                } else {
//                                                                    JOptionPane.showMessageDialog(this,"Ingrese la fecha de expedicion de la cedula del empleado.","Error",JOptionPane.ERROR_MESSAGE);
//                                                                }
//                                                            } else {
//                                                                JOptionPane.showMessageDialog(this,"Ingrese la fecha de nacimiento del empleado.","Error",JOptionPane.ERROR_MESSAGE);
//                                                            }
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
//                                                        if (f_nac.getDate()!=null) {
//                                                            if (f_exp.getDate()!=null) {
                                                                if (!dir_emp.getText().equals("")) {
                                                                    if (!tel_emp.getText().equals("")) {
                                                                        if (!barrio.getText().equals("")) {
                                                                            if (!cb_mun.getSelectedItem().equals("Seleccione..")) {
                                                                                if (mail_emp.getText().contains("@") && (mail_emp.getText().contains(".com")| mail_emp.getText().contains(".COM"))) {
                                                                                    if (!acud.getText().equals("")) {
                                                                                        if (!cb_par.getSelectedItem().equals("Seleccione..")) {
                                                                                            if (!tel_acud.getText().equals("")) {
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
                                                                                                    id_municipio = get_id_municipio(cb_mun.getSelectedItem());
                                                                                                    r = con.s.executeQuery ("SELECT * FROM `t_parentesco` WHERE (NOMBRE_PAR ='"+cb_par.getSelectedItem().toString()+"');");
                                                                                                    if(r.next()){
                                                                                                        id_par_acud=r.getInt("ID_PAR");    
                                                                                                    }
                                                                                                    String aux_f_nac = "";
                                                                                                    String aux_f_exp = "";
                                                                                                    if (f_nac.getDate()==null) {
                                                                                                        aux_f_nac = "1900-01-01";
                                                                                                    }else{
                                                                                                        aux_f_nac = new SimpleDateFormat("yyyy-MM-dd").format(f_nac.getDate());
                                                                                                    }
                                                                                                    if (f_exp.getDate()==null) {
                                                                                                        aux_f_exp = "1900-01-01";
                                                                                                    }else{
                                                                                                        aux_f_exp = new SimpleDateFormat("yyyy-MM-dd").format(f_exp.getDate());
                                                                                                    }
                                                                                                    int conf = JOptionPane.showConfirmDialog(this,"Esta seguro que desea continuar?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                                                                                                    if (conf == JOptionPane.YES_OPTION) {
                                                                                                        con.s.executeUpdate("UPDATE `t_novedades` SET `ID_EMPLEADO`="+Busc_cedula.getText()+",`ID_EMPRESA`='"+Busc_nit.getText()+"',`FECHA_INGRESO`='"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"',`FECHA_RETIRO`='1900-01-01',`SALARIO_NOVEDAD`="+salario.getText()+",`ID_EPS`=1,`ID_AFP`=1,`ARL_NOV`='"+arl+"',`CCF_NOV`="+cb_ccf.getSelectedItem()+",`F_NAC_NOV`='"+aux_f_nac+"',`F_EXP_NOV`='"+aux_f_exp+"',`DIR_EMP_NOV`='"+dir_emp.getText()+"',`BARRIO_NOV`='"+barrio.getText()+"',`ID_MUN_NOV`="+id_municipio+",`TEL_NOV`='"+tel_emp.getText()+"',`MAIL_NOV`='"+mail_emp.getText()+"',`ACUD_NOV`='"+acud.getText()+"',`ID_PAR_ACU_NOV`="+id_par_acud+",`TEL_ACUD_NOV`='"+tel_acud.getText()+"',`OBS_NOV`='"+obs.getText().toUpperCase()+"',`ID_OBRA`="+id_obra+",`ID_TIPO`=3, `F_REGISTRO`='"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"' WHERE ID_EMPLEADO="+ced_edit+" AND ID_EMPRESA='"+nit_edit+"' AND FECHA_INGRESO='"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_edit)+"' AND FECHA_RETIRO='"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_edit)+"' AND ID_TIPO="+id_tip_edit);
                                                                                                        if (id_tip_edit!=2) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',3,'MODIFICADO A PRE-INGRESO','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (f_ing_edit.compareTo(f_ing_nov.getDate())!=0) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',3,'MODIFICA FECHA INGRESO','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (!nit_edit.equals(Busc_nit.getText())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',3,'MODIFICA NIT EMPRESA DE "+nit_edit+" A "+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (!ced_edit.equals(Busc_cedula.getText())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',3,'MODIFICA EMPLEADO DE "+ced_edit+" A "+Busc_cedula.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (salario_edit!=Float.parseFloat(salario.getText())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',3,'MODIFICA SALARIO DE "+salario_edit+" A "+salario.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (!afp_edit.equals(cb_afp.getSelectedItem().toString())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',3,'MODIFICA AFP DE "+afp_edit+" A "+cb_afp.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (!arl_edit.equals(cb_arl.getSelectedItem().toString())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',3,'MODIFICA ARL DE "+arl_edit+" A "+cb_arl.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (!eps_edit.equals(cb_eps.getSelectedItem().toString())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',3,'MODIFICA EPS DE "+eps_edit+" A "+cb_eps.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (!ccf_edit.equals(cb_ccf.getSelectedItem().toString())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',3,'MODIFICA CCF DE "+ccf_edit+" A "+cb_ccf.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (!obra_edit.equals(cb_obra.getSelectedItem().toString())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',3,'MODIFICA OBRA DE "+obra_edit+" A "+cb_obra.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (!obs_edit.equals(obs.getText())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',3,'MODIFICA OBSERVACION A "+obs.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
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
                                                                                            } else {
                                                                                                JOptionPane.showMessageDialog(this,"Ingrese el telefono del acudiente del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                                            }
                                                                                        } else {
                                                                                            JOptionPane.showMessageDialog(this,"Ingrese el parentesco del acudiente del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                                        }
                                                                                    } else {
                                                                                        JOptionPane.showMessageDialog(this,"Ingrese el acudiente del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                                    }
                                                                                } else {
                                                                                    JOptionPane.showMessageDialog(this,"Ingrese un email valido para el empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                                }
                                                                            } else {
                                                                                JOptionPane.showMessageDialog(this,"Ingrese el municipio del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                            }
                                                                        } else {
                                                                            JOptionPane.showMessageDialog(this,"Ingrese el barrio del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                        }
                                                                    } else {
                                                                        JOptionPane.showMessageDialog(this,"Ingrese el telefono del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                } else {
                                                                    JOptionPane.showMessageDialog(this,"Ingrese la dirección del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                }
//                                                            } else {
//                                                                JOptionPane.showMessageDialog(this,"Ingrese la fecha de expedicion de la cedula del empleado.","Error",JOptionPane.ERROR_MESSAGE);
//                                                            }
//                                                        } else {
//                                                            JOptionPane.showMessageDialog(this,"Ingrese la fecha de nacimiento del empleado.","Error",JOptionPane.ERROR_MESSAGE);
//                                                        }
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
        if (tipo_nov.getSelectedItem().toString().equals("BLOQUEADO")) {
            if (f_ing_nov.getDate()!=null) {
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
//                                                        if (f_nac.getDate()!=null) {
//                                                            if (f_exp.getDate()!=null) {
                                                                if (!dir_emp.getText().equals("")) {
                                                                    if (!tel_emp.getText().equals("")) {
                                                                        if (!barrio.getText().equals("")) {
                                                                            if (!cb_mun.getSelectedItem().equals("Seleccione..")) {
                                                                                if (mail_emp.getText().contains("@") && (mail_emp.getText().contains(".com")| mail_emp.getText().contains(".COM"))) {
                                                                                    if (!acud.getText().equals("")) {
                                                                                        if (!cb_par.getSelectedItem().equals("Seleccione..")) {
                                                                                            if (!tel_acud.getText().equals("")) {
                                                                                                Conexion con = new Conexion();
                                                                                                con.conexion();
                                                                                                ResultSet r;
                                                                                                try {
                                                                                                    r = con.s.executeQuery ("SELECT * FROM `t_eps` WHERE (NOMBRE_EPS ='"+cb_eps.getSelectedItem().toString()+"');");
                                                                                                    if(r.next()){
                                                                                                        id_eps=r.getInt("ID_EPS");    
                                                                                                    }
                                                                                                    r = con.s.executeQuery ("SELECT * FROM `t_afp` WHERE (NOMBRE_AFP ='"+cb_afp.getSelectedItem().toString()+"');");
                                                                                                    if(r.next()){
                                                                                                        id_afp=r.getInt("ID_AFP");    
                                                                                                    }
                                                                                                    r = con.s.executeQuery ("SELECT * FROM `t_arl` WHERE (NOMBRE_ARL ='"+cb_arl.getSelectedItem().toString()+"');");
                                                                                                    if(r.next()){
                                                                                                        id_arl=r.getInt("ID_ARL");    
                                                                                                    }
                                                                                                    r = con.s.executeQuery ("SELECT * FROM `t_ccf` WHERE (NOMBRE_CCF ='"+cb_ccf.getSelectedItem().toString()+"');");
                                                                                                    if(r.next()){
                                                                                                        id_ccf=r.getInt("ID_CCF");    
                                                                                                    }
                                                                                                    r = con.s.executeQuery ("SELECT * FROM `t_obra` WHERE (NOMBRE_OBRA ='"+cb_obra.getSelectedItem().toString()+"');");
                                                                                                    if(r.next()){
                                                                                                        id_obra=r.getInt("ID_OBRA");    
                                                                                                    }
                                                                                                    id_municipio = get_id_municipio(cb_mun.getSelectedItem());
                                                                                                    r = con.s.executeQuery ("SELECT * FROM `t_parentesco` WHERE (NOMBRE_PAR ='"+cb_par.getSelectedItem().toString()+"');");
                                                                                                    if(r.next()){
                                                                                                        id_par_acud=r.getInt("ID_PAR");    
                                                                                                    }
                                                                                                    String aux_f_nac = "";
                                                                                                    String aux_f_exp = "";
                                                                                                    if (f_nac.getDate()==null) {
                                                                                                        aux_f_nac = "1900-01-01";
                                                                                                    }else{
                                                                                                        aux_f_nac = new SimpleDateFormat("yyyy-MM-dd").format(f_nac.getDate());
                                                                                                    }
                                                                                                    if (f_exp.getDate()==null) {
                                                                                                        aux_f_exp = "1900-01-01";
                                                                                                    }else{
                                                                                                        aux_f_exp = new SimpleDateFormat("yyyy-MM-dd").format(f_exp.getDate());
                                                                                                    }
                                                                                                    int conf = JOptionPane.showConfirmDialog(this,"Esta seguro que desea continuar?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                                                                                                    if (conf == JOptionPane.YES_OPTION) {
                                                                                                        con.s.executeUpdate("UPDATE `t_novedades` SET `ID_EMPLEADO`="+Busc_cedula.getText()+",`ID_EMPRESA`='"+Busc_nit.getText()+"',`FECHA_INGRESO`='"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"',`FECHA_RETIRO`='1900-01-01',`SALARIO_NOVEDAD`="+salario.getText()+",`ID_EPS`="+id_eps+",`ID_AFP`="+id_afp+",`ARL_NOV`='"+cb_arl.getSelectedItem()+"',`CCF_NOV`='"+cb_ccf.getSelectedItem()+"',`F_NAC_NOV`='"+aux_f_nac+"',`F_EXP_NOV`='"+aux_f_exp+"',`DIR_EMP_NOV`='"+dir_emp.getText()+"',`BARRIO_NOV`='"+barrio.getText()+"',`ID_MUN_NOV`="+id_municipio+",`TEL_NOV`='"+tel_emp.getText()+"',`MAIL_NOV`='"+mail_emp.getText()+"',`ACUD_NOV`='"+acud.getText()+"',`ID_PAR_ACU_NOV`="+id_par_acud+",`TEL_ACUD_NOV`='"+tel_acud.getText()+"',`OBS_NOV`='"+obs.getText().toUpperCase()+"',`ID_OBRA`="+id_obra+",`ID_TIPO`=4, `F_REGISTRO`='"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"' WHERE ID_EMPLEADO="+ced_edit+" AND ID_EMPRESA='"+nit_edit+"' AND FECHA_INGRESO='"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_edit)+"' AND FECHA_RETIRO='"+new SimpleDateFormat("yyyy-MM-dd").format(f_ret_edit)+"' AND ID_TIPO="+id_tip_edit);
                                                                                                        if (id_tip_edit!=4) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',4,'MODIFICADO A BLOQUEADO','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (f_ing_edit.compareTo(f_ing_nov.getDate())!=0) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',4,'MODIFICA FECHA INGRESO','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (!nit_edit.equals(Busc_nit.getText())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',4,'MODIFICA NIT EMPRESA DE "+nit_edit+" A "+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (!ced_edit.equals(Busc_cedula.getText())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',4,'MODIFICA EMPLEADO DE "+ced_edit+" A "+Busc_cedula.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (salario_edit!=Float.parseFloat(salario.getText())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',4,'MODIFICA SALARIO DE "+salario_edit+" A "+salario.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (!afp_edit.equals(cb_afp.getSelectedItem().toString())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',4,'MODIFICA AFP DE "+afp_edit+" A "+cb_afp.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (!arl_edit.equals(cb_arl.getSelectedItem().toString())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',4,'MODIFICA ARL DE "+arl_edit+" A "+cb_arl.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (!eps_edit.equals(cb_eps.getSelectedItem().toString())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',4,'MODIFICA EPS DE "+eps_edit+" A "+cb_eps.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (!ccf_edit.equals(cb_ccf.getSelectedItem().toString())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',4,'MODIFICA CCF DE "+ccf_edit+" A "+cb_ccf.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (!obra_edit.equals(cb_obra.getSelectedItem().toString())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',4,'MODIFICA OBRA DE "+obra_edit+" A "+cb_obra.getSelectedItem().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        if (!obs_edit.equals(obs.getText())) {
                                                                                                            con.s.executeUpdate("INSERT INTO `t_registro` (ID_EMPLEADO,ID_EMPRESA,F_INGRESO,F_RETIRO,ID_TIPO, REGISTRO, F_REGISTRO, FECHA, ID_USUARIO) VALUES ("+Busc_cedula.getText()+",'"+Busc_nit.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(f_ing_nov.getDate())+"','1900-01-01',4,'MODIFICA OBSERVACION A "+obs.getText()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+Main.id_usuario+"')");
                                                                                                        }
                                                                                                        JOptionPane.showMessageDialog(this,"Novedad de bloqueo editada correctamente.","Información",JOptionPane.INFORMATION_MESSAGE);
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
                                                                                            } else {
                                                                                                JOptionPane.showMessageDialog(this,"Ingrese el telefono del acudiente del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                                            }
                                                                                        } else {
                                                                                            JOptionPane.showMessageDialog(this,"Ingrese el parentesco del acudiente del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                                        }
                                                                                    } else {
                                                                                        JOptionPane.showMessageDialog(this,"Ingrese el acudiente del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                                    }
                                                                                } else {
                                                                                    JOptionPane.showMessageDialog(this,"Ingrese un email valido para el empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                                }
                                                                            } else {
                                                                                JOptionPane.showMessageDialog(this,"Ingrese el municipio del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                            }
                                                                        } else {
                                                                            JOptionPane.showMessageDialog(this,"Ingrese el barrio del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                        }
                                                                    } else {
                                                                        JOptionPane.showMessageDialog(this,"Ingrese el telefono del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                } else {
                                                                    JOptionPane.showMessageDialog(this,"Ingrese la dirección del empleado.","Error",JOptionPane.ERROR_MESSAGE);
                                                                }
//                                                            } else {
//                                                                JOptionPane.showMessageDialog(this,"Ingrese la fecha de expedicion de la cedula del empleado.","Error",JOptionPane.ERROR_MESSAGE);
//                                                            }
//                                                        } else {
//                                                            JOptionPane.showMessageDialog(this,"Ingrese la fecha de nacimiento del empleado.","Error",JOptionPane.ERROR_MESSAGE);
//                                                        }
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

    private void add_parActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_parActionPerformed
        // TODO add your handling code here:
        Add_Parentesco add_par = new Add_Parentesco(this, true);
        add_par.setLocationRelativeTo(this);
        add_par.setVisible(true);
    }//GEN-LAST:event_add_parActionPerformed

    private void add_munActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_munActionPerformed
        // TODO add your handling code here:
        Add_Municipio add_mun = new Add_Municipio(this, true);
        add_mun.setLocationRelativeTo(this);
        add_mun.setVisible(true);
    }//GEN-LAST:event_add_munActionPerformed

    private void add_epsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_epsActionPerformed
        // TODO add your handling code here:
        Add_EPS add_eps = new Add_EPS(this, true);
        add_eps.setLocationRelativeTo(this);
        add_eps.setVisible(true);
    }//GEN-LAST:event_add_epsActionPerformed

    private void tel_acudMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tel_acudMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2 & block) {
            tel_acud.setEditable(true);
        }
    }//GEN-LAST:event_tel_acudMouseClicked

    private void tel_acudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tel_acudFocusLost
        // TODO add your handling code here:
        if (!tel_acud.getText().equals("")) {
            tel_acud.setEditable(false);
        }
    }//GEN-LAST:event_tel_acudFocusLost

    private void acudMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acudMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2 & block) {
            acud.setEditable(true);
        }
    }//GEN-LAST:event_acudMouseClicked

    private void acudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_acudFocusLost
        // TODO add your handling code here:
        if (!acud.getText().equals("")) {
            acud.setEditable(false);
        }
    }//GEN-LAST:event_acudFocusLost

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

    private void tel_empMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tel_empMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2 & block) {
            tel_emp.setEditable(true);
        }
    }//GEN-LAST:event_tel_empMouseClicked

    private void tel_empFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tel_empFocusLost
        // TODO add your handling code here:
        if (!tel_emp.getText().equals("")) {
            tel_emp.setEditable(false);
        }
    }//GEN-LAST:event_tel_empFocusLost

    private void dir_empMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dir_empMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2 & block) {
            dir_emp.setEditable(true);
        }
    }//GEN-LAST:event_dir_empMouseClicked

    private void dir_empFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dir_empFocusLost
        // TODO add your handling code here:
        if (!dir_emp.getText().equals("")) {
            dir_emp.setEditable(false);
        }
    }//GEN-LAST:event_dir_empFocusLost

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
        if (tipo_nov.getSelectedItem().equals("INGRESO")) {
            f_ret_nov.setDate(null);
            f_ret_nov.setEnabled(false);
            f_ing_nov.setEnabled(true);
            cb_eps.setEnabled(true);
            cb_afp.setEnabled(true);
        }else if (tipo_nov.getSelectedItem().equals("RETIRO")) {
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
        }else if (tipo_nov.getSelectedItem().equals("BLOQUEADO")) {
            if (this.id_tip_edit==1) {
                f_ret_nov.setEnabled(false);
                f_ing_nov.setEnabled(false);
                cb_eps.setEnabled(false);
                cb_afp.setEnabled(false);
            }else{
                tipo_nov.setSelectedIndex(this.id_tip_edit-1);
                JOptionPane.showMessageDialog(this,"Solo puede modificar una novedad a BLOQUEADA si esta actualmente ACTIVO","Error",JOptionPane.ERROR_MESSAGE);
            }
            
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
    private javax.swing.JTextField acud;
    private javax.swing.JButton add_afp;
    private javax.swing.JButton add_arl;
    private javax.swing.JButton add_barrio1;
    private javax.swing.JButton add_ccf;
    private javax.swing.JButton add_emp;
    private javax.swing.JButton add_empresa;
    private javax.swing.JButton add_eps;
    private javax.swing.JButton add_mun;
    private javax.swing.JButton add_par;
    private javax.swing.JTextField ap_emp;
    private javax.swing.JTextField barrio;
    private javax.swing.JComboBox<String> cb_afp;
    private javax.swing.JComboBox<String> cb_arl;
    private javax.swing.JComboBox<String> cb_ccf;
    private javax.swing.JComboBox<String> cb_eps;
    private javax.swing.JComboBox<String> cb_mun;
    private javax.swing.JComboBox<String> cb_obra;
    private javax.swing.JComboBox<String> cb_par;
    private javax.swing.JTextField dir_emp;
    private javax.swing.JButton edit_emp;
    private javax.swing.JButton edit_empresa;
    private com.toedter.calendar.JDateChooser f_exp;
    private com.toedter.calendar.JDateChooser f_ing_nov;
    private com.toedter.calendar.JDateChooser f_nac;
    private com.toedter.calendar.JDateChooser f_ret_nov;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField mail_emp;
    private javax.swing.JTextField nom_empl;
    private javax.swing.JTextField nomb_emp;
    private javax.swing.JTextArea obs;
    private javax.swing.JTextField salario;
    private javax.swing.JTextField tel_acud;
    private javax.swing.JTextField tel_emp;
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

public void ac_mun(){
    cb_mun.removeAllItems();
    cb_mun.addItem("Seleccione..");
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
            String str=r.getString("NOMBRE_MUN")+"-"+r.getString("NOMBRE_DEP");
            cb_mun.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }

}
public void ac_par(){
    cb_par.removeAllItems();
    cb_par.addItem("Seleccione..");
    Conexion con = new Conexion();
    con.conexion();
    ResultSet r;
    try{
        r = con.s.executeQuery ("SELECT * FROM T_PARENTESCO ORDER BY NOMBRE_PAR ASC;");
        while(r.next()){
            String str=r.getString("NOMBRE_PAR");
            cb_par.addItem(str);
        }
        con.cerrar();
    }catch(SQLException j){
        con.cerrar();
        JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
    }

}
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
    f_nac.setDate(null);
    f_exp.setDate(null);
    dir_emp.setText("");
    tel_emp.setText("");
    barrio.setText("");
    cb_mun.setSelectedItem("Seleccione..");
    mail_emp.setText("");
    acud.setText("");
    cb_par.setSelectedItem("Seleccione..");
    tel_acud.setText("");
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
    barrio.setEnabled(true);
    add_ccf.setEnabled(true);
    add_emp.setEnabled(true);
    add_empresa.setEnabled(true);
    add_eps.setEnabled(true);
    add_mun.setEnabled(true);
    add_par.setEnabled(true);
    edit_emp.setEnabled(true);
    edit_empresa.setEnabled(true);
    f_nac.setEnabled(true);
    f_exp.setEnabled(true);
    dir_emp.setEditable(true);
    tel_emp.setEditable(true);
    cb_mun.setEnabled(true);
    mail_emp.setEditable(true);
    acud.setEditable(true);
    cb_par.setEnabled(true);
    tel_acud.setEditable(true);
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
    barrio.setEnabled(false);
    add_ccf.setEnabled(false);
    add_emp.setEnabled(false);
    add_empresa.setEnabled(false);
    add_eps.setEnabled(false);
    add_mun.setEnabled(false);
    add_par.setEnabled(false);
    edit_emp.setEnabled(false);
    edit_empresa.setEnabled(false);
    f_nac.setEnabled(false);
    f_exp.setEnabled(false);
    dir_emp.setEditable(false);
    tel_emp.setEditable(false);
    cb_mun.setEnabled(false);
    mail_emp.setEditable(false);
    acud.setEditable(false);
    cb_par.setEnabled(false);
    tel_acud.setEditable(false);
    obs.setEditable(false);
    Busc_cedula.requestFocus();

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
            "  INNER JOIN t_parentesco\n" +
            "    ON (t_novedades.ID_PAR_ACU_NOV = t_parentesco.ID_PAR)\n" +
            "  INNER JOIN t_obra\n" +
            "    ON (t_novedades.ID_OBRA = t_obra.ID_OBRA)\n" +
            "  INNER JOIN t_municipios \n" +
            "    ON (t_novedades.ID_MUN_NOV = t_municipios.ID_MUN)"+    
            "  INNER JOIN t_tipo_novedad\n" +
            "    ON (t_novedades.ID_TIPO = t_tipo_novedad.ID_TIPO)\n" +
            "  INNER JOIN t_departamentos\n" +
            "    ON (t_municipios.ID_DEP = t_departamentos.ID_DEP)\n"
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
            
            if (r.getString("F_NAC_NOV").equals("1900-01-01")) {
                f_nac.setDate(null);
            } else {
                f_nac.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(r.getString("F_NAC_NOV")));
            }
            
            if (r.getString("F_EXP_NOV").equals("1900-01-01")) {
                f_exp.setDate(null);
            } else {
                f_exp.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(r.getString("F_EXP_NOV")));
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
            dir_emp.setText(r.getString("DIR_EMP_NOV"));
            tel_emp.setText(r.getString("TEL_NOV"));
            barrio.setText(r.getString("BARRIO_NOV"));
            cb_mun.setSelectedItem(r.getString("NOMBRE_MUN")+"-"+r.getString("NOMBRE_DEP"));
            mail_emp.setText(r.getString("MAIL_NOV"));
            acud.setText(r.getString("ACUD_NOV"));
            cb_par.setSelectedItem(r.getString("NOMBRE_PAR"));
            tel_acud.setText(r.getString("TEL_ACUD_NOV"));
            obs.setText(r.getString("OBS_NOV"));
            this.obs_edit=obs.getText();
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
