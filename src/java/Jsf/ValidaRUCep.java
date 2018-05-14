/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jsf;

import java.io.Serializable;

/**
 *
 * @author sofimar
 */
public class ValidaRUCep implements Serializable{

    public static final int num_provincias = 24;

    public Boolean validaRucEP(String ruc) {
        int prov = Integer.parseInt(ruc.substring(0, 2));
        boolean val = false;

        if (!((prov > 0) && (prov <= num_provincias))) {
            return val;
        }

        Integer v1, v2, v3, v4, v5, v6, v7, v8, v9;
        Integer sumatoria;
        Integer modulo;
        Integer digito;
        Integer sustraendo;
        int[] d = new int[ruc.length()];

        for (int i = 0; i < d.length; i++) {
            d[i] = Integer.parseInt(ruc.charAt(i) + "");
        }

        v1 = d[0] * 3;
        v2 = d[1] * 2;
        v3 = d[2] * 7;
        v4 = d[3] * 6;
        v5 = d[4] * 5;
        v6 = d[5] * 4;
        v7 = d[6] * 3;
        v8 = d[7] * 2;
        v9 = d[8];

        sumatoria = v1 + v2 + v3 + v4 + v5 + v6 + v7 + v8;
        modulo = sumatoria % 11;
        sustraendo = modulo * 11;
        digito = 11 - (sumatoria - sustraendo);
        System.out.println("Digito RUC       --> " + digito);
        System.out.println("Digito Calculado --> " + v9);

        if (digito == v9) {
            val = true;
        } else {
            val = false;
        }
        return val;
    }

}
