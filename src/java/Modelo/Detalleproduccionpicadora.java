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
@Table(name = "detalleproduccionpicadora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleproduccionpicadora.findAll", query = "SELECT d FROM Detalleproduccionpicadora d"),
    @NamedQuery(name = "Detalleproduccionpicadora.findByIddetalleproduccionpicadora", query = "SELECT d FROM Detalleproduccionpicadora d WHERE d.iddetalleproduccionpicadora = :iddetalleproduccionpicadora"),
    @NamedQuery(name = "Detalleproduccionpicadora.findByCantidad", query = "SELECT d FROM Detalleproduccionpicadora d WHERE d.cantidad = :cantidad")})
public class Detalleproduccionpicadora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetalleproduccionpicadora")
    private Integer iddetalleproduccionpicadora;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Double cantidad;
    @JoinColumn(name = "idproduccionpicadora", referencedColumnName = "idproduccionpicadora")
    @ManyToOne
    private Produccionpicadora idproduccionpicadora;
    @JoinColumn(name = "codigo", referencedColumnName = "codigo")
    @ManyToOne
    private Articulo codigo;
    
    public Detalleproduccionpicadora() {
    }

    public Detalleproduccionpicadora(Integer iddetalleproduccionpicadora) {
        this.iddetalleproduccionpicadora = iddetalleproduccionpicadora;
    }

    public Integer getIddetalleproduccionpicadora() {
        return iddetalleproduccionpicadora;
    }

    public void setIddetalleproduccionpicadora(Integer iddetalleproduccionpicadora) {
        this.iddetalleproduccionpicadora = iddetalleproduccionpicadora;
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

    public Produccionpicadora getIdproduccionpicadora() {
        return idproduccionpicadora;
    }

    public void setIdproduccionpicadora(Produccionpicadora idproduccionpicadora) {
        this.idproduccionpicadora = idproduccionpicadora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalleproduccionpicadora != null ? iddetalleproduccionpicadora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleproduccionpicadora)) {
            return false;
        }
        Detalleproduccionpicadora other = (Detalleproduccionpicadora) object;
        if ((this.iddetalleproduccionpicadora == null && other.iddetalleproduccionpicadora != null) || (this.iddetalleproduccionpicadora != null && !this.iddetalleproduccionpicadora.equals(other.iddetalleproduccionpicadora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Detalleproduccionpicadora[ iddetalleproduccionpicadora=" + iddetalleproduccionpicadora + " ]";
    }
    
}
