package com.exequiel.shopcenter.app.presentacion.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.framework.ui.fragment.FrameworkBaseFragment;

/**
 * Created by exequiel on 02/04/2016.
 */
public class BuscarComercios extends FrameworkBaseFragment {

    private static final String TAG_FRAGMENT = "BUSCAR_COMERCIOS";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = onCreateView(inflater, container, savedInstanceState, R.layout.fragment_buscar_comercio);
        return view;
    }

    @Override
    public String getTagFragment() {
        return TAG_FRAGMENT;
    }
}
