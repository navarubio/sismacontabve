/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Maquinariapicadora;
import Modelo.Produccionpicadora;
import Modelo.Usomaquinariapicadora;
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
public class UsomaquinariapicadoraFacade extends AbstractFacade<Usomaquinariapicadora> implements UsomaquinariapicadoraFacadeLocal{
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsomaquinariapicadoraFacade() {
        super(Usomaquinariapicadora.class);
    }
    
@Override
    public List<Usomaquinariapicadora> usomaquinafechadesc() {
        String consulta = null;
        List<Usomaquinariapicadora> lista = null;
        try {
            consulta = "Select p From Usomaquinariapicadora p Order By p.fecha Desc";
            Query query = em.createQuery(consulta);
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
}
