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
@Table(name = "autorizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Autorizacion.findAll", query = "SELECT a FROM Autorizacion a"),
    @NamedQuery(name = "Autorizacion.findByIdautorizacion", query = "SELECT a FROM Autorizacion a WHERE a.idautorizacion = :idautorizacion"),
    @NamedQuery(name = "Autorizacion.findByFechaautorizacion", query = "SELECT a FROM Autorizacion a WHERE a.fechaautorizacion = :fechaautorizacion"),
    @NamedQuery(name = "Autorizacion.findByHora", query = "SELECT a FROM Autorizacion a WHERE a.hora = :hora"),
    @NamedQuery(name = "Autorizacion.findByObservaciones", query = "SELECT a FROM Autorizacion a WHERE a.observaciones = :observaciones")})
public class Autorizacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idautorizacion")
    private Integer idautorizacion;
    @Column(name = "fechaautorizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaautorizacion;
    @Size(max = 12)
    @Column(name = "hora")
    private String hora;
    @Size(max = 255)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario idusuario;
    @JoinColumn(name = "idcompra", referencedColumnName = "idcompra")
    @ManyToOne
    private Compra idcompra;

    public Autorizacion() {
    }

    public Autorizacion(Integer idautorizacion) {
        this.idautorizacion = idautorizacion;
    }

    public Integer getIdautorizacion() {
        return idautorizacion;
    }

    public void setIdautorizacion(Integer idautorizacion) {
        this.idautorizacion = idautorizacion;
    }

    public Date getFechaautorizacion() {
        return fechaautorizacion;
    }

    public void setFechaautorizacion(Date fechaautorizacion) {
        this.fechaautorizacion = fechaautorizacion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public Compra getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(Compra idcompra) {
        this.idcompra = idcompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idautorizacion != null ? idautorizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autorizacion)) {
            return false;
        }
        Autorizacion other = (Autorizacion) object;
        if ((this.idautorizacion == null && other.idautorizacion != null) || (this.idautorizacion != null && !this.idautorizacion.equals(other.idautorizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Autorizacion[ idautorizacion=" + idautorizacion + " ]";
    }
    
}
