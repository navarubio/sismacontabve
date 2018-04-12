/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Reposicioncajachica;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sofimar
 */
@Local
public interface ReposicioncajachicaFacadeLocal {

    void create(Reposicioncajachica reposicioncajachica);

    void edit(Reposicioncajachica reposicioncajachica);

    void remove(Reposicioncajachica reposicioncajachica);

    Reposicioncajachica find(Object id);

    List<Reposicioncajachica> findAll();

    List<Reposicioncajachica> findRange(int[] range);

    int count();
    
}
