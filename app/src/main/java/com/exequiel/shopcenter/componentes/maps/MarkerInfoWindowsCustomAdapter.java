package com.exequiel.shopcenter.componentes.maps;

import android.app.Activity;
import android.view.View;

import com.exequiel.shopcenter.framework.ui.adapter.BaseHolder;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by exequiel on 26/08/2015.
 * Clase para crear un market personalizado para googleMaps
 */
public abstract class MarkerInfoWindowsCustomAdapter<T> implements GoogleMap.InfoWindowAdapter{

    private Activity activity;
    private int resourceLayoutInfoWindow;

    public MarkerInfoWindowsCustomAdapter(int resourceLayoutInfoWindow, Activity activity){
        this.activity=activity;
        this.resourceLayoutInfoWindow=resourceLayoutInfoWindow;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        //TODO COMPLETAR PARA CONSTRUIR
        //UN MARKET CON BORDES DEL GLOBO
        //PERSONALIZADO TAMBIEN.
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View v = activity.getLayoutInflater().inflate(resourceLayoutInfoWindow,null);
        setDataInMarket(marker.getSnippet(),v);
        return v;
    }

    private void setDataInMarket(String idMarketString, View v){
        int idMarket = Integer.valueOf(idMarketString);
        setDataInHolder(idMarket,v);
    }

    /**
     * Hacer un new del BaseHolder concreto para
     * este adaptador, llamar al constructor vacio
     * para hacer uso de ButterKnife.
     * @return
     */
    public abstract BaseHolder<T> newHolder(View view,T data);
    public abstract void setDataInHolder(int dataOfMaket, View view);

}
