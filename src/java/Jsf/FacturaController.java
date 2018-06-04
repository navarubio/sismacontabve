package Jsf;

import Jpa.DetallefacturaFacadeLocal;
import Modelo.Factura;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.FacturaFacade;
import Jpa.FacturaFacadeLocal;
import Modelo.Cobroventa;
import Modelo.Detallefactura;
import Modelo.Estatusfactura;
import Modelo.Estatusfacturaventa;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import javax.inject.Inject;
import javax.servlet.ServletContext;

@Named("facturaController")
@ViewScoped
public class FacturaController implements Serializable {

    @EJB
    private Jpa.FacturaFacadeLocal ejbFacade;
    @EJB
    private Jpa.EstatusfacturaventaFacadeLocal estatusfacturaEJB;
    @EJB
    private Jpa.CobroventaFacadeLocal cobroventaEJB;
    @EJB
    DetallefacturaFacadeLocal detallefacturaEJB;
    private List<Factura> items = null;
    private List<Estatusfacturaventa> estatusfact;
    private List<Estatusfacturaventa> estatusfactxcobrar;
    private List<Factura> facturasactivas = null;
    private List<Cobroventa> cobrosporfactura = null;
    private Factura selected;
    private List<Detallefactura> detallesporfactura = null;
    @Inject
    private RequerimientosController requerimientosController;
    public FacturaController() {
    }

    @PostConstruct
    public void init() {
        facturasactivas = ejbFacade.buscarfacturasporCobrar(requerimientosController.getEmpresa());
    }

    public Factura getSelected() {
        return selected;
    }

    public void setSelected(Factura selected) {
        this.selected = selected;
    }

    public List<Detallefactura> getDetallesporfactura() {
        return detallesporfactura;
    }

    public void setDetallesporfactura(List<Detallefactura> detallesporfactura) {
        this.detallesporfactura = detallesporfactura;
    }

    public List<Estatusfacturaventa> getEstatusfact() {
        return estatusfact;
    }

    public void setEstatusfact(List<Estatusfacturaventa> estatusfact) {
        this.estatusfact = estatusfact;
    }

    public List<Estatusfacturaventa> getEstatusfactxcobrar() {
        return estatusfactxcobrar;
    }

    public List<Cobroventa> getCobrosporfactura() {
        return cobrosporfactura;
    }

    public void setCobrosporfactura(List<Cobroventa> cobrosporfactura) {
        this.cobrosporfactura = cobrosporfactura;
    }

    public void setEstatusfactxcobrar(List<Estatusfacturaventa> estatusfactxcobrar) {
        this.estatusfactxcobrar = estatusfactxcobrar;
    }


    /*public List<Factura> getFact() {
     return items;
     }*/
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FacturaFacadeLocal getFacade() {
        return ejbFacade;
    }

    public List<Factura> getFacturasactivas() {
        return facturasactivas;
    }

    public void setFacturasactivas(List<Factura> facturasactivas) {
        this.facturasactivas = facturasactivas;
    }

    public List<Factura> buscarFacturasActivas() {
        facturasactivas = ejbFacade.buscarfacturasporCobrar(requerimientosController.getEmpresa());
        return facturasactivas;
    }

    public Factura prepareCreate() {
        selected = new Factura();
        initializeEmbeddableKey();
        return selected;
    }
    /*@PostConstruct
     public void init(){
     items = new ArrayList<Factura>();
     }
        
     public String getTotalSubtotal() {
     int total = 0;

     for (items fact : getItems()) {
     total += fact();
     }

     return new DecimalFormat("###,###.###").format(total);
     }*/

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundlefactura").getString("FacturaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundlefactura").getString("FacturaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundlefactura").getString("FacturaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Factura> getItems() {
        if (items == null) {
            items = getFacade().facturasAll(requerimientosController.getEmpresa());
        }
        return items;
    }

    public List<Detallefactura> buscardetallesporfactura(Factura factura) {
        detallesporfactura = detallefacturaEJB.buscardetallefactura(selected);
        return detallesporfactura;
    }

    public List<Estatusfacturaventa> getEstatusFacturas() {
        estatusfact = estatusfacturaEJB.findAll();
        return estatusfact;
    }

    public List<Estatusfacturaventa> getStatusFactporCobrar() {
        estatusfactxcobrar = estatusfacturaEJB.ListarEstatusporCobrar();
        return estatusfactxcobrar;
    }

    public List<Cobroventa> getListacobrosporfactura() {
        cobrosporfactura = cobroventaEJB.buscarcobrosporfactura(selected.getNumerofact());
        return cobrosporfactura;
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundlefactura").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundlefactura").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Factura getFactura(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Factura> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Factura> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Factura.class)
    public static class FacturaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FacturaController controller = (FacturaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "facturaController");
            return controller.getFactura(getKey(value));
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
            if (object instanceof Factura) {
                Factura o = (Factura) object;
                return getStringKey(o.getNumerofact());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Factura.class.getName()});
                return null;
            }
        }
    }

    public void verFactura() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        //Instancia hacia la clase reporteClientes        
        reporteArticulo rArticulo = new reporteArticulo();

        int codigofactu = selected.getNumerofact();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/resources/reportes/facturafiscal.jasper");

        rArticulo.getFactura(ruta, codigofactu);
        FacesContext.getCurrentInstance().responseComplete();
    }
}
