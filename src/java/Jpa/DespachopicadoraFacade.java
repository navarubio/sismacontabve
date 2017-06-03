/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Despachopicadora;
import Modelo.Detallenotacarga;
import Modelo.Notacarga;
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
public class DespachopicadoraFacade extends AbstractFacade<Despachopicadora> implements  DespachopicadoraFacadeLocal{
    @PersistenceContext(unitName = "InpecaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DespachopicadoraFacade() {
        super(Despachopicadora.class);
    }
    
    @Override
    public List<Despachopicadora> despachosfiltrados(Notacarga nota) {
        String consulta;
        List<Despachopicadora> lista = null;
        try {
            consulta = "From Despachopicadora d where d.idnotacarga.idnotacarga = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, nota.getIdnotacarga());
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
}
