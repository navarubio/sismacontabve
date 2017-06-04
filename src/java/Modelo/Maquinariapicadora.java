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
@Table(name = "maquinariapicadora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Maquinariapicadora.findAll", query = "SELECT m FROM Maquinariapicadora m"),
    @NamedQuery(name = "Maquinariapicadora.findByIdmaquinariapicadora", query = "SELECT m FROM Maquinariapicadora m WHERE m.idmaquinariapicadora = :idmaquinariapicadora"),
    @NamedQuery(name = "Maquinariapicadora.findByDescripcion", query = "SELECT m FROM Maquinariapicadora m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "Maquinariapicadora.findByPrecio1", query = "SELECT m FROM Maquinariapicadora m WHERE m.precio1 = :precio1"),
    @NamedQuery(name = "Maquinariapicadora.findByPrecio2", query = "SELECT m FROM Maquinariapicadora m WHERE m.precio2 = :precio2")})
public class Maquinariapicadora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmaquinariapicadora")
    private Integer idmaquinariapicadora;
    @Size(max = 15)
    @Column(name = "serialmaquinaria")
    private String serialmaquinaria;
    @Size(max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio1")
    private Double precio1;
    @Column(name = "precio2")
    private Double precio2;
    @OneToMany(mappedBy = "idmaquinariapicadora")
    private Collection<Usomaquinariapicadora> usomaquinariapicadoraCollection;
    @JoinColumn(name = "idtipomaquinaria", referencedColumnName = "idtipomaquinaria")
    @ManyToOne
    private Tipomaquinaria idtipomaquinaria;
    @JoinColumn(name = "idmedida", referencedColumnName = "idmedida")
    @ManyToOne
    private Medida idmedida;


    public Maquinariapicadora() {
    }

    public Maquinariapicadora(Integer idmaquinariapicadora) {
        this.idmaquinariapicadora = idmaquinariapicadora;
    }

    public Integer getIdmaquinariapicadora() {
        return idmaquinariapicadora;
    }

    public void setIdmaquinariapicadora(Integer idmaquinariapicadora) {
        this.idmaquinariapicadora = idmaquinariapicadora;
    }
    
    public String getSerialmaquinaria() {
        return serialmaquinaria;
    }

    public void setSerialmaquinaria(String serialmaquinaria) {
        this.serialmaquinaria = serialmaquinaria;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Medida getIdmedida() {
        return idmedida;
    }

    public void setIdmedida(Medida idmedida) {
        this.idmedida = idmedida;
    }
    
    public Double getPrecio1() {
        return precio1;
    }

    public void setPrecio1(Double precio1) {
        this.precio1 = precio1;
    }

    public Double getPrecio2() {
        return precio2;
    }

    public void setPrecio2(Double precio2) {
        this.precio2 = precio2;
    }

    @XmlTransient
    public Collection<Usomaquinariapicadora> getUsomaquinariapicadoraCollection() {
        return usomaquinariapicadoraCollection;
    }

    public void setUsomaquinariapicadoraCollection(Collection<Usomaquinariapicadora> usomaquinariapicadoraCollection) {
        this.usomaquinariapicadoraCollection = usomaquinariapicadoraCollection;
    }

    public Tipomaquinaria getIdtipomaquinaria() {
        return idtipomaquinaria;
    }

    public void setIdtipomaquinaria(Tipomaquinaria idtipomaquinaria) {
        this.idtipomaquinaria = idtipomaquinaria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmaquinariapicadora != null ? idmaquinariapicadora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Maquinariapicadora)) {
            return false;
        }
        Maquinariapicadora other = (Maquinariapicadora) object;
        if ((this.idmaquinariapicadora == null && other.idmaquinariapicadora != null) || (this.idmaquinariapicadora != null && !this.idmaquinariapicadora.equals(other.idmaquinariapicadora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Maquinariapicadora[ idmaquinariapicadora=" + idmaquinariapicadora + " ]";
    }
    
}
