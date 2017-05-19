package Jsf;

import Modelo.Usomaquinariapicadora;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.UsomaquinariapicadoraFacade;
import Jpa.UsomaquinariapicadoraFacadeLocal;

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

@Named("usomaquinariapicadoraController")
@SessionScoped
public class UsomaquinariapicadoraController implements Serializable {

    @EJB
    private Jpa.UsomaquinariapicadoraFacadeLocal ejbFacade;
    private List<Usomaquinariapicadora> items = null;
    private Usomaquinariapicadora selected;

    public UsomaquinariapicadoraController() {
    }

    public Usomaquinariapicadora getSelected() {
        return selected;
    }

    public void setSelected(Usomaquinariapicadora selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsomaquinariapicadoraFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Usomaquinariapicadora prepareCreate() {
        selected = new Usomaquinariapicadora();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundlepicadora").getString("UsomaquinariapicadoraCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundlepicadora").getString("UsomaquinariapicadoraUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundlepicadora").getString("UsomaquinariapicadoraDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Usomaquinariapicadora> getItems() {
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

    public Usomaquinariapicadora getUsomaquinariapicadora(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Usomaquinariapicadora> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Usomaquinariapicadora> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Usomaquinariapicadora.class)
    public static class UsomaquinariapicadoraControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsomaquinariapicadoraController controller = (UsomaquinariapicadoraController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usomaquinariapicadoraController");
            return controller.getUsomaquinariapicadora(getKey(value));
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
            if (object instanceof Usomaquinariapicadora) {
                Usomaquinariapicadora o = (Usomaquinariapicadora) object;
                return getStringKey(o.getIdusomaquinariapicadora());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usomaquinariapicadora.class.getName()});
                return null;
            }
        }

    }

}
