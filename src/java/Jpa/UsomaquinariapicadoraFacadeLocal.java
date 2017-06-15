/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Tiposaldocontable;
import Modelo.Tipotrabajomaquinaria;
import Modelo.Usomaquinariapicadora;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Inpeca
 */
@Local
public interface UsomaquinariapicadoraFacadeLocal {

    void create(Usomaquinariapicadora usomaquinariapicadora);

    void edit(Usomaquinariapicadora usomaquinariapicadora);

    void remove(Usomaquinariapicadora usomaquinariapicadora);

    Usomaquinariapicadora find(Object id);

    List<Usomaquinariapicadora> findAll();

    List<Usomaquinariapicadora> findRange(int[] range);
    
    List<Usomaquinariapicadora> usomaquinafechadesc();

    int count();
    
}
