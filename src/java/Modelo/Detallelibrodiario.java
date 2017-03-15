/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sofimar
 */
@Entity
@Table(name = "detallelibrodiario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallelibrodiario.findAll", query = "SELECT d FROM Detallelibrodiario d"),
    @NamedQuery(name = "Detallelibrodiario.findByIddetallelibrodiario", query = "SELECT d FROM Detallelibrodiario d WHERE d.iddetallelibrodiario = :iddetallelibrodiario"),
    @NamedQuery(name = "Detallelibrodiario.findByDebe", query = "SELECT d FROM Detallelibrodiario d WHERE d.debe = :debe"),
    @NamedQuery(name = "Detallelibrodiario.findByHaber", query = "SELECT d FROM Detallelibrodiario d WHERE d.haber = :haber")})
public class Detallelibrodiario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetallelibrodiario")
    private Integer iddetallelibrodiario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "debe")
    private Double debe;
    @Column(name = "haber")
    private Double haber;
    @JoinColumn(name = "idlibrodiario", referencedColumnName = "idlibrodiario")
    @ManyToOne
    private Librodiario idlibrodiario;
    @JoinColumn(name = "idplandecuenta", referencedColumnName = "idplandecuenta")
    @ManyToOne
    private Plandecuenta idplandecuenta;

    public Detallelibrodiario() {
    }

    public Detallelibrodiario(Integer iddetallelibrodiario) {
        this.iddetallelibrodiario = iddetallelibrodiario;
    }

    public Integer getIddetallelibrodiario() {
        return iddetallelibrodiario;
    }

    public void setIddetallelibrodiario(Integer iddetallelibrodiario) {
        this.iddetallelibrodiario = iddetallelibrodiario;
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
        hash += (iddetallelibrodiario != null ? iddetallelibrodiario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallelibrodiario)) {
            return false;
        }
        Detallelibrodiario other = (Detallelibrodiario) object;
        if ((this.iddetallelibrodiario == null && other.iddetallelibrodiario != null) || (this.iddetallelibrodiario != null && !this.iddetallelibrodiario.equals(other.iddetallelibrodiario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Detallelibrodiario[ iddetallelibrodiario=" + iddetallelibrodiario + " ]";
    }
    
}
