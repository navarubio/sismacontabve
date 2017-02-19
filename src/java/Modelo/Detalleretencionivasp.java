/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sofimar
 */
@Entity
@Table(name = "detalleretencionivasp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleretencionivasp.findAll", query = "SELECT d FROM Detalleretencionivasp d"),
    @NamedQuery(name = "Detalleretencionivasp.findByIddetalleretencionivasp", query = "SELECT d FROM Detalleretencionivasp d WHERE d.iddetalleretencionivasp = :iddetalleretencionivasp"),
    @NamedQuery(name = "Detalleretencionivasp.findByTotalventa", query = "SELECT d FROM Detalleretencionivasp d WHERE d.totalventa = :totalventa"),
    @NamedQuery(name = "Detalleretencionivasp.findByBimponible", query = "SELECT d FROM Detalleretencionivasp d WHERE d.bimponible = :bimponible"),
    @NamedQuery(name = "Detalleretencionivasp.findByTotalivaretenido", query = "SELECT d FROM Detalleretencionivasp d WHERE d.totalivaretenido = :totalivaretenido")})
public class Detalleretencionivasp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetalleretencionivasp")
    private Integer iddetalleretencionivasp;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalventa")
    private Double totalventa;
    @Column(name = "bimponible")
    private Double bimponible;
    @Column(name = "totalivaretenido")
    private Double totalivaretenido;
    @JoinColumn(name = "idcomprobanteivasp", referencedColumnName = "idcomprobanteivasp")
    @ManyToOne
    private Comprobanteivasp idcomprobanteivasp;
    @JoinColumn(name = "idtiporetencioniva", referencedColumnName = "idtiporetencioniva")
    @ManyToOne
    private Tiporetencioniva idtiporetencioniva;
    @JoinColumn(name = "numerofact", referencedColumnName = "numerofact")
    @ManyToOne
    private Factura numerofact;

    public Detalleretencionivasp() {
    }

    public Detalleretencionivasp(Integer iddetalleretencionivasp) {
        this.iddetalleretencionivasp = iddetalleretencionivasp;
    }

    public Integer getIddetalleretencionivasp() {
        return iddetalleretencionivasp;
    }

    public void setIddetalleretencionivasp(Integer iddetalleretencionivasp) {
        this.iddetalleretencionivasp = iddetalleretencionivasp;
    }

    public Double getTotalventa() {
        return totalventa;
    }

    public void setTotalventa(Double totalventa) {
        this.totalventa = totalventa;
    }

    public Double getBimponible() {
        return bimponible;
    }

    public void setBimponible(Double bimponible) {
        this.bimponible = bimponible;
    }

    public Double getTotalivaretenido() {
        return totalivaretenido;
    }

    public void setTotalivaretenido(Double totalivaretenido) {
        this.totalivaretenido = totalivaretenido;
    }

    public Comprobanteivasp getIdcomprobanteivasp() {
        return idcomprobanteivasp;
    }

    public void setIdcomprobanteivasp(Comprobanteivasp idcomprobanteivasp) {
        this.idcomprobanteivasp = idcomprobanteivasp;
    }

    public Tiporetencioniva getIdtiporetencioniva() {
        return idtiporetencioniva;
    }

    public void setIdtiporetencioniva(Tiporetencioniva idtiporetencioniva) {
        this.idtiporetencioniva = idtiporetencioniva;
    }

    public Factura getNumerofact() {
        return numerofact;
    }

    public void setNumerofact(Factura numerofact) {
        this.numerofact = numerofact;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalleretencionivasp != null ? iddetalleretencionivasp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleretencionivasp)) {
            return false;
        }
        Detalleretencionivasp other = (Detalleretencionivasp) object;
        if ((this.iddetalleretencionivasp == null && other.iddetalleretencionivasp != null) || (this.iddetalleretencionivasp != null && !this.iddetalleretencionivasp.equals(other.iddetalleretencionivasp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Detalleretencionivasp[ iddetalleretencionivasp=" + iddetalleretencionivasp + " ]";
    }
    
}
