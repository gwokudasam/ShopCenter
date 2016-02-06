package com.exequiel.shopcenter.androidframework.data.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class VolleySingleton {
    private static VolleySingleton instance = null;
    private RequestQueue mRequestQueque;

    private VolleySingleton(Context context){
        mRequestQueque= Volley.newRequestQueue(context);
    }

    public static VolleySingleton getInstance(Context context){
        if (instance == null){
            instance = new VolleySingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueque(){
        return mRequestQueque;
    }
}
