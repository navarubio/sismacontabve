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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sofimar/Ecuador
 */
@Entity
@Table(name = "empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.findByRif", query = "SELECT e FROM Empresa e WHERE e.rif = :rif"),
    @NamedQuery(name = "Empresa.findByRazonsocial", query = "SELECT e FROM Empresa e WHERE e.razonsocial = :razonsocial"),
    @NamedQuery(name = "Empresa.findByNombrecomercial", query = "SELECT e FROM Empresa e WHERE e.nombrecomercial = :nombrecomercial"),
    @NamedQuery(name = "Empresa.findByDireccionfiscal", query = "SELECT e FROM Empresa e WHERE e.direccionfiscal = :direccionfiscal"),
    @NamedQuery(name = "Empresa.findByTelefonos", query = "SELECT e FROM Empresa e WHERE e.telefonos = :telefonos"),
    @NamedQuery(name = "Empresa.findByPresidente", query = "SELECT e FROM Empresa e WHERE e.presidente = :presidente"),
    @NamedQuery(name = "Empresa.findByAdministrador", query = "SELECT e FROM Empresa e WHERE e.administrador = :administrador"),
    @NamedQuery(name = "Empresa.findByContador", query = "SELECT e FROM Empresa e WHERE e.contador = :contador")})
public class Empresa implements Serializable {

    @OneToMany(mappedBy = "idempresa")
    private Collection<Cajachica> cajachicaCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idempresa")
    private Integer idempresa;
    @Size(max = 15)
    @Column(name = "rif")
    private String rif;
    @Size(max = 100)
    @Column(name = "razonsocial")
    private String razonsocial;
    @Size(max = 50)
    @Column(name = "nombrecomercial")
    private String nombrecomercial;
    @Size(max = 255)
    @Column(name = "direccionfiscal")
    private String direccionfiscal;
    @Size(max = 50)
    @Column(name = "telefonos")
    private String telefonos;
    @Size(max = 150)
    @Column(name = "presidente")
    private String presidente;
    @Size(max = 150)
    @Column(name = "administrador")
    private String administrador;
    @Size(max = 150)
    @Column(name = "contador")
    private String contador;
    @Column(name = "montoparaautorizacion")
    private Double montoparaautorizacion;
    @JoinColumn(name = "idcontribuyente", referencedColumnName = "idcontribuyente")
    @ManyToOne
    private Contribuyente idcontribuyente;
    @JoinColumn(name = "idpersonalidad", referencedColumnName = "idpersonalidad")
    @ManyToOne
    private Personalidadjuridica idpersonalidad;
    @Column(name = "serialconsumo")
    private Integer serialconsumo;
    @Column(name = "serialreposicion")
    private Integer serialreposicion;
    @Column(name = "serialconciliacion")
    private Integer serialconciliacion;
    @Column(name = "serialrequerimiento")
    private Integer serialrequerimiento;
    @Column(name = "serialcompra")
    private Integer serialcompra;
    @Column(name = "serialpagocompra")
    private Integer serialpagocompra;
    @Column(name = "serialcomprobanteiva")
    private Integer serialcomprobanteiva;
    @Column(name = "serialcomprobanteislr")
    private Integer serialcomprobanteislr;
    @Column(name = "serialfactura")
    private Integer serialfactura;
        
    @Column(name = "ctaxpagarproveed")
    private Integer ctaxpagarproved;
    @Column(name = "credfiscal")
    private Integer credfiscal;
    @Column(name = "retivacliente")
    private Integer retivacliente;
    @Column(name = "retislrcliente")
    private Integer retislrcliente;
    @Column(name = "ctasxcobrar")
    private Integer ctasxcobrar;
    @Column(name = "ctagastoprovisional")
    private Integer ctagastoprovisional;
    @Column(name = "debfiscal")
    private Integer debfiscal;
    @Column(name = "retislrxenterar")
    private Integer retislrxenterar;
    @Column(name = "retivaxenterar")
    private Integer retivaxenterar;
    @Column(name = "ctaxpagarinterna")
    private Integer ctaxpagarinterna;
    @Column(name = "serialasiento")
    private Integer serialasiento;

    @OneToMany(mappedBy = "idempresa")
    private Collection<Departamento> departamentoCollection;
    @OneToMany(mappedBy = "idempresa")
    private Collection<Centrodecosto> centrodecostoCollection;
    @OneToMany(mappedBy = "idempresa")
    private Collection<Cuentabancaria> cuentabancariaCollection;

    public Empresa() {
    }

    public Empresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getNombrecomercial() {
        return nombrecomercial;
    }

    public void setNombrecomercial(String nombrecomercial) {
        this.nombrecomercial = nombrecomercial;
    }

    public String getDireccionfiscal() {
        return direccionfiscal;
    }

    public void setDireccionfiscal(String direccionfiscal) {
        this.direccionfiscal = direccionfiscal;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getPresidente() {
        return presidente;
    }

    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }

    public String getContador() {
        return contador;
    }

    public void setContador(String contador) {
        this.contador = contador;
    }

    public Contribuyente getIdcontribuyente() {
        return idcontribuyente;
    }

    public void setIdcontribuyente(Contribuyente idcontribuyente) {
        this.idcontribuyente = idcontribuyente;
    }

    public Double getMontoparaautorizacion() {
        return montoparaautorizacion;
    }

    public void setMontoparaautorizacion(Double montoparaautorizacion) {
        this.montoparaautorizacion = montoparaautorizacion;
    }

    public Personalidadjuridica getIdpersonalidad() {
        return idpersonalidad;
    }

    public void setIdpersonalidad(Personalidadjuridica idpersonalidad) {
        this.idpersonalidad = idpersonalidad;
    }

    public Integer getSerialconsumo() {
        return serialconsumo;
    }

    public void setSerialconsumo(Integer serialconsumo) {
        this.serialconsumo = serialconsumo;
    }

    public Integer getSerialreposicion() {
        return serialreposicion;
    }

    public Integer getSerialconciliacion() {
        return serialconciliacion;
    }

    public void setSerialconciliacion(Integer serialconciliacion) {
        this.serialconciliacion = serialconciliacion;
    }

    public void setSerialreposicion(Integer serialreposicion) {
        this.serialreposicion = serialreposicion;
    }

    public Integer getSerialasiento() {
        return serialasiento;
    }

    public void setSerialasiento(Integer serialasiento) {
        this.serialasiento = serialasiento;
    }

    public Integer getCtaxpagarproved() {
        return ctaxpagarproved;
    }

    public void setCtaxpagarproved(Integer ctaxpagarproved) {
        this.ctaxpagarproved = ctaxpagarproved;
    }

    public Integer getCredfiscal() {
        return credfiscal;
    }

    public void setCredfiscal(Integer credfiscal) {
        this.credfiscal = credfiscal;
    }

    public Integer getRetivacliente() {
        return retivacliente;
    }

    public void setRetivacliente(Integer retivacliente) {
        this.retivacliente = retivacliente;
    }

    public Integer getRetislrcliente() {
        return retislrcliente;
    }

    public void setRetislrcliente(Integer retislrcliente) {
        this.retislrcliente = retislrcliente;
    }

    public Integer getCtasxcobrar() {
        return ctasxcobrar;
    }

    public void setCtasxcobrar(Integer ctasxcobrar) {
        this.ctasxcobrar = ctasxcobrar;
    }

    public Integer getCtagastoprovisional() {
        return ctagastoprovisional;
    }

    public void setCtagastoprovisional(Integer ctagastoprovisional) {
        this.ctagastoprovisional = ctagastoprovisional;
    }

    public Integer getDebfiscal() {
        return debfiscal;
    }

    public void setDebfiscal(Integer debfiscal) {
        this.debfiscal = debfiscal;
    }

    public Integer getRetislrxenterar() {
        return retislrxenterar;
    }

    public void setRetislrxenterar(Integer retislrxenterar) {
        this.retislrxenterar = retislrxenterar;
    }

    public Integer getRetivaxenterar() {
        return retivaxenterar;
    }

    public void setRetivaxenterar(Integer retivaxenterar) {
        this.retivaxenterar = retivaxenterar;
    }

    public Integer getCtaxpagarinterna() {
        return ctaxpagarinterna;
    }

    public void setCtaxpagarinterna(Integer ctaxpagarinterna) {
        this.ctaxpagarinterna = ctaxpagarinterna;
    }

    public Integer getSerialrequerimiento() {
        return serialrequerimiento;
    }

    public void setSerialrequerimiento(Integer serialrequerimiento) {
        this.serialrequerimiento = serialrequerimiento;
    }

    public Integer getSerialcompra() {
        return serialcompra;
    }

    public void setSerialcompra(Integer serialcompra) {
        this.serialcompra = serialcompra;
    }

    public Integer getSerialpagocompra() {
        return serialpagocompra;
    }

    public void setSerialpagocompra(Integer serialpagocompra) {
        this.serialpagocompra = serialpagocompra;
    }

    public Integer getSerialcomprobanteiva() {
        return serialcomprobanteiva;
    }

    public void setSerialcomprobanteiva(Integer serialcomprobanteiva) {
        this.serialcomprobanteiva = serialcomprobanteiva;
    }

    public Integer getSerialcomprobanteislr() {
        return serialcomprobanteislr;
    }

    public void setSerialcomprobanteislr(Integer serialcomprobanteislr) {
        this.serialcomprobanteislr = serialcomprobanteislr;
    }

    public Integer getSerialfactura() {
        return serialfactura;
    }

    public void setSerialfactura(Integer serialfactura) {
        this.serialfactura = serialfactura;
    }
    
    @XmlTransient
    public Collection<Departamento> getDepartamentoCollection() {
        return departamentoCollection;
    }

    public void setDepartamentoCollection(Collection<Departamento> departamentoCollection) {
        this.departamentoCollection = departamentoCollection;
    }
    
    @XmlTransient
    public Collection<Cuentabancaria> getCuentabacnariaCollection() {
        return cuentabancariaCollection;
    }

    public void setCuentabancariaCollection(Collection<Cuentabancaria> cuentabancariaCollection) {
        this.cuentabancariaCollection = cuentabancariaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rif != null ? rif.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.rif == null && other.rif != null) || (this.rif != null && !this.rif.equals(other.rif))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return razonsocial;
    }

    @XmlTransient
    public Collection<Centrodecosto> getCentrodecostoCollection() {
        return centrodecostoCollection;
    }

    public void setCentrodecostoCollection(Collection<Centrodecosto> centrodecostoCollection) {
        this.centrodecostoCollection = centrodecostoCollection;
    }

    @XmlTransient
    public Collection<Cajachica> getCajachicaCollection() {
        return cajachicaCollection;
    }

    public void setCajachicaCollection(Collection<Cajachica> cajachicaCollection) {
        this.cajachicaCollection = cajachicaCollection;
    }

}
