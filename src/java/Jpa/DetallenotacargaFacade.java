/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Detallenotacarga;
import Modelo.Notacarga;
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
public class DetallenotacargaFacade extends AbstractFacade<Detallenotacarga> implements DetallenotacargaFacadeLocal{
    @PersistenceContext(unitName = "InpecaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetallenotacargaFacade() {
        super(Detallenotacarga.class);
    }
    
    @Override
    public List<Detallenotacarga> detallesfiltrados(Notacarga nota) {
        String consulta;
        List<Detallenotacarga> lista = null;
        try {
            consulta = "From Detallenotacarga d where d.idnotacarga.idnotacarga = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, nota.getIdnotacarga());
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
}
