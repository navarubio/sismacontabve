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
    @NamedQuery(name = "Detallefactura.findByPrecioventa", query = "SELECT d FROM Detallefactura d WHERE d.precioventa = :precioventa"),
    @NamedQuery(name = "Detallefactura.findBySubtotal", query = "SELECT d FROM Detallefactura d WHERE d.subtotal= :subtotal"),
    @NamedQuery(name = "Detallefactura.findByTributoiva", query = "SELECT d FROM Detallefactura d WHERE d.tributoiva= :tributoiva"),
    @NamedQuery(name = "Detallefactura.findByTotal", query = "SELECT d FROM Detallefactura d WHERE d.total= :total")})
public class Detallefactura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetallefact")
    private Integer iddetallefact;
    @Column(name = "unidades")
    private Double unidades;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precioventa")
    private Double precioventa;
    @Column(name = "subtotal")
    private Double subtotal;
    @Column(name = "tributoiva")
    private Double tributoiva;
    @Column(name = "total")
    private Double total;
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

    public Double getUnidades() {
        return unidades;
    }

    public void setUnidades(Double unidades) {
        this.unidades = unidades;
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

    public Double getTributoiva() {
        return tributoiva;
    }

    public void setTributoiva(Double tributoiva) {
        this.tributoiva= tributoiva;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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
