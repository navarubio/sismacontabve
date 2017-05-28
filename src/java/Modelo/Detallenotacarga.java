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
@Table(name = "detallenotacarga")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallenotacarga.findAll", query = "SELECT d FROM Detallenotacarga d"),
    @NamedQuery(name = "Detallenotacarga.findByIddetallenotacarga", query = "SELECT d FROM Detallenotacarga d WHERE d.iddetallenotacarga = :iddetallenotacarga"),
    @NamedQuery(name = "Detallenotacarga.findByCantidad", query = "SELECT d FROM Detallenotacarga d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detallenotacarga.findByPrecioventa", query = "SELECT d FROM Detallenotacarga d WHERE d.precioventa = :precioventa"),
    @NamedQuery(name = "Detallenotacarga.findByTotalnota", query = "SELECT d FROM Detallenotacarga d WHERE d.totalnota= :totalnota"),
    @NamedQuery(name = "Detallenotacarga.findByPordespachar", query = "SELECT d FROM Detallenotacarga d WHERE d.pordespachar = :pordespachar")})
public class Detallenotacarga implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetallenotacarga")
    private Integer iddetallenotacarga;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Double cantidad;
    @Column(name = "precioventa")
    private Double precioventa;
    @Column(name = "subtotal")
    private Double subtotal;
    @Column(name = "iva")
    private Double iva;
    @Column(name = "totalnota")
    private Double totalnota;
    @Column(name = "pordespachar")
    private Double pordespachar;
    @JoinColumn(name = "idnotacarga", referencedColumnName = "idnotacarga")
    @ManyToOne
    private Notacarga idnotacarga;
    @JoinColumn(name = "codigo", referencedColumnName = "codigo")
    @ManyToOne
    private Articulo codigo;


    public Detallenotacarga() {
    }

    public Detallenotacarga(Integer iddetallenotacarga) {
        this.iddetallenotacarga = iddetallenotacarga;
    }

    public Integer getIddetallenotacarga() {
        return iddetallenotacarga;
    }

    public void setIddetallenotacarga(Integer iddetallenotacarga) {
        this.iddetallenotacarga = iddetallenotacarga;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioventa() {
        return precioventa;
    }

    public void setPrecioventa(Double precioventa) {
        this.precioventa = precioventa;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getTotalnota() {
        return totalnota;
    }

    public void setTotalnota(Double totalnota) {
        this.totalnota = totalnota;
    }

    public Double getPordespachar() {
        return pordespachar;
    }

    public void setPordespachar(Double pordespachar) {
        this.pordespachar = pordespachar;
    }

    public Notacarga getIdnotacarga() {
        return idnotacarga;
    }

    public void setIdnotacarga(Notacarga idnotacarga) {
        this.idnotacarga = idnotacarga;
    }

    public Articulo getCodigo() {
        return codigo;
    }

    public void setCodigo(Articulo codigo) {
        this.codigo = codigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetallenotacarga != null ? iddetallenotacarga.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallenotacarga)) {
            return false;
        }
        Detallenotacarga other = (Detallenotacarga) object;
        if ((this.iddetallenotacarga == null && other.iddetallenotacarga != null) || (this.iddetallenotacarga != null && !this.iddetallenotacarga.equals(other.iddetallenotacarga))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Detallenotacarga[ iddetallenotacarga=" + iddetallenotacarga + " ]";
    }
        
}
