/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.GUI;

import com.jm.Edicof.Clases.HiloUpdate;
import com.jm.Edicof.Clases.Importar;
import com.jm.Edicof.Clases.Programacion;
import com.jm.Edicof.Encriptar.Encriptar;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author ADMIN
 */

public class Main extends javax.swing.JFrame {
//private final static Logger LOGGER = Logger.getLogger(Main.class.getName());
public static File archivo = null;
Image icon;
TrayIcon t_icon = null;
SystemTray s_tray;
public static String ruta_file_option=new File(".")+"/conf.ini";
public static String ruta_program=new File(".")+"/task.ini";
public static int num_conection;
public static String host;
public static String bd;
public static String usu;
public static String cont;
public static boolean enable_prog;
public static int hh_pro;
public static int mm_pro;
public static String ruta_sql_bin;
public static String ruta_backup;
public static boolean enable_task;
public static String tipo_estacion;
static FileReader fr = null;
static BufferedReader br = null;
public static String id_usuario = null;
public static boolean time_login = true;
static boolean mainperm = false;
public static Programacion tarea=null ;
public static Dimension size =null;
public int version;
//public static String ver_str="";
public static String ver_str="3.0.4.7";
public String url_info_update = "https://onedrive.live.com/download?cid=51049062522CB054&resid=51049062522CB054%21219574&authkey=AP_5XduLgOsOxQg";//Ruta del archivo que contiene la informacion de la ultima version del software
//public String url_permission = "https://www.dropbox.com/s/c9bbt0blodjnrrs/PERMISOS.txt?dl=1";//Ruta del archivo que contiene la informacion de la ultima version del software
public static ArrayList conection_list = new ArrayList();
Model model;
    /**
     * Creates new form Main
     */

    public Main() {
//        try{
//            MavenXpp3Reader reader = new MavenXpp3Reader();
//            model = reader.read(new FileReader("pom.xml"));
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        ver_str = getClass().getPackage().getImplementationVersion();
//        System.out.println("VERS: "+ver_str);
//        //ver_str = model.getVersion();
        version = Integer.parseInt(ver_str.replace(".",""));
        if (check_update(url_info_update)) {
            int conf = JOptionPane.showConfirmDialog(this,"Hay una nueva version del software disponible\n Desea actualizar?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (conf == JOptionPane.YES_OPTION) {
                System.out.println("Url instalador: "+get_url_update(url_info_update));
                System.out.println("Version: "+get_ver_update(url_info_update));
                HiloUpdate h_up = new HiloUpdate(get_url_update(url_info_update),get_ver_update(url_info_update));
                h_up.start();
                System.exit(0);
            }else{
                init_aplication();
            }
        }else{
            init_aplication();
        }
    }
    public void init_aplication(){//if (getpermission(url_permission)) {
        if(get_conection()){//Get_file_config(this)
            ////////**************VERIFICA LA EXISTENCIA DEL ARCHIVO TASK.INI SI NO EXISTE LO CREA****************////////////////
            File archivo_task=new File(Main.ruta_program);
            if(!archivo_task.exists()){
                System.out.println("Archivo task no existe");
                try {
                    FileWriter fwo = new FileWriter(archivo_task);
                    PrintWriter pwo = new PrintWriter(fwo);
                    String[] par;
                    String aux;
                    String temp;
                    File archiv = new File(Main.ruta_file_option);
                    if(archiv.exists()){
                        try{
                            System.out.println("Leyendo archivo config");
                            FileReader fro = new FileReader (archiv);
                            BufferedReader bro = new BufferedReader(fro);
                            if((aux=bro.readLine())!=null){
                                temp = Encriptar.Desencriptar(aux);
                                par=temp.split("\\*");
                                System.out.println("Escribiendo archivo task");
                                pwo.println( Encriptar.Encriptar(par[4]+"*"+par[5]+"*"+par[6]+"*"+par[7]+"*"+par[8]+"*"+par[9]+"*"+par[10]));
                                archivo_task.setReadable(true);
                                archivo_task.setWritable(true);
                                fwo.close();
                                pwo.close();
                            }
                            bro.close();
                            fro.close();
                            ArrayList c = new ArrayList();
                            FileReader fro1 = new FileReader (archiv);
                            BufferedReader bro1 = new BufferedReader(fro1);
                            //System.out.println("Leyendo 2 archivo config");
                            while((aux=bro1.readLine())!=null){
                                temp = Encriptar.Desencriptar(aux);
                                c.add(temp.split("\\*"));
                            }
                            bro1.close();
                            fro1.close();
                            FileWriter fwc = new FileWriter(archiv);
                            PrintWriter pwc = new PrintWriter(fwc);
                            for(int i = 0; i<c.size();i++){
                                par = (String[])c.get(i);
                                //System.out.println(par[0]);
                                pwc.println( Encriptar.Encriptar(par[0]+"*"+par[1]+"*"+par[2]+"*"+par[3]));
                            }
                            archiv.setReadable(true);
                            archiv.setWritable(true);
                            pwc.close();
                            fwc.close();
                        }catch(Exception e){
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null,e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this,ex, "Error",JOptionPane.ERROR_MESSAGE);
                }
            }
            /////////*************************************PARAMETROS TAREA PROGRAMADA**********************//////////
            String[] t = load_task(ruta_program);
            enable_prog = t[0].equals("1");
            hh_pro = Integer.parseInt(t[1]);
            mm_pro = Integer.parseInt(t[2]);
            ruta_sql_bin = t[3];
            ruta_backup = t[4];
            enable_task = t[5].equals("1");
            tipo_estacion = t[6];
            System.out.println("Tarea programada: "+enable_prog);
            System.out.println("Hora programacion: "+hh_pro+":"+mm_pro);
            System.out.println("Ruta SQL Bin: "+ruta_sql_bin);
            System.out.println("Ruta Backup: "+ruta_backup);
            System.out.println("Enable task: "+enable_task);
            System.out.println("Tipo estacion: "+tipo_estacion);
            System.out.println("Numero de conexion: "+num_conection);
            /////////*************************************************************************************
            initComponents();
            icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png"));
            setIconImage(icon);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            this.setLocationRelativeTo(null);
            size=this.getSize();
            if(enable_prog){
                Tarea_programada();
            }
        }
        else{
            System.exit(0);
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
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        popup = new java.awt.PopupMenu();
        menuItem1 = new java.awt.MenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        rol = new javax.swing.JLabel();
        login = new javax.swing.JLabel();
        Bar = new javax.swing.JProgressBar();
        statusAnimationLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        conex = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton1 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        jButton23 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jm_iniciar_sesion = new javax.swing.JMenuItem();
        jm_cerrar_sesion = new javax.swing.JMenuItem();
        jm_cambio_contraseña = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem61 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jm_novedades = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenuItem36 = new javax.swing.JMenuItem();
        jMenuItem37 = new javax.swing.JMenuItem();
        jMenuItem68 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu21 = new javax.swing.JMenu();
        jMenuItem26 = new javax.swing.JMenuItem();
        jm_informacion = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem70 = new javax.swing.JMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem42 = new javax.swing.JMenuItem();
        jMenuItem46 = new javax.swing.JMenuItem();
        jMenu24 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu26 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu13 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenu15 = new javax.swing.JMenu();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem44 = new javax.swing.JMenuItem();
        jMenuItem45 = new javax.swing.JMenuItem();
        jMenu16 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem50 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem47 = new javax.swing.JMenuItem();
        jMenuItem48 = new javax.swing.JMenuItem();
        jMenuItem49 = new javax.swing.JMenuItem();
        jMenuItem43 = new javax.swing.JMenuItem();
        jMenu29 = new javax.swing.JMenu();
        jMenuItem73 = new javax.swing.JMenuItem();
        jMenu17 = new javax.swing.JMenu();
        jMenuItem38 = new javax.swing.JMenuItem();
        jMenuItem39 = new javax.swing.JMenuItem();
        jMenu18 = new javax.swing.JMenu();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenuItem31 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem57 = new javax.swing.JMenuItem();
        jMenuItem56 = new javax.swing.JMenuItem();
        jMenu19 = new javax.swing.JMenu();
        jMenuItem32 = new javax.swing.JMenuItem();
        jMenuItem33 = new javax.swing.JMenuItem();
        jm_reportes = new javax.swing.JMenu();
        jMenu23 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem69 = new javax.swing.JMenuItem();
        jMenu12 = new javax.swing.JMenu();
        jMenuItem58 = new javax.swing.JMenuItem();
        jMenuItem35 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem40 = new javax.swing.JMenuItem();
        jMenuItem51 = new javax.swing.JMenuItem();
        jm_asistencias = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem60 = new javax.swing.JMenuItem();
        jMenuItem64 = new javax.swing.JMenuItem();
        jMenuItem41 = new javax.swing.JMenuItem();
        jMenu27 = new javax.swing.JMenu();
        jMenuItem52 = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jMenu28 = new javax.swing.JMenu();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem72 = new javax.swing.JMenuItem();
        jMenuItem53 = new javax.swing.JMenuItem();
        jMenu14 = new javax.swing.JMenu();
        jMenu22 = new javax.swing.JMenu();
        jMenuItem55 = new javax.swing.JMenuItem();
        jMenuItem59 = new javax.swing.JMenuItem();
        jMenu25 = new javax.swing.JMenu();
        jMenuItem65 = new javax.swing.JMenuItem();
        jMenuItem71 = new javax.swing.JMenuItem();
        jMenuItem66 = new javax.swing.JMenuItem();
        jMenuItem67 = new javax.swing.JMenuItem();
        jm_pqr = new javax.swing.JMenu();
        jMenuItem62 = new javax.swing.JMenuItem();
        jMenuItem63 = new javax.swing.JMenuItem();
        jm_importar = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jm_usuarios = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem34 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jm_parametros = new javax.swing.JMenu();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem54 = new javax.swing.JMenuItem();
        jm_config = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu20 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();

        popup.setLabel("popupMenu1");

        menuItem1.setLabel("Abrir EDICOF");
        menuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem1ActionPerformed(evt);
            }
        });
        popup.add(menuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("EDICOF");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        rol.setFont(new java.awt.Font("Dialog", 0, 12));
        rol.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rol.setText(" ");
        rol.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        login.setFont(new java.awt.Font("Dialog", 0, 12));
        login.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        login.setText(" ");
        login.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Bar.setToolTipText("");
        Bar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Bar.setString("");
        Bar.setStringPainted(true);

        statusAnimationLabel.setText(" ");

        jLabel1.setText("Rol");

        jLabel2.setText("Usuario");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/WALLPAPPER_EDO2_RED_1.jpg"))); // NOI18N

        jLabel3.setText("Conexión");

        conex.setFont(new java.awt.Font("Dialog", 0, 12));
        conex.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        conex.setText(bd);
        conex.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusAnimationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rol, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(conex, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(conex, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rol, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusAnimationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jToolBar1.setRollover(true);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lock_open.png"))); // NOI18N
        jButton4.setToolTipText("Iniciar Sesion");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_iniciar_sesion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jButton4, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lock.png"))); // NOI18N
        jButton5.setToolTipText("Cerrar Sesion");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_cerrar_sesion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jButton5, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jm_cerrar_sesionActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/change_password.png"))); // NOI18N
        jButton6.setToolTipText("Cambiar Contraseña");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_cambio_contraseña, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jButton6, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jm_cambio_contraseñaActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);
        jToolBar1.add(jSeparator2);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/arrow_switch.png"))); // NOI18N
        jButton1.setToolTipText("Agregar Pre-Ingresos Traslados");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenuItem29, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jButton1, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/date_edit.png"))); // NOI18N
        jButton7.setToolTipText("Agregar Pre-Ingresos");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenuItem29, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jButton7, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton7);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/date_add.png"))); // NOI18N
        jButton8.setToolTipText("Agregar Ingresos");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenuItem36, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jButton8, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem36ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton8);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/date_delete.png"))); // NOI18N
        jButton9.setToolTipText("Agregar Retiros");
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenuItem37, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jButton9, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem37ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton9);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/date_magnify.png"))); // NOI18N
        jButton10.setToolTipText("Buscar Novedad");
        jButton10.setFocusable(false);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenuItem12, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jButton10, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton10);
        jToolBar1.add(jSeparator4);

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/group_add.png"))); // NOI18N
        jButton11.setToolTipText("Agregar Empleado");
        jButton11.setFocusable(false);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton11);

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/group_edit.png"))); // NOI18N
        jButton12.setToolTipText("Editar Empleado");
        jButton12.setFocusable(false);
        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton12);

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_tab_search.png"))); // NOI18N
        jButton13.setToolTipText("Buscar Empleado");
        jButton13.setFocusable(false);
        jButton13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenuItem28, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jButton13, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton13);
        jToolBar1.add(jSeparator5);

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/health.png"))); // NOI18N
        jButton14.setToolTipText("Agregar EPS");
        jButton14.setFocusable(false);
        jButton14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton14.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenuItem13, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jButton14, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton14);

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/medal_gold_add.png"))); // NOI18N
        jButton15.setToolTipText("Agregar AFP");
        jButton15.setFocusable(false);
        jButton15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenuItem18, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jButton15, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton15);

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/link_add.png"))); // NOI18N
        jButton16.setToolTipText("Agregar ARL");
        jButton16.setFocusable(false);
        jButton16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton16.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenuItem20, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jButton16, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton16);

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/heart_add.png"))); // NOI18N
        jButton17.setToolTipText("Agregar CCF");
        jButton17.setFocusable(false);
        jButton17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton17.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenuItem22, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jButton17, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton17);

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/building_add.png"))); // NOI18N
        jButton18.setToolTipText("Agregar Empresa");
        jButton18.setFocusable(false);
        jButton18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton18.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenuItem24, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jButton18, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton18);

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map_add.png"))); // NOI18N
        jButton19.setToolTipText("Agregar Municipio");
        jButton19.setFocusable(false);
        jButton19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton19.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenuItem38, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jButton19, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem38ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton19);

        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calendar_add.png"))); // NOI18N
        jButton21.setToolTipText("Agregar Obra");
        jButton21.setFocusable(false);
        jButton21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton21.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenuItem30, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jButton21, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton21);

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_add.png"))); // NOI18N
        jButton22.setToolTipText("Agregar Parentesco");
        jButton22.setFocusable(false);
        jButton22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton22.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenuItem32, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jButton22, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem32ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton22);
        jToolBar1.add(jSeparator6);

        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_add.png"))); // NOI18N
        jButton24.setToolTipText("Crear Usuario");
        jButton24.setFocusable(false);
        jButton24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton24.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenuItem7, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jButton24, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton24);

        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_edit.png"))); // NOI18N
        jButton25.setToolTipText("Editar Usuario");
        jButton25.setFocusable(false);
        jButton25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton25.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenuItem8, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jButton25, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton25);

        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_delete.png"))); // NOI18N
        jButton26.setToolTipText("Eliminar Usuario");
        jButton26.setFocusable(false);
        jButton26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton26.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenuItem34, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jButton26, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem34ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton26);

        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_go_1.png"))); // NOI18N
        jButton27.setToolTipText("Restaurar Usuario");
        jButton27.setFocusable(false);
        jButton27.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton27.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenuItem9, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jButton27, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton27);
        jToolBar1.add(jSeparator7);

        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/setting_tools.png"))); // NOI18N
        jButton23.setToolTipText("Opciones");
        jButton23.setFocusable(false);
        jButton23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton23.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenuItem5, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jButton23, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton23);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        jMenu1.setText("Archivo");

        jm_iniciar_sesion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jm_iniciar_sesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lock_open.png"))); // NOI18N
        jm_iniciar_sesion.setText("Iniciar Sesion");
        jm_iniciar_sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jm_iniciar_sesionActionPerformed(evt);
            }
        });
        jMenu1.add(jm_iniciar_sesion);

        jm_cerrar_sesion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jm_cerrar_sesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lock.png"))); // NOI18N
        jm_cerrar_sesion.setText("Cerrar Sesion");
        jm_cerrar_sesion.setEnabled(false);
        jm_cerrar_sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jm_cerrar_sesionActionPerformed(evt);
            }
        });
        jMenu1.add(jm_cerrar_sesion);

        jm_cambio_contraseña.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jm_cambio_contraseña.setIcon(new javax.swing.ImageIcon(getClass().getResource("/change_password.png"))); // NOI18N
        jm_cambio_contraseña.setText("Cambiar contraseña");
        jm_cambio_contraseña.setEnabled(false);
        jm_cambio_contraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jm_cambio_contraseñaActionPerformed(evt);
            }
        });
        jMenu1.add(jm_cambio_contraseña);
        jMenu1.add(jSeparator1);

        jMenuItem61.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem61.setText("Cambiar de conexion");
        jMenuItem61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem61ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem61);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancel_1.png"))); // NOI18N
        jMenuItem6.setText("Salir");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuBar1.add(jMenu1);

        jm_novedades.setText("Novedades");
        jm_novedades.setEnabled(false);

        jMenu10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/date.png"))); // NOI18N
        jMenu10.setText("Agregar Novedad");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_novedades, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenu10, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/arrow_switch.png"))); // NOI18N
        jMenuItem2.setText("Agregar Traslados");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_novedades, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem2, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem2);

        jMenuItem29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/date_edit.png"))); // NOI18N
        jMenuItem29.setText("Agregar Pre-Ingresos");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_novedades, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem29, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem29);

        jMenuItem36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/date_add.png"))); // NOI18N
        jMenuItem36.setText("Agregar Ingresos");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_novedades, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem36, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem36ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem36);

        jMenuItem37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/date_delete.png"))); // NOI18N
        jMenuItem37.setText("Agregar Retiros");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_novedades, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem37, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem37ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem37);

        jMenuItem68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/date_next.png"))); // NOI18N
        jMenuItem68.setText("Agregar Externos");
        jMenuItem68.setToolTipText("");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_novedades, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem68, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem68.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem68ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem68);

        jm_novedades.add(jMenu10);

        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/date_magnify.png"))); // NOI18N
        jMenuItem12.setText("Buscar Novedad");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_novedades, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem12, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jm_novedades.add(jMenuItem12);

        jMenu21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calendar_add.png"))); // NOI18N
        jMenu21.setText("Asistencias");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_novedades, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenu21, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calendar_add.png"))); // NOI18N
        jMenuItem26.setText("Ingresar asistencias");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenu21, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem26, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu21.add(jMenuItem26);

        jm_novedades.add(jMenu21);

        jMenuBar1.add(jm_novedades);

        jm_informacion.setText("Información");
        jm_informacion.setEnabled(false);

        jMenu2.setText("Empleados");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenu2, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/group_add.png"))); // NOI18N
        jMenuItem70.setText("Crear Empleados");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem70, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem70ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem70);

        jMenuItem28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_tab_search.png"))); // NOI18N
        jMenuItem28.setText("Buscar Empleado");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem28, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem28);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete.png"))); // NOI18N
        jMenu3.setText("Vetados");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenu3, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add.png"))); // NOI18N
        jMenuItem42.setText("Ingresar Vetados");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenu3, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem42, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem42ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem42);

        jMenuItem46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/document_inspector.png"))); // NOI18N
        jMenuItem46.setText("Lista de vetados");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenu3, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem46, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem46ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem46);

        jMenu2.add(jMenu3);

        jMenu24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map.png"))); // NOI18N
        jMenu24.setText("Info Socio Demografica");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenu24, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add.png"))); // NOI18N
        jMenuItem15.setText("Agregar Masivos");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenu24, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem15, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed1(evt);
            }
        });
        jMenu24.add(jMenuItem15);

        jMenu2.add(jMenu24);

        jMenu26.setText("Informacion de Transporte");

        jMenuItem16.setText("Agregar");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed1(evt);
            }
        });
        jMenu26.add(jMenuItem16);

        jMenu2.add(jMenu26);

        jm_informacion.add(jMenu2);

        jMenu13.setText("EPS's");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenu13, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/health.png"))); // NOI18N
        jMenuItem13.setText("Crear EPS");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem13, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem13);

        jMenuItem17.setText("Editar EPS");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem17, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem17);

        jm_informacion.add(jMenu13);

        jMenu8.setText("AFP's");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenu8, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/medal_gold_add.png"))); // NOI18N
        jMenuItem18.setText("Crear AFP");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem18, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem18);

        jMenuItem19.setText("Editar AFP");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem19, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem19);

        jm_informacion.add(jMenu8);

        jMenu11.setText("ARL's");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenu11, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/link_add.png"))); // NOI18N
        jMenuItem20.setText("Crear ARL");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem20, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem20);

        jMenuItem21.setText("Editar ARL");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem21, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem21);

        jm_informacion.add(jMenu11);

        jMenu15.setText("CCF's");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenu15, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/heart_add.png"))); // NOI18N
        jMenuItem22.setText("Crear CCF");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem22, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu15.add(jMenuItem22);

        jMenuItem23.setText("Editar CCF");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem23, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu15.add(jMenuItem23);

        jm_informacion.add(jMenu15);

        jMenu4.setText("Entidades");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenu4, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem44.setText("Crear entidad");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem44, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem44ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem44);

        jMenuItem45.setText("Editar entidad");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem45, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem45ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem45);

        jm_informacion.add(jMenu4);

        jMenu16.setText("Empresas");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenu16, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/building.png"))); // NOI18N
        jMenu5.setText("Administrar Empresas");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenu16, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenu5, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/building_add.png"))); // NOI18N
        jMenuItem24.setText("Crear Empresa");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenu5, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem24, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem24);

        jMenuItem50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_tab_search.png"))); // NOI18N
        jMenuItem50.setText("Buscar Empresa");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenu5, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem50, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem50ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem50);

        jMenu16.add(jMenu5);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/attribution.png"))); // NOI18N
        jMenu6.setText("Administrar Actividades");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenu16, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenu6, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem47.setText("Agregar Actividad");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenu6, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem47, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem47ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem47);

        jMenuItem48.setText("Editar Actividad");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenu6, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem48, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem48ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem48);

        jMenuItem49.setText("Lista de Actividades");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenu6, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem49, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem49ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem49);

        jMenu16.add(jMenu6);

        jMenuItem43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/change_password.png"))); // NOI18N
        jMenuItem43.setText("Administrar contraseñas");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem43, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem43ActionPerformed(evt);
            }
        });
        jMenu16.add(jMenuItem43);

        jm_informacion.add(jMenu16);

        jMenu29.setText("Barrios");

        jMenuItem73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add.png"))); // NOI18N
        jMenuItem73.setText("Agregar");
        jMenuItem73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem73ActionPerformed(evt);
            }
        });
        jMenu29.add(jMenuItem73);

        jm_informacion.add(jMenu29);

        jMenu17.setText("Municipios");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenu17, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map_add.png"))); // NOI18N
        jMenuItem38.setText("Crear Municipio");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem38, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem38ActionPerformed(evt);
            }
        });
        jMenu17.add(jMenuItem38);

        jMenuItem39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map_edit.png"))); // NOI18N
        jMenuItem39.setText("Editar Municipio");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem39, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem39ActionPerformed(evt);
            }
        });
        jMenu17.add(jMenuItem39);

        jm_informacion.add(jMenu17);

        jMenu18.setText("Obras");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenu18, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calendar_add.png"))); // NOI18N
        jMenuItem30.setText("Agregar Obra");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem30, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jMenu18.add(jMenuItem30);

        jMenuItem31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calendar_edit.png"))); // NOI18N
        jMenuItem31.setText("Editar Obra");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem31, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem31ActionPerformed(evt);
            }
        });
        jMenu18.add(jMenuItem31);

        jMenu9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/group.png"))); // NOI18N
        jMenu9.setText("Grupos para reportes");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenu5, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenu9, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/group_link.png"))); // NOI18N
        jMenuItem57.setText("Administrar Grupos");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenu9, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem57, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem57ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem57);

        jMenuItem56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/group_edit.png"))); // NOI18N
        jMenuItem56.setText("Asignar Grupos");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenu9, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem56, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem56ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem56);

        jMenu18.add(jMenu9);

        jm_informacion.add(jMenu18);

        jMenu19.setText("Parentescos");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenu19, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_add.png"))); // NOI18N
        jMenuItem32.setText("Crear Parentesco");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem32, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem32ActionPerformed(evt);
            }
        });
        jMenu19.add(jMenuItem32);

        jMenuItem33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_edit.png"))); // NOI18N
        jMenuItem33.setText("Editar Parentesco");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_informacion, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem33, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem33ActionPerformed(evt);
            }
        });
        jMenu19.add(jMenuItem33);

        jm_informacion.add(jMenu19);

        jMenuBar1.add(jm_informacion);

        jm_reportes.setText("Reportes");
        jm_reportes.setEnabled(false);

        jMenu23.setText("Personal Activo");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_reportes, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenu23, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_green.png"))); // NOI18N
        jMenuItem14.setText("Personal Activo");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_reportes, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem14, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu23.add(jMenuItem14);

        jMenuItem69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_gray.png"))); // NOI18N
        jMenuItem69.setText("Personal Activo Externos");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_reportes, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem69, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem69.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem69ActionPerformed(evt);
            }
        });
        jMenu23.add(jMenuItem69);

        jm_reportes.add(jMenu23);

        jMenu12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/book_go.png"))); // NOI18N
        jMenu12.setText("Precalculo Seguridad");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_reportes, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenu12, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem58.setText("Precalculo Seguridad Ciudad");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_reportes, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem58, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem58ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem58);

        jMenuItem35.setText("Precálculo Seguridad Obra");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_reportes, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem35, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem35ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem35);

        jm_reportes.add(jMenu12);

        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fingerprint.png"))); // NOI18N
        jMenuItem10.setText("Biométrico");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_reportes, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem10, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jm_reportes.add(jMenuItem10);

        jMenu7.setText("Novedades Pila");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_reportes, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenu7, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem40.setText("Novedades Pila Ingresos");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenu7, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem40, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem40ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem40);

        jMenuItem51.setText("Novedades Pila Retiros");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenu7, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem51, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem51ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem51);

        jm_reportes.add(jMenu7);

        jm_asistencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/report_user.png"))); // NOI18N
        jm_asistencias.setText("Asistencias");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_reportes, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jm_asistencias, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calendar.png"))); // NOI18N
        jMenuItem4.setText("Reporte Asistencias Contratista");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_asistencias, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem4, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jm_asistencias.add(jMenuItem4);

        jMenuItem60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calendar_link.png"))); // NOI18N
        jMenuItem60.setText("Reporte Asistencias Obra");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_asistencias, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem60, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem60ActionPerformed(evt);
            }
        });
        jm_asistencias.add(jMenuItem60);

        jMenuItem64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calendar_delete.png"))); // NOI18N
        jMenuItem64.setText("Reporte No Asistencias");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_asistencias, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem64, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem64ActionPerformed(evt);
            }
        });
        jm_asistencias.add(jMenuItem64);

        jm_reportes.add(jm_asistencias);

        jMenuItem41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/balance.png"))); // NOI18N
        jMenuItem41.setText("Balance de novedades");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_reportes, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem41, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem41ActionPerformed(evt);
            }
        });
        jm_reportes.add(jMenuItem41);

        jMenu27.setText("Empresas");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_reportes, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenu27, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small_business.png"))); // NOI18N
        jMenuItem52.setText("Empresas");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenu27, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem52, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem52ActionPerformed(evt);
            }
        });
        jMenu27.add(jMenuItem52);
        jMenu27.add(jSeparator8);

        jMenu28.setText("Fecha Limite");

        jMenuItem25.setText("Reporte agrupado");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenu28, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem25, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenu28.add(jMenuItem25);

        jMenuItem72.setText("Reporte por dia");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenu28, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem72, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenu28.add(jMenuItem72);

        jMenu27.add(jMenu28);

        jm_reportes.add(jMenu27);

        jMenuItem53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/email_send_receive.png"))); // NOI18N
        jMenuItem53.setText("Envio de Correos");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_reportes, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem53, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem53ActionPerformed(evt);
            }
        });
        jm_reportes.add(jMenuItem53);

        jMenu14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cross_promotion_selling.png"))); // NOI18N
        jMenu14.setText("Cruces");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_reportes, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenu14, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenu22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/document_quote.png"))); // NOI18N
        jMenu22.setText("ARL");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_reportes, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenu22, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cross_promotion_selling.png"))); // NOI18N
        jMenuItem55.setText("Agregar Cruce ARL");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenu14, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem55, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem55ActionPerformed(evt);
            }
        });
        jMenu22.add(jMenuItem55);

        jMenuItem59.setText("Consultar Cruce");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenu14, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem59, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem59ActionPerformed(evt);
            }
        });
        jMenu22.add(jMenuItem59);

        jMenu14.add(jMenu22);

        jMenu25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/document_signature.png"))); // NOI18N
        jMenu25.setText("PILA");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_reportes, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenu25, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem65.setText("Agregar Cruce Pila");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenu24, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem65, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem65ActionPerformed(evt);
            }
        });
        jMenu25.add(jMenuItem65);

        jMenuItem71.setText("Consultar Cruce");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jMenu24, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem71, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem71.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem71ActionPerformed(evt);
            }
        });
        jMenu25.add(jMenuItem71);

        jMenu14.add(jMenu25);

        jMenuItem66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/book_go.png"))); // NOI18N
        jMenuItem66.setText("Precalculo");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_reportes, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem66, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem66ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem66);

        jMenuItem67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_money.png"))); // NOI18N
        jMenuItem67.setText("Tesoreria");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_reportes, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem67, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem67ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem67);

        jm_reportes.add(jMenu14);

        jMenuBar1.add(jm_reportes);

        jm_pqr.setText("PQR");
        jm_pqr.setEnabled(false);

        jMenuItem62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emotion_question.png"))); // NOI18N
        jMenuItem62.setText("Buscar caso PQR");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_pqr, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem62, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem62ActionPerformed(evt);
            }
        });
        jm_pqr.add(jMenuItem62);

        jMenuItem63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add.png"))); // NOI18N
        jMenuItem63.setText("Agregar caso PQR");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_pqr, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem63, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem63ActionPerformed(evt);
            }
        });
        jm_pqr.add(jMenuItem63);

        jMenuBar1.add(jm_pqr);

        jm_importar.setText("Importar");
        jm_importar.setEnabled(false);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_excel.png"))); // NOI18N
        jMenuItem3.setText("Plantilla Seguimiento");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_importar, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem3, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jm_importar.add(jMenuItem3);

        jMenuBar1.add(jm_importar);

        jm_usuarios.setText("Usuarios");
        jm_usuarios.setEnabled(false);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_add.png"))); // NOI18N
        jMenuItem7.setText("Crear usuario");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_usuarios, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem7, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jm_usuarios.add(jMenuItem7);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_edit.png"))); // NOI18N
        jMenuItem8.setText("Editar usuario");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_usuarios, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem8, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jm_usuarios.add(jMenuItem8);

        jMenuItem34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_delete.png"))); // NOI18N
        jMenuItem34.setText("Eliminar usuario");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_usuarios, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem34, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem34ActionPerformed(evt);
            }
        });
        jm_usuarios.add(jMenuItem34);

        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_go_1.png"))); // NOI18N
        jMenuItem9.setText("Restaurar usuario");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_usuarios, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem9, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jm_usuarios.add(jMenuItem9);

        jMenuBar1.add(jm_usuarios);

        jm_parametros.setText("Parametros");
        jm_parametros.setEnabled(false);

        jMenuItem27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cash_stack.png"))); // NOI18N
        jMenuItem27.setText("Editar Salario Minimo");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_parametros, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem27, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jm_parametros.add(jMenuItem27);

        jMenuItem54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/email_send_receive.png"))); // NOI18N
        jMenuItem54.setText("Editar Correo Remitente");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_parametros, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem54, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem54ActionPerformed(evt);
            }
        });
        jm_parametros.add(jMenuItem54);

        jMenuBar1.add(jm_parametros);

        jm_config.setText("Configuración");

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/setting_tools.png"))); // NOI18N
        jMenuItem5.setText("Ajustes");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jm_config, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jMenuItem5, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jm_config.add(jMenuItem5);

        jMenuBar1.add(jm_config);

        jMenu20.setText("Ayuda");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/update.png"))); // NOI18N
        jMenuItem1.setText("Actualizar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu20.add(jMenuItem1);

        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/help.png"))); // NOI18N
        jMenuItem11.setText("Acerca de");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu20.add(jMenuItem11);

        jMenuBar1.add(jMenu20);

        setJMenuBar(jMenuBar1);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser(new File("").getAbsoluteFile().getAbsolutePath());
        FileFilter filter = new FileNameExtensionFilter("Libro de Excel (*.xlsx)","xlsx");
        FileFilter filter1 = new FileNameExtensionFilter("Libro de Excel habiltado para macros (*.xlsm)","xlsm");
        fc.setFileFilter(filter1);
        fc.setFileFilter(filter);
        if(fc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
            archivo = fc.getSelectedFile();
            Importar imp = new Importar (this,archivo);
            imp.start();
            
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        Opciones opt=new Opciones(this,false);
        opt.setLocationRelativeTo(this);
        opt.setVisible(true);
        if (enable_prog) {
            if (tarea==null) {
                System.out.println("Se instancia tarea nueva");
                Tarea_programada();
            }else{
                System.out.println("Tarea: "+tarea.getHh_prog()+" "+tarea.getMm_prog());
                System.out.println("Main: "+hh_pro+" "+mm_pro);
                if (tarea.getHh_prog()!= hh_pro | tarea.getMm_prog()!=mm_pro) {
                    tarea.refreh_Programacion();
                    tarea.set_hh_mm(hh_pro,mm_pro);
                    tarea.init_Tarea();
                }else{
                tarea.init_Tarea();
                }
            }
        }else{
            if (tarea==null) {
                
            }else{
                tarea.stanby_tarea();
            }
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        int reply;
        if (tipo_estacion.equals("SERVIDOR")) {
            reply = JOptionPane.showConfirmDialog(this,"Si cierra el aplicativo en este equipo la copia de seguridad automatica dejará de ejecutarse \n Desea salir?","Verificacion",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
        }else{
            reply = JOptionPane.showConfirmDialog(this,"Esta seguro que desea salir?","Verificacion",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
        }
        if (reply==JOptionPane.YES_OPTION){
            System.exit(0);
        }    
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        Buscar_Novedad edit_nov=new Buscar_Novedad(this,false);
        edit_nov.setLocationRelativeTo(this);
        edit_nov.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jm_iniciar_sesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jm_iniciar_sesionActionPerformed
        // TODO add your handling code here:
        if(time_login){
            
        Login login = new Login(this,true);
        //JOptionPane.showInternalMessageDialog(login, size);
        login.setLocationRelativeTo(this);
        login.setVisible(true);
        }
        else{
                JOptionPane.showMessageDialog(null,"Debe esperar el tiempo para intentar nuevamente.", "Error",JOptionPane.ERROR_MESSAGE);
        }  
    }//GEN-LAST:event_jm_iniciar_sesionActionPerformed

    private void jm_cerrar_sesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jm_cerrar_sesionActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(null,"Esta seguro que desea cerrar sesion?","Confirmar",JOptionPane.OK_CANCEL_OPTION)==0){
            Main.jm_iniciar_sesion.setEnabled(true);
            Main.jm_cerrar_sesion.setEnabled(false);
            Main.jm_cambio_contraseña.setEnabled(false);
            Main.jm_novedades.setEnabled(false);
            Main.jm_informacion.setEnabled(false);
            Main.jm_reportes.setEnabled(false);
            Main.jm_pqr.setEnabled(false);
            Main.jm_importar.setEnabled(false);
            Main.jm_usuarios.setEnabled(false);
            Main.jm_config.setEnabled(false);

            login.setText("");
            rol.setText("");
        }
        
    }//GEN-LAST:event_jm_cerrar_sesionActionPerformed

    private void jm_cambio_contraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jm_cambio_contraseñaActionPerformed
        // TODO add your handling code here:
        Cambiar_contraseña c_contraseña=new Cambiar_contraseña(this,false);
        c_contraseña.setLocationRelativeTo(this);
        c_contraseña.setVisible(true);
    }//GEN-LAST:event_jm_cambio_contraseñaActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        int reply;
        if (tipo_estacion.equals("SERVIDOR")) {
            reply = JOptionPane.showConfirmDialog(this,"Si cierra el aplicativo en este equipo la copia de seguridad automatica dejará de ejecutarse \n Desea salir?","Verificacion",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
        }else{
            reply = JOptionPane.showConfirmDialog(this,"Esta seguro que desea salir?","Verificacion",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
        }
        if (reply==JOptionPane.YES_OPTION){
            System.exit(0);
        }
        
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        // TODO add your handling code here:
        Add_Empleado add_empleado = new Add_Empleado(this, false);
        add_empleado.setLocationRelativeTo(this);
        add_empleado.setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // TODO add your handling code here:
        Edd_Empleado edd_empleado = new Edd_Empleado(this, false);
        edd_empleado.setLocationRelativeTo(this);
        edd_empleado.setVisible(true);
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // TODO add your handling code here:
        Add_EPS add_eps = new Add_EPS(this, false);
        add_eps.setLocationRelativeTo(this);
        add_eps.setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        // TODO add your handling code here:
        Edd_EPS edd_eps = new Edd_EPS(this, false);
        edd_eps.setLocationRelativeTo(this);
        edd_eps.setVisible(true);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        // TODO add your handling code here:
        Add_AFP add_afp = new Add_AFP(this, false);
        add_afp.setLocationRelativeTo(this);
        add_afp.setVisible(true);
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        // TODO add your handling code here:
        Edd_AFP edd_afp = new Edd_AFP(this, false);
        edd_afp.setLocationRelativeTo(this);
        edd_afp.setVisible(true);
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        // TODO add your handling code here:
        Add_ARL add_arl = new Add_ARL(this, false);
        add_arl.setLocationRelativeTo(this);
        add_arl.setVisible(true);
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        // TODO add your handling code here:
        Add_CCF add_ccf = new Add_CCF(this, false);
        add_ccf.setLocationRelativeTo(this);
        add_ccf.setVisible(true);
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        // TODO add your handling code here:
        Edd_ARL edd_arl = new Edd_ARL(this, false);
        edd_arl.setLocationRelativeTo(this);
        edd_arl.setVisible(true);
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        // TODO add your handling code here:
        Edd_CCF edd_ccf = new Edd_CCF(this, false);
        edd_ccf.setLocationRelativeTo(this);
        edd_ccf.setVisible(true);
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        // TODO add your handling code here:
        Add_Empresa add_empresa = new Add_Empresa(this, false);
        add_empresa.setLocationRelativeTo(this);
        add_empresa.setVisible(true);
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
        // TODO add your handling code here:
        Add_Obra add_obra = new Add_Obra(this, false);
        add_obra.setLocationRelativeTo(this);
        add_obra.setVisible(true);
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
        // TODO add your handling code here:
        Edd_Obra edd_obra = new Edd_Obra(this, false);
        edd_obra.setLocationRelativeTo(this);
        edd_obra.setVisible(true);
    }//GEN-LAST:event_jMenuItem31ActionPerformed

    private void jMenuItem32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem32ActionPerformed
        // TODO add your handling code here:
        Add_Parentesco add_par = new Add_Parentesco(this, false);
        add_par.setLocationRelativeTo(this);
        add_par.setVisible(true);
    }//GEN-LAST:event_jMenuItem32ActionPerformed

    private void jMenuItem33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem33ActionPerformed
        // TODO add your handling code here:
        Edd_Parentesco edd_parentesco = new Edd_Parentesco(this, false);
        edd_parentesco.setLocationRelativeTo(this);
        edd_parentesco.setVisible(true);
    }//GEN-LAST:event_jMenuItem33ActionPerformed

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        // TODO add your handling code here:
        if (enable_task) {
            int oldState = evt.getOldState();
            int newState = evt.getNewState();
            if ((oldState & this.ICONIFIED) == 0 && (newState & this.ICONIFIED) != 0) {
                if (t_icon == null ) {
                   t_icon = new TrayIcon(icon,"Software EDICOF",popup);
                    t_icon.setImageAutoSize(true); 
                }
                s_tray = SystemTray.getSystemTray();
                try {
                    if(SystemTray.isSupported()){
                        s_tray.add(t_icon);
                        this.setVisible(false);
                    }

                } catch (Exception e) {
                }
            } else if ((oldState & this.ICONIFIED) != 0 && (newState & this.ICONIFIED) == 0) {
              //System.out.println("Frame was deiconized");
            }

            if ((oldState & this.MAXIMIZED_BOTH) == 0 && (newState & this.MAXIMIZED_BOTH) != 0) {
              //System.out.println("Frame was maximized");
            } else if ((oldState & this.MAXIMIZED_BOTH) != 0 && (newState & this.MAXIMIZED_BOTH) == 0) {
              //System.out.println("Frame was minimized");
            }
        }
    }//GEN-LAST:event_formWindowStateChanged

    private void menuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem1ActionPerformed
        // TODO add your handling code here:
        s_tray.remove(t_icon);
        this.setVisible(true);
        this.requestFocus();
    }//GEN-LAST:event_menuItem1ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        Add_Usuario add_usuario = new Add_Usuario(this, false);
        add_usuario.setLocationRelativeTo(this);
        add_usuario.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        Edd_Usuario edd_usuario = new Edd_Usuario(this, true);
        edd_usuario.setLocationRelativeTo(this);
        edd_usuario.setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        Restaurar_usuario res_usuario = new Restaurar_usuario(this, false);
        res_usuario.setLocationRelativeTo(this);
        res_usuario.setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem34ActionPerformed
        // TODO add your handling code here:
        Del_Usuario del_usuario = new Del_Usuario(this, false);
        del_usuario.setLocationRelativeTo(this);
        del_usuario.setVisible(true);
    }//GEN-LAST:event_jMenuItem34ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // TODO add your handling code here:
        Rep_personal_activo rep_per_act = new Rep_personal_activo(this, false);
        rep_per_act.setLocationRelativeTo(this);
        rep_per_act.setVisible(true);
        
        
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem35ActionPerformed
        // TODO add your handling code here:
        Rep_precalculo_obra rep_prec =new Rep_precalculo_obra(this,false);
        rep_prec.setLocationRelativeTo(this);
        rep_prec.setVisible(true);
    }//GEN-LAST:event_jMenuItem35ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
        // TODO add your handling code here:
        Buscar_Empleado busc_nov=new Buscar_Empleado(this,false);
        busc_nov.setLocationRelativeTo(this);
        busc_nov.setVisible(true);
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
        size=this.getSize();
    }//GEN-LAST:event_formComponentResized

    private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
        // TODO add your handling code here:
        Add_Preingresos add_preing=new Add_Preingresos(this,false);
        add_preing.setLocationRelativeTo(this);
        add_preing.setVisible(true);
    }//GEN-LAST:event_jMenuItem29ActionPerformed

    private void jMenuItem38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem38ActionPerformed
        // TODO add your handling code here:
        Add_Municipio add_mun=new Add_Municipio(this,false);
        add_mun.setLocationRelativeTo(this);
        add_mun.setVisible(true);
    }//GEN-LAST:event_jMenuItem38ActionPerformed

    private void jMenuItem39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem39ActionPerformed
        // TODO add your handling code here:
        Edd_Municipio edd_mun=new Edd_Municipio(this,false);
        edd_mun.setLocationRelativeTo(this);
        edd_mun.setVisible(true);
    }//GEN-LAST:event_jMenuItem39ActionPerformed

    private void jMenuItem36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem36ActionPerformed
        // TODO add your handling code here:
        Add_Ingresos add_ing=new Add_Ingresos(this,false);
        add_ing.setLocationRelativeTo(this);
        add_ing.setVisible(true);
    }//GEN-LAST:event_jMenuItem36ActionPerformed

    private void jMenuItem37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem37ActionPerformed
        // TODO add your handling code here:
        Add_Retiros add_ret=new Add_Retiros(this,false);
        add_ret.setLocationRelativeTo(this);
        add_ret.setVisible(true);
    }//GEN-LAST:event_jMenuItem37ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        AboutBox abox=new AboutBox(this,false);
        abox.setLocationRelativeTo(this);
        abox.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if(time_login){
        Login login = new Login(this,false);
        login.setLocationRelativeTo(this);
        login.setVisible(true);
        }
        else{
                JOptionPane.showMessageDialog(null,"Debe esperar el tiempo para intentar nuevamente.", "Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        if (check_update(url_info_update)) {
            int conf = JOptionPane.showConfirmDialog(this,"Hay una nueva version del software disponible\n Desea actualizar?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (conf == JOptionPane.YES_OPTION) {
                HiloUpdate h_up = new HiloUpdate(get_url_update(url_info_update),get_ver_update(url_info_update));
                h_up.start();
                System.exit(0);
            }
        }else{
            JOptionPane.showMessageDialog(null,"El software ya se encuentra actualizado.", "Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        Add_Preingreso_Traslado add_tras=new Add_Preingreso_Traslado(this,false);
        add_tras.setLocationRelativeTo(this);
        add_tras.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Add_Preingreso_Traslado add_preing_trasl=new Add_Preingreso_Traslado(this,false);
        add_preing_trasl.setLocationRelativeTo(this);
        add_preing_trasl.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        Rep_biometrico rep_bio =new Rep_biometrico(this,false);
        rep_bio.setLocationRelativeTo(this);
        rep_bio.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        // TODO add your handling code here:
        Add_Asistencias add_asist =new Add_Asistencias(this,false);
        add_asist.setLocationRelativeTo(this);
        add_asist.setVisible(true);
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        Rep_asistencias_empresa rep_asist =new Rep_asistencias_empresa(this,false);
        rep_asist.setLocationRelativeTo(this);
        rep_asist.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        // TODO add your handling code here:
        Edd_Salario_minimo edd_salario =new Edd_Salario_minimo(this,false);
        edd_salario.setLocationRelativeTo(this);
        edd_salario.setVisible(true);
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem40ActionPerformed
        // TODO add your handling code here:
        Rep_novedades_pila_ingreso rep_nov_pila =new Rep_novedades_pila_ingreso(this,false);
        rep_nov_pila.setLocationRelativeTo(this);
        rep_nov_pila.setVisible(true);
    }//GEN-LAST:event_jMenuItem40ActionPerformed

    private void jMenuItem41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem41ActionPerformed
        // TODO add your handling code here:
        Rep_balance_novedades rep_bal = new Rep_balance_novedades(this, false);
        rep_bal.setLocationRelativeTo(this);
        rep_bal.setVisible(true);
    }//GEN-LAST:event_jMenuItem41ActionPerformed

    private void jMenuItem42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem42ActionPerformed
        // TODO add your handling code here:
        Add_vetados add_vet = new Add_vetados(this, false);
        add_vet.setLocationRelativeTo(this);
        add_vet.setVisible(true);
    }//GEN-LAST:event_jMenuItem42ActionPerformed

    private void jMenuItem43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem43ActionPerformed
        // TODO add your handling code here:
        Admin_password admin = new Admin_password(this, false);
        admin.setLocationRelativeTo(this);
        admin.setVisible(true);
    }//GEN-LAST:event_jMenuItem43ActionPerformed

    private void jMenuItem44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem44ActionPerformed
        // TODO add your handling code here:
        Add_Entidad add_ent = new Add_Entidad(this, false);
        add_ent.setLocationRelativeTo(this);
        add_ent.setVisible(true);
    }//GEN-LAST:event_jMenuItem44ActionPerformed

    private void jMenuItem45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem45ActionPerformed
        // TODO add your handling code here:
        Edd_Entidad edd_ent = new Edd_Entidad(this, false);
        edd_ent.setLocationRelativeTo(this);
        edd_ent.setVisible(true);
    }//GEN-LAST:event_jMenuItem45ActionPerformed

    private void jMenuItem46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem46ActionPerformed
        // TODO add your handling code here:
        View_Vetados vet = new View_Vetados(this, false);
        vet.setLocationRelativeTo(this);
        vet.setVisible(true);
    }//GEN-LAST:event_jMenuItem46ActionPerformed

    private void jMenuItem47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem47ActionPerformed
        // TODO add your handling code here:
        Add_Actividad add_act = new Add_Actividad(this, false);
        add_act.setLocationRelativeTo(this);
        add_act.setVisible(true);
    }//GEN-LAST:event_jMenuItem47ActionPerformed

    private void jMenuItem48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem48ActionPerformed
        // TODO add your handling code here:
        Edd_Actividad edd_act = new Edd_Actividad(this, false);
        edd_act.setLocationRelativeTo(this);
        edd_act.setVisible(true);
    }//GEN-LAST:event_jMenuItem48ActionPerformed

    private void jMenuItem49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem49ActionPerformed
        // TODO add your handling code here:
        View_Actividades view_act = new View_Actividades(this, false);
        view_act.setLocationRelativeTo(this);
        view_act.setVisible(true);
    }//GEN-LAST:event_jMenuItem49ActionPerformed

    private void jMenuItem50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem50ActionPerformed
        // TODO add your handling code here:
        Buscar_Empresa busc_emp = new Buscar_Empresa(this, false);
        busc_emp.setLocationRelativeTo(this);
        busc_emp.setVisible(true);
    }//GEN-LAST:event_jMenuItem50ActionPerformed

    private void jMenuItem51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem51ActionPerformed
        // TODO add your handling code here:
        Rep_novedades_pila_retiro rep_nov_pila_ret =new Rep_novedades_pila_retiro(this,false);
        rep_nov_pila_ret.setLocationRelativeTo(this);
        rep_nov_pila_ret.setVisible(true);
    }//GEN-LAST:event_jMenuItem51ActionPerformed

    private void jMenuItem52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem52ActionPerformed
        // TODO add your handling code here:
        Rep_Empresas rep_emp =new Rep_Empresas(this,false);
        rep_emp.setLocationRelativeTo(this);
        rep_emp.setVisible(true);
    }//GEN-LAST:event_jMenuItem52ActionPerformed

    private void jMenuItem53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem53ActionPerformed
        // TODO add your handling code here:
        Email mail = new Email(this, false);
        mail.setLocationRelativeTo(this);
        mail.setVisible(true);
    }//GEN-LAST:event_jMenuItem53ActionPerformed

    private void jMenuItem54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem54ActionPerformed
        // TODO add your handling code here:
        Edd_email ed_mail = new Edd_email(this, false);
        ed_mail.setLocationRelativeTo(this);
        ed_mail.setVisible(true);
    }//GEN-LAST:event_jMenuItem54ActionPerformed

    private void jMenuItem55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem55ActionPerformed
        // TODO add your handling code here:
        Rep_cruce_arl cruce = new Rep_cruce_arl(this, false);
        cruce.setLocationRelativeTo(this);
        cruce.setVisible(true);
    }//GEN-LAST:event_jMenuItem55ActionPerformed

    private void jMenuItem57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem57ActionPerformed
        // TODO add your handling code here:
        Admin_Grupos_Obras admin_grupos = new Admin_Grupos_Obras(this, false);
        admin_grupos.setLocationRelativeTo(this);
        admin_grupos.setVisible(true);
    }//GEN-LAST:event_jMenuItem57ActionPerformed

    private void jMenuItem56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem56ActionPerformed
        // TODO add your handling code here:
        if (rol.getText().equals("ADMINISTRADOR") | rol.getText().equals("MASTER")) {
            Asignar_Grupo_Obras ed_grupos = new Asignar_Grupo_Obras(this, false);
            ed_grupos.setLocationRelativeTo(this);
            ed_grupos.setVisible(true);
        }
        
    }//GEN-LAST:event_jMenuItem56ActionPerformed

    private void jMenuItem58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem58ActionPerformed
        // TODO add your handling code here:
        Rep_precalculo_ciudad rep_prec =new Rep_precalculo_ciudad(this,false);
        rep_prec.setLocationRelativeTo(this);
        rep_prec.setVisible(true);
    }//GEN-LAST:event_jMenuItem58ActionPerformed

    private void jMenuItem59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem59ActionPerformed
        // TODO add your handling code here:
        Busc_cruce_arl bc = new Busc_cruce_arl(this, false);
        bc.setLocationRelativeTo(this);
        bc.setVisible(true);
    }//GEN-LAST:event_jMenuItem59ActionPerformed

    private void jMenuItem60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem60ActionPerformed
        // TODO add your handling code here:
        Rep_asistencias_obras rep_asist =new Rep_asistencias_obras(this,false);
        rep_asist.setLocationRelativeTo(this);
        rep_asist.setVisible(true);
    }//GEN-LAST:event_jMenuItem60ActionPerformed

    private void jMenuItem61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem61ActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(null,"Esta seguro que desea cerrar sesion y cambiar de conexion?","Confirmar",JOptionPane.OK_CANCEL_OPTION)==0){
            Main.jm_iniciar_sesion.setEnabled(true);
            Main.jm_cerrar_sesion.setEnabled(false);
            Main.jm_cambio_contraseña.setEnabled(false);
            Main.jm_novedades.setEnabled(false);
            Main.jm_informacion.setEnabled(false);
            Main.jm_reportes.setEnabled(false);
            Main.jm_importar.setEnabled(false);
            Main.jm_usuarios.setEnabled(false);
            Main.jm_config.setEnabled(false);
            conex.setText("");
            login.setText("");
            rol.setText("");
            get_conection();
        }
    }//GEN-LAST:event_jMenuItem61ActionPerformed

    private void jMenuItem62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem62ActionPerformed
        // TODO add your handling code here:
        Casos_pqr casos =new Casos_pqr(this,false);
        casos.setLocationRelativeTo(this);
        casos.setVisible(true);
    }//GEN-LAST:event_jMenuItem62ActionPerformed

    private void jMenuItem63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem63ActionPerformed
        // TODO add your handling code here:
        Add_Caso add =new Add_Caso(this,false);
        add.setLocationRelativeTo(this);
        add.setVisible(true);
    }//GEN-LAST:event_jMenuItem63ActionPerformed

    private void jMenuItem64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem64ActionPerformed
        // TODO add your handling code here:
        Rep_no_asistencias_empresa rep_n_asist =new Rep_no_asistencias_empresa(this,false);
        rep_n_asist.setLocationRelativeTo(this);
        rep_n_asist.setVisible(true);
    }//GEN-LAST:event_jMenuItem64ActionPerformed

    private void jMenuItem65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem65ActionPerformed
        // TODO add your handling code here:
        Rep_cruce_pila rcp = new Rep_cruce_pila(this, false);
        rcp.setLocationRelativeTo(this);
        rcp.setVisible(true);
    }//GEN-LAST:event_jMenuItem65ActionPerformed

    private void jMenuItem66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem66ActionPerformed
        // TODO add your handling code here:
        CrucePrecalculo cp = new CrucePrecalculo(this, false);
        cp.setLocationRelativeTo(this);
        cp.setVisible(true);
    }//GEN-LAST:event_jMenuItem66ActionPerformed

    private void jMenuItem67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem67ActionPerformed
        // TODO add your handling code here:
        CruceTesoreria ct = new CruceTesoreria(this, false);
        ct.setLocationRelativeTo(this);
        ct.setVisible(true);
    }//GEN-LAST:event_jMenuItem67ActionPerformed

    private void jMenuItem68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem68ActionPerformed
        // TODO add your handling code here:
        Add_Externos add_ext=new Add_Externos(this,false);
        add_ext.setLocationRelativeTo(this);
        add_ext.setVisible(true);
    }//GEN-LAST:event_jMenuItem68ActionPerformed

    private void jMenuItem69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem69ActionPerformed
        // TODO add your handling code here:
        Rep_personal_activo_externos rep_per_act_ext = new Rep_personal_activo_externos(this, false);
        rep_per_act_ext.setLocationRelativeTo(this);
        rep_per_act_ext.setVisible(true);
    }//GEN-LAST:event_jMenuItem69ActionPerformed

    private void jMenuItem70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem70ActionPerformed
        // TODO add your handling code here:
        Add_Empleado_Masivos add_emp_mas = new Add_Empleado_Masivos(this, false);
        add_emp_mas.setLocationRelativeTo(this);
        add_emp_mas.setVisible(true);
    }//GEN-LAST:event_jMenuItem70ActionPerformed

    private void jMenuItem15ActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed1
        // TODO add your handling code here:
        Add_Info_Socio_Demografica_Masivos add_inf_mas = new Add_Info_Socio_Demografica_Masivos(this, false);
        add_inf_mas.setLocationRelativeTo(this);
        add_inf_mas.setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed1
    private void jMenuItem71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem71ActionPerformed
        // TODO add your handling code here:
        Busc_cruce_pila bc = new Busc_cruce_pila(this, false);
        bc.setLocationRelativeTo(this);
        bc.setVisible(true);
    }//GEN-LAST:event_jMenuItem71ActionPerformed

    private void jMenuItem16ActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed1
        // TODO add your handling code here:
        Add_Info_Transporte add_tr = new Add_Info_Transporte(this,false);
        add_tr.setLocationRelativeTo(this);
        add_tr.setVisible(true);
    }//GEN-LAST:event_jMenuItem16ActionPerformed1

    private void jMenuItem73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem73ActionPerformed
        // TODO add your handling code here:
        Add_Barrio add_br = new Add_Barrio(this,false);
        add_br.setLocationRelativeTo(this);
        add_br.setVisible(true);
    }//GEN-LAST:event_jMenuItem73ActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JProgressBar Bar;
    public static javax.swing.JLabel conex;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    protected static javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu16;
    private javax.swing.JMenu jMenu17;
    private javax.swing.JMenu jMenu18;
    private javax.swing.JMenu jMenu19;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu20;
    private javax.swing.JMenu jMenu21;
    private javax.swing.JMenu jMenu22;
    private javax.swing.JMenu jMenu23;
    private javax.swing.JMenu jMenu24;
    private javax.swing.JMenu jMenu25;
    private javax.swing.JMenu jMenu26;
    private javax.swing.JMenu jMenu27;
    private javax.swing.JMenu jMenu28;
    private javax.swing.JMenu jMenu29;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem35;
    private javax.swing.JMenuItem jMenuItem36;
    private javax.swing.JMenuItem jMenuItem37;
    private javax.swing.JMenuItem jMenuItem38;
    private javax.swing.JMenuItem jMenuItem39;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem40;
    private javax.swing.JMenuItem jMenuItem41;
    private javax.swing.JMenuItem jMenuItem42;
    private javax.swing.JMenuItem jMenuItem43;
    private javax.swing.JMenuItem jMenuItem44;
    private javax.swing.JMenuItem jMenuItem45;
    private javax.swing.JMenuItem jMenuItem46;
    private javax.swing.JMenuItem jMenuItem47;
    private javax.swing.JMenuItem jMenuItem48;
    private javax.swing.JMenuItem jMenuItem49;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem50;
    private javax.swing.JMenuItem jMenuItem51;
    private javax.swing.JMenuItem jMenuItem52;
    private javax.swing.JMenuItem jMenuItem53;
    private javax.swing.JMenuItem jMenuItem54;
    private javax.swing.JMenuItem jMenuItem55;
    private javax.swing.JMenuItem jMenuItem56;
    private javax.swing.JMenuItem jMenuItem57;
    private javax.swing.JMenuItem jMenuItem58;
    private javax.swing.JMenuItem jMenuItem59;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem60;
    private javax.swing.JMenuItem jMenuItem61;
    private javax.swing.JMenuItem jMenuItem62;
    private javax.swing.JMenuItem jMenuItem63;
    private javax.swing.JMenuItem jMenuItem64;
    private javax.swing.JMenuItem jMenuItem65;
    private javax.swing.JMenuItem jMenuItem66;
    private javax.swing.JMenuItem jMenuItem67;
    private javax.swing.JMenuItem jMenuItem68;
    private javax.swing.JMenuItem jMenuItem69;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem70;
    private javax.swing.JMenuItem jMenuItem71;
    private javax.swing.JMenuItem jMenuItem72;
    private javax.swing.JMenuItem jMenuItem73;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenu jm_asistencias;
    protected static javax.swing.JMenuItem jm_cambio_contraseña;
    protected static javax.swing.JMenuItem jm_cerrar_sesion;
    protected static javax.swing.JMenu jm_config;
    protected static javax.swing.JMenu jm_importar;
    protected static javax.swing.JMenu jm_informacion;
    protected static javax.swing.JMenuItem jm_iniciar_sesion;
    protected static javax.swing.JMenu jm_novedades;
    protected static javax.swing.JMenu jm_parametros;
    protected static javax.swing.JMenu jm_pqr;
    protected static javax.swing.JMenu jm_reportes;
    protected static javax.swing.JMenu jm_usuarios;
    public static javax.swing.JLabel login;
    private java.awt.MenuItem menuItem1;
    private java.awt.PopupMenu popup;
    public static javax.swing.JLabel rol;
    public static javax.swing.JLabel statusAnimationLabel;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
public void PrintList (List cdl){
    for (int i = 0; i < cdl.size(); i++) {
        List ctl = (List) cdl.get(i);
        for (int j = 0; j < ctl.size(); j++) {
            System.out.print(ctl.get(j));
            System.out.print(" ");
        }
        System.out.println("");
    }

}
public static Boolean Get_file_config(java.awt.Frame parent) {
    Boolean ret=false;
    String[] param;
    String aux;
    String temp;
    archivo = new File(ruta_file_option);
    if(archivo.exists()){
        try{
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            while((aux=br.readLine())!=null){
                temp = Encriptar.Desencriptar(aux);
                param=temp.split("\\*");
                host = param[0];
                bd = param[1];
                usu = param[2];
                cont = param[3];
                enable_prog = param[4].equals("1");
                hh_pro = Integer.parseInt(param[5]);
                mm_pro = Integer.parseInt(param[6]);
                ruta_sql_bin = param[7];
                ruta_backup = param[8];
                enable_task = param[9].equals("1");
                tipo_estacion = param[10];
            }
            br.close();
            fr.close();
            System.out.println("Host: "+host);
            System.out.println("DB: "+bd);
            System.out.println("Usuario DB: "+usu);
            System.out.println("Contraseña: "+cont);
            System.out.println("Tarea programada: "+enable_prog);
            System.out.println("Hora programacion: "+hh_pro+":"+mm_pro);
            System.out.println("Ruta SQL Bin: "+ruta_sql_bin);
            System.out.println("Ruta Backup: "+ruta_backup);
            System.out.println("Enable task: "+enable_task);
            System.out.println("Tipo estacion: "+tipo_estacion);
            ret = true;
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    else{
        JOptionPane.showMessageDialog(null,"El archivo de configuración no existe.", "Error",JOptionPane.ERROR_MESSAGE);
    }
    return ret;
}
public List GetListXls(File arc, String s){
    List resp = new ArrayList();
    try {
        FileInputStream fis = new FileInputStream(arc);
        XSSFWorkbook wb = new XSSFWorkbook(fis); // Crga todo el archivo
        XSSFSheet sfsheet = wb.getSheetAt(wb.getSheetIndex(s)); // Carga la hoja 1
        Iterator rowiterator = sfsheet.rowIterator(); //Este elemento es el que maneja las filas de la hoja seleccionada
        while(rowiterator.hasNext()){
            XSSFRow sfrow = (XSSFRow) rowiterator.next();
            Iterator iterator = sfrow.cellIterator();
            List celltemp = new ArrayList();
            while (iterator.hasNext()) { 
                XSSFCell sfcell = (XSSFCell) iterator.next();
                switch(sfcell.getCellType()) {
                    case XSSFCell.CELL_TYPE_NUMERIC:
                        if( DateUtil.isCellDateFormatted(sfcell) ){
                            celltemp.add(new SimpleDateFormat("dd-MM-yyyy").format(sfcell.getDateCellValue()));
                        }else{	
                            celltemp.add(new DecimalFormat("###").format(sfcell.getNumericCellValue()));
                        }
                        break;
                    case XSSFCell.CELL_TYPE_STRING:
                        celltemp.add(sfcell.getStringCellValue());
                        break;
                    case XSSFCell.CELL_TYPE_BOOLEAN:
                        celltemp.add(String.valueOf(sfcell.getBooleanCellValue()));
                        break;
                    case XSSFCell.CELL_TYPE_FORMULA:
                        celltemp.add(sfcell.getRawValue());
                        break;
                }
                //celltemp.add(sfcell);
            }
            resp.add(celltemp);
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,e, "Error",JOptionPane.ERROR_MESSAGE);
    }
return resp;
}
public void Tarea_programada(){
    try {
        tarea = new Programacion(hh_pro,mm_pro);
        tarea.init_Tarea();
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println(ex.getMessage());
    }
}
public static Dimension getsize() {
    return Main.size; //To change body of generated methods, choose Tools | Templates.
}
public static String getStackTrace(Exception e) {
    StringWriter sWriter = new StringWriter();
    PrintWriter pWriter = new PrintWriter(sWriter);
    e.printStackTrace(pWriter);
    return sWriter.toString();
}
public static Boolean getpermission(String url){
    boolean ret = false;
    try {
        URL ficheroUrl = new URL(url);
        BufferedReader in = new BufferedReader(new InputStreamReader(ficheroUrl.openStream()));
        String linea;
        while ((linea = in.readLine()) != null){
            return linea.equals("1");
        }
        in.close(); // Cerramos la conexión
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    return false;
}
public Boolean check_update(String url){
    try {
        URL ficheroUrl = new URL(url);
        BufferedReader in = new BufferedReader(new InputStreamReader(ficheroUrl.openStream()));
        String linea;
        while ((linea = in.readLine()) != null){
            if (linea.contains("VERSION=")){
                int ver = Integer.parseInt(linea.substring(8, linea.length()).replace(".",""));
                //System.out.println(ver);
                return ver>this.version;
            }
        }
        in.close(); // Cerramos la conexión
    }catch (IOException ex) {
        ex.printStackTrace();
        return false;
    }
    return false;
}
public String get_url_update(String url){
    try {
        URL ficheroUrl = new URL(url);
        BufferedReader in = new BufferedReader(new InputStreamReader(ficheroUrl.openStream()));
        String linea;
        while ((linea = in.readLine()) != null){
            if (linea.contains("LINK=")){
                return linea.substring(5, linea.length());
            }
        }
        in.close(); // Cerramos la conexión
    }catch (IOException ex) {
        ex.printStackTrace();
        return "";
    }
    return "";
}
public String get_ver_update(String url){
    try {
        URL ficheroUrl = new URL(url);
        BufferedReader in = new BufferedReader(new InputStreamReader(ficheroUrl.openStream()));
        String linea;
        while ((linea = in.readLine()) != null){
            if (linea.contains("VERSION=")){
                return linea.substring(8, linea.length()).replace(".","");
            }
        }
        in.close(); // Cerramos la conexión
    }catch (IOException ex) {
        ex.printStackTrace();
        return "";
    }
    return "";
}
public boolean get_conection(){
    Boolean ret=false;
    View_Conections vc = new View_Conections(this, true, ruta_file_option);
    vc.setLocationRelativeTo(null);
    vc.setVisible(true);
    String[] p = vc.getConection();
//    conection_list = vc.get_connections();
    if (p!=null) {
        if (p[1]!=null) {
            host = p[0];
            bd = p[1];
            if (conex!=null) {
                conex.setText(bd);
            }
            usu = p[2];
            cont = p[3];
            System.out.println("Host: "+host);
            System.out.println("DB: "+bd);
            System.out.println("Usuario DB: "+usu);
            System.out.println("Contraseña: "+cont);
            ret = true;
        }
    }
    return ret;
}
public String[] load_task(String r){
    String aux;
    String temp;
    String[] param = null;
    File arch = new File(r);
    if(arch.exists()){
        try{
            FileReader fr = new FileReader (arch);
            BufferedReader br = new BufferedReader(fr);
            while((aux=br.readLine())!=null){
                temp = Encriptar.Desencriptar(aux);
                param=temp.split("\\*");
            }
            br.close();
            fr.close();
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    return param;
}
}
