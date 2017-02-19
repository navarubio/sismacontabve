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
@Table(name = "estatuscomprobanteretencion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estatuscomprobanteretencion.findAll", query = "SELECT e FROM Estatuscomprobanteretencion e"),
    @NamedQuery(name = "Estatuscomprobanteretencion.findByIdestatuscomprobante", query = "SELECT e FROM Estatuscomprobanteretencion e WHERE e.idestatuscomprobante = :idestatuscomprobante"),
    @NamedQuery(name = "Estatuscomprobanteretencion.findByEstatuscomprobante", query = "SELECT e FROM Estatuscomprobanteretencion e WHERE e.estatuscomprobante = :estatuscomprobante")})
public class Estatuscomprobanteretencion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestatuscomprobante")
    private Integer idestatuscomprobante;
    @Size(max = 15)
    @Column(name = "estatuscomprobante")
    private String estatuscomprobante;
    @OneToMany(mappedBy = "idestatuscomprobante")
    private Collection<Comprobanteivaef> comprobanteivaefCollection;
    @OneToMany(mappedBy = "idestatuscomprobante")
    private Collection<Comprobanteislref> comprobanteislrefCollection;
    @OneToMany(mappedBy = "idestatuscomprobante")
    private Collection<Comprobanteislrsp> comprobanteislrspCollection;
    @OneToMany(mappedBy = "idestatuscomprobante")
    private Collection<Comprobanteivasp> comprobanteivaspCollection;

    public Estatuscomprobanteretencion() {
    }

    public Estatuscomprobanteretencion(Integer idestatuscomprobante) {
        this.idestatuscomprobante = idestatuscomprobante;
    }

    public Integer getIdestatuscomprobante() {
        return idestatuscomprobante;
    }

    public void setIdestatuscomprobante(Integer idestatuscomprobante) {
        this.idestatuscomprobante = idestatuscomprobante;
    }

    public String getEstatuscomprobante() {
        return estatuscomprobante;
    }

    public void setEstatuscomprobante(String estatuscomprobante) {
        this.estatuscomprobante = estatuscomprobante;
    }

    @XmlTransient
    public Collection<Comprobanteivaef> getComprobanteivaefCollection() {
        return comprobanteivaefCollection;
    }

    public void setComprobanteivaefCollection(Collection<Comprobanteivaef> comprobanteivaefCollection) {
        this.comprobanteivaefCollection = comprobanteivaefCollection;
    }

    @XmlTransient
    public Collection<Comprobanteislref> getComprobanteislrefCollection() {
        return comprobanteislrefCollection;
    }

    public void setComprobanteislrefCollection(Collection<Comprobanteislref> comprobanteislrefCollection) {
        this.comprobanteislrefCollection = comprobanteislrefCollection;
    }

    @XmlTransient
    public Collection<Comprobanteislrsp> getComprobanteislrspCollection() {
        return comprobanteislrspCollection;
    }

    public void setComprobanteislrspCollection(Collection<Comprobanteislrsp> comprobanteislrspCollection) {
        this.comprobanteislrspCollection = comprobanteislrspCollection;
    }

    @XmlTransient
    public Collection<Comprobanteivasp> getComprobanteivaspCollection() {
        return comprobanteivaspCollection;
    }

    public void setComprobanteivaspCollection(Collection<Comprobanteivasp> comprobanteivaspCollection) {
        this.comprobanteivaspCollection = comprobanteivaspCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestatuscomprobante != null ? idestatuscomprobante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estatuscomprobanteretencion)) {
            return false;
        }
        Estatuscomprobanteretencion other = (Estatuscomprobanteretencion) object;
        if ((this.idestatuscomprobante == null && other.idestatuscomprobante != null) || (this.idestatuscomprobante != null && !this.idestatuscomprobante.equals(other.idestatuscomprobante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Estatuscomprobanteretencion[ idestatuscomprobante=" + idestatuscomprobante + " ]";
    }
    
}
