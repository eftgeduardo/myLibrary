/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUD;

import controller.AuthorJpaController;
import controller.UsersJpaController;
import controller.exceptions.NonexistentEntityException;
import entities.Author;
import entities.Users;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan
 */
public class CrudAuthor extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    public CrudAuthor() {
        initComponents();
        loadTable();
        btnAdd.setEnabled(true);
        btnDelete.setEnabled(false);
        btnModify.setEnabled(false);
    }
        private void loadTable(){
        DefaultTableModel model= new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("firstName");
        model.addColumn("lastName");
        model.addColumn("Password");
        model.addColumn("Admin");
        List<Author> listAuthor = new ArrayList<Author>();
        AuthorJpaController ajc =new AuthorJpaController();
        listAuthor = ajc.findAuthorEntities();
        System.out.println(listAuthor);
        Object data[] =new Object[4];
        
        //Load objects to table.
        for (Author a:listAuthor){
            
            data[0]=a.getId();
            data[1]=a.getFirstName();
            data[2]=a.getMiddleName();
            data[3]=a.getLastName();
            model.addRow(data);
        }
        tblUsers.setModel(model);
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jScrollPane = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();
        txtMiddleName = new javax.swing.JTextField();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();

        setLayout(null);

        lblLibrary.setText("Edit Author");
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

        lbIdBook.setText("firstName");
        add(lbIdBook);
        lbIdBook.setBounds(516, 86, 60, 16);

        lblIdUsers.setText("MiddleName");
        add(lblIdUsers);
        lblIdUsers.setBounds(516, 116, 70, 16);

        lblDate.setText("LastName");
        add(lblDate);
        lblDate.setBounds(516, 146, 70, 16);

        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblUsers.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblUsers.setDoubleBuffered(true);
        tblUsers.setVerifyInputWhenFocusTarget(false);
        tblUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsersMouseClicked(evt);
            }
        });
        jScrollPane.setViewportView(tblUsers);

        add(jScrollPane);
        jScrollPane.setBounds(6, 36, 500, 307);
        add(txtMiddleName);
        txtMiddleName.setBounds(586, 116, 210, 24);
        add(txtFirstName);
        txtFirstName.setBounds(586, 86, 210, 24);
        add(txtLastName);
        txtLastName.setBounds(586, 146, 210, 24);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Author author = new Author();
        //author.setId(Integer.parseInt(txtId.getText()));
        author.setFirstName(txtFirstName.getText());
        author.setMiddleName(txtMiddleName.getText());
        author.setLastName(txtLastName.getText());
        AuthorJpaController ajc = new AuthorJpaController();
        ajc.create(author);
        loadTable();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        AuthorJpaController ajc = new AuthorJpaController();
        try {
            ajc.destroy(Integer.parseInt(txtId.getText()));
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CrudAuthor.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtId.setText("");
        txtFirstName.setText("");
        txtMiddleName.setText("");
        txtLastName.setText("");
        loadTable();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        txtId.setText("");
        txtFirstName.setText("");
        txtMiddleName.setText("");
        txtLastName.setText("");
        
        btnAdd.setEnabled(true);
        btnDelete.setEnabled(false);
        btnModify.setEnabled(false);
        //        //dChoserDueDate.cleanup();
        //        //btnExit.setEnabled(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyActionPerformed
        Author author = new Author();
        author.setId(Integer.parseInt(txtId.getText()));
        author.setFirstName(txtFirstName.getText());
        author.setMiddleName(txtMiddleName.getText());
        author.setLastName(txtLastName.getText());
        AuthorJpaController ajc = new AuthorJpaController();
        try {
            ajc.edit(author);
        } catch (Exception ex) {
            Logger.getLogger(CrudAuthor.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();
    }//GEN-LAST:event_btnModifyActionPerformed

    private void tblUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsersMouseClicked
        
        
        txtId.setText(String.valueOf(tblUsers.getValueAt(tblUsers.getSelectedRow(),0)));
        txtFirstName.setText(String.valueOf(tblUsers.getValueAt(tblUsers.getSelectedRow(),1)));
        txtMiddleName.setText(String.valueOf(tblUsers.getValueAt(tblUsers.getSelectedRow(),2)));
        txtLastName.setText(String.valueOf(tblUsers.getValueAt(tblUsers.getSelectedRow(),3)));

        btnAdd.setEnabled(false);
        btnDelete.setEnabled(true);
        btnModify.setEnabled(true);
        btnExit.setEnabled(true);
    }//GEN-LAST:event_tblUsersMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnModify;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel lbIdBook;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblIdUsers;
    private javax.swing.JLabel lblLibrary;
    private javax.swing.JTable tblUsers;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtMiddleName;
    // End of variables declaration//GEN-END:variables
}
