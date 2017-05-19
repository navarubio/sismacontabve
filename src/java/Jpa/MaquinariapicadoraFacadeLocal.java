/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Maquinariapicadora;
import Modelo.Medida;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Inpeca
 */
@Local
public interface MaquinariapicadoraFacadeLocal {

    void create(Maquinariapicadora maquinariapicadora);

    void edit(Maquinariapicadora maquinariapicadora);

    void remove(Maquinariapicadora maquinariapicadora);

    Maquinariapicadora find(Object id);

    List<Maquinariapicadora> findAll();

    List<Maquinariapicadora> findRange(int[] range);

    int count();
    
}
