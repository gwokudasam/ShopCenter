package com.exequiel.shopcenter.componentes.util;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrador on 25/08/2015.
 */
public class UtilRowRes {

    public static String readFile(Context context, int idResource){
        String text="";
        try
        {
            InputStream fraw =
                    context.getResources().openRawResource(idResource);

            BufferedReader brin =
                    new BufferedReader(new InputStreamReader(fraw));
            while (brin.ready()){
                text += brin.readLine();
            }
            fraw.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde recurso raw");
        }
        return text;
    }

}
