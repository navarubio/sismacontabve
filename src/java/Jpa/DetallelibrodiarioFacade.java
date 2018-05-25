/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Consumocajachica;
import Modelo.Detallelibrodiario;
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
public class DetallelibrodiarioFacade extends AbstractFacade<Detallelibrodiario> implements DetallelibrodiarioFacadeLocal{
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetallelibrodiarioFacade() {
        super(Detallelibrodiario.class);
    }
    
    @Override
    public List<Detallelibrodiario> detalleslibrodiarioAll() {
        String consulta;
        List<Detallelibrodiario> lista = null;
        try {
            consulta = "SELECT d FROM Detallelibrodiario d order by d.iddetallelibrodiario";
            Query query = em.createQuery(consulta);
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
}
