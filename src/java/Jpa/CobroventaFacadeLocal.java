/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Cobroventa;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sofimar
 */
@Local
public interface CobroventaFacadeLocal {
   


    void create(Cobroventa cobroventa);

    void edit(Cobroventa cobroventa);

    void remove(Cobroventa cobroventa);

    Cobroventa find(Object id);

    List<Cobroventa> findAll();

    List<Cobroventa> findRange(int[] range);

    int count();
    
}
