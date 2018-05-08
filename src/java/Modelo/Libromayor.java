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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sofimar
 */
@Entity
@Table(name = "libromayor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Libromayor.findAll", query = "SELECT l FROM Libromayor l"),
    @NamedQuery(name = "Libromayor.findByIdlibromayor", query = "SELECT l FROM Libromayor l WHERE l.idlibromayor = :idlibromayor"),
    @NamedQuery(name = "Libromayor.findBySaldoanterior", query = "SELECT l FROM Libromayor l WHERE l.saldoanterior = :saldoanterior"),
    @NamedQuery(name = "Libromayor.findByDebe", query = "SELECT l FROM Libromayor l WHERE l.debe = :debe"),
    @NamedQuery(name = "Libromayor.findByHaber", query = "SELECT l FROM Libromayor l WHERE l.haber = :haber"),
    @NamedQuery(name = "Libromayor.findBySaldoposterior", query = "SELECT l FROM Libromayor l WHERE l.saldoposterior = :saldoposterior")})
public class Libromayor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlibromayor")
    private Integer idlibromayor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "saldoanterior")
    private Double saldoanterior;
    @Column(name = "debe")
    private Double debe;
    @Column(name = "haber")
    private Double haber;
    @Column(name = "saldoposterior")
    private Double saldoposterior;
    @JoinColumn(name = "idlibrodiario", referencedColumnName = "idlibrodiario")
    @ManyToOne
    private Librodiario idlibrodiario;
    @JoinColumn(name = "idplandecuenta", referencedColumnName = "idplandecuenta")
    @ManyToOne
    private Plandecuenta idplandecuenta;
    @OneToMany(mappedBy = "idlibromayor")
    private Collection<Movimientobancario> movimientobancarioCollection;

    public Libromayor() {
    }

    public Libromayor(Integer idlibromayor) {
        this.idlibromayor = idlibromayor;
    }

    public Integer getIdlibromayor() {
        return idlibromayor;
    }

    public void setIdlibromayor(Integer idlibromayor) {
        this.idlibromayor = idlibromayor;
    }

    public Double getSaldoanterior() {
        return saldoanterior;
    }

    public void setSaldoanterior(Double saldoanterior) {
        this.saldoanterior = saldoanterior;
    }

    public Double getDebe() {
        return debe;
    }

    public void setDebe(Double debe) {
        this.debe = debe;
    }

    public Double getHaber() {
        return haber;
    }

    public void setHaber(Double haber) {
        this.haber = haber;
    }

    public Double getSaldoposterior() {
        return saldoposterior;
    }

    public void setSaldoposterior(Double saldoposterior) {
        this.saldoposterior = saldoposterior;
    }

    public Librodiario getIdlibrodiario() {
        return idlibrodiario;
    }

    public void setIdlibrodiario(Librodiario idlibrodiario) {
        this.idlibrodiario = idlibrodiario;
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
        hash += (idlibromayor != null ? idlibromayor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libromayor)) {
            return false;
        }
        Libromayor other = (Libromayor) object;
        if ((this.idlibromayor == null && other.idlibromayor != null) || (this.idlibromayor != null && !this.idlibromayor.equals(other.idlibromayor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Libromayor[ idlibromayor=" + idlibromayor + " ]";
    }
    
    @XmlTransient
    public Collection<Movimientobancario> getMovimientobancarioCollection() {
        return movimientobancarioCollection;
    }

    public void setMovimientobancarioCollection(Collection<Movimientobancario> movimientobancarioCollection) {
        this.movimientobancarioCollection = movimientobancarioCollection;
    }
    
}
