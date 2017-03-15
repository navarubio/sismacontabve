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
 * @author sofimar
 */
@Entity
@Table(name = "tipoconjunto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoconjunto.findAll", query = "SELECT t FROM Tipoconjunto t"),
    @NamedQuery(name = "Tipoconjunto.findByIdtipoconjunto", query = "SELECT t FROM Tipoconjunto t WHERE t.idtipoconjunto = :idtipoconjunto"),
    @NamedQuery(name = "Tipoconjunto.findByConjunto", query = "SELECT t FROM Tipoconjunto t WHERE t.conjunto = :conjunto")})
public class Tipoconjunto implements Serializable {
    @OneToMany(mappedBy = "idtipoconjunto")
    private Collection<Maestromovimiento> maestromovimientoCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipoconjunto")
    private Integer idtipoconjunto;
    @Size(max = 10)
    @Column(name = "conjunto")
    private String conjunto;

    public Tipoconjunto() {
    }

    public Tipoconjunto(Integer idtipoconjunto) {
        this.idtipoconjunto = idtipoconjunto;
    }

    public Integer getIdtipoconjunto() {
        return idtipoconjunto;
    }

    public void setIdtipoconjunto(Integer idtipoconjunto) {
        this.idtipoconjunto = idtipoconjunto;
    }

    public String getConjunto() {
        return conjunto;
    }

    public void setConjunto(String conjunto) {
        this.conjunto = conjunto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoconjunto != null ? idtipoconjunto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoconjunto)) {
            return false;
        }
        Tipoconjunto other = (Tipoconjunto) object;
        if ((this.idtipoconjunto == null && other.idtipoconjunto != null) || (this.idtipoconjunto != null && !this.idtipoconjunto.equals(other.idtipoconjunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Tipoconjunto[ idtipoconjunto=" + idtipoconjunto + " ]";
    }

    @XmlTransient
    public Collection<Maestromovimiento> getMaestromovimientoCollection() {
        return maestromovimientoCollection;
    }

    public void setMaestromovimientoCollection(Collection<Maestromovimiento> maestromovimientoCollection) {
        this.maestromovimientoCollection = maestromovimientoCollection;
    }
    
}
