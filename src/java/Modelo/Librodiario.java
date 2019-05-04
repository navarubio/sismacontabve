/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sofimar
 */
@Entity
@Table(name = "librodiario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Librodiario.findAll", query = "SELECT l FROM Librodiario l"),
    @NamedQuery(name = "Librodiario.findByIdlibrodiario", query = "SELECT l FROM Librodiario l WHERE l.idlibrodiario = :idlibrodiario"),
    @NamedQuery(name = "Librodiario.findByFecha", query = "SELECT l FROM Librodiario l WHERE l.fecha = :fecha"),
    @NamedQuery(name = "Librodiario.findByDescripcionasiento", query = "SELECT l FROM Librodiario l WHERE l.descripcionasiento = :descripcionasiento")})
public class Librodiario implements Serializable {
    @OneToMany(mappedBy = "idlibrodiario")
    private Collection<Maestromovimiento> maestromovimientoCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlibrodiario")
    private Integer idlibrodiario;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 255)
    @Column(name = "descripcionasiento")
    private String descripcionasiento;
    @OneToMany(mappedBy = "idlibrodiario")
    private Collection<Libromayor> libromayorCollection;
    @OneToMany(mappedBy = "idlibrodiario")
    private Collection<Detallelibrodiario> detallelibrodiarioCollection;
    @Column(name = "idempresa")
    private Integer idempresa;
    @Column(name = "serialasiento")
    private Integer serialasiento;
    public Librodiario() {
    }

    public Librodiario(Integer idlibrodiario) {
        this.idlibrodiario = idlibrodiario;
    }

    public Integer getIdlibrodiario() {
        return idlibrodiario;
    }

    public void setIdlibrodiario(Integer idlibrodiario) {
        this.idlibrodiario = idlibrodiario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcionasiento() {
        return descripcionasiento;
    }

    public void setDescripcionasiento(String descripcionasiento) {
        this.descripcionasiento = descripcionasiento;
    }

    @XmlTransient
    public Collection<Libromayor> getLibromayorCollection() {
        return libromayorCollection;
    }

    public void setLibromayorCollection(Collection<Libromayor> libromayorCollection) {
        this.libromayorCollection = libromayorCollection;
    }

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    } 

    public Integer getSerialasiento() {
        return serialasiento;
    }

    public void setSerialasiento(Integer serialasiento) {
        this.serialasiento = serialasiento;
    }

    @XmlTransient
    public Collection<Detallelibrodiario> getDetallelibrodiarioCollection() {
        return detallelibrodiarioCollection;
    }

    public void setDetallelibrodiarioCollection(Collection<Detallelibrodiario> detallelibrodiarioCollection) {
        this.detallelibrodiarioCollection = detallelibrodiarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlibrodiario != null ? idlibrodiario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Librodiario)) {
            return false;
        }
        Librodiario other = (Librodiario) object;
        if ((this.idlibrodiario == null && other.idlibrodiario != null) || (this.idlibrodiario != null && !this.idlibrodiario.equals(other.idlibrodiario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Librodiario[ idlibrodiario=" + idlibrodiario + " ]";
    }

    @XmlTransient
    public Collection<Maestromovimiento> getMaestromovimientoCollection() {
        return maestromovimientoCollection;
    }

    public void setMaestromovimientoCollection(Collection<Maestromovimiento> maestromovimientoCollection) {
        this.maestromovimientoCollection = maestromovimientoCollection;
    }
    
}
