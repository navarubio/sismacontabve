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
@Table(name = "usuariodeprol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuariodeprol.findAll", query = "SELECT u FROM Usuariodeprol u"),
    @NamedQuery(name = "Usuariodeprol.findByIdusuariodeprol", query = "SELECT u FROM Usuariodeprol u WHERE u.idusuariodeprol = :idusuariodeprol")})
public class Usuariodeprol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuariodeprol")
    private Integer idusuariodeprol;
    @JoinColumn(name = "iddepartamento", referencedColumnName = "iddepartamento")
    @ManyToOne
    private Departamento iddepartamento;
    @JoinColumn(name = "idrol", referencedColumnName = "idrol")
    @ManyToOne
    private Rol idrol;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario idusuario;

    public Usuariodeprol() {
    }

    public Usuariodeprol(Integer idusuariodeprol) {
        this.idusuariodeprol = idusuariodeprol;
    }

    public Integer getIdusuariodeprol() {
        return idusuariodeprol;
    }

    public void setIdusuariodeprol(Integer idusuariodeprol) {
        this.idusuariodeprol = idusuariodeprol;
    }

    public Departamento getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(Departamento iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public Rol getIdrol() {
        return idrol;
    }

    public void setIdrol(Rol idrol) {
        this.idrol = idrol;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuariodeprol != null ? idusuariodeprol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuariodeprol)) {
            return false;
        }
        Usuariodeprol other = (Usuariodeprol) object;
        if ((this.idusuariodeprol == null && other.idusuariodeprol != null) || (this.idusuariodeprol != null && !this.idusuariodeprol.equals(other.idusuariodeprol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Usuariodeprol[ idusuariodeprol=" + idusuariodeprol + " ]";
    }
    
}
