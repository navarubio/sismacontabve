/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Otroingreso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Inpeca
 */
@Local
public interface OtroingresoFacadeLocal {

    void create(Otroingreso otroingreso);

    void edit(Otroingreso otroingreso);

    void remove(Otroingreso otroingreso);

    Otroingreso find(Object id);

    List<Otroingreso> findAll();

    List<Otroingreso> findRange(int[] range);

    int count();
    
    Otroingreso ultimoingreso(); 
    
}
