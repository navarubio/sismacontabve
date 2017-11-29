/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Contribuyente;
import Modelo.Cuentabancaria;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Inpeca
 */
@Stateless

public class ContribuyenteFacade extends AbstractFacade<Contribuyente> implements ContribuyenteFacadeLocal{
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;
    private List<Contribuyente> lista = null;
    private String consulta;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContribuyenteFacade() {
        super(Contribuyente.class);
    }
    
    @Override
    public List<Contribuyente> contribuyentexPersona (int idpersona) {
        try { 
            consulta = "From Contribuyente c where c.idpersonalidad.idpersonalidad= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, idpersona);
            
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
