/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Tipotrabajomaquinaria;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sofimar
 */
@Stateless
public class TipotrabajomaquinariaFacade extends AbstractFacade<Tipotrabajomaquinaria> implements TipotrabajomaquinariaFacadeLocal{
    @PersistenceContext(unitName = "InpecaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipotrabajomaquinariaFacade() {
        super(Tipotrabajomaquinaria.class);
    }
    
}
