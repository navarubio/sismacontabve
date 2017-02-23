/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Tiporetencionislr;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Inpeca
 */
@Local
public interface TiporetencionislrFacadeLocal {

    void create(Tiporetencionislr tiporetencionislr);

    void edit(Tiporetencionislr tiporetencionislr);

    void remove(Tiporetencionislr tiporetencionislr);

    Tiporetencionislr find(Object id);

    List<Tiporetencionislr> findAll();

    List<Tiporetencionislr> findRange(int[] range);

    int count();
    
    Tiporetencionislr retencionislrFiltrada(int persona, int residen, int subg);
}
