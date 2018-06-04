/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Empresa;
import Modelo.Factura;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sofimar
 */
@Local
public interface FacturaFacadeLocal {

    void create(Factura factura);

    void edit(Factura factura);

    void remove(Factura factura);

    Factura find(Object id);

    List<Factura> findAll();

    List<Factura> findRange(int[] range);

    int count();
    
    Factura ultimaInsertada();
     
    int siguientefactura();
    
    String  siguientefacturaformat(); 
    
    List<Factura> buscarfacturasporCobrar(Empresa empre);
    
    List<Factura> buscarfacturasCobradas();
    
    String  ultimafacturaformat();
    
    List<Factura> facturasAll(Empresa empre);
    
}
