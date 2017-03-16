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
import Jpa.MaestromovimientoFacadeLocal;
import Jpa.TipoconjuntoFacadeLocal;
import Jpa.TipopagoFacadeLocal;
import Modelo.Banco;
import Modelo.Cobroventa;
import Modelo.Cuentabancaria;
import Modelo.Detallefactura;
import Modelo.Detalleretencionislrsp;
import Modelo.Detalleretencionivasp;
import Modelo.Estatuscontable;
import Modelo.Estatusfacturaventa;
import Modelo.Factura;
import Modelo.Maestromovimiento;
import Modelo.Tipoconjunto;
import Modelo.Tipopago;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
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
    @EJB
    private TipoconjuntoFacadeLocal tipoconjuntoEJB;
    @EJB
    private MaestromovimientoFacadeLocal maestromovimientoEJB;

    private Factura factura;
    private int numeroFact = 0;
    private int visualizar = 0;
    static Cobroventa cobro;
    private Cobroventa cobroventa = new Cobroventa();
    static Cuentabancaria cuentabancaria;
    private Detallefactura detallefact;
    private Estatusfacturaventa statusfactu = null;
    private Estatuscontable estatuscontab = null;
    private int formacobro = 0;
    static Banco banco;
    private int idbanco;
    private List<Detallefactura> detallesfactura;
    private List<Detallefactura> detallesfacturafiltrados;
    private List<Cuentabancaria> cuentasbancarias;
    private double saldocuenta;
    private List<Tipopago> tipopagos;
    private List<Cobroventa> cobrosefectuados;
    private List<Cuentabancaria> lstCuentasSelecc;
    private List<Banco> bancos;
    private int edad = 0;
    private String mensaje;
    private Date fechaactual = new Date();
    SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
    DecimalFormat formatearnumero = new DecimalFormat("###,###.##");
    private String correo;
    private envioCorreo enviomail;
    private Tipoconjunto tipoconjunto = null;
    private int tipofactura = 1;
    private double montoUT = 0;
    private double montopisoretiva = 0;
    private double montopisoretislr = 0;
    private double ivaretenido;
    private double islrretenido;
    private double montoacobrar;
    private double totalretenido;

    @Inject
    private Maestromovimiento maestromovi;
    @Inject
    private Detallefactura detallefactu;
    @Inject
    private Detalleretencionivasp detalleretencionivasp;
    @Inject
    private Detalleretencionislrsp detalleretencionislrsp;

    public double getSaldocuenta() {
        return saldocuenta;
    }

    public void setSaldocuenta(double saldocuenta) {
        this.saldocuenta = saldocuenta;
    }

    public Factura getFactura() {
        return factura;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public int getVisualizar() {
        return visualizar;
    }

    public void setVisualizar(int visualizar) {
        this.visualizar = visualizar;
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

    public void asignarDetalleFactura(Detallefactura detallefactura) {
        this.detallefactu = detallefactura;

    }

    public Detallefactura getDetallefactu() {
        return detallefactu;
    }

    public void setDetallefactu(Detallefactura detallefactu) {
        this.detallefactu = detallefactu;
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

    public static Cuentabancaria getCuentabancaria() {
        return cuentabancaria;
    }

    public void setCuentabancaria(Cuentabancaria cuentabancaria) {
        this.cuentabancaria = cuentabancaria;
    }

    @PostConstruct
    public void init() {
        detallesfactura = detallefacturaEJB.findAll();
        cuentasbancarias = cuentabancariaEJB.findAll();
        tipopagos = tipopagoEJB.findAll();
        cobrosefectuados = cobroventaEJB.findAll();
        bancos = bancoEJB.findAll();
        cobro = new Cobroventa();
        cobro.setFechacobro(fechaactual);
        visualizar = 0;
    }

    public List<Cuentabancaria> refrescarCuentasBancarias() {
        try {
            lstCuentasSelecc = cuentabancariaEJB.espxBanco(banco.getIdbanco());
        } catch (Exception e) {
        }
        cobro.setIdcuentabancaria(lstCuentasSelecc.get(0));
        return lstCuentasSelecc;
    }

    public void saldoactual() {
        saldocuenta = cuentabancariaEJB.saldoencuenta(lstCuentasSelecc);
    }

    public void asignar(Factura factura) {
        visualizar = 0;
        this.factura = factura;
        this.numeroFact = factura.getNumerofact();
        detallesfacturafiltrados = detallesenfacturaEJB.buscardetallefactura(factura);

        this.tipofactura = 1;
        this.visualizar = 0;
        this.totalretenido = 0;
        this.ivaretenido = 0;
        this.islrretenido = 0;
    


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
                double saldo = 0;
                cobro.setMontocobrado(factura.getSaldopendiente());
                factura.setSaldopendiente(saldo);
                int tipo = 1;
                statusfactu = estatusfacturaventaEJB.estatusfacturaPagada(tipo);
            } else {
                int tipo = 0;
                double saldop = 0;
                saldop = factura.getSaldopendiente() - cobro.getMontocobrado();
                if (saldop < 1) {
                    tipo = 1;
                } else {
                    tipo = 3;
                }

                factura.setSaldopendiente(saldop);
                statusfactu = estatusfacturaventaEJB.estatusfacturaAbonada(tipo);
            }
            factura.setIdestatusfacturaventa(statusfactu);
            facturaEJB.edit(factura);
            //codCompra = compraEJB.ultimacompraInsertada();

            cobro.setNumerofact(factura);
            cobro.setIdestatuscontable(estatuscontab);
            cuentabancaria = cobro.getIdcuentabancaria();
            cobro.setMontopendiente(factura.getSaldopendiente());
            cobroventaEJB.create(cobro);

            int tipoconj = 1;
            tipoconjunto = tipoconjuntoEJB.cambiartipoConjunto(tipoconj);
            maestromovi.setIdcobroventa(cobroventaEJB.ultimocobroInsertado());
            maestromovi.setFechamovimiento(cobro.getFechacobro());
            maestromovi.setIdtipoconjunto(tipoconjunto);
            maestromovi.setIdestatuscontable(estatuscontableEJB.estatusContablePorRegistrar());
            maestromovimientoEJB.create(maestromovi);

            double saldoactualbanco = 0;
            saldoactualbanco = cobro.getMontocobrado() + cobro.getIdcuentabancaria().getSaldo();
            cuentabancaria.setSaldo(saldoactualbanco);
            cuentabancariaEJB.edit(cuentabancaria);

            if (factura.getSaldopendiente() < 1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Su Cobro fue Almacenado"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El Abono recibido fue Almacenado"));
            }
            String subject;
            String ultimafactura = "" + factura.getNumerofact();
            String fechafactu = formateador.format(cobro.getFechacobro());
            correo = "FACTURA NRO: " + ultimafactura
                    + "  CONTROL: " + factura.getNumerocontrol()
                    + "  FECHA COBRO: " + fechafactu
                    + "  CLIENTE: " + factura.getRifcliente().getRazonsocial()
                    + "  RIF: " + factura.getRifcliente().getRifcliente()
                    + "  FORMA PAGO: " + cobro.getIdtipopago().getTipopago()
                    + "  BANCO: " + cobro.getIdcuentabancaria().getIdbanco().getNombrebanco()
                    + "  MONTO COBRADO: " + formatearnumero.format(cobro.getMontocobrado())
                    + "  MONTO PENDIENTE: " + formatearnumero.format(cobro.getMontopendiente())
                    + "  OBSERVACIONES: " + cobro.getObservacionescobro();

            subject = "Cobro N° " + cobro.getIdcobroventa();
            enviomail = new envioCorreo(correo, subject);
            enviomail.start();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al Grabar Cobro"));
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }

    public void seleccionpagofraccionado() {
        if (mensaje.equals("total")) {
            formacobro = 1;
        } else if (mensaje.equals("parcial")) {
            formacobro = 2;
        } else {
            formacobro = 0;
        }

    }
}
