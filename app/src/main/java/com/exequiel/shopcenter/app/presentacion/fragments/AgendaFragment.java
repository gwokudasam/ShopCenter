package com.exequiel.shopcenter.app.presentacion.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.app.presentacion.adapters.AgendaAdapter;
import com.exequiel.shopcenter.app.presentacion.adapters.CustomLinearLayoutManager;
import com.exequiel.shopcenter.app.presentacion.adapters.DetalleComercioAdapter;
import com.exequiel.shopcenter.app.presentacion.adapters.ItemComercioNuevo;
import com.exequiel.shopcenter.app.presentacion.adapters.WizardInicialAdapter;
import com.exequiel.shopcenter.framework.ui.fragment.FrameworkBaseFragment;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by exequiel on 20/03/2016.
 */
public class AgendaFragment extends FrameworkBaseFragment {

    private static final String TAG_FRAGMENT = "AGENDA_FRAGMENT";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = onCreateView(inflater, container, savedInstanceState, R.layout.list_agenda);

        AgendaAdapter recyclerViewAdapter1 = new AgendaAdapter();
        RecyclerView recyclerView1 = (RecyclerView) view.findViewById(R.id.recycler_agenda);
        final CustomLinearLayoutManager layoutManager1 = new CustomLinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setAdapter(recyclerViewAdapter1);
        recyclerView1.setNestedScrollingEnabled(false);
        return view;
    }

    @Override
    public String getTagFragment() {
        return TAG_FRAGMENT;
    }
}
