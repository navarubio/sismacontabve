package Jsf;

import Modelo.Movimientoinventariopicadora;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.MovimientoinventariopicadoraFacade;
import Jpa.MovimientoinventariopicadoraFacadeLocal;

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

@Named("movimientoinventariopicadoraController")
@SessionScoped
public class MovimientoinventariopicadoraController implements Serializable {

    @EJB
    private Jpa.MovimientoinventariopicadoraFacadeLocal ejbFacade;
    private List<Movimientoinventariopicadora> items = null;
    private Movimientoinventariopicadora selected;

    public MovimientoinventariopicadoraController() {
    }

    public Movimientoinventariopicadora getSelected() {
        return selected;
    }

    public void setSelected(Movimientoinventariopicadora selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MovimientoinventariopicadoraFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Movimientoinventariopicadora prepareCreate() {
        selected = new Movimientoinventariopicadora();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundlepicadora").getString("MovimientoinventariopicadoraCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundlepicadora").getString("MovimientoinventariopicadoraUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundlepicadora").getString("MovimientoinventariopicadoraDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Movimientoinventariopicadora> getItems() {
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

    public Movimientoinventariopicadora getMovimientoinventariopicadora(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Movimientoinventariopicadora> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Movimientoinventariopicadora> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Movimientoinventariopicadora.class)
    public static class MovimientoinventariopicadoraControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MovimientoinventariopicadoraController controller = (MovimientoinventariopicadoraController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "movimientoinventariopicadoraController");
            return controller.getMovimientoinventariopicadora(getKey(value));
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
            if (object instanceof Movimientoinventariopicadora) {
                Movimientoinventariopicadora o = (Movimientoinventariopicadora) object;
                return getStringKey(o.getIdmovimientoinventariopicadora());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Movimientoinventariopicadora.class.getName()});
                return null;
            }
        }

    }

}
