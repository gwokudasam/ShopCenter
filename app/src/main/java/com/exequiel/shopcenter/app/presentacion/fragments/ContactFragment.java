package com.exequiel.shopcenter.app.presentacion.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.app.presentacion.adapters.AgendaAdapter;
import com.exequiel.shopcenter.app.presentacion.adapters.CustomLinearLayoutManager;
import com.exequiel.shopcenter.framework.ui.fragment.FrameworkBaseFragment;

/**
 * Created by exequiel on 24/03/2016.
 */
public class ContactFragment extends FrameworkBaseFragment {

    String TAG_FRAGMENT = "contac_fragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = onCreateView(inflater, container, savedInstanceState, R.layout.fragment_contact);
        return view;
    }

    @Override
    public String getTagFragment() {
        return TAG_FRAGMENT;
    }

}
