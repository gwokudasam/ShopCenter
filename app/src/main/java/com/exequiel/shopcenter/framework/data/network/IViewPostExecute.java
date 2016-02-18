package com.exequiel.shopcenter.framework.data.network;

import android.app.Activity;

/**
 * Created by exequiel on 14/07/2015.
 */
public interface IViewPostExecute {
    Activity getActivity();
    void onPostExecuteSusefull(Object response);
    void onPostExecuteError(int statusCode);
}
