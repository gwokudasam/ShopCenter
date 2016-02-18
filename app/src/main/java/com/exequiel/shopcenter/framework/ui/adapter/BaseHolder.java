package com.exequiel.shopcenter.framework.ui.adapter;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Administrador on 11/12/2015.
 */
public abstract class BaseHolder<T>{

    private View view;

    public BaseHolder(View itemView){
        ButterKnife.inject(this, itemView);
        this.view = itemView;
    }

    public void setView(View view){
        this.view = view;
    }

    public View getView(){
        return view;
    }

    public abstract void setDataInHolder(T data,int position);
}
