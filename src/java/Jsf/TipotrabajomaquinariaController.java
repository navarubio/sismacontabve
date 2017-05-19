package Jsf;

import Modelo.Tipotrabajomaquinaria;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.TipotrabajomaquinariaFacade;
import Jpa.TipotrabajomaquinariaFacadeLocal;

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

@Named("tipotrabajomaquinariaController")
@SessionScoped
public class TipotrabajomaquinariaController implements Serializable {

    @EJB
    private Jpa.TipotrabajomaquinariaFacadeLocal ejbFacade;
    private List<Tipotrabajomaquinaria> items = null;
    private Tipotrabajomaquinaria selected;

    public TipotrabajomaquinariaController() {
    }

    public Tipotrabajomaquinaria getSelected() {
        return selected;
    }

    public void setSelected(Tipotrabajomaquinaria selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TipotrabajomaquinariaFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Tipotrabajomaquinaria prepareCreate() {
        selected = new Tipotrabajomaquinaria();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundlepicadora").getString("TipotrabajomaquinariaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundlepicadora").getString("TipotrabajomaquinariaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundlepicadora").getString("TipotrabajomaquinariaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Tipotrabajomaquinaria> getItems() {
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

    public Tipotrabajomaquinaria getTipotrabajomaquinaria(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Tipotrabajomaquinaria> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Tipotrabajomaquinaria> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Tipotrabajomaquinaria.class)
    public static class TipotrabajomaquinariaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipotrabajomaquinariaController controller = (TipotrabajomaquinariaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipotrabajomaquinariaController");
            return controller.getTipotrabajomaquinaria(getKey(value));
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
            if (object instanceof Tipotrabajomaquinaria) {
                Tipotrabajomaquinaria o = (Tipotrabajomaquinaria) object;
                return getStringKey(o.getIdtipotrabajomaquinaria());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Tipotrabajomaquinaria.class.getName()});
                return null;
            }
        }

    }

}
