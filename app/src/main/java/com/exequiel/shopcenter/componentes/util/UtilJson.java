package com.exequiel.shopcenter.componentes.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by exequiel on 26/08/2015.
 */
public class UtilJson {

    private static <R> Gson getGson(){
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return builder.create();
    }

    public static <R> R parseJson(String json, Class<R> type){
        Gson gson = getGson();
        return gson.fromJson(json, type);
    }
}
