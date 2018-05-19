package Jsf;

import Modelo.Libromayor;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.LibromayorFacade;
import Jpa.LibromayorFacadeLocal;
import Jpa.PlandecuentaFacadeLocal;
import Modelo.Caja_;
import Modelo.Cuentabancaria;
import Modelo.Libromayorcompuesto;
import Modelo.Movimientobancario;
import Modelo.Plandecuenta;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
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
import javax.inject.Inject;

@Named("libromayorController")
@ViewScoped
public class LibromayorController implements Serializable {

    @EJB
    private Jpa.LibromayorFacadeLocal ejbFacade;
    @EJB
    private Jpa.LibromayorcompuestoFacadeLocal libromayorcompuestoEJB;
    @EJB
    private PlandecuentaFacadeLocal plandecuentaEJB;

    private List<Libromayor> items = null;
    private List<Libromayorcompuesto> itemsfiltrados = null;
    private List<Libromayorcompuesto> itemsfiltrados2 = null;
    private Libromayor selected;
    private Plandecuenta cuentaseleccionada;
    private Cuentabancaria cuentabancaria;
    private Integer cuentacontab;
    private Date fechadesde;
    private Date fechahasta;
    private List<Libromayor> listado = new ArrayList();
    ArrayList<Libromayor> listaerrores1 = new ArrayList();
    DecimalFormat formatearnumero = new DecimalFormat("###,###.##");
    private Double saldocontable = 0.0;
    private Double saldogeneral = 0.0;

    @Inject
    private MovimientobancarioController movimientobancarioControler;

    public LibromayorController() {
    }

    public Libromayor getSelected() {
        return selected;
    }

    public void setSelected(Libromayor selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private LibromayorFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Plandecuenta getCuentaseleccionada() {
        return cuentaseleccionada;
    }

    public void setCuentaseleccionada(Plandecuenta cuentaseleccionada) {
        this.cuentaseleccionada = cuentaseleccionada;
    }

    public Date getFechadesde() {
        return fechadesde;
    }

    public void setFechadesde(Date fechadesde) {
        this.fechadesde = fechadesde;
    }

    public Date getFechahasta() {
        return fechahasta;
    }

    public void setFechahasta(Date fechahasta) {
        this.fechahasta = fechahasta;
    }

    public List<Libromayorcompuesto> getItemsfiltrados() {
        return itemsfiltrados;
    }

    public void setItemsfiltrados(List<Libromayorcompuesto> itemsfiltrados) {
        this.itemsfiltrados = itemsfiltrados;
    }

    public Integer getCuentacontab() {
        return cuentacontab;
    }

    public void setCuentacontab(Integer cuentacontab) {
        this.cuentacontab = cuentacontab;
    }

    public ArrayList<Libromayor> getListaerrores1() {
        return listaerrores1;
    }

    public void setListaerrores1(ArrayList<Libromayor> listaerrores1) {
        this.listaerrores1 = listaerrores1;
    }

    public MovimientobancarioController getMovimientobancarioControler() {
        return movimientobancarioControler;
    }

    public void setMovimientobancarioCotroler(MovimientobancarioController movimientobancarioControler) {
        this.movimientobancarioControler = movimientobancarioControler;
    }

    public List<Libromayorcompuesto> getItemsfiltrados2() {
        return itemsfiltrados2;
    }

    public void setItemsfiltrados2(List<Libromayorcompuesto> itemsfiltrados2) {
        this.itemsfiltrados2 = itemsfiltrados2;
    }

    public Double getSaldocontable() {
        return saldocontable;
    }

    public void setSaldocontable(Double saldocontable) {
        this.saldocontable = saldocontable;
    }

    public Double getSaldogeneral() {
        return saldogeneral;
    }

    public void setSaldogeneral(Double saldogeneral) {
        this.saldogeneral = saldogeneral;
    }

    @PostConstruct
    public void init() {

        if (itemsfiltrados != null) {
            itemsfiltrados.clear();
        }
    }

    public void actualizarBanco() {
//        itemsfiltrados.clear();
        movimientobancarioControler.actualizar();
        cuentacontab = movimientobancarioControler.getSelected().getIdcuentabancaria().getIdplandecuenta().getIdplandecuenta();
        fechadesde = movimientobancarioControler.getFechadesde();
        fechahasta = movimientobancarioControler.getFechahasta();
        actualizar();
    }

    public void actualizar() {
        itemsfiltrados = libromayorcompuestoEJB.buscarmayorporfecha(cuentacontab, fechadesde, fechahasta);
        Libromayorcompuesto movimiento;
        int ultimo = itemsfiltrados.size() - 1;

        if (!itemsfiltrados.isEmpty()) {
            movimiento = itemsfiltrados.get(ultimo);
            saldocontable = movimiento.getSaldoposterior();
        }
        cuentaseleccionada = plandecuentaEJB.buscarcuenta(cuentacontab);
        saldogeneral = cuentaseleccionada.getSaldogeneral();
    }

    public void conciliar() {
        listaerrores1.clear();
        Libromayorcompuesto movimiento;
        cuentaseleccionada = plandecuentaEJB.buscarcuenta(cuentacontab);
        saldogeneral = cuentaseleccionada.getSaldogeneral();
        itemsfiltrados = libromayorcompuestoEJB.buscarmayorporfechafinal(cuentacontab, fechahasta);
        int ultimo = itemsfiltrados.size() - 1;
        if (listado != null) {
            listado.clear();
        }
        int idlibromay = 0;
        Libromayor librmay;
        for (Libromayorcompuesto mayor : itemsfiltrados) {
            idlibromay = mayor.getIdlibromayor();
            librmay = ejbFacade.buscarLibro(idlibromay);
            listado.add(librmay);
        }
//        Collections.reverse(listado);

        conciliarSaldos();
        if (!itemsfiltrados.isEmpty()) {
            movimiento = itemsfiltrados.get(ultimo);
            saldocontable = movimiento.getSaldoposterior();
        }

    }

    /*public void conciliarSaldos() {
     double saldoant = 0;
     double saldoact = 0;
     double saldopost = 0;
     int tiposaldocontab = 0;
     int marcador = 0;
     int sinnovedad = 0;
     String stringposterior = null;
     String stringanterior = null;
     String stringactual;

     for (Libromayor seleccion : listado) {
     saldopost = seleccion.getSaldoposterior();
     stringposterior = formatearnumero.format(saldopost);

     if (marcador == 0) {
     stringanterior = stringposterior;
     //1)DEUDOR    2)ACREEDOR //
     tiposaldocontab = seleccion.getIdplandecuenta().getIdtiposaldocontable().getIdtiposaldocontable();
     }
     if (!stringanterior.equals(stringposterior)) {
     saldopost = saldoant;
     seleccion.setSaldoposterior(saldopost);
     sinnovedad = 1;
     }
     if (seleccion.getDebe() == 0) {
     if (tiposaldocontab == 1) {
     saldoant = saldopost + seleccion.getHaber();
     } else {
     saldoant = saldopost + seleccion.getHaber();
     }
     } else {
     if (tiposaldocontab == 1) {
     saldoant = saldopost - seleccion.getDebe();
     } else {
     saldoant = saldopost - seleccion.getDebe();
     }
     }
     stringanterior = formatearnumero.format(saldoant);
     saldoact = seleccion.getSaldoanterior();
     stringactual = formatearnumero.format(saldoact);

     if (stringanterior.equals(stringactual)) {

     } else {
     seleccion.setSaldoanterior(saldoant);
     sinnovedad = 1;
     }
     listaerrores1.add(seleccion);

     if (marcador == 0) {
     marcador++;
     }
     }
     if (sinnovedad == 0) {
     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "LOS SALDOS DEL MAYOR ESTAN AJUSTADOS"));
     } else {
     corregirSaldos();
     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "LOS SALDOS FUERON AJUSTADOS SATISFACTORIAMENTE"));
     }
     }*/
    public void conciliarSaldos() {
        double saldoant = 0;
        double saldoact = 0;
        double saldopost = 0;
        int tiposaldocontab = 0;
        int marcador = 0;
        int sinnovedad = 0;
        String stringposterior = null;
        String stringanterior = null;
        String stringactual;

        for (Libromayor seleccion : listado) {
            saldoant = seleccion.getSaldoanterior();
//            saldoant = seleccion.getSaldoanterior();
            stringanterior = formatearnumero.format(saldoant);
            stringposterior= formatearnumero.format(saldopost);
            
            if (marcador == 0) {
                stringposterior = stringanterior;
                //1)DEUDOR    2)ACREEDOR //
                tiposaldocontab = seleccion.getIdplandecuenta().getIdtiposaldocontable().getIdtiposaldocontable();
            }
            if (!stringanterior.equals(stringposterior)) {
                saldoant = saldopost;
                seleccion.setSaldoanterior(saldoant);
                sinnovedad = 1;
            }
            if (seleccion.getDebe() == 0) {
                if (tiposaldocontab == 1) {
                    saldopost = saldoant - seleccion.getHaber();
                } else {
                    saldopost = saldoant + seleccion.getHaber();
                }
            } else {
                if (tiposaldocontab == 1) {
                    saldopost = saldoant + seleccion.getDebe();
                } else {
                    saldopost = saldoant - seleccion.getDebe();
                }
            }
            stringposterior = formatearnumero.format(saldopost);
            saldoact = seleccion.getSaldoposterior();
            stringactual = formatearnumero.format(saldoact);

            if (!stringposterior.equals(stringactual)) {
                seleccion.setSaldoposterior(saldopost);
                sinnovedad = 1;
            }
            listaerrores1.add(seleccion);

            if (marcador == 0) {
                marcador++;
            }
        }
        if (sinnovedad == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "LOS SALDOS DEL MAYOR ESTAN AJUSTADOS"));
        } else {
            corregirSaldos();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "LOS SALDOS FUERON AJUSTADOS SATISFACTORIAMENTE"));
        }
    }

    public void corregirSaldos() {
        Libromayor movimientoListo;
        for (Libromayor movicorreg : listaerrores1) {
            movimientoListo = movicorreg;
            ejbFacade.edit(movimientoListo);
        }
        listaerrores1.clear();
        itemsfiltrados = libromayorcompuestoEJB.buscarmayorporfecha(cuentacontab, fechadesde, fechahasta);
    }

    public Libromayor prepareCreate() {
        selected = new Libromayor();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundlecontabilidad").getString("LibromayorCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundlecontabilidad").getString("LibromayorUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundlecontabilidad").getString("LibromayorDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Libromayor> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundlecontabilidad").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundlecontabilidad").getString("PersistenceErrorOccured"));
            }
        }
    }

    public String getTotalDebe() {
        double total = 0;

        for (Libromayor debe : getItems()) {
            total += debe.getDebe();
        }
        return new DecimalFormat("###,###.##").format(total);
    }

    public String getTotalHaber() {
        double total = 0;

        for (Libromayor haber : getItems()) {
            total += haber.getHaber();
        }
        return new DecimalFormat("###,###.##").format(total);
    }

    public String getMayTotalDebe() {
        double total = 0;
        if (itemsfiltrados != null) {
            for (Libromayorcompuesto debe : itemsfiltrados) {
                total += debe.getDebe();
            }
        }
        return new DecimalFormat("###,###.##").format(total);
    }

    public String getMayTotalHaber() {
        double total = 0;
        if (itemsfiltrados != null) {
            for (Libromayorcompuesto haber : itemsfiltrados) {
                total += haber.getHaber();
            }
        }
        return new DecimalFormat("###,###.##").format(total);
    }

    public Libromayor getLibromayor(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Libromayor> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Libromayor> getItemsAvailableSelectOne() {
        return getFacade().findAll();

    }

    @FacesConverter(forClass = Libromayor.class)
    public static class LibromayorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LibromayorController controller = (LibromayorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "libromayorController");
            return controller.getLibromayor(getKey(value));
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
            if (object instanceof Libromayor) {
                Libromayor o = (Libromayor) object;
                return getStringKey(o.getIdlibromayor());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Libromayor.class.getName()});
                return null;
            }
        }

    }

}
