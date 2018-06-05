/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Cobroventa;
import Modelo.Empresa;
import Modelo.Grupocontable;
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
public class GrupocontableFacade extends AbstractFacade<Grupocontable> implements GrupocontableFacadeLocal{
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupocontableFacade() {
        super(Grupocontable.class);
    }
    
    @Override
    public List<Grupocontable> grupocontableAll(Empresa empre) {
        String consulta;
        List<Grupocontable> lista = null;
        try {
            consulta = "From Grupocontable g where g.idempresa= ?1 order by g.codigocuenta";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
    
    
}
