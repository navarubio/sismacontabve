/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jsf;

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
 * @author sofimar
 */
@Entity
@Table(name = "menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menup.findAll", query = "SELECT m FROM Menup m"),
    @NamedQuery(name = "Menup.findByIdmenu", query = "SELECT m FROM Menup m WHERE m.idmenu = :idmenu"),
    @NamedQuery(name = "Menup.findByDescripcion", query = "SELECT m FROM Menup m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "Menup.findByUrl", query = "SELECT m FROM Menup m WHERE m.url = :url"),
    @NamedQuery(name = "Menup.findByIcon", query = "SELECT m FROM Menup m WHERE m.icon = :icon"),
    @NamedQuery(name = "Menup.findByEstado", query = "SELECT m FROM Menup m WHERE m.estado = :estado")})
public class Menup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmenu")
    private Integer idmenu;
    @Size(max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 100)
    @Column(name = "url")
    private String url;
    @Size(max = 50)
    @Column(name = "icon")
    private String icon;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(mappedBy = "codigosubmenu")
    private Collection<Menup> menupCollection;
    @JoinColumn(name = "codigosubmenu", referencedColumnName = "idmenu")
    @ManyToOne
    private Menup codigosubmenu;

    public Menup() {
    }

    public Menup(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public Integer getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Menup> getMenupCollection() {
        return menupCollection;
    }

    public void setMenupCollection(Collection<Menup> menupCollection) {
        this.menupCollection = menupCollection;
    }

    public Menup getCodigosubmenu() {
        return codigosubmenu;
    }

    public void setCodigosubmenu(Menup codigosubmenu) {
        this.codigosubmenu = codigosubmenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmenu != null ? idmenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menup)) {
            return false;
        }
        Menup other = (Menup) object;
        if ((this.idmenu == null && other.idmenu != null) || (this.idmenu != null && !this.idmenu.equals(other.idmenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Jsf.Menup[ idmenu=" + idmenu + " ]";
    }
    
}
