package Jsf;

import Jpa.AuxiliarrequerimientoFacadeLocal;
import Jpa.CajachicaFacadeLocal;
import Jpa.ConsumocajachicaFacadeLocal;
import Jpa.DetalleconsumocajachicaFacadeLocal;
import Jpa.EmpresaFacadeLocal;
import Jpa.EstatusconsumocajachicaFacadeLocal;
import Jpa.ProveedorFacadeLocal;
import Jpa.TipogastocajachicaFacadeLocal;
import Modelo.Cajachica;
import Modelo.Consumocajachica;
import Modelo.Detalleconsumocajachica;
import Modelo.Empresa;
import Modelo.Estatusconsumocajachica;
import Modelo.Proveedor;
import Modelo.Tipogastocajachica;
import Modelo.Usuario;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

//@ManagedBean(name = "consumoscajachicaController")
@Named
@ViewScoped

public class ConsumoscajachicaController implements Serializable {

    @EJB
    private AuxiliarrequerimientoFacadeLocal auxiliarrequerimientoEJB;
    @EJB
    private CajachicaFacadeLocal cajachicaEJB;
    @EJB
    private TipogastocajachicaFacadeLocal tipogastocajachicaEJB;
    @EJB
    private ProveedorFacadeLocal proveedorEJB;
    @EJB
    private EstatusconsumocajachicaFacadeLocal estatusconsumoEJB;
    @EJB
    private ConsumocajachicaFacadeLocal consumocajachicaEJB;
    @EJB
    private EmpresaFacadeLocal empresaEJB;
    @EJB
    private DetalleconsumocajachicaFacadeLocal detalleconsumoEJB;
    
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
    @Inject
    private Empresa empresa;
    @Inject
    private RequerimientosController requerimientosController;
    
    private Calendar cal = Calendar.getInstance();
    private Date fechaactual = cal.getTime();
    private String totalgeneralform;
    private String totalivaform;
    private String totalsubtotalform;
    private double totalgeneral = 0;
    private double totaliva = 0;
    private double totalsubtotal = 0;
    private int id = 0;
    DecimalFormat formatearnumero = new DecimalFormat("###,###.##");
    private List<Detalleconsumocajachica> listadetalles = new ArrayList();
    private List<Cajachica> cajaschicas;
    private List<Tipogastocajachica> tiposdegastos;
    private List<Consumocajachica> lstConsumos;

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

    public List<Consumocajachica> getLstConsumos() {
        return lstConsumos;
    }

    public void setLstConsumos(List<Consumocajachica> lstConsumos) {
        this.lstConsumos = lstConsumos;
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Proveedor getProvee() {
        return provee;
    }

    public void setProvee(Proveedor provee) {
        this.provee = provee;
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
//        empresa = consumocajachica.getIdcajachica().getIdempresa();
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

    public void asignarProveedor(Proveedor proveed) {
        provee = proveed;
    }

    public void modificar() {
//        eliminardetalle(detallin);
        double montotgeneral = 0;
        double total = 0;
        List<Detalleconsumocajachica> detallesactulizados;
        total = detalleamodif.getSubtotal() + detalleamodif.getIva();
        detalleamodif.setToalgeneral(total);
        
        listadetalles.add(detalleamodif);
        totaltotal();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El Consumo fue Modificado satisfactoriamente"));
    }

    public void asignarDetalle(Detalleconsumocajachica detalleaeditar) {
        detalleamodif = detalleaeditar;
    }

    public void registrar() {
        try {
            //------Almacenando Consumo ----------\\
            consumocajachica.setSubtotalconsumo(totalsubtotal);
            consumocajachica.setIvaconsumo(totaliva);
            consumocajachica.setTotalconsumo(totalgeneral);
            Usuario us = requerimientosController.getUsa();
            consumocajachica.setIdusuario(us);
            Estatusconsumocajachica statusconsumo = null;
            int tipo = 1;
            statusconsumo = estatusconsumoEJB.cambiarestatusConsumo(tipo);
            consumocajachica.setIdestatusconsumocajachica(statusconsumo);
            double saldoactual= 0;
            double saldoant = 0;
            saldoant=consumocajachica.getIdcajachica().getSaldoactual();
            saldoactual = (saldoant - totalgeneral);
            consumocajachica.setSaldocajaactual(saldoactual);
            int serial=consumocajachica.getIdcajachica().getIdempresa().getSerialconsumo()+1;
            consumocajachica.setSerialconsumo(serial);
            consumocajachicaEJB.create(consumocajachica);
            
            //--------Actualizando el serial consumo de tabla Empresa ------- \\
            empresa=consumocajachica.getIdcajachica().getIdempresa();
            empresa.setSerialconsumo(serial);
            empresaEJB.edit(empresa);

            //--------Actualizando el saldo de la caja chica afectada ------- \\
            
            cajachica=consumocajachica.getIdcajachica();
            cajachica.setSaldoactual(saldoactual);
            cajachicaEJB.edit(cajachica); 

            //--------Almacenando Detallesconsumocajacjica ------- \\
            for (Detalleconsumocajachica dt : listadetalles) {
                detalleconsumocajachica.setIdconsumocajachica(consumocajachica);
                detalleconsumocajachica.setFechaconsumo(dt.getFechaconsumo());
                detalleconsumocajachica.setIdtipogastocajachica(dt.getIdtipogastocajachica());
                detalleconsumocajachica.setNumerofactura(dt.getNumerofactura());
                detalleconsumocajachica.setRifproveedor(dt.getRifproveedor());
                detalleconsumocajachica.setSubtotal(dt.getSubtotal());
                detalleconsumocajachica.setIva(dt.getIva());
                detalleconsumocajachica.setToalgeneral(dt.getToalgeneral());
                detalleconsumoEJB.create(detalleconsumocajachica);
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Sus Consumos fueron Almacenado con el Lote Nro " + serial ));
            listadetalles.clear();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al Grabar Lote de Consumos"));
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
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

    public void eliminardetalle(Detalleconsumocajachica detalleaeliminar) {
        listadetalles.remove(detalleaeliminar.hashCode());
        int indice = 0;
        for (Detalleconsumocajachica detalle : listadetalles) {
            detalle.setIddetalleconsumocajachica(indice);
            indice++;
            id = indice;
        }
        if (detalleaeliminar.hashCode() == 0) {
            id = 0;
        }
        totaltotal(); 
    }

    public List<Consumocajachica> refrescarConsumoscajachica() {
        try {
            lstConsumos = consumocajachicaEJB.consumosxCaja(consumocajachica.getIdcajachica().getIdcajachica());
        } catch (Exception e) {
 
        }
        return lstConsumos;
    }
    



}
