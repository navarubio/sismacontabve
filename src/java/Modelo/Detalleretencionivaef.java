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
@Table(name = "detalleretencionivaef")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleretencionivaef.findAll", query = "SELECT d FROM Detalleretencionivaef d"),
    @NamedQuery(name = "Detalleretencionivaef.findByIddetalleretencionivaef", query = "SELECT d FROM Detalleretencionivaef d WHERE d.iddetalleretencionivaef = :iddetalleretencionivaef"),
    @NamedQuery(name = "Detalleretencionivaef.findByTotalcompra", query = "SELECT d FROM Detalleretencionivaef d WHERE d.totalcompra = :totalcompra"),
    @NamedQuery(name = "Detalleretencionivaef.findByBimponible", query = "SELECT d FROM Detalleretencionivaef d WHERE d.bimponible = :bimponible"),
    @NamedQuery(name = "Detalleretencionivaef.findByTotalivaretenido", query = "SELECT d FROM Detalleretencionivaef d WHERE d.totalivaretenido = :totalivaretenido")})
public class Detalleretencionivaef implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetalleretencionivaef")
    private Integer iddetalleretencionivaef;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalcompra")
    private Double totalcompra;
    @Column(name = "bimponible")
    private Double bimponible;
    @Column(name = "totalivaretenido")
    private Double totalivaretenido;
    @JoinColumn(name = "idcomprobanteivaef", referencedColumnName = "idcomprobanteivaef")
    @ManyToOne
    private Comprobanteivaef idcomprobanteivaef;
    @JoinColumn(name = "idtiporetencioniva", referencedColumnName = "idtiporetencioniva")
    @ManyToOne
    private Tiporetencioniva idtiporetencioniva;
    @JoinColumn(name = "idcompra", referencedColumnName = "idcompra")
    @ManyToOne
    private Compra idcompra;

    public Detalleretencionivaef() {
    }

    public Detalleretencionivaef(Integer iddetalleretencionivaef) {
        this.iddetalleretencionivaef = iddetalleretencionivaef;
    }

    public Integer getIddetalleretencionivaef() {
        return iddetalleretencionivaef;
    }

    public void setIddetalleretencionivaef(Integer iddetalleretencionivaef) {
        this.iddetalleretencionivaef = iddetalleretencionivaef;
    }

    public Double getTotalcompra() {
        return totalcompra;
    }

    public void setTotalcompra(Double totalcompra) {
        this.totalcompra = totalcompra;
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

    public Comprobanteivaef getIdcomprobanteivaef() {
        return idcomprobanteivaef;
    }

    public void setIdcomprobanteivaef(Comprobanteivaef idcomprobanteivaef) {
        this.idcomprobanteivaef = idcomprobanteivaef;
    }

    public Tiporetencioniva getIdtiporetencioniva() {
        return idtiporetencioniva;
    }

    public void setIdtiporetencioniva(Tiporetencioniva idtiporetencioniva) {
        this.idtiporetencioniva = idtiporetencioniva;
    }

    public Compra getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(Compra idcompra) {
        this.idcompra = idcompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalleretencionivaef != null ? iddetalleretencionivaef.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleretencionivaef)) {
            return false;
        }
        Detalleretencionivaef other = (Detalleretencionivaef) object;
        if ((this.iddetalleretencionivaef == null && other.iddetalleretencionivaef != null) || (this.iddetalleretencionivaef != null && !this.iddetalleretencionivaef.equals(other.iddetalleretencionivaef))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Detalleretencionivaef[ iddetalleretencionivaef=" + iddetalleretencionivaef + " ]";
    }
    
}
