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
@Table(name = "detalledespachopicadora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalledespachopicadora.findAll", query = "SELECT d FROM Detalledespachopicadora d"),
    @NamedQuery(name = "Detalledespachopicadora.findByIddetalledespachopicadora", query = "SELECT d FROM Detalledespachopicadora d WHERE d.iddetalledespachopicadora = :iddetalledespachopicadora"),
    @NamedQuery(name = "Detalledespachopicadora.findByCantidad", query = "SELECT d FROM Detalledespachopicadora d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detalledespachopicadora.findByPrecioventa", query = "SELECT d FROM Detalledespachopicadora d WHERE d.precioventa = :precioventa"),
    @NamedQuery(name = "Detalledespachopicadora.findBySubtotal", query = "SELECT d FROM Detalledespachopicadora d WHERE d.subtotal = :subtotal"),
    @NamedQuery(name = "Detalledespachopicadora.findByIva", query = "SELECT d FROM Detalledespachopicadora d WHERE d.iva = :iva"),
    @NamedQuery(name = "Detalledespachopicadora.findByTotalgeneral", query = "SELECT d FROM Detalledespachopicadora d WHERE d.totalgeneral = :totalgeneral")})
public class Detalledespachopicadora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetalledespachopicadora")
    private Integer iddetalledespachopicadora;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Double cantidad;
    @Column(name = "precioventa")
    private Double precioventa;
    @Column(name = "subtotal")
    private Double subtotal;
    @Column(name = "iva")
    private Double iva;
    @Column(name = "totalgeneral")
    private Double totalgeneral;
    @JoinColumn(name = "iddespachopicadora", referencedColumnName = "iddespachopicadora")
    @ManyToOne
    private Despachopicadora iddespachopicadora;
    @JoinColumn(name = "codigo", referencedColumnName = "codigo")
    @ManyToOne
    private Articulo codigo;
    

    public Detalledespachopicadora() {
    }

    public Detalledespachopicadora(Integer iddetalledespachopicadora) {
        this.iddetalledespachopicadora = iddetalledespachopicadora;
    }

    public Integer getIddetalledespachopicadora() {
        return iddetalledespachopicadora;
    }

    public void setIddetalledespachopicadora(Integer iddetalledespachopicadora) {
        this.iddetalledespachopicadora = iddetalledespachopicadora;
    }
    
    public Articulo getCodigo() {
        return codigo;
    }

    public void setCodigo(Articulo codigo) {
        this.codigo = codigo;
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

    public Double getTotalgeneral() {
        return totalgeneral;
    }

    public void setTotalgeneral(Double totalgeneral) {
        this.totalgeneral = totalgeneral;
    }

    public Despachopicadora getIddespachopicadora() {
        return iddespachopicadora;
    }

    public void setIddespachopicadora(Despachopicadora iddespachopicadora) {
        this.iddespachopicadora = iddespachopicadora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalledespachopicadora != null ? iddetalledespachopicadora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalledespachopicadora)) {
            return false;
        }
        Detalledespachopicadora other = (Detalledespachopicadora) object;
        if ((this.iddetalledespachopicadora == null && other.iddetalledespachopicadora != null) || (this.iddetalledespachopicadora != null && !this.iddetalledespachopicadora.equals(other.iddetalledespachopicadora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Detalledespachopicadora[ iddetalledespachopicadora=" + iddetalledespachopicadora + " ]";
    }
    
}
