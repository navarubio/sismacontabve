/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Retencionivasri;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sofimar
 */
@Local
public interface RetencionivasriFacadeLocal {

    void create(Retencionivasri retencionivasri);

    void edit(Retencionivasri retencionivasri);

    void remove(Retencionivasri retencionivasri);

    Retencionivasri find(Object id);

    List<Retencionivasri> findAll();

    List<Retencionivasri> findRange(int[] range);

    int count();
    
    Retencionivasri buscarcoPorcentajes (int codigoretencion);
}
