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
@Table(name = "detalleretencionislref")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleretencionislref.findAll", query = "SELECT d FROM Detalleretencionislref d"),
    @NamedQuery(name = "Detalleretencionislref.findByIddetalleretencionislref", query = "SELECT d FROM Detalleretencionislref d WHERE d.iddetalleretencionislref = :iddetalleretencionislref"),
    @NamedQuery(name = "Detalleretencionislref.findByTotalcompra", query = "SELECT d FROM Detalleretencionislref d WHERE d.totalcompra = :totalcompra"),
    @NamedQuery(name = "Detalleretencionislref.findByBimponible", query = "SELECT d FROM Detalleretencionislref d WHERE d.bimponible = :bimponible"),
    @NamedQuery(name = "Detalleretencionislref.findByProcentajeretencion", query = "SELECT d FROM Detalleretencionislref d WHERE d.procentajeretencion = :procentajeretencion"),
    @NamedQuery(name = "Detalleretencionislref.findBySustraendo", query = "SELECT d FROM Detalleretencionislref d WHERE d.sustraendo = :sustraendo"),
    @NamedQuery(name = "Detalleretencionislref.findByTotalislrretenido", query = "SELECT d FROM Detalleretencionislref d WHERE d.totalislrretenido = :totalislrretenido")})
public class Detalleretencionislref implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetalleretencionislref")
    private Integer iddetalleretencionislref;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalcompra")
    private Double totalcompra;
    @Column(name = "bimponible")
    private Double bimponible;
    @Column(name = "procentajeretencion")
    private Double procentajeretencion;
    @Column(name = "sustraendo")
    private Double sustraendo;
    @Column(name = "totalislrretenido")
    private Double totalislrretenido;
    @JoinColumn(name = "idcomprobanteislref", referencedColumnName = "idcomprobanteislref")
    @ManyToOne
    private Comprobanteislref idcomprobanteislref;
    @JoinColumn(name = "idtiporetencionislr", referencedColumnName = "idtiporetencionislr")
    @ManyToOne
    private Tiporetencionislr idtiporetencionislr;
    @JoinColumn(name = "idcompra", referencedColumnName = "idcompra")
    @ManyToOne
    private Compra idcompra;
    @JoinColumn(name = "idsubgrupo", referencedColumnName = "idsubgrupo")
    @ManyToOne
    private Subgrupo idsubgrupo;

    public Detalleretencionislref() {
    }

    public Detalleretencionislref(Integer iddetalleretencionislref) {
        this.iddetalleretencionislref = iddetalleretencionislref;
    }

    public Integer getIddetalleretencionislref() {
        return iddetalleretencionislref;
    }

    public void setIddetalleretencionislref(Integer iddetalleretencionislref) {
        this.iddetalleretencionislref = iddetalleretencionislref;
    }

    public Double getTotalcompra() {
        return totalcompra;
    }

    public void setTotalcompra(Double totalcompra) {
        this.totalcompra = totalcompra;
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

    public Comprobanteislref getIdcomprobanteislref() {
        return idcomprobanteislref;
    }

    public void setIdcomprobanteislref(Comprobanteislref idcomprobanteislref) {
        this.idcomprobanteislref = idcomprobanteislref;
    }

    public Tiporetencionislr getIdtiporetencionislr() {
        return idtiporetencionislr;
    }

    public void setIdtiporetencionislr(Tiporetencionislr idtiporetencionislr) {
        this.idtiporetencionislr = idtiporetencionislr;
    }

    public Compra getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(Compra idcompra) {
        this.idcompra = idcompra;
    }

    public Subgrupo getIdsubgrupo() {
        return idsubgrupo;
    }

    public void setIdsubgrupo(Subgrupo idsubgrupo) {
        this.idsubgrupo = idsubgrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalleretencionislref != null ? iddetalleretencionislref.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleretencionislref)) {
            return false;
        }
        Detalleretencionislref other = (Detalleretencionislref) object;
        if ((this.iddetalleretencionislref == null && other.iddetalleretencionislref != null) || (this.iddetalleretencionislref != null && !this.iddetalleretencionislref.equals(other.iddetalleretencionislref))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Detalleretencionislref[ iddetalleretencionislref=" + iddetalleretencionislref + " ]";
    }
    
}
