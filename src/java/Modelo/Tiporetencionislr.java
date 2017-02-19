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
@Table(name = "tiporetencionislr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tiporetencionislr.findAll", query = "SELECT t FROM Tiporetencionislr t"),
    @NamedQuery(name = "Tiporetencionislr.findByIdtiporetencionislr", query = "SELECT t FROM Tiporetencionislr t WHERE t.idtiporetencionislr = :idtiporetencionislr"),
    @NamedQuery(name = "Tiporetencionislr.findByDescripcionservicio", query = "SELECT t FROM Tiporetencionislr t WHERE t.descripcionservicio = :descripcionservicio"),
    @NamedQuery(name = "Tiporetencionislr.findByPorcentajebimponible", query = "SELECT t FROM Tiporetencionislr t WHERE t.porcentajebimponible = :porcentajebimponible"),
    @NamedQuery(name = "Tiporetencionislr.findByPorcentajeretencion", query = "SELECT t FROM Tiporetencionislr t WHERE t.porcentajeretencion = :porcentajeretencion"),
    @NamedQuery(name = "Tiporetencionislr.findBySustraendo", query = "SELECT t FROM Tiporetencionislr t WHERE t.sustraendo = :sustraendo"),
    @NamedQuery(name = "Tiporetencionislr.findByPisogravable", query = "SELECT t FROM Tiporetencionislr t WHERE t.pisogravable = :pisogravable"),
    @NamedQuery(name = "Tiporetencionislr.findByCodigoxml", query = "SELECT t FROM Tiporetencionislr t WHERE t.codigoxml = :codigoxml")})
public class Tiporetencionislr implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtiporetencionislr")
    private Integer idtiporetencionislr;
    @Size(max = 255)
    @Column(name = "descripcionservicio")
    private String descripcionservicio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "porcentajebimponible")
    private Double porcentajebimponible;
    @Column(name = "porcentajeretencion")
    private Double porcentajeretencion;
    @Column(name = "sustraendo")
    private Double sustraendo;
    @Column(name = "pisogravable")
    private Double pisogravable;
    @Size(max = 5)
    @Column(name = "codigoxml")
    private String codigoxml;
    @JoinColumn(name = "idsubgrupo", referencedColumnName = "idsubgrupo")
    @ManyToOne
    private Subgrupo idsubgrupo;
    @JoinColumn(name = "idpersonalidad", referencedColumnName = "idpersonalidad")
    @ManyToOne
    private Personalidadjuridica idpersonalidad;
    @JoinColumn(name = "idcontribuyente", referencedColumnName = "idcontribuyente")
    @ManyToOne
    private Contribuyente idcontribuyente;
    @JoinColumn(name = "idresidencia", referencedColumnName = "idresidencia")
    @ManyToOne
    private Residenciajuridica idresidencia;
    @OneToMany(mappedBy = "idtiporetencionislr")
    private Collection<Detalleretencionislrsp> detalleretencionislrspCollection;
    @OneToMany(mappedBy = "idtiporetencionislr")
    private Collection<Detalleretencionislref> detalleretencionislrefCollection;

    public Tiporetencionislr() {
    }

    public Tiporetencionislr(Integer idtiporetencionislr) {
        this.idtiporetencionislr = idtiporetencionislr;
    }

    public Integer getIdtiporetencionislr() {
        return idtiporetencionislr;
    }

    public void setIdtiporetencionislr(Integer idtiporetencionislr) {
        this.idtiporetencionislr = idtiporetencionislr;
    }

    public String getDescripcionservicio() {
        return descripcionservicio;
    }

    public void setDescripcionservicio(String descripcionservicio) {
        this.descripcionservicio = descripcionservicio;
    }

    public Double getPorcentajebimponible() {
        return porcentajebimponible;
    }

    public void setPorcentajebimponible(Double porcentajebimponible) {
        this.porcentajebimponible = porcentajebimponible;
    }

    public Double getPorcentajeretencion() {
        return porcentajeretencion;
    }

    public void setPorcentajeretencion(Double porcentajeretencion) {
        this.porcentajeretencion = porcentajeretencion;
    }

    public Double getSustraendo() {
        return sustraendo;
    }

    public void setSustraendo(Double sustraendo) {
        this.sustraendo = sustraendo;
    }

    public Double getPisogravable() {
        return pisogravable;
    }

    public void setPisogravable(Double pisogravable) {
        this.pisogravable = pisogravable;
    }

    public String getCodigoxml() {
        return codigoxml;
    }

    public void setCodigoxml(String codigoxml) {
        this.codigoxml = codigoxml;
    }

    public Subgrupo getIdsubgrupo() {
        return idsubgrupo;
    }

    public void setIdsubgrupo(Subgrupo idsubgrupo) {
        this.idsubgrupo = idsubgrupo;
    }

    public Personalidadjuridica getIdpersonalidad() {
        return idpersonalidad;
    }

    public void setIdpersonalidad(Personalidadjuridica idpersonalidad) {
        this.idpersonalidad = idpersonalidad;
    }

    public Contribuyente getIdcontribuyente() {
        return idcontribuyente;
    }

    public void setIdcontribuyente(Contribuyente idcontribuyente) {
        this.idcontribuyente = idcontribuyente;
    }

    public Residenciajuridica getIdresidencia() {
        return idresidencia;
    }

    public void setIdresidencia(Residenciajuridica idresidencia) {
        this.idresidencia = idresidencia;
    }

    @XmlTransient
    public Collection<Detalleretencionislrsp> getDetalleretencionislrspCollection() {
        return detalleretencionislrspCollection;
    }

    public void setDetalleretencionislrspCollection(Collection<Detalleretencionislrsp> detalleretencionislrspCollection) {
        this.detalleretencionislrspCollection = detalleretencionislrspCollection;
    }

    @XmlTransient
    public Collection<Detalleretencionislref> getDetalleretencionislrefCollection() {
        return detalleretencionislrefCollection;
    }

    public void setDetalleretencionislrefCollection(Collection<Detalleretencionislref> detalleretencionislrefCollection) {
        this.detalleretencionislrefCollection = detalleretencionislrefCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtiporetencionislr != null ? idtiporetencionislr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiporetencionislr)) {
            return false;
        }
        Tiporetencionislr other = (Tiporetencionislr) object;
        if ((this.idtiporetencionislr == null && other.idtiporetencionislr != null) || (this.idtiporetencionislr != null && !this.idtiporetencionislr.equals(other.idtiporetencionislr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Tiporetencionislr[ idtiporetencionislr=" + idtiporetencionislr + " ]";
    }
    
}
