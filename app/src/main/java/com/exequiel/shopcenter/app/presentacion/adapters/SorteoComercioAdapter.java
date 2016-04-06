package com.exequiel.shopcenter.app.presentacion.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.app.presentacion.adapters.Holders.DetalleProductoHolder;
import com.exequiel.shopcenter.app.presentacion.adapters.Holders.DetalleSorteoHolder;
import com.exequiel.shopcenter.app.presentacion.adapters.Holders.ItemCuponHolder;

import java.util.List;

/**
 * Created by exequiel on 18/02/2016.
 */
public class SorteoComercioAdapter extends RecyclerView.Adapter<DetalleSorteoHolder> {

    private List<Object> listData;
    private ItemCuponHolder.IMyViewHolderClicks listener;

    public SorteoComercioAdapter(ItemCuponHolder.IMyViewHolderClicks listener){
        super();
        this.listener=listener;
    }

    @Override
    public DetalleSorteoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comercio_detalle_sorteo,parent,false);
        return new DetalleSorteoHolder(v,listener);
    }

    @Override
    public void onBindViewHolder(DetalleSorteoHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

}
