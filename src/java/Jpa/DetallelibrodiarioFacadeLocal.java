/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Detallelibrodiario;
import Modelo.Librodiario;
import Modelo.Medida;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Inpeca
 */
@Local
public interface DetallelibrodiarioFacadeLocal {

    void create(Detallelibrodiario detallelibrodiario);

    void edit(Detallelibrodiario detallelibrodiario);

    void remove(Detallelibrodiario detallelibrodiario);

    Detallelibrodiario find(Object id);

    List<Detallelibrodiario> findAll();

    List<Detallelibrodiario> findRange(int[] range);

    int count();
    
    List<Detallelibrodiario> detalleslibrodiarioAll(); 
    
}
