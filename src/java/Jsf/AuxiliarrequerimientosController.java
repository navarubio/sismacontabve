package Jsf;

import Modelo.Auxiliarrequerimiento;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.AuxiliarrequerimientoFacade;
import Jpa.AuxiliarrequerimientoFacadeLocal;
import Modelo.Empresa;

import java.io.Serializable;
import java.sql.SQLException;
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
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;

@ManagedBean(name = "auxiliarrequerimientosController")
@SessionScoped
public class AuxiliarrequerimientosController implements Serializable {

    @EJB
    private AuxiliarrequerimientoFacadeLocal auxiliarrequerimientoEJB;
    private List<Auxiliarrequerimiento> requerimientos = null; 
    private List<Auxiliarrequerimiento> requerimientosactivos = null;
    @Inject
    private Auxiliarrequerimiento selected;
    private Empresa empresa;
    @Inject
    private AuxiliarrequerimientoController auxiliar;

    @PostConstruct
    public void init (){
        empresa = auxiliar.getEmpresa();
        requerimientosactivos = auxiliarrequerimientoEJB.buscarrequerimientosActivos(empresa);
    }
   
    public AuxiliarrequerimientosController() {
    }

    public Auxiliarrequerimiento getSelected() {
        return selected;
    }

    public void setSelected(Auxiliarrequerimiento selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AuxiliarrequerimientoFacadeLocal getFacade() {
        return auxiliarrequerimientoEJB;
    }

    public List<Auxiliarrequerimiento> getRequerimientosactivos() {
        return requerimientosactivos;
    }

    public void setRequerimientosactivos(List<Auxiliarrequerimiento> requerimientosactivos) {
        this.requerimientosactivos = requerimientosactivos;
    }
    
    public List<Auxiliarrequerimiento> buscarRequerimientosActivos() {
        requerimientosactivos = auxiliarrequerimientoEJB.buscarrequerimientosActivos(empresa);
        return requerimientosactivos;
    }

    public Auxiliarrequerimiento prepareCreate() {
        selected = new Auxiliarrequerimiento();
        initializeEmbeddableKey();
        return selected;
    }


    public List<Auxiliarrequerimiento> getItems() {
        if (requerimientos == null) {
            requerimientos = getFacade().findAll();
        }
        return requerimientos;
    }
    
    public void editar(){
        try {
            auxiliarrequerimientoEJB.edit(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Su Requerimiento fue modificado"));            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al Grabar Requerimiento"));
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }

    

    public Auxiliarrequerimiento getAuxiliarrequerimiento(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Auxiliarrequerimiento> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Auxiliarrequerimiento> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Auxiliarrequerimiento.class)
    public static class AuxiliarrequerimientoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AuxiliarrequerimientosController controller = (AuxiliarrequerimientosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "auxiliarrequerimientoController");
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
            if (object instanceof Auxiliarrequerimiento) {
                Auxiliarrequerimiento o = (Auxiliarrequerimiento) object;
                return getStringKey(o.getIdauxiliarrequerimiento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Auxiliarrequerimiento.class.getName()});
                return null;
            }
        }

    }
    public void verReporte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        //Instancia hacia la clase reporteClientes        
        reporteArticulo rArticulo = new reporteArticulo();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/resources/reportes/requerimientos.jasper");
       
        rArticulo.getReporte(ruta);        
        FacesContext.getCurrentInstance().responseComplete();               
    }

}
