/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Articulo;
import Modelo.Inventariopicadora;
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
public class InventariopicadoraFacade extends AbstractFacade<Inventariopicadora> implements InventariopicadoraFacadeLocal{
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InventariopicadoraFacade() {
        super(Inventariopicadora.class);
    }
    
    @Override
    public Inventariopicadora buscarAgregado (Integer codigo) {
        Inventariopicadora inv = null;
        String consulta;
        try {
            consulta = "From Inventariopicadora i where i.codigo.codigo = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, codigo);

            List<Inventariopicadora> lista = query.getResultList();
            if (!lista.isEmpty()) {
                inv = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return inv;
    }
    
}
