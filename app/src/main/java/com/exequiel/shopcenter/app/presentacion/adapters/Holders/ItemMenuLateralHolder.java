package com.exequiel.shopcenter.app.presentacion.adapters.Holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.exequiel.shopcenter.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by exequiel on 24/03/2016.
 */
public class ItemMenuLateralHolder {

    @InjectView(R.id.drawer_nav_title)
    protected TextView titulo;

    @InjectView(R.id.drawer_nav_badge)
    protected TextView badge;

    @InjectView(R.id.drawer_nav_icon)
    protected ImageView icon;

    @InjectView(R.id.order_item_layout_color)
    protected LinearLayout statusBar;

    @InjectView(R.id.menu_item)
    protected LinearLayout linearGral;

    private View view;


    public ItemMenuLateralHolder(View view){
        ButterKnife.inject(this, view);
        this.view = view;
    }


    public void setTitulo(String titulo) {
        this.titulo.setText(titulo);
    }

    public void setBadge(int cant) {
        if (cant!=0){
            this.badge.setVisibility(View.VISIBLE);
            this.badge.setText(String.valueOf(cant));
        }
        else
            this.badge.setVisibility(View.GONE);
    }

    public void setItemSelected(){
        statusBar.setBackgroundColor(view.getResources().getColor(R.color.primary));
        linearGral.setBackgroundColor(view.getResources().getColor(R.color.color_menu_negro_selected_bg));
        titulo.setTextColor(view.getResources().getColor(android.R.color.white));
    }

    public void setItemUnselect(){
        statusBar.setBackgroundColor(view.getResources().getColor(android.R.color.transparent));
        linearGral.setBackgroundColor(view.getResources().getColor(R.color.color_menu_negro_bg));
        titulo.setTextColor(view.getResources().getColor(R.color.color_menu_txt_color));
    }

    public void setImageResource(int imageResource) {
        this.icon.setImageResource(imageResource);
    }
}
