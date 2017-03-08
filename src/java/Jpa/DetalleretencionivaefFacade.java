/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Detalleretencionivaef;
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

    @PersistenceContext(unitName = "InpecaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleretencionivaefFacade() {
        super(Detalleretencionivaef.class);
    }

    @Override
    public List<Detalleretencionivaef> buscarretencionesporPreveedor(String rif) {
        String consulta;
        String rifprovee = rif;
        List<Detalleretencionivaef> lista = null;
        try {
            consulta = "From Detalleretencionivaef d where d.idcompra.rifproveedor.rifproveedor= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, rifprovee);
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
    @Override
    public List<Detalleretencionivaef> buscarretencionesActivas() {
        String consulta;
        List<Detalleretencionivaef> lista = null;
        try {
            consulta = "From Detalleretencionivaef d where d.idcomprobanteivaef= null";
            Query query = em.createQuery(consulta);
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
}
