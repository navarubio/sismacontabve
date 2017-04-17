/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Estatuscontable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Inpeca
 */
@Stateless
public class EstatuscontableFacade extends AbstractFacade<Estatuscontable> implements EstatuscontableFacadeLocal{
    @PersistenceContext(unitName = "InpecaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstatuscontableFacade() {
        super(Estatuscontable.class);
    }
    
    @Override
    public Estatuscontable estatusContablePorRegistrar () {
        Estatuscontable estatus = null;
        int paramet = 1;
        String consulta;
        try {
            consulta  = "From Estatuscontable e where e.idestatuscontable = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, paramet);

            List<Estatuscontable> lista = query.getResultList();
            if (!lista.isEmpty()) {
                estatus = lista.get(0);
}
        } catch (Exception e) {
            throw e;
        }
        return estatus;
    }
    
    @Override
    public Estatuscontable estatusContableRegistrada () {
        Estatuscontable estatus = null;
        int paramet = 2;
        String consulta;
        try {
            consulta  = "From Estatuscontable e where e.idestatuscontable = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, paramet);

            List<Estatuscontable> lista = query.getResultList();
            if (!lista.isEmpty()) {
                estatus = lista.get(0);
}
        } catch (Exception e) {
            throw e;
        }
        return estatus;
    }
}
