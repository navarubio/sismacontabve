/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Inpeca
 */
@Entity
@Table(name = "Tipoasiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoasiento.findAll", query = "SELECT t FROM Tipoasiento t"),
    @NamedQuery(name = "Tipoasiento.findByIdtipoasiento", query = "SELECT t FROM Tipoasiento t WHERE t.idtipoasiento = :idtipoasiento"),
    @NamedQuery(name = "Tipoasiento.findByTipoasiento", query = "SELECT t FROM Tipoasiento t WHERE t.tipoasiento = :tipoasiento")})
public class Tipoasiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipoasiento")
    private Integer idtipoasiento;
    @Size(max = 20)
    @Column(name = "tipoasiento")
    private String tipoasiento;
    
    @OneToMany(mappedBy = "idtipoasiento")
    private Collection<Librodiario> librodiarioCollection;
    
    public Tipoasiento() {
    }

    public Tipoasiento(Integer idtipoasiento) {
        this.idtipoasiento = idtipoasiento;
    }

    public Integer getIdtipoasiento() {
        return idtipoasiento;
    }

    public void setIdtipoasiento(Integer idtipoasiento) {
        this.idtipoasiento = idtipoasiento;
    }

    public String getTipoasiento() {
        return tipoasiento;
    }

    public void setTipoasiento(String tipoasiento) {
        this.tipoasiento = tipoasiento;
    }

    @XmlTransient
    public Collection<Librodiario> getLibrodiarioCollection() {
        return librodiarioCollection;
    }

    public void setLibrodiarioCollection(Collection<Librodiario> librodiarioCollection) {
        this.librodiarioCollection = librodiarioCollection;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoasiento != null ? idtipoasiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoasiento)) {
            return false;
        }
        Tipoasiento other = (Tipoasiento) object;
        if ((this.idtipoasiento == null && other.idtipoasiento != null) || (this.idtipoasiento != null && !this.idtipoasiento.equals(other.idtipoasiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tipoasiento;
    }
    
}
