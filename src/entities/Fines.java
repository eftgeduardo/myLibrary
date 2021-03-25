/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "fines")
@NamedQueries({
    @NamedQuery(name = "Fines.findAll", query = "SELECT f FROM Fines f"),
    @NamedQuery(name = "Fines.findById", query = "SELECT f FROM Fines f WHERE f.id = :id"),
    @NamedQuery(name = "Fines.findByFineCost", query = "SELECT f FROM Fines f WHERE f.fineCost = :fineCost"),
    @NamedQuery(name = "Fines.findByDaysLate", query = "SELECT f FROM Fines f WHERE f.daysLate = :daysLate"),
    @NamedQuery(name = "Fines.findByPaid", query = "SELECT f FROM Fines f WHERE f.paid = :paid")})
public class Fines implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fineCost")
    private float fineCost;
    @Basic(optional = false)
    @Column(name = "DaysLate")
    private int daysLate;
    @Basic(optional = false)
    @Column(name = "paid")
    private boolean paid;
    @JoinColumn(name = "IdBorrowed", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Borrowed idBorrowed;
    @JoinColumn(name = "IdUsers", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users idUsers;

    public Fines() {
    }

    public Fines(Integer id) {
        this.id = id;
    }

    public Fines(Integer id, float fineCost, int daysLate, boolean paid) {
        this.id = id;
        this.fineCost = fineCost;
        this.daysLate = daysLate;
        this.paid = paid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getFineCost() {
        return fineCost;
    }

    public void setFineCost(float fineCost) {
        this.fineCost = fineCost;
    }

    public int getDaysLate() {
        return daysLate;
    }

    public void setDaysLate(int daysLate) {
        this.daysLate = daysLate;
    }

    public boolean getPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Borrowed getIdBorrowed() {
        return idBorrowed;
    }

    public void setIdBorrowed(Borrowed idBorrowed) {
        this.idBorrowed = idBorrowed;
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
        if (!(object instanceof Fines)) {
            return false;
        }
        Fines other = (Fines) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Fines[ id=" + id + " ]";
    }
    
}
