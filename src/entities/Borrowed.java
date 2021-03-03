/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "borrowed")
@NamedQueries({
    @NamedQuery(name = "Borrowed.findAll", query = "SELECT b FROM Borrowed b"),
    @NamedQuery(name = "Borrowed.findById", query = "SELECT b FROM Borrowed b WHERE b.id = :id"),
    @NamedQuery(name = "Borrowed.findByDate", query = "SELECT b FROM Borrowed b WHERE b.date = :date"),
    @NamedQuery(name = "Borrowed.findByDueDate", query = "SELECT b FROM Borrowed b WHERE b.dueDate = :dueDate")})
public class Borrowed implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "dueDate")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @JoinColumn(name = "idBooks", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Books idBooks;
    @JoinColumn(name = "idUsers", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users idUsers;

    public Borrowed() {
    }

    public Borrowed(Integer id) {
        this.id = id;
    }

    public Borrowed(Integer id, Date date, Date dueDate) {
        this.id = id;
        this.date = date;
        this.dueDate = dueDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Books getIdBooks() {
        return idBooks;
    }

    public void setIdBooks(Books idBooks) {
        this.idBooks = idBooks;
    }

    public Users getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(Users idUsers) {
        this.idUsers = idUsers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Borrowed)) {
            return false;
        }
        Borrowed other = (Borrowed) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Borrowed[ id=" + id + " ]";
    }
    
}
