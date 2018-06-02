/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Empresa;
import Modelo.Otroingreso;
import Modelo.Reposicioncajachica;
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
public class ReposicioncajachicaFacade extends AbstractFacade<Reposicioncajachica> implements ReposicioncajachicaFacadeLocal{
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReposicioncajachicaFacade() {
        super(Reposicioncajachica.class);
    }
    
    @Override
    public List<Reposicioncajachica> reposicionesAll(Empresa empre) {
        String consulta;
        List<Reposicioncajachica> lista = null;
        try {
            consulta = "From Reposicioncajachica r where r.idcuentabancaria.idempresa.idempresa = ?1 order by r.idreposicioncajachica";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
}
