/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Consumocajachica;
import Modelo.Detalleconsumocajachica;
import Modelo.Empresa;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sofimar
 */
@Local
public interface DetalleconsumocajachicaFacadeLocal {

    void create(Detalleconsumocajachica detalleconsumocajachica);

    void edit(Detalleconsumocajachica detalleconsumocajachica);

    void remove(Detalleconsumocajachica detalleconsumocajachica);

    Detalleconsumocajachica find(Object id);

    List<Detalleconsumocajachica> findAll();

    List<Detalleconsumocajachica> findRange(int[] range);

    int count();
    
    List <Detalleconsumocajachica> listadetalleconsumosxListaConsumos (List<Consumocajachica> listaconsumos);
    
    List<Detalleconsumocajachica> detallesxConsumo(int idconsumocajachica);
    
    List<Detalleconsumocajachica> detallesconsumoscajachicaAll(Empresa empre);
}
