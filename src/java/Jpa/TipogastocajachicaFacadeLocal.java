/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Tipogastocajachica;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sofimar
 */
@Local
public interface TipogastocajachicaFacadeLocal {

    void create(Tipogastocajachica tipogastocajachica);

    void edit(Tipogastocajachica tipogastocajachica);

    void remove(Tipogastocajachica tipogastocajachica);

    Tipogastocajachica find(Object id);

    List<Tipogastocajachica> findAll();

    List<Tipogastocajachica> findRange(int[] range);

    int count();
    
}
