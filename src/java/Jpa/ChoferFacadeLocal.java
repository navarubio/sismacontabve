/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Caja;
import Modelo.Camion;
import Modelo.Chofer;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sofimar
 */
@Local
public interface ChoferFacadeLocal {

    void create(Chofer chofer);

    void edit(Chofer chofer);

    void remove(Chofer chofer);

    Chofer find(Object id);

    List<Chofer> findAll();

    List<Chofer> findRange(int[] range);

    int count();
    
}
