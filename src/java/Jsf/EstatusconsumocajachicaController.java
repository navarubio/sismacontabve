package Jsf;

import Modelo.Estatusconsumocajachica;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.EstatusconsumocajachicaFacade;

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

@Named("estatusconsumocajachicaController")
@SessionScoped
public class EstatusconsumocajachicaController implements Serializable {

    @EJB
    private Jpa.EstatusconsumocajachicaFacade ejbFacade;
    private List<Estatusconsumocajachica> items = null;
    private Estatusconsumocajachica selected;

    public EstatusconsumocajachicaController() {
    }

    public Estatusconsumocajachica getSelected() {
        return selected;
    }

    public void setSelected(Estatusconsumocajachica selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EstatusconsumocajachicaFacade getFacade() {
        return ejbFacade;
    }

    public Estatusconsumocajachica prepareCreate() {
        selected = new Estatusconsumocajachica();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundlecajachica").getString("EstatusconsumocajachicaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundlecajachica").getString("EstatusconsumocajachicaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundlecajachica").getString("EstatusconsumocajachicaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Estatusconsumocajachica> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundlecajachica").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundlecajachica").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Estatusconsumocajachica getEstatusconsumocajachica(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Estatusconsumocajachica> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Estatusconsumocajachica> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Estatusconsumocajachica.class)
    public static class EstatusconsumocajachicaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EstatusconsumocajachicaController controller = (EstatusconsumocajachicaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "estatusconsumocajachicaController");
            return controller.getEstatusconsumocajachica(getKey(value));
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
            if (object instanceof Estatusconsumocajachica) {
                Estatusconsumocajachica o = (Estatusconsumocajachica) object;
                return getStringKey(o.getIdestatusconsumocajachica());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Estatusconsumocajachica.class.getName()});
                return null;
            }
        }

    }

}
