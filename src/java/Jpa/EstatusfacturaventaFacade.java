/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Estatusfacturaventa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author sofimar
 */
@Stateless
public class EstatusfacturaventaFacade extends AbstractFacade<Estatusfacturaventa> implements EstatusfacturaventaFacadeLocal{
    @PersistenceContext(unitName = "InpecaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstatusfacturaventaFacade() {
        super(Estatusfacturaventa.class);
    }
    
    @Override
    public Estatusfacturaventa estatusFacturaPorCobrar () {
        Estatusfacturaventa estatus = null;
        String consulta;
        try {
            consulta  = "From Estatusfacturaventa e where e.idestatusfacturaventa= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, 2);

            List<Estatusfacturaventa> lista = query.getResultList();
            if (!lista.isEmpty()) {
                estatus = lista.get(0);
}
        } catch (Exception e) {
            throw e;
        }
        return estatus;
    }
    
    
}
