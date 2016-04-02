package com.exequiel.shopcenter.app.presentacion.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.exequiel.shopcenter.R;

/**
 * Created by exequiel on 31/03/2016.
 */
public class ItemImageViewPagerFragment extends Fragment {

    private static final String NRO_FRAGMENT = "nro_fragment";
    private int nroFragment;

    public static ItemImageViewPagerFragment newInstance(int nroFragment) {
        ItemImageViewPagerFragment fragment = new ItemImageViewPagerFragment();
        Bundle args = new Bundle();
        args.putInt(NRO_FRAGMENT, nroFragment);
        fragment.setArguments(args);
        //fragment.setRetainInstance(true);     //agrego para que no se pierda los valores de la instancia
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            this.nroFragment = getArguments().getInt(NRO_FRAGMENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_item_preimg_product, container, false);
        return view;
    }

}
