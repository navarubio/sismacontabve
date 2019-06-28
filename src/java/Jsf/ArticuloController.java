package Jsf;

import Modelo.Articulo;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.ArticuloFacadeLocal;
import Jpa.PlandecuentaFacadeLocal;
import Jpa.SubgrupoFacadeLocal;
import Modelo.Contribuyente;
import Modelo.Empresa;
import Modelo.Plandecuenta;
import Modelo.Subgrupo;
import Modelo.Usuario;
import Modelo.Usuariodeprol;

import java.io.Serializable;
import java.sql.SQLException;
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

@ManagedBean(name = "articuloController")
@SessionScoped
public class ArticuloController implements Serializable {

    @EJB
    private ArticuloFacadeLocal ejbFacade;
    @EJB
    private PlandecuentaFacadeLocal plandecuentaEJB;
    @EJB
    private SubgrupoFacadeLocal subgrupoEJB;
    private List<Articulo> items = null;
    private double pcosto=0;
    private double pventa=0;
    private Articulo selected;
    private Articulo articuloaclasificar;
    private int cuentaseleccionada;
    private List<Subgrupo> lstCuentasSelecc;
    @Inject
    private Usuario usa;
    @Inject
    private RequerimientosController requerimientosController;
    @Inject 
    private Empresa empresa;    
    @Inject
    private Usuariodeprol usuariodeprol;
    
    public ArticuloController() {
    }

    public Articulo getArticuloaclasificar() {
        return articuloaclasificar;
    }

    public void setArticuloaclasificar(Articulo articuloaclasificar) {
        this.articuloaclasificar = articuloaclasificar;
    }

    public int getCuentaseleccionada() {
        return cuentaseleccionada;
    }

    public void setCuentaseleccionada(int cuentaseleccionada) {
        this.cuentaseleccionada = cuentaseleccionada;
    }

    public Articulo getSelected() {
        return selected;
    }

    public void setSelected(Articulo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ArticuloFacadeLocal getFacade() {
        return ejbFacade;
    }

    public List<Subgrupo> getLstCuentasSelecc() {
        return lstCuentasSelecc;
    }

    public void setLstCuentasSelecc(List<Subgrupo> lstCuentasSelecc) {
        this.lstCuentasSelecc = lstCuentasSelecc;
    }

    public Articulo prepareCreate() {
        selected = new Articulo();
        selected.setPcosto(pcosto);
        selected.setPventa(pventa);
        initializeEmbeddableKey();
        usa=getUsuario();
        selected.setIdusuario(usa);
        selected.setIdempresa(usa.getIddepartamento().getIdempresa().getIdempresa());
        return selected;
    }
       
    public void asignarArticulo(Articulo articulo) {
        this.selected = ejbFacade.buscarArticulo(articulo.getCodigo());
        this.articuloaclasificar=selected;
    }

        public void modificar() {
        selected.setIdplandecuenta(plandecuentaEJB.buscarcuenta(cuentaseleccionada, requerimientosController.getEmpresa()));
        ejbFacade.edit(selected);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Su Articulo fue Asociado"));
    }


    public Usuario getUsuario() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        usa = us;
        return us;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ArticuloCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }

    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ArticuloUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ArticuloDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Articulo> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
        public List<Articulo> getListaOrdenada() {
        if (items == null) {
            items = getFacade().listadoArticulos();
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

    public List<Articulo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Articulo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Articulo.class)
    public static class ArticuloControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ArticuloController controller = (ArticuloController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "articuloController");
            return controller.getFacade().find(getKey(value));
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
            if (object instanceof Articulo) {
                Articulo o = (Articulo) object;
                return getStringKey(o.getCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Articulo.class.getName()});
                return null;
            }
        }

    }
    
    public void verReporte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        //Instancia hacia la clase reporteClientes        
        reporteArticulo rArticulo = new reporteArticulo();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/resources/reportes/catalogo.jasper");
       
        empresa= (Empresa) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");

        rArticulo.getCatalogo(ruta, empresa.getIdempresa());        
        FacesContext.getCurrentInstance().responseComplete();               
    }
    
    public List<Subgrupo> refrescarSubgrupo() {
        try {
            lstCuentasSelecc = subgrupoEJB.subgrupoxGrupo(selected.getIdgrupo().getIdgrupo());
        } catch (Exception e) {
        }
        return lstCuentasSelecc;
    }

}
