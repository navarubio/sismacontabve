/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Detalleretencionivasp;
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
public class DetalleretencionivaspFacade extends AbstractFacade<Detalleretencionivasp> implements DetalleretencionivaspFacadeLocal{
    @PersistenceContext(unitName = "InpecaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleretencionivaspFacade() {
        super(Detalleretencionivasp.class);
    }
    
    @Override
    public double retencionivaencobro(int factu) {
        String consulta;
        Detalleretencionivasp detalle=null;
        List<Detalleretencionivasp> lista = null;
        double montoret=0;
        try {
            consulta = "From Detalleretencionivasp d where d.numerofact.numerofact= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, factu);
            lista = query.getResultList();
            if (!lista.isEmpty()) {
                detalle = lista.get(0);
                montoret=detalle.getTotalivaretenido();
            }
        } catch (Exception e) {
            throw e;
        }
        return montoret;
    }
    
}
