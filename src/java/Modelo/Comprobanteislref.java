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
@Table(name = "comprobanteislref")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comprobanteislref.findAll", query = "SELECT c FROM Comprobanteislref c"),
    @NamedQuery(name = "Comprobanteislref.findByIdcomprobanteislref", query = "SELECT c FROM Comprobanteislref c WHERE c.idcomprobanteislref = :idcomprobanteislref"),
    @NamedQuery(name = "Comprobanteislref.findByComprobante", query = "SELECT c FROM Comprobanteislref c WHERE c.comprobante = :comprobante"),
    @NamedQuery(name = "Comprobanteislref.findByFecha", query = "SELECT c FROM Comprobanteislref c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Comprobanteislref.findByTotalgeneral", query = "SELECT c FROM Comprobanteislref c WHERE c.totalgeneral = :totalgeneral"),
    @NamedQuery(name = "Comprobanteislref.findByTotalbimponible", query = "SELECT c FROM Comprobanteislref c WHERE c.totalbimponible = :totalbimponible"),
    @NamedQuery(name = "Comprobanteislref.findByTotalislrretenido", query = "SELECT c FROM Comprobanteislref c WHERE c.totalislrretenido = :totalislrretenido")})
public class Comprobanteislref implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcomprobanteislref")
    private Integer idcomprobanteislref;
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
    @JoinColumn(name = "rifproveedor", referencedColumnName = "rifproveedor")
    @ManyToOne
    private Proveedor rifproveedor;
    @OneToMany(mappedBy = "idcomprobanteislref")
    private Collection<Detalleretencionislref> detalleretencionislrefCollection;
    @Column(name = "idempresa")
    private Integer idempresa;
    @Column(name = "serialcomprobanteislr")
    private Integer serialcomprobanteislr;

    public Comprobanteislref() {
    }

    public Comprobanteislref(Integer idcomprobanteislref) {
        this.idcomprobanteislref = idcomprobanteislref;
    }

    public Integer getIdcomprobanteislref() {
        return idcomprobanteislref;
    }

    public void setIdcomprobanteislref(Integer idcomprobanteislref) {
        this.idcomprobanteislref = idcomprobanteislref;
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

    public Proveedor getRifproveedor() {
        return rifproveedor;
    }

    public void setRifproveedor(Proveedor rifproveedor) {
        this.rifproveedor = rifproveedor;
    }

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public Integer getSerialcomprobanteislr() {
        return serialcomprobanteislr;
    }

    public void setSerialcomprobanteislr(Integer serialcomprobanteislr) {
        this.serialcomprobanteislr = serialcomprobanteislr;
    }

    @XmlTransient
    public Collection<Detalleretencionislref> getDetalleretencionislrefCollection() {
        return detalleretencionislrefCollection;
    }

    public void setDetalleretencionislrefCollection(Collection<Detalleretencionislref> detalleretencionislrefCollection) {
        this.detalleretencionislrefCollection = detalleretencionislrefCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcomprobanteislref != null ? idcomprobanteislref.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comprobanteislref)) {
            return false;
        }
        Comprobanteislref other = (Comprobanteislref) object;
        if ((this.idcomprobanteislref == null && other.idcomprobanteislref != null) || (this.idcomprobanteislref != null && !this.idcomprobanteislref.equals(other.idcomprobanteislref))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Comprobanteislref[ idcomprobanteislref=" + idcomprobanteislref + " ]";
    }
    
}
