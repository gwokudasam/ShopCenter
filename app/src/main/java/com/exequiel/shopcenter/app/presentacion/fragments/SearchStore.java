package com.exequiel.shopcenter.app.presentacion.fragments;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.componentes.maps.UtilMap;
import com.exequiel.shopcenter.framework.ui.fragment.FrameworkBaseFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by exequiel on 12/04/2016.
 */
public class SearchStore extends FrameworkBaseFragment {

    private static final String TAG_FRAGMENT = "STORE_LOCATOR_FRAGMENT";

    private SupportMapFragment fragment;
    private GoogleMap mapa;
    private View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView==null){
            rootView = onCreateView(inflater, container, savedInstanceState, R.layout.fragment_search_store);
            getMap();
        }
        if (getArguments()!=null){
            //setRuta(getArguments());
        }
        return rootView;
    }

    private void getMap() {
        fragment = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.store_locator_map));
        if (fragment!=null){
            mapa = fragment.getMap();
            if (mapa!=null){
                initMap();
            }
        }
    }

    private void initMap(){
        mapa.setMyLocationEnabled(true);

    }

    @Override
    public String getTagFragment() {
        return null;
    }
}
