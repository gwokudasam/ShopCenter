package com.exequiel.shopcenter.androidframework.exceptions;

/**
 * Created by Administrador on 22/08/2015.
 */
public class ConstructPublicFragmentException extends Exception {

    private static final String MENSAJE_ERROR ="Esta usando el constructor publico, use el metodo" +
            " new instance";

    public ConstructPublicFragmentException(){
        super(MENSAJE_ERROR);
    }
}
