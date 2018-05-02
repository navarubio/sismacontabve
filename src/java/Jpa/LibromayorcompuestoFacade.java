/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Libromayor;
import Modelo.Libromayorcompuesto;
import Modelo.Plandecuenta;
import java.util.Date;
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
public class LibromayorcompuestoFacade extends AbstractFacade<Libromayorcompuesto> implements LibromayorcompuestoFacadeLocal {
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LibromayorcompuestoFacade() {
        super(Libromayorcompuesto.class);
    }
    
    @Override
    public List<Libromayorcompuesto> buscarmayorporfecha (Integer cuentacontable, Date fechaini, Date fechafinish) {
        String consulta;
        List<Libromayorcompuesto> lista = null;
        try {
            consulta = "From Libromayorcompuesto l where l.idplandecuenta= ?1 and l.fecha between ?2 and ?3 order by l.fecha, l.idlibromayor";
            Query query = em.createQuery(consulta);
            query.setParameter(1, cuentacontable);
            query.setParameter(2, fechaini);
            query.setParameter(3, fechafinish);

            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
}
