package com.exequiel.shopcenter.app.presentacion.adapters.Holders;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.app.presentacion.activitys.DetalleComercioActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by exequiel on 18/02/2016.
 */
public class DetalleComercioHoler extends RecyclerView.ViewHolder  implements View.OnClickListener {

    private CircleImageView imgComercio;

    public DetalleComercioHoler(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);

        imgComercio = (CircleImageView) itemView.findViewById(R.id.last_comercio_circle_img);
    }

    @Override
    public void onClick(View v) {
        imgComercio.setBorderColorResource(R.color.primary);
        Intent intent = new Intent(itemView.getContext(), DetalleComercioActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        itemView.getContext().getApplicationContext().startActivity(intent);
    }

}
