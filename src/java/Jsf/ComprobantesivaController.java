package Jsf;

import Jpa.BancoFacadeLocal;
import Jpa.CobroventaFacadeLocal;
import Jpa.ComprobanteivaefFacadeLocal;
import Jpa.CuentabancariaFacadeLocal;
import Jpa.DetallefacturaFacadeLocal;
import Jpa.DetalleretencionivaefFacadeLocal;
import Jpa.EstatuscontableFacadeLocal;
import Jpa.EstatusfacturaventaFacadeLocal;
import Jpa.FacturaFacadeLocal;
import Jpa.TipopagoFacadeLocal;
import Modelo.Banco;
import Modelo.Cobroventa;
import Modelo.Comprobanteivaef;
import Modelo.Cuentabancaria;
import Modelo.Detallefactura;
import Modelo.Detalleretencionivaef;
import Modelo.Estatuscomprobanteretencion;
import Modelo.Estatuscontable;
import Modelo.Estatusfacturaventa;
import Modelo.Factura;
import Modelo.Tipopago;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.jboss.weld.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author sofimar
 */
@ManagedBean(name = "comprobantesivaController")
@SessionScoped

public class ComprobantesivaController implements Serializable {

    @EJB
    private ComprobanteivaefFacadeLocal comprobanteivaefEJB;
    @EJB
    private DetalleretencionivaefFacadeLocal detalleretencionivaefEJB;
            
    private Detalleretencionivaef detalleretencionivaef;
    private int numeroDetalle = 0;
    private Comprobanteivaef comprobanteivaef;
    private Factura factura;
    private String correlativo;
    private int anio;
    private int mes;
    private String mesfiscal;
    private String serialcomprobante;
    private Date fechacomprobante;
    private List<Detalleretencionivaef> detalleretivafiltrados = new ArrayList();
    private double totalgeneral;
    private double totalbaseimponible;
    private double totaliva;
    private double totalivaretenido;
    private int id=0;
    @Inject
    private Estatuscomprobanteretencion estatuscomprobanteretencion;
    

    ///////////////////////////////////////////

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

    static Cobroventa cobro;
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

    public Detalleretencionivaef getDetalleretencionivaef() {
        return detalleretencionivaef;
    }

    public void setDetalleretencionivaef(Detalleretencionivaef detalleretencionivaef) {
        this.detalleretencionivaef = detalleretencionivaef;
    }

    public Comprobanteivaef getComprobanteivaef() {
        return comprobanteivaef;
    }

    public void setComprobanteivaef(Comprobanteivaef comprobanteivaef) {
        this.comprobanteivaef = comprobanteivaef;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String getMesfiscal() {
        return mesfiscal;
    }

    public void setMesfiscal(String mesfiscal) {
        this.mesfiscal = mesfiscal;
    }
    
    public String getSerialcomprobante() {
        return serialcomprobante;
    }

    public void setSerialcomprobante(String serialcomprobante) {
        this.serialcomprobante = serialcomprobante;
    }

    public Date getFechacomprobante() {
        return fechacomprobante;
    }

    public void setFechacomprobante(Date fechacomprobante) {
        this.fechacomprobante = fechacomprobante;
    }

    public List<Detalleretencionivaef> getDetalleretivafiltrados() {
        return detalleretivafiltrados;
    }

    public void setDetalleretivafiltrados(List<Detalleretencionivaef> detalleretivafiltrados) {
        this.detalleretivafiltrados = detalleretivafiltrados;
    }

    public double getTotalgeneral() {
        return totalgeneral;
    }

    public void setTotalgeneral(double totalgeneral) {
        this.totalgeneral = totalgeneral;
    }

    public double getTotalbaseimponible() {
        return totalbaseimponible;
    }

    public void setTotalbaseimponible(double totalbaseimponible) {
        this.totalbaseimponible = totalbaseimponible;
    }

    public double getTotaliva() {
        return totaliva;
    }

    public void setTotaliva(double totaliva) {
        this.totaliva = totaliva;
    }

    public double getTotalivaretenido() {
        return totalivaretenido;
    }

    public void setTotalivaretenido(double totalivaretenido) {
        this.totalivaretenido = totalivaretenido;
    }
    
    

    
    public String devolversiguientecomprobante() {
        String siguiente;
        siguiente = comprobanteivaefEJB.siguientecomprobanteformat();
        correlativo=siguiente;
        return siguiente;
    }
    
    public void separarperiodofiscal(){
        //fechacomprobante=comprobanteivaef.getFecha();
        mes=0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechacomprobante);
        anio = cal.get(Calendar.YEAR);
        mes = cal.get(Calendar.MONTH)+1;
        String year= Integer.toString(anio);
        String month= String.format("%02d",mes);
        mesfiscal=month;        
        serialcomprobante=year + month + correlativo;
    }    

    public void onDateSelect(SelectEvent event) {    
        separarperiodofiscal();
    }
    
    public void obtenertotaltotales() {
        double montotgeneral = 0;
        double montotiva = 0;
        double montotsubtotal = 0;
        double montoretenido=0;
        for (Detalleretencionivaef detalleretiva : detalleretivafiltrados) {
            montotgeneral += detalleretiva.getTotalcompra();
            montotiva += detalleretiva.getTotalivacompra();
            montotsubtotal += detalleretiva.getBimponible();
            montoretenido  += detalleretiva.getTotalivaretenido();
        }
        totalgeneral = montotgeneral;
        totaliva = montotiva;
        totalbaseimponible = montotsubtotal;
        totalivaretenido = montoretenido;
    }
    
    public void asignar(Detalleretencionivaef detalleretivaef) {
        this.detalleretencionivaef = detalleretivaef;
        this.detalleretivafiltrados=detalleretencionivaefEJB.buscarretencionesporPreveedor(detalleretivaef.getIdcompra().getRifproveedor().getRifproveedor()); 
        obtenertotaltotales();
    }
    public void eliminar(Detalleretencionivaef detalleaeliminar) {
        int indc= detalleretivafiltrados.indexOf(detalleaeliminar);
        detalleretivafiltrados.remove(indc);
//        int indice = 0;
//        for (Detalleretencionivaef detalleretiva : detalleretivafiltrados) {
//            detalleretiva.setIddetalleretencionivaef(indice);
//            indice++;
//            id = indice;
//        }
//        if (detalleaeliminar.hashCode() == 0) {
//            id = 0;
//        }
        obtenertotaltotales();

    }
    public void registrar() {
        try {
//            estatuscomprobanteretencion.setIdestatuscomprobante(1);
            comprobanteivaef.setComprobante(serialcomprobante);
            comprobanteivaef.setFecha(fechacomprobante);
            comprobanteivaef.setAnio(anio);
            comprobanteivaef.setMes(mes);
            comprobanteivaef.setRifproveedor(detalleretencionivaef.getIdcompra().getRifproveedor());
            comprobanteivaef.setTotalgeneral(totalgeneral);
            comprobanteivaef.setTotalbimponible(totalbaseimponible);
            comprobanteivaef.setTotaliva(totaliva);
            comprobanteivaef.setTotalivaretenido(totalivaretenido);
//            comprobanteivaef.setIdestatuscomprobante(estatuscomprobanteretencion);
            comprobanteivaefEJB.create(comprobanteivaef);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Fue generado el Comprobante de Retencion de Iva "));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al Generar el Comprobante de Retencion de IVA"));
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }
///////////////////////////////////    
    
    
    
    public double getSaldocuenta() {
        return saldocuenta;
    }

    public void setSaldocuenta(double saldocuenta) {
        this.saldocuenta = saldocuenta;
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

    public static Cuentabancaria getCuentabancaria() {
        return cuentabancaria;
    }

    public void setCuentabancaria(Cuentabancaria cuentabancaria) {
        this.cuentabancaria = cuentabancaria;
    }

    @PostConstruct
    public void init() {
//        detallesfactura = detallefacturaEJB.findAll();
//        cuentasbancarias = cuentabancariaEJB.findAll();
//        tipopagos = tipopagoEJB.findAll();
//        cobrosefectuados = cobroventaEJB.findAll();
///        bancos = bancoEJB.findAll();
//        cobro = new Cobroventa();
//        cobro.setFechacobro(fechaactual);
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

    

    
    
    
    
    
    public List<Detallefactura> detallefacturaAuxiliar() {
        List<Detallefactura> listado = null;
        listado = detallesenfacturaEJB.buscardetallefactura(factura);
        return listado;
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
