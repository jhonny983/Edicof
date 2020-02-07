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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
//----------------------------------------------------------------------------------------------------------------------
public class PegarExcel_cruce_arl implements ActionListener
{
private String rowstring,value;
private Clipboard system;
private StringSelection stringSelection,stsel;
private JTable jTable1 ;
int pasteRows=0;
Object [] fila = new Object[16];
//----------------------------------------------------------------------------------------------------------------------
public PegarExcel_cruce_arl(JTable myJTable)
{
jTable1 = myJTable;

KeyStroke paste = KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK,false);

jTable1.registerKeyboardAction(this,"Paste",paste,JComponent.WHEN_FOCUSED);

system = Toolkit.getDefaultToolkit().getSystemClipboard();
}
//----------------------------------------------------------------------------------------------------------------------
public JTable getJTable() {return jTable1;}
//----------------------------------------------------------------------------------------------------------------------
public void setJTable(JTable jTable1) {this.jTable1=jTable1;}
//----------------------------------------------------------------------------------------------------------------------
void showErrorMessage(String msg)
{
JOptionPane.showMessageDialog(null, msg,
msg,
JOptionPane.ERROR_MESSAGE);
}
//----------------------------------------------------------------------------------------------------------------------
void pasteAction(){
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
                if (colCount==0) {
                    jTable1.setValueAt(Long.parseLong(stTmp.nextToken()),rowCount+selectRow,colCount+selectCol);
                    jTable1.changeSelection(rowCount+selectRow,colCount+selectCol, false, false);
                    jTable1.requestFocus();
                    colCount++;
                }else{
                    if (colCount==1) {
                        jTable1.setValueAt(stTmp.nextToken(),rowCount+selectRow,colCount+selectCol);
                        jTable1.changeSelection(rowCount+selectRow,colCount+selectCol, false, false);
                        jTable1.requestFocus();
                        colCount++;
                    }else{
                        if (colCount==2) {
                            jTable1.setValueAt(new SimpleDateFormat("dd-MM-yyyy").parse(stTmp.nextToken()),rowCount+selectRow,colCount+selectCol);
                            jTable1.changeSelection(rowCount+selectRow,colCount+selectCol, false, false);
                            jTable1.requestFocus();
                            colCount++;
                        }else{
                            if (colCount==3) {
                                jTable1.setValueAt(Float.parseFloat(stTmp.nextToken()),rowCount+selectRow,colCount+selectCol);
                                jTable1.changeSelection(rowCount+selectRow,colCount+selectCol, false, false);
                                jTable1.requestFocus();
                                colCount++;
                            }else{

                            }
                        }
                    }
                }
                
            }
            rowCount++;
        }
    }
    catch(UnsupportedFlavorException uf){
        System.out.println ("uf="+uf.getMessage ());
    }
    catch(IOException io){
        System.out.println ("io="+io.getMessage ());
    } catch (ParseException ex) {
        System.out.println ("pe="+ex.getMessage ());
    }

}
//----------------------------------------------------------------------------------------------------------------------
public void actionPerformed(ActionEvent e){
    if(e.getActionCommand ().compareTo ("Paste")==0){   
        //System.out.println("pegar");
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        //System.out.println("Filas actuales: "+jTable1.getRowCount());
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
        }
        catch(UnsupportedFlavorException uf){
            System.out.println ("uf="+uf.getMessage ());
        }
        catch(IOException io){
            System.out.println ("io="+io.getMessage ());
        }
        //System.out.println("Filas actuales: "+pasteRows);
        while(jTable1.getRowCount()<pasteRows){
            //System.out.println("qwert");
            modelo.addRow(fila);
            jTable1.setModel(modelo);
        }
        pasteAction();
        return;
    }
}
}
