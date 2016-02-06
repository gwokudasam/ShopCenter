package com.exequiel.shopcenter.componentes.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class UtilConectividad {
	
	public static boolean conectadoWifi(Context context){
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (info != null) {
				if (info.isConnected()) {
					return true;
				}
			}
		}
	return false;
	}
	
	public static boolean conectadoRedMovil(Context context){
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
				if (info != null) {
					if (info.isConnected()) {
						return true;
					}
				}
			}
			return false;
		}
	
	public static boolean hayConexionInternet(Context context){
		return (conectadoRedMovil(context) || conectadoWifi(context));
	}
	
}
