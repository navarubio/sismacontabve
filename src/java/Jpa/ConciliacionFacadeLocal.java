/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Conciliacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sofimar
 */
@Local
public interface ConciliacionFacadeLocal {

    void create(Conciliacion conciliacion);

    void edit(Conciliacion conciliacion);

    void remove(Conciliacion conciliacion);

    Conciliacion find(Object id);

    List<Conciliacion> findAll();

    List<Conciliacion> findRange(int[] range);

    int count();
    
    Conciliacion ultimaConciliacionInsertada();
    
}
