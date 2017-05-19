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
@Table(name = "usomaquinariapicadora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usomaquinariapicadora.findAll", query = "SELECT u FROM Usomaquinariapicadora u"),
    @NamedQuery(name = "Usomaquinariapicadora.findByIdusomaquinariapicadora", query = "SELECT u FROM Usomaquinariapicadora u WHERE u.idusomaquinariapicadora = :idusomaquinariapicadora"),
    @NamedQuery(name = "Usomaquinariapicadora.findByFecha", query = "SELECT u FROM Usomaquinariapicadora u WHERE u.fecha = :fecha"),
    @NamedQuery(name = "Usomaquinariapicadora.findByCantidad", query = "SELECT u FROM Usomaquinariapicadora u WHERE u.cantidad = :cantidad"),
    @NamedQuery(name = "Usomaquinariapicadora.findByPrecio", query = "SELECT u FROM Usomaquinariapicadora u WHERE u.precio = :precio"),
    @NamedQuery(name = "Usomaquinariapicadora.findBySubtotal", query = "SELECT u FROM Usomaquinariapicadora u WHERE u.subtotal = :subtotal"),
    @NamedQuery(name = "Usomaquinariapicadora.findByIva", query = "SELECT u FROM Usomaquinariapicadora u WHERE u.iva = :iva"),
    @NamedQuery(name = "Usomaquinariapicadora.findByTotal", query = "SELECT u FROM Usomaquinariapicadora u WHERE u.total = :total")})
public class Usomaquinariapicadora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusomaquinariapicadora")
    private Integer idusomaquinariapicadora;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Double cantidad;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "subtotal")
    private Double subtotal;
    @Column(name = "iva")
    private Double iva;
    @Column(name = "total")
    private Double total;
    @JoinColumn(name = "idmaquinariapicadora", referencedColumnName = "idmaquinariapicadora")
    @ManyToOne
    private Maquinariapicadora idmaquinariapicadora;
    @OneToMany(mappedBy = "idusomaquinariapicadora")
    private Collection<Distribucionusomaquinaria> distribucionusomaquinariaCollection;

    public Usomaquinariapicadora() {
    }

    public Usomaquinariapicadora(Integer idusomaquinariapicadora) {
        this.idusomaquinariapicadora = idusomaquinariapicadora;
    }

    public Integer getIdusomaquinariapicadora() {
        return idusomaquinariapicadora;
    }

    public void setIdusomaquinariapicadora(Integer idusomaquinariapicadora) {
        this.idusomaquinariapicadora = idusomaquinariapicadora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Maquinariapicadora getIdmaquinariapicadora() {
        return idmaquinariapicadora;
    }

    public void setIdmaquinariapicadora(Maquinariapicadora idmaquinariapicadora) {
        this.idmaquinariapicadora = idmaquinariapicadora;
    }

    @XmlTransient
    public Collection<Distribucionusomaquinaria> getDistribucionusomaquinariaCollection() {
        return distribucionusomaquinariaCollection;
    }

    public void setDistribucionusomaquinariaCollection(Collection<Distribucionusomaquinaria> distribucionusomaquinariaCollection) {
        this.distribucionusomaquinariaCollection = distribucionusomaquinariaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusomaquinariapicadora != null ? idusomaquinariapicadora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usomaquinariapicadora)) {
            return false;
        }
        Usomaquinariapicadora other = (Usomaquinariapicadora) object;
        if ((this.idusomaquinariapicadora == null && other.idusomaquinariapicadora != null) || (this.idusomaquinariapicadora != null && !this.idusomaquinariapicadora.equals(other.idusomaquinariapicadora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Usomaquinariapicadora[ idusomaquinariapicadora=" + idusomaquinariapicadora + " ]";
    }
    
}
