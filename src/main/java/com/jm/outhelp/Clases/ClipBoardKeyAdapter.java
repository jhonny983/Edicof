/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.outhelp.Clases;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author ADMIN
 */
public class ClipBoardKeyAdapter extends KeyAdapter {
    private static final String LINE_BREAK = "\r"; 
    private static final String CELL_BREAK = "\t"; 
    private static final Clipboard CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard(); 
    private JTable table=null; 

    public ClipBoardKeyAdapter(JTable table) { 
            this.table = table; 
    } 

    @Override 
    public void keyReleased(KeyEvent event) { 
        if (event.isControlDown()) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_C:
                    // Copy
                    cancelEditing();
                    copyToClipboard(false);
                    break;
                case KeyEvent.VK_X:
                    // Cut
//                    cancelEditing();
//                    copyToClipboard(true);
                    break;
                case KeyEvent.VK_V: 
                    // Paste
//                    cancelEditing();
//                    pasteFromClipboard();
                    break;
                default:
                    break;
            }
        } 
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
                    if (table.getValueAt(rowsSelected[i], colsSelected[j]) instanceof java.util.Date) {
                        aux = new SimpleDateFormat("dd-MM-yyyy").format(table.getValueAt(rowsSelected[i], colsSelected[j]));
                        if (aux.equals("01-01-1900")) {
                            aux="";
                        }
                    }else{
                        aux = table.getValueAt(rowsSelected[i], colsSelected[j]).toString();
                    }
                    excelStr.append(escape(aux)); 
                    //excelStr.append(escape(table.getValueAt(rowsSelected[i], colsSelected[j]))); 
                    if (isCut) { 
                            table.setValueAt(null, rowsSelected[i], colsSelected[j]); 
                    } 
                    if (j<numCols-1) { 
                            excelStr.append(CELL_BREAK); 
                    } 
                } 
            excelStr.append(LINE_BREAK); 
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
        } catch (UnsupportedFlavorException | IOException e) { 
            JOptionPane.showMessageDialog(null, "Invalid Paste Type", "Invalid Paste Type", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        String[] lines = pasteString.split(LINE_BREAK);
        for (int i=0 ; i<lines.length; i++) {
                String[] cells = lines[i].split(CELL_BREAK); 
                for (int j=0 ; j<cells.length; j++) { 
                        if (table.getRowCount()>startRow+i && table.getColumnCount()>startCol+j) { 
                                table.setValueAt(cells[j], startRow+i, startCol+j); 
                        } 
                } 
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
}