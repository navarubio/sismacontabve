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
@Table(name = "estatusfacturaventa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estatusfacturaventa.findAll", query = "SELECT e FROM Estatusfacturaventa e"),
    @NamedQuery(name = "Estatusfacturaventa.findByIdestatusfacturaventa", query = "SELECT e FROM Estatusfacturaventa e WHERE e.idestatusfacturaventa = :idestatusfacturaventa"),
    @NamedQuery(name = "Estatusfacturaventa.findByEstatusfacturaventa", query = "SELECT e FROM Estatusfacturaventa e WHERE e.estatusfacturaventa = :estatusfacturaventa")})
public class Estatusfacturaventa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestatusfacturaventa")
    private Integer idestatusfacturaventa;
    @Size(max = 25)
    @Column(name = "estatusfacturaventa")
    private String estatusfacturaventa;
    @OneToMany(mappedBy = "idestatusfacturaventa")
    private Collection<Factura> facturaCollection;

    public Estatusfacturaventa() {
    }

    public Estatusfacturaventa(Integer idestatusfacturaventa) {
        this.idestatusfacturaventa = idestatusfacturaventa;
    }

    public Integer getIdestatusfacturaventa() {
        return idestatusfacturaventa;
    }

    public void setIdestatusfacturaventa(Integer idestatusfacturaventa) {
        this.idestatusfacturaventa = idestatusfacturaventa;
    }

    public String getEstatusfacturaventa() {
        return estatusfacturaventa;
    }

    public void setEstatusfacturaventa(String estatusfacturaventa) {
        this.estatusfacturaventa = estatusfacturaventa;
    }

    @XmlTransient
    public Collection<Factura> getFacturaCollection() {
        return facturaCollection;
    }

    public void setFacturaCollection(Collection<Factura> facturaCollection) {
        this.facturaCollection = facturaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestatusfacturaventa != null ? idestatusfacturaventa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estatusfacturaventa)) {
            return false;
        }
        Estatusfacturaventa other = (Estatusfacturaventa) object;
        if ((this.idestatusfacturaventa == null && other.idestatusfacturaventa != null) || (this.idestatusfacturaventa != null && !this.idestatusfacturaventa.equals(other.idestatusfacturaventa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Estatusfacturaventa[ idestatusfacturaventa=" + idestatusfacturaventa + " ]";
    }
    
}
