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
@Table(name = "despachopicadora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Despachopicadora.findAll", query = "SELECT d FROM Despachopicadora d"),
    @NamedQuery(name = "Despachopicadora.findByIddespachopicadora", query = "SELECT d FROM Despachopicadora d WHERE d.iddespachopicadora = :iddespachopicadora"),
    @NamedQuery(name = "Despachopicadora.findByFechadespacho", query = "SELECT d FROM Despachopicadora d WHERE d.fechadespacho = :fechadespacho"),
    @NamedQuery(name = "Despachopicadora.findBySerial", query = "SELECT d FROM Despachopicadora d WHERE d.serial = :serial"),
    @NamedQuery(name = "Despachopicadora.findByHora", query = "SELECT d FROM Despachopicadora d WHERE d.hora = :hora"),
    @NamedQuery(name = "Despachopicadora.findByObservaciones", query = "SELECT d FROM Despachopicadora d WHERE d.observaciones = :observaciones")})
public class Despachopicadora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddespachopicadora")
    private Integer iddespachopicadora;
    @Column(name = "fechadespacho")
    @Temporal(TemporalType.DATE)
    private Date fechadespacho;
    @Column(name = "serial")
    private Integer serial;
    @Size(max = 12)
    @Column(name = "hora")
    private String hora;
    @Size(max = 255)
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "cantidad")
    private Double cantidad;
    @Column(name = "pendiente")
    private Double pendiente;
    @OneToMany(mappedBy = "iddespachopicadora")
    private Collection<Movimientoinventariopicadora> movimientoinventariopicadoraCollection;
    @OneToMany(mappedBy = "iddespachopicadora")
    private Collection<Detalledespachopicadora> detalledespachopicadoraCollection;
    @JoinColumn(name = "iddespachador", referencedColumnName = "iddespachador")
    @ManyToOne
    private Despachador iddespachador;
    @JoinColumn(name = "idchofer", referencedColumnName = "idchofer")
    @ManyToOne
    private Chofer idchofer;
    @JoinColumn(name = "idcamion", referencedColumnName = "idcamion")
    @ManyToOne
    private Camion idcamion;
    @JoinColumn(name = "numerofact", referencedColumnName = "numerofact")
    @ManyToOne
    private Factura numerofact;
    @JoinColumn(name = "idnotacarga", referencedColumnName = "idnotacarga")
    @ManyToOne
    private Notacarga idnotacarga;
    @JoinColumn(name = "codigo", referencedColumnName = "codigo")
    @ManyToOne
    private Articulo codigo;

    

    public Despachopicadora() {
    }
    
    public Despachopicadora(Integer iddespachopicadora) {
        this.iddespachopicadora = iddespachopicadora;
    }

    public Factura getNumerofact() {
        return numerofact;
    }

    public void setNumerofact(Factura numerofact) {
        this.numerofact = numerofact;
    }

    public Notacarga getIdnotacarga() {
        return idnotacarga;
    }

    public void setIdnotacarga(Notacarga idnotacarga) {
        this.idnotacarga = idnotacarga;
    }
    
    public Integer getIddespachopicadora() {
        return iddespachopicadora;
    }

    public Articulo getCodigo() {
        return codigo;
    }

    public void setCodigo(Articulo codigo) {
        this.codigo = codigo;
    }
    
    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPendiente() {
        return pendiente;
    }

    public void setPendiente (Double pendiente) {
        this.pendiente = pendiente;
    }
    public void setIddespachopicadora(Integer iddespachopicadora) {
        this.iddespachopicadora = iddespachopicadora;
    }

    public Date getFechadespacho() {
        return fechadespacho;
    }

    public void setFechadespacho(Date fechadespacho) {
        this.fechadespacho = fechadespacho;
    }

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial= serial;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public Collection<Movimientoinventariopicadora> getMovimientoinventariopicadoraCollection() {
        return movimientoinventariopicadoraCollection;
    }

    public void setMovimientoinventariopicadoraCollection(Collection<Movimientoinventariopicadora> movimientoinventariopicadoraCollection) {
        this.movimientoinventariopicadoraCollection = movimientoinventariopicadoraCollection;
    }

    @XmlTransient
    public Collection<Detalledespachopicadora> getDetalledespachopicadoraCollection() {
        return detalledespachopicadoraCollection;
    }

    public void setDetalledespachopicadoraCollection(Collection<Detalledespachopicadora> detalledespachopicadoraCollection) {
        this.detalledespachopicadoraCollection = detalledespachopicadoraCollection;
    }

    public Despachador getIddespachador() {
        return iddespachador;
    }

    public void setIddespachador(Despachador iddespachador) {
        this.iddespachador = iddespachador;
    }

    public Chofer getIdchofer() {
        return idchofer;
    }

    public void setIdchofer(Chofer idchofer) {
        this.idchofer = idchofer;
    }

    public Camion getIdcamion() {
        return idcamion;
    }

    public void setIdcamion(Camion idcamion) {
        this.idcamion = idcamion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddespachopicadora != null ? iddespachopicadora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Despachopicadora)) {
            return false;
        }
        Despachopicadora other = (Despachopicadora) object;
        if ((this.iddespachopicadora == null && other.iddespachopicadora != null) || (this.iddespachopicadora != null && !this.iddespachopicadora.equals(other.iddespachopicadora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Despachopicadora[ iddespachopicadora=" + iddespachopicadora + " ]";
    }
    
}
