/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Compra;
import Modelo.Empresa;
import Modelo.Estatusfactura;
import java.util.Date;
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
public class CompraFacade extends AbstractFacade<Compra> implements CompraFacadeLocal{

    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompraFacade() {
        super(Compra.class);
    }
    
    @Override
    public Compra ultimacompraInsertada() {
        String consulta = null;
        Compra ultimo = new Compra();
        try {
            consulta = "Select c From Compra c Order By c.idcompra Desc";
            Query query = em.createQuery(consulta);
            List<Compra> lista = query.getResultList();
            if (!lista.isEmpty()) {
                ultimo = lista.get(0);
}
        } catch (Exception e) {
            throw e;
        }
        return ultimo;
    }
    @Override
    public List<Compra> buscarcomprasporAutorizar(Empresa empre) {
        String consulta;
        int idstatus = 1;
        List<Compra> lista = null;
        try {
            consulta = "From Compra c where c.iddepartamento.idempresa.idempresa= ?1 and c.idestatusfactura.idestatusfactura= ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            query.setParameter(2, idstatus);
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
    @Override
    public List<Compra> buscarcomprasporPagar(Empresa empre) {
        String consulta;
        int idstatus = 3;
        List<Compra> lista = null;
        try {
            consulta = "From Compra c where c.idauxiliarrequerimiento.iddepartamento.idempresa.idempresa = ?1 and c.idestatusfactura.idestatusfactura < ?2 order by c.serialcompra";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            query.setParameter(2, idstatus);
            
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
    @Override
    public List<Compra> buscarcomprasPagadas(Empresa empre) {
        String consulta;
        int idstatus = 3;
        List<Compra> lista = null;
        try {
            consulta = "From Compra c where c.iddepartamento.idempresa.idempresa= ?1 and c.idestatusfactura.idestatusfactura= ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            query.setParameter(2, idstatus);
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }  
    
    @Override
    public List<Compra> buscarcomprasFiltradas (Estatusfactura status, Date fechaini, Date fechafinish, Empresa empre) {
        String consulta;
        List<Compra> lista = null;
        try {
            consulta = "From Compra c where c.iddepartamento.idempresa.idempresa= ?1 and c.idestatusfactura.idestatusfactura= ?2 and c.fechaorden between ?3 and ?4 order by c.fechaorden";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            query.setParameter(2, status.getIdestatusfactura());
            query.setParameter(3, fechaini);
            query.setParameter(4, fechafinish);

            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
}
