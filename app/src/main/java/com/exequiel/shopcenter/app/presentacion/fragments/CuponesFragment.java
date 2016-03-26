package com.exequiel.shopcenter.app.presentacion.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.app.presentacion.adapters.AgendaAdapter;
import com.exequiel.shopcenter.app.presentacion.adapters.CustomLinearLayoutManager;
import com.exequiel.shopcenter.app.presentacion.adapters.Holders.ItemCuponHolder;
import com.exequiel.shopcenter.app.presentacion.adapters.ItemCuponsAdapter;
import com.exequiel.shopcenter.app.presentacion.fragments.handle.HandleFragmentsActivityGral;
import com.exequiel.shopcenter.framework.ui.fragment.FrameworkBaseFragment;

/**
 * Created by exequiel on 24/03/2016.
 */
public class CuponesFragment extends FrameworkBaseFragment {

    private static final String TAG_FRAGMENT = "CUPONS_FRAGMENT";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = onCreateView(inflater, container, savedInstanceState, R.layout.fragment_list_cupon);

        ItemCuponsAdapter recyclerViewAdapter1 = new ItemCuponsAdapter(new ItemCuponHolder.IMyViewHolderClicks() {
            @Override
            public void onItemClick(View caller) {
                HandleFragmentsActivityGral.changeFragment(DetalleCuponFragment.class,getFragmentManager());
                Log.i("Cupon clicks","cuponss");
            }
        });
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
