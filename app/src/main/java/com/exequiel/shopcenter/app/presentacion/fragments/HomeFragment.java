package com.exequiel.shopcenter.app.presentacion.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.app.presentacion.adapters.CustomLinearLayoutManager;
import com.exequiel.shopcenter.app.presentacion.adapters.DetalleComentarioComercioAdapter;
import com.exequiel.shopcenter.app.presentacion.adapters.ItemComercioNuevo;
import com.exequiel.shopcenter.app.presentacion.adapters.WizardInicialAdapter;
import com.exequiel.shopcenter.framework.ui.fragment.FrameworkBaseFragment;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by exequiel on 09/07/2015.
 */
public class HomeFragment extends FrameworkBaseFragment {


    private static final String TAG_FRAGMENT = "HOME_FRAGMENT";

    private static String URL_COLLECTION = "http://kipling.com";

    private boolean flag = false;

    //ViewPager
    CirclePageIndicator titlePageIndicator;
    ViewPager viewPager;
    WizardInicialAdapter wizardInicialAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = onCreateView(inflater, container, savedInstanceState, R.layout.fragment_home);
        //ViewPager
        titlePageIndicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        wizardInicialAdapter = new WizardInicialAdapter(getFragmentManager());
        viewPager.setAdapter(wizardInicialAdapter);
        titlePageIndicator.setViewPager(viewPager);
        //ViewPager

        ItemComercioNuevo recyclerViewAdapter3 = new ItemComercioNuevo();
        RecyclerView recyclerView3 = (RecyclerView) view.findViewById(R.id.recyclerviewlastcomer);
        final CustomLinearLayoutManager layoutManager3 = new CustomLinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        // final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // layoutManager = new LinearLayoutManager(this);
        //final MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false, getScreenHeight(this));
        recyclerView3.setLayoutManager(layoutManager3);
        recyclerView3.setAdapter(recyclerViewAdapter3);
        recyclerView3.setNestedScrollingEnabled(false);
        return view;
    }

    @Override
    public String getTagFragment() {
        return TAG_FRAGMENT;
    }

}
