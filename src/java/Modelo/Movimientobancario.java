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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Inpeca
 */
@Entity
@Table(name = "movimientobancario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimientobancario.findAll", query = "SELECT m FROM Movimientobancario m"),
    @NamedQuery(name = "Movimientobancario.findByIdmovimiento", query = "SELECT m FROM Movimientobancario m WHERE m.idmovimiento = :idmovimiento"),
    @NamedQuery(name = "Movimientobancario.findByIdcuentabancaria", query = "SELECT m FROM Movimientobancario m WHERE m.idcuentabancaria = :idcuentabancaria"),
    @NamedQuery(name = "Movimientobancario.findByFecha", query = "SELECT m FROM Movimientobancario m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Movimientobancario.findBySaldoanterior", query = "SELECT m FROM Movimientobancario m WHERE m.saldoanterior = :saldoanterior"),
    @NamedQuery(name = "Movimientobancario.findByDebito", query = "SELECT m FROM Movimientobancario m WHERE m.debito = :debito"),
    @NamedQuery(name = "Movimientobancario.findByCredito", query = "SELECT m FROM Movimientobancario m WHERE m.credito = :credito"),
    @NamedQuery(name = "Movimientobancario.findBySaldoactual", query = "SELECT m FROM Movimientobancario m WHERE m.saldoactual = :saldoactual")})
public class Movimientobancario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmovimiento")
    private Integer idmovimiento;
//    @Column(name = "idcuentabancaria")
//    private Integer idcuentabancaria;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "saldoanterior")
    private Double saldoanterior;
    @Column(name = "debito")
    private Double debito;
    @Column(name = "credito")
    private Double credito;
    @Column(name = "saldoactual")
    private Double saldoactual;
    @JoinColumn(name = "idcuentabancaria", referencedColumnName = "idcuentabancaria")
    @ManyToOne
    private Cuentabancaria idcuentabancaria;
    @JoinColumn(name = "idpagocompra", referencedColumnName = "idpagocompra")
    @ManyToOne
    private Pagocompra idpagocompra;
    @JoinColumn(name = "idcobroventa", referencedColumnName = "idcobroventa")
    @ManyToOne
    private Cobroventa idcobroventa;
    @JoinColumn(name = "idotroingreso", referencedColumnName = "idotroingreso")
    @ManyToOne
    private Otroingreso idotroingreso;


    public Movimientobancario() {
    }

    public Movimientobancario(Integer idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public Integer getIdmovimiento() {
        return idmovimiento;
    }

    public void setIdmovimiento(Integer idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public Cuentabancaria getIdcuentabancaria() {
        return idcuentabancaria;
    }

    public void setIdcuentabancaria(Cuentabancaria idcuentabancaria) {
        this.idcuentabancaria = idcuentabancaria;
    }

    public Pagocompra getIdpagocompra() {
        return idpagocompra;
    }

    public void setIdpagocompra(Pagocompra idpagocompra) {
        this.idpagocompra = idpagocompra;
    }

    public Cobroventa getIdcobroventa() {
        return idcobroventa;
    }

    public void setIdcobroventa(Cobroventa idcobroventa) {
        this.idcobroventa = idcobroventa;
    }

    public Otroingreso getIdotroingreso() {
        return idotroingreso;
    }

    public void setIdotroingreso(Otroingreso idotroingreso) {
        this.idotroingreso = idotroingreso;
    }

    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getSaldoanterior() {
        return saldoanterior;
    }

    public void setSaldoanterior(Double saldoanterior) {
        this.saldoanterior = saldoanterior;
    }

    public Double getDebito() {
        return debito;
    }

    public void setDebito(Double debito) {
        this.debito = debito;
    }

    public Double getCredito() {
        return credito;
    }

    public void setCredito(Double credito) {
        this.credito = credito;
    }

    public Double getSaldoactual() {
        return saldoactual;
    }

    public void setSaldoactual(Double saldoactual) {
        this.saldoactual = saldoactual;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmovimiento != null ? idmovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimientobancario)) {
            return false;
        }
        Movimientobancario other = (Movimientobancario) object;
        if ((this.idmovimiento == null && other.idmovimiento != null) || (this.idmovimiento != null && !this.idmovimiento.equals(other.idmovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Movimientobancario[ idmovimiento=" + idmovimiento + " ]";
    }
    
}
