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
@Table(name = "chofer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chofer.findAll", query = "SELECT c FROM Chofer c"),
    @NamedQuery(name = "Chofer.findByIdchofer", query = "SELECT c FROM Chofer c WHERE c.idchofer = :idchofer"),
    @NamedQuery(name = "Chofer.findByNombre", query = "SELECT c FROM Chofer c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Chofer.findByCedula", query = "SELECT c FROM Chofer c WHERE c.cedula = :cedula"),
    @NamedQuery(name = "Chofer.findByTelefonos", query = "SELECT c FROM Chofer c WHERE c.telefonos = :telefonos")})
public class Chofer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idchofer")
    private Integer idchofer;
    @Size(max = 200)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 10)
    @Column(name = "cedula")
    private String cedula;
    @Size(max = 100)
    @Column(name = "telefonos")
    private String telefonos;
    @OneToMany(mappedBy = "idchofer")
    private Collection<Despachopicadora> despachopicadoraCollection;

    public Chofer() {
    }

    public Chofer(Integer idchofer) {
        this.idchofer = idchofer;
    }

    public Integer getIdchofer() {
        return idchofer;
    }

    public void setIdchofer(Integer idchofer) {
        this.idchofer = idchofer;
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

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
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
        hash += (idchofer != null ? idchofer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chofer)) {
            return false;
        }
        Chofer other = (Chofer) object;
        if ((this.idchofer == null && other.idchofer != null) || (this.idchofer != null && !this.idchofer.equals(other.idchofer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Chofer[ idchofer=" + idchofer + " ]";
    }
    
}
