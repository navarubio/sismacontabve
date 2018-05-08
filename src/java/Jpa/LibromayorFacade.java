/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Articulo;
import Modelo.Librodiario;
import Modelo.Libromayor;
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
public class LibromayorFacade extends AbstractFacade<Libromayor> implements LibromayorFacadeLocal{
    @PersistenceContext(unitName = "SismacontabecPU")
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
    
    @Override
    public Libromayor buscarLibro (Integer id) {
        Libromayor may = null;
        String consulta;
        try {
            consulta = "From Libromayor l where l.idlibromayor = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, id);

            List<Libromayor> lista = query.getResultList();
            if (!lista.isEmpty()) {
                may = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return may;
    }
    
    @Override
    public Libromayor ultimoInsertado() {
        String consulta = null;
        Libromayor ultimo = new Libromayor();
        try {
            consulta = "Select l From Libromayor l Order By l.idlibromayor Desc";
            Query query = em.createQuery(consulta);
            List<Libromayor> lista = query.getResultList();
            if (!lista.isEmpty()) {
                ultimo = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return ultimo;
    }
    
    
}
