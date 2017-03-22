/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Detallefactura;
import Modelo.Detalleretencionislref;
import Modelo.Detalleretencionislrsp;
import Modelo.Factura;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sofimar
 */
@Local
public interface DetalleretencionislrspFacadeLocal {

    void create(Detalleretencionislrsp detalleretencionislrsp);

    void edit(Detalleretencionislrsp detalleretencionislrsp);

    void remove(Detalleretencionislrsp detalleretencionislrsp);

    Detalleretencionislrsp find(Object id);

    List<Detalleretencionislrsp> findAll();

    List<Detalleretencionislrsp> findRange(int[] range);

    int count();
    
    
}
