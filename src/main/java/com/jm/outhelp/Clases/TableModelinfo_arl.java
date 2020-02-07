/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.outhelp.Clases;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class TableModelinfo_arl extends DefaultTableModel{
    public TableModelinfo_arl(Object rowData[][], Object columnNames[]) {
        super(rowData, columnNames);
    }
    boolean[] canEdit = new boolean [] {
        true, true, true, true
    };
    Class[] types = new Class [] {
        Long.class,
        java.lang.Object.class,
        java.util.Date.class,
        Float.class
    };
    
    @Override
    public Class getColumnClass(int col) {
        //return getValueAt(0, col).getClass();        
        return types[col];
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
    
}
