package com.exequiel.shopcenter.componentes.gps;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Date;

/**
 * Created by exequiel on 15/09/2015.
 */

    public class GPSTracker extends Service implements LocationListener
    {

        private final Context mContext;

        // flag for GPS status
        boolean isGPSEnabled = false;

        // flag for network status
        boolean isNetworkEnabled = false;

        // flag for GPS status
        boolean canGetLocation = false;

        public static Location lastLocation = null;

        private static Date FH_ULTIMA_ACTUALIZACION = null;

        public static Date getFhUltimaActualizacion()throws GpsChangedException {
            if (FH_ULTIMA_ACTUALIZACION==null) {
                throw new GpsChangedException("Sin ubicación registrada al momento");
            }else
                return FH_ULTIMA_ACTUALIZACION;
        }

        private double latitude;
        private double longitude;

        private static long MIN_DISTANCE_CHANGE_FOR_UPDATES;
        private static long MIN_DISTANCE_REAL;

        private static long MIN_TIME_BW_UPDATES;

        protected LocationManager locationManager;


        public static void setTimeBWUpdate(long miliseconds){
            MIN_TIME_BW_UPDATES = miliseconds;
        }

        public static void setMinDistanceReal(long meters){
            MIN_DISTANCE_REAL = meters;
        }

        public static void setMinDistanceChangeForUpdate(long meters){
            MIN_DISTANCE_CHANGE_FOR_UPDATES = meters;
        }

        public GPSTracker(Context context)
        {
            this.mContext = context;
            iniciarGpstraker();
        }

        private void iniciarObjetos()
        {
            // Obtener el manejador de ubicaciones
            locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);

            // Verificamos si el gps está activado
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            // Verificamos si hay conexion a internet
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }

        /**
         * Inicializa el servicio usando el Gps
         *
         * @return true si el gps esta activado false si el gps no está activado
         */
        private boolean iniciarServiceConGps()
        {
            if (isGPSEnabled)
            {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES,this);
                return true;
            }
            else
                return false;
        }

        /**
         * Inicializa el servicio usando la network
         *
         * @return true si el gps esta activado false si el gps no está activado
         */
        private boolean iniciarServiceConNetwork()
        {
            if (isNetworkEnabled)
            {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                return true;
            }
            else
                return false;
        }

        public Location getLastLocation()
        {
            return lastLocation;
        }

        public static String getStringLocation(){
            String loc ="";
            if (lastLocation!=null) {
                loc+=lastLocation.getLatitude();
                loc+=lastLocation.getLongitude();
            }
            return loc;
        }

        private void iniciarGpstraker()
        {
            iniciarObjetos();
            iniciarServiceConGps();
        }


        /**
         * Function to get latitude
         * */
        public double getLatitude()
        {
            if (lastLocation != null)
            {
                latitude = lastLocation.getLatitude();
            }

            // return latitude
            return latitude;
        }

        /**
         * Function to get longitude
         * */
        public double getLongitude()
        {
            if (lastLocation != null)
            {
                longitude = lastLocation.getLongitude();
            }

            // return longitude
            return longitude;
        }

        /**
         * Function to check GPS/wifi enabled
         *
         * @return boolean
         * */
        public boolean canGetLocation()
        {
            return this.canGetLocation;
        }


        @Override
        public void onLocationChanged(Location location)
        {

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
    }

