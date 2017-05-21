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
@Table(name = "inventariopicadora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inventariopicadora.findAll", query = "SELECT i FROM Inventariopicadora i"),
    @NamedQuery(name = "Inventariopicadora.findByIdinventariopicadora", query = "SELECT i FROM Inventariopicadora i WHERE i.idinventariopicadora = :idinventariopicadora"),
    @NamedQuery(name = "Inventariopicadora.findByCantidad", query = "SELECT i FROM Inventariopicadora i WHERE i.cantidad = :cantidad")})
public class Inventariopicadora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idinventariopicadora")
    private Integer idinventariopicadora;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Double cantidad;
    @JoinColumn(name = "codigo", referencedColumnName = "codigo")
    @ManyToOne
    private Articulo codigo;    

    public Inventariopicadora() {
    }

    public Inventariopicadora(Integer idinventariopicadora) {
        this.idinventariopicadora = idinventariopicadora;
    }

    public Integer getIdinventariopicadora() {
        return idinventariopicadora;
    }

    public void setIdinventariopicadora(Integer idinventariopicadora) {
        this.idinventariopicadora = idinventariopicadora;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinventariopicadora != null ? idinventariopicadora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventariopicadora)) {
            return false;
        }
        Inventariopicadora other = (Inventariopicadora) object;
        if ((this.idinventariopicadora == null && other.idinventariopicadora != null) || (this.idinventariopicadora != null && !this.idinventariopicadora.equals(other.idinventariopicadora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Inventariopicadora[ idinventariopicadora=" + idinventariopicadora + " ]";
    }
    
}
