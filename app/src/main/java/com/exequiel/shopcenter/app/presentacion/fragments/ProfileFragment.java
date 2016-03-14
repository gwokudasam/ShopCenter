package com.exequiel.shopcenter.app.presentacion.fragments;

import android.graphics.Color;
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
import com.exequiel.shopcenter.app.presentacion.adapters.ItemComercioNuevo;
import com.exequiel.shopcenter.app.presentacion.adapters.WizardInicialAdapter;
import com.exequiel.shopcenter.framework.ui.fragment.FrameworkBaseFragment;
import com.exequiel.shopcenter.framework.ui.view.ProportionalImageView;
import com.viewpagerindicator.CirclePageIndicator;

import jp.wasabeef.blurry.Blurry;

/**
 * Created by exequiel on 10/03/2016.
 */
public class ProfileFragment extends FrameworkBaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = onCreateView(inflater, container, savedInstanceState, R.layout.fragment_profile);
        Blurry.with(getActivity())
                .radius(25)
                .sampling(1)
                .color(Color.argb(66, 0, 255, 255))
                .async()
                .capture(view.findViewById(R.id.circle_image_view))
                .into((ImageView) view.findViewById(R.id.circle_image_view));
        return view;
    }

    @Override
    public String getTagFragment() {
        return null;
    }
}
