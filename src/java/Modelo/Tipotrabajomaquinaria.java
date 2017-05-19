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
@Table(name = "tipotrabajomaquinaria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipotrabajomaquinaria.findAll", query = "SELECT t FROM Tipotrabajomaquinaria t"),
    @NamedQuery(name = "Tipotrabajomaquinaria.findByIdtipotrabajomaquinaria", query = "SELECT t FROM Tipotrabajomaquinaria t WHERE t.idtipotrabajomaquinaria = :idtipotrabajomaquinaria"),
    @NamedQuery(name = "Tipotrabajomaquinaria.findByTipotrabajomaquinaria", query = "SELECT t FROM Tipotrabajomaquinaria t WHERE t.tipotrabajomaquinaria = :tipotrabajomaquinaria")})
public class Tipotrabajomaquinaria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipotrabajomaquinaria")
    private Integer idtipotrabajomaquinaria;
    @Size(max = 150)
    @Column(name = "tipotrabajomaquinaria")
    private String tipotrabajomaquinaria;
    @OneToMany(mappedBy = "idtipotrabajomaquinaria")
    private Collection<Distribucionusomaquinaria> distribucionusomaquinariaCollection;

    public Tipotrabajomaquinaria() {
    }

    public Tipotrabajomaquinaria(Integer idtipotrabajomaquinaria) {
        this.idtipotrabajomaquinaria = idtipotrabajomaquinaria;
    }

    public Integer getIdtipotrabajomaquinaria() {
        return idtipotrabajomaquinaria;
    }

    public void setIdtipotrabajomaquinaria(Integer idtipotrabajomaquinaria) {
        this.idtipotrabajomaquinaria = idtipotrabajomaquinaria;
    }

    public String getTipotrabajomaquinaria() {
        return tipotrabajomaquinaria;
    }

    public void setTipotrabajomaquinaria(String tipotrabajomaquinaria) {
        this.tipotrabajomaquinaria = tipotrabajomaquinaria;
    }

    @XmlTransient
    public Collection<Distribucionusomaquinaria> getDistribucionusomaquinariaCollection() {
        return distribucionusomaquinariaCollection;
    }

    public void setDistribucionusomaquinariaCollection(Collection<Distribucionusomaquinaria> distribucionusomaquinariaCollection) {
        this.distribucionusomaquinariaCollection = distribucionusomaquinariaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipotrabajomaquinaria != null ? idtipotrabajomaquinaria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipotrabajomaquinaria)) {
            return false;
        }
        Tipotrabajomaquinaria other = (Tipotrabajomaquinaria) object;
        if ((this.idtipotrabajomaquinaria == null && other.idtipotrabajomaquinaria != null) || (this.idtipotrabajomaquinaria != null && !this.idtipotrabajomaquinaria.equals(other.idtipotrabajomaquinaria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Tipotrabajomaquinaria[ idtipotrabajomaquinaria=" + idtipotrabajomaquinaria + " ]";
    }
    
}
