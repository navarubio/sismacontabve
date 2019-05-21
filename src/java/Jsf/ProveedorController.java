package Jsf;

import Jpa.ContribuyenteFacadeLocal;
import Modelo.Proveedor;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.ProveedorFacade;
import Jpa.ProveedorFacadeLocal;
import Jpa.ResidenciajuridicaFacadeLocal;
import Modelo.Contribuyente;
import Modelo.Empresa;
import Modelo.Residenciajuridica;
import Modelo.Usuario;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.servlet.ServletContext;

@ManagedBean(name = "proveedorController")
@SessionScoped
public class ProveedorController implements Serializable {

    @EJB
    private ProveedorFacadeLocal ejbFacade;
    @EJB
    private ContribuyenteFacadeLocal contribuyenteEJB;
    @EJB
    private ResidenciajuridicaFacadeLocal residenciajuridicaEJB;
    private List<Proveedor> items = null;
    private Proveedor selected;
    private ClienteController clientecontrolador = new ClienteController();
    private List<Contribuyente> listadoContri = null;

    private List<Residenciajuridica> listadoResidencia = null;
    @Inject
    private Usuario usa;
    @Inject 
    private Empresa empresa;    
    private Date fechaactual = new Date();
    private ValidarRUC validarucspv = new ValidarRUC();
    private ValidaRUCep validarrucep = new ValidaRUCep();
    private ValidaCedula validarcedula = new ValidaCedula();
    private Boolean rucvalido;
    
    

    public ProveedorController() {
    }

    public Proveedor getSelected() {
        return selected;
    }

    public void setSelected(Proveedor selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ProveedorFacadeLocal getFacade() {
        return ejbFacade;
    }

    public List<Contribuyente> getListadoContri() {
        return listadoContri;
    }

    public void setListadoContri(List<Contribuyente> listadoContri) {
        this.listadoContri = listadoContri;
    }

    public List<Residenciajuridica> getListadoResidencia() {
        return listadoResidencia;
    }

    public void setListadoResidencia(List<Residenciajuridica> listadoResidencia) {
        this.listadoResidencia = listadoResidencia;
    }

    public Proveedor prepareCreate() {
        selected = new Proveedor();
        selected.setFechainscripcion(fechaactual);
        initializeEmbeddableKey();
        usa = getUsuario();
        selected.setIdusuario(usa);
        return selected;
    }

    public Usuario getUsuario() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        usa = us;
        return us;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProveedorCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProveedorUpdated"));
    }
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ProveedorDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Proveedor> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Proveedor getProveedor(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Proveedor> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Proveedor> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Proveedor.class)
    public static class ProveedorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProveedorController controller = (ProveedorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "proveedorController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Proveedor) {
                Proveedor o = (Proveedor) object;
                return getStringKey(o.getRifproveedor());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Proveedor.class.getName()});
                return null;
            }
        }

    }

    public void verReporte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        //Instancia hacia la clase reporteProveedores 
        reporteArticulo rArticulo = new reporteArticulo();
        empresa= (Empresa) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        FacesContext facesContext = FacesContext.getCurrentInstance(); 
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/resources/reportes/proveedores.jasper");

        rArticulo.getReporteProvee(ruta, empresa.getIdempresa() );
        FacesContext.getCurrentInstance().responseComplete();
    }

    public List<Contribuyente> refrescarContribuyentes() {
        try {
            listadoContri = contribuyenteEJB.contribuyentexPersona(selected.getIdpersonalidad().getIdpersonalidad());
            listadoResidencia = residenciajuridicaEJB.residenciaxPersona(selected.getIdpersonalidad().getIdpersonalidad());
        } catch (Exception e) {
        }
        return listadoContri;
    }
    public List<Contribuyente> ContribuyentesXpersonalidad(Empresa empre) {
        this.empresa=empre;
 //       if (!listadoContri.isEmpty()){
 //           listadoContri.clear();
//        }
        try {
            listadoContri = contribuyenteEJB.contribuyentexPersona(empresa.getIdpersonalidad().getIdpersonalidad());
        } catch (Exception e) {
        }
        return listadoContri;
    }
}
