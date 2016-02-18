package com.exequiel.shopcenter.app.data;

import android.content.Context;
import android.content.SharedPreferences;

public class UtilPreferences
{

   // example name key private static final String KEY_FIRST_OPEN_APP = "first_open_app";
    private static String NAME_APP="";


    private static SharedPreferences getSharedPreferences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(NAME_APP, Context.MODE_PRIVATE);
        return preferences;
    }

    /* ejemplo de como usar las preferences con dto
    public static void setCustomerOrders(Context context, @Nullable GetCustomerOrdersDto dto) {
        String json = "";
        if (dto!=null)
            json = UtilGson.toJson(dto);
        getSharedPreferences(context).edit().putString(KEY_ORDERS, json).commit();
    }

    public static @Nullable
    GetCustomerOrdersDto getCustomerOrdersDto(Context context){
        String json = getSharedPreferences(context).getString(KEY_ORDERS, "");
        GetCustomerOrdersDto dtoResp;
        if (json.compareTo("")==0)
            dtoResp=null;
        else
            dtoResp = UtilGson.parseJson(json, GetCustomerOrdersDto.class);
        return dtoResp;
    }*/

}
