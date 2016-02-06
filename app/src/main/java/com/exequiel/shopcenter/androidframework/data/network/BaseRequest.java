package com.exequiel.shopcenter.androidframework.data.network;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by exequiel on 01/06/2015.
 */
public abstract class BaseRequest {

    public static enum TipoRequest{JSON_OBJECT_REQUEST,JSON_ARRAY_REQUEST,IMAGE_REQUEST,CUSTOM_JSON_OBJECT_REQUEST};

    private TipoRequest mTipo;
    private Context mContext;

    private String urlWebServiceFinal=null;

    private ProgressDialog progress;
    private Activity activity;
    private boolean visible;
    private String msjProgress;

    /**
     * Pasar por parámetro los campos obligatorios en las clases especializadas
     * de lo contrario el método fallará, es un request no visible en segundo plano
     * @param tipo
     *  Tipo de request que se esta elaborando, imagen Json simple, Lista etc.
     * @param context
     * El contexto de la app
     * @param posFijoUrlDefecto
     * El postFijo del webService por lo gral el nombre del controller
     * @param urlWebServiceAlternativo
     * Url del webService completa por si se desea conectar con un webService alternativo al
     * por defecto, si es esta el caso pasar null en el parametro posFijo en caso contrario
     * pasar null en urlWebServiceAlternativo
     */
    protected BaseRequest(TipoRequest tipo, Context context, String posFijoUrlDefecto,
                          String urlWebServiceAlternativo){
        mTipo=tipo;
        this.mContext =context;
        if (posFijoUrlDefecto!=null && getUrlDefaultService()!=null && urlWebServiceAlternativo==null){
            this.urlWebServiceFinal = getUrlDefaultService() + posFijoUrlDefecto;
        }else if (urlWebServiceAlternativo!=null){
            this.urlWebServiceFinal = urlWebServiceAlternativo;
        }//TODO si no hay post fijo debería saltar una excepcion
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    protected BaseRequest(TipoRequest tipo, Activity activity, String posFijoUrlDefecto,
                          String urlWebServiceAlternativo, boolean visible, String msjProgress){
        mTipo=tipo;
        if (posFijoUrlDefecto!=null && getUrlDefaultService()!=null && urlWebServiceAlternativo==null){
            this.urlWebServiceFinal = getUrlDefaultService() + posFijoUrlDefecto;
        }else if (urlWebServiceAlternativo!=null){
            this.urlWebServiceFinal = urlWebServiceAlternativo;
        }
        this.activity=activity;
        mContext = activity;
        this.visible=visible;
        this.msjProgress=msjProgress;
    }

    //TODO sobrecargar metodos put,post,delete ...

    private void executeRequest(int verbo,Object obReq,HashMap headers){
        if (headers==null && mTipo == TipoRequest.CUSTOM_JSON_OBJECT_REQUEST){
            //TODO lanzar excepcion
        }
        Request req = crearRequest(mTipo,verbo,obReq,headers);
        addRequestToQueque(req);
        if (visible){
            this.progress = new ProgressDialog(activity);
            this.progress.setCancelable(false);
            if (msjProgress!=null)
                this.progress.setMessage(msjProgress);
            this.progress.show();
        }
    }

    /**
     * Si el Request no es del tipo Custom enviar null en el parametro
     * header
     * @param obReq
     * @param headers
     */
    public void executePost(Object obReq,HashMap headers){
        executeRequest(Request.Method.POST, obReq, headers);
    }

    /**
     * Si el Request no es del tipo Custom enviar null en el parametro
     * header
     * @param obReq
     * @param headers
     */
    public void executeGet(Object obReq,HashMap headers){
        executeRequest(Request.Method.GET,obReq,headers);
    }

    /**
     * Si el Request no es del tipo Custom enviar null en el parametro
     * header
     * @param obReq
     * @param headers
     */
    public void executePut(Object obReq,HashMap headers){
        executeRequest(Request.Method.PUT,obReq,headers);
    }

    /**
     * Si el Request no es del tipo Custom enviar null en el parametro
     * header
     * @param obReq
     * @param headers
     */
    public void executeDelete(Object obReq,HashMap headers){
        executeRequest(Request.Method.DELETE,obReq,headers);
    }

    private void addRequestToQueque(Request request){
        if (request != null) {
            request.setTag(this);
            RetryPolicy policy = new DefaultRetryPolicy(getSocketTimeOut(),
                    getNumeroDeReintentos(),DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            request.setRetryPolicy(policy);
            VolleySingleton.getInstance(mContext).getRequestQueque().add(request);
        }
    }

    private void setOffProgress(){
        if (visible){
            progress.dismiss();
        }
    }

    private JsonObjectRequest getJsonObjectRequest(int method,Object obReq){
        String jsonEntity = null;
        if (obReq!=null)
            jsonEntity = toJson(obReq);
        JsonObjectRequest resp = new JsonObjectRequest(method
                , urlWebServiceFinal
                ,jsonEntity
                ,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                onResponseListener(response);
                setOffProgress();
            }
        }
                , new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                onErrorListener(error);
                setOffProgress();
            }
        });
        return resp;
    }

    private CustomJsonObjectRequest getCustomJsonObjectRequest(int method,Object obReq,HashMap headers){
        String jsonEntity = null;
        if (obReq!=null)
            jsonEntity = toJson(obReq);
        CustomJsonObjectRequest resp = new CustomJsonObjectRequest(method
                , urlWebServiceFinal
                ,jsonEntity
                ,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                onResponseListener(response);
                setOffProgress();
            }
        }
                , new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                onErrorListener(error);
                setOffProgress();
            }
        },headers);
        return resp;
    }

    private JsonArrayRequest getJsonArrayRequest(int method,Object obReq){
        String jsonEntity = null;
        if (obReq!=null)
            jsonEntity = toJson(obReq);
        JsonArrayRequest resp = new JsonArrayRequest(method
                , urlWebServiceFinal
                ,jsonEntity
                ,new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {
                onResponseListener(response);
                setOffProgress();
            }
        }
                , new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                onErrorListener(error);
                setOffProgress();
            }
        });
        return resp;
    }

    private ImageRequest getImageRequest(int method){
        ImageRequest request = new ImageRequest(
                urlWebServiceFinal,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        onResponseListener(bitmap);
                        setOffProgress();
                    }
                }, 1000, 1000, null,null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        onErrorResponse(error);
                        setOffProgress();
                    }
                });
        return request;
    }

    private Request crearRequest(TipoRequest tipo, int method, Object obReq, HashMap headers){
        Request req = null;
        switch (tipo){
            case JSON_OBJECT_REQUEST:
                req = getJsonObjectRequest(method, obReq);
                break;
            case IMAGE_REQUEST:
                //TODO tERMINAR ESTA IMPLEMENTACIÓN VERIFICAR COMO FUNCIONA SEGÚN LA DOCUMENTACIÓN
                req = getImageRequest(method);
                break;
            case JSON_ARRAY_REQUEST:
                req = getJsonArrayRequest(method,obReq);
                break;
            case CUSTOM_JSON_OBJECT_REQUEST:
                req = getCustomJsonObjectRequest(method,obReq,headers);
        }
        return req;
    }

    //TODO evaluar si es mejor enfoque mover a BaseDto o como utilJson

    private <R> Gson getGson(){
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return builder.create();
    }

    protected <R> R parseJson(String json, Class<R> type){
        Gson gson = getGson();
        return gson.fromJson(json, type);
    }


    protected String toJson(Object obj){
        Gson gson = getGson();
        return gson.toJson(obj);
    }

    public abstract void onErrorListener(VolleyError volleyError);
    public abstract void onResponseListener(Object response );
    public abstract String getUrlDefaultService();
    public abstract int getNumeroDeReintentos();
    public abstract int getSocketTimeOut();
}
