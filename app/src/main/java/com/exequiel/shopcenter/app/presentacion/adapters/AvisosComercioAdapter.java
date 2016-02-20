package com.exequiel.shopcenter.app.presentacion.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.app.presentacion.adapters.Holders.DetalleComercioHoler;

import java.util.List;

/**
 * Created by exequiel on 18/02/2016.
 */
public class AvisosComercioAdapter extends RecyclerView.Adapter<DetalleComercioHoler> {

    private List<Object> listData;

    @Override
    public DetalleComercioHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_detalle_com_avisos,parent,false);
        return new DetalleComercioHoler(v);
    }

    @Override
    public void onBindViewHolder(DetalleComercioHoler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

}
