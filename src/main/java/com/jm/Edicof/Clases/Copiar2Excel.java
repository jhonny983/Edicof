/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.Clases;

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
import java.text.SimpleDateFormat;
//----------------------------------------------------------------------------------------------------------------------
public class Copiar2Excel implements ActionListener
{
private String rowstring,value;
private Clipboard system;
private StringSelection stringSelection,stsel;
private JTable jTable1 ;
int pasteRows=0;
private boolean btn=false;
Object [] fila = new Object[21];
//----------------------------------------------------------------------------------------------------------------------
public Copiar2Excel(JTable myJTable)
{
    jTable1 = myJTable;
    KeyStroke copy = KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK, false);
    //KeyStroke paste = KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK,false);
    jTable1.registerKeyboardAction(this,"Copy",copy, JComponent.WHEN_FOCUSED);
    //jTable1.registerKeyboardAction(this,"Paste",paste,JComponent.WHEN_FOCUSED);
    system = Toolkit.getDefaultToolkit().getSystemClipboard();
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
            jTable1.setValueAt(stTmp.nextToken (),rowCount+selectRow,colCount+selectCol);
            jTable1.changeSelection(rowCount+selectRow,colCount+selectCol, false, false);
            jTable1.requestFocus();
            colCount++;
        }
        rowCount++;
        }
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
    StringBuilder sbf = new StringBuilder();
    int rows=0,cols=0;
    if (btn) {
        rows=jTable1.getRowCount();
        cols=jTable1.getColumnCount();
    }else{
        
        jTable1.setColumnSelectionInterval(0, jTable1.getColumnCount() - 1);
        rows=jTable1.getSelectedRows().length;
        cols=jTable1.getSelectedColumns().length;
    }
    final int numcols = jTable1.getColumnCount();
    final int numrows = jTable1.getRowCount();
    final int[] rowsselected = jTable1.getSelectedRows();
    final int[] colsselected = jTable1.getSelectedColumns();
    System.out.println("RowCount: "+numrows);
    System.out.println("ColCount: "+numcols);
    System.out.println("RowSelectedCount: "+rowsselected.length);
    System.out.println("ColSelectedCount: "+colsselected.length);
    System.out.println("Rows: "+rows);
    System.out.println("Cols: "+cols);
    if (!((numrows - 1 >= rowsselected[rowsselected.length - 1] - rowsselected[0] && numrows >= rowsselected.length) && (numcols - 1 >= colsselected[colsselected.length - 1] - colsselected[0] && numcols >= colsselected.length))) {
            JOptionPane.showMessageDialog(null, "Invalid Copy Selection","Invalid Copy Selection",JOptionPane.ERROR_MESSAGE);
            return;
        }
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (j==21) {
                sbf.append(jTable1.getValueAt(rowsselected[i], colsselected[j]).toString().replace("\n", " - "));
            }else if(j==4 | j==5 | j==14 | j==15 | j==16 ){
                if (new SimpleDateFormat("yyyy-MM-dd").format(jTable1.getValueAt(rowsselected[i], colsselected[j])).equals("1900-01-01")) {
                    sbf.append("");
                }else{
                    sbf.append(new SimpleDateFormat("yyyy-MM-dd").format(jTable1.getValueAt(rowsselected[i], colsselected[j])));
                }
            }else if (jTable1.getValueAt(rowsselected[i], colsselected[j])!=null){
                sbf.append(jTable1.getValueAt(rowsselected[i], colsselected[j]));
            }else{
                sbf.append("");
            }
            if (j < numcols - 1) {
                sbf.append('\t');
            }
        }
        sbf.append('\n');
    }
    stsel = new StringSelection(sbf.toString());
    system = Toolkit.getDefaultToolkit().getSystemClipboard();
    system.setContents(stsel, stsel);
    if (btn) {
//        jTable1.setColumnSelectionAllowed(false);
        jTable1.getSelectionModel().clearSelection();
//        this.btn=false;
    }
    setBtn(false);
    JOptionPane.showMessageDialog(null,"Los datos se copiaron con exito","Información",JOptionPane.INFORMATION_MESSAGE);
}
//----------------------------------------------------------------------------------------------------------------------
    @Override
public void actionPerformed(ActionEvent e){
        if (e.getActionCommand ().compareTo ("Copy")==0) {
            copyaction();
        }    
    }

public void setBtn(boolean btn) {
    this.btn = btn;
}



}
