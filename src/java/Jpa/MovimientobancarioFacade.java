/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Movimientobancario;
import Modelo.Otroingreso;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Inpeca
 */
@Stateless
public class MovimientobancarioFacade extends AbstractFacade<Movimientobancario> implements MovimientobancarioFacadeLocal{
    @PersistenceContext(unitName = "InpecaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovimientobancarioFacade() {
        super(Movimientobancario.class);
    }
    
    @Override
    public List<Movimientobancario> buscarmovimiento (Otroingreso otro) {
        String consulta;
        List<Movimientobancario> lista = null;
        try {
            consulta = "From Movimientobancario m where m.idotroingreso.idotroingreso= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, otro.getIdotroingreso());
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
}
