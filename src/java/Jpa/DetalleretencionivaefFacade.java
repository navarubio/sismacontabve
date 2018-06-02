/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Detalleretencionivaef;
import Modelo.Empresa;
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
public class DetalleretencionivaefFacade extends AbstractFacade<Detalleretencionivaef> implements DetalleretencionivaefFacadeLocal {

    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleretencionivaefFacade() {
        super(Detalleretencionivaef.class);
    }

    @Override
    public List<Detalleretencionivaef> buscarretencionesporPreveedor(String rif, Empresa empre) {
        String consulta;
        String rifprovee = rif;
        List<Detalleretencionivaef> lista = null;
        try {
            consulta = "From Detalleretencionivaef d where d.idcompra.iddepartamento.idempresa.idempresa= ?1 and d.idcompra.rifproveedor.rifproveedor= ?2 AND d.idcomprobanteivaef=null";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            query.setParameter(2, rifprovee);
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
    @Override
    public List<Detalleretencionivaef> buscarretencionesActivas(Empresa empre) {
        String consulta;
        List<Detalleretencionivaef> lista = null;
        try {
            consulta = "From Detalleretencionivaef d where d.idcompra.iddepartamento.idempresa.idempresa= ?1 and  d.idcomprobanteivaef= null";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
    @Override
    public double retencionivaporpago(int pago) {
        String consulta;
        Detalleretencionivaef detalle=null;
        List<Detalleretencionivaef> lista = null;
        double montoret=0;
        try {
            consulta = "From Detalleretencionivaef d where d.idpagocompra.idpagocompra= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, pago);
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
