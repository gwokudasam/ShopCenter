package com.exequiel.shopcenter.componentes.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Environment;

import java.io.File;

/**
 * Created by exequiel on 20/07/2015.
 */
public class UtilDirectorio {

    public static File getDirectorioImagenes(Context context){
        ContextWrapper cw = new ContextWrapper(context);
        //File dirImages = cw.getDir("Imagenes", Context.);
        File dirImages = Environment.getExternalStorageDirectory();
        return dirImages;
    }


}
