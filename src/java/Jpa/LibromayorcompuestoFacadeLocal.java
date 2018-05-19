/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Libromayor;
import Modelo.Libromayorcompuesto;
import Modelo.Plandecuenta;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sofimar
 */
@Local
public interface LibromayorcompuestoFacadeLocal {

    void create(Libromayorcompuesto libromayorcompuesto);

    void edit(Libromayorcompuesto libromayorcompuesto);

    void remove(Libromayorcompuesto libromayorcompuesto);

    Libromayorcompuesto find(Object id);

    List<Libromayorcompuesto> findAll();

    List<Libromayorcompuesto> findRange(int[] range);

    int count();
    
    List<Libromayorcompuesto> buscarmayorporfecha (Integer cuentacontable, Date fechaini, Date fechafinish);
    
    List<Libromayorcompuesto> buscarmayorporfechafinal (Integer cuentacontable, Date fechafinish);
}
