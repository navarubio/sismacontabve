/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Produccionpicadora;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Inpeca
 */
@Local
public interface ProduccionpicadoraFacadeLocal {

    void create(Produccionpicadora produccionpicadora);

    void edit(Produccionpicadora produccionpicadora);

    void remove(Produccionpicadora produccionpicadora);

    Produccionpicadora find(Object id);

    List<Produccionpicadora> findAll();

    List<Produccionpicadora> findRange(int[] range);

    int count();
    
    Produccionpicadora ultimoInsertado();
    
}
