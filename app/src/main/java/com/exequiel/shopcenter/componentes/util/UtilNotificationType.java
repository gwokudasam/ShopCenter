package com.exequiel.shopcenter.componentes.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;


public class UtilNotificationType
{	
	private static UtilNotificationType instancia = null;
	
	public static UtilNotificationType getInstance(){
		if (instancia == null) {
			instancia = new UtilNotificationType();
		}
		return instancia;
	}

	public static void notificarAndroidSimple(String titulo, String cuerpo, Context context)
	{
		notificarAndroidSimple(titulo, cuerpo, context, null);
	}
	
	private static NotificationCompat.BigTextStyle getBuilderNotificationBigText(String titulo, String cuerpo, Context context, PendingIntent pendingIntent){
		return new NotificationCompat.BigTextStyle(getBuilderNotificationSimple(titulo, cuerpo, context, pendingIntent))
        .bigText(cuerpo)
        .setBigContentTitle(titulo);
	}
	
	private static NotificationCompat.Builder getBuilderNotificationSimple(String titulo, String cuerpo, Context context, PendingIntent pendingIntent){
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
        .setContentTitle(titulo)
        .setContentText(cuerpo)
        .setTicker(titulo+" : "+cuerpo)
        .setDefaults(Notification.DEFAULT_SOUND)
        .setVibrate(new long[] {1000,1000})
        .setAutoCancel(true)
        //.setSmallIcon(R.drawable.icon)
        .setContentIntent(pendingIntent);
		return builder;
	}
	
	public static void notificarAndroidSimple(String titulo, String cuerpo, Context context, PendingIntent pendingIntent)
	{
		NotificationManager notificationManager;
		Notification miNotification = getBuilderNotificationSimple(titulo, cuerpo, context, pendingIntent).build();
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, miNotification);
	}
	
	public static void notificarAndroidBigText(String titulo, String cuerpo, Context context)
	{
		notificarAndroidBigText(titulo, cuerpo, context, null);
	}
	
	public static void notificarAndroidBigText(String titulo, String cuerpo, Context context, PendingIntent pendingIntent)
	{
		NotificationManager notificationManager;
		Notification miNotification = getBuilderNotificationBigText(titulo, cuerpo, context, pendingIntent).build();
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, miNotification);
	}

	public static void notificarSimple(String mensaje, Context context)
	{
		Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
	}

	public static void notificarVibrador(String mensaje, Context context)
	{
		Vibrator vibretor = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		vibretor.vibrate(1000);
		notificarSimple(mensaje, context);
	}

	public static void notificarSonido(Context context)
	{
		Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		Ringtone r = RingtoneManager.getRingtone(context.getApplicationContext(), notification);
		r.play();
	}

}
