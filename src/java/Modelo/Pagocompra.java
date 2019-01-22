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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Inpeca
 */
@Entity
@Table(name = "pagocompra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagocompra.findAll", query = "SELECT p FROM Pagocompra p"),
    @NamedQuery(name = "Pagocompra.findByIdpagocompra", query = "SELECT p FROM Pagocompra p WHERE p.idpagocompra = :idpagocompra"),
    @NamedQuery(name = "Pagocompra.findByCuentareceptora", query = "SELECT p FROM Pagocompra p WHERE p.cuentareceptora = :cuentareceptora"),
    @NamedQuery(name = "Pagocompra.findByTotalpago", query = "SELECT p FROM Pagocompra p WHERE p.totalpago = :totalpago"),
    @NamedQuery(name = "Pagocompra.findByAprobacion", query = "SELECT p FROM Pagocompra p WHERE p.aprobacion = :aprobacion"),
    @NamedQuery(name = "Pagocompra.findByObservacionespago", query = "SELECT p FROM Pagocompra p WHERE p.observacionespago= :observacionespago"),
    @NamedQuery(name = "Pagocompra.findByFechapago", query = "SELECT p FROM Pagocompra p WHERE p.fechapago = :fechapago")})
public class Pagocompra implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "montoretenido")
    private double montoretenido;
    @OneToMany(mappedBy = "idpagocompra")
    private Collection<Maestromovimiento> maestromovimientoCollection;
    @JoinColumn(name = "idmaestro", referencedColumnName = "idmaestro")
    @ManyToOne
    private Maestromovimiento idmaestro;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpagocompra")
    private Integer idpagocompra;
    @Size(max = 23)
    @Column(name = "cuentareceptora")
    private String cuentareceptora;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalpago")
    private Double totalpago;
    @Column(name = "saldopendiente")
    private Double saldopendiente;
    @Size(max = 15)
    @Column(name = "aprobacion")
    private String aprobacion;
    @Size(max = 255)
    @Column(name = "observacionespago")
    private String observacionespago;
    @Column(name = "fechapago")
    @Temporal(TemporalType.DATE)
    private Date fechapago;
    @OneToMany(mappedBy = "idpagocompra")
    private Collection<Retencionpago> retencionpagoCollection;
    @OneToMany(mappedBy = "idpagocompra")
    private Collection<Detalleretencionivaef> detalleretencionivaefCollection;
    @JoinColumn(name = "idcompra", referencedColumnName = "idcompra")
    @ManyToOne
    private Compra idcompra;
    @JoinColumn(name = "idtipopago", referencedColumnName = "idtipopago")
    @ManyToOne
    private Tipopago idtipopago;
    @JoinColumn(name = "idbanco", referencedColumnName = "idbanco")
    @ManyToOne
    private Banco idbanco;
    @JoinColumn(name = "idcuentabancaria", referencedColumnName = "idcuentabancaria")
    @ManyToOne
    private Cuentabancaria idcuentabancaria;
    @JoinColumn(name = "idplandecuenta", referencedColumnName = "idplandecuenta")
    @ManyToOne
    private Plandecuenta idplandecuenta;
    @JoinColumn(name = "iddepartamento", referencedColumnName = "iddepartamento")
    @ManyToOne
    private Departamento iddepartamento;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario idusuario;
    @Column(name = "serialpagocompra")
    private Integer serialpagocompra;
    @Size(max = 10)
    @Column(name = "autorizacion")
    private String autorizacion;
    

    public Pagocompra() {
    }

    public Pagocompra(Integer idpagocompra) {
        this.idpagocompra = idpagocompra;
    }

    public Integer getIdpagocompra() {
        return idpagocompra;
    }

    public void setIdpagocompra(Integer idpagocompra) {
        this.idpagocompra = idpagocompra;
    }

    public String getCuentareceptora() {
        return cuentareceptora;
    }

    public void setCuentareceptora(String cuentareceptora) {
        this.cuentareceptora = cuentareceptora;
    }

    public Double getTotalpago() {
        return totalpago;
    }

    public void setTotalpago(Double totalpago) {
        this.totalpago = totalpago;
    }

    public String getAprobacion() {
        return aprobacion;
    }

    public Double getSaldopendiente() {
        return saldopendiente;
    }

    public void setSaldopendiente(Double saldopendiente) {
        this.saldopendiente = saldopendiente;
    }

    public String getObservacionespago() {
        return observacionespago;
    }

    public void setObservacionespago(String observacionespago) {
        this.observacionespago = observacionespago;
    }

    public void setAprobacion(String aprobacion) {
        this.aprobacion = aprobacion;
    }

    public Date getFechapago() {
        return fechapago;
    }

    public void setFechapago(Date fechapago) {
        this.fechapago = fechapago;
    }

    @XmlTransient
    public Collection<Retencionpago> getRetencionpagoCollection() {
        return retencionpagoCollection;
    }

    public void setRetencionpagoCollection(Collection<Retencionpago> retencionpagoCollection) {
        this.retencionpagoCollection = retencionpagoCollection;
    }

    @XmlTransient
    public Collection<Detalleretencionivaef> getDetallerencionivaefCollection() {
        return detalleretencionivaefCollection;
    }

    public void setDetalleretencionivaefCollection(Collection<Detalleretencionivaef> detalleretencionivaefCollection) {
        this.detalleretencionivaefCollection = detalleretencionivaefCollection;
    }

    public Compra getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(Compra idcompra) {
        this.idcompra = idcompra;
    }

    public Tipopago getIdtipopago() {
        return idtipopago;
    }

    public void setIdtipopago(Tipopago idtipopago) {
        this.idtipopago = idtipopago;
    }

    public Banco getIdbanco() {
        return idbanco;
    }

    public void setIdbanco(Banco idbanco) {
        this.idbanco = idbanco;
    }

    public Cuentabancaria getIdcuentabancaria() {
        return idcuentabancaria;
    }

    public void setIdcuentabancaria(Cuentabancaria idcuentabancaria) {
        this.idcuentabancaria = idcuentabancaria;
    }

    public Plandecuenta getIdplandecuenta() {
        return idplandecuenta;
    }

    public void setIdplandecuenta(Plandecuenta idplandecuenta) {
        this.idplandecuenta = idplandecuenta;
    }

    public Departamento getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(Departamento iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public Integer getSerialpagocompra() {
        return serialpagocompra;
    }

    public void setSerialpagocompra(Integer serialpagocompra) {
        this.serialpagocompra = serialpagocompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpagocompra != null ? idpagocompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagocompra)) {
            return false;
        }
        Pagocompra other = (Pagocompra) object;
        if ((this.idpagocompra == null && other.idpagocompra != null) || (this.idpagocompra != null && !this.idpagocompra.equals(other.idpagocompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Pagocompra[ idpagocompra=" + idpagocompra + " ]";
    }

    public double getMontoretenido() {
        return montoretenido;
    }

    public void setMontoretenido(double montoretenido) {
        this.montoretenido = montoretenido;
    }

    @XmlTransient
    public Collection<Maestromovimiento> getMaestromovimientoCollection() {
        return maestromovimientoCollection;
    }

    public void setMaestromovimientoCollection(Collection<Maestromovimiento> maestromovimientoCollection) {
        this.maestromovimientoCollection = maestromovimientoCollection;
    }

    public Maestromovimiento getIdmaestro() {
        return idmaestro;
    }

    public void setIdmaestro(Maestromovimiento idmaestro) {
        this.idmaestro = idmaestro;
    }

    public String getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(String autorizacion) {
        this.autorizacion = autorizacion;
    }

}
