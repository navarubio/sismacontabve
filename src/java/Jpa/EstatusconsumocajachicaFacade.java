/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Estatusconsumocajachica;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sofimar
 */
@Stateless
public class EstatusconsumocajachicaFacade extends AbstractFacade<Estatusconsumocajachica> implements EstatusconsumocajachicaFacadeLocal{
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstatusconsumocajachicaFacade() {
        super(Estatusconsumocajachica.class);
    }
    
}
