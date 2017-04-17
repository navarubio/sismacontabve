/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Libromayor;
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
public class LibromayorFacade extends AbstractFacade<Libromayor> implements LibromayorFacadeLocal{
    @PersistenceContext(unitName = "InpecaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LibromayorFacade() {
        super(Libromayor.class);
    }
    
    @Override
    public List<Libromayor> listacuentaespecifica(int codcta) {
        String consulta;
        Libromayor cuenta = null;
        List<Libromayor> lista = null;
        try {
            consulta = "From Libromayor l where l.idplandecuenta.idplandecuenta= ?1 order by l.idlibromayor";
            Query query = em.createQuery(consulta);
            query.setParameter(1, codcta);
            lista = query.getResultList();
            if (!lista.isEmpty()) {
                cuenta = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
}
