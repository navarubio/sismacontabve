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
@Table(name = "estatusconsumocajachica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estatusconsumocajachica.findAll", query = "SELECT e FROM Estatusconsumocajachica e"),
    @NamedQuery(name = "Estatusconsumocajachica.findByIdestatusconsumocajachica", query = "SELECT e FROM Estatusconsumocajachica e WHERE e.idestatusconsumocajachica = :idestatusconsumocajachica"),
    @NamedQuery(name = "Estatusconsumocajachica.findByEstatusconsumocajachica", query = "SELECT e FROM Estatusconsumocajachica e WHERE e.estatusconsumocajachica = :estatusconsumocajachica")})
public class Estatusconsumocajachica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestatusconsumocajachica")
    private Integer idestatusconsumocajachica;
    @Size(max = 25)
    @Column(name = "estatusconsumocajachica")
    private String estatusconsumocajachica;
    @OneToMany(mappedBy = "idestatusconsumocajachica")
    private Collection<Consumocajachica> consumocajachicaCollection;

    public Estatusconsumocajachica() {
    }

    public Estatusconsumocajachica(Integer idestatusconsumocajachica) {
        this.idestatusconsumocajachica = idestatusconsumocajachica;
    }

    public Integer getIdestatusconsumocajachica() {
        return idestatusconsumocajachica;
    }

    public void setIdestatusconsumocajachica(Integer idestatusconsumocajachica) {
        this.idestatusconsumocajachica = idestatusconsumocajachica;
    }

    public String getEstatusconsumocajachica() {
        return estatusconsumocajachica;
    }

    public void setEstatusconsumocajachica(String estatusconsumocajachica) {
        this.estatusconsumocajachica = estatusconsumocajachica;
    }

    @XmlTransient
    public Collection<Consumocajachica> getConsumocajachicaCollection() {
        return consumocajachicaCollection;
    }

    public void setConsumocajachicaCollection(Collection<Consumocajachica> consumocajachicaCollection) {
        this.consumocajachicaCollection = consumocajachicaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestatusconsumocajachica != null ? idestatusconsumocajachica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estatusconsumocajachica)) {
            return false;
        }
        Estatusconsumocajachica other = (Estatusconsumocajachica) object;
        if ((this.idestatusconsumocajachica == null && other.idestatusconsumocajachica != null) || (this.idestatusconsumocajachica != null && !this.idestatusconsumocajachica.equals(other.idestatusconsumocajachica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Estatusconsumocajachica[ idestatusconsumocajachica=" + idestatusconsumocajachica + " ]";
    }
    
}
