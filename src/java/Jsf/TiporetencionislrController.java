package Jsf;

import Modelo.Tiporetencionislr;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.TiporetencionislrFacadeLocal;

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

@Named("tiporetencionislrController")
@SessionScoped
public class TiporetencionislrController implements Serializable {

    @EJB
    private Jpa.TiporetencionislrFacadeLocal ejbFacade;
    private List<Tiporetencionislr> items = null;
    private Tiporetencionislr selected;

    public TiporetencionislrController() {
    }

    public Tiporetencionislr getSelected() {
        return selected;
    }

    public void setSelected(Tiporetencionislr selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TiporetencionislrFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Tiporetencionislr prepareCreate() {
        selected = new Tiporetencionislr();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundletributos").getString("TiporetencionislrCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundletributos").getString("TiporetencionislrUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundletributos").getString("TiporetencionislrDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Tiporetencionislr> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundletributos").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundletributos").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Tiporetencionislr getTiporetencionislr(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Tiporetencionislr> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Tiporetencionislr> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Tiporetencionislr.class)
    public static class TiporetencionislrControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TiporetencionislrController controller = (TiporetencionislrController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tiporetencionislrController");
            return controller.getTiporetencionislr(getKey(value));
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
            if (object instanceof Tiporetencionislr) {
                Tiporetencionislr o = (Tiporetencionislr) object;
                return getStringKey(o.getIdtiporetencionislr());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Tiporetencionislr.class.getName()});
                return null;
            }
        }

    }

}
