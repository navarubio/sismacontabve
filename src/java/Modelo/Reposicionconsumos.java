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
@Table(name = "reposicionconsumos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reposicionconsumos.findAll", query = "SELECT r FROM Reposicionconsumos r"),
    @NamedQuery(name = "Reposicionconsumos.findByIdresposicionconsumos", query = "SELECT r FROM Reposicionconsumos r WHERE r.idresposicionconsumos = :idresposicionconsumos")})
public class Reposicionconsumos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idresposicionconsumos")
    private Integer idresposicionconsumos;
    @JoinColumn(name = "idconsumocajachica", referencedColumnName = "idconsumocajachica")
    @ManyToOne
    private Consumocajachica idconsumocajachica;
    @JoinColumn(name = "idreposicioncajachica", referencedColumnName = "idreposicioncajachica")
    @ManyToOne
    private Reposicioncajachica idreposicioncajachica;

    public Reposicionconsumos() {
    }

    public Reposicionconsumos(Integer idresposicionconsumos) {
        this.idresposicionconsumos = idresposicionconsumos;
    }

    public Integer getIdresposicionconsumos() {
        return idresposicionconsumos;
    }

    public void setIdresposicionconsumos(Integer idresposicionconsumos) {
        this.idresposicionconsumos = idresposicionconsumos;
    }

    public Consumocajachica getIdconsumocajachica() {
        return idconsumocajachica;
    }

    public void setIdconsumocajachica(Consumocajachica idconsumocajachica) {
        this.idconsumocajachica = idconsumocajachica;
    }

    public Reposicioncajachica getIdreposicioncajachica() {
        return idreposicioncajachica;
    }

    public void setIdreposicioncajachica(Reposicioncajachica idreposicioncajachica) {
        this.idreposicioncajachica = idreposicioncajachica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idresposicionconsumos != null ? idresposicionconsumos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reposicionconsumos)) {
            return false;
        }
        Reposicionconsumos other = (Reposicionconsumos) object;
        if ((this.idresposicionconsumos == null && other.idresposicionconsumos != null) || (this.idresposicionconsumos != null && !this.idresposicionconsumos.equals(other.idresposicionconsumos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Reposicionconsumos[ idresposicionconsumos=" + idresposicionconsumos + " ]";
    }
    
}
