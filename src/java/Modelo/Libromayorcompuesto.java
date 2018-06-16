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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sofimar
 */
@Entity
@Table(name = "libromayorcompuesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Libromayorcompuesto.findAll", query = "SELECT l FROM Libromayorcompuesto l"),
    @NamedQuery(name = "Libromayorcompuesto.findByIdlibromayor", query = "SELECT l FROM Libromayorcompuesto l WHERE l.idlibromayor = :idlibromayor"),
    @NamedQuery(name = "Libromayorcompuesto.findByFecha", query = "SELECT l FROM Libromayorcompuesto l WHERE l.fecha = :fecha"),
    @NamedQuery(name = "Libromayorcompuesto.findByIdlibrodiario", query = "SELECT l FROM Libromayorcompuesto l WHERE l.idlibrodiario = :idlibrodiario"),
    @NamedQuery(name = "Libromayorcompuesto.findByIdplandecuenta", query = "SELECT l FROM Libromayorcompuesto l WHERE l.idplandecuenta = :idplandecuenta"),
    @NamedQuery(name = "Libromayorcompuesto.findBySaldoanterior", query = "SELECT l FROM Libromayorcompuesto l WHERE l.saldoanterior = :saldoanterior"),
    @NamedQuery(name = "Libromayorcompuesto.findByDebe", query = "SELECT l FROM Libromayorcompuesto l WHERE l.debe = :debe"),
    @NamedQuery(name = "Libromayorcompuesto.findByHaber", query = "SELECT l FROM Libromayorcompuesto l WHERE l.haber = :haber"),
    @NamedQuery(name = "Libromayorcompuesto.findBySaldoposterior", query = "SELECT l FROM Libromayorcompuesto l WHERE l.saldoposterior = :saldoposterior")})
public class Libromayorcompuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idlibromayor")
    private Integer idlibromayor;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "idlibrodiario")
    private Integer idlibrodiario;
    @Column(name = "idplandecuenta")
    private Integer idplandecuenta;
    @Size(max = 200)
    @Column(name = "descripcioncuenta")
    private String descripcioncuenta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "saldoanterior")
    private Double saldoanterior;
    @Column(name = "debe")
    private Double debe;
    @Column(name = "haber")
    private Double haber;
    @Column(name = "saldoposterior")
    private Double saldoposterior;
    @Column(name = "idempresa")
    private Integer idempresa;
    @Column(name = "codigocuenta")
    private Integer codigocuenta;

    public Libromayorcompuesto() {
    }

    public Integer getIdlibromayor() {
        return idlibromayor;
    }

    public void setIdlibromayor(Integer idlibromayor) {
        this.idlibromayor = idlibromayor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdlibrodiario() {
        return idlibrodiario;
    }

    public void setIdlibrodiario(Integer idlibrodiario) {
        this.idlibrodiario = idlibrodiario;
    }

    public Integer getIdplandecuenta() {
        return idplandecuenta;
    }

    public void setIdplandecuenta(Integer idplandecuenta) {
        this.idplandecuenta = idplandecuenta;
    }

    public String getDescripcioncuenta() {
        return descripcioncuenta;
    }

    public void setDescripcioncuenta(String descripcioncuenta) {
        this.descripcioncuenta = descripcioncuenta;
    }

    public Double getSaldoanterior() {
        return saldoanterior;
    }

    public void setSaldoanterior(Double saldoanterior) {
        this.saldoanterior = saldoanterior;
    }

    public Double getDebe() {
        return debe;
    }

    public void setDebe(Double debe) {
        this.debe = debe;
    }

    public Double getHaber() {
        return haber;
    }

    public void setHaber(Double haber) {
        this.haber = haber;
    }

    public Double getSaldoposterior() {
        return saldoposterior;
    }

    public void setSaldoposterior(Double saldoposterior) {
        this.saldoposterior = saldoposterior;
    }
    
    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    } 

    public Integer getCodigocuenta() {
        return codigocuenta;
    }

    public void setCodigocuenta(Integer codigocuenta) {
        this.codigocuenta = codigocuenta;
    }
}
