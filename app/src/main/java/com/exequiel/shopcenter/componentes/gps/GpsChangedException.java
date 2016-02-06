package com.exequiel.shopcenter.componentes.gps;

/**
 * Created by exequiel on 15/09/2015.
 */
public class GpsChangedException extends Exception {

    public GpsChangedException(String mensaje){
        super(mensaje);
    }

    public GpsChangedException(Exception ex){
        super(ex);
    }

}