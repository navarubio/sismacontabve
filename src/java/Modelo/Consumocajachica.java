/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sofimar
 */
@Entity
@Table(name = "consumocajachica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consumocajachica.findAll", query = "SELECT c FROM Consumocajachica c"),
    @NamedQuery(name = "Consumocajachica.findByIdconsumocajachica", query = "SELECT c FROM Consumocajachica c WHERE c.idconsumocajachica = :idconsumocajachica"),
    @NamedQuery(name = "Consumocajachica.findByFechaloteconsumo", query = "SELECT c FROM Consumocajachica c WHERE c.fechaloteconsumo = :fechaloteconsumo"),
    @NamedQuery(name = "Consumocajachica.findByObservaciones", query = "SELECT c FROM Consumocajachica c WHERE c.observaciones = :observaciones"),
    @NamedQuery(name = "Consumocajachica.findBySubtotalconsumo", query = "SELECT c FROM Consumocajachica c WHERE c.subtotalconsumo = :subtotalconsumo"),
    @NamedQuery(name = "Consumocajachica.findByIvaconsumo", query = "SELECT c FROM Consumocajachica c WHERE c.ivaconsumo = :ivaconsumo"),
    @NamedQuery(name = "Consumocajachica.findByTotalconsumo", query = "SELECT c FROM Consumocajachica c WHERE c.totalconsumo = :totalconsumo"),
    @NamedQuery(name = "Consumocajachica.findBySaldocajaactual", query = "SELECT c FROM Consumocajachica c WHERE c.saldocajaactual = :saldocajaactual")})
public class Consumocajachica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idconsumocajachica")
    private Integer idconsumocajachica;
    @Column(name = "serialconsumo")
    private Integer serialconsumo;
    @Column(name = "fechaloteconsumo")
    @Temporal(TemporalType.DATE)
    private Date fechaloteconsumo;
    @Size(max = 255)
    @Column(name = "observaciones")
    private String observaciones;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "subtotalconsumo")
    private Double subtotalconsumo;
    @Column(name = "ivaconsumo")
    private Double ivaconsumo;
    @Column(name = "totalconsumo")
    private Double totalconsumo;
    @Column(name = "saldocajaactual")
    private Double saldocajaactual;
    @JoinColumn(name = "idcajachica", referencedColumnName = "idcajachica")
    @ManyToOne
    private Cajachica idcajachica;
    @JoinColumn(name = "idestatusconsumocajachica", referencedColumnName = "idestatusconsumocajachica")
    @ManyToOne
    private Estatusconsumocajachica idestatusconsumocajachica;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario idusuario;
    @OneToMany(mappedBy = "idconsumocajachica")
    private Collection<Detalleconsumocajachica> detalleconsumocajachicaCollection;
    @OneToMany(mappedBy = "idconsumocajachica")
    private Collection<Reposicionconsumos> reposicionconsumosCollection;

    public Consumocajachica() {
    }

    public Consumocajachica(Integer idconsumocajachica) {
        this.idconsumocajachica = idconsumocajachica;
    }

    public Integer getIdconsumocajachica() {
        return idconsumocajachica;
    }

    public void setIdconsumocajachica(Integer idconsumocajachica) {
        this.idconsumocajachica = idconsumocajachica;
    }

    public Integer getSerialconsumo() {
        return serialconsumo;
    }

    public void setSerialconsumo(Integer serialconsumo) {
        this.serialconsumo = serialconsumo;
    }
    
    public Date getFechaloteconsumo() {
        return fechaloteconsumo;
    }

    public void setFechaloteconsumo(Date fechaloteconsumo) {
        this.fechaloteconsumo = fechaloteconsumo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Double getSubtotalconsumo() {
        return subtotalconsumo;
    }

    public void setSubtotalconsumo(Double subtotalconsumo) {
        this.subtotalconsumo = subtotalconsumo;
    }

    public Double getIvaconsumo() {
        return ivaconsumo;
    }

    public void setIvaconsumo(Double ivaconsumo) {
        this.ivaconsumo = ivaconsumo;
    }

    public Double getTotalconsumo() {
        return totalconsumo;
    }

    public void setTotalconsumo(Double totalconsumo) {
        this.totalconsumo = totalconsumo;
    }

    public Double getSaldocajaactual() {
        return saldocajaactual;
    }

    public void setSaldocajaactual(Double saldocajaactual) {
        this.saldocajaactual = saldocajaactual;
    }

    public Cajachica getIdcajachica() {
        return idcajachica;
    }

    public void setIdcajachica(Cajachica idcajachica) {
        this.idcajachica = idcajachica;
    }

    public Estatusconsumocajachica getIdestatusconsumocajachica() {
        return idestatusconsumocajachica;
    }

    public void setIdestatusconsumocajachica(Estatusconsumocajachica idestatusconsumocajachica) {
        this.idestatusconsumocajachica = idestatusconsumocajachica;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @XmlTransient
    public Collection<Detalleconsumocajachica> getDetalleconsumocajachicaCollection() {
        return detalleconsumocajachicaCollection;
    }

    public void setDetalleconsumocajachicaCollection(Collection<Detalleconsumocajachica> detalleconsumocajachicaCollection) {
        this.detalleconsumocajachicaCollection = detalleconsumocajachicaCollection;
    }

    @XmlTransient
    public Collection<Reposicionconsumos> getReposicionconsumosCollection() {
        return reposicionconsumosCollection;
    }

    public void setReposicionconsumosCollection(Collection<Reposicionconsumos> reposicionconsumosCollection) {
        this.reposicionconsumosCollection = reposicionconsumosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idconsumocajachica != null ? idconsumocajachica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consumocajachica)) {
            return false;
        }
        Consumocajachica other = (Consumocajachica) object;
        if ((this.idconsumocajachica == null && other.idconsumocajachica != null) || (this.idconsumocajachica != null && !this.idconsumocajachica.equals(other.idconsumocajachica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Consumocajachica[ idconsumocajachica=" + idconsumocajachica + " ]";
    }
    
}
