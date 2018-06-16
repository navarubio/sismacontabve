/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jsf;

import Jpa.AutorizacionFacadeLocal;
import Jpa.AuxiliarrequerimientoFacadeLocal;
import Jpa.BancoFacadeLocal;
import Jpa.CompraFacadeLocal;
import Jpa.CuentabancariaFacadeLocal;
import Jpa.DepartamentoFacadeLocal;
import Jpa.DetallecompraFacadeLocal;
import Jpa.DetalleconsumocajachicaFacadeLocal;
import Jpa.DetallelibrodiarioFacadeLocal;
import Jpa.DetalleretencionislrefFacade;
import Jpa.DetalleretencionislrefFacadeLocal;
import Jpa.DetalleretencionivaefFacade;
import Jpa.DetalleretencionivaefFacadeLocal;
import Jpa.EmpresaFacadeLocal;
import Jpa.EstatuscontableFacadeLocal;
import Jpa.EstatusfacturaFacadeLocal;
import Jpa.LibrodiarioFacadeLocal;
import Jpa.LibromayorFacadeLocal;
import Jpa.MaestromovimientoFacadeLocal;
import Jpa.MovimientobancarioFacadeLocal;
import Jpa.PagocompraFacadeLocal;
import Jpa.PlandecuentaFacadeLocal;
import Jpa.ReposicionconsumosFacadeLocal;
import Jpa.RequerimientoFacadeLocal;
import Jpa.TipoconjuntoFacadeLocal;
import Jpa.TipopagoFacadeLocal;
import Jpa.TiporetencionislrFacadeLocal;
import Modelo.Articulo;
import Modelo.Autorizacion;
import Modelo.Auxiliarrequerimiento;
import Modelo.Banco;
import Modelo.Cajachica;
import Modelo.Compra;
import Modelo.Comprobanteivaef;
import Modelo.Consumocajachica;
import Modelo.Cuentabancaria;
import Modelo.Departamento;
import Modelo.Detallecompra;
import Modelo.Detalleconsumocajachica;
import Modelo.Detallelibrodiario;
import Modelo.Detalleretencionislref;
import Modelo.Detalleretencionivaef;
import Modelo.Empresa;
import Modelo.Estatuscontable;
import Modelo.Estatusfactura;
import Modelo.Librodiario;
import Modelo.Libromayor;
import Modelo.Maestromovimiento;
import Modelo.Movimientobancario;
import Modelo.Otroingreso;
import Modelo.Pagocompra;
import Modelo.Plandecuenta;
import Modelo.Reposicioncajachica;
import Modelo.Requerimiento;
import Modelo.Tipoconjunto;
import Modelo.Tipopago;
import Modelo.Tiporetencionislr;
import Modelo.Usuario;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Inpeca
 */
@Named
@SessionScoped

public class AsientosreposicionController implements Serializable {

    @EJB
    private CuentabancariaFacadeLocal cuentabancariaEJB;
    @EJB
    private PagocompraFacadeLocal pagocompraEJB;

    @EJB
    private EstatuscontableFacadeLocal estatuscontableEJB;

    @EJB
    private AuxiliarrequerimientoFacadeLocal auxiliarrequerimientoEJB;

    @EJB
    private DetallecompraFacadeLocal detallecompraEJB;
    @EJB
    private DepartamentoFacadeLocal departamentoEJB;
    @EJB
    private TipopagoFacadeLocal tipopagoEJB;
    @EJB
    private BancoFacadeLocal bancoEJB;

    @EJB
    private MaestromovimientoFacadeLocal maestromovimientoEJB;

    @EJB
    private MovimientobancarioFacadeLocal movimientoBancarioEJB;
    @EJB
    private PlandecuentaFacadeLocal plandecuentaEJB;
    @EJB
    private LibrodiarioFacadeLocal librodiarioEJB;
    @EJB
    private LibromayorFacadeLocal libromayorEJB;
    @EJB
    private DetallelibrodiarioFacadeLocal detallelibrodiarioEJB;
    @EJB
    private ReposicionconsumosFacadeLocal reposicionconsumosEJB;
    @EJB
    private DetalleconsumocajachicaFacadeLocal detalleconsumoEJB;

    @Inject
    private Reposicioncajachica reposicionCajachica;
    @Inject
    private Movimientobancario movimientoreposicion;
    @Inject
    private Cajachica cajachica;
    @Inject
    private Otroingreso otroingreso;
    private int idReposicion = 0;
    @Inject
    private Movimientobancario movimientobancario;
    @Inject
    private RequerimientosController requerimientosController;

    private List<Movimientobancario> reposicionespecifica;
    private List<Consumocajachica> lstConsumos;
    private List<Detalleconsumocajachica> lstDetallesConsumos;
    private Auxiliarrequerimiento auxiliarrequerimiento;
    private Compra compra;
    private List<Movimientobancario> ingresoespecifico;
    private Maestromovimiento master;
    private Autorizacion autoriza;
    private Detallecompra detallecompras;
    private Detallelibrodiario detalleaanexar = new Detallelibrodiario();
    private Pagocompra pagocompra = new Pagocompra();
    private Pagocompra pagocompraver;
    private Estatusfactura statusfactu = null;
    private int formapago = 0;
    private Estatuscontable estatuscontab = null;
    private Banco banco;
    private Cuentabancaria cuentabanco;
    private Pagocompra pago;
    private Usuario usa;
    private Departamento dpto;
    private Compra codCompra;
    private Empresa empresa;
    private int visualizar = 0;
    private int tipocompra = 1;
    private double montoUT = 0;
    private double montopisoretiva = 0;
    private double montopisoretislr = 0;
    private int idAuxiliar = 0;
    private int idOtroingreso = 0;
    private int id = 0;
    private int indicearreglo = 0;
    private double totaldebegeneral = 0;
    private double totalhabergeneral = 0;
    private int vercasilla = 0;
    private double retiva = 0;
    private double retislr = 0;
    private String totalgeneralform;
    private List<Auxiliarrequerimiento> auxiliarrequerimientos;
    private List<Tiporetencionislr> tiporetencionesfiltradasPD = null;
    private List<Cuentabancaria> cuentasbancarias;
    private Libromayor codlibromayor;

    private List<Tipopago> tipopagos;
    private List<Detallecompra> detallecompraFiltrados;
    private List<Banco> bancos;
    private List<Cuentabancaria> lstCuentasSelecc;
    private List<Pagocompra> pagosefectuados;

    private String mensaje;
    private Date fechaactual = new Date();
    SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
    DecimalFormat formatearnumero = new DecimalFormat("###,###.##");
    private String correo;
    private envioCorreo enviomail;
    @Inject
    private Detalleretencionivaef detalleretencionivaef;
    @Inject
    private Detalleretencionislref detalleretencionislref;
    @Inject
    private Movimientobancario movimientoingreso;
    @Inject
    private Movimientobancario movimientoegreso;
    private double ivaretenido;
    private double islrretenido;
    private double montoapagar;
    private double totalretenido;
    ArrayList<Detallecompra> lista = new ArrayList();
    private Tipoconjunto tipoconjunto = null;
    private List<Detallelibrodiario> listadetalleslibrodiario = new ArrayList();
    private List<Detallelibrodiario> listadetalleslibrodiario2 = new ArrayList();

    private Detallelibrodiario detalleamodificar;
    private int cuentaseleccionada;
    private Librodiario codlibrodiario;
    int tamaño = 0;

    @Inject
    private Auxiliarrequerimiento auxiliar;
    private Requerimiento requerimiento;
    @Inject
    private Compra compras;
    @Inject
    private Detallecompra detallecompra;
    @Inject
    private Tipopago tipopago;
    @Inject
    private Cuentabancaria cuentabancaria;
    @Inject
    private Maestromovimiento maestromovi;
    @Inject
    private Librodiario librodiario;
    @Inject
    private Detallelibrodiario detallelibroventa;

    @Inject
    private Libromayor libromayor;

    public List<Detallelibrodiario> getListadetalleslibrodiario() {
        return listadetalleslibrodiario;
    }

    public void setListadetalleslibrodiario(List<Detallelibrodiario> listadetalleslibrodiario) {
        this.listadetalleslibrodiario = listadetalleslibrodiario;
    }

    public CuentabancariaFacadeLocal getCuentabancariaEJB() {
        return cuentabancariaEJB;
    }

    public void setCuentabancariaEJB(CuentabancariaFacadeLocal cuentabancariaEJB) {
        this.cuentabancariaEJB = cuentabancariaEJB;
    }

    public List<Detallelibrodiario> getListadetalleslibrodiario2() {
        return listadetalleslibrodiario2;
    }

    public void setListadetalleslibrodiario2(List<Detallelibrodiario> listadetalleslibrodiario2) {
        this.listadetalleslibrodiario2 = listadetalleslibrodiario2;
    }

    public int getCuentaseleccionada() {
        return cuentaseleccionada;
    }

    public void setCuentaseleccionada(int cuentaseleccionada) {
        this.cuentaseleccionada = cuentaseleccionada;
    }

    public Librodiario getLibrodiario() {
        return librodiario;
    }

    public Detallelibrodiario getDetalleamodificar() {
        return detalleamodificar;
    }

    public void setDetalleamodificar(Detallelibrodiario detalleamodificar) {
        this.detalleamodificar = detalleamodificar;
    }

    public int getVercasilla() {
        return vercasilla;
    }

    public void setVercasilla(int vercasilla) {
        this.vercasilla = vercasilla;
    }

    public void setLibrodiario(Librodiario librodiario) {
        this.librodiario = librodiario;
    }

    public Detallelibrodiario getDetallelibroventa() {
        return detallelibroventa;
    }

    public double getRetiva() {
        return retiva;
    }

    public void setRetiva(double retiva) {
        this.retiva = retiva;
    }

    public double getRetislr() {
        return retislr;
    }

    public Detallelibrodiario getDetalleaanexar() {
        return detalleaanexar;
    }

    public void setDetalleaanexar(Detallelibrodiario detalleaanexar) {
        this.detalleaanexar = detalleaanexar;
    }

    public void setRetislr(double retislr) {
        this.retislr = retislr;
    }

    public double getTotaldebegeneral() {
        return totaldebegeneral;
    }

    public void setTotaldebegeneral(double totaldebegeneral) {
        this.totaldebegeneral = totaldebegeneral;
    }

    public double getTotalhabergeneral() {
        return totalhabergeneral;
    }

    public void setTotalhabergeneral(double totalhabergeneral) {
        this.totalhabergeneral = totalhabergeneral;
    }

    public void setDetallelibroventa(Detallelibrodiario detallelibroventa) {
        this.detallelibroventa = detallelibroventa;
    }

    public Libromayor getLibromayor() {
        return libromayor;
    }

    public void setLibromayor(Libromayor libromayor) {
        this.libromayor = libromayor;
    }

    public List<Cuentabancaria> getLstCuentasSelecc() {
        return lstCuentasSelecc;
    }

    public double getIvaretenido() {
        return ivaretenido;
    }

    public void setIvaretenido(double ivaretenido) {
        this.ivaretenido = ivaretenido;
    }

    public void setLstCuentasSelecc(List<Cuentabancaria> lstCuentasSelecc) {
        this.lstCuentasSelecc = lstCuentasSelecc;
    }

    public Pagocompra getPagocompra() {
        return pagocompra;
    }

    public Detalleretencionivaef getDetalleretencionivaef() {
        return detalleretencionivaef;
    }

    public void setDetalleretencionivaef(Detalleretencionivaef detalleretencionivaef) {
        this.detalleretencionivaef = detalleretencionivaef;
    }

    public Detalleretencionislref getDetalleretencionislref() {
        return detalleretencionislref;
    }

    public void setDetalleretencionislref(Detalleretencionislref detalleretencionislref) {
        this.detalleretencionislref = detalleretencionislref;
    }

    public Requerimiento getRequerimiento() {
        return requerimiento;
    }

    public void setRequerimiento(Requerimiento requerimiento) {
        this.requerimiento = requerimiento;
    }

    public int getFormapago() {
        return formapago;
    }

    public List<Tiporetencionislr> getTiporetencionesfiltradasPD() {
        return tiporetencionesfiltradasPD;
    }

    public void setTiporetencionesfiltradasPD(List<Tiporetencionislr> tiporetencionesfiltradasPD) {
        this.tiporetencionesfiltradasPD = tiporetencionesfiltradasPD;
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

    public Cuentabancaria getCuentabanco() {
        return cuentabanco;
    }

    public void setCuentabanco(Cuentabancaria cuentabanco) {
        this.cuentabanco = cuentabanco;
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

    public double getTotalretenido() {
        return totalretenido;
    }

    public void setTotalretenido(double totalretenido) {
        this.totalretenido = totalretenido;
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

    public void asignarDetalleCompra(Detallecompra detallecompra) {
        this.detallecompra = detallecompra;

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

    public Otroingreso getOtroingreso() {
        return otroingreso;
    }

    public void setOtroingreso(Otroingreso otroingreso) {
        this.otroingreso = otroingreso;
    }

    public Autorizacion getAutoriza() {
        return autoriza;
    }

    public void setAutoriza(Autorizacion autoriza) {
        this.autoriza = autoriza;
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

    public List<Movimientobancario> getIngresoespecifico() {
        return ingresoespecifico;
    }

    public void setIngresoespecifico(List<Movimientobancario> ingresoespecifico) {
        this.ingresoespecifico = ingresoespecifico;
    }

    public Movimientobancario getMovimientoingreso() {
        return movimientoingreso;
    }

    public void setMovimientoingreso(Movimientobancario movimientoingreso) {
        this.movimientoingreso = movimientoingreso;
    }

    public Movimientobancario getMovimientoegreso() {
        return movimientoegreso;
    }

    public void setMovimientoegreso(Movimientobancario movimientoegreso) {
        this.movimientoegreso = movimientoegreso;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public int getVisualizar() {
        return visualizar;
    }

    public void setVisualizar(int visualizar) {
        this.visualizar = visualizar;
    }

    public int getTipocompra() {
        return tipocompra;
    }

    public void setTipocompra(int tipocompra) {
        this.tipocompra = tipocompra;
    }

    public Cajachica getCajachica() {
        return cajachica;
    }

    public void setCajachica(Cajachica cajachica) {
        this.cajachica = cajachica;
    }

    public Reposicioncajachica getReposicionCajachica() {
        return reposicionCajachica;
    }

    public void setReposicionCajachica(Reposicioncajachica reposicionCajachica) {
        this.reposicionCajachica = reposicionCajachica;
    }

    public List<Consumocajachica> getLstConsumos() {
        return lstConsumos;
    }

    public void setLstConsumos(List<Consumocajachica> lstConsumos) {
        this.lstConsumos = lstConsumos;
    }

    public List<Detalleconsumocajachica> getLstDetallesConsumos() {
        return lstDetallesConsumos;
    }

    public void setLstDetallesConsumos(List<Detalleconsumocajachica> lstDetallesConsumos) {
        this.lstDetallesConsumos = lstDetallesConsumos;
    }

    public String getTotalgeneralform() {
        return totalgeneralform;
    }

    public void setTotalgeneralform(String totalgeneralform) {
        this.totalgeneralform = totalgeneralform;
    }

    @PostConstruct
    public void init() {
        visualizar = 0;
        vercasilla = 0;
        tamaño = 0;
//        librodiario.setFecha(fechaactual);
        auxiliarrequerimientos = auxiliarrequerimientoEJB.findAll();
        cuentasbancarias = cuentabancariaEJB.findAll();
        tipopagos = tipopagoEJB.findAll();
        bancos = bancoEJB.findAll();
        pagosefectuados = pagocompraEJB.findAll();
        pago = new Pagocompra();
        listadetalleslibrodiario.clear();
        retiva = 0;
        retislr = 0;
        //articulos = articuloEJB.findAll();
        //comprasporautorizar=compraEJB.buscarcomprasporAutorizar();

//        this.auxiliarrequerimiento=requerimientosController.getAuxrequer();
    }

    public void asignarOtroingreso(Otroingreso otro, Maestromovimiento maestro) {
        this.retiva = 0;
        this.retislr = 0;
        this.tamaño = 0;
        this.vercasilla = 0;
        this.otroingreso = otro;
        this.master = maestro;
        this.idOtroingreso = otro.getIdotroingreso();
        this.ingresoespecifico = movimientoBancarioEJB.buscarmovimiento(otro);
        this.movimientoingreso = ingresoespecifico.get(0);
        tamaño = ingresoespecifico.size();
        if (tamaño > 1) {
            this.vercasilla = 1;
            this.movimientoegreso = ingresoespecifico.get(1);
            librodiario.setDescripcionasiento("P/R INGRESO ING- " + otroingreso.getIdotroingreso() + " POR CONCEPTO DE " + movimientoegreso.getIdcuentabancaria().getIdbanco().getNombrebanco() + " AL " + movimientoingreso.getIdcuentabancaria().getIdbanco().getNombrebanco());
        } else {
            librodiario.setDescripcionasiento("P/R INGRESO ING- " + otroingreso.getIdotroingreso() + " POR CONCEPTO DE " + otroingreso.getIdtipoingreso().getTipoingreso() + " " + otroingreso.getObservaciones());
        }
        listadetalleslibrodiario = detallesasiento();
        librodiario.setFecha(otroingreso.getFechaingreso());
//        Detallecompra detal = detallecompraFiltrados.get(0);
        //       Articulo artic = detal.getCodigo();

//        this.compra.setIdauxiliarrequerimiento(auxiliar);
    }

    public void asignarReposicion(Reposicioncajachica repo, Maestromovimiento maestro) {
        empresa= requerimientosController.getEmpresa();
        this.retiva = 0;
        this.retislr = 0;
        this.tamaño = 0;
        this.vercasilla = 0;
        this.reposicionCajachica = repo;
        this.master = maestro;
        this.idReposicion = repo.getIdreposicioncajachica();
        this.reposicionespecifica = movimientoBancarioEJB.buscarmovimiento(repo);
        this.movimientoreposicion = reposicionespecifica.get(0);
        this.cajachica = reposicionconsumosEJB.devolverCajachica(repo);
        this.lstConsumos = reposicionconsumosEJB.listaconsumosxReposicion(repo);
        this.lstDetallesConsumos = detalleconsumoEJB.listadetalleconsumosxListaConsumos(lstConsumos);
        totaltotal();
        if (lstDetallesConsumos.isEmpty()) {
            tamaño = 0;
            this.vercasilla = 1;
            librodiario.setDescripcionasiento("P/R APERTURA DE CAJA CHICA SEGUN REP- " + reposicionCajachica.getSerialreposicion());
        } else {
            tamaño = 1;
            librodiario.setDescripcionasiento("P/R REPOSICION DE CAJA CHICA SEGUN REP- " + reposicionCajachica.getIdreposicioncajachica() + " POR CONCEPTO DE " + reposicionCajachica.getObservaciones());
        }
        listadetalleslibrodiario = detallesasiento();
        librodiario.setFecha(reposicionCajachica.getFecharesposicion());
    }

    public List<Detallecompra> detallecompraAuxiliar() {
        List<Detallecompra> listado = null;
        listado = detallecompraEJB.buscardetallecompra(compra);
        return listado;
    }

    public List<Detallelibrodiario> detallesasiento() {
        List<Detallelibrodiario> detallesasiento = null;
        anexar();
        detallesasiento = listadetalleslibrodiario;
        return detallesasiento;
    }

    public List<Detallelibrodiario> detallesasientopago() {
        List<Detallelibrodiario> detallesasiento = null;
        anexarpagocompra();
        detallesasiento = listadetalleslibrodiario;
        return detallesasiento;
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
            lstCuentasSelecc = cuentabancariaEJB.espxBanco(banco.getIdbanco(), requerimientosController.getEmpresa());
        } catch (Exception e) {
        }
        pagocompra.setIdcuentabancaria(lstCuentasSelecc.get(0));
        return lstCuentasSelecc;
    }

    public void registrar() {
        try {
            double debe = 0;
            double haber = 0;
            double saldototaltotal = 0;
            librodiario.setIdempresa(empresa.getIdempresa());
            librodiarioEJB.create(librodiario);
            codlibrodiario = librodiarioEJB.ultimoInsertado();

            Detallelibrodiario detalleld = new Detallelibrodiario();
            Libromayor libromy = new Libromayor();
            Plandecuenta cuentacontable = new Plandecuenta();
            int cuentareposiciom = 0;
            int cuentamovi = 0;
            cuentareposiciom = reposicionCajachica.getIdcuentabancaria().getIdplandecuenta().getIdplandecuenta();

            for (Detallelibrodiario dld : listadetalleslibrodiario) {
                detalleld.setIdlibrodiario(codlibrodiario);
                libromy.setIdlibrodiario(codlibrodiario);
                cuentacontable = plandecuentaEJB.buscarcuenta(dld.getIdplandecuenta().getIdplandecuenta(), empresa);

                detalleld.setIdplandecuenta(dld.getIdplandecuenta());
                libromy.setIdplandecuenta(dld.getIdplandecuenta());

                if (dld.getDebe() != null) {
                    detalleld.setDebe(dld.getDebe());
                    detalleld.setHaber(0.0);
                    debe = dld.getDebe();
                    libromy.setHaber(0.0);
                    libromy.setDebe(dld.getDebe());
                }
                if (dld.getHaber() != null) {
                    detalleld.setHaber(dld.getHaber());
                    detalleld.setDebe(0.0);
                    haber = dld.getHaber();
                    libromy.setDebe(0.0);
                    libromy.setHaber(dld.getHaber());
                }
                double saldoant = plandecuentaEJB.buscarsaldoanterior(dld.getIdplandecuenta().getIdplandecuenta());
                libromy.setSaldoanterior(requerimientosController.redondearDecimales(saldoant));
                if (dld.getIdplandecuenta().getIdtiposaldocontable().getIdtiposaldocontable() == 1) {
                    saldototaltotal = (((saldoant) + debe) - haber);
                } else if (dld.getIdplandecuenta().getIdtiposaldocontable().getIdtiposaldocontable() == 2) {
                    saldototaltotal = (((saldoant) - haber) + debe);
                }
                libromy.setSaldoposterior(requerimientosController.redondearDecimales(saldototaltotal));
                cuentacontable.setSaldogeneral(requerimientosController.redondearDecimales(saldototaltotal));
                cuentamovi = cuentacontable.getIdplandecuenta();
                detallelibrodiarioEJB.create(detalleld);
                libromayorEJB.create(libromy);
                codlibromayor = libromayorEJB.ultimoInsertado();
                if (cuentareposiciom == cuentamovi) {
                    movimientobancario = movimientoBancarioEJB.buscarmovimientoxreposicion(reposicionCajachica);
                    movimientobancario.setIdlibromayor(codlibromayor);
                    movimientoBancarioEJB.edit(movimientobancario);
                }
                plandecuentaEJB.edit(cuentacontable);
                master.setIdestatuscontable(estatuscontableEJB.estatusContableRegistrada());
                master.setIdlibrodiario(codlibrodiario);
                maestromovimientoEJB.edit(master);

                debe = 0;
                haber = 0;
                saldototaltotal = 0;
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Su Asiento fue Almacenado Codigo" + codlibrodiario.getIdlibrodiario(), ""));
            listadetalleslibrodiario.clear();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al Grabar Asiento Contable", "Aviso"));
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }

    public Date fechaactual() {
        Date fecha = new Date();
        return fecha;
    }

    public void anexar() {
        listadetalleslibrodiario.clear();
        id = 0;
        visualizar = 0;
        if (tamaño > 0) {
            for (Detalleconsumocajachica detal : lstDetallesConsumos) {
                Detallelibrodiario detallelibr = new Detallelibrodiario();

                detallelibr.setIdplandecuenta(detal.getIdtipogastocajachica().getIdplandecuenta());
                detallelibr.setDebe(detal.getToalgeneral());
                detallelibr.setIddetallelibrodiario(id);
                this.listadetalleslibrodiario.add(detallelibr);
                id++;
            }

            Detallelibrodiario detallelib = new Detallelibrodiario();

            detallelib.setIdplandecuenta(cajachica.getIdplandecuenta());
            detallelib.setHaber(reposicionCajachica.getMontoreposicion());
            detallelib.setIddetallelibrodiario(id);
            this.listadetalleslibrodiario.add(detallelib);
            id++;

            Detallelibrodiario detallelibro = new Detallelibrodiario();
            detallelibro.setIdplandecuenta(cajachica.getIdplandecuenta());
            detallelibro.setDebe(reposicionCajachica.getMontoreposicion());
            detallelibro.setIddetallelibrodiario(id);
            this.listadetalleslibrodiario.add(detallelibro);
            id++;

            Detallelibrodiario detallelibro2 = new Detallelibrodiario();

            detallelibro2.setIdplandecuenta(reposicionCajachica.getIdcuentabancaria().getIdplandecuenta());
            detallelibro2.setHaber(reposicionCajachica.getMontoreposicion());
            detallelibro2.setIddetallelibrodiario(id);
            this.listadetalleslibrodiario.add(detallelibro2);
            id++;
        } else {
            Detallelibrodiario detallelibro = new Detallelibrodiario();
            detallelibro.setIdplandecuenta(cajachica.getIdplandecuenta());
            detallelibro.setDebe(reposicionCajachica.getMontoreposicion());
            detallelibro.setIddetallelibrodiario(id);
            this.listadetalleslibrodiario.add(detallelibro);
            id++;

            Detallelibrodiario detallelibrod = new Detallelibrodiario();

            detallelibrod.setIdplandecuenta(reposicionCajachica.getIdcuentabancaria().getIdplandecuenta());
            detallelibrod.setHaber(reposicionCajachica.getMontoreposicion());
            detallelibrod.setIddetallelibrodiario(id);
            this.listadetalleslibrodiario.add(detallelibrod);
            id++;
        }
    }

    public void anexarpagocompra() {
        listadetalleslibrodiario.clear();
        id = 0;
        visualizar = 0;

        Detallelibrodiario detallelibro = new Detallelibrodiario();

        int codctadebe = 21115;
        Plandecuenta cuentaporpagar = plandecuentaEJB.buscarcuenta(codctadebe, empresa);
        detallelibro.setIdplandecuenta(cuentaporpagar);
        if (pagocompra.getSaldopendiente() > 0) {
            if (pagocompra.getMontoretenido() > 0) {
                detallelibro.setDebe(pagocompra.getTotalpago() + retiva + retislr);
            } else {
                detallelibro.setDebe(pagocompra.getTotalpago());
            }
        } else {
            if (pagocompra.getTotalpago() < compra.getTotal()) {
                detallelibro.setDebe(pagocompra.getTotalpago());
            } else {
                detallelibro.setDebe(compra.getTotal());
            }
        }
        detallelibro.setIddetallelibrodiario(id);
        this.listadetalleslibrodiario.add(detallelibro);
        id++;

        if (pagocompra.getMontoretenido() > 0) {

            if (pagocompra.getMontoretenido() == retiva) {
                Detallelibrodiario detallelib = new Detallelibrodiario();
                int codcta = 21235;
                Plandecuenta cuentaretencioniva = plandecuentaEJB.buscarcuenta(codcta, empresa);
                detallelib.setIdplandecuenta(cuentaretencioniva);
                detallelib.setHaber(pagocompra.getMontoretenido());
                detallelib.setIddetallelibrodiario(id);
                this.listadetalleslibrodiario.add(detallelib);
                id++;
            } else if (pagocompra.getMontoretenido() > retiva) {
                Detallelibrodiario detallelib = new Detallelibrodiario();
                int codcta = 21235;
                Plandecuenta cuentaretencioniva = plandecuentaEJB.buscarcuenta(codcta, empresa);
                detallelib.setIdplandecuenta(cuentaretencioniva);
                detallelib.setHaber(retiva);
                detallelib.setIddetallelibrodiario(id);
                this.listadetalleslibrodiario.add(detallelib);
                id++;

                Detallelibrodiario detallelibr1 = new Detallelibrodiario();
                int codcta1 = 212310;
                Plandecuenta cuentaretencionislr = plandecuentaEJB.buscarcuenta(codcta1, empresa);
                detallelibr1.setIdplandecuenta(cuentaretencionislr);
                detallelibr1.setHaber(retislr);
                detallelibr1.setIddetallelibrodiario(id);
                this.listadetalleslibrodiario.add(detallelibr1);
                id++;
            }
        }

        Detallelibrodiario detallelibr = new Detallelibrodiario();
        detallelibr.setIdplandecuenta(pagocompra.getIdcuentabancaria().getIdplandecuenta());
        detallelibr.setHaber(pagocompra.getTotalpago());
        detallelibr.setIddetallelibrodiario(id);
        this.listadetalleslibrodiario.add(detallelibr);
        id++;

    }

    public String totaldebe() {
        double montotdebe = 0;

        for (Detallelibrodiario detall : listadetalleslibrodiario) {
            if (detall.getDebe() != null) {
                montotdebe += detall.getDebe();
            }
        }
        totaldebegeneral = montotdebe;
        return new DecimalFormat("###,###.##").format(montotdebe);
//        return montotdebe;
    }

    public String totalhaber() {
        double montothaber = 0;

        for (Detallelibrodiario detall : listadetalleslibrodiario) {
            if (detall.getHaber() != null) {
                montothaber += detall.getHaber();
            }
        }
        totalhabergeneral = montothaber;
        return new DecimalFormat("###,###.##").format(montothaber);
//        return montothaber;
    }

    public void eliminar(Detallelibrodiario detalleld) {
        listadetalleslibrodiario.remove(detalleld.hashCode());
        int indice = 0;
        for (Detallelibrodiario detallazo : listadetalleslibrodiario) {
            detallazo.setIddetallelibrodiario(indice);
            indice++;
            id = indice;
        }
        if (detalleld.hashCode() == 0) {
            id = 0;
        }
    }

    public void asignarDetallelibrodiario(Detallelibrodiario detallelbr) {
        detalleamodificar = detallelbr;
        cuentaseleccionada = detallelbr.getIdplandecuenta().getIdplandecuenta();
        indicearreglo = detallelbr.hashCode();
    }

    public void modificar() {
        detalleamodificar.setIdplandecuenta(plandecuentaEJB.buscarcuenta(cuentaseleccionada, empresa));
        listadetalleslibrodiario.set(indicearreglo, detalleamodificar);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Su Cuenta fue modificada"));
    }

    public void anexarregistro() {
        Detallelibrodiario detalleanexo = new Detallelibrodiario();
        detalleaanexar.setIdplandecuenta(plandecuentaEJB.buscarcuenta(cuentaseleccionada, empresa));
        int indic = listadetalleslibrodiario.size();
        detalleaanexar.setIddetallelibrodiario(indic);
        detalleanexo.setIddetallelibrodiario(indic);
        detalleanexo.setIdplandecuenta(detalleaanexar.getIdplandecuenta());
        detalleanexo.setDebe(detalleaanexar.getDebe());
        detalleanexo.setHaber(detalleaanexar.getHaber());
        this.listadetalleslibrodiario.add(detalleanexo);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Su Cuenta fue modificada"));
    }

    public void totaltotal() {
        double montotgeneral = 0;

        for (Consumocajachica consutotal : lstConsumos) {
            montotgeneral += consutotal.getTotalconsumo();
        }
        totalgeneralform = formatearnumero.format(montotgeneral);
    }

}
