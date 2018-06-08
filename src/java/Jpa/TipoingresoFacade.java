/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Cajachica;
import Modelo.Empresa;
import Modelo.Tipoingreso;
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
public class TipoingresoFacade extends AbstractFacade<Tipoingreso> implements TipoingresoFacadeLocal{
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoingresoFacade() {
        super(Tipoingreso.class);
    }
    
    @Override
    public List<Tipoingreso> tipoingresoAll(Empresa empre) {
        String consulta;
        List<Tipoingreso> lista = null;
        try {
            consulta = "SELECT t FROM Tipoingreso t where t.idempresa = ?1 order by t.idtipoingreso";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
}
