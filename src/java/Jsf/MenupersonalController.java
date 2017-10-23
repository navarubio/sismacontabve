package Jsf;

import Jpa.MenuFacadeLocal;
import Jpa.MenurolFacadeLocal;
import Modelo.Itemmenu;
import Modelo.Menu;
import Modelo.Menurol;
import Modelo.Submenu;
import Modelo.Subnivel;
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
    @EJB
    private MenurolFacadeLocal menurolEJB;
    private List<Menu> lista;
    private List<Menurol> listamenurol;
    private List<Menurol> listamenuitems;
    private MenuModel menumodelo;
    private MenuModel menumodelonuevo;
    Menu subMenu;
    Submenu submenu1;
    Submenu submenu2;
    Subnivel subnivel=null;
    Itemmenu itemmenu = null;

    public MenuModel getMenumodelo() {
        return menumodelo;
    }

    public void setMenumodelo(MenuModel menumodelo) {
        this.menumodelo = menumodelo;
    }

    public MenuModel getMenumodelonuevo() {
        return menumodelonuevo;
    }

    public void setMenumodelonuevo(MenuModel menumodelonuevo) {
        this.menumodelonuevo = menumodelonuevo;
    }

    @PostConstruct
    public void init() {
        this.listadeMenu();
        this.listadeMenunuevo();
        menumodelo = new DefaultMenuModel();
        menumodelonuevo = new DefaultMenuModel();
        subMenu=new Menu();
 //       cargarMenu();
        cargarMenunuevo();

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

    public void listadeMenunuevo() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

        try {
            listamenurol = menurolEJB.itemsxrol(us.getIdrol());
        } catch (Exception e) {
        }
    }
 
    public void cargarMenunuevo() {
        for (Menurol sm : listamenurol) {
            if (sm.getIditemmenu().getIdsubmenu().getIdsubmenu() != 1) {
                if (submenu1 != sm.getIditemmenu().getIdsubmenu()) {
                    DefaultSubMenu nivel1submenu = new DefaultSubMenu(sm.getIditemmenu().getIdsubmenu().getSubmenu());
                    if (sm.getIditemmenu().getIdsubmenu().getIcon() != null) {
                        nivel1submenu.setIcon(sm.getIditemmenu().getIdsubmenu().getIcon());
                    }
                    submenu1 = sm.getIditemmenu().getIdsubmenu();
                    for (Menurol sm2 : listamenurol) {
                        if (sm2.getIditemmenu().getIdsubmenu().getIdsubmenu().equals(sm.getIditemmenu().getIdsubmenu().getIdsubmenu())) {
                            
                            if (subnivel!=sm2.getIditemmenu().getIdsubnivel()){
                                DefaultSubMenu nivel2submenu = new DefaultSubMenu(sm2.getIditemmenu().getIdsubnivel().getSubnivel());
                                if (sm2.getIditemmenu().getIdsubmenu().getIcon() != null) {
                                    nivel2submenu.setIcon(sm2.getIditemmenu().getIdsubmenu().getIcon());
                                }
                                subnivel = sm2.getIditemmenu().getIdsubnivel();

                                for (Menurol it : listamenurol) {
                                    submenu2 = it.getIditemmenu().getIdsubmenu();
                                    if (submenu2 != null) {
                                        if (sm2.getIditemmenu().getIdsubmenu().equals(it.getIditemmenu().getIdsubmenu())) {
                                            if (it.getIditemmenu().getIdsubnivel().equals(sm2.getIditemmenu().getIdsubnivel())) {
                                                DefaultMenuItem item = new DefaultMenuItem(it.getIditemmenu().getDescripcion());
                                                if (it.getIditemmenu().getUrl() != null) {
                                                    item.setUrl(it.getIditemmenu().getUrl());
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
                    menumodelonuevo.addElement(nivel1submenu);
                }
            } else {
                DefaultMenuItem item = new DefaultMenuItem(sm.getIditemmenu().getDescripcion());
                item.setIcon(sm.getIditemmenu().getIcon());
                if (sm.getIditemmenu().getUrl() != null) {
                    item.setUrl(sm.getIditemmenu().getUrl());
                }
                menumodelonuevo.addElement(item);
            }
        }
    }

    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

}
