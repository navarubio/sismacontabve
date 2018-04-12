package Jsf;

import Modelo.Centrodecosto;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.CentrodecostoFacade;
import Jpa.CentrodecostoFacadeLocal;

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

@Named("centrodecostoController")
@SessionScoped
public class CentrodecostoController implements Serializable {

    @EJB
    private Jpa.CentrodecostoFacadeLocal ejbFacade;
    private List<Centrodecosto> items = null;
    private Centrodecosto selected;

    public CentrodecostoController() {
    }

    public Centrodecosto getSelected() {
        return selected;
    }

    public void setSelected(Centrodecosto selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CentrodecostoFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Centrodecosto prepareCreate() {
        selected = new Centrodecosto();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundlecentrocosto").getString("CentrodecostoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundlecentrocosto").getString("CentrodecostoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundlecentrocosto").getString("CentrodecostoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Centrodecosto> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundlecentrocosto").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundlecentrocosto").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Centrodecosto getCentrodecosto(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Centrodecosto> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Centrodecosto> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Centrodecosto.class)
    public static class CentrodecostoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CentrodecostoController controller = (CentrodecostoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "centrodecostoController");
            return controller.getCentrodecosto(getKey(value));
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
            if (object instanceof Centrodecosto) {
                Centrodecosto o = (Centrodecosto) object;
                return getStringKey(o.getCodigocentrocosto());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Centrodecosto.class.getName()});
                return null;
            }
        }

    }

}
