package Jsf;

import Jpa.ArticuloFacadeLocal;
import Jpa.AuxiliarrequerimientoFacadeLocal;
import Jpa.DepartamentoFacadeLocal;
import Jpa.RequerimientoFacadeLocal;
import Modelo.Articulo;
import Modelo.Auxiliarrequerimiento;
import Modelo.Departamento;
import Modelo.Estatusrequerimiento;
import Modelo.Requerimiento;
import Modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped

public class RequerimientosController implements Serializable {

    @EJB
    private ArticuloFacadeLocal articuloEJB;
    @EJB
    private RequerimientoFacadeLocal requerimientoEJB;
    @EJB
    private AuxiliarrequerimientoFacadeLocal auxiliarrequerimientoEJB;
    @EJB
    private DepartamentoFacadeLocal departamentoEJB;

    private List<Articulo> articulos = null;
    private List<Requerimiento> requerimientos = null;
    private List<Requerimiento> listarequerimiento = new ArrayList();
    private String codigo = null;
    private String descripcion = null;
    private int cantidad = 0;
    private double pcosto = 0;
    private double subtotal= 0;
    private double totalgeneral=0;
    private double totaliva=0;
    private double totalsubtotal=0;
    
    private Auxiliarrequerimiento codAux;

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotalgeneral() {
        return totalgeneral;
    }

    public void setTotalgeneral(double totalgeneral) {
        this.totalgeneral = totalgeneral;
    }
    
    public List<Requerimiento> getListarequerimiento() {
        return listarequerimiento;
    }

    public double getTotaliva() {
        return totaliva;
    }

    public void setTotaliva(double totaliva) {
        this.totaliva = totaliva;
    }

    public double getTotalsubtotal() {
        return totalsubtotal;
    }

    public void setTotalsubtotal(double totalsubtotal) {
        this.totalsubtotal = totalsubtotal;
    }

    
    public void setListarequerimiento(List<Requerimiento> listarequerimiento) {
        this.listarequerimiento = listarequerimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPcosto() {
        return pcosto;
    }

    public void setPcosto(double pcosto) {
        this.pcosto = pcosto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    public List<Requerimiento> getRequerimientos() {
        return requerimientos;
    }

    public void setRequerimientos(List<Requerimiento> requerimientos) {
        this.requerimientos = requerimientos;
    }

    @Inject
    private Articulo articulo;
    @Inject
    private Requerimiento requer;

    @PostConstruct
    public void init() {
        articulos = articuloEJB.findAll();
    }

    @Inject
    private Auxiliarrequerimiento auxrequer;
    @Inject
    private Usuario usa;
    @Inject
    private Departamento dpto;
    @Inject
    private Estatusrequerimiento statusreq;
    private int statu = 1;

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Requerimiento getRequer() {
        return requer;
    }

    public void setRequer(Requerimiento requer) {
        this.requer = requer;
    }

    public Auxiliarrequerimiento getAuxrequer() {
        return auxrequer;
    }

    public void setAuxrequer(Auxiliarrequerimiento auxrequer) {
        this.auxrequer = auxrequer;
    }

    public Usuario getUsuario() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        usa = us;
        return us;
    }

    public Departamento buscarDepartamento() {
        Usuario usua = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        dpto = departamentoEJB.buscarDepartamento(usua);
        statusreq.setIdestatusrequerimiento(statu);
        return dpto;
    }

    public void anexar() {
        double alicuota=0;
        double iva =0;
        double total=0;
        Requerimiento reque = new Requerimiento();
        reque.setCodigo(requer.getCodigo());
        reque.setCantidad(cantidad);
        reque.setPcosto(pcosto);
        subtotal=cantidad*pcosto;
        reque.setSubtotal(subtotal);                
        alicuota=reque.getCodigo().getIdgravamen().getAlicuota();
        iva = (subtotal*alicuota)/100;
        total=subtotal+iva;
        reque.setTributoiva(iva);
        reque.setTotal(total);
        totalgeneral+=total;
        totaliva+=iva;
        totalsubtotal+=subtotal;
        this.listarequerimiento.add(reque);
        requerimientos = requerimientoEJB.findAll();
    }
    
    public void eliminar (Requerimiento requerim){
        listarequerimiento.remove(requerim);
    }

    public void registrar() {
        Articulo art = new Articulo();
        try {
            auxrequer.setIddepartamento(dpto);
            auxrequer.setIdusuario(usa);
            auxrequer.setIdestatusrequerimiento(statusreq);
            auxrequer.setSubtotal(totalsubtotal);
            auxrequer.setMontoiva(totaliva);
            auxrequer.setMontototal(totalgeneral);
            
            auxiliarrequerimientoEJB.create(auxrequer);

            
            codAux = requerimientoEJB.ultimoInsertado();

            for (Requerimiento rq : listarequerimiento) {
                Articulo arti = rq.getCodigo();
                requer.setIdauxiliarrequerimiento(codAux);
                requer.setCodigo(arti);
                requer.setCantidad(rq.getCantidad());
                requer.setPcosto(rq.getPcosto());
                requer.setSubtotal(rq.getSubtotal());
                requer.setTributoiva(rq.getTributoiva());
                requer.setTotal(rq.getTotal());
                requerimientoEJB.create(requer);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Su Requerimiento fue Almacenado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al Grabar Requerimiento"));
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }
}
