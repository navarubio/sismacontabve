/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Estatusfactura;
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
public class EstatusfacturaFacade extends AbstractFacade<Estatusfactura> implements EstatusfacturaFacadeLocal{

    @PersistenceContext(unitName = "InpecaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstatusfacturaFacade() {
        super(Estatusfactura.class);
    }
    
    @Override
    public Estatusfactura cambiarestatusFactura(int tipo) {
        String consulta;
        int idstatus=tipo;
        Estatusfactura estatus = new Estatusfactura();
        try {
            consulta = "From Estatusfactura e where e.idestatusfactura= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, idstatus);
            List<Estatusfactura> lista = query.getResultList();
            if (!lista.isEmpty()) {
                estatus = lista.get(0);
}

        } catch (Exception e) {
            throw e;
        }
        return estatus;
    }
    @Override
    public Estatusfactura estatusfacturaPagada(int tipo) {
        String consulta;
        int idstatus=tipo;
        Estatusfactura estatus = new Estatusfactura();
        try {
            consulta = "From Estatusfactura e where e.idestatusfactura= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, idstatus);
            List<Estatusfactura> lista = query.getResultList();
            if (!lista.isEmpty()) {
                estatus = lista.get(0);
            }

        } catch (Exception e) {
            throw e;
        }
        return estatus;
    }
    
    @Override
    public List<Estatusfactura> ListarEstatusporPagar() {
        String consulta;
        int idstatus = 0;
        int idstatus2 =2;
        int idstatus3= 4;
        List<Estatusfactura> lista = null;
        try {
            consulta = "From Estatusfactura e where e.idestatusfactura= ?1 or e.idestatusfactura= ?2 or e.idestatusfactura= ?3";
            Query query = em.createQuery(consulta);
            query.setParameter(1, idstatus);
            query.setParameter(2, idstatus2);            
            query.setParameter(3, idstatus3);            
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
}
