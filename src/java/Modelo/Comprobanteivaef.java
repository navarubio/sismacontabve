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
@Table(name = "comprobanteivaef")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comprobanteivaef.findAll", query = "SELECT c FROM Comprobanteivaef c"),
    @NamedQuery(name = "Comprobanteivaef.findByIdcomprobanteivaef", query = "SELECT c FROM Comprobanteivaef c WHERE c.idcomprobanteivaef = :idcomprobanteivaef"),
    @NamedQuery(name = "Comprobanteivaef.findByComprobante", query = "SELECT c FROM Comprobanteivaef c WHERE c.comprobante = :comprobante"),
    @NamedQuery(name = "Comprobanteivaef.findByFecha", query = "SELECT c FROM Comprobanteivaef c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Comprobanteivaef.findByAnio", query = "SELECT c FROM Comprobanteivaef c WHERE c.anio = :anio"),
    @NamedQuery(name = "Comprobanteivaef.findByMes", query = "SELECT c FROM Comprobanteivaef c WHERE c.mes = :mes"),
    @NamedQuery(name = "Comprobanteivaef.findByTotalgeneral", query = "SELECT c FROM Comprobanteivaef c WHERE c.totalgeneral = :totalgeneral"),
    @NamedQuery(name = "Comprobanteivaef.findByTotalbimponible", query = "SELECT c FROM Comprobanteivaef c WHERE c.totalbimponible = :totalbimponible"),
    @NamedQuery(name = "Comprobanteivaef.findByTotaliva", query = "SELECT c FROM Comprobanteivaef c WHERE c.totaliva = :totaliva"),
    @NamedQuery(name = "Comprobanteivaef.findByTotalivaretenido", query = "SELECT c FROM Comprobanteivaef c WHERE c.totalivaretenido = :totalivaretenido")})
public class Comprobanteivaef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcomprobanteivaef")
    private Integer idcomprobanteivaef;
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
    @JoinColumn(name = "idestatuscomprobante", referencedColumnName = "idestatuscomprobante")
    @ManyToOne
    private Estatuscomprobanteretencion idestatuscomprobante;
    @JoinColumn(name = "rifproveedor", referencedColumnName = "rifproveedor")
    @ManyToOne
    private Proveedor rifproveedor;
    @OneToMany(mappedBy = "idcomprobanteivaef")
    private Collection<Detalleretencionivaef> detalleretencionivaefCollection;
    @Column(name = "idempresa")
    private Integer idempresa;
    @Column(name = "serialcomprobanteiva")
    private Integer serialcomprobanteiva;

    public Comprobanteivaef() {
    }

    public Comprobanteivaef(Integer idcomprobanteivaef) {
        this.idcomprobanteivaef = idcomprobanteivaef;
    }

    public Integer getIdcomprobanteivaef() {
        return idcomprobanteivaef;
    }

    public void setIdcomprobanteivaef(Integer idcomprobanteivaef) {
        this.idcomprobanteivaef = idcomprobanteivaef;
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

    public Integer getSerialcomprobanteiva() {
        return serialcomprobanteiva;
    }

    public void setSerialcomprobanteiva(Integer serialcomprobanteiva) {
        this.serialcomprobanteiva = serialcomprobanteiva;
    }
    
    @XmlTransient
    public Collection<Detalleretencionivaef> getDetalleretencionivaefCollection() {
        return detalleretencionivaefCollection;
    }

    public void setDetalleretencionivaefCollection(Collection<Detalleretencionivaef> detalleretencionivaefCollection) {
        this.detalleretencionivaefCollection = detalleretencionivaefCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcomprobanteivaef != null ? idcomprobanteivaef.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comprobanteivaef)) {
            return false;
        }
        Comprobanteivaef other = (Comprobanteivaef) object;
        if ((this.idcomprobanteivaef == null && other.idcomprobanteivaef != null) || (this.idcomprobanteivaef != null && !this.idcomprobanteivaef.equals(other.idcomprobanteivaef))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Comprobanteivaef[ idcomprobanteivaef=" + idcomprobanteivaef + " ]";
    }

}
