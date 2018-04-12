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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sofimar
 */
@Entity
@Table(name = "centrodecosto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Centrodecosto.findAll", query = "SELECT c FROM Centrodecosto c"),
    @NamedQuery(name = "Centrodecosto.findByCodigocentrocosto", query = "SELECT c FROM Centrodecosto c WHERE c.codigocentrocosto = :codigocentrocosto"),
    @NamedQuery(name = "Centrodecosto.findByDescripcion", query = "SELECT c FROM Centrodecosto c WHERE c.descripcion = :descripcion")})
public class Centrodecosto implements Serializable {
    @OneToMany(mappedBy = "codigocentrocosto")
    private Collection<Detalleconsumocajachica> detalleconsumocajachicaCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigocentrocosto")
    private Integer codigocentrocosto;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "iddepartamento", referencedColumnName = "iddepartamento")
    @ManyToOne
    private Departamento iddepartamento;
    @JoinColumn(name = "idempresa", referencedColumnName = "idempresa")
    @ManyToOne
    private Empresa idempresa;
    @JoinColumn(name = "idresponsable", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario idresponsable;

    public Centrodecosto() {
    }

    public Centrodecosto(Integer codigocentrocosto) {
        this.codigocentrocosto = codigocentrocosto;
    }

    public Integer getCodigocentrocosto() {
        return codigocentrocosto;
    }

    public void setCodigocentrocosto(Integer codigocentrocosto) {
        this.codigocentrocosto = codigocentrocosto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Departamento getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(Departamento iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public Empresa getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Empresa idempresa) {
        this.idempresa = idempresa;
    }

    public Usuario getIdresponsable() {
        return idresponsable;
    }

    public void setIdresponsable(Usuario idresponsable) {
        this.idresponsable = idresponsable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigocentrocosto != null ? codigocentrocosto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Centrodecosto)) {
            return false;
        }
        Centrodecosto other = (Centrodecosto) object;
        if ((this.codigocentrocosto == null && other.codigocentrocosto != null) || (this.codigocentrocosto != null && !this.codigocentrocosto.equals(other.codigocentrocosto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  descripcion;
    }

    @XmlTransient
    public Collection<Detalleconsumocajachica> getDetalleconsumocajachicaCollection() {
        return detalleconsumocajachicaCollection;
    }

    public void setDetalleconsumocajachicaCollection(Collection<Detalleconsumocajachica> detalleconsumocajachicaCollection) {
        this.detalleconsumocajachicaCollection = detalleconsumocajachicaCollection;
    }
    
}
