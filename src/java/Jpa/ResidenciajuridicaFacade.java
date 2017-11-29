/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Contribuyente;
import Modelo.Residenciajuridica;
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
public class ResidenciajuridicaFacade extends AbstractFacade<Residenciajuridica> implements ResidenciajuridicaFacadeLocal{
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;
    private List<Residenciajuridica> lista = null;
    private String consulta;


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResidenciajuridicaFacade() {
        super(Residenciajuridica.class);
    }
    
    @Override
    public List<Residenciajuridica> residenciaxPersona (int idpersona) {
        try { 
            consulta = "From Residenciajuridica r where r.idpersonalidad.idpersonalidad= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, idpersona);
            
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
