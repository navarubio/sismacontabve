package Jsf;

import Modelo.Detallelibrodiario;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.DetallelibrodiarioFacade;
import Jpa.DetallelibrodiarioFacadeLocal;

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
import javax.faces.view.ViewScoped;

@Named("detallelibrodiarioController")
@ViewScoped
public class DetallelibrodiarioController implements Serializable {

    @EJB
    private Jpa.DetallelibrodiarioFacadeLocal ejbFacade;
    private List<Detallelibrodiario> items = null;
    private Detallelibrodiario selected;

    public DetallelibrodiarioController() {
    }

    public Detallelibrodiario getSelected() {
        return selected;
    }

    public void setSelected(Detallelibrodiario selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DetallelibrodiarioFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Detallelibrodiario prepareCreate() {
        selected = new Detallelibrodiario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundlecontabilidad").getString("DetallelibrodiarioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundlecontabilidad").getString("DetallelibrodiarioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundlecontabilidad").getString("DetallelibrodiarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Detallelibrodiario> getItems() {
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

    public Detallelibrodiario getDetallelibrodiario(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Detallelibrodiario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Detallelibrodiario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Detallelibrodiario.class)
    public static class DetallelibrodiarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetallelibrodiarioController controller = (DetallelibrodiarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detallelibrodiarioController");
            return controller.getDetallelibrodiario(getKey(value));
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
            if (object instanceof Detallelibrodiario) {
                Detallelibrodiario o = (Detallelibrodiario) object;
                return getStringKey(o.getIddetallelibrodiario());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Detallelibrodiario.class.getName()});
                return null;
            }
        }

    }

}
