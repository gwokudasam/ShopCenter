package com.exequiel.shopcenter.componentes.util;

import java.util.HashMap;

/**
 * Created by exequiel on 20/08/2015.
 */
public class UtilRest {

    public static HashMap<String,String> getTokenHeader(String token){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("X-Token", token);
        return hashMap;
    }
}
