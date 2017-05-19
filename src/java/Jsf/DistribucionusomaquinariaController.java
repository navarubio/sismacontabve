package Jsf;

import Modelo.Distribucionusomaquinaria;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.DistribucionusomaquinariaFacade;
import Jpa.DistribucionusomaquinariaFacadeLocal;

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

@Named("distribucionusomaquinariaController")
@SessionScoped
public class DistribucionusomaquinariaController implements Serializable {

    @EJB
    private Jpa.DistribucionusomaquinariaFacadeLocal ejbFacade;
    private List<Distribucionusomaquinaria> items = null;
    private Distribucionusomaquinaria selected;

    public DistribucionusomaquinariaController() {
    }

    public Distribucionusomaquinaria getSelected() {
        return selected;
    }

    public void setSelected(Distribucionusomaquinaria selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DistribucionusomaquinariaFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Distribucionusomaquinaria prepareCreate() {
        selected = new Distribucionusomaquinaria();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundlepicadora").getString("DistribucionusomaquinariaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundlepicadora").getString("DistribucionusomaquinariaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundlepicadora").getString("DistribucionusomaquinariaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Distribucionusomaquinaria> getItems() {
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

    public Distribucionusomaquinaria getDistribucionusomaquinaria(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Distribucionusomaquinaria> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Distribucionusomaquinaria> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Distribucionusomaquinaria.class)
    public static class DistribucionusomaquinariaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DistribucionusomaquinariaController controller = (DistribucionusomaquinariaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "distribucionusomaquinariaController");
            return controller.getDistribucionusomaquinaria(getKey(value));
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
            if (object instanceof Distribucionusomaquinaria) {
                Distribucionusomaquinaria o = (Distribucionusomaquinaria) object;
                return getStringKey(o.getIddistribucionusomaquinaria());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Distribucionusomaquinaria.class.getName()});
                return null;
            }
        }

    }

}
