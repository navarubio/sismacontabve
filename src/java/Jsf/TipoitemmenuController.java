package Jsf;

import Modelo.Tipoitemmenu;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.TipoitemmenuFacade;
import Jpa.TipoitemmenuFacadeLocal;

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

@Named("tipoitemmenuController")
@SessionScoped
public class TipoitemmenuController implements Serializable {

    @EJB
    private Jpa.TipoitemmenuFacadeLocal ejbFacade;
    private List<Tipoitemmenu> items = null;
    private Tipoitemmenu selected;

    public TipoitemmenuController() {
    }

    public Tipoitemmenu getSelected() {
        return selected;
    }

    public void setSelected(Tipoitemmenu selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TipoitemmenuFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Tipoitemmenu prepareCreate() {
        selected = new Tipoitemmenu();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundlemenu").getString("TipoitemmenuCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundlemenu").getString("TipoitemmenuUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundlemenu").getString("TipoitemmenuDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Tipoitemmenu> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundlemenu").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundlemenu").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Tipoitemmenu getTipoitemmenu(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Tipoitemmenu> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Tipoitemmenu> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Tipoitemmenu.class)
    public static class TipoitemmenuControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoitemmenuController controller = (TipoitemmenuController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoitemmenuController");
            return controller.getTipoitemmenu(getKey(value));
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
            if (object instanceof Tipoitemmenu) {
                Tipoitemmenu o = (Tipoitemmenu) object;
                return getStringKey(o.getIdtipoitemmenu());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Tipoitemmenu.class.getName()});
                return null;
            }
        }

    }

}
