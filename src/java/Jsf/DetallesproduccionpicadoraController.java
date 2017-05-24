package Jsf;

import Modelo.Detalleproduccionpicadora;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.DetalleproduccionpicadoraFacade;
import Jpa.DetalleproduccionpicadoraFacadeLocal;

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
import javax.faces.view.ViewScoped;

@Named("detallesproduccionpicadoraController")
@ViewScoped
public class DetallesproduccionpicadoraController implements Serializable {

    @EJB
    private Jpa.DetalleproduccionpicadoraFacadeLocal ejbFacade;
    private List<Detalleproduccionpicadora> items = null;
    private Detalleproduccionpicadora selected;

    public DetallesproduccionpicadoraController() {
    }

    public Detalleproduccionpicadora getSelected() {
        return selected;
    }

    public void setSelected(Detalleproduccionpicadora selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DetalleproduccionpicadoraFacadeLocal getFacade() {
        return ejbFacade;
    }



    public List<Detalleproduccionpicadora> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }


    public Detalleproduccionpicadora getDetalleproduccionpicadora(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Detalleproduccionpicadora> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Detalleproduccionpicadora> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }


}

