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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tipogastocajachica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipogastocajachica.findAll", query = "SELECT t FROM Tipogastocajachica t"),
    @NamedQuery(name = "Tipogastocajachica.findByIdtipogastocajachica", query = "SELECT t FROM Tipogastocajachica t WHERE t.idtipogastocajachica = :idtipogastocajachica"),
    @NamedQuery(name = "Tipogastocajachica.findByTipogastocajachica", query = "SELECT t FROM Tipogastocajachica t WHERE t.tipogastocajachica = :tipogastocajachica")})
public class Tipogastocajachica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipogastocajachica")
    private Integer idtipogastocajachica;
    @Size(max = 150)
    @Column(name = "tipogastocajachica")
    private String tipogastocajachica;
    @OneToMany(mappedBy = "idtipogastocajachica")
    private Collection<Detalleconsumocajachica> detalleconsumocajachicaCollection;
    @JoinColumn(name = "idplandecuenta", referencedColumnName = "idplandecuenta")
    @ManyToOne
    private Plandecuenta idplandecuenta;

    public Tipogastocajachica() {
    }

    public Tipogastocajachica(Integer idtipogastocajachica) {
        this.idtipogastocajachica = idtipogastocajachica;
    }

    public Integer getIdtipogastocajachica() {
        return idtipogastocajachica;
    }

    public void setIdtipogastocajachica(Integer idtipogastocajachica) {
        this.idtipogastocajachica = idtipogastocajachica;
    }

    public String getTipogastocajachica() {
        return tipogastocajachica;
    }

    public void setTipogastocajachica(String tipogastocajachica) {
        this.tipogastocajachica = tipogastocajachica;
    }

    @XmlTransient
    public Collection<Detalleconsumocajachica> getDetalleconsumocajachicaCollection() {
        return detalleconsumocajachicaCollection;
    }

    public void setDetalleconsumocajachicaCollection(Collection<Detalleconsumocajachica> detalleconsumocajachicaCollection) {
        this.detalleconsumocajachicaCollection = detalleconsumocajachicaCollection;
    }

    public Plandecuenta getIdplandecuenta() {
        return idplandecuenta;
    }

    public void setIdplandecuenta(Plandecuenta idplandecuenta) {
        this.idplandecuenta = idplandecuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipogastocajachica != null ? idtipogastocajachica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipogastocajachica)) {
            return false;
        }
        Tipogastocajachica other = (Tipogastocajachica) object;
        if ((this.idtipogastocajachica == null && other.idtipogastocajachica != null) || (this.idtipogastocajachica != null && !this.idtipogastocajachica.equals(other.idtipogastocajachica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Tipogastocajachica[ idtipogastocajachica=" + idtipogastocajachica + " ]";
    }
    
}
