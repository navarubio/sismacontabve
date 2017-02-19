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
@Table(name = "detalleretencionislrsp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleretencionislrsp.findAll", query = "SELECT d FROM Detalleretencionislrsp d"),
    @NamedQuery(name = "Detalleretencionislrsp.findByIddetalleretencionislrsp", query = "SELECT d FROM Detalleretencionislrsp d WHERE d.iddetalleretencionislrsp = :iddetalleretencionislrsp"),
    @NamedQuery(name = "Detalleretencionislrsp.findByTotalventa", query = "SELECT d FROM Detalleretencionislrsp d WHERE d.totalventa = :totalventa"),
    @NamedQuery(name = "Detalleretencionislrsp.findByBimponible", query = "SELECT d FROM Detalleretencionislrsp d WHERE d.bimponible = :bimponible"),
    @NamedQuery(name = "Detalleretencionislrsp.findByProcentajeretencion", query = "SELECT d FROM Detalleretencionislrsp d WHERE d.procentajeretencion = :procentajeretencion"),
    @NamedQuery(name = "Detalleretencionislrsp.findBySustraendo", query = "SELECT d FROM Detalleretencionislrsp d WHERE d.sustraendo = :sustraendo"),
    @NamedQuery(name = "Detalleretencionislrsp.findByTotalislrretenido", query = "SELECT d FROM Detalleretencionislrsp d WHERE d.totalislrretenido = :totalislrretenido")})
public class Detalleretencionislrsp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetalleretencionislrsp")
    private Integer iddetalleretencionislrsp;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalventa")
    private Double totalventa;
    @Column(name = "bimponible")
    private Double bimponible;
    @Column(name = "procentajeretencion")
    private Double procentajeretencion;
    @Column(name = "sustraendo")
    private Double sustraendo;
    @Column(name = "totalislrretenido")
    private Double totalislrretenido;
    @JoinColumn(name = "idcomprobanteislrsp", referencedColumnName = "idcomprobanteislrsp")
    @ManyToOne
    private Comprobanteislrsp idcomprobanteislrsp;
    @JoinColumn(name = "idtiporetencionislr", referencedColumnName = "idtiporetencionislr")
    @ManyToOne
    private Tiporetencionislr idtiporetencionislr;
    @JoinColumn(name = "numerofact", referencedColumnName = "numerofact")
    @ManyToOne
    private Factura numerofact;

    public Detalleretencionislrsp() {
    }

    public Detalleretencionislrsp(Integer iddetalleretencionislrsp) {
        this.iddetalleretencionislrsp = iddetalleretencionislrsp;
    }

    public Integer getIddetalleretencionislrsp() {
        return iddetalleretencionislrsp;
    }

    public void setIddetalleretencionislrsp(Integer iddetalleretencionislrsp) {
        this.iddetalleretencionislrsp = iddetalleretencionislrsp;
    }

    public Double getTotalventa() {
        return totalventa;
    }

    public void setTotalventa(Double totalventa) {
        this.totalventa = totalventa;
    }

    public Double getBimponible() {
        return bimponible;
    }

    public void setBimponible(Double bimponible) {
        this.bimponible = bimponible;
    }

    public Double getProcentajeretencion() {
        return procentajeretencion;
    }

    public void setProcentajeretencion(Double procentajeretencion) {
        this.procentajeretencion = procentajeretencion;
    }

    public Double getSustraendo() {
        return sustraendo;
    }

    public void setSustraendo(Double sustraendo) {
        this.sustraendo = sustraendo;
    }

    public Double getTotalislrretenido() {
        return totalislrretenido;
    }

    public void setTotalislrretenido(Double totalislrretenido) {
        this.totalislrretenido = totalislrretenido;
    }

    public Comprobanteislrsp getIdcomprobanteislrsp() {
        return idcomprobanteislrsp;
    }

    public void setIdcomprobanteislrsp(Comprobanteislrsp idcomprobanteislrsp) {
        this.idcomprobanteislrsp = idcomprobanteislrsp;
    }

    public Tiporetencionislr getIdtiporetencionislr() {
        return idtiporetencionislr;
    }

    public void setIdtiporetencionislr(Tiporetencionislr idtiporetencionislr) {
        this.idtiporetencionislr = idtiporetencionislr;
    }

    public Factura getNumerofact() {
        return numerofact;
    }

    public void setNumerofact(Factura numerofact) {
        this.numerofact = numerofact;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalleretencionislrsp != null ? iddetalleretencionislrsp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleretencionislrsp)) {
            return false;
        }
        Detalleretencionislrsp other = (Detalleretencionislrsp) object;
        if ((this.iddetalleretencionislrsp == null && other.iddetalleretencionislrsp != null) || (this.iddetalleretencionislrsp != null && !this.iddetalleretencionislrsp.equals(other.iddetalleretencionislrsp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Detalleretencionislrsp[ iddetalleretencionislrsp=" + iddetalleretencionislrsp + " ]";
    }
    
}
