/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Personalidadjuridica;
import Modelo.Residenciajuridica;
import Modelo.Tiporetencionislr;
import Modelo.Tiporetencioniva;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Inpeca
 */
@Local
public interface TiporetencionivaFacadeLocal {

    void create(Tiporetencioniva tiporetencioniva);

    void edit(Tiporetencioniva tiporetencioniva);

    void remove(Tiporetencioniva tiporetencioniva);

    Tiporetencioniva find(Object id);

    List<Tiporetencioniva> findAll();

    List<Tiporetencioniva> findRange(int[] range);

    int count();
    
    List<Tiporetencioniva> tiporetencionivaxGrupo (int idgrupo);
}
