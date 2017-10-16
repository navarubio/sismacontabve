package Jsf;

import Jpa.MenuFacadeLocal;
import Modelo.Menu;
import Modelo.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author sofimar
 */
@ManagedBean(name = "menupersonalController")
@SessionScoped
public class MenupersonalController implements Serializable {

    @EJB
    private MenuFacadeLocal menuEJB;
    private List<Menu> lista;
    private MenuModel menumodelo;
    Menu subMenu;

    public MenuModel getMenumodelo() {
        return menumodelo;
    }

    public void setMenumodelo(MenuModel menumodelo) {
        this.menumodelo = menumodelo;
    }

    @PostConstruct
    public void init() {
        this.listadeMenu();
        menumodelo = new DefaultMenuModel();
//        subMenu=new Menu();
        cargarMenu();

    }

    public void listadeMenu() {
        try {
            lista = menuEJB.detallesOrdenados();
        } catch (Exception e) {

        }

    }

    public void cargarMenu() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

        for (Menu sm : lista) {
            if (sm.getIdtipoitemmenu().getIdtipoitemmenu() == 1) {
                if (sm.getIddepartamento().getIddepartamento().equals(us.getIddepartamento().getIddepartamento())) {
                    DefaultSubMenu nivel1submenu = new DefaultSubMenu(sm.getDescripcion());
                    if (sm.getIcon() != null) {
                        nivel1submenu.setIcon(sm.getIcon());
                    }
                    for (Menu sm2 : lista) {
                        if (sm2.getCodigosubmenu() != null) {
                            if (sm2.getCodigosubmenu().getIdmenu().equals(sm.getIdmenu()) && sm2.getIdtipoitemmenu().getIdtipoitemmenu() == 2) {
//                            if (sm2.getIdsubmenu().getIdsubmenu().equals(sm.getIdsubmenu().getIdsubmenu()) && sm2.getIdtipoitemmenu().getIdtipoitemmenu() == 2) {

                                DefaultSubMenu nivel2submenu = new DefaultSubMenu(sm2.getDescripcion());
                                if (sm2.getIcon() != null) {
                                    nivel2submenu.setIcon(sm2.getIcon());
                                }
                                for (Menu it : lista) {
                                    subMenu = it.getCodigosubmenu();
                                    if (subMenu != null) {
                                        if (sm2.getIdmenu().equals(it.getCodigosubmenu().getIdmenu())) {
                                            if (it.getIdsubmenu().getIdsubmenu().equals(sm2.getIdsubmenu().getIdsubmenu()) && it.getIdtipoitemmenu().getIdtipoitemmenu() == 3 && it.getIdrol().getIdrol() <= (us.getIdrol().getIdrol()) && it.getIddepartamento().getIddepartamento().equals(us.getIddepartamento().getIddepartamento())) {
                                                DefaultMenuItem item = new DefaultMenuItem(it.getDescripcion());
                                                if (it.getUrl() != null) {
                                                    item.setUrl(it.getUrl());
                                                }
                                                nivel2submenu.addElement(item);
                                            }
                                        }
                                    }
                                }
                                nivel1submenu.addElement(nivel2submenu);
                            }
                        }

                    }
                    menumodelo.addElement(nivel1submenu);
                }
            } else if (sm.getIdtipoitemmenu().getIdtipoitemmenu() == 3) {
                if (sm.getCodigosubmenu() == null) {
                    DefaultMenuItem item = new DefaultMenuItem(sm.getDescripcion());
                    item.setIcon(sm.getIcon());
                    if (sm.getUrl() != null) {
                        item.setUrl(sm.getUrl());
                    }
                    menumodelo.addElement(item);
                }
            }

        }
    }

    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

}
