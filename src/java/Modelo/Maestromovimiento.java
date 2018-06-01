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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sofimar
 */
@Entity
@Table(name = "maestromovimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Maestromovimiento.findAll", query = "SELECT m FROM Maestromovimiento m"),
    @NamedQuery(name = "Maestromovimiento.findByIdmaestro", query = "SELECT m FROM Maestromovimiento m WHERE m.idmaestro = :idmaestro"),
    @NamedQuery(name = "Maestromovimiento.findByFechamovimiento", query = "SELECT m FROM Maestromovimiento m WHERE m.fechamovimiento = :fechamovimiento")})
public class Maestromovimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmaestro")
    private Integer idmaestro;
    @Column(name = "fechamovimiento")
    @Temporal(TemporalType.DATE)
    private Date fechamovimiento;
    @OneToMany(mappedBy = "idmaestro")
    private Collection<Compra> compraCollection;
    @OneToMany(mappedBy = "idmaestro")
    private Collection<Factura> facturaCollection;
    @JoinColumn(name = "idestatuscontable", referencedColumnName = "idestatuscontable")
    @ManyToOne(optional = false)
    private Estatuscontable idestatuscontable;
    @JoinColumn(name = "idtipoconjunto", referencedColumnName = "idtipoconjunto")
    @ManyToOne
    private Tipoconjunto idtipoconjunto;
    @JoinColumn(name = "idcompra", referencedColumnName = "idcompra")
    @ManyToOne
    private Compra idcompra;
    @JoinColumn(name = "numerofact", referencedColumnName = "numerofact")
    @ManyToOne
    private Factura numerofact;
    @JoinColumn(name = "idlibrodiario", referencedColumnName = "idlibrodiario")
    @ManyToOne
    private Librodiario idlibrodiario;
    @JoinColumn(name = "idautorizacion", referencedColumnName = "idautorizacion")
    @ManyToOne
    private Autorizacion idautorizacion;
    @JoinColumn(name = "idpagocompra", referencedColumnName = "idpagocompra")
    @ManyToOne
    private Pagocompra idpagocompra;
    @JoinColumn(name = "idcobroventa", referencedColumnName = "idcobroventa")
    @ManyToOne
    private Cobroventa idcobroventa;
    @JoinColumn(name = "idotroingreso", referencedColumnName = "idotroingreso")
    @ManyToOne
    private Otroingreso idotroingreso;
    @JoinColumn(name = "idreposicioncajachica", referencedColumnName = "idreposicioncajachica")
    @ManyToOne
    private Reposicioncajachica idreposicioncajachica;

    @OneToMany(mappedBy = "idmaestro")
    private Collection<Cobroventa> cobroventaCollection;
    @OneToMany(mappedBy = "idmaestro")
    private Collection<Autorizacion> autorizacionCollection;
    @OneToMany(mappedBy = "idmaestro")
    private Collection<Pagocompra> pagocompraCollection;
    @Column(name = "idempresa")
    private Integer idempresa;

    public Maestromovimiento() {
    }

    public Maestromovimiento(Integer idmaestro) {
        this.idmaestro = idmaestro;
    }

    public Integer getIdmaestro() {
        return idmaestro;
    }

    public void setIdmaestro(Integer idmaestro) {
        this.idmaestro = idmaestro;
    }

    public Date getFechamovimiento() {
        return fechamovimiento;
    }

    public void setFechamovimiento(Date fechamovimiento) {
        this.fechamovimiento = fechamovimiento;
    }

    @XmlTransient
    public Collection<Compra> getCompraCollection() {
        return compraCollection;
    }

    public void setCompraCollection(Collection<Compra> compraCollection) {
        this.compraCollection = compraCollection;
    }

    @XmlTransient
    public Collection<Factura> getFacturaCollection() {
        return facturaCollection;
    }

    public void setFacturaCollection(Collection<Factura> facturaCollection) {
        this.facturaCollection = facturaCollection;
    }

    public Estatuscontable getIdestatuscontable() {
        return idestatuscontable;
    }

    public void setIdestatuscontable(Estatuscontable idestatuscontable) {
        this.idestatuscontable = idestatuscontable;
    }

    public Tipoconjunto getIdtipoconjunto() {
        return idtipoconjunto;
    }

    public void setIdtipoconjunto(Tipoconjunto idtipoconjunto) {
        this.idtipoconjunto = idtipoconjunto;
    }

    public Compra getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(Compra idcompra) {
        this.idcompra = idcompra;
    }

    public Factura getNumerofact() {
        return numerofact;
    }

    public void setNumerofact(Factura numerofact) {
        this.numerofact = numerofact;
    }

    public Librodiario getIdlibrodiario() {
        return idlibrodiario;
    }

    public void setIdlibrodiario(Librodiario idlibrodiario) {
        this.idlibrodiario = idlibrodiario;
    }

    public Autorizacion getIdautorizacion() {
        return idautorizacion;
    }

    public void setIdautorizacion(Autorizacion idautorizacion) {
        this.idautorizacion = idautorizacion;
    }

    public Pagocompra getIdpagocompra() {
        return idpagocompra;
    }

    public void setIdpagocompra(Pagocompra idpagocompra) {
        this.idpagocompra = idpagocompra;
    }

    public Cobroventa getIdcobroventa() {
        return idcobroventa;
    }

    public void setIdcobroventa(Cobroventa idcobroventa) {
        this.idcobroventa = idcobroventa;
    }

    public Otroingreso getIdotroingreso() {
        return idotroingreso;
    }

    public void setIdotroingreso(Otroingreso idotroingreso) {
        this.idotroingreso = idotroingreso;
    }

    public Reposicioncajachica getIdreposicioncajachica() {
        return idreposicioncajachica;
    }

    public void setIdreposicioncajachica(Reposicioncajachica idreposicioncajachica) {
        this.idreposicioncajachica = idreposicioncajachica;
    }

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }
 
    @XmlTransient
    public Collection<Cobroventa> getCobroventaCollection() {
        return cobroventaCollection;
    }

    public void setCobroventaCollection(Collection<Cobroventa> cobroventaCollection) {
        this.cobroventaCollection = cobroventaCollection;
    }

    @XmlTransient
    public Collection<Autorizacion> getAutorizacionCollection() {
        return autorizacionCollection;
    }

    public void setAutorizacionCollection(Collection<Autorizacion> autorizacionCollection) {
        this.autorizacionCollection = autorizacionCollection;
    }

    @XmlTransient
    public Collection<Pagocompra> getPagocompraCollection() {
        return pagocompraCollection;
    }

    public void setPagocompraCollection(Collection<Pagocompra> pagocompraCollection) {
        this.pagocompraCollection = pagocompraCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmaestro != null ? idmaestro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Maestromovimiento)) {
            return false;
        }
        Maestromovimiento other = (Maestromovimiento) object;
        if ((this.idmaestro == null && other.idmaestro != null) || (this.idmaestro != null && !this.idmaestro.equals(other.idmaestro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Maestromovimiento[ idmaestro=" + idmaestro + " ]";
    }

}
