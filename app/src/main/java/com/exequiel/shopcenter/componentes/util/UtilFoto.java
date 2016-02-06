package com.exequiel.shopcenter.componentes.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by exequiel on 18/08/2015.
 */
public class UtilFoto {

    /**
     * Invocar el método desde una activity
     * que implemente onResult()
     * @param activity
     */
    public static String tomarFotografia(Activity activity) {
        String ruta_foto_actual = UtilDirectorio.getDirectorioImagenes(activity) + getCode()
                + ".jpg";
        File mi_foto = new File(ruta_foto_actual);
        try {
            mi_foto.createNewFile();
        } catch (IOException ex) {
            Log.e("ERROR ", "Error:" + ex);
        }
        //
        Uri uri = Uri.fromFile(mi_foto);
        // Abre la camara para tomar la foto
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Guarda imagen
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        // Retorna a la actividad
        activity.startActivityForResult(cameraIntent, UtilConstForResult.REQUEST_CODE_FOTO);
        return ruta_foto_actual;
    }

    public static Uri tomarFotografia(Fragment fragment) {

        String ruta_foto_actual = UtilDirectorio.getDirectorioImagenes(fragment.getActivity()) + getCode()
                + ".jpg";

        File mi_foto = new File(UtilDirectorio.getDirectorioImagenes(fragment.getActivity()),getCode()+".jpg");
        /*try {
            mi_foto.createNewFile();
        } catch (IOException ex) {
            Log.e("ERROR ", "Error:" + ex);
        }*/
        //
        Uri uri = Uri.fromFile(mi_foto);
        // Abre la camara para tomar la foto
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Guarda imagen
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        // Retorna a la actividad
        try {
            cameraIntent.putExtra("return-data", true);
            fragment.startActivityForResult(cameraIntent, UtilConstForResult.REQUEST_CODE_FOTO);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
        return uri;
    }

    /**
     * Metodo privado que genera un codigo unico segun la hora y fecha del
     * sistema
     *
     * @return photoCode
     * */
    @SuppressLint("SimpleDateFormat")
    private static String getCode() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFormat.format(new Date());
        String photoCode = "pic_" + date;
        return photoCode;
    }
}
