/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Empresa;
import Modelo.Tipoingreso;
import Modelo.Tipopago;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Inpeca
 */
@Local
public interface TipoingresoFacadeLocal {

    void create(Tipoingreso tipoingreso);

    void edit(Tipoingreso tipoingreso);

    void remove(Tipoingreso tipoingreso);

    Tipoingreso find(Object id);

    List<Tipoingreso> findAll();

    List<Tipoingreso> findRange(int[] range);

    int count();
    
    List<Tipoingreso> tipoingresoAll(Empresa empre);
    
}
