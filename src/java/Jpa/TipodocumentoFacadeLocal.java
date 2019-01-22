/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Tipodocumento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Lic. Manuel Nava Rubio
 */
@Local
public interface TipodocumentoFacadeLocal {

    void create(Tipodocumento tipodocumento);

    void edit(Tipodocumento tipodocumento);

    void remove(Tipodocumento tipodocumento);

    Tipodocumento find(Object id);

    List<Tipodocumento> findAll();

    List<Tipodocumento> findRange(int[] range);

    int count();
    
}
