package com.exequiel.shopcenter.app.presentacion.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.app.presentacion.adapters.Holders.DetalleComercioHoler;
import com.exequiel.shopcenter.app.presentacion.adapters.Holders.DetalleProductoHolder;
import com.exequiel.shopcenter.app.presentacion.adapters.Holders.ItemCuponHolder;

import java.util.List;

/**
 * Created by exequiel on 18/02/2016.
 */
public class ProductosComercioAdapter extends RecyclerView.Adapter<DetalleProductoHolder> {

    private List<Object> listData;
    private ItemCuponHolder.IMyViewHolderClicks listener;

    public ProductosComercioAdapter(ItemCuponHolder.IMyViewHolderClicks listener){
        super();
        this.listener=listener;
    }

    @Override
    public DetalleProductoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_detalle_com_destacado,parent,false);
        return new DetalleProductoHolder(v,listener);
    }

    @Override
    public void onBindViewHolder(DetalleProductoHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

}
