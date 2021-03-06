/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUD;

import controller.BooksJpaController;
import controller.BorrowedJpaController;
import controller.FinesJpaController;
import controller.UsersJpaController;
import controller.exceptions.NonexistentEntityException;
import entities.Books;
import entities.Borrowed;
import entities.Fines;
import entities.Users;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan
 */
public class CrudFines extends javax.swing.JPanel {

    /**
     * Creates new form panelPrueba
     */
    public CrudFines() {
        initComponents();
        loadTable();
        btnAdd.setEnabled(true);
        btnDelete.setEnabled(false);
        btnModify.setEnabled(false);
        
    }
    private void loadTable(){
        DefaultTableModel model= new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("IdUsers");
        model.addColumn("IdBorrowed");
        model.addColumn("Fine Cost");
        model.addColumn("Days Late");
        model.addColumn("Paid");
        List<Fines> listFines = new ArrayList<Fines>();
        FinesJpaController fjc =new FinesJpaController();
        listFines = fjc.findFinesEntities();

        Object data[] =new Object[6];
        
        //Load objects to table.
        for (Fines f:listFines){
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            data[0]=f.getId();
            data[1]=f.getIdUsers();
            data[2]=f.getIdBorrowed();
            data[3]=f.getFineCost();
            data[4]=f.getDaysLate();
            data[5]=f.getPaid();
            model.addRow(data);
        }
        //load comboBox users
        for (Users u:(new UsersJpaController()).findUsersEntities()){
            cbIDUsers.addItem(u);
        }
        //load Combobox Borrowed
        for (Borrowed b:(new BorrowedJpaController()).findBorrowedEntities()){
            cbIDBorrowed.addItem(b);
        }
        tblFines.setModel(model);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupReturned = new javax.swing.ButtonGroup();
        lblLibrary = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnModify = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        lblId = new javax.swing.JLabel();
        lbIdBook = new javax.swing.JLabel();
        lblIdUsers = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblDueDate = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        tblFines = new javax.swing.JTable();
        ReturnedTrue = new javax.swing.JRadioButton();
        ReturnedFalse = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        txtFine = new javax.swing.JTextField();
        txtDaysLate = new javax.swing.JTextField();
        cbIDUsers = new javax.swing.JComboBox<>();
        cbIDBorrowed = new javax.swing.JComboBox<>();

        setLayout(null);

        lblLibrary.setText("Edit Fines");
        add(lblLibrary);
        lblLibrary.setBounds(226, 6, 80, 16);

        btnAdd.setText("add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        add(btnAdd);
        btnAdd.setBounds(530, 290, 50, 24);

        btnDelete.setText("delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        add(btnDelete);
        btnDelete.setBounds(590, 290, 70, 24);

        btnExit.setText("exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        add(btnExit);
        btnExit.setBounds(750, 290, 60, 24);

        btnModify.setText("modify");
        btnModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyActionPerformed(evt);
            }
        });
        add(btnModify);
        btnModify.setBounds(670, 290, 70, 24);

        txtId.setEditable(false);
        add(txtId);
        txtId.setBounds(586, 56, 60, 24);

        lblId.setText("ID");
        add(lblId);
        lblId.setBounds(516, 56, 12, 16);

        lbIdBook.setText("ID Users");
        add(lbIdBook);
        lbIdBook.setBounds(516, 86, 60, 16);

        lblIdUsers.setText("ID Borrowed");
        add(lblIdUsers);
        lblIdUsers.setBounds(516, 116, 68, 16);

        lblDate.setText("Fine Cost");
        add(lblDate);
        lblDate.setBounds(516, 146, 70, 16);

        lblDueDate.setText("Days Late");
        add(lblDueDate);
        lblDueDate.setBounds(516, 176, 60, 16);

        tblFines.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblFines.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblFines.setDoubleBuffered(true);
        tblFines.setVerifyInputWhenFocusTarget(false);
        tblFines.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFinesMouseClicked(evt);
            }
        });
        jScrollPane.setViewportView(tblFines);

        add(jScrollPane);
        jScrollPane.setBounds(6, 36, 500, 307);

        btnGroupReturned.add(ReturnedTrue);
        ReturnedTrue.setText("yes");
        ReturnedTrue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnedTrueActionPerformed(evt);
            }
        });
        add(ReturnedTrue);
        ReturnedTrue.setBounds(600, 220, 51, 28);

        btnGroupReturned.add(ReturnedFalse);
        ReturnedFalse.setSelected(true);
        ReturnedFalse.setText("no");
        add(ReturnedFalse);
        ReturnedFalse.setBounds(600, 250, 46, 28);

        jLabel1.setText("Paid");
        add(jLabel1);
        jLabel1.setBounds(520, 230, 70, 16);

        txtFine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFineActionPerformed(evt);
            }
        });
        add(txtFine);
        txtFine.setBounds(586, 146, 210, 24);

        txtDaysLate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDaysLateActionPerformed(evt);
            }
        });
        add(txtDaysLate);
        txtDaysLate.setBounds(586, 176, 210, 24);

        cbIDUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIDUsersActionPerformed(evt);
            }
        });
        add(cbIDUsers);
        cbIDUsers.setBounds(586, 86, 210, 26);

        cbIDBorrowed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIDBorrowedActionPerformed(evt);
            }
        });
        add(cbIDBorrowed);
        cbIDBorrowed.setBounds(586, 116, 210, 26);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Fines fines = new Fines();
        //fines.setId(Integer.parseInt(txtId.getText()));
        fines.setIdUsers((Users)cbIDUsers.getSelectedItem());
        fines.setIdBorrowed((Borrowed)cbIDBorrowed.getSelectedItem());
        fines.setFineCost(Float.parseFloat(txtFine.getText()));
        fines.setDaysLate(Integer.parseInt(txtDaysLate.getText()));
        fines.setPaid(ReturnedTrue.isSelected());
        FinesJpaController fjc = new FinesJpaController();
        fjc.create(fines);
        loadTable();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        FinesJpaController fjc = new FinesJpaController();
        try {
            fjc.destroy(Integer.parseInt(txtId.getText()));
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CrudFines.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();
        txtId.setText("");
        cbIDBorrowed.setSelectedIndex(0);
        cbIDUsers.setSelectedIndex(0);
        
        
        txtFine.setText("");
        txtDaysLate.setText("");
        ReturnedFalse.setSelected(true);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        btnAdd.setEnabled(true);
        btnDelete.setEnabled(false);
        btnModify.setEnabled(false);
        
        txtId.setText("");
        cbIDBorrowed.setSelectedIndex(0);
        cbIDUsers.setSelectedIndex(0);
        
        
        txtFine.setText("");
        txtDaysLate.setText("");
        ReturnedFalse.setSelected(true);
        
        //btnExit.setEnabled(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnExitActionPerformed

    private void tblFinesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFinesMouseClicked
//        System.out.println(tblBorrowed.getValueAt(0, 1).getClass());
        FinesJpaController fjc = new FinesJpaController();
        
        txtId.setText((String.valueOf(tblFines.getValueAt(tblFines.getSelectedRow(),0))));
        cbIDUsers.getModel().setSelectedItem(tblFines.getValueAt(tblFines.getSelectedRow(),1));
        cbIDBorrowed.getModel().setSelectedItem(tblFines.getValueAt(tblFines.getSelectedRow(),2));
        txtFine.setText(String.valueOf(tblFines.getValueAt(tblFines.getSelectedRow(),3)));
        txtDaysLate.setText(String.valueOf(tblFines.getValueAt(tblFines.getSelectedRow(),4)));
        ReturnedTrue.setSelected(Boolean.parseBoolean(String.valueOf(tblFines.getValueAt(tblFines.getSelectedRow(),5))));
        
        ReturnedFalse.setSelected(!Boolean.parseBoolean(String.valueOf(tblFines.getValueAt(tblFines.getSelectedRow(),5))));
        
        btnAdd.setEnabled(false);
        btnDelete.setEnabled(true);
        btnModify.setEnabled(true);
        btnExit.setEnabled(true);
        
    }//GEN-LAST:event_tblFinesMouseClicked

    private void btnModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyActionPerformed

        Fines fines = new Fines();
        fines.setId(Integer.parseInt(txtId.getText()));
        fines.setIdUsers((Users)cbIDUsers.getSelectedItem());
        fines.setIdBorrowed((Borrowed)cbIDBorrowed.getSelectedItem());
        fines.setFineCost(Float.parseFloat(txtFine.getText()));
        fines.setDaysLate(Integer.parseInt(txtDaysLate.getText()));
        fines.setPaid(ReturnedTrue.isSelected());
        FinesJpaController fjc = new FinesJpaController();
        try {
            fjc.edit(fines);
        } catch (Exception ex) {
            Logger.getLogger(CrudFines.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();
    }//GEN-LAST:event_btnModifyActionPerformed

    private void ReturnedTrueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnedTrueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ReturnedTrueActionPerformed

    private void txtFineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFineActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFineActionPerformed

    private void txtDaysLateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDaysLateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDaysLateActionPerformed

    private void cbIDUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIDUsersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbIDUsersActionPerformed

    private void cbIDBorrowedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIDBorrowedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbIDBorrowedActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton ReturnedFalse;
    private javax.swing.JRadioButton ReturnedTrue;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.ButtonGroup btnGroupReturned;
    private javax.swing.JButton btnModify;
    private javax.swing.JComboBox<Object
    > cbIDBorrowed;
    private javax.swing.JComboBox<Object
    > cbIDUsers;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel lbIdBook;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDueDate;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblIdUsers;
    private javax.swing.JLabel lblLibrary;
    private javax.swing.JTable tblFines;
    private javax.swing.JTextField txtDaysLate;
    private javax.swing.JTextField txtFine;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables
}
