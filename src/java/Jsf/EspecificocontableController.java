package Jsf;

import Modelo.Especificocontable;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.EspecificocontableFacade;
import Jpa.EspecificocontableFacadeLocal;
import Jpa.PlandecuentaFacadeLocal;
import Modelo.Empresa;
import Modelo.Grupocontable;
import Modelo.Plandecuenta;
import Modelo.Subgrupocontable;

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
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named("especificocontableController")
@SessionScoped
public class EspecificocontableController implements Serializable {

    @EJB
    private Jpa.EspecificocontableFacadeLocal ejbFacade;
    @EJB
    private Jpa.SubgrupocontableFacadeLocal ejbFacadeSG;
    @EJB
    private Jpa.GrupocontableFacadeLocal ejbFacadeG;
    @EJB
    private PlandecuentaFacadeLocal plandecuentaEJB;

    private List<Especificocontable> items = null;
    private List<Especificocontable> itemsmodelo = null;
    private Especificocontable selected;
    @Inject
    private RequerimientosController requerimientosController;
    @Inject
    private Especificocontable especif;
    @Inject
    private Plandecuenta plandecuenta;

    private List<Grupocontable> lstGrupos;
    private List<Subgrupocontable> lstSubgrupos;

    public EspecificocontableController() {
    }

    @PostConstruct
    public void init() {
        lstGrupos = ejbFacadeG.grupocontableAll(requerimientosController.getEmpresa());
    }

    public Especificocontable getSelected() {
        return selected;
    }

    public void setSelected(Especificocontable selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EspecificocontableFacadeLocal getFacade() {
        return ejbFacade;
    }

    public List<Especificocontable> getItemsmodelo() {
        return itemsmodelo;
    }

    public void setItemsmodelo(List<Especificocontable> itemsmodelo) {
        this.itemsmodelo = itemsmodelo;
    }

    public List<Grupocontable> getLstGrupos() {
        return lstGrupos;
    }

    public void setLstGrupos(List<Grupocontable> lstGrupos) {
        this.lstGrupos = lstGrupos;
    }

    public List<Subgrupocontable> getLstSubgrupos() {
        return lstSubgrupos;
    }

    public void setLstSubgrupos(List<Subgrupocontable> lstSubgrupos) {
        this.lstSubgrupos = lstSubgrupos;
    }

    public Especificocontable prepareCreate() {
        selected = new Especificocontable();
        selected.setIdempresa(requerimientosController.getEmpresa().getIdempresa());
        selected.setCodigocuenta(0);
        initializeEmbeddableKey();
        return selected;
    }

    public List<Subgrupocontable> refrescarSubgrupos() {
        try {
            if (selected != null) {
                lstSubgrupos = ejbFacadeSG.subgxGrupo(selected.getIdgrupocontable(), requerimientosController.getEmpresa());
            }
        } catch (Exception e) {
        }
        return lstSubgrupos;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundlecontable").getString("EspecificocontableCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundlecontable").getString("EspecificocontableUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundlecontable").getString("EspecificocontableDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Especificocontable> getItems() {
        if (items == null) {
            items = getFacade().especificocontableAll(requerimientosController.getEmpresa());
        }
        return items;
    }

    public List<Especificocontable> getItemsModelo() {
        if (itemsmodelo == null) {
            itemsmodelo = getFacade().especificocontableModelo();
        }
        return itemsmodelo;
    }

    public void clonarEspecifico() {
        try {
            Empresa empresa = requerimientosController.getEmpresa();
            int estructura=0;
            for (Especificocontable espmodelo : itemsmodelo) {
                especif = espmodelo;
                especif.setIdempresa(empresa.getIdempresa());
                ejbFacade.create(especif);
                
                plandecuenta.setCodigocuenta(espmodelo.getCodigocuenta());
                plandecuenta.setIdgrupocontable(espmodelo.getIdgrupocontable());
                plandecuenta.setIdsubgrupocontable(espmodelo.getIdsubgrupocontable());
                plandecuenta.setIdespecificocontable(espmodelo.getIdespecificocontable());
                plandecuenta.setIdsubespecificocontable(estructura);
                plandecuenta.setIdgeneralcuenta(estructura);
                plandecuenta.setIdempresa(empresa.getIdempresa());
                plandecuenta.setDescripcioncuenta(espmodelo.getEspecificocontable());

                plandecuentaEJB.create(plandecuenta);

            }
            items = ejbFacade.especificocontableAll(empresa);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Especifico Modelo Clonado Satisfactoriamente", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al Clonar Especifico Modelo", ""));
        }
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundlecontable").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundlecontable").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Especificocontable getEspecificocontable(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Especificocontable> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Especificocontable> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Especificocontable.class)
    public static class EspecificocontableControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EspecificocontableController controller = (EspecificocontableController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "especificocontableController");
            return controller.getEspecificocontable(getKey(value));
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
            if (object instanceof Especificocontable) {
                Especificocontable o = (Especificocontable) object;
                return getStringKey(o.getCodigocuenta());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Especificocontable.class.getName()});
                return null;
            }
        }

    }

}
