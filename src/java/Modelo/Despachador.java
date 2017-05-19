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
@Table(name = "despachador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Despachador.findAll", query = "SELECT d FROM Despachador d"),
    @NamedQuery(name = "Despachador.findByIddespachador", query = "SELECT d FROM Despachador d WHERE d.iddespachador = :iddespachador"),
    @NamedQuery(name = "Despachador.findByNombre", query = "SELECT d FROM Despachador d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "Despachador.findByCedula", query = "SELECT d FROM Despachador d WHERE d.cedula = :cedula")})
public class Despachador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddespachador")
    private Integer iddespachador;
    @Size(max = 150)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 10)
    @Column(name = "cedula")
    private String cedula;
    @OneToMany(mappedBy = "iddespachador")
    private Collection<Despachopicadora> despachopicadoraCollection;

    public Despachador() {
    }

    public Despachador(Integer iddespachador) {
        this.iddespachador = iddespachador;
    }

    public Integer getIddespachador() {
        return iddespachador;
    }

    public void setIddespachador(Integer iddespachador) {
        this.iddespachador = iddespachador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    @XmlTransient
    public Collection<Despachopicadora> getDespachopicadoraCollection() {
        return despachopicadoraCollection;
    }

    public void setDespachopicadoraCollection(Collection<Despachopicadora> despachopicadoraCollection) {
        this.despachopicadoraCollection = despachopicadoraCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddespachador != null ? iddespachador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Despachador)) {
            return false;
        }
        Despachador other = (Despachador) object;
        if ((this.iddespachador == null && other.iddespachador != null) || (this.iddespachador != null && !this.iddespachador.equals(other.iddespachador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Despachador[ iddespachador=" + iddespachador + " ]";
    }
    
}
