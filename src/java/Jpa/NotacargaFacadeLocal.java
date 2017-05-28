/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Notacarga;
import Modelo.Otroingreso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Inpeca
 */
@Local
public interface NotacargaFacadeLocal {

    void create(Notacarga notacarga);

    void edit(Notacarga notacarga);

    void remove(Notacarga notacarga);

    Notacarga find(Object id);

    List<Notacarga> findAll();

    List<Notacarga> findRange(int[] range);

    int count();
    
    Notacarga ultimaInsertada();
    
    int siguientenotacarga();
        
}
