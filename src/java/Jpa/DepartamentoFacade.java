/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Departamento;
import Modelo.Empresa;
import Modelo.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author sofimarye
 */
@Stateless
public class DepartamentoFacade extends AbstractFacade<Departamento> implements DepartamentoFacadeLocal{

    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartamentoFacade() {
        super(Departamento.class);
    }
    
    @Override
    public Departamento buscarDepartamento (Usuario us) {
        Departamento dpto=null;
        String consulta;
        try {
            consulta  = "From Departamento d where d.iddepartamento = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, us.getIddepartamento().getIddepartamento());

            List<Departamento> lista = query.getResultList();
            if (!lista.isEmpty()) {
                dpto = lista.get(0);
}
        } catch (Exception e) {
            throw e;
        }
        return dpto;
    }
    
    @Override
    public List<Departamento> devolverDepartamentos (Empresa empre) {
        Empresa empresa = null;
        String consulta;
        List<Departamento> lista = null;
        try {
            consulta = "From Departamento d where d.idempresa.idempresa = ?1 Order By d.departamento";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
}
        return lista;
    }
}
