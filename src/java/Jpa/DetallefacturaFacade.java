/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Detallefactura;
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
public class DetallefacturaFacade extends AbstractFacade<Detallefactura> implements DetallefacturaFacadeLocal {

    @PersistenceContext(unitName = "InpecaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetallefacturaFacade() {
        super(Detallefactura.class);
    }

    @Override
    public List<Detallefactura> buscardetallefactura(Factura factu) {
        String consulta;
        List<Detallefactura> lista = null;
        try {
            consulta = "From Detallefactura d where d.numerofact.numerofact=?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, factu.getNumerofact());
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

}
