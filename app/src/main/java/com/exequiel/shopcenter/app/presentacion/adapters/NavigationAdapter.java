package com.exequiel.shopcenter.app.presentacion.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.exequiel.shopcenter.R;

import java.util.ArrayList;

public class NavigationAdapter extends BaseAdapter {
    private Activity activity;
    ArrayList<ItemObject> arrayitms;
    LayoutInflater inflator;

    public NavigationAdapter(Activity activity, ArrayList<ItemObject> listarry) {
        super();
        this.activity = activity;
        this.arrayitms = listarry;
        this.inflator = activity.getLayoutInflater();
    }

    @Override
    public Object getItem(int position) {
        return arrayitms.get(position);
    }

    public int getCount() {
        return arrayitms.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class NavigationItem {
        TextView title;
        ImageView icon;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        NavigationItem view;
        ItemObject itm = arrayitms.get(position);

        if (convertView == null) {
            view = new NavigationItem();
            convertView = inflator.inflate(R.layout.item_menu, null);

            view.title = (TextView) convertView.findViewById(R.id.drawer_nav_title);
            view.icon = (ImageView) convertView.findViewById(R.id.drawer_nav_icon);;

            convertView.setTag(view);
        }
        else {
            view = (NavigationItem) convertView.getTag();
        }

        view.title.setText(itm.getTitulo());
        view.icon.setImageResource(itm.getIcono());
        return convertView;
    }
}
