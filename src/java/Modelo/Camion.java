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
@Table(name = "camion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Camion.findAll", query = "SELECT c FROM Camion c"),
    @NamedQuery(name = "Camion.findByIdcamion", query = "SELECT c FROM Camion c WHERE c.idcamion = :idcamion"),
    @NamedQuery(name = "Camion.findByPlacas", query = "SELECT c FROM Camion c WHERE c.placas = :placas"),
    @NamedQuery(name = "Camion.findByColor", query = "SELECT c FROM Camion c WHERE c.color = :color"),
    @NamedQuery(name = "Camion.findByCubicaje", query = "SELECT c FROM Camion c WHERE c.cubicaje = :cubicaje")})
public class Camion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcamion")
    private Integer idcamion;
    @Size(max = 10)
    @Column(name = "placas")
    private String placas;
    @Size(max = 10)
    @Column(name = "color")
    private String color;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cubicaje")
    private Double cubicaje;
    @OneToMany(mappedBy = "idcamion")
    private Collection<Despachopicadora> despachopicadoraCollection;

    public Camion() {
    }

    public Camion(Integer idcamion) {
        this.idcamion = idcamion;
    }

    public Integer getIdcamion() {
        return idcamion;
    }

    public void setIdcamion(Integer idcamion) {
        this.idcamion = idcamion;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getCubicaje() {
        return cubicaje;
    }

    public void setCubicaje(Double cubicaje) {
        this.cubicaje = cubicaje;
    }

    @XmlTransient
    public Collection<Despachopicadora> getDespachopicadoraCollection() {
        return despachopicadoraCollection;
    }

    public void setDespachopicadoraCollection(Collection<Despachopicadora> despachopicadoraCollection) {
        this.despachopicadoraCollection = despachopicadoraCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcamion != null ? idcamion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Camion)) {
            return false;
        }
        Camion other = (Camion) object;
        if ((this.idcamion == null && other.idcamion != null) || (this.idcamion != null && !this.idcamion.equals(other.idcamion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Camion[ idcamion=" + idcamion + " ]";
    }
    
}
