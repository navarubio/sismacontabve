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
@Table(name = "tipoitemmenu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoitemmenu.findAll", query = "SELECT t FROM Tipoitemmenu t"),
    @NamedQuery(name = "Tipoitemmenu.findByIdtipoitemmenu", query = "SELECT t FROM Tipoitemmenu t WHERE t.idtipoitemmenu = :idtipoitemmenu"),
    @NamedQuery(name = "Tipoitemmenu.findByTipoitemmenu", query = "SELECT t FROM Tipoitemmenu t WHERE t.tipoitemmenu = :tipoitemmenu")})
public class Tipoitemmenu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipoitemmenu")
    private Integer idtipoitemmenu;
    @Size(max = 50)
    @Column(name = "tipoitemmenu")
    private String tipoitemmenu;
    @OneToMany(mappedBy = "idtipoitemmenu")
    private Collection<Menu> menuCollection;

    public Tipoitemmenu() {
    }

    public Tipoitemmenu(Integer idtipoitemmenu) {
        this.idtipoitemmenu = idtipoitemmenu;
    }

    public Integer getIdtipoitemmenu() {
        return idtipoitemmenu;
    }

    public void setIdtipoitemmenu(Integer idtipoitemmenu) {
        this.idtipoitemmenu = idtipoitemmenu;
    }

    public String getTipoitemmenu() {
        return tipoitemmenu;
    }

    public void setTipoitemmenu(String tipoitemmenu) {
        this.tipoitemmenu = tipoitemmenu;
    }

    @XmlTransient
    public Collection<Menu> getMenuCollection() {
        return menuCollection;
    }

    public void setMenuCollection(Collection<Menu> menuCollection) {
        this.menuCollection = menuCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoitemmenu != null ? idtipoitemmenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoitemmenu)) {
            return false;
        }
        Tipoitemmenu other = (Tipoitemmenu) object;
        if ((this.idtipoitemmenu == null && other.idtipoitemmenu != null) || (this.idtipoitemmenu != null && !this.idtipoitemmenu.equals(other.idtipoitemmenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tipoitemmenu;
    }
    
}
