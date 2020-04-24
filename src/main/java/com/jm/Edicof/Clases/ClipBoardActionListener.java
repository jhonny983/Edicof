/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.Clases;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class ClipBoardActionListener implements ActionListener{
    private static final String LINE_BREAK = "\r"; 
    private static final String CELL_BREAK = "\t"; 
    private Clipboard CLIPBOARD; 
    private JTable table=null; 
    private Object [] fila = new Object[10];
    int pasteRows=0;
    int pasteCols=0;
    int marginCols=0;
    int marginRows=0;

    public ClipBoardActionListener(JTable table) { 
        this.table = table; 
        KeyStroke paste = KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK,false);
        KeyStroke copy = KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK,false);
        KeyStroke cut = KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK,false);
        table.registerKeyboardAction(this,"Paste",paste,JComponent.WHEN_FOCUSED);
        table.registerKeyboardAction(this,"Copy",copy,JComponent.WHEN_FOCUSED);
        table.registerKeyboardAction(this,"Cut",cut,JComponent.WHEN_FOCUSED);
        CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard();
    } 
    private void showErrorMessage(String msg){
        JOptionPane.showMessageDialog(null, msg,"Error al pegar",JOptionPane.ERROR_MESSAGE);
    }
    
    private void copyToClipboard(boolean isCut) { 
        String aux=null;
        int numCols = 0;
        int[] colsSelected=null;
        if (!table.getCellSelectionEnabled()) {
            numCols=table.getColumnCount();
            colsSelected = new int[numCols];
            for (int i = 0; i < numCols; i++) {
                colsSelected[i]=i;
            }
        }else{
            numCols=table.getSelectedColumnCount();
            colsSelected=table.getSelectedColumns();
        }
        int numRows=table.getSelectedRowCount(); 
        int[] rowsSelected=table.getSelectedRows(); 
        if (numRows!=rowsSelected[rowsSelected.length-1]-rowsSelected[0]+1 || numRows!=rowsSelected.length || numCols!=colsSelected[colsSelected.length-1]-colsSelected[0]+1 || numCols!=colsSelected.length) {
            JOptionPane.showMessageDialog(null, "Invalid Copy Selection", "Invalid Copy Selection", JOptionPane.ERROR_MESSAGE);
            return; 
        } 
        StringBuffer excelStr=new StringBuffer(); 
        for (int i=0; i<numRows; i++) {
                for (int j=0; j<numCols; j++) { 
                    Object test = table.getValueAt(rowsSelected[i], colsSelected[j]);
                    if (test instanceof java.util.Date) {
                        aux = new SimpleDateFormat("dd-MM-yyyy").format(table.getValueAt(rowsSelected[i], colsSelected[j]));
                        if (aux.equals("01-01-1900")) {
                            aux="";
                        }
                    }else{
                        if (test.toString().contains("%")) {
                            aux=test.toString().replace("%","");
                        }else{
                            aux = table.getValueAt(rowsSelected[i], colsSelected[j]).toString();
                        }
                    }
                    excelStr.append(escape(aux)); 
                    //excelStr.append(escape(table.getValueAt(rowsSelected[i], colsSelected[j]))); 
                    if (isCut) { 
                            table.setValueAt(null, rowsSelected[i], colsSelected[j]); 
                    } 
                    if (j<numCols-1) { 
                            excelStr.append("\t"); 
                    } 
                } 
            excelStr.append("\n"); 
        }   
        StringSelection sel  = new StringSelection(excelStr.toString()); 
        CLIPBOARD.setContents(sel, sel); 
    } 

    private void pasteFromClipboard() { 
        int startRow=table.getSelectedRows()[0]; 
        int startCol=table.getSelectedColumns()[0];

        String pasteString = ""; 
        try { 
            pasteString = (String)(CLIPBOARD.getContents(this).getTransferData(DataFlavor.stringFlavor)); 
            if(pasteString==null) {
                showErrorMessage("El portapapeles esta vacio");
                return;
            }
            int selectCol=table.getSelectedColumn();
            int selectRow=table.getSelectedRow();
            if(selectCol<0||selectRow<0) {
                showErrorMessage("Seleccione una celda para inicar a pegar la información");
                return;
            }
            String[] lines = pasteString.split("\n");
            String[] cells = lines[0].split(CELL_BREAK);
            pasteRows=lines.length;
            pasteCols=cells.length;
            marginCols=table.getColumnCount()-selectCol;
            marginRows=table.getRowCount()-selectRow;
            if(marginCols<pasteCols || marginRows<pasteRows){
                showErrorMessage("La tabla no posee el espacio suficiente para pegar los datos");
                return;
            }
            lines = pasteString.split("\n");
            for (int i=0 ; i<lines.length; i++) {
                cells = lines[i].split(CELL_BREAK); 
                for (int j=0 ; j<cells.length; j++) { 
                    if (table.getRowCount()>startRow+i && table.getColumnCount()>startCol+j) { 
                        Object test = cells[j];
                        if (test.toString().contains("%")) {
                            test = test.toString().replace("%","");
                        }
                        table.setValueAt(test, startRow+i, startCol+j); 
                        table.changeSelection(startRow+i,startCol+j, false, false);
                        table.requestFocus();
                    } 
                } 
            }

        } catch (UnsupportedFlavorException | IOException e) { 
            showErrorMessage("Invalid Paste Type");
            return; 
        }
    } 

    private void cancelEditing() { 
        if (table.getCellEditor() != null) { 
            table.getCellEditor().cancelCellEditing(); 
        } 
    } 

    private String escape(Object cell) { 
        return cell.toString().replace(LINE_BREAK, " ").replace(CELL_BREAK, " "); 
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand ()) {
            case "Copy":
                // Copy
                cancelEditing();
                copyToClipboard(false);
                break;
            case "Cut":
                // Cut
                cancelEditing();
                copyToClipboard(true);
                break;
            case "Paste": 
                // Paste
                cancelEditing();
                DefaultTableModel modelo = (DefaultTableModel) table.getModel();
                String pasteString = ""; 
                try { 
                    pasteString = (String)(CLIPBOARD.getData(DataFlavor.stringFlavor)); 
                    if(pasteString==null) {
                        showErrorMessage("El portapapeles esta vacio");
                        return;
                    }
                    int selectCol=table.getSelectedColumn();
                    int selectRow=table.getSelectedRow();
                    if(selectCol<0||selectRow<0) {
                        showErrorMessage("Seleccione una celda para inicar a pegar la información");
                        return;
                    }
                    String[] lines = pasteString.split("\n");
                    String[] cells = lines[0].split(CELL_BREAK);
                    pasteRows=lines.length;
                    pasteCols=cells.length;
                    System.out.println("Filas a pegar: "+pasteRows);
                    System.out.println("Columnas a pegar: "+pasteCols);
                    System.out.println("Filas de la tabla: "+table.getRowCount());
                    System.out.println("Columnas de la tabla: "+table.getColumnCount());
                    while(table.getRowCount()<pasteRows){
                        modelo.addRow(fila);
                        table.setModel(modelo);
                    }
                    pasteFromClipboard();
                } catch (UnsupportedFlavorException | IOException evt) { 
                    showErrorMessage("Invalid Paste Type");
                    break; 
                }
                break;
            default:
                break;
        }
    }
}