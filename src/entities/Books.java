/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "books")
@NamedQueries({
    @NamedQuery(name = "Books.findAll", query = "SELECT b FROM Books b"),
    @NamedQuery(name = "Books.findById", query = "SELECT b FROM Books b WHERE b.id = :id"),
    @NamedQuery(name = "Books.findByTitle", query = "SELECT b FROM Books b WHERE b.title = :title"),
    @NamedQuery(name = "Books.findByEditorial", query = "SELECT b FROM Books b WHERE b.editorial = :editorial"),
    @NamedQuery(name = "Books.findByExistences", query = "SELECT b FROM Books b WHERE b.existences = :existences")})
public class Books implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBooks")
    private Collection<Borrowed> borrowedCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "editorial")
    private String editorial;
    @Column(name = "existences")
    private Integer existences;
    @JoinColumn(name = "idAuthor", referencedColumnName = "id")
    @ManyToOne
    private Author idAuthor;
    @JoinColumn(name = "idShelve", referencedColumnName = "id")
    @ManyToOne
    private Shelves idShelve;

    public Books() {
    }

    public Books(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Integer getExistences() {
        return existences;
    }

    public void setExistences(Integer existences) {
        this.existences = existences;
    }

    public Author getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Author idAuthor) {
        this.idAuthor = idAuthor;
    }

    public Shelves getIdShelve() {
        return idShelve;
    }

    public void setIdShelve(Shelves idShelve) {
        this.idShelve = idShelve;
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
        if (!(object instanceof Books)) {
            return false;
        }
        Books other = (Books) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Books[ id=" + id + " ]";
    }

    public Collection<Borrowed> getBorrowedCollection() {
        return borrowedCollection;
    }

    public void setBorrowedCollection(Collection<Borrowed> borrowedCollection) {
        this.borrowedCollection = borrowedCollection;
    }
    
}
