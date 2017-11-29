/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Detalleretencionislrsp;
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
public class DetalleretencionislrspFacade extends AbstractFacade<Detalleretencionislrsp> implements DetalleretencionislrspFacadeLocal{
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleretencionislrspFacade() {
        super(Detalleretencionislrsp.class);
    }
    
    @Override
    public double retencionislrencobro(int factu) {
        String consulta;
        Detalleretencionislrsp detalle=null;
        List<Detalleretencionislrsp> lista = null;
        double montoret=0;
        try {
            consulta = "From Detalleretencionislrsp d where d.numerofact.numerofact= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, factu);
            lista = query.getResultList();
            if (!lista.isEmpty()) {
                detalle = lista.get(0);
                montoret=detalle.getTotalislrretenido();
            }
        } catch (Exception e) {
            throw e;
        }
        return montoret;
    }
}
