package Jsf;

import Modelo.Detalleproduccionpicadora;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.DetalleproduccionpicadoraFacade;
import Jpa.DetalleproduccionpicadoraFacadeLocal;

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

@Named("detalleproduccionpicadoraController")
@SessionScoped
public class DetalleproduccionpicadoraController implements Serializable {

    @EJB
    private Jpa.DetalleproduccionpicadoraFacadeLocal ejbFacade;
    private List<Detalleproduccionpicadora> items = null;
    private Detalleproduccionpicadora selected;

    public DetalleproduccionpicadoraController() {
    }

    public Detalleproduccionpicadora getSelected() {
        return selected;
    }

    public void setSelected(Detalleproduccionpicadora selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DetalleproduccionpicadoraFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Detalleproduccionpicadora prepareCreate() {
        selected = new Detalleproduccionpicadora();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundlepicadora").getString("DetalleproduccionpicadoraCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundlepicadora").getString("DetalleproduccionpicadoraUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundlepicadora").getString("DetalleproduccionpicadoraDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Detalleproduccionpicadora> getItems() {
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

    public Detalleproduccionpicadora getDetalleproduccionpicadora(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Detalleproduccionpicadora> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Detalleproduccionpicadora> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Detalleproduccionpicadora.class)
    public static class DetalleproduccionpicadoraControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetalleproduccionpicadoraController controller = (DetalleproduccionpicadoraController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detalleproduccionpicadoraController");
            return controller.getDetalleproduccionpicadora(getKey(value));
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
            if (object instanceof Detalleproduccionpicadora) {
                Detalleproduccionpicadora o = (Detalleproduccionpicadora) object;
                return getStringKey(o.getIddetalleproduccionpicadora());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Detalleproduccionpicadora.class.getName()});
                return null;
            }
        }

    }

}
