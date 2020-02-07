/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.outhelp.Clases;

/**
 *
 * @author ADMIN
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.datatransfer.*;
import java.util.*;
import java.io.IOException;
import java.text.NumberFormat;
import javax.swing.table.DefaultTableModel;
//----------------------------------------------------------------------------------------------------------------------
public class Copiar2Excel_CruceTesoreria implements ActionListener
{
private String rowstring,value;
private Clipboard system;
private StringSelection stringSelection,stsel;
private JTable jTable1 ;
int pasteRows=0;
private boolean btn=false;
Object [] fila = new Object[21];
DefaultTableModel model;
//----------------------------------------------------------------------------------------------------------------------
public Copiar2Excel_CruceTesoreria(JTable myJTable)
{
    jTable1 = myJTable;
    KeyStroke copy = KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK, false);
    KeyStroke paste = KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK,false);
    jTable1.registerKeyboardAction(this,"Copy",copy, JComponent.WHEN_FOCUSED);
    jTable1.registerKeyboardAction(this,"Paste",paste,JComponent.WHEN_FOCUSED);
    system = Toolkit.getDefaultToolkit().getSystemClipboard();
    model=(DefaultTableModel) myJTable.getModel();
}
//----------------------------------------------------------------------------------------------------------------------
public JTable getJTable() {
    return jTable1;
}
//----------------------------------------------------------------------------------------------------------------------
public void setJTable(JTable jTable1) {
    this.jTable1=jTable1;
}
//----------------------------------------------------------------------------------------------------------------------
void showErrorMessage(String msg){
    JOptionPane.showMessageDialog(null, msg,"Error",JOptionPane.ERROR_MESSAGE);
}
//----------------------------------------------------------------------------------------------------------------------
public void pasteAction(){
    system = Toolkit.getDefaultToolkit().getSystemClipboard();
    try{
        String data= (String)system.getData(DataFlavor.stringFlavor);
        if(data==null) {
            showErrorMessage("No data on clipboard");
            return;
        }
        int selectCol=jTable1.getSelectedColumn();
        int selectRow=jTable1.getSelectedRow();
        if(selectCol<0||selectRow<0) {
            showErrorMessage("Please Select cell");
            return;
        }
        //devuelve clipboard contenido
        StringTokenizer st,stTmp;
        st=new StringTokenizer(data,"\n");
        pasteRows=st.countTokens ();
        st=new StringTokenizer(st.nextToken ().trim (),"\t");
        int pasteCols=st.countTokens ();
        int marginCols=jTable1.getColumnCount()-selectCol;
        int marginRows=jTable1.getRowCount()-selectRow;
        //revisa espacio disponible
        if(marginCols<pasteCols || marginRows<pasteRows){
            showErrorMessage("La tabla no posee el espacio suficiente para pegar los datos");
            return;
        }
        st=new StringTokenizer (data,"\n");
        int rowCount=0,colCount;
        //copia a la tabla
        while(st.hasMoreTokens()){
            stTmp=new StringTokenizer (st.nextToken (),"\t");
            colCount=0;
            while(stTmp.hasMoreTokens ()){
                if (jTable1.getModel().isCellEditable(rowCount+selectRow,colCount+selectCol)) {
                    jTable1.setValueAt(stTmp.nextToken ().replace("\n"," - "),rowCount+selectRow,colCount+selectCol);
                    jTable1.changeSelection(rowCount+selectRow,colCount+selectCol, false, false);
                    //jTable1.getCellEditor().stopCellEditing();
                    jTable1.requestFocus();
                    colCount++;
                }else{
                    stTmp.nextToken();
                    jTable1.changeSelection(rowCount+selectRow,colCount+selectCol, false, false);
                    jTable1.requestFocus();
                    colCount++;
                }
            }
        rowCount++;
        }
        //jTable1.getCellEditor().stopCellEditing();
    }
    catch(UnsupportedFlavorException uf){
        System.out.println ("uf="+uf.getMessage ());
    }
    catch(IOException io){
        System.out.println ("io="+io.getMessage ());
    }

}
//----------------------------------------------------------------------------------------------------------------------
public void copyaction(){
    Locale locale = new Locale("es","CO"); // elegimos Argentina
    NumberFormat nf = NumberFormat.getNumberInstance(locale);
    StringBuilder sbf = new StringBuilder();
    // Check to ensure we have selected only a contiguous block of cells.

    // Select rows from start to end if start is 0 we change to 1 or leave it (used to preserve coloums headers)

    final int numcols = jTable1.getColumnCount();
    final int numrows = jTable1.getRowCount();
    final int[] rowsselected = jTable1.getSelectedRows();
    final int[] colsselected = jTable1.getSelectedColumns();
    System.out.println("RowCount: "+numrows);
    System.out.println("ColCount: "+numcols);
    System.out.println("RowSelectedCount: "+rowsselected.length);
    System.out.println("ColSelectedCount: "+colsselected.length);
    System.out.println("rowsselected[rowsselected.length - 1]: "+(rowsselected[rowsselected.length - 1]) );
    System.out.println("rowsselected[0]: "+(rowsselected[0]) );
    System.out.println("colsselected[colsselected.length - 1]: "+(colsselected[colsselected.length - 1]) );
    System.out.println("colsselected[0]: "+(colsselected[0]) );
    System.out.println(": "+(numrows - 1 >= rowsselected[rowsselected.length - 1] - rowsselected[0] && numrows >= rowsselected.length));
    System.out.println(": "+(numcols - 1 >= colsselected[colsselected.length - 1] - colsselected[0] && numcols >= colsselected.length));
    if (!((numrows - 1 >= rowsselected[rowsselected.length - 1] - rowsselected[0] && numrows >= rowsselected.length) && (numcols - 1 >= colsselected[colsselected.length - 1] - colsselected[0] && numcols >= colsselected.length))) {
        JOptionPane.showMessageDialog(null, "Invalid Copy Selection","Invalid Copy Selection",JOptionPane.ERROR_MESSAGE);
        return;
    }
    for (int i = 0; i < rowsselected.length; i++) {
        for (int j = 0; j < colsselected.length; j++) {
            //System.out.println("x_y: "+i+","+j);
            sbf.append(jTable1.getValueAt(rowsselected[i], colsselected[j]).toString().replace("\n"," - ").replace('.',','));
            if (j < colsselected.length-1) {
                sbf.append('\t');
            }
        }
        sbf.append('\n');
    }
    stsel = new StringSelection(sbf.toString());
    system = Toolkit.getDefaultToolkit().getSystemClipboard();
    system.setContents(stsel, stsel);
//    if (btn) {
//        jTable1.setColumnSelectionAllowed(false);
//        jTable1.getSelectionModel().clearSelection();
//    }
//    setBtn(false);
//    JOptionPane.showMessageDialog(null,"Los datos se copiaron con exito","Información",JOptionPane.INFORMATION_MESSAGE);
}
//----------------------------------------------------------------------------------------------------------------------
    @Override
public void actionPerformed(ActionEvent e){
        if (e.getActionCommand ().compareTo ("Copy")==0) {
            copyaction();
        }else if (e.getActionCommand ().compareTo ("Paste")==0) {
            pasteAction();
        }
   
    }

public void setBtn(boolean btn) {
    this.btn = btn;
}



}
