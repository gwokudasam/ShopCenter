package com.exequiel.shopcenter.componentes.util;

import android.content.Context;
import android.os.Build;
import android.os.Vibrator;
import android.provider.Settings.Secure;
import android.view.Display;
import android.view.WindowManager;

import java.util.Locale;

public class UtilDevice
{

	public static String getNameOS(){
		return "Android";
	}

	public static String getDisplayInfo(Context context)
	{
		Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

		StringBuilder dimensionDisplay = new StringBuilder();
		dimensionDisplay.append("Resolucion " + display.getWidth() + "x" + display.getHeight() + ", ");
		dimensionDisplay.append("Densidad " + context.getResources().getDisplayMetrics().densityDpi + " dpi");

		return dimensionDisplay.toString();
	}

	public static int getResolutionWidth(Context context){
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	public static int getResolutionHeight(Context context){
		return context.getResources().getDisplayMetrics().heightPixels;
	}

	public static String getAndroidId(Context context)
	{
		
		String android_id = Secure.getString(context.getContentResolver(),
				Secure.ANDROID_ID);
		return android_id;
		/* Numero IMEI
		TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tManager.getDeviceId();*/		
	}

	public static String getAndroidVersion()
	{
		return "Android " + Build.VERSION.RELEASE + " (API " + String.valueOf(Build.VERSION.SDK_INT) + ")";
	}

	public static String getManufacturer()
	{
		return Build.MANUFACTURER.toUpperCase();
	}

	public static String getProduct()
	{
		return Build.PRODUCT.toUpperCase();
	}
	
	public static void vibrar(Long miliseconds, Context context)
	{
		Vibrator vibretor = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		vibretor.vibrate(miliseconds);
	}

	public static Locale getLocale(Context context){
		Locale current = context.getResources().getConfiguration().locale;
		return current;
	}
}