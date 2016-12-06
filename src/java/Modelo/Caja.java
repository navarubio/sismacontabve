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
@Table(name = "caja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caja.findAll", query = "SELECT c FROM Caja c"),
    @NamedQuery(name = "Caja.findByIdcaja", query = "SELECT c FROM Caja c WHERE c.idcaja = :idcaja"),
    @NamedQuery(name = "Caja.findByDescripcion", query = "SELECT c FROM Caja c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Caja.findByTotaldespachado", query = "SELECT c FROM Caja c WHERE c.totaldespachado = :totaldespachado"),
    @NamedQuery(name = "Caja.findByNumeracionfactura", query = "SELECT c FROM Caja c WHERE c.numeracionfactura = :numeracionfactura"),
    @NamedQuery(name = "Caja.findByClientesatendidos", query = "SELECT c FROM Caja c WHERE c.clientesatendidos = :clientesatendidos")})
public class Caja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcaja")
    private Integer idcaja;
    @Size(max = 25)
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totaldespachado")
    private Double totaldespachado;
    @Column(name = "numeracionfactura")
    private Integer numeracionfactura;
    @Column(name = "clientesatendidos")
    private Integer clientesatendidos;
    @OneToMany(mappedBy = "idcaja")
    private Collection<Factura> facturaCollection;
    @JoinColumn(name = "idestatuscaja", referencedColumnName = "idestatuscaja")
    @ManyToOne
    private Estatuscaja idestatuscaja;

    public Caja() {
    }

    public Caja(Integer idcaja) {
        this.idcaja = idcaja;
    }

    public Integer getIdcaja() {
        return idcaja;
    }

    public void setIdcaja(Integer idcaja) {
        this.idcaja = idcaja;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getTotaldespachado() {
        return totaldespachado;
    }

    public void setTotaldespachado(Double totaldespachado) {
        this.totaldespachado = totaldespachado;
    }

    public Integer getNumeracionfactura() {
        return numeracionfactura;
    }

    public void setNumeracionfactura(Integer numeracionfactura) {
        this.numeracionfactura = numeracionfactura;
    }

    public Integer getClientesatendidos() {
        return clientesatendidos;
    }

    public void setClientesatendidos(Integer clientesatendidos) {
        this.clientesatendidos = clientesatendidos;
    }

    @XmlTransient
    public Collection<Factura> getFacturaCollection() {
        return facturaCollection;
    }

    public void setFacturaCollection(Collection<Factura> facturaCollection) {
        this.facturaCollection = facturaCollection;
    }

    public Estatuscaja getIdestatuscaja() {
        return idestatuscaja;
    }

    public void setIdestatuscaja(Estatuscaja idestatuscaja) {
        this.idestatuscaja = idestatuscaja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcaja != null ? idcaja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caja)) {
            return false;
        }
        Caja other = (Caja) object;
        if ((this.idcaja == null && other.idcaja != null) || (this.idcaja != null && !this.idcaja.equals(other.idcaja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
}
