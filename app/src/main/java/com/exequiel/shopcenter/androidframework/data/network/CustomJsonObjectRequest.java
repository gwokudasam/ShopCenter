package com.exequiel.shopcenter.androidframework.data.network;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by exequiel on 13/07/2015.
 */
public class CustomJsonObjectRequest extends JsonObjectRequest {

    private HashMap headers;

    public CustomJsonObjectRequest(int method, String url, String jsonRequest,Response.Listener listener, Response.ErrorListener errorListener, HashMap headers)
    {
        super(method, url, jsonRequest, listener, errorListener);
        this.headers = headers;
    }

    @Override
    public Map getHeaders() throws AuthFailureError {
        return this.headers;
    }

}
