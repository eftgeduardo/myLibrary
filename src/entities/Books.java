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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "Books.findByAuthor", query = "SELECT b FROM Books b WHERE b.author = :author"),
    @NamedQuery(name = "Books.findByExistences", query = "SELECT b FROM Books b WHERE b.existences = :existences"),
    @NamedQuery(name = "Books.findByShelve", query = "SELECT b FROM Books b WHERE b.shelve = :shelve")})
public class Books implements Serializable {
    
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
    @Column(name = "author")
    private String author;
    @Column(name = "existences")
    private Integer existences;
    @Column(name = "shelve")
    private String shelve;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getExistences() {
        return existences;
    }

    public void setExistences(Integer existences) {
        this.existences = existences;
    }

    public String getShelve() {
        return shelve;
    }

    public void setShelve(String shelve) {
        this.shelve = shelve;
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
    
}
