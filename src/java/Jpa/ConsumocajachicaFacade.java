/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Consumocajachica;
import Modelo.Cuentabancaria;
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
public class ConsumocajachicaFacade extends AbstractFacade<Consumocajachica> implements ConsumocajachicaFacadeLocal {

    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;
    private String consulta;
    private List<Consumocajachica> lista = null;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConsumocajachicaFacade() {
        super(Consumocajachica.class);
    }

    @Override
    public List<Consumocajachica> consumosxCaja(int idcaja) {
        int idstatus2 =1;
        try {
            consulta = "From Consumocajachica c where c.idcajachica.idcajachica= ?1 AND c.idestatusconsumocajachica.idestatusconsumocajachica= ?2 order by c.idconsumocajachica";
            Query query = em.createQuery(consulta);
            query.setParameter(1, idcaja);
            query.setParameter(2, idstatus2);
            lista = query.getResultList();
//            if (!lista.isEmpty()) {
//                usuario = lista.get(0);
//            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

}
