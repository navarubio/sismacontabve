/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Estatusconsumocajachica;
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
    
    @Override
    public Estatusconsumocajachica cambiarestatusConsumo(int tipo) {
        String consulta;
        int idstatus=tipo;
        Estatusconsumocajachica estatus = new Estatusconsumocajachica();
        try {
            consulta = "From Estatusconsumocajachica e where e.idestatusconsumocajachica= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, idstatus);
            List<Estatusconsumocajachica> lista = query.getResultList();
            if (!lista.isEmpty()) {
                estatus = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return estatus;
    }

    
    
}
