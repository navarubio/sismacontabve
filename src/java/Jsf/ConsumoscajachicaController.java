package Jsf;

import Jpa.ArticuloFacadeLocal;
import Jpa.AutorizacionFacadeLocal;
import Jpa.AuxiliarrequerimientoFacadeLocal;
import Jpa.CajachicaFacadeLocal;
import Jpa.CompraFacadeLocal;
import Jpa.DepartamentoFacadeLocal;
import Jpa.DetallecompraFacadeLocal;
import Jpa.EmpresaFacadeLocal;
import Jpa.EstatuscontableFacadeLocal;
import Jpa.EstatusfacturaFacadeLocal;
import Jpa.EstatusrequerimientoFacadeLocal;
import Jpa.MaestromovimientoFacadeLocal;
import Jpa.ProveedorFacadeLocal;
import Jpa.RequerimientoFacadeLocal;
import Jpa.TipoconjuntoFacadeLocal;
import Jpa.TipogastocajachicaFacadeLocal;
import Modelo.Articulo;
import Modelo.Autorizacion;
import Modelo.Auxiliarrequerimiento;
import Modelo.Cajachica;
import Modelo.Detallecompra;
import Modelo.Compra;
import Modelo.Consumocajachica;
import Modelo.Departamento;
import Modelo.Detalleconsumocajachica;
import Modelo.Empresa;
import Modelo.Estatusfactura;
import Modelo.Estatusrequerimiento;
import Modelo.Maestromovimiento;
import Modelo.Proveedor;
import Modelo.Requerimiento;
import Modelo.Tipoconjunto;
import Modelo.Tipogastocajachica;
import Modelo.Usuario;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean(name = "consumoscajachicaController")
@SessionScoped

public class ConsumoscajachicaController implements Serializable {

    @EJB
    private AuxiliarrequerimientoFacadeLocal auxiliarrequerimientoEJB;
    @EJB
    private CajachicaFacadeLocal cajachicaEJB;
    @EJB
    private TipogastocajachicaFacadeLocal tipogastocajachicaEJB;
    @EJB
    private ProveedorFacadeLocal proveedorEJB;

    //PARA ELIMINAR AL TERMINAR DE CONFIGURAR EL CONTROLADOR//////////////
    @EJB
    private AutorizacionFacadeLocal autorizacionEJB;
    @EJB
    private RequerimientoFacadeLocal requerimientoEJB;

    @EJB
    private ArticuloFacadeLocal articuloEJB;
    @EJB
    private CompraFacadeLocal compraEJB;
    @EJB
    private DetallecompraFacadeLocal detallecompraEJB;
    @EJB
    private EstatusrequerimientoFacadeLocal estatusrequerimientoEJB;
    @EJB
    private EstatusfacturaFacadeLocal estatusfacturaEJB;
    @EJB
    private MaestromovimientoFacadeLocal maestromovimientoEJB;
    @EJB
    private TipoconjuntoFacadeLocal tipoconjuntoEJB;
    @EJB
    private EstatuscontableFacadeLocal estatuscontableEJB;
    @EJB
    private EmpresaFacadeLocal empresaEJB;

    //PARA ELIMINAR AL TERMINAR DE CONFIGURAR EL CONTROLADOR//////////////
    @Inject
    private Cajachica cajachica;
    @Inject
    private Consumocajachica consumocajachica;
    @Inject
    private Detalleconsumocajachica detalleconsumocajachica;
    @Inject
    private Proveedor provee;
    @Inject
    private Detalleconsumocajachica detalleamodif;

    private java.util.GregorianCalendar fecha1 = new GregorianCalendar();
    private Calendar cal = Calendar.getInstance();
    private Date fechaactual = cal.getTime();
    private String totalgeneralform;
    private String totalivaform;
    private String totalsubtotalform;
    private double totalgeneral = 0;
    private double totaliva = 0;
    private double totalsubtotal = 0;

    DecimalFormat formatearnumero = new DecimalFormat("###,###.##");
    private List<Detalleconsumocajachica> listadetalles = new ArrayList();

    @Inject
    private Auxiliarrequerimiento auxiliar;
    @Inject
    private Requerimiento requerimiento;
    @Inject
    private Compra compra;
    @Inject
    private Detallecompra detallecompra;
    @Inject
    private Autorizacion autorizacion;
    @Inject
    private Maestromovimiento maestromovi;
    @Inject
    private Requerimiento requer;
    @Inject
    private Articulo articulo;
    @Inject
    private Empresa empresa;

    @Inject
    private RequerimientosController requerimientosController;

    private List<Cajachica> cajaschicas;
    private List<Tipogastocajachica> tiposdegastos;

    public List<Cajachica> getCajaschicas() {
        return cajaschicas;
    }

    public void setCajaschicas(List<Cajachica> cajaschicas) {
        this.cajaschicas = cajaschicas;
    }

    public List<Tipogastocajachica> getTiposdegastos() {
        return tiposdegastos;
    }

    public void setTiposdegastos(List<Tipogastocajachica> tiposdegastos) {
        this.tiposdegastos = tiposdegastos;
    }

    public Cajachica getCajachica() {
        return cajachica;
    }

    public void setCajachica(Cajachica cajachica) {
        this.cajachica = cajachica;
    }

    public Consumocajachica getConsumocajachica() {
        return consumocajachica;
    }

    public void setConsumocajachica(Consumocajachica consumocajachica) {
        this.consumocajachica = consumocajachica;
    }

    public Detalleconsumocajachica getDetalleconsumocajachica() {
        return detalleconsumocajachica;
    }

    public void setDetalleconsumocajachica(Detalleconsumocajachica detalleconsumocajachica) {
        this.detalleconsumocajachica = detalleconsumocajachica;
    }

    public List<Detalleconsumocajachica> getListadetalles() {
        return listadetalles;
    }

    public void setListadetalles(List<Detalleconsumocajachica> listadetalles) {
        this.listadetalles = listadetalles;
    }

    public double getTotaliva() {
        return totaliva;
    }

    public void setTotaliva(double totaliva) {
        this.totaliva = totaliva;
    }

    public double getTotalsubtotal() {
        return totalsubtotal;
    }

    public double getTotalgeneral() {
        return totalgeneral;
    }

    public void setTotalgeneral(double totalgeneral) {
        this.totalgeneral = totalgeneral;
    }

    public void setTotalsubtotal(double totalsubtotal) {
        this.totalsubtotal = totalsubtotal;
    }

    public String getTotalgeneralform() {
        return totalgeneralform;
    }

    public void setTotalgeneralform(String totalgeneralform) {
        this.totalgeneralform = totalgeneralform;
    }

    public String getTotalivaform() {
        return totalivaform;
    }

    public void setTotalivaform(String totalivaform) {
        this.totalivaform = totalivaform;
    }

    public String getTotalsubtotalform() {
        return totalsubtotalform;
    }

    public void setTotalsubtotalform(String totalsubtotalform) {
        this.totalsubtotalform = totalsubtotalform;
    }

    public Detalleconsumocajachica getDetalleamodif() {
        return detalleamodif;
    }

    public void setDetalleamodif(Detalleconsumocajachica detalleamodif) {
        this.detalleamodif = detalleamodif;
    }

    private List<Requerimiento> listarequerimiento = new ArrayList();
    private List<Auxiliarrequerimiento> auxiliarrequerimientos;
    private List<Requerimiento> requerimientos;
    private List<Proveedor> proveedores;
    private List<Articulo> articulos;
    private List<Requerimiento> requerimientosFiltrados;
    private List<Detallecompra> detallesCompras;
    private List<Detallecompra> detallesactuales;
    private List<Compra> comprasporautorizar = null;
    private List<Compra> comprasporpagar = null;
    private List<Compra> compraspagadas = null;
    private double pcosto = 0;
    private double pventa = 0;
    private double cantidad = 0;
    private double subtotal = 0;
    private int id = 0;
    private int varAutoriza = 0;
    private int idAuxiliar = 0;
    private Auxiliarrequerimiento auxiliarrequerimiento;
    private Usuario usa;
    private Departamento dpto;
    private Compra codCompra;
    private Autorizacion codAutoriza;
    private Compra compraautorizada;
    private Tipoconjunto tipoconjunto = null;

    public Compra getCompra() {
        return compra;
    }

    public Compra getCompraautorizada() {
        return compraautorizada;
    }

    public void setCompraautorizada(Compra compraautorizada) {
        this.compraautorizada = compraautorizada;
    }

    public Requerimiento getRequer() {
        return requer;
    }

    public void setRequer(Requerimiento requer) {
        this.requer = requer;
    }

    public double getPcosto() {
        return pcosto;
    }

    public void setPcosto(double pcosto) {
        this.pcosto = pcosto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Autorizacion getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(Autorizacion autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Detallecompra getDetallecompra() {
        return detallecompra;
    }

    public void setDetallecompra(Detallecompra detallecompra) {
        this.detallecompra = detallecompra;
    }

    public List<Requerimiento> getListarequerimiento() {
        return listarequerimiento;
    }

    public void setListarequerimiento(List<Requerimiento> listarequerimiento) {
        this.listarequerimiento = listarequerimiento;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public int getIdAuxiliar() {
        return idAuxiliar;
    }

    public void setIdAuxiliar(int idAuxiliar) {
        this.idAuxiliar = idAuxiliar;
    }

    public Proveedor getProvee() {
        return provee;
    }

    public void setProvee(Proveedor provee) {
        this.provee = provee;
    }

    public List<Auxiliarrequerimiento> getAuxiliarrequerimientos() {
        return auxiliarrequerimientos;
    }

    public void setAuxiliarrequerimientos(List<Auxiliarrequerimiento> auxiliarrequerimientos) {
        this.auxiliarrequerimientos = auxiliarrequerimientos;
    }

    public List<Requerimiento> getRequerimientos() {
        return requerimientos;
    }

    public void setRequerimientos(List<Requerimiento> requerimientos) {
        this.requerimientos = requerimientos;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    public Auxiliarrequerimiento getAuxiliarrequerimiento() {
        return auxiliarrequerimiento;
    }

    public void setAuxiliarrequerimiento(Auxiliarrequerimiento auxiliarrequerimiento) {
        this.auxiliarrequerimiento = auxiliarrequerimiento;
    }

    public Requerimiento getRequerimiento() {
        return requerimiento;
    }

    public void setRequerimiento(Requerimiento requerimiento) {
        this.requerimiento = requerimiento;
    }

    public List<Requerimiento> getRequerimientosFiltrados() {
        return requerimientosFiltrados;
    }

    public void setRequerimientosFiltrados(List<Requerimiento> requerimientosFiltrados) {
        this.requerimientosFiltrados = requerimientosFiltrados;
    }

    public List<Detallecompra> getDetallesCompras() {
        return detallesCompras;
    }

    public void setDetallesCompras(List<Detallecompra> detallesCompras) {
        this.detallesCompras = detallesCompras;
    }

    public List<Compra> getComprasporautorizar() {
        return comprasporautorizar;
    }

    public void setComprasporautorizar(List<Compra> comprasporautorizar) {
        this.comprasporautorizar = comprasporautorizar;
    }

    public List<Compra> getComprasporpagar() {
        return comprasporpagar;
    }

    public void setComprasporpagar(List<Compra> comprasporpagar) {
        this.comprasporpagar = comprasporpagar;
    }

    public List<Compra> getCompraspagadas() {
        return compraspagadas;
    }

    public void setCompraspagadas(List<Compra> compraspagadas) {
        this.compraspagadas = compraspagadas;
    }

    public RequerimientosController getRequerimientosController() {
        return requerimientosController;
    }

    public void setRequerimientosController(RequerimientosController requerimientosController) {
        this.requerimientosController = requerimientosController;
    }

    @PostConstruct
    public void init() {
        cajaschicas = cajachicaEJB.findAll();
        consumocajachica.setFechaloteconsumo(fechaactual);
        tiposdegastos = tipogastocajachicaEJB.findAll();

        compra.setFechaorden(fechaactual);
        auxiliarrequerimientos = auxiliarrequerimientoEJB.findAll();
        requerimientos = requerimientoEJB.findAll();
        proveedores = proveedorEJB.findAll();
        articulos = articuloEJB.findAll();

        varAutoriza = 0;
        listarequerimiento.clear();
        empresa = empresaEJB.devolverEmpresabase();

//        this.auxiliarrequerimiento=requerimientosController.getAuxrequer();
    }

    public List<Proveedor> listarproveedores() {
        List<Proveedor> lista = null;
        lista = proveedorEJB.findAll();
        return lista;
    }

    public List<Cajachica> listarcajaschicas() {
        List<Cajachica> lista = null;
        lista = cajachicaEJB.findAll();
        return lista;
    }

    public List<Requerimiento> buscarrequerimiento() {
        List<Requerimiento> listado = null;
        listado = requerimientoEJB.buscarrequerimientos(auxiliarrequerimiento);
        return listado;
    }

    /*    public List<Detallecompra> buscardetallecompra() {
     List<Detallecompra> listado = null;
     listado = detallecompraEJB.buscardetalle(compra);
     return listado;
     }*/
    public List<Requerimiento> requerimientosAuxiliar() {
        List<Requerimiento> listado = null;
        listado = requerimientoEJB.requerimientosAuxiliar(idAuxiliar);
        return listado;
    }

    public void asignarProveedor(Proveedor proveed) {
        provee = proveed;
    }

    public void modificar() {
        double subtotal = 0;
        double alicuota = 0;
        double iva = 0;
        double montotgeneral = 0;
        double montotiva = 0;
        double montotsubtotal = 0;
        double total = 0;
        List<Requerimiento> requerimientosactulizado;
        subtotal = requerimiento.getCantidad() * requerimiento.getPcosto();
        alicuota = requerimiento.getCodigo().getIdgravamen().getAlicuota();
        iva = (subtotal * alicuota) / 100;
        total = subtotal + iva;
        requerimiento.setSubtotal(subtotal);
        requerimiento.setTributoiva(iva);
        requerimiento.setTotal(total);

        requerimientoEJB.edit(requerimiento);

        requerimientosactulizado = buscarrequerimiento();
        for (Requerimiento requeri : requerimientosactulizado) {
            montotgeneral += requeri.getTotal();
            montotiva += requeri.getTributoiva();
            montotsubtotal += requeri.getSubtotal();
        }

        auxiliarrequerimiento.setSubtotal(montotsubtotal);
        auxiliarrequerimiento.setMontoiva(montotiva);
        auxiliarrequerimiento.setMontototal(montotgeneral);

        auxiliarrequerimientoEJB.edit(auxiliarrequerimiento);
        totaltotal();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Su Requerimiento fue Modificado"));
    }

    public void autorizar() {
        Usuario us = requerimientosController.getUsa();
        Date fecha = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        Date fechafinal = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String fechaFormateada = sdf.format(fecha);
        try {
            fechafinal = sdf.parse(fechaFormateada);
        } catch (ParseException pe) {
        }
        String fechaCadena = hourFormat.format(fecha);
        autorizacion.setIdusuario(us);
        autorizacion.setHora(fechaCadena);
        autorizacion.setIdcompra(compraautorizada);
        autorizacion.setFechaautorizacion(fechafinal);
        autorizacionEJB.create(autorizacion);
        autorizacion.setObservaciones(null);
        Estatusfactura statusfactu = null;
        codAutoriza = autorizacionEJB.ultimaautorizacionInsertada();
        int tipo = 2;
        statusfactu = estatusfacturaEJB.cambiarestatusFactura(tipo);
        compraautorizada.setIdestatusfactura(statusfactu);
        compraEJB.edit(compraautorizada);

        int tipoconj = 2;
        tipoconjunto = tipoconjuntoEJB.cambiartipoConjunto(tipoconj);
        maestromovi.setIdautorizacion(codAutoriza);
        maestromovi.setFechamovimiento(autorizacion.getFechaautorizacion());
        maestromovi.setIdtipoconjunto(tipoconjunto);
        maestromovi.setIdestatuscontable(estatuscontableEJB.estatusContablePorRegistrar());
        maestromovi.setIdcompra(compraautorizada);
        maestromovimientoEJB.create(maestromovi);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Compra Autorizada por Gerencia"));
    }

    public void asignarRequerimiento(Requerimiento requeri) {
        requerimiento = requeri;
    }

    public void asignarDetalle(Detalleconsumocajachica detalleaeditar) {
        detalleamodif = detalleaeditar;
    }

    public void asignarCompra(Compra compraselec) {
        compra = compraselec;
    }

    public void asignarCompraAutorizada(Compra compraselec) {
        this.idAuxiliar = compraselec.getIdauxiliarrequerimiento().getIdauxiliarrequerimiento();
        this.auxiliar = compraselec.getIdauxiliarrequerimiento();
        auxiliarrequerimiento = auxiliar;
        requerimientosFiltrados = requerimientosAuxiliar();
        listarequerimiento = requerimientosFiltrados;
        compraautorizada = compraselec;
        totaltotal();
    }

    public List<Requerimiento> solicitarRequerimientosFiltro() {
        return requerimientosFiltrados;
    }

    public void actualizarRequerimiento() {
        double subtotal = 0;
        double alicuota = 0;
        double iva = 0;
        double total = 0;
        subtotal = requerimiento.getCantidad() * requerimiento.getPcosto();
        alicuota = requerimiento.getCodigo().getIdgravamen().getAlicuota();
        iva = subtotal * alicuota;
        total = subtotal + iva;
        requerimiento.setSubtotal(subtotal);
        requerimiento.setTributoiva(iva);
        requerimiento.setTotal(total);
    }

    public void registrar() {
        try {
            compra.setRifproveedor(provee);
            compra.setSubtotal(auxiliar.getSubtotal());
            compra.setIva(auxiliar.getMontoiva());
            compra.setTotal(auxiliar.getMontototal());
            compra.setMontopendiente(auxiliar.getMontototal());
            Usuario us = requerimientosController.getUsa();
            compra.setIdusuario(us);
            Estatusfactura statusfactu = null;
            int tipo = 0;
            //Para fijar monto minimo de de aprobacion para compras directas
            // ESTO DEBE PASARSE A REGLAS DE NEGOCIO EN DROOLS

            if (compra.getTotal() <= empresa.getMontoparaautorizacion()) {
                tipo = 0;
            } else if (compra.getTotal() > empresa.getMontoparaautorizacion()) {
                tipo = 1;
            }
            statusfactu = estatusfacturaEJB.cambiarestatusFactura(tipo);
            compra.setIdestatusfactura(statusfactu);
            compraEJB.create(compra);

            Estatusrequerimiento statusreque = null;
            statusreque = estatusrequerimientoEJB.cambiarestatusaProcesado();
            auxiliarrequerimiento.setIdestatusrequerimiento(statusreque);
            auxiliarrequerimientoEJB.edit(auxiliarrequerimiento);
            codCompra = compraEJB.ultimacompraInsertada();

            if (tipo == 0) {
                int tipoconj = 2;
                tipoconjunto = tipoconjuntoEJB.cambiartipoConjunto(tipoconj);
                maestromovi.setIdcompra(codCompra);
                maestromovi.setFechamovimiento(compra.getFechaorden());
                maestromovi.setIdtipoconjunto(tipoconjunto);
                maestromovi.setIdestatuscontable(estatuscontableEJB.estatusContablePorRegistrar());
                maestromovimientoEJB.create(maestromovi);
            }

            int numerocompra = codCompra.getIdcompra();
            for (Requerimiento rq : listarequerimiento) {
                Articulo arti = rq.getCodigo();
                detallecompra.setIdcompra(codCompra);
                detallecompra.setCodigo(arti);
                detallecompra.setCantidad(rq.getCantidad());
                detallecompra.setPcosto(rq.getPcosto());
                detallecompra.setSubtotal(rq.getSubtotal());
                detallecompra.setTributoiva(rq.getTributoiva());
                detallecompra.setTotalapagar(rq.getTotal());
                detallecompraEJB.create(detallecompra);
            }
            if (tipo == 1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Su Requerimiento fue enviado para Autorizacion con el Nro " + numerocompra));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Su Requerimiento fue Almacenado con el Nro " + numerocompra));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al Grabar Requerimiento"));
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }

    public List<Requerimiento> buscarRequerimiento(Auxiliarrequerimiento auxi) {
        requerimientosFiltrados = requerimientoEJB.buscarrequerimientos(auxiliar);
        return requerimientosFiltrados;
    }

    public List<Requerimiento> requerimientosAuxiliar(int idaux) {
        requerimientosFiltrados = requerimientoEJB.requerimientosAuxiliar(idAuxiliar);
        return requerimientosFiltrados;
    }

    public List<Compra> buscarComprasporAutorizar() {
        comprasporautorizar = compraEJB.buscarcomprasporAutorizar();
        return comprasporautorizar;
    }

    public List<Compra> buscarComprasporPagar() {
        comprasporpagar = compraEJB.buscarcomprasporPagar();
        return comprasporpagar;
    }

    public List<Compra> buscarComprasPagadas() {
        compraspagadas = compraEJB.buscarcomprasPagadas();
        return comprasporpagar;
    }

    public void buscarArticulo() {
        articulo = requer.getCodigo();
        pcosto = articulo.getPcosto();
        pventa = articulo.getPventa();
    }

    public void anexar() {
        if (detalleconsumocajachica.getSubtotal() != 0) {
            double total = 0;
            Detalleconsumocajachica detalle = new Detalleconsumocajachica();
            detalle.setFechaconsumo(detalleconsumocajachica.getFechaconsumo());
            detalle.setRifproveedor(detalleconsumocajachica.getRifproveedor());
            detalle.setIdtipogastocajachica(detalleconsumocajachica.getIdtipogastocajachica());
            detalle.setNumerofactura(detalleconsumocajachica.getNumerofactura());
            detalle.setSubtotal(detalleconsumocajachica.getSubtotal());
            detalle.setIva(detalleconsumocajachica.getIva());
            total = detalleconsumocajachica.getSubtotal() + detalleconsumocajachica.getIva();
            detalle.setToalgeneral(total);
            this.listadetalles.add(detalle);
            id++;
            total = 0;
            totaltotal();
//            visualizar=1;
//            requer.setCodigo(null);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "No puede dejar el campo Subtotal en 0.0"));
        }
    }

    public void totaltotal() {
        double montotgeneral = 0;
        double montotiva = 0;
        double montotsubtotal = 0;

        for (Detalleconsumocajachica detalles : listadetalles) {
            montotgeneral += detalles.getToalgeneral();
            montotiva += detalles.getIva();
            montotsubtotal += detalles.getSubtotal();
        }
        totalgeneral = montotgeneral;
        totaliva = montotiva;
        totalsubtotal = montotsubtotal;
        totalgeneralform = formatearnumero.format(totalgeneral);
        totalivaform = formatearnumero.format(totaliva);
        totalsubtotalform = formatearnumero.format(totalsubtotal);

    }

    public void eliminar(Requerimiento requerim) {
        requerimientoEJB.remove(requerim);
        listarequerimiento.remove(requerim);
        totaltotal();
        auxiliarrequerimiento.setSubtotal(totalsubtotal);
        auxiliarrequerimiento.setMontoiva(totaliva);
        auxiliarrequerimiento.setMontototal(totalgeneral);
        auxiliarrequerimientoEJB.edit(auxiliarrequerimiento);
    }

    public void eliminardetalle(Detalleconsumocajachica detalleaeliminar) {
        listadetalles.remove(detalleaeliminar);
        totaltotal();
        /*        auxiliarrequerimiento.setSubtotal(totalsubtotal);
         auxiliarrequerimiento.setMontoiva(totaliva);
         auxiliarrequerimiento.setMontototal(totalgeneral);
         auxiliarrequerimientoEJB.edit(auxiliarrequerimiento);*/
    }

    public void asignar(Auxiliarrequerimiento aux) {
        this.auxiliarrequerimiento = aux;
        this.idAuxiliar = aux.getIdauxiliarrequerimiento();
        this.auxiliar = aux;
        requerimientosFiltrados = requerimientosAuxiliar();
        listarequerimiento = requerimientosFiltrados;
        this.compra.setIdauxiliarrequerimiento(auxiliar);
        totaltotal();
    }

}
