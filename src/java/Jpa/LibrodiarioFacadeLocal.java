/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Empresa;
import Modelo.Librodiario;
import Modelo.Medida;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Inpeca
 */
@Local
public interface LibrodiarioFacadeLocal {

    void create(Librodiario librodiario);

    void edit(Librodiario librodiario);

    void remove(Librodiario librodiario);

    Librodiario find(Object id);

    List<Librodiario> findAll();

    List<Librodiario> findRange(int[] range);

    int count();
    
    Librodiario ultimoInsertado(Empresa empre);
    
}
