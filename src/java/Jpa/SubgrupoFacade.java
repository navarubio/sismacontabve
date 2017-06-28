/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Residenciajuridica;
import Modelo.Subgrupo;
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
public class SubgrupoFacade extends AbstractFacade<Subgrupo> implements SubgrupoFacadeLocal{
    @PersistenceContext(unitName = "InpecaPU")
    private EntityManager em;
    private List<Subgrupo> lista = null;
    private String consulta;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubgrupoFacade() {
        super(Subgrupo.class);
    }
    
    @Override
    public List<Subgrupo> subgrupoxGrupo (int idgrupo) {
        try { 
            consulta = "From Subgrupo s where s.idgrupo.idgrupo= ?1";
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
