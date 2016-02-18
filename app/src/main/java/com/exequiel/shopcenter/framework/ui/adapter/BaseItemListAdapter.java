package com.exequiel.shopcenter.framework.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;

import com.exequiel.shopcenter.framework.data.entity.BaseDto;

import java.util.List;

/**
 * Clase que sirve como base para cualquier
 * listado de la aplicacion.
 * Created by exequiel on 02/06/2015.
 */
public abstract class BaseItemListAdapter<T extends BaseDto> extends ArrayAdapter<T> {

    private List<T> dataItems;
    private LayoutInflater inflater;
    private int resourceItem;

    public BaseItemListAdapter(Activity activity, List<T> dataItems, int resourceItem){
        super(activity,resourceItem,dataItems);
        this.dataItems = dataItems;
        Fragment fragment;
        this.inflater = activity.getLayoutInflater();
        this.resourceItem = resourceItem;
    }

    @Override
    public boolean isEmpty() {
        return dataItems == null || dataItems.isEmpty();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        T item = dataItems.get(position);
        BaseHolder holder = null;
        if (convertView != null) {
             holder = (BaseHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(resourceItem, null);
            convertView.setTag(crearHolder(convertView,item));
        }
        holder.setDataInHolder(item,position);
        return convertView;
    }

    public abstract BaseHolder crearHolder(View view,T item);

}
