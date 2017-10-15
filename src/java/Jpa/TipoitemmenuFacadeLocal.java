/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Tipoingreso;
import Modelo.Tipoitemmenu;
import Modelo.Tipomaquinaria;
import Modelo.Tipopago;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Inpeca
 */
@Local
public interface TipoitemmenuFacadeLocal {

    void create(Tipoitemmenu tipoitemmenu);

    void edit(Tipoitemmenu tipoitemmenu);

    void remove(Tipoitemmenu tipoitemmenu);

    Tipoitemmenu find(Object id);

    List<Tipoitemmenu> findAll();

    List<Tipoitemmenu> findRange(int[] range);

    int count();
    
}
