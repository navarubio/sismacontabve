/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Caja;
import Modelo.Camion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sofimar
 */
@Local
public interface CamionFacadeLocal {

    void create(Camion camion);

    void edit(Camion camion);

    void remove(Camion camion);

    Camion find(Object id);

    List<Camion> findAll();

    List<Camion> findRange(int[] range);

    int count();
    
    
}
