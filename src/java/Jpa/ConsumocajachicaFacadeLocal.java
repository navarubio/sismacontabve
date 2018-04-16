/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Consumocajachica;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sofimar
 */
@Local
public interface ConsumocajachicaFacadeLocal {

    void create(Consumocajachica consumocajachica);

    void edit(Consumocajachica consumocajachica);

    void remove(Consumocajachica consumocajachica);

    Consumocajachica find(Object id);

    List<Consumocajachica> findAll();

    List<Consumocajachica> findRange(int[] range);
    
    List<Consumocajachica> consumosxCaja(int idcaja);

    int count();
    
}
