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
@Table(name = "cobroventa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cobroventa.findAll", query = "SELECT c FROM Cobroventa c"),
    @NamedQuery(name = "Cobroventa.findByIdcobroventa", query = "SELECT c FROM Cobroventa c WHERE c.idcobroventa = :idcobroventa"),
    @NamedQuery(name = "Cobroventa.findByCuentaoriginadora", query = "SELECT c FROM Cobroventa c WHERE c.cuentaoriginadora = :cuentaoriginadora"),
    @NamedQuery(name = "Cobroventa.findByMontocobrado", query = "SELECT c FROM Cobroventa c WHERE c.montocobrado = :montocobrado"),
    @NamedQuery(name = "Cobroventa.findByAprobacion", query = "SELECT c FROM Cobroventa c WHERE c.aprobacion = :aprobacion"),
    @NamedQuery(name = "Cobroventa.findByFechacobro", query = "SELECT c FROM Cobroventa c WHERE c.fechacobro = :fechacobro"),
    @NamedQuery(name = "Cobroventa.findByMontopendiente", query = "SELECT c FROM Cobroventa c WHERE c.montopendiente = :montopendiente")})
public class Cobroventa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcobroventa")
    private Integer idcobroventa;
    @Size(max = 23)
    @Column(name = "cuentaoriginadora")
    private String cuentaoriginadora;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montocobrado")
    private Double montocobrado;
    @Size(max = 15)
    @Column(name = "aprobacion")
    private String aprobacion;
    @Column(name = "fechacobro")
    @Temporal(TemporalType.DATE)
    private Date fechacobro;
    @Column(name = "montopendiente")
    private Double montopendiente;
    @JoinColumn(name = "numerofact", referencedColumnName = "numerofact")
    @ManyToOne
    private Factura numerofact;
    @JoinColumn(name = "idtipopago", referencedColumnName = "idtipopago")
    @ManyToOne
    private Tipopago idtipopago;
    @JoinColumn(name = "idcuentabancaria", referencedColumnName = "idcuentabancaria")
    @ManyToOne
    private Cuentabancaria idcuentabancaria;
    @JoinColumn(name = "idestatuscontable", referencedColumnName = "idestatuscontable")
    @ManyToOne
    private Estatuscontable idestatuscontable;

    public Cobroventa() {
    }

    public Cobroventa(Integer idcobroventa) {
        this.idcobroventa = idcobroventa;
    }

    public Integer getIdcobroventa() {
        return idcobroventa;
    }

    public void setIdcobroventa(Integer idcobroventa) {
        this.idcobroventa = idcobroventa;
    }

    public String getCuentaoriginadora() {
        return cuentaoriginadora;
    }

    public void setCuentaoriginadora(String cuentaoriginadora) {
        this.cuentaoriginadora = cuentaoriginadora;
    }

    public Double getMontocobrado() {
        return montocobrado;
    }

    public void setMontocobrado(Double montocobrado) {
        this.montocobrado = montocobrado;
    }

    public String getAprobacion() {
        return aprobacion;
    }

    public void setAprobacion(String aprobacion) {
        this.aprobacion = aprobacion;
    }

    public Date getFechacobro() {
        return fechacobro;
    }

    public void setFechacobro(Date fechacobro) {
        this.fechacobro = fechacobro;
    }

    public Double getMontopendiente() {
        return montopendiente;
    }

    public void setMontopendiente(Double montopendiente) {
        this.montopendiente = montopendiente;
    }

    public Factura getNumerofact() {
        return numerofact;
    }

    public void setNumerofact(Factura numerofact) {
        this.numerofact = numerofact;
    }

    public Tipopago getIdtipopago() {
        return idtipopago;
    }

    public void setIdtipopago(Tipopago idtipopago) {
        this.idtipopago = idtipopago;
    }

    public Cuentabancaria getIdcuentabancaria() {
        return idcuentabancaria;
    }

    public void setIdcuentabancaria(Cuentabancaria idcuentabancaria) {
        this.idcuentabancaria = idcuentabancaria;
    }

    public Estatuscontable getIdestatuscontable() {
        return idestatuscontable;
    }

    public void setIdestatuscontable(Estatuscontable idestatuscontable) {
        this.idestatuscontable = idestatuscontable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcobroventa != null ? idcobroventa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cobroventa)) {
            return false;
        }
        Cobroventa other = (Cobroventa) object;
        if ((this.idcobroventa == null && other.idcobroventa != null) || (this.idcobroventa != null && !this.idcobroventa.equals(other.idcobroventa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Cobroventa[ idcobroventa=" + idcobroventa + " ]";
    }
    
}
