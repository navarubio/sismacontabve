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
import Jpa.MaestromovimientoFacadeLocal;
import Jpa.Numeroaletras;
import Jpa.TipoconjuntoFacadeLocal;
import Modelo.Articulo;
import Modelo.Caja;
import Modelo.Cliente;
import Modelo.Detallefactura;
import Modelo.Estatuscontable;
import Modelo.Factura;
import Modelo.Maestromovimiento;
import Modelo.Requerimiento;
import Modelo.Tipoconjunto;
import Modelo.Usuario;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
import javax.servlet.ServletContext;

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
    @EJB
    private MaestromovimientoFacadeLocal maestromovimientoEJB;
    @EJB
    private TipoconjuntoFacadeLocal tipoconjuntoEJB;

    private Detallefactura detallefactura;
    private RequerimientosController reque = new RequerimientosController();

    private List<Factura> facturas;
    private List<Detallefactura> detallesfacturas;
    private List<Cliente> clientes;
    private List<Articulo> articulos;
    private Factura codfactura;
    private int number;
    private Date fechaactual = new Date();
    SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
    DecimalFormat formatearnumero = new DecimalFormat("###,###.##");
    private String correo;
    private envioCorreo enviomail;
    private Tipoconjunto tipoconjunto = null;

    @Inject
    private Factura factura;
    @Inject
    private Cliente cliente;
    @Inject
    private Detallefactura detalle;
    @Inject
    private Maestromovimiento maestromovi;

    Numeroaletras numletras = new Numeroaletras();

    String numero = "";
    String cantidadenletras = "";

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
        factura.setFecha(fechaactual);
    }

    public void registrarventa() {
        Articulo art = new Articulo();
        Date fecha = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String fechaCadena = hourFormat.format(fecha);
        DecimalFormat numformat = new DecimalFormat("#######.##");
        try {
            Usuario usua = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            factura.setIdusuario(usua);
            factura.setRifcliente(cliente);
            factura.setBimponiblefact(reque.getTotalsubtotal());
            factura.setIvafact(reque.getTotaliva());
            factura.setTotalgeneral(reque.getTotalgeneral());
            numero = numformat.format(factura.getTotalgeneral());
            cantidadenletras = numletras.Convertir(numero, true);
            factura.setCantidadenletras(cantidadenletras);
            factura.setSaldopendiente(reque.getTotalgeneral());
            factura.setHora(fechaCadena);
            factura.setIdcaja(cajaEJB.ubicarCaja());
            factura.setIdestatuscontable(estatuscontableEJB.estatusContablePorRegistrar());
            factura.setIdestatusfacturaventa(estatusfacturaventaEJB.estatusFacturaPorCobrar());
            facturaEJB.create(factura);

            codfactura = facturaEJB.ultimaInsertada();
            number = codfactura.getNumerofact();
            
            int tipoconj = 1;
            tipoconjunto = tipoconjuntoEJB.cambiartipoConjunto(tipoconj);
            maestromovi.setNumerofact(codfactura);
            maestromovi.setFechamovimiento(factura.getFecha());
            maestromovi.setIdtipoconjunto(tipoconjunto);
            maestromovi.setIdestatuscontable(estatuscontableEJB.estatusContablePorRegistrar());
            maestromovimientoEJB.create(maestromovi);

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
            String subject;
            String ultimafactura = facturaEJB.ultimafacturaformat();
            String fechafactu = formateador.format(factura.getFecha());
            correo = "FACTURA NRO: " + ultimafactura
                    + "  CONTROL: " + factura.getNumerocontrol()
                    + "  USUARIO: " + factura.getIdusuario().getNombre()
                    + "  DEPARTAMENTO: " + factura.getIdusuario().getIddepartamento().getDepartamento()
                    + "  FECHA: " + fechafactu
                    + "  CLIENTE: " + factura.getRifcliente().getRazonsocial()
                    + "  RIF: " + factura.getRifcliente().getRifcliente()
                    + "  SUBTOTAL: " + formatearnumero.format(factura.getBimponiblefact())
                    + "  IVA: " + formatearnumero.format(factura.getIvafact())
                    + "  TOTAL: " + formatearnumero.format(factura.getTotalgeneral())
                    + "  OBSERVACIONES: " + factura.getObservacionesfact();

            subject = "Emisión de Factura N° " + ultimafactura;
            enviomail = new envioCorreo(correo, subject);
            enviomail.start();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "La Factura se registro exitosamente con el numero " + facturaEJB.ultimafacturaformat()));
            reque.limpiarListaArreglo();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al Grabar esta Factura"));
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }

    public String devolversiguientefactura() {
        String siguiente;
        siguiente = facturaEJB.siguientefacturaformat();
        return siguiente;
    }

}
