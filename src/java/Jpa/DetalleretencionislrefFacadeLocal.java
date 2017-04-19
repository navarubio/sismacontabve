/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Detallefactura;
import Modelo.Detalleretencionislref;
import Modelo.Factura;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sofimar
 */
@Local
public interface DetalleretencionislrefFacadeLocal {

    void create(Detalleretencionislref detalleretencionislref);

    void edit(Detalleretencionislref detalleretencionislref);

    void remove(Detalleretencionislref detalleretencionislref);

    Detalleretencionislref find(Object id);

    List<Detalleretencionislref> findAll();

    List<Detalleretencionislref> findRange(int[] range);

    int count();
    
    List<Detalleretencionislref> buscarretencionesporPreveedor(String rif);
    
    List<Detalleretencionislref> buscarretencionesActivas(); 
    
    double retencionislrporpago(int compra);
    
}
