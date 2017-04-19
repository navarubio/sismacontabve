/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Comprobanteislref;
import Modelo.Detalleretencionislref;
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
public class DetalleretencionislrefFacade extends AbstractFacade<Detalleretencionislref> implements DetalleretencionislrefFacadeLocal {
    @PersistenceContext(unitName = "InpecaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleretencionislrefFacade() {
        super(Detalleretencionislref.class);
    }
    
    @Override
    public List<Detalleretencionislref> buscarretencionesporPreveedor(String rif) {
        String consulta;
        String rifprovee = rif;
        List<Detalleretencionislref> lista = null;
        try {
            consulta = "From Detalleretencionislref d where d.idcompra.rifproveedor.rifproveedor= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, rifprovee);
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    @Override
    public List<Detalleretencionislref> buscarretencionesActivas() {
        String consulta;
        List<Detalleretencionislref> lista = null;
        try {
            consulta = "From Detalleretencionislref d where d.idcomprobanteislref= null";
            Query query = em.createQuery(consulta);
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
    @Override
    public double retencionislrporpago(int compra) {
        String consulta;
        Detalleretencionislref detalle=null;
        List<Detalleretencionislref> lista = null;
        double montoret=0;
        try {
            consulta = "From Detalleretencionislref d where d.idcompra.idcompra= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, compra);
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
