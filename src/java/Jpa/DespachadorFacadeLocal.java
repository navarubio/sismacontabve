/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Deposito;
import Modelo.Despachador;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Inpeca
 */
@Local
public interface DespachadorFacadeLocal {

    void create(Despachador despachador);

    void edit(Despachador despachador);

    void remove(Despachador despachador);

    Despachador find(Object id);

    List<Despachador> findAll();

    List<Despachador> findRange(int[] range);

    int count();
    
}
