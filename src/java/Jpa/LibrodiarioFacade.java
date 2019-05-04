/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Empresa;
import Modelo.Librodiario;
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
public class LibrodiarioFacade extends AbstractFacade<Librodiario> implements LibrodiarioFacadeLocal{
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LibrodiarioFacade() {
        super(Librodiario.class);
    }
    
    @Override
    public Librodiario ultimoInsertado(Empresa empre) {
        String consulta = null;
        Librodiario ultimo = new Librodiario();
        try {
            //consulta = "Select l From Librodiario l Order By l.idlibrodiario Desc";
            consulta =  "From Librodiario l where l.idempresa = ?1 Order By l.idlibrodiario Desc";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            List<Librodiario> lista = query.getResultList();
            if (!lista.isEmpty()) {
                ultimo = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return ultimo;
    }
}
