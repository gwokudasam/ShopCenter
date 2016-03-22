package com.exequiel.shopcenter.app.presentacion.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.app.presentacion.adapters.CustomLinearLayoutManager;
import com.exequiel.shopcenter.app.presentacion.adapters.DetalleComercioAdapter;
import com.exequiel.shopcenter.framework.ui.fragment.FrameworkBaseFragment;

/**
 * Created by exequiel on 21/03/2016.
 */
public class ListProductsFragment extends FrameworkBaseFragment {

    private static final String TAG_FRAGMENT = "AGENDA_FRAGMENT";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = onCreateView(inflater, container, savedInstanceState, R.layout.list_productos);

        DetalleComercioAdapter recyclerViewAdapter1 = new DetalleComercioAdapter();
        RecyclerView recyclerView1 = (RecyclerView) view.findViewById(R.id.recycler_products);
        recyclerView1.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView1.setAdapter(recyclerViewAdapter1);
        recyclerView1.setNestedScrollingEnabled(false);
        return view;
    }

    @Override
    public String getTagFragment() {
        return TAG_FRAGMENT;
    }
}
