/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Empresa;
import Modelo.Plandecuenta;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author sofimarye
 */
@Stateless
public class PlandecuentaFacade extends AbstractFacade<Plandecuenta> implements PlandecuentaFacadeLocal {

    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    private List<Plandecuenta> lista = null;
    private String consulta;
    private Plandecuenta plandecuenta = null;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlandecuentaFacade() {
        super(Plandecuenta.class);
    }

    @Override
    public List<Plandecuenta> itemsordenados(Empresa empre) {
        try {
            consulta = "SELECT p FROM Plandecuenta p where p.idempresa= ?1 order by p.idgrupocontable,p.idsubgrupocontable,p.idespecificocontable,p.idsubespecificocontable,p.idgeneralcuenta";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            lista = query.getResultList();
//            if (!lista.isEmpty()) {
//                usuario = lista.get(0);
//            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    @Override
    public Plandecuenta buscarcuenta(int codcta) {
        String consulta;
        Plandecuenta cuenta = null;
        List<Plandecuenta> lista = null;
        try {
            consulta = "From Plandecuenta p where p.idplandecuenta= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, codcta);
            lista = query.getResultList();
            if (!lista.isEmpty()) {
                cuenta = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return cuenta;
    }
    @Override
    public double buscarsaldoanterior(int codcta) {
        String consulta;
        Plandecuenta cuenta = null;
        double sandoant=0;
        List<Plandecuenta> lista = null;
        try {
            consulta = "From Plandecuenta p where p.idplandecuenta= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, codcta);
            lista = query.getResultList();
            if (!lista.isEmpty()) {
                cuenta = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return cuenta.getSaldogeneral();
    }
    
    
}
