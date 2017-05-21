/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Articulo;
import Modelo.Grupocontable;
import Modelo.Inventariopicadora;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Inpeca
 */
@Local
public interface InventariopicadoraFacadeLocal {

    void create(Inventariopicadora inventariopicadora);

    void edit(Inventariopicadora inventariopicadora);

    void remove(Inventariopicadora inventariopicadora);

    Inventariopicadora find(Object id);

    List<Inventariopicadora> findAll();

    List<Inventariopicadora> findRange(int[] range);

    int count();
    
    Inventariopicadora buscarAgregado (Articulo articulo);
    
}
