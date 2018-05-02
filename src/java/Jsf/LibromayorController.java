package Jsf;

import Modelo.Libromayor;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.LibromayorFacade;
import Jpa.LibromayorFacadeLocal;
import Modelo.Libromayorcompuesto;
import Modelo.Movimientobancario;
import Modelo.Plandecuenta;

import java.io.Serializable;
import java.text.DecimalFormat;
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
import javax.faces.view.ViewScoped;

@Named("libromayorController")
@ViewScoped
public class LibromayorController implements Serializable {

    @EJB
    private Jpa.LibromayorFacadeLocal ejbFacade;
    @EJB
    private Jpa.LibromayorcompuestoFacadeLocal libromayorcompuestoEJB;

    private List<Libromayor> items = null;
    private List<Libromayorcompuesto> itemsfiltrados = null;
    private Libromayor selected;
    private Plandecuenta cuentaseleccionada;
    private Integer cuentacontab;
    private Date fechadesde;
    private Date fechahasta;
    private List<Libromayor> listado = new ArrayList();

    public LibromayorController() {
    }

    public Libromayor getSelected() {
        return selected;
    }

    public void setSelected(Libromayor selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private LibromayorFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Plandecuenta getCuentaseleccionada() {
        return cuentaseleccionada;
    }

    public void setCuentaseleccionada(Plandecuenta cuentaseleccionada) {
        this.cuentaseleccionada = cuentaseleccionada;
    }

    public Date getFechadesde() {
        return fechadesde;
    }

    public void setFechadesde(Date fechadesde) {
        this.fechadesde = fechadesde;
    }

    public Date getFechahasta() {
        return fechahasta;
    }

    public void setFechahasta(Date fechahasta) {
        this.fechahasta = fechahasta;
    }

    public List<Libromayorcompuesto> getItemsfiltrados() {
        return itemsfiltrados;
    }

    public void setItemsfiltrados(List<Libromayorcompuesto> itemsfiltrados) {
        this.itemsfiltrados = itemsfiltrados;
    }

    public Integer getCuentacontab() {
        return cuentacontab;
    }

    public void setCuentacontab(Integer cuentacontab) {
        this.cuentacontab = cuentacontab;
    }

    @PostConstruct
    public void init() {

        if (itemsfiltrados != null) {
            itemsfiltrados.clear();
        }
    }

    public void actualizar() {
        itemsfiltrados = libromayorcompuestoEJB.buscarmayorporfecha(cuentacontab, fechadesde, fechahasta);
    }

    public void conciliar() {
//        listaerrores1.clear();
        itemsfiltrados = libromayorcompuestoEJB.buscarmayorporfecha(cuentacontab, fechadesde, fechahasta);
        if (listado != null) {
            listado.clear();
        }
        int idlibromay = 0;
        Libromayor librmay;
        for (Libromayorcompuesto mayor : itemsfiltrados) {
            idlibromay = mayor.getIdlibromayor();
            librmay = ejbFacade.buscarLibro(idlibromay);
            listado.add(librmay);
        }

//        conciliarSaldos();
    }

    /*   public void conciliarSaldos() {
     double saldoant = 0;
     double saldoact = 0;
     double saldopost = 0;
     int marcador = 0;
     int sinnovedad = 0;
     String cadena = null;
     String cadenaactual;
     String anterior;
     for (Movimientobancario seleccion : itemsfiltrados) {
     saldoant = seleccion.getSaldoanterior();
     anterior = formatearnumero.format(saldoant);
     cadena = formatearnumero.format(saldopost);
     if (marcador == 0) {
     cadena = anterior;
     }

     if (anterior.equals(cadena)) {
     if (seleccion.getDebito() == null) {
     saldopost = saldoant + seleccion.getCredito();
     } else {
     saldopost = saldoant - seleccion.getDebito();

     }
     cadena = formatearnumero.format(saldopost);
     saldoact = seleccion.getSaldoactual();
     cadenaactual = formatearnumero.format(saldoact);

     if (cadena.equals(cadenaactual)) {

     } else {
     seleccion.setSaldoactual(saldopost);
     sinnovedad = 1;
     }
     listaerrores1.add(seleccion);
     } else {
     saldoant = saldopost;
     seleccion.setSaldoanterior(saldopost);
     if (seleccion.getDebito() == null) {
     saldopost = saldoant + seleccion.getCredito();
     } else {
     saldopost = saldoant - seleccion.getDebito();

     }
     cadena = formatearnumero.format(saldopost);
     saldoact = seleccion.getSaldoactual();
     cadenaactual = formatearnumero.format(saldoact);

     if (cadena.equals(cadenaactual)) {

     } else {
     seleccion.setSaldoactual(saldopost);
     sinnovedad = 1;
     }
     listaerrores1.add(seleccion);
     }
     if (marcador == 0) {
     marcador++;
     }
     }
     if (marcador == 0) {
     marcador++;
     }
     if (sinnovedad == 0) {
     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "LOS SALDOS ESTAN AJUSTADOS"));
     } else {
     corregirSaldos();
     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "LOS SALDOS FUERON AJUSTADOS SATISFACTORIAMENTE"));
     }

     }

     public void corregirSaldos() {
     Movimientobancario movimientoListo;
     for (Movimientobancario movicorreg : listaerrores1) {
     movimientoListo = movicorreg;
     movimientobancarioEJB.edit(movimientoListo);
     }
     }
     */
    public Libromayor prepareCreate() {
        selected = new Libromayor();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundlecontabilidad").getString("LibromayorCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundlecontabilidad").getString("LibromayorUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundlecontabilidad").getString("LibromayorDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Libromayor> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundlecontabilidad").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundlecontabilidad").getString("PersistenceErrorOccured"));
            }
        }
    }

    public String getTotalDebe() {
        double total = 0;

        for (Libromayor debe : getItems()) {
            total += debe.getDebe();
        }
        return new DecimalFormat("###,###.##").format(total);
    }

    public String getTotalHaber() {
        double total = 0;

        for (Libromayor haber : getItems()) {
            total += haber.getHaber();
        }
        return new DecimalFormat("###,###.##").format(total);
    }

    public String getMayTotalDebe() {
        double total = 0;
        if (itemsfiltrados != null) {
            for (Libromayorcompuesto debe : itemsfiltrados) {
                total += debe.getDebe();
            }
        }
        return new DecimalFormat("###,###.##").format(total);
    }

    public String getMayTotalHaber() {
        double total = 0;
        if (itemsfiltrados != null) {
            for (Libromayorcompuesto haber : itemsfiltrados) {
                total += haber.getHaber();
            }
        }
        return new DecimalFormat("###,###.##").format(total);
    }

    public Libromayor getLibromayor(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Libromayor> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Libromayor> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Libromayor.class)
    public static class LibromayorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LibromayorController controller = (LibromayorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "libromayorController");
            return controller.getLibromayor(getKey(value));
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
            if (object instanceof Libromayor) {
                Libromayor o = (Libromayor) object;
                return getStringKey(o.getIdlibromayor());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Libromayor.class.getName()});
                return null;
            }
        }

    }

}
