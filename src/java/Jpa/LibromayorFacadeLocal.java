/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Librodiario;
import Modelo.Libromayor;
import Modelo.Medida;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Inpeca
 */
@Local
public interface LibromayorFacadeLocal {

    void create(Libromayor libromayor);

    void edit(Libromayor libromayor);

    void remove(Libromayor libromayor);

    Libromayor find(Object id);

    List<Libromayor> findAll();

    List<Libromayor> findRange(int[] range);

    int count();
    
    List<Libromayor> listacuentaespecifica(int codcta);
    
    Libromayor buscarLibro (Integer id);
    
    Libromayor ultimoInsertado();
    
}
