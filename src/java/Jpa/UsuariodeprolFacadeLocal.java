/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Empresa;
import Modelo.Usuario;
import Modelo.Usuariodeprol;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sofimar
 */
@Local
public interface UsuariodeprolFacadeLocal {

    void create(Usuariodeprol usuariodeprol);

    void edit(Usuariodeprol usuariodeprol);

    void remove(Usuariodeprol usuariodeprol);

    Usuariodeprol find(Object id);

    List<Usuariodeprol> findAll();

    List<Usuariodeprol> findRange(int[] range);

    int count();
    
    Usuariodeprol UsuarioDptoRol (Usuario us, Empresa empre);
    
}
