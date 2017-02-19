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
@Table(name = "comprobanteivasp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comprobanteivasp.findAll", query = "SELECT c FROM Comprobanteivasp c"),
    @NamedQuery(name = "Comprobanteivasp.findByIdcomprobanteivasp", query = "SELECT c FROM Comprobanteivasp c WHERE c.idcomprobanteivasp = :idcomprobanteivasp"),
    @NamedQuery(name = "Comprobanteivasp.findByComprobante", query = "SELECT c FROM Comprobanteivasp c WHERE c.comprobante = :comprobante"),
    @NamedQuery(name = "Comprobanteivasp.findByFecha", query = "SELECT c FROM Comprobanteivasp c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Comprobanteivasp.findByAnio", query = "SELECT c FROM Comprobanteivasp c WHERE c.anio = :anio"),
    @NamedQuery(name = "Comprobanteivasp.findByMes", query = "SELECT c FROM Comprobanteivasp c WHERE c.mes = :mes"),
    @NamedQuery(name = "Comprobanteivasp.findByTotalgeneral", query = "SELECT c FROM Comprobanteivasp c WHERE c.totalgeneral = :totalgeneral"),
    @NamedQuery(name = "Comprobanteivasp.findByTotalbimponible", query = "SELECT c FROM Comprobanteivasp c WHERE c.totalbimponible = :totalbimponible"),
    @NamedQuery(name = "Comprobanteivasp.findByTotaliva", query = "SELECT c FROM Comprobanteivasp c WHERE c.totaliva = :totaliva"),
    @NamedQuery(name = "Comprobanteivasp.findByTotalivaretenido", query = "SELECT c FROM Comprobanteivasp c WHERE c.totalivaretenido = :totalivaretenido")})
public class Comprobanteivasp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcomprobanteivasp")
    private Integer idcomprobanteivasp;
    @Size(max = 14)
    @Column(name = "comprobante")
    private String comprobante;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "anio")
    private Integer anio;
    @Column(name = "mes")
    private Integer mes;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalgeneral")
    private Double totalgeneral;
    @Column(name = "totalbimponible")
    private Double totalbimponible;
    @Column(name = "totaliva")
    private Double totaliva;
    @Column(name = "totalivaretenido")
    private Double totalivaretenido;
    @OneToMany(mappedBy = "idcomprobanteivasp")
    private Collection<Detalleretencionivasp> detalleretencionivaspCollection;
    @JoinColumn(name = "idestatuscomprobante", referencedColumnName = "idestatuscomprobante")
    @ManyToOne
    private Estatuscomprobanteretencion idestatuscomprobante;
    @JoinColumn(name = "rifcliente", referencedColumnName = "rifcliente")
    @ManyToOne
    private Cliente rifcliente;

    public Comprobanteivasp() {
    }

    public Comprobanteivasp(Integer idcomprobanteivasp) {
        this.idcomprobanteivasp = idcomprobanteivasp;
    }

    public Integer getIdcomprobanteivasp() {
        return idcomprobanteivasp;
    }

    public void setIdcomprobanteivasp(Integer idcomprobanteivasp) {
        this.idcomprobanteivasp = idcomprobanteivasp;
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

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
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

    public Double getTotaliva() {
        return totaliva;
    }

    public void setTotaliva(Double totaliva) {
        this.totaliva = totaliva;
    }

    public Double getTotalivaretenido() {
        return totalivaretenido;
    }

    public void setTotalivaretenido(Double totalivaretenido) {
        this.totalivaretenido = totalivaretenido;
    }

    @XmlTransient
    public Collection<Detalleretencionivasp> getDetalleretencionivaspCollection() {
        return detalleretencionivaspCollection;
    }

    public void setDetalleretencionivaspCollection(Collection<Detalleretencionivasp> detalleretencionivaspCollection) {
        this.detalleretencionivaspCollection = detalleretencionivaspCollection;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcomprobanteivasp != null ? idcomprobanteivasp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comprobanteivasp)) {
            return false;
        }
        Comprobanteivasp other = (Comprobanteivasp) object;
        if ((this.idcomprobanteivasp == null && other.idcomprobanteivasp != null) || (this.idcomprobanteivasp != null && !this.idcomprobanteivasp.equals(other.idcomprobanteivasp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Comprobanteivasp[ idcomprobanteivasp=" + idcomprobanteivasp + " ]";
    }
    
}
