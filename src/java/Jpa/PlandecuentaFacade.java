/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jpa;

import Modelo.Detalleconsumocajachica;
import Modelo.Empresa;
import Modelo.Plandecuenta;
import Modelo.Tipocuentacontable;
import java.util.ArrayList;
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
public class PlandecuentaFacade extends AbstractFacade<Plandecuenta> implements PlandecuentaFacadeLocal {

    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;

    private List<Plandecuenta> lista = null;
    private String consulta;
    private Plandecuenta plandecuenta = null;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlandecuentaFacade() {
        super(Plandecuenta.class);
    }

    @Override
    public List<Plandecuenta> itemsordenados(Empresa empre) {
        try {
            consulta = "SELECT p FROM Plandecuenta p where p.idempresa= ?1 order by p.idgrupocontable,p.idsubgrupocontable,p.idespecificocontable,p.idsubespecificocontable,p.idgeneralcuenta";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
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
    public List<Plandecuenta> cuentasdeMovimiento() {
        int empresamodelo = 0;
        try {
            consulta = "SELECT p FROM Plandecuenta p where p.idempresa= ?1 and p.idgeneralcuenta>0 order by p.idgrupocontable,p.idsubgrupocontable,p.idespecificocontable,p.idsubespecificocontable,p.idgeneralcuenta";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empresamodelo);
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
    public Plandecuenta buscarcuenta(int codcta, Empresa empre) {
        String consulta;
        Plandecuenta cuenta = null;
        List<Plandecuenta> lista = null;
        try {
            consulta = "From Plandecuenta p where p.idempresa= ?1 and p.codigocuenta= ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            query.setParameter(2, codcta);

            lista = query.getResultList();
            if (!lista.isEmpty()) {
                cuenta = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return cuenta;
    }

    @Override
    public Plandecuenta buscarcuentaxcodigo(int codcta, Empresa empre) {
        String consulta;
        Plandecuenta cuenta = null;
        List<Plandecuenta> lista = null;
        try {
            consulta = "From Plandecuenta p where p.idempresa= ?1 and p.codigocuenta= ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            query.setParameter(2, codcta);

            lista = query.getResultList();
            if (!lista.isEmpty()) {
                cuenta = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return cuenta;
    }

    @Override
    public double buscarsaldoanterior(int codcta , Empresa empre) {
        String consulta;
        Plandecuenta cuenta = null;
        double sandoant = 0;
        List<Plandecuenta> lista = null;
        try {
            consulta = "From Plandecuenta p where p.idempresa= ?1 and p.codigocuenta= ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            query.setParameter(2, codcta);
            lista = query.getResultList();
            if (!lista.isEmpty()) {
                cuenta = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return cuenta.getSaldogeneral();
    }

    @Override
    public List<Plandecuenta> itemseeff(Empresa empre, int tipocuenta) {
        List<Plandecuenta> listaeeff = new ArrayList<>();
        List<Plandecuenta> listaeeffinal = new ArrayList<>();
        int one, two, tree, four, five = 0;
        int oneant=0;
        int twoant=0;
        int treeant=0;
        int fourant=0;
        int fiveant=0;
        Plandecuenta cuentacontable;
        try {
            if(tipocuenta<3){
                consulta = "SELECT p FROM Plandecuenta p where p.idempresa= ?1 and p.idtipocuentacontable.idtipocuentacontable= ?2 order by p.idgrupocontable,p.idsubgrupocontable,p.idespecificocontable,p.idsubespecificocontable,p.idgeneralcuenta";
            }else{
                consulta = "SELECT p FROM Plandecuenta p where p.idempresa= ?1 and p.idtipocuentacontable.idtipocuentacontable< ?2 order by p.idgrupocontable,p.idsubgrupocontable,p.idespecificocontable,p.idsubespecificocontable,p.idgeneralcuenta";
            }
            Query query = em.createQuery(consulta);
            query.setParameter(1, empre.getIdempresa());
            query.setParameter(2, tipocuenta);
            lista = query.getResultList();
            for (Plandecuenta plan : lista) {
                if (plan.getSaldogeneral() != null) {
                    if (plan.getSaldogeneral() > 0) {
                        listaeeff.add(plan);
                    } else if (plan.getSaldogeneral() < 0) {
                        listaeeff.add(plan);
                    }

                }
            }
            for (Plandecuenta plan1 : listaeeff) {
                String codigog = plan1.getIdgrupocontable() + "0000";
                one = Integer.parseInt(codigog);

                String codigosg = plan1.getIdgrupocontable() + "" + plan1.getIdsubgrupocontable() + "000";
                two = Integer.parseInt(codigosg);

                String codigoesp = plan1.getIdgrupocontable() + "" + plan1.getIdsubgrupocontable() + "" + plan1.getIdespecificocontable() + "00";
                tree = Integer.parseInt(codigoesp);

                String codigosubesp = plan1.getIdgrupocontable() + "" + plan1.getIdsubgrupocontable() + "" + plan1.getIdespecificocontable() + "" + plan1.getIdsubespecificocontable() + "0";
                four = Integer.parseInt(codigosubesp);
                
                String codigogral = plan1.getIdgrupocontable() + "" + plan1.getIdsubgrupocontable() + "" + plan1.getIdespecificocontable() + "" + plan1.getIdsubespecificocontable() + ""+plan1.getIdgeneralcuenta();
                five = Integer.parseInt(codigogral);
                

                for (Plandecuenta general : lista) {

                    String codigogrupo = general.getIdgrupocontable() + "0000";
                    int uno = Integer.parseInt(codigogrupo);

                    String codigosubgrupo = general.getIdgrupocontable() + "" + general.getIdsubgrupocontable() + "000";
                    int dos = Integer.parseInt(codigosubgrupo);

                    String codigoespecifico = general.getIdgrupocontable() + "" + general.getIdsubgrupocontable() + "" + general.getIdespecificocontable() + "00";
                    int tres = Integer.parseInt(codigoespecifico);

                    String codigosubespecifico = general.getIdgrupocontable() + "" + general.getIdsubgrupocontable() + "" + general.getIdespecificocontable() + "" + general.getIdsubespecificocontable() + "0";
                    int cuatro = Integer.parseInt(codigosubespecifico);
                    
                    String codigogeneral = general.getIdgrupocontable() + "" + general.getIdsubgrupocontable() + "" + general.getIdespecificocontable() + "" + general.getIdsubespecificocontable() + ""+general.getIdgeneralcuenta();
                    int cinco = Integer.parseInt(codigogeneral);

                    if (uno == one && uno == dos ) {
                        if (oneant!=one){
                            listaeeffinal.add(general);
                        }
                    }
                    if (uno == one && dos == two && dos == tres) {
                        if (twoant!=two ){
                            listaeeffinal.add(general);
                        }
                    }
                    if (uno == one && dos == two && tres == tree && tres==cuatro) {
                        if (treeant!=tree){
                            listaeeffinal.add(general);
                        }
                    }
                    if (uno == one && dos == two && tres == tree && cuatro==four && cuatro==cinco) {
                        if (fourant!=four){
                            listaeeffinal.add(general);
                        }
                    }
                    if (uno == one && dos == two && tres == tree && cuatro==four && cinco==five) {
                        if (fiveant!=five){
                            listaeeffinal.add(general);
                        }
                    }                    

                }
                oneant=one;
                twoant=two;
                treeant=tree;
                fourant=four;
                fiveant=five;
            }

        } catch (Exception e) {
            throw e;
        }
        return listaeeffinal;
    }

}
