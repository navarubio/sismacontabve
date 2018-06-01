/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Banco;
import Modelo.Cuentabancaria;
import Modelo.Empresa;
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
public class CuentabancariaFacade extends AbstractFacade<Cuentabancaria> implements CuentabancariaFacadeLocal{

    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;
    private List<Cuentabancaria> lista = null;
    private String consulta;
    private Cuentabancaria cuentabancaria=null;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentabancariaFacade() {
        super(Cuentabancaria.class);
    }
    
    @Override
    public List<Cuentabancaria> espxBanco(int idbank, Empresa empre) {
        try { 
            consulta = "From Cuentabancaria c where c.idbanco.idbanco= ?1 and c.idempresa.idempresa= ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, idbank);
            query.setParameter(2, empre.getIdempresa());
            
            lista = query.getResultList();
//            if (!lista.isEmpty()) {
//                usuario = lista.get(0);
//            }
        } catch (Exception e) {
            throw e;
}
        return lista;
    }
    @Override
    public double saldoencuenta(List <Cuentabancaria> lista){
        double saldo=0;
        if (!lista.isEmpty()) {
            saldo = lista.get(0).getSaldo();
        }
        return saldo;
    }
    
    @Override
    public List<Cuentabancaria> cuentasAll(Empresa empre) {
        try { 
            consulta = "From Cuentabancaria c where c.idempresa.idempresa = ?1 order by c.idbanco";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
//    @Override
//    public List<Banco> bancosEmpresa (Empresa empre) {
//        List<Banco> listabancos=null;
//        try { 
//            consulta = "From Cuentabancaria c where c.idempresa.idempresa = ?1 order by c.idbanco";
//            Query query = em.createQuery(consulta);
//            query.setParameter(1, empre.getIdempresa());
//            
//            lista = query.getResultList();
//            
//            for (Cuentabancaria cuenta:lista){
//                
//            }
//            
//        } catch (Exception e) {
//            throw e;
//        }
//        return listabancos;
    //}
    
    
    
}
