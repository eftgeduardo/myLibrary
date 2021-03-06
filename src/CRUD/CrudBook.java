/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUD;

import controller.AuthorJpaController;
import controller.BooksJpaController;
import controller.ShelvesJpaController;
import controller.exceptions.NonexistentEntityException;
import entities.Author;
import entities.Books;
import entities.Shelves;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan
 */
public class CrudBook extends javax.swing.JPanel {

    /**
     * Creates new form CrudBook3
     */
    public CrudBook() {
        initComponents();
        loadTable();
        btnAdd.setEnabled(true);
        btnDelete.setEnabled(false);
        btnModify.setEnabled(false);
    }
    private void loadTable(){
        DefaultTableModel model= new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("Title");
        model.addColumn("Editorial");
        model.addColumn("id Author");
        model.addColumn("Existences");
        model.addColumn("id Shelve");
        List<Books> listBooks = new ArrayList<Books>();
        BooksJpaController bjc =new BooksJpaController();
        listBooks = bjc.findBooksEntities();

        Object data[] =new Object[6];
        
        //Load objects to table.
        for (Books b:listBooks){
            data[0]=b.getId();
            data[1]=b.getTitle();
            data[2]=b.getEditorial();
            data[3]=b.getIdAuthor();
            data[4]=b.getExistences();
            data[5]=b.getIdShelve();
            model.addRow(data);
        }
        //load comboBox users
        for (Shelves s:(new ShelvesJpaController()).findShelvesEntities()){
            cbIDShelve.addItem(s);
        }
        //load Combobox Books
        for (Author a:(new AuthorJpaController()).findAuthorEntities()){
            cbIDAuthor.addItem(a);
        }
        tblBooks.setModel(model);

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
        lblId = new javax.swing.JLabel();
        lbIdBook = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        tblBooks = new javax.swing.JTable();
        lblShelve = new javax.swing.JLabel();
        cbIDShelve = new javax.swing.JComboBox<>();
        txtExistences = new javax.swing.JTextField();
        lblExistences = new javax.swing.JLabel();
        lblAuthor = new javax.swing.JLabel();
        cbIDAuthor = new javax.swing.JComboBox<>();
        txtEditorial = new javax.swing.JTextField();
        lblEditorial = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();

        setLayout(null);

        lblLibrary.setText("Edit Books");
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

        lblId.setText("ID");
        add(lblId);
        lblId.setBounds(516, 56, 12, 16);

        lbIdBook.setText("Title");
        add(lbIdBook);
        lbIdBook.setBounds(516, 86, 60, 16);

        tblBooks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblBooks.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblBooks.setDoubleBuffered(true);
        tblBooks.setVerifyInputWhenFocusTarget(false);
        tblBooks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBooksMouseClicked(evt);
            }
        });
        jScrollPane.setViewportView(tblBooks);

        add(jScrollPane);
        jScrollPane.setBounds(6, 36, 500, 307);

        lblShelve.setText("id Shelve");
        add(lblShelve);
        lblShelve.setBounds(516, 206, 60, 16);

        cbIDShelve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIDShelveActionPerformed(evt);
            }
        });
        add(cbIDShelve);
        cbIDShelve.setBounds(586, 206, 220, 26);
        add(txtExistences);
        txtExistences.setBounds(586, 176, 220, 24);

        lblExistences.setText("Existences");
        add(lblExistences);
        lblExistences.setBounds(516, 176, 60, 16);

        lblAuthor.setText("id Author");
        add(lblAuthor);
        lblAuthor.setBounds(516, 146, 48, 16);

        cbIDAuthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIDAuthorActionPerformed(evt);
            }
        });
        add(cbIDAuthor);
        cbIDAuthor.setBounds(586, 146, 220, 26);
        add(txtEditorial);
        txtEditorial.setBounds(586, 116, 220, 24);

        lblEditorial.setText("Editorial");
        add(lblEditorial);
        lblEditorial.setBounds(516, 116, 45, 16);
        add(txtTitle);
        txtTitle.setBounds(586, 86, 220, 24);

        txtId.setFocusable(false);
        add(txtId);
        txtId.setBounds(586, 56, 60, 24);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Books books = new Books();
        books.setIdAuthor((Author) cbIDAuthor.getSelectedItem());
        books.setEditorial(txtEditorial.getText());
        books.setExistences(Integer.parseInt(txtExistences.getText()));
        //books.setId(WIDTH);
        books.setIdShelve((Shelves) cbIDShelve.getSelectedItem());
        books.setTitle(txtTitle.getText());
        BooksJpaController bjc =new BooksJpaController();
        bjc.create(books);
        loadTable();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        BooksJpaController bjc =new BooksJpaController();//
        try {        
            bjc.destroy(Integer.parseInt(txtId.getText()));
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CrudBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadTable();
        btnAdd.setEnabled(true);
        btnDelete.setEnabled(false);
        btnModify.setEnabled(false);

        txtEditorial.setText("");
        txtId.setText("");
        txtExistences.setText("");
        txtTitle.setText("");

        cbIDAuthor.setSelectedIndex(0);
        cbIDShelve.setSelectedIndex(0);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        btnAdd.setEnabled(true);
        btnDelete.setEnabled(false);
        btnModify.setEnabled(false);

        txtEditorial.setText("");
        txtId.setText("");
        txtExistences.setText("");
        txtTitle.setText("");

        cbIDAuthor.setSelectedIndex(0);
        cbIDShelve.setSelectedIndex(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyActionPerformed
        Books books = new Books();
        books.setId(Integer.parseInt(txtId.getText()));
        books.setIdAuthor((Author) cbIDAuthor.getSelectedItem());
        books.setEditorial(txtEditorial.getText());
        books.setExistences(Integer.parseInt(txtExistences.getText()));
        books.setIdShelve((Shelves) cbIDShelve.getSelectedItem());
        books.setTitle(txtTitle.getText());
        BooksJpaController bjc =new BooksJpaController();
        try {
            bjc.edit(books);
        } catch (Exception ex) {
            Logger.getLogger(CrudBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();
    }//GEN-LAST:event_btnModifyActionPerformed

    private void tblBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBooksMouseClicked

        txtId.setText(String.valueOf(tblBooks.getValueAt(tblBooks.getSelectedRow(), 0)));
        txtTitle.setText(String.valueOf(tblBooks.getValueAt(tblBooks.getSelectedRow(), 1)));
        txtEditorial.setText(String.valueOf(tblBooks.getValueAt(tblBooks.getSelectedRow(), 2)));
        cbIDAuthor.getModel().setSelectedItem(tblBooks.getValueAt(tblBooks.getSelectedRow(),3));
        txtExistences.setText(String.valueOf(tblBooks.getValueAt(tblBooks.getSelectedRow(), 4)));
        cbIDShelve.getModel().setSelectedItem(tblBooks.getValueAt(tblBooks.getSelectedRow(),5));
        btnAdd.setEnabled(false);
        btnDelete.setEnabled(true);
        btnModify.setEnabled(true);
        btnExit.setEnabled(true);
    }//GEN-LAST:event_tblBooksMouseClicked

    private void cbIDShelveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIDShelveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbIDShelveActionPerformed

    private void cbIDAuthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIDAuthorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbIDAuthorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnModify;
    private javax.swing.JComboBox<Object
    > cbIDAuthor;
    private javax.swing.JComboBox<Object
    > cbIDShelve;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel lbIdBook;
    private javax.swing.JLabel lblAuthor;
    private javax.swing.JLabel lblEditorial;
    private javax.swing.JLabel lblExistences;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblLibrary;
    private javax.swing.JLabel lblShelve;
    private javax.swing.JTable tblBooks;
    private javax.swing.JTextField txtEditorial;
    private javax.swing.JTextField txtExistences;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtTitle;
    // End of variables declaration//GEN-END:variables
}
