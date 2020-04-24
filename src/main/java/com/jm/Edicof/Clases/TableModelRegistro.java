/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.Clases;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class TableModelRegistro extends DefaultTableModel{
    public TableModelRegistro(Object rowData[][], Object columnNames[]) {
        super(rowData, columnNames);
    }
    boolean[] canEdit = new boolean [] { false, false, false, false};
    Class[] types = new Class [] {
        java.lang.Object.class,
        java.util.Date.class,
        java.util.Date.class,
        java.lang.Object.class
    };
    
    @Override
    public Class getColumnClass(int col) {
        //return types [col];
        return getValueAt(0, col).getClass();        
//return types[col];
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
    
}
