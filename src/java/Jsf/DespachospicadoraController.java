package Jsf;

import Modelo.Despachopicadora;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.DespachopicadoraFacade;
import Jpa.DespachopicadoraFacadeLocal;

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

@Named("despachospicadoraController")
@SessionScoped
public class DespachospicadoraController implements Serializable {

    @EJB
    private Jpa.DespachopicadoraFacadeLocal ejbFacade;
    private List<Despachopicadora> items = null;
    private Despachopicadora selected;

    public DespachospicadoraController() {
    }

    public Despachopicadora getSelected() {
        return selected;
    }

    public void setSelected(Despachopicadora selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DespachopicadoraFacadeLocal getFacade() {
        return ejbFacade;
    }

    
    public Despachopicadora getDespachopicadora(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Despachopicadora> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Despachopicadora> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

   

}
