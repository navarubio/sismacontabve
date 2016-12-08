/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jsf;

import Jpa.ArticuloFacadeLocal;
import Jpa.CajaFacadeLocal;
import Jpa.ClienteFacadeLocal;
import Jpa.DetallefacturaFacadeLocal;
import Jpa.EstatuscontableFacade;
import Jpa.EstatuscontableFacadeLocal;
import Jpa.EstatusfacturaventaFacadeLocal;
import Jpa.FacturaFacadeLocal;
import Modelo.Articulo;
import Modelo.Caja;
import Modelo.Cliente;
import Modelo.Detallefactura;
import Modelo.Estatuscontable;
import Modelo.Factura;
import Modelo.Requerimiento;
import Modelo.Usuario;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 * @author sofimar
 */
@ManagedBean(name = "facturasController")
@ViewScoped

public class FacturasController implements Serializable {

    @EJB
    private FacturaFacadeLocal facturaEJB;
    @EJB
    private DetallefacturaFacadeLocal detallefacturaEJB;
    @EJB
    private ClienteFacadeLocal clienteEJB;
    @EJB
    private ArticuloFacadeLocal articuloEJB;
    @EJB
    private CajaFacadeLocal cajaEJB;
    @EJB
    private EstatuscontableFacadeLocal estatuscontableEJB;
    @EJB
    private EstatusfacturaventaFacadeLocal estatusfacturaventaEJB;

    private Detallefactura detallefactura;
    private RequerimientosController reque = new RequerimientosController();

    private List<Factura> facturas;
    private List<Detallefactura> detallesfacturas;
    private List<Cliente> clientes;
    private List<Articulo> articulos;
    private Factura codfactura;

    @Inject
    private Factura factura;
    @Inject
    private Cliente cliente;
    @Inject
    private Detallefactura detalle;

    public Detallefactura getDetallefactura() {
        return detallefactura;
    }

    public void setDetallefactura(Detallefactura detallefactura) {
        this.detallefactura = detallefactura;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public List<Detallefactura> getDetallesfacturas() {
        return detallesfacturas;
    }

    public void setDetallesfacturas(List<Detallefactura> detallesfacturas) {
        this.detallesfacturas = detallesfacturas;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente clientt) {
        this.cliente = clientt;
    }

    public Detallefactura getDetalle() {
        return detalle;
    }

    public void setDetalle(Detallefactura detalle) {
        this.detalle = detalle;
    }

    @PostConstruct
    public void init() {
        clientes = clienteEJB.findAll();
        articulos = articuloEJB.findAll();
    }

    public void registrarventa() {
        Articulo art = new Articulo();
        Date fecha = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String fechaCadena = hourFormat.format(fecha);
        try {
            Usuario usua = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            factura.setIdusuario(usua);
            factura.setRifcliente(cliente);
            factura.setBimponiblefact(reque.getTotalsubtotal());
            factura.setIvafact(reque.getTotaliva());
            factura.setTotalgeneral(reque.getTotalgeneral());
            factura.setSaldopendiente(reque.getTotalgeneral());            
            factura.setHora(fechaCadena);
            factura.setIdcaja(cajaEJB.ubicarCaja());
            factura.setIdestatuscontable(estatuscontableEJB.estatusContablePorRegistrar());
            factura.setIdestatusfacturaventa(estatusfacturaventaEJB.estatusFacturaPorCobrar());
            facturaEJB.create(factura);

            codfactura = facturaEJB.ultimaInsertada();

            for (Requerimiento rq : reque.getListarequerimiento()) {
                Articulo arti = rq.getCodigo();
                detalle.setNumerofact(codfactura);
                detalle.setCodigo(arti);
                detalle.setUnidades(rq.getCantidad());
                detalle.setPrecioventa(rq.getPcosto());
                detalle.setSubtotal(rq.getSubtotal());
                detalle.setTributoiva(rq.getTributoiva());
                detalle.setTotal(rq.getTotal());
                detallefacturaEJB.create(detalle);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Su Requerimiento fue Almacenado"));
            reque.limpiarListaArreglo();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al Grabar Requerimiento"));
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }

    public int devolversiguientefactura() {
        int siguiente;
        siguiente = facturaEJB.siguientefactura();
        return siguiente;
    }
}
