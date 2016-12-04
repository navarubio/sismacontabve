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
@Table(name = "detallefactura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallefactura.findAll", query = "SELECT d FROM Detallefactura d"),
    @NamedQuery(name = "Detallefactura.findByIddetallefact", query = "SELECT d FROM Detallefactura d WHERE d.iddetallefact = :iddetallefact"),
    @NamedQuery(name = "Detallefactura.findByUnidades", query = "SELECT d FROM Detallefactura d WHERE d.unidades = :unidades"),
    @NamedQuery(name = "Detallefactura.findByPreciocosto", query = "SELECT d FROM Detallefactura d WHERE d.preciocosto = :preciocosto"),
    @NamedQuery(name = "Detallefactura.findByPrecioventabruto", query = "SELECT d FROM Detallefactura d WHERE d.precioventabruto = :precioventabruto"),
    @NamedQuery(name = "Detallefactura.findByDescuento", query = "SELECT d FROM Detallefactura d WHERE d.descuento = :descuento"),
    @NamedQuery(name = "Detallefactura.findByPrecioventaneta", query = "SELECT d FROM Detallefactura d WHERE d.precioventaneta = :precioventaneta"),
    @NamedQuery(name = "Detallefactura.findByTotalcosto", query = "SELECT d FROM Detallefactura d WHERE d.totalcosto = :totalcosto"),
    @NamedQuery(name = "Detallefactura.findByTotaldescuento", query = "SELECT d FROM Detallefactura d WHERE d.totaldescuento = :totaldescuento"),
    @NamedQuery(name = "Detallefactura.findByTotalventa", query = "SELECT d FROM Detallefactura d WHERE d.totalventa = :totalventa")})
public class Detallefactura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetallefact")
    private Integer iddetallefact;
    @Column(name = "unidades")
    private Integer unidades;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preciocosto")
    private Double preciocosto;
    @Column(name = "precioventabruto")
    private Double precioventabruto;
    @Column(name = "descuento")
    private Double descuento;
    @Column(name = "precioventaneta")
    private Double precioventaneta;
    @Column(name = "totalcosto")
    private Double totalcosto;
    @Column(name = "totaldescuento")
    private Double totaldescuento;
    @Column(name = "totalventa")
    private Double totalventa;
    @JoinColumn(name = "numerofact", referencedColumnName = "numerofact")
    @ManyToOne
    private Factura numerofact;
    @JoinColumn(name = "codigo", referencedColumnName = "codigo")
    @ManyToOne
    private Articulo codigo;

    public Detallefactura() {
    }

    public Detallefactura(Integer iddetallefact) {
        this.iddetallefact = iddetallefact;
    }

    public Integer getIddetallefact() {
        return iddetallefact;
    }

    public void setIddetallefact(Integer iddetallefact) {
        this.iddetallefact = iddetallefact;
    }

    public Integer getUnidades() {
        return unidades;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }

    public Double getPreciocosto() {
        return preciocosto;
    }

    public void setPreciocosto(Double preciocosto) {
        this.preciocosto = preciocosto;
    }

    public Double getPrecioventabruto() {
        return precioventabruto;
    }

    public void setPrecioventabruto(Double precioventabruto) {
        this.precioventabruto = precioventabruto;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getPrecioventaneta() {
        return precioventaneta;
    }

    public void setPrecioventaneta(Double precioventaneta) {
        this.precioventaneta = precioventaneta;
    }

    public Double getTotalcosto() {
        return totalcosto;
    }

    public void setTotalcosto(Double totalcosto) {
        this.totalcosto = totalcosto;
    }

    public Double getTotaldescuento() {
        return totaldescuento;
    }

    public void setTotaldescuento(Double totaldescuento) {
        this.totaldescuento = totaldescuento;
    }

    public Double getTotalventa() {
        return totalventa;
    }

    public void setTotalventa(Double totalventa) {
        this.totalventa = totalventa;
    }

    public Factura getNumerofact() {
        return numerofact;
    }

    public void setNumerofact(Factura numerofact) {
        this.numerofact = numerofact;
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
        hash += (iddetallefact != null ? iddetallefact.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallefactura)) {
            return false;
        }
        Detallefactura other = (Detallefactura) object;
        if ((this.iddetallefact == null && other.iddetallefact != null) || (this.iddetallefact != null && !this.iddetallefact.equals(other.iddetallefact))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Detallefactura[ iddetallefact=" + iddetallefact + " ]";
    }
    
}
