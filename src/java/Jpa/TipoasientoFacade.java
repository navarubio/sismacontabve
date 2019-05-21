/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Estatuscontable;
import Modelo.Medida;
import Modelo.Tipoasiento;
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
public class TipoasientoFacade extends AbstractFacade<Tipoasiento> implements TipoasientoFacadeLocal{
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoasientoFacade() {
        super(Tipoasiento.class);
    }
    
    @Override
    public Tipoasiento TipoAsientoNormal () {
        Tipoasiento asientoNormal = null;
        int paramet = 1;
        String consulta;
        try {
            consulta  = "From Tipoasiento t where t.idtipoasiento = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, paramet);

            List<Tipoasiento> lista = query.getResultList();
            if (!lista.isEmpty()) {
                 asientoNormal= lista.get(0);
}
        } catch (Exception e) {
            throw e;
        }
        return asientoNormal;
    }
    
}
