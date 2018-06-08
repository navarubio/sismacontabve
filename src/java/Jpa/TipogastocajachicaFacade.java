/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Empresa;
import Modelo.Tipogastocajachica;
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
    
    @Override
    public List<Tipogastocajachica> tipogastocajachicaAll(Empresa empre) {
        String consulta;
        List<Tipogastocajachica> lista = null;
        try {
            consulta = "SELECT t FROM Tipogastocajachica t  where t.idempresa= ?1 order by t.idtipogastocajachica";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
}
