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
@Table(name = "reposicioncajachica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reposicioncajachica.findAll", query = "SELECT r FROM Reposicioncajachica r"),
    @NamedQuery(name = "Reposicioncajachica.findByIdreposicioncajachica", query = "SELECT r FROM Reposicioncajachica r WHERE r.idreposicioncajachica = :idreposicioncajachica"),
    @NamedQuery(name = "Reposicioncajachica.findByFecharesposicion", query = "SELECT r FROM Reposicioncajachica r WHERE r.fecharesposicion = :fecharesposicion"),
    @NamedQuery(name = "Reposicioncajachica.findByMontoreposicion", query = "SELECT r FROM Reposicioncajachica r WHERE r.montoreposicion = :montoreposicion"),
    @NamedQuery(name = "Reposicioncajachica.findByNumerodocumento", query = "SELECT r FROM Reposicioncajachica r WHERE r.numerodocumento = :numerodocumento"),
    @NamedQuery(name = "Reposicioncajachica.findByBeneficiario", query = "SELECT r FROM Reposicioncajachica r WHERE r.beneficiario = :beneficiario"),
    @NamedQuery(name = "Reposicioncajachica.findByObservaciones", query = "SELECT r FROM Reposicioncajachica r WHERE r.observaciones = :observaciones")})
public class Reposicioncajachica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idreposicioncajachica")
    private Integer idreposicioncajachica;
    @Column(name = "serialreposicion")
    private Integer serialreposicion;
    @Column(name = "fecharesposicion")
    @Temporal(TemporalType.DATE)
    private Date fecharesposicion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montoreposicion")
    private Double montoreposicion;
    @Size(max = 15)
    @Column(name = "numerodocumento")
    private String numerodocumento;
    @Size(max = 50)
    @Column(name = "beneficiario")
    private String beneficiario;
    @Size(max = 255)
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(mappedBy = "idreposicioncajachica")
    private Collection<Reposicionconsumos> reposicionconsumosCollection;
    @JoinColumn(name = "idcuentabancaria", referencedColumnName = "idcuentabancaria")
    @ManyToOne
    private Cuentabancaria idcuentabancaria;
    @JoinColumn(name = "idtipopago", referencedColumnName = "idtipopago")
    @ManyToOne
    private Tipopago idtipopago;
    @JoinColumn(name = "idrepositor", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario idrepositor;
    @OneToMany(mappedBy = "idreposicioncajachica")
    private Collection<Maestromovimiento> maestromovimientoCollection;    


    public Reposicioncajachica() {
    }

    public Reposicioncajachica(Integer idreposicioncajachica) {
        this.idreposicioncajachica = idreposicioncajachica;
    }

    public Integer getIdreposicioncajachica() {
        return idreposicioncajachica;
    }

    public void setIdreposicioncajachica(Integer idreposicioncajachica) {
        this.idreposicioncajachica = idreposicioncajachica;
    }

    public Integer getSerialreposicion() {
        return serialreposicion;
    }

    public void setSerialreposicion(Integer serialreposicion) {
        this.serialreposicion = serialreposicion;
    }

    public Date getFecharesposicion() {
        return fecharesposicion;
    }

    public void setFecharesposicion(Date fecharesposicion) {
        this.fecharesposicion = fecharesposicion;
    }

    public Double getMontoreposicion() {
        return montoreposicion;
    }

    public void setMontoreposicion(Double montoreposicion) {
        this.montoreposicion = montoreposicion;
    }

    public String getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(String numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public Collection<Reposicionconsumos> getReposicionconsumosCollection() {
        return reposicionconsumosCollection;
    }

    public void setReposicionconsumosCollection(Collection<Reposicionconsumos> reposicionconsumosCollection) {
        this.reposicionconsumosCollection = reposicionconsumosCollection;
    }

    public Cuentabancaria getIdcuentabancaria() {
        return idcuentabancaria;
    }

    public void setIdcuentabancaria(Cuentabancaria idcuentabancaria) {
        this.idcuentabancaria = idcuentabancaria;
    }

    public Tipopago getIdtipopago() {
        return idtipopago;
    }

    public void setIdtipopago(Tipopago idtipopago) {
        this.idtipopago = idtipopago;
    }

    public Usuario getIdrepositor() {
        return idrepositor;
    }

    public void setIdrepositor(Usuario idrepositor) {
        this.idrepositor = idrepositor;
    }

    @XmlTransient
    public Collection<Maestromovimiento> getMaestromovimientoCollection() {
        return maestromovimientoCollection;
    }

    public void setMaestromovimientoCollection(Collection<Maestromovimiento> maestromovimientoCollection) {
        this.maestromovimientoCollection = maestromovimientoCollection;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idreposicioncajachica != null ? idreposicioncajachica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reposicioncajachica)) {
            return false;
        }
        Reposicioncajachica other = (Reposicioncajachica) object;
        if ((this.idreposicioncajachica == null && other.idreposicioncajachica != null) || (this.idreposicioncajachica != null && !this.idreposicioncajachica.equals(other.idreposicioncajachica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Reposicioncajachica[ idreposicioncajachica=" + idreposicioncajachica + " ]";
    }
    
}
