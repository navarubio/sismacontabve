package Jsf;

import Jpa.UsuarioFacadeLocal;
import Jpa.UsuariodeprolFacadeLocal;
import Modelo.Empresa;
import Modelo.Usuario;
import Modelo.Usuariodeprol;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@ViewScoped

public class IndexController implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    @EJB
    private UsuariodeprolFacadeLocal usuariodeprolEJB;
    @Inject
    private Usuario usuario;
    private Empresa empresa;
    private Usuariodeprol usuariodeprol;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    public String iniciarSesion() {
        Usuario us;
        String redireccion = null;
        try {
            us = usuarioEJB.iniciarSesion(usuario);
            usuario=us;
            if (us != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empresa", empresa);
                buscardepartamentorol();
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuariodeprol", usuariodeprol);              

                //Almacenar la session JSF
                redireccion = "protegido/menup?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales Incorrectas"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error!"));
        }
        return redireccion;
    }
    public void buscardepartamentorol(){
        usuariodeprol=usuariodeprolEJB.UsuarioDptoRol(usuario, empresa);
    }

    public String enviarConfirmacion() {
        String redi = "/productor/Confirmacion?faces-redirect=true";
        return redi;
    }
    }
