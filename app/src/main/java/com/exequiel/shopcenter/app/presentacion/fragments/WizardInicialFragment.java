package com.exequiel.shopcenter.app.presentacion.fragments;

import android.content.Context;
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
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WizardInicialFragment extends Fragment {


    private TextView lblTitulo;
    private TextView lblSubTitulo;
    private ImageView imgView;
    private Button btnNext;

    private static final String NRO_FRAGMENT = "nro_fragment";
    private int nroFragment;

    public static WizardInicialFragment newInstance(int nroFragment) {
        WizardInicialFragment fragment = new WizardInicialFragment();   //instanciamos un nuevo fragment
        Bundle args = new Bundle();                                 //guardamos los parametros
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
        View view = inflater.inflate(R.layout.fragment_wizard_inicial, container, false);
        lblTitulo = (TextView) view.findViewById(R.id.fragment_wizard_title);
        lblSubTitulo = (TextView) view.findViewById(R.id.fragment_wizard_subtitle);
        imgView = (ImageView) view.findViewById(R.id.wizard_fragment_image_view);
        return view;
    }

}
