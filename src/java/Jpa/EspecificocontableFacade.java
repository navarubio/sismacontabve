/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Empresa;
import Modelo.Especificocontable;
import Modelo.Subgrupocontable;
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
public class EspecificocontableFacade extends AbstractFacade<Especificocontable> implements EspecificocontableFacadeLocal{
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;
    
    private List<Especificocontable> lista = null;
    private String consulta;
    private Especificocontable especifico=null;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EspecificocontableFacade() {
        super(Especificocontable.class);
    }
    
    @Override
    public List<Especificocontable> espxSGrupo(int idgrupo, int idsubg, Empresa empre) {
        try {
            consulta = "From Especificocontable e where e.idempresa= ?1 and  e.idgrupocontable = ?2 and  e.idsubgrupocontable= ?3";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            query.setParameter(2, idgrupo);
            query.setParameter(3, idsubg);
            
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
    public List<Especificocontable> especificocontableAll(Empresa empre) {
        String consulta;
        List<Especificocontable> lista = null;
        try {
            consulta = "From Especificocontable ec where ec.idempresa= ?1 order by ec.codigocuenta";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
    @Override
    public List<Especificocontable> especificocontableModelo() {
        String consulta;
        int empresamodelo=0;
        List<Especificocontable> lista = null;
        try {
            consulta = "From Especificocontable ec where ec.idempresa= ?1 order by ec.codigocuenta";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empresamodelo);
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
}
