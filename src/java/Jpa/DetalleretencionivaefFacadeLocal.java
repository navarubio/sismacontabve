/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Comprobanteivaef;
import Modelo.Detalleretencionivaef;
import Modelo.Empresa;
import Modelo.Factura;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sofimar
 */
@Local
public interface DetalleretencionivaefFacadeLocal {

    void create(Detalleretencionivaef detalleretencionivaef);

    void edit(Detalleretencionivaef detalleretencionivaef);

    void remove(Detalleretencionivaef detalleretencionivaef);

    Detalleretencionivaef  find(Object id);

    List<Detalleretencionivaef> findAll();

    List<Detalleretencionivaef> findRange(int[] range);

    int count();
    
    List<Detalleretencionivaef> buscarretencionesporPreveedor(String rif, Empresa empre); 
    
    List<Detalleretencionivaef> buscarretencionesActivas(Empresa empre);
    
    double retencionivaporpago(int pago);
}
