package com.exequiel.shopcenter.componentes.maps;

import android.app.Activity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 25/08/2015.
 */
public class UtilMap {

    public interface IMarketMapCustomInfo {
        LatLng getLatLon();
        String getSnippet();
        String getTitle();
        BitmapDescriptor getIcon();
    }

    /**
     * Obtiene la direccionn url para el request, con datos específicos
     *
     * @param origin
     * @param dest
     * @param markerPoints
     *            Lista con los markerPoints necesario
     * @return
     */
    public static String getDirectionsUrl(LatLng origin, LatLng dest, ArrayList<? extends IMarketMapCustomInfo> markerPoints)
    {
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        String sensor = "sensor=false";
        String waypoints = "";

        int i=0;
        if (markerPoints!=null){
            for (IMarketMapCustomInfo marketMap : markerPoints){
                if (i==0){
                    waypoints = "waypoints=";
                    i++;
                }
                waypoints += marketMap.getLatLon().latitude + "," + marketMap.getLatLon().longitude + "|";
            }
        }
        String parameters=str_origin + "&" + str_dest + "&" + sensor + "&mode=walking";
        if (waypoints.compareTo("")!=0){
            parameters+="&" + waypoints;
        }
        String output = "json";
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;
        return url;
    }

    public static String getDirectionsUrl(LatLng origin, LatLng dest)
    {
        return getDirectionsUrl(origin,dest,null);
    }

    public static void dibujarRuta(LatLng origin, LatLng dest, GoogleMap map,Activity activity){
        // Getting URL to the Google Directions API
        String url = getDirectionsUrl(origin, dest);
        DownloadTask downloadTask = new DownloadTask(map,activity);
        // Start downloading json data from Google Directions API
        downloadTask.execute(url);
    }
/*
    public static SupportMapFragment getMap(Activity activity){
        SupportMapFragment fragment =null;
        fragment = (SupportMapFragment) activity.getFragmentManager().findFragmentById(R.id.activity_ruta_mapa);
        fragment = ((SupportMapFragment) activity.getSupportFragmentManager().findFragmentById(R.id.activity_ruta_mapa));
        fragment = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.activity_ruta_mapa));
    }*/

    public static void dibujarMarcadores(List<? extends IMarketMapCustomInfo> listMarkets,GoogleMap map)
    {
        int i =0 ;
        for (IMarketMapCustomInfo detalle : listMarkets)
        {
            MarkerOptions options = new MarkerOptions();
            options.position(detalle.getLatLon());
            options.snippet(detalle.getSnippet());
            options.icon(detalle.getIcon());
            options.title(detalle.getTitle());
            if (map!=null){
                map.addMarker(options);
            }
        }
    }

    public static double calculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);
        return Radius * c;
    }

    public static void centrarMapa(LatLng centerPoint, GoogleMap mapa,int cantZoom, int milisVelocity) {
        CameraUpdate center = CameraUpdateFactory.newLatLng(centerPoint);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(cantZoom);
        mapa.moveCamera(center);
        mapa.animateCamera(zoom,milisVelocity,null);
    }
}
