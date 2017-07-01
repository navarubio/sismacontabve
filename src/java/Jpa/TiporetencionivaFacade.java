/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Tiporetencioniva;
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
public class TiporetencionivaFacade extends AbstractFacade<Tiporetencioniva> implements TiporetencionivaFacadeLocal{
    @PersistenceContext(unitName = "InpecaPU")
    private EntityManager em;
    private List<Tiporetencioniva> lista = null;
    private String consulta;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TiporetencionivaFacade() {
        super(Tiporetencioniva.class);
    }
    
    @Override
    public List<Tiporetencioniva> tiporetencionivaxGrupo (int idgrupo)  {
        try { 
            consulta = "From Tiporetencioniva t where t.idgrupo.idgrupo= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, idgrupo);
            
            lista = query.getResultList();
//            if (!lista.isEmpty()) {
//                usuario = lista.get(0);
//            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

}
