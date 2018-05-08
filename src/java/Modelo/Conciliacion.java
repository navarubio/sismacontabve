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
@Table(name = "conciliacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conciliacion.findAll", query = "SELECT c FROM Conciliacion c"),
    @NamedQuery(name = "Conciliacion.findByIdconciliacion", query = "SELECT c FROM Conciliacion c WHERE c.idconciliacion = :idconciliacion"),
    @NamedQuery(name = "Conciliacion.findBySerialconciliacion", query = "SELECT c FROM Conciliacion c WHERE c.serialconciliacion = :serialconciliacion"),
    @NamedQuery(name = "Conciliacion.findByFecharegistro", query = "SELECT c FROM Conciliacion c WHERE c.fecharegistro = :fecharegistro"),
    @NamedQuery(name = "Conciliacion.findByFechaconciliacion", query = "SELECT c FROM Conciliacion c WHERE c.fechaconciliacion = :fechaconciliacion"),
    @NamedQuery(name = "Conciliacion.findBySaldoedocuenta", query = "SELECT c FROM Conciliacion c WHERE c.saldoedocuenta = :saldoedocuenta"),
    @NamedQuery(name = "Conciliacion.findByDebitoscontables", query = "SELECT c FROM Conciliacion c WHERE c.debitoscontables = :debitoscontables"),
    @NamedQuery(name = "Conciliacion.findByCreditoscontables", query = "SELECT c FROM Conciliacion c WHERE c.creditoscontables = :creditoscontables"),
    @NamedQuery(name = "Conciliacion.findBySaldocontable", query = "SELECT c FROM Conciliacion c WHERE c.saldocontable = :saldocontable"),
    @NamedQuery(name = "Conciliacion.findBySaldocontableajustado", query = "SELECT c FROM Conciliacion c WHERE c.saldocontableajustado = :saldocontableajustado"),
    @NamedQuery(name = "Conciliacion.findBySaldobancarioajustado", query = "SELECT c FROM Conciliacion c WHERE c.saldobancarioajustado = :saldobancarioajustado"),
    @NamedQuery(name = "Conciliacion.findByIdusuario", query = "SELECT c FROM Conciliacion c WHERE c.idusuario = :idusuario")})
public class Conciliacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idconciliacion")
    private Integer idconciliacion;
    @Column(name = "serialconciliacion")
    private Integer serialconciliacion;
    @Column(name = "fecharegistro")
    @Temporal(TemporalType.DATE)
    private Date fecharegistro;
    @Column(name = "fechaconciliacion")
    @Temporal(TemporalType.DATE)
    private Date fechaconciliacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "saldoedocuenta")
    private Double saldoedocuenta;
    @Column(name = "debitoscontables")
    private Double debitoscontables;
    @Column(name = "creditoscontables")
    private Double creditoscontables;
    @Column(name = "saldocontable")
    private Double saldocontable;
    @Column(name = "saldocontableajustado")
    private Double saldocontableajustado;
    @Column(name = "saldobancarioajustado")
    private Double saldobancarioajustado;
    @Column(name = "idusuario")
    private Integer idusuario;
    @JoinColumn(name = "idcuentabancaria", referencedColumnName = "idcuentabancaria")
    @ManyToOne
    private Cuentabancaria idcuentabancaria;
    @OneToMany(mappedBy = "idconciliacion")
    private Collection<Movimientobancario> movimientobancarioCollection;

    public Conciliacion() {
    }

    public Conciliacion(Integer idconciliacion) {
        this.idconciliacion = idconciliacion;
    }

    public Integer getIdconciliacion() {
        return idconciliacion;
    }

    public void setIdconciliacion(Integer idconciliacion) {
        this.idconciliacion = idconciliacion;
    }

    public Integer getSerialconciliacion() {
        return serialconciliacion;
    }

    public void setSerialconciliacion(Integer serialconciliacion) {
        this.serialconciliacion = serialconciliacion;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public Date getFechaconciliacion() {
        return fechaconciliacion;
    }

    public void setFechaconciliacion(Date fechaconciliacion) {
        this.fechaconciliacion = fechaconciliacion;
    }

    public Double getSaldoedocuenta() {
        return saldoedocuenta;
    }

    public void setSaldoedocuenta(Double saldoedocuenta) {
        this.saldoedocuenta = saldoedocuenta;
    }

    public Double getDebitoscontables() {
        return debitoscontables;
    }

    public void setDebitoscontables(Double debitoscontables) {
        this.debitoscontables = debitoscontables;
    }

    public Double getCreditoscontables() {
        return creditoscontables;
    }

    public void setCreditoscontables(Double creditoscontables) {
        this.creditoscontables = creditoscontables;
    }

    public Double getSaldocontable() {
        return saldocontable;
    }

    public void setSaldocontable(Double saldocontable) {
        this.saldocontable = saldocontable;
    }

    public Double getSaldocontableajustado() {
        return saldocontableajustado;
    }

    public void setSaldocontableajustado(Double saldocontableajustado) {
        this.saldocontableajustado = saldocontableajustado;
    }

    public Double getSaldobancarioajustado() {
        return saldobancarioajustado;
    }

    public void setSaldobancarioajustado(Double saldobancarioajustado) {
        this.saldobancarioajustado = saldobancarioajustado;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Cuentabancaria getIdcuentabancaria() {
        return idcuentabancaria;
    }

    public void setIdcuentabancaria(Cuentabancaria idcuentabancaria) {
        this.idcuentabancaria = idcuentabancaria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idconciliacion != null ? idconciliacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conciliacion)) {
            return false;
        }
        Conciliacion other = (Conciliacion) object;
        if ((this.idconciliacion == null && other.idconciliacion != null) || (this.idconciliacion != null && !this.idconciliacion.equals(other.idconciliacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Conciliacion[ idconciliacion=" + idconciliacion + " ]";
    }
    
    @XmlTransient
    public Collection<Movimientobancario> getMovimientobancarioCollection() {
        return movimientobancarioCollection;
    }

    public void setMovimientobancarioCollection(Collection<Movimientobancario> movimientobancarioCollection) {
        this.movimientobancarioCollection = movimientobancarioCollection;
    }
    
}
