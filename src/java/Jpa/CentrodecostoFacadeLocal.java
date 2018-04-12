/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Centrodecosto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sofimar
 */
@Local
public interface CentrodecostoFacadeLocal {

    void create(Centrodecosto centrodecosto);

    void edit(Centrodecosto centrodecosto);

    void remove(Centrodecosto centrodecosto);

    Centrodecosto find(Object id);

    List<Centrodecosto> findAll();

    List<Centrodecosto> findRange(int[] range);

    int count();
    
}
