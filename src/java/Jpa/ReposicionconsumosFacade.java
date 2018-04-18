/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Cajachica;
import Modelo.Consumocajachica;
import Modelo.Reposicioncajachica;
import Modelo.Reposicionconsumos;
import java.util.ArrayList;
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
public class ReposicionconsumosFacade extends AbstractFacade<Reposicionconsumos> implements ReposicionconsumosFacadeLocal{
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReposicionconsumosFacade() {
        super(Reposicionconsumos.class);
    }
    
    @Override
    public Cajachica devolverCajachica (Reposicioncajachica reposicion) {
        String consulta;
        Consumocajachica consumo=null;
        Reposicionconsumos repoconsumo;
        Cajachica caja=null;
        List<Reposicionconsumos> lista = null;
        try {
            consulta = "From Reposicionconsumos r where r.idreposicioncajachica.idreposicioncajachica= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, reposicion.getIdreposicioncajachica());
            lista = query.getResultList();
            if (!lista.isEmpty()) {
                repoconsumo = lista.get(0);
                consumo=repoconsumo.getIdconsumocajachica();
                caja=consumo.getIdcajachica();
            }
        } catch (Exception e) {
            throw e;
        }
        return caja;
    }
    
    @Override
    public List <Reposicionconsumos> listaReposicionconsumosfiltrada (Reposicioncajachica reposicion) {
        String consulta;
        List<Reposicionconsumos> lista = null;
        try {
            consulta = "From Reposicionconsumos r where r.idreposicioncajachica.idreposicioncajachica= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, reposicion.getIdreposicioncajachica());
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
    @Override
    public List <Consumocajachica> listaconsumosxReposicion (Reposicioncajachica reposicion) {
        String consulta;
        List<Reposicionconsumos> lista = null;
        List<Consumocajachica> listaconsumos = new ArrayList<>();
//        List<Consumocajachica> listaconsumos=null;
        try {
            consulta = "From Reposicionconsumos r where r.idreposicioncajachica.idreposicioncajachica= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, reposicion.getIdreposicioncajachica());
            lista = query.getResultList();
            for (Reposicionconsumos rc : lista) {
                listaconsumos.add(rc.getIdconsumocajachica());
            }
        } catch (Exception e) {
            throw e;
        }
        return listaconsumos;
    }

}
