package Jsf;

import Modelo.Produccionpicadora;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.ProduccionpicadoraFacade;
import Jpa.ProduccionpicadoraFacadeLocal;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;

@Named("produccionpicadoraController")
@ViewScoped
public class ProduccionpicadoraController implements Serializable {

    @EJB
    private Jpa.ProduccionpicadoraFacadeLocal ejbFacade;
    private List<Produccionpicadora> items = null;
    private Produccionpicadora selected;

    public ProduccionpicadoraController() {
    }

    public Produccionpicadora getSelected() {
        return selected;
    }

    public void setSelected(Produccionpicadora selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ProduccionpicadoraFacadeLocal getFacade() {
        return ejbFacade;
    }
    
    @PostConstruct
    public void init() {
        selected = new Produccionpicadora();
        items = ejbFacade.produccionfechadesc();
    }
    public Produccionpicadora prepareCreate() {
        selected = new Produccionpicadora();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundlepicadora").getString("ProduccionpicadoraCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundlepicadora").getString("ProduccionpicadoraUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundlepicadora").getString("ProduccionpicadoraDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Produccionpicadora> getItems() {
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

    public Produccionpicadora getProduccionpicadora(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Produccionpicadora> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Produccionpicadora> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Produccionpicadora.class)
    public static class ProduccionpicadoraControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProduccionpicadoraController controller = (ProduccionpicadoraController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "produccionpicadoraController");
            return controller.getProduccionpicadora(getKey(value));
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
            if (object instanceof Produccionpicadora) {
                Produccionpicadora o = (Produccionpicadora) object;
                return getStringKey(o.getIdproduccionpicadora());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Produccionpicadora.class.getName()});
                return null;
            }
        }

    }

}
