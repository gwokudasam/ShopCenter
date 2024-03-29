package com.exequiel.shopcenter.framework.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Administrador on 01/12/2015.
 */
public abstract class FrameworkBaseFragment extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState, int idResources) {
        View view = inflater.inflate(idResources, container, false);
        ButterKnife.inject(this, view);
        return view;
    }
    public abstract String getTagFragment();


}
