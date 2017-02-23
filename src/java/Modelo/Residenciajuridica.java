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
@Table(name = "residenciajuridica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Residenciajuridica.findAll", query = "SELECT r FROM Residenciajuridica r"),
    @NamedQuery(name = "Residenciajuridica.findByIdresidencia", query = "SELECT r FROM Residenciajuridica r WHERE r.idresidencia = :idresidencia"),
    @NamedQuery(name = "Residenciajuridica.findByResidencia", query = "SELECT r FROM Residenciajuridica r WHERE r.residencia = :residencia"),
    @NamedQuery(name = "Residenciajuridica.findByAbreviatura", query = "SELECT r FROM Residenciajuridica r WHERE r.abreviatura = :abreviatura")})
public class Residenciajuridica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idresidencia")
    private Integer idresidencia;
    @Size(max = 20)
    @Column(name = "residencia")
    private String residencia;
    @Size(max = 2)
    @Column(name = "abreviatura")
    private String abreviatura;
    @OneToMany(mappedBy = "idresidencia")
    private Collection<Tiporetencionislr> tiporetencionislrCollection;
    @OneToMany(mappedBy = "idresidencia")
    private Collection<Proveedor> proveedorCollection;


    public Residenciajuridica() {
    }

    public Residenciajuridica(Integer idresidencia) {
        this.idresidencia = idresidencia;
    }

    public Integer getIdresidencia() {
        return idresidencia;
    }

    public void setIdresidencia(Integer idresidencia) {
        this.idresidencia = idresidencia;
    }

    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    @XmlTransient
    public Collection<Tiporetencionislr> getTiporetencionislrCollection() {
        return tiporetencionislrCollection;
    }

    public void setTiporetencionislrCollection(Collection<Tiporetencionislr> tiporetencionislrCollection) {
        this.tiporetencionislrCollection = tiporetencionislrCollection;
    }
    
    @XmlTransient
    public Collection<Proveedor> getProveedorCollection() {
        return proveedorCollection;
    }

    public void setProveedorCollection(Collection<Proveedor> proveedorCollection) {
        this.proveedorCollection = proveedorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idresidencia != null ? idresidencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Residenciajuridica)) {
            return false;
        }
        Residenciajuridica other = (Residenciajuridica) object;
        if ((this.idresidencia == null && other.idresidencia != null) || (this.idresidencia != null && !this.idresidencia.equals(other.idresidencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Residenciajuridica[ idresidencia=" + idresidencia + " ]";
    }
    
}
