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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sofimar
 */
@Entity
@Table(name = "retencionivasri")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Retencionivasri.findAll", query = "SELECT r FROM Retencionivasri r"),
    @NamedQuery(name = "Retencionivasri.findByIdretencionivasri", query = "SELECT r FROM Retencionivasri r WHERE r.idretencionivasri = :idretencionivasri"),
    @NamedQuery(name = "Retencionivasri.findByPorcentajeivabienes", query = "SELECT r FROM Retencionivasri r WHERE r.porcentajeivabienes = :porcentajeivabienes"),
    @NamedQuery(name = "Retencionivasri.findByPorcentajeivaservicios", query = "SELECT r FROM Retencionivasri r WHERE r.porcentajeivaservicios = :porcentajeivaservicios")})
public class Retencionivasri implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idretencionivasri")
    private Integer idretencionivasri;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "porcentajeivabienes")
    private Double porcentajeivabienes;
    @Column(name = "porcentajeivaservicios")
    private Double porcentajeivaservicios;

    public Retencionivasri() {
    }

    public Retencionivasri(Integer idretencionivasri) {
        this.idretencionivasri = idretencionivasri;
    }

    public Integer getIdretencionivasri() {
        return idretencionivasri;
    }

    public void setIdretencionivasri(Integer idretencionivasri) {
        this.idretencionivasri = idretencionivasri;
    }

    public Double getPorcentajeivabienes() {
        return porcentajeivabienes;
    }

    public void setPorcentajeivabienes(Double porcentajeivabienes) {
        this.porcentajeivabienes = porcentajeivabienes;
    }

    public Double getPorcentajeivaservicios() {
        return porcentajeivaservicios;
    }

    public void setPorcentajeivaservicios(Double porcentajeivaservicios) {
        this.porcentajeivaservicios = porcentajeivaservicios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idretencionivasri != null ? idretencionivasri.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Retencionivasri)) {
            return false;
        }
        Retencionivasri other = (Retencionivasri) object;
        if ((this.idretencionivasri == null && other.idretencionivasri != null) || (this.idretencionivasri != null && !this.idretencionivasri.equals(other.idretencionivasri))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Retencionivasri[ idretencionivasri=" + idretencionivasri + " ]";
    }
    
}
