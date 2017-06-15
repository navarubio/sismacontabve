package Jsf;

import Jpa.ArticuloFacadeLocal;
import Modelo.Detalleproduccionpicadora;
import Jsf.util.JsfUtil;
import Jsf.util.JsfUtil.PersistAction;
import Jpa.DetalleproduccionpicadoraFacade;
import Jpa.DetalleproduccionpicadoraFacadeLocal;
import Jpa.ProduccionpicadoraFacadeLocal;
import Modelo.Articulo;
import Modelo.Produccionpicadora;

import java.io.Serializable;
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
    @EJB
    private ProduccionpicadoraFacadeLocal produccionpicadoraEJB;
    
    @EJB
    private ArticuloFacadeLocal articuloEJB;
    
    private List<Detalleproduccionpicadora> items = null;
    private Detalleproduccionpicadora selected;
    private Articulo articuloelegido;
    private List<Articulo> articulos = null;
    private List<Produccionpicadora> producido;
    private Date fechadesde;
    private Date fechahasta;

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

    public Articulo getArticuloelegido() {
        return articuloelegido;
    }

    public void setArticuloelegido(Articulo articuloelegido) {
        this.articuloelegido = articuloelegido;
    }

    public List<Detalleproduccionpicadora> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<Produccionpicadora> getProducido() {
        return producido;
    }

    public void setProducido(List<Produccionpicadora> producido) {
        this.producido = producido;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
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

    public Detalleproduccionpicadora getDetalleproduccionpicadora(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Detalleproduccionpicadora> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Detalleproduccionpicadora> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @PostConstruct
    public void init() {
        articulos = articuloEJB.listadoAgregadospicadora();
    }

    public void actualizar(){
        items= ejbFacade.buscarmovimientoporfecha(fechadesde, fechadesde);
    }

}

