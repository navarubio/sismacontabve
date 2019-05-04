package Jsf;

import Modelo.Cliente;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.ClienteFacadeLocal;
import Jpa.ContribuyenteFacadeLocal;
import Modelo.Contribuyente;
import Modelo.Cuentabancaria;
import Modelo.Empresa;
import Modelo.Proveedor;
import Modelo.Usuario;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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

@ManagedBean(name = "clienteController")
@SessionScoped
public class ClienteController implements Serializable {

    @EJB
    private ClienteFacadeLocal ejbFacade;
    @EJB
    private ContribuyenteFacadeLocal contribuyenteEJB;
    private List<Cliente> items = null;
    private List<Contribuyente> lstCuentasSelecc;
    private Cliente selected;
    @Inject
    private Proveedor proveedor;
    @Inject
    private Usuario usa;
    private Date fechaactual = new Date();
    private ValidarRUC validarucspv = new ValidarRUC();
    private ValidaRUCep validarrucep= new ValidaRUCep();
    private ValidaCedula validarcedula= new ValidaCedula();
    private Boolean rucvalido;
    @Inject 
    private Empresa empresa;     
    

    public ClienteController() {
    }

    @PostConstruct
    public void init() {
        lstCuentasSelecc = null;

    }

    public Cliente getSelected() {
        return selected;
    }

    public void setSelected(Cliente selected) {
        this.selected = selected;
    }

    public List<Contribuyente> getLstCuentasSelecc() {
        return lstCuentasSelecc;
    }

    public void setLstCuentasSelecc(List<Contribuyente> lstCuentasSelecc) {
        this.lstCuentasSelecc = lstCuentasSelecc;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ClienteFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Cliente prepareCreate() {
        selected = new Cliente();
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
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ClienteCreated"));
        if (!JsfUtil.isValidationFailed()) {
           items = null;    // Invalidate list of items to trigger re-query.
        }
    }


    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ClienteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ClienteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Cliente> getItems() {
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

    public List<Cliente> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Cliente> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    



}

    @FacesConverter(forClass = Cliente.class)
public static class ClienteControllerConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        ClienteController controller = (ClienteController) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "clienteController");
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
        if (object instanceof Cliente) {
            Cliente o = (Cliente) object;
            return getStringKey(o.getRifcliente());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Cliente.class.getName()});
            return null;
        }
    }

}
public void verReporte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        //Instancia hacia la clase reporteClientes        
        reporteArticulo rArticulo = new reporteArticulo();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/resources/reportes/clientesgeneral.jasper");
        empresa= (Empresa) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
       
        rArticulo.getReporteClient(ruta, empresa.getIdempresa());        
        FacesContext.getCurrentInstance().responseComplete();               
    }
    
    public List<Contribuyente> refrescarContribuyentes() {
        try {
            lstCuentasSelecc = contribuyenteEJB.contribuyentexPersona(selected.getIdpersonalidad().getIdpersonalidad());
        } catch (Exception e) {
        }
        return lstCuentasSelecc;
    }
    
    public List<Contribuyente> refrescarContribuyentes2() {
        
        lstCuentasSelecc=null;
        try {
            lstCuentasSelecc = contribuyenteEJB.contribuyentexPersona(proveedor.getIdpersonalidad().getIdpersonalidad());
        } catch (Exception e) {
        }
        return lstCuentasSelecc;
    }

    public void validarRucSociedadesprivadas (){
        rucvalido=validarucspv.validacionRUC(selected.getRifcliente());
        if (rucvalido){
            System.out.print("RUC VALIDADO");
        }else{
            System.out.print("RUC ERRADO");
            
        }
    }
}
