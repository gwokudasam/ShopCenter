package com.exequiel.shopcenter.androidframework.ui.adapter;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by exequiel on 26/08/2015.
 * BaseHolder con ButterKnife
 */

public abstract class BaseHolder<T>{
    private T data;
    private View view;

    public BaseHolder(View view, T data){
        ButterKnife.inject(this,view);
        this.data = data;
        this.view = view;
        setearDatosInHolder(data);
    }

    public void setView(View view){
        this.view = view;
    }

    public View getView(){
        return view;
    }

    public T getData(){
        return data;
    }

    public abstract void setearDatosInHolder(T data);
}
