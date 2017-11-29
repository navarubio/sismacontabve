/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Cobroventa;
import Modelo.Retencionivasri;
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
public class RetencionivasriFacade extends AbstractFacade<Retencionivasri> implements RetencionivasriFacadeLocal {
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RetencionivasriFacade() {
        super(Retencionivasri.class);
    }
    
    @Override
    public Retencionivasri buscarcoPorcentajes (int codigoretencion) {
        String consulta;
        List<Retencionivasri> lista = null;
        Retencionivasri retencion=new Retencionivasri();
        if (codigoretencion > 0) {
            try {
                consulta = "From Retencionivasri r where r.idretencionivasri= ?1";
                Query query = em.createQuery(consulta);
                query.setParameter(1, codigoretencion);
                lista = query.getResultList();
                if (!lista.isEmpty()) {
                    retencion = lista.get(0);
                }

            } catch (Exception e) {
                throw e;
            }
        }
        return retencion;
    }
}
