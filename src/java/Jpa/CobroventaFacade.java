/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Cobroventa;
import Modelo.Factura;
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
public class CobroventaFacade extends AbstractFacade<Cobroventa> implements CobroventaFacadeLocal {

    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CobroventaFacade() {
        super(Cobroventa.class);
    }

    @Override
    public List<Cobroventa> buscarcobrosporfactura(int factu) {
        String consulta;
        List<Cobroventa> lista = null;
        if (factu > 0) {
            try {
                consulta = "From Cobroventa c where c.numerofact.numerofact = ?1";
                Query query = em.createQuery(consulta);
                query.setParameter(1, factu);
                lista = query.getResultList();
            } catch (Exception e) {
                throw e;
            }
        }
        return lista;
    }
@Override
    public Cobroventa ultimocobroInsertado() {
        String consulta = null;
        Cobroventa ultimo = new Cobroventa();
        try {
            consulta = "Select c From Cobroventa c Order By c.idcobroventa Desc";
            Query query = em.createQuery(consulta);
            List<Cobroventa> lista = query.getResultList();
            if (!lista.isEmpty()) {
                ultimo = lista.get(0);
}
        } catch (Exception e) {
            throw e;
        }
        return ultimo;
    }
}
