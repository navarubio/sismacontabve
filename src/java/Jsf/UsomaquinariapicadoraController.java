package Jsf;

import Modelo.Usomaquinariapicadora;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.UsomaquinariapicadoraFacade;
import Jpa.UsomaquinariapicadoraFacadeLocal;
import Modelo.Articulo;
import Modelo.Cuentabancaria;
import Modelo.Detallenotacarga;
import Modelo.Maquinariapicadora;
import Modelo.Usuario;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named("usomaquinariapicadoraController")
@SessionScoped
public class UsomaquinariapicadoraController implements Serializable {

    @EJB
    private Jpa.UsomaquinariapicadoraFacadeLocal ejbFacade;
    private List<Usomaquinariapicadora> items = null;
    private List<Usomaquinariapicadora> listadehrsmaquina = new ArrayList();
    private List<Usomaquinariapicadora> detalles = null;
    private Usomaquinariapicadora selected;
    private List<Double> listadeprecio = new ArrayList();
    private double cantidad = 0;
    private double subtotal = 0;
    private int id = 0;
    private double totalgeneral = 0;
    private double totaliva = 0;
    private double totalsubtotal = 0;
    private double totalcantidad = 0;
    
    @Inject
    private Usomaquinariapicadora hrsuso;

    public UsomaquinariapicadoraController() {
    }

    public Usomaquinariapicadora getSelected() {
        return selected;
    }

    public void setSelected(Usomaquinariapicadora selected) {
        this.selected = selected;
    }

    public List<Usomaquinariapicadora> getListadehrsmaquina() {
        return listadehrsmaquina;
    }

    public void setListadehrsmaquina(List<Usomaquinariapicadora> listadehrsmaquina) {
        this.listadehrsmaquina = listadehrsmaquina;
    }

    protected void setEmbeddableKeys() {
    }

    public List<Double> getListadeprecio() {
        return listadeprecio;
    }

    public void setListadeprecio(List<Double> listadeprecio) {
        this.listadeprecio = listadeprecio;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    protected void initializeEmbeddableKey() {
    }

    private UsomaquinariapicadoraFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Usomaquinariapicadora getHrsuso() {
        return hrsuso;
    }

    public void setHrsuso(Usomaquinariapicadora hrsuso) {
        this.hrsuso = hrsuso;
    }
    
    public double getTotalgeneral() {
        return totalgeneral;
    }

    public void setTotalgeneral(double totalgeneral) {
        this.totalgeneral = totalgeneral;
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

    @PostConstruct
    public void init() {
        listadehrsmaquina.clear();
        totalsubtotal = 0;
        totaliva = 0;
        totalgeneral = 0;
        totalcantidad = 0;
        items= ejbFacade.usomaquinafechadesc();
    }
    
    public Usomaquinariapicadora prepareCreate() {
        selected = new Usomaquinariapicadora();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundlepicadora").getString("UsomaquinariapicadoraCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundlepicadora").getString("UsomaquinariapicadoraUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundlepicadora").getString("UsomaquinariapicadoraDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void listaprecios() {
        listadeprecio.clear();
        this.listadeprecio.add(hrsuso.getIdmaquinariapicadora().getPrecio1());
        this.listadeprecio.add(hrsuso.getIdmaquinariapicadora().getPrecio2());
    }

    public void anexaraHrs() {
        if (cantidad != 0) {
            double alicuota = 0;
            double iva = 0;
            double total = 0;
            Usomaquinariapicadora hrsmaquina = new Usomaquinariapicadora();
            hrsmaquina.setIdmaquinariapicadora(hrsuso.getIdmaquinariapicadora());
            hrsmaquina.setFecha(hrsuso.getFecha());
            hrsmaquina.setIdmaquinariapicadora(hrsuso.getIdmaquinariapicadora());
            hrsmaquina.setCantidad(cantidad);
            hrsmaquina.setPrecio(hrsuso.getPrecio());
            subtotal=cantidad * hrsuso.getPrecio();
            hrsmaquina.setSubtotal(subtotal);
            alicuota = hrsmaquina.getIdmaquinariapicadora().getIdgravamen().getAlicuota();
            iva = (subtotal * alicuota) / 100;
            total = subtotal + iva;
            hrsmaquina.setSubtotal(subtotal);
            hrsmaquina.setIva(iva);
            hrsmaquina.setTotal(total);
            hrsmaquina.setIdusomaquinariapicadora(id);
            this.listadehrsmaquina.add(hrsmaquina);
            id++;
//            detalles = ejbFacade.findAll();
            cantidad = 0;
  //          hrsmaquina.setIdmaquinariapicadora(null);
            totaltotal();
//            requer.setCodigo(null);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "No puede dejar el campo Cantidad en 0.0"));
        }

    }

     public double totaltotal() {
        double montotgeneral = 0;
        double montotiva = 0;
        double montotsubtotal = 0;
        double cantidadmt3 = 0;

        for (Usomaquinariapicadora requeri : listadehrsmaquina) {
            montotgeneral += requeri.getTotal();
            montotiva += requeri.getIva();
            montotsubtotal += requeri.getSubtotal();
            cantidadmt3 += requeri.getCantidad();
        }
        totalgeneral = montotgeneral;
        totaliva = montotiva;
        totalsubtotal = montotsubtotal;
        totalcantidad = cantidadmt3;

        return montotgeneral;
    }
     
      public void eliminar(Usomaquinariapicadora hrsusomaq) {
        listadehrsmaquina.remove(hrsusomaq.hashCode());
        int indice = 0;
        for (Usomaquinariapicadora requeri : listadehrsmaquina) {
            requeri.setIdusomaquinariapicadora(indice);
            indice++;
            id = indice;
        }
        if (hrsusomaq.hashCode() == 0) {
            id = 0;
        }
        totaltotal();
    }
    
    public void registrarHrsMaquina() {
        Articulo art = new Articulo();
        Date fecha = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String fechaCadena = hourFormat.format(fecha);
        DecimalFormat numformat = new DecimalFormat("#######.##");
        int cardinal=0;
        try {
            for (Usomaquinariapicadora rq : listadehrsmaquina) {
                selected=listadehrsmaquina.get(cardinal);
                cardinal++;
                ejbFacade.create(selected);
            }
//            String subject;
//            String ultimafactura = ejbFacade.u();
//            String fechafactu = formateador.format(factura.getFecha());
//            correo = "FACTURA NRO: " + ultimafactura
//                    + "  CONTROL: " + factura.getNumerocontrol()
//                    + "  USUARIO: " + factura.getIdusuario().getNombre()
//                    + "  DEPARTAMENTO: " + factura.getIdusuario().getIddepartamento().getDepartamento()
//                    + "  FECHA: " + fechafactu
//                    + "  CLIENTE: " + factura.getRifcliente().getRazonsocial()
//                    + "  RIF: " + factura.getRifcliente().getRifcliente()
//                    + "  SUBTOTAL: " + formatearnumero.format(factura.getBimponiblefact())
//                    + "  IVA: " + formatearnumero.format(factura.getIvafact())
//                    + "  TOTAL: " + formatearnumero.format(factura.getTotalgeneral())
//                    + "  OBSERVACIONES: " + factura.getObservacionesfact();
//
//            subject = "Emisión de Factura N° " + ultimafactura;
//            enviomail = new envioCorreo(correo, subject);
//            enviomail.start();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Las horas de maquinaria se registraron exitosamente", "Aviso"));
            limpiarListaArreglo();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al Grabar hotas Maquinaria", "Aviso"));
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }
    
    public void limpiarListaArreglo() {
        listadehrsmaquina.clear();
        totaltotal();
    }
    public List<Usomaquinariapicadora> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundlepicadora").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundlepicadora").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Usomaquinariapicadora getUsomaquinariapicadora(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Usomaquinariapicadora> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Usomaquinariapicadora> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Usomaquinariapicadora.class)
    public static class UsomaquinariapicadoraControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsomaquinariapicadoraController controller = (UsomaquinariapicadoraController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usomaquinariapicadoraController");
            return controller.getUsomaquinariapicadora(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Usomaquinariapicadora) {
                Usomaquinariapicadora o = (Usomaquinariapicadora) object;
                return getStringKey(o.getIdusomaquinariapicadora());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usomaquinariapicadora.class.getName()});
                return null;
            }
        }

    }

}
