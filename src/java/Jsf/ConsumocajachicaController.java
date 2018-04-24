package Jsf;

import Modelo.Consumocajachica;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.ConsumocajachicaFacade;
import Jpa.ConsumocajachicaFacadeLocal;
import Jpa.DetalleconsumocajachicaFacadeLocal;
import Jpa.ReposicioncajachicaFacadeLocal;
import Jpa.ReposicionconsumosFacadeLocal;
import Modelo.Detalleconsumocajachica;
import Modelo.Reposicioncajachica;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named("consumocajachicaController")
@ViewScoped
public class ConsumocajachicaController implements Serializable {

    @EJB
    private Jpa.ConsumocajachicaFacadeLocal ejbFacade;
    @EJB
    private DetalleconsumocajachicaFacadeLocal detalleconsumoEJB;
    @EJB
    private ReposicionconsumosFacadeLocal reposicionEJB;
    
    private List<Consumocajachica> items = null;
    private List<Detalleconsumocajachica> detalles = null;
    private Consumocajachica selected;
    private String totalgeneralform;
    private String totalivaform;
    private String totalsubtotalform;
    private Consumocajachica consumoseleccionado;
    DecimalFormat formatearnumero = new DecimalFormat("###,###.##");
    @Inject
    private ConsumoscajachicaController consumosCotroler;
    @Inject
    private Consumocajachica consumoefectuado;
    @Inject
    private Reposicioncajachica reposicionefectuada;    

    public ConsumocajachicaController() {
    }

    public Consumocajachica getSelected() {
        return selected;
    }

    public void setSelected(Consumocajachica selected) {
        this.selected = selected;
        this.consumoefectuado = selected;
        if (selected != null) {
            asignar();
        }
    }

    public Consumocajachica getConsumoseleccionado() {
        return consumoseleccionado;
    }

    public void setConsumoseleccionado(Consumocajachica consumoseleccionado) {
        if (consumoseleccionado != null) {
            this.consumoseleccionado = consumoseleccionado;
            this.selected = consumoseleccionado;
            asignar();
        }

    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ConsumocajachicaFacadeLocal getFacade() {
        return ejbFacade;
    }

    public ConsumoscajachicaController getConsumosCotroler() {
        return consumosCotroler;
    }

    public void setConsumosCotroler(ConsumoscajachicaController consumosCotroler) {
        this.consumosCotroler = consumosCotroler;
    }

    public List<Detalleconsumocajachica> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalleconsumocajachica> detalles) {
        this.detalles = detalles;
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

    public Consumocajachica getConsumoefectuado() {
        return consumoefectuado;
    }

    public void setConsumoefectuado(Consumocajachica consumoefectuado) {
        this.consumoefectuado = consumoefectuado;
    }

    public Reposicioncajachica getReposicionefectuada() {
        return reposicionefectuada;
    }

    public void setReposicionefectuada(Reposicioncajachica reposicionefectuada) {
        this.reposicionefectuada = reposicionefectuada;
    }
    
    

    public void asignar() {

        detalles = detalleconsumoEJB.detallesxConsumo(consumoseleccionado.getIdconsumocajachica());
        reposicionefectuada=reposicionEJB.devolverReposicionxConsumo(consumoseleccionado);
        if (detalles.isEmpty()){
            double montosencero=0;
            double montox=consumoseleccionado.getIdcajachica().getMontoasignado();
            consumosCotroler.setTotalgeneral(montox);
            totalgeneralform = formatearnumero.format(montox);
            totalivaform=formatearnumero.format(montosencero);
            totalsubtotalform=formatearnumero.format(montosencero);
        }else{
            consumosCotroler.setListadetalles(detalles);
            consumosCotroler.totaltotal();
            this.totalgeneralform = consumosCotroler.getTotalgeneralform();
            this.totalivaform = consumosCotroler.getTotalivaform();
            this.totalsubtotalform = consumosCotroler.getTotalsubtotalform();
        }
    }

    public Consumocajachica prepareCreate() {
        selected = new Consumocajachica();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundlecajachica").getString("ConsumocajachicaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundlecajachica").getString("ConsumocajachicaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundlecajachica").getString("ConsumocajachicaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Consumocajachica> getItems() {
        if (items == null) {
            items = getFacade().consumoscajachicaAll();
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundlecajachica").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundlecajachica").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Consumocajachica getConsumocajachica(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Consumocajachica> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Consumocajachica> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Consumocajachica.class)
    public static class ConsumocajachicaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConsumocajachicaController controller = (ConsumocajachicaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "consumocajachicaController");
            return controller.getConsumocajachica(getKey(value));
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
            if (object instanceof Consumocajachica) {
                Consumocajachica o = (Consumocajachica) object;
                return getStringKey(o.getIdconsumocajachica());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Consumocajachica.class.getName()});
                return null;
            }
        }

    }

}
