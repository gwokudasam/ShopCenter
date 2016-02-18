package com.exequiel.shopcenter.framework.exceptions;

/**
 * Created by exequiel on 01/06/2015.
 */
public class BaseRequestException extends Exception {

    public static final String ERROR_DEFAULT="Error en el Base request";
    public static final String ERROR_SIN_TIPO_REQUEST="Error el request fue creado sin Tipo de Request";

    public BaseRequestException(){
        super(ERROR_DEFAULT);
    }

    public BaseRequestException(String msj){
        super(msj);
    }
}
