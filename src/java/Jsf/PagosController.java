/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jsf;

import Jpa.AuxiliarrequerimientoFacadeLocal;
import Jpa.BancoFacadeLocal;
import Jpa.CompraFacadeLocal;
import Jpa.CuentabancariaFacadeLocal;
import Jpa.DepartamentoFacadeLocal;
import Jpa.DetallecompraFacadeLocal;
import Jpa.EstatuscontableFacadeLocal;
import Jpa.EstatusfacturaFacadeLocal;
import Jpa.PagocompraFacadeLocal;
import Jpa.RequerimientoFacadeLocal;
import Jpa.TipopagoFacadeLocal;
import Modelo.Auxiliarrequerimiento;
import Modelo.Banco;
import Modelo.Compra;
import Modelo.Cuentabancaria;
import Modelo.Departamento;
import Modelo.Detallecompra;
import Modelo.Estatuscontable;
import Modelo.Estatusfactura;
import Modelo.Pagocompra;
import Modelo.Requerimiento;
import Modelo.Tipopago;
import Modelo.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Inpeca
 */
@Named
@SessionScoped

public class PagosController implements Serializable {

    @EJB
    private CuentabancariaFacadeLocal cuentabancariaEJB;
    @EJB
    private PagocompraFacadeLocal pagocompraEJB;
    @EJB
    private EstatusfacturaFacadeLocal estatusfacturaEJB;
    @EJB
    private EstatuscontableFacadeLocal estatuscontableEJB;
    
    @EJB
    private AuxiliarrequerimientoFacadeLocal auxiliarrequerimientoEJB;
    @EJB
    private CompraFacadeLocal compraEJB;
    @EJB
    private DetallecompraFacadeLocal detallecompraEJB;
    @EJB
    private DepartamentoFacadeLocal departamentoEJB;
    @EJB
    private TipopagoFacadeLocal tipopagoEJB;
    @EJB
    private BancoFacadeLocal bancoEJB;

    private Auxiliarrequerimiento auxiliarrequerimiento;
    private Compra compra;
    private Detallecompra detallecompras;
    private Pagocompra pagocompra = new Pagocompra();
    private Pagocompra pagocompraver ;
    private Estatusfactura statusfactu = null;
    private int formapago =0;
    private Estatuscontable estatuscontab=null;
    private Banco banco;
    private Pagocompra pago;
    private Usuario usa;
    private Departamento dpto;
    private Compra codCompra;
    private int idAuxiliar = 0;
    private int idCompra = 0;
    private List<Auxiliarrequerimiento> auxiliarrequerimientos;
    private List<Cuentabancaria> cuentasbancarias;
    private List<Tipopago> tipopagos;
    private List<Detallecompra> detallecompraFiltrados;
    private List<Banco> bancos;
    private List<Cuentabancaria> lstCuentasSelecc;
    private List<Pagocompra> pagosefectuados;
    private List <Pagocompra> pagoespecifico;
    private String mensaje;
    private Date fechaactual = new Date();
    

    @Inject
    private Auxiliarrequerimiento auxiliar;
    @Inject
    private Requerimiento requerimiento;
    @Inject
    private Compra compras;
    @Inject
    private Detallecompra detallecompra;
    @Inject
    private Tipopago tipopago;
    @Inject
    private Cuentabancaria cuentabancaria;

    public List<Cuentabancaria> getLstCuentasSelecc() {
        return lstCuentasSelecc;
    }

    public void setLstCuentasSelecc(List<Cuentabancaria> lstCuentasSelecc) {
        this.lstCuentasSelecc = lstCuentasSelecc;
    }

    public Pagocompra getPagocompra() {
        return pagocompra;
    }

    public int getFormapago() {
        return formapago;
    }

    public void setFormapago(int formapago) {
        this.formapago = formapago;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public void setPagocompra(Pagocompra pagocompra) {
        this.pagocompra = pagocompra;
    }

    public Pagocompra getPagocompraver() {
        return pagocompraver;
    }

    public void setPagocompraver(Pagocompra pagocompraver) {
        this.pagocompraver = pagocompraver;
    }

    public Date getFechaactual() {
        return fechaactual;
    }

    public void setFechaactual(Date fechaactual) {
        this.fechaactual = fechaactual;
    }

    public List<Banco> getBancos() {
        return bancos;
    }

    public void setBancos(List<Banco> bancos) {
        this.bancos = bancos;
    }

    public Tipopago getTipopago() {
        return tipopago;
    }

    public void setTipopago(Tipopago tipopago) {
        this.tipopago = tipopago;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Pagocompra getPago() {
        return pago;
    }

    public void setPago(Pagocompra pago) {
        this.pago = pago;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<Detallecompra> getDetallecompraFiltrados() {
        return detallecompraFiltrados;
    }

    public void setDetallecompraFiltrados(List<Detallecompra> detallecompraFiltrados) {
        this.detallecompraFiltrados = detallecompraFiltrados;
    }

    public Auxiliarrequerimiento getAuxiliarrequerimiento() {
        return auxiliarrequerimiento;
    }

    public void setAuxiliarrequerimiento(Auxiliarrequerimiento auxiliarrequerimiento) {
        this.auxiliarrequerimiento = auxiliarrequerimiento;
    }

    public int getIdAuxiliar() {
        return idAuxiliar;
    }

    public void setIdAuxiliar(int idAuxiliar) {
        this.idAuxiliar = idAuxiliar;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Detallecompra getDetallecompra() {
        return detallecompra;
    }

    public void setDetallecompra(Detallecompra detallecompra) {
        this.detallecompra = detallecompra;
    }

    public List<Tipopago> getTipopagos() {
        return tipopagos;
    }

    public void setTipopagos(List<Tipopago> tipopagos) {
        this.tipopagos = tipopagos;
    }

    public List<Pagocompra> getPagosefectuados() {
        return pagosefectuados;
    }

    public void setPagosefectuados(List<Pagocompra> pagosefectuados) {
        this.pagosefectuados = pagosefectuados;
    }

    public List<Pagocompra> getPagoespecifico() {
        return pagoespecifico;
    }

    public void setPagoespecifico(List<Pagocompra> pagoespecifico) {
        this.pagoespecifico = pagoespecifico;
    }

    @PostConstruct
    public void init() {
        auxiliarrequerimientos = auxiliarrequerimientoEJB.findAll();
        cuentasbancarias = cuentabancariaEJB.findAll();
        tipopagos = tipopagoEJB.findAll();
        bancos = bancoEJB.findAll();
        pagosefectuados = pagocompraEJB.findAll();
        pago = new Pagocompra();
        //articulos = articuloEJB.findAll();
        //comprasporautorizar=compraEJB.buscarcomprasporAutorizar();

//        this.auxiliarrequerimiento=requerimientosController.getAuxrequer();
    }

    public void asignar(Compra compr) {
        this.compra = compr;
        this.idCompra = compr.getIdcompra();
        this.auxiliarrequerimiento = compr.getIdauxiliarrequerimiento();
//        this.auxiliar = aux;
        detallecompraFiltrados = detallecompraAuxiliar();
//        this.compra.setIdauxiliarrequerimiento(auxiliar);
    }

    public void asignarCompra(Compra compr) {
        this.compra = compr;
        this.idCompra = compr.getIdcompra();
        this.pagoespecifico = pagocompraEJB.buscarpago(compr);
        this.pagocompra=pagocompraEJB.buscarpagototal(compr);
        this.auxiliarrequerimiento = compr.getIdauxiliarrequerimiento();
//        this.auxiliar = aux;
        detallecompraFiltrados = detallecompraAuxiliar();
//        this.compra.setIdauxiliarrequerimiento(auxiliar);
    }

    public List<Detallecompra> detallecompraAuxiliar() {
        List<Detallecompra> listado = null;
        listado = detallecompraEJB.buscardetallecompra(compra);
        return listado;
    }

    public List<Requerimiento> requerimientosAuxiliar() {
        List<Requerimiento> listado = null;
        //**** listado = requerimientoEJB.requerimientosAuxiliar(idAuxiliar);
        return listado;
    }

    public Usuario getUsuario() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        usa = us;
        return us;
    }

    public Departamento buscarDepartamento() {
        Usuario usua = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        dpto = departamentoEJB.buscarDepartamento(usua);
//        statusreq.setIdestatusrequerimiento(statu);
        return dpto;
    }

    public List<Pagocompra> buscarTodoslosPagos() {
        pagosefectuados = pagocompraEJB.findAll();
        return pagosefectuados;
    }

    public List<Cuentabancaria> refrescarCuentasBancarias() {
        try {
            lstCuentasSelecc = cuentabancariaEJB.espxBanco(banco.getIdbanco());
        } catch (Exception e) {
        }
        pagocompra.setIdcuentabancaria(lstCuentasSelecc.get(0));
        return lstCuentasSelecc;
    }

    public void registrar() {
        try {
            /**
             * compra.setRifproveedor(provee);
             * compra.setSubtotal(auxiliar.getSubtotal());
             * compra.setIva(auxiliar.getMontoiva());
             * compra.setTotal(auxiliar.getMontototal()); Usuario us = (Usuario)
             * FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
             * compra.setIdusuario(us);*
             */
            estatuscontab = estatuscontableEJB.estatusContablePorRegistrar();
            if (formapago==1){
                
            }
            int tipo = 3;
            statusfactu = estatusfacturaEJB.cambiarestatusFactura(tipo);
            compra.setIdestatusfactura(statusfactu);
            compraEJB.edit(compra);
            //codCompra = compraEJB.ultimacompraInsertada();

            pagocompra.setIdcompra(compra);
            pagocompra.setTotalpago(compra.getTotal());
            pagocompraEJB.create(pagocompra);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Su Pago fue Almacenado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al Grabar Pago"));
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }
    
     public void seleccionpagofraccionado() {
        if (mensaje.equals("total")) {
            formapago = 1;
        } else if (mensaje.equals("parcial")) {
            formapago = 2;
        } else {
            formapago = 0;
        }

    }
    public Date fechaactual(){
        Date fecha = new Date();
        return fecha;
    } 
}
