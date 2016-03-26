package com.exequiel.shopcenter.app.presentacion.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.app.presentacion.adapters.Holders.DetalleComercioHoler;
import com.exequiel.shopcenter.app.presentacion.adapters.Holders.ItemCuponHolder;

/**
 * Created by exequiel on 25/03/2016.
 */
public class ItemCuponsAdapter extends RecyclerView.Adapter<ItemCuponHolder>  {

    private ItemCuponHolder.IMyViewHolderClicks listener;

    public ItemCuponsAdapter(ItemCuponHolder.IMyViewHolderClicks listener){
        super();
        this.listener=listener;
    }

    @Override
    public ItemCuponHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cupon,parent,false);
        return new ItemCuponHolder(v, listener);
    }



    @Override
    public void onBindViewHolder(ItemCuponHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
