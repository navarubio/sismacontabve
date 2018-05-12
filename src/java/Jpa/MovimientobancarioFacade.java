/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Cobroventa;
import Modelo.Cuentabancaria;
import Modelo.Movimientobancario;
import Modelo.Otroingreso;
import Modelo.Pagocompra;
import Modelo.Plandecuenta;
import Modelo.Reposicioncajachica;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Inpeca
 */
@Stateless
public class MovimientobancarioFacade extends AbstractFacade<Movimientobancario> implements MovimientobancarioFacadeLocal {

    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovimientobancarioFacade() {
        super(Movimientobancario.class);
    }

    @Override
    public List<Movimientobancario> buscarmovimiento(Otroingreso otro) {
        String consulta;
        List<Movimientobancario> lista = null;
        try {
            consulta = "From Movimientobancario m where m.idotroingreso.idotroingreso= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, otro.getIdotroingreso());
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    @Override
    public Movimientobancario buscarmovimientoxIdotroingresoCtaContable(Otroingreso otro, Plandecuenta cuentacontab) {
        String consulta;
        Movimientobancario movimientobanc = null;
        List<Movimientobancario> lista = null;
        try {
            consulta = "From Movimientobancario m where m.idotroingreso.idotroingreso= ?1 and m.idcuentabancaria.idplandecuenta.idplandecuenta= ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, otro.getIdotroingreso());
            query.setParameter(2, cuentacontab.getIdplandecuenta());
            lista = query.getResultList();
            if (!lista.isEmpty()) {
                movimientobanc = lista.get(0);
            }
        } catch (Exception e) {
            throw e;

        }
        return movimientobanc;
    }

    @Override
    public Movimientobancario buscarmovimientoxIdcobro(Cobroventa cobro) {
        String consulta;
        Movimientobancario movimientobanc = null;
        List<Movimientobancario> lista = null;
        try {
            consulta = "From Movimientobancario m where m.idcobroventa.idcobroventa= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, cobro.getIdcobroventa());
            lista = query.getResultList();
            if (!lista.isEmpty()) {
                movimientobanc = lista.get(0);
            }
        } catch (Exception e) {
            throw e;

        }
        return movimientobanc;
    }

    @Override
    public List<Movimientobancario> buscarmovimiento(Reposicioncajachica reposicion) {
        String consulta;
        List<Movimientobancario> lista = null;
        try {
            consulta = "From Movimientobancario m where m.idreposicioncajachica.idreposicioncajachica= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, reposicion.getIdreposicioncajachica());
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    @Override
    public Movimientobancario buscarmovimientoxreposicion(Reposicioncajachica reposicion) {
        String consulta;
        List<Movimientobancario> lista = null;
        Movimientobancario movimientobanc = null;
        try {
            consulta = "From Movimientobancario m where m.idreposicioncajachica.idreposicioncajachica= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, reposicion.getIdreposicioncajachica());
            lista = query.getResultList();
            if (!lista.isEmpty()) {
                movimientobanc = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return movimientobanc;
    }

    @Override
    public List<Movimientobancario> buscarmovimientoporfecha(Cuentabancaria cuenta, Date fechaini, Date fechafinish) {
        String consulta;
        List<Movimientobancario> lista = null;
        try {
            consulta = "From Movimientobancario m where m.idcuentabancaria.idcuentabancaria= ?1 and m.fecha between ?2 and ?3 order by m.fecha, m.idmovimiento";
            Query query = em.createQuery(consulta);
            query.setParameter(1, cuenta.getIdcuentabancaria());
            query.setParameter(2, fechaini);
            query.setParameter(3, fechafinish);

            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    @Override
    public Movimientobancario buscarmovimientoxIdpago(Pagocompra pago) {
        String consulta;
        Movimientobancario movimientobanc = null;
        List<Movimientobancario> lista = null;
        try {
            consulta = "From Movimientobancario m where m.idpagocompra.idpagocompra= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, pago.getIdpagocompra());
            lista = query.getResultList();
            if (!lista.isEmpty()) {
                movimientobanc = lista.get(0);
            }
        } catch (Exception e) {
            throw e;

        }
        return movimientobanc;
    }

    @Override
    public List<Movimientobancario> buscarmovimientosConciliacion(Cuentabancaria cuenta, Date fechafinish) {
        String consulta;
        List<Movimientobancario> lista = null;
        Boolean condicion = false;
        double libromayor = 0;
        try {
            consulta = "From Movimientobancario m where m.idcuentabancaria.idcuentabancaria= ?1 and m.fecha <= ?2 and m.conciliado = ?3 and  m.idlibromayor.idlibromayor > ?4 order by m.fecha, m.idmovimiento";
            Query query = em.createQuery(consulta);
            query.setParameter(1, cuenta.getIdcuentabancaria());
            query.setParameter(2, fechafinish);
            query.setParameter(3, condicion);
            query.setParameter(4, libromayor);
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
}
