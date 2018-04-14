/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Tipogastocajachica;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sofimar
 */
@Stateless
public class TipogastocajachicaFacade extends AbstractFacade<Tipogastocajachica> implements TipogastocajachicaFacadeLocal{
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipogastocajachicaFacade() {
        super(Tipogastocajachica.class);
    }
    
}
