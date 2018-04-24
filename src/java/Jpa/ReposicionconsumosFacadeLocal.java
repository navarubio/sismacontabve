/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Cajachica;
import Modelo.Consumocajachica;
import Modelo.Reposicioncajachica;
import Modelo.Reposicionconsumos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sofimar
 */
@Local
public interface ReposicionconsumosFacadeLocal {

    void create(Reposicionconsumos reposicionconsumos);

    void edit(Reposicionconsumos reposicionconsumos);

    void remove(Reposicionconsumos reposicionconsumos);

    Reposicionconsumos find(Object id);

    List<Reposicionconsumos> findAll();

    List<Reposicionconsumos> findRange(int[] range);

    int count();
    
    Cajachica devolverCajachica (Reposicioncajachica reposicion);
    
    List <Reposicionconsumos> listaReposicionconsumosfiltrada (Reposicioncajachica reposicion);
    
    List <Consumocajachica> listaconsumosxReposicion (Reposicioncajachica reposicion);
    
    Reposicioncajachica devolverReposicionxConsumo (Consumocajachica consumo);
}
