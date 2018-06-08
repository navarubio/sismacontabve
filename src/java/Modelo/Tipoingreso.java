/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sofimar
 */
@Entity
@Table(name = "tipoingreso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoingreso.findAll", query = "SELECT t FROM Tipoingreso t"),
    @NamedQuery(name = "Tipoingreso.findByIdtipoingreso", query = "SELECT t FROM Tipoingreso t WHERE t.idtipoingreso = :idtipoingreso"),
    @NamedQuery(name = "Tipoingreso.findByTipoingreso", query = "SELECT t FROM Tipoingreso t WHERE t.tipoingreso = :tipoingreso")})
public class Tipoingreso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipoingreso")
    private Integer idtipoingreso;
    @Size(max = 30)
    @Column(name = "tipoingreso")
    private String tipoingreso;
    @OneToMany(mappedBy = "idtipoingreso")
    private Collection<Otroingreso> otroingresoCollection;
    @JoinColumn(name = "idplandecuenta", referencedColumnName = "idplandecuenta")
    @ManyToOne
    private Plandecuenta idplandecuenta;
    @Column(name = "idempresa")
    private Integer idempresa;

    public Tipoingreso() {
    }

    public Tipoingreso(Integer idtipoingreso) {
        this.idtipoingreso = idtipoingreso;
    }

    public Integer getIdtipoingreso() {
        return idtipoingreso;
    }

    public void setIdtipoingreso(Integer idtipoingreso) {
        this.idtipoingreso = idtipoingreso;
    }

    public String getTipoingreso() {
        return tipoingreso;
    }

    public void setTipoingreso(String tipoingreso) {
        this.tipoingreso = tipoingreso;
    }

    public Plandecuenta getIdplandecuenta() {
        return idplandecuenta;
    }

    public void setIdplandecuenta(Plandecuenta idplandecuenta) {
        this.idplandecuenta = idplandecuenta;
    }

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    @XmlTransient
    public Collection<Otroingreso> getOtroingresoCollection() {
        return otroingresoCollection;
    }

    public void setOtroingresoCollection(Collection<Otroingreso> otroingresoCollection) {
        this.otroingresoCollection = otroingresoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoingreso != null ? idtipoingreso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoingreso)) {
            return false;
        }
        Tipoingreso other = (Tipoingreso) object;
        if ((this.idtipoingreso == null && other.idtipoingreso != null) || (this.idtipoingreso != null && !this.idtipoingreso.equals(other.idtipoingreso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Tipoingreso[ idtipoingreso=" + idtipoingreso + " ]";
    }

}
