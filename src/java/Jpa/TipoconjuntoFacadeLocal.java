/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Tipoconjunto;
import Modelo.Tipocuentacontable;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Inpeca
 */
@Local
public interface TipoconjuntoFacadeLocal {

    void create(Tipoconjunto tipoconjunto);

    void edit(Tipoconjunto tipoconjunto);

    void remove(Tipoconjunto tipoconjunto);

    Tipoconjunto find(Object id);

    List<Tipoconjunto> findAll();

    List<Tipoconjunto> findRange(int[] range);

    int count();
    
    Tipoconjunto cambiartipoConjunto(int tipo);
}
