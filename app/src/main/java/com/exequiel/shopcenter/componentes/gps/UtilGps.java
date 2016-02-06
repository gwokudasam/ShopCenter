package com.exequiel.shopcenter.componentes.gps;

import android.content.Context;
import android.location.LocationManager;

/**
 * Created by exequiel on 15/09/2015.
 */
public class UtilGps
{
    private static Context context;

    // Declaring a Location Manager
    private static LocationManager locationManager;

    public UtilGps(Context appContext)
    {
        context = appContext;
    }

    public static boolean isGpsProviderEnabled(Context context)
    {
        // Obtener el manejador de ubicaciones
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // Verificamos si el gps está activado
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static boolean isNetworLocationProviderEnabled(Context context)
    {
        // Obtener el manejador de ubicaciones
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // Verificamos si la ubicación por internet está activado
        return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
}