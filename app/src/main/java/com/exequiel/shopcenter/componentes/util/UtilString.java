package com.exequiel.shopcenter.componentes.util;

/**
 * Created by exequiel on 10/09/2015.
 */
public class UtilString {

    public static String remplazarElemento(String cadena,int pos,char c){
        StringBuilder aux = new StringBuilder(cadena);
        aux.setCharAt(pos,c);
        return aux.toString();
    }
}
