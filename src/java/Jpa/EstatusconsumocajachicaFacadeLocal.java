/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Estatusconsumocajachica;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sofimar
 */
@Local
public interface EstatusconsumocajachicaFacadeLocal {

    void create(Estatusconsumocajachica estatusconsumocajachica);

    void edit(Estatusconsumocajachica estatusconsumocajachica);

    void remove(Estatusconsumocajachica estatusconsumocajachica);

    Estatusconsumocajachica find(Object id);

    List<Estatusconsumocajachica> findAll();

    List<Estatusconsumocajachica> findRange(int[] range);

    int count();
    
    Estatusconsumocajachica cambiarestatusConsumo(int tipo); 
    
}
