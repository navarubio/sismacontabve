/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sofimar
 */
@Entity
@Table(name = "distribucionusomaquinaria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Distribucionusomaquinaria.findAll", query = "SELECT d FROM Distribucionusomaquinaria d"),
    @NamedQuery(name = "Distribucionusomaquinaria.findByIddistribucionusomaquinaria", query = "SELECT d FROM Distribucionusomaquinaria d WHERE d.iddistribucionusomaquinaria = :iddistribucionusomaquinaria"),
    @NamedQuery(name = "Distribucionusomaquinaria.findByCantidad", query = "SELECT d FROM Distribucionusomaquinaria d WHERE d.cantidad = :cantidad")})
public class Distribucionusomaquinaria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddistribucionusomaquinaria")
    private Integer iddistribucionusomaquinaria;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Double cantidad;
    @JoinColumn(name = "idusomaquinariapicadora", referencedColumnName = "idusomaquinariapicadora")
    @ManyToOne
    private Usomaquinariapicadora idusomaquinariapicadora;
    @JoinColumn(name = "idtipotrabajomaquinaria", referencedColumnName = "idtipotrabajomaquinaria")
    @ManyToOne
    private Tipotrabajomaquinaria idtipotrabajomaquinaria;

    public Distribucionusomaquinaria() {
    }

    public Distribucionusomaquinaria(Integer iddistribucionusomaquinaria) {
        this.iddistribucionusomaquinaria = iddistribucionusomaquinaria;
    }

    public Integer getIddistribucionusomaquinaria() {
        return iddistribucionusomaquinaria;
    }

    public void setIddistribucionusomaquinaria(Integer iddistribucionusomaquinaria) {
        this.iddistribucionusomaquinaria = iddistribucionusomaquinaria;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Usomaquinariapicadora getIdusomaquinariapicadora() {
        return idusomaquinariapicadora;
    }

    public void setIdusomaquinariapicadora(Usomaquinariapicadora idusomaquinariapicadora) {
        this.idusomaquinariapicadora = idusomaquinariapicadora;
    }

    public Tipotrabajomaquinaria getIdtipotrabajomaquinaria() {
        return idtipotrabajomaquinaria;
    }

    public void setIdtipotrabajomaquinaria(Tipotrabajomaquinaria idtipotrabajomaquinaria) {
        this.idtipotrabajomaquinaria = idtipotrabajomaquinaria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddistribucionusomaquinaria != null ? iddistribucionusomaquinaria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Distribucionusomaquinaria)) {
            return false;
        }
        Distribucionusomaquinaria other = (Distribucionusomaquinaria) object;
        if ((this.iddistribucionusomaquinaria == null && other.iddistribucionusomaquinaria != null) || (this.iddistribucionusomaquinaria != null && !this.iddistribucionusomaquinaria.equals(other.iddistribucionusomaquinaria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Distribucionusomaquinaria[ iddistribucionusomaquinaria=" + iddistribucionusomaquinaria + " ]";
    }
    
}
