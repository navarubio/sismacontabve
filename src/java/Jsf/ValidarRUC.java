/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jsf;

/**
 * VALIDAR RUC SOCIEDAD PRIVADAS Y PERSONAS NATURALES NO RESIDENTES
 * @author sofimar
 */
public class ValidarRUC {

    private static final int num_provincias = 24;
    //public static String rucPrueba = "1790011674001";
    private static int[] coeficientes = {4, 3, 2, 7, 6, 5, 4, 3, 2};
    private static int constante = 11;

    
    public Boolean validacionRUC(String ruc) {
        //verifica que los dos primeros dígitos correspondan a un valor entre 1 y NUMERO_DE_PROVINCIAS
        int prov = Integer.parseInt(ruc.substring(0, 2));

        if (!((prov > 0) && (prov <= num_provincias))) {
            System.out.println("Error: ruc ingresada mal");
            return false;
        }

        //verifica que el último dígito de la cédula sea válido
        int[] d = new int[10];
        int suma = 0;

        //Asignamos el string a un array
        for (int i = 0; i < d.length; i++) {
            d[i] = Integer.parseInt(ruc.charAt(i) + "");
        }

        for (int i = 0; i < d.length - 1; i++) {
            d[i] = d[i] * coeficientes[i];
            suma += d[i];
            //System.out.println("Vector d en " + i + " es " + d[i]);
        }

        System.out.println("Suma es: " + suma);

        int aux, resp;

        aux = suma % constante;
        resp = constante - aux;

        resp = (resp == 10) ? 0 : resp;

        System.out.println("Aux: " + aux);
        System.out.println("Resp " + resp);
        System.out.println("d[9] " + d[9]);

        if (resp == d[9]) {
            return true;
        } else {
            return false;
        }
    }
}
