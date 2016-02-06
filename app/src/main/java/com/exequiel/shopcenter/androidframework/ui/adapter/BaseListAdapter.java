package com.exequiel.shopcenter.androidframework.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.exequiel.shopcenter.androidframework.data.local.BaseDto;

import java.util.List;

/**
 * Created by exequiel on 02/06/2015.
 */
public abstract class BaseListAdapter<T extends BaseDto> extends ArrayAdapter<T> {

    private List<T> dataItems;
    private LayoutInflater inflater;
    private int resourceItem;

    public BaseListAdapter(Activity activity, List<T> dataItems, int resourceItem){
        super(activity,resourceItem,dataItems);
        this.dataItems = dataItems;
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
        if (convertView != null) {
            BaseHolder holder = (BaseHolder) convertView.getTag();
            holder.setearDatosInHolder(item);
        } else {
            convertView = inflater.inflate(resourceItem, null);
            convertView.setTag(crearHolder(convertView,item));
        }
        return convertView;
    }

    public abstract BaseHolder crearHolder(View view,T item);

}
