package com.exequiel.shopcenter.componentes.imagecroop;

import android.app.Activity;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by exequiel on 29/08/2015.
 */
public class CropImage implements DialogInterface.OnClickListener , DialogInterface.OnCancelListener {

    private Activity activity;
    private Fragment fragment;
    private ArrayList<CropOption> cropOptions;
    private Uri imageCaptureUri;

    public static final int CROP_FROM_CAMERA = 99;

    /**
     * Clase que nos va a permitir recortar
     * la imagen de perfil con la galeria
     * de android
     * @param activity
     * @param fragment
     */
    public CropImage(Activity activity,Fragment fragment){
       this.activity = activity;
        this.fragment = fragment;
    }

    public void doCrop(Uri imageCaptureUri) {
        this.imageCaptureUri = imageCaptureUri;
        cropOptions = new ArrayList<CropOption>();
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image/*");
        List<ResolveInfo> list = activity.getPackageManager().queryIntentActivities( intent, 0 );
        int size = list.size();
        if (size == 0) {
            Toast.makeText(activity, "Can not find image crop app", Toast.LENGTH_SHORT).show();

            return;
        } else {
            intent.setData(imageCaptureUri);
            intent.putExtra("outputX", 200);
            intent.putExtra("outputY", 200);
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("scale", true);
            intent.putExtra("return-data", true);

            if (size == 1) {
                Intent i 		= new Intent(intent);
                ResolveInfo res	= list.get(0);
                i.setComponent( new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
                fragment.startActivityForResult(i, CROP_FROM_CAMERA);
            } else {
                for (ResolveInfo res : list) {
                    final CropOption co = new CropOption();
                    co.title 	= activity.getPackageManager().getApplicationLabel(res.activityInfo.applicationInfo);
                    co.icon		= activity.getPackageManager().getApplicationIcon(res.activityInfo.applicationInfo);
                    co.appIntent= new Intent(intent);
                    co.appIntent.setComponent( new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
                    cropOptions.add(co);
                }

                CropOptionAdapter adapter = new CropOptionAdapter(activity.getApplicationContext(), cropOptions);

                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Choose Crop App");
                builder.setAdapter( adapter, this);

                builder.setOnCancelListener(this);
                AlertDialog alert = builder.create();
                alert.show();
            }
        }
    }

    @Override
    public void onCancel( DialogInterface dialog ) {
        if (imageCaptureUri != null ) {
            imageCaptureUri = null;
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int item) {
        fragment.startActivityForResult(cropOptions.get(item).appIntent, CROP_FROM_CAMERA);
    }
}
