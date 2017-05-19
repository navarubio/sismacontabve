/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Medida;
import Modelo.Movimientoinventariopicadora;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Inpeca
 */
@Local
public interface MovimientoinventariopicadoraFacadeLocal {

    void create(Movimientoinventariopicadora movimientoinventariopicadora);

    void edit(Movimientoinventariopicadora movimientoinventariopicadora);

    void remove(Movimientoinventariopicadora movimientoinventariopicadora);

    Movimientoinventariopicadora find(Object id);

    List<Movimientoinventariopicadora> findAll();

    List<Movimientoinventariopicadora> findRange(int[] range);

    int count();
    
}
