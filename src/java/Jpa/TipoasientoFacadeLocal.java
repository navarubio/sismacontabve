/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Tipoasiento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Inpeca
 */
@Local
public interface TipoasientoFacadeLocal {

    void create(Tipoasiento tipoasiento);

    void edit(Tipoasiento tipoasiento);

    void remove(Tipoasiento tipoasiento);

    Tipoasiento find(Object id);

    List<Tipoasiento> findAll();

    List<Tipoasiento> findRange(int[] range);

    int count();
    
    Tipoasiento TipoAsientoNormal ();
    
}
