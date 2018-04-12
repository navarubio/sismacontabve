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
@Table(name = "cajachica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cajachica.findAll", query = "SELECT c FROM Cajachica c"),
    @NamedQuery(name = "Cajachica.findByIdcajachica", query = "SELECT c FROM Cajachica c WHERE c.idcajachica = :idcajachica"),
    @NamedQuery(name = "Cajachica.findByDescripcion", query = "SELECT c FROM Cajachica c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Cajachica.findByMontoasignado", query = "SELECT c FROM Cajachica c WHERE c.montoasignado = :montoasignado"),
    @NamedQuery(name = "Cajachica.findByMontomaximo", query = "SELECT c FROM Cajachica c WHERE c.montomaximo = :montomaximo"),
    @NamedQuery(name = "Cajachica.findByMontominimo", query = "SELECT c FROM Cajachica c WHERE c.montominimo = :montominimo"),
    @NamedQuery(name = "Cajachica.findBySaldoactual", query = "SELECT c FROM Cajachica c WHERE c.saldoactual = :saldoactual"),
    @NamedQuery(name = "Cajachica.findByEstatuscajachica", query = "SELECT c FROM Cajachica c WHERE c.estatuscajachica = :estatuscajachica")})
public class Cajachica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcajachica")
    private Integer idcajachica;
    @Size(max = 150)
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montoasignado")
    private Double montoasignado;
    @Column(name = "montomaximo")
    private Double montomaximo;
    @Column(name = "montominimo")
    private Double montominimo;
    @Column(name = "saldoactual")
    private Double saldoactual;
    @Column(name = "estatuscajachica")
    private Integer estatuscajachica;
    @OneToMany(mappedBy = "idcajachica")
    private Collection<Consumocajachica> consumocajachicaCollection;
    @JoinColumn(name = "idempresa", referencedColumnName = "idempresa")
    @ManyToOne
    private Empresa idempresa;
    @JoinColumn(name = "idplandecuenta", referencedColumnName = "idplandecuenta")
    @ManyToOne
    private Plandecuenta idplandecuenta;
    @JoinColumn(name = "idcustodio", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario idcustodio;

    public Cajachica() {
    }

    public Cajachica(Integer idcajachica) {
        this.idcajachica = idcajachica;
    }

    public Integer getIdcajachica() {
        return idcajachica;
    }

    public void setIdcajachica(Integer idcajachica) {
        this.idcajachica = idcajachica;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getMontoasignado() {
        return montoasignado;
    }

    public void setMontoasignado(Double montoasignado) {
        this.montoasignado = montoasignado;
    }

    public Double getMontomaximo() {
        return montomaximo;
    }

    public void setMontomaximo(Double montomaximo) {
        this.montomaximo = montomaximo;
    }

    public Double getMontominimo() {
        return montominimo;
    }

    public void setMontominimo(Double montominimo) {
        this.montominimo = montominimo;
    }

    public Double getSaldoactual() {
        return saldoactual;
    }

    public void setSaldoactual(Double saldoactual) {
        this.saldoactual = saldoactual;
    }

    public Integer getEstatuscajachica() {
        return estatuscajachica;
    }

    public void setEstatuscajachica(Integer estatuscajachica) {
        this.estatuscajachica = estatuscajachica;
    }

    @XmlTransient
    public Collection<Consumocajachica> getConsumocajachicaCollection() {
        return consumocajachicaCollection;
    }

    public void setConsumocajachicaCollection(Collection<Consumocajachica> consumocajachicaCollection) {
        this.consumocajachicaCollection = consumocajachicaCollection;
    }

    public Empresa getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Empresa idempresa) {
        this.idempresa = idempresa;
    }

    public Plandecuenta getIdplandecuenta() {
        return idplandecuenta;
    }

    public void setIdplandecuenta(Plandecuenta idplandecuenta) {
        this.idplandecuenta = idplandecuenta;
    }

    public Usuario getIdcustodio() {
        return idcustodio;
    }

    public void setIdcustodio(Usuario idcustodio) {
        this.idcustodio = idcustodio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcajachica != null ? idcajachica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cajachica)) {
            return false;
        }
        Cajachica other = (Cajachica) object;
        if ((this.idcajachica == null && other.idcajachica != null) || (this.idcajachica != null && !this.idcajachica.equals(other.idcajachica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Cajachica[ idcajachica=" + idcajachica + " ]";
    }
    
}
