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
@Table(name = "movimientoinventariopicadora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimientoinventariopicadora.findAll", query = "SELECT m FROM Movimientoinventariopicadora m"),
    @NamedQuery(name = "Movimientoinventariopicadora.findByIdmovimientoinventariopicadora", query = "SELECT m FROM Movimientoinventariopicadora m WHERE m.idmovimientoinventariopicadora = :idmovimientoinventariopicadora"),
    @NamedQuery(name = "Movimientoinventariopicadora.findByCantidadanterior", query = "SELECT m FROM Movimientoinventariopicadora m WHERE m.cantidadanterior = :cantidadanterior"),
    @NamedQuery(name = "Movimientoinventariopicadora.findByAumento", query = "SELECT m FROM Movimientoinventariopicadora m WHERE m.aumento = :aumento"),
    @NamedQuery(name = "Movimientoinventariopicadora.findByDisminucion", query = "SELECT m FROM Movimientoinventariopicadora m WHERE m.disminucion = :disminucion"),
    @NamedQuery(name = "Movimientoinventariopicadora.findByCantidadactual", query = "SELECT m FROM Movimientoinventariopicadora m WHERE m.cantidadactual = :cantidadactual")})
public class Movimientoinventariopicadora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmovimientoinventariopicadora")
    private Integer idmovimientoinventariopicadora;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidadanterior")
    private Double cantidadanterior;
    @Column(name = "aumento")
    private Double aumento;
    @Column(name = "disminucion")
    private Double disminucion;
    @Column(name = "cantidadactual")
    private Double cantidadactual;
    @JoinColumn(name = "idproduccionpicadora", referencedColumnName = "idproduccionpicadora")
    @ManyToOne
    private Produccionpicadora idproduccionpicadora;
    @JoinColumn(name = "iddespachopicadora", referencedColumnName = "iddespachopicadora")
    @ManyToOne
    private Despachopicadora iddespachopicadora;
    @JoinColumn(name = "codigo", referencedColumnName = "codigo")
    @ManyToOne
    private Articulo codigo;

    public Movimientoinventariopicadora() {
    }

    public Movimientoinventariopicadora(Integer idmovimientoinventariopicadora) {
        this.idmovimientoinventariopicadora = idmovimientoinventariopicadora;
    }

    public Integer getIdmovimientoinventariopicadora() {
        return idmovimientoinventariopicadora;
    }

    public void setIdmovimientoinventariopicadora(Integer idmovimientoinventariopicadora) {
        this.idmovimientoinventariopicadora = idmovimientoinventariopicadora;
    }

    public Articulo getCodigo() {
        return codigo;
    }

    public void setCodigo(Articulo codigo) {
        this.codigo = codigo;
    }
    
    public Double getCantidadanterior() {
        return cantidadanterior;
    }

    public void setCantidadanterior(Double cantidadanterior) {
        this.cantidadanterior = cantidadanterior;
    }

    public Double getAumento() {
        return aumento;
    }

    public void setAumento(Double aumento) {
        this.aumento = aumento;
    }

    public Double getDisminucion() {
        return disminucion;
    }

    public void setDisminucion(Double disminucion) {
        this.disminucion = disminucion;
    }

    public Double getCantidadactual() {
        return cantidadactual;
    }

    public void setCantidadactual(Double cantidadactual) {
        this.cantidadactual = cantidadactual;
    }

    public Produccionpicadora getIdproduccionpicadora() {
        return idproduccionpicadora;
    }

    public void setIdproduccionpicadora(Produccionpicadora idproduccionpicadora) {
        this.idproduccionpicadora = idproduccionpicadora;
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
        hash += (idmovimientoinventariopicadora != null ? idmovimientoinventariopicadora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimientoinventariopicadora)) {
            return false;
        }
        Movimientoinventariopicadora other = (Movimientoinventariopicadora) object;
        if ((this.idmovimientoinventariopicadora == null && other.idmovimientoinventariopicadora != null) || (this.idmovimientoinventariopicadora != null && !this.idmovimientoinventariopicadora.equals(other.idmovimientoinventariopicadora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Movimientoinventariopicadora[ idmovimientoinventariopicadora=" + idmovimientoinventariopicadora + " ]";
    }
    
}
