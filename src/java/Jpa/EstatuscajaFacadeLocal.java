/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Estatuscaja;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sofimar
 */
@Local
public interface EstatuscajaFacadeLocal {

    void create(Estatuscaja estatuscaja);

    void edit(Estatuscaja estatuscaja);

    void remove(Estatuscaja estatuscaja);

    Estatuscaja find(Object id);

    List<Estatuscaja> findAll();

    List<Estatuscaja> findRange(int[] range);

    int count();
    
}
