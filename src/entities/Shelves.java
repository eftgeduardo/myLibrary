/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "shelves")
@NamedQueries({
    @NamedQuery(name = "Shelves.findAll", query = "SELECT s FROM Shelves s"),
    @NamedQuery(name = "Shelves.findById", query = "SELECT s FROM Shelves s WHERE s.id = :id"),
    @NamedQuery(name = "Shelves.findByName", query = "SELECT s FROM Shelves s WHERE s.name = :name"),
    @NamedQuery(name = "Shelves.findByCategories", query = "SELECT s FROM Shelves s WHERE s.categories = :categories")})
public class Shelves implements Serializable {

    @OneToMany(mappedBy = "idShelve")
    private Collection<Books> booksCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "categories")
    private String categories;

    public Shelves() {
    }

    public Shelves(Integer id) {
        this.id = id;
    }

    public Shelves(Integer id, String name, String categories) {
        this.id = id;
        this.name = name;
        this.categories = categories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
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
        if (!(object instanceof Shelves)) {
            return false;
        }
        Shelves other = (Shelves) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Shelves[ id=" + id + " ]";
    }

    public Collection<Books> getBooksCollection() {
        return booksCollection;
    }

    public void setBooksCollection(Collection<Books> booksCollection) {
        this.booksCollection = booksCollection;
    }
    
}
