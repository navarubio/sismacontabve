/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Tiposaldocontable;
import Modelo.Tipotrabajomaquinaria;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Inpeca
 */
@Local
public interface TipotrabajomaquinariaFacadeLocal {

    void create(Tipotrabajomaquinaria tipotrabajomaquinaria);

    void edit(Tipotrabajomaquinaria tipotrabajomaquinaria);

    void remove(Tipotrabajomaquinaria tipotrabajomaquinaria);

    Tipotrabajomaquinaria find(Object id);

    List<Tipotrabajomaquinaria> findAll();

    List<Tipotrabajomaquinaria> findRange(int[] range);

    int count();
    
}
