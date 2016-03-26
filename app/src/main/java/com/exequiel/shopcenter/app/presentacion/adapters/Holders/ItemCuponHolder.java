package com.exequiel.shopcenter.app.presentacion.adapters.Holders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.exequiel.shopcenter.app.presentacion.fragments.DetalleCuponFragment;
import com.exequiel.shopcenter.app.presentacion.fragments.handle.HandleFragmentsActivityGral;

/**
 * Created by exequiel on 25/03/2016.
 */
public class ItemCuponHolder  extends RecyclerView.ViewHolder  implements View.OnClickListener{

    private IMyViewHolderClicks listener;

    public ItemCuponHolder(View itemView, IMyViewHolderClicks listener) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(v);
    }

    public static interface IMyViewHolderClicks {
        public void onItemClick(View caller);
    }
}
