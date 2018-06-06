package Jsf;

import Modelo.Subespecificocontable;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.SubespecificocontableFacadeLocal;
import Jpa.SubgrupocontableFacadeLocal;
import Modelo.Empresa;
import Modelo.Especificocontable;
import Modelo.Grupocontable;
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

@Named("subespecificocontableController")
@SessionScoped
public class SubespecificocontableController implements Serializable {

    @EJB
    private Jpa.SubespecificocontableFacadeLocal ejbFacade;
    @EJB
    private Jpa.EspecificocontableFacadeLocal ejbFacadeES;
    @EJB
    private Jpa.SubgrupocontableFacadeLocal ejbFacadeSG;
    @EJB
    private Jpa.GrupocontableFacadeLocal ejbFacadeG;

    private List<Subespecificocontable> items = null;
    private Subespecificocontable selected;

    private int idgrupo;
    private int idsubgrupo;
    private int idespecifico;
    private int idsubespecifico;
    private String codigoc;
    @Inject
    private RequerimientosController requerimientosController;
    @Inject
    private Subespecificocontable subespecif;

    private List<Grupocontable> lstGrupos;
    private List<Subgrupocontable> lstSubgrupos;
    private List<Especificocontable> lstEspecificos;
    private List<Subespecificocontable> lstSubespecificos;

    private List<Subespecificocontable> itemsmodelo = null;

    @PostConstruct
    public void init() {
        lstGrupos = ejbFacadeG.findAll();
    }

    public String getCodigoc() {
        return codigoc;
    }

    public void setCodigoc(String codigoc) {
        this.codigoc = codigoc;
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

    public List<Especificocontable> getLstEspecificos() {
        return lstEspecificos;
    }

    public void setLstEspecificos(List<Especificocontable> lstEspecificos) {
        this.lstEspecificos = lstEspecificos;
    }

    public SubespecificocontableController() {
    }

    public Subespecificocontable getSelected() {
        return selected;
    }

    public void setSelected(Subespecificocontable selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SubespecificocontableFacadeLocal getFacade() {
        return ejbFacade;
    }

    public SubgrupocontableFacadeLocal getEjbFacadeSG() {
        return ejbFacadeSG;
    }

    public List<Subespecificocontable> getItemsmodelo() {
        return itemsmodelo;
    }

    public void setItemsmodelo(List<Subespecificocontable> itemsmodelo) {
        this.itemsmodelo = itemsmodelo;

    }

    public void clonarEspecifico() {
        try {
            Empresa empresa = requerimientosController.getEmpresa();
            for (Subespecificocontable subespmodelo : itemsmodelo) {
                subespecif = subespmodelo;
                subespecif.setIdempresa(empresa.getIdempresa());
                ejbFacade.create(subespecif);
            }
            items = ejbFacade.subespecificocontableAll(empresa);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SubEspecifico Modelo Clonado Satisfactoriamente", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al Clonar SubEspecifico Modelo", ""));
        }
    }

    public Subespecificocontable prepareCreate() {
        selected = new Subespecificocontable();
        selected.setIdempresa(requerimientosController.getEmpresa().getIdempresa());
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

    public List<Especificocontable> refrescarEspecificos() {
        try {
            if (selected != null) {
                lstEspecificos = ejbFacadeES.espxSGrupo(selected.getIdgrupocontable(),
                        selected.getIdsubgrupocontable(), requerimientosController.getEmpresa());
            }
        } catch (Exception e) {
        }
        return lstEspecificos;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SubespecificocontableCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SubespecificocontableUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SubespecificocontableDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Subespecificocontable> getItems() {
        if (items == null) {
            items = getFacade().subespecificocontableAll(requerimientosController.getEmpresa());
        }
        return items;
    }

    public List<Subespecificocontable> getItemsModelo() {
        if (itemsmodelo == null) {
            itemsmodelo = getFacade().subespecificocontableModelo();
        }
        return itemsmodelo;
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
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Subespecificocontable getSubespecificocontable(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Subespecificocontable> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Subespecificocontable> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Subespecificocontable.class)
    public static class SubespecificocontableControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SubespecificocontableController controller = (SubespecificocontableController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "subespecificocontableController");
            return controller.getSubespecificocontable(getKey(value));
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
            if (object instanceof Subespecificocontable) {
                Subespecificocontable o = (Subespecificocontable) object;
                return getStringKey(o.getCodigocuenta());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Subespecificocontable.class.getName()});
                return null;
            }
        }

    }

}
