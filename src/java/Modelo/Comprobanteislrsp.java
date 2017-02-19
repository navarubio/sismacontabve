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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sofimar
 */
@Entity
@Table(name = "comprobanteislrsp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comprobanteislrsp.findAll", query = "SELECT c FROM Comprobanteislrsp c"),
    @NamedQuery(name = "Comprobanteislrsp.findByIdcomprobanteislrsp", query = "SELECT c FROM Comprobanteislrsp c WHERE c.idcomprobanteislrsp = :idcomprobanteislrsp"),
    @NamedQuery(name = "Comprobanteislrsp.findByComprobante", query = "SELECT c FROM Comprobanteislrsp c WHERE c.comprobante = :comprobante"),
    @NamedQuery(name = "Comprobanteislrsp.findByFecha", query = "SELECT c FROM Comprobanteislrsp c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Comprobanteislrsp.findByTotalgeneral", query = "SELECT c FROM Comprobanteislrsp c WHERE c.totalgeneral = :totalgeneral"),
    @NamedQuery(name = "Comprobanteislrsp.findByTotalbimponible", query = "SELECT c FROM Comprobanteislrsp c WHERE c.totalbimponible = :totalbimponible"),
    @NamedQuery(name = "Comprobanteislrsp.findByTotalislrretenido", query = "SELECT c FROM Comprobanteislrsp c WHERE c.totalislrretenido = :totalislrretenido")})
public class Comprobanteislrsp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcomprobanteislrsp")
    private Integer idcomprobanteislrsp;
    @Size(max = 14)
    @Column(name = "comprobante")
    private String comprobante;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalgeneral")
    private Double totalgeneral;
    @Column(name = "totalbimponible")
    private Double totalbimponible;
    @Column(name = "totalislrretenido")
    private Double totalislrretenido;
    @JoinColumn(name = "idestatuscomprobante", referencedColumnName = "idestatuscomprobante")
    @ManyToOne
    private Estatuscomprobanteretencion idestatuscomprobante;
    @JoinColumn(name = "rifcliente", referencedColumnName = "rifcliente")
    @ManyToOne
    private Cliente rifcliente;
    @OneToMany(mappedBy = "idcomprobanteislrsp")
    private Collection<Detalleretencionislrsp> detalleretencionislrspCollection;

    public Comprobanteislrsp() {
    }

    public Comprobanteislrsp(Integer idcomprobanteislrsp) {
        this.idcomprobanteislrsp = idcomprobanteislrsp;
    }

    public Integer getIdcomprobanteislrsp() {
        return idcomprobanteislrsp;
    }

    public void setIdcomprobanteislrsp(Integer idcomprobanteislrsp) {
        this.idcomprobanteislrsp = idcomprobanteislrsp;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotalgeneral() {
        return totalgeneral;
    }

    public void setTotalgeneral(Double totalgeneral) {
        this.totalgeneral = totalgeneral;
    }

    public Double getTotalbimponible() {
        return totalbimponible;
    }

    public void setTotalbimponible(Double totalbimponible) {
        this.totalbimponible = totalbimponible;
    }

    public Double getTotalislrretenido() {
        return totalislrretenido;
    }

    public void setTotalislrretenido(Double totalislrretenido) {
        this.totalislrretenido = totalislrretenido;
    }

    public Estatuscomprobanteretencion getIdestatuscomprobante() {
        return idestatuscomprobante;
    }

    public void setIdestatuscomprobante(Estatuscomprobanteretencion idestatuscomprobante) {
        this.idestatuscomprobante = idestatuscomprobante;
    }

    public Cliente getRifcliente() {
        return rifcliente;
    }

    public void setRifcliente(Cliente rifcliente) {
        this.rifcliente = rifcliente;
    }

    @XmlTransient
    public Collection<Detalleretencionislrsp> getDetalleretencionislrspCollection() {
        return detalleretencionislrspCollection;
    }

    public void setDetalleretencionislrspCollection(Collection<Detalleretencionislrsp> detalleretencionislrspCollection) {
        this.detalleretencionislrspCollection = detalleretencionislrspCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcomprobanteislrsp != null ? idcomprobanteislrsp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comprobanteislrsp)) {
            return false;
        }
        Comprobanteislrsp other = (Comprobanteislrsp) object;
        if ((this.idcomprobanteislrsp == null && other.idcomprobanteislrsp != null) || (this.idcomprobanteislrsp != null && !this.idcomprobanteislrsp.equals(other.idcomprobanteislrsp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Comprobanteislrsp[ idcomprobanteislrsp=" + idcomprobanteislrsp + " ]";
    }
    
}
