/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Tipoingreso;
import Modelo.Tipomaquinaria;
import Modelo.Tipopago;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Inpeca
 */
@Local
public interface TipomaquinariaFacadeLocal {

    void create(Tipomaquinaria tipomaquinaria);

    void edit(Tipomaquinaria tipomaquinaria);

    void remove(Tipomaquinaria tipomaquinaria);

    Tipomaquinaria find(Object id);

    List<Tipomaquinaria> findAll();

    List<Tipomaquinaria> findRange(int[] range);

    int count();
    
}
