/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Tipoconjunto;
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
public class TipoconjuntoFacade extends AbstractFacade<Tipoconjunto> implements TipoconjuntoFacadeLocal{
    @PersistenceContext(unitName = "InpecaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoconjuntoFacade() {
        super(Tipoconjunto.class);
    }
    
    @Override
    public Tipoconjunto cambiartipoConjunto(int tipo) {
        String consulta;
        int idtipo=tipo;
        Tipoconjunto estatus = new Tipoconjunto();
        try {
            consulta = "From Tipoconjunto t where t.idtipoconjunto= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, idtipo);
            List<Tipoconjunto> lista = query.getResultList();
            if (!lista.isEmpty()) {
                estatus = lista.get(0);
            }

        } catch (Exception e) {
            throw e;
        }
        return estatus;
    }
    
}
