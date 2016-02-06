package com.exequiel.shopcenter.androidframework.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Administrador on 01/12/2015.
 */
public class BaseFragment extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState, int idResources) {
        View view = inflater.inflate(idResources, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

}
