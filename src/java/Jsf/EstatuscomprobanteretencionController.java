package Jsf;

import Modelo.Estatuscomprobanteretencion;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.EstatuscomprobanteretencionFacade;

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

@Named("estatuscomprobanteretencionController")
@SessionScoped
public class EstatuscomprobanteretencionController implements Serializable {

    @EJB
    private Jpa.EstatuscomprobanteretencionFacade ejbFacade;
    private List<Estatuscomprobanteretencion> items = null;
    private Estatuscomprobanteretencion selected;

    public EstatuscomprobanteretencionController() {
    }

    public Estatuscomprobanteretencion getSelected() {
        return selected;
    }

    public void setSelected(Estatuscomprobanteretencion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EstatuscomprobanteretencionFacade getFacade() {
        return ejbFacade;
    }

    public Estatuscomprobanteretencion prepareCreate() {
        selected = new Estatuscomprobanteretencion();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundletributos").getString("EstatuscomprobanteretencionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundletributos").getString("EstatuscomprobanteretencionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundletributos").getString("EstatuscomprobanteretencionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Estatuscomprobanteretencion> getItems() {
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

    public Estatuscomprobanteretencion getEstatuscomprobanteretencion(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Estatuscomprobanteretencion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Estatuscomprobanteretencion> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Estatuscomprobanteretencion.class)
    public static class EstatuscomprobanteretencionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EstatuscomprobanteretencionController controller = (EstatuscomprobanteretencionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "estatuscomprobanteretencionController");
            return controller.getEstatuscomprobanteretencion(getKey(value));
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
            if (object instanceof Estatuscomprobanteretencion) {
                Estatuscomprobanteretencion o = (Estatuscomprobanteretencion) object;
                return getStringKey(o.getIdestatuscomprobante());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Estatuscomprobanteretencion.class.getName()});
                return null;
            }
        }

    }

}
