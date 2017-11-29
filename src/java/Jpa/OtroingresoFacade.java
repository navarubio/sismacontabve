/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Otroingreso;
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
public class OtroingresoFacade extends AbstractFacade<Otroingreso> implements OtroingresoFacadeLocal{
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OtroingresoFacade() {
        super(Otroingreso.class);
    }
    
    @Override
    public Otroingreso ultimoingreso() {
        String consulta = null;
        Otroingreso ultimo = new Otroingreso();
        int numeracion;
//        DecimalFormat myFormatter = new DecimalFormat("00000000"); 
        //formatear la cantidad 
        try {
            consulta = "Select o From Otroingreso o Order By o.idotroingreso Desc";
            Query query = em.createQuery(consulta);
            List<Otroingreso> lista = query.getResultList();
            if (!lista.isEmpty()) {
                ultimo = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return ultimo;
    }
}
