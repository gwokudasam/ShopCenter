package com.exequiel.shopcenter.app.presentacion.adapters.Holders;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.app.presentacion.activitys.DetalleComercioActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by exequiel on 25/03/2016.
 */
public class DetalleProductoHolder  extends RecyclerView.ViewHolder  implements View.OnClickListener {

    private ItemCuponHolder.IMyViewHolderClicks listener;

    public DetalleProductoHolder(View itemView, ItemCuponHolder.IMyViewHolderClicks listener) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(v);
    }
}
