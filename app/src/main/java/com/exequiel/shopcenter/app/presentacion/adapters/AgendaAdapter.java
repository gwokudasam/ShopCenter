package com.exequiel.shopcenter.app.presentacion.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.app.presentacion.adapters.Holders.DetalleComercioHoler;

/**
 * Created by exequiel on 21/03/2016.
 */
public class AgendaAdapter extends RecyclerView.Adapter<DetalleComercioHoler> {

    @Override
    public DetalleComercioHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_agenda,parent,false);
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
