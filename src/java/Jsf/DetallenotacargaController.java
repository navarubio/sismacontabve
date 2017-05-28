package Jsf;

import Modelo.Detallenotacarga;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.DetallenotacargaFacade;
import Jpa.DetallenotacargaFacadeLocal;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("detallenotacargaController")
@SessionScoped
public class DetallenotacargaController implements Serializable {

    @EJB
    private Jpa.DetallenotacargaFacadeLocal ejbFacade;
    private List<Detallenotacarga> items = null;
    private Detallenotacarga selected;

    public DetallenotacargaController() {
    }

    public Detallenotacarga getSelected() {
        return selected;
    }

    public void setSelected(Detallenotacarga selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DetallenotacargaFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Detallenotacarga prepareCreate() {
        selected = new Detallenotacarga();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundlenotacarga").getString("DetallenotacargaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundlenotacarga").getString("DetallenotacargaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundlenotacarga").getString("DetallenotacargaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Detallenotacarga> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundlenotacarga").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundlenotacarga").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Detallenotacarga getDetallenotacarga(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Detallenotacarga> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Detallenotacarga> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Detallenotacarga.class)
    public static class DetallenotacargaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetallenotacargaController controller = (DetallenotacargaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detallenotacargaController");
            return controller.getDetallenotacarga(getKey(value));
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
            if (object instanceof Detallenotacarga) {
                Detallenotacarga o = (Detallenotacarga) object;
                return getStringKey(o.getIddetallenotacarga());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Detallenotacarga.class.getName()});
                return null;
            }
        }

    }

}
