/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jsf;

import Jpa.BancoFacadeLocal;
import Jpa.CobroventaFacadeLocal;
import Jpa.CuentabancariaFacadeLocal;
import Jpa.DetallefacturaFacadeLocal;
import Jpa.EstatuscontableFacadeLocal;
import Jpa.EstatusfacturaventaFacadeLocal;
import Jpa.FacturaFacadeLocal;
import Jpa.TipopagoFacadeLocal;
import Modelo.Banco;
import Modelo.Cobroventa;
import Modelo.Cuentabancaria;
import Modelo.Detallefactura;
import Modelo.Estatuscontable;
import Modelo.Estatusfacturaventa;
import Modelo.Factura;
import Modelo.Tipopago;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author sofimar
 */
@ManagedBean(name = "cobroventasController")
@SessionScoped

public class CobroventasController implements Serializable {

    @EJB
    private DetallefacturaFacadeLocal detallefacturaEJB;
    @EJB
    private DetallefacturaFacadeLocal detallesenfacturaEJB;
    @EJB
    private CuentabancariaFacadeLocal cuentabancariaEJB;
    @EJB
    private TipopagoFacadeLocal tipopagoEJB;
    @EJB
    private CobroventaFacadeLocal cobroventaEJB;
    @EJB
    private EstatusfacturaventaFacadeLocal estatusfacturaventaEJB;
    @EJB
    private FacturaFacadeLocal facturaEJB;
    @EJB
    private BancoFacadeLocal bancoEJB;
    @EJB
    private EstatuscontableFacadeLocal estatuscontableEJB;

    private Factura factura;
    private int numeroFact = 0;
    private Cobroventa cobro;
    private Detallefactura detallefact;
    private Estatusfacturaventa statusfactu = null;
    private Estatuscontable estatuscontab = null;
    private int formacobro=0;
    private Banco banco;
    private int idbanco;
    private List<Detallefactura> detallesfactura;
    private List<Detallefactura> detallesfacturafiltrados;
    private List<Cuentabancaria> cuentasbancarias;
    private List<Tipopago> tipopagos;
    private List<Cobroventa> cobrosefectuados;
    private List<Cuentabancaria> lstCuentasSelecc;
    private List<Banco> bancos;

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public List<Tipopago> getTipopagos() {
        return tipopagos;
    }

    public void setTipopagos(List<Tipopago> tipopagos) {
        this.tipopagos = tipopagos;
    }

    public List<Cobroventa> getCobrosefectuados() {
        return cobrosefectuados;
    }

    public void setCobrosefectuados(List<Cobroventa> cobrosefectuados) {
        this.cobrosefectuados = cobrosefectuados;
    }

    public List<Detallefactura> getDetallesfacturafiltrados() {
        return detallesfacturafiltrados;
    }

    public void setDetallesfacturafiltrados(List<Detallefactura> detallesfacturafiltrados) {
        this.detallesfacturafiltrados = detallesfacturafiltrados;
    }

    public Cobroventa getCobro() {
        return cobro;
    }

    public void setCobro(Cobroventa cobro) {
        this.cobro = cobro;
    }

    public List<Cuentabancaria> getLstCuentasSelecc() {
        return lstCuentasSelecc;
    }

    public void setLstCuentasSelecc(List<Cuentabancaria> lstCuentasSelecc) {
        this.lstCuentasSelecc = lstCuentasSelecc;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public int getIdbanco() {
        return idbanco;
    }

    public void setIdbanco(int idbanco) {
        this.idbanco = idbanco;
    }

    public List<Banco> getBancos() {
        return bancos;
    }

    public void setBancos(List<Banco> bancos) {
        this.bancos = bancos;
    }

    public int getFormacobro() {
        return formacobro;
    }

    public void setFormacobro(int formacobro) {
        this.formacobro = formacobro;
    }

    @PostConstruct
    public void init() {
        detallesfactura = detallefacturaEJB.findAll();
        cuentasbancarias = cuentabancariaEJB.findAll();
        tipopagos = tipopagoEJB.findAll();
        cobrosefectuados = cobroventaEJB.findAll();
        bancos = bancoEJB.findAll();
        cobro = new Cobroventa();
    }

    public List<Cuentabancaria> refrescarCuentasBancarias() {
        try {
            lstCuentasSelecc = cuentabancariaEJB.espxBanco(banco.getIdbanco());
        } catch (Exception e) {
        }
        return lstCuentasSelecc;
    }

    public void asignar(Factura factura) {
        this.factura = factura;
        this.numeroFact = factura.getNumerofact();
        detallesfacturafiltrados = detallesenfacturaEJB.buscardetallefactura(factura);
    }

    public List<Detallefactura> detallefacturaAuxiliar() {
        List<Detallefactura> listado = null;
        listado = detallesenfacturaEJB.buscardetallefactura(factura);
        return listado;
    }

    public void registrar() {
        try {
            estatuscontab = estatuscontableEJB.estatusContablePorRegistrar();
            if (formacobro == 1) {
                double saldo =0;
                factura.setSaldopendiente(saldo);
                int tipo = 1;
                statusfactu = estatusfacturaventaEJB.estatusfacturaPagada(tipo);        
            }else{
                int tipo =3;
                double saldop =0;
                saldop = factura.getTotalgeneral()-cobro.getMontocobrado();
                factura.setSaldopendiente(saldop);
                statusfactu = estatusfacturaventaEJB.estatusfacturaAbonada(tipo);
            }
            factura.setIdestatusfacturaventa(statusfactu);            
            facturaEJB.edit(factura);
            //codCompra = compraEJB.ultimacompraInsertada();

            cobro.setNumerofact(factura);
            cobro.setIdestatuscontable(estatuscontab);
            cobroventaEJB.create(cobro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Su Pago fue Almacenado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al Grabar Pago"));
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }
}
