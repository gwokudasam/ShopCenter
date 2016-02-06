package com.exequiel.shopcenter.componentes.util;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

import com.exequiel.shopcenter.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by exequiel on 21/08/2015.
 */
public class UtilShare {

    private static Uri getUriFromRute(String ruta){
        String imagePath = ruta;
        File imageFileToShare = new File(imagePath);
        Uri uri = Uri.fromFile(imageFileToShare);
        return uri;
    }

    public static void callPhone(String numberPhone, Context context){


        try{
            Intent intent = new Intent(Intent.ACTION_CALL);
            String uri = "tel:"+numberPhone;
            intent.setData(Uri.parse(uri));
            context.startActivity(intent);
        }catch (ActivityNotFoundException ex){
            Toast.makeText(context, "U kunt niet bellen op een tablet te maken", Toast.LENGTH_LONG).show();
        }
    }


    public static void shareFacebook(View v, Uri uri){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, (String) v.getTag(R.string.app_name));
        //shareIntent.putExtra(Intent.EXTRA_TEXT, (String) v.getTag(R.drawable.ic_launcher));
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);

        PackageManager pm = v.getContext().getPackageManager();
        List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
        for (final ResolveInfo app : activityList)
        {
            if ((app.activityInfo.name).contains("facebook"))
            {
                final ActivityInfo activity = app.activityInfo;
                final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
                shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                shareIntent.setComponent(name);
                v.getContext().startActivity(shareIntent);
                break;
            }
        }
    }

    public static void shareWhatsApp(View v, Uri uri){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/html");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, (String) v.getTag(R.string.app_name));
        shareIntent.putExtra(Intent.EXTRA_TEXT, (String) v.getTag(R.drawable.ic_launcher));
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);

        PackageManager pm = v.getContext().getPackageManager();
        List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
        for (final ResolveInfo app : activityList) {
            if ((app.activityInfo.name).contains("com.whatsapp")) {
                final ActivityInfo activity = app.activityInfo;
                final ComponentName name = new ComponentName(
                        activity.applicationInfo.packageName, activity.name);
                shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                shareIntent.setComponent(name);
                v.getContext().startActivity(shareIntent);
                break;
            }
        }
    }

    public static void shareTwitter(View v, Uri uri){
        Intent shareIntent =  new Intent( Intent. ACTION_SEND );
        shareIntent . setType ( "text/plain" );
        shareIntent . putExtra ( Intent. EXTRA_SUBJECT ,  (String) v . getTag ( R. string . app_name ));
        shareIntent . putExtra ( Intent. EXTRA_TEXT ,  (String) v . getTag ( R. drawable . ic_launcher ));
        shareIntent . putExtra ( Intent. EXTRA_STREAM , uri );

        PackageManager pm = v . getContext (). getPackageManager ();
        List< ResolveInfo > activityList = pm . queryIntentActivities ( shareIntent ,  0 );
        for  ( final ResolveInfo app : activityList )
        {
            if  ( "com.twitter.android.PostActivity" . equals ( app . activityInfo . name ))
            {
                final ActivityInfo activity = app . activityInfo ;
                final ComponentName name =  new ComponentName( activity . applicationInfo . packageName , activity . name );
                shareIntent . addCategory ( Intent. CATEGORY_LAUNCHER );
                shareIntent . setFlags ( Intent. FLAG_ACTIVITY_NEW_TASK |  Intent. FLAG_ACTIVITY_RESET_TASK_IF_NEEDED );
                shareIntent . setComponent ( name );
                v . getContext (). startActivity ( shareIntent );
                break ;
            }
        }
    }

    public static void shareMailConImagenAdjunta(String asunto, String msj, String ruta,Fragment fragment,String[] to){
        Resources resources = fragment.getResources();
        Intent emailIntent = new Intent();
        emailIntent.setAction(Intent.ACTION_SEND);
        // Native email client doesn't currently support HTML, but it doesn't hurt to try in case they fix it
        emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(msj));
        emailIntent.putExtra(Intent.EXTRA_EMAIL,to);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, asunto);
        emailIntent.putExtra(Intent.EXTRA_STREAM, getUriFromRute(ruta));
        emailIntent.setType("message/rfc822");
        PackageManager pm = fragment.getActivity().getPackageManager();
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("image/*");
        Intent openInChooser = Intent.createChooser(emailIntent, "Selecteer het delen");
        List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
        List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
        for (int i = 0; i < resInfo.size(); i++) {
            // Extract the label, append it, and repackage it in a LabeledIntent
            ResolveInfo ri = resInfo.get(i);
            String packageName = ri.activityInfo.packageName;
            if(packageName.contains("android.email")) {
                emailIntent.setPackage(packageName);
            } else if(packageName.contains("android.gm")) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("image/*");
                if(packageName.contains("android.gm")) { // If Gmail shows up twice, try removing this else-if clause and the reference to "android.gm" above
                    intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(msj));
                    intent.putExtra(Intent.EXTRA_EMAIL, to);
                    intent.putExtra(Intent.EXTRA_SUBJECT, asunto);
                    intent.putExtra(Intent.EXTRA_STREAM, getUriFromRute(ruta));
                    intent.setType("message/rfc822");
                }
                intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));
            }
        }

        // convert intentList to array
        LabeledIntent[] extraIntents = intentList.toArray( new LabeledIntent[ intentList.size() ]);
        openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
        fragment.startActivityForResult(openInChooser,55);
        //startActivity(openInChooser);
    }

    public static void sharedEmailSimple(String asunto,String msgACompartir,Fragment fragment){
        Resources resources = fragment.getResources();
        Intent emailIntent = new Intent();
        emailIntent.setAction(Intent.ACTION_SEND);
        // Native email client doesn't currently support HTML, but it doesn't hurt to try in case they fix it
        emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(asunto));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, msgACompartir);
        emailIntent.setType("message/rfc822");
        PackageManager pm = fragment.getActivity().getPackageManager();
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        Intent openInChooser = Intent.createChooser(emailIntent, "share chooser text");
        List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
        List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
        for (int i = 0; i < resInfo.size(); i++) {
            // Extract the label, append it, and repackage it in a LabeledIntent
            ResolveInfo ri = resInfo.get(i);
            String packageName = ri.activityInfo.packageName;
            if(packageName.contains("android.email")) {
                emailIntent.setPackage(packageName);
                break;
            }
        }
        LabeledIntent[] extraIntents = intentList.toArray( new LabeledIntent[ intentList.size() ]);
        openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
        fragment.startActivityForResult(emailIntent, 0);
    }
}
