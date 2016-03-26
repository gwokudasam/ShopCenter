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
import com.exequiel.shopcenter.app.presentacion.adapters.Holders.ItemMenuLateralHolder;
import com.exequiel.shopcenter.app.presentacion.adapters.ItemComercioNuevo;
import com.exequiel.shopcenter.app.presentacion.adapters.WizardInicialAdapter;
import com.exequiel.shopcenter.framework.ui.fragment.FrameworkBaseFragment;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by exequiel on 09/07/2015.
 */
public class HomeFragment extends FrameworkBaseFragment {


    private static final String TAG_FRAGMENT = "HOME_FRAGMENT";

    //ViewPager
    @InjectView(R.id.indicator)
    protected CirclePageIndicator titlePageIndicator;

    @InjectView(R.id.pager)
    protected ViewPager viewPager;

    RecyclerView recyclerView3;

    private WizardInicialAdapter wizardInicialAdapter;


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
        recyclerView3 = (RecyclerView) view.findViewById(R.id.recyclerviewlastcomer);
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
    public void onResume() {
        super.onResume();
        deseleccionarItems();
    }

    private void deseleccionarItems(){
        CircleImageView imgComercio;
        int childCount = recyclerView3.getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            View v = recyclerView3.getChildAt(i);
            imgComercio = (CircleImageView) v.findViewById(R.id.last_comercio_circle_img);
            imgComercio.setBorderColorResource(android.R.color.white);
        }
    }

    @Override
    public String getTagFragment() {
        return TAG_FRAGMENT;
    }

}
