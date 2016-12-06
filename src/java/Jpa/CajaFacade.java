/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Caja;
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
public class CajaFacade extends AbstractFacade<Caja> implements CajaFacadeLocal{
    @PersistenceContext(unitName = "InpecaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CajaFacade() {
        super(Caja.class);
    }
    
    @Override
    public Caja ubicarCaja () {
        Caja caja = null;
        String consulta;
        try {
            consulta  = "From Caja c where c.idcaja = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, 1);

            List<Caja> lista = query.getResultList();
            if (!lista.isEmpty()) {
                caja = lista.get(0);
}
        } catch (Exception e) {
            throw e;
        }
        return caja;
    }
}
