/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Cajachica;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sofimar
 */
@Local
public interface CajachicaFacadeLocal {

    void create(Cajachica cajachica);

    void edit(Cajachica cajachica);

    void remove(Cajachica cajachica);

    Cajachica find(Object id);

    List<Cajachica> findAll();

    List<Cajachica> findRange(int[] range);

    int count();
    
}
