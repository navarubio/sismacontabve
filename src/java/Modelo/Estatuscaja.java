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
@Table(name = "estatuscaja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estatuscaja.findAll", query = "SELECT e FROM Estatuscaja e"),
    @NamedQuery(name = "Estatuscaja.findByIdestatuscaja", query = "SELECT e FROM Estatuscaja e WHERE e.idestatuscaja = :idestatuscaja"),
    @NamedQuery(name = "Estatuscaja.findByEstatuscaja", query = "SELECT e FROM Estatuscaja e WHERE e.estatuscaja = :estatuscaja")})
public class Estatuscaja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestatuscaja")
    private Integer idestatuscaja;
    @Size(max = 25)
    @Column(name = "estatuscaja")
    private String estatuscaja;
    @OneToMany(mappedBy = "idestatuscaja")
    private Collection<Caja> cajaCollection;

    public Estatuscaja() {
    }

    public Estatuscaja(Integer idestatuscaja) {
        this.idestatuscaja = idestatuscaja;
    }

    public Integer getIdestatuscaja() {
        return idestatuscaja;
    }

    public void setIdestatuscaja(Integer idestatuscaja) {
        this.idestatuscaja = idestatuscaja;
    }

    public String getEstatuscaja() {
        return estatuscaja;
    }

    public void setEstatuscaja(String estatuscaja) {
        this.estatuscaja = estatuscaja;
    }

    @XmlTransient
    public Collection<Caja> getCajaCollection() {
        return cajaCollection;
    }

    public void setCajaCollection(Collection<Caja> cajaCollection) {
        this.cajaCollection = cajaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestatuscaja != null ? idestatuscaja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estatuscaja)) {
            return false;
        }
        Estatuscaja other = (Estatuscaja) object;
        if ((this.idestatuscaja == null && other.idestatuscaja != null) || (this.idestatuscaja != null && !this.idestatuscaja.equals(other.idestatuscaja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Estatuscaja[ idestatuscaja=" + idestatuscaja + " ]";
    }
    
}
