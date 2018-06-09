/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Empresa;
import Modelo.Especificocontable;
import Modelo.Subespecificocontable;
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
public class SubespecificocontableFacade extends AbstractFacade<Subespecificocontable> implements SubespecificocontableFacadeLocal{
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;
    
    private List<Subespecificocontable> lista = null;
    private String consulta;
    private Subespecificocontable subespecifico=null;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubespecificocontableFacade() {
        super(Subespecificocontable.class);
    }
    
    @Override
    public List<Subespecificocontable> subespxEspecifico(int idgrupo, int idsubg, int idespec, Empresa empre) {
        try {
            consulta = "From Subespecificocontable s where s.idempresa= ?1 and s.idgrupocontable = ?2 and  s.idsubgrupocontable= ?3 and  s.idespecificocontable= ?4";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            query.setParameter(2, idgrupo);
            query.setParameter(3, idsubg);
            query.setParameter(4, idespec);
            
            lista = query.getResultList();
//            if (!lista.isEmpty()) {
//                usuario = lista.get(0);
//            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
    @Override
    public List<Subespecificocontable> subespecificocontableAll(Empresa empre) {
        String consulta;
        List<Subespecificocontable> lista = null;
        try {
            consulta = "From Subespecificocontable sec where sec.idempresa= ?1 order by sec.codigocuenta";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
    @Override
    public List<Subespecificocontable> subespecificocontableModelo() {
        String consulta;
        int empresamodelo=0;
        List<Subespecificocontable> lista = null;
        try {
            consulta = "From Subespecificocontable sec where sec.idempresa= ?1 order by sec.codigocuenta";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empresamodelo);
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
}
