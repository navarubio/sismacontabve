/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Empresa;
import Modelo.Plandecuenta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Inpeca
 */
@Local
public interface PlandecuentaFacadeLocal {

    void create(Plandecuenta plandecuenta);

    void edit(Plandecuenta plandecuenta);

    void remove(Plandecuenta plandecuenta);

    Plandecuenta find(Object id);

    List<Plandecuenta> findAll();

    List<Plandecuenta> findRange(int[] range);
    
    List<Plandecuenta> itemsordenados(Empresa empresa);

    int count();
    
    Plandecuenta buscarcuenta(int codcta, Empresa empre);
    
    Plandecuenta buscarcuentaxcodigo(int codcta, Empresa empre);
    
    double buscarsaldoanterior(int codcta, Empresa empre );
    
    List<Plandecuenta> cuentasdeMovimiento();
    
    List<Plandecuenta> itemseeff(Empresa empre, int tipocuenta);
    
}
