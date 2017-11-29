/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Autorizacion;
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
public class AutorizacionFacade extends AbstractFacade<Autorizacion> implements AutorizacionFacadeLocal{
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AutorizacionFacade() {
        super(Autorizacion.class);
    }
    @Override
    public Autorizacion buscarAutorizacion (int idcompra) {
        Autorizacion autorizacion = null;
        String consulta;
        try {
            consulta = "From Autorizacion a where a.idcompra.idcompra = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, idcompra);

            List<Autorizacion> lista = query.getResultList();
            if (!lista.isEmpty()) {
                autorizacion = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return autorizacion;
    }
    
    @Override
    public Autorizacion ultimaautorizacionInsertada() {
        String consulta = null;
        Autorizacion ultimo = new Autorizacion();
        try {
            consulta = "Select a From Autorizacion a Order By a.idautorizacion Desc";
            Query query = em.createQuery(consulta);
            List<Autorizacion> lista = query.getResultList();
            if (!lista.isEmpty()) {
                ultimo = lista.get(0);
}
        } catch (Exception e) {
            throw e;
        }
        return ultimo;
    }
}
