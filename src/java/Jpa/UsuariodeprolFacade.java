/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Empresa;
import Modelo.Usuario;
import Modelo.Usuariodeprol;
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
public class UsuariodeprolFacade extends AbstractFacade<Usuariodeprol> implements UsuariodeprolFacadeLocal {
    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariodeprolFacade() {
        super(Usuariodeprol.class);
    }
    
    @Override
    public Usuariodeprol UsuarioDptoRol (Usuario us, Empresa empre) {
        Usuariodeprol usuariodeprol = null;
        String consulta;
        try {
            consulta  = "From Usuariodeprol u where u.idusuario.idusuario = ?1 and u.iddepartamento.idempresa.idempresa = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, us.getIdusuario());
            query.setParameter(2, empre.getIdempresa());

            List<Usuariodeprol> lista = query.getResultList();
            if (!lista.isEmpty()) {
                usuariodeprol = lista.get(0);
}
        } catch (Exception e) {
            throw e;
        }
        return usuariodeprol;
    }
    
}
