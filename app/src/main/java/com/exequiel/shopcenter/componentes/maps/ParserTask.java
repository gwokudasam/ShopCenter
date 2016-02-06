package com.exequiel.shopcenter.componentes.maps;

/**
 * Created by exequiel on 26/08/2015.
 */

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** A class to parse the Google Places in JSON format */
public class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>>
{
    private GoogleMap map;
    private Activity activity;

    public ParserTask(GoogleMap map,Activity activity){
        this.map = map;
        this.activity = activity;
    }

    // Parsing the data in non-ui thread
    @Override
    protected List<List<HashMap<String, String>>> doInBackground(String... jsonData)
    {
        JSONObject jObject;
        List<List<HashMap<String, String>>> routes = null;
        try
        {
            jObject = new JSONObject(jsonData[0]);
            DirectionsJSONParser parser = new DirectionsJSONParser();
            // Starts parsing data
            routes = parser.parse(jObject);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return routes;
    }

    // Executes in UI thread, after the parsing process
    @Override
    protected void onPostExecute(List<List<HashMap<String, String>>> result)
    {
        if(result == null)
            return;

        ArrayList<LatLng> points = null;
        PolylineOptions lineOptions = null;


        //Declaro un Builder para crear el CameraUpdate y así centrar el mapa
        //de forma tal que se vea toda la ruta
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        // Traversing through all the routes
        for (int i = 0; i < result.size(); i++)
        {
            points = new ArrayList<LatLng>();
            lineOptions = new PolylineOptions();

            // Fetching i-th route
            List<HashMap<String, String>> path = result.get(i);

            // Fetching all the points in i-th route
            for (int j = 0; j < path.size(); j++)
            {
                HashMap<String, String> point = path.get(j);
                double lat = Double.parseDouble(point.get("lat"));
                double lng = Double.parseDouble(point.get("lng"));
                LatLng position = new LatLng(lat, lng);
                //agrego position para crear el CameraUpdate
                builder.include(position);
                points.add(position);
            }
            // Adding all the points in the route to LineOptions
            lineOptions.addAll(points);
            lineOptions.width(5);
            lineOptions.color(Color.BLACK);
        }
        LatLngBounds bounds = builder.build();
        if (lineOptions!=null){
            map.addPolyline(lineOptions);
            if (bounds!=null){
                int padding = 40; // offset from edges of the map in pixels
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                map.animateCamera(cu);
            }else if (points!=null)
                UtilMap.centrarMapa(points.get(0), map, 10, 3000);
        }else{
            Toast.makeText(activity, "Error calculando ruta", Toast.LENGTH_LONG).show();
        }
    }
}
