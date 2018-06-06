/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Empresa;
import Modelo.Grupocontable;
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
public class SubgrupocontableFacade extends AbstractFacade<Subgrupocontable> implements SubgrupocontableFacadeLocal{
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;
    
    private List<Subgrupocontable> lista = null;
    private String consulta;
    private Subgrupocontable subgrupo=null;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubgrupocontableFacade() {
        super(Subgrupocontable.class);
    }
 
    @Override
    public List<Subgrupocontable> subgxGrupo(int idgrupo, Empresa empre) {
        try {
            consulta = "From Subgrupocontable s where s.idempresa= ?1 and s.idgrupocontable = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            query.setParameter(2, idgrupo);            
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
    public List<Subgrupocontable> subgrupocontableAll(Empresa empre) {
        String consulta;
        List<Subgrupocontable> lista = null;
        try {
            consulta = "From Subgrupocontable sg where sg.idempresa= ?1 order by sg.codigocuenta";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
    @Override
    public List<Subgrupocontable> subgrupocontableModelo() {
        String consulta;
        int empresamodelo=0;
        List<Subgrupocontable> lista = null;
        try {
            consulta = "From Subgrupocontable sg where sg.idempresa= ?1 order by sg.codigocuenta";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empresamodelo);
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
}
