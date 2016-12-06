/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Estatusfacturaventa;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sofimar
 */
@Local
public interface EstatusfacturaventaFacadeLocal {

    void create(Estatusfacturaventa estatusfacturaventa);

    void edit(Estatusfacturaventa estatusfacturaventa);

    void remove(Estatusfacturaventa estatusfacturaventa);

    Estatusfacturaventa find(Object id);

    List<Estatusfacturaventa> findAll();

    List<Estatusfacturaventa> findRange(int[] range);

    int count();
    
    public Estatusfacturaventa estatusFacturaPorCobrar ();
    
}
