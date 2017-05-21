/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Auxiliarrequerimiento;
import Modelo.Produccionpicadora;
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
public class ProduccionpicadoraFacade extends AbstractFacade<Produccionpicadora> implements ProduccionpicadoraFacadeLocal{
    @PersistenceContext(unitName = "InpecaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProduccionpicadoraFacade() {
        super(Produccionpicadora.class);
    }
    
    @Override
    public Produccionpicadora ultimoInsertado() {
        String consulta = null;
        Produccionpicadora ultimo = new Produccionpicadora();
        try {
            consulta = "Select p From Produccionpicadora p Order By p.idproduccionpicadora Desc";
            Query query = em.createQuery(consulta);
            List<Produccionpicadora> lista = query.getResultList();
            if (!lista.isEmpty()) {
                ultimo = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return ultimo;
    }

    
}
