/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sofimar
 */
@Entity
@Table(name = "datatributaria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datatributaria.findAll", query = "SELECT d FROM Datatributaria d"),
    @NamedQuery(name = "Datatributaria.findByIddatatributaria", query = "SELECT d FROM Datatributaria d WHERE d.iddatatributaria = :iddatatributaria"),
    @NamedQuery(name = "Datatributaria.findByAniofiscal", query = "SELECT d FROM Datatributaria d WHERE d.aniofiscal = :aniofiscal"),
    @NamedQuery(name = "Datatributaria.findByUnidadtributaria", query = "SELECT d FROM Datatributaria d WHERE d.unidadtributaria = :unidadtributaria"),
    @NamedQuery(name = "Datatributaria.findByFactorderetencion", query = "SELECT d FROM Datatributaria d WHERE d.factorderetencion = :factorderetencion")})
public class Datatributaria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddatatributaria")
    private Integer iddatatributaria;
    @Column(name = "aniofiscal")
    @Temporal(TemporalType.DATE)
    private Date aniofiscal;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "unidadtributaria")
    private Double unidadtributaria;
    @Column(name = "factorderetencion")
    private Double factorderetencion;

    public Datatributaria() {
    }

    public Datatributaria(Integer iddatatributaria) {
        this.iddatatributaria = iddatatributaria;
    }

    public Integer getIddatatributaria() {
        return iddatatributaria;
    }

    public void setIddatatributaria(Integer iddatatributaria) {
        this.iddatatributaria = iddatatributaria;
    }

    public Date getAniofiscal() {
        return aniofiscal;
    }

    public void setAniofiscal(Date aniofiscal) {
        this.aniofiscal = aniofiscal;
    }

    public Double getUnidadtributaria() {
        return unidadtributaria;
    }

    public void setUnidadtributaria(Double unidadtributaria) {
        this.unidadtributaria = unidadtributaria;
    }

    public Double getFactorderetencion() {
        return factorderetencion;
    }

    public void setFactorderetencion(Double factorderetencion) {
        this.factorderetencion = factorderetencion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddatatributaria != null ? iddatatributaria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datatributaria)) {
            return false;
        }
        Datatributaria other = (Datatributaria) object;
        if ((this.iddatatributaria == null && other.iddatatributaria != null) || (this.iddatatributaria != null && !this.iddatatributaria.equals(other.iddatatributaria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Datatributaria[ iddatatributaria=" + iddatatributaria + " ]";
    }
    
}
