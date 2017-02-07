package Jsf;

import Jpa.CuentabancariaFacadeLocal;
import Modelo.Otroingreso;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.OtroingresoFacade;
import Jpa.OtroingresoFacadeLocal;
import Modelo.Cuentabancaria;
import Modelo.Usuario;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;

@Named("otroingresoController")
@ViewScoped
public class OtroingresoController implements Serializable {

    @EJB
    private Jpa.OtroingresoFacadeLocal ejbFacade;
    @EJB
    private CuentabancariaFacadeLocal cuentabancariaEJB;
    private List<Otroingreso> items = null;
    private Otroingreso selected;
    private Cuentabancaria cuentabancaria;
    private double montoingreso;
    private Otroingreso ingreso = new Otroingreso();
    private Date fechaactual = new Date();
    private Usuario usa;
    private RequerimientosController requer = new RequerimientosController();

    public OtroingresoController() {
    }

    public Otroingreso getSelected() {
        return selected;
    }

    public Otroingreso getIngreso() {
        return ingreso;
    }

    public void setIngreso(Otroingreso ingreso) {
        this.ingreso = ingreso;
    }

    public double getMontoingreso() {
        return montoingreso;
    }

    public void setMontoingreso(double montoingreso) {
        this.montoingreso = montoingreso;
    }

    public void setSelected(Otroingreso selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private OtroingresoFacadeLocal getFacade() {
        return ejbFacade;
    }

    @PostConstruct
    public void init() {
        ingreso.setFechaingreso(fechaactual);

    }

    public Otroingreso prepareCreate() {
        selected = new Otroingreso();
        initializeEmbeddableKey();
        return selected;
    }

    public Cuentabancaria getCuentabancaria() {
        return cuentabancaria;
    }

    public void setCuentabancaria(Cuentabancaria cuentabancaria) {
        this.cuentabancaria = cuentabancaria;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundleingreso").getString("OtroingresoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundleingreso").getString("OtroingresoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundleingreso").getString("OtroingresoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Otroingreso> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundleingreso").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundleingreso").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Otroingreso getOtroingreso(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Otroingreso> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Otroingreso> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Otroingreso.class)
    public static class OtroingresoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OtroingresoController controller = (OtroingresoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "otroingresoController");
            return controller.getOtroingreso(getKey(value));
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
            if (object instanceof Otroingreso) {
                Otroingreso o = (Otroingreso) object;
                return getStringKey(o.getIdotroingreso());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Otroingreso.class.getName()});
                return null;
            }
        }
    }

    public void registrar() {
        try {
            cuentabancaria = CobroventasController.cobro.getIdcuentabancaria();
            ingreso.setIdcuentabancaria(cuentabancaria);
            usa = requer.getUsuario();
            ingreso.setIdusuario(usa);
            double saldoactualbanco = 0;
            saldoactualbanco = montoingreso + cuentabancaria.getSaldo();
            cuentabancaria.setSaldo(saldoactualbanco);
            ingreso.setMontoingresado(montoingreso);
            ejbFacade.create(ingreso);
            cuentabancariaEJB.edit(cuentabancaria);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Su Ingreso fue Almacenado", "Su Ingreso fue Almacenado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al Grabar Ingreso", "Error al Grabar Ingreso"));
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }

}
