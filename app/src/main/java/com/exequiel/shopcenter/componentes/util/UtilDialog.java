package com.exequiel.shopcenter.componentes.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by exequiel on 18/08/2015.
 */
public class UtilDialog {

    public static void showDialog(Context context,String title,String[] items, DialogInterface.OnClickListener click){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setSingleChoiceItems(items,0,click);
        builder.create();
        builder.show();
    }
}
