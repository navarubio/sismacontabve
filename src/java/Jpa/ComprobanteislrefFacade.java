/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Comprobanteislref;
import java.text.DecimalFormat;
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
public class ComprobanteislrefFacade extends AbstractFacade<Comprobanteislref> implements ComprobanteislrefFacadeLocal {

    @PersistenceContext(unitName = "InpecaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComprobanteislrefFacade() {
        super(Comprobanteislref.class);
    }

    @Override
    public String siguientecomprobanteformat() {
        String consulta = null;
        Comprobanteislref ultimo = new Comprobanteislref();
        int numeracion = 0;
        DecimalFormat myFormatter = new DecimalFormat("00000");
        //formatear la cantidad 
        try {
            consulta = "Select c From Comprobanteislref c Order By c.idcomprobanteislref Desc";
            Query query = em.createQuery(consulta);
            List<Comprobanteislref> lista = query.getResultList();
            if (!lista.isEmpty()) {
                ultimo = lista.get(0);
                numeracion = ultimo.getIdcomprobanteislref() + 1;
            } else {
                numeracion = numeracion + 1;
            }

        } catch (Exception e) {
            throw e;
        }

        String output = myFormatter.format(numeracion);
        return output;
    }
}
