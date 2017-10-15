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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sofimar
 */
@Entity
@Table(name = "menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m"),
    @NamedQuery(name = "Menu.findByIdmenu", query = "SELECT m FROM Menu m WHERE m.idmenu = :idmenu"),
    @NamedQuery(name = "Menu.findByDescripcion", query = "SELECT m FROM Menu m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "Menu.findByUrl", query = "SELECT m FROM Menu m WHERE m.url = :url"),
    @NamedQuery(name = "Menu.findByIcon", query = "SELECT m FROM Menu m WHERE m.icon = :icon"),
    @NamedQuery(name = "Menu.findByEstado", query = "SELECT m FROM Menu m WHERE m.estado = :estado")})
public class Menu implements Serializable {
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
    @JoinColumn(name = "idsubmenu", referencedColumnName = "idsubmenu")
    @ManyToOne
    private Submenu idsubmenu;
    @JoinColumn(name = "idrol", referencedColumnName = "idrol")
    @ManyToOne
    private Rol idrol;
    @JoinColumn(name = "idtipoitemmenu", referencedColumnName = "idtipoitemmenu")
    @ManyToOne
    private Tipoitemmenu idtipoitemmenu;
    @JoinColumn(name = "iddepartamento", referencedColumnName = "iddepartamento")
    @ManyToOne
    private Departamento iddepartamento;
    
    @JoinColumn(name = "codigosubmenu", referencedColumnName = "idmenu")
    @ManyToOne
    private Menu codigosubmenu;

    public Menu() {
    }

    public Menu(Integer idmenu) {
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

    public Submenu getIdsubmenu() {
        return idsubmenu;
    }

    public void setIdsubmenu(Submenu idsubmenu) {
        this.idsubmenu = idsubmenu;
    }

    public Rol getIdrol() {
        return idrol;
    }

    public void setIdrol(Rol idrol) {
        this.idrol = idrol;
    }

    public Tipoitemmenu getIdtipoitemmenu() {
        return idtipoitemmenu;
    }

    public void setIdtipoitemmenu(Tipoitemmenu idtipoitemmenu) {
        this.idtipoitemmenu = idtipoitemmenu;
    }

    public Departamento getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(Departamento iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public Menu getCodigosubmenu() {
        return codigosubmenu;
    }

    public void setCodigosubmenu(Menu codigosubmenu) {
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
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.idmenu == null && other.idmenu != null) || (this.idmenu != null && !this.idmenu.equals(other.idmenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
}
