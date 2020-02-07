/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.outhelp.GUI;

import com.jm.outhelp.GUI.*;
import com.jm.outhelp.Clases.CellRender_Cruce_Arl;
import com.jm.outhelp.Clases.ClipBoardActionListener;
import com.jm.outhelp.Clases.Conexion;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Johnnatan
 */
public class Busc_cruce_arl extends javax.swing.JDialog {
TextAutoCompleter tac_empleador = null;
DefaultTableModel modelo = null;
Object [] fila = new Object[16];
    /**
     * Creates new form Rep_cruce_arl
     */
    public Busc_cruce_arl(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/group_gear.png")));
        init();
        ac_empleador();
        tac_empleador.setMode(0);
        DefaultTableCellRenderer tcr = (DefaultTableCellRenderer)cruces.getDefaultRenderer(Object.class);
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        cruces.getColumnModel().getColumn(0).setCellRenderer(tcr);
        cruces.getColumnModel().getColumn(1).setCellRenderer(tcr);
        cruces.getColumnModel().getColumn(2).setCellRenderer(tcr);
        cruces.getColumnModel().getColumn(3).setCellRenderer(tcr);
    }
    public void init(){
        tac_empleador = new TextAutoCompleter(t_empleador);
        
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
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        t_empleador = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cruces = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/export_excel.png"))); // NOI18N
        jMenuItem1.setText("Exportar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Busqueda de Cruce ARL");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione la empresa"));

        jLabel2.setText("Nombre");

        t_empleador.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(t_empleador)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_empleador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Información descargada de la ARL"));

        new ClipBoardActionListener(cruces);
        cruces.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Cruce", "Nit Empresa", "Nombre Empresa", "Fecha Cruce"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        cruces.setToolTipText("");
        cruces.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(cruces);
        if (cruces.getColumnModel().getColumnCount() > 0) {
            cruces.getColumnModel().getColumn(0).setResizable(false);
            cruces.getColumnModel().getColumn(1).setResizable(false);
            cruces.getColumnModel().getColumn(2).setResizable(false);
            cruces.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Generar"));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/document_inspector.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.setToolTipText("Prresione para generar la vista previa del cruce.");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (!t_empleador.getText().equals("")) {
            if (check_empleador(t_empleador.getText())) {
                DefaultTableModel modelo = (DefaultTableModel)cruces.getModel();
                Object [] fila = new Object[3];
                if (cruces.getRowCount()>0) {
                    int j = cruces.getRowCount();
                    for (int i = 0; i < j; i++) {
                        modelo.removeRow(cruces.getRowCount()-1);
                        cruces.setModel(modelo);
                    }
                }
                Conexion con = new Conexion();
                con.conexion();
                ResultSet r;
                try {
                    r = con.s.executeQuery ("SELECT *\n" +
                                            "FROM\n" +
                                            "    t_cruce_arl\n" +
                                            "    INNER JOIN t_empresas \n" +
                                            "        ON (t_cruce_arl.ID_EMPRESA = t_empresas.ID_EMPRESA) \n" +
                                            "WHERE NOMBRE_EMPRESA='"+t_empleador.getText()+"' \n" +
                                            "ORDER BY FECHA_CRUCE DESC");
                while(r.next()){
                    modelo.addRow(fila);
                    modelo.setValueAt(r.getString("ID_CRUCE"),cruces.getRowCount()-1,0);
                    modelo.setValueAt(r.getString("ID_EMPRESA"),cruces.getRowCount()-1,1);
                    modelo.setValueAt(r.getString("NOMBRE_EMPRESA"),cruces.getRowCount()-1,2);
                    modelo.setValueAt(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(r.getString("FECHA_CRUCE"))),cruces.getRowCount()-1,3);
                }
                con.cerrar();
                }catch(SQLException | ParseException e){
                    e.printStackTrace();
                    con.cerrar();
                }
            }else{
                t_empleador.requestFocus();
                JOptionPane.showMessageDialog(null,"Verifique el nombre del empleador","Error",JOptionPane.ERROR_MESSAGE);
            }
        }else{
            t_empleador.requestFocus();
            JOptionPane.showMessageDialog(null,"Verifique el nombre del empleador","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        if (cruces.getSelectedRow()!=-1) {
            Conexion con = new Conexion();
            con.conexion();
            ResultSet r;
            try {
                r = con.s.executeQuery ("SELECT *\n" +
                                        "FROM\n" +
                                        "    t_cruce_arl\n" +
                                        "    INNER JOIN t_empresas \n" +
                                        "        ON (t_cruce_arl.ID_EMPRESA = t_empresas.ID_EMPRESA) \n" +
                                        "AND  ID_CRUCE = "+cruces.getValueAt(cruces.getSelectedRow(), 0)+"\n" +
                                        "ORDER BY FECHA_CRUCE DESC");
            if(r.next()){
                InputStream input = r.getBinaryStream("CRUCE");
                HSSFWorkbook workbook = new HSSFWorkbook(input);
                JFileChooser fc = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("Archivos Excel (*.xls)","xls");
                fc.setFileFilter(filter);
                fc.setSelectedFile(new File("Cruce_ARL_"+r.getString("NOMBRE_EMPRESA")+"_"+new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss").format(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(r.getString("FECHA_CRUCE")))+"_.xls"));
//                fc.setSelectedFile(new File("Cruce_ARL_.xls"));
                if(fc.showSaveDialog(this)==JFileChooser.APPROVE_OPTION){
                    FileOutputStream fileOut;
                    fileOut = new FileOutputStream(fc.getSelectedFile().getAbsolutePath());
                    workbook.write(fileOut);
                    fileOut.close();
                    JOptionPane.showMessageDialog(this,"El Cruce fue exportado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            con.cerrar();
            }catch(SQLException | IOException | ParseException e){
                e.printStackTrace();
                con.cerrar();
            }
        }else{
            JOptionPane.showMessageDialog(null,"Debe seleccionar un registro para exportarlo","Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(Busc_cruce_arl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Busc_cruce_arl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Busc_cruce_arl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Busc_cruce_arl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Busc_cruce_arl dialog = new Busc_cruce_arl(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable cruces;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField t_empleador;
    // End of variables declaration//GEN-END:variables

public void ac_empleador(){
    tac_empleador.removeAllItems();
    //tac_empleador.addItem("Seleccione..");
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
public boolean check_table(JTable j){
    boolean ret = true;
    modelo = (DefaultTableModel)j.getModel(); 
    for (int i = 0; i < j.getRowCount(); i++) {
        if(check_cedula(modelo.getValueAt(i, 0))){
            if(check_fecha(modelo.getValueAt(i, 2))){
                if (check_porc(modelo.getValueAt(i, 3).toString().replace(',', '.'))) {
                    
                }else{
                    j.changeSelection(i,3, false, false);
                    j.requestFocus();
                    JOptionPane.showMessageDialog(null,"El valor del porcentaje no es válido","Error",JOptionPane.ERROR_MESSAGE);
                    ret=false;
                    break;
                }
            }else{
                j.changeSelection(i,2, false, false);
                j.requestFocus();
                JOptionPane.showMessageDialog(null,"La fecha ingresada no es valida, verifique el formato","Error",JOptionPane.ERROR_MESSAGE);
                ret=false;
                break;
            }
        }else{
            j.changeSelection(i,0, false, false);
            j.requestFocus();
            JOptionPane.showMessageDialog(null,"Verifique el numero de cedula del empleado","Error",JOptionPane.ERROR_MESSAGE);
            ret=false;
            break;
        }
    }
    return ret;
}
public boolean check_cedula(Object ced){
    boolean ret=false;
    if (ced!=null) {
        if (chech_char(ced.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>,.")) {
            if(!ced.toString().trim().equals("")){
                if (comprobarLong(ced.toString().trim())) {
                    ret=true;
                }
            }
        }
    }
    return ret;  
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
public boolean check_porc(Object porc){
    boolean ret=true;
    if (porc!=null) {
        if (chech_char(porc.toString().trim(),"'#$%&()=?¡¿/*+[]{};:<>")) {
            try {
                Float p = Float.parseFloat(porc.toString());
                ret = true;
            } catch (Exception e) {
                ret = false;
            }
        }else{
            ret = false;
            //JOptionPane.showMessageDialog(null,"Verifique que el campo no contenga caracteres especiales","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    return ret;
}
}
