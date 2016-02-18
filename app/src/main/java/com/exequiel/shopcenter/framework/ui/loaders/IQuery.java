package com.exequiel.shopcenter.framework.ui.loaders;

import android.content.Context;

/**
 * Created by exequiel on 23/06/2015.
 */
public interface IQuery<D> {

    /**
     * Ejecuta una consulta personalizada a un repositorio
     * de datos dado, es utilizada cuando el RepositoryLoader
     * se encuentra en el proceso de carga
     * @param context
     * Es el contexto necesario para acceder a un repositorio de datos
     * que as� lo necesite por ejemplo.
     * @return
     * El dato que sea necesaria devolver en la ejecuci�n de la consulta
     * un listado, un dato espec�fico etc, lo �nico que tiene que coincidir
     * con el tipo de dato pasado por par�metro en la clase RepositoryLoader
     */
    public abstract D execute(Context context);
}
