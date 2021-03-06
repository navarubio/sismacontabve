/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Cajachica;
import Modelo.Empresa;
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
public class CajachicaFacade extends AbstractFacade<Cajachica> implements CajachicaFacadeLocal {
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CajachicaFacade() {
        super(Cajachica.class);
    }
    
    @Override
    public List<Cajachica> cajaschicasAll(Empresa empre) {
        String consulta;
        List<Cajachica> lista = null;
        try {
            consulta = "SELECT c FROM Cajachica c where c.idempresa.idempresa = ?1 order by c.idcajachica";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
    
}
