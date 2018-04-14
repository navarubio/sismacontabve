/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sofimar
 */
@Entity
@Table(name = "detalleconsumocajachica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleconsumocajachica.findAll", query = "SELECT d FROM Detalleconsumocajachica d"),
    @NamedQuery(name = "Detalleconsumocajachica.findByIddetalleconsumocajachica", query = "SELECT d FROM Detalleconsumocajachica d WHERE d.iddetalleconsumocajachica = :iddetalleconsumocajachica"),
    @NamedQuery(name = "Detalleconsumocajachica.findByFechaconsumo", query = "SELECT d FROM Detalleconsumocajachica d WHERE d.fechaconsumo = :fechaconsumo"),
    @NamedQuery(name = "Detalleconsumocajachica.findBySubtotal", query = "SELECT d FROM Detalleconsumocajachica d WHERE d.subtotal = :subtotal"),
    @NamedQuery(name = "Detalleconsumocajachica.findByIva", query = "SELECT d FROM Detalleconsumocajachica d WHERE d.iva = :iva"),
    @NamedQuery(name = "Detalleconsumocajachica.findByToalgeneral", query = "SELECT d FROM Detalleconsumocajachica d WHERE d.toalgeneral = :toalgeneral"),
    @NamedQuery(name = "Detalleconsumocajachica.findByAprobacion", query = "SELECT d FROM Detalleconsumocajachica d WHERE d.aprobacion = :aprobacion")})
public class Detalleconsumocajachica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetalleconsumocajachica")
    private Integer iddetalleconsumocajachica;
    @Column(name = "fechaconsumo")
    @Temporal(TemporalType.DATE)
    private Date fechaconsumo;
    @Size(max = 10)
    @Column(name = "numerofactura")
    private String numerofactura;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "subtotal")
    private Double subtotal;
    @Column(name = "iva")
    private Double iva;
    @Column(name = "toalgeneral")
    private Double toalgeneral;
    @Column(name = "aprobacion")
    private Integer aprobacion;
    @JoinColumn(name = "codigocentrocosto", referencedColumnName = "codigocentrocosto")
    @ManyToOne
    private Centrodecosto codigocentrocosto;
    @JoinColumn(name = "idconsumocajachica", referencedColumnName = "idconsumocajachica")
    @ManyToOne
    private Consumocajachica idconsumocajachica;
    @JoinColumn(name = "rifproveedor", referencedColumnName = "rifproveedor")
    @ManyToOne
    private Proveedor rifproveedor;
    @JoinColumn(name = "idtipogastocajachica", referencedColumnName = "idtipogastocajachica")
    @ManyToOne
    private Tipogastocajachica idtipogastocajachica;

    public Detalleconsumocajachica() {
    }

    public Detalleconsumocajachica(Integer iddetalleconsumocajachica) {
        this.iddetalleconsumocajachica = iddetalleconsumocajachica;
    }

    public Integer getIddetalleconsumocajachica() {
        return iddetalleconsumocajachica;
    }

    public void setIddetalleconsumocajachica(Integer iddetalleconsumocajachica) {
        this.iddetalleconsumocajachica = iddetalleconsumocajachica;
    }

    public Date getFechaconsumo() {
        return fechaconsumo;
    }

    public void setFechaconsumo(Date fechaconsumo) {
        this.fechaconsumo = fechaconsumo;
    }

    public String getNumerofactura() {
        return numerofactura;
    }

    public void setNumerofactura(String numerofactura) {
        this.numerofactura = numerofactura;
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

    public Double getToalgeneral() {
        return toalgeneral;
    }

    public void setToalgeneral(Double toalgeneral) {
        this.toalgeneral = toalgeneral;
    }

    public Integer getAprobacion() {
        return aprobacion;
    }

    public void setAprobacion(Integer aprobacion) {
        this.aprobacion = aprobacion;
    }

    public Centrodecosto getCodigocentrocosto() {
        return codigocentrocosto;
    }

    public void setCodigocentrocosto(Centrodecosto codigocentrocosto) {
        this.codigocentrocosto = codigocentrocosto;
    }

    public Consumocajachica getIdconsumocajachica() {
        return idconsumocajachica;
    }

    public void setIdconsumocajachica(Consumocajachica idconsumocajachica) {
        this.idconsumocajachica = idconsumocajachica;
    }

    public Proveedor getRifproveedor() {
        return rifproveedor;
    }

    public void setRifproveedor(Proveedor rifproveedor) {
        this.rifproveedor = rifproveedor;
    }

    public Tipogastocajachica getIdtipogastocajachica() {
        return idtipogastocajachica;
    }

    public void setIdtipogastocajachica(Tipogastocajachica idtipogastocajachica) {
        this.idtipogastocajachica = idtipogastocajachica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalleconsumocajachica != null ? iddetalleconsumocajachica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleconsumocajachica)) {
            return false;
        }
        Detalleconsumocajachica other = (Detalleconsumocajachica) object;
        if ((this.iddetalleconsumocajachica == null && other.iddetalleconsumocajachica != null) || (this.iddetalleconsumocajachica != null && !this.iddetalleconsumocajachica.equals(other.iddetalleconsumocajachica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Detalleconsumocajachica[ iddetalleconsumocajachica=" + iddetalleconsumocajachica + " ]";
    }

}
