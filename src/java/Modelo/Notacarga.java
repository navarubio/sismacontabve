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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sofimar
 */
@Entity
@Table(name = "notacarga")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notacarga.findAll", query = "SELECT n FROM Notacarga n"),
    @NamedQuery(name = "Notacarga.findByIdnotacarga", query = "SELECT n FROM Notacarga n WHERE n.idnotacarga = :idnotacarga"),
    @NamedQuery(name = "Notacarga.findByFecha", query = "SELECT n FROM Notacarga n WHERE n.fecha = :fecha"),
    @NamedQuery(name = "Notacarga.findByObservacionesnotacarga", query = "SELECT n FROM Notacarga n WHERE n.observacionesnotacarga = :observacionesnotacarga"),
    @NamedQuery(name = "Notacarga.findByCantidad", query = "SELECT n FROM Notacarga n WHERE n.cantidad = :cantidad"),
    @NamedQuery(name = "Notacarga.findByBimponible", query = "SELECT n FROM Notacarga n WHERE n.bimponible = :bimponible"),
    @NamedQuery(name = "Notacarga.findByIva", query = "SELECT n FROM Notacarga n WHERE n.iva = :iva"),
    @NamedQuery(name = "Notacarga.findByTotalgeneral", query = "SELECT n FROM Notacarga n WHERE n.totalgeneral = :totalgeneral"),
    @NamedQuery(name = "Notacarga.findByPendiente", query = "SELECT n FROM Notacarga n WHERE n.pendiente = :pendiente")})
public class Notacarga implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnotacarga")
    private Integer idnotacarga;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 255)
    @Column(name = "observacionesnotacarga")
    private String observacionesnotacarga;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Double cantidad;
    @Column(name = "bimponible")
    private Double bimponible;
    @Column(name = "iva")
    private Double iva;
    @Column(name = "totalgeneral")
    private Double totalgeneral;
    @Column(name = "pendiente")
    private Double pendiente;
    @JoinColumn(name = "rifcliente", referencedColumnName = "rifcliente")
    @ManyToOne
    private Cliente rifcliente; 
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario idusuario;

    @OneToMany(mappedBy = "idnotacarga")
    private Collection<Detallenotacarga> detallenotacargaCollection;

    @OneToMany(mappedBy = "idnotacarga")
    private Collection<Despachopicadora> despachopicadoraCollection;

    
    public Notacarga() {
    }

    public Notacarga(Integer idnotacarga) {
        this.idnotacarga = idnotacarga;
    }

    public Integer getIdnotacarga() {
        return idnotacarga;
    }

    public void setIdnotacarga(Integer idnotacarga) {
        this.idnotacarga = idnotacarga;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservacionesnotacarga() {
        return observacionesnotacarga;
    }

    public void setObservacionesnotacarga(String observacionesnotacarga) {
        this.observacionesnotacarga = observacionesnotacarga;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getBimponible() {
        return bimponible;
    }

    public void setBimponible(Double bimponible) {
        this.bimponible = bimponible;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getTotalgeneral() {
        return totalgeneral;
    }

    public void setTotalgeneral(Double totalgeneral) {
        this.totalgeneral = totalgeneral;
    }

    public Double getPendiente() {
        return pendiente;
    }

    public void setPendiente(Double pendiente) {
        this.pendiente = pendiente;
    }

    public Cliente getRifcliente() {
        return rifcliente;
    }

    public void setRifcliente(Cliente rifcliente) {
        this.rifcliente = rifcliente;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @XmlTransient
    public Collection<Detallenotacarga> getDetallenotacargaCollection() {
        return detallenotacargaCollection;
    }

    public void setDetallenotacargaCollection(Collection<Detallenotacarga> detallenotacargaCollection) {
        this.detallenotacargaCollection = detallenotacargaCollection;
    }
    
    @XmlTransient
    public Collection<Despachopicadora> getDespachopicadoraCollection() {
        return despachopicadoraCollection;
    }

    public void setDespachopicadoraCollection(Collection<Despachopicadora> despachopicadoraCollection) {
        this.despachopicadoraCollection = despachopicadoraCollection;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnotacarga != null ? idnotacarga.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notacarga)) {
            return false;
        }
        Notacarga other = (Notacarga) object;
        if ((this.idnotacarga == null && other.idnotacarga != null) || (this.idnotacarga != null && !this.idnotacarga.equals(other.idnotacarga))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Notacarga[ idnotacarga=" + idnotacarga + " ]";
    }
    
}
