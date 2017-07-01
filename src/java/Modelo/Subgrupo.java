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
 * @author Inpeca
 */
@Entity
@Table(name = "subgrupo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subgrupo.findAll", query = "SELECT s FROM Subgrupo s"),
    @NamedQuery(name = "Subgrupo.findByIdsubgrupo", query = "SELECT s FROM Subgrupo s WHERE s.idsubgrupo = :idsubgrupo"),
    @NamedQuery(name = "Subgrupo.findBySubgrupo", query = "SELECT s FROM Subgrupo s WHERE s.subgrupo = :subgrupo")})
public class Subgrupo implements Serializable {
    @OneToMany(mappedBy = "idsubgrupo")
    private Collection<Tiporetencionislr> tiporetencionislrCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsubgrupo")
    private Integer idsubgrupo;
    @Size(max = 200)
    @Column(name = "subgrupo")
    private String subgrupo;
    @Column(name = "porcentajeretencion")
    private Double procentajeretencion;
    @JoinColumn(name = "idgrupo", referencedColumnName = "idgrupo")
    @ManyToOne
    private Grupo idgrupo;
    @OneToMany(mappedBy = "idsubgrupo")
    private Collection<Articulo> articuloCollection;
    @OneToMany(mappedBy = "idsubgrupo") 
    private Collection<Detalleretencionislref> detalleretencionislrefCollection;
    
        public Subgrupo() {
    }

    public Subgrupo(Integer idsubgrupo) {
        this.idsubgrupo = idsubgrupo;
    }

    public Integer getIdsubgrupo() {
        return idsubgrupo;
    }

    public void setIdsubgrupo(Integer idsubgrupo) {
        this.idsubgrupo = idsubgrupo;
    }

    public String getSubgrupo() {
        return subgrupo;
    }

    public void setSubgrupo(String subgrupo) {
        this.subgrupo = subgrupo;
    }

    public Grupo getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(Grupo idgrupo) {
        this.idgrupo = idgrupo;
    }

    public Double getProcentajeretencion() {
        return procentajeretencion;
    }

    public void setProcentajeretencion(Double procentajeretencion) {
        this.procentajeretencion = procentajeretencion;
    }
    
    @XmlTransient
    public Collection<Articulo> getArticuloCollection() {
        return articuloCollection;
    }

    public void setArticuloCollection(Collection<Articulo> articuloCollection) {
        this.articuloCollection = articuloCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsubgrupo != null ? idsubgrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subgrupo)) {
            return false;
        }
        Subgrupo other = (Subgrupo) object;
        if ((this.idsubgrupo == null && other.idsubgrupo != null) || (this.idsubgrupo != null && !this.idsubgrupo.equals(other.idsubgrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return subgrupo;
    }

    @XmlTransient
    public Collection<Tiporetencionislr> getTiporetencionislrCollection() {
        return tiporetencionislrCollection;
    }

    public void setTiporetencionislrCollection(Collection<Tiporetencionislr> tiporetencionislrCollection) {
        this.tiporetencionislrCollection = tiporetencionislrCollection;
    }
    
    @XmlTransient
    public Collection<Detalleretencionislref> getDetalleretencionislrefCollection() {
        return detalleretencionislrefCollection;
    }

    public void setDetalleretencionislrefCollection(Collection<Detalleretencionislref> detalleretencionislrefCollection) {
        this.detalleretencionislrefCollection = detalleretencionislrefCollection;
    }
}
