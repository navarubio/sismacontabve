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
@Table(name = "produccionpicadora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produccionpicadora.findAll", query = "SELECT p FROM Produccionpicadora p"),
    @NamedQuery(name = "Produccionpicadora.findByIdproduccionpicadora", query = "SELECT p FROM Produccionpicadora p WHERE p.idproduccionpicadora = :idproduccionpicadora"),
    @NamedQuery(name = "Produccionpicadora.findByFecha", query = "SELECT p FROM Produccionpicadora p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Produccionpicadora.findByHorastrituracion", query = "SELECT p FROM Produccionpicadora p WHERE p.horastrituracion = :horastrituracion"),
    @NamedQuery(name = "Produccionpicadora.findByObservaciones", query = "SELECT p FROM Produccionpicadora p WHERE p.observaciones = :observaciones")})
public class Produccionpicadora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproduccionpicadora")
    private Integer idproduccionpicadora;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "horastrituracion")
    private Double horastrituracion;
    @Size(max = 255)
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(mappedBy = "idproduccionpicadora")
    private Collection<Movimientoinventariopicadora> movimientoinventariopicadoraCollection;
    @OneToMany(mappedBy = "idproduccionpicadora")
    private Collection<Detalleproduccionpicadora> detalleproduccionpicadoraCollection;

    public Produccionpicadora() {
    }

    public Produccionpicadora(Integer idproduccionpicadora) {
        this.idproduccionpicadora = idproduccionpicadora;
    }

    public Integer getIdproduccionpicadora() {
        return idproduccionpicadora;
    }

    public void setIdproduccionpicadora(Integer idproduccionpicadora) {
        this.idproduccionpicadora = idproduccionpicadora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getHorastrituracion() {
        return horastrituracion;
    }

    public void setHorastrituracion(Double horastrituracion) {
        this.horastrituracion = horastrituracion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public Collection<Movimientoinventariopicadora> getMovimientoinventariopicadoraCollection() {
        return movimientoinventariopicadoraCollection;
    }

    public void setMovimientoinventariopicadoraCollection(Collection<Movimientoinventariopicadora> movimientoinventariopicadoraCollection) {
        this.movimientoinventariopicadoraCollection = movimientoinventariopicadoraCollection;
    }

    @XmlTransient
    public Collection<Detalleproduccionpicadora> getDetalleproduccionpicadoraCollection() {
        return detalleproduccionpicadoraCollection;
    }

    public void setDetalleproduccionpicadoraCollection(Collection<Detalleproduccionpicadora> detalleproduccionpicadoraCollection) {
        this.detalleproduccionpicadoraCollection = detalleproduccionpicadoraCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproduccionpicadora != null ? idproduccionpicadora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produccionpicadora)) {
            return false;
        }
        Produccionpicadora other = (Produccionpicadora) object;
        if ((this.idproduccionpicadora == null && other.idproduccionpicadora != null) || (this.idproduccionpicadora != null && !this.idproduccionpicadora.equals(other.idproduccionpicadora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Produccionpicadora[ idproduccionpicadora=" + idproduccionpicadora + " ]";
    }
    
}
