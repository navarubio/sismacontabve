/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Comprobanteivaef;
import Modelo.Detalleretencionivaef;
import Modelo.Detalleretencionivasp;
import Modelo.Factura;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sofimar
 */
@Local
public interface DetalleretencionivaspFacadeLocal {

    void create(Detalleretencionivasp detalleretencionivasp);

    void edit(Detalleretencionivasp detalleretencionivasp);

    void remove(Detalleretencionivasp detalleretencionivasp);

    Detalleretencionivasp  find(Object id);

    List<Detalleretencionivasp> findAll();

    List<Detalleretencionivasp> findRange(int[] range);

    int count();
}
