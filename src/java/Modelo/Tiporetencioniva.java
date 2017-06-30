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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sofimar
 */
@Entity
@Table(name = "tiporetencioniva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tiporetencioniva.findAll", query = "SELECT t FROM Tiporetencioniva t"),
    @NamedQuery(name = "Tiporetencioniva.findByIdtiporetencioniva", query = "SELECT t FROM Tiporetencioniva t WHERE t.idtiporetencioniva = :idtiporetencioniva"),
    @NamedQuery(name = "Tiporetencioniva.findByPorcentajeiva", query = "SELECT t FROM Tiporetencioniva t WHERE t.porcentajeiva = :porcentajeiva")})
public class Tiporetencioniva implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtiporetencioniva")
    private Integer idtiporetencioniva;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "porcentajeiva")
    private Double porcentajeiva;
    @Column(name = "porcentajeivaserv")
    private Double porcentajeivaser;
    @OneToMany(mappedBy = "idtiporetencioniva")
    private Collection<Detalleretencionivaef> detalleretencionivaefCollection;
    @OneToMany(mappedBy = "idtiporetencioniva")
    private Collection<Detalleretencionivasp> detalleretencionivaspCollection;

    public Tiporetencioniva() {
    }

    public Tiporetencioniva(Integer idtiporetencioniva) {
        this.idtiporetencioniva = idtiporetencioniva;
    }

    public Integer getIdtiporetencioniva() {
        return idtiporetencioniva;
    }

    public void setIdtiporetencioniva(Integer idtiporetencioniva) {
        this.idtiporetencioniva = idtiporetencioniva;
    }

    public Double getPorcentajeiva() {
        return porcentajeiva;
    }

    public void setPorcentajeiva(Double porcentajeiva) {
        this.porcentajeiva = porcentajeiva;
    }

    public Double getPorcentajeivaser() {
        return porcentajeivaser;
    }

    public void setPorcentajeivaser(Double porcentajeivaser) {
        this.porcentajeivaser = porcentajeivaser;
    }
    

    @XmlTransient
    public Collection<Detalleretencionivaef> getDetalleretencionivaefCollection() {
        return detalleretencionivaefCollection;
    }

    public void setDetalleretencionivaefCollection(Collection<Detalleretencionivaef> detalleretencionivaefCollection) {
        this.detalleretencionivaefCollection = detalleretencionivaefCollection;
    }

    @XmlTransient
    public Collection<Detalleretencionivasp> getDetalleretencionivaspCollection() {
        return detalleretencionivaspCollection;
    }

    public void setDetalleretencionivaspCollection(Collection<Detalleretencionivasp> detalleretencionivaspCollection) {
        this.detalleretencionivaspCollection = detalleretencionivaspCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtiporetencioniva != null ? idtiporetencioniva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiporetencioniva)) {
            return false;
        }
        Tiporetencioniva other = (Tiporetencioniva) object;
        if ((this.idtiporetencioniva == null && other.idtiporetencioniva != null) || (this.idtiporetencioniva != null && !this.idtiporetencioniva.equals(other.idtiporetencioniva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Tiporetencioniva[ idtiporetencioniva=" + idtiporetencioniva + " ]";
    }
    
}
