package com.exequiel.shopcenter.app.presentacion.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.app.presentacion.fragments.handle.HandleFragmentsActivityGral;
import com.exequiel.shopcenter.componentes.util.UtilConectividad;

/**
 * Created by exequiel on 10/09/2015.
 */
public class SocialTabFragment extends BasicTabBarFragment implements BasicTabBarFragment.IBasicTabBarCustomFragment  {

    private static final String TAG_FRAGMENT = "SOCIAL_TAB_FRAGMENT";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = onCreateView(inflater,container,savedInstanceState, R.layout.fragment_tabs_social);
        setTabs(this,view);
        return view;
    }

    @Override
    public int getCountTabs() {
        return 3;
    }

    @Override
    public String[] getTitles() {
        Activity activity = getActivity();
        String[] titles = {"FACEBOOK","INSTAGRAM","TWITTER"};
        return titles;
    }
    @Override
    public void onTabChanged(int pestania) {
        switch (pestania){
            case 0 :{
                if(UtilConectividad.hayConexionInternet(getActivity()))
                    HandleFragmentsActivityGral.putWebFragment(getFragmentManager(), "https://m.facebook.com/jeanscentre.nl", R.id.fragment_tabs_social_contenedor_tab1);
                else
                    Toast.makeText(getActivity(),"Sin conexión a internet",Toast.LENGTH_SHORT).show();
                break;
            }
            case 1 :{
                if(UtilConectividad.hayConexionInternet(getActivity()))
                    HandleFragmentsActivityGral.putWebFragment(getFragmentManager(), "https://instagram.com/jeanscentre/", R.id.fragment_tabs_social_contenedor_tab2);
                else
                    Toast.makeText(getActivity(),"Sin conexión a internet",Toast.LENGTH_SHORT).show();
                break;
            }
            case 2 :{
                if(UtilConectividad.hayConexionInternet(getActivity()))
                    HandleFragmentsActivityGral.putWebFragment(getFragmentManager(), "https://twitter.com/jeanscentre", R.id.fragment_tabs_social_contenedor_tab3);
                else
                    Toast.makeText(getActivity(),"Sin conexión a internet",Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    @Override
    public int[] getResourcesTab() {
        int[] tabs = {R.id.fragment_tabs_social_tab1, R.id.fragment_tabs_social_tab2, R.id.fragment_tabs_social_tab3};
        return tabs;
    }

    @Override
    public String getTagFragment() {
        return TAG_FRAGMENT;
    }
}
