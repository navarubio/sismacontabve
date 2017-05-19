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
@Table(name = "tipomaquinaria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipomaquinaria.findAll", query = "SELECT t FROM Tipomaquinaria t"),
    @NamedQuery(name = "Tipomaquinaria.findByIdtipomaquinaria", query = "SELECT t FROM Tipomaquinaria t WHERE t.idtipomaquinaria = :idtipomaquinaria"),
    @NamedQuery(name = "Tipomaquinaria.findByTipomaquinaria", query = "SELECT t FROM Tipomaquinaria t WHERE t.tipomaquinaria = :tipomaquinaria")})
public class Tipomaquinaria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipomaquinaria")
    private Integer idtipomaquinaria;
    @Size(max = 150)
    @Column(name = "tipomaquinaria")
    private String tipomaquinaria;
    @OneToMany(mappedBy = "idtipomaquinaria")
    private Collection<Maquinariapicadora> maquinariapicadoraCollection;

    public Tipomaquinaria() {
    }

    public Tipomaquinaria(Integer idtipomaquinaria) {
        this.idtipomaquinaria = idtipomaquinaria;
    }

    public Integer getIdtipomaquinaria() {
        return idtipomaquinaria;
    }

    public void setIdtipomaquinaria(Integer idtipomaquinaria) {
        this.idtipomaquinaria = idtipomaquinaria;
    }

    public String getTipomaquinaria() {
        return tipomaquinaria;
    }

    public void setTipomaquinaria(String tipomaquinaria) {
        this.tipomaquinaria = tipomaquinaria;
    }

    @XmlTransient
    public Collection<Maquinariapicadora> getMaquinariapicadoraCollection() {
        return maquinariapicadoraCollection;
    }

    public void setMaquinariapicadoraCollection(Collection<Maquinariapicadora> maquinariapicadoraCollection) {
        this.maquinariapicadoraCollection = maquinariapicadoraCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipomaquinaria != null ? idtipomaquinaria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipomaquinaria)) {
            return false;
        }
        Tipomaquinaria other = (Tipomaquinaria) object;
        if ((this.idtipomaquinaria == null && other.idtipomaquinaria != null) || (this.idtipomaquinaria != null && !this.idtipomaquinaria.equals(other.idtipomaquinaria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Tipomaquinaria[ idtipomaquinaria=" + idtipomaquinaria + " ]";
    }
    
}
