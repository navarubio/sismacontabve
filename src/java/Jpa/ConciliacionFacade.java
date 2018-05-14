/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Conciliacion;
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
public class ConciliacionFacade extends AbstractFacade<Conciliacion> implements ConciliacionFacadeLocal{
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConciliacionFacade() {
        super(Conciliacion.class);
    }
    
    @Override
    public Conciliacion ultimaConciliacionInsertada() {
        String consulta = null;
        Conciliacion ultima = new Conciliacion();
        try {
            consulta = "Select c From Conciliacion c Order By c.idconciliacion Desc";
            Query query = em.createQuery(consulta);
            List<Conciliacion> lista = query.getResultList();
            if (!lista.isEmpty()) {
                ultima = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return ultima;
    }
    
}
