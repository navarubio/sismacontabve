/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Consumocajachica;
import Modelo.Detalleconsumocajachica;
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
public class DetalleconsumocajachicaFacade extends AbstractFacade<Detalleconsumocajachica> implements DetalleconsumocajachicaFacadeLocal {

    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleconsumocajachicaFacade() {
        super(Detalleconsumocajachica.class);
    }

    @Override
    public List<Detalleconsumocajachica> listadetalleconsumosxListaConsumos(List<Consumocajachica> listaconsumos) {
        String consulta;
        List<Detalleconsumocajachica> listadetalles = new ArrayList<>();
        List<Detalleconsumocajachica> listadetallescompletos = new ArrayList<>();
//        List<Consumocajachica> listaconsumos=null;
        try {
            for (Consumocajachica consumos : listaconsumos) {
                consulta = "From Detalleconsumocajachica d where d.idconsumocajachica.idconsumocajachica= ?1 order by d.idconsumocajachica.idconsumocajachica";
                Query query = em.createQuery(consulta);
                query.setParameter(1, consumos.getIdconsumocajachica());
                listadetalles = query.getResultList();
                for (Detalleconsumocajachica dc : listadetalles) {
                    listadetallescompletos.add(dc);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return listadetallescompletos;
    }

    @Override
    public List<Detalleconsumocajachica> detallesxConsumo(int idconsumocajachica) {
        String consulta;
        List<Detalleconsumocajachica> lista = null;

        try {
            consulta = "From Detalleconsumocajachica d where d.idconsumocajachica.idconsumocajachica= ?1 order by d.iddetalleconsumocajachica";
            Query query = em.createQuery(consulta);
            query.setParameter(1, idconsumocajachica);
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

}
