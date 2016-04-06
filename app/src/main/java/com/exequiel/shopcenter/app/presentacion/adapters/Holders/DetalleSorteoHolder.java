package com.exequiel.shopcenter.app.presentacion.adapters.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by exequiel on 25/03/2016.
 */
public class DetalleSorteoHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

    private ItemCuponHolder.IMyViewHolderClicks listener;

    public DetalleSorteoHolder(View itemView, ItemCuponHolder.IMyViewHolderClicks listener) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(v);
    }
}
