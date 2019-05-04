/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Empresa;
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
public class EmpresaFacade extends AbstractFacade<Empresa> implements EmpresaFacadeLocal{
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpresaFacade() {
        super(Empresa.class);
    }
    
    @Override
    public Empresa devolverEmpresabase () {
        Empresa empresa = null;
        String consulta;
        try {
            consulta = "SELECT e FROM Empresa e";
            Query query = em.createQuery(consulta);
            List<Empresa> lista = query.getResultList();
            if (!lista.isEmpty()) {
                empresa = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return empresa;
    }
    
    @Override
    public int devolverSerialConsumo (Empresa empre) {
        Empresa empresa=null;
        int serialconsumo = 0;
        String consulta;
        try {
            consulta = "From Empresa e where e.idempresa = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            List<Empresa> lista = query.getResultList();
            if (!lista.isEmpty()) {
                empresa = lista.get(0);
                serialconsumo=empre.getSerialconsumo()+1;
            }
        } catch (Exception e) {
            throw e;
        }
        return serialconsumo;
    }
    
   @Override
    public int devolverSerialAsiento (Empresa empre) {
        Empresa empresa=null;
        int serialasiento = 0;
        String consulta;
        try {
            consulta = "From Empresa e where e.idempresa = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            List<Empresa> lista = query.getResultList();
            if (!lista.isEmpty()) {
                empresa = lista.get(0);
                serialasiento=empre.getSerialasiento()+1;
            }
        } catch (Exception e) {
            throw e;
        }
        return serialasiento;
    }
}
